package com.bumptech.glide.load.j.g;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.k.a;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.o.k;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

/* JADX INFO: compiled from: ByteBufferGifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements com.bumptech.glide.load.f<ByteBuffer, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final C0078a f3294a = new C0078a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b f3295b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f3296c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<ImageHeaderParser> f3297d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b f3298e;
    private final C0078a f;
    private final com.bumptech.glide.load.j.g.b g;

    /* JADX INFO: renamed from: com.bumptech.glide.load.j.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class C0078a {
        C0078a() {
        }

        com.bumptech.glide.k.a a(a.InterfaceC0060a interfaceC0060a, com.bumptech.glide.k.c cVar, ByteBuffer byteBuffer, int i) {
            return new com.bumptech.glide.k.e(interfaceC0060a, cVar, byteBuffer, i);
        }
    }

    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<com.bumptech.glide.k.d> f3299a = k.e(0);

        b() {
        }

        synchronized com.bumptech.glide.k.d a(ByteBuffer byteBuffer) {
            com.bumptech.glide.k.d dVarPoll;
            dVarPoll = this.f3299a.poll();
            if (dVarPoll == null) {
                dVarPoll = new com.bumptech.glide.k.d();
            }
            return dVarPoll.p(byteBuffer);
        }

        synchronized void b(com.bumptech.glide.k.d dVar) {
            dVar.a();
            this.f3299a.offer(dVar);
        }
    }

    public a(Context context, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar) {
        this(context, list, eVar, bVar, f3295b, f3294a);
    }

    private e c(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.k.d dVar, com.bumptech.glide.load.e eVar) {
        long jB = com.bumptech.glide.o.f.b();
        try {
            com.bumptech.glide.k.c cVarC = dVar.c();
            if (cVarC.b() > 0 && cVarC.c() == 0) {
                Bitmap.Config config = eVar.c(i.f3316a) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                com.bumptech.glide.k.a aVarA = this.f.a(this.g, cVarC, byteBuffer, e(cVarC, i, i2));
                aVarA.c(config);
                aVarA.e();
                Bitmap bitmapD = aVarA.d();
                if (bitmapD == null) {
                    return null;
                }
                e eVar2 = new e(new c(this.f3296c, aVarA, com.bumptech.glide.load.j.c.c(), i, i2, bitmapD));
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.o.f.a(jB));
                }
                return eVar2;
            }
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.o.f.a(jB));
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.o.f.a(jB));
            }
        }
    }

    private static int e(com.bumptech.glide.k.c cVar, int i, int i2) {
        int iMin = Math.min(cVar.a() / i2, cVar.d() / i);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable("BufferGifDecoder", 2) && iMax > 1) {
            Log.v("BufferGifDecoder", "Downsampling GIF, sampleSize: " + iMax + ", target dimens: [" + i + "x" + i2 + "], actual dimens: [" + cVar.d() + "x" + cVar.a() + "]");
        }
        return iMax;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public e a(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.load.e eVar) {
        com.bumptech.glide.k.d dVarA = this.f3298e.a(byteBuffer);
        try {
            return c(byteBuffer, i, i2, dVarA, eVar);
        } finally {
            this.f3298e.b(dVarA);
        }
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.e eVar) throws IOException {
        return !((Boolean) eVar.c(i.f3317b)).booleanValue() && com.bumptech.glide.load.b.f(this.f3297d, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    a(Context context, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar, b bVar2, C0078a c0078a) {
        this.f3296c = context.getApplicationContext();
        this.f3297d = list;
        this.f = c0078a;
        this.g = new com.bumptech.glide.load.j.g.b(eVar, bVar);
        this.f3298e = bVar2;
    }
}
