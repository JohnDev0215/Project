package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class uh {
    public static final boolean a;
    public final MaterialButton b;
    public mk c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public PorterDuff.Mode j;
    public ColorStateList k;
    public ColorStateList l;
    public ColorStateList m;
    public Drawable n;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;
    public boolean r;
    public LayerDrawable s;

    static {
        a = Build.VERSION.SDK_INT >= 21;
    }

    public uh(MaterialButton materialButton, mk mkVar) {
        this.b = materialButton;
        this.c = mkVar;
    }

    public final void A(mk mkVar) {
        if (d() != null) {
            d().setShapeAppearanceModel(mkVar);
        }
        if (l() != null) {
            l().setShapeAppearanceModel(mkVar);
        }
        if (c() != null) {
            c().setShapeAppearanceModel(mkVar);
        }
    }

    public void B(int i, int i2) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.d, this.f, i2 - this.e, i - this.g);
        }
    }

    public final void C() {
        hk hkVarD = d();
        hk hkVarL = l();
        if (hkVarD != null) {
            hkVarD.h0(this.i, this.l);
            if (hkVarL != null) {
                hkVarL.g0(this.i, this.o ? bi.c(this.b, R$attr.colorSurface) : 0);
            }
        }
    }

    public final InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.d, this.f, this.e, this.g);
    }

    public final Drawable a() {
        hk hkVar = new hk(this.c);
        hkVar.N(this.b.getContext());
        y7.o(hkVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            y7.p(hkVar, mode);
        }
        hkVar.h0(this.i, this.l);
        hk hkVar2 = new hk(this.c);
        hkVar2.setTint(0);
        hkVar2.g0(this.i, this.o ? bi.c(this.b, R$attr.colorSurface) : 0);
        if (a) {
            hk hkVar3 = new hk(this.c);
            this.n = hkVar3;
            y7.n(hkVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(xj.d(this.m), D(new LayerDrawable(new Drawable[]{hkVar2, hkVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        wj wjVar = new wj(this.c);
        this.n = wjVar;
        y7.o(wjVar, xj.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hkVar2, hkVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    public int b() {
        return this.h;
    }

    public pk c() {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.s.getNumberOfLayers() > 2 ? (pk) this.s.getDrawable(2) : (pk) this.s.getDrawable(1);
    }

    public hk d() {
        return e(false);
    }

    public final hk e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return a ? (hk) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (hk) this.s.getDrawable(!z ? 1 : 0);
    }

    public ColorStateList f() {
        return this.m;
    }

    public mk g() {
        return this.c;
    }

    public ColorStateList h() {
        return this.l;
    }

    public int i() {
        return this.i;
    }

    public ColorStateList j() {
        return this.k;
    }

    public PorterDuff.Mode k() {
        return this.j;
    }

    public final hk l() {
        return e(true);
    }

    public boolean m() {
        return this.p;
    }

    public boolean n() {
        return this.r;
    }

    public void o(TypedArray typedArray) {
        this.d = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        this.e = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        this.f = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        this.g = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        int i = R$styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.h = dimensionPixelSize;
            u(this.c.w(dimensionPixelSize));
            this.q = true;
        }
        this.i = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        this.j = oj.i(typedArray.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = sj.a(this.b.getContext(), typedArray, R$styleable.MaterialButton_backgroundTint);
        this.l = sj.a(this.b.getContext(), typedArray, R$styleable.MaterialButton_strokeColor);
        this.m = sj.a(this.b.getContext(), typedArray, R$styleable.MaterialButton_rippleColor);
        this.r = typedArray.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        int iE = t9.E(this.b);
        int paddingTop = this.b.getPaddingTop();
        int iD = t9.D(this.b);
        int paddingBottom = this.b.getPaddingBottom();
        if (typedArray.hasValue(R$styleable.MaterialButton_android_background)) {
            q();
        } else {
            this.b.setInternalBackground(a());
            hk hkVarD = d();
            if (hkVarD != null) {
                hkVarD.W(dimensionPixelSize2);
            }
        }
        t9.w0(this.b, iE + this.d, paddingTop + this.f, iD + this.e, paddingBottom + this.g);
    }

    public void p(int i) {
        if (d() != null) {
            d().setTint(i);
        }
    }

    public void q() {
        this.p = true;
        this.b.setSupportBackgroundTintList(this.k);
        this.b.setSupportBackgroundTintMode(this.j);
    }

    public void r(boolean z) {
        this.r = z;
    }

    public void s(int i) {
        if (this.q && this.h == i) {
            return;
        }
        this.h = i;
        this.q = true;
        u(this.c.w(i));
    }

    public void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = a;
            if (z && (this.b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.b.getBackground()).setColor(xj.d(colorStateList));
            } else {
                if (z || !(this.b.getBackground() instanceof wj)) {
                    return;
                }
                ((wj) this.b.getBackground()).setTintList(xj.d(colorStateList));
            }
        }
    }

    public void u(mk mkVar) {
        this.c = mkVar;
        A(mkVar);
    }

    public void v(boolean z) {
        this.o = z;
        C();
    }

    public void w(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            C();
        }
    }

    public void x(int i) {
        if (this.i != i) {
            this.i = i;
            C();
        }
    }

    public void y(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            if (d() != null) {
                y7.o(d(), this.k);
            }
        }
    }

    public void z(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            if (d() == null || this.j == null) {
                return;
            }
            y7.p(d(), this.j);
        }
    }
}
