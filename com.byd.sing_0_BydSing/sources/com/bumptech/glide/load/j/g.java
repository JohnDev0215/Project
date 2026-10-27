package com.bumptech.glide.load.j;

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
    private final h f2974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final URL f2975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2976d;
    private String e;
    private URL f;
    private volatile byte[] g;
    private int h;

    public g(URL url) {
        this(url, h.f2978b);
    }

    private byte[] d() {
        if (this.g == null) {
            this.g = c().getBytes(com.bumptech.glide.load.c.f2733a);
        }
        return this.g;
    }

    private String f() {
        if (TextUtils.isEmpty(this.e)) {
            String string = this.f2976d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) com.bumptech.glide.m.j.d(this.f2975c)).toString();
            }
            this.e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.e;
    }

    private URL g() throws MalformedURLException {
        if (this.f == null) {
            this.f = new URL(f());
        }
        return this.f;
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        messageDigest.update(d());
    }

    public String c() {
        String str = this.f2976d;
        return str != null ? str : ((URL) com.bumptech.glide.m.j.d(this.f2975c)).toString();
    }

    public Map<String, String> e() {
        return this.f2974b.a();
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return c().equals(gVar.c()) && this.f2974b.equals(gVar.f2974b);
    }

    public URL h() throws MalformedURLException {
        return g();
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        if (this.h == 0) {
            int iHashCode = c().hashCode();
            this.h = iHashCode;
            this.h = (iHashCode * 31) + this.f2974b.hashCode();
        }
        return this.h;
    }

    public String toString() {
        return c();
    }

    public g(String str) {
        this(str, h.f2978b);
    }

    public g(URL url, h hVar) {
        this.f2975c = (URL) com.bumptech.glide.m.j.d(url);
        this.f2976d = null;
        this.f2974b = (h) com.bumptech.glide.m.j.d(hVar);
    }

    public g(String str, h hVar) {
        this.f2975c = null;
        this.f2976d = com.bumptech.glide.m.j.b(str);
        this.f2974b = (h) com.bumptech.glide.m.j.d(hVar);
    }
}
