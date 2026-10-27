package spi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.resource.gif.GifDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class r7 implements in<Drawable, byte[]> {
    public final w1 a;
    public final in<Bitmap, byte[]> b;
    public final in<GifDrawable, byte[]> c;

    public r7(@NonNull w1 w1Var, @NonNull in<Bitmap, byte[]> inVar, @NonNull in<GifDrawable, byte[]> inVar2) {
        this.a = w1Var;
        this.b = inVar;
        this.c = inVar2;
    }

    @Override // spi.in
    @Nullable
    public final wm<byte[]> a(@NonNull wm<Drawable> wmVar, @NonNull lk lkVar) {
        Drawable drawable = wmVar.get();
        if (drawable instanceof BitmapDrawable) {
            return this.b.a(y1.b(((BitmapDrawable) drawable).getBitmap(), this.a), lkVar);
        }
        if (drawable instanceof GifDrawable) {
            return this.c.a(wmVar, lkVar);
        }
        return null;
    }
}
