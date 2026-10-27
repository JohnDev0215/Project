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
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.f0;
import androidx.customview.view.AbsSavedState;
import b.g.k.b0;
import b.g.k.t;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.e;
import com.google.android.material.internal.f;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.x.h;
import d.b.a.a.x.i;
import d.b.a.a.x.m;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f4190g = {R.attr.state_checked};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f4191h = {-16842910};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f4192i = k.Widget_Design_NavigationView;
    private final e j;
    private final f k;
    c l;
    private final int m;
    private final int[] n;
    private MenuInflater o;
    private ViewTreeObserver.OnGlobalLayoutListener p;

    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            c cVar = NavigationView.this.l;
            return cVar != null && cVar.a(menuItem);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(g gVar) {
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.n);
            boolean z = NavigationView.this.n[1] == 0;
            NavigationView.this.k.y(z);
            NavigationView.this.setDrawTopInsetForeground(z);
            Activity activityA = com.google.android.material.internal.b.a(NavigationView.this.getContext());
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

    private ColorStateList d(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = b.a.k.a.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(b.a.a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f4191h;
        return new ColorStateList(new int[][]{iArr, f4190g, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    private final Drawable e(f0 f0Var) {
        h hVar = new h(m.b(getContext(), f0Var.n(l.NavigationView_itemShapeAppearance, 0), f0Var.n(l.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        hVar.X(d.b.a.a.u.c.b(getContext(), f0Var, l.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) hVar, f0Var.f(l.NavigationView_itemShapeInsetStart, 0), f0Var.f(l.NavigationView_itemShapeInsetTop, 0), f0Var.f(l.NavigationView_itemShapeInsetEnd, 0), f0Var.f(l.NavigationView_itemShapeInsetBottom, 0));
    }

    private boolean f(f0 f0Var) {
        return f0Var.s(l.NavigationView_itemShapeAppearance) || f0Var.s(l.NavigationView_itemShapeAppearanceOverlay);
    }

    private MenuInflater getMenuInflater() {
        if (this.o == null) {
            this.o = new b.a.o.g(getContext());
        }
        return this.o;
    }

    private void i() {
        this.p = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.p);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    protected void a(b0 b0Var) {
        this.k.l(b0Var);
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

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Build.VERSION.SDK_INT < 16) {
            getViewTreeObserver().removeGlobalOnLayoutListener(this.p);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.p);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), this.m), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else if (mode == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.m, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.j.S(savedState.f4193d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f4193d = bundle;
        this.j.U(bundle);
        return savedState;
    }

    public void setCheckedItem(int i2) {
        MenuItem menuItemFindItem = this.j.findItem(i2);
        if (menuItemFindItem != null) {
            this.k.z((androidx.appcompat.view.menu.i) menuItemFindItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f2);
        }
        i.d(this, f2);
    }

    public void setItemBackground(Drawable drawable) {
        this.k.B(drawable);
    }

    public void setItemBackgroundResource(int i2) {
        setItemBackground(androidx.core.content.a.d(getContext(), i2));
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
        public Bundle f4193d;

        static class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f4193d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.f4193d);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i2) {
        ColorStateList colorStateListD;
        int iN;
        boolean z;
        int i3 = f4192i;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        f fVar = new f();
        this.k = fVar;
        this.n = new int[2];
        Context context2 = getContext();
        e eVar = new e(context2);
        this.j = eVar;
        f0 f0VarI = com.google.android.material.internal.l.i(context2, attributeSet, l.NavigationView, i2, i3, new int[0]);
        int i4 = l.NavigationView_android_background;
        if (f0VarI.s(i4)) {
            t.m0(this, f0VarI.g(i4));
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
        if (f0VarI.s(l.NavigationView_elevation)) {
            setElevation(f0VarI.f(r13, 0));
        }
        setFitsSystemWindows(f0VarI.a(l.NavigationView_android_fitsSystemWindows, false));
        this.m = f0VarI.f(l.NavigationView_android_maxWidth, 0);
        int i5 = l.NavigationView_itemIconTint;
        if (f0VarI.s(i5)) {
            colorStateListD = f0VarI.c(i5);
        } else {
            colorStateListD = d(R.attr.textColorSecondary);
        }
        int i6 = l.NavigationView_itemTextAppearance;
        if (f0VarI.s(i6)) {
            iN = f0VarI.n(i6, 0);
            z = true;
        } else {
            iN = 0;
            z = false;
        }
        int i7 = l.NavigationView_itemIconSize;
        if (f0VarI.s(i7)) {
            setItemIconSize(f0VarI.f(i7, 0));
        }
        int i8 = l.NavigationView_itemTextColor;
        ColorStateList colorStateListC = f0VarI.s(i8) ? f0VarI.c(i8) : null;
        if (!z && colorStateListC == null) {
            colorStateListC = d(R.attr.textColorPrimary);
        }
        Drawable drawableG = f0VarI.g(l.NavigationView_itemBackground);
        if (drawableG == null && f(f0VarI)) {
            drawableG = e(f0VarI);
        }
        int i9 = l.NavigationView_itemHorizontalPadding;
        if (f0VarI.s(i9)) {
            fVar.C(f0VarI.f(i9, 0));
        }
        int iF = f0VarI.f(l.NavigationView_itemIconPadding, 0);
        setItemMaxLines(f0VarI.k(l.NavigationView_itemMaxLines, 1));
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
        int i10 = l.NavigationView_menu;
        if (f0VarI.s(i10)) {
            h(f0VarI.n(i10, 0));
        }
        int i11 = l.NavigationView_headerLayout;
        if (f0VarI.s(i11)) {
            g(f0VarI.n(i11, 0));
        }
        f0VarI.w();
        i();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.j.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.k.z((androidx.appcompat.view.menu.i) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
