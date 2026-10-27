package com.amazon.avod.media;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.EnumSet;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum AudioFormat {
    STEREO,
    AC_3_5_1,
    AC_3_7_1;

    public static final AudioFormat DEFAULT;
    private static final ImmutableMap<AudioFormat, ImmutableSet<AudioFormat>> LESS_THAN_OR_EQUAL_AUDIO_FORMATS;
    public static final AudioFormat MAX;

    static {
        AudioFormat audioFormat = STEREO;
        AudioFormat audioFormat2 = AC_3_5_1;
        AudioFormat audioFormat3 = AC_3_7_1;
        DEFAULT = audioFormat;
        MAX = audioFormat3;
        LESS_THAN_OR_EQUAL_AUDIO_FORMATS = ImmutableMap.of(audioFormat3, ImmutableSet.of(audioFormat, audioFormat2, audioFormat3), audioFormat2, ImmutableSet.of(audioFormat, audioFormat2), audioFormat, ImmutableSet.of(audioFormat));
    }

    public static AudioFormat fromString(String str) {
        for (AudioFormat audioFormat : EnumSet.allOf(AudioFormat.class)) {
            if (audioFormat.name().equals(str)) {
                return audioFormat;
            }
        }
        return DEFAULT;
    }

    public boolean isSurroundSound() {
        return this != STEREO;
    }

    @Nonnull
    public static ImmutableSet<AudioFormat> getLessThanOrEqualFormats(@Nonnull AudioFormat audioFormat) {
        ImmutableMap<AudioFormat, ImmutableSet<AudioFormat>> immutableMap = LESS_THAN_OR_EQUAL_AUDIO_FORMATS;
        Preconditions.checkArgument(immutableMap.containsKey(audioFormat));
        return immutableMap.get(audioFormat);
    }
}
