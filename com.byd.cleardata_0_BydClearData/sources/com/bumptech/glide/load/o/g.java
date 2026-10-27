package com.bumptech.glide.load.o;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class g implements com.bumptech.glide.load.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f2228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final URL f2229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2230d;
    private String e;
    private URL f;
    private volatile byte[] g;
    private int h;

    public g(String str) {
        this(str, h.f2232b);
    }

    public g(String str, h hVar) {
        this.f2229c = null;
        this.f2230d = com.bumptech.glide.t.j.b(str);
        this.f2228b = (h) com.bumptech.glide.t.j.d(hVar);
    }

    public g(URL url) {
        this(url, h.f2232b);
    }

    public g(URL url, h hVar) {
        this.f2229c = (URL) com.bumptech.glide.t.j.d(url);
        this.f2230d = null;
        this.f2228b = (h) com.bumptech.glide.t.j.d(hVar);
    }

    private byte[] d() {
        if (this.g == null) {
            this.g = c().getBytes(com.bumptech.glide.load.g.f2011a);
        }
        return this.g;
    }

    private String f() {
        if (TextUtils.isEmpty(this.e)) {
            String string = this.f2230d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) com.bumptech.glide.t.j.d(this.f2229c)).toString();
            }
            this.e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.e;
    }

    private URL g() {
        if (this.f == null) {
            this.f = new URL(f());
        }
        return this.f;
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        messageDigest.update(d());
    }

    public String c() {
        String str = this.f2230d;
        return str != null ? str : ((URL) com.bumptech.glide.t.j.d(this.f2229c)).toString();
    }

    public Map<String, String> e() {
        return this.f2228b.a();
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return c().equals(gVar.c()) && this.f2228b.equals(gVar.f2228b);
    }

    public URL h() {
        return g();
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        if (this.h == 0) {
            int iHashCode = c().hashCode();
            this.h = iHashCode;
            this.h = (iHashCode * 31) + this.f2228b.hashCode();
        }
        return this.h;
    }

    public String toString() {
        return c();
    }
}
