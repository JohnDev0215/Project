package spi;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class mf<Z> extends jv<ImageView, Z> {

    @Nullable
    public Animatable e;

    public mf(ImageView imageView) {
        super(imageView);
    }

    @Deprecated
    public mf(ImageView imageView, boolean z) {
        super(imageView, z);
    }

    @Override // spi.rs
    public final void c(@NonNull Z z, @Nullable vt<? super Z> vtVar) {
        l(z);
    }

    @Override // spi.k1, spi.rs
    public final void d(@Nullable Drawable drawable) {
        l(null);
        j(drawable);
    }

    @Override // spi.jv, spi.k1, spi.rs
    public final void g(@Nullable Drawable drawable) {
        l(null);
        j(drawable);
    }

    @Override // spi.jv, spi.k1, spi.rs
    public final void i(@Nullable Drawable drawable) {
        this.c.a();
        Animatable animatable = this.e;
        if (animatable != null) {
            animatable.stop();
        }
        l(null);
        j(drawable);
    }

    public final void j(Drawable drawable) {
        ((ImageView) this.b).setImageDrawable(drawable);
    }

    public abstract void k(@Nullable Z z);

    public final void l(@Nullable Z z) {
        k(z);
        if (!(z instanceof Animatable)) {
            this.e = null;
            return;
        }
        Animatable animatable = (Animatable) z;
        this.e = animatable;
        animatable.start();
    }

    @Override // spi.k1, spi.ug
    public final void onStart() {
        Animatable animatable = this.e;
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override // spi.k1, spi.ug
    public final void onStop() {
        Animatable animatable = this.e;
        if (animatable != null) {
            animatable.stop();
        }
    }
}
