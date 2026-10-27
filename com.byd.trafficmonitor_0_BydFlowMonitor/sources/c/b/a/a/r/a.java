package c.b.a.a.r;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import b.h.m.t;
import c.b.a.a.b;
import c.b.a.a.f0.c;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.i0.p;
import c.b.a.a.l;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f4064a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MaterialButton f4065b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f4066c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4067d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4068e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4069f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4070g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4071h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4072i;
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
        f4064a = Build.VERSION.SDK_INT >= 21;
    }

    public a(MaterialButton materialButton, m mVar) {
        this.f4065b = materialButton;
        this.f4066c = mVar;
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

    public void B(int i2, int i3) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f4067d, this.f4069f, i3 - this.f4068e, i2 - this.f4070g);
        }
    }

    public final void C() {
        h hVarD = d();
        h hVarL = l();
        if (hVarD != null) {
            hVarD.h0(this.f4072i, this.l);
            if (hVarL != null) {
                hVarL.g0(this.f4072i, this.o ? c.b.a.a.w.a.c(this.f4065b, b.colorSurface) : 0);
            }
        }
    }

    public final InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f4067d, this.f4069f, this.f4068e, this.f4070g);
    }

    public final Drawable a() {
        h hVar = new h(this.f4066c);
        hVar.N(this.f4065b.getContext());
        b.h.f.l.a.o(hVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            b.h.f.l.a.p(hVar, mode);
        }
        hVar.h0(this.f4072i, this.l);
        h hVar2 = new h(this.f4066c);
        hVar2.setTint(0);
        hVar2.g0(this.f4072i, this.o ? c.b.a.a.w.a.c(this.f4065b, b.colorSurface) : 0);
        if (f4064a) {
            h hVar3 = new h(this.f4066c);
            this.n = hVar3;
            b.h.f.l.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(c.b.a.a.g0.b.d(this.m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        c.b.a.a.g0.a aVar = new c.b.a.a.g0.a(this.f4066c);
        this.n = aVar;
        b.h.f.l.a.o(aVar, c.b.a.a.g0.b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    public int b() {
        return this.f4071h;
    }

    public p c() {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.s.getNumberOfLayers() > 2 ? (p) this.s.getDrawable(2) : (p) this.s.getDrawable(1);
    }

    public h d() {
        return e(false);
    }

    public final h e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f4064a ? (h) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (h) this.s.getDrawable(!z ? 1 : 0);
    }

    public ColorStateList f() {
        return this.m;
    }

    public m g() {
        return this.f4066c;
    }

    public ColorStateList h() {
        return this.l;
    }

    public int i() {
        return this.f4072i;
    }

    public ColorStateList j() {
        return this.k;
    }

    public PorterDuff.Mode k() {
        return this.j;
    }

    public final h l() {
        return e(true);
    }

    public boolean m() {
        return this.p;
    }

    public boolean n() {
        return this.r;
    }

    public void o(TypedArray typedArray) {
        this.f4067d = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetLeft, 0);
        this.f4068e = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetRight, 0);
        this.f4069f = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetTop, 0);
        this.f4070g = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetBottom, 0);
        int i2 = l.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i2)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i2, -1);
            this.f4071h = dimensionPixelSize;
            u(this.f4066c.w(dimensionPixelSize));
            this.q = true;
        }
        this.f4072i = typedArray.getDimensionPixelSize(l.MaterialButton_strokeWidth, 0);
        this.j = c.b.a.a.d0.p.i(typedArray.getInt(l.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f4065b.getContext(), typedArray, l.MaterialButton_backgroundTint);
        this.l = c.a(this.f4065b.getContext(), typedArray, l.MaterialButton_strokeColor);
        this.m = c.a(this.f4065b.getContext(), typedArray, l.MaterialButton_rippleColor);
        this.r = typedArray.getBoolean(l.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(l.MaterialButton_elevation, 0);
        int iE = t.E(this.f4065b);
        int paddingTop = this.f4065b.getPaddingTop();
        int iD = t.D(this.f4065b);
        int paddingBottom = this.f4065b.getPaddingBottom();
        if (typedArray.hasValue(l.MaterialButton_android_background)) {
            q();
        } else {
            this.f4065b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.y0(this.f4065b, iE + this.f4067d, paddingTop + this.f4069f, iD + this.f4068e, paddingBottom + this.f4070g);
    }

    public void p(int i2) {
        if (d() != null) {
            d().setTint(i2);
        }
    }

    public void q() {
        this.p = true;
        this.f4065b.setSupportBackgroundTintList(this.k);
        this.f4065b.setSupportBackgroundTintMode(this.j);
    }

    public void r(boolean z) {
        this.r = z;
    }

    public void s(int i2) {
        if (this.q && this.f4071h == i2) {
            return;
        }
        this.f4071h = i2;
        this.q = true;
        u(this.f4066c.w(i2));
    }

    public void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f4064a;
            if (z && (this.f4065b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f4065b.getBackground()).setColor(c.b.a.a.g0.b.d(colorStateList));
            } else {
                if (z || !(this.f4065b.getBackground() instanceof c.b.a.a.g0.a)) {
                    return;
                }
                ((c.b.a.a.g0.a) this.f4065b.getBackground()).setTintList(c.b.a.a.g0.b.d(colorStateList));
            }
        }
    }

    public void u(m mVar) {
        this.f4066c = mVar;
        A(mVar);
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

    public void x(int i2) {
        if (this.f4072i != i2) {
            this.f4072i = i2;
            C();
        }
    }

    public void y(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            if (d() != null) {
                b.h.f.l.a.o(d(), this.k);
            }
        }
    }

    public void z(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            if (d() == null || this.j == null) {
                return;
            }
            b.h.f.l.a.p(d(), this.j);
        }
    }
}
