package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import defpackage.l7;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class o2 {
    public final TextView a;
    public e3 b;
    public e3 c;
    public e3 d;
    public e3 e;
    public e3 f;
    public e3 g;
    public e3 h;
    public final p2 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends l7.a {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;
        public final /* synthetic */ WeakReference c;

        public a(int i, int i2, WeakReference weakReference) {
            this.a = i;
            this.b = i2;
            this.c = weakReference;
        }

        @Override // l7.a
        public void c(int i) {
        }

        @Override // l7.a
        public void d(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.a) != -1) {
                typeface = Typeface.create(typeface, i, (this.b & 2) != 0);
            }
            o2.this.n(this.c, typeface);
        }
    }

    public o2(TextView textView) {
        this.a = textView;
        this.i = new p2(textView);
    }

    public static e3 d(Context context, h2 h2Var, int i) {
        ColorStateList colorStateListF = h2Var.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        e3 e3Var = new e3();
        e3Var.d = true;
        e3Var.a = colorStateListF;
        return e3Var;
    }

    public final void A(int i, float f) {
        this.i.y(i, f);
    }

    public final void B(Context context, g3 g3Var) {
        String strO;
        this.j = g3Var.k(R$styleable.TextAppearance_android_textStyle, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = g3Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = R$styleable.TextAppearance_android_fontFamily;
        if (!g3Var.s(i2) && !g3Var.s(R$styleable.TextAppearance_fontFamily)) {
            int i3 = R$styleable.TextAppearance_android_typeface;
            if (g3Var.s(i3)) {
                this.m = false;
                int iK2 = g3Var.k(i3, 1);
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
        int i4 = R$styleable.TextAppearance_fontFamily;
        if (g3Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = g3Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.a)));
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
        if (this.l != null || (strO = g3Var.o(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strO, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strO, 0), this.k, (this.j & 2) != 0);
        }
    }

    public final void a(Drawable drawable, e3 e3Var) {
        if (drawable == null || e3Var == null) {
            return;
        }
        h2.i(drawable, e3Var, this.a.getDrawableState());
    }

    public void b() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    public void c() {
        this.i.b();
    }

    public int e() {
        return this.i.j();
    }

    public int f() {
        return this.i.k();
    }

    public int g() {
        return this.i.l();
    }

    public int[] h() {
        return this.i.m();
    }

    public int i() {
        return this.i.n();
    }

    public ColorStateList j() {
        e3 e3Var = this.h;
        if (e3Var != null) {
            return e3Var.a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        e3 e3Var = this.h;
        if (e3Var != null) {
            return e3Var.b;
        }
        return null;
    }

    public boolean l() {
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
    public void m(android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.o2.m(android.util.AttributeSet, int):void");
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

    public void o(boolean z, int i, int i2, int i3, int i4) {
        if (ja.a) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i) {
        String strO;
        ColorStateList colorStateListC;
        g3 g3VarT = g3.t(context, i, R$styleable.TextAppearance);
        int i2 = R$styleable.TextAppearance_textAllCaps;
        if (g3VarT.s(i2)) {
            r(g3VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = R$styleable.TextAppearance_android_textColor;
            if (g3VarT.s(i4) && (colorStateListC = g3VarT.c(i4)) != null) {
                this.a.setTextColor(colorStateListC);
            }
        }
        int i5 = R$styleable.TextAppearance_android_textSize;
        if (g3VarT.s(i5) && g3VarT.f(i5, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        B(context, g3VarT);
        if (i3 >= 26) {
            int i6 = R$styleable.TextAppearance_fontVariationSettings;
            if (g3VarT.s(i6) && (strO = g3VarT.o(i6)) != null) {
                this.a.setFontVariationSettings(strO);
            }
        }
        g3VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.j);
        }
    }

    public void r(boolean z) {
        this.a.setAllCaps(z);
    }

    public void s(int i, int i2, int i3, int i4) throws IllegalArgumentException {
        this.i.u(i, i2, i3, i4);
    }

    public void t(int[] iArr, int i) throws IllegalArgumentException {
        this.i.v(iArr, i);
    }

    public void u(int i) {
        this.i.w(i);
    }

    public void v(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new e3();
        }
        e3 e3Var = this.h;
        e3Var.a = colorStateList;
        e3Var.d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new e3();
        }
        e3 e3Var = this.h;
        e3Var.b = mode;
        e3Var.c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            TextView textView = this.a;
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
            Drawable[] compoundDrawablesRelative2 = this.a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.a;
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
        Drawable[] compoundDrawables = this.a.getCompoundDrawables();
        TextView textView3 = this.a;
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
        e3 e3Var = this.h;
        this.b = e3Var;
        this.c = e3Var;
        this.d = e3Var;
        this.e = e3Var;
        this.f = e3Var;
        this.g = e3Var;
    }

    public void z(int i, float f) {
        if (ja.a || l()) {
            return;
        }
        A(i, f);
    }
}
