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
import androidx.appcompat.widget.e0;
import androidx.customview.view.AbsSavedState;
import b.g.k.b0;
import b.g.k.t;
import c.a.a.a.k;
import c.a.a.a.l;
import c.a.a.a.w.h;
import c.a.a.a.w.i;
import com.google.android.material.internal.p;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4073b = k.h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f4074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final BottomNavigationMenuView f4075d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final BottomNavigationPresenter f4076e;
    private ColorStateList f;
    private MenuInflater g;
    private d h;
    private c i;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Bundle f4077d;

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
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void v(Parcel parcel, ClassLoader classLoader) {
            this.f4077d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f4077d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            v(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.h == null || BottomNavigationView.this.h.a(menuItem)) ? false : true;
            }
            BottomNavigationView.this.i.a(menuItem);
            return true;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(g gVar) {
        }
    }

    class b implements p.d {
        b() {
        }

        @Override // com.google.android.material.internal.p.d
        public b0 a(View view, b0 b0Var, p.e eVar) {
            eVar.f4351d += b0Var.e();
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

    private void c(Context context) {
        View view = new View(context);
        view.setBackgroundColor(androidx.core.content.a.b(context, c.a.a.a.c.f2262a));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(c.a.a.a.d.i)));
        addView(view);
    }

    private void d() {
        p.b(this, new b());
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
        if (this.g == null) {
            this.g = new b.a.o.g(getContext());
        }
        return this.g;
    }

    public void f(int i) {
        this.f4076e.l(true);
        getMenuInflater().inflate(i, this.f4074c);
        this.f4076e.l(false);
        this.f4076e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f4075d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f4075d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f4075d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f4075d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f;
    }

    public int getItemTextAppearanceActive() {
        return this.f4075d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f4075d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f4075d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f4075d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f4074c;
    }

    public int getSelectedItemId() {
        return this.f4075d.getSelectedItemId();
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
        super.onRestoreInstanceState(savedState.u());
        this.f4074c.S(savedState.f4077d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f4077d = bundle;
        this.f4074c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        i.d(this, f);
    }

    public void setItemBackground(Drawable drawable) {
        this.f4075d.setItemBackground(drawable);
        this.f = null;
    }

    public void setItemBackgroundResource(int i) {
        this.f4075d.setItemBackgroundRes(i);
        this.f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.f4075d.f() != z) {
            this.f4075d.setItemHorizontalTranslationEnabled(z);
            this.f4076e.n(false);
        }
    }

    public void setItemIconSize(int i) {
        this.f4075d.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f4075d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f == colorStateList) {
            if (colorStateList != null || this.f4075d.getItemBackground() == null) {
                return;
            }
            this.f4075d.setItemBackground(null);
            return;
        }
        this.f = colorStateList;
        if (colorStateList == null) {
            this.f4075d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = c.a.a.a.u.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f4075d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, colorStateListA);
        this.f4075d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i) {
        this.f4075d.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.f4075d.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f4075d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        if (this.f4075d.getLabelVisibilityMode() != i) {
            this.f4075d.setLabelVisibilityMode(i);
            this.f4076e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.h = dVar;
    }

    public void setSelectedItemId(int i) {
        MenuItem menuItemFindItem = this.f4074c.findItem(i);
        if (menuItemFindItem == null || this.f4074c.O(menuItemFindItem, this.f4076e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.f2261e);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, f4073b), attributeSet, i);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f4076e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new com.google.android.material.bottomnavigation.a(context2);
        this.f4074c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f4075d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), aVar);
        int[] iArr = l.W;
        int i2 = k.h;
        int i3 = l.f0;
        int i4 = l.e0;
        e0 e0VarI = com.google.android.material.internal.k.i(context2, attributeSet, iArr, i, i2, i3, i4);
        int i5 = l.c0;
        if (e0VarI.s(i5)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i5));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(l.b0, getResources().getDimensionPixelSize(c.a.a.a.d.f2271e)));
        if (e0VarI.s(i3)) {
            setItemTextAppearanceInactive(e0VarI.n(i3, 0));
        }
        if (e0VarI.s(i4)) {
            setItemTextAppearanceActive(e0VarI.n(i4, 0));
        }
        int i6 = l.g0;
        if (e0VarI.s(i6)) {
            setItemTextColor(e0VarI.c(i6));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            t.m0(this, e(context2));
        }
        if (e0VarI.s(l.Y)) {
            t.q0(this, e0VarI.f(r2, 0));
        }
        androidx.core.graphics.drawable.a.o(getBackground().mutate(), c.a.a.a.t.c.b(context2, e0VarI, l.X));
        setLabelVisibilityMode(e0VarI.l(l.h0, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(l.a0, true));
        int iN = e0VarI.n(l.Z, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(c.a.a.a.t.c.b(context2, e0VarI, l.d0));
        }
        int i7 = l.i0;
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
