package b.b.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import b.g.b.c.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f804a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f805b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f806c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f807d;
    public c0 e;
    public c0 f;
    public c0 g;
    public c0 h;
    public final n i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f808a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f809b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f810c;

        public a(int i, int i2, WeakReference weakReference) {
            this.f808a = i;
            this.f809b = i2;
            this.f810c = weakReference;
        }

        @Override // b.g.b.c.f.a
        public void c(int i) {
        }

        @Override // b.g.b.c.f.a
        public void d(Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f808a) != -1) {
                typeface = Typeface.create(typeface, i, (this.f809b & 2) != 0);
            }
            m.this.n(this.f810c, typeface);
        }
    }

    public m(TextView textView) {
        this.f804a = textView;
        this.i = new n(textView);
    }

    public static c0 d(Context context, f fVar, int i) {
        ColorStateList colorStateListF = fVar.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f751d = true;
        c0Var.f748a = colorStateListF;
        return c0Var;
    }

    public final void A(int i, float f) {
        this.i.y(i, f);
    }

    public final void B(Context context, e0 e0Var) {
        String strO;
        this.j = e0Var.k(R$styleable.TextAppearance_android_textStyle, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iK = e0Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iK;
            if (iK != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i2 = R$styleable.TextAppearance_android_fontFamily;
        if (!e0Var.s(i2) && !e0Var.s(R$styleable.TextAppearance_fontFamily)) {
            int i3 = R$styleable.TextAppearance_android_typeface;
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
        int i4 = R$styleable.TextAppearance_fontFamily;
        if (e0Var.s(i4)) {
            i2 = i4;
        }
        int i5 = this.k;
        int i6 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i2, this.j, new a(i5, i6, new WeakReference(this.f804a)));
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

    public final void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f804a.getDrawableState());
    }

    public void b() {
        if (this.f805b != null || this.f806c != null || this.f807d != null || this.e != null) {
            Drawable[] compoundDrawables = this.f804a.getCompoundDrawables();
            a(compoundDrawables[0], this.f805b);
            a(compoundDrawables[1], this.f806c);
            a(compoundDrawables[2], this.f807d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f804a.getCompoundDrawablesRelative();
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
        c0 c0Var = this.h;
        if (c0Var != null) {
            return c0Var.f748a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        c0 c0Var = this.h;
        if (c0Var != null) {
            return c0Var.f749b;
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
        throw new UnsupportedOperationException("Method not decompiled: b.b.f.m.m(android.util.AttributeSet, int):void");
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
        if (b.g.k.b.f1177a) {
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
        e0 e0VarT = e0.t(context, i, R$styleable.TextAppearance);
        int i2 = R$styleable.TextAppearance_textAllCaps;
        if (e0VarT.s(i2)) {
            r(e0VarT.a(i2, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            int i4 = R$styleable.TextAppearance_android_textColor;
            if (e0VarT.s(i4) && (colorStateListC = e0VarT.c(i4)) != null) {
                this.f804a.setTextColor(colorStateListC);
            }
        }
        int i5 = R$styleable.TextAppearance_android_textSize;
        if (e0VarT.s(i5) && e0VarT.f(i5, -1) == 0) {
            this.f804a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i3 >= 26) {
            int i6 = R$styleable.TextAppearance_fontVariationSettings;
            if (e0VarT.s(i6) && (strO = e0VarT.o(i6)) != null) {
                this.f804a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f804a.setTypeface(typeface, this.j);
        }
    }

    public void r(boolean z) {
        this.f804a.setAllCaps(z);
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
            this.h = new c0();
        }
        c0 c0Var = this.h;
        c0Var.f748a = colorStateList;
        c0Var.f751d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new c0();
        }
        c0 c0Var = this.h;
        c0Var.f749b = mode;
        c0Var.f750c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f804a.getCompoundDrawablesRelative();
            TextView textView = this.f804a;
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
            Drawable[] compoundDrawablesRelative2 = this.f804a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f804a;
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
        Drawable[] compoundDrawables = this.f804a.getCompoundDrawables();
        TextView textView3 = this.f804a;
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
        c0 c0Var = this.h;
        this.f805b = c0Var;
        this.f806c = c0Var;
        this.f807d = c0Var;
        this.e = c0Var;
        this.f = c0Var;
        this.g = c0Var;
    }

    public void z(int i, float f) {
        if (b.g.k.b.f1177a || l()) {
            return;
        }
        A(i, f);
    }
}
