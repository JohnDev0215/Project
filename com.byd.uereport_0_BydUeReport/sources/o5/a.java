package o5;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import b6.c;
import c6.b;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import e6.h;
import e6.m;
import e6.p;
import o0.t;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f6872a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MaterialButton f6873b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f6874c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6875d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6876e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6877f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6878g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6879h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6880i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public PorterDuff.Mode f6881j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f6882k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f6883l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f6884m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f6885n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f6886o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6887p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6888q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f6889r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LayerDrawable f6890s;

    static {
        f6872a = Build.VERSION.SDK_INT >= 21;
    }

    public a(MaterialButton materialButton, m mVar) {
        this.f6873b = materialButton;
        this.f6874c = mVar;
    }

    public final void A(m mVar) {
        if (d() != null) {
            d().setShapeAppearanceModel(mVar);
        }
        if (l() != null) {
            l().setShapeAppearanceModel(mVar);
        }
        if (c() != null) {
            c().setShapeAppearanceModel(mVar);
        }
    }

    public void B(int i8, int i9) {
        Drawable drawable = this.f6885n;
        if (drawable != null) {
            drawable.setBounds(this.f6875d, this.f6877f, i9 - this.f6876e, i8 - this.f6878g);
        }
    }

    public final void C() {
        h hVarD = d();
        h hVarL = l();
        if (hVarD != null) {
            hVarD.h0(this.f6880i, this.f6883l);
            if (hVarL != null) {
                hVarL.g0(this.f6880i, this.f6886o ? t5.a.c(this.f6873b, R$attr.colorSurface) : 0);
            }
        }
    }

    public final InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f6875d, this.f6877f, this.f6876e, this.f6878g);
    }

    public final Drawable a() {
        h hVar = new h(this.f6874c);
        hVar.N(this.f6873b.getContext());
        h0.a.o(hVar, this.f6882k);
        PorterDuff.Mode mode = this.f6881j;
        if (mode != null) {
            h0.a.p(hVar, mode);
        }
        hVar.h0(this.f6880i, this.f6883l);
        h hVar2 = new h(this.f6874c);
        hVar2.setTint(0);
        hVar2.g0(this.f6880i, this.f6886o ? t5.a.c(this.f6873b, R$attr.colorSurface) : 0);
        if (f6872a) {
            h hVar3 = new h(this.f6874c);
            this.f6885n = hVar3;
            h0.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(b.d(this.f6884m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.f6885n);
            this.f6890s = rippleDrawable;
            return rippleDrawable;
        }
        c6.a aVar = new c6.a(this.f6874c);
        this.f6885n = aVar;
        h0.a.o(aVar, b.d(this.f6884m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.f6885n});
        this.f6890s = layerDrawable;
        return D(layerDrawable);
    }

    public int b() {
        return this.f6879h;
    }

    public p c() {
        LayerDrawable layerDrawable = this.f6890s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f6890s.getNumberOfLayers() > 2 ? (p) this.f6890s.getDrawable(2) : (p) this.f6890s.getDrawable(1);
    }

    public h d() {
        return e(false);
    }

    public final h e(boolean z7) {
        LayerDrawable layerDrawable = this.f6890s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f6872a ? (h) ((LayerDrawable) ((InsetDrawable) this.f6890s.getDrawable(0)).getDrawable()).getDrawable(!z7 ? 1 : 0) : (h) this.f6890s.getDrawable(!z7 ? 1 : 0);
    }

    public ColorStateList f() {
        return this.f6884m;
    }

    public m g() {
        return this.f6874c;
    }

    public ColorStateList h() {
        return this.f6883l;
    }

    public int i() {
        return this.f6880i;
    }

    public ColorStateList j() {
        return this.f6882k;
    }

    public PorterDuff.Mode k() {
        return this.f6881j;
    }

    public final h l() {
        return e(true);
    }

    public boolean m() {
        return this.f6887p;
    }

    public boolean n() {
        return this.f6889r;
    }

    public void o(TypedArray typedArray) {
        this.f6875d = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        this.f6876e = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        this.f6877f = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        this.f6878g = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        int i8 = R$styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i8)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i8, -1);
            this.f6879h = dimensionPixelSize;
            u(this.f6874c.w(dimensionPixelSize));
            this.f6888q = true;
        }
        this.f6880i = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        this.f6881j = z5.p.i(typedArray.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f6882k = c.a(this.f6873b.getContext(), typedArray, R$styleable.MaterialButton_backgroundTint);
        this.f6883l = c.a(this.f6873b.getContext(), typedArray, R$styleable.MaterialButton_strokeColor);
        this.f6884m = c.a(this.f6873b.getContext(), typedArray, R$styleable.MaterialButton_rippleColor);
        this.f6889r = typedArray.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        int iF = t.F(this.f6873b);
        int paddingTop = this.f6873b.getPaddingTop();
        int iE = t.E(this.f6873b);
        int paddingBottom = this.f6873b.getPaddingBottom();
        if (typedArray.hasValue(R$styleable.MaterialButton_android_background)) {
            q();
        } else {
            this.f6873b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.z0(this.f6873b, iF + this.f6875d, paddingTop + this.f6877f, iE + this.f6876e, paddingBottom + this.f6878g);
    }

    public void p(int i8) {
        if (d() != null) {
            d().setTint(i8);
        }
    }

    public void q() {
        this.f6887p = true;
        this.f6873b.setSupportBackgroundTintList(this.f6882k);
        this.f6873b.setSupportBackgroundTintMode(this.f6881j);
    }

    public void r(boolean z7) {
        this.f6889r = z7;
    }

    public void s(int i8) {
        if (this.f6888q && this.f6879h == i8) {
            return;
        }
        this.f6879h = i8;
        this.f6888q = true;
        u(this.f6874c.w(i8));
    }

    public void t(ColorStateList colorStateList) {
        if (this.f6884m != colorStateList) {
            this.f6884m = colorStateList;
            boolean z7 = f6872a;
            if (z7 && (this.f6873b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f6873b.getBackground()).setColor(b.d(colorStateList));
            } else {
                if (z7 || !(this.f6873b.getBackground() instanceof c6.a)) {
                    return;
                }
                ((c6.a) this.f6873b.getBackground()).setTintList(b.d(colorStateList));
            }
        }
    }

    public void u(m mVar) {
        this.f6874c = mVar;
        A(mVar);
    }

    public void v(boolean z7) {
        this.f6886o = z7;
        C();
    }

    public void w(ColorStateList colorStateList) {
        if (this.f6883l != colorStateList) {
            this.f6883l = colorStateList;
            C();
        }
    }

    public void x(int i8) {
        if (this.f6880i != i8) {
            this.f6880i = i8;
            C();
        }
    }

    public void y(ColorStateList colorStateList) {
        if (this.f6882k != colorStateList) {
            this.f6882k = colorStateList;
            if (d() != null) {
                h0.a.o(d(), this.f6882k);
            }
        }
    }

    public void z(PorterDuff.Mode mode) {
        if (this.f6881j != mode) {
            this.f6881j = mode;
            if (d() == null || this.f6881j == null) {
                return;
            }
            h0.a.p(d(), this.f6881j);
        }
    }
}
