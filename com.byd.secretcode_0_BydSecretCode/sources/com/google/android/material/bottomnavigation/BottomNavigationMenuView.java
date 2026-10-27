package com.google.android.material.bottomnavigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.badge.BadgeDrawable;
import defpackage.ae;
import defpackage.ce;
import defpackage.ea;
import defpackage.ij;
import defpackage.od;
import defpackage.p1;
import defpackage.r1;
import defpackage.t9;
import defpackage.u0;
import defpackage.w1;
import defpackage.w8;
import defpackage.wb;
import defpackage.y8;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends ViewGroup implements w1 {
    public static final int[] b = {R.attr.state_checked};
    public static final int[] c = {-16842910};
    public BottomNavigationPresenter A;
    public p1 B;
    public final ce d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final View.OnClickListener j;
    public final w8<BottomNavigationItemView> k;
    public boolean l;
    public int m;
    public BottomNavigationItemView[] n;
    public int o;
    public int p;
    public ColorStateList q;
    public int r;
    public ColorStateList s;
    public final ColorStateList t;
    public int u;
    public int v;
    public Drawable w;
    public int x;
    public int[] y;
    public SparseArray<BadgeDrawable> z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            r1 itemData = ((BottomNavigationItemView) view).getItemData();
            if (BottomNavigationMenuView.this.B.O(itemData, BottomNavigationMenuView.this.A, 0)) {
                return;
            }
            itemData.setChecked(true);
        }
    }

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemViewB = this.k.b();
        return bottomNavigationItemViewB == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewB;
    }

    private void setBadgeIfNeeded(BottomNavigationItemView bottomNavigationItemView) {
        BadgeDrawable badgeDrawable;
        int id = bottomNavigationItemView.getId();
        if (h(id) && (badgeDrawable = this.z.get(id)) != null) {
            bottomNavigationItemView.setBadge(badgeDrawable);
        }
    }

    @Override // defpackage.w1
    public void b(p1 p1Var) {
        this.B = p1Var;
    }

    public void d() {
        removeAllViews();
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                if (bottomNavigationItemView != null) {
                    this.k.a(bottomNavigationItemView);
                    bottomNavigationItemView.h();
                }
            }
        }
        if (this.B.size() == 0) {
            this.o = 0;
            this.p = 0;
            this.n = null;
            return;
        }
        i();
        this.n = new BottomNavigationItemView[this.B.size()];
        boolean zG = g(this.m, this.B.G().size());
        for (int i = 0; i < this.B.size(); i++) {
            this.A.l(true);
            this.B.getItem(i).setCheckable(true);
            this.A.l(false);
            BottomNavigationItemView newItem = getNewItem();
            this.n[i] = newItem;
            newItem.setIconTintList(this.q);
            newItem.setIconSize(this.r);
            newItem.setTextColor(this.t);
            newItem.setTextAppearanceInactive(this.u);
            newItem.setTextAppearanceActive(this.v);
            newItem.setTextColor(this.s);
            Drawable drawable = this.w;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.x);
            }
            newItem.setShifting(zG);
            newItem.setLabelVisibilityMode(this.m);
            newItem.e((r1) this.B.getItem(i), 0);
            newItem.setItemPosition(i);
            newItem.setOnClickListener(this.j);
            if (this.o != 0 && this.B.getItem(i).getItemId() == this.o) {
                this.p = i;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.B.size() - 1, this.p);
        this.p = iMin;
        this.B.getItem(iMin).setChecked(true);
    }

    public ColorStateList e(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = u0.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = c;
        return new ColorStateList(new int[][]{iArr, b, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public boolean f() {
        return this.l;
    }

    public final boolean g(int i, int i2) {
        if (i == -1) {
            if (i2 > 3) {
                return true;
            }
        } else if (i == 0) {
            return true;
        }
        return false;
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.z;
    }

    public ColorStateList getIconTintList() {
        return this.q;
    }

    public Drawable getItemBackground() {
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        return (bottomNavigationItemViewArr == null || bottomNavigationItemViewArr.length <= 0) ? this.w : bottomNavigationItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.x;
    }

    public int getItemIconSize() {
        return this.r;
    }

    public int getItemTextAppearanceActive() {
        return this.v;
    }

    public int getItemTextAppearanceInactive() {
        return this.u;
    }

    public ColorStateList getItemTextColor() {
        return this.s;
    }

    public int getLabelVisibilityMode() {
        return this.m;
    }

    public int getSelectedItemId() {
        return this.o;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean h(int i) {
        return i != -1;
    }

    public final void i() {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.B.size(); i++) {
            hashSet.add(Integer.valueOf(this.B.getItem(i).getItemId()));
        }
        for (int i2 = 0; i2 < this.z.size(); i2++) {
            int iKeyAt = this.z.keyAt(i2);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                this.z.delete(iKeyAt);
            }
        }
    }

    public void j(int i) {
        int size = this.B.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.B.getItem(i2);
            if (i == item.getItemId()) {
                this.o = i;
                this.p = i2;
                item.setChecked(true);
                return;
            }
        }
    }

    public void k() {
        p1 p1Var = this.B;
        if (p1Var == null || this.n == null) {
            return;
        }
        int size = p1Var.size();
        if (size != this.n.length) {
            d();
            return;
        }
        int i = this.o;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.B.getItem(i2);
            if (item.isChecked()) {
                this.o = item.getItemId();
                this.p = i2;
            }
        }
        if (i != this.o) {
            ae.a(this, this.d);
        }
        boolean zG = g(this.m, this.B.G().size());
        for (int i3 = 0; i3 < size; i3++) {
            this.A.l(true);
            this.n[i3].setLabelVisibilityMode(this.m);
            this.n[i3].setShifting(zG);
            this.n[i3].e((r1) this.B.getItem(i3), 0);
            this.A.l(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        ea.D0(accessibilityNodeInfo).d0(ea.b.b(1, this.B.G().size(), false, 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int measuredWidth = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                if (t9.z(this) == 1) {
                    int i8 = i5 - measuredWidth;
                    childAt.layout(i8 - childAt.getMeasuredWidth(), 0, i8, i6);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i6);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.B.G().size();
        int childCount = getChildCount();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.i, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (g(this.m, size2) && this.l) {
            View childAt = getChildAt(this.p);
            int iMax = this.h;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.g, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i3 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.f * i3), Math.min(iMax, this.g));
            int i4 = size - iMin;
            int iMin2 = Math.min(i4 / (i3 == 0 ? 1 : i3), this.e);
            int i5 = i4 - (i3 * iMin2);
            int i6 = 0;
            while (i6 < childCount) {
                if (getChildAt(i6).getVisibility() != 8) {
                    int[] iArr = this.y;
                    iArr[i6] = i6 == this.p ? iMin : iMin2;
                    if (i5 > 0) {
                        iArr[i6] = iArr[i6] + 1;
                        i5--;
                    }
                } else {
                    this.y[i6] = 0;
                }
                i6++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 == 0 ? 1 : size2), this.g);
            int i7 = size - (size2 * iMin3);
            for (int i8 = 0; i8 < childCount; i8++) {
                if (getChildAt(i8).getVisibility() != 8) {
                    int[] iArr2 = this.y;
                    iArr2[i8] = iMin3;
                    if (i7 > 0) {
                        iArr2[i8] = iArr2[i8] + 1;
                        i7--;
                    }
                } else {
                    this.y[i8] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt2 = getChildAt(i9);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.y[i9], TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0), View.resolveSizeAndState(this.i, iMakeMeasureSpec, 0));
    }

    public void setBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
        this.z = sparseArray;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setBadge(sparseArray.get(bottomNavigationItemView.getId()));
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.q = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.w = drawable;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i) {
        this.x = i;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(i);
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.l = z;
    }

    public void setItemIconSize(int i) {
        this.r = i;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconSize(i);
            }
        }
    }

    public void setItemTextAppearanceActive(int i) {
        this.v = i;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceActive(i);
                ColorStateList colorStateList = this.s;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i) {
        this.u = i;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceInactive(i);
                ColorStateList colorStateList = this.s;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.s = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        this.m = i;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.A = bottomNavigationPresenter;
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new y8(5);
        this.o = 0;
        this.p = 0;
        this.z = new SparseArray<>(5);
        Resources resources = getResources();
        this.e = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_max_width);
        this.f = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_min_width);
        this.g = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_max_width);
        this.h = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_min_width);
        this.i = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_height);
        this.t = e(R.attr.textColorSecondary);
        od odVar = new od();
        this.d = odVar;
        odVar.m0(0);
        odVar.U(115L);
        odVar.W(new wb());
        odVar.e0(new ij());
        this.j = new a();
        this.y = new int[5];
        t9.t0(this, 1);
    }
}
