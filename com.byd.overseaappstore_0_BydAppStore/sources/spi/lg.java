package spi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class lg implements wm<BitmapDrawable>, pf {
    public final Resources a;
    public final wm<Bitmap> b;

    private lg(@NonNull Resources resources, @NonNull wm<Bitmap> wmVar) {
        Objects.requireNonNull(resources, "Argument must not be null");
        this.a = resources;
        Objects.requireNonNull(wmVar, "Argument must not be null");
        this.b = wmVar;
    }

    @Nullable
    public static wm<BitmapDrawable> b(@NonNull Resources resources, @Nullable wm<Bitmap> wmVar) {
        if (wmVar == null) {
            return null;
        }
        return new lg(resources, wmVar);
    }

    @Override // spi.wm
    public final int a() {
        return this.b.a();
    }

    @Override // spi.wm
    @NonNull
    public final Class<BitmapDrawable> c() {
        return BitmapDrawable.class;
    }

    @Override // spi.wm
    @NonNull
    public final BitmapDrawable get() {
        return new BitmapDrawable(this.a, this.b.get());
    }

    @Override // spi.pf
    public final void initialize() {
        wm<Bitmap> wmVar = this.b;
        if (wmVar instanceof pf) {
            ((pf) wmVar).initialize();
        }
    }

    @Override // spi.wm
    public final void recycle() {
        this.b.recycle();
    }
}
