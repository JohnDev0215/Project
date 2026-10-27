package Z4;

import F1.A;
import F1.AbstractC0265y;
import F1.H;
import U4.k;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import b5.AbstractC0997a;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends FrameLayout {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final c f10897q = new c();
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f10898m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f10899n;
    public ColorStateList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public PorterDuff.Mode f10900p;

    public d(Context context, AttributeSet attributeSet) {
        super(AbstractC0997a.a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, K4.a.f3599s);
        if (typedArrayObtainStyledAttributes.hasValue(6)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
            int[] iArr = H.f2303a;
            A.j(this, dimensionPixelSize);
        }
        this.l = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.f10898m = typedArrayObtainStyledAttributes.getFloat(3, 1.0f);
        setBackgroundTintList(com.bumptech.glide.c.Z(context2, typedArrayObtainStyledAttributes, 4));
        setBackgroundTintMode(k.f(typedArrayObtainStyledAttributes.getInt(5, -1), PorterDuff.Mode.SRC_IN));
        this.f10899n = typedArrayObtainStyledAttributes.getFloat(1, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f10897q);
        setFocusable(true);
        if (getBackground() == null) {
            float dimension = getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(com.bumptech.glide.c.q0(com.bumptech.glide.c.Y(this, R.attr.colorSurface), com.bumptech.glide.c.Y(this, R.attr.colorOnSurface), getBackgroundOverlayColorAlpha()));
            ColorStateList colorStateList = this.o;
            if (colorStateList != null) {
                gradientDrawable.setTintList(colorStateList);
            }
            int[] iArr2 = H.f2303a;
            setBackground(gradientDrawable);
        }
    }

    public float getActionTextColorAlpha() {
        return this.f10899n;
    }

    public int getAnimationMode() {
        return this.l;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f10898m;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int[] iArr = H.f2303a;
        AbstractC0265y.b(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
    }

    public void setAnimationMode(int i7) {
        this.l = i7;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.o != null) {
            drawable = drawable.mutate();
            drawable.setTintList(this.o);
            drawable.setTintMode(this.f10900p);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.o = colorStateList;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            drawableMutate.setTintList(colorStateList);
            drawableMutate.setTintMode(this.f10900p);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f10900p = mode;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            drawableMutate.setTintMode(mode);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    public void setOnAttachStateChangeListener(a aVar) {
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f10897q);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(b bVar) {
    }
}
