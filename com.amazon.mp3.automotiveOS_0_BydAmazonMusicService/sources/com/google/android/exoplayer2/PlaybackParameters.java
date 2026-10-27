package com.google.android.exoplayer2;

/* JADX INFO: loaded from: classes6.dex */
public final class PlaybackParameters {
    public static final PlaybackParameters DEFAULT = new PlaybackParameters(1.0f, 1.0f);
    public final float pitch;
    private final int scaledUsPerMs;
    public final float speed;

    public PlaybackParameters(float f, float f2) {
        this.speed = f;
        this.pitch = f2;
        this.scaledUsPerMs = Math.round(f * 1000.0f);
    }

    public long getSpeedAdjustedDurationUs(long j) {
        return j * ((long) this.scaledUsPerMs);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PlaybackParameters.class != obj.getClass()) {
            return false;
        }
        PlaybackParameters playbackParameters = (PlaybackParameters) obj;
        return this.speed == playbackParameters.speed && this.pitch == playbackParameters.pitch;
    }

    public int hashCode() {
        return ((527 + Float.floatToRawIntBits(this.speed)) * 31) + Float.floatToRawIntBits(this.pitch);
    }
}
