package b2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Queue;
import l1.a;
import n1.k;

/* JADX INFO: compiled from: ByteBufferGifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements k<ByteBuffer, c> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final C0032a f3474f = new C0032a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final b f3475g = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f3476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<ImageHeaderParser> f3477b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f3478c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final C0032a f3479d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b2.b f3480e;

    /* JADX INFO: renamed from: b2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class C0032a {
        C0032a() {
        }

        l1.a a(a.InterfaceC0114a interfaceC0114a, l1.c cVar, ByteBuffer byteBuffer, int i7) {
            return new l1.e(interfaceC0114a, cVar, byteBuffer, i7);
        }
    }

    /* JADX INFO: compiled from: ByteBufferGifDecoder.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<l1.d> f3481a = k2.k.e(0);

        b() {
        }

        synchronized l1.d a(ByteBuffer byteBuffer) {
            l1.d dVarPoll;
            dVarPoll = this.f3481a.poll();
            if (dVarPoll == null) {
                dVarPoll = new l1.d();
            }
            return dVarPoll.p(byteBuffer);
        }

        synchronized void b(l1.d dVar) {
            dVar.a();
            this.f3481a.offer(dVar);
        }
    }

    public a(Context context, List<ImageHeaderParser> list, r1.d dVar, r1.b bVar) {
        this(context, list, dVar, bVar, f3475g, f3474f);
    }

    private e c(ByteBuffer byteBuffer, int i7, int i8, l1.d dVar, n1.i iVar) {
        long jB = k2.f.b();
        try {
            l1.c cVarC = dVar.c();
            if (cVarC.b() > 0 && cVarC.c() == 0) {
                Bitmap.Config config = iVar.c(i.f3522a) == n1.b.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                l1.a aVarA = this.f3479d.a(this.f3480e, cVarC, byteBuffer, e(cVarC, i7, i8));
                aVarA.c(config);
                aVarA.e();
                Bitmap bitmapD = aVarA.d();
                if (bitmapD == null) {
                    return null;
                }
                e eVar = new e(new c(this.f3476a, aVarA, w1.c.c(), i7, i8, bitmapD));
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k2.f.a(jB));
                }
                return eVar;
            }
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k2.f.a(jB));
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k2.f.a(jB));
            }
        }
    }

    private static int e(l1.c cVar, int i7, int i8) {
        int iMin = Math.min(cVar.a() / i8, cVar.d() / i7);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable("BufferGifDecoder", 2) && iMax > 1) {
            Log.v("BufferGifDecoder", "Downsampling GIF, sampleSize: " + iMax + ", target dimens: [" + i7 + "x" + i8 + "], actual dimens: [" + cVar.d() + "x" + cVar.a() + "]");
        }
        return iMax;
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public e b(ByteBuffer byteBuffer, int i7, int i8, n1.i iVar) {
        l1.d dVarA = this.f3478c.a(byteBuffer);
        try {
            return c(byteBuffer, i7, i8, dVarA, iVar);
        } finally {
            this.f3478c.b(dVarA);
        }
    }

    @Override // n1.k
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public boolean a(ByteBuffer byteBuffer, n1.i iVar) {
        return !((Boolean) iVar.c(i.f3523b)).booleanValue() && com.bumptech.glide.load.a.c(this.f3477b, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    a(Context context, List<ImageHeaderParser> list, r1.d dVar, r1.b bVar, b bVar2, C0032a c0032a) {
        this.f3476a = context.getApplicationContext();
        this.f3477b = list;
        this.f3479d = c0032a;
        this.f3480e = new b2.b(dVar, bVar);
        this.f3478c = bVar2;
    }
}
