package spi;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Log;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class k3 extends z1 {
    public static final byte[] b = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(ig.a);

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(b);
    }

    @Override // spi.z1
    public final Bitmap c(@NonNull w1 w1Var, @NonNull Bitmap bitmap, int i, int i2) {
        Paint paint = st.a;
        if (bitmap.getWidth() > i || bitmap.getHeight() > i2) {
            if (Log.isLoggable("TransformationUtils", 2)) {
                Log.v("TransformationUtils", "requested target size too big for input, fit centering instead");
            }
            return st.b(w1Var, bitmap, i, i2);
        }
        if (!Log.isLoggable("TransformationUtils", 2)) {
            return bitmap;
        }
        Log.v("TransformationUtils", "requested target size larger or equal to input, returning input");
        return bitmap;
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        return obj instanceof k3;
    }

    @Override // spi.ig
    public final int hashCode() {
        return -670243078;
    }
}
