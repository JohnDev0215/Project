package com.stingray.karaoke.car.utils;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/stingray/karaoke/car/utils/LayoutMode;", "", "<init>", "(Ljava/lang/String;I)V", "WIDE_ULTRA", "WIDE_NORMAL", "WIDE_COMPACT", "SQUARE_ULTRA", "SQUARE_NORMAL", "SQUARE_COMPACT", "NARROW_ULTRA", "NARROW_NORMAL", "NARROW_COMPACT", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LayoutMode {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ LayoutMode[] $VALUES;
    public static final LayoutMode WIDE_ULTRA = new LayoutMode("WIDE_ULTRA", 0);
    public static final LayoutMode WIDE_NORMAL = new LayoutMode("WIDE_NORMAL", 1);
    public static final LayoutMode WIDE_COMPACT = new LayoutMode("WIDE_COMPACT", 2);
    public static final LayoutMode SQUARE_ULTRA = new LayoutMode("SQUARE_ULTRA", 3);
    public static final LayoutMode SQUARE_NORMAL = new LayoutMode("SQUARE_NORMAL", 4);
    public static final LayoutMode SQUARE_COMPACT = new LayoutMode("SQUARE_COMPACT", 5);
    public static final LayoutMode NARROW_ULTRA = new LayoutMode("NARROW_ULTRA", 6);
    public static final LayoutMode NARROW_NORMAL = new LayoutMode("NARROW_NORMAL", 7);
    public static final LayoutMode NARROW_COMPACT = new LayoutMode("NARROW_COMPACT", 8);

    private static final /* synthetic */ LayoutMode[] $values() {
        return new LayoutMode[]{WIDE_ULTRA, WIDE_NORMAL, WIDE_COMPACT, SQUARE_ULTRA, SQUARE_NORMAL, SQUARE_COMPACT, NARROW_ULTRA, NARROW_NORMAL, NARROW_COMPACT};
    }

    static {
        LayoutMode[] layoutModeArr$values = $values();
        $VALUES = layoutModeArr$values;
        $ENTRIES = r.e(layoutModeArr$values);
    }

    private LayoutMode(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static LayoutMode valueOf(String str) {
        return (LayoutMode) Enum.valueOf(LayoutMode.class, str);
    }

    public static LayoutMode[] values() {
        return (LayoutMode[]) $VALUES.clone();
    }
}
