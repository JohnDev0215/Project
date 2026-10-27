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
import b.b.p.j.g;
import b.b.q.e0;
import b.h.m.b0;
import b.h.m.t;
import c.b.a.a.d0.p;
import c.b.a.a.i0.h;
import c.b.a.a.i0.i;
import c.b.a.a.k;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4571b = k.Widget_Design_BottomNavigationView;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f4572c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BottomNavigationMenuView f4573d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final BottomNavigationPresenter f4574e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f4575f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MenuInflater f4576g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d f4577h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f4578i;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f4579d;

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
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void k(Parcel parcel, ClassLoader classLoader) {
            this.f4579d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeBundle(this.f4579d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            k(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public class a implements g.a {
        public a() {
        }

        @Override // b.b.p.j.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.f4578i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.f4577h == null || BottomNavigationView.this.f4577h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.f4578i.a(menuItem);
            return true;
        }

        @Override // b.b.p.j.g.a
        public void b(g gVar) {
        }
    }

    public class b implements p.e {
        public b() {
        }

        @Override // c.b.a.a.d0.p.e
        public b0 a(View view, b0 b0Var, p.f fVar) {
            fVar.f3812d += b0Var.e();
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

    private MenuInflater getMenuInflater() {
        if (this.f4576g == null) {
            this.f4576g = new b.b.p.g(getContext());
        }
        return this.f4576g;
    }

    public final void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(b.h.e.a.b(context, c.b.a.a.c.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(c.b.a.a.d.design_bottom_navigation_shadow_height)));
        addView(view);
    }

    public final void d() {
        p.b(this, new b());
    }

    public final h e(Context context) {
        h hVar = new h();
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            hVar.X(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
        }
        hVar.N(context);
        return hVar;
    }

    public void f(int i2) {
        this.f4574e.l(true);
        getMenuInflater().inflate(i2, this.f4572c);
        this.f4574e.l(false);
        this.f4574e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f4573d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f4573d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f4573d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f4573d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f4575f;
    }

    public int getItemTextAppearanceActive() {
        return this.f4573d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f4573d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f4573d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f4573d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f4572c;
    }

    public int getSelectedItemId() {
        return this.f4573d.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.e(this);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.j());
        this.f4572c.S(savedState.f4579d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f4579d = bundle;
        this.f4572c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public void setItemBackground(Drawable drawable) {
        this.f4573d.setItemBackground(drawable);
        this.f4575f = null;
    }

    public void setItemBackgroundResource(int i2) {
        this.f4573d.setItemBackgroundRes(i2);
        this.f4575f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.f4573d.f() != z) {
            this.f4573d.setItemHorizontalTranslationEnabled(z);
            this.f4574e.n(false);
        }
    }

    public void setItemIconSize(int i2) {
        this.f4573d.setItemIconSize(i2);
    }

    public void setItemIconSizeRes(int i2) {
        setItemIconSize(getResources().getDimensionPixelSize(i2));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f4573d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f4575f == colorStateList) {
            if (colorStateList != null || this.f4573d.getItemBackground() == null) {
                return;
            }
            this.f4573d.setItemBackground(null);
            return;
        }
        this.f4575f = colorStateList;
        if (colorStateList == null) {
            this.f4573d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = c.b.a.a.g0.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f4573d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = b.h.f.l.a.r(gradientDrawable);
        b.h.f.l.a.o(drawableR, colorStateListA);
        this.f4573d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i2) {
        this.f4573d.setItemTextAppearanceActive(i2);
    }

    public void setItemTextAppearanceInactive(int i2) {
        this.f4573d.setItemTextAppearanceInactive(i2);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f4573d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i2) {
        if (this.f4573d.getLabelVisibilityMode() != i2) {
            this.f4573d.setLabelVisibilityMode(i2);
            this.f4574e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.f4578i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.f4577h = dVar;
    }

    public void setSelectedItemId(int i2) {
        MenuItem menuItemFindItem = this.f4572c.findItem(i2);
        if (menuItemFindItem == null || this.f4572c.O(menuItemFindItem, this.f4574e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.b.a.a.b.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i2) {
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, f4571b), attributeSet, i2);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f4574e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new c.b.a.a.q.a(context2);
        this.f4572c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f4573d = bottomNavigationMenuView;
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
        e0 e0VarI = c.b.a.a.d0.k.i(context2, attributeSet, iArr, i2, i3, i4, i5);
        int i6 = l.BottomNavigationView_itemIconTint;
        if (e0VarI.s(i6)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i6));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(l.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(c.b.a.a.d.design_bottom_navigation_icon_size)));
        if (e0VarI.s(i4)) {
            setItemTextAppearanceInactive(e0VarI.n(i4, 0));
        }
        if (e0VarI.s(i5)) {
            setItemTextAppearanceActive(e0VarI.n(i5, 0));
        }
        int i7 = l.BottomNavigationView_itemTextColor;
        if (e0VarI.s(i7)) {
            setItemTextColor(e0VarI.c(i7));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.n0(this, e(context2));
        }
        if (e0VarI.s(l.BottomNavigationView_elevation)) {
            t.r0(this, e0VarI.f(r2, 0));
        }
        b.h.f.l.a.o(getBackground().mutate(), c.b.a.a.f0.c.b(context2, e0VarI, l.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(e0VarI.l(l.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(l.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = e0VarI.n(l.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(c.b.a.a.f0.c.b(context2, e0VarI, l.BottomNavigationView_itemRippleColor));
        }
        int i8 = l.BottomNavigationView_menu;
        if (e0VarI.s(i8)) {
            f(e0VarI.n(i8, 0));
        }
        e0VarI.w();
        addView(bottomNavigationMenuView, layoutParams);
        if (Build.VERSION.SDK_INT < 21) {
            c(context2);
        }
        aVar.V(new a());
        d();
    }
}
