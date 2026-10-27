package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class cc extends z1 {
    public static final byte[] b = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(ig.a);

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(b);
    }

    @Override // spi.z1
    public final Bitmap c(@NonNull w1 w1Var, @NonNull Bitmap bitmap, int i, int i2) {
        return st.b(w1Var, bitmap, i, i2);
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        return obj instanceof cc;
    }

    @Override // spi.ig
    public final int hashCode() {
        return 1572326941;
    }
}
