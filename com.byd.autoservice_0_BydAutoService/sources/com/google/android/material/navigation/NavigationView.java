package com.google.android.material.navigation;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.R$attr;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import i2.e;
import i2.f;
import i2.k;
import l0.b0;
import l0.t;
import n.g;
import n2.h;
import n2.i;
import n2.m;
import o.e0;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f2384g = {R.attr.state_checked};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f2385h = {-16842910};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f2386i = R$style.Widget_Design_NavigationView;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final e f2387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f f2388k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f2389l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2390m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int[] f2391n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public MenuInflater f2392o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ViewTreeObserver.OnGlobalLayoutListener f2393p;

    public class a implements g.a {
        public a() {
        }

        @Override // n.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            c cVar = NavigationView.this.f2389l;
            return cVar != null && cVar.a(menuItem);
        }

        @Override // n.g.a
        public void b(g gVar) {
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.f2391n);
            boolean z3 = NavigationView.this.f2391n[1] == 0;
            NavigationView.this.f2388k.y(z3);
            NavigationView.this.setDrawTopInsetForeground(z3);
            Activity activityA = i2.b.a(NavigationView.this.getContext());
            if (activityA == null || Build.VERSION.SDK_INT < 21) {
                return;
            }
            NavigationView.this.setDrawBottomInsetForeground((activityA.findViewById(R.id.content).getHeight() == NavigationView.this.getHeight()) && (Color.alpha(activityA.getWindow().getNavigationBarColor()) != 0));
        }
    }

    public interface c {
        boolean a(MenuItem menuItem);
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.f2392o == null) {
            this.f2392o = new m.g(getContext());
        }
        return this.f2392o;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void a(b0 b0Var) {
        this.f2388k.l(b0Var);
    }

    public final ColorStateList d(int i3) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i3, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = j.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i4 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f2385h;
        return new ColorStateList(new int[][]{iArr, f2384g, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i4, defaultColor});
    }

    public final Drawable e(e0 e0Var) {
        h hVar = new h(m.b(getContext(), e0Var.n(R$styleable.NavigationView_itemShapeAppearance, 0), e0Var.n(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        hVar.X(k2.c.b(getContext(), e0Var, R$styleable.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) hVar, e0Var.f(R$styleable.NavigationView_itemShapeInsetStart, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetTop, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetEnd, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public final boolean f(e0 e0Var) {
        return e0Var.s(R$styleable.NavigationView_itemShapeAppearance) || e0Var.s(R$styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    public View g(int i3) {
        return this.f2388k.x(i3);
    }

    public MenuItem getCheckedItem() {
        return this.f2388k.o();
    }

    public int getHeaderCount() {
        return this.f2388k.p();
    }

    public Drawable getItemBackground() {
        return this.f2388k.q();
    }

    public int getItemHorizontalPadding() {
        return this.f2388k.r();
    }

    public int getItemIconPadding() {
        return this.f2388k.s();
    }

    public ColorStateList getItemIconTintList() {
        return this.f2388k.v();
    }

    public int getItemMaxLines() {
        return this.f2388k.t();
    }

    public ColorStateList getItemTextColor() {
        return this.f2388k.u();
    }

    public Menu getMenu() {
        return this.f2387j;
    }

    public void h(int i3) {
        this.f2388k.K(true);
        getMenuInflater().inflate(i3, this.f2387j);
        this.f2388k.K(false);
        this.f2388k.n(false);
    }

    public final void i() {
        this.f2393p = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.f2393p);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.f2393p);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.f2393p);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        if (mode == Integer.MIN_VALUE) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i3), this.f2390m), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else if (mode == 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(this.f2390m, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i3, i4);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        this.f2387j.S(savedState.f2394d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f2394d = bundle;
        this.f2387j.U(bundle);
        return savedState;
    }

    public void setCheckedItem(int i3) {
        MenuItem menuItemFindItem = this.f2387j.findItem(i3);
        if (menuItemFindItem != null) {
            this.f2388k.z((n.i) menuItemFindItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f3);
        }
        i.d(this, f3);
    }

    public void setItemBackground(Drawable drawable) {
        this.f2388k.B(drawable);
    }

    public void setItemBackgroundResource(int i3) {
        setItemBackground(b0.a.d(getContext(), i3));
    }

    public void setItemHorizontalPadding(int i3) {
        this.f2388k.C(i3);
    }

    public void setItemHorizontalPaddingResource(int i3) {
        this.f2388k.C(getResources().getDimensionPixelSize(i3));
    }

    public void setItemIconPadding(int i3) {
        this.f2388k.D(i3);
    }

    public void setItemIconPaddingResource(int i3) {
        this.f2388k.D(getResources().getDimensionPixelSize(i3));
    }

    public void setItemIconSize(int i3) {
        this.f2388k.E(i3);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f2388k.F(colorStateList);
    }

    public void setItemMaxLines(int i3) {
        this.f2388k.G(i3);
    }

    public void setItemTextAppearance(int i3) {
        this.f2388k.H(i3);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f2388k.I(colorStateList);
    }

    public void setNavigationItemSelectedListener(c cVar) {
        this.f2389l = cVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i3) {
        super.setOverScrollMode(i3);
        f fVar = this.f2388k;
        if (fVar != null) {
            fVar.J(i3);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f2394d;

        public static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f2394d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeBundle(this.f2394d);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i3) {
        ColorStateList colorStateListD;
        int iN;
        boolean z3;
        int i4 = f2386i;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        f fVar = new f();
        this.f2388k = fVar;
        this.f2391n = new int[2];
        Context context2 = getContext();
        e eVar = new e(context2);
        this.f2387j = eVar;
        e0 e0VarI = k.i(context2, attributeSet, R$styleable.NavigationView, i3, i4, new int[0]);
        int i5 = R$styleable.NavigationView_android_background;
        if (e0VarI.s(i5)) {
            t.m0(this, e0VarI.g(i5));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            Drawable background = getBackground();
            h hVar = new h();
            if (background instanceof ColorDrawable) {
                hVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            hVar.N(context2);
            t.m0(this, hVar);
        }
        if (e0VarI.s(R$styleable.NavigationView_elevation)) {
            setElevation(e0VarI.f(r13, 0));
        }
        setFitsSystemWindows(e0VarI.a(R$styleable.NavigationView_android_fitsSystemWindows, false));
        this.f2390m = e0VarI.f(R$styleable.NavigationView_android_maxWidth, 0);
        int i6 = R$styleable.NavigationView_itemIconTint;
        if (e0VarI.s(i6)) {
            colorStateListD = e0VarI.c(i6);
        } else {
            colorStateListD = d(R.attr.textColorSecondary);
        }
        int i7 = R$styleable.NavigationView_itemTextAppearance;
        if (e0VarI.s(i7)) {
            iN = e0VarI.n(i7, 0);
            z3 = true;
        } else {
            iN = 0;
            z3 = false;
        }
        int i8 = R$styleable.NavigationView_itemIconSize;
        if (e0VarI.s(i8)) {
            setItemIconSize(e0VarI.f(i8, 0));
        }
        int i9 = R$styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListC = e0VarI.s(i9) ? e0VarI.c(i9) : null;
        if (!z3 && colorStateListC == null) {
            colorStateListC = d(R.attr.textColorPrimary);
        }
        Drawable drawableG = e0VarI.g(R$styleable.NavigationView_itemBackground);
        if (drawableG == null && f(e0VarI)) {
            drawableG = e(e0VarI);
        }
        int i10 = R$styleable.NavigationView_itemHorizontalPadding;
        if (e0VarI.s(i10)) {
            fVar.C(e0VarI.f(i10, 0));
        }
        int iF = e0VarI.f(R$styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(e0VarI.k(R$styleable.NavigationView_itemMaxLines, 1));
        eVar.V(new a());
        fVar.A(1);
        fVar.f(context2, eVar);
        fVar.F(colorStateListD);
        fVar.J(getOverScrollMode());
        if (z3) {
            fVar.H(iN);
        }
        fVar.I(colorStateListC);
        fVar.B(drawableG);
        fVar.D(iF);
        eVar.b(fVar);
        addView((View) fVar.w(this));
        int i11 = R$styleable.NavigationView_menu;
        if (e0VarI.s(i11)) {
            h(e0VarI.n(i11, 0));
        }
        int i12 = R$styleable.NavigationView_headerLayout;
        if (e0VarI.s(i12)) {
            g(e0VarI.n(i12, 0));
        }
        e0VarI.w();
        i();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.f2387j.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.f2388k.z((n.i) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
