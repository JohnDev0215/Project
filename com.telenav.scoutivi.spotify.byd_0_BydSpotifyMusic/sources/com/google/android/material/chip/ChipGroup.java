package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import com.google.android.material.internal.j;
import i0.u;
import j0.c;
import java.util.ArrayList;
import java.util.List;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends com.google.android.material.internal.c {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final int f4323s = k.Widget_MaterialComponents_ChipGroup;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f4324j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f4325k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f4326l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f4327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private d f4328n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final b f4329o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private e f4330p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f4331q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f4332r;

    private class b implements CompoundButton.OnCheckedChangeListener {
        private b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z6) {
            if (ChipGroup.this.f4332r) {
                return;
            }
            if (ChipGroup.this.getCheckedChipIds().isEmpty() && ChipGroup.this.f4327m) {
                ChipGroup.this.r(compoundButton.getId(), true);
                ChipGroup.this.q(compoundButton.getId(), false);
                return;
            }
            int id = compoundButton.getId();
            if (!z6) {
                if (ChipGroup.this.f4331q == id) {
                    ChipGroup.this.setCheckedId(-1);
                }
            } else {
                if (ChipGroup.this.f4331q != -1 && ChipGroup.this.f4331q != id && ChipGroup.this.f4326l) {
                    ChipGroup chipGroup = ChipGroup.this;
                    chipGroup.r(chipGroup.f4331q, false);
                }
                ChipGroup.this.setCheckedId(id);
            }
        }
    }

    public static class c extends ViewGroup.MarginLayoutParams {
        public c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public c(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public c(int i7, int i8) {
            super(i7, i8);
        }
    }

    public interface d {
        void a(ChipGroup chipGroup, int i7);
    }

    private class e implements ViewGroup.OnHierarchyChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ViewGroup.OnHierarchyChangeListener f4334a;

        private e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    view2.setId(u.h());
                }
                Chip chip = (Chip) view2;
                if (chip.isChecked()) {
                    ((ChipGroup) view).m(chip.getId());
                }
                chip.setOnCheckedChangeListenerInternal(ChipGroup.this.f4329o);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f4334a;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                ((Chip) view2).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f4334a;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.chipGroupStyle);
    }

    private int getChipCount() {
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if (getChildAt(i8) instanceof Chip) {
                i7++;
            }
        }
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(int i7, boolean z6) {
        this.f4331q = i7;
        d dVar = this.f4328n;
        if (dVar != null && this.f4326l && z6) {
            dVar.a(this, i7);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(int i7, boolean z6) {
        View viewFindViewById = findViewById(i7);
        if (viewFindViewById instanceof Chip) {
            this.f4332r = true;
            ((Chip) viewFindViewById).setChecked(z6);
            this.f4332r = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i7) {
        q(i7, true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i8 = this.f4331q;
                if (i8 != -1 && this.f4326l) {
                    r(i8, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i7, layoutParams);
    }

    @Override // com.google.android.material.internal.c
    public boolean c() {
        return super.c();
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof c);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.f4326l) {
            return this.f4331q;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.f4326l) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.f4324j;
    }

    public int getChipSpacingVertical() {
        return this.f4325k;
    }

    public void m(int i7) {
        int i8 = this.f4331q;
        if (i7 == i8) {
            return;
        }
        if (i8 != -1 && this.f4326l) {
            r(i8, false);
        }
        if (i7 != -1) {
            r(i7, true);
        }
        setCheckedId(i7);
    }

    public void n() {
        this.f4332r = true;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (childAt instanceof Chip) {
                ((Chip) childAt).setChecked(false);
            }
        }
        this.f4332r = false;
        setCheckedId(-1);
    }

    int o(View view) {
        if (!(view instanceof Chip)) {
            return -1;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if (getChildAt(i8) instanceof Chip) {
                if (((Chip) getChildAt(i8)) == view) {
                    return i7;
                }
                i7++;
            }
        }
        return -1;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i7 = this.f4331q;
        if (i7 != -1) {
            r(i7, true);
            setCheckedId(this.f4331q);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        j0.c.p0(accessibilityNodeInfo).R(c.b.a(getRowCount(), c() ? getChipCount() : -1, false, p() ? 1 : 2));
    }

    public boolean p() {
        return this.f4326l;
    }

    public void setChipSpacing(int i7) {
        setChipSpacingHorizontal(i7);
        setChipSpacingVertical(i7);
    }

    public void setChipSpacingHorizontal(int i7) {
        if (this.f4324j != i7) {
            this.f4324j = i7;
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
        if (this.f4325k != i7) {
            this.f4325k = i7;
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

    public void setOnCheckedChangeListener(d dVar) {
        this.f4328n = dVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f4330p.f4334a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z6) {
        this.f4327m = z6;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i7) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.c
    public void setSingleLine(boolean z6) {
        super.setSingleLine(z6);
    }

    public void setSingleSelection(boolean z6) {
        if (this.f4326l != z6) {
            this.f4326l = z6;
            n();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f4323s;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        this.f4329o = new b();
        this.f4330p = new e();
        this.f4331q = -1;
        this.f4332r = false;
        TypedArray typedArrayH = j.h(getContext(), attributeSet, l.ChipGroup, i7, i8, new int[0]);
        int dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(l.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayH.getDimensionPixelOffset(l.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayH.getDimensionPixelOffset(l.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayH.getBoolean(l.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayH.getBoolean(l.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayH.getBoolean(l.ChipGroup_selectionRequired, false));
        int resourceId = typedArrayH.getResourceId(l.ChipGroup_checkedChip, -1);
        if (resourceId != -1) {
            this.f4331q = resourceId;
        }
        typedArrayH.recycle();
        super.setOnHierarchyChangeListener(this.f4330p);
        u.t0(this, 1);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new c(layoutParams);
    }

    public void setSingleLine(int i7) {
        setSingleLine(getResources().getBoolean(i7));
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }
}
