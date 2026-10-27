package c.b.a.a.f;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import b.g.j.t;
import c.b.a.a.s.c;
import c.b.a.a.t.b;
import c.b.a.a.v.h;
import c.b.a.a.v.m;
import c.b.a.a.v.p;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1687a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MaterialButton f1688b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f1689c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1690d;
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
        f1687a = Build.VERSION.SDK_INT >= 21;
    }

    public a(MaterialButton materialButton, m mVar) {
        this.f1688b = materialButton;
        this.f1689c = mVar;
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

    public void B(int i, int i2) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f1690d, this.f, i2 - this.e, i - this.g);
        }
    }

    public final void C() {
        h hVarD = d();
        h hVarL = l();
        if (hVarD != null) {
            hVarD.h0(this.i, this.l);
            if (hVarL != null) {
                hVarL.g0(this.i, this.o ? c.b.a.a.k.a.c(this.f1688b, R$attr.colorSurface) : 0);
            }
        }
    }

    public final InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f1690d, this.f, this.e, this.g);
    }

    public final Drawable a() {
        h hVar = new h(this.f1689c);
        hVar.N(this.f1688b.getContext());
        b.g.c.l.a.o(hVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            b.g.c.l.a.p(hVar, mode);
        }
        hVar.h0(this.i, this.l);
        h hVar2 = new h(this.f1689c);
        hVar2.setTint(0);
        hVar2.g0(this.i, this.o ? c.b.a.a.k.a.c(this.f1688b, R$attr.colorSurface) : 0);
        if (f1687a) {
            h hVar3 = new h(this.f1689c);
            this.n = hVar3;
            b.g.c.l.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(b.d(this.m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        c.b.a.a.t.a aVar = new c.b.a.a.t.a(this.f1689c);
        this.n = aVar;
        b.g.c.l.a.o(aVar, b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    public int b() {
        return this.h;
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
        return f1687a ? (h) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (h) this.s.getDrawable(!z ? 1 : 0);
    }

    public ColorStateList f() {
        return this.m;
    }

    public m g() {
        return this.f1689c;
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
        this.f1690d = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        this.e = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        this.f = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        this.g = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        int i = R$styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.h = dimensionPixelSize;
            u(this.f1689c.w(dimensionPixelSize));
            this.q = true;
        }
        this.i = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        this.j = c.b.a.a.q.p.i(typedArray.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f1688b.getContext(), typedArray, R$styleable.MaterialButton_backgroundTint);
        this.l = c.a(this.f1688b.getContext(), typedArray, R$styleable.MaterialButton_strokeColor);
        this.m = c.a(this.f1688b.getContext(), typedArray, R$styleable.MaterialButton_rippleColor);
        this.r = typedArray.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        int iE = t.E(this.f1688b);
        int paddingTop = this.f1688b.getPaddingTop();
        int iD = t.D(this.f1688b);
        int paddingBottom = this.f1688b.getPaddingBottom();
        if (typedArray.hasValue(R$styleable.MaterialButton_android_background)) {
            q();
        } else {
            this.f1688b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.w0(this.f1688b, iE + this.f1690d, paddingTop + this.f, iD + this.e, paddingBottom + this.g);
    }

    public void p(int i) {
        if (d() != null) {
            d().setTint(i);
        }
    }

    public void q() {
        this.p = true;
        this.f1688b.setSupportBackgroundTintList(this.k);
        this.f1688b.setSupportBackgroundTintMode(this.j);
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
        u(this.f1689c.w(i));
    }

    public void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f1687a;
            if (z && (this.f1688b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f1688b.getBackground()).setColor(b.d(colorStateList));
            } else {
                if (z || !(this.f1688b.getBackground() instanceof c.b.a.a.t.a)) {
                    return;
                }
                ((c.b.a.a.t.a) this.f1688b.getBackground()).setTintList(b.d(colorStateList));
            }
        }
    }

    public void u(m mVar) {
        this.f1689c = mVar;
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
                b.g.c.l.a.o(d(), this.k);
            }
        }
    }

    public void z(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            if (d() == null || this.j == null) {
                return;
            }
            b.g.c.l.a.p(d(), this.j);
        }
    }
}
