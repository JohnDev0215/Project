package com.spotify.webapi.search;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public class WebApiSearchModel$Album {
    public final String a;
    public final String b;
    public final List c;

    public WebApiSearchModel$Album(@z61(name = "uri") String str, @z61(name = "name") String str2, @z61(name = "images") List<WebApiSearchModel$Image> list) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.a = str;
        this.b = str2;
        this.c = list;
    }

    public List a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public String toString() {
        return b();
    }

    public /* synthetic */ WebApiSearchModel$Album(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : list);
    }
}
