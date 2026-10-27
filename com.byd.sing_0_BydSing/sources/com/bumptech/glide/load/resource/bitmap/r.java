package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.resource.bitmap.h;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: StreamBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class r implements com.bumptech.glide.load.f<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f3147a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f3148b;

    /* JADX INFO: compiled from: StreamBitmapDecoder.java */
    static class a implements h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final RecyclableBufferedInputStream f3149a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.m.d f3150b;

        a(RecyclableBufferedInputStream recyclableBufferedInputStream, com.bumptech.glide.m.d dVar) {
            this.f3149a = recyclableBufferedInputStream;
            this.f3150b = dVar;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.h.b
        public void a(com.bumptech.glide.load.engine.x.e eVar, Bitmap bitmap) throws IOException {
            IOException iOExceptionA = this.f3150b.a();
            if (iOExceptionA != null) {
                if (bitmap == null) {
                    throw iOExceptionA;
                }
                eVar.d(bitmap);
                throw iOExceptionA;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.h.b
        public void b() {
            this.f3149a.b();
        }
    }

    public r(h hVar, com.bumptech.glide.load.engine.x.b bVar) {
        this.f3147a = hVar;
        this.f3148b = bVar;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(InputStream inputStream, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        boolean z;
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z = false;
        } else {
            z = true;
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.f3148b);
        }
        com.bumptech.glide.m.d dVarB = com.bumptech.glide.m.d.b(recyclableBufferedInputStream);
        try {
            return this.f3147a.g(new com.bumptech.glide.m.h(dVarB), i, i2, eVar, new a(recyclableBufferedInputStream, dVarB));
        } finally {
            dVarB.c();
            if (z) {
                recyclableBufferedInputStream.c();
            }
        }
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, com.bumptech.glide.load.e eVar) {
        return this.f3147a.p(inputStream);
    }
}
