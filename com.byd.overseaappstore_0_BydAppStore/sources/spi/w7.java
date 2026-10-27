package spi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class w7 implements qt<Drawable> {
    public final qt<Bitmap> b;
    public final boolean c;

    public w7(qt<Bitmap> qtVar, boolean z) {
        this.b = qtVar;
        this.c = z;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        this.b.a(messageDigest);
    }

    @Override // spi.qt
    @NonNull
    public final wm<Drawable> b(@NonNull Context context, @NonNull wm<Drawable> wmVar, int i, int i2) {
        w1 w1Var = com.bumptech.glide.a.b(context).a;
        Drawable drawable = wmVar.get();
        wm<Bitmap> wmVarA = v7.a(w1Var, drawable, i, i2);
        if (wmVarA != null) {
            wm<Bitmap> wmVarB = this.b.b(context, wmVarA, i, i2);
            if (!wmVarB.equals(wmVarA)) {
                return lg.b(context.getResources(), wmVarB);
            }
            wmVarB.recycle();
            return wmVar;
        }
        if (!this.c) {
            return wmVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (obj instanceof w7) {
            return this.b.equals(((w7) obj).b);
        }
        return false;
    }

    @Override // spi.ig
    public final int hashCode() {
        return this.b.hashCode();
    }
}
