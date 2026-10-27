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
import java.util.HashSet;
import k1.b;
import k1.p;
import n0.e;
import o0.t;
import p0.c;
import q.g;
import q.i;
import q.n;
import z5.j;

/* JADX INFO: loaded from: classes2.dex */
public class BottomNavigationMenuView extends ViewGroup implements n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3632d = {R.attr.state_checked};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f3633e = {-16842910};
    public int[] A;
    public SparseArray<BadgeDrawable> B;
    public BottomNavigationPresenter C;
    public g D;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p f3634f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3635g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3636h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3637i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f3638j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f3639k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View.OnClickListener f3640l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e<BottomNavigationItemView> f3641m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3642n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3643o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public BottomNavigationItemView[] f3644p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3645q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3646r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f3647s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f3648t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ColorStateList f3649u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ColorStateList f3650v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3651w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f3652x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Drawable f3653y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3654z;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i itemData = ((BottomNavigationItemView) view).getItemData();
            if (BottomNavigationMenuView.this.D.O(itemData, BottomNavigationMenuView.this.C, 0)) {
                return;
            }
            itemData.setChecked(true);
        }
    }

    public BottomNavigationMenuView(Context context) {
        this(context, null);
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemViewB = this.f3641m.b();
        return bottomNavigationItemViewB == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewB;
    }

    private void setBadgeIfNeeded(BottomNavigationItemView bottomNavigationItemView) {
        BadgeDrawable badgeDrawable;
        int id = bottomNavigationItemView.getId();
        if (h(id) && (badgeDrawable = this.B.get(id)) != null) {
            bottomNavigationItemView.setBadge(badgeDrawable);
        }
    }

    @Override // q.n
    public void b(g gVar) {
        this.D = gVar;
    }

    public void d() {
        removeAllViews();
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                if (bottomNavigationItemView != null) {
                    this.f3641m.a(bottomNavigationItemView);
                    bottomNavigationItemView.h();
                }
            }
        }
        if (this.D.size() == 0) {
            this.f3645q = 0;
            this.f3646r = 0;
            this.f3644p = null;
            return;
        }
        i();
        this.f3644p = new BottomNavigationItemView[this.D.size()];
        boolean zG = g(this.f3643o, this.D.G().size());
        for (int i8 = 0; i8 < this.D.size(); i8++) {
            this.C.h(true);
            this.D.getItem(i8).setCheckable(true);
            this.C.h(false);
            BottomNavigationItemView newItem = getNewItem();
            this.f3644p[i8] = newItem;
            newItem.setIconTintList(this.f3647s);
            newItem.setIconSize(this.f3648t);
            newItem.setTextColor(this.f3650v);
            newItem.setTextAppearanceInactive(this.f3651w);
            newItem.setTextAppearanceActive(this.f3652x);
            newItem.setTextColor(this.f3649u);
            Drawable drawable = this.f3653y;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.f3654z);
            }
            newItem.setShifting(zG);
            newItem.setLabelVisibilityMode(this.f3643o);
            newItem.e((i) this.D.getItem(i8), 0);
            newItem.setItemPosition(i8);
            newItem.setOnClickListener(this.f3640l);
            if (this.f3645q != 0 && this.D.getItem(i8).getItemId() == this.f3645q) {
                this.f3646r = i8;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.D.size() - 1, this.f3646r);
        this.f3646r = iMin;
        this.D.getItem(iMin).setChecked(true);
    }

    public ColorStateList e(int i8) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i8, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = m.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i9 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f3633e;
        return new ColorStateList(new int[][]{iArr, f3632d, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i9, defaultColor});
    }

    public boolean f() {
        return this.f3642n;
    }

    public final boolean g(int i8, int i9) {
        if (i8 == -1) {
            if (i9 > 3) {
                return true;
            }
        } else if (i8 == 0) {
            return true;
        }
        return false;
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.B;
    }

    public ColorStateList getIconTintList() {
        return this.f3647s;
    }

    public Drawable getItemBackground() {
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        return (bottomNavigationItemViewArr == null || bottomNavigationItemViewArr.length <= 0) ? this.f3653y : bottomNavigationItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.f3654z;
    }

    public int getItemIconSize() {
        return this.f3648t;
    }

    public int getItemTextAppearanceActive() {
        return this.f3652x;
    }

    public int getItemTextAppearanceInactive() {
        return this.f3651w;
    }

    public ColorStateList getItemTextColor() {
        return this.f3649u;
    }

    public int getLabelVisibilityMode() {
        return this.f3643o;
    }

    public int getSelectedItemId() {
        return this.f3645q;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean h(int i8) {
        return i8 != -1;
    }

    public final void i() {
        HashSet hashSet = new HashSet();
        for (int i8 = 0; i8 < this.D.size(); i8++) {
            hashSet.add(Integer.valueOf(this.D.getItem(i8).getItemId()));
        }
        for (int i9 = 0; i9 < this.B.size(); i9++) {
            int iKeyAt = this.B.keyAt(i9);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                this.B.delete(iKeyAt);
            }
        }
    }

    public void j(int i8) {
        int size = this.D.size();
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = this.D.getItem(i9);
            if (i8 == item.getItemId()) {
                this.f3645q = i8;
                this.f3646r = i9;
                item.setChecked(true);
                return;
            }
        }
    }

    public void k() {
        g gVar = this.D;
        if (gVar == null || this.f3644p == null) {
            return;
        }
        int size = gVar.size();
        if (size != this.f3644p.length) {
            d();
            return;
        }
        int i8 = this.f3645q;
        for (int i9 = 0; i9 < size; i9++) {
            MenuItem item = this.D.getItem(i9);
            if (item.isChecked()) {
                this.f3645q = item.getItemId();
                this.f3646r = i9;
            }
        }
        if (i8 != this.f3645q) {
            k1.n.a(this, this.f3634f);
        }
        boolean zG = g(this.f3643o, this.D.G().size());
        for (int i10 = 0; i10 < size; i10++) {
            this.C.h(true);
            this.f3644p[i10].setLabelVisibilityMode(this.f3643o);
            this.f3644p[i10].setShifting(zG);
            this.f3644p[i10].e((i) this.D.getItem(i10), 0);
            this.C.h(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        c.F0(accessibilityNodeInfo).e0(c.b.b(1, this.D.G().size(), false, 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int childCount = getChildCount();
        int i12 = i10 - i8;
        int i13 = i11 - i9;
        int measuredWidth = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                if (t.A(this) == 1) {
                    int i15 = i12 - measuredWidth;
                    childAt.layout(i15 - childAt.getMeasuredWidth(), 0, i15, i13);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i13);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int size = View.MeasureSpec.getSize(i8);
        int size2 = this.D.G().size();
        int childCount = getChildCount();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f3639k, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (g(this.f3643o, size2) && this.f3642n) {
            View childAt = getChildAt(this.f3646r);
            int iMax = this.f3638j;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.f3637i, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i10 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.f3636h * i10), Math.min(iMax, this.f3637i));
            int i11 = size - iMin;
            int iMin2 = Math.min(i11 / (i10 == 0 ? 1 : i10), this.f3635g);
            int i12 = i11 - (i10 * iMin2);
            int i13 = 0;
            while (i13 < childCount) {
                if (getChildAt(i13).getVisibility() != 8) {
                    int[] iArr = this.A;
                    iArr[i13] = i13 == this.f3646r ? iMin : iMin2;
                    if (i12 > 0) {
                        iArr[i13] = iArr[i13] + 1;
                        i12--;
                    }
                } else {
                    this.A[i13] = 0;
                }
                i13++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 == 0 ? 1 : size2), this.f3637i);
            int i14 = size - (size2 * iMin3);
            for (int i15 = 0; i15 < childCount; i15++) {
                if (getChildAt(i15).getVisibility() != 8) {
                    int[] iArr2 = this.A;
                    iArr2[i15] = iMin3;
                    if (i14 > 0) {
                        iArr2[i15] = iArr2[i15] + 1;
                        i14--;
                    }
                } else {
                    this.A[i15] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i16 = 0; i16 < childCount; i16++) {
            View childAt2 = getChildAt(i16);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.A[i16], TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0), View.resolveSizeAndState(this.f3639k, iMakeMeasureSpec, 0));
    }

    public void setBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
        this.B = sparseArray;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setBadge(sparseArray.get(bottomNavigationItemView.getId()));
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.f3647s = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.f3653y = drawable;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i8) {
        this.f3654z = i8;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(i8);
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z7) {
        this.f3642n = z7;
    }

    public void setItemIconSize(int i8) {
        this.f3648t = i8;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconSize(i8);
            }
        }
    }

    public void setItemTextAppearanceActive(int i8) {
        this.f3652x = i8;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceActive(i8);
                ColorStateList colorStateList = this.f3649u;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i8) {
        this.f3651w = i8;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceInactive(i8);
                ColorStateList colorStateList = this.f3649u;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.f3649u = colorStateList;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.f3644p;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i8) {
        this.f3643o = i8;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.C = bottomNavigationPresenter;
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3641m = new n0.g(5);
        this.f3645q = 0;
        this.f3646r = 0;
        this.B = new SparseArray<>(5);
        Resources resources = getResources();
        this.f3635g = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_max_width);
        this.f3636h = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_item_min_width);
        this.f3637i = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_max_width);
        this.f3638j = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_active_item_min_width);
        this.f3639k = resources.getDimensionPixelSize(R$dimen.design_bottom_navigation_height);
        this.f3650v = e(R.attr.textColorSecondary);
        b bVar = new b();
        this.f3634f = bVar;
        bVar.m0(0);
        bVar.U(115L);
        bVar.W(new w0.b());
        bVar.e0(new j());
        this.f3640l = new a();
        this.A = new int[5];
        t.v0(this, 1);
    }
}
