package b.d.a.a.q;

import a.b.k.l;
import a.h.k.q;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import b.d.a.a.b;
import b.d.a.a.e0.g;
import b.d.a.a.e0.j;
import b.d.a.a.e0.n;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialButton f2044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public j f2045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2049f;
    public int g;
    public int h;
    public PorterDuff.Mode i;
    public ColorStateList j;
    public ColorStateList k;
    public ColorStateList l;
    public Drawable m;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q;
    public LayerDrawable r;
    public int s;

    public a(MaterialButton materialButton, j jVar) {
        this.f2044a = materialButton;
        this.f2045b = jVar;
    }

    public n a() {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        int numberOfLayers = this.r.getNumberOfLayers();
        LayerDrawable layerDrawable2 = this.r;
        return (n) (numberOfLayers > 2 ? layerDrawable2.getDrawable(2) : layerDrawable2.getDrawable(1));
    }

    public g b() {
        return c(false);
    }

    public final g c(boolean z) {
        LayerDrawable layerDrawable = this.r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (g) ((LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public final g d() {
        return c(true);
    }

    public void e(j jVar) {
        this.f2045b = jVar;
        if (b() != null) {
            g gVarB = b();
            gVarB.f1828a.f1835a = jVar;
            gVarB.invalidateSelf();
        }
        if (d() != null) {
            g gVarD = d();
            gVarD.f1828a.f1835a = jVar;
            gVarD.invalidateSelf();
        }
        if (a() != null) {
            a().setShapeAppearanceModel(jVar);
        }
    }

    public final void f(int i, int i2) {
        int iT = q.t(this.f2044a);
        int paddingTop = this.f2044a.getPaddingTop();
        int paddingEnd = this.f2044a.getPaddingEnd();
        int paddingBottom = this.f2044a.getPaddingBottom();
        int i3 = this.f2048e;
        int i4 = this.f2049f;
        this.f2049f = i2;
        this.f2048e = i;
        if (!this.o) {
            g();
        }
        this.f2044a.setPaddingRelative(iT, (paddingTop + i) - i3, paddingEnd, (paddingBottom + i2) - i4);
    }

    public final void g() {
        MaterialButton materialButton = this.f2044a;
        g gVar = new g(this.f2045b);
        gVar.o(this.f2044a.getContext());
        gVar.setTintList(this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            gVar.setTintMode(mode);
        }
        gVar.t(this.h, this.k);
        g gVar2 = new g(this.f2045b);
        gVar2.setTint(0);
        gVar2.s(this.h, this.n ? l.i.x(this.f2044a, b.colorSurface) : 0);
        g gVar3 = new g(this.f2045b);
        this.m = gVar3;
        gVar3.setTint(-1);
        RippleDrawable rippleDrawable = new RippleDrawable(b.d.a.a.c0.a.a(this.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{gVar2, gVar}), this.f2046c, this.f2048e, this.f2047d, this.f2049f), this.m);
        this.r = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        g gVarB = b();
        if (gVarB != null) {
            gVarB.p(this.s);
        }
    }

    public final void h() {
        g gVarB = b();
        g gVarD = d();
        if (gVarB != null) {
            gVarB.t(this.h, this.k);
            if (gVarD != null) {
                gVarD.s(this.h, this.n ? l.i.x(this.f2044a, b.colorSurface) : 0);
            }
        }
    }
}
