package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import com.bumptech.glide.load.p.d.j;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class u implements com.bumptech.glide.load.k<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f2364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.b f2365b;

    static class a implements j.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final s f2366a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.t.d f2367b;

        a(s sVar, com.bumptech.glide.t.d dVar) {
            this.f2366a = sVar;
            this.f2367b = dVar;
        }

        @Override // com.bumptech.glide.load.p.d.j.b
        public void a(com.bumptech.glide.load.n.a0.e eVar, Bitmap bitmap) throws IOException {
            IOException iOExceptionA = this.f2367b.a();
            if (iOExceptionA != null) {
                if (bitmap == null) {
                    throw iOExceptionA;
                }
                eVar.d(bitmap);
                throw iOExceptionA;
            }
        }

        @Override // com.bumptech.glide.load.p.d.j.b
        public void b() {
            this.f2366a.b();
        }
    }

    public u(j jVar, com.bumptech.glide.load.n.a0.b bVar) {
        this.f2364a = jVar;
        this.f2365b = bVar;
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(InputStream inputStream, int i, int i2, com.bumptech.glide.load.i iVar) {
        boolean z;
        s sVar;
        if (inputStream instanceof s) {
            sVar = (s) inputStream;
            z = false;
        } else {
            z = true;
            sVar = new s(inputStream, this.f2365b);
        }
        com.bumptech.glide.t.d dVarB = com.bumptech.glide.t.d.b(sVar);
        try {
            return this.f2364a.g(new com.bumptech.glide.t.h(dVarB), i, i2, iVar, new a(sVar, dVarB));
        } finally {
            dVarB.c();
            if (z) {
                sVar.c();
            }
        }
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, com.bumptech.glide.load.i iVar) {
        return this.f2364a.p(inputStream);
    }
}
