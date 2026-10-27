package com.spotify.webapi.search;

import com.spotify.webapi.service.models.Search;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$TrackItem {
    public final String a;
    public final String b;
    public final boolean c;
    public final WebApiSearchModel$Album d;
    public final List e;
    public final List f;

    public WebApiSearchModel$TrackItem(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "explicit") boolean z, @z61(name = Search.Type.ALBUM) WebApiSearchModel$Album webApiSearchModel$Album, @z61(name = "artists") List<? extends WebApiSearchModel$Artist> list, @z61(name = "tags") List<String> list2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = webApiSearchModel$Album;
        this.e = list;
        this.f = list2;
    }

    public final WebApiSearchModel$TrackItem copy(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "explicit") boolean z, @z61(name = Search.Type.ALBUM) WebApiSearchModel$Album webApiSearchModel$Album, @z61(name = "artists") List<? extends WebApiSearchModel$Artist> list, @z61(name = "tags") List<String> list2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new WebApiSearchModel$TrackItem(str, str2, z, webApiSearchModel$Album, list, list2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$TrackItem)) {
            return false;
        }
        WebApiSearchModel$TrackItem webApiSearchModel$TrackItem = (WebApiSearchModel$TrackItem) obj;
        return v03.b(this.a, webApiSearchModel$TrackItem.a) && v03.b(this.b, webApiSearchModel$TrackItem.b) && this.c == webApiSearchModel$TrackItem.c && v03.b(this.d, webApiSearchModel$TrackItem.d) && v03.b(this.e, webApiSearchModel$TrackItem.e) && v03.b(this.f, webApiSearchModel$TrackItem.f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        boolean z = this.c;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iB + r2) * 31;
        WebApiSearchModel$Album webApiSearchModel$Album = this.d;
        int iHashCode = (i + (webApiSearchModel$Album == null ? 0 : webApiSearchModel$Album.hashCode())) * 31;
        List list = this.e;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List list2 = this.f;
        return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
    }

    public final String toString() {
        return this.b;
    }

    public /* synthetic */ WebApiSearchModel$TrackItem(String str, String str2, boolean z, WebApiSearchModel$Album webApiSearchModel$Album, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : webApiSearchModel$Album, (i & 16) != 0 ? null : list, (i & 32) != 0 ? null : list2);
    }
}
