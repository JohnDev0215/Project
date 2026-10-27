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
import defpackage.aj;
import defpackage.ba;
import defpackage.dj;
import defpackage.e7;
import defpackage.ej;
import defpackage.fl;
import defpackage.g1;
import defpackage.g3;
import defpackage.hk;
import defpackage.ik;
import defpackage.jj;
import defpackage.mk;
import defpackage.p1;
import defpackage.r1;
import defpackage.sj;
import defpackage.t9;
import defpackage.u0;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout {
    public static final int[] g = {R.attr.state_checked};
    public static final int[] h = {-16842910};
    public static final int i = R$style.Widget_Design_NavigationView;
    public final dj j;
    public final ej k;
    public c l;
    public final int m;
    public final int[] n;
    public MenuInflater o;
    public ViewTreeObserver.OnGlobalLayoutListener p;

    public class a implements p1.a {
        public a() {
        }

        @Override // p1.a
        public boolean a(p1 p1Var, MenuItem menuItem) {
            c cVar = NavigationView.this.l;
            return cVar != null && cVar.a(menuItem);
        }

        @Override // p1.a
        public void b(p1 p1Var) {
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.n);
            boolean z = NavigationView.this.n[1] == 0;
            NavigationView.this.k.y(z);
            NavigationView.this.setDrawTopInsetForeground(z);
            Activity activityA = aj.a(NavigationView.this.getContext());
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
        if (this.o == null) {
            this.o = new g1(getContext());
        }
        return this.o;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void a(ba baVar) {
        this.k.l(baVar);
    }

    public final ColorStateList d(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = u0.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = h;
        return new ColorStateList(new int[][]{iArr, g, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    public final Drawable e(g3 g3Var) {
        hk hkVar = new hk(mk.b(getContext(), g3Var.n(R$styleable.NavigationView_itemShapeAppearance, 0), g3Var.n(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        hkVar.X(sj.b(getContext(), g3Var, R$styleable.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) hkVar, g3Var.f(R$styleable.NavigationView_itemShapeInsetStart, 0), g3Var.f(R$styleable.NavigationView_itemShapeInsetTop, 0), g3Var.f(R$styleable.NavigationView_itemShapeInsetEnd, 0), g3Var.f(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public final boolean f(g3 g3Var) {
        return g3Var.s(R$styleable.NavigationView_itemShapeAppearance) || g3Var.s(R$styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    public View g(int i2) {
        return this.k.x(i2);
    }

    public MenuItem getCheckedItem() {
        return this.k.o();
    }

    public int getHeaderCount() {
        return this.k.p();
    }

    public Drawable getItemBackground() {
        return this.k.q();
    }

    public int getItemHorizontalPadding() {
        return this.k.r();
    }

    public int getItemIconPadding() {
        return this.k.s();
    }

    public ColorStateList getItemIconTintList() {
        return this.k.v();
    }

    public int getItemMaxLines() {
        return this.k.t();
    }

    public ColorStateList getItemTextColor() {
        return this.k.u();
    }

    public Menu getMenu() {
        return this.j;
    }

    public void h(int i2) {
        this.k.K(true);
        getMenuInflater().inflate(i2, this.j);
        this.k.K(false);
        this.k.n(false);
    }

    public final void i() {
        this.p = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.p);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ik.e(this);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.p);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.p);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), this.m), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.m, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        this.j.S(savedState.d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.d = bundle;
        this.j.U(bundle);
        return savedState;
    }

    public void setCheckedItem(int i2) {
        MenuItem menuItemFindItem = this.j.findItem(i2);
        if (menuItemFindItem != null) {
            this.k.z((r1) menuItemFindItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        }
        ik.d(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        this.k.B(drawable);
    }

    public void setItemBackgroundResource(int i2) {
        setItemBackground(e7.d(getContext(), i2));
    }

    public void setItemHorizontalPadding(int i2) {
        this.k.C(i2);
    }

    public void setItemHorizontalPaddingResource(int i2) {
        this.k.C(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconPadding(int i2) {
        this.k.D(i2);
    }

    public void setItemIconPaddingResource(int i2) {
        this.k.D(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconSize(int i2) {
        this.k.E(i2);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.k.F(colorStateList);
    }

    public void setItemMaxLines(int i2) {
        this.k.G(i2);
    }

    public void setItemTextAppearance(int i2) {
        this.k.H(i2);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.k.I(colorStateList);
    }

    public void setNavigationItemSelectedListener(c cVar) {
        this.l = cVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i2) {
        super.setOverScrollMode(i2);
        ej ejVar = this.k;
        if (ejVar != null) {
            ejVar.J(i2);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public Bundle d;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.d);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i2) {
        ColorStateList colorStateListD;
        int iN;
        boolean z;
        int i3 = i;
        super(fl.c(context, attributeSet, i2, i3), attributeSet, i2);
        ej ejVar = new ej();
        this.k = ejVar;
        this.n = new int[2];
        Context context2 = getContext();
        dj djVar = new dj(context2);
        this.j = djVar;
        g3 g3VarI = jj.i(context2, attributeSet, R$styleable.NavigationView, i2, i3, new int[0]);
        int i4 = R$styleable.NavigationView_android_background;
        if (g3VarI.s(i4)) {
            t9.m0(this, g3VarI.g(i4));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            Drawable background = getBackground();
            hk hkVar = new hk();
            if (background instanceof ColorDrawable) {
                hkVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            hkVar.N(context2);
            t9.m0(this, hkVar);
        }
        if (g3VarI.s(R$styleable.NavigationView_elevation)) {
            setElevation(g3VarI.f(r13, 0));
        }
        setFitsSystemWindows(g3VarI.a(R$styleable.NavigationView_android_fitsSystemWindows, false));
        this.m = g3VarI.f(R$styleable.NavigationView_android_maxWidth, 0);
        int i5 = R$styleable.NavigationView_itemIconTint;
        if (g3VarI.s(i5)) {
            colorStateListD = g3VarI.c(i5);
        } else {
            colorStateListD = d(R.attr.textColorSecondary);
        }
        int i6 = R$styleable.NavigationView_itemTextAppearance;
        if (g3VarI.s(i6)) {
            iN = g3VarI.n(i6, 0);
            z = true;
        } else {
            iN = 0;
            z = false;
        }
        int i7 = R$styleable.NavigationView_itemIconSize;
        if (g3VarI.s(i7)) {
            setItemIconSize(g3VarI.f(i7, 0));
        }
        int i8 = R$styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListC = g3VarI.s(i8) ? g3VarI.c(i8) : null;
        if (!z && colorStateListC == null) {
            colorStateListC = d(R.attr.textColorPrimary);
        }
        Drawable drawableG = g3VarI.g(R$styleable.NavigationView_itemBackground);
        if (drawableG == null && f(g3VarI)) {
            drawableG = e(g3VarI);
        }
        int i9 = R$styleable.NavigationView_itemHorizontalPadding;
        if (g3VarI.s(i9)) {
            ejVar.C(g3VarI.f(i9, 0));
        }
        int iF = g3VarI.f(R$styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(g3VarI.k(R$styleable.NavigationView_itemMaxLines, 1));
        djVar.V(new a());
        ejVar.A(1);
        ejVar.f(context2, djVar);
        ejVar.F(colorStateListD);
        ejVar.J(getOverScrollMode());
        if (z) {
            ejVar.H(iN);
        }
        ejVar.I(colorStateListC);
        ejVar.B(drawableG);
        ejVar.D(iF);
        djVar.b(ejVar);
        addView((View) ejVar.w(this));
        int i10 = R$styleable.NavigationView_menu;
        if (g3VarI.s(i10)) {
            h(g3VarI.n(i10, 0));
        }
        int i11 = R$styleable.NavigationView_headerLayout;
        if (g3VarI.s(i11)) {
            g(g3VarI.n(i11, 0));
        }
        g3VarI.w();
        i();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.j.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.k.z((r1) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
