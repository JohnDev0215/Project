package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$EpisodeItem {
    public final String a;
    public final String b;
    public final boolean c;
    public final List d;

    public WebApiSearchModel$EpisodeItem(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "explicit") boolean z, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = list;
    }

    public final WebApiSearchModel$EpisodeItem copy(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "explicit") boolean z, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        return new WebApiSearchModel$EpisodeItem(str, str2, z, list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$EpisodeItem)) {
            return false;
        }
        WebApiSearchModel$EpisodeItem webApiSearchModel$EpisodeItem = (WebApiSearchModel$EpisodeItem) obj;
        return v03.b(this.a, webApiSearchModel$EpisodeItem.a) && v03.b(this.b, webApiSearchModel$EpisodeItem.b) && this.c == webApiSearchModel$EpisodeItem.c && v03.b(this.d, webApiSearchModel$EpisodeItem.d);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    public final int hashCode() {
        int iB = hf2.b(this.b, this.a.hashCode() * 31, 31);
        boolean z = this.c;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iB + r2) * 31;
        List list = this.d;
        return i + (list == null ? 0 : list.hashCode());
    }

    public final String toString() {
        return this.b;
    }

    public /* synthetic */ WebApiSearchModel$EpisodeItem(String str, String str2, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : list);
    }
}
