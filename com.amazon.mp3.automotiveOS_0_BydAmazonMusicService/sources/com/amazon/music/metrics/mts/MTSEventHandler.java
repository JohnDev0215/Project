package com.amazon.music.metrics.mts;

import com.amazon.music.file.FileAccessor;
import com.amazon.music.file.FileAccessorConfig;
import com.amazon.music.file.MaxFileSizeException;
import com.amazon.music.metrics.event.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSEventHandler extends EventHandler<MTSEvent, Exception> {
    public static final long DEFAULT_UPLOAD_DELAY_MS = TimeUnit.SECONDS.toMillis(60);
    private static final Logger LOG = LoggerFactory.getLogger(MTSEventHandler.class.getSimpleName());
    private MTSCounterMetadataProvider counterProvider;
    private boolean mCurrentlyUploading;
    private MTSEventDataProvider mDataProvider;
    private MTSEventHandlerErrorReporter mErrorReporter;
    private List<MTSEvent> mEventsToRecord;
    private MTSFileConfig mFileConfig;
    private final Object mLock;
    private MTSMinervaMetricsProvider mMTSMinervaMetricsProvider;
    private boolean mPendingUpload;
    private MTSFileConfig mPriorityFileConfig;
    private boolean mPriorityFileSet;
    private HashSet<String> mPriorityMetrics;
    private final long mUploadDelayMs;
    private boolean mUploadImmediately;
    private MTSEventUploaderFactory mUploaderFactory;
    private SimpleSumAggregatedLong maxFileSizeCount;
    private boolean recordMaxFileSize;

    public interface MTSCounterMetadataProvider {
    }

    public interface MTSMinervaMetricsProvider {
        void sendMetrics(String str, String str2, List<Object> list);
    }

    public MTSEventHandler(MTSEventDataProvider mTSEventDataProvider, MTSFileConfig mTSFileConfig, MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventHandlerErrorReporter mTSEventHandlerErrorReporter, long j, MTSCounterMetadataProvider mTSCounterMetadataProvider, MTSFileConfig mTSFileConfig2) {
        this(mTSEventDataProvider, mTSFileConfig, mTSEventUploaderFactory, mTSEventHandlerErrorReporter, j);
        this.counterProvider = mTSCounterMetadataProvider;
        this.mPriorityFileConfig = mTSFileConfig2;
        this.mPriorityFileSet = true;
        initializePriorityMetricsHashSet();
    }

    public MTSEventHandler(MTSEventDataProvider mTSEventDataProvider, MTSFileConfig mTSFileConfig, MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventHandlerErrorReporter mTSEventHandlerErrorReporter, long j) {
        super(MTSEvent.class);
        this.mLock = new Object();
        this.mEventsToRecord = new ArrayList();
        this.mPriorityMetrics = new HashSet<>();
        this.mUploadImmediately = false;
        this.mPriorityFileSet = true;
        this.maxFileSizeCount = new SimpleSumAggregatedLong();
        this.recordMaxFileSize = false;
        this.mUploadDelayMs = j;
        this.mFileConfig = mTSFileConfig;
        this.mUploaderFactory = mTSEventUploaderFactory;
        this.mDataProvider = mTSEventDataProvider;
        this.mErrorReporter = mTSEventHandlerErrorReporter;
        this.mPriorityFileConfig = mTSFileConfig;
        this.mPriorityFileSet = false;
    }

    private void initializePriorityMetricsHashSet() {
        this.mPriorityMetrics.add("lyricsViewed");
        this.mPriorityMetrics.add("primeTrackPlayedLocally");
        this.mPriorityMetrics.add("streamingTerminated");
        this.mPriorityMetrics.add("trackDownloaded");
        this.mPriorityMetrics.add("trackImported");
        this.mPriorityMetrics.add("trackPlayedLocally");
        this.mPriorityMetrics.add("trackStreamed");
    }

    public MTSEventHandler(MTSEventDataProvider mTSEventDataProvider, MTSFileConfig mTSFileConfig) {
        this(mTSEventDataProvider, mTSFileConfig, null, new NoOpErrorReporter(), 0L);
    }

    public MTSEventHandler(MTSEventDataProvider mTSEventDataProvider, MTSFileConfig mTSFileConfig, MTSEventUploaderFactory mTSEventUploaderFactory, MTSEventHandlerErrorReporter mTSEventHandlerErrorReporter, long j, MTSCounterMetadataProvider mTSCounterMetadataProvider, MTSFileConfig mTSFileConfig2, MTSMinervaMetricsProvider mTSMinervaMetricsProvider) {
        this(mTSEventDataProvider, mTSFileConfig, mTSEventUploaderFactory, mTSEventHandlerErrorReporter, j, mTSCounterMetadataProvider, mTSFileConfig2);
        this.mMTSMinervaMetricsProvider = mTSMinervaMetricsProvider;
    }

    public void deleteSavedMetrics() throws IOException {
        createFileDeleter(this.mFileConfig).deleteFile();
    }

    private FileAccessor createFileDeleter(FileAccessorConfig fileAccessorConfig) {
        return new FileAccessor(fileAccessorConfig);
    }

    @Override // com.amazon.music.metrics.event.EventHandler
    public void handleEvent(MTSEvent mTSEvent) throws Throwable {
        LOG.trace("handleEvent: {}", mTSEvent.getEventName());
        synchronized (this.mLock) {
            if (this.mCurrentlyUploading) {
                this.mEventsToRecord.add(mTSEvent);
            } else {
                recordEvent(mTSEvent);
            }
            if (this.mPendingUpload) {
                return;
            }
            this.mPendingUpload = true;
            handleUpload();
        }
    }

    private boolean recordEvent(MTSEvent mTSEvent) {
        try {
            getRecorder(getConfigFile(mTSEvent)).recordEvent(mTSEvent);
            return true;
        } catch (Exception e) {
            LOG.warn("Exception while handling event recording, proceeding to upload phase", (Throwable) e);
            if (!(e instanceof MaxFileSizeException)) {
                return false;
            }
            this.mUploadImmediately = true;
            this.mLock.notifyAll();
            this.recordMaxFileSize = true;
            try {
                this.maxFileSizeCount.addValue(1L);
                return false;
            } catch (ArithmeticException e2) {
                LOG.error("Error when trying to add value, value not added", (Throwable) e2);
                recordMaxFileSizeException(Long.MAX_VALUE);
                return false;
            }
        }
    }

    @Override // com.amazon.music.metrics.event.EventHandler
    public void handleSignout() throws Throwable {
        this.mUploadImmediately = true;
        handleUpload();
    }

    private void handleUpload() throws Throwable {
        while (true) {
            if (!this.mUploadImmediately) {
                sleepForDelay();
            }
            this.mUploadImmediately = false;
            synchronized (this.mLock) {
                this.mCurrentlyUploading = true;
            }
            if (this.mPriorityFileSet) {
                try {
                    getReporter(this.mPriorityFileConfig).reportEvents();
                } catch (Exception e) {
                    LOG.warn("Error uploading Priority events", (Throwable) e);
                }
            }
            try {
                getReporter(this.mFileConfig).reportEvents();
            } catch (Exception e2) {
                LOG.warn("Error uploading events", (Throwable) e2);
            }
            synchronized (this.mLock) {
                this.mCurrentlyUploading = false;
                this.mPendingUpload = false;
                Iterator<MTSEvent> it = this.mEventsToRecord.iterator();
                while (it.hasNext()) {
                    if (recordEvent(it.next())) {
                        this.mPendingUpload = true;
                    }
                }
                this.mEventsToRecord.clear();
                recordMaxFileSizeException(this.maxFileSizeCount.getAggregatedValue().longValue());
                if (!this.mPendingUpload) {
                    return;
                }
            }
        }
    }

    private void sleepForDelay() {
        try {
            synchronized (this.mLock) {
                this.mLock.wait(this.mUploadDelayMs);
            }
        } catch (InterruptedException unused) {
        }
    }

    MTSEventRecorder getRecorder(MTSFileConfig mTSFileConfig) {
        return new MTSEventRecorder(mTSFileConfig, getTransformer());
    }

    MTSEventReporter getReporter(MTSFileConfig mTSFileConfig) {
        return new MTSEventReporter(mTSFileConfig, this.mUploaderFactory, getTransformer(), this.mErrorReporter);
    }

    private MTSEventTransformer getTransformer() {
        return new MTSEventTransformer(this.mDataProvider);
    }

    private MTSFileConfig getConfigFile(MTSEvent mTSEvent) {
        return isPriorityMetric(mTSEvent) ? this.mPriorityFileConfig : this.mFileConfig;
    }

    private boolean isPriorityMetric(MTSEvent mTSEvent) {
        return this.mPriorityMetrics.contains(mTSEvent.getEventName());
    }

    private void sendMinervaMetrics(String str, Object... objArr) {
        if (this.mMTSMinervaMetricsProvider != null) {
            this.mMTSMinervaMetricsProvider.sendMetrics("9mo3p4af", str, Arrays.asList(objArr));
        }
    }

    private void recordMaxFileSizeException(long j) {
        if (this.recordMaxFileSize) {
            Object[] objArr = new Object[4];
            objArr[0] = "Counter";
            objArr[1] = Long.valueOf(j);
            objArr[2] = "MetricName";
            objArr[3] = isFireOS() ? "MaxFileSizeExceptionFireOs" : "MaxFileSizeException";
            sendMinervaMetrics("o9sy/2/03330400", objArr);
            this.recordMaxFileSize = false;
            this.maxFileSizeCount.resetmSum();
        }
    }

    private static class SimpleSumAggregatedLong {
        private long mSum = 0;

        public void addValue(long j) {
            this.mSum = addExact(this.mSum, j);
        }

        public Long getAggregatedValue() {
            return Long.valueOf(this.mSum);
        }

        public void resetmSum() {
            this.mSum = 0L;
        }

        public long addExact(long j, long j2) {
            long j3 = j + j2;
            if (((j ^ j3) & (j2 ^ j3)) >= 0) {
                return j3;
            }
            throw new ArithmeticException("Long overflow if value added, value dropped");
        }
    }

    private boolean isFireOS() {
        try {
            Class.forName("com.amazon.mp3.FireOSApplication");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
