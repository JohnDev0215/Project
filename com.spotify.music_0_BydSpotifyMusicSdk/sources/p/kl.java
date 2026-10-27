package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class kl extends FrameLayout {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final jl f144p = new jl();
    public int c;
    public final float e;
    public final float k;
    public final int l;
    public final int m;
    public ColorStateList n;
    public PorterDuff.Mode o;

    public kl(Context context, AttributeSet attributeSet) {
        super(f60.S(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, xz1.x);
        if (typedArrayObtainStyledAttributes.hasValue(6)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
            WeakHashMap weakHashMap = zt2.a;
            ot2.s(this, dimensionPixelSize);
        }
        this.c = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.e = typedArrayObtainStyledAttributes.getFloat(3, 1.0f);
        setBackgroundTintList(ci1.v(context2, typedArrayObtainStyledAttributes, 4));
        setBackgroundTintMode(f60.B(typedArrayObtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
        this.k = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, -1);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f144p);
        setFocusable(true);
        if (getBackground() == null) {
            float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(f60.x(f60.q(this, R.attr.colorSurface), f60.q(this, R.attr.colorOnSurface), getBackgroundOverlayColorAlpha()));
            ColorStateList colorStateList = this.n;
            if (colorStateList != null) {
                ya0.h(gradientDrawable, colorStateList);
            }
            WeakHashMap weakHashMap2 = zt2.a;
            it2.q(this, gradientDrawable);
        }
    }

    public float getActionTextColorAlpha() {
        return this.k;
    }

    public int getAnimationMode() {
        return this.c;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.e;
    }

    public int getMaxInlineActionWidth() {
        return this.m;
    }

    public int getMaxWidth() {
        return this.l;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.l;
        if (i3 <= 0 || getMeasuredWidth() <= i3) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
    }

    public void setAnimationMode(int i) {
        this.c = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.n != null) {
            drawable = drawable.mutate();
            ya0.h(drawable, this.n);
            ya0.i(drawable, this.o);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.n = colorStateList;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            ya0.h(drawableMutate, colorStateList);
            ya0.i(drawableMutate, this.o);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.o = mode;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            ya0.i(drawableMutate, mode);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    public void setOnAttachStateChangeListener(hl hlVar) {
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f144p);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(il ilVar) {
    }
}
