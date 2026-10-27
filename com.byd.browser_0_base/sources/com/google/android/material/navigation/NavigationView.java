package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import cl1.c;
import cl1.h;
import com.google.android.material.R$attr;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import defpackage.C0195l;
import defpackage.bz;
import defpackage.cl1;
import defpackage.in3;
import defpackage.kl1;
import defpackage.oj2;
import defpackage.om3;
import defpackage.pt3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.vj2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout {
    public static final int[] v = {R.attr.state_checked};
    public static final int[] w = {-16842910};
    public static final int x = R$style.Widget_Design_NavigationView;
    public final NavigationMenu j;
    public final cl1 k;
    public final int l;
    public final int[] m;
    public SupportMenuInflater n;
    public kl1 o;
    public boolean p;
    public boolean q;
    public final int r;
    public final int s;
    public Path t;
    public final RectF u;

    public interface b {
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.n == null) {
            this.n = new SupportMenuInflater(getContext());
        }
        return this.n;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public final void a(pt3 pt3Var) {
        cl1 cl1Var = this.k;
        cl1Var.getClass();
        int iE = pt3Var.e();
        if (cl1Var.B != iE) {
            cl1Var.B = iE;
            int i = (cl1Var.f.getChildCount() == 0 && cl1Var.z) ? cl1Var.B : 0;
            NavigationMenuView navigationMenuView = cl1Var.e;
            navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
        }
        NavigationMenuView navigationMenuView2 = cl1Var.e;
        navigationMenuView2.setPadding(0, navigationMenuView2.getPaddingTop(), 0, pt3Var.b());
        om3.c(cl1Var.f, pt3Var);
    }

    public final ColorStateList b(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListB = bz.b(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListB.getDefaultColor();
        int[] iArr = w;
        return new ColorStateList(new int[][]{iArr, v, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListB.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public final InsetDrawable c(TintTypedArray tintTypedArray, ColorStateList colorStateList) {
        sc1 sc1Var = new sc1(new uj2(uj2.a(getContext(), tintTypedArray.i(R$styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.i(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0), new C0195l(0))));
        sc1Var.n(colorStateList);
        return new InsetDrawable((Drawable) sc1Var, tintTypedArray.d(R$styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.d(R$styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.d(R$styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.d(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.t == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.t);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(iSave);
    }

    public MenuItem getCheckedItem() {
        return this.k.i.i;
    }

    public int getDividerInsetEnd() {
        return this.k.w;
    }

    public int getDividerInsetStart() {
        return this.k.v;
    }

    public int getHeaderCount() {
        return this.k.f.getChildCount();
    }

    public Drawable getItemBackground() {
        return this.k.p;
    }

    public int getItemHorizontalPadding() {
        return this.k.r;
    }

    public int getItemIconPadding() {
        return this.k.t;
    }

    public ColorStateList getItemIconTintList() {
        return this.k.o;
    }

    public int getItemMaxLines() {
        return this.k.A;
    }

    public ColorStateList getItemTextColor() {
        return this.k.n;
    }

    public int getItemVerticalPadding() {
        return this.k.s;
    }

    public Menu getMenu() {
        return this.j;
    }

    public int getSubheaderInsetEnd() {
        this.k.getClass();
        return 0;
    }

    public int getSubheaderInsetStart() {
        return this.k.x;
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.d0(this);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.o);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int i3 = this.l;
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), i3), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        this.j.t(savedState.g);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.g = bundle;
        this.j.v(bundle);
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        super.onSizeChanged(i, i2, i3, i4);
        boolean z = getParent() instanceof DrawerLayout;
        RectF rectF = this.u;
        if (!z || (i5 = this.s) <= 0 || !(getBackground() instanceof sc1)) {
            this.t = null;
            rectF.setEmpty();
            return;
        }
        sc1 sc1Var = (sc1) getBackground();
        uj2 uj2Var = sc1Var.e.a;
        uj2Var.getClass();
        uj2.a aVar = new uj2.a(uj2Var);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (Gravity.getAbsoluteGravity(this.r, om3.e.c(this)) == 3) {
            float f = i5;
            aVar.g(f);
            aVar.e(f);
        } else {
            float f2 = i5;
            aVar.f(f2);
            aVar.d(f2);
        }
        sc1Var.setShapeAppearanceModel(new uj2(aVar));
        if (this.t == null) {
            this.t = new Path();
        }
        this.t.reset();
        rectF.set(0.0f, 0.0f, i, i2);
        vj2 vj2Var = vj2.a.a;
        sc1.b bVar = sc1Var.e;
        vj2Var.a(bVar.a, bVar.j, rectF, null, this.t);
        invalidate();
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.q = z;
    }

    public void setCheckedItem(int i) {
        MenuItem menuItemFindItem = this.j.findItem(i);
        if (menuItemFindItem != null) {
            this.k.i.E((MenuItemImpl) menuItemFindItem);
        }
    }

    public void setDividerInsetEnd(int i) {
        cl1 cl1Var = this.k;
        cl1Var.w = i;
        cl1Var.d(false);
    }

    public void setDividerInsetStart(int i) {
        cl1 cl1Var = this.k;
        cl1Var.v = i;
        cl1Var.d(false);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oj2.c0(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        cl1 cl1Var = this.k;
        cl1Var.p = drawable;
        cl1Var.d(false);
    }

    public void setItemBackgroundResource(int i) {
        Context context = getContext();
        Object obj = bz.a;
        setItemBackground(context.getDrawable(i));
    }

    public void setItemHorizontalPadding(int i) {
        cl1 cl1Var = this.k;
        cl1Var.r = i;
        cl1Var.d(false);
    }

    public void setItemHorizontalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        cl1 cl1Var = this.k;
        cl1Var.r = dimensionPixelSize;
        cl1Var.d(false);
    }

    public void setItemIconPadding(int i) {
        cl1 cl1Var = this.k;
        cl1Var.t = i;
        cl1Var.d(false);
    }

    public void setItemIconPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        cl1 cl1Var = this.k;
        cl1Var.t = dimensionPixelSize;
        cl1Var.d(false);
    }

    public void setItemIconSize(int i) {
        cl1 cl1Var = this.k;
        if (cl1Var.u != i) {
            cl1Var.u = i;
            cl1Var.y = true;
            cl1Var.d(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        cl1 cl1Var = this.k;
        cl1Var.o = colorStateList;
        cl1Var.d(false);
    }

    public void setItemMaxLines(int i) {
        cl1 cl1Var = this.k;
        cl1Var.A = i;
        cl1Var.d(false);
    }

    public void setItemTextAppearance(int i) {
        cl1 cl1Var = this.k;
        cl1Var.m = i;
        cl1Var.d(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        cl1 cl1Var = this.k;
        cl1Var.n = colorStateList;
        cl1Var.d(false);
    }

    public void setItemVerticalPadding(int i) {
        cl1 cl1Var = this.k;
        cl1Var.s = i;
        cl1Var.d(false);
    }

    public void setItemVerticalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        cl1 cl1Var = this.k;
        cl1Var.s = dimensionPixelSize;
        cl1Var.d(false);
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        cl1 cl1Var = this.k;
        if (cl1Var != null) {
            cl1Var.D = i;
            NavigationMenuView navigationMenuView = cl1Var.e;
            if (navigationMenuView != null) {
                navigationMenuView.setOverScrollMode(i);
            }
        }
    }

    public void setSubheaderInsetEnd(int i) {
        cl1 cl1Var = this.k;
        cl1Var.x = i;
        cl1Var.d(false);
    }

    public void setSubheaderInsetStart(int i) {
        cl1 cl1Var = this.k;
        cl1Var.x = i;
        cl1Var.d(false);
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.p = z;
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public Bundle g;

        public class a implements Parcelable.ClassLoaderCreator<SavedState> {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeBundle(this.g);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationView(Context context, AttributeSet attributeSet, int i) {
        ColorStateList colorStateListB;
        int i2 = x;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        cl1 cl1Var = new cl1();
        this.k = cl1Var;
        this.m = new int[2];
        this.p = true;
        this.q = true;
        this.r = 0;
        this.s = 0;
        this.u = new RectF();
        Context context2 = getContext();
        NavigationMenu navigationMenu = new NavigationMenu(context2);
        this.j = navigationMenu;
        TintTypedArray tintTypedArrayE = r83.e(context2, attributeSet, R$styleable.NavigationView, i, i2, new int[0]);
        int i3 = R$styleable.NavigationView_android_background;
        if (tintTypedArrayE.l(i3)) {
            Drawable drawableE = tintTypedArrayE.e(i3);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(this, drawableE);
        }
        this.s = tintTypedArrayE.d(R$styleable.NavigationView_drawerLayoutCornerSize, 0);
        this.r = tintTypedArrayE.h(R$styleable.NavigationView_android_layout_gravity, 0);
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            uj2 uj2Var = new uj2(uj2.b(context2, attributeSet, i, i2));
            Drawable background = getBackground();
            sc1 sc1Var = new sc1(uj2Var);
            if (background instanceof ColorDrawable) {
                sc1Var.n(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            sc1Var.k(context2);
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.d.n(this, sc1Var);
        }
        if (tintTypedArrayE.l(R$styleable.NavigationView_elevation)) {
            setElevation(tintTypedArrayE.d(r2, 0));
        }
        setFitsSystemWindows(tintTypedArrayE.a(R$styleable.NavigationView_android_fitsSystemWindows, false));
        this.l = tintTypedArrayE.d(R$styleable.NavigationView_android_maxWidth, 0);
        int i4 = R$styleable.NavigationView_subheaderColor;
        ColorStateList colorStateListB2 = tintTypedArrayE.l(i4) ? tintTypedArrayE.b(i4) : null;
        int i5 = R$styleable.NavigationView_subheaderTextAppearance;
        int i6 = tintTypedArrayE.l(i5) ? tintTypedArrayE.i(i5, 0) : 0;
        if (i6 == 0 && colorStateListB2 == null) {
            colorStateListB2 = b(R.attr.textColorSecondary);
        }
        int i7 = R$styleable.NavigationView_itemIconTint;
        if (tintTypedArrayE.l(i7)) {
            colorStateListB = tintTypedArrayE.b(i7);
        } else {
            colorStateListB = b(R.attr.textColorSecondary);
        }
        int i8 = R$styleable.NavigationView_itemTextAppearance;
        int i9 = tintTypedArrayE.l(i8) ? tintTypedArrayE.i(i8, 0) : 0;
        int i10 = R$styleable.NavigationView_itemIconSize;
        if (tintTypedArrayE.l(i10)) {
            setItemIconSize(tintTypedArrayE.d(i10, 0));
        }
        int i11 = R$styleable.NavigationView_itemTextColor;
        ColorStateList colorStateListB3 = tintTypedArrayE.l(i11) ? tintTypedArrayE.b(i11) : null;
        if (i9 == 0 && colorStateListB3 == null) {
            colorStateListB3 = b(R.attr.textColorPrimary);
        }
        Drawable drawableE2 = tintTypedArrayE.e(R$styleable.NavigationView_itemBackground);
        if (drawableE2 == null) {
            if (tintTypedArrayE.l(R$styleable.NavigationView_itemShapeAppearance) || tintTypedArrayE.l(R$styleable.NavigationView_itemShapeAppearanceOverlay)) {
                drawableE2 = c(tintTypedArrayE, rc1.b(getContext(), tintTypedArrayE, R$styleable.NavigationView_itemShapeFillColor));
                ColorStateList colorStateListB4 = rc1.b(context2, tintTypedArrayE, R$styleable.NavigationView_itemRippleColor);
                if (colorStateListB4 != null) {
                    cl1Var.q = new RippleDrawable(colorStateListB4, null, c(tintTypedArrayE, null));
                    cl1Var.d(false);
                }
            }
        }
        int i12 = R$styleable.NavigationView_itemHorizontalPadding;
        if (tintTypedArrayE.l(i12)) {
            setItemHorizontalPadding(tintTypedArrayE.d(i12, 0));
        }
        int i13 = R$styleable.NavigationView_itemVerticalPadding;
        if (tintTypedArrayE.l(i13)) {
            setItemVerticalPadding(tintTypedArrayE.d(i13, 0));
        }
        setDividerInsetStart(tintTypedArrayE.d(R$styleable.NavigationView_dividerInsetStart, 0));
        setDividerInsetEnd(tintTypedArrayE.d(R$styleable.NavigationView_dividerInsetEnd, 0));
        setSubheaderInsetStart(tintTypedArrayE.d(R$styleable.NavigationView_subheaderInsetStart, 0));
        setSubheaderInsetEnd(tintTypedArrayE.d(R$styleable.NavigationView_subheaderInsetEnd, 0));
        setTopInsetScrimEnabled(tintTypedArrayE.a(R$styleable.NavigationView_topInsetScrimEnabled, this.p));
        setBottomInsetScrimEnabled(tintTypedArrayE.a(R$styleable.NavigationView_bottomInsetScrimEnabled, this.q));
        int iD = tintTypedArrayE.d(R$styleable.NavigationView_itemIconPadding, 0);
        setItemMaxLines(tintTypedArrayE.h(R$styleable.NavigationView_itemMaxLines, 1));
        navigationMenu.e = new a();
        cl1Var.h = 1;
        cl1Var.h(context2, navigationMenu);
        if (i6 != 0) {
            cl1Var.k = i6;
            cl1Var.d(false);
        }
        cl1Var.l = colorStateListB2;
        cl1Var.d(false);
        cl1Var.o = colorStateListB;
        cl1Var.d(false);
        int overScrollMode = getOverScrollMode();
        cl1Var.D = overScrollMode;
        NavigationMenuView navigationMenuView = cl1Var.e;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(overScrollMode);
        }
        if (i9 != 0) {
            cl1Var.m = i9;
            cl1Var.d(false);
        }
        cl1Var.n = colorStateListB3;
        cl1Var.d(false);
        cl1Var.p = drawableE2;
        cl1Var.d(false);
        cl1Var.t = iD;
        cl1Var.d(false);
        navigationMenu.b(cl1Var, navigationMenu.a);
        if (cl1Var.e == null) {
            NavigationMenuView navigationMenuView2 = (NavigationMenuView) cl1Var.j.inflate(R$layout.design_navigation_menu, (ViewGroup) this, false);
            cl1Var.e = navigationMenuView2;
            navigationMenuView2.setAccessibilityDelegateCompat(cl1Var.new h(cl1Var.e));
            if (cl1Var.i == null) {
                cl1Var.i = cl1Var.new c();
            }
            int i14 = cl1Var.D;
            if (i14 != -1) {
                cl1Var.e.setOverScrollMode(i14);
            }
            cl1Var.f = (LinearLayout) cl1Var.j.inflate(R$layout.design_navigation_item_header, (ViewGroup) cl1Var.e, false);
            cl1Var.e.setAdapter(cl1Var.i);
        }
        addView(cl1Var.e);
        int i15 = R$styleable.NavigationView_menu;
        if (tintTypedArrayE.l(i15)) {
            int i16 = tintTypedArrayE.i(i15, 0);
            cl1.c cVar = cl1Var.i;
            if (cVar != null) {
                cVar.j = true;
            }
            getMenuInflater().inflate(i16, navigationMenu);
            cl1.c cVar2 = cl1Var.i;
            if (cVar2 != null) {
                cVar2.j = false;
            }
            cl1Var.d(false);
        }
        int i17 = R$styleable.NavigationView_headerLayout;
        if (tintTypedArrayE.l(i17)) {
            cl1Var.f.addView(cl1Var.j.inflate(tintTypedArrayE.i(i17, 0), (ViewGroup) cl1Var.f, false));
            NavigationMenuView navigationMenuView3 = cl1Var.e;
            navigationMenuView3.setPadding(0, 0, 0, navigationMenuView3.getPaddingBottom());
        }
        tintTypedArrayE.n();
        this.o = new kl1(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this.o);
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.j.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.k.i.E((MenuItemImpl) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public class a implements MenuBuilder.Callback {
        public a() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            NavigationView.this.getClass();
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
        }
    }

    public void setNavigationItemSelectedListener(b bVar) {
    }
}
