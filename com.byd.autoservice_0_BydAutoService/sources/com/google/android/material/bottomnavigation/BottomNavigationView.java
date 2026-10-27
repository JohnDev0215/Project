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
import i2.k;
import i2.p;
import l0.b0;
import l0.t;
import n.g;
import n2.h;
import n2.i;
import o.e0;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f2079b = R$style.Widget_Design_BottomNavigationView;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f2080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BottomNavigationMenuView f2081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final BottomNavigationPresenter f2082e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f2083f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public MenuInflater f2084g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d f2085h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f2086i;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Bundle f2087d;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel, ClassLoader classLoader) {
            this.f2087d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeBundle(this.f2087d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public class a implements g.a {
        public a() {
        }

        @Override // n.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.f2086i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.f2085h == null || BottomNavigationView.this.f2085h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.f2086i.a(menuItem);
            return true;
        }

        @Override // n.g.a
        public void b(g gVar) {
        }
    }

    public class b implements p.d {
        public b() {
        }

        @Override // i2.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            eVar.f3388d += b0Var.e();
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
        if (this.f2084g == null) {
            this.f2084g = new m.g(getContext());
        }
        return this.f2084g;
    }

    public final void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(b0.a.b(context, R$color.design_bottom_navigation_shadow_color));
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

    public void f(int i3) {
        this.f2082e.l(true);
        getMenuInflater().inflate(i3, this.f2080c);
        this.f2082e.l(false);
        this.f2082e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f2081d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f2081d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f2081d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f2081d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f2083f;
    }

    public int getItemTextAppearanceActive() {
        return this.f2081d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f2081d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f2081d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f2081d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f2080c;
    }

    public int getSelectedItemId() {
        return this.f2081d.getSelectedItemId();
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
        this.f2080c.S(savedState.f2087d);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f2087d = bundle;
        this.f2080c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        i.d(this, f3);
    }

    public void setItemBackground(Drawable drawable) {
        this.f2081d.setItemBackground(drawable);
        this.f2083f = null;
    }

    public void setItemBackgroundResource(int i3) {
        this.f2081d.setItemBackgroundRes(i3);
        this.f2083f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z3) {
        if (this.f2081d.f() != z3) {
            this.f2081d.setItemHorizontalTranslationEnabled(z3);
            this.f2082e.n(false);
        }
    }

    public void setItemIconSize(int i3) {
        this.f2081d.setItemIconSize(i3);
    }

    public void setItemIconSizeRes(int i3) {
        setItemIconSize(getResources().getDimensionPixelSize(i3));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f2081d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f2083f == colorStateList) {
            if (colorStateList != null || this.f2081d.getItemBackground() == null) {
                return;
            }
            this.f2081d.setItemBackground(null);
            return;
        }
        this.f2083f = colorStateList;
        if (colorStateList == null) {
            this.f2081d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = l2.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2081d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = e0.a.r(gradientDrawable);
        e0.a.o(drawableR, colorStateListA);
        this.f2081d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i3) {
        this.f2081d.setItemTextAppearanceActive(i3);
    }

    public void setItemTextAppearanceInactive(int i3) {
        this.f2081d.setItemTextAppearanceInactive(i3);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f2081d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i3) {
        if (this.f2081d.getLabelVisibilityMode() != i3) {
            this.f2081d.setLabelVisibilityMode(i3);
            this.f2082e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.f2086i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.f2085h = dVar;
    }

    public void setSelectedItemId(int i3) {
        MenuItem menuItemFindItem = this.f2080c.findItem(i3);
        if (menuItemFindItem == null || this.f2080c.O(menuItemFindItem, this.f2082e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i3) {
        super(r2.a.c(context, attributeSet, i3, f2079b), attributeSet, i3);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f2082e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new w1.a(context2);
        this.f2080c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f2081d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), aVar);
        int[] iArr = R$styleable.BottomNavigationView;
        int i4 = R$style.Widget_Design_BottomNavigationView;
        int i5 = R$styleable.BottomNavigationView_itemTextAppearanceInactive;
        int i6 = R$styleable.BottomNavigationView_itemTextAppearanceActive;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i3, i4, i5, i6);
        int i7 = R$styleable.BottomNavigationView_itemIconTint;
        if (e0VarI.s(i7)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i7));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(R$styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_icon_size)));
        if (e0VarI.s(i5)) {
            setItemTextAppearanceInactive(e0VarI.n(i5, 0));
        }
        if (e0VarI.s(i6)) {
            setItemTextAppearanceActive(e0VarI.n(i6, 0));
        }
        int i8 = R$styleable.BottomNavigationView_itemTextColor;
        if (e0VarI.s(i8)) {
            setItemTextColor(e0VarI.c(i8));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.m0(this, e(context2));
        }
        if (e0VarI.s(R$styleable.BottomNavigationView_elevation)) {
            t.q0(this, e0VarI.f(r2, 0));
        }
        e0.a.o(getBackground().mutate(), k2.c.b(context2, e0VarI, R$styleable.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(e0VarI.l(R$styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = e0VarI.n(R$styleable.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(k2.c.b(context2, e0VarI, R$styleable.BottomNavigationView_itemRippleColor));
        }
        int i9 = R$styleable.BottomNavigationView_menu;
        if (e0VarI.s(i9)) {
            f(e0VarI.n(i9, 0));
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
