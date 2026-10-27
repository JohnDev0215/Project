package com.amazon.avod.media.playback.support;

/* JADX INFO: loaded from: classes2.dex */
public enum PlaybackSupportInfo {
    SUPPORTED_SOFTWARE(true, 1),
    SUPPORTED_SOFTWARE_PERFORMANCE_DEGRADATION(true, 2),
    SUPPORTED_HARDWARE(true, 3),
    SUPPORTED_HARDWARE_WITH_SOFTWARE_DRM(true, 4),
    NOT_SUPPORTED_HARDWARE_ARCHITECTURE(false, 5),
    NOT_SUPPORTED_LIMITED_CAPABILITIES(false, 6),
    NOT_SUPPORTED_PLATFORM_OS(false, 7),
    NOT_SUPPORTED_BLACK_LISTED(false, 8),
    NOT_SUPPORTED_NATIVE_LIB_LOAD_TIMEOUT(false, 9);

    private static final int PLAYBACK_NOT_SUPPORTED = Integer.MIN_VALUE;
    private final int mSupportLevel;

    PlaybackSupportInfo(boolean z, int i) {
        this.mSupportLevel = z ? i : i | Integer.MIN_VALUE;
    }

    public boolean isPlaybackSupported() {
        return (this.mSupportLevel & Integer.MIN_VALUE) == 0;
    }

    public boolean isPlaybackSustainable() {
        return isPlaybackSupported() && this != SUPPORTED_SOFTWARE_PERFORMANCE_DEGRADATION;
    }

    public int getSupportLevel() {
        return this.mSupportLevel;
    }

    public static PlaybackSupportInfo lookup(int i) {
        for (PlaybackSupportInfo playbackSupportInfo : values()) {
            if (playbackSupportInfo.mSupportLevel == i) {
                return playbackSupportInfo;
            }
        }
        return null;
    }
}
