package spi;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class tj extends u7<Drawable> {
    private tj(Drawable drawable) {
        super(drawable);
    }

    @Nullable
    public static wm<Drawable> b(@Nullable Drawable drawable) {
        if (drawable != null) {
            return new tj(drawable);
        }
        return null;
    }

    @Override // spi.wm
    public final int a() {
        return Math.max(1, this.a.getIntrinsicHeight() * this.a.getIntrinsicWidth() * 4);
    }

    @Override // spi.wm
    @NonNull
    public final Class<Drawable> c() {
        return this.a.getClass();
    }

    @Override // spi.wm
    public final void recycle() {
    }
}
