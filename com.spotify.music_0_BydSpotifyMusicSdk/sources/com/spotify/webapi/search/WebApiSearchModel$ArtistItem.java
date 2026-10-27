package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$ArtistItem extends WebApiSearchModel$Artist {
    public final String c;
    public final String d;
    public final List e;

    public /* synthetic */ WebApiSearchModel$ArtistItem(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : list);
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Artist
    public final String a() {
        return this.d;
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Artist
    public final String b() {
        return this.c;
    }

    public final WebApiSearchModel$ArtistItem copy(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new WebApiSearchModel$ArtistItem(str, str2, list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$ArtistItem)) {
            return false;
        }
        WebApiSearchModel$ArtistItem webApiSearchModel$ArtistItem = (WebApiSearchModel$ArtistItem) obj;
        return v03.b(this.c, webApiSearchModel$ArtistItem.c) && v03.b(this.d, webApiSearchModel$ArtistItem.d) && v03.b(this.e, webApiSearchModel$ArtistItem.e);
    }

    public final int hashCode() {
        int iB = hf2.b(this.d, this.c.hashCode() * 31, 31);
        List list = this.e;
        return iB + (list == null ? 0 : list.hashCode());
    }

    @Override // com.spotify.webapi.search.WebApiSearchModel$Artist
    public final String toString() {
        return this.d;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebApiSearchModel$ArtistItem(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        super(str, str2);
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.c = str;
        this.d = str2;
        this.e = list;
    }
}
