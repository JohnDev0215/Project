package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class zt implements bn<Bitmap, Bitmap> {

    public static final class a implements wm<Bitmap> {
        public final Bitmap a;

        public a(@NonNull Bitmap bitmap) {
            this.a = bitmap;
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

        @Override // spi.wm
        public final void recycle() {
        }
    }

    @Override // spi.bn
    public final /* bridge */ /* synthetic */ boolean a(@NonNull Bitmap bitmap, @NonNull lk lkVar) throws IOException {
        return true;
    }

    @Override // spi.bn
    public final wm<Bitmap> b(@NonNull Bitmap bitmap, int i, int i2, @NonNull lk lkVar) throws IOException {
        return new a(bitmap);
    }
}
