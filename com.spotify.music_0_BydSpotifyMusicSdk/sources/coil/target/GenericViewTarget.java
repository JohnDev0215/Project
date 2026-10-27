package coil.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import p.n70;
import p.s91;
import p.yi2;

/* JADX INFO: loaded from: classes.dex */
public abstract class GenericViewTarget<T extends View> implements n70, yi2 {
    public boolean c;

    @Override // p.yi2
    public void c(Drawable drawable) {
        l(drawable);
    }

    @Override // p.yi2
    public final void f(Drawable drawable) {
        l(drawable);
    }

    @Override // p.yi2
    public final void g(Drawable drawable) {
        l(drawable);
    }

    public abstract Drawable h();

    public abstract ImageView i();

    public abstract void j(Drawable drawable);

    public final void k() {
        Object objH = h();
        Animatable animatable = objH instanceof Animatable ? (Animatable) objH : null;
        if (animatable == null) {
            return;
        }
        if (this.c) {
            animatable.start();
        } else {
            animatable.stop();
        }
    }

    public final void l(Drawable drawable) {
        Object objH = h();
        Animatable animatable = objH instanceof Animatable ? (Animatable) objH : null;
        if (animatable != null) {
            animatable.stop();
        }
        j(drawable);
        k();
    }

    @Override // p.n70
    public final void onStart(s91 s91Var) {
        this.c = true;
        k();
    }

    @Override // p.n70
    public final void onStop(s91 s91Var) {
        this.c = false;
        k();
    }
}
