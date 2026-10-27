package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$styleable;
import androidx.core.content.res.e;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: androidx.appcompat.widget.w, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class C0076w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final TextView f387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private S f388b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private S f389c;
    private S d;
    private S e;
    private S f;
    private S g;
    private S h;

    @NonNull
    private final C0078y i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* JADX INFO: renamed from: androidx.appcompat.widget.w$a */
    /* JADX INFO: compiled from: caradapter-bug */
    class a extends e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f392c;

        a(int i, int i2, WeakReference weakReference) {
            this.f390a = i;
            this.f391b = i2;
            this.f392c = weakReference;
        }

        @Override // androidx.core.content.res.e.b
        public void c(int i) {
        }

        @Override // androidx.core.content.res.e.b
        public void d(@NonNull Typeface typeface) {
            int i;
            if (Build.VERSION.SDK_INT >= 28 && (i = this.f390a) != -1) {
                typeface = Typeface.create(typeface, i, (this.f391b & 2) != 0);
            }
            C0076w.this.l(this.f392c, typeface);
        }
    }

    C0076w(@NonNull TextView textView) {
        this.f387a = textView;
        this.i = new C0078y(this.f387a);
    }

    private void a(Drawable drawable, S s) {
        if (drawable == null || s == null) {
            return;
        }
        C0063i.i(drawable, s, this.f387a.getDrawableState());
    }

    private static S d(Context context, C0063i c0063i, int i) {
        ColorStateList colorStateListF = c0063i.f(context, i);
        if (colorStateListF == null) {
            return null;
        }
        S s = new S();
        s.d = true;
        s.f301a = colorStateListF;
        return s;
    }

    private void v(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.f387a.getCompoundDrawablesRelative();
            TextView textView = this.f387a;
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
            Drawable[] compoundDrawablesRelative2 = this.f387a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.f387a;
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
        Drawable[] compoundDrawables = this.f387a.getCompoundDrawables();
        TextView textView3 = this.f387a;
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

    private void w() {
        S s = this.h;
        this.f388b = s;
        this.f389c = s;
        this.d = s;
        this.e = s;
        this.f = s;
        this.g = s;
    }

    private void y(int i, float f) {
        this.i.w(i, f);
    }

    private void z(Context context, U u) {
        String strN;
        int i = Build.VERSION.SDK_INT;
        this.j = u.j(R$styleable.TextAppearance_android_textStyle, this.j);
        if (i >= 28) {
            int iJ = u.j(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = iJ;
            if (iJ != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        if (!u.r(R$styleable.TextAppearance_android_fontFamily) && !u.r(R$styleable.TextAppearance_fontFamily)) {
            if (u.r(R$styleable.TextAppearance_android_typeface)) {
                this.m = false;
                int iJ2 = u.j(R$styleable.TextAppearance_android_typeface, 1);
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
        int i2 = u.r(R$styleable.TextAppearance_fontFamily) ? R$styleable.TextAppearance_fontFamily : R$styleable.TextAppearance_android_fontFamily;
        int i3 = this.k;
        int i4 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceI = u.i(i2, this.j, new a(i3, i4, new WeakReference(this.f387a)));
                if (typefaceI != null) {
                    if (i < 28 || this.k == -1) {
                        this.l = typefaceI;
                    } else {
                        this.l = Typeface.create(Typeface.create(typefaceI, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strN = u.n(i2)) == null) {
            return;
        }
        if (i < 28 || this.k == -1) {
            this.l = Typeface.create(strN, this.j);
        } else {
            this.l = Typeface.create(Typeface.create(strN, 0), this.k, (this.j & 2) != 0);
        }
    }

    void b() {
        if (this.f388b != null || this.f389c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.f387a.getCompoundDrawables();
            a(compoundDrawables[0], this.f388b);
            a(compoundDrawables[1], this.f389c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.f387a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    void c() {
        this.i.b();
    }

    int e() {
        return this.i.i();
    }

    int f() {
        return this.i.j();
    }

    int g() {
        return this.i.k();
    }

    int[] h() {
        return this.i.l();
    }

    int i() {
        return this.i.m();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    boolean j() {
        return this.i.q();
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0133  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void k(@androidx.annotation.Nullable android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instruction units count: 832
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.C0076w.k(android.util.AttributeSet, int):void");
    }

    void l(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                textView.setTypeface(typeface, this.j);
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    void m(boolean z, int i, int i2, int i3, int i4) {
        if (androidx.core.widget.b.f602a) {
            return;
        }
        c();
    }

    void n() {
        b();
    }

    void o(Context context, int i) {
        String strN;
        ColorStateList colorStateListC;
        ColorStateList colorStateListC2;
        ColorStateList colorStateListC3;
        int i2 = Build.VERSION.SDK_INT;
        U uS = U.s(context, i, R$styleable.TextAppearance);
        if (uS.r(R$styleable.TextAppearance_textAllCaps)) {
            p(uS.a(R$styleable.TextAppearance_textAllCaps, false));
        }
        if (i2 < 23) {
            if (uS.r(R$styleable.TextAppearance_android_textColor) && (colorStateListC3 = uS.c(R$styleable.TextAppearance_android_textColor)) != null) {
                this.f387a.setTextColor(colorStateListC3);
            }
            if (uS.r(R$styleable.TextAppearance_android_textColorLink) && (colorStateListC2 = uS.c(R$styleable.TextAppearance_android_textColorLink)) != null) {
                this.f387a.setLinkTextColor(colorStateListC2);
            }
            if (uS.r(R$styleable.TextAppearance_android_textColorHint) && (colorStateListC = uS.c(R$styleable.TextAppearance_android_textColorHint)) != null) {
                this.f387a.setHintTextColor(colorStateListC);
            }
        }
        if (uS.r(R$styleable.TextAppearance_android_textSize) && uS.e(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            this.f387a.setTextSize(0, 0.0f);
        }
        z(context, uS);
        if (i2 >= 26 && uS.r(R$styleable.TextAppearance_fontVariationSettings) && (strN = uS.n(R$styleable.TextAppearance_fontVariationSettings)) != null) {
            this.f387a.setFontVariationSettings(strN);
        }
        uS.v();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f387a.setTypeface(typeface, this.j);
        }
    }

    void p(boolean z) {
        this.f387a.setAllCaps(z);
    }

    void q(int i, int i2, int i3, int i4) {
        this.i.s(i, i2, i3, i4);
    }

    void r(@NonNull int[] iArr, int i) {
        this.i.t(iArr, i);
    }

    void s(int i) {
        this.i.u(i);
    }

    void t(@Nullable ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new S();
        }
        S s = this.h;
        s.f301a = colorStateList;
        s.d = colorStateList != null;
        w();
    }

    void u(@Nullable PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new S();
        }
        S s = this.h;
        s.f302b = mode;
        s.f303c = mode != null;
        w();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    void x(int i, float f) {
        if (androidx.core.widget.b.f602a || j()) {
            return;
        }
        y(i, f);
    }
}
