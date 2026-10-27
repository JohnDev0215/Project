package com.amazon.avod.playback;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackZoomLevel {
    private final ZoomLevel mZoomLevel;
    private float mZoomRatio;
    public static final PlaybackZoomLevel NO_ZOOM = new PlaybackZoomLevel(ZoomLevel.NO_ZOOM);
    public static final PlaybackZoomLevel NATIVE = new PlaybackZoomLevel(ZoomLevel.NATIVE);
    public static final PlaybackZoomLevel FULL_SCREEN = new PlaybackZoomLevel(ZoomLevel.FULL_SCREEN);
    public static final PlaybackZoomLevel WINDOW_BOXING = new PlaybackZoomLevel(ZoomLevel.WINDOW_BOXING);
    public static final PlaybackZoomLevel RATIO_185_1 = new PlaybackZoomLevel(1.85f);
    public static final PlaybackZoomLevel RATIO_235_1 = new PlaybackZoomLevel(2.35f);
    public static final PlaybackZoomLevel RATIO_239_1 = new PlaybackZoomLevel(2.39f);
    public static final PlaybackZoomLevel INVISIBLE = new PlaybackZoomLevel(ZoomLevel.INVISIBLE);

    public enum ZoomLevel {
        NO_ZOOM,
        NATIVE,
        FULL_SCREEN,
        WINDOW_BOXING,
        CUSTOM,
        INVISIBLE
    }

    private PlaybackZoomLevel(ZoomLevel zoomLevel) {
        this.mZoomLevel = zoomLevel;
        if (zoomLevel == ZoomLevel.NO_ZOOM) {
            this.mZoomRatio = 1.0f;
        }
        if (zoomLevel == ZoomLevel.WINDOW_BOXING) {
            this.mZoomRatio = 0.85f;
        }
    }

    public PlaybackZoomLevel(float f) {
        this(ZoomLevel.CUSTOM);
        this.mZoomRatio = f;
    }

    public ZoomLevel getZoomLevel() {
        return this.mZoomLevel;
    }

    public float getZoomRatio() {
        return this.mZoomRatio;
    }

    @Nonnull
    public static PlaybackZoomLevel getZoomLevelFromName(@Nullable String str) {
        try {
            if (str == null) {
                return NATIVE;
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$PlaybackZoomLevel$ZoomLevel[ZoomLevel.valueOf(str).ordinal()];
            if (i == 1) {
                return WINDOW_BOXING;
            }
            if (i == 2) {
                return FULL_SCREEN;
            }
            return NATIVE;
        } catch (IllegalArgumentException unused) {
            return NATIVE;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.PlaybackZoomLevel$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$PlaybackZoomLevel$ZoomLevel;

        static {
            int[] iArr = new int[ZoomLevel.values().length];
            $SwitchMap$com$amazon$avod$playback$PlaybackZoomLevel$ZoomLevel = iArr;
            try {
                iArr[ZoomLevel.WINDOW_BOXING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$PlaybackZoomLevel$ZoomLevel[ZoomLevel.FULL_SCREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$PlaybackZoomLevel$ZoomLevel[ZoomLevel.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
