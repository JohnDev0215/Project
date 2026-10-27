package com.amazon.music.media.playback;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackAttributes {
    public static final PlaybackAttributes UNKNOWN = new PlaybackAttributes(PlaybackAudioQuality.UNKNOWN, -1, -1, -1);
    private final int bitDepth;
    private final int bitrate;
    private final PlaybackAudioQuality playbackAudioQuality;
    private final int sampleRate;

    public PlaybackAttributes(@NonNull PlaybackAudioQuality playbackAudioQuality, int i, int i2, int i3) {
        this.playbackAudioQuality = playbackAudioQuality;
        this.bitrate = i;
        this.bitDepth = i2;
        this.sampleRate = i3;
    }

    @NonNull
    public PlaybackAudioQuality getPlaybackAudioQuality() {
        return this.playbackAudioQuality;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public int getBitDepth() {
        return this.bitDepth;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public String toString() {
        return " PlaybackAttributes { playbackAudioQuality = " + this.playbackAudioQuality + ",bitrate = " + this.bitrate + ", bitDepth = " + this.bitDepth + ", sampleRate = " + this.sampleRate + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlaybackAttributes playbackAttributes = (PlaybackAttributes) obj;
        return this.bitrate == playbackAttributes.bitrate && this.bitDepth == playbackAttributes.bitDepth && this.sampleRate == playbackAttributes.sampleRate && this.playbackAudioQuality.equals(playbackAttributes.playbackAudioQuality);
    }

    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.playbackAudioQuality.hashCode()) * 31) + this.bitrate) * 31) + this.bitDepth) * 31) + this.sampleRate;
    }
}
