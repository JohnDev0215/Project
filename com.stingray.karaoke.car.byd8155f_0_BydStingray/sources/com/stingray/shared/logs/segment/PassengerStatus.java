package com.stingray.shared.logs.segment;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/shared/logs/segment/PassengerStatus;", "", "<init>", "(Ljava/lang/String;I)V", "DETECTED", "UNDETECTED", "NOT_AVAILABLE", "UNKNOWN", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PassengerStatus {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ PassengerStatus[] $VALUES;
    public static final PassengerStatus DETECTED = new PassengerStatus("DETECTED", 0);
    public static final PassengerStatus UNDETECTED = new PassengerStatus("UNDETECTED", 1);
    public static final PassengerStatus NOT_AVAILABLE = new PassengerStatus("NOT_AVAILABLE", 2);
    public static final PassengerStatus UNKNOWN = new PassengerStatus("UNKNOWN", 3);

    private static final /* synthetic */ PassengerStatus[] $values() {
        return new PassengerStatus[]{DETECTED, UNDETECTED, NOT_AVAILABLE, UNKNOWN};
    }

    static {
        PassengerStatus[] passengerStatusArr$values = $values();
        $VALUES = passengerStatusArr$values;
        $ENTRIES = r.e(passengerStatusArr$values);
    }

    private PassengerStatus(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static PassengerStatus valueOf(String str) {
        return (PassengerStatus) Enum.valueOf(PassengerStatus.class, str);
    }

    public static PassengerStatus[] values() {
        return (PassengerStatus[]) $VALUES.clone();
    }
}
