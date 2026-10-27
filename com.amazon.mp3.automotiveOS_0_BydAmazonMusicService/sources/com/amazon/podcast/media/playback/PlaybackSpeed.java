package com.amazon.podcast.media.playback;

/* JADX INFO: loaded from: classes5.dex */
public enum PlaybackSpeed {
    POINT_5(0.5f, "0.5 x", "0.5X"),
    POINT_8(0.8f, "0.8 x", "0.8X"),
    ONE(1.0f, "1 x", "1X"),
    ONE_POINT_TWO(1.2f, "1.2 x", "1.2X"),
    ONE_POINT_FOUR(1.4f, "1.4 x", "1.4X"),
    ONE_POINT_FIVE(1.5f, "1.5 x", "1.5X"),
    ONE_POINT_SIX(1.6f, "1.6 x", "1.6X"),
    ONE_POINT_EIGHT(1.8f, "1.8 x", "1.8X"),
    TWO(2.0f, "2 x", "2X"),
    THREE(3.0f, "3 x", "3X");

    private final String displayText;
    private final String iconText;
    private final float value;

    PlaybackSpeed(final float value, final String displayText, final String iconText) {
        this.displayText = displayText;
        this.iconText = iconText;
        this.value = value;
    }

    public static PlaybackSpeed lookupByValue(final float speed) {
        for (PlaybackSpeed playbackSpeed : values()) {
            if (playbackSpeed.getValue() == speed) {
                return playbackSpeed;
            }
        }
        return null;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public String getIconText() {
        return this.iconText;
    }

    public float getValue() {
        return this.value;
    }
}
