package com.google.android.material.snackbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.vp2;
import defpackage.yn3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    public static final /* synthetic */ int a = 0;

    public static class Behavior extends SwipeDismissBehavior<View> {
        public final b h = new b(this);

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean g(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.h.getClass();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    synchronized (vp2.a().a) {
                    }
                }
            } else if (coordinatorLayout.u(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                synchronized (vp2.a().a) {
                }
            }
            return super.g(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public final boolean s(View view) {
            this.h.getClass();
            return view instanceof SnackbarBaseLayout;
        }
    }

    public static class SnackbarBaseLayout extends FrameLayout {
        public static final a n = new a();
        public BaseTransientBottomBar<?> e;
        public final uj2 f;
        public int g;
        public final float h;
        public final float i;
        public final int j;
        public final int k;
        public ColorStateList l;
        public PorterDuff.Mode m;

        public class a implements View.OnTouchListener {
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        }

        public SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            Drawable drawable;
            super(uc1.a(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
            int i = R$styleable.SnackbarLayout_elevation;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(i, 0);
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.i.l(this, dimensionPixelSize);
            }
            this.g = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearance) || typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearanceOverlay)) {
                this.f = new uj2(uj2.b(context2, attributeSet, 0, 0));
            }
            this.h = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(rc1.a(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(yn3.e(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.i = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_android_maxWidth, -1);
            this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_maxActionInlineWidth, -1);
            typedArrayObtainStyledAttributes.recycle();
            setOnTouchListener(n);
            setFocusable(true);
            if (getBackground() == null) {
                int iJ = oj2.J(oj2.p(this, R$attr.colorSurface), oj2.p(this, R$attr.colorOnSurface), getBackgroundOverlayColorAlpha());
                uj2 uj2Var = this.f;
                if (uj2Var != null) {
                    int i2 = BaseTransientBottomBar.a;
                    sc1 sc1Var = new sc1(uj2Var);
                    sc1Var.n(ColorStateList.valueOf(iJ));
                    drawable = sc1Var;
                } else {
                    Resources resources = getResources();
                    int i3 = BaseTransientBottomBar.a;
                    float dimension = resources.getDimension(R$dimen.mtrl_snackbar_background_corner_radius);
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setShape(0);
                    gradientDrawable.setCornerRadius(dimension);
                    gradientDrawable.setColor(iJ);
                    drawable = gradientDrawable;
                }
                ColorStateList colorStateList = this.l;
                if (colorStateList != null) {
                    drawable.setTintList(colorStateList);
                }
                WeakHashMap<View, in3> weakHashMap2 = om3.a;
                om3.d.n(this, drawable);
            }
        }

        private void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.e = baseTransientBottomBar;
        }

        public float getActionTextColorAlpha() {
            return this.i;
        }

        public int getAnimationMode() {
            return this.g;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.h;
        }

        public int getMaxInlineActionWidth() {
            return this.k;
        }

        public int getMaxWidth() {
            return this.j;
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.e;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.getClass();
                throw null;
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.h.c(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public final void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.e != null) {
                synchronized (vp2.a().a) {
                }
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            int i3 = this.j;
            if (i3 <= 0 || getMeasuredWidth() <= i3) {
                return;
            }
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), i2);
        }

        public void setAnimationMode(int i) {
            this.g = i;
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.l != null) {
                drawable = drawable.mutate();
                drawable.setTintList(this.l);
                drawable.setTintMode(this.m);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.l = colorStateList;
            if (getBackground() != null) {
                Drawable drawableMutate = getBackground().mutate();
                drawableMutate.setTintList(colorStateList);
                drawableMutate.setTintMode(this.m);
                if (drawableMutate != getBackground()) {
                    super.setBackgroundDrawable(drawableMutate);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.m = mode;
            if (getBackground() != null) {
                Drawable drawableMutate = getBackground().mutate();
                drawableMutate.setTintMode(mode);
                if (drawableMutate != getBackground()) {
                    super.setBackgroundDrawable(drawableMutate);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                if (this.e == null) {
                    return;
                }
                int i = BaseTransientBottomBar.a;
                throw null;
            }
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : n);
            super.setOnClickListener(onClickListener);
        }
    }

    public class a implements Handler.Callback {
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBottomBar) message.obj).getClass();
                throw null;
            }
            if (i != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).getClass();
            throw null;
        }
    }

    public static class b {
        public b(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.getClass();
            swipeDismissBehavior.e = Math.min(Math.max(0.0f, 0.1f), 1.0f);
            swipeDismissBehavior.f = Math.min(Math.max(0.0f, 0.6f), 1.0f);
            swipeDismissBehavior.c = 0;
        }
    }

    static {
        new Handler(Looper.getMainLooper(), new a());
    }
}
