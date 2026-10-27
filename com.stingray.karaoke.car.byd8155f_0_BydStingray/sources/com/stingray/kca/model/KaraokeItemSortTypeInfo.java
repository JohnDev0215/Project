package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import android.hardware.bydauto.ac.tsimpl.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/KaraokeItemSortTypeInfo;", "", "Lcom/stingray/kca/model/KaraokeItemSortType;", "type", "", "name", "pageItemId", "<init>", "(Lcom/stingray/kca/model/KaraokeItemSortType;Ljava/lang/String;Ljava/lang/String;)V", "copy", "(Lcom/stingray/kca/model/KaraokeItemSortType;Ljava/lang/String;Ljava/lang/String;)Lcom/stingray/kca/model/KaraokeItemSortTypeInfo;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class KaraokeItemSortTypeInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KaraokeItemSortType f13132a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13133b;
    public final String c;

    public KaraokeItemSortTypeInfo() {
        this(null, null, null, 7, null);
    }

    public final KaraokeItemSortTypeInfo copy(@o(name = "type") KaraokeItemSortType type, @o(name = "name") String name, @o(name = "pageItemId") String pageItemId) {
        return new KaraokeItemSortTypeInfo(type, name, pageItemId);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KaraokeItemSortTypeInfo)) {
            return false;
        }
        KaraokeItemSortTypeInfo karaokeItemSortTypeInfo = (KaraokeItemSortTypeInfo) obj;
        return this.f13132a == karaokeItemSortTypeInfo.f13132a && m.a(this.f13133b, karaokeItemSortTypeInfo.f13133b) && m.a(this.c, karaokeItemSortTypeInfo.c);
    }

    public final int hashCode() {
        KaraokeItemSortType karaokeItemSortType = this.f13132a;
        int iHashCode = (karaokeItemSortType == null ? 0 : karaokeItemSortType.hashCode()) * 31;
        String str = this.f13133b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("KaraokeItemSortTypeInfo(type=");
        sb.append(this.f13132a);
        sb.append(", name=");
        sb.append(this.f13133b);
        sb.append(", pageItemId=");
        return a.l(sb, this.c, ")");
    }

    public KaraokeItemSortTypeInfo(@o(name = "type") KaraokeItemSortType karaokeItemSortType, @o(name = "name") String str, @o(name = "pageItemId") String str2) {
        this.f13132a = karaokeItemSortType;
        this.f13133b = str;
        this.c = str2;
    }

    public /* synthetic */ KaraokeItemSortTypeInfo(KaraokeItemSortType karaokeItemSortType, String str, String str2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : karaokeItemSortType, (i7 & 2) != 0 ? null : str, (i7 & 4) != 0 ? null : str2);
    }
}
