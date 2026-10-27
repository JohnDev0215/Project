package com.stingray.karaoke.car.ui.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/karaoke/car/ui/player/PlaybackType;", "", "<init>", "(Ljava/lang/String;I)V", "PLAY_PAUSE", "SKIP_NEXT", "SKIP_PREVIOUS", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PlaybackType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PlaybackType[] $VALUES;
    public static final PlaybackType PLAY_PAUSE = new PlaybackType("PLAY_PAUSE", 0);
    public static final PlaybackType SKIP_NEXT = new PlaybackType("SKIP_NEXT", 1);
    public static final PlaybackType SKIP_PREVIOUS = new PlaybackType("SKIP_PREVIOUS", 2);

    private static final /* synthetic */ PlaybackType[] $values() {
        return new PlaybackType[]{PLAY_PAUSE, SKIP_NEXT, SKIP_PREVIOUS};
    }

    static {
        PlaybackType[] playbackTypeArr$values = $values();
        $VALUES = playbackTypeArr$values;
        $ENTRIES = r.e(playbackTypeArr$values);
    }

    private PlaybackType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PlaybackType valueOf(String str) {
        return (PlaybackType) Enum.valueOf(PlaybackType.class, str);
    }

    public static PlaybackType[] values() {
        return (PlaybackType[]) $VALUES.clone();
    }
}
