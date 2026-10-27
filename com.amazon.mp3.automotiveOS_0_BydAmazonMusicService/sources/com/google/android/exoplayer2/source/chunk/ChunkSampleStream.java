package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.chunk.ChunkSource;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private final T chunkSource;
    private final SampleQueue[] embeddedSampleQueues;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    long lastSeekPositionUs;
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final LinkedList<BaseMediaChunk> mediaChunks;
    private final int minLoadableRetryCount;
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    private final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks;
    private ReleaseCallback<T> releaseCallback;
    private final Loader loader = new Loader("Loader:ChunkSampleStream");
    private final ChunkHolder nextChunkHolder = new ChunkHolder();

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    public ChunkSampleStream(int i, int[] iArr, T t, SequenceableLoader.Callback<ChunkSampleStream<T>> callback, Allocator allocator, long j, int i2, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher) {
        this.primaryTrackType = i;
        this.embeddedTrackTypes = iArr;
        this.chunkSource = t;
        this.callback = callback;
        this.eventDispatcher = eventDispatcher;
        this.minLoadableRetryCount = i2;
        LinkedList<BaseMediaChunk> linkedList = new LinkedList<>();
        this.mediaChunks = linkedList;
        this.readOnlyMediaChunks = Collections.unmodifiableList(linkedList);
        int i3 = 0;
        int length = iArr == null ? 0 : iArr.length;
        this.embeddedSampleQueues = new SampleQueue[length];
        this.embeddedTracksSelected = new boolean[length];
        int i4 = length + 1;
        int[] iArr2 = new int[i4];
        SampleQueue[] sampleQueueArr = new SampleQueue[i4];
        SampleQueue sampleQueue = new SampleQueue(allocator);
        this.primarySampleQueue = sampleQueue;
        iArr2[0] = i;
        sampleQueueArr[0] = sampleQueue;
        while (i3 < length) {
            SampleQueue sampleQueue2 = new SampleQueue(allocator);
            this.embeddedSampleQueues[i3] = sampleQueue2;
            int i5 = i3 + 1;
            sampleQueueArr[i5] = sampleQueue2;
            iArr2[i5] = iArr[i3];
            i3 = i5;
        }
        this.mediaChunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j;
        this.lastSeekPositionUs = j;
    }

    public void discardEmbeddedTracksTo(long j) {
        int i = 0;
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i >= sampleQueueArr.length) {
                return;
            }
            sampleQueueArr[i].discardTo(j, true, this.embeddedTracksSelected[i]);
            i++;
        }
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j, int i) {
        for (int i2 = 0; i2 < this.embeddedSampleQueues.length; i2++) {
            if (this.embeddedTrackTypes[i2] == i) {
                Assertions.checkState(!this.embeddedTracksSelected[i2]);
                this.embeddedTracksSelected[i2] = true;
                this.embeddedSampleQueues[i2].rewind();
                this.embeddedSampleQueues[i2].advanceTo(j, true, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long jMax = this.lastSeekPositionUs;
        BaseMediaChunk last = this.mediaChunks.getLast();
        if (!last.isLoadCompleted()) {
            if (this.mediaChunks.size() > 1) {
                last = this.mediaChunks.get(r2.size() - 2);
            } else {
                last = null;
            }
        }
        if (last != null) {
            jMax = Math.max(jMax, last.endTimeUs);
        }
        return Math.max(jMax, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void seekToUs(long r7) {
        /*
            r6 = this;
            r6.lastSeekPositionUs = r7
            boolean r0 = r6.isPendingReset()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L1f
            com.google.android.exoplayer2.source.SampleQueue r0 = r6.primarySampleQueue
            long r3 = r6.getNextLoadPositionUs()
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 >= 0) goto L16
            r3 = r1
            goto L17
        L16:
            r3 = r2
        L17:
            boolean r0 = r0.advanceTo(r7, r1, r3)
            if (r0 == 0) goto L1f
            r0 = r1
            goto L20
        L1f:
            r0 = r2
        L20:
            if (r0 == 0) goto L41
            com.google.android.exoplayer2.source.SampleQueue r0 = r6.primarySampleQueue
            int r0 = r0.getReadIndex()
            r6.discardDownstreamMediaChunks(r0)
            com.google.android.exoplayer2.source.SampleQueue r0 = r6.primarySampleQueue
            r0.discardToRead()
            com.google.android.exoplayer2.source.SampleQueue[] r0 = r6.embeddedSampleQueues
            int r3 = r0.length
            r4 = r2
        L34:
            if (r4 >= r3) goto L6a
            r5 = r0[r4]
            r5.rewind()
            r5.discardTo(r7, r1, r2)
            int r4 = r4 + 1
            goto L34
        L41:
            r6.pendingResetPositionUs = r7
            r6.loadingFinished = r2
            java.util.LinkedList<com.google.android.exoplayer2.source.chunk.BaseMediaChunk> r7 = r6.mediaChunks
            r7.clear()
            com.google.android.exoplayer2.upstream.Loader r7 = r6.loader
            boolean r7 = r7.isLoading()
            if (r7 == 0) goto L58
            com.google.android.exoplayer2.upstream.Loader r7 = r6.loader
            r7.cancelLoading()
            goto L6a
        L58:
            com.google.android.exoplayer2.source.SampleQueue r7 = r6.primarySampleQueue
            r7.reset()
            com.google.android.exoplayer2.source.SampleQueue[] r7 = r6.embeddedSampleQueues
            int r8 = r7.length
        L60:
            if (r2 >= r8) goto L6a
            r0 = r7[r2]
            r0.reset()
            int r2 = r2 + 1
            goto L60
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.chunk.ChunkSampleStream.seekToUs(long):void");
    }

    public void release() {
        if (this.loader.release(this)) {
            return;
        }
        this.primarySampleQueue.discardToEnd();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.discardToEnd();
        }
    }

    public void release(ReleaseCallback<T> releaseCallback) {
        this.releaseCallback = releaseCallback;
        release();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
        ReleaseCallback<T> releaseCallback = this.releaseCallback;
        if (releaseCallback != null) {
            releaseCallback.onSampleStreamReleased(this);
        }
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return this.loadingFinished || (!isPendingReset() && this.primarySampleQueue.hasNextSample());
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        if (this.loader.isLoading()) {
            return;
        }
        this.chunkSource.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (isPendingReset()) {
            return -3;
        }
        discardDownstreamMediaChunks(this.primarySampleQueue.getReadIndex());
        int i = this.primarySampleQueue.read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
        if (i == -4) {
            this.primarySampleQueue.discardToRead();
        }
        return i;
    }

    @Override // com.google.android.exoplayer2.source.SampleStream
    public void skipData(long j) {
        if (this.loadingFinished && j > this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            this.primarySampleQueue.advanceToEnd();
        } else {
            this.primarySampleQueue.advanceTo(j, true, true);
        }
        this.primarySampleQueue.discardToRead();
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        this.chunkSource.onChunkLoadCompleted(chunk);
        this.eventDispatcher.loadCompleted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        if (z) {
            return;
        }
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(Chunk chunk, long j, long j2, IOException iOException) {
        boolean z;
        long jBytesLoaded = chunk.bytesLoaded();
        boolean zIsMediaChunk = isMediaChunk(chunk);
        if (this.chunkSource.onChunkLoadError(chunk, !zIsMediaChunk || jBytesLoaded == 0 || this.mediaChunks.size() > 1, iOException)) {
            if (zIsMediaChunk) {
                BaseMediaChunk baseMediaChunkRemoveLast = this.mediaChunks.removeLast();
                Assertions.checkState(baseMediaChunkRemoveLast == chunk);
                this.primarySampleQueue.discardUpstreamSamples(baseMediaChunkRemoveLast.getFirstSampleIndex(0));
                int i = 0;
                while (true) {
                    SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
                    if (i >= sampleQueueArr.length) {
                        break;
                    }
                    SampleQueue sampleQueue = sampleQueueArr[i];
                    i++;
                    sampleQueue.discardUpstreamSamples(baseMediaChunkRemoveLast.getFirstSampleIndex(i));
                }
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
            }
            z = true;
        } else {
            z = false;
        }
        this.eventDispatcher.loadError(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, jBytesLoaded, iOException, z);
        if (!z) {
            return 0;
        }
        this.callback.onContinueLoadingRequested(this);
        return 2;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        long j2;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        BaseMediaChunk lastMediaChunk = null;
        if (isPendingReset()) {
            j2 = this.pendingResetPositionUs;
        } else {
            lastMediaChunk = getLastMediaChunk();
            j2 = lastMediaChunk.endTimeUs;
        }
        BaseMediaChunk baseMediaChunk = lastMediaChunk;
        long j3 = j2;
        T t = this.chunkSource;
        long j4 = this.pendingResetPositionUs;
        t.getNextChunk(baseMediaChunk, j4 != -9223372036854775807L ? j4 : j, j3, this.nextChunkHolder);
        ChunkHolder chunkHolder = this.nextChunkHolder;
        boolean z = chunkHolder.endOfStream;
        Chunk chunk = chunkHolder.chunk;
        chunkHolder.clear();
        if (z) {
            this.pendingResetPositionUs = -9223372036854775807L;
            this.loadingFinished = true;
            return true;
        }
        if (chunk == null) {
            return false;
        }
        if (isMediaChunk(chunk)) {
            this.pendingResetPositionUs = -9223372036854775807L;
            BaseMediaChunk baseMediaChunk2 = (BaseMediaChunk) chunk;
            baseMediaChunk2.init(this.mediaChunkOutput);
            this.mediaChunks.add(baseMediaChunk2);
        }
        this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
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

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    boolean isPendingReset() {
        return this.pendingResetPositionUs != -9223372036854775807L;
    }

    private BaseMediaChunk getLastMediaChunk() {
        if (this.mediaChunks.isEmpty()) {
            return null;
        }
        return this.mediaChunks.get(r0.size() - 1);
    }

    private void discardDownstreamMediaChunks(int i) {
        if (this.mediaChunks.isEmpty()) {
            return;
        }
        while (this.mediaChunks.size() > 1 && this.mediaChunks.get(1).getFirstSampleIndex(0) <= i) {
            this.mediaChunks.removeFirst();
        }
        BaseMediaChunk first = this.mediaChunks.getFirst();
        Format format = first.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, first.trackSelectionReason, first.trackSelectionData, first.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }

    public final class EmbeddedSampleStream implements SampleStream {
        private final int index;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
        }

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue, int i) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue;
            this.index = i;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            ChunkSampleStream chunkSampleStream = ChunkSampleStream.this;
            return chunkSampleStream.loadingFinished || (!chunkSampleStream.isPendingReset() && this.sampleQueue.hasNextSample());
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void skipData(long j) {
            if (ChunkSampleStream.this.loadingFinished && j > this.sampleQueue.getLargestQueuedTimestampUs()) {
                this.sampleQueue.advanceToEnd();
            } else {
                this.sampleQueue.advanceTo(j, true, true);
            }
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            SampleQueue sampleQueue = this.sampleQueue;
            ChunkSampleStream chunkSampleStream = ChunkSampleStream.this;
            return sampleQueue.read(formatHolder, decoderInputBuffer, z, chunkSampleStream.loadingFinished, chunkSampleStream.lastSeekPositionUs);
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }
    }
}
