package com.google.android.material.button;

import B2.O;
import F0.K;
import F1.H;
import K4.a;
import N1.C0420a;
import Q4.d;
import Q4.e;
import Q4.f;
import U4.k;
import Y4.j;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import b5.AbstractC0997a;
import com.google.android.material.timepicker.h;
import com.stingray.drivemodedetector.R;
import h6.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final /* synthetic */ int f12808v = 0;
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e f12809m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0420a f12810n;
    public final LinkedHashSet o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final K f12811p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Integer[] f12812q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f12813r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f12814s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f12815t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f12816u;

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        super(AbstractC0997a.a(context, attributeSet, R.attr.materialButtonToggleGroupStyle, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup), attributeSet, R.attr.materialButtonToggleGroupStyle);
        this.l = new ArrayList();
        this.f12809m = new e(this);
        this.f12810n = new C0420a(this);
        this.o = new LinkedHashSet();
        this.f12811p = new K(this, 3);
        this.f12813r = false;
        TypedArray typedArrayE = k.e(getContext(), attributeSet, a.f3593k, R.attr.materialButtonToggleGroupStyle, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup, new int[0]);
        setSingleSelection(typedArrayE.getBoolean(2, false));
        this.f12816u = typedArrayE.getResourceId(0, -1);
        this.f12815t = typedArrayE.getBoolean(1, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayE.recycle();
        int[] iArr = H.f2303a;
        setImportantForAccessibility(1);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            if (c(i7)) {
                return i7;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (c(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i7 = 0;
        for (int i10 = 0; i10 < getChildCount(); i10++) {
            if ((getChildAt(i10) instanceof MaterialButton) && c(i10)) {
                i7++;
            }
        }
        return i7;
    }

    private void setCheckedId(int i7) {
        this.f12816u = i7;
        b(i7, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            int[] iArr = H.f2303a;
            materialButton.setId(View.generateViewId());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.f12797p.add(this.f12809m);
        materialButton.setOnPressedChangeListenerInternal(this.f12810n);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i7 = firstVisibleChildIndex + 1; i7 < getChildCount(); i7++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i7);
            int iMin = Math.min(materialButton.getStrokeWidth(), ((MaterialButton) getChildAt(i7 - 1)).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
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
            materialButton.setLayoutParams(layoutParams2);
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

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            n.j("MaterialButtonToggleGroup", "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i7, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.f12806y) {
            d(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        Y4.k shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.l.add(new f(shapeAppearanceModel.f10750e, shapeAppearanceModel.f10753h, shapeAppearanceModel.f10751f, shapeAppearanceModel.f10752g));
        H.g(materialButton, new d(this, 0));
    }

    public final void b(int i7, boolean z6) {
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            ((h) it.next()).a();
        }
    }

    public final boolean c(int i7) {
        return getChildAt(i7).getVisibility() != 8;
    }

    public final boolean d(int i7, boolean z6) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.f12815t && checkedButtonIds.isEmpty()) {
            View viewFindViewById = findViewById(i7);
            if (viewFindViewById instanceof MaterialButton) {
                this.f12813r = true;
                ((MaterialButton) viewFindViewById).setChecked(true);
                this.f12813r = false;
            }
            this.f12816u = i7;
            return false;
        }
        if (z6 && this.f12814s) {
            checkedButtonIds.remove(Integer.valueOf(i7));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                View viewFindViewById2 = findViewById(iIntValue);
                if (viewFindViewById2 instanceof MaterialButton) {
                    this.f12813r = true;
                    ((MaterialButton) viewFindViewById2).setChecked(false);
                    this.f12813r = false;
                }
                b(iIntValue, false);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.f12811p);
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            treeMap.put((MaterialButton) getChildAt(i7), Integer.valueOf(i7));
        }
        this.f12812q = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        f fVar;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i7 = 0; i7 < childCount; i7++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i7);
            if (materialButton.getVisibility() != 8) {
                j jVarD = materialButton.getShapeAppearanceModel().d();
                f fVar2 = (f) this.l.get(i7);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z6 = getOrientation() == 0;
                    Y4.a aVar = f.f6240e;
                    if (i7 == firstVisibleChildIndex) {
                        if (z6) {
                            int[] iArr = H.f2303a;
                            fVar = getLayoutDirection() == 1 ? new f(aVar, aVar, fVar2.f6242b, fVar2.c) : new f(fVar2.f6241a, fVar2.d, aVar, aVar);
                        } else {
                            fVar = new f(fVar2.f6241a, aVar, fVar2.f6242b, aVar);
                        }
                    } else if (i7 != lastVisibleChildIndex) {
                        fVar2 = null;
                    } else if (z6) {
                        int[] iArr2 = H.f2303a;
                        fVar = getLayoutDirection() == 1 ? new f(fVar2.f6241a, fVar2.d, aVar, aVar) : new f(aVar, aVar, fVar2.f6242b, fVar2.c);
                    } else {
                        fVar = new f(aVar, fVar2.d, aVar, fVar2.c);
                    }
                    fVar2 = fVar;
                }
                if (fVar2 == null) {
                    jVarD.f10741e = new Y4.a(0.0f);
                    jVarD.f10742f = new Y4.a(0.0f);
                    jVarD.f10743g = new Y4.a(0.0f);
                    jVarD.f10744h = new Y4.a(0.0f);
                } else {
                    jVarD.f10741e = fVar2.f6241a;
                    jVarD.f10744h = fVar2.d;
                    jVarD.f10742f = fVar2.f6242b;
                    jVarD.f10743g = fVar2.c;
                }
                materialButton.setShapeAppearanceModel(jVarD.a());
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.f12814s) {
            return this.f12816u;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i7);
            if (materialButton.f12806y) {
                arrayList.add(Integer.valueOf(materialButton.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i7, int i10) {
        Integer[] numArr = this.f12812q;
        if (numArr != null && i10 < numArr.length) {
            return numArr[i10].intValue();
        }
        n.t("MaterialButtonToggleGroup", "Child order wasn't updated");
        return i10;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        MaterialButton materialButton;
        super.onFinishInflate();
        int i7 = this.f12816u;
        if (i7 == -1 || (materialButton = (MaterialButton) findViewById(i7)) == null) {
            return;
        }
        materialButton.setChecked(true);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) O.e0(1, getVisibleButtonCount(), this.f12814s ? 1 : 2).f527m);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        e();
        a();
        super.onMeasure(i7, i10);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.f12797p.remove(this.f12809m);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.l.remove(iIndexOfChild);
        }
        e();
        a();
    }

    public void setSelectionRequired(boolean z6) {
        this.f12815t = z6;
    }

    public void setSingleSelection(boolean z6) {
        if (this.f12814s != z6) {
            this.f12814s = z6;
            this.f12813r = true;
            for (int i7 = 0; i7 < getChildCount(); i7++) {
                MaterialButton materialButton = (MaterialButton) getChildAt(i7);
                materialButton.setChecked(false);
                b(materialButton.getId(), false);
            }
            this.f12813r = false;
            setCheckedId(-1);
        }
    }

    public void setSingleSelection(int i7) {
        setSingleSelection(getResources().getBoolean(i7));
    }
}
