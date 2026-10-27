package com.stingray.karaoke.car.utils;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/stingray/karaoke/car/utils/ToastType;", "", "<init>", "(Ljava/lang/String;I)V", "DEFAULT", "ADD_TO_QUEUE", "ADD_TO_FAVORITE", "REMOVE_FROM_FAVORITE", "DRIVING_DISABLED_ERROR", "DRIVING_DISABLED_WARNING", "DRIVING_DISABLED_WITHOUT_PASSENGER", "SCORING_ON_HOLD", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ToastType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ ToastType[] $VALUES;
    public static final ToastType DEFAULT = new ToastType("DEFAULT", 0);
    public static final ToastType ADD_TO_QUEUE = new ToastType("ADD_TO_QUEUE", 1);
    public static final ToastType ADD_TO_FAVORITE = new ToastType("ADD_TO_FAVORITE", 2);
    public static final ToastType REMOVE_FROM_FAVORITE = new ToastType("REMOVE_FROM_FAVORITE", 3);
    public static final ToastType DRIVING_DISABLED_ERROR = new ToastType("DRIVING_DISABLED_ERROR", 4);
    public static final ToastType DRIVING_DISABLED_WARNING = new ToastType("DRIVING_DISABLED_WARNING", 5);
    public static final ToastType DRIVING_DISABLED_WITHOUT_PASSENGER = new ToastType("DRIVING_DISABLED_WITHOUT_PASSENGER", 6);
    public static final ToastType SCORING_ON_HOLD = new ToastType("SCORING_ON_HOLD", 7);

    private static final /* synthetic */ ToastType[] $values() {
        return new ToastType[]{DEFAULT, ADD_TO_QUEUE, ADD_TO_FAVORITE, REMOVE_FROM_FAVORITE, DRIVING_DISABLED_ERROR, DRIVING_DISABLED_WARNING, DRIVING_DISABLED_WITHOUT_PASSENGER, SCORING_ON_HOLD};
    }

    static {
        ToastType[] toastTypeArr$values = $values();
        $VALUES = toastTypeArr$values;
        $ENTRIES = r.e(toastTypeArr$values);
    }

    private ToastType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static ToastType valueOf(String str) {
        return (ToastType) Enum.valueOf(ToastType.class, str);
    }

    public static ToastType[] values() {
        return (ToastType[]) $VALUES.clone();
    }
}
