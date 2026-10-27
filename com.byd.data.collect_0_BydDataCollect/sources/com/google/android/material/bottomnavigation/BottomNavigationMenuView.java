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
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.i;
import androidx.appcompat.view.menu.n;
import b.g.j.e;
import b.g.k.c0.c;
import b.g.k.t;
import b.o.b;
import b.o.o;
import b.o.q;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.k;
import d.b.a.a.d;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends ViewGroup implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f3771b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f3772c = {-16842910};
    private BottomNavigationPresenter A;
    private g B;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final q f3773d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3774e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3775f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3776g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f3777h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f3778i;
    private final View.OnClickListener j;
    private final e<BottomNavigationItemView> k;
    private boolean l;
    private int m;
    private BottomNavigationItemView[] n;
    private int o;
    private int p;
    private ColorStateList q;
    private int r;
    private ColorStateList s;
    private final ColorStateList t;
    private int u;
    private int v;
    private Drawable w;
    private int x;
    private int[] y;
    private SparseArray<BadgeDrawable> z;

    class a implements View.OnClickListener {
        a() {
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

    private boolean g(int i2, int i3) {
        if (i2 == -1) {
            if (i3 > 3) {
                return true;
            }
        } else if (i2 == 0) {
            return true;
        }
        return false;
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemViewB = this.k.b();
        return bottomNavigationItemViewB == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewB;
    }

    private boolean h(int i2) {
        return i2 != -1;
    }

    private void i() {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            hashSet.add(Integer.valueOf(this.B.getItem(i2).getItemId()));
        }
        for (int i3 = 0; i3 < this.z.size(); i3++) {
            int iKeyAt = this.z.keyAt(i3);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                this.z.delete(iKeyAt);
            }
        }
    }

    private void setBadgeIfNeeded(BottomNavigationItemView bottomNavigationItemView) {
        BadgeDrawable badgeDrawable;
        int id = bottomNavigationItemView.getId();
        if (h(id) && (badgeDrawable = this.z.get(id)) != null) {
            bottomNavigationItemView.setBadge(badgeDrawable);
        }
    }

    @Override // androidx.appcompat.view.menu.n
    public void b(g gVar) {
        this.B = gVar;
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
        for (int i2 = 0; i2 < this.B.size(); i2++) {
            this.A.l(true);
            this.B.getItem(i2).setCheckable(true);
            this.A.l(false);
            BottomNavigationItemView newItem = getNewItem();
            this.n[i2] = newItem;
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
            newItem.e((i) this.B.getItem(i2), 0);
            newItem.setItemPosition(i2);
            newItem.setOnClickListener(this.j);
            if (this.o != 0 && this.B.getItem(i2).getItemId() == this.o) {
                this.p = i2;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.B.size() - 1, this.p);
        this.p = iMin;
        this.B.getItem(iMin).setChecked(true);
    }

    public ColorStateList e(int i2) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i2, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListC = b.a.k.a.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(b.a.a.colorPrimary, typedValue, true)) {
            return null;
        }
        int i3 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f3772c;
        return new ColorStateList(new int[][]{iArr, f3771b, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i3, defaultColor});
    }

    public boolean f() {
        return this.l;
    }

    SparseArray<BadgeDrawable> getBadgeDrawables() {
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

    void j(int i2) {
        int size = this.B.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.B.getItem(i3);
            if (i2 == item.getItemId()) {
                this.o = i2;
                this.p = i3;
                item.setChecked(true);
                return;
            }
        }
    }

    public void k() {
        g gVar = this.B;
        if (gVar == null || this.n == null) {
            return;
        }
        int size = gVar.size();
        if (size != this.n.length) {
            d();
            return;
        }
        int i2 = this.o;
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = this.B.getItem(i3);
            if (item.isChecked()) {
                this.o = item.getItemId();
                this.p = i3;
            }
        }
        if (i2 != this.o) {
            o.a(this, this.f3773d);
        }
        boolean zG = g(this.m, this.B.G().size());
        for (int i4 = 0; i4 < size; i4++) {
            this.A.l(true);
            this.n[i4].setLabelVisibilityMode(this.m);
            this.n[i4].setShifting(zG);
            this.n[i4].e((i) this.B.getItem(i4), 0);
            this.A.l(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        c.C0(accessibilityNodeInfo).d0(c.b.b(1, this.B.G().size(), false, 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        int measuredWidth = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                if (t.z(this) == 1) {
                    int i9 = i6 - measuredWidth;
                    childAt.layout(i9 - childAt.getMeasuredWidth(), 0, i9, i7);
                } else {
                    childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, i7);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.B.G().size();
        int childCount = getChildCount();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.f3778i, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        if (g(this.m, size2) && this.l) {
            View childAt = getChildAt(this.p);
            int iMax = this.f3777h;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.f3776g, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i4 = size2 - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.f3775f * i4), Math.min(iMax, this.f3776g));
            int i5 = size - iMin;
            int iMin2 = Math.min(i5 / (i4 == 0 ? 1 : i4), this.f3774e);
            int i6 = i5 - (i4 * iMin2);
            int i7 = 0;
            while (i7 < childCount) {
                if (getChildAt(i7).getVisibility() != 8) {
                    int[] iArr = this.y;
                    iArr[i7] = i7 == this.p ? iMin : iMin2;
                    if (i6 > 0) {
                        iArr[i7] = iArr[i7] + 1;
                        i6--;
                    }
                } else {
                    this.y[i7] = 0;
                }
                i7++;
            }
        } else {
            int iMin3 = Math.min(size / (size2 == 0 ? 1 : size2), this.f3776g);
            int i8 = size - (size2 * iMin3);
            for (int i9 = 0; i9 < childCount; i9++) {
                if (getChildAt(i9).getVisibility() != 8) {
                    int[] iArr2 = this.y;
                    iArr2[i9] = iMin3;
                    if (i8 > 0) {
                        iArr2[i9] = iArr2[i9] + 1;
                        i8--;
                    }
                } else {
                    this.y[i9] = 0;
                }
            }
        }
        int measuredWidth = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            if (childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.y[i10], TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth += childAt2.getMeasuredWidth();
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(measuredWidth, View.MeasureSpec.makeMeasureSpec(measuredWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), 0), View.resolveSizeAndState(this.f3778i, iMakeMeasureSpec, 0));
    }

    void setBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
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

    public void setItemBackgroundRes(int i2) {
        this.x = i2;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setItemBackground(i2);
            }
        }
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.l = z;
    }

    public void setItemIconSize(int i2) {
        this.r = i2;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setIconSize(i2);
            }
        }
    }

    public void setItemTextAppearanceActive(int i2) {
        this.v = i2;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceActive(i2);
                ColorStateList colorStateList = this.s;
                if (colorStateList != null) {
                    bottomNavigationItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceInactive(int i2) {
        this.u = i2;
        BottomNavigationItemView[] bottomNavigationItemViewArr = this.n;
        if (bottomNavigationItemViewArr != null) {
            for (BottomNavigationItemView bottomNavigationItemView : bottomNavigationItemViewArr) {
                bottomNavigationItemView.setTextAppearanceInactive(i2);
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

    public void setLabelVisibilityMode(int i2) {
        this.m = i2;
    }

    public void setPresenter(BottomNavigationPresenter bottomNavigationPresenter) {
        this.A = bottomNavigationPresenter;
    }

    public BottomNavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = new b.g.j.g(5);
        this.o = 0;
        this.p = 0;
        this.z = new SparseArray<>(5);
        Resources resources = getResources();
        this.f3774e = resources.getDimensionPixelSize(d.design_bottom_navigation_item_max_width);
        this.f3775f = resources.getDimensionPixelSize(d.design_bottom_navigation_item_min_width);
        this.f3776g = resources.getDimensionPixelSize(d.design_bottom_navigation_active_item_max_width);
        this.f3777h = resources.getDimensionPixelSize(d.design_bottom_navigation_active_item_min_width);
        this.f3778i = resources.getDimensionPixelSize(d.design_bottom_navigation_height);
        this.t = e(R.attr.textColorSecondary);
        b bVar = new b();
        this.f3773d = bVar;
        bVar.q0(0);
        bVar.Y(115L);
        bVar.a0(new b.l.a.a.b());
        bVar.i0(new k());
        this.j = new a();
        this.y = new int[5];
        t.t0(this, 1);
    }
}
