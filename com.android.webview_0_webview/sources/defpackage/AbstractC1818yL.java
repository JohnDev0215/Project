package defpackage;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: renamed from: yL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1818yL {
    public static Bitmap a(GM gm) {
        HM hm = gm.b;
        if (hm == null) {
            return null;
        }
        int i = hm.e;
        int i2 = hm.f;
        long j = ((long) i) * ((long) i2);
        if (gm.d == null || i <= 0 || i2 <= 0 || j > 2305843009213693951L) {
            return null;
        }
        int i3 = hm.b;
        if (i3 != 4 && i3 != 5) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(AbstractC1158mK.a(gm.d));
        if (byteBufferWrap.capacity() <= 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferWrap);
        return bitmapCreateBitmap;
    }

    public static C0153Ij b(GM gm) {
        Bitmap bitmapA = a(gm);
        if (bitmapA == null) {
            return null;
        }
        C0153Ij c0153Ij = new C0153Ij(null);
        int width = bitmapA.getWidth();
        int height = bitmapA.getHeight();
        c0153Ij.c = bitmapA;
        C0135Hj c0135Hj = c0153Ij.a;
        c0135Hj.a = width;
        c0135Hj.b = height;
        Objects.requireNonNull(c0153Ij);
        if (c0153Ij.c != null) {
            return c0153Ij;
        }
        throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
    }
}
