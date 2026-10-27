package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f1038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f1039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f1040c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1041d;

    public h(ImageView imageView) {
        this.f1038a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.f1041d == null) {
            this.f1041d = new c0();
        }
        c0 c0Var = this.f1041d;
        c0Var.a();
        ColorStateList colorStateListA = b.f.n.e.a(this.f1038a);
        if (colorStateListA != null) {
            c0Var.f998d = true;
            c0Var.f995a = colorStateListA;
        }
        PorterDuff.Mode modeB = b.f.n.e.b(this.f1038a);
        if (modeB != null) {
            c0Var.f997c = true;
            c0Var.f996b = modeB;
        }
        if (!c0Var.f998d && !c0Var.f997c) {
            return false;
        }
        f.i(drawable, c0Var, this.f1038a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.f1038a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f1040c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f1038a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f1039b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f1038a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f1040c;
        if (c0Var != null) {
            return c0Var.f995a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f1040c;
        if (c0Var != null) {
            return c0Var.f996b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f1038a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i2) {
        int iM;
        Context context = this.f1038a.getContext();
        int[] iArr = b.b.j.T;
        e0 e0VarU = e0.u(context, attributeSet, iArr, i2, 0);
        ImageView imageView = this.f1038a;
        b.f.m.s.R(imageView, imageView.getContext(), iArr, attributeSet, e0VarU.q(), i2, 0);
        try {
            Drawable drawable = this.f1038a.getDrawable();
            if (drawable == null && (iM = e0VarU.m(b.b.j.U, -1)) != -1 && (drawable = b.b.l.a.a.d(this.f1038a.getContext(), iM)) != null) {
                this.f1038a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i3 = b.b.j.V;
            if (e0VarU.r(i3)) {
                b.f.n.e.c(this.f1038a, e0VarU.c(i3));
            }
            int i4 = b.b.j.W;
            if (e0VarU.r(i4)) {
                b.f.n.e.d(this.f1038a, q.e(e0VarU.j(i4, -1), null));
            }
        } finally {
            e0VarU.v();
        }
    }

    public void g(int i2) {
        if (i2 != 0) {
            Drawable drawableD = b.b.l.a.a.d(this.f1038a.getContext(), i2);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f1038a.setImageDrawable(drawableD);
        } else {
            this.f1038a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f1040c == null) {
            this.f1040c = new c0();
        }
        c0 c0Var = this.f1040c;
        c0Var.f995a = colorStateList;
        c0Var.f998d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f1040c == null) {
            this.f1040c = new c0();
        }
        c0 c0Var = this.f1040c;
        c0Var.f996b = mode;
        c0Var.f997c = true;
        b();
    }

    public final boolean j() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f1039b != null : i2 == 21;
    }
}
