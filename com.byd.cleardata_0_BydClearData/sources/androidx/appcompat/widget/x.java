package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.core.content.c.f;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextView f1057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t0 f1058b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t0 f1059c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t0 f1060d;
    private t0 e;
    private t0 f;
    private t0 g;
    private t0 h;
    private final z i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1061a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1062b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f1063c;

        a(int i, int i2, WeakReference weakReference) {
            this.f1061a = i;
            this.f1062b = i2;
            this.f1063c = weakReference;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i) {
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f1061a) != -1) {
                typeface = Typeface.create(typeface, i, (this.f1062b & 2) != 0);
            }
            x.this.n(this.f1063c, typeface);
        }
    }

    x(TextView textView) {
        this.f1057a = textView;
        this.i = new z(textView);
    }

    private void A(int i, float f) {
        this.i.y(i, f);
    }

    private void B(Context context, v0 v0Var) {
        String strO;
        Typeface typefaceCreate;
        Typeface typeface;
        this.j = v0Var.k(a.a.j.M2, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = v0Var.k(a.a.j.R2, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = a.a.j.Q2;
        if (!v0Var.s(i2) && !v0Var.s(a.a.j.S2)) {
            int i3 = a.a.j.L2;
            if (v0Var.s(i3)) {
                this.m = false;
                int iK2 = v0Var.k(i3, 1);
                if (iK2 == 1) {
                    typeface = Typeface.SANS_SERIF;
                } else if (iK2 == 2) {
                    typeface = Typeface.SERIF;
                } else if (iK2 != 3) {
                    return;
                } else {
                    typeface = Typeface.MONOSPACE;
                }
                this.l = typeface;
                return;
            }
            return;
        }
        this.l = null;
        int i4 = a.a.j.S2;
        if (v0Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = v0Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.f1057a)));
                if (typefaceJ != null) {
                    if (i >= 28 && this.k != -1) {
                        typefaceJ = Typeface.create(Typeface.create(typefaceJ, 0), this.k, (this.j & 2) != 0);
                    }
                    this.l = typefaceJ;
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strO = v0Var.o(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            typefaceCreate = Typeface.create(strO, this.j);
        } else {
            typefaceCreate = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
        this.l = typefaceCreate;
    }

    private void a(Drawable drawable, t0 t0Var) {
        if (drawable == null || t0Var == null) {
            return;
        }
        i.i(drawable, t0Var, this.f1057a.getDrawableState());
    }

    private static t0 d(Context context, i iVar, int i) {
        ColorStateList colorStateListF = iVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        t0 t0Var = new t0();
        t0Var.f1025d = true;
        t0Var.f1022a = colorStateListF;
        return t0Var;
    }

    private void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f1057a.getCompoundDrawablesRelative();
            TextView textView = this.f1057a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        if (i >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f1057a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f1057a;
                Drawable drawable7 = compoundDrawablesRelative2[0];
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable8 = compoundDrawablesRelative2[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
        }
        Drawable[] compoundDrawables = this.f1057a.getCompoundDrawables();
        TextView textView3 = this.f1057a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    private void y() {
        t0 t0Var = this.h;
        this.f1058b = t0Var;
        this.f1059c = t0Var;
        this.f1060d = t0Var;
        this.e = t0Var;
        this.f = t0Var;
        this.g = t0Var;
    }

    void b() {
        if (this.f1058b != null || this.f1059c != null || this.f1060d != null || this.e != null) {
            Drawable[] compoundDrawables = this.f1057a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1058b);
            a(compoundDrawables[1], this.f1059c);
            a(compoundDrawables[2], this.f1060d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f1057a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    void c() {
        this.i.b();
    }

    int e() {
        return this.i.j();
    }

    int f() {
        return this.i.k();
    }

    int g() {
        return this.i.l();
    }

    int[] h() {
        return this.i.m();
    }

    int i() {
        return this.i.n();
    }

    ColorStateList j() {
        t0 t0Var = this.h;
        if (t0Var != null) {
            return t0Var.f1022a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        t0 t0Var = this.h;
        if (t0Var != null) {
            return t0Var.f1023b;
        }
        return null;
    }

    boolean l() {
        return this.i.s();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01a6  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void m(android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.x.m(android.util.AttributeSet, int):void");
    }

    void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.j);
            }
        }
    }

    void o(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f1246a) {
            return;
        }
        c();
    }

    void p() {
        b();
    }

    void q(Context context, int i) {
        String strO;
        ColorStateList colorStateListC;
        v0 v0VarT = v0.t(context, i, a.a.j.J2);
        int i2 = a.a.j.U2;
        if (v0VarT.s(i2)) {
            r(v0VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = a.a.j.N2;
            if (v0VarT.s(i4) && (colorStateListC = v0VarT.c(i4)) != null) {
                this.f1057a.setTextColor(colorStateListC);
            }
        }
        int i5 = a.a.j.K2;
        if (v0VarT.s(i5) && v0VarT.f(i5, -1) == 0) {
            this.f1057a.setTextSize(0, 0.0f);
        }
        B(context, v0VarT);
        if (i3 >= 26) {
            int i6 = a.a.j.T2;
            if (v0VarT.s(i6) && (strO = v0VarT.o(i6)) != null) {
                this.f1057a.setFontVariationSettings(strO);
            }
        }
        v0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f1057a.setTypeface(typeface, this.j);
        }
    }

    void r(boolean z) {
        this.f1057a.setAllCaps(z);
    }

    void s(int i, int i2, int i3, int i4) {
        this.i.u(i, i2, i3, i4);
    }

    void t(int[] iArr, int i) {
        this.i.v(iArr, i);
    }

    void u(int i) {
        this.i.w(i);
    }

    void v(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new t0();
        }
        t0 t0Var = this.h;
        t0Var.f1022a = colorStateList;
        t0Var.f1025d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new t0();
        }
        t0 t0Var = this.h;
        t0Var.f1023b = mode;
        t0Var.f1024c = mode != null;
        y();
    }

    void z(int i, float f) {
        if (androidx.core.widget.b.f1246a || l()) {
            return;
        }
        A(i, f);
    }
}
