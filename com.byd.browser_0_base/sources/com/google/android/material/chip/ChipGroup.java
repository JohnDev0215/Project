package com.google.android.material.chip;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.FlowLayout;
import defpackage.bn;
import defpackage.cn;
import defpackage.in3;
import defpackage.jc1;
import defpackage.om3;
import defpackage.r83;
import defpackage.uc1;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChipGroup extends FlowLayout {
    public static final int o = R$style.Widget_MaterialComponents_ChipGroup;
    public int i;
    public int j;
    public d k;
    public final cn<Chip> l;
    public final int m;
    public final e n;

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams() {
            super(-2, -2);
        }
    }

    public class a implements cn.a {
        public a() {
        }
    }

    public class b implements d {
        public b() {
        }
    }

    @Deprecated
    public interface c {
    }

    public interface d {
    }

    public class e implements ViewGroup.OnHierarchyChangeListener {
        public ViewGroup.OnHierarchyChangeListener e;

        public e() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewAdded(View view, View view2) {
            ChipGroup chipGroup = ChipGroup.this;
            if (view == chipGroup && (view2 instanceof Chip)) {
                if (view2.getId() == -1) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    view2.setId(om3.e.a());
                }
                cn<Chip> cnVar = chipGroup.l;
                Chip chip = (Chip) view2;
                cnVar.a.put(Integer.valueOf(chip.getId()), chip);
                if (chip.isChecked()) {
                    cnVar.a(chip);
                }
                chip.setInternalOnCheckedChangeListener(new bn(cnVar));
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.e;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public final void onChildViewRemoved(View view, View view2) {
            ChipGroup chipGroup = ChipGroup.this;
            if (view == chipGroup && (view2 instanceof Chip)) {
                cn<Chip> cnVar = chipGroup.l;
                Chip chip = (Chip) view2;
                cnVar.getClass();
                chip.setInternalOnCheckedChangeListener(null);
                cnVar.a.remove(Integer.valueOf(chip.getId()));
                cnVar.b.remove(Integer.valueOf(chip.getId()));
            }
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.e;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    public ChipGroup(Context context) {
        this(context, null);
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

    @Override // com.google.android.material.internal.FlowLayout
    public final boolean a() {
        return this.g;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof LayoutParams);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public int getCheckedChipId() {
        return this.l.c();
    }

    public List<Integer> getCheckedChipIds() {
        return this.l.b(this);
    }

    public int getChipSpacingHorizontal() {
        return this.i;
    }

    public int getChipSpacingVertical() {
        return this.j;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.m;
        if (i != -1) {
            cn<Chip> cnVar = this.l;
            jc1<T> jc1Var = (jc1) cnVar.a.get(Integer.valueOf(i));
            if (jc1Var != 0 && cnVar.a(jc1Var)) {
                cnVar.d();
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).p(AccessibilityNodeInfoCompat.c.a(getRowCount(), this.g ? getChipCount() : -1, this.l.d ? 1 : 2));
    }

    public void setChipSpacing(int i) {
        setChipSpacingHorizontal(i);
        setChipSpacingVertical(i);
    }

    public void setChipSpacingHorizontal(int i) {
        if (this.i != i) {
            this.i = i;
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
        if (this.j != i) {
            this.j = i;
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

    @Deprecated
    public void setOnCheckedChangeListener(c cVar) {
        if (cVar == null) {
            setOnCheckedStateChangeListener(null);
        } else {
            setOnCheckedStateChangeListener(new b());
        }
    }

    public void setOnCheckedStateChangeListener(d dVar) {
        this.k = dVar;
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.n.e = onHierarchyChangeListener;
    }

    public void setSelectionRequired(boolean z) {
        this.l.e = z;
    }

    @Deprecated
    public void setShowDividerHorizontal(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Deprecated
    public void setShowDividerVertical(int i) {
        throw new UnsupportedOperationException("Changing divider modes has no effect. ChipGroup do not use divider drawables as spacing.");
    }

    @Override // com.google.android.material.internal.FlowLayout
    public void setSingleLine(boolean z) {
        super.setSingleLine(z);
    }

    public void setSingleSelection(boolean z) {
        cn<Chip> cnVar = this.l;
        if (cnVar.d != z) {
            cnVar.d = z;
            boolean z2 = !cnVar.b.isEmpty();
            Iterator it = cnVar.a.values().iterator();
            while (it.hasNext()) {
                cnVar.e((jc1) it.next(), false);
            }
            if (z2) {
                cnVar.d();
            }
        }
    }

    public ChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.chipGroupStyle);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setSingleLine(int i) {
        setSingleLine(getResources().getBoolean(i));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ChipGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = o;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        cn<Chip> cnVar = new cn<>();
        this.l = cnVar;
        e eVar = new e();
        this.n = eVar;
        TypedArray typedArrayD = r83.d(getContext(), attributeSet, R$styleable.ChipGroup, i, i2, new int[0]);
        int dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacing, 0);
        setChipSpacingHorizontal(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingHorizontal, dimensionPixelOffset));
        setChipSpacingVertical(typedArrayD.getDimensionPixelOffset(R$styleable.ChipGroup_chipSpacingVertical, dimensionPixelOffset));
        setSingleLine(typedArrayD.getBoolean(R$styleable.ChipGroup_singleLine, false));
        setSingleSelection(typedArrayD.getBoolean(R$styleable.ChipGroup_singleSelection, false));
        setSelectionRequired(typedArrayD.getBoolean(R$styleable.ChipGroup_selectionRequired, false));
        this.m = typedArrayD.getResourceId(R$styleable.ChipGroup_checkedChip, -1);
        typedArrayD.recycle();
        cnVar.setOnCheckedStateChangeListener(new a());
        super.setOnHierarchyChangeListener(eVar);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
