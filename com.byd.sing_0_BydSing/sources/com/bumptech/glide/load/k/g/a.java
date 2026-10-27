package com.bumptech.glide.load.k.g;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.i.a;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.m.k;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;

/* JADX INFO: compiled from: ByteBufferGifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements com.bumptech.glide.load.f<ByteBuffer, c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final C0102a f3065a = new C0102a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b f3066b = new b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f3067c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<ImageHeaderParser> f3068d;
    private final b e;
    private final C0102a f;
    private final com.bumptech.glide.load.k.g.b g;

    /* JADX INFO: renamed from: com.bumptech.glide.load.k.g.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class C0102a {
        C0102a() {
        }

        com.bumptech.glide.i.a a(a.InterfaceC0083a interfaceC0083a, com.bumptech.glide.i.c cVar, ByteBuffer byteBuffer, int i) {
            return new com.bumptech.glide.i.e(interfaceC0083a, cVar, byteBuffer, i);
        }
    }

    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<com.bumptech.glide.i.d> f3069a = k.e(0);

        b() {
        }

        synchronized com.bumptech.glide.i.d a(ByteBuffer byteBuffer) {
            com.bumptech.glide.i.d dVarPoll;
            dVarPoll = this.f3069a.poll();
            if (dVarPoll == null) {
                dVarPoll = new com.bumptech.glide.i.d();
            }
            return dVarPoll.p(byteBuffer);
        }

        synchronized void b(com.bumptech.glide.i.d dVar) {
            dVar.a();
            this.f3069a.offer(dVar);
        }
    }

    public a(Context context, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar) {
        this(context, list, eVar, bVar, f3066b, f3065a);
    }

    private e c(ByteBuffer byteBuffer, int i, int i2, com.bumptech.glide.i.d dVar, com.bumptech.glide.load.e eVar) {
        long jB = com.bumptech.glide.m.f.b();
        try {
            com.bumptech.glide.i.c cVarC = dVar.c();
            if (cVarC.b() > 0 && cVarC.c() == 0) {
                Bitmap.Config config = eVar.c(i.f3083a) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                com.bumptech.glide.i.a aVarA = this.f.a(this.g, cVarC, byteBuffer, e(cVarC, i, i2));
                aVarA.g(config);
                aVarA.c();
                Bitmap bitmapB = aVarA.b();
                if (bitmapB == null) {
                    return null;
                }
                e eVar2 = new e(new c(this.f3067c, aVarA, com.bumptech.glide.load.k.c.c(), i, i2, bitmapB));
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.m.f.a(jB));
                }
                return eVar2;
            }
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.m.f.a(jB));
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + com.bumptech.glide.m.f.a(jB));
            }
        }
    }

    private static int e(com.bumptech.glide.i.c cVar, int i, int i2) {
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
        com.bumptech.glide.i.d dVarA = this.e.a(byteBuffer);
        try {
            return c(byteBuffer, i, i2, dVarA, eVar);
        } finally {
            this.e.b(dVarA);
        }
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, com.bumptech.glide.load.e eVar) throws IOException {
        return !((Boolean) eVar.c(i.f3084b)).booleanValue() && com.bumptech.glide.load.b.f(this.f3068d, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    a(Context context, List<ImageHeaderParser> list, com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar, b bVar2, C0102a c0102a) {
        this.f3067c = context.getApplicationContext();
        this.f3068d = list;
        this.f = c0102a;
        this.g = new com.bumptech.glide.load.k.g.b(eVar, bVar);
        this.e = bVar2;
    }
}
