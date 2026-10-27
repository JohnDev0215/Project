package com.stingray.drivemodedetector;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/stingray/drivemodedetector/DriveMode;", "", "<init>", "(Ljava/lang/String;I)V", "PARKED", "DRIVING", "drivemodedetector_byd8155fRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DriveMode {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ DriveMode[] $VALUES;
    public static final DriveMode PARKED = new DriveMode("PARKED", 0);
    public static final DriveMode DRIVING = new DriveMode("DRIVING", 1);

    private static final /* synthetic */ DriveMode[] $values() {
        return new DriveMode[]{PARKED, DRIVING};
    }

    static {
        DriveMode[] driveModeArr$values = $values();
        $VALUES = driveModeArr$values;
        $ENTRIES = r.e(driveModeArr$values);
    }

    private DriveMode(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static DriveMode valueOf(String str) {
        return (DriveMode) Enum.valueOf(DriveMode.class, str);
    }

    public static DriveMode[] values() {
        return (DriveMode[]) $VALUES.clone();
    }
}
