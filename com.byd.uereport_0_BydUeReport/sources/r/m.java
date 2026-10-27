package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import f0.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f7585a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f7586b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f7587c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f7588d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f7589e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f7590f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c0 f7591g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c0 f7592h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f7593i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7594j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7595k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Typeface f7596l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f7597m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f7598a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f7599b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f7600c;

        public a(int i8, int i9, WeakReference weakReference) {
            this.f7598a = i8;
            this.f7599b = i9;
            this.f7600c = weakReference;
        }

        @Override // f0.f.a
        public void c(int i8) {
        }

        @Override // f0.f.a
        public void d(Typeface typeface) {
            int i8;
            if (Build.VERSION.SDK_INT >= 28 && (i8 = this.f7598a) != -1) {
                typeface = Typeface.create(typeface, i8, (this.f7599b & 2) != 0);
            }
            m.this.n(this.f7600c, typeface);
        }
    }

    public m(TextView textView) {
        this.f7585a = textView;
        this.f7593i = new n(textView);
    }

    public static c0 d(Context context, f fVar, int i8) {
        ColorStateList colorStateListF = fVar.f(context, i8);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f7497d = true;
        c0Var.f7494a = colorStateListF;
        return c0Var;
    }

    public final void A(int i8, float f8) {
        this.f7593i.y(i8, f8);
    }

    public final void B(Context context, e0 e0Var) {
        String strO;
        this.f7594j = e0Var.k(R$styleable.TextAppearance_android_textStyle, this.f7594j);
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 28) {
            int iK = e0Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.f7595k = iK;
            if (iK != -1) {
                this.f7594j = (this.f7594j & 2) | 0;
            }
        }
        int i9 = R$styleable.TextAppearance_android_fontFamily;
        if (!e0Var.s(i9) && !e0Var.s(R$styleable.TextAppearance_fontFamily)) {
            int i10 = R$styleable.TextAppearance_android_typeface;
            if (e0Var.s(i10)) {
                this.f7597m = false;
                int iK2 = e0Var.k(i10, 1);
                if (iK2 == 1) {
                    this.f7596l = Typeface.SANS_SERIF;
                    return;
                } else if (iK2 == 2) {
                    this.f7596l = Typeface.SERIF;
                    return;
                } else {
                    if (iK2 != 3) {
                        return;
                    }
                    this.f7596l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f7596l = null;
        int i11 = R$styleable.TextAppearance_fontFamily;
        if (e0Var.s(i11)) {
            i9 = i11;
        }
        int i12 = this.f7595k;
        int i13 = this.f7594j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i9, this.f7594j, new a(i12, i13, new WeakReference(this.f7585a)));
                if (typefaceJ != null) {
                    if (i8 < 28 || this.f7595k == -1) {
                        this.f7596l = typefaceJ;
                    } else {
                        this.f7596l = Typeface.create(Typeface.create(typefaceJ, 0), this.f7595k, (this.f7594j & 2) != 0);
                    }
                }
                this.f7597m = this.f7596l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f7596l != null || (strO = e0Var.o(i9)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f7595k == -1) {
            this.f7596l = Typeface.create(strO, this.f7594j);
        } else {
            this.f7596l = Typeface.create(Typeface.create(strO, 0), this.f7595k, (this.f7594j & 2) != 0);
        }
    }

    public final void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f7585a.getDrawableState());
    }

    public void b() {
        if (this.f7586b != null || this.f7587c != null || this.f7588d != null || this.f7589e != null) {
            Drawable[] compoundDrawables = this.f7585a.getCompoundDrawables();
            a(compoundDrawables[0], this.f7586b);
            a(compoundDrawables[1], this.f7587c);
            a(compoundDrawables[2], this.f7588d);
            a(compoundDrawables[3], this.f7589e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f7590f == null && this.f7591g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f7585a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f7590f);
            a(compoundDrawablesRelative[2], this.f7591g);
        }
    }

    public void c() {
        this.f7593i.b();
    }

    public int e() {
        return this.f7593i.j();
    }

    public int f() {
        return this.f7593i.k();
    }

    public int g() {
        return this.f7593i.l();
    }

    public int[] h() {
        return this.f7593i.m();
    }

    public int i() {
        return this.f7593i.n();
    }

    public ColorStateList j() {
        c0 c0Var = this.f7592h;
        if (c0Var != null) {
            return c0Var.f7494a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        c0 c0Var = this.f7592h;
        if (c0Var != null) {
            return c0Var.f7495b;
        }
        return null;
    }

    public boolean l() {
        return this.f7593i.s();
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
        throw new UnsupportedOperationException("Method not decompiled: r.m.m(android.util.AttributeSet, int):void");
    }

    public void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.f7597m) {
            this.f7596l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.f7594j);
            }
        }
    }

    public void o(boolean z7, int i8, int i9, int i10, int i11) {
        if (r0.b.f7723a) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i8) {
        String strO;
        ColorStateList colorStateListC;
        e0 e0VarT = e0.t(context, i8, R$styleable.TextAppearance);
        int i9 = R$styleable.TextAppearance_textAllCaps;
        if (e0VarT.s(i9)) {
            r(e0VarT.a(i9, false));
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 23) {
            int i11 = R$styleable.TextAppearance_android_textColor;
            if (e0VarT.s(i11) && (colorStateListC = e0VarT.c(i11)) != null) {
                this.f7585a.setTextColor(colorStateListC);
            }
        }
        int i12 = R$styleable.TextAppearance_android_textSize;
        if (e0VarT.s(i12) && e0VarT.f(i12, -1) == 0) {
            this.f7585a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i10 >= 26) {
            int i13 = R$styleable.TextAppearance_fontVariationSettings;
            if (e0VarT.s(i13) && (strO = e0VarT.o(i13)) != null) {
                this.f7585a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.f7596l;
        if (typeface != null) {
            this.f7585a.setTypeface(typeface, this.f7594j);
        }
    }

    public void r(boolean z7) {
        this.f7585a.setAllCaps(z7);
    }

    public void s(int i8, int i9, int i10, int i11) throws IllegalArgumentException {
        this.f7593i.u(i8, i9, i10, i11);
    }

    public void t(int[] iArr, int i8) throws IllegalArgumentException {
        this.f7593i.v(iArr, i8);
    }

    public void u(int i8) {
        this.f7593i.w(i8);
    }

    public void v(ColorStateList colorStateList) {
        if (this.f7592h == null) {
            this.f7592h = new c0();
        }
        c0 c0Var = this.f7592h;
        c0Var.f7494a = colorStateList;
        c0Var.f7497d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.f7592h == null) {
            this.f7592h = new c0();
        }
        c0 c0Var = this.f7592h;
        c0Var.f7495b = mode;
        c0Var.f7496c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f7585a.getCompoundDrawablesRelative();
            TextView textView = this.f7585a;
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
        if (i8 >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f7585a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f7585a;
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
        Drawable[] compoundDrawables = this.f7585a.getCompoundDrawables();
        TextView textView3 = this.f7585a;
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
        c0 c0Var = this.f7592h;
        this.f7586b = c0Var;
        this.f7587c = c0Var;
        this.f7588d = c0Var;
        this.f7589e = c0Var;
        this.f7590f = c0Var;
        this.f7591g = c0Var;
    }

    public void z(int i8, float f8) {
        if (r0.b.f7723a || l()) {
            return;
        }
        A(i8, f8);
    }
}
