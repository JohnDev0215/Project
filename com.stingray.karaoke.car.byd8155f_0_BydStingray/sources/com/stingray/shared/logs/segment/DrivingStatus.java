package com.stingray.shared.logs.segment;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/shared/logs/segment/DrivingStatus;", "", "<init>", "(Ljava/lang/String;I)V", "PARKED", "DRIVING", "NOT_APPLICABLE", "UNKNOWN", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DrivingStatus {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ DrivingStatus[] $VALUES;
    public static final DrivingStatus PARKED = new DrivingStatus("PARKED", 0);
    public static final DrivingStatus DRIVING = new DrivingStatus("DRIVING", 1);
    public static final DrivingStatus NOT_APPLICABLE = new DrivingStatus("NOT_APPLICABLE", 2);
    public static final DrivingStatus UNKNOWN = new DrivingStatus("UNKNOWN", 3);

    private static final /* synthetic */ DrivingStatus[] $values() {
        return new DrivingStatus[]{PARKED, DRIVING, NOT_APPLICABLE, UNKNOWN};
    }

    static {
        DrivingStatus[] drivingStatusArr$values = $values();
        $VALUES = drivingStatusArr$values;
        $ENTRIES = r.e(drivingStatusArr$values);
    }

    private DrivingStatus(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static DrivingStatus valueOf(String str) {
        return (DrivingStatus) Enum.valueOf(DrivingStatus.class, str);
    }

    public static DrivingStatus[] values() {
        return (DrivingStatus[]) $VALUES.clone();
    }
}
