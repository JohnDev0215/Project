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
import e6.h;
import e6.i;
import e6.m;
import o0.b0;
import o0.t;
import q.g;
import r.e0;
import z5.e;
import z5.f;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationView extends ScrimInsetsFrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f3965i = {R.attr.state_checked};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f3966j = {-16842910};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int f3967k = R$style.Widget_Design_NavigationView;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final e f3968l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f3969m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public c f3970n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f3971o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int[] f3972p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public MenuInflater f3973q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ViewTreeObserver.OnGlobalLayoutListener f3974r;

    public class a implements g.a {
        public a() {
        }

        @Override // q.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            c cVar = NavigationView.this.f3970n;
            return cVar != null && cVar.a(menuItem);
        }

        @Override // q.g.a
        public void b(g gVar) {
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            NavigationView navigationView = NavigationView.this;
            navigationView.getLocationOnScreen(navigationView.f3972p);
            boolean z7 = NavigationView.this.f3972p[1] == 0;
            NavigationView.this.f3969m.y(z7);
            NavigationView.this.setDrawTopInsetForeground(z7);
            Activity activityA = z5.b.a(NavigationView.this.getContext());
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
        if (this.f3973q == null) {
            this.f3973q = new p.g(getContext());
        }
        return this.f3973q;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public void a(b0 b0Var) {
        this.f3969m.h(b0Var);
    }

    public final ColorStateList d(int i8) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i8, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = m.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i9 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f3966j;
        return new ColorStateList(new int[][]{iArr, f3965i, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i9, defaultColor});
    }

    public final Drawable e(e0 e0Var) {
        h hVar = new h(m.b(getContext(), e0Var.n(R$styleable.NavigationView_itemShapeAppearance, 0), e0Var.n(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).m());
        hVar.X(b6.c.b(getContext(), e0Var, R$styleable.NavigationView_itemShapeFillColor));
        return new InsetDrawable((Drawable) hVar, e0Var.f(R$styleable.NavigationView_itemShapeInsetStart, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetTop, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetEnd, 0), e0Var.f(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public final boolean f(e0 e0Var) {
        return e0Var.s(R$styleable.NavigationView_itemShapeAppearance) || e0Var.s(R$styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    public View g(int i8) {
        return this.f3969m.x(i8);
    }

    public MenuItem getCheckedItem() {
        return this.f3969m.o();
    }

    public int getHeaderCount() {
        return this.f3969m.p();
    }

    public Drawable getItemBackground() {
        return this.f3969m.q();
    }

    public int getItemHorizontalPadding() {
        return this.f3969m.r();
    }

    public int getItemIconPadding() {
        return this.f3969m.s();
    }

    public ColorStateList getItemIconTintList() {
        return this.f3969m.v();
    }

    public int getItemMaxLines() {
        return this.f3969m.t();
    }

    public ColorStateList getItemTextColor() {
        return this.f3969m.u();
    }

    public Menu getMenu() {
        return this.f3968l;
    }

    public void h(int i8) {
        this.f3969m.K(true);
        getMenuInflater().inflate(i8, this.f3968l);
        this.f3969m.K(false);
        this.f3969m.g(false);
    }

    public final void i() {
        this.f3974r = new b();
        getViewTreeObserver().addOnGlobalLayoutListener(this.f3974r);
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
            getViewTreeObserver().removeGlobalOnLayoutListener(this.f3974r);
        } else {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.f3974r);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        if (mode == Integer.MIN_VALUE) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i8), this.f3971o), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else if (mode == 0) {
            i8 = View.MeasureSpec.makeMeasureSpec(this.f3971o, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i8, i9);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        this.f3968l.S(savedState.f3975f);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f3975f = bundle;
        this.f3968l.U(bundle);
        return savedState;
    }

    public void setCheckedItem(int i8) {
        MenuItem menuItemFindItem = this.f3968l.findItem(i8);
        if (menuItemFindItem != null) {
            this.f3969m.z((q.i) menuItemFindItem);
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f8);
        }
        i.d(this, f8);
    }

    public void setItemBackground(Drawable drawable) {
        this.f3969m.B(drawable);
    }

    public void setItemBackgroundResource(int i8) {
        setItemBackground(e0.a.d(getContext(), i8));
    }

    public void setItemHorizontalPadding(int i8) {
        this.f3969m.C(i8);
    }

    public void setItemHorizontalPaddingResource(int i8) {
        this.f3969m.C(getResources().getDimensionPixelSize(i8));
    }

    public void setItemIconPadding(int i8) {
        this.f3969m.D(i8);
    }

    public void setItemIconPaddingResource(int i8) {
        this.f3969m.D(getResources().getDimensionPixelSize(i8));
    }

    public void setItemIconSize(int i8) {
        this.f3969m.E(i8);
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f3969m.F(colorStateList);
    }

    public void setItemMaxLines(int i8) {
        this.f3969m.G(i8);
    }

    public void setItemTextAppearance(int i8) {
        this.f3969m.H(i8);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3969m.I(colorStateList);
    }

    public void setNavigationItemSelectedListener(c cVar) {
        this.f3970n = cVar;
    }

    @Override // android.view.View
    public void setOverScrollMode(int i8) {
        super.setOverScrollMode(i8);
        f fVar = this.f3969m;
        if (fVar != null) {
            fVar.J(i8);
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bundle f3975f;

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
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f3975f = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeBundle(this.f3975f);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i8) {
        ColorStateList colorStateListD;
        int iN;
        boolean z7;
        int i9 = f3967k;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        f fVar = new f();
        this.f3969m = fVar;
        this.f3972p = new int[2];
        Context context2 = getContext();
        e eVar = new e(context2);
        this.f3968l = eVar;
        e0 e0VarI = k.i(context2, attributeSet, R$styleable.NavigationView, i8, i9, new int[0]);
        int i10 = R$styleable.NavigationView_android_background;
        if (e0VarI.s(i10)) {
            t.o0(this, e0VarI.g(i10));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            Drawable background = getBackground();
            h hVar = new h();
            if (background instanceof ColorDrawable) {
                hVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            hVar.N(context2);
            t.o0(this, hVar);
        }
        if (e0VarI.s(R$styleable.NavigationView_elevation)) {
            setElevation(e0VarI.f(r13, 0));
        }
        setFitsSystemWindows(e0VarI.a(R$styleable.NavigationView_android_fitsSystemWindows, false));
        this.f3971o = e0VarI.f(R$styleable.NavigationView_android_maxWidth, 0);
        int i11 = R$styleable.NavigationView_itemIconTint;
        if (e0VarI.s(i11)) {
            colorStateListD = e0VarI.c(i11);
        } else {
            colorStateListD = d(R.attr.textColorSecondary);
        }
        int i12 = R$styleable.NavigationView_itemTextAppearance;
        if (e0VarI.s(i12)) {
            iN = e0VarI.n(i12, 0);
            z7 = true;
        } else {
            iN = 0;
            z7 = false;
        }
        int i13 = R$styleable.NavigationView_itemIconSize;
        if (e0VarI.s(i13)) {
            setItemIconSize(e0VarI.f(i13, 0));
        }
        int i14 = R$styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListC = e0VarI.s(i14) ? e0VarI.c(i14) : null;
        if (!z7 && colorStateListC == null) {
            colorStateListC = d(R.attr.textColorPrimary);
        }
        Drawable drawableG = e0VarI.g(R$styleable.NavigationView_itemBackground);
        if (drawableG == null && f(e0VarI)) {
            drawableG = e(e0VarI);
        }
        int i15 = R$styleable.NavigationView_itemHorizontalPadding;
        if (e0VarI.s(i15)) {
            fVar.C(e0VarI.f(i15, 0));
        }
        int iF = e0VarI.f(R$styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(e0VarI.k(R$styleable.NavigationView_itemMaxLines, 1));
        eVar.V(new a());
        fVar.A(1);
        fVar.b(context2, eVar);
        fVar.F(colorStateListD);
        fVar.J(getOverScrollMode());
        if (z7) {
            fVar.H(iN);
        }
        fVar.I(colorStateListC);
        fVar.B(drawableG);
        fVar.D(iF);
        eVar.b(fVar);
        addView((View) fVar.w(this));
        int i16 = R$styleable.NavigationView_menu;
        if (e0VarI.s(i16)) {
            h(e0VarI.n(i16, 0));
        }
        int i17 = R$styleable.NavigationView_headerLayout;
        if (e0VarI.s(i17)) {
            g(e0VarI.n(i17, 0));
        }
        e0VarI.w();
        i();
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.f3968l.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.f3969m.z((q.i) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
