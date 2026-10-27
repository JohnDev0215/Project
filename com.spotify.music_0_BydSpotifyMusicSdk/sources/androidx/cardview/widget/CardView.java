package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import p.o52;
import p.rz1;
import p.u11;
import p.ww1;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {
    public static final int[] n = {R.attr.colorBackground};
    public static final u11 o = new u11();
    public boolean c;
    public boolean e;
    public final Rect k;
    public final Rect l;
    public final ww1 m;

    public CardView(Context context, AttributeSet attributeSet) {
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, com.spotify.music.R.attr.cardViewStyle);
        Rect rect = new Rect();
        this.k = rect;
        this.l = new Rect();
        ww1 ww1Var = new ww1(1, this);
        this.m = ww1Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, rz1.a, com.spotify.music.R.attr.cardViewStyle, com.spotify.music.R.style.CardView);
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(2);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(n);
            int color = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color, fArr);
            colorStateListValueOf = ColorStateList.valueOf(fArr[2] > 0.5f ? getResources().getColor(com.spotify.music.R.color.cardview_light_background) : getResources().getColor(com.spotify.music.R.color.cardview_dark_background));
        }
        float dimension = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
        this.c = typedArrayObtainStyledAttributes.getBoolean(7, false);
        this.e = typedArrayObtainStyledAttributes.getBoolean(6, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, dimensionPixelSize);
        dimension3 = dimension2 > dimension3 ? dimension2 : dimension3;
        typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        u11 u11Var = o;
        o52 o52Var = new o52(dimension, colorStateListValueOf);
        ww1Var.e = o52Var;
        ((CardView) ww1Var.k).setBackgroundDrawable(o52Var);
        CardView cardView = (CardView) ww1Var.k;
        cardView.setClipToOutline(true);
        cardView.setElevation(dimension2);
        u11Var.J(ww1Var, dimension3);
    }

    public ColorStateList getCardBackgroundColor() {
        return ((o52) ((Drawable) this.m.e)).h;
    }

    public float getCardElevation() {
        return ((CardView) this.m.k).getElevation();
    }

    public int getContentPaddingBottom() {
        return this.k.bottom;
    }

    public int getContentPaddingLeft() {
        return this.k.left;
    }

    public int getContentPaddingRight() {
        return this.k.right;
    }

    public int getContentPaddingTop() {
        return this.k.top;
    }

    public float getMaxCardElevation() {
        return ((o52) ((Drawable) this.m.e)).e;
    }

    public boolean getPreventCornerOverlap() {
        return this.e;
    }

    public float getRadius() {
        return ((o52) ((Drawable) this.m.e)).a;
    }

    public boolean getUseCompatPadding() {
        return this.c;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(i);
        o52 o52Var = (o52) ((Drawable) this.m.e);
        if (colorStateListValueOf == null) {
            o52Var.getClass();
            colorStateListValueOf = ColorStateList.valueOf(0);
        }
        o52Var.h = colorStateListValueOf;
        o52Var.b.setColor(colorStateListValueOf.getColorForState(o52Var.getState(), o52Var.h.getDefaultColor()));
        o52Var.invalidateSelf();
    }

    public void setCardElevation(float f) {
        ((CardView) this.m.k).setElevation(f);
    }

    public void setMaxCardElevation(float f) {
        o.J(this.m, f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.e) {
            this.e = z;
            u11 u11Var = o;
            ww1 ww1Var = this.m;
            u11Var.J(ww1Var, ((o52) ((Drawable) ww1Var.e)).e);
        }
    }

    public void setRadius(float f) {
        o52 o52Var = (o52) ((Drawable) this.m.e);
        if (f == o52Var.a) {
            return;
        }
        o52Var.a = f;
        o52Var.b(null);
        o52Var.invalidateSelf();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.c != z) {
            this.c = z;
            u11 u11Var = o;
            ww1 ww1Var = this.m;
            u11Var.J(ww1Var, ((o52) ((Drawable) ww1Var.e)).e);
        }
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        o52 o52Var = (o52) ((Drawable) this.m.e);
        if (colorStateList == null) {
            o52Var.getClass();
            colorStateList = ColorStateList.valueOf(0);
        }
        o52Var.h = colorStateList;
        o52Var.b.setColor(colorStateList.getColorForState(o52Var.getState(), o52Var.h.getDefaultColor()));
        o52Var.invalidateSelf();
    }
}
