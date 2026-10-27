package com.google.android.exoplayer2.text.cea;

import android.util.Log;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import java.util.LinkedList;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes6.dex */
abstract class CeaDecoder implements SubtitleDecoder {
    private static final String TAG = "CeaDecoder";
    private final LinkedList<SubtitleInputBuffer> availableInputBuffers = new LinkedList<>();
    private final LinkedList<SubtitleOutputBuffer> availableOutputBuffers;
    private SubtitleInputBuffer dequeuedInputBuffer;
    private boolean isEndOfStream;
    private long lastDecodedTimestampUs;
    protected long playbackPositionUs;
    private final PriorityQueue<SubtitleInputBuffer> queuedInputBuffers;
    private final LinkedList<SubtitleOutputBuffer> queuedOutputBuffers;

    protected abstract Subtitle createSubtitle();

    protected abstract void decode(SubtitleInputBuffer subtitleInputBuffer);

    protected abstract boolean isNewSubtitleDataAvailable();

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void release() {
    }

    public CeaDecoder() {
        for (int i = 0; i < 10; i++) {
            this.availableInputBuffers.add(new SubtitleInputBuffer());
        }
        this.availableOutputBuffers = new LinkedList<>();
        for (int i2 = 0; i2 < 16; i2++) {
            this.availableOutputBuffers.add(new CeaOutputBuffer(this));
        }
        this.queuedInputBuffers = new PriorityQueue<>();
        this.queuedOutputBuffers = new LinkedList<>();
    }

    @Override // com.google.android.exoplayer2.text.SubtitleDecoder
    public void setPositionUs(long j) {
        this.playbackPositionUs = j;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        Assertions.checkState(this.dequeuedInputBuffer == null);
        if (this.availableInputBuffers.isEmpty()) {
            return null;
        }
        SubtitleInputBuffer subtitleInputBufferPollFirst = this.availableInputBuffers.pollFirst();
        this.dequeuedInputBuffer = subtitleInputBufferPollFirst;
        return subtitleInputBufferPollFirst;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        Assertions.checkArgument(subtitleInputBuffer == this.dequeuedInputBuffer);
        this.isEndOfStream = subtitleInputBuffer.isEndOfStream();
        if (subtitleInputBuffer.isDecodeOnly() || subtitleInputBuffer.timeUs < this.lastDecodedTimestampUs) {
            releaseInputBuffer(subtitleInputBuffer);
        } else {
            this.queuedInputBuffers.add(subtitleInputBuffer);
        }
        this.dequeuedInputBuffer = null;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        while (!this.queuedInputBuffers.isEmpty() && this.queuedInputBuffers.peek().timeUs <= this.playbackPositionUs && (this.isEndOfStream || this.queuedInputBuffers.size() >= 5)) {
            SubtitleInputBuffer subtitleInputBufferPoll = this.queuedInputBuffers.poll();
            if (subtitleInputBufferPoll.isEndOfStream()) {
                SubtitleOutputBuffer subtitleOutputBufferPollFirst = this.availableOutputBuffers.pollFirst();
                if (subtitleOutputBufferPollFirst != null) {
                    subtitleOutputBufferPollFirst.addFlag(4);
                }
                releaseInputBuffer(subtitleInputBufferPoll);
                return subtitleOutputBufferPollFirst;
            }
            this.lastDecodedTimestampUs = subtitleInputBufferPoll.timeUs;
            decode(subtitleInputBufferPoll);
            releaseInputBuffer(subtitleInputBufferPoll);
        }
        return this.queuedOutputBuffers.pollFirst();
    }

    private void releaseInputBuffer(SubtitleInputBuffer subtitleInputBuffer) {
        subtitleInputBuffer.clear();
        this.availableInputBuffers.add(subtitleInputBuffer);
    }

    protected void releaseOutputBuffer(SubtitleOutputBuffer subtitleOutputBuffer) {
        subtitleOutputBuffer.clear();
        this.availableOutputBuffers.add(subtitleOutputBuffer);
    }

    public void onNewSubtitleDataAvailable(long j) {
        if (isNewSubtitleDataAvailable()) {
            SubtitleOutputBuffer subtitleOutputBufferPollFirst = this.availableOutputBuffers.pollFirst();
            if (subtitleOutputBufferPollFirst != null) {
                subtitleOutputBufferPollFirst.setContent(j, createSubtitle(), Long.MAX_VALUE);
                this.queuedOutputBuffers.add(subtitleOutputBufferPollFirst);
            } else {
                Log.w(TAG, "Insufficient Output Buffers for subtitle!!!");
            }
        }
    }

    @Override // com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        this.playbackPositionUs = 0L;
        this.lastDecodedTimestampUs = 0L;
        this.isEndOfStream = false;
        while (!this.queuedInputBuffers.isEmpty()) {
            releaseInputBuffer(this.queuedInputBuffers.poll());
        }
        SubtitleInputBuffer subtitleInputBuffer = this.dequeuedInputBuffer;
        if (subtitleInputBuffer != null) {
            releaseInputBuffer(subtitleInputBuffer);
            this.dequeuedInputBuffer = null;
        }
    }
}
