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
import e6.h;
import e6.i;
import o0.b0;
import o0.t;
import q.g;
import r.e0;
import z5.k;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f3662d = R$style.Widget_Design_BottomNavigationView;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f3663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final BottomNavigationMenuView f3664f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final BottomNavigationPresenter f3665g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f3666h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public MenuInflater f3667i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d f3668j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public c f3669k;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Bundle f3670f;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public final void g(Parcel parcel, ClassLoader classLoader) {
            this.f3670f = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeBundle(this.f3670f);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public class a implements g.a {
        public a() {
        }

        @Override // q.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.f3669k == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.f3668j == null || BottomNavigationView.this.f3668j.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.f3669k.a(menuItem);
            return true;
        }

        @Override // q.g.a
        public void b(g gVar) {
        }
    }

    public class b implements p.d {
        public b() {
        }

        @Override // z5.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            eVar.f9619d += b0Var.e();
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
        if (this.f3667i == null) {
            this.f3667i = new p.g(getContext());
        }
        return this.f3667i;
    }

    public final void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(e0.a.b(context, R$color.design_bottom_navigation_shadow_color));
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

    public void f(int i8) {
        this.f3665g.h(true);
        getMenuInflater().inflate(i8, this.f3663e);
        this.f3665g.h(false);
        this.f3665g.g(true);
    }

    public Drawable getItemBackground() {
        return this.f3664f.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f3664f.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f3664f.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f3664f.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f3666h;
    }

    public int getItemTextAppearanceActive() {
        return this.f3664f.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f3664f.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f3664f.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f3664f.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f3663e;
    }

    public int getSelectedItemId() {
        return this.f3664f.getSelectedItemId();
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
        this.f3663e.S(savedState.f3670f);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f3670f = bundle;
        this.f3663e.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        i.d(this, f8);
    }

    public void setItemBackground(Drawable drawable) {
        this.f3664f.setItemBackground(drawable);
        this.f3666h = null;
    }

    public void setItemBackgroundResource(int i8) {
        this.f3664f.setItemBackgroundRes(i8);
        this.f3666h = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z7) {
        if (this.f3664f.f() != z7) {
            this.f3664f.setItemHorizontalTranslationEnabled(z7);
            this.f3665g.g(false);
        }
    }

    public void setItemIconSize(int i8) {
        this.f3664f.setItemIconSize(i8);
    }

    public void setItemIconSizeRes(int i8) {
        setItemIconSize(getResources().getDimensionPixelSize(i8));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f3664f.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f3666h == colorStateList) {
            if (colorStateList != null || this.f3664f.getItemBackground() == null) {
                return;
            }
            this.f3664f.setItemBackground(null);
            return;
        }
        this.f3666h = colorStateList;
        if (colorStateList == null) {
            this.f3664f.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = c6.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3664f.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = h0.a.r(gradientDrawable);
        h0.a.o(drawableR, colorStateListA);
        this.f3664f.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i8) {
        this.f3664f.setItemTextAppearanceActive(i8);
    }

    public void setItemTextAppearanceInactive(int i8) {
        this.f3664f.setItemTextAppearanceInactive(i8);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3664f.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i8) {
        if (this.f3664f.getLabelVisibilityMode() != i8) {
            this.f3664f.setLabelVisibilityMode(i8);
            this.f3665g.g(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.f3669k = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.f3668j = dVar;
    }

    public void setSelectedItemId(int i8) {
        MenuItem menuItemFindItem = this.f3663e.findItem(i8);
        if (menuItemFindItem == null || this.f3663e.O(menuItemFindItem, this.f3665g, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i8) {
        super(i6.a.c(context, attributeSet, i8, f3662d), attributeSet, i8);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f3665g = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new n5.a(context2);
        this.f3663e = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f3664f = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.c(bottomNavigationMenuView);
        bottomNavigationPresenter.e(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.b(getContext(), aVar);
        int[] iArr = R$styleable.BottomNavigationView;
        int i9 = R$style.Widget_Design_BottomNavigationView;
        int i10 = R$styleable.BottomNavigationView_itemTextAppearanceInactive;
        int i11 = R$styleable.BottomNavigationView_itemTextAppearanceActive;
        e0 e0VarI = k.i(context2, attributeSet, iArr, i8, i9, i10, i11);
        int i12 = R$styleable.BottomNavigationView_itemIconTint;
        if (e0VarI.s(i12)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i12));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(R$styleable.BottomNavigationView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.design_bottom_navigation_icon_size)));
        if (e0VarI.s(i10)) {
            setItemTextAppearanceInactive(e0VarI.n(i10, 0));
        }
        if (e0VarI.s(i11)) {
            setItemTextAppearanceActive(e0VarI.n(i11, 0));
        }
        int i13 = R$styleable.BottomNavigationView_itemTextColor;
        if (e0VarI.s(i13)) {
            setItemTextColor(e0VarI.c(i13));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.o0(this, e(context2));
        }
        if (e0VarI.s(R$styleable.BottomNavigationView_elevation)) {
            t.s0(this, e0VarI.f(r2, 0));
        }
        h0.a.o(getBackground().mutate(), b6.c.b(context2, e0VarI, R$styleable.BottomNavigationView_backgroundTint));
        setLabelVisibilityMode(e0VarI.l(R$styleable.BottomNavigationView_labelVisibilityMode, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        int iN = e0VarI.n(R$styleable.BottomNavigationView_itemBackground, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(b6.c.b(context2, e0VarI, R$styleable.BottomNavigationView_itemRippleColor));
        }
        int i14 = R$styleable.BottomNavigationView_menu;
        if (e0VarI.s(i14)) {
            f(e0VarI.n(i14, 0));
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
