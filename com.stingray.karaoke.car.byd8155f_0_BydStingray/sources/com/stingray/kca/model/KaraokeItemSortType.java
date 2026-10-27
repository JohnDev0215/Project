package com.stingray.kca.model;

import E5.o;
import h6.r;
import kotlin.Metadata;
import t7.InterfaceC2446a;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0012"}, d2 = {"Lcom/stingray/kca/model/KaraokeItemSortType;", "", "value", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "POPULARITY", "SONGAZ", "SONGZA", "ARTISTAZ", "ARTISTZA", "RELEASEYEAR", "DATEADDED", "UnknownDefaultOpenApi", "toString", "Companion", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KaraokeItemSortType {
    private static final /* synthetic */ InterfaceC2446a $ENTRIES;
    private static final /* synthetic */ KaraokeItemSortType[] $VALUES;
    private final String value;

    @o(name = "POPULARITY")
    public static final KaraokeItemSortType POPULARITY = new KaraokeItemSortType("POPULARITY", 0, "POPULARITY");

    @o(name = "SONG_A_Z")
    public static final KaraokeItemSortType SONGAZ = new KaraokeItemSortType("SONGAZ", 1, "SONG_A_Z");

    @o(name = "SONG_Z_A")
    public static final KaraokeItemSortType SONGZA = new KaraokeItemSortType("SONGZA", 2, "SONG_Z_A");

    @o(name = "ARTIST_A_Z")
    public static final KaraokeItemSortType ARTISTAZ = new KaraokeItemSortType("ARTISTAZ", 3, "ARTIST_A_Z");

    @o(name = "ARTIST_Z_A")
    public static final KaraokeItemSortType ARTISTZA = new KaraokeItemSortType("ARTISTZA", 4, "ARTIST_Z_A");

    @o(name = "RELEASE_YEAR")
    public static final KaraokeItemSortType RELEASEYEAR = new KaraokeItemSortType("RELEASEYEAR", 5, "RELEASE_YEAR");

    @o(name = "DATE_ADDED")
    public static final KaraokeItemSortType DATEADDED = new KaraokeItemSortType("DATEADDED", 6, "DATE_ADDED");

    @o(name = "unknown_default_open_api")
    public static final KaraokeItemSortType UnknownDefaultOpenApi = new KaraokeItemSortType("UnknownDefaultOpenApi", 7, "unknown_default_open_api");

    private static final /* synthetic */ KaraokeItemSortType[] $values() {
        return new KaraokeItemSortType[]{POPULARITY, SONGAZ, SONGZA, ARTISTAZ, ARTISTZA, RELEASEYEAR, DATEADDED, UnknownDefaultOpenApi};
    }

    static {
        KaraokeItemSortType[] karaokeItemSortTypeArr$values = $values();
        $VALUES = karaokeItemSortTypeArr$values;
        $ENTRIES = r.e(karaokeItemSortTypeArr$values);
        INSTANCE = new Companion(0);
    }

    private KaraokeItemSortType(String str, int i7, String str2) {
        this.value = str2;
    }

    public static InterfaceC2446a getEntries() {
        return $ENTRIES;
    }

    public static KaraokeItemSortType valueOf(String str) {
        return (KaraokeItemSortType) Enum.valueOf(KaraokeItemSortType.class, str);
    }

    public static KaraokeItemSortType[] values() {
        return (KaraokeItemSortType[]) $VALUES.clone();
    }

    public final String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
