package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001BÍ\u0001\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\n\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0010\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJÖ\u0001\u0010\u001b\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00102\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lcom/stingray/kca/model/KaraokeSongResponseV2;", "", "", "id", "title", "artistName", "artistId", "Lcom/stingray/kca/model/Filter;", "genre", "era", "", "singerCount", "durationSec", "releaseYear", "", "explicit", "", "languages", "cover", "available", "hasPitchInfo", "Lcom/stingray/kca/model/MusicalKey;", "musicalKey", "", "tempo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/Filter;Lcom/stingray/kca/model/Filter;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/stingray/kca/model/MusicalKey;Ljava/lang/Float;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/Filter;Lcom/stingray/kca/model/Filter;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/stingray/kca/model/MusicalKey;Ljava/lang/Float;)Lcom/stingray/kca/model/KaraokeSongResponseV2;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class KaraokeSongResponseV2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13137b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Filter f13138e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Filter f13139f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Integer f13140g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Integer f13141h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Integer f13142i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Boolean f13143j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List f13144k;
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Boolean f13145m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Boolean f13146n;
    public final MusicalKey o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Float f13147p;

    public KaraokeSongResponseV2() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 65535, null);
    }

    public final KaraokeSongResponseV2 copy(@o(name = "id") String id, @o(name = "title") String title, @o(name = "artistName") String artistName, @o(name = "artistId") String artistId, @o(name = "genre") Filter genre, @o(name = "era") Filter era, @o(name = "singerCount") Integer singerCount, @o(name = "durationSec") Integer durationSec, @o(name = "releaseYear") Integer releaseYear, @o(name = "explicit") Boolean explicit, @o(name = "languages") List<Filter> languages, @o(name = "cover") String cover, @o(name = "available") Boolean available, @o(name = "hasPitchInfo") Boolean hasPitchInfo, @o(name = "musicalKey") MusicalKey musicalKey, @o(name = "tempo") Float tempo) {
        return new KaraokeSongResponseV2(id, title, artistName, artistId, genre, era, singerCount, durationSec, releaseYear, explicit, languages, cover, available, hasPitchInfo, musicalKey, tempo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KaraokeSongResponseV2)) {
            return false;
        }
        KaraokeSongResponseV2 karaokeSongResponseV2 = (KaraokeSongResponseV2) obj;
        return m.a(this.f13136a, karaokeSongResponseV2.f13136a) && m.a(this.f13137b, karaokeSongResponseV2.f13137b) && m.a(this.c, karaokeSongResponseV2.c) && m.a(this.d, karaokeSongResponseV2.d) && m.a(this.f13138e, karaokeSongResponseV2.f13138e) && m.a(this.f13139f, karaokeSongResponseV2.f13139f) && m.a(this.f13140g, karaokeSongResponseV2.f13140g) && m.a(this.f13141h, karaokeSongResponseV2.f13141h) && m.a(this.f13142i, karaokeSongResponseV2.f13142i) && m.a(this.f13143j, karaokeSongResponseV2.f13143j) && m.a(this.f13144k, karaokeSongResponseV2.f13144k) && m.a(this.l, karaokeSongResponseV2.l) && m.a(this.f13145m, karaokeSongResponseV2.f13145m) && m.a(this.f13146n, karaokeSongResponseV2.f13146n) && m.a(this.o, karaokeSongResponseV2.o) && m.a(this.f13147p, karaokeSongResponseV2.f13147p);
    }

    public final int hashCode() {
        String str = this.f13136a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13137b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.d;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Filter filter = this.f13138e;
        int iHashCode5 = (iHashCode4 + (filter == null ? 0 : filter.hashCode())) * 31;
        Filter filter2 = this.f13139f;
        int iHashCode6 = (iHashCode5 + (filter2 == null ? 0 : filter2.hashCode())) * 31;
        Integer num = this.f13140g;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.f13141h;
        int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.f13142i;
        int iHashCode9 = (iHashCode8 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Boolean bool = this.f13143j;
        int iHashCode10 = (iHashCode9 + (bool == null ? 0 : bool.hashCode())) * 31;
        List list = this.f13144k;
        int iHashCode11 = (iHashCode10 + (list == null ? 0 : list.hashCode())) * 31;
        String str5 = this.l;
        int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool2 = this.f13145m;
        int iHashCode13 = (iHashCode12 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.f13146n;
        int iHashCode14 = (iHashCode13 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        MusicalKey musicalKey = this.o;
        int iHashCode15 = (iHashCode14 + (musicalKey == null ? 0 : musicalKey.hashCode())) * 31;
        Float f10 = this.f13147p;
        return iHashCode15 + (f10 != null ? f10.hashCode() : 0);
    }

    public final String toString() {
        return "KaraokeSongResponseV2(id=" + this.f13136a + ", title=" + this.f13137b + ", artistName=" + this.c + ", artistId=" + this.d + ", genre=" + this.f13138e + ", era=" + this.f13139f + ", singerCount=" + this.f13140g + ", durationSec=" + this.f13141h + ", releaseYear=" + this.f13142i + ", explicit=" + this.f13143j + ", languages=" + this.f13144k + ", cover=" + this.l + ", available=" + this.f13145m + ", hasPitchInfo=" + this.f13146n + ", musicalKey=" + this.o + ", tempo=" + this.f13147p + ")";
    }

    public KaraokeSongResponseV2(@o(name = "id") String str, @o(name = "title") String str2, @o(name = "artistName") String str3, @o(name = "artistId") String str4, @o(name = "genre") Filter filter, @o(name = "era") Filter filter2, @o(name = "singerCount") Integer num, @o(name = "durationSec") Integer num2, @o(name = "releaseYear") Integer num3, @o(name = "explicit") Boolean bool, @o(name = "languages") List<Filter> list, @o(name = "cover") String str5, @o(name = "available") Boolean bool2, @o(name = "hasPitchInfo") Boolean bool3, @o(name = "musicalKey") MusicalKey musicalKey, @o(name = "tempo") Float f10) {
        this.f13136a = str;
        this.f13137b = str2;
        this.c = str3;
        this.d = str4;
        this.f13138e = filter;
        this.f13139f = filter2;
        this.f13140g = num;
        this.f13141h = num2;
        this.f13142i = num3;
        this.f13143j = bool;
        this.f13144k = list;
        this.l = str5;
        this.f13145m = bool2;
        this.f13146n = bool3;
        this.o = musicalKey;
        this.f13147p = f10;
    }

    public /* synthetic */ KaraokeSongResponseV2(String str, String str2, String str3, String str4, Filter filter, Filter filter2, Integer num, Integer num2, Integer num3, Boolean bool, List list, String str5, Boolean bool2, Boolean bool3, MusicalKey musicalKey, Float f10, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : str4, (i7 & 16) != 0 ? null : filter, (i7 & 32) != 0 ? null : filter2, (i7 & 64) != 0 ? null : num, (i7 & 128) != 0 ? null : num2, (i7 & 256) != 0 ? null : num3, (i7 & 512) != 0 ? null : bool, (i7 & 1024) != 0 ? null : list, (i7 & 2048) != 0 ? null : str5, (i7 & 4096) != 0 ? null : bool2, (i7 & 8192) != 0 ? null : bool3, (i7 & 16384) != 0 ? null : musicalKey, (i7 & 32768) != 0 ? null : f10);
    }
}
