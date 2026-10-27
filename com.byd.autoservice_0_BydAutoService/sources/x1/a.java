package x1;

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
import k2.c;
import l0.t;
import l2.b;
import n2.h;
import n2.m;
import n2.p;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f5721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MaterialButton f5722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f5723c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5724d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5725e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5726f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5727g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5728h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5729i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public PorterDuff.Mode f5730j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f5731k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f5732l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f5733m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f5734n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5735o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5736p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5737q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5738r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LayerDrawable f5739s;

    static {
        f5721a = Build.VERSION.SDK_INT >= 21;
    }

    public a(MaterialButton materialButton, m mVar) {
        this.f5722b = materialButton;
        this.f5723c = mVar;
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

    public void B(int i3, int i4) {
        Drawable drawable = this.f5734n;
        if (drawable != null) {
            drawable.setBounds(this.f5724d, this.f5726f, i4 - this.f5725e, i3 - this.f5727g);
        }
    }

    public final void C() {
        h hVarD = d();
        h hVarL = l();
        if (hVarD != null) {
            hVarD.h0(this.f5729i, this.f5732l);
            if (hVarL != null) {
                hVarL.g0(this.f5729i, this.f5735o ? c2.a.c(this.f5722b, R$attr.colorSurface) : 0);
            }
        }
    }

    public final InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f5724d, this.f5726f, this.f5725e, this.f5727g);
    }

    public final Drawable a() {
        h hVar = new h(this.f5723c);
        hVar.N(this.f5722b.getContext());
        e0.a.o(hVar, this.f5731k);
        PorterDuff.Mode mode = this.f5730j;
        if (mode != null) {
            e0.a.p(hVar, mode);
        }
        hVar.h0(this.f5729i, this.f5732l);
        h hVar2 = new h(this.f5723c);
        hVar2.setTint(0);
        hVar2.g0(this.f5729i, this.f5735o ? c2.a.c(this.f5722b, R$attr.colorSurface) : 0);
        if (f5721a) {
            h hVar3 = new h(this.f5723c);
            this.f5734n = hVar3;
            e0.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(b.d(this.f5733m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.f5734n);
            this.f5739s = rippleDrawable;
            return rippleDrawable;
        }
        l2.a aVar = new l2.a(this.f5723c);
        this.f5734n = aVar;
        e0.a.o(aVar, b.d(this.f5733m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.f5734n});
        this.f5739s = layerDrawable;
        return D(layerDrawable);
    }

    public int b() {
        return this.f5728h;
    }

    public p c() {
        LayerDrawable layerDrawable = this.f5739s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f5739s.getNumberOfLayers() > 2 ? (p) this.f5739s.getDrawable(2) : (p) this.f5739s.getDrawable(1);
    }

    public h d() {
        return e(false);
    }

    public final h e(boolean z3) {
        LayerDrawable layerDrawable = this.f5739s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f5721a ? (h) ((LayerDrawable) ((InsetDrawable) this.f5739s.getDrawable(0)).getDrawable()).getDrawable(!z3 ? 1 : 0) : (h) this.f5739s.getDrawable(!z3 ? 1 : 0);
    }

    public ColorStateList f() {
        return this.f5733m;
    }

    public m g() {
        return this.f5723c;
    }

    public ColorStateList h() {
        return this.f5732l;
    }

    public int i() {
        return this.f5729i;
    }

    public ColorStateList j() {
        return this.f5731k;
    }

    public PorterDuff.Mode k() {
        return this.f5730j;
    }

    public final h l() {
        return e(true);
    }

    public boolean m() {
        return this.f5736p;
    }

    public boolean n() {
        return this.f5738r;
    }

    public void o(TypedArray typedArray) {
        this.f5724d = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        this.f5725e = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        this.f5726f = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        this.f5727g = typedArray.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        int i3 = R$styleable.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i3)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i3, -1);
            this.f5728h = dimensionPixelSize;
            u(this.f5723c.w(dimensionPixelSize));
            this.f5737q = true;
        }
        this.f5729i = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        this.f5730j = i2.p.i(typedArray.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f5731k = c.a(this.f5722b.getContext(), typedArray, R$styleable.MaterialButton_backgroundTint);
        this.f5732l = c.a(this.f5722b.getContext(), typedArray, R$styleable.MaterialButton_strokeColor);
        this.f5733m = c.a(this.f5722b.getContext(), typedArray, R$styleable.MaterialButton_rippleColor);
        this.f5738r = typedArray.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        int iE = t.E(this.f5722b);
        int paddingTop = this.f5722b.getPaddingTop();
        int iD = t.D(this.f5722b);
        int paddingBottom = this.f5722b.getPaddingBottom();
        if (typedArray.hasValue(R$styleable.MaterialButton_android_background)) {
            q();
        } else {
            this.f5722b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.w0(this.f5722b, iE + this.f5724d, paddingTop + this.f5726f, iD + this.f5725e, paddingBottom + this.f5727g);
    }

    public void p(int i3) {
        if (d() != null) {
            d().setTint(i3);
        }
    }

    public void q() {
        this.f5736p = true;
        this.f5722b.setSupportBackgroundTintList(this.f5731k);
        this.f5722b.setSupportBackgroundTintMode(this.f5730j);
    }

    public void r(boolean z3) {
        this.f5738r = z3;
    }

    public void s(int i3) {
        if (this.f5737q && this.f5728h == i3) {
            return;
        }
        this.f5728h = i3;
        this.f5737q = true;
        u(this.f5723c.w(i3));
    }

    public void t(ColorStateList colorStateList) {
        if (this.f5733m != colorStateList) {
            this.f5733m = colorStateList;
            boolean z3 = f5721a;
            if (z3 && (this.f5722b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f5722b.getBackground()).setColor(b.d(colorStateList));
            } else {
                if (z3 || !(this.f5722b.getBackground() instanceof l2.a)) {
                    return;
                }
                ((l2.a) this.f5722b.getBackground()).setTintList(b.d(colorStateList));
            }
        }
    }

    public void u(m mVar) {
        this.f5723c = mVar;
        A(mVar);
    }

    public void v(boolean z3) {
        this.f5735o = z3;
        C();
    }

    public void w(ColorStateList colorStateList) {
        if (this.f5732l != colorStateList) {
            this.f5732l = colorStateList;
            C();
        }
    }

    public void x(int i3) {
        if (this.f5729i != i3) {
            this.f5729i = i3;
            C();
        }
    }

    public void y(ColorStateList colorStateList) {
        if (this.f5731k != colorStateList) {
            this.f5731k = colorStateList;
            if (d() != null) {
                e0.a.o(d(), this.f5731k);
            }
        }
    }

    public void z(PorterDuff.Mode mode) {
        if (this.f5730j != mode) {
            this.f5730j = mode;
            if (d() == null || this.f5730j == null) {
                return;
            }
            e0.a.p(d(), this.f5730j);
        }
    }
}
