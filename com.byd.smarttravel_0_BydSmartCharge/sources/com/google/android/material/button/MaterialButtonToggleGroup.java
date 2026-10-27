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
import b.g.j.c0.c;
import b.g.j.g;
import b.g.j.t;
import c.b.a.a.q.k;
import c.b.a.a.q.p;
import c.b.a.a.v.m;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2119b = MaterialButtonToggleGroup.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f2120c = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<d> f2121d;
    public final c e;
    public final f f;
    public final LinkedHashSet<e> g;
    public final Comparator<MaterialButton> h;
    public Integer[] i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;

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

    public class b extends b.g.j.a {
        public b() {
        }

        @Override // b.g.j.a
        public void g(View view, b.g.j.c0.c cVar) {
            super.g(view, cVar);
            cVar.e0(c.C0024c.a(0, 1, MaterialButtonToggleGroup.this.m(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public class c implements MaterialButton.a {
        public c() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z) {
            if (MaterialButtonToggleGroup.this.j) {
                return;
            }
            if (MaterialButtonToggleGroup.this.k) {
                MaterialButtonToggleGroup.this.m = z ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.t(materialButton.getId(), z)) {
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
        public static final c.b.a.a.v.c f2125a = new c.b.a.a.v.a(0.0f);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public c.b.a.a.v.c f2126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c.b.a.a.v.c f2127c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c.b.a.a.v.c f2128d;
        public c.b.a.a.v.c e;

        public d(c.b.a.a.v.c cVar, c.b.a.a.v.c cVar2, c.b.a.a.v.c cVar3, c.b.a.a.v.c cVar4) {
            this.f2126b = cVar;
            this.f2127c = cVar3;
            this.f2128d = cVar4;
            this.e = cVar2;
        }

        public static d a(d dVar) {
            c.b.a.a.v.c cVar = f2125a;
            return new d(cVar, dVar.e, cVar, dVar.f2128d);
        }

        public static d b(d dVar, View view) {
            return p.h(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            c.b.a.a.v.c cVar = dVar.f2126b;
            c.b.a.a.v.c cVar2 = dVar.e;
            c.b.a.a.v.c cVar3 = f2125a;
            return new d(cVar, cVar2, cVar3, cVar3);
        }

        public static d d(d dVar) {
            c.b.a.a.v.c cVar = f2125a;
            return new d(cVar, cVar, dVar.f2127c, dVar.f2128d);
        }

        public static d e(d dVar, View view) {
            return p.h(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            c.b.a.a.v.c cVar = dVar.f2126b;
            c.b.a.a.v.c cVar2 = f2125a;
            return new d(cVar, cVar2, dVar.f2127c, cVar2);
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public class f implements MaterialButton.b {
        public f() {
        }

        @Override // com.google.android.material.button.MaterialButton.b
        public void a(MaterialButton materialButton, boolean z) {
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
        for (int i = 0; i < childCount; i++) {
            if (o(i)) {
                return i;
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
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && o(i2)) {
                i++;
            }
        }
        return i;
    }

    public static void s(m.b bVar, d dVar) {
        if (dVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.E(dVar.f2126b).v(dVar.e).I(dVar.f2127c).z(dVar.f2128d);
        }
    }

    private void setCheckedId(int i) {
        this.m = i;
        k(i, true);
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
        materialButton.a(this.e);
        materialButton.setOnPressedChangeListenerInternal(this.f);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(f2119b, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            t(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        m shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f2121d.add(new d(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
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
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButtonL = l(i);
            int iMin = Math.min(materialButtonL.getStrokeWidth(), l(i - 1).getStrokeWidth());
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
        if (this.k) {
            return this.m;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton materialButtonL = l(i);
            if (materialButtonL.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonL.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.i;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w(f2119b, "Child order wasn't updated");
        return i2;
    }

    public final LinearLayout.LayoutParams h(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    public final void i(int i) {
        r(i, true);
        t(i, true);
        setCheckedId(i);
    }

    public void j() {
        this.j = true;
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton materialButtonL = l(i);
            materialButtonL.setChecked(false);
            k(materialButtonL.getId(), false);
        }
        this.j = false;
        setCheckedId(-1);
    }

    public final void k(int i, boolean z) {
        Iterator<e> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().a(this, i, z);
        }
    }

    public final MaterialButton l(int i) {
        return (MaterialButton) getChildAt(i);
    }

    public final int m(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if (getChildAt(i2) == view) {
                return i;
            }
            if ((getChildAt(i2) instanceof MaterialButton) && o(i2)) {
                i++;
            }
        }
        return -1;
    }

    public final d n(int i, int i2, int i3) {
        d dVar = this.f2121d.get(i);
        if (i2 == i3) {
            return dVar;
        }
        boolean z = getOrientation() == 0;
        if (i == i2) {
            return z ? d.e(dVar, this) : d.f(dVar);
        }
        if (i == i3) {
            return z ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    public final boolean o(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i = this.m;
        if (i != -1) {
            i(i);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        b.g.j.c0.c.D0(accessibilityNodeInfo).d0(c.b.b(1, getVisibleButtonCount(), false, p() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        v();
        g();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.e(this.e);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f2121d.remove(iIndexOfChild);
        }
        v();
        g();
    }

    public boolean p() {
        return this.k;
    }

    public final void q(int i) {
        if (getChildCount() == 0 || i == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) l(i).getLayoutParams();
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

    public final void r(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById instanceof MaterialButton) {
            this.j = true;
            ((MaterialButton) viewFindViewById).setChecked(z);
            this.j = false;
        }
    }

    public void setSelectionRequired(boolean z) {
        this.l = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.k != z) {
            this.k = z;
            j();
        }
    }

    public final boolean t(int i, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.l && checkedButtonIds.isEmpty()) {
            r(i, true);
            this.m = i;
            return false;
        }
        if (z && this.k) {
            checkedButtonIds.remove(Integer.valueOf(i));
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
        TreeMap treeMap = new TreeMap(this.h);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(l(i), Integer.valueOf(i));
        }
        this.i = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    public void v() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButtonL = l(i);
            if (materialButtonL.getVisibility() != 8) {
                m.b bVarV = materialButtonL.getShapeAppearanceModel().v();
                s(bVarV, n(i, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonL.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i) {
        int i2 = f2120c;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.f2121d = new ArrayList();
        a aVar = null;
        this.e = new c(this, aVar);
        this.f = new f(this, aVar);
        this.g = new LinkedHashSet<>();
        this.h = new a();
        this.j = false;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i, i2, new int[0]);
        setSingleSelection(typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.m = typedArrayH.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.l = typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayH.recycle();
        t.t0(this, 1);
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
