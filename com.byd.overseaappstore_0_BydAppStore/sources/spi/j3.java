package spi;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class j3 extends z1 {
    public static final byte[] b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(ig.a);

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(b);
    }

    @Override // spi.z1
    public final Bitmap c(@NonNull w1 w1Var, @NonNull Bitmap bitmap, int i, int i2) {
        float width;
        float height;
        Paint paint = st.a;
        if (bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float width2 = 0.0f;
        if (bitmap.getWidth() * i2 > bitmap.getHeight() * i) {
            width = i2 / bitmap.getHeight();
            width2 = (i - (bitmap.getWidth() * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = i / bitmap.getWidth();
            height = (i2 - (bitmap.getHeight() * width)) * 0.5f;
        }
        matrix.setScale(width, width);
        matrix.postTranslate((int) (width2 + 0.5f), (int) (height + 0.5f));
        Bitmap bitmapD = w1Var.d(i, i2, st.c(bitmap));
        bitmapD.setHasAlpha(bitmap.hasAlpha());
        st.a(bitmap, bitmapD, matrix);
        return bitmapD;
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        return obj instanceof j3;
    }

    @Override // spi.ig
    public final int hashCode() {
        return -599754482;
    }
}
