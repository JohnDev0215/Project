package com.google.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ConditionVariable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
final class ExtractorMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final ExtractorMediaSource.EventListener eventListener;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private final Listener listener;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private boolean notifyDiscontinuity;
    private boolean prepared;
    private boolean released;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private boolean[] trackEnabledStates;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private final Uri uri;
    private final Loader loader = new Loader("Loader:ExtractorMediaPeriod");
    private final ConditionVariable loadCondition = new ConditionVariable();
    private final Runnable maybeFinishPrepareRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.ExtractorMediaPeriod.1
        @Override // java.lang.Runnable
        public void run() {
            ExtractorMediaPeriod.this.maybeFinishPrepare();
        }
    };
    private final Runnable onContinueLoadingRequestedRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.ExtractorMediaPeriod.2
        @Override // java.lang.Runnable
        public void run() {
            if (ExtractorMediaPeriod.this.released) {
                return;
            }
            ExtractorMediaPeriod.this.callback.onContinueLoadingRequested(ExtractorMediaPeriod.this);
        }
    };
    private final Handler handler = new Handler();
    private int[] sampleQueueTrackIds = new int[0];
    private SampleQueue[] sampleQueues = new SampleQueue[0];
    private long pendingResetPositionUs = -9223372036854775807L;
    private long length = -1;

    interface Listener {
        void onSourceInfoRefreshed(long j, boolean z);
    }

    public ExtractorMediaPeriod(Uri uri, DataSource dataSource, Extractor[] extractorArr, int i, Handler handler, ExtractorMediaSource.EventListener eventListener, Listener listener, Allocator allocator, String str, int i2) {
        this.uri = uri;
        this.dataSource = dataSource;
        this.minLoadableRetryCount = i;
        this.eventHandler = handler;
        this.eventListener = eventListener;
        this.listener = listener;
        this.allocator = allocator;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i2;
        this.extractorHolder = new ExtractorHolder(extractorArr, this);
    }

    public void release() {
        boolean zRelease = this.loader.release(this);
        if (this.prepared && !zRelease) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.discardToEnd();
            }
        }
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.extractorHolder.release();
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.loadCondition.open();
        startLoading();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        TrackSelection trackSelection;
        Assertions.checkState(this.prepared);
        int i = this.enabledTrackCount;
        int i2 = 0;
        for (int i3 = 0; i3 < trackSelectionArr.length; i3++) {
            SampleStream sampleStream = sampleStreamArr[i3];
            if (sampleStream != null && (trackSelectionArr[i3] == null || !zArr[i3])) {
                int i4 = ((SampleStreamImpl) sampleStream).track;
                Assertions.checkState(this.trackEnabledStates[i4]);
                this.enabledTrackCount--;
                this.trackEnabledStates[i4] = false;
                sampleStreamArr[i3] = null;
            }
        }
        boolean z = !this.seenFirstTrackSelection ? j == 0 : i != 0;
        for (int i5 = 0; i5 < trackSelectionArr.length; i5++) {
            if (sampleStreamArr[i5] == null && (trackSelection = trackSelectionArr[i5]) != null) {
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int iIndexOf = this.tracks.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!this.trackEnabledStates[iIndexOf]);
                this.enabledTrackCount++;
                this.trackEnabledStates[iIndexOf] = true;
                sampleStreamArr[i5] = new SampleStreamImpl(iIndexOf);
                zArr2[i5] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[iIndexOf];
                    sampleQueue.rewind();
                    z = (sampleQueue.advanceTo(j, true, true) || sampleQueue.getReadIndex() == 0) ? false : true;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length = sampleQueueArr.length;
                while (i2 < length) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length2 = sampleQueueArr2.length;
                while (i2 < length2) {
                    sampleQueueArr2[i2].reset();
                    i2++;
                }
            }
        } else if (z) {
            j = seekToUs(j);
            while (i2 < sampleStreamArr.length) {
                if (sampleStreamArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            this.sampleQueues[i].discardTo(j, false, this.trackEnabledStates[i]);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        if (this.loadingFinished) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean zOpen = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return zOpen;
        }
        startLoading();
        return true;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity) {
            return -9223372036854775807L;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long largestQueuedTimestampUs;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            largestQueuedTimestampUs = Long.MAX_VALUE;
            int length = this.sampleQueues.length;
            for (int i = 0; i < length; i++) {
                if (this.trackIsAudioVideoFlags[i]) {
                    largestQueuedTimestampUs = Math.min(largestQueuedTimestampUs, this.sampleQueues[i].getLargestQueuedTimestampUs());
                }
            }
        } else {
            largestQueuedTimestampUs = getLargestQueuedTimestampUs();
        }
        return largestQueuedTimestampUs == Long.MIN_VALUE ? this.lastSeekPositionUs : largestQueuedTimestampUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        this.lastSeekPositionUs = j;
        this.notifyDiscontinuity = false;
        if (!isPendingReset() && seekInsideBufferUs(j)) {
            return j;
        }
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
        }
        return j;
    }

    boolean isReady(int i) {
        return this.loadingFinished || (!isPendingReset() && this.sampleQueues[i].hasNextSample());
    }

    void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
    }

    int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (this.notifyDiscontinuity || isPendingReset()) {
            return -3;
        }
        return this.sampleQueues[i].read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
    }

    void skipData(int i, long j) {
        SampleQueue sampleQueue = this.sampleQueues[i];
        if (this.loadingFinished && j > sampleQueue.getLargestQueuedTimestampUs()) {
            sampleQueue.advanceToEnd();
        } else {
            sampleQueue.advanceTo(j, true, true);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        if (this.durationUs == -9223372036854775807L) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            long j3 = largestQueuedTimestampUs == Long.MIN_VALUE ? 0L : largestQueuedTimestampUs + 10000;
            this.durationUs = j3;
            this.listener.onSourceInfoRefreshed(j3, this.seekMap.isSeekable());
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        if (z) {
            return;
        }
        copyLengthFromLoader(extractingLoadable);
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        if (this.enabledTrackCount > 0) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException) {
        copyLengthFromLoader(extractingLoadable);
        notifyLoadError(iOException);
        if (isLoadableExceptionFatal(iOException)) {
            return 3;
        }
        int i = getExtractedSamplesCount() > this.extractedSamplesCountAtStartOfLoad ? 1 : 0;
        configureRetry(extractingLoadable);
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        return i;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        int length = this.sampleQueues.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.sampleQueueTrackIds[i3] == i) {
                return this.sampleQueues[i3];
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i4 = length + 1;
        int[] iArrCopyOf = Arrays.copyOf(this.sampleQueueTrackIds, i4);
        this.sampleQueueTrackIds = iArrCopyOf;
        iArrCopyOf[length] = i;
        SampleQueue[] sampleQueueArr = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i4);
        this.sampleQueues = sampleQueueArr;
        sampleQueueArr[length] = sampleQueue;
        return sampleQueue;
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
        this.seekMap = seekMap;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.google.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (this.released || this.prepared || this.seekMap == null || !this.sampleQueuesBuilt) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue.getUpstreamFormat() == null) {
                return;
            }
        }
        this.loadCondition.close();
        int length = this.sampleQueues.length;
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        this.trackIsAudioVideoFlags = new boolean[length];
        this.trackEnabledStates = new boolean[length];
        this.durationUs = this.seekMap.getDurationUs();
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                Format upstreamFormat = this.sampleQueues[i].getUpstreamFormat();
                trackGroupArr[i] = new TrackGroup(upstreamFormat);
                String str = upstreamFormat.sampleMimeType;
                if (!MimeTypes.isVideo(str) && !MimeTypes.isAudio(str)) {
                    z = false;
                }
                this.trackIsAudioVideoFlags[i] = z;
                this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
                i++;
            } else {
                this.tracks = new TrackGroupArray(trackGroupArr);
                this.prepared = true;
                this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
                this.callback.onPrepared(this);
                return;
            }
        }
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private void startLoading() {
        SeekMap seekMap;
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j = this.durationUs;
            if (j != -9223372036854775807L && this.pendingResetPositionUs >= j) {
                this.loadingFinished = true;
                this.pendingResetPositionUs = -9223372036854775807L;
                return;
            } else {
                extractingLoadable.setLoadPosition(this.seekMap.getPosition(this.pendingResetPositionUs), this.pendingResetPositionUs);
                this.pendingResetPositionUs = -9223372036854775807L;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        int i = this.minLoadableRetryCount;
        if (i == -1) {
            i = (this.prepared && this.length == -1 && ((seekMap = this.seekMap) == null || seekMap.getDurationUs() == -9223372036854775807L)) ? 6 : 3;
        }
        this.loader.startLoading(extractingLoadable, this, i);
    }

    private void configureRetry(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            SeekMap seekMap = this.seekMap;
            if (seekMap == null || seekMap.getDurationUs() == -9223372036854775807L) {
                this.lastSeekPositionUs = 0L;
                this.notifyDiscontinuity = this.prepared;
                for (SampleQueue sampleQueue : this.sampleQueues) {
                    sampleQueue.reset();
                }
                extractingLoadable.setLoadPosition(0L, 0L);
            }
        }
    }

    private boolean seekInsideBufferUs(long j) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (!sampleQueue.advanceTo(j, true, false) && (this.trackIsAudioVideoFlags[i] || !this.haveAudioVideoTracks)) {
                return false;
            }
            sampleQueue.discardToRead();
        }
        return true;
    }

    private int getExtractedSamplesCount() {
        int writeIndex = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            writeIndex += sampleQueue.getWriteIndex();
        }
        return writeIndex;
    }

    private long getLargestQueuedTimestampUs() {
        long jMax = Long.MIN_VALUE;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            jMax = Math.max(jMax, sampleQueue.getLargestQueuedTimestampUs());
        }
        return jMax;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private boolean isLoadableExceptionFatal(IOException iOException) {
        return iOException instanceof UnrecognizedInputFormatException;
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler = this.eventHandler;
        if (handler == null || this.eventListener == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.google.android.exoplayer2.source.ExtractorMediaPeriod.3
            @Override // java.lang.Runnable
            public void run() {
                ExtractorMediaPeriod.this.eventListener.onLoadError(iOException);
            }
        });
    }

    private final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return ExtractorMediaPeriod.this.isReady(this.track);
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            ExtractorMediaPeriod.this.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            return ExtractorMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z);
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void skipData(long j) {
            ExtractorMediaPeriod.this.skipData(this.track, j);
        }
    }

    final class ExtractingLoadable implements Loader.Loadable {
        private final DataSource dataSource;
        private final ExtractorHolder extractorHolder;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private long seekTimeUs;
        private final Uri uri;
        private final PositionHolder positionHolder = new PositionHolder();
        private boolean pendingExtractorSeek = true;
        private long length = -1;

        public ExtractingLoadable(Uri uri, DataSource dataSource, ExtractorHolder extractorHolder, ConditionVariable conditionVariable) {
            this.uri = (Uri) Assertions.checkNotNull(uri);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder);
            this.loadCondition = conditionVariable;
        }

        public void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
        public void load() throws Throwable {
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                DefaultExtractorInput defaultExtractorInput = null;
                try {
                    long position = this.positionHolder.position;
                    long jOpen = this.dataSource.open(new DataSpec(this.uri, position, -1L, ExtractorMediaPeriod.this.customCacheKey));
                    this.length = jOpen;
                    if (jOpen != -1) {
                        this.length = jOpen + position;
                    }
                    DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(this.dataSource, position, this.length);
                    try {
                        Extractor extractorSelectExtractor = this.extractorHolder.selectExtractor(defaultExtractorInput2, this.dataSource.getUri());
                        if (this.pendingExtractorSeek) {
                            extractorSelectExtractor.seek(position, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            i = extractorSelectExtractor.read(defaultExtractorInput2, this.positionHolder);
                            if (defaultExtractorInput2.getPosition() > ExtractorMediaPeriod.this.continueLoadingCheckIntervalBytes + position) {
                                position = defaultExtractorInput2.getPosition();
                                this.loadCondition.close();
                                ExtractorMediaPeriod.this.handler.post(ExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                            }
                        }
                        if (i == 1) {
                            i = 0;
                        } else {
                            this.positionHolder.position = defaultExtractorInput2.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                    } catch (Throwable th) {
                        th = th;
                        defaultExtractorInput = defaultExtractorInput2;
                        if (i != 1 && defaultExtractorInput != null) {
                            this.positionHolder.position = defaultExtractorInput.getPosition();
                        }
                        Util.closeQuietly(this.dataSource);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
    }

    private static final class ExtractorHolder {
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        public ExtractorHolder(Extractor[] extractorArr, ExtractorOutput extractorOutput) {
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, Uri uri) throws InterruptedException, IOException {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                return extractor;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Extractor extractor2 = extractorArr[i];
                try {
                    if (extractor2.sniff(extractorInput)) {
                        this.extractor = extractor2;
                        extractorInput.resetPeekPosition();
                        break;
                    }
                    continue;
                } catch (EOFException unused) {
                } catch (Throwable th) {
                    extractorInput.resetPeekPosition();
                    throw th;
                }
                extractorInput.resetPeekPosition();
                i++;
            }
            Extractor extractor3 = this.extractor;
            if (extractor3 == null) {
                throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
            }
            extractor3.init(this.extractorOutput);
            return this.extractor;
        }

        public void release() {
            Extractor extractor = this.extractor;
            if (extractor != null) {
                extractor.release();
                this.extractor = null;
            }
        }
    }
}
