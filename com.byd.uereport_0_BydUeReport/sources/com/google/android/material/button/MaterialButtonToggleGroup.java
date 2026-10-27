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
import e6.m;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import o0.g;
import o0.t;
import p0.c;
import z5.k;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3732d = MaterialButtonToggleGroup.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3733e = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<d> f3734f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c f3735g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f3736h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LinkedHashSet<e> f3737i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Comparator<MaterialButton> f3738j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Integer[] f3739k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3740l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3741m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3742n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3743o;

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

    public class b extends o0.a {
        public b() {
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            cVar.f0(c.C0101c.a(0, 1, MaterialButtonToggleGroup.this.m(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public class c implements MaterialButton.a {
        public c() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z7) {
            if (MaterialButtonToggleGroup.this.f3740l) {
                return;
            }
            if (MaterialButtonToggleGroup.this.f3741m) {
                MaterialButtonToggleGroup.this.f3743o = z7 ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.t(materialButton.getId(), z7)) {
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
        public static final e6.c f3747a = new e6.a(0.0f);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public e6.c f3748b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public e6.c f3749c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public e6.c f3750d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e6.c f3751e;

        public d(e6.c cVar, e6.c cVar2, e6.c cVar3, e6.c cVar4) {
            this.f3748b = cVar;
            this.f3749c = cVar3;
            this.f3750d = cVar4;
            this.f3751e = cVar2;
        }

        public static d a(d dVar) {
            e6.c cVar = f3747a;
            return new d(cVar, dVar.f3751e, cVar, dVar.f3750d);
        }

        public static d b(d dVar, View view) {
            return p.h(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            e6.c cVar = dVar.f3748b;
            e6.c cVar2 = dVar.f3751e;
            e6.c cVar3 = f3747a;
            return new d(cVar, cVar2, cVar3, cVar3);
        }

        public static d d(d dVar) {
            e6.c cVar = f3747a;
            return new d(cVar, cVar, dVar.f3749c, dVar.f3750d);
        }

        public static d e(d dVar, View view) {
            return p.h(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            e6.c cVar = dVar.f3748b;
            e6.c cVar2 = f3747a;
            return new d(cVar, cVar2, dVar.f3749c, cVar2);
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i8, boolean z7);
    }

    public class f implements MaterialButton.b {
        public f() {
        }

        @Override // com.google.android.material.button.MaterialButton.b
        public void a(MaterialButton materialButton, boolean z7) {
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
        for (int i8 = 0; i8 < childCount; i8++) {
            if (o(i8)) {
                return i8;
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
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if ((getChildAt(i9) instanceof MaterialButton) && o(i9)) {
                i8++;
            }
        }
        return i8;
    }

    public static void s(m.b bVar, d dVar) {
        if (dVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.E(dVar.f3748b).v(dVar.f3751e).I(dVar.f3749c).z(dVar.f3750d);
        }
    }

    private void setCheckedId(int i8) {
        this.f3743o = i8;
        k(i8, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(t.k());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.a(this.f3735g);
        materialButton.setOnPressedChangeListenerInternal(this.f3736h);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(f3732d, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i8, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            t(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        m shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f3734f.add(new d(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        t.l0(materialButton, new b());
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
        for (int i8 = firstVisibleChildIndex + 1; i8 < getChildCount(); i8++) {
            MaterialButton materialButtonL = l(i8);
            int iMin = Math.min(materialButtonL.getStrokeWidth(), l(i8 - 1).getStrokeWidth());
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
        if (this.f3741m) {
            return this.f3743o;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            MaterialButton materialButtonL = l(i8);
            if (materialButtonL.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonL.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i8, int i9) {
        Integer[] numArr = this.f3739k;
        if (numArr != null && i9 < numArr.length) {
            return numArr[i9].intValue();
        }
        Log.w(f3732d, "Child order wasn't updated");
        return i9;
    }

    public final LinearLayout.LayoutParams h(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    public final void i(int i8) {
        r(i8, true);
        t(i8, true);
        setCheckedId(i8);
    }

    public void j() {
        this.f3740l = true;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            MaterialButton materialButtonL = l(i8);
            materialButtonL.setChecked(false);
            k(materialButtonL.getId(), false);
        }
        this.f3740l = false;
        setCheckedId(-1);
    }

    public final void k(int i8, boolean z7) {
        Iterator<e> it = this.f3737i.iterator();
        while (it.hasNext()) {
            it.next().a(this, i8, z7);
        }
    }

    public final MaterialButton l(int i8) {
        return (MaterialButton) getChildAt(i8);
    }

    public final int m(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < getChildCount(); i9++) {
            if (getChildAt(i9) == view) {
                return i8;
            }
            if ((getChildAt(i9) instanceof MaterialButton) && o(i9)) {
                i8++;
            }
        }
        return -1;
    }

    public final d n(int i8, int i9, int i10) {
        d dVar = this.f3734f.get(i8);
        if (i9 == i10) {
            return dVar;
        }
        boolean z7 = getOrientation() == 0;
        if (i8 == i9) {
            return z7 ? d.e(dVar, this) : d.f(dVar);
        }
        if (i8 == i10) {
            return z7 ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    public final boolean o(int i8) {
        return getChildAt(i8).getVisibility() != 8;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        int i8 = this.f3743o;
        if (i8 != -1) {
            i(i8);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        p0.c.F0(accessibilityNodeInfo).e0(c.b.b(1, getVisibleButtonCount(), false, p() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        v();
        g();
        super.onMeasure(i8, i9);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.e(this.f3735g);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f3734f.remove(iIndexOfChild);
        }
        v();
        g();
    }

    public boolean p() {
        return this.f3741m;
    }

    public final void q(int i8) {
        if (getChildCount() == 0 || i8 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) l(i8).getLayoutParams();
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

    public final void r(int i8, boolean z7) {
        View viewFindViewById = findViewById(i8);
        if (viewFindViewById instanceof MaterialButton) {
            this.f3740l = true;
            ((MaterialButton) viewFindViewById).setChecked(z7);
            this.f3740l = false;
        }
    }

    public void setSelectionRequired(boolean z7) {
        this.f3742n = z7;
    }

    public void setSingleSelection(boolean z7) {
        if (this.f3741m != z7) {
            this.f3741m = z7;
            j();
        }
    }

    public final boolean t(int i8, boolean z7) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.f3742n && checkedButtonIds.isEmpty()) {
            r(i8, true);
            this.f3743o = i8;
            return false;
        }
        if (z7 && this.f3741m) {
            checkedButtonIds.remove(Integer.valueOf(i8));
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
        TreeMap treeMap = new TreeMap(this.f3738j);
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            treeMap.put(l(i8), Integer.valueOf(i8));
        }
        this.f3739k = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    public void v() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i8 = 0; i8 < childCount; i8++) {
            MaterialButton materialButtonL = l(i8);
            if (materialButtonL.getVisibility() != 8) {
                m.b bVarV = materialButtonL.getShapeAppearanceModel().v();
                s(bVarV, n(i8, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonL.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3733e;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3734f = new ArrayList();
        a aVar = null;
        this.f3735g = new c(this, aVar);
        this.f3736h = new f(this, aVar);
        this.f3737i = new LinkedHashSet<>();
        this.f3738j = new a();
        this.f3740l = false;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.MaterialButtonToggleGroup, i8, i9, new int[0]);
        setSingleSelection(typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_singleSelection, false));
        this.f3743o = typedArrayH.getResourceId(R$styleable.MaterialButtonToggleGroup_checkedButton, -1);
        this.f3742n = typedArrayH.getBoolean(R$styleable.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayH.recycle();
        t.v0(this, 1);
    }

    public void setSingleSelection(int i8) {
        setSingleSelection(getResources().getBoolean(i8));
    }
}
