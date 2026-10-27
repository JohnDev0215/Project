package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;

/* JADX INFO: loaded from: classes6.dex */
public final class ClippingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    public final MediaPeriod mediaPeriod;
    private boolean pendingInitialDiscontinuity;
    private long startUs = -9223372036854775807L;
    private long endUs = -9223372036854775807L;
    private ClippingSampleStream[] sampleStreams = new ClippingSampleStream[0];

    public ClippingMediaPeriod(MediaPeriod mediaPeriod, boolean z) {
        this.mediaPeriod = mediaPeriod;
        this.pendingInitialDiscontinuity = z;
    }

    public void setClipping(long j, long j2) {
        this.startUs = j;
        this.endUs = j2;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.mediaPeriod.prepare(this, this.startUs + j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        this.mediaPeriod.maybeThrowPrepareError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.mediaPeriod.getTrackGroups();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0067  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[] r18, boolean[] r19, com.google.android.exoplayer2.source.SampleStream[] r20, boolean[] r21, long r22) {
        /*
            r17 = this;
            r8 = r17
            r9 = r20
            int r0 = r9.length
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = new com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream[r0]
            r8.sampleStreams = r0
            int r0 = r9.length
            com.google.android.exoplayer2.source.SampleStream[] r10 = new com.google.android.exoplayer2.source.SampleStream[r0]
            r0 = 0
            r1 = r0
        Le:
            int r2 = r9.length
            r11 = 0
            if (r1 >= r2) goto L25
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r2 = r8.sampleStreams
            r3 = r9[r1]
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r3 = (com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream) r3
            r2[r1] = r3
            if (r3 == 0) goto L20
            com.google.android.exoplayer2.source.SampleStream r11 = com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream.access$000(r3)
        L20:
            r10[r1] = r11
            int r1 = r1 + 1
            goto Le
        L25:
            com.google.android.exoplayer2.source.MediaPeriod r1 = r8.mediaPeriod
            long r2 = r8.startUs
            long r6 = r22 + r2
            r2 = r18
            r3 = r19
            r4 = r10
            r5 = r21
            long r12 = r1.selectTracks(r2, r3, r4, r5, r6)
            boolean r1 = r8.pendingInitialDiscontinuity
            r2 = 1
            if (r1 == 0) goto L4e
            long r3 = r8.startUs
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 == 0) goto L4b
            boolean r1 = shouldKeepInitialDiscontinuity(r18)
            if (r1 == 0) goto L4b
            r1 = r2
            goto L4c
        L4b:
            r1 = r0
        L4c:
            r8.pendingInitialDiscontinuity = r1
        L4e:
            long r3 = r8.startUs
            long r5 = r22 + r3
            int r1 = (r12 > r5 ? 1 : (r12 == r5 ? 0 : -1))
            if (r1 == 0) goto L68
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 < 0) goto L67
            long r3 = r8.endUs
            r5 = -9223372036854775808
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 == 0) goto L68
            int r1 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r1 > 0) goto L67
            goto L68
        L67:
            r2 = r0
        L68:
            com.google.android.exoplayer2.util.Assertions.checkState(r2)
            r14 = r0
        L6c:
            int r0 = r9.length
            if (r14 >= r0) goto La6
            r0 = r10[r14]
            if (r0 != 0) goto L78
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r8.sampleStreams
            r0[r14] = r11
            goto L9d
        L78:
            r0 = r9[r14]
            if (r0 == 0) goto L88
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r8.sampleStreams
            r0 = r0[r14]
            com.google.android.exoplayer2.source.SampleStream r0 = com.google.android.exoplayer2.source.ClippingMediaPeriod.ClippingSampleStream.access$000(r0)
            r1 = r10[r14]
            if (r0 == r1) goto L9d
        L88:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r15 = r8.sampleStreams
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream r16 = new com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream
            r2 = r10[r14]
            long r3 = r8.startUs
            long r5 = r8.endUs
            boolean r7 = r8.pendingInitialDiscontinuity
            r0 = r16
            r1 = r17
            r0.<init>(r1, r2, r3, r5, r7)
            r15[r14] = r16
        L9d:
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r8.sampleStreams
            r0 = r0[r14]
            r9[r14] = r0
            int r14 = r14 + 1
            goto L6c
        La6:
            long r0 = r8.startUs
            long r12 = r12 - r0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j) {
        this.mediaPeriod.discardBuffer(j + this.startUs);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        if (this.pendingInitialDiscontinuity) {
            for (ClippingSampleStream clippingSampleStream : this.sampleStreams) {
                if (clippingSampleStream != null) {
                    clippingSampleStream.clearPendingDiscontinuity();
                }
            }
            this.pendingInitialDiscontinuity = false;
            long discontinuity = readDiscontinuity();
            if (discontinuity != -9223372036854775807L) {
                return discontinuity;
            }
            return 0L;
        }
        long discontinuity2 = this.mediaPeriod.readDiscontinuity();
        if (discontinuity2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        Assertions.checkState(discontinuity2 >= this.startUs);
        long j = this.endUs;
        Assertions.checkState(j == Long.MIN_VALUE || discontinuity2 <= j);
        return discontinuity2 - this.startUs;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long bufferedPositionUs = this.mediaPeriod.getBufferedPositionUs();
        if (bufferedPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || bufferedPositionUs < j) {
                return Math.max(0L, bufferedPositionUs - this.startUs);
            }
        }
        return Long.MIN_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long seekToUs(long r6) {
        /*
            r5 = this;
            com.google.android.exoplayer2.source.ClippingMediaPeriod$ClippingSampleStream[] r0 = r5.sampleStreams
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L5:
            if (r3 >= r1) goto L11
            r4 = r0[r3]
            if (r4 == 0) goto Le
            r4.clearSentEos()
        Le:
            int r3 = r3 + 1
            goto L5
        L11:
            com.google.android.exoplayer2.source.MediaPeriod r0 = r5.mediaPeriod
            long r3 = r5.startUs
            long r3 = r3 + r6
            long r0 = r0.seekToUs(r3)
            long r3 = r5.startUs
            long r6 = r6 + r3
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 == 0) goto L31
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 < 0) goto L32
            long r6 = r5.endUs
            r3 = -9223372036854775808
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L31
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 > 0) goto L32
        L31:
            r2 = 1
        L32:
            com.google.android.exoplayer2.util.Assertions.checkState(r2)
            long r6 = r5.startUs
            long r0 = r0 - r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.ClippingMediaPeriod.seekToUs(long):long");
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        long nextLoadPositionUs = this.mediaPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs != Long.MIN_VALUE) {
            long j = this.endUs;
            if (j == Long.MIN_VALUE || nextLoadPositionUs < j) {
                return nextLoadPositionUs - this.startUs;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        return this.mediaPeriod.continueLoading(j + this.startUs);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        Assertions.checkState((this.startUs == -9223372036854775807L || this.endUs == -9223372036854775807L) ? false : true);
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.callback.onContinueLoadingRequested(this);
    }

    private static boolean shouldKeepInitialDiscontinuity(TrackSelection[] trackSelectionArr) {
        for (TrackSelection trackSelection : trackSelectionArr) {
            if (trackSelection != null && !MimeTypes.isAudio(trackSelection.getSelectedFormat().sampleMimeType)) {
                return true;
            }
        }
        return false;
    }

    private static final class ClippingSampleStream implements SampleStream {
        private final long endUs;
        private final MediaPeriod mediaPeriod;
        private boolean pendingDiscontinuity;
        private boolean sentEos;
        private final long startUs;
        private final SampleStream stream;

        public ClippingSampleStream(MediaPeriod mediaPeriod, SampleStream sampleStream, long j, long j2, boolean z) {
            this.mediaPeriod = mediaPeriod;
            this.stream = sampleStream;
            this.startUs = j;
            this.endUs = j2;
            this.pendingDiscontinuity = z;
        }

        public void clearPendingDiscontinuity() {
            this.pendingDiscontinuity = false;
        }

        public void clearSentEos() {
            this.sentEos = false;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return this.stream.isReady();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            this.stream.maybeThrowError();
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (this.pendingDiscontinuity) {
                return -3;
            }
            if (this.sentEos) {
                decoderInputBuffer.setFlags(4);
                return -4;
            }
            int data = this.stream.readData(formatHolder, decoderInputBuffer, z);
            long j = this.endUs;
            if (j != Long.MIN_VALUE && ((data == -4 && decoderInputBuffer.timeUs >= j) || (data == -3 && this.mediaPeriod.getBufferedPositionUs() == Long.MIN_VALUE))) {
                decoderInputBuffer.clear();
                decoderInputBuffer.setFlags(4);
                this.sentEos = true;
                return -4;
            }
            if (data == -4 && !decoderInputBuffer.isEndOfStream()) {
                decoderInputBuffer.timeUs -= this.startUs;
            }
            return data;
        }

        @Override // com.google.android.exoplayer2.source.SampleStream
        public void skipData(long j) {
            this.stream.skipData(this.startUs + j);
        }
    }
}
