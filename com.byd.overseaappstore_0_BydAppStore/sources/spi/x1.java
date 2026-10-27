package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class x1 implements w1 {
    @Override // spi.w1
    public final void a(int i) {
    }

    @Override // spi.w1
    public final void b() {
    }

    @Override // spi.w1
    public void c(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // spi.w1
    @NonNull
    public final Bitmap d(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // spi.w1
    @NonNull
    public final Bitmap e(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }
}
