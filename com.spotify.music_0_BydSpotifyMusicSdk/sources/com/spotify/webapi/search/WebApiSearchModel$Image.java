package com.spotify.webapi.search;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class WebApiSearchModel$Image {
    public final String a;
    public final Integer b;
    public final Integer c;

    public WebApiSearchModel$Image(@z61(name = "url") String str, @z61(name = "height") Integer num, @z61(name = "width") Integer num2) {
        v03.j(str, "url");
        this.a = str;
        this.b = num;
        this.c = num2;
    }

    public final WebApiSearchModel$Image copy(@z61(name = "url") String str, @z61(name = "height") Integer num, @z61(name = "width") Integer num2) {
        v03.j(str, "url");
        return new WebApiSearchModel$Image(str, num, num2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebApiSearchModel$Image)) {
            return false;
        }
        WebApiSearchModel$Image webApiSearchModel$Image = (WebApiSearchModel$Image) obj;
        return v03.b(this.a, webApiSearchModel$Image.a) && v03.b(this.b, webApiSearchModel$Image.b) && v03.b(this.c, webApiSearchModel$Image.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        Integer num = this.b;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.c;
        return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public final String toString() {
        return "Image(url=" + this.a + ", height=" + this.b + ", width=" + this.c + ')';
    }

    public /* synthetic */ WebApiSearchModel$Image(String str, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? 0 : num, (i & 4) != 0 ? 0 : num2);
    }
}
