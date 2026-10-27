package com.google.android.material.navigation;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import defpackage.bz;
import defpackage.ij1;
import defpackage.in3;
import defpackage.k83;
import defpackage.om3;
import defpackage.sc1;
import defpackage.u22;
import defpackage.u5;
import defpackage.uj2;
import java.util.HashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    public static final int[] G = {R.attr.state_checked};
    public static final int[] H = {-16842910};
    public int A;
    public uj2 B;
    public boolean C;
    public ColorStateList D;
    public NavigationBarPresenter E;
    public MenuBuilder F;
    public final AutoTransition e;
    public final a f;
    public final u22 g;
    public final SparseArray<View.OnTouchListener> h;
    public int i;
    public NavigationBarItemView[] j;
    public int k;
    public int l;
    public ColorStateList m;
    public int n;
    public ColorStateList o;
    public final ColorStateList p;
    public int q;
    public int r;
    public Drawable s;
    public int t;
    public final SparseArray<com.google.android.material.badge.a> u;
    public int v;
    public int w;
    public boolean x;
    public int y;
    public int z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            MenuItemImpl itemData = ((NavigationBarItemView) view).getItemData();
            NavigationBarMenuView navigationBarMenuView = NavigationBarMenuView.this;
            if (navigationBarMenuView.F.q(itemData, navigationBarMenuView.E, 0)) {
                return;
            }
            itemData.setChecked(true);
        }
    }

    public NavigationBarMenuView(Context context) {
        super(context);
        this.g = new u22(5);
        this.h = new SparseArray<>(5);
        this.k = 0;
        this.l = 0;
        this.u = new SparseArray<>(5);
        this.v = -1;
        this.w = -1;
        this.C = false;
        this.p = c(R.attr.textColorSecondary);
        if (isInEditMode()) {
            this.e = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.e = autoTransition;
            autoTransition.U(0);
            autoTransition.H(ij1.c(getContext(), R$attr.motionDurationLong1, getResources().getInteger(R$integer.material_motion_duration_long_1)));
            autoTransition.J(ij1.d(getContext(), R$attr.motionEasingStandard, u5.b));
            autoTransition.Q(new k83());
        }
        this.f = new a();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 1);
    }

    public static boolean f(int i, int i2) {
        return i != -1 ? i == 0 : i2 > 3;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.g.a();
        return navigationBarItemView == null ? e(getContext()) : navigationBarItemView;
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView) {
        com.google.android.material.badge.a aVar;
        int id = navigationBarItemView.getId();
        if ((id != -1) && (aVar = this.u.get(id)) != null) {
            navigationBarItemView.setBadge(aVar);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public final void a() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.g.b(navigationBarItemView);
                    if (navigationBarItemView.F != null) {
                        ImageView imageView = navigationBarItemView.o;
                        if (imageView != null) {
                            navigationBarItemView.setClipChildren(true);
                            navigationBarItemView.setClipToPadding(true);
                            com.google.android.material.badge.a aVar = navigationBarItemView.F;
                            if (aVar != null) {
                                if (aVar.d() != null) {
                                    aVar.d().setForeground(null);
                                } else {
                                    imageView.getOverlay().remove(aVar);
                                }
                            }
                        }
                        navigationBarItemView.F = null;
                    }
                    navigationBarItemView.t = null;
                    navigationBarItemView.z = 0.0f;
                    navigationBarItemView.e = false;
                }
            }
        }
        if (this.F.size() == 0) {
            this.k = 0;
            this.l = 0;
            this.j = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.F.size(); i++) {
            hashSet.add(Integer.valueOf(this.F.getItem(i).getItemId()));
        }
        int i2 = 0;
        while (true) {
            SparseArray<com.google.android.material.badge.a> sparseArray = this.u;
            if (i2 >= sparseArray.size()) {
                break;
            }
            int iKeyAt = sparseArray.keyAt(i2);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                sparseArray.delete(iKeyAt);
            }
            i2++;
        }
        this.j = new NavigationBarItemView[this.F.size()];
        boolean zF = f(this.i, this.F.l().size());
        for (int i3 = 0; i3 < this.F.size(); i3++) {
            this.E.f = true;
            this.F.getItem(i3).setCheckable(true);
            this.E.f = false;
            NavigationBarItemView newItem = getNewItem();
            this.j[i3] = newItem;
            newItem.setIconTintList(this.m);
            newItem.setIconSize(this.n);
            newItem.setTextColor(this.p);
            newItem.setTextAppearanceInactive(this.q);
            newItem.setTextAppearanceActive(this.r);
            newItem.setTextColor(this.o);
            int i4 = this.v;
            if (i4 != -1) {
                newItem.setItemPaddingTop(i4);
            }
            int i5 = this.w;
            if (i5 != -1) {
                newItem.setItemPaddingBottom(i5);
            }
            newItem.setActiveIndicatorWidth(this.y);
            newItem.setActiveIndicatorHeight(this.z);
            newItem.setActiveIndicatorMarginHorizontal(this.A);
            newItem.setActiveIndicatorDrawable(d());
            newItem.setActiveIndicatorResizeable(this.C);
            newItem.setActiveIndicatorEnabled(this.x);
            Drawable drawable = this.s;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.t);
            }
            newItem.setShifting(zF);
            newItem.setLabelVisibilityMode(this.i);
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.F.getItem(i3);
            newItem.c(menuItemImpl);
            newItem.setItemPosition(i3);
            SparseArray<View.OnTouchListener> sparseArray2 = this.h;
            int i6 = menuItemImpl.a;
            newItem.setOnTouchListener(sparseArray2.get(i6));
            newItem.setOnClickListener(this.f);
            int i7 = this.k;
            if (i7 != 0 && i6 == i7) {
                this.l = i3;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.F.size() - 1, this.l);
        this.l = iMin;
        this.F.getItem(iMin).setChecked(true);
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public final void b(MenuBuilder menuBuilder) {
        this.F = menuBuilder;
    }

    public final ColorStateList c(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListB = bz.b(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListB.getDefaultColor();
        int[] iArr = H;
        return new ColorStateList(new int[][]{iArr, G, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListB.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public final sc1 d() {
        if (this.B == null || this.D == null) {
            return null;
        }
        sc1 sc1Var = new sc1(this.B);
        sc1Var.n(this.D);
        return sc1Var;
    }

    public abstract NavigationBarItemView e(Context context);

    public SparseArray<com.google.android.material.badge.a> getBadgeDrawables() {
        return this.u;
    }

    public ColorStateList getIconTintList() {
        return this.m;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.D;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.x;
    }

    public int getItemActiveIndicatorHeight() {
        return this.z;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.A;
    }

    public uj2 getItemActiveIndicatorShapeAppearance() {
        return this.B;
    }

    public int getItemActiveIndicatorWidth() {
        return this.y;
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.s : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.t;
    }

    public int getItemIconSize() {
        return this.n;
    }

    public int getItemPaddingBottom() {
        return this.w;
    }

    public int getItemPaddingTop() {
        return this.v;
    }

    public int getItemTextAppearanceActive() {
        return this.r;
    }

    public int getItemTextAppearanceInactive() {
        return this.q;
    }

    public ColorStateList getItemTextColor() {
        return this.o;
    }

    public int getLabelVisibilityMode() {
        return this.i;
    }

    public MenuBuilder getMenu() {
        return this.F;
    }

    public int getSelectedItemId() {
        return this.k;
    }

    public int getSelectedItemPosition() {
        return this.l;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).p(AccessibilityNodeInfoCompat.c.a(1, this.F.l().size(), 1));
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.m = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.D = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.x = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.z = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.A = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z) {
        this.C = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(uj2 uj2Var) {
        this.B = uj2Var;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.y = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.s = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i) {
        this.t = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i);
            }
        }
    }

    public void setItemIconSize(int i) {
        this.n = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i);
            }
        }
    }

    public void setItemPaddingBottom(int i) {
        this.w = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i);
            }
        }
    }

    public void setItemPaddingTop(int i) {
        this.v = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i);
            }
        }
    }

    public void setItemTextAppearanceActive(int i) {
        this.r = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i);
                ColorStateList colorStateList = this.o;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i) {
        this.q = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i);
                ColorStateList colorStateList = this.o;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.o = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.j;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        this.i = i;
    }

    public void setPresenter(NavigationBarPresenter navigationBarPresenter) {
        this.E = navigationBarPresenter;
    }
}
