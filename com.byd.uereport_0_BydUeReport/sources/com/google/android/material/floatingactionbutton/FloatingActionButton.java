package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.stateful.ExtendableSavedState;
import e6.m;
import e6.p;
import j5.k;
import java.util.List;
import o0.s;
import o0.t;
import r.f;
import r.h;
import r0.l;
import y5.d;
import y5.e;

/* JADX INFO: loaded from: classes2.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements s, l, x5.a, p, CoordinatorLayout.b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3886e = R$style.Widget_Design_FloatingActionButton;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f3887f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f3888g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f3889h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f3890i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f3891j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3892k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3893l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3894m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3895n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3896o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3897p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f3898q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f3899r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final h f3900s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final x5.b f3901t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public y5.d f3902u;

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: E */
        public /* bridge */ /* synthetic */ boolean b(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            return super.b(coordinatorLayout, floatingActionButton, rect);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: H */
        public /* bridge */ /* synthetic */ boolean h(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            return super.h(coordinatorLayout, floatingActionButton, view);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        /* JADX INFO: renamed from: I */
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i8) {
            return super.l(coordinatorLayout, floatingActionButton, i8);
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ void g(CoordinatorLayout.f fVar) {
            super.g(fVar);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public class a implements d.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f3906a;

        public a(b bVar) {
            this.f3906a = bVar;
        }

        @Override // y5.d.j
        public void a() {
            this.f3906a.b(FloatingActionButton.this);
        }

        @Override // y5.d.j
        public void b() {
            this.f3906a.a(FloatingActionButton.this);
        }
    }

    public static abstract class b {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    public class c implements d6.b {
        public c() {
        }

        @Override // d6.b
        public void a(int i8, int i9, int i10, int i11) {
            FloatingActionButton.this.f3898q.set(i8, i9, i10, i11);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i8 + floatingActionButton.f3895n, i9 + FloatingActionButton.this.f3895n, i10 + FloatingActionButton.this.f3895n, i11 + FloatingActionButton.this.f3895n);
        }

        @Override // d6.b
        public void b(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }

        @Override // d6.b
        public boolean c() {
            return FloatingActionButton.this.f3897p;
        }
    }

    public class d<T extends FloatingActionButton> implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k<T> f3909a;

        public d(k<T> kVar) {
            this.f3909a = kVar;
        }

        @Override // y5.d.i
        public void a() {
            this.f3909a.a(FloatingActionButton.this);
        }

        @Override // y5.d.i
        public void b() {
            this.f3909a.b(FloatingActionButton.this);
        }

        public boolean equals(Object obj) {
            return (obj instanceof d) && ((d) obj).f3909a.equals(this.f3909a);
        }

        public int hashCode() {
            return this.f3909a.hashCode();
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private y5.d getImpl() {
        if (this.f3902u == null) {
            this.f3902u = h();
        }
        return this.f3902u;
    }

    public static int r(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i8, size);
        }
        if (mode == 0) {
            return i8;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    @Override // x5.a
    public boolean a() {
        return this.f3901t.c();
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().E(getDrawableState());
    }

    public void e(Animator.AnimatorListener animatorListener) {
        getImpl().d(animatorListener);
    }

    public void f(Animator.AnimatorListener animatorListener) {
        getImpl().e(animatorListener);
    }

    public void g(k<? extends FloatingActionButton> kVar) {
        getImpl().f(new d(kVar));
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.f3887f;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.f3888g;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().n();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().q();
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().t();
    }

    public Drawable getContentBackground() {
        return getImpl().k();
    }

    public int getCustomSize() {
        return this.f3894m;
    }

    public int getExpandedComponentIdHint() {
        return this.f3901t.b();
    }

    public j5.h getHideMotionSpec() {
        return getImpl().p();
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.f3891j;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.f3891j;
    }

    public m getShapeAppearanceModel() {
        return (m) n0.h.e(getImpl().u());
    }

    public j5.h getShowMotionSpec() {
        return getImpl().v();
    }

    public int getSize() {
        return this.f3893l;
    }

    public int getSizeDimension() {
        return k(this.f3893l);
    }

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    @Override // r0.l
    public ColorStateList getSupportImageTintList() {
        return this.f3889h;
    }

    @Override // r0.l
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f3890i;
    }

    public boolean getUseCompatPadding() {
        return this.f3897p;
    }

    public final y5.d h() {
        return Build.VERSION.SDK_INT >= 21 ? new e(this, new c()) : new y5.d(this, new c());
    }

    @Deprecated
    public boolean i(Rect rect) {
        if (!t.Q(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        p(rect);
        return true;
    }

    public void j(Rect rect) {
        rect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        p(rect);
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().A();
    }

    public final int k(int i8) {
        int i9 = this.f3894m;
        if (i9 != 0) {
            return i9;
        }
        Resources resources = getResources();
        return i8 != -1 ? i8 != 1 ? resources.getDimensionPixelSize(R$dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R$dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? k(1) : k(0);
    }

    public void l(b bVar) {
        m(bVar, true);
    }

    public void m(b bVar, boolean z7) {
        getImpl().w(u(bVar), z7);
    }

    public boolean n() {
        return getImpl().y();
    }

    public boolean o() {
        return getImpl().z();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getImpl().B();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getImpl().D();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i8, int i9) {
        int sizeDimension = getSizeDimension();
        this.f3895n = (sizeDimension - this.f3896o) / 2;
        getImpl().f0();
        int iMin = Math.min(r(sizeDimension, i8), r(sizeDimension, i9));
        Rect rect = this.f3898q;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.f());
        this.f3901t.d((Bundle) n0.h.e(extendableSavedState.f4030f.get("expandableWidgetHelper")));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (parcelableOnSaveInstanceState == null) {
            parcelableOnSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(parcelableOnSaveInstanceState);
        extendableSavedState.f4030f.put("expandableWidgetHelper", this.f3901t.e());
        return extendableSavedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && i(this.f3899r) && !this.f3899r.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void p(Rect rect) {
        int i8 = rect.left;
        Rect rect2 = this.f3898q;
        rect.left = i8 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    public final void q() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.f3889h;
        if (colorStateList == null) {
            h0.a.c(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f3890i;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(f.e(colorForState, mode));
    }

    public void s(b bVar) {
        t(bVar, true);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i8) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.f3887f != colorStateList) {
            this.f3887f = colorStateList;
            getImpl().L(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f3888g != mode) {
            this.f3888g = mode;
            getImpl().M(mode);
        }
    }

    public void setCompatElevation(float f8) {
        getImpl().N(f8);
    }

    public void setCompatElevationResource(int i8) {
        setCompatElevation(getResources().getDimension(i8));
    }

    public void setCompatHoveredFocusedTranslationZ(float f8) {
        getImpl().Q(f8);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i8) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i8));
    }

    public void setCompatPressedTranslationZ(float f8) {
        getImpl().U(f8);
    }

    public void setCompatPressedTranslationZResource(int i8) {
        setCompatPressedTranslationZ(getResources().getDimension(i8));
    }

    public void setCustomSize(int i8) {
        if (i8 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i8 != this.f3894m) {
            this.f3894m = i8;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        getImpl().g0(f8);
    }

    public void setEnsureMinTouchTargetSize(boolean z7) {
        if (z7 != getImpl().o()) {
            getImpl().O(z7);
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i8) {
        this.f3901t.f(i8);
    }

    public void setHideMotionSpec(j5.h hVar) {
        getImpl().P(hVar);
    }

    public void setHideMotionSpecResource(int i8) {
        setHideMotionSpec(j5.h.d(getContext(), i8));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().e0();
            if (this.f3889h != null) {
                q();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i8) {
        this.f3900s.g(i8);
        q();
    }

    public void setRippleColor(int i8) {
        setRippleColor(ColorStateList.valueOf(i8));
    }

    @Override // android.view.View
    public void setScaleX(float f8) {
        super.setScaleX(f8);
        getImpl().I();
    }

    @Override // android.view.View
    public void setScaleY(float f8) {
        super.setScaleY(f8);
        getImpl().I();
    }

    public void setShadowPaddingEnabled(boolean z7) {
        getImpl().W(z7);
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        getImpl().X(mVar);
    }

    public void setShowMotionSpec(j5.h hVar) {
        getImpl().Y(hVar);
    }

    public void setShowMotionSpecResource(int i8) {
        setShowMotionSpec(j5.h.d(getContext(), i8));
    }

    public void setSize(int i8) {
        this.f3894m = 0;
        if (i8 != this.f3893l) {
            this.f3893l = i8;
            requestLayout();
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    @Override // r0.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.f3889h != colorStateList) {
            this.f3889h = colorStateList;
            q();
        }
    }

    @Override // r0.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f3890i != mode) {
            this.f3890i = mode;
            q();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f8) {
        super.setTranslationX(f8);
        getImpl().J();
    }

    @Override // android.view.View
    public void setTranslationY(float f8) {
        super.setTranslationY(f8);
        getImpl().J();
    }

    @Override // android.view.View
    public void setTranslationZ(float f8) {
        super.setTranslationZ(f8);
        getImpl().J();
    }

    public void setUseCompatPadding(boolean z7) {
        if (this.f3897p != z7) {
            this.f3897p = z7;
            getImpl().C();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
    }

    public void t(b bVar, boolean z7) {
        getImpl().c0(u(bVar), z7);
    }

    public final d.j u(b bVar) {
        if (bVar == null) {
            return null;
        }
        return new a(bVar);
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f3903a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b f3904b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f3905c;

        public BaseBehavior() {
            this.f3905c = true;
        }

        public static boolean F(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] */
        public boolean b(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.f3898q;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        public final void G(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            Rect rect = floatingActionButton.f3898q;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) floatingActionButton.getLayoutParams();
            int i8 = 0;
            int i9 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) fVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) fVar).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) {
                i8 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) fVar).topMargin) {
                i8 = -rect.top;
            }
            if (i8 != 0) {
                t.W(floatingActionButton, i8);
            }
            if (i9 != 0) {
                t.V(floatingActionButton, i9);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
        public boolean h(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                K(coordinatorLayout, (AppBarLayout) view, floatingActionButton);
                return false;
            }
            if (!F(view)) {
                return false;
            }
            L(view, floatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i8) {
            List<View> listV = coordinatorLayout.v(floatingActionButton);
            int size = listV.size();
            for (int i9 = 0; i9 < size; i9++) {
                View view = listV.get(i9);
                if (!(view instanceof AppBarLayout)) {
                    if (F(view) && L(view, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (K(coordinatorLayout, (AppBarLayout) view, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.M(floatingActionButton, i8);
            G(coordinatorLayout, floatingActionButton);
            return true;
        }

        public final boolean J(View view, FloatingActionButton floatingActionButton) {
            return this.f3905c && ((CoordinatorLayout.f) floatingActionButton.getLayoutParams()).e() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        public final boolean K(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!J(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.f3903a == null) {
                this.f3903a = new Rect();
            }
            Rect rect = this.f3903a;
            z5.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.m(this.f3904b, false);
                return true;
            }
            floatingActionButton.t(this.f3904b, false);
            return true;
        }

        public final boolean L(View view, FloatingActionButton floatingActionButton) {
            if (!J(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.m(this.f3904b, false);
                return true;
            }
            floatingActionButton.t(this.f3904b, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.f1193h == 0) {
                fVar.f1193h = 80;
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.f3905c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingActionButtonStyle);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.f3891j != colorStateList) {
            this.f3891j = colorStateList;
            getImpl().V(this.f3891j);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3886e;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3898q = new Rect();
        this.f3899r = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayH = z5.k.h(context2, attributeSet, R$styleable.FloatingActionButton, i8, i9, new int[0]);
        this.f3887f = b6.c.a(context2, typedArrayH, R$styleable.FloatingActionButton_backgroundTint);
        this.f3888g = z5.p.i(typedArrayH.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.f3891j = b6.c.a(context2, typedArrayH, R$styleable.FloatingActionButton_rippleColor);
        this.f3893l = typedArrayH.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.f3894m = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_fabCustomSize, 0);
        this.f3892k = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayH.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = typedArrayH.getDimension(R$styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = typedArrayH.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.f3897p = typedArrayH.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.mtrl_fab_min_touch_target);
        this.f3896o = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_maxImageSize, 0);
        j5.h hVarC = j5.h.c(context2, typedArrayH, R$styleable.FloatingActionButton_showMotionSpec);
        j5.h hVarC2 = j5.h.c(context2, typedArrayH, R$styleable.FloatingActionButton_hideMotionSpec);
        m mVarM = m.g(context2, attributeSet, i8, i9, m.f4705a).m();
        boolean z7 = typedArrayH.getBoolean(R$styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(typedArrayH.getBoolean(R$styleable.FloatingActionButton_android_enabled, true));
        typedArrayH.recycle();
        h hVar = new h(this);
        this.f3900s = hVar;
        hVar.f(attributeSet, i8);
        this.f3901t = new x5.b(this);
        getImpl().X(mVarM);
        getImpl().x(this.f3887f, this.f3888g, this.f3891j, this.f3892k);
        getImpl().T(dimensionPixelSize);
        getImpl().N(dimension);
        getImpl().Q(dimension2);
        getImpl().U(dimension3);
        getImpl().S(this.f3896o);
        getImpl().Y(hVarC);
        getImpl().P(hVarC2);
        getImpl().O(z7);
        setScaleType(ImageView.ScaleType.MATRIX);
    }
}
