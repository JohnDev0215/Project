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
    private final TextView f671a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private d0 f672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private d0 f673c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private d0 f674d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private d0 f675e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d0 f676f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private d0 f677g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d0 f678h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final n f679i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f680a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f681b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f682c;

        a(int i2, int i3, WeakReference weakReference) {
            this.f680a = i2;
            this.f681b = i3;
            this.f682c = weakReference;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i2) {
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            int i2;
            if (Build.VERSION.SDK_INT >= 28 && (i2 = this.f680a) != -1) {
                typeface = Typeface.create(typeface, i2, (this.f681b & 2) != 0);
            }
            m.this.n(this.f682c, typeface);
        }
    }

    m(TextView textView) {
        this.f671a = textView;
        this.f679i = new n(textView);
    }

    private void A(int i2, float f2) {
        this.f679i.y(i2, f2);
    }

    private void B(Context context, f0 f0Var) {
        String strO;
        this.j = f0Var.k(b.a.j.TextAppearance_android_textStyle, this.j);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int iK = f0Var.k(b.a.j.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i3 = b.a.j.TextAppearance_android_fontFamily;
        if (!f0Var.s(i3) && !f0Var.s(b.a.j.TextAppearance_fontFamily)) {
            int i4 = b.a.j.TextAppearance_android_typeface;
            if (f0Var.s(i4)) {
                this.m = false;
                int iK2 = f0Var.k(i4, 1);
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
        int i5 = b.a.j.TextAppearance_fontFamily;
        if (f0Var.s(i5)) {
            i3 = i5;
        }
        int i6 = this.k;
        int i7 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = f0Var.j(i3, this.j, new a(i6, i7, new WeakReference(this.f671a)));
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
        if (this.l != null || (strO = f0Var.o(i3)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    private void a(Drawable drawable, d0 d0Var) {
        if (drawable == null || d0Var == null) {
            return;
        }
        f.i(drawable, d0Var, this.f671a.getDrawableState());
    }

    private static d0 d(Context context, f fVar, int i2) {
        ColorStateList colorStateListF = fVar.f(context, i2);
        if (colorStateListF == null) {
            return null;
        }
        d0 d0Var = new d0();
        d0Var.f600d = true;
        d0Var.f597a = colorStateListF;
        return d0Var;
    }

    private void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f671a.getCompoundDrawablesRelative();
            TextView textView = this.f671a;
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
            Drawable[] compoundDrawablesRelative2 = this.f671a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f671a;
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
        Drawable[] compoundDrawables = this.f671a.getCompoundDrawables();
        TextView textView3 = this.f671a;
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
        d0 d0Var = this.f678h;
        this.f672b = d0Var;
        this.f673c = d0Var;
        this.f674d = d0Var;
        this.f675e = d0Var;
        this.f676f = d0Var;
        this.f677g = d0Var;
    }

    void b() {
        if (this.f672b != null || this.f673c != null || this.f674d != null || this.f675e != null) {
            Drawable[] compoundDrawables = this.f671a.getCompoundDrawables();
            a(compoundDrawables[0], this.f672b);
            a(compoundDrawables[1], this.f673c);
            a(compoundDrawables[2], this.f674d);
            a(compoundDrawables[3], this.f675e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f676f == null && this.f677g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f671a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f676f);
            a(compoundDrawablesRelative[2], this.f677g);
        }
    }

    void c() {
        this.f679i.b();
    }

    int e() {
        return this.f679i.j();
    }

    int f() {
        return this.f679i.k();
    }

    int g() {
        return this.f679i.l();
    }

    int[] h() {
        return this.f679i.m();
    }

    int i() {
        return this.f679i.n();
    }

    ColorStateList j() {
        d0 d0Var = this.f678h;
        if (d0Var != null) {
            return d0Var.f597a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        d0 d0Var = this.f678h;
        if (d0Var != null) {
            return d0Var.f598b;
        }
        return null;
    }

    boolean l() {
        return this.f679i.s();
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

    void o(boolean z, int i2, int i3, int i4, int i5) {
        if (androidx.core.widget.b.f1294a) {
            return;
        }
        c();
    }

    void p() {
        b();
    }

    void q(Context context, int i2) {
        String strO;
        ColorStateList colorStateListC;
        f0 f0VarT = f0.t(context, i2, b.a.j.TextAppearance);
        int i3 = b.a.j.TextAppearance_textAllCaps;
        if (f0VarT.s(i3)) {
            r(f0VarT.a(i3, false));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 23) {
            int i5 = b.a.j.TextAppearance_android_textColor;
            if (f0VarT.s(i5) && (colorStateListC = f0VarT.c(i5)) != null) {
                this.f671a.setTextColor(colorStateListC);
            }
        }
        int i6 = b.a.j.TextAppearance_android_textSize;
        if (f0VarT.s(i6) && f0VarT.f(i6, -1) == 0) {
            this.f671a.setTextSize(0, 0.0f);
        }
        B(context, f0VarT);
        if (i4 >= 26) {
            int i7 = b.a.j.TextAppearance_fontVariationSettings;
            if (f0VarT.s(i7) && (strO = f0VarT.o(i7)) != null) {
                this.f671a.setFontVariationSettings(strO);
            }
        }
        f0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f671a.setTypeface(typeface, this.j);
        }
    }

    void r(boolean z) {
        this.f671a.setAllCaps(z);
    }

    void s(int i2, int i3, int i4, int i5) {
        this.f679i.u(i2, i3, i4, i5);
    }

    void t(int[] iArr, int i2) {
        this.f679i.v(iArr, i2);
    }

    void u(int i2) {
        this.f679i.w(i2);
    }

    void v(ColorStateList colorStateList) {
        if (this.f678h == null) {
            this.f678h = new d0();
        }
        d0 d0Var = this.f678h;
        d0Var.f597a = colorStateList;
        d0Var.f600d = colorStateList != null;
        y();
    }

    void w(PorterDuff.Mode mode) {
        if (this.f678h == null) {
            this.f678h = new d0();
        }
        d0 d0Var = this.f678h;
        d0Var.f598b = mode;
        d0Var.f599c = mode != null;
        y();
    }

    void z(int i2, float f2) {
        if (androidx.core.widget.b.f1294a || l()) {
            return;
        }
        A(i2, f2);
    }
}
