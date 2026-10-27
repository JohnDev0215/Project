package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import y.b;

/* JADX INFO: compiled from: AppCompatTextHelper.java */
/* JADX INFO: loaded from: classes.dex */
class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final TextView f1215a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private q0 f1216b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private q0 f1217c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private q0 f1218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private q0 f1219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private q0 f1220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private q0 f1221g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private q0 f1222h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final x f1223i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1224j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1225k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Typeface f1226l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f1227m;

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1228a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1229b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ WeakReference f1230c;

        a(int i7, int i8, WeakReference weakReference) {
            this.f1228a = i7;
            this.f1229b = i8;
            this.f1230c = weakReference;
        }

        @Override // y.b.a
        public void d(int i7) {
        }

        @Override // y.b.a
        public void e(Typeface typeface) {
            int i7 = this.f1228a;
            if (i7 != -1) {
                typeface = Typeface.create(typeface, i7, (this.f1229b & 2) != 0);
            }
            v.this.n(this.f1230c, typeface);
        }
    }

    /* JADX INFO: compiled from: AppCompatTextHelper.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ TextView f1232e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Typeface f1233g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ int f1234h;

        b(TextView textView, Typeface typeface, int i7) {
            this.f1232e = textView;
            this.f1233g = typeface;
            this.f1234h = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f1232e.setTypeface(this.f1233g, this.f1234h);
        }
    }

    v(TextView textView) {
        this.f1215a = textView;
        this.f1223i = new x(textView);
    }

    private void B(int i7, float f7) {
        this.f1223i.u(i7, f7);
    }

    private void C(Context context, s0 s0Var) {
        String strO;
        this.f1224j = s0Var.k(e.j.TextAppearance_android_textStyle, this.f1224j);
        int iK = s0Var.k(e.j.TextAppearance_android_textFontWeight, -1);
        this.f1225k = iK;
        if (iK != -1) {
            this.f1224j = (this.f1224j & 2) | 0;
        }
        int i7 = e.j.TextAppearance_android_fontFamily;
        if (!s0Var.s(i7) && !s0Var.s(e.j.TextAppearance_fontFamily)) {
            int i8 = e.j.TextAppearance_android_typeface;
            if (s0Var.s(i8)) {
                this.f1227m = false;
                int iK2 = s0Var.k(i8, 1);
                if (iK2 == 1) {
                    this.f1226l = Typeface.SANS_SERIF;
                    return;
                } else if (iK2 == 2) {
                    this.f1226l = Typeface.SERIF;
                    return;
                } else {
                    if (iK2 != 3) {
                        return;
                    }
                    this.f1226l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f1226l = null;
        int i9 = e.j.TextAppearance_fontFamily;
        if (s0Var.s(i9)) {
            i7 = i9;
        }
        int i10 = this.f1225k;
        int i11 = this.f1224j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceJ = s0Var.j(i7, this.f1224j, new a(i10, i11, new WeakReference(this.f1215a)));
                if (typefaceJ != null) {
                    if (this.f1225k != -1) {
                        this.f1226l = Typeface.create(Typeface.create(typefaceJ, 0), this.f1225k, (this.f1224j & 2) != 0);
                    } else {
                        this.f1226l = typefaceJ;
                    }
                }
                this.f1227m = this.f1226l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1226l != null || (strO = s0Var.o(i7)) == null) {
            return;
        }
        if (this.f1225k != -1) {
            this.f1226l = Typeface.create(Typeface.create(strO, 0), this.f1225k, (this.f1224j & 2) != 0);
        } else {
            this.f1226l = Typeface.create(strO, this.f1224j);
        }
    }

    private void a(Drawable drawable, q0 q0Var) {
        if (drawable == null || q0Var == null) {
            return;
        }
        i.i(drawable, q0Var, this.f1215a.getDrawableState());
    }

    private static q0 d(Context context, i iVar, int i7) {
        ColorStateList colorStateListF = iVar.f(context, i7);
        if (colorStateListF == null) {
            return null;
        }
        q0 q0Var = new q0();
        q0Var.f1179d = true;
        q0Var.f1176a = colorStateListF;
        return q0Var;
    }

    private void y(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        if (drawable5 != null || drawable6 != null) {
            Drawable[] compoundDrawablesRelative = this.f1215a.getCompoundDrawablesRelative();
            TextView textView = this.f1215a;
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
        Drawable[] compoundDrawablesRelative2 = this.f1215a.getCompoundDrawablesRelative();
        if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
            TextView textView2 = this.f1215a;
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
        Drawable[] compoundDrawables = this.f1215a.getCompoundDrawables();
        TextView textView3 = this.f1215a;
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

    private void z() {
        q0 q0Var = this.f1222h;
        this.f1216b = q0Var;
        this.f1217c = q0Var;
        this.f1218d = q0Var;
        this.f1219e = q0Var;
        this.f1220f = q0Var;
        this.f1221g = q0Var;
    }

    void A(int i7, float f7) {
        if (androidx.core.widget.b.f2018a || l()) {
            return;
        }
        B(i7, f7);
    }

    void b() {
        if (this.f1216b != null || this.f1217c != null || this.f1218d != null || this.f1219e != null) {
            Drawable[] compoundDrawables = this.f1215a.getCompoundDrawables();
            a(compoundDrawables[0], this.f1216b);
            a(compoundDrawables[1], this.f1217c);
            a(compoundDrawables[2], this.f1218d);
            a(compoundDrawables[3], this.f1219e);
        }
        if (this.f1220f == null && this.f1221g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f1215a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f1220f);
        a(compoundDrawablesRelative[2], this.f1221g);
    }

    void c() {
        this.f1223i.a();
    }

    int e() {
        return this.f1223i.g();
    }

    int f() {
        return this.f1223i.h();
    }

    int g() {
        return this.f1223i.i();
    }

    int[] h() {
        return this.f1223i.j();
    }

    int i() {
        return this.f1223i.k();
    }

    ColorStateList j() {
        q0 q0Var = this.f1222h;
        if (q0Var != null) {
            return q0Var.f1176a;
        }
        return null;
    }

    PorterDuff.Mode k() {
        q0 q0Var = this.f1222h;
        if (q0Var != null) {
            return q0Var.f1177b;
        }
        return null;
    }

    boolean l() {
        return this.f1223i.o();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ff  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void m(android.util.AttributeSet r18, int r19) {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.v.m(android.util.AttributeSet, int):void");
    }

    void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.f1227m) {
            this.f1226l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                if (i0.u.O(textView)) {
                    textView.post(new b(textView, typeface, this.f1224j));
                } else {
                    textView.setTypeface(typeface, this.f1224j);
                }
            }
        }
    }

    void o(boolean z6, int i7, int i8, int i9, int i10) {
        if (androidx.core.widget.b.f2018a) {
            return;
        }
        c();
    }

    void p() {
        b();
    }

    void q(Context context, int i7) {
        String strO;
        s0 s0VarT = s0.t(context, i7, e.j.TextAppearance);
        int i8 = e.j.TextAppearance_textAllCaps;
        if (s0VarT.s(i8)) {
            s(s0VarT.a(i8, false));
        }
        int i9 = e.j.TextAppearance_android_textSize;
        if (s0VarT.s(i9) && s0VarT.f(i9, -1) == 0) {
            this.f1215a.setTextSize(0, 0.0f);
        }
        C(context, s0VarT);
        int i10 = e.j.TextAppearance_fontVariationSettings;
        if (s0VarT.s(i10) && (strO = s0VarT.o(i10)) != null) {
            this.f1215a.setFontVariationSettings(strO);
        }
        s0VarT.w();
        Typeface typeface = this.f1226l;
        if (typeface != null) {
            this.f1215a.setTypeface(typeface, this.f1224j);
        }
    }

    void r(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT >= 30 || inputConnection == null) {
            return;
        }
        k0.a.e(editorInfo, textView.getText());
    }

    void s(boolean z6) {
        this.f1215a.setAllCaps(z6);
    }

    void t(int i7, int i8, int i9, int i10) {
        this.f1223i.q(i7, i8, i9, i10);
    }

    void u(int[] iArr, int i7) {
        this.f1223i.r(iArr, i7);
    }

    void v(int i7) {
        this.f1223i.s(i7);
    }

    void w(ColorStateList colorStateList) {
        if (this.f1222h == null) {
            this.f1222h = new q0();
        }
        q0 q0Var = this.f1222h;
        q0Var.f1176a = colorStateList;
        q0Var.f1179d = colorStateList != null;
        z();
    }

    void x(PorterDuff.Mode mode) {
        if (this.f1222h == null) {
            this.f1222h = new q0();
        }
        q0 q0Var = this.f1222h;
        q0Var.f1177b = mode;
        q0Var.f1178c = mode != null;
        z();
    }
}
