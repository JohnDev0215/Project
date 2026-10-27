package com.stingray.companion.model;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/companion/model/HostStatus;", "", "<init>", "(Ljava/lang/String;I)V", "CONNECTED", "PAUSED", "DISCONNECTED", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class HostStatus {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ HostStatus[] $VALUES;
    public static final HostStatus CONNECTED = new HostStatus("CONNECTED", 0);
    public static final HostStatus PAUSED = new HostStatus("PAUSED", 1);
    public static final HostStatus DISCONNECTED = new HostStatus("DISCONNECTED", 2);

    private static final /* synthetic */ HostStatus[] $values() {
        return new HostStatus[]{CONNECTED, PAUSED, DISCONNECTED};
    }

    static {
        HostStatus[] hostStatusArr$values = $values();
        $VALUES = hostStatusArr$values;
        $ENTRIES = r.e(hostStatusArr$values);
    }

    private HostStatus(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static HostStatus valueOf(String str) {
        return (HostStatus) Enum.valueOf(HostStatus.class, str);
    }

    public static HostStatus[] values() {
        return (HostStatus[]) $VALUES.clone();
    }
}
