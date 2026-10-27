package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.InvalidRendererTimestampException;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.visualon.OSMPUtils.voMimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class NativePlaybackStatus {
    private static final int AUDIO_BITRATE = 96;
    private static final int BYTES_IN_AUDIO_BUFFER = 40;
    private static final int BYTES_IN_VIDEO_BUFFER = 48;
    private static final int END_OF_STREAM_STATUS = 88;
    private static final long IMPOSSIBLY_LARGE_TIMESTAMP_US = TimeSpan.fromHours(1000000).getTotalMicroseconds();
    private static final int LAST_AUDIO_TIMESTAMP_PASSED_TO_RENDERER = 8;
    private static final int LAST_RENDERED_TIMESTAMP = 0;
    private static final int LAST_VIDEO_TIMESTAMP_PASSED_TO_RENDERER = 16;
    private static final int MOST_RECENT_ERROR_CODE = 112;
    public static final int NATIVE_STATUS_OK = 0;
    private static final int SAMPLES_IN_AUDIO_BUFFER = 24;
    private static final int SAMPLES_IN_VIDEO_BUFFER = 32;
    private static final int SCALED_AUDIO_DELIVERY_FPS = 56;
    private static final int SCALED_AUDIO_RENDERING_FPS = 72;
    private static final int SCALED_VIDEO_DELIVERY_FPS = 64;
    private static final int SCALED_VIDEO_RENDERING_FPS = 80;
    private static final int VIDEO_BITRATE = 104;
    private final Object mMutex = new Object();
    private ByteBuffer mSourceBuffer;

    public void init(ByteBuffer byteBuffer) {
        synchronized (this.mMutex) {
            ByteBuffer byteBuffer2 = (ByteBuffer) Preconditions.checkNotNull(byteBuffer);
            this.mSourceBuffer = byteBuffer2;
            byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
        }
    }

    public boolean haveStreamsReachedEnd() {
        long valueFromBuffer = getValueFromBuffer(88);
        if (((-4) & valueFromBuffer) != 0) {
            DLog.errorf("INVALID STREAM STATUS FLAGS: 0x%x read from the source buffer", Long.valueOf(valueFromBuffer));
        } else if (valueFromBuffer != 0) {
            DLog.devf(valueFromBuffer == 1 ? "Audio stream has reached EOS" : valueFromBuffer == 2 ? "Video stream has reached EOS" : "Audio and video streams have both reached EOS");
        }
        return valueFromBuffer == 3;
    }

    public long getLastRenderedTimeStampInNanos() throws InvalidRendererTimestampException {
        long valueFromBuffer = getValueFromBuffer(0);
        if (valueFromBuffer <= 0 || valueFromBuffer > IMPOSSIBLY_LARGE_TIMESTAMP_US) {
            throw new InvalidRendererTimestampException(String.format(Locale.US, "Invalid last rendered timestamp: %d microseconds", Long.valueOf(valueFromBuffer)));
        }
        return TimeUnit.MICROSECONDS.toNanos(valueFromBuffer);
    }

    public long getLastTimeStampPassedToRendererInNanos(SampleType sampleType) {
        return TimeUnit.MICROSECONDS.toNanos(Math.max(0L, getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 16 : 8)));
    }

    public void setLastTimeStampPassedToRenderInNanos(SampleType sampleType, long j) {
        setValueToBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 16 : 8, TimeUnit.NANOSECONDS.toMicros(Math.max(0L, j)));
    }

    public long getNumberOfBytesInQueue(SampleType sampleType) {
        return getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 48 : 40);
    }

    public long getNumberOfSamplesInQueue(SampleType sampleType) {
        return getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 32 : 24);
    }

    public double getDeliveryFPS(SampleType sampleType) {
        return getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 64 : 56) / 1000000.0d;
    }

    public double getRenderingFPS(SampleType sampleType) {
        return getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 80 : 72) / 1000000.0d;
    }

    public int getBitrate(SampleType sampleType) {
        return (int) getValueFromBuffer(sampleType == SampleType.VIDEO_SAMPLE ? 104 : 96);
    }

    private long getValueFromBuffer(int i) {
        long j;
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mSourceBuffer != null);
            j = this.mSourceBuffer.getLong(i);
        }
        return j;
    }

    private void setValueToBuffer(int i, long j) {
        synchronized (this.mMutex) {
            Preconditions.checkState(this.mSourceBuffer != null);
            this.mSourceBuffer.putLong(i, j);
        }
    }

    public int checkRendererError() {
        return (int) getValueFromBuffer(112);
    }

    public String toString() {
        String str;
        String str2 = "INVALID";
        try {
            str = String.format(Locale.US, "%.03fs", Float.valueOf(TimeUnit.NANOSECONDS.toMillis(getLastRenderedTimeStampInNanos()) / 1000.0f));
        } catch (InvalidRendererTimestampException unused) {
            str = "INVALID";
        }
        long valueFromBuffer = getValueFromBuffer(88);
        if (valueFromBuffer == 0) {
            str2 = "false";
        } else if (valueFromBuffer == 1) {
            str2 = voMimeTypes.VOBASE_TYPE_AUDIO;
        } else if (valueFromBuffer == 2) {
            str2 = voMimeTypes.VOBASE_TYPE_VIDEO;
        } else if (valueFromBuffer == 3) {
            str2 = "audio and video";
        }
        Locale locale = Locale.US;
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        SampleType sampleType = SampleType.AUDIO_SAMPLE;
        SampleType sampleType2 = SampleType.VIDEO_SAMPLE;
        return String.format(locale, "LastSubmitted=(%.03fs,%.03fs) LastRendered=%s BufferedSamples=(%d,%d) BufferedBytes=(%d,%d) DeliveryFPS=(%.03f,%.03f) RenderingFPS=(%.03f,%.03f) Bitrate=(%d,%d) ReachedEndOfStream: %s", Float.valueOf(timeUnit.toMillis(getLastTimeStampPassedToRendererInNanos(sampleType)) / 1000.0f), Float.valueOf(timeUnit.toMillis(getLastTimeStampPassedToRendererInNanos(sampleType2)) / 1000.0f), str, Long.valueOf(getNumberOfSamplesInQueue(sampleType)), Long.valueOf(getNumberOfSamplesInQueue(sampleType2)), Long.valueOf(getNumberOfBytesInQueue(sampleType)), Long.valueOf(getNumberOfBytesInQueue(sampleType2)), Double.valueOf(getDeliveryFPS(sampleType)), Double.valueOf(getDeliveryFPS(sampleType2)), Double.valueOf(getRenderingFPS(sampleType)), Double.valueOf(getRenderingFPS(sampleType2)), Integer.valueOf(getBitrate(sampleType)), Integer.valueOf(getBitrate(sampleType2)), str2);
    }
}
