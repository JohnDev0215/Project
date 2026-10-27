package com.bumptech.glide.load.engine.y;

import com.bumptech.glide.m.k;
import com.bumptech.glide.m.l.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: SafeKeyGenerator.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.m.g<com.bumptech.glide.load.c, String> f2898a = new com.bumptech.glide.m.g<>(1000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.g.j.e<b> f2899b = com.bumptech.glide.m.l.a.d(10, new a());

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    class a implements a.d<b> {
        a() {
        }

        @Override // com.bumptech.glide.m.l.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    private static final class b implements a.f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final MessageDigest f2901b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final com.bumptech.glide.m.l.c f2902c = com.bumptech.glide.m.l.c.a();

        b(MessageDigest messageDigest) {
            this.f2901b = messageDigest;
        }

        @Override // com.bumptech.glide.m.l.a.f
        public com.bumptech.glide.m.l.c d() {
            return this.f2902c;
        }
    }

    private String a(com.bumptech.glide.load.c cVar) {
        b bVar = (b) com.bumptech.glide.m.j.d(this.f2899b.b());
        try {
            cVar.b(bVar.f2901b);
            return k.s(bVar.f2901b.digest());
        } finally {
            this.f2899b.a(bVar);
        }
    }

    public String b(com.bumptech.glide.load.c cVar) {
        String strG;
        synchronized (this.f2898a) {
            strG = this.f2898a.g(cVar);
        }
        if (strG == null) {
            strG = a(cVar);
        }
        synchronized (this.f2898a) {
            this.f2898a.k(cVar, strG);
        }
        return strG;
    }
}
