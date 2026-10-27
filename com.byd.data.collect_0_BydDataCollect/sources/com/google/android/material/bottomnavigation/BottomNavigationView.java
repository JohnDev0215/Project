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
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.f0;
import androidx.customview.view.AbsSavedState;
import b.g.k.b0;
import b.g.k.t;
import com.google.android.material.internal.q;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.x.h;
import d.b.a.a.x.i;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3786b = k.Widget_Design_BottomNavigationView;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f3787c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final BottomNavigationMenuView f3788d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final BottomNavigationPresenter f3789e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f3790f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private MenuInflater f3791g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d f3792h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private c f3793i;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Bundle f3794d;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void c(Parcel parcel, ClassLoader classLoader) {
            this.f3794d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.f3794d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            c(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.f3793i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.f3792h == null || BottomNavigationView.this.f3792h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.f3793i.a(menuItem);
            return true;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(g gVar) {
        }
    }

    class b implements q.e {
        b() {
        }

        @Override // com.google.android.material.internal.q.e
        public b0 a(View view, b0 b0Var, q.f fVar) {
            fVar.f4189d += b0Var.f();
            fVar.a(view);
            return b0Var;
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

    private void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(androidx.core.content.a.b(context, d.b.a.a.c.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(d.b.a.a.d.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    private void d() {
        q.b(this, new b());
    }

    private h e(Context context) {
        h hVar = new h();
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            hVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
        }
        hVar.N(context);
        return hVar;
    }

    private MenuInflater getMenuInflater() {
        if (this.f3791g == null) {
            this.f3791g = new b.a.o.g(getContext());
        }
        return this.f3791g;
    }

    public void f(int i2) {
        this.f3789e.l(true);
        getMenuInflater().inflate(i2, this.f3787c);
        this.f3789e.l(false);
        this.f3789e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f3788d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f3788d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f3788d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f3788d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f3790f;
    }

    public int getItemTextAppearanceActive() {
        return this.f3788d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f3788d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f3788d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f3788d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f3787c;
    }

    public int getSelectedItemId() {
        return this.f3788d.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.a());
        this.f3787c.S(savedState.f3794d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f3794d = bundle;
        this.f3787c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public void setItemBackground(Drawable drawable) {
        this.f3788d.setItemBackground(drawable);
        this.f3790f = null;
    }

    public void setItemBackgroundResource(int i2) {
        this.f3788d.setItemBackgroundRes(i2);
        this.f3790f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.f3788d.f() != z) {
            this.f3788d.setItemHorizontalTranslationEnabled(z);
            this.f3789e.n(false);
        }
    }

    public void setItemIconSize(int i2) {
        this.f3788d.setItemIconSize(i2);
    }

    public void setItemIconSizeRes(int i2) {
        setItemIconSize(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f3788d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f3790f == colorStateList) {
            if (colorStateList != null || this.f3788d.getItemBackground() == null) {
                return;
            }
            this.f3788d.setItemBackground(null);
            return;
        }
        this.f3790f = colorStateList;
        if (colorStateList == null) {
            this.f3788d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = d.b.a.a.v.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3788d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, colorStateListA);
        this.f3788d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i2) {
        this.f3788d.setItemTextAppearanceActive(i2);
    }

    public void setItemTextAppearanceInactive(int i2) {
        this.f3788d.setItemTextAppearanceInactive(i2);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3788d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f3788d.getLabelVisibilityMode() != i2) {
            this.f3788d.setLabelVisibilityMode(i2);
            this.f3789e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.f3793i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.f3792h = dVar;
    }

    public void setSelectedItemId(int i2) {
        MenuItem menuItemFindItem = this.f3787c.findItem(i2);
        if (menuItemFindItem == null || this.f3787c.O(menuItemFindItem, this.f3789e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i2) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, f3786b), attributeSet, i2);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f3789e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new com.google.android.material.bottomnavigation.a(context2);
        this.f3787c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f3788d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), aVar);
        int[] iArr = l.BottomNavigationView;
        int i3 = k.Widget_Design_BottomNavigationView;
        int i4 = l.BottomNavigationView_itemTextAppearanceInactive;
        int i5 = l.BottomNavigationView_itemTextAppearanceActive;
        f0 f0VarI = com.google.android.material.internal.l.i(context2, attributeSet, iArr, i2, i3, i4, i5);
        int i6 = l.BottomNavigationView_itemIconTint;
        if (f0VarI.s(i6)) {
            bottomNavigationMenuView.setIconTintList(f0VarI.c(i6));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(f0VarI.f(l.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(d.b.a.a.d.design_bottom_navigation_icon_size)));
        if (f0VarI.s(i4)) {
            setItemTextAppearanceInactive(f0VarI.n(i4, 0));
        }
        if (f0VarI.s(i5)) {
            setItemTextAppearanceActive(f0VarI.n(i5, 0));
        }
        int i7 = l.BottomNavigationView_itemTextColor;
        if (f0VarI.s(i7)) {
            setItemTextColor(f0VarI.c(i7));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.m0(this, e(context2));
        }
        if (f0VarI.s(l.BottomNavigationView_elevation)) {
            t.q0(this, f0VarI.f(r2, 0));
        }
        androidx.core.graphics.drawable.a.o(getBackground().mutate(), d.b.a.a.u.c.b(context2, f0VarI, l.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(f0VarI.l(l.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(f0VarI.a(l.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = f0VarI.n(l.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(d.b.a.a.u.c.b(context2, f0VarI, l.BottomNavigationView_itemRippleColor));
        }
        int i8 = l.BottomNavigationView_menu;
        if (f0VarI.s(i8)) {
            f(f0VarI.n(i8, 0));
        }
        f0VarI.w();
        addView(bottomNavigationMenuView, layoutParams);
        if (Build.VERSION.SDK_INT < 21) {
            c(context2);
        }
        aVar.V(new a());
        d();
    }
}
