package com.stingray.shared.logs.segment.songQueue;

import F6.c;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \u00042\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/shared/logs/segment/songQueue/SourceType;", "", "<init>", "(Ljava/lang/String;I)V", "Companion", "F6/c", "SWIMLANE", "PAGE", "PLAYER", "DEEP_LINK", "PROMO_BANNER", "shared_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SourceType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ SourceType[] $VALUES;
    public static final c Companion;
    public static final SourceType SWIMLANE = new SourceType("SWIMLANE", 0);
    public static final SourceType PAGE = new SourceType("PAGE", 1);
    public static final SourceType PLAYER = new SourceType("PLAYER", 2);
    public static final SourceType DEEP_LINK = new SourceType("DEEP_LINK", 3);
    public static final SourceType PROMO_BANNER = new SourceType("PROMO_BANNER", 4);

    private static final /* synthetic */ SourceType[] $values() {
        return new SourceType[]{SWIMLANE, PAGE, PLAYER, DEEP_LINK, PROMO_BANNER};
    }

    static {
        SourceType[] sourceTypeArr$values = $values();
        $VALUES = sourceTypeArr$values;
        $ENTRIES = r.e(sourceTypeArr$values);
        Companion = new c();
    }

    private SourceType(String str, int i7) {
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static SourceType valueOf(String str) {
        return (SourceType) Enum.valueOf(SourceType.class, str);
    }

    public static SourceType[] values() {
        return (SourceType[]) $VALUES.clone();
    }
}
