package com.stingray.karaoke.car.utils;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/karaoke/car/utils/WindowSizeClass;", "", "<init>", "(Ljava/lang/String;I)V", "COMPACT", "MEDIUM", "EXPANDED", "ULTRA", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class WindowSizeClass {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ WindowSizeClass[] $VALUES;
    public static final WindowSizeClass COMPACT = new WindowSizeClass("COMPACT", 0);
    public static final WindowSizeClass MEDIUM = new WindowSizeClass("MEDIUM", 1);
    public static final WindowSizeClass EXPANDED = new WindowSizeClass("EXPANDED", 2);
    public static final WindowSizeClass ULTRA = new WindowSizeClass("ULTRA", 3);

    private static final /* synthetic */ WindowSizeClass[] $values() {
        return new WindowSizeClass[]{COMPACT, MEDIUM, EXPANDED, ULTRA};
    }

    static {
        WindowSizeClass[] windowSizeClassArr$values = $values();
        $VALUES = windowSizeClassArr$values;
        $ENTRIES = r.e(windowSizeClassArr$values);
    }

    private WindowSizeClass(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static WindowSizeClass valueOf(String str) {
        return (WindowSizeClass) Enum.valueOf(WindowSizeClass.class, str);
    }

    public static WindowSizeClass[] values() {
        return (WindowSizeClass[]) $VALUES.clone();
    }
}
