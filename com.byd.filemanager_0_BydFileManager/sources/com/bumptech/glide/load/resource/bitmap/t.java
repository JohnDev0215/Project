package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.resource.bitmap.j;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: StreamBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class t implements com.bumptech.glide.load.f<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f3385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f3386b;

    /* JADX INFO: compiled from: StreamBitmapDecoder.java */
    static class a implements j.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final RecyclableBufferedInputStream f3387a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.bumptech.glide.o.d f3388b;

        a(RecyclableBufferedInputStream recyclableBufferedInputStream, com.bumptech.glide.o.d dVar) {
            this.f3387a = recyclableBufferedInputStream;
            this.f3388b = dVar;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.j.b
        public void a(com.bumptech.glide.load.engine.x.e eVar, Bitmap bitmap) throws IOException {
            IOException iOExceptionJ = this.f3388b.j();
            if (iOExceptionJ != null) {
                if (bitmap == null) {
                    throw iOExceptionJ;
                }
                eVar.d(bitmap);
                throw iOExceptionJ;
            }
        }

        @Override // com.bumptech.glide.load.resource.bitmap.j.b
        public void b() {
            this.f3387a.k();
        }
    }

    public t(j jVar, com.bumptech.glide.load.engine.x.b bVar) {
        this.f3385a = jVar;
        this.f3386b = bVar;
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
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.f3386b);
        }
        com.bumptech.glide.o.d dVarK = com.bumptech.glide.o.d.k(recyclableBufferedInputStream);
        try {
            return this.f3385a.g(new com.bumptech.glide.o.h(dVarK), i, i2, eVar, new a(recyclableBufferedInputStream, dVarK));
        } finally {
            dVarK.l();
            if (z) {
                recyclableBufferedInputStream.l();
            }
        }
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(InputStream inputStream, com.bumptech.glide.load.e eVar) {
        return this.f3385a.p(inputStream);
    }
}
