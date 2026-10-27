package com.stingray.karaoke.car.ui.navigation;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/karaoke/car/ui/navigation/NavigationType;", "", "<init>", "(Ljava/lang/String;I)V", "TAB", "PLAYER", "MAIN", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class NavigationType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ NavigationType[] $VALUES;
    public static final NavigationType TAB = new NavigationType("TAB", 0);
    public static final NavigationType PLAYER = new NavigationType("PLAYER", 1);
    public static final NavigationType MAIN = new NavigationType("MAIN", 2);

    private static final /* synthetic */ NavigationType[] $values() {
        return new NavigationType[]{TAB, PLAYER, MAIN};
    }

    static {
        NavigationType[] navigationTypeArr$values = $values();
        $VALUES = navigationTypeArr$values;
        $ENTRIES = r.e(navigationTypeArr$values);
    }

    private NavigationType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static NavigationType valueOf(String str) {
        return (NavigationType) Enum.valueOf(NavigationType.class, str);
    }

    public static NavigationType[] values() {
        return (NavigationType[]) $VALUES.clone();
    }
}
