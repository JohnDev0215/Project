package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0002\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0003\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u000b\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\r\u0012\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\r\u0012\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\r\u0012\u0016\b\u0003\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001a\u0012\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u0098\u0002\u0010\"\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\t2\u0010\b\u0003\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u000b2\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\r2\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000b2\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\r2\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0016\b\u0003\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001a2\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÆ\u0001¢\u0006\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/stingray/kca/model/KaraokeItem;", "", "", "id", "name", "image", "subtitle", "Lcom/stingray/kca/model/KaraokeItemType;", "type", "Lcom/stingray/kca/model/KaraokeItemChildType;", "childType", "", "childItems", "", "showChildren", "showPlayAll", "Lcom/stingray/kca/model/KaraokeItemSortTypeInfo;", "sortingTypes", "", "visibleChildItemsCount", "alwaysLoadChildren", "isExplicit", "isAvailable", "hasPitchInfo", "alwaysHasMore", "fillWithEmpty", "", "logDatabag", "Lcom/stingray/kca/model/Images;", "images", "Lcom/stingray/kca/model/ScoringDetail;", "scoringDetail", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/KaraokeItemType;Lcom/stingray/kca/model/KaraokeItemChildType;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/Map;Lcom/stingray/kca/model/Images;Lcom/stingray/kca/model/ScoringDetail;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/stingray/kca/model/KaraokeItemType;Lcom/stingray/kca/model/KaraokeItemChildType;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/util/Map;Lcom/stingray/kca/model/Images;Lcom/stingray/kca/model/ScoringDetail;)Lcom/stingray/kca/model/KaraokeItem;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class KaraokeItem {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13108b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final KaraokeItemType f13109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final KaraokeItemChildType f13110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List f13111g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Boolean f13112h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Boolean f13113i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f13114j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Integer f13115k;
    public final Boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Boolean f13116m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Boolean f13117n;
    public final Boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Boolean f13118p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Boolean f13119q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Map f13120r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Images f13121s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ScoringDetail f13122t;

    public KaraokeItem() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1048575, null);
    }

    public final KaraokeItem copy(@o(name = "id") String id, @o(name = "name") String name, @o(name = "image") String image, @o(name = "subtitle") String subtitle, @o(name = "type") KaraokeItemType type, @o(name = "childType") KaraokeItemChildType childType, @o(name = "childItems") List<KaraokeItem> childItems, @o(name = "showChildren") Boolean showChildren, @o(name = "showPlayAll") Boolean showPlayAll, @o(name = "sortingTypes") List<KaraokeItemSortTypeInfo> sortingTypes, @o(name = "visibleChildItemsCount") Integer visibleChildItemsCount, @o(name = "alwaysLoadChildren") Boolean alwaysLoadChildren, @o(name = "isExplicit") Boolean isExplicit, @o(name = "isAvailable") Boolean isAvailable, @o(name = "hasPitchInfo") Boolean hasPitchInfo, @o(name = "alwaysHasMore") Boolean alwaysHasMore, @o(name = "fillWithEmpty") Boolean fillWithEmpty, @o(name = "logDatabag") Map<String, String> logDatabag, @o(name = "images") Images images, @o(name = "scoringDetail") ScoringDetail scoringDetail) {
        return new KaraokeItem(id, name, image, subtitle, type, childType, childItems, showChildren, showPlayAll, sortingTypes, visibleChildItemsCount, alwaysLoadChildren, isExplicit, isAvailable, hasPitchInfo, alwaysHasMore, fillWithEmpty, logDatabag, images, scoringDetail);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KaraokeItem)) {
            return false;
        }
        KaraokeItem karaokeItem = (KaraokeItem) obj;
        return m.a(this.f13107a, karaokeItem.f13107a) && m.a(this.f13108b, karaokeItem.f13108b) && m.a(this.c, karaokeItem.c) && m.a(this.d, karaokeItem.d) && this.f13109e == karaokeItem.f13109e && this.f13110f == karaokeItem.f13110f && m.a(this.f13111g, karaokeItem.f13111g) && m.a(this.f13112h, karaokeItem.f13112h) && m.a(this.f13113i, karaokeItem.f13113i) && m.a(this.f13114j, karaokeItem.f13114j) && m.a(this.f13115k, karaokeItem.f13115k) && m.a(this.l, karaokeItem.l) && m.a(this.f13116m, karaokeItem.f13116m) && m.a(this.f13117n, karaokeItem.f13117n) && m.a(this.o, karaokeItem.o) && m.a(this.f13118p, karaokeItem.f13118p) && m.a(this.f13119q, karaokeItem.f13119q) && m.a(this.f13120r, karaokeItem.f13120r) && m.a(this.f13121s, karaokeItem.f13121s) && m.a(this.f13122t, karaokeItem.f13122t);
    }

    public final int hashCode() {
        String str = this.f13107a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13108b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.d;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        KaraokeItemType karaokeItemType = this.f13109e;
        int iHashCode5 = (iHashCode4 + (karaokeItemType == null ? 0 : karaokeItemType.hashCode())) * 31;
        KaraokeItemChildType karaokeItemChildType = this.f13110f;
        int iHashCode6 = (iHashCode5 + (karaokeItemChildType == null ? 0 : karaokeItemChildType.hashCode())) * 31;
        List list = this.f13111g;
        int iHashCode7 = (iHashCode6 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.f13112h;
        int iHashCode8 = (iHashCode7 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.f13113i;
        int iHashCode9 = (iHashCode8 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        List list2 = this.f13114j;
        int iHashCode10 = (iHashCode9 + (list2 == null ? 0 : list2.hashCode())) * 31;
        Integer num = this.f13115k;
        int iHashCode11 = (iHashCode10 + (num == null ? 0 : num.hashCode())) * 31;
        Boolean bool3 = this.l;
        int iHashCode12 = (iHashCode11 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.f13116m;
        int iHashCode13 = (iHashCode12 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.f13117n;
        int iHashCode14 = (iHashCode13 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Boolean bool6 = this.o;
        int iHashCode15 = (iHashCode14 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        Boolean bool7 = this.f13118p;
        int iHashCode16 = (iHashCode15 + (bool7 == null ? 0 : bool7.hashCode())) * 31;
        Boolean bool8 = this.f13119q;
        int iHashCode17 = (iHashCode16 + (bool8 == null ? 0 : bool8.hashCode())) * 31;
        Map map = this.f13120r;
        int iHashCode18 = (iHashCode17 + (map == null ? 0 : map.hashCode())) * 31;
        Images images = this.f13121s;
        int iHashCode19 = (iHashCode18 + (images == null ? 0 : images.hashCode())) * 31;
        ScoringDetail scoringDetail = this.f13122t;
        return iHashCode19 + (scoringDetail != null ? scoringDetail.hashCode() : 0);
    }

    public final String toString() {
        return "KaraokeItem(id=" + this.f13107a + ", name=" + this.f13108b + ", image=" + this.c + ", subtitle=" + this.d + ", type=" + this.f13109e + ", childType=" + this.f13110f + ", childItems=" + this.f13111g + ", showChildren=" + this.f13112h + ", showPlayAll=" + this.f13113i + ", sortingTypes=" + this.f13114j + ", visibleChildItemsCount=" + this.f13115k + ", alwaysLoadChildren=" + this.l + ", isExplicit=" + this.f13116m + ", isAvailable=" + this.f13117n + ", hasPitchInfo=" + this.o + ", alwaysHasMore=" + this.f13118p + ", fillWithEmpty=" + this.f13119q + ", logDatabag=" + this.f13120r + ", images=" + this.f13121s + ", scoringDetail=" + this.f13122t + ")";
    }

    public KaraokeItem(@o(name = "id") String str, @o(name = "name") String str2, @o(name = "image") String str3, @o(name = "subtitle") String str4, @o(name = "type") KaraokeItemType karaokeItemType, @o(name = "childType") KaraokeItemChildType karaokeItemChildType, @o(name = "childItems") List<KaraokeItem> list, @o(name = "showChildren") Boolean bool, @o(name = "showPlayAll") Boolean bool2, @o(name = "sortingTypes") List<KaraokeItemSortTypeInfo> list2, @o(name = "visibleChildItemsCount") Integer num, @o(name = "alwaysLoadChildren") Boolean bool3, @o(name = "isExplicit") Boolean bool4, @o(name = "isAvailable") Boolean bool5, @o(name = "hasPitchInfo") Boolean bool6, @o(name = "alwaysHasMore") Boolean bool7, @o(name = "fillWithEmpty") Boolean bool8, @o(name = "logDatabag") Map<String, String> map, @o(name = "images") Images images, @o(name = "scoringDetail") ScoringDetail scoringDetail) {
        this.f13107a = str;
        this.f13108b = str2;
        this.c = str3;
        this.d = str4;
        this.f13109e = karaokeItemType;
        this.f13110f = karaokeItemChildType;
        this.f13111g = list;
        this.f13112h = bool;
        this.f13113i = bool2;
        this.f13114j = list2;
        this.f13115k = num;
        this.l = bool3;
        this.f13116m = bool4;
        this.f13117n = bool5;
        this.o = bool6;
        this.f13118p = bool7;
        this.f13119q = bool8;
        this.f13120r = map;
        this.f13121s = images;
        this.f13122t = scoringDetail;
    }

    public /* synthetic */ KaraokeItem(String str, String str2, String str3, String str4, KaraokeItemType karaokeItemType, KaraokeItemChildType karaokeItemChildType, List list, Boolean bool, Boolean bool2, List list2, Integer num, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Map map, Images images, ScoringDetail scoringDetail, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : str4, (i7 & 16) != 0 ? null : karaokeItemType, (i7 & 32) != 0 ? null : karaokeItemChildType, (i7 & 64) != 0 ? null : list, (i7 & 128) != 0 ? null : bool, (i7 & 256) != 0 ? null : bool2, (i7 & 512) != 0 ? null : list2, (i7 & 1024) != 0 ? null : num, (i7 & 2048) != 0 ? null : bool3, (i7 & 4096) != 0 ? null : bool4, (i7 & 8192) != 0 ? null : bool5, (i7 & 16384) != 0 ? null : bool6, (i7 & 32768) != 0 ? null : bool7, (i7 & 65536) != 0 ? null : bool8, (i7 & 131072) != 0 ? null : map, (i7 & 262144) != 0 ? null : images, (i7 & 524288) != 0 ? null : scoringDetail);
    }
}
