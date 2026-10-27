package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import M5.b;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\u0016\b\u0003\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJT\u0010\u000b\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\u0004\u001a\u00020\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0016\b\u0003\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/stingray/kca/model/ArFilterItem;", "", "", "id", "link", "displayTitle", "image", "", "additionalProperties", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Lcom/stingray/kca/model/ArFilterItem;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ArFilterItem {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13058a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13059b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map f13060e;

    public ArFilterItem(@o(name = "id") String str, @o(name = "link") String str2, @o(name = "displayTitle") String str3, @o(name = "image") String str4, @o(name = "additionalProperties") Map<String, Object> map) {
        m.f(str, "id");
        m.f(str2, "link");
        this.f13058a = str;
        this.f13059b = str2;
        this.c = str3;
        this.d = str4;
        this.f13060e = map;
    }

    public final ArFilterItem copy(@o(name = "id") String id, @o(name = "link") String link, @o(name = "displayTitle") String displayTitle, @o(name = "image") String image, @o(name = "additionalProperties") Map<String, Object> additionalProperties) {
        m.f(id, "id");
        m.f(link, "link");
        return new ArFilterItem(id, link, displayTitle, image, additionalProperties);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArFilterItem)) {
            return false;
        }
        ArFilterItem arFilterItem = (ArFilterItem) obj;
        return m.a(this.f13058a, arFilterItem.f13058a) && m.a(this.f13059b, arFilterItem.f13059b) && m.a(this.c, arFilterItem.c) && m.a(this.d, arFilterItem.d) && m.a(this.f13060e, arFilterItem.f13060e);
    }

    public final int hashCode() {
        int iC = b.c(this.f13059b, this.f13058a.hashCode() * 31, 31);
        String str = this.c;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map map = this.f13060e;
        return iHashCode2 + (map != null ? map.hashCode() : 0);
    }

    public final String toString() {
        return "ArFilterItem(id=" + this.f13058a + ", link=" + this.f13059b + ", displayTitle=" + this.c + ", image=" + this.d + ", additionalProperties=" + this.f13060e + ")";
    }

    public /* synthetic */ ArFilterItem(String str, String str2, String str3, String str4, Map map, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i7 & 4) != 0 ? null : str3, (i7 & 8) != 0 ? null : str4, (i7 & 16) != 0 ? null : map);
    }
}
