package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import b.f.f.c.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1069a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f1070b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f1071c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1072d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f1073e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f1074f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c0 f1075g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c0 f1076h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f1077i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1078a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1079b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f1080c;

        public a(int i2, int i3, WeakReference weakReference) {
            this.f1078a = i2;
            this.f1079b = i3;
            this.f1080c = weakReference;
        }

        @Override // b.f.f.c.f.a
        public void c(int i2) {
        }

        @Override // b.f.f.c.f.a
        public void d(Typeface typeface) {
            int i2;
            if (Build.VERSION.SDK_INT >= 28 && (i2 = this.f1078a) != -1) {
                typeface = Typeface.create(typeface, i2, (this.f1079b & 2) != 0);
            }
            m.this.n(this.f1080c, typeface);
        }
    }

    public m(TextView textView) {
        this.f1069a = textView;
        this.f1077i = new n(textView);
    }

    public static c0 d(Context context, f fVar, int i2) {
        ColorStateList colorStateListF = fVar.f(context, i2);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f998d = true;
        c0Var.f995a = colorStateListF;
        return c0Var;
    }

    public final void A(int i2, float f2) {
        this.f1077i.y(i2, f2);
    }

    public final void B(Context context, e0 e0Var) {
        String strN;
        this.j = e0Var.j(b.b.j.c3, this.j);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int iJ = e0Var.j(b.b.j.h3, -1);
            this.k = iJ;
            if (iJ != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i3 = b.b.j.g3;
        if (!e0Var.r(i3) && !e0Var.r(b.b.j.i3)) {
            int i4 = b.b.j.b3;
            if (e0Var.r(i4)) {
                this.m = false;
                int iJ2 = e0Var.j(i4, 1);
                if (iJ2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iJ2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iJ2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i5 = b.b.j.i3;
        if (e0Var.r(i5)) {
            i3 = i5;
        }
        int i6 = this.k;
        int i7 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceI = e0Var.i(i3, this.j, new a(i6, i7, new WeakReference(this.f1069a)));
                if (typefaceI != null) {
                    if (i2 < 28 || this.k == -1) {
                        this.l = typefaceI;
                    } else {
                        this.l = Typeface.create(Typeface.create(typefaceI, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strN = e0Var.n(i3)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strN, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strN, 0), this.k, (this.j & 2) != 0);
        }
    }

    public final void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f1069a.getDrawableState());
    }

    public void b() {
        if (this.f1070b != null || this.f1071c != null || this.f1072d != null || this.f1073e != null) {
            Drawable[] compoundDrawables = this.f1069a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1070b);
            a(compoundDrawables[1], this.f1071c);
            a(compoundDrawables[2], this.f1072d);
            a(compoundDrawables[3], this.f1073e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f1074f == null && this.f1075g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f1069a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f1074f);
            a(compoundDrawablesRelative[2], this.f1075g);
        }
    }

    public void c() {
        this.f1077i.b();
    }

    public int e() {
        return this.f1077i.j();
    }

    public int f() {
        return this.f1077i.k();
    }

    public int g() {
        return this.f1077i.l();
    }

    public int[] h() {
        return this.f1077i.m();
    }

    public int i() {
        return this.f1077i.n();
    }

    public ColorStateList j() {
        c0 c0Var = this.f1076h;
        if (c0Var != null) {
            return c0Var.f995a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        c0 c0Var = this.f1076h;
        if (c0Var != null) {
            return c0Var.f996b;
        }
        return null;
    }

    public boolean l() {
        return this.f1077i.s();
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
    public void m(android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.q.m.m(android.util.AttributeSet, int):void");
    }

    public void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.j);
            }
        }
    }

    public void o(boolean z, int i2, int i3, int i4, int i5) {
        if (b.f.n.b.f1833a) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i2) {
        String strN;
        ColorStateList colorStateListC;
        e0 e0VarS = e0.s(context, i2, b.b.j.Z2);
        int i3 = b.b.j.k3;
        if (e0VarS.r(i3)) {
            r(e0VarS.a(i3, false));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 23) {
            int i5 = b.b.j.d3;
            if (e0VarS.r(i5) && (colorStateListC = e0VarS.c(i5)) != null) {
                this.f1069a.setTextColor(colorStateListC);
            }
        }
        int i6 = b.b.j.a3;
        if (e0VarS.r(i6) && e0VarS.e(i6, -1) == 0) {
            this.f1069a.setTextSize(0, 0.0f);
        }
        B(context, e0VarS);
        if (i4 >= 26) {
            int i7 = b.b.j.j3;
            if (e0VarS.r(i7) && (strN = e0VarS.n(i7)) != null) {
                this.f1069a.setFontVariationSettings(strN);
            }
        }
        e0VarS.v();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f1069a.setTypeface(typeface, this.j);
        }
    }

    public void r(boolean z) {
        this.f1069a.setAllCaps(z);
    }

    public void s(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        this.f1077i.u(i2, i3, i4, i5);
    }

    public void t(int[] iArr, int i2) throws IllegalArgumentException {
        this.f1077i.v(iArr, i2);
    }

    public void u(int i2) {
        this.f1077i.w(i2);
    }

    public void v(ColorStateList colorStateList) {
        if (this.f1076h == null) {
            this.f1076h = new c0();
        }
        c0 c0Var = this.f1076h;
        c0Var.f995a = colorStateList;
        c0Var.f998d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.f1076h == null) {
            this.f1076h = new c0();
        }
        c0 c0Var = this.f1076h;
        c0Var.f996b = mode;
        c0Var.f997c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f1069a.getCompoundDrawablesRelative();
            TextView textView = this.f1069a;
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
        if (i2 >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f1069a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f1069a;
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
        Drawable[] compoundDrawables = this.f1069a.getCompoundDrawables();
        TextView textView3 = this.f1069a;
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

    public final void y() {
        c0 c0Var = this.f1076h;
        this.f1070b = c0Var;
        this.f1071c = c0Var;
        this.f1072d = c0Var;
        this.f1073e = c0Var;
        this.f1074f = c0Var;
        this.f1075g = c0Var;
    }

    public void z(int i2, float f2) {
        if (b.f.n.b.f1833a || l()) {
            return;
        }
        A(i2, f2);
    }
}
