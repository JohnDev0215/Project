package com.bumptech.glide.load.n.b0;

import com.bumptech.glide.t.k;
import com.bumptech.glide.t.l.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.t.g<com.bumptech.glide.load.g, String> f2084a = new com.bumptech.glide.t.g<>(1000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.g.j.e<b> f2085b = com.bumptech.glide.t.l.a.d(10, new a());

    class a implements a.d<b> {
        a() {
        }

        @Override // com.bumptech.glide.t.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final class b implements a.f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final MessageDigest f2087b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.bumptech.glide.t.l.c f2088c = com.bumptech.glide.t.l.c.a();

        b(MessageDigest messageDigest) {
            this.f2087b = messageDigest;
        }

        @Override // com.bumptech.glide.t.l.a.f
        public com.bumptech.glide.t.l.c e() {
            return this.f2088c;
        }
    }

    private String a(com.bumptech.glide.load.g gVar) {
        b bVar = (b) com.bumptech.glide.t.j.d(this.f2085b.b());
        try {
            gVar.a(bVar.f2087b);
            return k.s(bVar.f2087b.digest());
        } finally {
            this.f2085b.a(bVar);
        }
    }

    public String b(com.bumptech.glide.load.g gVar) {
        String strG;
        synchronized (this.f2084a) {
            strG = this.f2084a.g(gVar);
        }
        if (strG == null) {
            strG = a(gVar);
        }
        synchronized (this.f2084a) {
            this.f2084a.k(gVar, strG);
        }
        return strG;
    }
}
