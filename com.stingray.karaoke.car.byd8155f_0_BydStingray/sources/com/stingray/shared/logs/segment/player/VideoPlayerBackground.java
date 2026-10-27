package com.stingray.shared.logs.segment.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/shared/logs/segment/player/VideoPlayerBackground;", "", "<init>", "(Ljava/lang/String;I)V", "DISABLED", "VIDEO", "CAMERA", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class VideoPlayerBackground {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ VideoPlayerBackground[] $VALUES;
    public static final VideoPlayerBackground DISABLED = new VideoPlayerBackground("DISABLED", 0);
    public static final VideoPlayerBackground VIDEO = new VideoPlayerBackground("VIDEO", 1);
    public static final VideoPlayerBackground CAMERA = new VideoPlayerBackground("CAMERA", 2);

    private static final /* synthetic */ VideoPlayerBackground[] $values() {
        return new VideoPlayerBackground[]{DISABLED, VIDEO, CAMERA};
    }

    static {
        VideoPlayerBackground[] videoPlayerBackgroundArr$values = $values();
        $VALUES = videoPlayerBackgroundArr$values;
        $ENTRIES = r.e(videoPlayerBackgroundArr$values);
    }

    private VideoPlayerBackground(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static VideoPlayerBackground valueOf(String str) {
        return (VideoPlayerBackground) Enum.valueOf(VideoPlayerBackground.class, str);
    }

    public static VideoPlayerBackground[] values() {
        return (VideoPlayerBackground[]) $VALUES.clone();
    }
}
