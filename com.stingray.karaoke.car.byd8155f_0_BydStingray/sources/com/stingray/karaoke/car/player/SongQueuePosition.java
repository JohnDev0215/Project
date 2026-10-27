package com.stingray.karaoke.car.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/karaoke/car/player/SongQueuePosition;", "", "<init>", "(Ljava/lang/String;I)V", "SING_NOW", "SING_NEXT", "SING_LAST", "SCORE_NOW", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SongQueuePosition {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ SongQueuePosition[] $VALUES;
    public static final SongQueuePosition SING_NOW = new SongQueuePosition("SING_NOW", 0);
    public static final SongQueuePosition SING_NEXT = new SongQueuePosition("SING_NEXT", 1);
    public static final SongQueuePosition SING_LAST = new SongQueuePosition("SING_LAST", 2);
    public static final SongQueuePosition SCORE_NOW = new SongQueuePosition("SCORE_NOW", 3);

    private static final /* synthetic */ SongQueuePosition[] $values() {
        return new SongQueuePosition[]{SING_NOW, SING_NEXT, SING_LAST, SCORE_NOW};
    }

    static {
        SongQueuePosition[] songQueuePositionArr$values = $values();
        $VALUES = songQueuePositionArr$values;
        $ENTRIES = r.e(songQueuePositionArr$values);
    }

    private SongQueuePosition(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static SongQueuePosition valueOf(String str) {
        return (SongQueuePosition) Enum.valueOf(SongQueuePosition.class, str);
    }

    public static SongQueuePosition[] values() {
        return (SongQueuePosition[]) $VALUES.clone();
    }
}
