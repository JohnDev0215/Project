package defpackage;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.cardview.widget.CardView;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;
import defpackage.om3;
import defpackage.uj2;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class ic1 {
    public static final double t = Math.cos(Math.toRadians(45.0d));
    public final MaterialCardView a;
    public final Rect b;
    public final sc1 c;
    public final sc1 d;
    public int e;
    public int f;
    public int g;
    public int h;
    public Drawable i;
    public Drawable j;
    public ColorStateList k;
    public ColorStateList l;
    public uj2 m;
    public ColorStateList n;
    public RippleDrawable o;
    public LayerDrawable p;
    public sc1 q;
    public boolean r;
    public boolean s;

    public ic1(MaterialCardView materialCardView, AttributeSet attributeSet, int i) {
        int i2 = MaterialCardView.s;
        this.b = new Rect();
        this.r = false;
        this.a = materialCardView;
        sc1 sc1Var = new sc1(materialCardView.getContext(), attributeSet, i, i2);
        this.c = sc1Var;
        sc1Var.k(materialCardView.getContext());
        sc1Var.q();
        uj2 uj2Var = sc1Var.e.a;
        uj2Var.getClass();
        uj2.a aVar = new uj2.a(uj2Var);
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        int i3 = R$styleable.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            aVar.c(typedArrayObtainStyledAttributes.getDimension(i3, 0.0f));
        }
        this.d = new sc1();
        g(new uj2(aVar));
        typedArrayObtainStyledAttributes.recycle();
    }

    public static float b(d70 d70Var, float f) {
        if (d70Var instanceof tc2) {
            return (float) ((1.0d - t) * ((double) f));
        }
        if (d70Var instanceof q50) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float a() {
        d70 d70Var = this.m.a;
        sc1 sc1Var = this.c;
        return Math.max(Math.max(b(d70Var, sc1Var.j()), b(this.m.b, sc1Var.e.a.f.a(sc1Var.h()))), Math.max(b(this.m.c, sc1Var.e.a.g.a(sc1Var.h())), b(this.m.d, sc1Var.e.a.h.a(sc1Var.h()))));
    }

    public final LayerDrawable c() {
        if (this.o == null) {
            this.q = new sc1(this.m);
            this.o = new RippleDrawable(this.k, null, this.q);
        }
        if (this.p == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.o, this.d, this.j});
            this.p = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.p;
    }

    public final hc1 d(Drawable drawable) {
        int iCeil;
        int i;
        if (this.a.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil((r0.getMaxCardElevation() * 1.5f) + (h() ? a() : 0.0f));
            iCeil = (int) Math.ceil(r0.getMaxCardElevation() + (h() ? a() : 0.0f));
            i = iCeil2;
        } else {
            iCeil = 0;
            i = 0;
        }
        return new hc1(drawable, iCeil, i, iCeil, i);
    }

    public final void e(int i, int i2) {
        int iCeil;
        int iCeil2;
        int i3;
        int i4;
        if (this.p != null) {
            MaterialCardView materialCardView = this.a;
            if (materialCardView.getUseCompatPadding()) {
                iCeil = (int) Math.ceil(((materialCardView.getMaxCardElevation() * 1.5f) + (h() ? a() : 0.0f)) * 2.0f);
                iCeil2 = (int) Math.ceil((materialCardView.getMaxCardElevation() + (h() ? a() : 0.0f)) * 2.0f);
            } else {
                iCeil = 0;
                iCeil2 = 0;
            }
            int i5 = this.g;
            int i6 = (i5 & 8388613) == 8388613 ? ((i - this.e) - this.f) - iCeil2 : this.e;
            int i7 = (i5 & 80) == 80 ? this.e : ((i2 - this.e) - this.f) - iCeil;
            int i8 = (i5 & 8388613) == 8388613 ? this.e : ((i - this.e) - this.f) - iCeil2;
            int i9 = (i5 & 80) == 80 ? ((i2 - this.e) - this.f) - iCeil : this.e;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.e.c(materialCardView) == 1) {
                i4 = i8;
                i3 = i6;
            } else {
                i3 = i8;
                i4 = i6;
            }
            this.p.setLayerInset(2, i4, i9, i3, i7);
        }
    }

    public final void f(Drawable drawable) {
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.j = drawableMutate;
            drawableMutate.setTintList(this.l);
            boolean zIsChecked = this.a.isChecked();
            Drawable drawable2 = this.j;
            if (drawable2 != null) {
                drawable2.setAlpha(zIsChecked ? 255 : 0);
            }
        } else {
            this.j = null;
        }
        LayerDrawable layerDrawable = this.p;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, this.j);
        }
    }

    public final void g(uj2 uj2Var) {
        this.m = uj2Var;
        sc1 sc1Var = this.c;
        sc1Var.setShapeAppearanceModel(uj2Var);
        sc1Var.z = !sc1Var.l();
        sc1 sc1Var2 = this.d;
        if (sc1Var2 != null) {
            sc1Var2.setShapeAppearanceModel(uj2Var);
        }
        sc1 sc1Var3 = this.q;
        if (sc1Var3 != null) {
            sc1Var3.setShapeAppearanceModel(uj2Var);
        }
    }

    public final boolean h() {
        MaterialCardView materialCardView = this.a;
        return materialCardView.getPreventCornerOverlap() && this.c.l() && materialCardView.getUseCompatPadding();
    }

    public final void i() {
        MaterialCardView materialCardView = this.a;
        boolean z = true;
        if (!(materialCardView.getPreventCornerOverlap() && !this.c.l()) && !h()) {
            z = false;
        }
        float cardViewRadius = 0.0f;
        float fA = z ? a() : 0.0f;
        if (materialCardView.getPreventCornerOverlap() && materialCardView.getUseCompatPadding()) {
            cardViewRadius = (float) ((1.0d - t) * ((double) materialCardView.getCardViewRadius()));
        }
        int i = (int) (fA - cardViewRadius);
        Rect rect = this.b;
        materialCardView.g.set(rect.left + i, rect.top + i, rect.right + i, rect.bottom + i);
        CardView.k.j0(materialCardView.i);
    }

    public final void j() {
        boolean z = this.r;
        MaterialCardView materialCardView = this.a;
        if (!z) {
            materialCardView.setBackgroundInternal(d(this.c));
        }
        materialCardView.setForeground(d(this.i));
    }
}
