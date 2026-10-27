package com.stingray.kca.model;

import A7.m;
import E5.o;
import E5.r;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
@r(generateAdapter = true)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J4\u0010\b\u001a\u00020\u00002\u0010\b\u0003\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/stingray/kca/model/FavoriteIdsResponse;", "", "", "", "artistIds", "songIds", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "copy", "(Ljava/util/List;Ljava/util/List;)Lcom/stingray/kca/model/FavoriteIdsResponse;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class FavoriteIdsResponse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f13087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f13088b;

    /* JADX WARN: Multi-variable type inference failed */
    public FavoriteIdsResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public final FavoriteIdsResponse copy(@o(name = "artistIds") List<String> artistIds, @o(name = "songIds") List<String> songIds) {
        return new FavoriteIdsResponse(artistIds, songIds);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FavoriteIdsResponse)) {
            return false;
        }
        FavoriteIdsResponse favoriteIdsResponse = (FavoriteIdsResponse) obj;
        return m.a(this.f13087a, favoriteIdsResponse.f13087a) && m.a(this.f13088b, favoriteIdsResponse.f13088b);
    }

    public final int hashCode() {
        List list = this.f13087a;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List list2 = this.f13088b;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public final String toString() {
        return "FavoriteIdsResponse(artistIds=" + this.f13087a + ", songIds=" + this.f13088b + ")";
    }

    public FavoriteIdsResponse(@o(name = "artistIds") List<String> list, @o(name = "songIds") List<String> list2) {
        this.f13087a = list;
        this.f13088b = list2;
    }

    public /* synthetic */ FavoriteIdsResponse(List list, List list2, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? null : list, (i7 & 2) != 0 ? null : list2);
    }
}
