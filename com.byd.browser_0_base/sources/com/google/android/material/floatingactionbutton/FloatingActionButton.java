package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
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
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatImageHelper;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.d;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.stateful.ExtendableSavedState;
import defpackage.fj1;
import defpackage.in3;
import defpackage.mi0;
import defpackage.ni0;
import defpackage.oj2;
import defpackage.om3;
import defpackage.p80;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.th;
import defpackage.tj2;
import defpackage.tn0;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.un0;
import defpackage.wm2;
import defpackage.xh;
import defpackage.yh;
import defpackage.yj2;
import defpackage.yn3;
import defpackage.ze3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements mi0, yj2, CoordinatorLayout.b {
    public static final int u = R$style.Widget_Design_FloatingActionButton;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public ColorStateList h;
    public PorterDuff.Mode i;
    public ColorStateList j;
    public int k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public final Rect p;
    public final Rect q;
    public final AppCompatImageHelper r;
    public final ni0 s;
    public un0 t;

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(a aVar) {
            super.setInternalAutoHideListener(aVar);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public class b implements tj2 {
        public b() {
        }
    }

    public class c<T extends FloatingActionButton> implements d.f {
        public final ze3<T> a;

        public c(BottomAppBar.b bVar) {
            this.a = bVar;
        }

        @Override // com.google.android.material.floatingactionbutton.d.f
        public final void a() {
            BottomAppBar.b bVar = (BottomAppBar.b) this.a;
            bVar.getClass();
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            float translationX = floatingActionButton.getTranslationX();
            BottomAppBar bottomAppBar = BottomAppBar.this;
            float f = bottomAppBar.getTopEdgeTreatment().i;
            sc1 sc1Var = bottomAppBar.d0;
            if (f != translationX) {
                bottomAppBar.getTopEdgeTreatment().i = translationX;
                sc1Var.invalidateSelf();
            }
            float fMax = Math.max(0.0f, -floatingActionButton.getTranslationY());
            if (bottomAppBar.getTopEdgeTreatment().h != fMax) {
                yh topEdgeTreatment = bottomAppBar.getTopEdgeTreatment();
                if (fMax < 0.0f) {
                    topEdgeTreatment.getClass();
                    throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
                }
                topEdgeTreatment.h = fMax;
                sc1Var.invalidateSelf();
            }
            sc1Var.o(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.d.f
        public final void b() {
            BottomAppBar.b bVar = (BottomAppBar.b) this.a;
            bVar.getClass();
            sc1 sc1Var = BottomAppBar.this.d0;
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            sc1Var.o(floatingActionButton.getVisibility() == 0 ? floatingActionButton.getScaleY() : 0.0f);
        }

        public final boolean equals(Object obj) {
            return (obj instanceof c) && ((c) obj).a.equals(this.a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private d getImpl() {
        if (this.t == null) {
            this.t = new un0(this, new b());
        }
        return this.t;
    }

    public static int n(int i, int i2) {
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

    @Override // defpackage.mi0
    public final boolean a() {
        return this.s.b;
    }

    public final void d(BottomAppBar.a aVar) {
        d impl = getImpl();
        if (impl.t == null) {
            impl.t = new ArrayList<>();
        }
        impl.t.add(aVar);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().j(getDrawableState());
    }

    public final void f(xh xhVar) {
        d impl = getImpl();
        if (impl.s == null) {
            impl.s = new ArrayList<>();
        }
        impl.s.add(xhVar);
    }

    public final void g(BottomAppBar.b bVar) {
        d impl = getImpl();
        c cVar = new c(bVar);
        if (impl.u == null) {
            impl.u = new ArrayList<>();
        }
        impl.u.add(cVar);
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.f;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.g;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.Behavior<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().e();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().i;
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().j;
    }

    public Drawable getContentBackground() {
        return getImpl().e;
    }

    public int getCustomSize() {
        return this.l;
    }

    public int getExpandedComponentIdHint() {
        return this.s.c;
    }

    public fj1 getHideMotionSpec() {
        return getImpl().n;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.j;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.j;
    }

    public uj2 getShapeAppearanceModel() {
        uj2 uj2Var = getImpl().a;
        uj2Var.getClass();
        return uj2Var;
    }

    public fj1 getShowMotionSpec() {
        return getImpl().m;
    }

    public int getSize() {
        return this.k;
    }

    public int getSizeDimension() {
        return h(this.k);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.h;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.i;
    }

    public boolean getUseCompatPadding() {
        return this.o;
    }

    public final int h(int i) {
        int i2 = this.l;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        return i != -1 ? i != 1 ? resources.getDimensionPixelSize(R$dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R$dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? h(1) : h(0);
    }

    public void hide(a aVar) {
        i(aVar, true);
    }

    public final void i(a aVar, boolean z) {
        d impl = getImpl();
        com.google.android.material.floatingactionbutton.a aVar2 = aVar == null ? null : new com.google.android.material.floatingactionbutton.a(this, aVar);
        if (impl.v.getVisibility() != 0 ? impl.r != 2 : impl.r == 1) {
            return;
        }
        Animator animator = impl.l;
        if (animator != null) {
            animator.cancel();
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        FloatingActionButton floatingActionButton = impl.v;
        if (!(om3.g.c(floatingActionButton) && !floatingActionButton.isInEditMode())) {
            floatingActionButton.b(z ? 8 : 4, z);
            if (aVar2 != null) {
                aVar2.a.a(aVar2.b);
                return;
            }
            return;
        }
        fj1 fj1Var = impl.n;
        AnimatorSet animatorSetB = fj1Var != null ? impl.b(fj1Var, 0.0f, 0.0f, 0.0f) : impl.c(0.0f, 0.4f, 0.4f, d.F, d.G);
        animatorSetB.addListener(new com.google.android.material.floatingactionbutton.b(impl, z, aVar2));
        ArrayList<Animator.AnimatorListener> arrayList = impl.t;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetB.addListener(it.next());
            }
        }
        animatorSetB.start();
    }

    public final boolean j() {
        d impl = getImpl();
        if (impl.v.getVisibility() == 0) {
            if (impl.r != 1) {
                return false;
            }
        } else if (impl.r == 2) {
            return false;
        }
        return true;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().h();
    }

    public final boolean k() {
        d impl = getImpl();
        if (impl.v.getVisibility() != 0) {
            if (impl.r != 2) {
                return false;
            }
        } else if (impl.r == 1) {
            return false;
        }
        return true;
    }

    public final void l(Rect rect) {
        int i = rect.left;
        Rect rect2 = this.p;
        rect.left = i + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    public final void m() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.h;
        if (colorStateList == null) {
            drawable.clearColorFilter();
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.i;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(AppCompatDrawableManager.c(colorForState, mode));
    }

    public final void o(a aVar, boolean z) {
        d impl = getImpl();
        com.google.android.material.floatingactionbutton.a aVar2 = aVar == null ? null : new com.google.android.material.floatingactionbutton.a(this, aVar);
        if (impl.v.getVisibility() == 0 ? impl.r != 1 : impl.r == 2) {
            return;
        }
        Animator animator = impl.l;
        if (animator != null) {
            animator.cancel();
        }
        boolean z2 = impl.m == null;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        FloatingActionButton floatingActionButton = impl.v;
        boolean z3 = om3.g.c(floatingActionButton) && !floatingActionButton.isInEditMode();
        Matrix matrix = impl.A;
        if (!z3) {
            floatingActionButton.b(0, z);
            floatingActionButton.setAlpha(1.0f);
            floatingActionButton.setScaleY(1.0f);
            floatingActionButton.setScaleX(1.0f);
            impl.p = 1.0f;
            impl.a(1.0f, matrix);
            floatingActionButton.setImageMatrix(matrix);
            if (aVar2 != null) {
                aVar2.a.b();
                return;
            }
            return;
        }
        if (floatingActionButton.getVisibility() != 0) {
            floatingActionButton.setAlpha(0.0f);
            floatingActionButton.setScaleY(z2 ? 0.4f : 0.0f);
            floatingActionButton.setScaleX(z2 ? 0.4f : 0.0f);
            float f = z2 ? 0.4f : 0.0f;
            impl.p = f;
            impl.a(f, matrix);
            floatingActionButton.setImageMatrix(matrix);
        }
        fj1 fj1Var = impl.m;
        AnimatorSet animatorSetB = fj1Var != null ? impl.b(fj1Var, 1.0f, 1.0f, 1.0f) : impl.c(1.0f, 1.0f, 1.0f, d.D, d.E);
        animatorSetB.addListener(new com.google.android.material.floatingactionbutton.c(impl, z, aVar2));
        ArrayList<Animator.AnimatorListener> arrayList = impl.s;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetB.addListener(it.next());
            }
        }
        animatorSetB.start();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        d impl = getImpl();
        sc1 sc1Var = impl.b;
        FloatingActionButton floatingActionButton = impl.v;
        if (sc1Var != null) {
            oj2.e0(floatingActionButton, sc1Var);
        }
        if (!(impl instanceof un0)) {
            ViewTreeObserver viewTreeObserver = floatingActionButton.getViewTreeObserver();
            if (impl.B == null) {
                impl.B = new tn0(impl);
            }
            viewTreeObserver.addOnPreDrawListener(impl.B);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.v.getViewTreeObserver();
        tn0 tn0Var = impl.B;
        if (tn0Var != null) {
            viewTreeObserver.removeOnPreDrawListener(tn0Var);
            impl.B = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.m = (sizeDimension - this.n) / 2;
        getImpl().q();
        int iMin = Math.min(n(sizeDimension, i), n(sizeDimension, i2));
        Rect rect = this.p;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.e);
        Bundle bundle = extendableSavedState.g.get("expandableWidgetHelper");
        bundle.getClass();
        ni0 ni0Var = this.s;
        ni0Var.getClass();
        ni0Var.b = bundle.getBoolean("expanded", false);
        ni0Var.c = bundle.getInt("expandedComponentIdHint", 0);
        if (ni0Var.b) {
            View view = ni0Var.a;
            ViewParent parent = view.getParent();
            if (parent instanceof CoordinatorLayout) {
                ((CoordinatorLayout) parent).h(view);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (parcelableOnSaveInstanceState == null) {
            parcelableOnSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(parcelableOnSaveInstanceState);
        wm2<String, Bundle> wm2Var = extendableSavedState.g;
        ni0 ni0Var = this.s;
        ni0Var.getClass();
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", ni0Var.b);
        bundle.putInt("expandedComponentIdHint", ni0Var.c);
        wm2Var.put("expandableWidgetHelper", bundle);
        return extendableSavedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getAction() == 0) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            boolean zC = om3.g.c(this);
            Rect rect = this.q;
            if (zC) {
                rect.set(0, 0, getWidth(), getHeight());
                l(rect);
                z = true;
            } else {
                z = false;
            }
            if (z && !rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
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
        if (this.f != colorStateList) {
            this.f = colorStateList;
            d impl = getImpl();
            sc1 sc1Var = impl.b;
            if (sc1Var != null) {
                sc1Var.setTintList(colorStateList);
            }
            th thVar = impl.d;
            if (thVar != null) {
                if (colorStateList != null) {
                    thVar.m = colorStateList.getColorForState(thVar.getState(), thVar.m);
                }
                thVar.p = colorStateList;
                thVar.n = true;
                thVar.invalidateSelf();
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.g != mode) {
            this.g = mode;
            sc1 sc1Var = getImpl().b;
            if (sc1Var != null) {
                sc1Var.setTintMode(mode);
            }
        }
    }

    public void setCompatElevation(float f) {
        d impl = getImpl();
        if (impl.h != f) {
            impl.h = f;
            impl.k(f, impl.i, impl.j);
        }
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f) {
        d impl = getImpl();
        if (impl.i != f) {
            impl.i = f;
            impl.k(impl.h, f, impl.j);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f) {
        d impl = getImpl();
        if (impl.j != f) {
            impl.j = f;
            impl.k(impl.h, impl.i, f);
        }
    }

    public void setCompatPressedTranslationZResource(int i) {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i != this.l) {
            this.l = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        sc1 sc1Var = getImpl().b;
        if (sc1Var != null) {
            sc1Var.m(f);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().f) {
            getImpl().f = z;
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        this.s.c = i;
    }

    public void setHideMotionSpec(fj1 fj1Var) {
        getImpl().n = fj1Var;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(fj1.b(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            d impl = getImpl();
            float f = impl.p;
            impl.p = f;
            Matrix matrix = impl.A;
            impl.a(f, matrix);
            impl.v.setImageMatrix(matrix);
            if (this.h != null) {
                m();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.r.c(i);
        m();
    }

    public void setMaxImageSize(int i) {
        this.n = i;
        d impl = getImpl();
        if (impl.q != i) {
            impl.q = i;
            float f = impl.p;
            impl.p = f;
            Matrix matrix = impl.A;
            impl.a(f, matrix);
            impl.v.setImageMatrix(matrix);
        }
    }

    public void setRippleColor(int i) {
        setRippleColor(ColorStateList.valueOf(i));
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        super.setScaleX(f);
        ArrayList<d.f> arrayList = getImpl().u;
        if (arrayList != null) {
            Iterator<d.f> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        super.setScaleY(f);
        ArrayList<d.f> arrayList = getImpl().u;
        if (arrayList != null) {
            Iterator<d.f> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public void setShadowPaddingEnabled(boolean z) {
        d impl = getImpl();
        impl.g = z;
        impl.q();
    }

    @Override // defpackage.yj2
    public void setShapeAppearanceModel(uj2 uj2Var) {
        getImpl().n(uj2Var);
    }

    public void setShowMotionSpec(fj1 fj1Var) {
        getImpl().m = fj1Var;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(fj1.b(getContext(), i));
    }

    public void setSize(int i) {
        this.l = 0;
        if (i != this.k) {
            this.k = i;
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
        if (this.h != colorStateList) {
            this.h = colorStateList;
            m();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.i != mode) {
            this.i = mode;
            m();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        super.setTranslationX(f);
        getImpl().l();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        getImpl().l();
    }

    @Override // android.view.View
    public void setTranslationZ(float f) {
        super.setTranslationZ(f);
        getImpl().l();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.o != z) {
            this.o = z;
            getImpl().i();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void show(a aVar) {
        o(aVar, true);
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect a;
        public a b;
        public final boolean c;

        public BaseBehavior() {
            this.c = true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean a(View view, Rect rect) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int left = floatingActionButton.getLeft();
            Rect rect2 = floatingActionButton.p;
            rect.set(left + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void c(CoordinatorLayout.e eVar) {
            if (eVar.h == 0) {
                eVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean d(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                t(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.e ? ((CoordinatorLayout.e) layoutParams).a instanceof BottomSheetBehavior : false) {
                    u(view2, floatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, int i) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            List<View> listP = coordinatorLayout.p(floatingActionButton);
            int size = listP.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                View view2 = listP.get(i3);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof CoordinatorLayout.e ? ((CoordinatorLayout.e) layoutParams).a instanceof BottomSheetBehavior : false) && u(view2, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (t(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.w(floatingActionButton, i);
            Rect rect = floatingActionButton.p;
            if (rect == null || rect.centerX() <= 0 || rect.centerY() <= 0) {
                return true;
            }
            CoordinatorLayout.e eVar = (CoordinatorLayout.e) floatingActionButton.getLayoutParams();
            int i4 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) eVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) eVar).leftMargin ? -rect.left : 0;
            if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) eVar).bottomMargin) {
                i2 = rect.bottom;
            } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) eVar).topMargin) {
                i2 = -rect.top;
            }
            if (i2 != 0) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                floatingActionButton.offsetTopAndBottom(i2);
            }
            if (i4 == 0) {
                return true;
            }
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            floatingActionButton.offsetLeftAndRight(i4);
            return true;
        }

        public final boolean s(View view, FloatingActionButton floatingActionButton) {
            return this.c && ((CoordinatorLayout.e) floatingActionButton.getLayoutParams()).f == view.getId() && floatingActionButton.getUserSetVisibility() == 0;
        }

        public void setInternalAutoHideListener(a aVar) {
            this.b = aVar;
        }

        public final boolean t(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!s(appBarLayout, floatingActionButton)) {
                return false;
            }
            if (this.a == null) {
                this.a = new Rect();
            }
            Rect rect = this.a;
            p80.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.i(this.b, false);
                return true;
            }
            floatingActionButton.o(this.b, false);
            return true;
        }

        public final boolean u(View view, FloatingActionButton floatingActionButton) {
            if (!s(view, floatingActionButton)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.e) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.i(this.b, false);
                return true;
            }
            floatingActionButton.o(this.b, false);
            return true;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.c = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingActionButtonStyle);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            getImpl().m(this.j);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = u;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.p = new Rect();
        this.q = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.FloatingActionButton, i, i2, new int[0]);
        this.f = rc1.a(context2, typedArrayD, R$styleable.FloatingActionButton_backgroundTint);
        this.g = yn3.e(typedArrayD.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.j = rc1.a(context2, typedArrayD, R$styleable.FloatingActionButton_rippleColor);
        this.k = typedArrayD.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.l = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_fabCustomSize, 0);
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayD.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = typedArrayD.getDimension(R$styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = typedArrayD.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.o = typedArrayD.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.mtrl_fab_min_touch_target);
        setMaxImageSize(typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_maxImageSize, 0));
        fj1 fj1VarA = fj1.a(context2, typedArrayD, R$styleable.FloatingActionButton_showMotionSpec);
        fj1 fj1VarA2 = fj1.a(context2, typedArrayD, R$styleable.FloatingActionButton_hideMotionSpec);
        uj2 uj2Var = new uj2(uj2.c(context2, attributeSet, i, i2, uj2.m));
        boolean z = typedArrayD.getBoolean(R$styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(typedArrayD.getBoolean(R$styleable.FloatingActionButton_android_enabled, true));
        typedArrayD.recycle();
        AppCompatImageHelper appCompatImageHelper = new AppCompatImageHelper(this);
        this.r = appCompatImageHelper;
        appCompatImageHelper.b(attributeSet, i);
        this.s = new ni0(this);
        getImpl().n(uj2Var);
        getImpl().g(this.f, this.g, this.j, dimensionPixelSize);
        getImpl().k = dimensionPixelSize2;
        d impl = getImpl();
        if (impl.h != dimension) {
            impl.h = dimension;
            impl.k(dimension, impl.i, impl.j);
        }
        d impl2 = getImpl();
        if (impl2.i != dimension2) {
            impl2.i = dimension2;
            impl2.k(impl2.h, dimension2, impl2.j);
        }
        d impl3 = getImpl();
        if (impl3.j != dimension3) {
            impl3.j = dimension3;
            impl3.k(impl3.h, impl3.i, dimension3);
        }
        getImpl().m = fj1VarA;
        getImpl().n = fj1VarA2;
        getImpl().f = z;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public static abstract class a {
        public void b() {
        }

        public void a(FloatingActionButton floatingActionButton) {
        }
    }
}
