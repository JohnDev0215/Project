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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.j;
import com.google.android.material.internal.l;
import e3.k;
import i0.h;
import i0.u;
import j0.c;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import o2.k;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final String f4253p = MaterialButtonToggleGroup.class.getSimpleName();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private static final int f4254q = k.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final List<d> f4255e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final c f4256g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f f4257h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final LinkedHashSet<e> f4258i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Comparator<MaterialButton> f4259j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Integer[] f4260k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f4261l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f4262m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4263n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f4264o;

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

    class b extends i0.a {
        b() {
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.S(c.C0090c.a(0, 1, MaterialButtonToggleGroup.this.n(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    private class c implements MaterialButton.a {
        private c() {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z6) {
            if (MaterialButtonToggleGroup.this.f4261l) {
                return;
            }
            if (MaterialButtonToggleGroup.this.f4262m) {
                MaterialButtonToggleGroup.this.f4264o = z6 ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.u(materialButton.getId(), z6)) {
                MaterialButtonToggleGroup.this.l(materialButton.getId(), materialButton.isChecked());
            }
            MaterialButtonToggleGroup.this.invalidate();
        }

        /* synthetic */ c(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    private static class d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final e3.c f4268e = new e3.a(0.0f);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        e3.c f4269a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        e3.c f4270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e3.c f4271c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e3.c f4272d;

        d(e3.c cVar, e3.c cVar2, e3.c cVar3, e3.c cVar4) {
            this.f4269a = cVar;
            this.f4270b = cVar3;
            this.f4271c = cVar4;
            this.f4272d = cVar2;
        }

        public static d a(d dVar) {
            e3.c cVar = f4268e;
            return new d(cVar, dVar.f4272d, cVar, dVar.f4271c);
        }

        public static d b(d dVar, View view) {
            return l.d(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            e3.c cVar = dVar.f4269a;
            e3.c cVar2 = dVar.f4272d;
            e3.c cVar3 = f4268e;
            return new d(cVar, cVar2, cVar3, cVar3);
        }

        public static d d(d dVar) {
            e3.c cVar = f4268e;
            return new d(cVar, cVar, dVar.f4270b, dVar.f4271c);
        }

        public static d e(d dVar, View view) {
            return l.d(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            e3.c cVar = dVar.f4269a;
            e3.c cVar2 = f4268e;
            return new d(cVar, cVar2, dVar.f4270b, cVar2);
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i7, boolean z6);
    }

    private class f implements MaterialButton.b {
        private f() {
        }

        @Override // com.google.android.material.button.MaterialButton.b
        public void a(MaterialButton materialButton, boolean z6) {
            MaterialButtonToggleGroup.this.invalidate();
        }

        /* synthetic */ f(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }
    }

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.materialButtonToggleGroupStyle);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            if (p(i7)) {
                return i7;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (p(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if ((getChildAt(i8) instanceof MaterialButton) && p(i8)) {
                i7++;
            }
        }
        return i7;
    }

    private void h() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i7 = firstVisibleChildIndex + 1; i7 < getChildCount(); i7++) {
            MaterialButton materialButtonM = m(i7);
            int iMin = Math.min(materialButtonM.getStrokeWidth(), m(i7 - 1).getStrokeWidth());
            LinearLayout.LayoutParams layoutParamsI = i(materialButtonM);
            if (getOrientation() == 0) {
                h.c(layoutParamsI, 0);
                h.d(layoutParamsI, -iMin);
                layoutParamsI.topMargin = 0;
            } else {
                layoutParamsI.bottomMargin = 0;
                layoutParamsI.topMargin = -iMin;
                h.d(layoutParamsI, 0);
            }
            materialButtonM.setLayoutParams(layoutParamsI);
        }
        r(firstVisibleChildIndex);
    }

    private LinearLayout.LayoutParams i(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    private void j(int i7, boolean z6) {
        MaterialButton materialButton = (MaterialButton) findViewById(i7);
        if (materialButton != null) {
            materialButton.setChecked(z6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(int i7, boolean z6) {
        Iterator<e> it = this.f4258i.iterator();
        while (it.hasNext()) {
            it.next().a(this, i7, z6);
        }
    }

    private MaterialButton m(int i7) {
        return (MaterialButton) getChildAt(i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int n(View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            if (getChildAt(i8) == view) {
                return i7;
            }
            if ((getChildAt(i8) instanceof MaterialButton) && p(i8)) {
                i7++;
            }
        }
        return -1;
    }

    private d o(int i7, int i8, int i9) {
        d dVar = this.f4255e.get(i7);
        if (i8 == i9) {
            return dVar;
        }
        boolean z6 = getOrientation() == 0;
        if (i7 == i8) {
            return z6 ? d.e(dVar, this) : d.f(dVar);
        }
        if (i7 == i9) {
            return z6 ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    private boolean p(int i7) {
        return getChildAt(i7).getVisibility() != 8;
    }

    private void r(int i7) {
        if (getChildCount() == 0 || i7 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) m(i7).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            h.c(layoutParams, 0);
            h.d(layoutParams, 0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    private void s(int i7, boolean z6) {
        View viewFindViewById = findViewById(i7);
        if (viewFindViewById instanceof MaterialButton) {
            this.f4261l = true;
            ((MaterialButton) viewFindViewById).setChecked(z6);
            this.f4261l = false;
        }
    }

    private void setCheckedId(int i7) {
        this.f4264o = i7;
        l(i7, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(u.h());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.a(this.f4256g);
        materialButton.setOnPressedChangeListenerInternal(this.f4257h);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    private static void t(k.b bVar, d dVar) {
        if (dVar == null) {
            bVar.o(0.0f);
        } else {
            bVar.B(dVar.f4269a).t(dVar.f4272d).F(dVar.f4270b).x(dVar.f4271c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean u(int i7, boolean z6) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.f4263n && checkedButtonIds.isEmpty()) {
            s(i7, true);
            this.f4264o = i7;
            return false;
        }
        if (z6 && this.f4262m) {
            checkedButtonIds.remove(Integer.valueOf(i7));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                s(iIntValue, false);
                l(iIntValue, false);
            }
        }
        return true;
    }

    private void v() {
        TreeMap treeMap = new TreeMap(this.f4259j);
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            treeMap.put(m(i7), Integer.valueOf(i7));
        }
        this.f4260k = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(f4253p, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i7, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            u(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        e3.k shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f4255e.add(new d(shapeAppearanceModel.r(), shapeAppearanceModel.j(), shapeAppearanceModel.t(), shapeAppearanceModel.l()));
        u.k0(materialButton, new b());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        v();
        super.dispatchDraw(canvas);
    }

    public void g(e eVar) {
        this.f4258i.add(eVar);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.f4262m) {
            return this.f4264o;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            MaterialButton materialButtonM = m(i7);
            if (materialButtonM.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonM.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i7, int i8) {
        Integer[] numArr = this.f4260k;
        if (numArr != null && i8 < numArr.length) {
            return numArr[i8].intValue();
        }
        Log.w(f4253p, "Child order wasn't updated");
        return i8;
    }

    public void k() {
        this.f4261l = true;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            MaterialButton materialButtonM = m(i7);
            materialButtonM.setChecked(false);
            l(materialButtonM.getId(), false);
        }
        this.f4261l = false;
        setCheckedId(-1);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i7 = this.f4264o;
        if (i7 != -1) {
            j(i7, true);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        j0.c.p0(accessibilityNodeInfo).R(c.b.a(1, getVisibleButtonCount(), false, q() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        w();
        h();
        super.onMeasure(i7, i8);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.h(this.f4256g);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f4255e.remove(iIndexOfChild);
        }
        w();
        h();
    }

    public boolean q() {
        return this.f4262m;
    }

    public void setSelectionRequired(boolean z6) {
        this.f4263n = z6;
    }

    public void setSingleSelection(boolean z6) {
        if (this.f4262m != z6) {
            this.f4262m = z6;
            k();
        }
    }

    void w() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i7 = 0; i7 < childCount; i7++) {
            MaterialButton materialButtonM = m(i7);
            if (materialButtonM.getVisibility() != 8) {
                k.b bVarV = materialButtonM.getShapeAppearanceModel().v();
                t(bVarV, o(i7, firstVisibleChildIndex, lastVisibleChildIndex));
                materialButtonM.setShapeAppearanceModel(bVarV.m());
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f4254q;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        this.f4255e = new ArrayList();
        a aVar = null;
        this.f4256g = new c(this, aVar);
        this.f4257h = new f(this, aVar);
        this.f4258i = new LinkedHashSet<>();
        this.f4259j = new a();
        this.f4261l = false;
        TypedArray typedArrayH = j.h(getContext(), attributeSet, o2.l.MaterialButtonToggleGroup, i7, i8, new int[0]);
        setSingleSelection(typedArrayH.getBoolean(o2.l.MaterialButtonToggleGroup_singleSelection, false));
        this.f4264o = typedArrayH.getResourceId(o2.l.MaterialButtonToggleGroup_checkedButton, -1);
        this.f4263n = typedArrayH.getBoolean(o2.l.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayH.recycle();
        u.t0(this, 1);
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }
}
