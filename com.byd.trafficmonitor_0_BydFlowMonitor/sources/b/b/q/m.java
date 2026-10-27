package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import b.h.e.c.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1784a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f1785b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f1786c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1787d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f1788e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f1789f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c0 f1790g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c0 f1791h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f1792i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f1793a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f1794b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f1795c;

        public a(int i2, int i3, WeakReference weakReference) {
            this.f1793a = i2;
            this.f1794b = i3;
            this.f1795c = weakReference;
        }

        @Override // b.h.e.c.f.a
        public void c(int i2) {
        }

        @Override // b.h.e.c.f.a
        public void d(Typeface typeface) {
            int i2;
            if (Build.VERSION.SDK_INT >= 28 && (i2 = this.f1793a) != -1) {
                typeface = Typeface.create(typeface, i2, (this.f1794b & 2) != 0);
            }
            m.this.n(this.f1795c, typeface);
        }
    }

    public m(TextView textView) {
        this.f1784a = textView;
        this.f1792i = new n(textView);
    }

    public static c0 d(Context context, f fVar, int i2) {
        ColorStateList colorStateListF = fVar.f(context, i2);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f1707d = true;
        c0Var.f1704a = colorStateListF;
        return c0Var;
    }

    public final void A(int i2, float f2) {
        this.f1792i.y(i2, f2);
    }

    public final void B(Context context, e0 e0Var) {
        String strO;
        this.j = e0Var.k(b.b.j.TextAppearance_android_textStyle, this.j);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int iK = e0Var.k(b.b.j.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i3 = b.b.j.TextAppearance_android_fontFamily;
        if (!e0Var.s(i3) && !e0Var.s(b.b.j.TextAppearance_fontFamily)) {
            int i4 = b.b.j.TextAppearance_android_typeface;
            if (e0Var.s(i4)) {
                this.m = false;
                int iK2 = e0Var.k(i4, 1);
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
        int i5 = b.b.j.TextAppearance_fontFamily;
        if (e0Var.s(i5)) {
            i3 = i5;
        }
        int i6 = this.k;
        int i7 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i3, this.j, new a(i6, i7, new WeakReference(this.f1784a)));
                if (typefaceJ != null) {
                    if (i2 < 28 || this.k == -1) {
                        this.l = typefaceJ;
                    } else {
                        this.l = Typeface.create(Typeface.create(typefaceJ, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strO = e0Var.o(i3)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    public final void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f1784a.getDrawableState());
    }

    public void b() {
        if (this.f1785b != null || this.f1786c != null || this.f1787d != null || this.f1788e != null) {
            Drawable[] compoundDrawables = this.f1784a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1785b);
            a(compoundDrawables[1], this.f1786c);
            a(compoundDrawables[2], this.f1787d);
            a(compoundDrawables[3], this.f1788e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f1789f == null && this.f1790g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f1784a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f1789f);
            a(compoundDrawablesRelative[2], this.f1790g);
        }
    }

    public void c() {
        this.f1792i.b();
    }

    public int e() {
        return this.f1792i.j();
    }

    public int f() {
        return this.f1792i.k();
    }

    public int g() {
        return this.f1792i.l();
    }

    public int[] h() {
        return this.f1792i.m();
    }

    public int i() {
        return this.f1792i.n();
    }

    public ColorStateList j() {
        c0 c0Var = this.f1791h;
        if (c0Var != null) {
            return c0Var.f1704a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        c0 c0Var = this.f1791h;
        if (c0Var != null) {
            return c0Var.f1705b;
        }
        return null;
    }

    public boolean l() {
        return this.f1792i.s();
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
        if (b.h.n.b.f2693a) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i2) {
        String strO;
        ColorStateList colorStateListC;
        e0 e0VarT = e0.t(context, i2, b.b.j.TextAppearance);
        int i3 = b.b.j.TextAppearance_textAllCaps;
        if (e0VarT.s(i3)) {
            r(e0VarT.a(i3, false));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 23) {
            int i5 = b.b.j.TextAppearance_android_textColor;
            if (e0VarT.s(i5) && (colorStateListC = e0VarT.c(i5)) != null) {
                this.f1784a.setTextColor(colorStateListC);
            }
        }
        int i6 = b.b.j.TextAppearance_android_textSize;
        if (e0VarT.s(i6) && e0VarT.f(i6, -1) == 0) {
            this.f1784a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i4 >= 26) {
            int i7 = b.b.j.TextAppearance_fontVariationSettings;
            if (e0VarT.s(i7) && (strO = e0VarT.o(i7)) != null) {
                this.f1784a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f1784a.setTypeface(typeface, this.j);
        }
    }

    public void r(boolean z) {
        this.f1784a.setAllCaps(z);
    }

    public void s(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        this.f1792i.u(i2, i3, i4, i5);
    }

    public void t(int[] iArr, int i2) throws IllegalArgumentException {
        this.f1792i.v(iArr, i2);
    }

    public void u(int i2) {
        this.f1792i.w(i2);
    }

    public void v(ColorStateList colorStateList) {
        if (this.f1791h == null) {
            this.f1791h = new c0();
        }
        c0 c0Var = this.f1791h;
        c0Var.f1704a = colorStateList;
        c0Var.f1707d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.f1791h == null) {
            this.f1791h = new c0();
        }
        c0 c0Var = this.f1791h;
        c0Var.f1705b = mode;
        c0Var.f1706c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f1784a.getCompoundDrawablesRelative();
            TextView textView = this.f1784a;
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
            Drawable[] compoundDrawablesRelative2 = this.f1784a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f1784a;
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
        Drawable[] compoundDrawables = this.f1784a.getCompoundDrawables();
        TextView textView3 = this.f1784a;
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
        c0 c0Var = this.f1791h;
        this.f1785b = c0Var;
        this.f1786c = c0Var;
        this.f1787d = c0Var;
        this.f1788e = c0Var;
        this.f1789f = c0Var;
        this.f1790g = c0Var;
    }

    public void z(int i2, float f2) {
        if (b.h.n.b.f2693a || l()) {
            return;
        }
        A(i2, f2);
    }
}
