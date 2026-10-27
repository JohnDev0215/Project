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
import c.g.j.e;
import c.g.k.c0.c;
import c.g.k.t;
import c.n.b;
import c.n.o;
import c.n.q;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.j;
import d.b.a.a.d;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends ViewGroup implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f3412b = {R.attr.state_checked};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f3413c = {-16842910};
    private BottomNavigationPresenter A;
    private g B;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final q f3414d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
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

    private boolean g(int i, int i2) {
        if (i == -1) {
            if (i2 > 3) {
                return true;
            }
        } else if (i == 0) {
            return true;
        }
        return false;
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemViewB = this.k.b();
        return bottomNavigationItemViewB == null ? new BottomNavigationItemView(getContext()) : bottomNavigationItemViewB;
    }

    private boolean h(int i) {
        return i != -1;
    }

    private void i() {
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
        for (int i = 0; i < this.B.size(); i++) {
            this.A.h(true);
            this.B.getItem(i).setCheckable(true);
            this.A.h(false);
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
            newItem.e((i) this.B.getItem(i), 0);
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
        ColorStateList colorStateListC = c.a.k.a.a.c(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(c.a.a.y, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListC.getDefaultColor();
        int[] iArr = f3413c;
        return new ColorStateList(new int[][]{iArr, f3412b, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListC.getColorForState(iArr, defaultColor), i2, defaultColor});
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

    void j(int i) {
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
        g gVar = this.B;
        if (gVar == null || this.n == null) {
            return;
        }
        int size = gVar.size();
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
            o.a(this, this.f3414d);
        }
        boolean zG = g(this.m, this.B.G().size());
        for (int i3 = 0; i3 < size; i3++) {
            this.A.h(true);
            this.n[i3].setLabelVisibilityMode(this.m);
            this.n[i3].setShifting(zG);
            this.n[i3].e((i) this.B.getItem(i3), 0);
            this.A.h(false);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        c.D0(accessibilityNodeInfo).d0(c.b.b(1, this.B.G().size(), false, 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int measuredWidth = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                if (t.z(this) == 1) {
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
    protected void onMeasure(int i, int i2) {
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
        this.k = new c.g.j.g(5);
        this.o = 0;
        this.p = 0;
        this.z = new SparseArray<>(5);
        Resources resources = getResources();
        this.e = resources.getDimensionPixelSize(d.f);
        this.f = resources.getDimensionPixelSize(d.g);
        this.g = resources.getDimensionPixelSize(d.f3914b);
        this.h = resources.getDimensionPixelSize(d.f3915c);
        this.i = resources.getDimensionPixelSize(d.f3916d);
        this.t = e(R.attr.textColorSecondary);
        b bVar = new b();
        this.f3414d = bVar;
        bVar.m0(0);
        bVar.U(115L);
        bVar.W(new c.k.a.a.b());
        bVar.e0(new j());
        this.j = new a();
        this.y = new int[5];
        t.t0(this, 1);
    }
}
