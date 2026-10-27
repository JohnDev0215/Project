package com.bumptech.glide.load.i;

import android.net.Uri;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: GlideUrl.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f3196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final URL f3197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3198d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3199e;
    private URL f;
    private volatile byte[] g;
    private int h;

    public g(URL url) {
        this(url, h.f3201b);
    }

    private byte[] d() {
        if (this.g == null) {
            this.g = c().getBytes(com.bumptech.glide.load.c.f2926a);
        }
        return this.g;
    }

    private String f() {
        if (TextUtils.isEmpty(this.f3199e)) {
            String string = this.f3198d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) com.bumptech.glide.o.j.d(this.f3197c)).toString();
            }
            this.f3199e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.f3199e;
    }

    private URL g() throws MalformedURLException {
        if (this.f == null) {
            this.f = new URL(f());
        }
        return this.f;
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        messageDigest.update(d());
    }

    public String c() {
        String str = this.f3198d;
        return str != null ? str : ((URL) com.bumptech.glide.o.j.d(this.f3197c)).toString();
    }

    public Map<String, String> e() {
        return this.f3196b.a();
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return c().equals(gVar.c()) && this.f3196b.equals(gVar.f3196b);
    }

    public URL h() throws MalformedURLException {
        return g();
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        if (this.h == 0) {
            int iHashCode = c().hashCode();
            this.h = iHashCode;
            this.h = (iHashCode * 31) + this.f3196b.hashCode();
        }
        return this.h;
    }

    public String toString() {
        return c();
    }

    public g(String str) {
        this(str, h.f3201b);
    }

    public g(URL url, h hVar) {
        this.f3197c = (URL) com.bumptech.glide.o.j.d(url);
        this.f3198d = null;
        this.f3196b = (h) com.bumptech.glide.o.j.d(hVar);
    }

    public g(String str, h hVar) {
        this.f3197c = null;
        this.f3198d = com.bumptech.glide.o.j.b(str);
        this.f3196b = (h) com.bumptech.glide.o.j.d(hVar);
    }
}
