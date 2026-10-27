package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
final class ChannelMappingAudioProcessor implements AudioProcessor {
    private boolean active;
    private ByteBuffer buffer;
    private int channelCount;
    private boolean inputEnded;
    private ByteBuffer outputBuffer;
    private int[] outputChannels;
    private int[] pendingOutputChannels;
    private int sampleRateHz;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputEncoding() {
        return 2;
    }

    public ChannelMappingAudioProcessor() {
        ByteBuffer byteBuffer = AudioProcessor.EMPTY_BUFFER;
        this.buffer = byteBuffer;
        this.outputBuffer = byteBuffer;
        this.channelCount = -1;
        this.sampleRateHz = -1;
    }

    public void setChannelMap(int[] iArr) {
        this.pendingOutputChannels = iArr;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean configure(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        boolean z = !Arrays.equals(this.pendingOutputChannels, this.outputChannels);
        int[] iArr = this.pendingOutputChannels;
        this.outputChannels = iArr;
        if (iArr == null) {
            this.active = false;
            return z;
        }
        if (i3 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        }
        if (!z && this.sampleRateHz == i && this.channelCount == i2) {
            return false;
        }
        this.sampleRateHz = i;
        this.channelCount = i2;
        this.active = i2 != iArr.length;
        int i4 = 0;
        while (true) {
            int[] iArr2 = this.outputChannels;
            if (i4 >= iArr2.length) {
                return true;
            }
            int i5 = iArr2[i4];
            if (i5 >= i2) {
                throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
            }
            this.active = (i5 != i4) | this.active;
            i4++;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isActive() {
        return this.active;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int getOutputChannelCount() {
        int[] iArr = this.outputChannels;
        return iArr == null ? this.channelCount : iArr.length;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueInput(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int length = ((iLimit - iPosition) / (this.channelCount * 2)) * this.outputChannels.length * 2;
        if (this.buffer.capacity() < length) {
            this.buffer = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        while (iPosition < iLimit) {
            for (int i : this.outputChannels) {
                this.buffer.putShort(byteBuffer.getShort((i * 2) + iPosition));
            }
            iPosition += this.channelCount * 2;
        }
        byteBuffer.position(iLimit);
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == AudioProcessor.EMPTY_BUFFER;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void flush() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.inputEnded = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void reset() {
        flush();
        this.buffer = AudioProcessor.EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputChannels = null;
        this.active = false;
    }
}
