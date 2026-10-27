package spi;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.security.MessageDigest;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class fd implements qt<GifDrawable> {
    public final qt<Bitmap> b;

    public fd(qt<Bitmap> qtVar) {
        Objects.requireNonNull(qtVar, "Argument must not be null");
        this.b = qtVar;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        this.b.a(messageDigest);
    }

    @Override // spi.qt
    @NonNull
    public final wm<GifDrawable> b(@NonNull Context context, @NonNull wm<GifDrawable> wmVar, int i, int i2) {
        GifDrawable gifDrawable = wmVar.get();
        wm<Bitmap> y1Var = new y1(gifDrawable.b(), com.bumptech.glide.a.b(context).a);
        wm<Bitmap> wmVarB = this.b.b(context, y1Var, i, i2);
        if (!y1Var.equals(wmVarB)) {
            y1Var.recycle();
        }
        Bitmap bitmap = wmVarB.get();
        gifDrawable.a.a.c(this.b, bitmap);
        return wmVar;
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (obj instanceof fd) {
            return this.b.equals(((fd) obj).b);
        }
        return false;
    }

    @Override // spi.ig
    public final int hashCode() {
        return this.b.hashCode();
    }
}
