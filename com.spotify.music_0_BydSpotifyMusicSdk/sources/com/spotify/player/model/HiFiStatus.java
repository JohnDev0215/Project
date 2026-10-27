package com.spotify.player.model;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class HiFiStatus {
    public static final HiFiStatus c;
    public static final HiFiStatus e;
    public static final HiFiStatus k;
    public static final /* synthetic */ HiFiStatus[] l;

    static {
        HiFiStatus hiFiStatus = new HiFiStatus("NONE", 0);
        c = hiFiStatus;
        HiFiStatus hiFiStatus2 = new HiFiStatus("OFF", 1);
        e = hiFiStatus2;
        HiFiStatus hiFiStatus3 = new HiFiStatus("ON", 2);
        k = hiFiStatus3;
        l = new HiFiStatus[]{hiFiStatus, hiFiStatus2, hiFiStatus3};
    }

    public static HiFiStatus valueOf(String str) {
        return (HiFiStatus) Enum.valueOf(HiFiStatus.class, str);
    }

    public static HiFiStatus[] values() {
        return (HiFiStatus[]) l.clone();
    }
}
