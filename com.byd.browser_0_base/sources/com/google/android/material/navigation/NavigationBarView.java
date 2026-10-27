package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.C0195l;
import defpackage.dc2;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.uk1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final /* synthetic */ int j = 0;
    public final uk1 e;
    public final NavigationBarMenuView f;
    public final NavigationBarPresenter g;
    public ColorStateList h;
    public SupportMenuInflater i;

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

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.e, i);
            parcel.writeBundle(this.g);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.g = parcel.readBundle(classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public interface b {
    }

    public interface c {
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        NavigationBarPresenter navigationBarPresenter = new NavigationBarPresenter();
        this.g = navigationBarPresenter;
        Context context2 = getContext();
        int[] iArr = R$styleable.NavigationBarView;
        int i3 = R$styleable.NavigationBarView_itemTextAppearanceInactive;
        int i4 = R$styleable.NavigationBarView_itemTextAppearanceActive;
        TintTypedArray tintTypedArrayE = r83.e(context2, attributeSet, iArr, i, i2, i3, i4);
        uk1 uk1Var = new uk1(context2, getClass(), getMaxItemCount());
        this.e = uk1Var;
        NavigationBarMenuView navigationBarMenuViewA = a(context2);
        this.f = navigationBarMenuViewA;
        navigationBarPresenter.e = navigationBarMenuViewA;
        navigationBarPresenter.g = 1;
        navigationBarMenuViewA.setPresenter(navigationBarPresenter);
        uk1Var.b(navigationBarPresenter, uk1Var.a);
        getContext();
        navigationBarPresenter.e.F = uk1Var;
        int i5 = R$styleable.NavigationBarView_itemIconTint;
        if (tintTypedArrayE.l(i5)) {
            navigationBarMenuViewA.setIconTintList(tintTypedArrayE.b(i5));
        } else {
            navigationBarMenuViewA.setIconTintList(navigationBarMenuViewA.c(R.attr.textColorSecondary));
        }
        setItemIconSize(tintTypedArrayE.d(R$styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.mtrl_navigation_bar_item_default_icon_size)));
        if (tintTypedArrayE.l(i3)) {
            setItemTextAppearanceInactive(tintTypedArrayE.i(i3, 0));
        }
        if (tintTypedArrayE.l(i4)) {
            setItemTextAppearanceActive(tintTypedArrayE.i(i4, 0));
        }
        int i6 = R$styleable.NavigationBarView_itemTextColor;
        if (tintTypedArrayE.l(i6)) {
            setItemTextColor(tintTypedArrayE.b(i6));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            sc1 sc1Var = new sc1();
            Drawable background = getBackground();
            if (background instanceof ColorDrawable) {
                sc1Var.n(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            sc1Var.k(context2);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.n(this, sc1Var);
        }
        int i7 = R$styleable.NavigationBarView_itemPaddingTop;
        if (tintTypedArrayE.l(i7)) {
            setItemPaddingTop(tintTypedArrayE.d(i7, 0));
        }
        int i8 = R$styleable.NavigationBarView_itemPaddingBottom;
        if (tintTypedArrayE.l(i8)) {
            setItemPaddingBottom(tintTypedArrayE.d(i8, 0));
        }
        if (tintTypedArrayE.l(R$styleable.NavigationBarView_elevation)) {
            setElevation(tintTypedArrayE.d(r0, 0));
        }
        getBackground().mutate().setTintList(rc1.b(context2, tintTypedArrayE, R$styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(tintTypedArrayE.b.getInteger(R$styleable.NavigationBarView_labelVisibilityMode, -1));
        int i9 = tintTypedArrayE.i(R$styleable.NavigationBarView_itemBackground, 0);
        if (i9 != 0) {
            navigationBarMenuViewA.setItemBackgroundRes(i9);
        } else {
            setItemRippleColor(rc1.b(context2, tintTypedArrayE, R$styleable.NavigationBarView_itemRippleColor));
        }
        int i10 = tintTypedArrayE.i(R$styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (i10 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(i10, R$styleable.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(rc1.a(context2, typedArrayObtainStyledAttributes, R$styleable.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(new uj2(uj2.a(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0, new C0195l(0))));
            typedArrayObtainStyledAttributes.recycle();
        }
        int i11 = R$styleable.NavigationBarView_menu;
        if (tintTypedArrayE.l(i11)) {
            int i12 = tintTypedArrayE.i(i11, 0);
            navigationBarPresenter.f = true;
            getMenuInflater().inflate(i12, uk1Var);
            navigationBarPresenter.f = false;
            navigationBarPresenter.d(true);
        }
        tintTypedArrayE.n();
        addView(navigationBarMenuViewA);
        uk1Var.e = new a();
    }

    private MenuInflater getMenuInflater() {
        if (this.i == null) {
            this.i = new SupportMenuInflater(getContext());
        }
        return this.i;
    }

    public abstract NavigationBarMenuView a(Context context);

    public ColorStateList getItemActiveIndicatorColor() {
        return this.f.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.f.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.f.getItemActiveIndicatorMarginHorizontal();
    }

    public uj2 getItemActiveIndicatorShapeAppearance() {
        return this.f.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.f.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.f.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.f.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.f.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.f.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.f.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.f.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.h;
    }

    public int getItemTextAppearanceActive() {
        return this.f.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.f.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.f.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.f.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.e;
    }

    public MenuView getMenuView() {
        return this.f;
    }

    public NavigationBarPresenter getPresenter() {
        return this.g;
    }

    public int getSelectedItemId() {
        return this.f.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.d0(this);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.e);
        this.e.t(savedState.g);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.g = bundle;
        this.e.v(bundle);
        return savedState;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oj2.c0(this, f);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.f.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.f.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.f.setItemActiveIndicatorHeight(i);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.f.setItemActiveIndicatorMarginHorizontal(i);
    }

    public void setItemActiveIndicatorShapeAppearance(uj2 uj2Var) {
        this.f.setItemActiveIndicatorShapeAppearance(uj2Var);
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.f.setItemActiveIndicatorWidth(i);
    }

    public void setItemBackground(Drawable drawable) {
        this.f.setItemBackground(drawable);
        this.h = null;
    }

    public void setItemBackgroundResource(int i) {
        this.f.setItemBackgroundRes(i);
        this.h = null;
    }

    public void setItemIconSize(int i) {
        this.f.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.f.setIconTintList(colorStateList);
    }

    public void setItemPaddingBottom(int i) {
        this.f.setItemPaddingBottom(i);
    }

    public void setItemPaddingTop(int i) {
        this.f.setItemPaddingTop(i);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        ColorStateList colorStateList2 = this.h;
        NavigationBarMenuView navigationBarMenuView = this.f;
        if (colorStateList2 == colorStateList) {
            if (colorStateList != null || navigationBarMenuView.getItemBackground() == null) {
                return;
            }
            navigationBarMenuView.setItemBackground(null);
            return;
        }
        this.h = colorStateList;
        if (colorStateList == null) {
            navigationBarMenuView.setItemBackground(null);
        } else {
            navigationBarMenuView.setItemBackground(new RippleDrawable(dc2.a(colorStateList), null, null));
        }
    }

    public void setItemTextAppearanceActive(int i) {
        this.f.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.f.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        NavigationBarMenuView navigationBarMenuView = this.f;
        if (navigationBarMenuView.getLabelVisibilityMode() != i) {
            navigationBarMenuView.setLabelVisibilityMode(i);
            this.g.d(false);
        }
    }

    public void setSelectedItemId(int i) {
        uk1 uk1Var = this.e;
        MenuItem menuItemFindItem = uk1Var.findItem(i);
        if (menuItemFindItem == null || uk1Var.q(menuItemFindItem, this.g, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public class a implements MenuBuilder.Callback {
        public a() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            int i = NavigationBarView.j;
            NavigationBarView navigationBarView = NavigationBarView.this;
            navigationBarView.getClass();
            navigationBarView.getClass();
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
        }
    }

    public void setOnItemReselectedListener(b bVar) {
    }

    public void setOnItemSelectedListener(c cVar) {
    }
}
