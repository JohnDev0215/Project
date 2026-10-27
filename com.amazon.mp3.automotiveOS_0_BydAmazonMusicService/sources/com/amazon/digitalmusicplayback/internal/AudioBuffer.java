package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioTrack;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
class AudioBuffer {
    private static final int BUFFER_POINTERS_SIZE = 8;
    public static final int ERROR = -100;
    private static final int PACKET_LENGTH_SIZE = 4;
    private AudioFormat mAndroidFormat;
    private final Context mApplicationContext;
    private ByteBuffer mAudioBuffer;
    private final AudioDataSource mAudioDataSource;
    private com.amazon.digitalmusicplayback.AudioFormat mAudioFormat;
    private final DriverEventReporter mDriverEventReporter;
    private final LoggingFacade.Logger mLogger;
    private long mMemoryAddress;
    private ByteBuffer mSilenceBuffer;

    private static native long getDirectBufferAddress(ByteBuffer byteBuffer);

    public AudioBuffer(@NonNull LoggingFacade loggingFacade, @NonNull Context context, @NonNull AudioDataSource audioDataSource, @NonNull DriverEventReporter driverEventReporter) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mAudioDataSource = audioDataSource;
        this.mDriverEventReporter = driverEventReporter;
    }

    public void addSilenceToAudioTrack(AudioTrack audioTrack) {
        if (audioTrack == null) {
            return;
        }
        int minBufferSize = AudioTrack.getMinBufferSize(audioTrack.getSampleRate(), audioTrack.getChannelConfiguration(), audioTrack.getAudioFormat());
        ByteBuffer byteBuffer = this.mSilenceBuffer;
        if (byteBuffer == null || byteBuffer.capacity() < minBufferSize) {
            this.mSilenceBuffer = ByteBuffer.allocate(minBufferSize);
        }
        this.mSilenceBuffer.position(0);
        audioTrack.write(this.mSilenceBuffer, minBufferSize, 1);
    }

    public long fillAudioBuffer(com.amazon.digitalmusicplayback.AudioFormat audioFormat, AudioFormat audioFormat2, int i) {
        if (remaining() > 0) {
            this.mLogger.error("fetchFromAudioDataSource requires the buffer to be empty", new Object[0]);
            return this.mAudioBuffer.remaining();
        }
        ByteBuffer byteBuffer = this.mAudioBuffer;
        if (byteBuffer == null || byteBuffer.capacity() < i + 8) {
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i + 8);
            this.mAudioBuffer = byteBufferAllocateDirect;
            this.mMemoryAddress = getDirectBufferAddress(byteBufferAllocateDirect);
            makeAudioBufferEmpty();
            this.mLogger.debug("Allocated bufferSizeBytes=%d", Integer.valueOf(this.mAudioBuffer.capacity() - 8));
        } else {
            com.amazon.digitalmusicplayback.AudioFormat audioFormat3 = this.mAudioFormat;
            if (audioFormat3 != null && !AudioFormats.equals(audioFormat3, audioFormat)) {
                makeAudioBufferEmpty();
            }
        }
        this.mAudioBuffer.clear();
        this.mAudioBuffer.order(ByteOrder.nativeOrder());
        this.mAudioBuffer.putLong(this.mMemoryAddress + 8);
        this.mAudioFormat = audioFormat;
        this.mAndroidFormat = audioFormat2;
        long jFillAudioDataBuffer = this.mAudioDataSource.fillAudioDataBuffer(audioFormat.getSampleRate(), this.mAudioFormat.getChannelLayout(), this.mAudioFormat.getSampleFormat(), this.mAudioFormat.getChannelCount(), this.mMemoryAddress, i);
        if (jFillAudioDataBuffer > 0) {
            this.mAudioBuffer.limit((int) (8 + jFillAudioDataBuffer));
            this.mAudioBuffer.position(8);
        } else {
            makeAudioBufferEmpty();
        }
        return jFillAudioDataBuffer;
    }

    public void makeAudioBufferEmpty() {
        ByteBuffer byteBuffer = this.mAudioBuffer;
        if (byteBuffer != null) {
            byteBuffer.position(0);
            this.mAudioBuffer.limit(0);
        }
    }

    public int remaining() {
        ByteBuffer byteBuffer = this.mAudioBuffer;
        if (byteBuffer == null) {
            return 0;
        }
        return byteBuffer.remaining();
    }

    public int writeToAudioTrack(AudioTrack audioTrack) {
        if (remaining() == 0) {
            this.mLogger.error("writeToAudioTrack requires the buffer to have data", new Object[0]);
            return 0;
        }
        if (audioTrack.getSampleRate() != this.mAndroidFormat.getSampleRate() || audioTrack.getAudioFormat() != this.mAndroidFormat.getEncoding() || audioTrack.getChannelCount() != this.mAndroidFormat.getChannelCount()) {
            this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "writeToAudioTrack", "AudioTrack format does not match buffer, track=%s buffer=%s", AudioFormats.toShortString(audioTrack), AudioFormats.toShortString(this.mAndroidFormat));
            makeAudioBufferEmpty();
            return -100;
        }
        if (AudioFormats.isPCM(this.mAudioFormat.getSampleFormat())) {
            return audioTrack.write(this.mAudioBuffer, remaining(), 1);
        }
        if (remaining() < 4) {
            this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "writeToAudioTrack", "Frame size is truncated in the buffer, remaining=%d sampleFormat=%s", Integer.valueOf(remaining()), this.mAudioFormat.getSampleFormat());
            makeAudioBufferEmpty();
            return -100;
        }
        int iPosition = this.mAudioBuffer.position();
        int i = this.mAudioBuffer.getInt();
        if (i < 0 || i > remaining()) {
            this.mDriverEventReporter.asyncMetricAndLogProgrammingError(this.mLogger, "writeToAudioTrack", "Bad frame size found in the buffer, frameSize=%d remaining=%d sampleFormat=%s", Integer.valueOf(i), Integer.valueOf(this.mAudioBuffer.remaining()), this.mAudioFormat.getSampleFormat());
            makeAudioBufferEmpty();
            return -100;
        }
        int iWrite = audioTrack.write(this.mAudioBuffer, i, 0);
        if (this.mAudioBuffer.position() != iPosition + 4 + i) {
            this.mAudioBuffer.position(iPosition);
        }
        return iWrite;
    }
}
