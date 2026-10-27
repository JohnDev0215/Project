package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import defpackage.C0195l;
import defpackage.C0214v;
import defpackage.in3;
import defpackage.l00;
import defpackage.om3;
import defpackage.r83;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.yn3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final int o = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    public final ArrayList e;
    public final e f;
    public final LinkedHashSet<d> g;
    public final a h;
    public Integer[] i;
    public boolean j;
    public boolean k;
    public boolean l;
    public final int m;
    public HashSet n;

    public class a implements Comparator<MaterialButton> {
        public a() {
        }

        @Override // java.util.Comparator
        public final int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            MaterialButton materialButton3 = materialButton;
            MaterialButton materialButton4 = materialButton2;
            int iCompareTo = Boolean.valueOf(materialButton3.isChecked()).compareTo(Boolean.valueOf(materialButton4.isChecked()));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompareTo2 = Boolean.valueOf(materialButton3.isPressed()).compareTo(Boolean.valueOf(materialButton4.isPressed()));
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
            MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
            return Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton3)).compareTo(Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton4)));
        }
    }

    public class b extends C0214v {
        public b() {
        }

        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int i;
            this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
            int i2 = MaterialButtonToggleGroup.o;
            MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
            materialButtonToggleGroup.getClass();
            if (view instanceof MaterialButton) {
                int i3 = 0;
                for (int i4 = 0; i4 < materialButtonToggleGroup.getChildCount(); i4++) {
                    if (materialButtonToggleGroup.getChildAt(i4) == view) {
                        i = i3;
                        break;
                    }
                    if ((materialButtonToggleGroup.getChildAt(i4) instanceof MaterialButton) && materialButtonToggleGroup.d(i4)) {
                        i3++;
                    }
                }
                i = -1;
            } else {
                i = -1;
            }
            accessibilityNodeInfoCompat.q(AccessibilityNodeInfoCompat.d.a(0, 1, i, 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public static class c {
        public static final C0195l e = new C0195l(0.0f);
        public final l00 a;
        public final l00 b;
        public final l00 c;
        public final l00 d;

        public c(l00 l00Var, l00 l00Var2, l00 l00Var3, l00 l00Var4) {
            this.a = l00Var;
            this.b = l00Var3;
            this.c = l00Var4;
            this.d = l00Var2;
        }
    }

    public interface d {
        void a();
    }

    public class e implements MaterialButton.b {
        public e() {
        }
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (d(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (d(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && d(i2)) {
                i++;
            }
        }
        return i;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            materialButton.setId(om3.e.a());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.setOnPressedChangeListenerInternal(this.f);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButtonC = c(i);
            int iMin = Math.min(materialButtonC.getStrokeWidth(), c(i - 1).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButtonC.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
            if (getOrientation() == 0) {
                layoutParams2.setMarginEnd(0);
                layoutParams2.setMarginStart(-iMin);
                layoutParams2.topMargin = 0;
            } else {
                layoutParams2.bottomMargin = 0;
                layoutParams2.topMargin = -iMin;
                layoutParams2.setMarginStart(0);
            }
            materialButtonC.setLayoutParams(layoutParams2);
        }
        if (getChildCount() == 0 || firstVisibleChildIndex == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) c(firstVisibleChildIndex).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams3.topMargin = 0;
            layoutParams3.bottomMargin = 0;
        } else {
            layoutParams3.setMarginEnd(0);
            layoutParams3.setMarginStart(0);
            layoutParams3.leftMargin = 0;
            layoutParams3.rightMargin = 0;
        }
    }

    public void addOnButtonCheckedListener(d dVar) {
        this.g.add(dVar);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e("MaterialButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        b(materialButton.getId(), materialButton.isChecked());
        uj2 shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.e.add(new c(shapeAppearanceModel.e, shapeAppearanceModel.h, shapeAppearanceModel.f, shapeAppearanceModel.g));
        om3.k(materialButton, new b());
    }

    public final void b(int i, boolean z) {
        if (i == -1) {
            Log.e("MaterialButtonToggleGroup", "Button ID is not valid: " + i);
            return;
        }
        HashSet hashSet = new HashSet(this.n);
        if (z && !hashSet.contains(Integer.valueOf(i))) {
            if (this.k && !hashSet.isEmpty()) {
                hashSet.clear();
            }
            hashSet.add(Integer.valueOf(i));
        } else {
            if (z || !hashSet.contains(Integer.valueOf(i))) {
                return;
            }
            if (!this.l || hashSet.size() > 1) {
                hashSet.remove(Integer.valueOf(i));
            }
        }
        e(hashSet);
    }

    public final MaterialButton c(int i) {
        return (MaterialButton) getChildAt(i);
    }

    public final boolean d(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.h);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(c(i), Integer.valueOf(i));
        }
        this.i = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e(Set<Integer> set) {
        HashSet hashSet = this.n;
        this.n = new HashSet(set);
        for (int i = 0; i < getChildCount(); i++) {
            int id = c(i).getId();
            boolean zContains = set.contains(Integer.valueOf(id));
            View viewFindViewById = findViewById(id);
            if (viewFindViewById instanceof MaterialButton) {
                this.j = true;
                ((MaterialButton) viewFindViewById).setChecked(zContains);
                this.j = false;
            }
            if (hashSet.contains(Integer.valueOf(id)) != set.contains(Integer.valueOf(id))) {
                set.contains(Integer.valueOf(id));
                Iterator<d> it = this.g.iterator();
                while (it.hasNext()) {
                    it.next().a();
                }
            }
        }
        invalidate();
    }

    public final void f() {
        c cVar;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButtonC = c(i);
            if (materialButtonC.getVisibility() != 8) {
                uj2 shapeAppearanceModel = materialButtonC.getShapeAppearanceModel();
                shapeAppearanceModel.getClass();
                uj2.a aVar = new uj2.a(shapeAppearanceModel);
                c cVar2 = (c) this.e.get(i);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z = getOrientation() == 0;
                    C0195l c0195l = c.e;
                    if (i == firstVisibleChildIndex) {
                        cVar = z ? yn3.d(this) ? new c(c0195l, c0195l, cVar2.b, cVar2.c) : new c(cVar2.a, cVar2.d, c0195l, c0195l) : new c(cVar2.a, c0195l, cVar2.b, c0195l);
                    } else if (i == lastVisibleChildIndex) {
                        cVar = z ? yn3.d(this) ? new c(cVar2.a, cVar2.d, c0195l, c0195l) : new c(c0195l, c0195l, cVar2.b, cVar2.c) : new c(c0195l, cVar2.d, c0195l, cVar2.c);
                    } else {
                        cVar2 = null;
                    }
                    cVar2 = cVar;
                }
                if (cVar2 == null) {
                    aVar.c(0.0f);
                } else {
                    aVar.e = cVar2.a;
                    aVar.h = cVar2.d;
                    aVar.f = cVar2.b;
                    aVar.g = cVar2.c;
                }
                materialButtonC.setShapeAppearanceModel(new uj2(aVar));
            }
        }
    }

    public int getCheckedButtonId() {
        if (!this.k || this.n.isEmpty()) {
            return -1;
        }
        return ((Integer) this.n.iterator().next()).intValue();
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            int id = c(i).getId();
            if (this.n.contains(Integer.valueOf(id))) {
                arrayList.add(Integer.valueOf(id));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.i;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w("MaterialButtonToggleGroup", "Child order wasn't updated");
        return i2;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        int i = this.m;
        if (i != -1) {
            e(Collections.singleton(Integer.valueOf(i)));
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).p(AccessibilityNodeInfoCompat.c.a(1, getVisibleButtonCount(), this.k ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        f();
        a();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            ((MaterialButton) view).setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.e.remove(iIndexOfChild);
        }
        f();
        a();
    }

    public void removeOnButtonCheckedListener(d dVar) {
        this.g.remove(dVar);
    }

    public void setSelectionRequired(boolean z) {
        this.l = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.k != z) {
            this.k = z;
            e(new HashSet());
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = o;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.e = new ArrayList();
        this.f = new e();
        this.g = new LinkedHashSet<>();
        this.h = new a();
        this.j = false;
        this.n = new HashSet();
        TypedArray typedArrayD = r83.d(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i, i2, new int[0]);
        setSingleSelection(typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.m = typedArrayD.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.l = typedArrayD.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayD.recycle();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.p(this, 1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
