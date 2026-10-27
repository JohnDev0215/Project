package b.d.a.a.f0;

import a.b.k.l;
import a.h.k.q;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import b.d.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class d extends FrameLayout {
    public static final View.OnTouchListener h = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f1883a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f1884b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1885c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f1886d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f1887e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f1888f;
    public PorterDuff.Mode g;

    public static class a implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public d(Context context, AttributeSet attributeSet) {
        super(b.d.a.a.i0.a.a.a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, k.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(k.SnackbarLayout_elevation)) {
            q.Z(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(k.SnackbarLayout_elevation, 0));
        }
        this.f1885c = typedArrayObtainStyledAttributes.getInt(k.SnackbarLayout_animationMode, 0);
        this.f1886d = typedArrayObtainStyledAttributes.getFloat(k.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(l.i.y(context2, typedArrayObtainStyledAttributes, k.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(l.i.e0(typedArrayObtainStyledAttributes.getInt(k.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f1887e = typedArrayObtainStyledAttributes.getFloat(k.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(h);
        setFocusable(true);
        if (getBackground() == null) {
            float dimension = getResources().getDimension(b.d.a.a.d.mtrl_snackbar_background_corner_radius);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(dimension);
            gradientDrawable.setColor(l.i.R(l.i.x(this, b.d.a.a.b.colorSurface), l.i.x(this, b.d.a.a.b.colorOnSurface), getBackgroundOverlayColorAlpha()));
            ColorStateList colorStateList = this.f1888f;
            if (colorStateList != null) {
                gradientDrawable.setTintList(colorStateList);
            }
            q.X(this, gradientDrawable);
        }
    }

    public float getActionTextColorAlpha() {
        return this.f1887e;
    }

    public int getAnimationMode() {
        return this.f1885c;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f1886d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f1884b;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        q.S(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f1884b;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c cVar = this.f1883a;
        if (cVar != null) {
            cVar.a(this, i, i2, i3, i4);
        }
    }

    public void setAnimationMode(int i) {
        this.f1885c = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f1888f != null) {
            drawable = drawable.mutate();
            drawable.setTintList(this.f1888f);
            drawable.setTintMode(this.g);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f1888f = colorStateList;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            drawableMutate.setTintList(colorStateList);
            drawableMutate.setTintMode(this.g);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.g = mode;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            drawableMutate.setTintMode(mode);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    public void setOnAttachStateChangeListener(b bVar) {
        this.f1884b = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : h);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(c cVar) {
        this.f1883a = cVar;
    }
}
