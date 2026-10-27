package com.stingray.karaoke.car.p000enum;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/karaoke/car/enum/SongIconType;", "", "<init>", "(Ljava/lang/String;I)V", "None", "Free", "Sample", "Purchased", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SongIconType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ SongIconType[] $VALUES;
    public static final SongIconType None = new SongIconType("None", 0);
    public static final SongIconType Free = new SongIconType("Free", 1);
    public static final SongIconType Sample = new SongIconType("Sample", 2);
    public static final SongIconType Purchased = new SongIconType("Purchased", 3);

    private static final /* synthetic */ SongIconType[] $values() {
        return new SongIconType[]{None, Free, Sample, Purchased};
    }

    static {
        SongIconType[] songIconTypeArr$values = $values();
        $VALUES = songIconTypeArr$values;
        $ENTRIES = r.e(songIconTypeArr$values);
    }

    private SongIconType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static SongIconType valueOf(String str) {
        return (SongIconType) Enum.valueOf(SongIconType.class, str);
    }

    public static SongIconType[] values() {
        return (SongIconType[]) $VALUES.clone();
    }
}
