package com.amazon.music.media.playback.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.digitalmusicplayback.AudioAttributes;
import com.amazon.digitalmusicplayback.DeviceCapability;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlayerCapabilities;

/* JADX INFO: loaded from: classes4.dex */
public class DomainModelTranslator {
    @NonNull
    public static PlaybackAttributes translateCurrrentPlaybackAttributes(AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return PlaybackAttributes.UNKNOWN;
        }
        return new PlaybackAttributes(PlaybackAudioQuality.fromPlayerAudioQuality(audioAttributes.getAudioQuality()), audioAttributes.getBitrate(), audioAttributes.getBitDepth(), audioAttributes.getSampleRate());
    }

    @NonNull
    public static PlaybackAttributes translateBestAvailablePlaybackAttributes(AudioAttributes audioAttributes) {
        if (audioAttributes == null) {
            return PlaybackAttributes.UNKNOWN;
        }
        return new PlaybackAttributes(PlaybackAudioQuality.fromPlayerAudioQuality(audioAttributes.getBestAvailableAudioQuality()), -1, audioAttributes.getBestAvailableBitDepth(), audioAttributes.getBestAvailableSampleRate());
    }

    @Nullable
    public static PlayerCapabilities translate(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            return null;
        }
        return new PlayerCapabilities(deviceCapability.getSupportsUHD(), deviceCapability.getMaximumBitDepth(), deviceCapability.getMaximumSampleRate());
    }
}
