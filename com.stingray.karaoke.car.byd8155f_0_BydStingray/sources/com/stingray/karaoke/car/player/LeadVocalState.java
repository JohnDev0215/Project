package com.stingray.karaoke.car.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/stingray/karaoke/car/player/LeadVocalState;", "", "<init>", "(Ljava/lang/String;I)V", "LeadVocalOn", "LeadVocalOff", "DisabledLeadVocalOn", "DisabledLeadVocalOff", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LeadVocalState {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ LeadVocalState[] $VALUES;
    public static final LeadVocalState LeadVocalOn = new LeadVocalState("LeadVocalOn", 0);
    public static final LeadVocalState LeadVocalOff = new LeadVocalState("LeadVocalOff", 1);
    public static final LeadVocalState DisabledLeadVocalOn = new LeadVocalState("DisabledLeadVocalOn", 2);
    public static final LeadVocalState DisabledLeadVocalOff = new LeadVocalState("DisabledLeadVocalOff", 3);

    private static final /* synthetic */ LeadVocalState[] $values() {
        return new LeadVocalState[]{LeadVocalOn, LeadVocalOff, DisabledLeadVocalOn, DisabledLeadVocalOff};
    }

    static {
        LeadVocalState[] leadVocalStateArr$values = $values();
        $VALUES = leadVocalStateArr$values;
        $ENTRIES = r.e(leadVocalStateArr$values);
    }

    private LeadVocalState(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static LeadVocalState valueOf(String str) {
        return (LeadVocalState) Enum.valueOf(LeadVocalState.class, str);
    }

    public static LeadVocalState[] values() {
        return (LeadVocalState[]) $VALUES.clone();
    }
}
