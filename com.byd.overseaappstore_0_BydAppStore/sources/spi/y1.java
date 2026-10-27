package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class y1 implements wm<Bitmap>, pf {
    public final Bitmap a;
    public final w1 b;

    public y1(@NonNull Bitmap bitmap, @NonNull w1 w1Var) {
        Objects.requireNonNull(bitmap, "Bitmap must not be null");
        this.a = bitmap;
        Objects.requireNonNull(w1Var, "BitmapPool must not be null");
        this.b = w1Var;
    }

    @Nullable
    public static y1 b(@Nullable Bitmap bitmap, @NonNull w1 w1Var) {
        if (bitmap == null) {
            return null;
        }
        return new y1(bitmap, w1Var);
    }

    @Override // spi.wm
    public final int a() {
        return tu.c(this.a);
    }

    @Override // spi.wm
    @NonNull
    public final Class<Bitmap> c() {
        return Bitmap.class;
    }

    @Override // spi.wm
    @NonNull
    public final Bitmap get() {
        return this.a;
    }

    @Override // spi.pf
    public final void initialize() {
        this.a.prepareToDraw();
    }

    @Override // spi.wm
    public final void recycle() {
        this.b.c(this.a);
    }
}
