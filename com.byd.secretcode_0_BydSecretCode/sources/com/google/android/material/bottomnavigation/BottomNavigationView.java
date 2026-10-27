package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.ba;
import defpackage.e7;
import defpackage.fl;
import defpackage.g1;
import defpackage.g3;
import defpackage.hk;
import defpackage.ik;
import defpackage.jj;
import defpackage.oj;
import defpackage.p1;
import defpackage.sj;
import defpackage.t9;
import defpackage.th;
import defpackage.xj;
import defpackage.y7;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {
    public static final int b = R$style.Widget_Design_BottomNavigationView;
    public final p1 c;
    public final BottomNavigationMenuView d;
    public final BottomNavigationPresenter e;
    public ColorStateList f;
    public MenuInflater g;
    public d h;
    public c i;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel, ClassLoader classLoader) {
            this.d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public class a implements p1.a {
        public a() {
        }

        @Override // p1.a
        public boolean a(p1 p1Var, MenuItem menuItem) {
            if (BottomNavigationView.this.i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.h == null || BottomNavigationView.this.h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.i.a(menuItem);
            return true;
        }

        @Override // p1.a
        public void b(p1 p1Var) {
        }
    }

    public class b implements oj.d {
        public b() {
        }

        @Override // oj.d
        public ba a(View view, ba baVar, oj.e eVar) {
            eVar.d += baVar.e();
            eVar.a(view);
            return baVar;
        }
    }

    public interface c {
        void a(MenuItem menuItem);
    }

    public interface d {
        boolean a(MenuItem menuItem);
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.g == null) {
            this.g = new g1(getContext());
        }
        return this.g;
    }

    public final void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(e7.b(context, R$color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    public final void d() {
        oj.b(this, new b());
    }

    public final hk e(Context context) {
        hk hkVar = new hk();
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            hkVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
        }
        hkVar.N(context);
        return hkVar;
    }

    public void f(int i) {
        this.e.l(true);
        getMenuInflater().inflate(i, this.c);
        this.e.l(false);
        this.e.n(true);
    }

    public Drawable getItemBackground() {
        return this.d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f;
    }

    public int getItemTextAppearanceActive() {
        return this.d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.c;
    }

    public int getSelectedItemId() {
        return this.d.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ik.e(this);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.f());
        this.c.S(savedState.d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.d = bundle;
        this.c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ik.d(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        this.d.setItemBackground(drawable);
        this.f = null;
    }

    public void setItemBackgroundResource(int i) {
        this.d.setItemBackgroundRes(i);
        this.f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.d.f() != z) {
            this.d.setItemHorizontalTranslationEnabled(z);
            this.e.n(false);
        }
    }

    public void setItemIconSize(int i) {
        this.d.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f == colorStateList) {
            if (colorStateList != null || this.d.getItemBackground() == null) {
                return;
            }
            this.d.setItemBackground(null);
            return;
        }
        this.f = colorStateList;
        if (colorStateList == null) {
            this.d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = xj.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = y7.r(gradientDrawable);
        y7.o(drawableR, colorStateListA);
        this.d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i) {
        this.d.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.d.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        if (this.d.getLabelVisibilityMode() != i) {
            this.d.setLabelVisibilityMode(i);
            this.e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.h = dVar;
    }

    public void setSelectedItemId(int i) {
        MenuItem menuItemFindItem = this.c.findItem(i);
        if (menuItemFindItem == null || this.c.O(menuItemFindItem, this.e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(fl.c(context, attributeSet, i, b), attributeSet, i);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.e = bottomNavigationPresenter;
        Context context2 = getContext();
        p1 thVar = new th(context2);
        this.c = thVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        thVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), thVar);
        int[] iArr = R$styleable.BottomNavigationView;
        int i2 = R$style.Widget_Design_BottomNavigationView;
        int i3 = R$styleable.BottomNavigationView_itemTextAppearanceInactive;
        int i4 = R$styleable.BottomNavigationView_itemTextAppearanceActive;
        g3 g3VarI = jj.i(context2, attributeSet, iArr, i, i2, i3, i4);
        int i5 = R$styleable.BottomNavigationView_itemIconTint;
        if (g3VarI.s(i5)) {
            bottomNavigationMenuView.setIconTintList(g3VarI.c(i5));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(g3VarI.f(R$styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_icon_size)));
        if (g3VarI.s(i3)) {
            setItemTextAppearanceInactive(g3VarI.n(i3, 0));
        }
        if (g3VarI.s(i4)) {
            setItemTextAppearanceActive(g3VarI.n(i4, 0));
        }
        int i6 = R$styleable.BottomNavigationView_itemTextColor;
        if (g3VarI.s(i6)) {
            setItemTextColor(g3VarI.c(i6));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t9.m0(this, e(context2));
        }
        if (g3VarI.s(R$styleable.BottomNavigationView_elevation)) {
            t9.q0(this, g3VarI.f(r2, 0));
        }
        y7.o(getBackground().mutate(), sj.b(context2, g3VarI, R$styleable.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(g3VarI.l(R$styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(g3VarI.a(R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = g3VarI.n(R$styleable.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(sj.b(context2, g3VarI, R$styleable.BottomNavigationView_itemRippleColor));
        }
        int i7 = R$styleable.BottomNavigationView_menu;
        if (g3VarI.s(i7)) {
            f(g3VarI.n(i7, 0));
        }
        g3VarI.w();
        addView(bottomNavigationMenuView, layoutParams);
        if (Build.VERSION.SDK_INT < 21) {
            c(context2);
        }
        thVar.V(new a());
        d();
    }
}
