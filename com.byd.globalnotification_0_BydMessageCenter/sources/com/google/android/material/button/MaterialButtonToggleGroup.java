package com.google.android.material.button;

import a.b.k.l;
import a.h.k.q;
import a.h.k.x.b;
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
import b.d.a.a.e0.j;
import b.d.a.a.j;
import b.d.a.a.z.k;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final String k = MaterialButtonToggleGroup.class.getSimpleName();
    public static final int l = j.Widget_MaterialComponents_MaterialButtonToggleGroup;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<d> f2408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f2409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f2410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet<e> f2411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Comparator<MaterialButton> f2412e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Integer[] f2413f;
    public boolean g;
    public boolean h;
    public boolean i;
    public int j;

    public class a implements Comparator<MaterialButton> {
        public a() {
        }

        @Override // java.util.Comparator
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            MaterialButton materialButton3 = materialButton;
            MaterialButton materialButton4 = materialButton2;
            int iCompareTo = Boolean.valueOf(materialButton3.isChecked()).compareTo(Boolean.valueOf(materialButton4.isChecked()));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            int iCompareTo2 = Boolean.valueOf(materialButton3.isPressed()).compareTo(Boolean.valueOf(materialButton4.isPressed()));
            return iCompareTo2 != 0 ? iCompareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton3)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton4)));
        }
    }

    public class b extends a.h.k.a {
        public b() {
        }

        @Override // a.h.k.a
        public void d(View view, a.h.k.x.b bVar) {
            this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
            bVar.i(b.c.a(0, 1, MaterialButtonToggleGroup.a(MaterialButtonToggleGroup.this, view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    public class c implements MaterialButton.a {
        public c(a aVar) {
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup materialButtonToggleGroup = MaterialButtonToggleGroup.this;
            if (materialButtonToggleGroup.g) {
                return;
            }
            if (materialButtonToggleGroup.h) {
                materialButtonToggleGroup.j = z ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.g(materialButton.getId(), z)) {
                MaterialButtonToggleGroup.this.c(materialButton.getId(), materialButton.isChecked());
            }
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    public static class d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final b.d.a.a.e0.c f2417e = new b.d.a.a.e0.a(0.0f);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b.d.a.a.e0.c f2418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.d.a.a.e0.c f2419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.d.a.a.e0.c f2420c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.d.a.a.e0.c f2421d;

        public d(b.d.a.a.e0.c cVar, b.d.a.a.e0.c cVar2, b.d.a.a.e0.c cVar3, b.d.a.a.e0.c cVar4) {
            this.f2418a = cVar;
            this.f2419b = cVar3;
            this.f2420c = cVar4;
            this.f2421d = cVar2;
        }
    }

    public interface e {
        void a(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z);
    }

    public class f implements MaterialButton.b {
        public f(a aVar) {
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        int i = b.d.a.a.b.materialButtonToggleGroupStyle;
        super(b.d.a.a.i0.a.a.a(context, attributeSet, i, l), attributeSet, i);
        this.f2408a = new ArrayList();
        this.f2409b = new c(null);
        this.f2410c = new f(null);
        this.f2411d = new LinkedHashSet<>();
        this.f2412e = new a();
        this.g = false;
        TypedArray typedArrayD = k.d(getContext(), attributeSet, b.d.a.a.k.MaterialButtonToggleGroup, i, l, new int[0]);
        setSingleSelection(typedArrayD.getBoolean(b.d.a.a.k.MaterialButtonToggleGroup_singleSelection, false));
        this.j = typedArrayD.getResourceId(b.d.a.a.k.MaterialButtonToggleGroup_checkedButton, -1);
        this.i = typedArrayD.getBoolean(b.d.a.a.k.MaterialButtonToggleGroup_selectionRequired, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayD.recycle();
        q.b0(this, 1);
    }

    public static int a(MaterialButtonToggleGroup materialButtonToggleGroup, View view) {
        if (materialButtonToggleGroup == null) {
            throw null;
        }
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i = 0;
        for (int i2 = 0; i2 < materialButtonToggleGroup.getChildCount(); i2++) {
            if (materialButtonToggleGroup.getChildAt(i2) == view) {
                return i;
            }
            if ((materialButtonToggleGroup.getChildAt(i2) instanceof MaterialButton) && materialButtonToggleGroup.e(i2)) {
                i++;
            }
        }
        return -1;
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (e(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (e(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && e(i2)) {
                i++;
            }
        }
        return i;
    }

    private void setCheckedId(int i) {
        this.j = i;
        c(i, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(q.e());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.f2404d.add(this.f2409b);
        materialButton.setOnPressedChangeListenerInternal(this.f2410c);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(k, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            g(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        b.d.a.a.e0.j shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.f2408a.add(new d(shapeAppearanceModel.f1847e, shapeAppearanceModel.h, shapeAppearanceModel.f1848f, shapeAppearanceModel.g));
        q.U(materialButton, new b());
    }

    public final void b() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButtonD = d(i);
            int iMin = Math.min(materialButtonD.getStrokeWidth(), d(i - 1).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButtonD.getLayoutParams();
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
            materialButtonD.setLayoutParams(layoutParams2);
        }
        if (getChildCount() == 0 || firstVisibleChildIndex == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ((MaterialButton) getChildAt(firstVisibleChildIndex)).getLayoutParams();
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

    public final void c(int i, boolean z) {
        Iterator<e> it = this.f2411d.iterator();
        while (it.hasNext()) {
            it.next().a(this, i, z);
        }
    }

    public final MaterialButton d(int i) {
        return (MaterialButton) getChildAt(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.f2412e);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put(d(i), Integer.valueOf(i));
        }
        this.f2413f = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final boolean e(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    public final void f(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById instanceof MaterialButton) {
            this.g = true;
            ((MaterialButton) viewFindViewById).setChecked(z);
            this.g = false;
        }
    }

    public final boolean g(int i, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.i && checkedButtonIds.isEmpty()) {
            f(i, true);
            this.j = i;
            return false;
        }
        if (z && this.h) {
            checkedButtonIds.remove(Integer.valueOf(i));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                f(iIntValue, false);
                c(iIntValue, false);
            }
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.h) {
            return this.j;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton materialButtonD = d(i);
            if (materialButtonD.isChecked()) {
                arrayList.add(Integer.valueOf(materialButtonD.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.f2413f;
        if (numArr != null && i2 < numArr.length) {
            return numArr[i2].intValue();
        }
        Log.w(k, "Child order wasn't updated");
        return i2;
    }

    public void h() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButtonD = d(i);
            if (materialButtonD.getVisibility() != 8) {
                b.d.a.a.e0.j shapeAppearanceModel = materialButtonD.getShapeAppearanceModel();
                d dVar = null;
                if (shapeAppearanceModel == null) {
                    throw null;
                }
                j.b bVar = new j.b(shapeAppearanceModel);
                d dVar2 = this.f2408a.get(i);
                if (firstVisibleChildIndex == lastVisibleChildIndex) {
                    dVar = dVar2;
                } else {
                    boolean z = getOrientation() == 0;
                    if (i == firstVisibleChildIndex) {
                        if (!z) {
                            b.d.a.a.e0.c cVar = dVar2.f2418a;
                            b.d.a.a.e0.c cVar2 = d.f2417e;
                            dVar = new d(cVar, cVar2, dVar2.f2419b, cVar2);
                        } else if (l.i.M(this)) {
                            b.d.a.a.e0.c cVar3 = d.f2417e;
                            dVar = new d(cVar3, cVar3, dVar2.f2419b, dVar2.f2420c);
                        } else {
                            b.d.a.a.e0.c cVar4 = dVar2.f2418a;
                            b.d.a.a.e0.c cVar5 = dVar2.f2421d;
                            b.d.a.a.e0.c cVar6 = d.f2417e;
                            dVar = new d(cVar4, cVar5, cVar6, cVar6);
                        }
                    } else if (i == lastVisibleChildIndex) {
                        if (!z) {
                            b.d.a.a.e0.c cVar7 = d.f2417e;
                            dVar = new d(cVar7, dVar2.f2421d, cVar7, dVar2.f2420c);
                        } else if (l.i.M(this)) {
                            b.d.a.a.e0.c cVar8 = dVar2.f2418a;
                            b.d.a.a.e0.c cVar9 = dVar2.f2421d;
                            b.d.a.a.e0.c cVar10 = d.f2417e;
                            dVar = new d(cVar8, cVar9, cVar10, cVar10);
                        } else {
                            b.d.a.a.e0.c cVar11 = d.f2417e;
                            dVar = new d(cVar11, cVar11, dVar2.f2419b, dVar2.f2420c);
                        }
                    }
                }
                if (dVar == null) {
                    bVar.c(0.0f);
                } else {
                    bVar.f1853e = dVar.f2418a;
                    bVar.h = dVar.f2421d;
                    bVar.f1854f = dVar.f2419b;
                    bVar.g = dVar.f2420c;
                }
                materialButtonD.setShapeAppearanceModel(bVar.a());
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        MaterialButton materialButton;
        super.onFinishInflate();
        int i = this.j;
        if (i == -1 || (materialButton = (MaterialButton) findViewById(i)) == null) {
            return;
        }
        materialButton.setChecked(true);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) b.C0016b.a(1, getVisibleButtonCount(), false, this.h ? 1 : 2).f865a);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        h();
        b();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.f2404d.remove(this.f2409b);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.f2408a.remove(iIndexOfChild);
        }
        h();
        b();
    }

    public void setSelectionRequired(boolean z) {
        this.i = z;
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }

    public void setSingleSelection(boolean z) {
        if (this.h != z) {
            this.h = z;
            this.g = true;
            for (int i = 0; i < getChildCount(); i++) {
                MaterialButton materialButtonD = d(i);
                materialButtonD.setChecked(false);
                c(materialButtonD.getId(), false);
            }
            this.g = false;
            setCheckedId(-1);
        }
    }
}
