package com.stingray.shared.logs.segment.player;

import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/stingray/shared/logs/segment/player/CastPlatform;", "", "<init>", "(Ljava/lang/String;I)V", "CHROMECAST", "DLNA", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CastPlatform {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ CastPlatform[] $VALUES;
    public static final CastPlatform CHROMECAST = new CastPlatform("CHROMECAST", 0);
    public static final CastPlatform DLNA = new CastPlatform("DLNA", 1);

    private static final /* synthetic */ CastPlatform[] $values() {
        return new CastPlatform[]{CHROMECAST, DLNA};
    }

    static {
        CastPlatform[] castPlatformArr$values = $values();
        $VALUES = castPlatformArr$values;
        $ENTRIES = r.e(castPlatformArr$values);
    }

    private CastPlatform(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static CastPlatform valueOf(String str) {
        return (CastPlatform) Enum.valueOf(CastPlatform.class, str);
    }

    public static CastPlatform[] values() {
        return (CastPlatform[]) $VALUES.clone();
    }
}
