package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJN\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0003\u001a\u00020\u00022\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/stingray/kca/model/ArFilterGroup;", "", "", "id", "", "Lcom/stingray/kca/model/ArFilterItem;", "items", "defaultItemId", "displayTitle", "image", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/ArFilterGroup;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ArFilterGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13053b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f13054e;

    public ArFilterGroup(@o(name = "id") String str, @o(name = "items") List<ArFilterItem> list, @o(name = "defaultItemId") String str2, @o(name = "displayTitle") String str3, @o(name = "image") String str4) {
        m.f(str, "id");
        m.f(list, "items");
        this.f13052a = str;
        this.f13053b = list;
        this.c = str2;
        this.d = str3;
        this.f13054e = str4;
    }

    public final ArFilterGroup copy(@o(name = "id") String id, @o(name = "items") List<ArFilterItem> items, @o(name = "defaultItemId") String defaultItemId, @o(name = "displayTitle") String displayTitle, @o(name = "image") String image) {
        m.f(id, "id");
        m.f(items, "items");
        return new ArFilterGroup(id, items, defaultItemId, displayTitle, image);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArFilterGroup)) {
            return false;
        }
        ArFilterGroup arFilterGroup = (ArFilterGroup) obj;
        return m.a(this.f13052a, arFilterGroup.f13052a) && m.a(this.f13053b, arFilterGroup.f13053b) && m.a(this.c, arFilterGroup.c) && m.a(this.d, arFilterGroup.d) && m.a(this.f13054e, arFilterGroup.f13054e);
    }

    public final int hashCode() {
        int iHashCode = (this.f13053b.hashCode() + (this.f13052a.hashCode() * 31)) * 31;
        String str = this.c;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.f13054e;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ArFilterGroup(id=");
        sb.append(this.f13052a);
        sb.append(", items=");
        sb.append(this.f13053b);
        sb.append(", defaultItemId=");
        sb.append(this.c);
        sb.append(", displayTitle=");
        sb.append(this.d);
        sb.append(", image=");
        return a.l(sb, this.f13054e, ")");
    }

    public /* synthetic */ ArFilterGroup(String str, List list, String str2, String str3, String str4, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i7 & 4) != 0 ? null : str2, (i7 & 8) != 0 ? null : str3, (i7 & 16) != 0 ? null : str4);
    }
}
