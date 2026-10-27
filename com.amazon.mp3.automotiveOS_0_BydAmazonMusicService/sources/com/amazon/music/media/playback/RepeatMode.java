package com.amazon.music.media.playback;

import java.util.EnumSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public enum RepeatMode {
    REPEAT_NONE,
    REPEAT_ALL,
    REPEAT_ONE;

    public static final EnumSet<RepeatMode> ALL_REPEAT_MODES;
    public static final EnumSet<RepeatMode> REPEAT_NONE_ONLY;
    public static final EnumSet<RepeatMode> REPEAT_ONE_OR_NONE;

    static {
        RepeatMode repeatMode = REPEAT_NONE;
        RepeatMode repeatMode2 = REPEAT_ONE;
        ALL_REPEAT_MODES = EnumSet.allOf(RepeatMode.class);
        REPEAT_NONE_ONLY = EnumSet.of(repeatMode);
        REPEAT_ONE_OR_NONE = EnumSet.of(repeatMode2, repeatMode);
    }

    public static RepeatMode getNextRepeatMode(RepeatMode repeatMode, Set<RepeatMode> set) {
        if (set == null) {
            return REPEAT_NONE;
        }
        int iOrdinal = repeatMode == null ? -1 : repeatMode.ordinal();
        int length = values().length;
        for (int i = 1; i <= length; i++) {
            RepeatMode repeatMode2 = values()[(iOrdinal + i) % length];
            if (set.contains(repeatMode2)) {
                return repeatMode2;
            }
        }
        return REPEAT_NONE;
    }
}
