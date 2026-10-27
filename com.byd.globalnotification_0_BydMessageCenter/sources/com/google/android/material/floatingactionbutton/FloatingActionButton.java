package com.google.android.material.floatingactionbutton;

import a.b.k.l;
import a.h.k.q;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.d.a.a.e0.n;
import b.d.a.a.k;
import b.d.a.a.l.j;
import b.d.a.a.y.e;
import b.d.a.a.y.f;
import b.d.a.a.y.g;
import b.d.a.a.z.d;
import b.d.a.a.z.p;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends p implements b.d.a.a.x.a, n, CoordinatorLayout.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f2437b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f2438c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ColorStateList f2439d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PorterDuff.Mode f2440e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f2441f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public final Rect k;
    public e l;

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f2442a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2443b;

        public BaseBehavior() {
            this.f2443b = true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k.FloatingActionButton_Behavior_Layout);
            this.f2443b = typedArrayObtainStyledAttributes.getBoolean(k.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean B(FloatingActionButton floatingActionButton, Rect rect) {
            Rect rect2 = floatingActionButton.k;
            rect.set(floatingActionButton.getLeft() + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        public final boolean C(View view, FloatingActionButton floatingActionButton) {
            return this.f2443b && ((CoordinatorLayout.f) floatingActionButton.getLayoutParams()).f1519f == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        public final boolean D(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!C(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.f2442a == null) {
                this.f2442a = new Rect();
            }
            Rect rect = this.f2442a;
            d.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.i(null, false);
                return true;
            }
            floatingActionButton.n(null, false);
            return true;
        }

        public final boolean E(View view, FloatingActionButton floatingActionButton) {
            if (!C(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.i(null, false);
                return true;
            }
            floatingActionButton.n(null, false);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public /* bridge */ /* synthetic */ boolean a(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            return B((FloatingActionButton) view, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void f(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean g(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                D(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.f ? ((CoordinatorLayout.f) layoutParams).f1514a instanceof BottomSheetBehavior : false) {
                    E(view2, floatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public boolean k(CoordinatorLayout coordinatorLayout, View view, int i) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            List<View> listD = coordinatorLayout.d(floatingActionButton);
            int size = listD.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view2 = listD.get(i2);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.f ? ((CoordinatorLayout.f) layoutParams).f1514a instanceof BottomSheetBehavior : false) && E(view2, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (D(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.q(floatingActionButton, i);
            return true;
        }
    }

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class a {
        public void a(FloatingActionButton floatingActionButton) {
        }

        public void b(FloatingActionButton floatingActionButton) {
        }
    }

    public class b implements b.d.a.a.d0.b {
        public b() {
        }
    }

    public class c<T extends FloatingActionButton> implements e.InterfaceC0045e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j<T> f2445a;

        public c(j<T> jVar) {
            this.f2445a = jVar;
        }

        @Override // b.d.a.a.y.e.InterfaceC0045e
        public void a() {
            this.f2445a.a(FloatingActionButton.this);
        }

        @Override // b.d.a.a.y.e.InterfaceC0045e
        public void b() {
            this.f2445a.b(FloatingActionButton.this);
        }

        public boolean equals(Object obj) {
            return (obj instanceof c) && ((c) obj).f2445a.equals(this.f2445a);
        }

        public int hashCode() {
            return this.f2445a.hashCode();
        }
    }

    private e getImpl() {
        if (this.l == null) {
            this.l = new g(this, new b());
        }
        return this.l;
    }

    public static int m(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == Integer.MIN_VALUE) {
            return Math.min(i, size);
        }
        if (mode == 0) {
            return i;
        }
        if (mode == 1073741824) {
            return size;
        }
        throw new IllegalArgumentException();
    }

    @Override // b.d.a.a.x.a
    public boolean a() {
        throw null;
    }

    public void d(Animator.AnimatorListener animatorListener) {
        e impl = getImpl();
        if (impl.r == null) {
            impl.r = new ArrayList<>();
        }
        impl.r.add(null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().i(getDrawableState());
    }

    public void e(Animator.AnimatorListener animatorListener) {
        e impl = getImpl();
        if (impl.q == null) {
            impl.q = new ArrayList<>();
        }
        impl.q.add(animatorListener);
    }

    public void f(j<? extends FloatingActionButton> jVar) {
        e impl = getImpl();
        c cVar = new c(null);
        if (impl.s == null) {
            impl.s = new ArrayList<>();
        }
        impl.s.add(cVar);
    }

    @Deprecated
    public boolean g(Rect rect) {
        if (!q.C(this)) {
            return false;
        }
        rect.set(0, 0, getWidth(), getHeight());
        throw null;
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.f2437b;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.f2438c;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().c();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().f2150f;
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().g;
    }

    public Drawable getContentBackground() {
        if (getImpl() != null) {
            return null;
        }
        throw null;
    }

    public int getCustomSize() {
        return this.h;
    }

    public int getExpandedComponentIdHint() {
        throw null;
    }

    public b.d.a.a.l.g getHideMotionSpec() {
        return getImpl().m;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.f2441f;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.f2441f;
    }

    public b.d.a.a.e0.j getShapeAppearanceModel() {
        b.d.a.a.e0.j jVar = getImpl().f2145a;
        l.i.h(jVar);
        return jVar;
    }

    public b.d.a.a.l.g getShowMotionSpec() {
        return getImpl().l;
    }

    public int getSize() {
        return this.g;
    }

    public int getSizeDimension() {
        return h(this.g);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.f2439d;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f2440e;
    }

    public boolean getUseCompatPadding() {
        return this.j;
    }

    public final int h(int i) {
        int i2 = this.h;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        if (i != -1) {
            return resources.getDimensionPixelSize(i != 1 ? b.d.a.a.d.design_fab_size_normal : b.d.a.a.d.design_fab_size_mini);
        }
        return Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? h(1) : h(0);
    }

    public void i(a aVar, boolean z) {
        e impl = getImpl();
        b.d.a.a.y.b bVar = aVar == null ? null : new b.d.a.a.y.b(this, aVar);
        if (impl.e()) {
            return;
        }
        Animator animator = impl.k;
        if (animator != null) {
            animator.cancel();
        }
        if (!impl.q()) {
            impl.t.b(z ? 8 : 4, z);
            if (bVar != null) {
                bVar.f2136a.a(bVar.f2137b);
                return;
            }
            return;
        }
        b.d.a.a.l.g gVar = impl.m;
        if (gVar == null) {
            if (impl.j == null) {
                impl.j = b.d.a.a.l.g.b(impl.t.getContext(), b.d.a.a.a.design_fab_hide_motion_spec);
            }
            gVar = impl.j;
            l.i.h(gVar);
        }
        AnimatorSet animatorSetA = impl.a(gVar, 0.0f, 0.0f, 0.0f);
        animatorSetA.addListener(new b.d.a.a.y.c(impl, z, bVar));
        ArrayList<Animator.AnimatorListener> arrayList = impl.r;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetA.addListener(it.next());
            }
        }
        animatorSetA.start();
    }

    public boolean j() {
        return getImpl().e();
    }

    @Override // android.widget.ImageView, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().g();
    }

    public boolean k() {
        return getImpl().f();
    }

    public final void l() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.f2439d;
        if (colorStateList == null) {
            drawable.clearColorFilter();
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f2440e;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(a.b.p.j.c(colorForState, mode));
    }

    public void n(a aVar, boolean z) {
        e impl = getImpl();
        b.d.a.a.y.b bVar = aVar == null ? null : new b.d.a.a.y.b(this, aVar);
        if (impl.f()) {
            return;
        }
        Animator animator = impl.k;
        if (animator != null) {
            animator.cancel();
        }
        if (!impl.q()) {
            impl.t.b(0, z);
            impl.t.setAlpha(1.0f);
            impl.t.setScaleY(1.0f);
            impl.t.setScaleX(1.0f);
            impl.n(1.0f);
            if (bVar != null) {
                bVar.f2136a.b(bVar.f2137b);
                return;
            }
            return;
        }
        if (impl.t.getVisibility() != 0) {
            impl.t.setAlpha(0.0f);
            impl.t.setScaleY(0.0f);
            impl.t.setScaleX(0.0f);
            impl.n(0.0f);
        }
        b.d.a.a.l.g gVar = impl.l;
        if (gVar == null) {
            if (impl.i == null) {
                impl.i = b.d.a.a.l.g.b(impl.t.getContext(), b.d.a.a.a.design_fab_show_motion_spec);
            }
            gVar = impl.i;
            l.i.h(gVar);
        }
        AnimatorSet animatorSetA = impl.a(gVar, 1.0f, 1.0f, 1.0f);
        animatorSetA.addListener(new b.d.a.a.y.d(impl, z, bVar));
        ArrayList<Animator.AnimatorListener> arrayList = impl.q;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetA.addListener(it.next());
            }
        }
        animatorSetA.start();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        e impl = getImpl();
        if (impl.m()) {
            ViewTreeObserver viewTreeObserver = impl.t.getViewTreeObserver();
            if (impl.z == null) {
                impl.z = new f(impl);
            }
            viewTreeObserver.addOnPreDrawListener(impl.z);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.t.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = impl.z;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            impl.z = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.i = (sizeDimension + 0) / 2;
        getImpl().t();
        Math.min(m(sizeDimension, i), m(sizeDimension, i2));
        throw null;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b.d.a.a.g0.a)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b.d.a.a.g0.a aVar = (b.d.a.a.g0.a) parcelable;
        super.onRestoreInstanceState(aVar.f892a);
        l.i.h(aVar.f1894c.getOrDefault("expandableWidgetHelper", null));
        throw null;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        if (super.onSaveInstanceState() == null) {
            new Bundle();
        }
        a.j.a.a aVar = a.j.a.a.f891b;
        throw null;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            g(null);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.f2437b != colorStateList) {
            this.f2437b = colorStateList;
            if (getImpl() == null) {
                throw null;
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f2438c != mode) {
            this.f2438c = mode;
            if (getImpl() == null) {
                throw null;
            }
        }
    }

    public void setCompatElevation(float f2) {
        e impl = getImpl();
        if (impl.f2149e != f2) {
            impl.f2149e = f2;
            impl.j(f2, impl.f2150f, impl.g);
        }
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f2) {
        e impl = getImpl();
        if (impl.f2150f != f2) {
            impl.f2150f = f2;
            impl.j(impl.f2149e, f2, impl.g);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f2) {
        e impl = getImpl();
        if (impl.g != f2) {
            impl.g = f2;
            impl.j(impl.f2149e, impl.f2150f, f2);
        }
    }

    public void setCompatPressedTranslationZResource(int i) {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i != this.h) {
            this.h = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (getImpl() == null) {
            throw null;
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().f2147c) {
            getImpl().f2147c = z;
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        throw null;
    }

    public void setHideMotionSpec(b.d.a.a.l.g gVar) {
        getImpl().m = gVar;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(b.d.a.a.l.g.b(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            e impl = getImpl();
            impl.n(impl.o);
            if (this.f2439d != null) {
                l();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        throw null;
    }

    public void setRippleColor(int i) {
        setRippleColor(ColorStateList.valueOf(i));
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.f2441f != colorStateList) {
            this.f2441f = colorStateList;
            getImpl().o(this.f2441f);
        }
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        super.setScaleX(f2);
        getImpl().k();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        super.setScaleY(f2);
        getImpl().k();
    }

    public void setShadowPaddingEnabled(boolean z) {
        e impl = getImpl();
        impl.f2148d = z;
        impl.t();
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(b.d.a.a.e0.j jVar) {
        getImpl().f2145a = jVar;
    }

    public void setShowMotionSpec(b.d.a.a.l.g gVar) {
        getImpl().l = gVar;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(b.d.a.a.l.g.b(getContext(), i));
    }

    public void setSize(int i) {
        this.h = 0;
        if (i != this.g) {
            this.g = i;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.f2439d != colorStateList) {
            this.f2439d = colorStateList;
            l();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f2440e != mode) {
            this.f2440e = mode;
            l();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        super.setTranslationX(f2);
        getImpl().l();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        super.setTranslationY(f2);
        getImpl().l();
    }

    @Override // android.view.View
    public void setTranslationZ(float f2) {
        super.setTranslationZ(f2);
        getImpl().l();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.j != z) {
            this.j = z;
            getImpl().h();
        }
    }

    @Override // b.d.a.a.z.p, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }
}
