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
import i2.j;
import java.util.HashSet;
import k0.e;
import l0.t;
import m0.c;
import n.g;
import n.i;
import n.n;
import y0.b;
import y0.p;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends ViewGroup implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f2047b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f2048c = {-16842910};
    public BottomNavigationPresenter A;
    public g B;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final p f2049d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2051f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2052g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2053h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2054i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final View.OnClickListener f2055j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final e<BottomNavigationItemView> f2056k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2057l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2058m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public BottomNavigationItemView[] f2059n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f2060o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2061p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ColorStateList f2062q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f2063r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f2064s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ColorStateList f2065t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f2066u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f2067v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Drawable f2068w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2069x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f2070y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public SparseArray<BadgeDrawable> f2071z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i itemData = ((BottomNavigationItemView) view).getItemData();
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
        BottomNavigationItemView bottomNavigationItemViewB = this.f2056k.b();
        return bottomNavigationItemViewB == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewB;
    }

    private void setBadgeIfNeeded(BottomNavigationItemView bottomNavigationItemView) {
        BadgeDrawable badgeDrawable;
        int id = bottomNavigationItemView.getId();
        if (h(id) && (badgeDrawable = this.f2071z.get(id)) != null) {
            bottomNavigationItemView.setBadge(badgeDrawable);
        }
    }

    @Override // n.n
    public void b(g gVar) {
        this.B = gVar;
    }

    public void d() {
        removeAllViews();
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                if (bottomNavigationItemView != null) {
                    this.f2056k.a(bottomNavigationItemView);
                    bottomNavigationItemView.h();
                }
            }
        }
        if (this.B.size() == 0) {
            this.f2060o = 0;
            this.f2061p = 0;
            this.f2059n = null;
            return;
        }
        i();
        this.f2059n = new BottomNavigationItemView[this.B.size()];
        boolean zG = g(this.f2058m, this.B.G().size());
        for (int i3 = 0; i3 < this.B.size(); i3++) {
            this.A.l(true);
            this.B.getItem(i3).setCheckable(true);
            this.A.l(false);
            BottomNavigationItemView newItem = getNewItem();
            this.f2059n[i3] = newItem;
            newItem.setIconTintList(this.f2062q);
            newItem.setIconSize(this.f2063r);
            newItem.setTextColor(this.f2065t);
            newItem.setTextAppearanceInactive(this.f2066u);
            newItem.setTextAppearanceActive(this.f2067v);
            newItem.setTextColor(this.f2064s);
            Drawable drawable = this.f2068w;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.f2069x);
            }
            newItem.setShifting(zG);
            newItem.setLabelVisibilityMode(this.f2058m);
            newItem.e((i) this.B.getItem(i3), 0);
            newItem.setItemPosition(i3);
            newItem.setOnClickListener(this.f2055j);
            if (this.f2060o != 0 && this.B.getItem(i3).getItemId() == this.f2060o) {
                this.f2061p = i3;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.B.size() - 1, this.f2061p);
        this.f2061p = iMin;
        this.B.getItem(iMin).setChecked(true);
    }

    public ColorStateList e(int i3) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i3, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = j.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i4 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f2048c;
        return new ColorStateList(new int[][]{iArr, f2047b, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i4, defaultColor});
    }

    public boolean f() {
        return this.f2057l;
    }

    public final boolean g(int i3, int i4) {
        if (i3 == -1) {
            if (i4 > 3) {
                return true;
            }
        } else if (i3 == 0) {
            return true;
        }
        return false;
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.f2071z;
    }

    public ColorStateList getIconTintList() {
        return this.f2062q;
    }

    public Drawable getItemBackground() {
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        return (bottomNavigationItemViewArr == null || bottomNavigationItemViewArr.length <= 0) ? this.f2068w : bottomNavigationItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.f2069x;
    }

    public int getItemIconSize() {
        return this.f2063r;
    }

    public int getItemTextAppearanceActive() {
        return this.f2067v;
    }

    public int getItemTextAppearanceInactive() {
        return this.f2066u;
    }

    public ColorStateList getItemTextColor() {
        return this.f2064s;
    }

    public int getLabelVisibilityMode() {
        return this.f2058m;
    }

    public int getSelectedItemId() {
        return this.f2060o;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean h(int i3) {
        return i3 != -1;
    }

    public final void i() {
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < this.B.size(); i3++) {
            hashSet.add(Integer.valueOf(this.B.getItem(i3).getItemId()));
        }
        for (int i4 = 0; i4 < this.f2071z.size(); i4++) {
            int iKeyAt = this.f2071z.keyAt(i4);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                this.f2071z.delete(iKeyAt);
            }
        }
    }

    public void j(int i3) {
        int size = this.B.size();
        for (int i4 = 0; i4 < size; i4++) {
            MenuItem item = this.B.getItem(i4);
            if (i3 == item.getItemId()) {
                this.f2060o = i3;
                this.f2061p = i4;
                item.setChecked(true);
                return;
            }
        }
    }

    public void k() {
        g gVar = this.B;
        if (gVar == null || this.f2059n == null) {
            return;
        }
        int size = gVar.size();
        if (size != this.f2059n.length) {
            d();
            return;
        }
        int i3 = this.f2060o;
        for (int i4 = 0; i4 < size; i4++) {
            MenuItem item = this.B.getItem(i4);
            if (item.isChecked()) {
                this.f2060o = item.getItemId();
                this.f2061p = i4;
            }
        }
        if (i3 != this.f2060o) {
            y0.n.a(this, this.f2049d);
        }
        boolean zG = g(this.f2058m, this.B.G().size());
        for (int i5 = 0; i5 < size; i5++) {
            this.A.l(true);
            this.f2059n[i5].setLabelVisibilityMode(this.f2058m);
            this.f2059n[i5].setShifting(zG);
            this.f2059n[i5].e((i) this.B.getItem(i5), 0);
            this.A.l(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        c.D0(accessibilityNodeInfo).d0(c.b.b(1, this.B.G().size(), false, 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        int childCount = getChildCount();
        int i7 = i5 - i3;
        int i8 = i6 - i4;
        int measuredWidth = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                if (t.z(this) == 1) {
                    int i10 = i7 - measuredWidth;
                    childAt.layout(i10 - childAt.getMeasuredWidth(), 0, i10, i8);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i8);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        int size = View.MeasureSpec.getSize(i3);
        int size2 = this.B.G().size();
        int childCount = getChildCount();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f2054i, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (g(this.f2058m, size2) && this.f2057l) {
            View childAt = getChildAt(this.f2061p);
            int iMax = this.f2053h;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.f2052g, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i5 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.f2051f * i5), Math.min(iMax, this.f2052g));
            int i6 = size - iMin;
            int iMin2 = Math.min(i6 / (i5 == 0 ? 1 : i5), this.f2050e);
            int i7 = i6 - (i5 * iMin2);
            int i8 = 0;
            while (i8 < childCount) {
                if (getChildAt(i8).getVisibility() != 8) {
                    int[] iArr = this.f2070y;
                    iArr[i8] = i8 == this.f2061p ? iMin : iMin2;
                    if (i7 > 0) {
                        iArr[i8] = iArr[i8] + 1;
                        i7--;
                    }
                } else {
                    this.f2070y[i8] = 0;
                }
                i8++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 == 0 ? 1 : size2), this.f2052g);
            int i9 = size - (size2 * iMin3);
            for (int i10 = 0; i10 < childCount; i10++) {
                if (getChildAt(i10).getVisibility() != 8) {
                    int[] iArr2 = this.f2070y;
                    iArr2[i10] = iMin3;
                    if (i9 > 0) {
                        iArr2[i10] = iArr2[i10] + 1;
                        i9--;
                    }
                } else {
                    this.f2070y[i10] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt2 = getChildAt(i11);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.f2070y[i11], TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0), View.resolveSizeAndState(this.f2054i, iMakeMeasureSpec, 0));
    }

    public void setBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
        this.f2071z = sparseArray;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setBadge(sparseArray.get(bottomNavigationItemView.getId()));
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.f2062q = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.f2068w = drawable;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i3) {
        this.f2069x = i3;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(i3);
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z3) {
        this.f2057l = z3;
    }

    public void setItemIconSize(int i3) {
        this.f2063r = i3;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconSize(i3);
            }
        }
    }

    public void setItemTextAppearanceActive(int i3) {
        this.f2067v = i3;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceActive(i3);
                ColorStateList colorStateList = this.f2064s;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i3) {
        this.f2066u = i3;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceInactive(i3);
                ColorStateList colorStateList = this.f2064s;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f2064s = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f2059n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i3) {
        this.f2058m = i3;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.A = bottomNavigationPresenter;
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2056k = new k0.g(5);
        this.f2060o = 0;
        this.f2061p = 0;
        this.f2071z = new SparseArray<>(5);
        Resources resources = getResources();
        this.f2050e = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_max_width);
        this.f2051f = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_min_width);
        this.f2052g = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_max_width);
        this.f2053h = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_min_width);
        this.f2054i = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_height);
        this.f2065t = e(R.attr.textColorSecondary);
        b bVar = new b();
        this.f2049d = bVar;
        bVar.m0(0);
        bVar.U(115L);
        bVar.W(new s0.b());
        bVar.e0(new j());
        this.f2055j = new a();
        this.f2070y = new int[5];
        t.t0(this, 1);
    }
}
