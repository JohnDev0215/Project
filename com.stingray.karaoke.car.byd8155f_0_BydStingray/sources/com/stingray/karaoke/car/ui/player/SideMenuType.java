package com.stingray.karaoke.car.ui.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/stingray/karaoke/car/ui/player/SideMenuType;", "", "<init>", "(Ljava/lang/String;I)V", "SONG_QUEUE", "COMPANION", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SideMenuType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ SideMenuType[] $VALUES;
    public static final SideMenuType SONG_QUEUE = new SideMenuType("SONG_QUEUE", 0);
    public static final SideMenuType COMPANION = new SideMenuType("COMPANION", 1);

    private static final /* synthetic */ SideMenuType[] $values() {
        return new SideMenuType[]{SONG_QUEUE, COMPANION};
    }

    static {
        SideMenuType[] sideMenuTypeArr$values = $values();
        $VALUES = sideMenuTypeArr$values;
        $ENTRIES = r.e(sideMenuTypeArr$values);
    }

    private SideMenuType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static SideMenuType valueOf(String str) {
        return (SideMenuType) Enum.valueOf(SideMenuType.class, str);
    }

    public static SideMenuType[] values() {
        return (SideMenuType[]) $VALUES.clone();
    }
}
