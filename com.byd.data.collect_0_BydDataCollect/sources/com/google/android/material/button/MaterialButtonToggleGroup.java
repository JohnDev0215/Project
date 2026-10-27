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
import b.g.k.c0.c;
import b.g.k.g;
import b.g.k.t;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.l;
import com.google.android.material.internal.q;
import d.b.a.a.k;
import d.b.a.a.x.m;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3830b = MaterialButtonToggleGroup.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3831c = k.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<d> f3832d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c f3833e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final f f3834f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final LinkedHashSet<e> f3835g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Comparator<MaterialButton> f3836h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Integer[] f3837i;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;

    class a implements Comparator<MaterialButton> {
        a() {
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

    class b extends b.g.k.a {
        b() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.e0(c.C0047c.a(0, 1, MaterialButtonToggleGroup.this.m(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    private class c implements MaterialButton.a {
        private c() {
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

        /* synthetic */ c(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final d.b.a.a.x.c f3841a = new d.b.a.a.x.a(0.0f);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        d.b.a.a.x.c f3842b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        d.b.a.a.x.c f3843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        d.b.a.a.x.c f3844d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        d.b.a.a.x.c f3845e;

        d(d.b.a.a.x.c cVar, d.b.a.a.x.c cVar2, d.b.a.a.x.c cVar3, d.b.a.a.x.c cVar4) {
            this.f3842b = cVar;
            this.f3843c = cVar3;
            this.f3844d = cVar4;
            this.f3845e = cVar2;
        }

        public static d a(d dVar) {
            d.b.a.a.x.c cVar = f3841a;
            return new d(cVar, dVar.f3845e, cVar, dVar.f3844d);
        }

        public static d b(d dVar, View view) {
            return q.h(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            d.b.a.a.x.c cVar = dVar.f3842b;
            d.b.a.a.x.c cVar2 = dVar.f3845e;
            d.b.a.a.x.c cVar3 = f3841a;
            return new d(cVar, cVar2, cVar3, cVar3);
        }

        public static d d(d dVar) {
            d.b.a.a.x.c cVar = f3841a;
            return new d(cVar, cVar, dVar.f3843c, dVar.f3844d);
        }

        public static d e(d dVar, View view) {
            return q.h(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            d.b.a.a.x.c cVar = dVar.f3842b;
            d.b.a.a.x.c cVar2 = f3841a;
            return new d(cVar, cVar2, dVar.f3843c, cVar2);
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z);
    }

    private class f implements MaterialButton.b {
        private f() {
        }

        @Override // com.google.android.material.button.MaterialButton.b
        public void a(MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup.this.invalidate();
        }

        /* synthetic */ f(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public MaterialButtonToggleGroup(Context context) {
        this(context, null);
    }

    private void g() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
            MaterialButton materialButtonL = l(i2);
            int iMin = Math.min(materialButtonL.getStrokeWidth(), l(i2 - 1).getStrokeWidth());
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

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (o(i2)) {
                return i2;
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
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if ((getChildAt(i3) instanceof MaterialButton) && o(i3)) {
                i2++;
            }
        }
        return i2;
    }

    private LinearLayout.LayoutParams h(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    private void i(int i2) {
        r(i2, true);
        t(i2, true);
        setCheckedId(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(int i2, boolean z) {
        Iterator<e> it = this.f3835g.iterator();
        while (it.hasNext()) {
            it.next().a(this, i2, z);
        }
    }

    private MaterialButton l(int i2) {
        return (MaterialButton) getChildAt(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int m(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == view) {
                return i2;
            }
            if ((getChildAt(i3) instanceof MaterialButton) && o(i3)) {
                i2++;
            }
        }
        return -1;
    }

    private d n(int i2, int i3, int i4) {
        d dVar = this.f3832d.get(i2);
        if (i3 == i4) {
            return dVar;
        }
        boolean z = getOrientation() == 0;
        if (i2 == i3) {
            return z ? d.e(dVar, this) : d.f(dVar);
        }
        if (i2 == i4) {
            return z ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    private boolean o(int i2) {
        return getChildAt(i2).getVisibility() != 8;
    }

    private void q(int i2) {
        if (getChildCount() == 0 || i2 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) l(i2).getLayoutParams();
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

    private void r(int i2, boolean z) {
        View viewFindViewById = findViewById(i2);
        if (viewFindViewById instanceof MaterialButton) {
            this.j = true;
            ((MaterialButton) viewFindViewById).setChecked(z);
            this.j = false;
        }
    }

    private static void s(m.b bVar, d dVar) {
        if (dVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.E(dVar.f3842b).v(dVar.f3845e).I(dVar.f3843c).z(dVar.f3844d);
        }
    }

    private void setCheckedId(int i2) {
        this.m = i2;
        k(i2, true);
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
        materialButton.a(this.f3833e);
        materialButton.setOnPressedChangeListenerInternal(this.f3834f);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean t(int i2, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.l && checkedButtonIds.isEmpty()) {
            r(i2, true);
            this.m = i2;
            return false;
        }
        if (z && this.k) {
            checkedButtonIds.remove(Integer.valueOf(i2));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                r(iIntValue, false);
                k(iIntValue, false);
            }
        }
        return true;
    }

    private void u() {
        TreeMap treeMap = new TreeMap(this.f3836h);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            treeMap.put(l(i2), Integer.valueOf(i2));
        }
        this.f3837i = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(f3830b, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i2, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            t(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        m shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f3832d.add(new d(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        t.j0(materialButton, new b());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        u();
        super.dispatchDraw(canvas);
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
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            MaterialButton materialButtonL = l(i2);
            if (materialButtonL.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonL.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        Integer[] numArr = this.f3837i;
        if (numArr != null && i3 < numArr.length) {
            return numArr[i3].intValue();
        }
        Log.w(f3830b, "Child order wasn't updated");
        return i3;
    }

    public void j() {
        this.j = true;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            MaterialButton materialButtonL = l(i2);
            materialButtonL.setChecked(false);
            k(materialButtonL.getId(), false);
        }
        this.j = false;
        setCheckedId(-1);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.m;
        if (i2 != -1) {
            i(i2);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        b.g.k.c0.c.C0(accessibilityNodeInfo).d0(c.b.b(1, getVisibleButtonCount(), false, p() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        v();
        g();
        super.onMeasure(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.e(this.f3833e);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f3832d.remove(iIndexOfChild);
        }
        v();
        g();
    }

    public boolean p() {
        return this.k;
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

    void v() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i2 = 0; i2 < childCount; i2++) {
            MaterialButton materialButtonL = l(i2);
            if (materialButtonL.getVisibility() != 8) {
                m.b bVarV = materialButtonL.getShapeAppearanceModel().v();
                s(bVarV, n(i2, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonL.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, d.b.a.a.b.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f3831c;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f3832d = new ArrayList();
        a aVar = null;
        this.f3833e = new c(this, aVar);
        this.f3834f = new f(this, aVar);
        this.f3835g = new LinkedHashSet<>();
        this.f3836h = new a();
        this.j = false;
        TypedArray typedArrayH = l.h(getContext(), attributeSet, d.b.a.a.l.MaterialButtonToggleGroup, i2, i3, new int[0]);
        setSingleSelection(typedArrayH.getBoolean(d.b.a.a.l.MaterialButtonToggleGroup_singleSelection, false));
        this.m = typedArrayH.getResourceId(d.b.a.a.l.MaterialButtonToggleGroup_checkedButton, -1);
        this.l = typedArrayH.getBoolean(d.b.a.a.l.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayH.recycle();
        t.t0(this, 1);
    }

    public void setSingleSelection(int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }
}
