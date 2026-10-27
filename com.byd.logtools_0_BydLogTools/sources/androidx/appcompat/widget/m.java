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
    private final TextView f649a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f651c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f652d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c0 f653e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private c0 f654f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private c0 f655g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private c0 f656h;
    private final n i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f657a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f658b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f659c;

        a(int i, int i2, WeakReference weakReference) {
            this.f657a = i;
            this.f658b = i2;
            this.f659c = weakReference;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i) {
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f657a) != -1) {
                typeface = Typeface.create(typeface, i, (this.f658b & 2) != 0);
            }
            m.this.n(this.f659c, typeface);
        }
    }

    m(TextView textView) {
        this.f649a = textView;
        this.i = new n(textView);
    }

    private void A(int i, float f2) {
        this.i.y(i, f2);
    }

    private void B(Context context, e0 e0Var) {
        String strO;
        this.j = e0Var.k(b.a.j.c3, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = e0Var.k(b.a.j.h3, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = b.a.j.g3;
        if (!e0Var.s(i2) && !e0Var.s(b.a.j.i3)) {
            int i3 = b.a.j.b3;
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
        int i4 = b.a.j.i3;
        if (e0Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.f649a)));
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
        f.i(drawable, c0Var, this.f649a.getDrawableState());
    }

    private static c0 d(Context context, f fVar, int i) {
        ColorStateList colorStateListF = fVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f575d = true;
        c0Var.f572a = colorStateListF;
        return c0Var;
    }

    private void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f649a.getCompoundDrawablesRelative();
            TextView textView = this.f649a;
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
            Drawable[] compoundDrawablesRelative2 = this.f649a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f649a;
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
        Drawable[] compoundDrawables = this.f649a.getCompoundDrawables();
        TextView textView3 = this.f649a;
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
        c0 c0Var = this.f656h;
        this.f650b = c0Var;
        this.f651c = c0Var;
        this.f652d = c0Var;
        this.f653e = c0Var;
        this.f654f = c0Var;
        this.f655g = c0Var;
    }

    void b() {
        if (this.f650b != null || this.f651c != null || this.f652d != null || this.f653e != null) {
            Drawable[] compoundDrawables = this.f649a.getCompoundDrawables();
            a(compoundDrawables[0], this.f650b);
            a(compoundDrawables[1], this.f651c);
            a(compoundDrawables[2], this.f652d);
            a(compoundDrawables[3], this.f653e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f654f == null && this.f655g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f649a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f654f);
            a(compoundDrawablesRelative[2], this.f655g);
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
        c0 c0Var = this.f656h;
        if (c0Var != null) {
            return c0Var.f572a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        c0 c0Var = this.f656h;
        if (c0Var != null) {
            return c0Var.f573b;
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
        if (androidx.core.widget.b.f1395a) {
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
        e0 e0VarT = e0.t(context, i, b.a.j.Z2);
        int i2 = b.a.j.k3;
        if (e0VarT.s(i2)) {
            r(e0VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = b.a.j.d3;
            if (e0VarT.s(i4) && (colorStateListC = e0VarT.c(i4)) != null) {
                this.f649a.setTextColor(colorStateListC);
            }
        }
        int i5 = b.a.j.a3;
        if (e0VarT.s(i5) && e0VarT.f(i5, -1) == 0) {
            this.f649a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i3 >= 26) {
            int i6 = b.a.j.j3;
            if (e0VarT.s(i6) && (strO = e0VarT.o(i6)) != null) {
                this.f649a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f649a.setTypeface(typeface, this.j);
        }
    }

    void r(boolean z) {
        this.f649a.setAllCaps(z);
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
        if (this.f656h == null) {
            this.f656h = new c0();
        }
        c0 c0Var = this.f656h;
        c0Var.f572a = colorStateList;
        c0Var.f575d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.f656h == null) {
            this.f656h = new c0();
        }
        c0 c0Var = this.f656h;
        c0Var.f573b = mode;
        c0Var.f574c = mode != null;
        y();
    }

    void z(int i, float f2) {
        if (androidx.core.widget.b.f1395a || l()) {
            return;
        }
        A(i, f2);
    }
}
