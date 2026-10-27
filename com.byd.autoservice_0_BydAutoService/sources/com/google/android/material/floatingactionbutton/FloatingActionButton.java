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
import h2.d;
import h2.e;
import java.util.List;
import l0.s;
import l0.t;
import n0.l;
import n2.m;
import n2.p;
import o.f;
import o.h;
import s1.k;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements s, l, g2.a, p, CoordinatorLayout.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2303c = R$style.Widget_Design_FloatingActionButton;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorStateList f2304d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PorterDuff.Mode f2305e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f2306f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f2307g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f2308h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2309i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2310j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2311k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2312l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2313m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2314n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Rect f2315o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f2316p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final h f2317q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final g2.b f2318r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h2.d f2319s;

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
        public /* bridge */ /* synthetic */ boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i3) {
            return super.l(coordinatorLayout, floatingActionButton, i3);
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
        public final /* synthetic */ b f2323a;

        public a(b bVar) {
            this.f2323a = bVar;
        }

        @Override // h2.d.j
        public void a() {
            this.f2323a.b(FloatingActionButton.this);
        }

        @Override // h2.d.j
        public void b() {
            this.f2323a.a(FloatingActionButton.this);
        }
    }

    public static abstract class b {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    public class c implements m2.b {
        public c() {
        }

        @Override // m2.b
        public void a(int i3, int i4, int i5, int i6) {
            FloatingActionButton.this.f2315o.set(i3, i4, i5, i6);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.setPadding(i3 + floatingActionButton.f2312l, i4 + FloatingActionButton.this.f2312l, i5 + FloatingActionButton.this.f2312l, i6 + FloatingActionButton.this.f2312l);
        }

        @Override // m2.b
        public boolean b() {
            return FloatingActionButton.this.f2314n;
        }

        @Override // m2.b
        public void c(Drawable drawable) {
            if (drawable != null) {
                FloatingActionButton.super.setBackgroundDrawable(drawable);
            }
        }
    }

    public class d<T extends FloatingActionButton> implements d.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k<T> f2326a;

        public d(k<T> kVar) {
            this.f2326a = kVar;
        }

        @Override // h2.d.i
        public void a() {
            this.f2326a.a(FloatingActionButton.this);
        }

        @Override // h2.d.i
        public void b() {
            this.f2326a.b(FloatingActionButton.this);
        }

        public boolean equals(Object obj) {
            return (obj instanceof d) && ((d) obj).f2326a.equals(this.f2326a);
        }

        public int hashCode() {
            return this.f2326a.hashCode();
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private h2.d getImpl() {
        if (this.f2319s == null) {
            this.f2319s = h();
        }
        return this.f2319s;
    }

    public static int r(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i4);
        int size = View.MeasureSpec.getSize(i4);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i3, size);
        }
        if (mode == 0) {
            return i3;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    @Override // g2.a
    public boolean a() {
        return this.f2318r.c();
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
        return this.f2304d;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.f2305e;
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
        return this.f2311k;
    }

    public int getExpandedComponentIdHint() {
        return this.f2318r.b();
    }

    public s1.h getHideMotionSpec() {
        return getImpl().p();
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.f2308h;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.f2308h;
    }

    public m getShapeAppearanceModel() {
        return (m) k0.h.c(getImpl().u());
    }

    public s1.h getShowMotionSpec() {
        return getImpl().v();
    }

    public int getSize() {
        return this.f2310j;
    }

    public int getSizeDimension() {
        return k(this.f2310j);
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    @Override // n0.l
    public ColorStateList getSupportImageTintList() {
        return this.f2306f;
    }

    @Override // n0.l
    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f2307g;
    }

    public boolean getUseCompatPadding() {
        return this.f2314n;
    }

    public final h2.d h() {
        return Build.VERSION.SDK_INT >= 21 ? new e(this, new c()) : new h2.d(this, new c());
    }

    @Deprecated
    public boolean i(Rect rect) {
        if (!t.P(this)) {
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

    public final int k(int i3) {
        int i4 = this.f2311k;
        if (i4 != 0) {
            return i4;
        }
        Resources resources = getResources();
        return i3 != -1 ? i3 != 1 ? resources.getDimensionPixelSize(R$dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R$dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? k(1) : k(0);
    }

    public void l(b bVar) {
        m(bVar, true);
    }

    public void m(b bVar, boolean z3) {
        getImpl().w(u(bVar), z3);
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
    public void onMeasure(int i3, int i4) {
        int sizeDimension = getSizeDimension();
        this.f2312l = (sizeDimension - this.f2313m) / 2;
        getImpl().f0();
        int iMin = Math.min(r(sizeDimension, i3), r(sizeDimension, i4));
        Rect rect = this.f2315o;
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
        this.f2318r.d((Bundle) k0.h.c(extendableSavedState.f2451d.get("expandableWidgetHelper")));
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (parcelableOnSaveInstanceState == null) {
            parcelableOnSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(parcelableOnSaveInstanceState);
        extendableSavedState.f2451d.put("expandableWidgetHelper", this.f2318r.e());
        return extendableSavedState;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && i(this.f2316p) && !this.f2316p.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void p(Rect rect) {
        int i3 = rect.left;
        Rect rect2 = this.f2315o;
        rect.left = i3 + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    public final void q() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.f2306f;
        if (colorStateList == null) {
            e0.a.c(drawable);
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f2307g;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(f.e(colorForState, mode));
    }

    public void s(b bVar) {
        t(bVar, true);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i3) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.f2304d != colorStateList) {
            this.f2304d = colorStateList;
            getImpl().L(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f2305e != mode) {
            this.f2305e = mode;
            getImpl().M(mode);
        }
    }

    public void setCompatElevation(float f3) {
        getImpl().N(f3);
    }

    public void setCompatElevationResource(int i3) {
        setCompatElevation(getResources().getDimension(i3));
    }

    public void setCompatHoveredFocusedTranslationZ(float f3) {
        getImpl().Q(f3);
    }

    public void setCompatHoveredFocusedTranslationZResource(int i3) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i3));
    }

    public void setCompatPressedTranslationZ(float f3) {
        getImpl().U(f3);
    }

    public void setCompatPressedTranslationZResource(int i3) {
        setCompatPressedTranslationZ(getResources().getDimension(i3));
    }

    public void setCustomSize(int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i3 != this.f2311k) {
            this.f2311k = i3;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        getImpl().g0(f3);
    }

    public void setEnsureMinTouchTargetSize(boolean z3) {
        if (z3 != getImpl().o()) {
            getImpl().O(z3);
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i3) {
        this.f2318r.f(i3);
    }

    public void setHideMotionSpec(s1.h hVar) {
        getImpl().P(hVar);
    }

    public void setHideMotionSpecResource(int i3) {
        setHideMotionSpec(s1.h.d(getContext(), i3));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            getImpl().e0();
            if (this.f2306f != null) {
                q();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i3) {
        this.f2317q.g(i3);
        q();
    }

    public void setRippleColor(int i3) {
        setRippleColor(ColorStateList.valueOf(i3));
    }

    @Override // android.view.View
    public void setScaleX(float f3) {
        super.setScaleX(f3);
        getImpl().I();
    }

    @Override // android.view.View
    public void setScaleY(float f3) {
        super.setScaleY(f3);
        getImpl().I();
    }

    public void setShadowPaddingEnabled(boolean z3) {
        getImpl().W(z3);
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        getImpl().X(mVar);
    }

    public void setShowMotionSpec(s1.h hVar) {
        getImpl().Y(hVar);
    }

    public void setShowMotionSpecResource(int i3) {
        setShowMotionSpec(s1.h.d(getContext(), i3));
    }

    public void setSize(int i3) {
        this.f2311k = 0;
        if (i3 != this.f2310j) {
            this.f2310j = i3;
            requestLayout();
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    @Override // n0.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.f2306f != colorStateList) {
            this.f2306f = colorStateList;
            q();
        }
    }

    @Override // n0.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f2307g != mode) {
            this.f2307g = mode;
            q();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f3) {
        super.setTranslationX(f3);
        getImpl().J();
    }

    @Override // android.view.View
    public void setTranslationY(float f3) {
        super.setTranslationY(f3);
        getImpl().J();
    }

    @Override // android.view.View
    public void setTranslationZ(float f3) {
        super.setTranslationZ(f3);
        getImpl().J();
    }

    public void setUseCompatPadding(boolean z3) {
        if (this.f2314n != z3) {
            this.f2314n = z3;
            getImpl().C();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i3) {
        super.setVisibility(i3);
    }

    public void t(b bVar, boolean z3) {
        getImpl().c0(u(bVar), z3);
    }

    public final d.j u(b bVar) {
        if (bVar == null) {
            return null;
        }
        return new a(bVar);
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f2320a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b f2321b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2322c;

        public BaseBehavior() {
            this.f2322c = true;
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
            Rect rect2 = floatingActionButton.f2315o;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        public final void G(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton) {
            Rect rect = floatingActionButton.f2315o;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return;
            }
            CoordinatorLayout.f fVar = (CoordinatorLayout.f) floatingActionButton.getLayoutParams();
            int i3 = 0;
            int i4 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) fVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) fVar).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) fVar).bottomMargin) {
                i3 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) fVar).topMargin) {
                i3 = -rect.top;
            }
            if (i3 != 0) {
                t.V(floatingActionButton, i3);
            }
            if (i4 != 0) {
                t.U(floatingActionButton, i4);
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
        public boolean l(CoordinatorLayout coordinatorLayout, FloatingActionButton floatingActionButton, int i3) {
            List<View> listV = coordinatorLayout.v(floatingActionButton);
            int size = listV.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = listV.get(i4);
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
            coordinatorLayout.M(floatingActionButton, i3);
            G(coordinatorLayout, floatingActionButton);
            return true;
        }

        public final boolean J(View view, FloatingActionButton floatingActionButton) {
            return this.f2322c && ((CoordinatorLayout.f) floatingActionButton.getLayoutParams()).e() == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        public final boolean K(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!J(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.f2320a == null) {
                this.f2320a = new Rect();
            }
            Rect rect = this.f2320a;
            i2.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.m(this.f2321b, false);
                return true;
            }
            floatingActionButton.t(this.f2321b, false);
            return true;
        }

        public final boolean L(View view, FloatingActionButton floatingActionButton) {
            if (!J(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.m(this.f2321b, false);
                return true;
            }
            floatingActionButton.t(this.f2321b, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.f1104h == 0) {
                fVar.f1104h = 80;
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.f2322c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingActionButtonStyle);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.f2308h != colorStateList) {
            this.f2308h = colorStateList;
            getImpl().V(this.f2308h);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2303c;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2315o = new Rect();
        this.f2316p = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayH = i2.k.h(context2, attributeSet, R$styleable.FloatingActionButton, i3, i4, new int[0]);
        this.f2304d = k2.c.a(context2, typedArrayH, R$styleable.FloatingActionButton_backgroundTint);
        this.f2305e = i2.p.i(typedArrayH.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.f2308h = k2.c.a(context2, typedArrayH, R$styleable.FloatingActionButton_rippleColor);
        this.f2310j = typedArrayH.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.f2311k = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_fabCustomSize, 0);
        this.f2309i = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayH.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = typedArrayH.getDimension(R$styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = typedArrayH.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.f2314n = typedArrayH.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.mtrl_fab_min_touch_target);
        this.f2313m = typedArrayH.getDimensionPixelSize(R$styleable.FloatingActionButton_maxImageSize, 0);
        s1.h hVarC = s1.h.c(context2, typedArrayH, R$styleable.FloatingActionButton_showMotionSpec);
        s1.h hVarC2 = s1.h.c(context2, typedArrayH, R$styleable.FloatingActionButton_hideMotionSpec);
        m mVarM = m.g(context2, attributeSet, i3, i4, m.f4152a).m();
        boolean z3 = typedArrayH.getBoolean(R$styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(typedArrayH.getBoolean(R$styleable.FloatingActionButton_android_enabled, true));
        typedArrayH.recycle();
        h hVar = new h(this);
        this.f2317q = hVar;
        hVar.f(attributeSet, i3);
        this.f2318r = new g2.b(this);
        getImpl().X(mVarM);
        getImpl().x(this.f2304d, this.f2305e, this.f2308h, this.f2309i);
        getImpl().T(dimensionPixelSize);
        getImpl().N(dimension);
        getImpl().Q(dimension2);
        getImpl().U(dimension3);
        getImpl().S(this.f2313m);
        getImpl().Y(hVarC);
        getImpl().P(hVarC2);
        getImpl().O(z3);
        setScaleType(ImageView.ScaleType.MATRIX);
    }
}
