package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$PlaylistItem {
    public final String a;
    public final String b;
    public final List c;

    public WebApiSearchModel$PlaylistItem(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.a = str;
        this.b = str2;
        this.c = list;
    }

    public final WebApiSearchModel$PlaylistItem copy(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new WebApiSearchModel$PlaylistItem(str, str2, list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$PlaylistItem)) {
            return false;
        }
        WebApiSearchModel$PlaylistItem webApiSearchModel$PlaylistItem = (WebApiSearchModel$PlaylistItem) obj;
        return v03.b(this.a, webApiSearchModel$PlaylistItem.a) && v03.b(this.b, webApiSearchModel$PlaylistItem.b) && v03.b(this.c, webApiSearchModel$PlaylistItem.c);
    }

    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        List list = this.c;
        return iB + (list == null ? 0 : list.hashCode());
    }

    public final String toString() {
        return this.b;
    }

    public /* synthetic */ WebApiSearchModel$PlaylistItem(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : list);
    }
}
