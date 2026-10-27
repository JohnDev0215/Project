package com.stingray.karaoke.car.p000enum;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/stingray/karaoke/car/enum/KaraokeAssetAccess;", "", "<init>", "(Ljava/lang/String;I)V", "None", "InSubscription", "Purchased", "Showcase", "Sample", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeAssetAccess {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ KaraokeAssetAccess[] $VALUES;
    public static final KaraokeAssetAccess None = new KaraokeAssetAccess("None", 0);
    public static final KaraokeAssetAccess InSubscription = new KaraokeAssetAccess("InSubscription", 1);
    public static final KaraokeAssetAccess Purchased = new KaraokeAssetAccess("Purchased", 2);
    public static final KaraokeAssetAccess Showcase = new KaraokeAssetAccess("Showcase", 3);
    public static final KaraokeAssetAccess Sample = new KaraokeAssetAccess("Sample", 4);

    private static final /* synthetic */ KaraokeAssetAccess[] $values() {
        return new KaraokeAssetAccess[]{None, InSubscription, Purchased, Showcase, Sample};
    }

    static {
        KaraokeAssetAccess[] karaokeAssetAccessArr$values = $values();
        $VALUES = karaokeAssetAccessArr$values;
        $ENTRIES = r.e(karaokeAssetAccessArr$values);
    }

    private KaraokeAssetAccess(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static KaraokeAssetAccess valueOf(String str) {
        return (KaraokeAssetAccess) Enum.valueOf(KaraokeAssetAccess.class, str);
    }

    public static KaraokeAssetAccess[] values() {
        return (KaraokeAssetAccess[]) $VALUES.clone();
    }
}
