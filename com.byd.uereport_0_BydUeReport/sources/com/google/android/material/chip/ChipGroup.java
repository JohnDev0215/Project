package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import o0.t;
import p0.c;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class ChipGroup extends FlowLayout {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int f3789h = R$style.Widget_MaterialComponents_ChipGroup;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3790i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3791j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3792k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3793l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f3794m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b f3795n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public e f3796o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3797p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3798q;

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
            if (ChipGroup.this.f3798q) {
                return;
            }
            if (ChipGroup.this.getCheckedChipIds().isEmpty() && ChipGroup.this.f3793l) {
                ChipGroup.this.q(compoundButton.getId(), true);
                ChipGroup.this.p(compoundButton.getId(), false);
                return;
            }
            int id = compoundButton.getId();
            if (!z7) {
                if (ChipGroup.this.f3797p == id) {
                    ChipGroup.this.setCheckedId(-1);
                }
            } else {
                if (ChipGroup.this.f3797p != -1 && ChipGroup.this.f3797p != id && ChipGroup.this.f3792k) {
                    ChipGroup chipGroup = ChipGroup.this;
                    chipGroup.q(chipGroup.f3797p, false);
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

        public c(int i8, int i9) {
            super(i8, i9);
        }
    }

    public interface d {
        void a(ChipGroup chipGroup, int i8);
    }

    public class e implements ViewGroup.OnHierarchyChangeListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ViewGroup.OnHierarchyChangeListener f3800d;

        public e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    view2.setId(Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : view2.hashCode());
                }
                ((Chip) view2).setOnCheckedChangeListenerInternal(ChipGroup.this.f3795n);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f3800d;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                ((Chip) view2).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f3800d;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    private int getChipCount() {
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if (getChildAt(i9) instanceof Chip) {
                i8++;
            }
        }
        return i8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i8) {
        p(i8, true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i9 = this.f3797p;
                if (i9 != -1 && this.f3792k) {
                    q(i9, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i8, layoutParams);
    }

    @Override // com.google.android.material.internal.FlowLayout
    public boolean c() {
        return super.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof c);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new c(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new c(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        if (this.f3792k) {
            return this.f3797p;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.f3792k) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.f3790i;
    }

    public int getChipSpacingVertical() {
        return this.f3791j;
    }

    public void m() {
        this.f3798q = true;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (childAt instanceof Chip) {
                ((Chip) childAt).setChecked(false);
            }
        }
        this.f3798q = false;
        setCheckedId(-1);
    }

    public int n(View view) {
        if (!(view instanceof Chip)) {
            return -1;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if (getChildAt(i9) instanceof Chip) {
                if (((Chip) getChildAt(i9)) == view) {
                    return i8;
                }
                i8++;
            }
        }
        return -1;
    }

    public boolean o() {
        return this.f3792k;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i8 = this.f3797p;
        if (i8 != -1) {
            q(i8, true);
            setCheckedId(this.f3797p);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        p0.c.F0(accessibilityNodeInfo).e0(c.b.b(getRowCount(), c() ? getChipCount() : -1, false, o() ? 1 : 2));
    }

    public final void p(int i8, boolean z7) {
        this.f3797p = i8;
        d dVar = this.f3794m;
        if (dVar != null && this.f3792k && z7) {
            dVar.a(this, i8);
        }
    }

    public final void q(int i8, boolean z7) {
        View viewFindViewById = findViewById(i8);
        if (viewFindViewById instanceof Chip) {
            this.f3798q = true;
            ((Chip) viewFindViewById).setChecked(z7);
            this.f3798q = false;
        }
    }

    public void setChipSpacing(int i8) {
        setChipSpacingHorizontal(i8);
        setChipSpacingVertical(i8);
    }

    public void setChipSpacingHorizontal(int i8) {
        if (this.f3790i != i8) {
            this.f3790i = i8;
            setItemSpacing(i8);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i8) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i8));
    }

    public void setChipSpacingResource(int i8) {
        setChipSpacing(getResources().getDimensionPixelOffset(i8));
    }

    public void setChipSpacingVertical(int i8) {
        if (this.f3791j != i8) {
            this.f3791j = i8;
            setLineSpacing(i8);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i8) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i8));
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
    public void setFlexWrap(int i8) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(d dVar) {
        this.f3794m = dVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f3796o.f3800d = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z7) {
        this.f3793l = z7;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i8) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i8) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z7) {
        super.setSingleLine(z7);
    }

    public void setSingleSelection(boolean z7) {
        if (this.f3792k != z7) {
            this.f3792k = z7;
            m();
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipGroupStyle);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new c(layoutParams);
    }

    public void setSingleLine(int i8) {
        setSingleLine(getResources().getBoolean(i8));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3789h;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3795n = new b();
        this.f3796o = new e();
        this.f3797p = -1;
        this.f3798q = false;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.ChipGroup, i8, i9, new int[0]);
        int dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayH.getBoolean(R$styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayH.getBoolean(R$styleable.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayH.getBoolean(R$styleable.ChipGroup_selectionRequired, false));
        int resourceId = typedArrayH.getResourceId(R$styleable.ChipGroup_checkedChip, -1);
        if (resourceId != -1) {
            this.f3797p = resourceId;
        }
        typedArrayH.recycle();
        super.setOnHierarchyChangeListener(this.f3796o);
        t.v0(this, 1);
    }

    public void setSingleSelection(int i8) {
        setSingleSelection(getResources().getBoolean(i8));
    }
}
