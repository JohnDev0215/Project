package n4;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import h4.InterfaceC1359a;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class i extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f19943b = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(e4.e.f14430a);

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        messageDigest.update(f19943b);
    }

    @Override // n4.e
    public final Bitmap c(InterfaceC1359a interfaceC1359a, Bitmap bitmap, int i7, int i10) {
        Paint paint = y.f19972a;
        if (bitmap.getWidth() > i7 || bitmap.getHeight() > i10) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return y.b(interfaceC1359a, bitmap, i7, i10);
        }
        if (!Log.isLoggable("TransformationUtils", 2)) {
            return bitmap;
        }
        Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
        return bitmap;
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        return obj instanceof i;
    }

    @Override // e4.e
    public final int hashCode() {
        return -670243078;
    }
}
