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
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import i2.k;
import i2.p;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import l0.g;
import l0.t;
import m0.c;
import n2.m;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2149b = MaterialButtonToggleGroup.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2150c = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<d> f2151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final c f2152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f2153f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final LinkedHashSet<e> f2154g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Comparator<MaterialButton> f2155h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Integer[] f2156i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2157j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2158k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2159l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2160m;

    public class a implements Comparator<MaterialButton> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            int iCompareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
            return iCompareTo2 != 0 ? iCompareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton2)));
        }
    }

    public class b extends l0.a {
        public b() {
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            cVar.e0(c.C0036c.a(0, 1, MaterialButtonToggleGroup.this.m(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public class c implements MaterialButton.a {
        public c() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z3) {
            if (MaterialButtonToggleGroup.this.f2157j) {
                return;
            }
            if (MaterialButtonToggleGroup.this.f2158k) {
                MaterialButtonToggleGroup.this.f2160m = z3 ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.t(materialButton.getId(), z3)) {
                MaterialButtonToggleGroup.this.k(materialButton.getId(), materialButton.isChecked());
            }
            MaterialButtonToggleGroup.this.invalidate();
        }

        public /* synthetic */ c(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final n2.c f2164a = new n2.a(0.0f);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public n2.c f2165b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public n2.c f2166c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public n2.c f2167d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public n2.c f2168e;

        public d(n2.c cVar, n2.c cVar2, n2.c cVar3, n2.c cVar4) {
            this.f2165b = cVar;
            this.f2166c = cVar3;
            this.f2167d = cVar4;
            this.f2168e = cVar2;
        }

        public static d a(d dVar) {
            n2.c cVar = f2164a;
            return new d(cVar, dVar.f2168e, cVar, dVar.f2167d);
        }

        public static d b(d dVar, View view) {
            return p.h(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            n2.c cVar = dVar.f2165b;
            n2.c cVar2 = dVar.f2168e;
            n2.c cVar3 = f2164a;
            return new d(cVar, cVar2, cVar3, cVar3);
        }

        public static d d(d dVar) {
            n2.c cVar = f2164a;
            return new d(cVar, cVar, dVar.f2166c, dVar.f2167d);
        }

        public static d e(d dVar, View view) {
            return p.h(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            n2.c cVar = dVar.f2165b;
            n2.c cVar2 = f2164a;
            return new d(cVar, cVar2, dVar.f2166c, cVar2);
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i3, boolean z3);
    }

    public class f implements MaterialButton.b {
        public f() {
        }

        @Override // com.google.android.material.button.MaterialButton.b
        public void a(MaterialButton materialButton, boolean z3) {
            MaterialButtonToggleGroup.this.invalidate();
        }

        public /* synthetic */ f(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            if (o(i3)) {
                return i3;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (o(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if ((getChildAt(i4) instanceof MaterialButton) && o(i4)) {
                i3++;
            }
        }
        return i3;
    }

    public static void s(m.b bVar, d dVar) {
        if (dVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.E(dVar.f2165b).v(dVar.f2168e).I(dVar.f2166c).z(dVar.f2167d);
        }
    }

    private void setCheckedId(int i3) {
        this.f2160m = i3;
        k(i3, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(t.j());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.a(this.f2152e);
        materialButton.setOnPressedChangeListenerInternal(this.f2153f);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(f2149b, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i3, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            t(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        m shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f2151d.add(new d(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        t.j0(materialButton, new b());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        u();
        super.dispatchDraw(canvas);
    }

    public final void g() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i3 = firstVisibleChildIndex + 1; i3 < getChildCount(); i3++) {
            MaterialButton materialButtonL = l(i3);
            int iMin = Math.min(materialButtonL.getStrokeWidth(), l(i3 - 1).getStrokeWidth());
            LinearLayout.LayoutParams layoutParamsH = h(materialButtonL);
            if (getOrientation() == 0) {
                g.c(layoutParamsH, 0);
                g.d(layoutParamsH, -iMin);
            } else {
                layoutParamsH.bottomMargin = 0;
                layoutParamsH.topMargin = -iMin;
            }
            materialButtonL.setLayoutParams(layoutParamsH);
        }
        q(firstVisibleChildIndex);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.f2158k) {
            return this.f2160m;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            MaterialButton materialButtonL = l(i3);
            if (materialButtonL.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonL.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i3, int i4) {
        Integer[] numArr = this.f2156i;
        if (numArr != null && i4 < numArr.length) {
            return numArr[i4].intValue();
        }
        Log.w(f2149b, "Child order wasn't updated");
        return i4;
    }

    public final LinearLayout.LayoutParams h(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    public final void i(int i3) {
        r(i3, true);
        t(i3, true);
        setCheckedId(i3);
    }

    public void j() {
        this.f2157j = true;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            MaterialButton materialButtonL = l(i3);
            materialButtonL.setChecked(false);
            k(materialButtonL.getId(), false);
        }
        this.f2157j = false;
        setCheckedId(-1);
    }

    public final void k(int i3, boolean z3) {
        Iterator<e> it = this.f2154g.iterator();
        while (it.hasNext()) {
            it.next().a(this, i3, z3);
        }
    }

    public final MaterialButton l(int i3) {
        return (MaterialButton) getChildAt(i3);
    }

    public final int m(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == view) {
                return i3;
            }
            if ((getChildAt(i4) instanceof MaterialButton) && o(i4)) {
                i3++;
            }
        }
        return -1;
    }

    public final d n(int i3, int i4, int i5) {
        d dVar = this.f2151d.get(i3);
        if (i4 == i5) {
            return dVar;
        }
        boolean z3 = getOrientation() == 0;
        if (i3 == i4) {
            return z3 ? d.e(dVar, this) : d.f(dVar);
        }
        if (i3 == i5) {
            return z3 ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    public final boolean o(int i3) {
        return getChildAt(i3).getVisibility() != 8;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i3 = this.f2160m;
        if (i3 != -1) {
            i(i3);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        m0.c.D0(accessibilityNodeInfo).d0(c.b.b(1, getVisibleButtonCount(), false, p() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        v();
        g();
        super.onMeasure(i3, i4);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.e(this.f2152e);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f2151d.remove(iIndexOfChild);
        }
        v();
        g();
    }

    public boolean p() {
        return this.f2158k;
    }

    public final void q(int i3) {
        if (getChildCount() == 0 || i3 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) l(i3).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            g.c(layoutParams, 0);
            g.d(layoutParams, 0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    public final void r(int i3, boolean z3) {
        View viewFindViewById = findViewById(i3);
        if (viewFindViewById instanceof MaterialButton) {
            this.f2157j = true;
            ((MaterialButton) viewFindViewById).setChecked(z3);
            this.f2157j = false;
        }
    }

    public void setSelectionRequired(boolean z3) {
        this.f2159l = z3;
    }

    public void setSingleSelection(boolean z3) {
        if (this.f2158k != z3) {
            this.f2158k = z3;
            j();
        }
    }

    public final boolean t(int i3, boolean z3) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.f2159l && checkedButtonIds.isEmpty()) {
            r(i3, true);
            this.f2160m = i3;
            return false;
        }
        if (z3 && this.f2158k) {
            checkedButtonIds.remove(Integer.valueOf(i3));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                r(iIntValue, false);
                k(iIntValue, false);
            }
        }
        return true;
    }

    public final void u() {
        TreeMap treeMap = new TreeMap(this.f2155h);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            treeMap.put(l(i3), Integer.valueOf(i3));
        }
        this.f2156i = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    public void v() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i3 = 0; i3 < childCount; i3++) {
            MaterialButton materialButtonL = l(i3);
            if (materialButtonL.getVisibility() != 8) {
                m.b bVarV = materialButtonL.getShapeAppearanceModel().v();
                s(bVarV, n(i3, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonL.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2150c;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2151d = new ArrayList();
        a aVar = null;
        this.f2152e = new c(this, aVar);
        this.f2153f = new f(this, aVar);
        this.f2154g = new LinkedHashSet<>();
        this.f2155h = new a();
        this.f2157j = false;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i3, i4, new int[0]);
        setSingleSelection(typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.f2160m = typedArrayH.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.f2159l = typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayH.recycle();
        t.t0(this, 1);
    }

    public void setSingleSelection(int i3) {
        setSingleSelection(getResources().getBoolean(i3));
    }
}
