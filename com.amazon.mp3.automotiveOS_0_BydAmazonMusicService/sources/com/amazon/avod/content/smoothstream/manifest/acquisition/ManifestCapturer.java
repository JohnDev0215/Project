package com.amazon.avod.content.smoothstream.manifest.acquisition;

import android.os.Environment;
import com.amazon.avod.config.HttpClientConfig;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.util.SlidingWindowEventTracker;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ScheduledExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.amazon.music.destination.parser.ParserUtil;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Lists;
import com.google.common.io.Closer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestCapturer implements ManifestCapturerInterface {
    private PostManifestPayload mAdManifest;
    private boolean mHasAdManifest;
    private final HttpClientConfig mHttpClientConfig;
    private boolean mIsEnabled;
    private boolean mIsLatestManifestIsDAI;
    private boolean mIsLatestManifestIsMultiPeriod;
    private final boolean mIsLiveStream;
    private PostManifestPayload mLatestMainManifest;
    private final ManifestCapturerConfig mManifestCapturerConfig;
    private final ScheduledExecutorService mManifestCapturerExecutorService;
    private final Object mManifestCapturerMutex;
    private final ManifestUploader mManifestUploader;
    final EvictingQueue<PostManifestPayload> mManifests;
    private final SlidingWindowEventTracker mSlidingWindowEventTracker;

    /* JADX WARN: Illegal instructions before constructor call */
    public ManifestCapturer(@Nonnull ManifestUploader manifestUploader, @Nonnull VideoSpecification videoSpecification) {
        ManifestCapturerConfig manifestCapturerConfig = ManifestCapturerConfig.INSTANCE;
        this(manifestUploader, manifestCapturerConfig.getMaxHistoricalManifestsToUpload(), manifestCapturerConfig.isManifestCaptureEnabled(), ScheduledExecutorBuilder.newBuilderFor("ManifestCapturer", new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build(), new SlidingWindowEventTracker(manifestCapturerConfig.getManifestCaptureTimeWindow(), 1), HttpClientConfig.getInstance(), manifestCapturerConfig, videoSpecification);
    }

    ManifestCapturer(@Nonnull ManifestUploader manifestUploader, int i, boolean z, @Nonnull ScheduledExecutorService scheduledExecutorService, @Nonnull SlidingWindowEventTracker slidingWindowEventTracker, @Nonnull HttpClientConfig httpClientConfig, @Nonnull ManifestCapturerConfig manifestCapturerConfig, @Nonnull VideoSpecification videoSpecification) {
        this.mIsLatestManifestIsDAI = false;
        this.mIsLatestManifestIsMultiPeriod = false;
        this.mManifestUploader = (ManifestUploader) Preconditions.checkNotNull(manifestUploader, "manifestUploader");
        this.mManifestCapturerExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        this.mSlidingWindowEventTracker = (SlidingWindowEventTracker) Preconditions.checkNotNull(slidingWindowEventTracker, "slidingWindowEventTracker");
        this.mManifests = EvictingQueue.create(i);
        this.mIsEnabled = z;
        this.mHttpClientConfig = (HttpClientConfig) Preconditions.checkNotNull(httpClientConfig, "httpClientConfig");
        this.mManifestCapturerMutex = new Object();
        this.mManifestCapturerConfig = (ManifestCapturerConfig) Preconditions.checkNotNull(manifestCapturerConfig, "manifestCapturerConfig");
        this.mIsLiveStream = ((VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification")).isLiveStream();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void capture(final long j, @Nonnull final ByteBuffer byteBuffer, @Nullable final String str, final String str2, @Nonnull final String str3, final boolean z, @Nullable final Map<String, List<String>> map) {
        synchronized (this.mManifestCapturerMutex) {
            if (this.mIsEnabled) {
                Preconditions.checkNotNull(str3, ImagesContract.URL);
                Preconditions.checkNotNull(byteBuffer, "manifest");
                this.mManifestCapturerExecutorService.schedule(new Runnable() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturer.1
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        String string;
                        try {
                            string = Charset.forName(ParserUtil.UTF_8).decode(byteBuffer).toString();
                            if (ManifestCapturer.this.mHttpClientConfig.getEnableLatestManifestLogging()) {
                                ManifestCapturer.this.logManifest(String.format(Locale.US, "%s%n%s", str3, string));
                            }
                        } catch (Exception e) {
                            DLog.exceptionf(e, "Error decoding manifest XML", new Object[0]);
                            string = "";
                        }
                        PostManifestPayload postManifestPayloadGenerate = PostManifestPayload.generate(j, string, str, str2, str3, map);
                        ManifestCapturer.this.mIsLatestManifestIsDAI = z;
                        ManifestCapturer.this.mIsLatestManifestIsMultiPeriod = false;
                        if (str3.equals("AdManifest")) {
                            ManifestCapturer.this.mAdManifest = postManifestPayloadGenerate;
                            ManifestCapturer.this.mHasAdManifest = true;
                        } else {
                            ManifestCapturer.this.mLatestMainManifest = postManifestPayloadGenerate;
                        }
                    }
                }, 0L, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void captureParsedData(boolean z) {
        synchronized (this.mManifestCapturerMutex) {
            this.mIsLatestManifestIsMultiPeriod = z;
            if (!this.mSlidingWindowEventTracker.isEventCountGreaterThanOrEqualToThreshold()) {
                this.mSlidingWindowEventTracker.recordEvent(TimeSpan.now());
                uploadFromContinuousTimeout();
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void uploadAdManifest() {
        synchronized (this.mManifestCapturerMutex) {
            uploadInternal(null);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void uploadFromException(@Nonnull Exception exc) {
        synchronized (this.mManifestCapturerMutex) {
            Preconditions2.checkNotNullWeakly(exc, "exceptionTriggeringUpload");
            if (this.mManifestCapturerConfig.isExceptionUploadEnabled(latestManifestIsLiveLinear(), latestManifestIsMultiPeriod(), latestManifestIsDAI(), this.mHasAdManifest)) {
                uploadInternal(exc);
            }
        }
    }

    public void uploadFromContinuousTimeout() {
        synchronized (this.mManifestCapturerMutex) {
            if (this.mManifestCapturerConfig.isContinuousUploadEnabled(latestManifestIsLiveLinear(), latestManifestIsMultiPeriod(), latestManifestIsDAI())) {
                uploadInternal(null);
            }
        }
    }

    private void uploadInternal(@Nullable final Exception exc) {
        synchronized (this.mManifestCapturerMutex) {
            if (this.mIsEnabled) {
                this.mManifestCapturerExecutorService.schedule(new Runnable() { // from class: com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturer.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ManifestCapturer.this.mLatestMainManifest != null) {
                            if (exc != null) {
                                ManifestCapturer.this.mLatestMainManifest.setException(exc);
                            }
                            ManifestCapturer manifestCapturer = ManifestCapturer.this;
                            manifestCapturer.mManifests.add(manifestCapturer.mLatestMainManifest);
                            ManifestCapturer.this.mLatestMainManifest = null;
                        }
                        if (ManifestCapturer.this.mAdManifest != null) {
                            if (exc != null) {
                                ManifestCapturer.this.mAdManifest.setException(exc);
                            }
                            ManifestCapturer manifestCapturer2 = ManifestCapturer.this;
                            manifestCapturer2.mManifests.add(manifestCapturer2.mAdManifest);
                            ManifestCapturer.this.mAdManifest = null;
                        }
                        if (exc != null && ManifestCapturer.this.mManifests.size() == 0) {
                            DLog.exceptionf(exc, "Tried to upload manifest for this exception but there is no manifest captured", new Object[0]);
                        }
                        if (exc != null || ManifestCapturer.this.shouldUploadAllLiveManifest() || ManifestCapturer.this.shouldUploadAllAdManifest()) {
                            ManifestCapturer.this.mManifestUploader.upload(Lists.newArrayList(ManifestCapturer.this.mManifests));
                            ManifestCapturer.this.mManifests.clear();
                            ManifestCapturer.this.mHasAdManifest = false;
                        }
                    }
                }, 0L, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void shutdown() {
        synchronized (this.mManifestCapturerMutex) {
            this.mIsEnabled = false;
            this.mManifestCapturerExecutorService.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004b -> B:24:0x0058). Please report as a decompilation issue!!! */
    public void logManifest(@Nullable String str) throws Throwable {
        if (this.mIsEnabled && str != null) {
            Closer closerCreate = Closer.create();
            try {
                try {
                    try {
                        ((FileOutputStream) closerCreate.register(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "LatestManifestXml.txt")))).write(str.getBytes(ParserUtil.UTF_8));
                        closerCreate.close();
                    } catch (IOException e) {
                        DLog.errorf("Fail to log manifest to local storage. Error: %s", e.getMessage());
                        closerCreate.close();
                    }
                } catch (IOException e2) {
                    DLog.logf("Could not close instance of FileOutputStream: %s", e2);
                }
            } catch (Throwable th) {
                try {
                    closerCreate.close();
                } catch (IOException e3) {
                    DLog.logf("Could not close instance of FileOutputStream: %s", e3);
                }
                throw th;
            }
        }
    }

    private boolean latestManifestIsDAI() {
        return this.mIsLatestManifestIsDAI;
    }

    private boolean latestManifestIsMultiPeriod() {
        return this.mIsLatestManifestIsMultiPeriod;
    }

    private boolean latestManifestIsLiveLinear() {
        return (!this.mIsLiveStream || latestManifestIsDAI() || latestManifestIsMultiPeriod()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldUploadAllAdManifest() {
        return this.mHasAdManifest && this.mManifestCapturerConfig.shouldUploadAllAdManifest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldUploadAllLiveManifest() {
        return this.mIsLiveStream && this.mManifestCapturerConfig.uploadAllManifests();
    }
}
