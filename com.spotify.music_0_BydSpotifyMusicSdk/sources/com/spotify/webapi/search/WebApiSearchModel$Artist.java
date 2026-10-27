package com.spotify.webapi.search;

import p.c71;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public class WebApiSearchModel$Artist {
    public final String a;
    public final String b;

    public WebApiSearchModel$Artist(@z61(name = "uri") String str, @z61(name = "name") String str2) {
        v03.j(str, "uri");
        v03.j(str2, "name");
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public String toString() {
        return a();
    }
}
