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
import i2.k;
import java.util.ArrayList;
import java.util.List;
import l0.t;
import m0.c;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends FlowLayout {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f2206f = R$style.Widget_MaterialComponents_ChipGroup;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2207g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2208h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2209i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2210j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public d f2211k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final b f2212l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public e f2213m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2214n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2215o;

    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
            if (ChipGroup.this.f2215o) {
                return;
            }
            if (ChipGroup.this.getCheckedChipIds().isEmpty() && ChipGroup.this.f2210j) {
                ChipGroup.this.q(compoundButton.getId(), true);
                ChipGroup.this.p(compoundButton.getId(), false);
                return;
            }
            int id = compoundButton.getId();
            if (!z3) {
                if (ChipGroup.this.f2214n == id) {
                    ChipGroup.this.setCheckedId(-1);
                }
            } else {
                if (ChipGroup.this.f2214n != -1 && ChipGroup.this.f2214n != id && ChipGroup.this.f2209i) {
                    ChipGroup chipGroup = ChipGroup.this;
                    chipGroup.q(chipGroup.f2214n, false);
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

        public c(int i3, int i4) {
            super(i3, i4);
        }
    }

    public interface d {
        void a(ChipGroup chipGroup, int i3);
    }

    public class e implements ViewGroup.OnHierarchyChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ViewGroup.OnHierarchyChangeListener f2217a;

        public e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    view2.setId(Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : view2.hashCode());
                }
                ((Chip) view2).setOnCheckedChangeListenerInternal(ChipGroup.this.f2212l);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f2217a;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == ChipGroup.this && (view2 instanceof Chip)) {
                ((Chip) view2).setOnCheckedChangeListenerInternal(null);
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.f2217a;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
    }

    private int getChipCount() {
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) instanceof Chip) {
                i3++;
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i3) {
        p(i3, true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof Chip) {
            Chip chip = (Chip) view;
            if (chip.isChecked()) {
                int i4 = this.f2214n;
                if (i4 != -1 && this.f2209i) {
                    q(i4, false);
                }
                setCheckedId(chip.getId());
            }
        }
        super.addView(view, i3, layoutParams);
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
        if (this.f2209i) {
            return this.f2214n;
        }
        return -1;
    }

    public List<Integer> getCheckedChipIds() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if ((childAt instanceof Chip) && ((Chip) childAt).isChecked()) {
                arrayList.add(Integer.valueOf(childAt.getId()));
                if (this.f2209i) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    public int getChipSpacingHorizontal() {
        return this.f2207g;
    }

    public int getChipSpacingVertical() {
        return this.f2208h;
    }

    public void m() {
        this.f2215o = true;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt instanceof Chip) {
                ((Chip) childAt).setChecked(false);
            }
        }
        this.f2215o = false;
        setCheckedId(-1);
    }

    public int n(View view) {
        if (!(view instanceof Chip)) {
            return -1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) instanceof Chip) {
                if (((Chip) getChildAt(i4)) == view) {
                    return i3;
                }
                i3++;
            }
        }
        return -1;
    }

    public boolean o() {
        return this.f2209i;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i3 = this.f2214n;
        if (i3 != -1) {
            q(i3, true);
            setCheckedId(this.f2214n);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        m0.c.D0(accessibilityNodeInfo).d0(c.b.b(getRowCount(), c() ? getChipCount() : -1, false, o() ? 1 : 2));
    }

    public final void p(int i3, boolean z3) {
        this.f2214n = i3;
        d dVar = this.f2211k;
        if (dVar != null && this.f2209i && z3) {
            dVar.a(this, i3);
        }
    }

    public final void q(int i3, boolean z3) {
        View viewFindViewById = findViewById(i3);
        if (viewFindViewById instanceof Chip) {
            this.f2215o = true;
            ((Chip) viewFindViewById).setChecked(z3);
            this.f2215o = false;
        }
    }

    public void setChipSpacing(int i3) {
        setChipSpacingHorizontal(i3);
        setChipSpacingVertical(i3);
    }

    public void setChipSpacingHorizontal(int i3) {
        if (this.f2207g != i3) {
            this.f2207g = i3;
            setItemSpacing(i3);
            requestLayout();
        }
    }

    public void setChipSpacingHorizontalResource(int i3) {
        setChipSpacingHorizontal(getResources().getDimensionPixelOffset(i3));
    }

    public void setChipSpacingResource(int i3) {
        setChipSpacing(getResources().getDimensionPixelOffset(i3));
    }

    public void setChipSpacingVertical(int i3) {
        if (this.f2208h != i3) {
            this.f2208h = i3;
            setLineSpacing(i3);
            requestLayout();
        }
    }

    public void setChipSpacingVerticalResource(int i3) {
        setChipSpacingVertical(getResources().getDimensionPixelOffset(i3));
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
    public void setFlexWrap(int i3) {
        throw new UnsupportedOperationException("Changing flex wrap not allowed. ChipGroup exposes a singleLine attribute instead.");
    }

    public void setOnCheckedChangeListener(d dVar) {
        this.f2211k = dVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.f2213m.f2217a = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z3) {
        this.f2210j = z3;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i3) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i3) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z3) {
        super.setSingleLine(z3);
    }

    public void setSingleSelection(boolean z3) {
        if (this.f2209i != z3) {
            this.f2209i = z3;
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

    public void setSingleLine(int i3) {
        setSingleLine(getResources().getBoolean(i3));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2206f;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2212l = new b();
        this.f2213m = new e();
        this.f2214n = -1;
        this.f2215o = false;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.ChipGroup, i3, i4, new int[0]);
        int dimensionPixelOffset = typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayH.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayH.getBoolean(R$styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayH.getBoolean(R$styleable.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayH.getBoolean(R$styleable.ChipGroup_selectionRequired, false));
        int resourceId = typedArrayH.getResourceId(R$styleable.ChipGroup_checkedChip, -1);
        if (resourceId != -1) {
            this.f2214n = resourceId;
        }
        typedArrayH.recycle();
        super.setOnHierarchyChangeListener(this.f2213m);
        t.t0(this, 1);
    }

    public void setSingleSelection(int i3) {
        setSingleSelection(getResources().getBoolean(i3));
    }
}
