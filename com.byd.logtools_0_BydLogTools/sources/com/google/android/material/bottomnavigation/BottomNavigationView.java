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
import com.google.android.material.internal.p;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.w.h;
import d.b.a.a.w.i;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3400b = k.f4751h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f3401c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final BottomNavigationMenuView f3402d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final BottomNavigationPresenter f3403e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f3404f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private MenuInflater f3405g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private d f3406h;
    private c i;

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Bundle f3407d;

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

        private void g(Parcel parcel, ClassLoader classLoader) {
            this.f3407d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f3407d);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            g(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(g gVar, MenuItem menuItem) {
            if (BottomNavigationView.this.i == null || menuItem.getItemId() != BottomNavigationView.this.getSelectedItemId()) {
                return (BottomNavigationView.this.f3406h == null || BottomNavigationView.this.f3406h.a(menuItem)) ? false : true;
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
            eVar.f3768d += b0Var.e();
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
        view.setBackgroundColor(androidx.core.content.a.b(context, d.b.a.a.c.f4695a));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(d.b.a.a.d.i)));
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
        if (this.f3405g == null) {
            this.f3405g = new b.a.o.g(getContext());
        }
        return this.f3405g;
    }

    public void f(int i) {
        this.f3403e.l(true);
        getMenuInflater().inflate(i, this.f3401c);
        this.f3403e.l(false);
        this.f3403e.n(true);
    }

    public Drawable getItemBackground() {
        return this.f3402d.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f3402d.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f3402d.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f3402d.getIconTintList();
    }

    public ColorStateList getItemRippleColor() {
        return this.f3404f;
    }

    public int getItemTextAppearanceActive() {
        return this.f3402d.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f3402d.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f3402d.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f3402d.getLabelVisibilityMode();
    }

    public int getMaxItemCount() {
        return 5;
    }

    public Menu getMenu() {
        return this.f3401c;
    }

    public int getSelectedItemId() {
        return this.f3402d.getSelectedItemId();
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
        super.onRestoreInstanceState(savedState.e());
        this.f3401c.S(savedState.f3407d);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.f3407d = bundle;
        this.f3401c.U(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        i.d(this, f2);
    }

    public void setItemBackground(Drawable drawable) {
        this.f3402d.setItemBackground(drawable);
        this.f3404f = null;
    }

    public void setItemBackgroundResource(int i) {
        this.f3402d.setItemBackgroundRes(i);
        this.f3404f = null;
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        if (this.f3402d.f() != z) {
            this.f3402d.setItemHorizontalTranslationEnabled(z);
            this.f3403e.n(false);
        }
    }

    public void setItemIconSize(int i) {
        this.f3402d.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f3402d.setIconTintList(colorStateList);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        if (this.f3404f == colorStateList) {
            if (colorStateList != null || this.f3402d.getItemBackground() == null) {
                return;
            }
            this.f3402d.setItemBackground(null);
            return;
        }
        this.f3404f = colorStateList;
        if (colorStateList == null) {
            this.f3402d.setItemBackground(null);
            return;
        }
        ColorStateList colorStateListA = d.b.a.a.u.b.a(colorStateList);
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3402d.setItemBackground(new RippleDrawable(colorStateListA, null, null));
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(1.0E-5f);
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, colorStateListA);
        this.f3402d.setItemBackground(drawableR);
    }

    public void setItemTextAppearanceActive(int i) {
        this.f3402d.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.f3402d.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3402d.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        if (this.f3402d.getLabelVisibilityMode() != i) {
            this.f3402d.setLabelVisibilityMode(i);
            this.f3403e.n(false);
        }
    }

    public void setOnNavigationItemReselectedListener(c cVar) {
        this.i = cVar;
    }

    public void setOnNavigationItemSelectedListener(d dVar) {
        this.f3406h = dVar;
    }

    public void setSelectedItemId(int i) {
        MenuItem menuItemFindItem = this.f3401c.findItem(i);
        if (menuItemFindItem == null || this.f3401c.O(menuItemFindItem, this.f3403e, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.f4691e);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, f3400b), attributeSet, i);
        BottomNavigationPresenter bottomNavigationPresenter = new BottomNavigationPresenter();
        this.f3403e = bottomNavigationPresenter;
        Context context2 = getContext();
        g aVar = new com.google.android.material.bottomnavigation.a(context2);
        this.f3401c = aVar;
        BottomNavigationMenuView bottomNavigationMenuView = new BottomNavigationMenuView(context2);
        this.f3402d = bottomNavigationMenuView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        bottomNavigationMenuView.setLayoutParams(layoutParams);
        bottomNavigationPresenter.b(bottomNavigationMenuView);
        bottomNavigationPresenter.g(1);
        bottomNavigationMenuView.setPresenter(bottomNavigationPresenter);
        aVar.b(bottomNavigationPresenter);
        bottomNavigationPresenter.f(getContext(), aVar);
        int[] iArr = l.W;
        int i2 = k.f4751h;
        int i3 = l.f0;
        int i4 = l.e0;
        e0 e0VarI = com.google.android.material.internal.k.i(context2, attributeSet, iArr, i, i2, i3, i4);
        int i5 = l.c0;
        if (e0VarI.s(i5)) {
            bottomNavigationMenuView.setIconTintList(e0VarI.c(i5));
        } else {
            bottomNavigationMenuView.setIconTintList(bottomNavigationMenuView.e(R.attr.textColorSecondary));
        }
        setItemIconSize(e0VarI.f(l.b0, getResources().getDimensionPixelSize(d.b.a.a.d.f4707e)));
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
        androidx.core.graphics.drawable.a.o(getBackground().mutate(), d.b.a.a.t.c.b(context2, e0VarI, l.X));
        setLabelVisibilityMode(e0VarI.l(l.h0, -1));
        setItemHorizontalTranslationEnabled(e0VarI.a(l.a0, true));
        int iN = e0VarI.n(l.Z, 0);
        if (iN != 0) {
            bottomNavigationMenuView.setItemBackgroundRes(iN);
        } else {
            setItemRippleColor(d.b.a.a.t.c.b(context2, e0VarI, l.d0));
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
