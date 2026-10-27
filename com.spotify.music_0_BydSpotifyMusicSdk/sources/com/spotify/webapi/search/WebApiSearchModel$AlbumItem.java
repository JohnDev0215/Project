package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$AlbumItem extends WebApiSearchModel$Album {
    public final String d;
    public final String e;
    public final List f;
    public final List g;

    public /* synthetic */ WebApiSearchModel$AlbumItem(String str, String str2, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : list2);
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Album
    public final List a() {
        return this.g;
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Album
    public final String b() {
        return this.e;
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Album
    public final String c() {
        return this.d;
    }

    public final WebApiSearchModel$AlbumItem copy(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "artists") List<? extends WebApiSearchModel$Artist> list, @z61(name = "images") List<WebApiSearchModel$Image> list2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new WebApiSearchModel$AlbumItem(str, str2, list, list2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$AlbumItem)) {
            return false;
        }
        WebApiSearchModel$AlbumItem webApiSearchModel$AlbumItem = (WebApiSearchModel$AlbumItem) obj;
        return v03.b(this.d, webApiSearchModel$AlbumItem.d) && v03.b(this.e, webApiSearchModel$AlbumItem.e) && v03.b(this.f, webApiSearchModel$AlbumItem.f) && v03.b(this.g, webApiSearchModel$AlbumItem.g);
    }

    public final int hashCode() {
        int iB = hf2.b(this.e, this.d.hashCode() * 31, 31);
        List list = this.f;
        int iHashCode = (iB + (list == null ? 0 : list.hashCode())) * 31;
        List list2 = this.g;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Album
    public final String toString() {
        return this.e;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebApiSearchModel$AlbumItem(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "artists") List<? extends WebApiSearchModel$Artist> list, @z61(name = "images") List<WebApiSearchModel$Image> list2) {
        super(str, str2, list2);
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.d = str;
        this.e = str2;
        this.f = list;
        this.g = list2;
    }
}
