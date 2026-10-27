package com.bumptech.glide.load.engine.y;

import com.bumptech.glide.o.k;
import com.bumptech.glide.o.l.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: SafeKeyGenerator.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.o.g<com.bumptech.glide.load.c, String> f3151a = new com.bumptech.glide.o.g<>(1000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.g.j.e<b> f3152b = com.bumptech.glide.o.l.a.d(10, new a());

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    class a implements a.d<b> {
        a() {
        }

        @Override // com.bumptech.glide.o.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    private static final class b implements a.f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final MessageDigest f3154b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.bumptech.glide.o.l.c f3155c = com.bumptech.glide.o.l.c.a();

        b(MessageDigest messageDigest) {
            this.f3154b = messageDigest;
        }

        @Override // com.bumptech.glide.o.l.a.f
        public com.bumptech.glide.o.l.c e() {
            return this.f3155c;
        }
    }

    private String a(com.bumptech.glide.load.c cVar) {
        b bVar = (b) com.bumptech.glide.o.j.d(this.f3152b.b());
        try {
            cVar.a(bVar.f3154b);
            return k.s(bVar.f3154b.digest());
        } finally {
            this.f3152b.a(bVar);
        }
    }

    public String b(com.bumptech.glide.load.c cVar) {
        String strG;
        synchronized (this.f3151a) {
            strG = this.f3151a.g(cVar);
        }
        if (strG == null) {
            strG = a(cVar);
        }
        synchronized (this.f3151a) {
            this.f3151a.k(cVar, strG);
        }
        return strG;
    }
}
