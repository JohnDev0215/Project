package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.hls.HlsChunkSource;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes6.dex */
final class HlsSampleStreamWrapper implements Loader.Callback<Chunk>, Loader.ReleaseCallback, SequenceableLoader, ExtractorOutput, SampleQueue.UpstreamFormatChangedListener {
    private final Allocator allocator;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    private Format downstreamTrackFormat;
    private int enabledTrackCount;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    private boolean haveAudioVideoTrackGroups;
    private long lastSeekPositionUs;
    private boolean loadingFinished;
    private final int minLoadableRetryCount;
    private final Format muxedAudioFormat;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primaryTrackGroupIndex;
    private boolean released;
    private boolean sampleQueuesBuilt;
    private boolean seenFirstTrackSelection;
    private boolean[] trackGroupEnabledStates;
    private boolean[] trackGroupIsAudioVideoFlags;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private int upstreamChunkUid;
    private final Loader loader = new Loader("Loader:HlsSampleStreamWrapper");
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
    private int[] sampleQueueTrackIds = new int[0];
    private SampleQueue[] sampleQueues = new SampleQueue[0];
    private final LinkedList<HlsMediaChunk> mediaChunks = new LinkedList<>();
    private final Runnable maybeFinishPrepareRunnable = new Runnable() { // from class: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.1
        @Override // java.lang.Runnable
        public void run() {
            HlsSampleStreamWrapper.this.maybeFinishPrepare();
        }
    };
    private final Handler handler = new Handler();

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(HlsMasterPlaylist.HlsUrl hlsUrl);

        void onPrepared();
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap) {
    }

    public HlsSampleStreamWrapper(int i, Callback callback, HlsChunkSource hlsChunkSource, Allocator allocator, long j, Format format, int i2, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher) {
        this.trackType = i;
        this.callback = callback;
        this.chunkSource = hlsChunkSource;
        this.allocator = allocator;
        this.muxedAudioFormat = format;
        this.minLoadableRetryCount = i2;
        this.eventDispatcher = eventDispatcher;
        this.lastSeekPositionUs = j;
        this.pendingResetPositionUs = j;
    }

    public void continuePreparing() {
        if (this.prepared) {
            return;
        }
        continueLoading(this.lastSeekPositionUs);
    }

    public void prepareSingleTrack(Format format) {
        track(0, -1).format(format);
        this.sampleQueuesBuilt = true;
        maybeFinishPrepare();
    }

    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[] r16, boolean[] r17, com.google.android.exoplayer2.source.SampleStream[] r18, boolean[] r19, long r20, boolean r22) {
        /*
            Method dump skipped, instruction units count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long, boolean):boolean");
    }

    public void discardBuffer(long j) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            this.sampleQueues[i].discardTo(j, false, this.trackGroupEnabledStates[i]);
        }
    }

    public boolean seekToUs(long j, boolean z) {
        this.lastSeekPositionUs = j;
        if (!z && !isPendingReset() && seekInsideBufferUs(j)) {
            return false;
        }
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return true;
        }
        resetSampleQueues();
        return true;
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:506)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:509)
        */
    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        /*
            r7 = this;
            boolean r0 = r7.loadingFinished
            if (r0 == 0) goto L7
            r0 = -9223372036854775808
            return r0
        L7:
            boolean r0 = r7.isPendingReset()
            if (r0 == 0) goto L10
            long r0 = r7.pendingResetPositionUs
            return r0
        L10:
            long r0 = r7.lastSeekPositionUs
            java.util.LinkedList<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r2 = r7.mediaChunks
            java.lang.Object r2 = r2.getLast()
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r2 = (com.google.android.exoplayer2.source.hls.HlsMediaChunk) r2
            boolean r3 = r2.isLoadCompleted()
            if (r3 == 0) goto L21
            goto L3a
        L21:
            java.util.LinkedList<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r2 = r7.mediaChunks
            int r2 = r2.size()
            r3 = 1
            if (r2 <= r3) goto L39
            java.util.LinkedList<com.google.android.exoplayer2.source.hls.HlsMediaChunk> r2 = r7.mediaChunks
            int r3 = r2.size()
            int r3 = r3 + (-2)
            java.lang.Object r2 = r2.get(r3)
            com.google.android.exoplayer2.source.hls.HlsMediaChunk r2 = (com.google.android.exoplayer2.source.hls.HlsMediaChunk) r2
            goto L3a
        L39:
            r2 = 0
        L3a:
            if (r2 == 0) goto L42
            long r2 = r2.endTimeUs
            long r0 = java.lang.Math.max(r0, r2)
        L42:
            com.google.android.exoplayer2.source.SampleQueue[] r2 = r7.sampleQueues
            int r3 = r2.length
            r4 = 0
        L46:
            if (r4 >= r3) goto L55
            r5 = r2[r4]
            long r5 = r5.getLargestQueuedTimestampUs()
            long r0 = java.lang.Math.max(r0, r5)
            int r4 = r4 + 1
            goto L46
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.getBufferedPositionUs():long");
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
        resetSampleQueues();
    }

    public void setIsTimestampMaster(boolean z) {
        this.chunkSource.setIsTimestampMaster(z);
    }

    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        this.chunkSource.onPlaylistBlacklisted(hlsUrl, j);
    }

    boolean isReady(int i) {
        return this.loadingFinished || (!isPendingReset() && this.sampleQueues[i].hasNextSample());
    }

    void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (isPendingReset()) {
            return -3;
        }
        if (!this.mediaChunks.isEmpty()) {
            while (this.mediaChunks.size() > 1 && finishedReadingChunk(this.mediaChunks.getFirst())) {
                this.mediaChunks.removeFirst();
            }
            HlsMediaChunk first = this.mediaChunks.getFirst();
            Format format = first.trackFormat;
            if (!format.equals(this.downstreamTrackFormat)) {
                this.eventDispatcher.downstreamFormatChanged(this.trackType, format, first.trackSelectionReason, first.trackSelectionData, first.startTimeUs);
            }
            this.downstreamTrackFormat = format;
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

    private boolean finishedReadingChunk(HlsMediaChunk hlsMediaChunk) {
        int i = hlsMediaChunk.uid;
        int i2 = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.sampleQueues;
            if (i2 >= sampleQueueArr.length) {
                return true;
            }
            if (this.trackGroupEnabledStates[i2] && sampleQueueArr[i2].peekSourceId() == i) {
                return false;
            }
            i2++;
        }
    }

    private void resetSampleQueues() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset(this.pendingResetUpstreamFormats);
        }
        this.pendingResetUpstreamFormats = false;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        HlsChunkSource hlsChunkSource = this.chunkSource;
        HlsMediaChunk last = this.mediaChunks.isEmpty() ? null : this.mediaChunks.getLast();
        long j2 = this.pendingResetPositionUs;
        if (j2 == -9223372036854775807L) {
            j2 = j;
        }
        hlsChunkSource.getNextChunk(last, j2, this.nextChunkHolder);
        HlsChunkSource.HlsChunkHolder hlsChunkHolder = this.nextChunkHolder;
        boolean z = hlsChunkHolder.endOfStream;
        Chunk chunk = hlsChunkHolder.chunk;
        HlsMasterPlaylist.HlsUrl hlsUrl = hlsChunkHolder.playlist;
        hlsChunkHolder.clear();
        if (z) {
            this.pendingResetPositionUs = -9223372036854775807L;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            if (hlsUrl != null) {
                this.callback.onPlaylistRefreshRequired(hlsUrl);
            }
            return false;
        }
        if (isMediaChunk(chunk)) {
            this.pendingResetPositionUs = -9223372036854775807L;
            HlsMediaChunk hlsMediaChunk = (HlsMediaChunk) chunk;
            hlsMediaChunk.init(this);
            this.mediaChunks.add(hlsMediaChunk);
        }
        this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
        return true;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return this.mediaChunks.getLast().endTimeUs;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        this.chunkSource.onChunkLoadCompleted(chunk);
        this.eventDispatcher.loadCompleted(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
        } else {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        if (z) {
            return;
        }
        resetSampleQueues();
        if (this.enabledTrackCount > 0) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(Chunk chunk, long j, long j2, IOException iOException) {
        long jBytesLoaded = chunk.bytesLoaded();
        boolean zIsMediaChunk = isMediaChunk(chunk);
        boolean z = true;
        if (!this.chunkSource.onChunkLoadError(chunk, !zIsMediaChunk || jBytesLoaded == 0, iOException)) {
            z = false;
        } else if (zIsMediaChunk) {
            Assertions.checkState(this.mediaChunks.removeLast() == chunk);
            if (this.mediaChunks.isEmpty()) {
                this.pendingResetPositionUs = this.lastSeekPositionUs;
            }
        }
        this.eventDispatcher.loadError(chunk.dataSpec, chunk.type, this.trackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded(), iOException, z);
        if (!z) {
            return 0;
        }
        if (!this.prepared) {
            continueLoading(this.lastSeekPositionUs);
            return 2;
        }
        this.callback.onContinueLoadingRequested(this);
        return 2;
    }

    public void init(int i, boolean z) {
        this.upstreamChunkUid = i;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.sourceId(i);
        }
        if (z) {
            for (SampleQueue sampleQueue2 : this.sampleQueues) {
                sampleQueue2.splice();
            }
        }
    }

    @Override // com.google.android.exoplayer2.extractor.ExtractorOutput
    public SampleQueue track(int i, int i2) {
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

    @Override // com.google.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeFinishPrepare() {
        if (this.released || this.prepared || !this.sampleQueuesBuilt) {
            return;
        }
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue.getUpstreamFormat() == null) {
                return;
            }
        }
        buildTracks();
        this.prepared = true;
        this.callback.onPrepared();
    }

    private void buildTracks() {
        int length = this.sampleQueues.length;
        int i = -1;
        int i2 = 0;
        char c = 0;
        while (true) {
            char c2 = 3;
            if (i2 >= length) {
                break;
            }
            String str = this.sampleQueues[i2].getUpstreamFormat().sampleMimeType;
            if (!MimeTypes.isVideo(str)) {
                if (MimeTypes.isAudio(str)) {
                    c2 = 2;
                } else {
                    c2 = MimeTypes.isText(str) ? (char) 1 : (char) 0;
                }
            }
            if (c2 > c) {
                i = i2;
                c = c2;
            } else if (c2 == c && i != -1) {
                i = -1;
            }
            i2++;
        }
        TrackGroup trackGroup = this.chunkSource.getTrackGroup();
        int i3 = trackGroup.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupEnabledStates = new boolean[length];
        this.trackGroupIsAudioVideoFlags = new boolean[length];
        TrackGroup[] trackGroupArr = new TrackGroup[length];
        for (int i4 = 0; i4 < length; i4++) {
            Format upstreamFormat = this.sampleQueues[i4].getUpstreamFormat();
            String str2 = upstreamFormat.sampleMimeType;
            boolean z = MimeTypes.isVideo(str2) || MimeTypes.isAudio(str2);
            this.trackGroupIsAudioVideoFlags[i4] = z;
            this.haveAudioVideoTrackGroups = z | this.haveAudioVideoTrackGroups;
            if (i4 == i) {
                Format[] formatArr = new Format[i3];
                for (int i5 = 0; i5 < i3; i5++) {
                    formatArr[i5] = deriveFormat(trackGroup.getFormat(i5), upstreamFormat);
                }
                trackGroupArr[i4] = new TrackGroup(formatArr);
                this.primaryTrackGroupIndex = i4;
            } else {
                trackGroupArr[i4] = new TrackGroup(deriveFormat((c == 3 && MimeTypes.isAudio(upstreamFormat.sampleMimeType)) ? this.muxedAudioFormat : null, upstreamFormat));
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
    }

    private void setTrackGroupEnabledState(int i, boolean z) {
        Assertions.checkState(this.trackGroupEnabledStates[i] != z);
        this.trackGroupEnabledStates[i] = z;
        this.enabledTrackCount += z ? 1 : -1;
    }

    private static Format deriveFormat(Format format, Format format2) {
        if (format == null) {
            return format2;
        }
        String videoCodecs = null;
        int trackType = MimeTypes.getTrackType(format2.sampleMimeType);
        if (trackType == 1) {
            videoCodecs = getAudioCodecs(format.codecs);
        } else if (trackType == 2) {
            videoCodecs = getVideoCodecs(format.codecs);
        }
        return format2.copyWithContainerInfo(format.id, videoCodecs, format.bitrate, format.width, format.height, format.selectionFlags, format.language);
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private boolean seekInsideBufferUs(long j) {
        int length = this.sampleQueues.length;
        for (int i = 0; i < length; i++) {
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (!sampleQueue.advanceTo(j, true, false) && (this.trackGroupIsAudioVideoFlags[i] || !this.haveAudioVideoTrackGroups)) {
                return false;
            }
            sampleQueue.discardToRead();
        }
        return true;
    }

    private static String getAudioCodecs(String str) {
        return getCodecsOfType(str, 1);
    }

    private static String getVideoCodecs(String str) {
        return getCodecsOfType(str, 2);
    }

    private static String getCodecsOfType(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("(\\s*,\\s*)|(\\s*$)");
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (i == MimeTypes.getTrackTypeOfCodec(str2)) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(str2);
            }
        }
        if (sb.length() > 0) {
            return sb.toString();
        }
        return null;
    }
}
