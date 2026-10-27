package spi;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public final class s2 implements bn<ByteBuffer, GifDrawable> {
    public static final a f = new a();
    public static final b g = new b();
    public final Context a;
    public final List<ImageHeaderParser> b;
    public final b c;
    public final a d;
    public final ad e;

    @VisibleForTesting
    public static class a {
    }

    @VisibleForTesting
    public static class b {
        public final Queue<jd> a;

        public b() {
            char[] cArr = tu.a;
            this.a = new ArrayDeque(0);
        }
    }

    public s2(Context context) {
        this(context, com.bumptech.glide.a.b(context).d.e(), com.bumptech.glide.a.b(context).a, com.bumptech.glide.a.b(context).e);
    }

    public s2(Context context, List<ImageHeaderParser> list, w1 w1Var, m0 m0Var) {
        this(context, list, w1Var, m0Var, g, f);
    }

    @VisibleForTesting
    public s2(Context context, List<ImageHeaderParser> list, w1 w1Var, m0 m0Var, b bVar, a aVar) {
        this.a = context.getApplicationContext();
        this.b = list;
        this.d = aVar;
        this.e = new ad(w1Var, m0Var);
        this.c = bVar;
    }

    public static int d(id idVar, int i, int i2) {
        int iMin = Math.min(idVar.g / i2, idVar.f / i);
        int iMax = Math.max(1, iMin == 0 ? 0 : Integer.highestOneBit(iMin));
        if (Log.isLoggable("BufferGifDecoder", 2) && iMax > 1) {
            StringBuilder sbL = e.l("Downsampling GIF, sampleSize: ", iMax, ", target dimens: [", i, "x");
            sbL.append(i2);
            sbL.append("], actual dimens: [");
            sbL.append(idVar.f);
            sbL.append("x");
            sbL.append(idVar.g);
            sbL.append("]");
            Log.v("BufferGifDecoder", sbL.toString());
        }
        return iMax;
    }

    @Override // spi.bn
    public final boolean a(@NonNull ByteBuffer byteBuffer, @NonNull lk lkVar) throws IOException {
        return !((Boolean) lkVar.c(kd.b)).booleanValue() && com.bumptech.glide.load.a.d(this.b, byteBuffer) == ImageHeaderParser.ImageType.GIF;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayDeque, java.util.Queue<spi.jd>] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.util.ArrayDeque, java.util.Queue<spi.jd>] */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.util.ArrayDeque, java.util.Queue<spi.jd>] */
    @Override // spi.bn
    public final wm<GifDrawable> b(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull lk lkVar) throws IOException {
        jd jdVar;
        ByteBuffer byteBuffer2 = byteBuffer;
        b bVar = this.c;
        synchronized (bVar) {
            jd jdVar2 = (jd) bVar.a.poll();
            if (jdVar2 == null) {
                jdVar2 = new jd();
            }
            jdVar = jdVar2;
            jdVar.b = null;
            Arrays.fill(jdVar.a, (byte) 0);
            jdVar.c = new id();
            jdVar.d = 0;
            ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer2.asReadOnlyBuffer();
            jdVar.b = byteBufferAsReadOnlyBuffer;
            byteBufferAsReadOnlyBuffer.position(0);
            jdVar.b.order(ByteOrder.LITTLE_ENDIAN);
        }
        try {
            ed edVarC = c(byteBuffer2, i, i2, jdVar, lkVar);
            b bVar2 = this.c;
            synchronized (bVar2) {
                jdVar.b = null;
                jdVar.c = null;
                bVar2.a.offer(jdVar);
            }
            return edVarC;
        } catch (Throwable th) {
            b bVar3 = this.c;
            synchronized (bVar3) {
                jdVar.b = null;
                jdVar.c = null;
                bVar3.a.offer(jdVar);
                throw th;
            }
        }
    }

    @Nullable
    public final ed c(ByteBuffer byteBuffer, int i, int i2, jd jdVar, lk lkVar) {
        int i3 = ch.b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            id idVarB = jdVar.b();
            if (idVarB.c > 0 && idVarB.b == 0) {
                Bitmap.Config config = lkVar.c(kd.a) == DecodeFormat.PREFER_RGB_565 ? Bitmap.Config.RGB_565 : Bitmap.Config.ARGB_8888;
                int iD = d(idVarB, i, i2);
                a aVar = this.d;
                ad adVar = this.e;
                Objects.requireNonNull(aVar);
                bs bsVar = new bs(adVar, idVarB, byteBuffer, iD);
                bsVar.h(config);
                bsVar.k = (bsVar.k + 1) % bsVar.l.c;
                Bitmap bitmapA = bsVar.a();
                if (bitmapA == null) {
                    return null;
                }
                ed edVar = new ed(new GifDrawable(this.a, bsVar, du.b, i, i2, bitmapA));
                if (Log.isLoggable("BufferGifDecoder", 2)) {
                    StringBuilder sbK = e.k("Decoded GIF from stream in ");
                    sbK.append(ch.a(jElapsedRealtimeNanos));
                    Log.v("BufferGifDecoder", sbK.toString());
                }
                return edVar;
            }
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                StringBuilder sbK2 = e.k("Decoded GIF from stream in ");
                sbK2.append(ch.a(jElapsedRealtimeNanos));
                Log.v("BufferGifDecoder", sbK2.toString());
            }
            return null;
        } finally {
            if (Log.isLoggable("BufferGifDecoder", 2)) {
                StringBuilder sbK3 = e.k("Decoded GIF from stream in ");
                sbK3.append(ch.a(jElapsedRealtimeNanos));
                Log.v("BufferGifDecoder", sbK3.toString());
            }
        }
    }
}
