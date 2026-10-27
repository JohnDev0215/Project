package defpackage;

import android.graphics.Bitmap;
import com.google.android.gms.vision.Frame;
import defpackage.jl2;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: BitmapUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ze {
    public static Bitmap a(xe xeVar) {
        pe peVar;
        ne neVar = xeVar.c;
        if (neVar == null) {
            return null;
        }
        ye yeVar = xeVar.b;
        int i = yeVar.c;
        int i2 = yeVar.d;
        long j = ((long) i) * ((long) i2);
        if (i <= 0 || i2 <= 0 || j > 2305843009213693951L) {
            return null;
        }
        if (neVar.a == 0) {
            peVar = new pe(null, ByteBuffer.wrap(neVar.b));
        } else {
            oe oeVar = neVar.c;
            jl2 jl2Var = oeVar.b;
            long j2 = oeVar.c;
            jl2.a aVar = jl2.a.c;
            peVar = new pe(oeVar.b, jl2Var.d(j2));
        }
        try {
            ByteBuffer byteBuffer = peVar.f;
            if (byteBuffer.capacity() <= 0) {
                return null;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
            peVar.close();
            return bitmapCreateBitmap;
        } finally {
            try {
                peVar.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static Frame b(xe xeVar) {
        Bitmap bitmapA = a(xeVar);
        if (bitmapA == null) {
            return null;
        }
        return new Frame.Builder().setBitmap(bitmapA).build();
    }
}
