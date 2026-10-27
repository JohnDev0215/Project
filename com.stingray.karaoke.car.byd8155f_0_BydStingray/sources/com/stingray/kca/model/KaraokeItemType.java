package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0011"}, d2 = {"Lcom/stingray/kca/model/KaraokeItemType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TAB", "GROUP", "PLAY", "HEROBANNER", "DEEPLINK", "WEBLINK", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeItemType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ KaraokeItemType[] $VALUES;
    private final String value;

    @o(name = "TAB")
    public static final KaraokeItemType TAB = new KaraokeItemType("TAB", 0, "TAB");

    @o(name = "GROUP")
    public static final KaraokeItemType GROUP = new KaraokeItemType("GROUP", 1, "GROUP");

    @o(name = "PLAY")
    public static final KaraokeItemType PLAY = new KaraokeItemType("PLAY", 2, "PLAY");

    @o(name = "HERO_BANNER")
    public static final KaraokeItemType HEROBANNER = new KaraokeItemType("HEROBANNER", 3, "HERO_BANNER");

    @o(name = "DEEP_LINK")
    public static final KaraokeItemType DEEPLINK = new KaraokeItemType("DEEPLINK", 4, "DEEP_LINK");

    @o(name = "WEB_LINK")
    public static final KaraokeItemType WEBLINK = new KaraokeItemType("WEBLINK", 5, "WEB_LINK");

    @o(name = "unknown_default_open_api")
    public static final KaraokeItemType UnknownDefaultOpenApi = new KaraokeItemType("UnknownDefaultOpenApi", 6, "unknown_default_open_api");

    private static final /* synthetic */ KaraokeItemType[] $values() {
        return new KaraokeItemType[]{TAB, GROUP, PLAY, HEROBANNER, DEEPLINK, WEBLINK, UnknownDefaultOpenApi};
    }

    static {
        KaraokeItemType[] karaokeItemTypeArr$values = $values();
        $VALUES = karaokeItemTypeArr$values;
        $ENTRIES = r.e(karaokeItemTypeArr$values);
        INSTANCE = new Companion(0);
    }

    private KaraokeItemType(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static KaraokeItemType valueOf(String str) {
        return (KaraokeItemType) Enum.valueOf(KaraokeItemType.class, str);
    }

    public static KaraokeItemType[] values() {
        return (KaraokeItemType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
