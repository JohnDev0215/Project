package a.b.p;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f316a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v0 f317b;

    public m(ImageView imageView) {
        this.f316a = imageView;
    }

    public void a() {
        v0 v0Var;
        Drawable drawable = this.f316a.getDrawable();
        if (drawable != null) {
            e0.b(drawable);
        }
        if (drawable == null || (v0Var = this.f317b) == null) {
            return;
        }
        j.f(drawable, v0Var, this.f316a.getDrawableState());
    }

    public void b(AttributeSet attributeSet, int i) {
        int iL;
        x0 x0VarQ = x0.q(this.f316a.getContext(), attributeSet, a.b.j.AppCompatImageView, i, 0);
        ImageView imageView = this.f316a;
        a.h.k.q.T(imageView, imageView.getContext(), a.b.j.AppCompatImageView, attributeSet, x0VarQ.f390b, i, 0);
        try {
            Drawable drawable = this.f316a.getDrawable();
            if (drawable == null && (iL = x0VarQ.l(a.b.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = a.b.l.a.a.b(this.f316a.getContext(), iL)) != null) {
                this.f316a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                e0.b(drawable);
            }
            if (x0VarQ.o(a.b.j.AppCompatImageView_tint)) {
                this.f316a.setImageTintList(x0VarQ.c(a.b.j.AppCompatImageView_tint));
            }
            if (x0VarQ.o(a.b.j.AppCompatImageView_tintMode)) {
                this.f316a.setImageTintMode(e0.c(x0VarQ.j(a.b.j.AppCompatImageView_tintMode, -1), null));
            }
            x0VarQ.f390b.recycle();
        } catch (Throwable th) {
            x0VarQ.f390b.recycle();
            throw th;
        }
    }

    public void c(int i) {
        if (i != 0) {
            Drawable drawableB = a.b.l.a.a.b(this.f316a.getContext(), i);
            if (drawableB != null) {
                e0.b(drawableB);
            }
            this.f316a.setImageDrawable(drawableB);
        } else {
            this.f316a.setImageDrawable(null);
        }
        a();
    }

    public void d(ColorStateList colorStateList) {
        if (this.f317b == null) {
            this.f317b = new v0();
        }
        v0 v0Var = this.f317b;
        v0Var.f366a = colorStateList;
        v0Var.f369d = true;
        a();
    }

    public void e(PorterDuff.Mode mode) {
        if (this.f317b == null) {
            this.f317b = new v0();
        }
        v0 v0Var = this.f317b;
        v0Var.f367b = mode;
        v0Var.f368c = true;
        a();
    }
}
