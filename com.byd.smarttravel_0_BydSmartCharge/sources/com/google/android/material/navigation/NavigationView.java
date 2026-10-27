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
import b.b.e.j.g;
import b.b.f.e0;
import b.g.j.b0;
import b.g.j.t;
import c.b.a.a.q.e;
import c.b.a.a.q.f;
import c.b.a.a.q.k;
import c.b.a.a.v.h;
import c.b.a.a.v.i;
import c.b.a.a.v.m;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ScrimInsetsFrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout {
    public static final int[] g = {R.attr.state_checked};
    public static final int[] h = {-16842910};
    public static final int i = R$style.Widget_Design_NavigationView;
    public final e j;
    public final f k;
    public c l;
    public final int m;
    public final int[] n;
    public MenuInflater o;
    public ViewTreeObserver.OnGlobalLayoutListener p;

    public class a implements g.a {
        public a() {
        }

        @Override // b.b.e.j.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            c cVar = NavigationView.this.l;
            return cVar != null && cVar.a(menuItem);
        }

        @Override // b.b.e.j.g.a
        public void b(g gVar) {
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
            Activity activityA = c.b.a.a.q.b.a(NavigationView.this.getContext());
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
            this.o = new b.b.e.g(getContext());
        }
        return this.o;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void a(b0 b0Var) {
        this.k.l(b0Var);
    }

    public final ColorStateList d(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = b.b.b.a.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = h;
        return new ColorStateList(new int[][]{iArr, g, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    public final Drawable e(e0 e0Var) {
        h hVar = new h(m.b(getContext(), e0Var.n(R$styleable.NavigationView_itemShapeAppearance, 0), e0Var.n(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        hVar.X(c.b.a.a.s.c.b(getContext(), e0Var, R$styleable.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) hVar, e0Var.f(R$styleable.NavigationView_itemShapeInsetStart, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetTop, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetEnd, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public final boolean f(e0 e0Var) {
        return e0Var.s(R$styleable.NavigationView_itemShapeAppearance) || e0Var.s(R$styleable.NavigationView_itemShapeAppearanceOverlay);
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
        i.e(this);
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
        this.j.S(savedState.f2213d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f2213d = bundle;
        this.j.U(bundle);
        return savedState;
    }

    public void setCheckedItem(int i2) {
        MenuItem menuItemFindItem = this.j.findItem(i2);
        if (menuItemFindItem != null) {
            this.k.z((b.b.e.j.i) menuItemFindItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        }
        i.d(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        this.k.B(drawable);
    }

    public void setItemBackgroundResource(int i2) {
        setItemBackground(b.g.b.a.d(getContext(), i2));
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
        f fVar = this.k;
        if (fVar != null) {
            fVar.J(i2);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f2213d;

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
            this.f2213d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f2213d);
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
        super(c.b.a.a.z.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        f fVar = new f();
        this.k = fVar;
        this.n = new int[2];
        Context context2 = getContext();
        e eVar = new e(context2);
        this.j = eVar;
        e0 e0VarI = k.i(context2, attributeSet, R$styleable.NavigationView, i2, i3, new int[0]);
        int i4 = R$styleable.NavigationView_android_background;
        if (e0VarI.s(i4)) {
            t.m0(this, e0VarI.g(i4));
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
        this.m = e0VarI.f(R$styleable.NavigationView_android_maxWidth, 0);
        int i5 = R$styleable.NavigationView_itemIconTint;
        if (e0VarI.s(i5)) {
            colorStateListD = e0VarI.c(i5);
        } else {
            colorStateListD = d(R.attr.textColorSecondary);
        }
        int i6 = R$styleable.NavigationView_itemTextAppearance;
        if (e0VarI.s(i6)) {
            iN = e0VarI.n(i6, 0);
            z = true;
        } else {
            iN = 0;
            z = false;
        }
        int i7 = R$styleable.NavigationView_itemIconSize;
        if (e0VarI.s(i7)) {
            setItemIconSize(e0VarI.f(i7, 0));
        }
        int i8 = R$styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListC = e0VarI.s(i8) ? e0VarI.c(i8) : null;
        if (!z && colorStateListC == null) {
            colorStateListC = d(R.attr.textColorPrimary);
        }
        Drawable drawableG = e0VarI.g(R$styleable.NavigationView_itemBackground);
        if (drawableG == null && f(e0VarI)) {
            drawableG = e(e0VarI);
        }
        int i9 = R$styleable.NavigationView_itemHorizontalPadding;
        if (e0VarI.s(i9)) {
            fVar.C(e0VarI.f(i9, 0));
        }
        int iF = e0VarI.f(R$styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(e0VarI.k(R$styleable.NavigationView_itemMaxLines, 1));
        eVar.V(new a());
        fVar.A(1);
        fVar.f(context2, eVar);
        fVar.F(colorStateListD);
        fVar.J(getOverScrollMode());
        if (z) {
            fVar.H(iN);
        }
        fVar.I(colorStateListC);
        fVar.B(drawableG);
        fVar.D(iF);
        eVar.b(fVar);
        addView((View) fVar.w(this));
        int i10 = R$styleable.NavigationView_menu;
        if (e0VarI.s(i10)) {
            h(e0VarI.n(i10, 0));
        }
        int i11 = R$styleable.NavigationView_headerLayout;
        if (e0VarI.s(i11)) {
            g(e0VarI.n(i11, 0));
        }
        e0VarI.w();
        i();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.j.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.k.z((b.b.e.j.i) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
