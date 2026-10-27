package r4;

import A4.k;
import F.l;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import d4.C1069b;
import d4.C1070c;
import d4.C1071d;
import e4.EnumC1225a;
import e4.j;
import g4.x;
import h4.InterfaceC1359a;
import i4.C1410c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import l8.C1789g;
import l9.n;
import p4.C2103b;

/* JADX INFO: renamed from: r4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2288a implements j {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final n f21809f = new n(7);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C1410c f21810g = new C1410c(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f21811a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f21812b;
    public final C1410c c;
    public final n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1789g f21813e;

    public C2288a(Context context, ArrayList arrayList, InterfaceC1359a interfaceC1359a, l lVar) {
        n nVar = f21809f;
        this.f21811a = context.getApplicationContext();
        this.f21812b = arrayList;
        this.d = nVar;
        this.f21813e = new C1789g(interfaceC1359a, lVar);
        this.c = f21810g;
    }

    public static int d(C1069b c1069b, int i7, int i10) {
        int iMin = Math.min(c1069b.f13354g / i10, c1069b.f13353f / i7);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable("BufferGifDecoder", 2) && iMax > 1) {
            StringBuilder sbL = M5.b.l("Downsampling GIF, sampleSize: ", ", target dimens: [", iMax, i7, "x");
            sbL.append(i10);
            sbL.append("], actual dimens: [");
            sbL.append(c1069b.f13353f);
            sbL.append("x");
            sbL.append(c1069b.f13354g);
            sbL.append("]");
            Log.v("BufferGifDecoder", sbL.toString());
        }
        return iMax;
    }

    @Override // e4.j
    public final x a(Object obj, int i7, int i10, e4.h hVar) {
        C1070c c1070c;
        ByteBuffer byteBuffer = (ByteBuffer) obj;
        C1410c c1410c = this.c;
        synchronized (c1410c) {
            try {
                C1070c c1070c2 = (C1070c) c1410c.f15749a.poll();
                if (c1070c2 == null) {
                    c1070c2 = new C1070c();
                }
                c1070c = c1070c2;
                c1070c.f13360b = null;
                Arrays.fill(c1070c.f13359a, (byte) 0);
                c1070c.c = new C1069b();
                c1070c.d = 0;
                ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                c1070c.f13360b = byteBufferAsReadOnlyBuffer;
                byteBufferAsReadOnlyBuffer.position(0);
                c1070c.f13360b.order(ByteOrder.LITTLE_ENDIAN);
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            return c(byteBuffer, i7, i10, c1070c, hVar);
        } finally {
            this.c.a(c1070c);
        }
    }

    @Override // e4.j
    public final boolean b(Object obj, e4.h hVar) {
        return !((Boolean) hVar.c(AbstractC2295h.f21842b)).booleanValue() && l9.l.S(this.f21812b, (ByteBuffer) obj) == ImageHeaderParser$ImageType.GIF;
    }

    public final C2103b c(ByteBuffer byteBuffer, int i7, int i10, C1070c c1070c, e4.h hVar) {
        int i11 = k.f423b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            C1069b c1069bB = c1070c.b();
            if (c1069bB.c > 0 && c1069bB.f13351b == 0) {
                Bitmap.Config config = hVar.c(AbstractC2295h.f21841a) == EnumC1225a.f14427m ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                int iD = d(c1069bB, i7, i10);
                n nVar = this.d;
                C1789g c1789g = this.f21813e;
                nVar.getClass();
                C1071d c1071d = new C1071d(c1789g, c1069bB, byteBuffer, iD);
                c1071d.c(config);
                c1071d.f13369k = (c1071d.f13369k + 1) % c1071d.l.c;
                Bitmap bitmapB = c1071d.b();
                if (bitmapB == null) {
                    if (Log.isLoggable("BufferGifDecoder", 2)) {
                        Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k.a(jElapsedRealtimeNanos));
                    }
                    return null;
                }
                C2103b c2103b = new C2103b(new C2290c(new C2289b(new C2294g(com.bumptech.glide.b.a(this.f21811a), c1071d, i7, i10, bitmapB))), 1);
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k.a(jElapsedRealtimeNanos));
                }
                return c2103b;
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                Log.v("BufferGifDecoder", "Decoded GIF from stream in " + k.a(jElapsedRealtimeNanos));
            }
        }
    }
}
