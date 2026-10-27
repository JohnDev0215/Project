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

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextView f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f993c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f994d;
    private c0 e;
    private c0 f;
    private c0 g;
    private c0 h;
    private final n i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f995a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f996b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f997c;

        a(int i, int i2, WeakReference weakReference) {
            this.f995a = i;
            this.f996b = i2;
            this.f997c = weakReference;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i) {
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f995a) != -1) {
                typeface = Typeface.create(typeface, i, (this.f996b & 2) != 0);
            }
            m.this.n(this.f997c, typeface);
        }
    }

    m(TextView textView) {
        this.f991a = textView;
        this.i = new n(textView);
    }

    private void A(int i, float f) {
        this.i.y(i, f);
    }

    private void B(Context context, e0 e0Var) {
        String strO;
        this.j = e0Var.k(c.a.j.c3, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = e0Var.k(c.a.j.h3, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = c.a.j.g3;
        if (!e0Var.s(i2) && !e0Var.s(c.a.j.i3)) {
            int i3 = c.a.j.b3;
            if (e0Var.s(i3)) {
                this.m = false;
                int iK2 = e0Var.k(i3, 1);
                if (iK2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iK2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iK2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i4 = c.a.j.i3;
        if (e0Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.f991a)));
                if (typefaceJ != null) {
                    if (i < 28 || this.k == -1) {
                        this.l = typefaceJ;
                    } else {
                        this.l = Typeface.create(Typeface.create(typefaceJ, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strO = e0Var.o(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    private void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f991a.getDrawableState());
    }

    private static c0 d(Context context, f fVar, int i) {
        ColorStateList colorStateListF = fVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f938d = true;
        c0Var.f935a = colorStateListF;
        return c0Var;
    }

    private void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f991a.getCompoundDrawablesRelative();
            TextView textView = this.f991a;
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
            Drawable[] compoundDrawablesRelative2 = this.f991a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f991a;
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
        Drawable[] compoundDrawables = this.f991a.getCompoundDrawables();
        TextView textView3 = this.f991a;
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
        c0 c0Var = this.h;
        this.f992b = c0Var;
        this.f993c = c0Var;
        this.f994d = c0Var;
        this.e = c0Var;
        this.f = c0Var;
        this.g = c0Var;
    }

    void b() {
        if (this.f992b != null || this.f993c != null || this.f994d != null || this.e != null) {
            Drawable[] compoundDrawables = this.f991a.getCompoundDrawables();
            a(compoundDrawables[0], this.f992b);
            a(compoundDrawables[1], this.f993c);
            a(compoundDrawables[2], this.f994d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f991a.getCompoundDrawablesRelative();
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
        c0 c0Var = this.h;
        if (c0Var != null) {
            return c0Var.f935a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        c0 c0Var = this.h;
        if (c0Var != null) {
            return c0Var.f936b;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.m.m(android.util.AttributeSet, int):void");
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
        if (androidx.core.widget.b.f1452a) {
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
        e0 e0VarT = e0.t(context, i, c.a.j.Z2);
        int i2 = c.a.j.k3;
        if (e0VarT.s(i2)) {
            r(e0VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = c.a.j.d3;
            if (e0VarT.s(i4) && (colorStateListC = e0VarT.c(i4)) != null) {
                this.f991a.setTextColor(colorStateListC);
            }
        }
        int i5 = c.a.j.a3;
        if (e0VarT.s(i5) && e0VarT.f(i5, -1) == 0) {
            this.f991a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i3 >= 26) {
            int i6 = c.a.j.j3;
            if (e0VarT.s(i6) && (strO = e0VarT.o(i6)) != null) {
                this.f991a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f991a.setTypeface(typeface, this.j);
        }
    }

    void r(boolean z) {
        this.f991a.setAllCaps(z);
    }

    void s(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.i.u(i, i2, i3, i4);
    }

    void t(int[] iArr, int i) throws IllegalArgumentException {
        this.i.v(iArr, i);
    }

    void u(int i) {
        this.i.w(i);
    }

    void v(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new c0();
        }
        c0 c0Var = this.h;
        c0Var.f935a = colorStateList;
        c0Var.f938d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new c0();
        }
        c0 c0Var = this.h;
        c0Var.f936b = mode;
        c0Var.f937c = mode != null;
        y();
    }

    void z(int i, float f) {
        if (androidx.core.widget.b.f1452a || l()) {
            return;
        }
        A(i, f);
    }
}
