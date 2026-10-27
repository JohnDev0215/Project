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
import b.b.e.j.g;
import b.b.f.e0;
import b.g.j.b0;
import b.g.j.t;
import c.b.a.a.q.k;
import c.b.a.a.q.p;
import c.b.a.a.v.h;
import c.b.a.a.v.i;
import com.google.android.material.R$attr;
import com.google.android.material.R$color;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2095b = R$style.Widget_Design_BottomNavigationView;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f2096c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BottomNavigationMenuView f2097d;
    public final BottomNavigationPresenter e;
    public ColorStateList f;
    public MenuInflater g;
    public d h;
    public c i;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f2098d;

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
            this.f2098d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f2098d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public class a implements g.a {
        public a() {
        }

        @Override // b.b.e.j.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.h == null || BottomNavigationView.this.h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.i.a(menuItem);
            return true;
        }

        @Override // b.b.e.j.g.a
        public void b(g gVar) {
        }
    }

    public class b implements p.d {
        public b() {
        }

        @Override // c.b.a.a.q.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            eVar.f1821d += b0Var.e();
            eVar.a(view);
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
        if (this.g == null) {
            this.g = new b.b.e.g(getContext());
        }
        return this.g;
    }

    public final void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(b.g.b.a.b(context, R$color.design_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_shadow_height)));
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

    public void f(int i) {
        this.e.l(true);
        getMenuInflater().inflate(i, this.f2096c);
        this.e.l(false);
        this.e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f2097d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f2097d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f2097d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f2097d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f;
    }

    public int getItemTextAppearanceActive() {
        return this.f2097d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f2097d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f2097d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f2097d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f2096c;
    }

    public int getSelectedItemId() {
        return this.f2097d.getSelectedItemId();
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
        super.onRestoreInstanceState(savedState.f());
        this.f2096c.S(savedState.f2098d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f2098d = bundle;
        this.f2096c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        i.d(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        this.f2097d.setItemBackground(drawable);
        this.f = null;
    }

    public void setItemBackgroundResource(int i) {
        this.f2097d.setItemBackgroundRes(i);
        this.f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.f2097d.f() != z) {
            this.f2097d.setItemHorizontalTranslationEnabled(z);
            this.e.n(false);
        }
    }

    public void setItemIconSize(int i) {
        this.f2097d.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f2097d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f == colorStateList) {
            if (colorStateList != null || this.f2097d.getItemBackground() == null) {
                return;
            }
            this.f2097d.setItemBackground(null);
            return;
        }
        this.f = colorStateList;
        if (colorStateList == null) {
            this.f2097d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = c.b.a.a.t.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2097d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = b.g.c.l.a.r(gradientDrawable);
        b.g.c.l.a.o(drawableR, colorStateListA);
        this.f2097d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i) {
        this.f2097d.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.f2097d.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f2097d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        if (this.f2097d.getLabelVisibilityMode() != i) {
            this.f2097d.setLabelVisibilityMode(i);
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
        MenuItem menuItemFindItem = this.f2096c.findItem(i);
        if (menuItemFindItem == null || this.f2096c.O(menuItemFindItem, this.e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, f2095b), attributeSet, i);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new c.b.a.a.e.a(context2);
        this.f2096c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f2097d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), aVar);
        int[] iArr = R$styleable.BottomNavigationView;
        int i2 = R$style.Widget_Design_BottomNavigationView;
        int i3 = R$styleable.BottomNavigationView_itemTextAppearanceInactive;
        int i4 = R$styleable.BottomNavigationView_itemTextAppearanceActive;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i, i2, i3, i4);
        int i5 = R$styleable.BottomNavigationView_itemIconTint;
        if (e0VarI.s(i5)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i5));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(R$styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_icon_size)));
        if (e0VarI.s(i3)) {
            setItemTextAppearanceInactive(e0VarI.n(i3, 0));
        }
        if (e0VarI.s(i4)) {
            setItemTextAppearanceActive(e0VarI.n(i4, 0));
        }
        int i6 = R$styleable.BottomNavigationView_itemTextColor;
        if (e0VarI.s(i6)) {
            setItemTextColor(e0VarI.c(i6));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.m0(this, e(context2));
        }
        if (e0VarI.s(R$styleable.BottomNavigationView_elevation)) {
            t.q0(this, e0VarI.f(r2, 0));
        }
        b.g.c.l.a.o(getBackground().mutate(), c.b.a.a.s.c.b(context2, e0VarI, R$styleable.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(e0VarI.l(R$styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = e0VarI.n(R$styleable.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(c.b.a.a.s.c.b(context2, e0VarI, R$styleable.BottomNavigationView_itemRippleColor));
        }
        int i7 = R$styleable.BottomNavigationView_menu;
        if (e0VarI.s(i7)) {
            f(e0VarI.n(i7, 0));
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
