package o;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import c0.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f4326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f4327b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f4328c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f4329d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f4330e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f4331f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c0 f4332g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c0 f4333h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f4334i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4335j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4336k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Typeface f4337l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4338m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f4339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4340b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f4341c;

        public a(int i3, int i4, WeakReference weakReference) {
            this.f4339a = i3;
            this.f4340b = i4;
            this.f4341c = weakReference;
        }

        @Override // c0.f.a
        public void c(int i3) {
        }

        @Override // c0.f.a
        public void d(Typeface typeface) {
            int i3;
            if (Build.VERSION.SDK_INT >= 28 && (i3 = this.f4339a) != -1) {
                typeface = Typeface.create(typeface, i3, (this.f4340b & 2) != 0);
            }
            m.this.n(this.f4341c, typeface);
        }
    }

    public m(TextView textView) {
        this.f4326a = textView;
        this.f4334i = new n(textView);
    }

    public static c0 d(Context context, f fVar, int i3) {
        ColorStateList colorStateListF = fVar.f(context, i3);
        if (colorStateListF == null) {
            return null;
        }
        c0 c0Var = new c0();
        c0Var.f4238d = true;
        c0Var.f4235a = colorStateListF;
        return c0Var;
    }

    public final void A(int i3, float f3) {
        this.f4334i.y(i3, f3);
    }

    public final void B(Context context, e0 e0Var) {
        String strO;
        this.f4335j = e0Var.k(R$styleable.TextAppearance_android_textStyle, this.f4335j);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            int iK = e0Var.k(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.f4336k = iK;
            if (iK != -1) {
                this.f4335j = (this.f4335j & 2) | 0;
            }
        }
        int i4 = R$styleable.TextAppearance_android_fontFamily;
        if (!e0Var.s(i4) && !e0Var.s(R$styleable.TextAppearance_fontFamily)) {
            int i5 = R$styleable.TextAppearance_android_typeface;
            if (e0Var.s(i5)) {
                this.f4338m = false;
                int iK2 = e0Var.k(i5, 1);
                if (iK2 == 1) {
                    this.f4337l = Typeface.SANS_SERIF;
                    return;
                } else if (iK2 == 2) {
                    this.f4337l = Typeface.SERIF;
                    return;
                } else {
                    if (iK2 != 3) {
                        return;
                    }
                    this.f4337l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f4337l = null;
        int i6 = R$styleable.TextAppearance_fontFamily;
        if (e0Var.s(i6)) {
            i4 = i6;
        }
        int i7 = this.f4336k;
        int i8 = this.f4335j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = e0Var.j(i4, this.f4335j, new a(i7, i8, new WeakReference(this.f4326a)));
                if (typefaceJ != null) {
                    if (i3 < 28 || this.f4336k == -1) {
                        this.f4337l = typefaceJ;
                    } else {
                        this.f4337l = Typeface.create(Typeface.create(typefaceJ, 0), this.f4336k, (this.f4335j & 2) != 0);
                    }
                }
                this.f4338m = this.f4337l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f4337l != null || (strO = e0Var.o(i4)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f4336k == -1) {
            this.f4337l = Typeface.create(strO, this.f4335j);
        } else {
            this.f4337l = Typeface.create(Typeface.create(strO, 0), this.f4336k, (this.f4335j & 2) != 0);
        }
    }

    public final void a(Drawable drawable, c0 c0Var) {
        if (drawable == null || c0Var == null) {
            return;
        }
        f.i(drawable, c0Var, this.f4326a.getDrawableState());
    }

    public void b() {
        if (this.f4327b != null || this.f4328c != null || this.f4329d != null || this.f4330e != null) {
            Drawable[] compoundDrawables = this.f4326a.getCompoundDrawables();
            a(compoundDrawables[0], this.f4327b);
            a(compoundDrawables[1], this.f4328c);
            a(compoundDrawables[2], this.f4329d);
            a(compoundDrawables[3], this.f4330e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f4331f == null && this.f4332g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f4326a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f4331f);
            a(compoundDrawablesRelative[2], this.f4332g);
        }
    }

    public void c() {
        this.f4334i.b();
    }

    public int e() {
        return this.f4334i.j();
    }

    public int f() {
        return this.f4334i.k();
    }

    public int g() {
        return this.f4334i.l();
    }

    public int[] h() {
        return this.f4334i.m();
    }

    public int i() {
        return this.f4334i.n();
    }

    public ColorStateList j() {
        c0 c0Var = this.f4333h;
        if (c0Var != null) {
            return c0Var.f4235a;
        }
        return null;
    }

    public PorterDuff.Mode k() {
        c0 c0Var = this.f4333h;
        if (c0Var != null) {
            return c0Var.f4236b;
        }
        return null;
    }

    public boolean l() {
        return this.f4334i.s();
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
        throw new UnsupportedOperationException("Method not decompiled: o.m.m(android.util.AttributeSet, int):void");
    }

    public void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.f4338m) {
            this.f4337l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.f4335j);
            }
        }
    }

    public void o(boolean z3, int i3, int i4, int i5, int i6) {
        if (n0.b.f4070a) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i3) {
        String strO;
        ColorStateList colorStateListC;
        e0 e0VarT = e0.t(context, i3, R$styleable.TextAppearance);
        int i4 = R$styleable.TextAppearance_textAllCaps;
        if (e0VarT.s(i4)) {
            r(e0VarT.a(i4, false));
        }
        int i5 = Build.VERSION.SDK_INT;
        if (i5 < 23) {
            int i6 = R$styleable.TextAppearance_android_textColor;
            if (e0VarT.s(i6) && (colorStateListC = e0VarT.c(i6)) != null) {
                this.f4326a.setTextColor(colorStateListC);
            }
        }
        int i7 = R$styleable.TextAppearance_android_textSize;
        if (e0VarT.s(i7) && e0VarT.f(i7, -1) == 0) {
            this.f4326a.setTextSize(0, 0.0f);
        }
        B(context, e0VarT);
        if (i5 >= 26) {
            int i8 = R$styleable.TextAppearance_fontVariationSettings;
            if (e0VarT.s(i8) && (strO = e0VarT.o(i8)) != null) {
                this.f4326a.setFontVariationSettings(strO);
            }
        }
        e0VarT.w();
        Typeface typeface = this.f4337l;
        if (typeface != null) {
            this.f4326a.setTypeface(typeface, this.f4335j);
        }
    }

    public void r(boolean z3) {
        this.f4326a.setAllCaps(z3);
    }

    public void s(int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        this.f4334i.u(i3, i4, i5, i6);
    }

    public void t(int[] iArr, int i3) throws IllegalArgumentException {
        this.f4334i.v(iArr, i3);
    }

    public void u(int i3) {
        this.f4334i.w(i3);
    }

    public void v(ColorStateList colorStateList) {
        if (this.f4333h == null) {
            this.f4333h = new c0();
        }
        c0 c0Var = this.f4333h;
        c0Var.f4235a = colorStateList;
        c0Var.f4238d = colorStateList != null;
        y();
    }

    public void w(PorterDuff.Mode mode) {
        if (this.f4333h == null) {
            this.f4333h = new c0();
        }
        c0 c0Var = this.f4333h;
        c0Var.f4236b = mode;
        c0Var.f4237c = mode != null;
        y();
    }

    public final void x(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f4326a.getCompoundDrawablesRelative();
            TextView textView = this.f4326a;
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
        if (i3 >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.f4326a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f4326a;
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
        Drawable[] compoundDrawables = this.f4326a.getCompoundDrawables();
        TextView textView3 = this.f4326a;
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
        c0 c0Var = this.f4333h;
        this.f4327b = c0Var;
        this.f4328c = c0Var;
        this.f4329d = c0Var;
        this.f4330e = c0Var;
        this.f4331f = c0Var;
        this.f4332g = c0Var;
    }

    public void z(int i3, float f3) {
        if (n0.b.f4070a || l()) {
            return;
        }
        A(i3, f3);
    }
}
