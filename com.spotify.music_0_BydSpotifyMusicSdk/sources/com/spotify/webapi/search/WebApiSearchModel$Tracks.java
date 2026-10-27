package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$Tracks {
    public final List a;

    public WebApiSearchModel$Tracks(@z61(name = "items") List<WebApiSearchModel$TrackItem> list) {
        this.a = list;
    }

    public final WebApiSearchModel$Tracks copy(@z61(name = "items") List<WebApiSearchModel$TrackItem> list) {
        return new WebApiSearchModel$Tracks(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WebApiSearchModel$Tracks) && v03.b(this.a, ((WebApiSearchModel$Tracks) obj).a);
    }

    public final int hashCode() {
        List list = this.a;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public final String toString() {
        return hf2.e(new StringBuilder("Tracks(items="), this.a, ')');
    }

    public /* synthetic */ WebApiSearchModel$Tracks(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
