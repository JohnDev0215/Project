package spi;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface hh {
    @Nullable
    Bitmap a();

    String b(int i, int i2, Bitmap.Config config);

    void c(Bitmap bitmap);

    @Nullable
    Bitmap d(int i, int i2, Bitmap.Config config);

    int e(Bitmap bitmap);

    String f(Bitmap bitmap);
}
