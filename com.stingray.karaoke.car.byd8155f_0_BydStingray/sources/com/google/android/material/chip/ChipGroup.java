package com.google.android.material.chip;

import B2.O;
import F1.H;
import K4.a;
import R4.f;
import R4.g;
import R4.h;
import R4.i;
import U4.d;
import U4.k;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import b5.AbstractC0997a;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends d {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f12835p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f12836q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f12837r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12838s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final f f12839t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final i f12840u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f12841v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12842w;

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet) {
        Context contextA = AbstractC0997a.a(context, attributeSet, R.attr.chipGroupStyle, R.style.Widget_MaterialComponents_ChipGroup);
        super(contextA, attributeSet, R.attr.chipGroupStyle);
        this.f9056n = false;
        TypedArray typedArrayObtainStyledAttributes = contextA.getTheme().obtainStyledAttributes(attributeSet, a.f3590h, 0, 0);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.f9055m = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f12839t = new f(this);
        i iVar = new i(this);
        this.f12840u = iVar;
        this.f12841v = -1;
        this.f12842w = false;
        TypedArray typedArrayE = k.e(getContext(), attributeSet, a.c, R.attr.chipGroupStyle, R.style.Widget_MaterialComponents_ChipGroup, new int[0]);
        int dimensionPixelOffset = typedArrayE.getDimensionPixelOffset(1, 0);
        setChipSpacingHorizontal(typedArrayE.getDimensionPixelOffset(2, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayE.getDimensionPixelOffset(3, dimensionPixelOffset));
        setSingleLine(typedArrayE.getBoolean(5, false));
        setSingleSelection(typedArrayE.getBoolean(6, false));
        setSelectionRequired(typedArrayE.getBoolean(4, false));
        int resourceId = typedArrayE.getResourceId(0, -1);
        if (resourceId != -1) {
            this.f12841v = resourceId;
        }
        typedArrayE.recycle();
        super.setOnHierarchyChangeListener(iVar);
        int[] iArr = H.f2303a;
        setImportantForAccessibility(1);
    }

    private int getChipCount() {
        int i7 = 0;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            if (getChildAt(i10) instanceof Chip) {
                i7++;
            }
        }
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i7) {
        this.f12841v = i7;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i10 = this.f12841v;
                if (i10 != -1 && this.f12837r) {
                    c(i10, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i7, layoutParams);
    }

    public final void b(int i7) {
        int i10 = this.f12841v;
        if (i7 == i10) {
            return;
        }
        if (i10 != -1 && this.f12837r) {
            c(i10, false);
        }
        if (i7 != -1) {
            c(i7, true);
        }
        setCheckedId(i7);
    }

    public final void c(int i7, boolean z6) {
        View viewFindViewById = findViewById(i7);
        if (viewFindViewById instanceof Chip) {
            this.f12842w = true;
            ((Chip) viewFindViewById).setChecked(z6);
            this.f12842w = false;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof g);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.f12837r) {
            return this.f12841v;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.f12837r) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.f12835p;
    }

    public int getChipSpacingVertical() {
        return this.f12836q;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i7 = this.f12841v;
        if (i7 != -1) {
            c(i7, true);
            setCheckedId(this.f12841v);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) O.e0(getRowCount(), this.f9056n ? getChipCount() : -1, this.f12837r ? 1 : 2).f527m);
    }

    public void setChipSpacing(int i7) {
        setChipSpacingHorizontal(i7);
        setChipSpacingVertical(i7);
    }

    public void setChipSpacingHorizontal(int i7) {
        if (this.f12835p != i7) {
            this.f12835p = i7;
            setItemSpacing(i7);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i7) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i7));
    }

    public void setChipSpacingResource(int i7) {
        setChipSpacing(getResources().getDimensionPixelOffset(i7));
    }

    public void setChipSpacingVertical(int i7) {
        if (this.f12836q != i7) {
            this.f12836q = i7;
            setLineSpacing(i7);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i7) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i7));
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
    public void setFlexWrap(int i7) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(h hVar) {
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f12840u.l = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z6) {
        this.f12838s = z6;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // U4.d
    public void setSingleLine(boolean z6) {
        super.setSingleLine(z6);
    }

    public void setSingleSelection(boolean z6) {
        if (this.f12837r != z6) {
            this.f12837r = z6;
            this.f12842w = true;
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                View childAt = getChildAt(i7);
                if (childAt instanceof Chip) {
                    ((Chip) childAt).setChecked(false);
                }
            }
            this.f12842w = false;
            setCheckedId(-1);
        }
    }

    public void setSingleLine(int i7) {
        setSingleLine(getResources().getBoolean(i7));
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new g(layoutParams);
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }
}
