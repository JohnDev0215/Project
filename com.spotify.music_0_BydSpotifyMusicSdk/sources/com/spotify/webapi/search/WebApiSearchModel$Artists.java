package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$Artists {
    public final List a;

    public WebApiSearchModel$Artists(@z61(name = "items") List<WebApiSearchModel$ArtistItem> list) {
        this.a = list;
    }

    public final WebApiSearchModel$Artists copy(@z61(name = "items") List<WebApiSearchModel$ArtistItem> list) {
        return new WebApiSearchModel$Artists(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WebApiSearchModel$Artists) && v03.b(this.a, ((WebApiSearchModel$Artists) obj).a);
    }

    public final int hashCode() {
        List list = this.a;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public final String toString() {
        return hf2.e(new StringBuilder("Artists(items="), this.a, ')');
    }

    public /* synthetic */ WebApiSearchModel$Artists(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
