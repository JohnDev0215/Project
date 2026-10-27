package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ4\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/stingray/kca/model/ArtistResponseV2;", "", "", "id", "name", "", "songCount", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/stingray/kca/model/ArtistResponseV2;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class ArtistResponseV2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f13064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13065b;
    public final Integer c;

    public ArtistResponseV2() {
        this(null, null, null, 7, null);
    }

    public final ArtistResponseV2 copy(@o(name = "id") String id, @o(name = "name") String name, @o(name = "songCount") Integer songCount) {
        return new ArtistResponseV2(id, name, songCount);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArtistResponseV2)) {
            return false;
        }
        ArtistResponseV2 artistResponseV2 = (ArtistResponseV2) obj;
        return m.a(this.f13064a, artistResponseV2.f13064a) && m.a(this.f13065b, artistResponseV2.f13065b) && m.a(this.c, artistResponseV2.c);
    }

    public final int hashCode() {
        String str = this.f13064a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f13065b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.c;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    public final String toString() {
        return "ArtistResponseV2(id=" + this.f13064a + ", name=" + this.f13065b + ", songCount=" + this.c + ")";
    }

    public ArtistResponseV2(@o(name = "id") String str, @o(name = "name") String str2, @o(name = "songCount") Integer num) {
        this.f13064a = str;
        this.f13065b = str2;
        this.c = num;
    }

    public /* synthetic */ ArtistResponseV2(String str, String str2, Integer num, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : str, (i7 & 2) != 0 ? null : str2, (i7 & 4) != 0 ? null : num);
    }
}
