package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000f"}, d2 = {"Lcom/stingray/kca/model/KaraokeItemChildType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "NONE", "SONG", "ARTIST", "GROUP", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeItemChildType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ KaraokeItemChildType[] $VALUES;
    private final String value;

    @o(name = "NONE")
    public static final KaraokeItemChildType NONE = new KaraokeItemChildType("NONE", 0, "NONE");

    @o(name = "SONG")
    public static final KaraokeItemChildType SONG = new KaraokeItemChildType("SONG", 1, "SONG");

    @o(name = "ARTIST")
    public static final KaraokeItemChildType ARTIST = new KaraokeItemChildType("ARTIST", 2, "ARTIST");

    @o(name = "GROUP")
    public static final KaraokeItemChildType GROUP = new KaraokeItemChildType("GROUP", 3, "GROUP");

    @o(name = "unknown_default_open_api")
    public static final KaraokeItemChildType UnknownDefaultOpenApi = new KaraokeItemChildType("UnknownDefaultOpenApi", 4, "unknown_default_open_api");

    private static final /* synthetic */ KaraokeItemChildType[] $values() {
        return new KaraokeItemChildType[]{NONE, SONG, ARTIST, GROUP, UnknownDefaultOpenApi};
    }

    static {
        KaraokeItemChildType[] karaokeItemChildTypeArr$values = $values();
        $VALUES = karaokeItemChildTypeArr$values;
        $ENTRIES = r.e(karaokeItemChildTypeArr$values);
        INSTANCE = new Companion(0);
    }

    private KaraokeItemChildType(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static KaraokeItemChildType valueOf(String str) {
        return (KaraokeItemChildType) Enum.valueOf(KaraokeItemChildType.class, str);
    }

    public static KaraokeItemChildType[] values() {
        return (KaraokeItemChildType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
