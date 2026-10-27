package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import p.f60;
import p.it2;
import p.iv0;
import p.ps;
import p.qs;
import p.rs;
import p.ss;
import p.v03;
import p.xz1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends iv0 {
    public int m;
    public int n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f33p;
    public final ps q;
    public final ss r;
    public int s;
    public boolean t;

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet) {
        Context contextS = f60.S(context, attributeSet, R.attr.chipGroupStyle, R.style.Widget_MaterialComponents_ChipGroup);
        super(contextS, attributeSet, R.attr.chipGroupStyle);
        this.k = false;
        TypedArray typedArrayObtainStyledAttributes = contextS.getTheme().obtainStyledAttributes(attributeSet, xz1.h, 0, 0);
        this.c = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.q = new ps(this);
        ss ssVar = new ss(this);
        this.r = ssVar;
        this.s = -1;
        this.t = false;
        TypedArray typedArrayE = v03.E(getContext(), attributeSet, xz1.c, R.attr.chipGroupStyle, R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
        int dimensionPixelOffset = typedArrayE.getDimensionPixelOffset(1, 0);
        setChipSpacingHorizontal(typedArrayE.getDimensionPixelOffset(2, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayE.getDimensionPixelOffset(3, dimensionPixelOffset));
        setSingleLine(typedArrayE.getBoolean(5, false));
        setSingleSelection(typedArrayE.getBoolean(6, false));
        setSelectionRequired(typedArrayE.getBoolean(4, false));
        int resourceId = typedArrayE.getResourceId(0, -1);
        if (resourceId != -1) {
            this.s = resourceId;
        }
        typedArrayE.recycle();
        super.setOnHierarchyChangeListener(ssVar);
        WeakHashMap weakHashMap = zt2.a;
        it2.s(this, 1);
    }

    private int getChipCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2) instanceof Chip) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i) {
        this.s = i;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i2 = this.s;
                if (i2 != -1 && this.o) {
                    c(i2, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i, layoutParams);
    }

    public final void b(int i) {
        int i2 = this.s;
        if (i == i2) {
            return;
        }
        if (i2 != -1 && this.o) {
            c(i2, false);
        }
        if (i != -1) {
            c(i, true);
        }
        setCheckedId(i);
    }

    public final void c(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById instanceof Chip) {
            this.t = true;
            ((Chip) viewFindViewById).setChecked(z);
            this.t = false;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof qs);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new qs(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new qs(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.o) {
            return this.s;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.o) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.m;
    }

    public int getChipSpacingVertical() {
        return this.n;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.s;
        if (i != -1) {
            c(i, true);
            setCheckedId(this.s);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(getRowCount(), this.k ? getChipCount() : -1, false, this.o ? 1 : 2));
    }

    public void setChipSpacing(int i) {
        setChipSpacingHorizontal(i);
        setChipSpacingVertical(i);
    }

    public void setChipSpacingHorizontal(int i) {
        if (this.m != i) {
            this.m = i;
            setItemSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingResource(int i) {
        setChipSpacing(getResources().getDimensionPixelOffset(i));
    }

    public void setChipSpacingVertical(int i) {
        if (this.n != i) {
            this.n = i;
            setLineSpacing(i);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i));
    }

    @Deprecated
    public void setDividerDrawableHorizontal(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setDividerDrawableVertical(Drawable drawable) {
        throw new UnsupportedOperationException("Changing divider drawables have no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setFlexWrap(int i) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(rs rsVar) {
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.r.a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.f33p = z;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // p.iv0
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(boolean z) {
        if (this.o != z) {
            this.o = z;
            this.t = true;
            for (int i = 0; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                if (childAt instanceof Chip) {
                    ((Chip) childAt).setChecked(false);
                }
            }
            this.t = false;
            setCheckedId(-1);
        }
    }

    public void setSingleLine(int i) {
        setSingleLine(getResources().getBoolean(i));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new qs(layoutParams);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
