package h2;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import i2.d;

/* JADX INFO: compiled from: ImageViewTarget.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class e<Z> extends i<ImageView, Z> implements d.a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Animatable f6597m;

    public e(ImageView imageView) {
        super(imageView);
    }

    private void q(Z z6) {
        if (!(z6 instanceof Animatable)) {
            this.f6597m = null;
            return;
        }
        Animatable animatable = (Animatable) z6;
        this.f6597m = animatable;
        animatable.start();
    }

    private void s(Z z6) {
        r(z6);
        q(z6);
    }

    @Override // h2.a, d2.i
    public void a() {
        Animatable animatable = this.f6597m;
        if (animatable != null) {
            animatable.start();
        }
    }

    @Override // i2.d.a
    public void b(Drawable drawable) {
        ((ImageView) this.f6600e).setImageDrawable(drawable);
    }

    @Override // i2.d.a
    public Drawable c() {
        return ((ImageView) this.f6600e).getDrawable();
    }

    @Override // h2.i, h2.a, h2.h
    public void d(Drawable drawable) {
        super.d(drawable);
        s(null);
        b(drawable);
    }

    @Override // h2.a, h2.h
    public void f(Drawable drawable) {
        super.f(drawable);
        s(null);
        b(drawable);
    }

    @Override // h2.a, d2.i
    public void g() {
        Animatable animatable = this.f6597m;
        if (animatable != null) {
            animatable.stop();
        }
    }

    @Override // h2.i, h2.a, h2.h
    public void i(Drawable drawable) {
        super.i(drawable);
        Animatable animatable = this.f6597m;
        if (animatable != null) {
            animatable.stop();
        }
        s(null);
        b(drawable);
    }

    @Override // h2.h
    public void k(Z z6, i2.d<? super Z> dVar) {
        if (dVar == null || !dVar.a(z6, this)) {
            s(z6);
        } else {
            q(z6);
        }
    }

    protected abstract void r(Z z6);
}
