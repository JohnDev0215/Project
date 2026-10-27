package Q4;

import F1.H;
import Y4.g;
import Y4.k;
import Y4.u;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import com.google.android.material.button.MaterialButton;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialButton f6223a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f6224b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6226f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f6228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f6229i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f6230j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f6231k;
    public ColorStateList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g f6232m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6233n = false;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6234p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f6235q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public RippleDrawable f6236r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f6237s;

    public c(MaterialButton materialButton, k kVar) {
        this.f6223a = materialButton;
        this.f6224b = kVar;
    }

    public final u a() {
        RippleDrawable rippleDrawable = this.f6236r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f6236r.getNumberOfLayers() > 2 ? (u) this.f6236r.getDrawable(2) : (u) this.f6236r.getDrawable(1);
    }

    public final g b(boolean z6) {
        RippleDrawable rippleDrawable = this.f6236r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (g) ((LayerDrawable) ((InsetDrawable) this.f6236r.getDrawable(0)).getDrawable()).getDrawable(!z6 ? 1 : 0);
    }

    public final void c(k kVar) {
        this.f6224b = kVar;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(kVar);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(kVar);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(kVar);
        }
    }

    public final void d(int i7, int i10) {
        int[] iArr = H.f2303a;
        MaterialButton materialButton = this.f6223a;
        int paddingStart = materialButton.getPaddingStart();
        int paddingTop = materialButton.getPaddingTop();
        int paddingEnd = materialButton.getPaddingEnd();
        int paddingBottom = materialButton.getPaddingBottom();
        int i11 = this.f6225e;
        int i12 = this.f6226f;
        this.f6226f = i10;
        this.f6225e = i7;
        if (!this.o) {
            e();
        }
        materialButton.setPaddingRelative(paddingStart, (paddingTop + i7) - i11, paddingEnd, (paddingBottom + i10) - i12);
    }

    public final void e() {
        g gVar = new g(this.f6224b);
        MaterialButton materialButton = this.f6223a;
        gVar.f(materialButton.getContext());
        gVar.setTintList(this.f6230j);
        PorterDuff.Mode mode = this.f6229i;
        if (mode != null) {
            gVar.setTintMode(mode);
        }
        float f10 = this.f6228h;
        ColorStateList colorStateList = this.f6231k;
        gVar.l.f10715j = f10;
        gVar.invalidateSelf();
        Y4.f fVar = gVar.l;
        if (fVar.d != colorStateList) {
            fVar.d = colorStateList;
            gVar.onStateChange(gVar.getState());
        }
        g gVar2 = new g(this.f6224b);
        gVar2.setTint(0);
        float f11 = this.f6228h;
        int iY = this.f6233n ? com.bumptech.glide.c.Y(materialButton, R.attr.colorSurface) : 0;
        gVar2.l.f10715j = f11;
        gVar2.invalidateSelf();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iY);
        Y4.f fVar2 = gVar2.l;
        if (fVar2.d != colorStateListValueOf) {
            fVar2.d = colorStateListValueOf;
            gVar2.onStateChange(gVar2.getState());
        }
        g gVar3 = new g(this.f6224b);
        this.f6232m = gVar3;
        gVar3.setTint(-1);
        ColorStateList colorStateListValueOf2 = this.l;
        if (colorStateListValueOf2 == null) {
            colorStateListValueOf2 = ColorStateList.valueOf(0);
        }
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateListValueOf2, new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{gVar2, gVar}), this.c, this.f6225e, this.d, this.f6226f), this.f6232m);
        this.f6236r = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        g gVarB = b(false);
        if (gVarB != null) {
            gVarB.g(this.f6237s);
        }
    }

    public final void f() {
        g gVarB = b(false);
        g gVarB2 = b(true);
        if (gVarB != null) {
            float f10 = this.f6228h;
            ColorStateList colorStateList = this.f6231k;
            gVarB.l.f10715j = f10;
            gVarB.invalidateSelf();
            Y4.f fVar = gVarB.l;
            if (fVar.d != colorStateList) {
                fVar.d = colorStateList;
                gVarB.onStateChange(gVarB.getState());
            }
            if (gVarB2 != null) {
                float f11 = this.f6228h;
                int iY = this.f6233n ? com.bumptech.glide.c.Y(this.f6223a, R.attr.colorSurface) : 0;
                gVarB2.l.f10715j = f11;
                gVarB2.invalidateSelf();
                ColorStateList colorStateListValueOf = ColorStateList.valueOf(iY);
                Y4.f fVar2 = gVarB2.l;
                if (fVar2.d != colorStateListValueOf) {
                    fVar2.d = colorStateListValueOf;
                    gVarB2.onStateChange(gVarB2.getState());
                }
            }
        }
    }
}
