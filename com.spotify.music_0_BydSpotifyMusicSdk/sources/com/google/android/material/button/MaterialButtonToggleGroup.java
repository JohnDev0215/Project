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
import com.google.android.material.timepicker.e;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.WeakHashMap;
import p.cf1;
import p.df1;
import p.ef1;
import p.ex1;
import p.f60;
import p.it2;
import p.jt2;
import p.oc2;
import p.oh1;
import p.v03;
import p.wg;
import p.xe1;
import p.xz1;
import p.y0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    public static final /* synthetic */ int s = 0;
    public final ArrayList c;
    public final df1 e;
    public final oh1 k;
    public final LinkedHashSet l;
    public final ex1 m;
    public Integer[] n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f31p;
    public boolean q;
    public int r;

    public MaterialButtonToggleGroup(Context context, AttributeSet attributeSet) {
        super(f60.S(context, attributeSet, R.attr.materialButtonToggleGroupStyle, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup), attributeSet, R.attr.materialButtonToggleGroupStyle);
        this.c = new ArrayList();
        this.e = new df1(this);
        this.k = new oh1(this);
        this.l = new LinkedHashSet();
        this.m = new ex1(1, this);
        this.o = false;
        TypedArray typedArrayE = v03.E(getContext(), attributeSet, xz1.l, R.attr.materialButtonToggleGroupStyle, R.style.Widget_MaterialComponents_MaterialButtonToggleGroup, new int[0]);
        setSingleSelection(typedArrayE.getBoolean(2, false));
        this.r = typedArrayE.getResourceId(0, -1);
        this.q = typedArrayE.getBoolean(1, false);
        setChildrenDrawingOrderEnabled(true);
        typedArrayE.recycle();
        WeakHashMap weakHashMap = zt2.a;
        it2.s(this, 1);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (c(i)) {
                return i;
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
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            if ((getChildAt(i2) instanceof MaterialButton) && c(i2)) {
                i++;
            }
        }
        return i;
    }

    private void setCheckedId(int i) {
        this.r = i;
        b(i, true);
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            WeakHashMap weakHashMap = zt2.a;
            materialButton.setId(jt2.a());
        }
    }

    private void setupButtonChild(MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.m.add(this.e);
        materialButton.setOnPressedChangeListenerInternal(this.k);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public final void a() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            int iMin = Math.min(materialButton.getStrokeWidth(), ((MaterialButton) getChildAt(i - 1)).getStrokeWidth());
            ViewGroup.LayoutParams layoutParams = materialButton.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
            if (getOrientation() == 0) {
                xe1.g(layoutParams2, 0);
                xe1.h(layoutParams2, -iMin);
                layoutParams2.topMargin = 0;
            } else {
                layoutParams2.bottomMargin = 0;
                layoutParams2.topMargin = -iMin;
                xe1.h(layoutParams2, 0);
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
            xe1.g(layoutParams3, 0);
            xe1.h(layoutParams3, 0);
            layoutParams3.leftMargin = 0;
            layoutParams3.rightMargin = 0;
        }
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
        if (materialButton.isChecked()) {
            d(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        oc2 shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.c.add(new ef1(shapeAppearanceModel.e, shapeAppearanceModel.h, shapeAppearanceModel.f, shapeAppearanceModel.g));
        zt2.k(materialButton, new cf1(this, 0));
    }

    public final void b(int i, boolean z) {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((e) it.next()).a();
        }
    }

    public final boolean c(int i) {
        return getChildAt(i).getVisibility() != 8;
    }

    public final boolean d(int i, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.q && checkedButtonIds.isEmpty()) {
            View viewFindViewById = findViewById(i);
            if (viewFindViewById instanceof MaterialButton) {
                this.o = true;
                ((MaterialButton) viewFindViewById).setChecked(true);
                this.o = false;
            }
            this.r = i;
            return false;
        }
        if (z && this.f31p) {
            checkedButtonIds.remove(Integer.valueOf(i));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                View viewFindViewById2 = findViewById(iIntValue);
                if (viewFindViewById2 instanceof MaterialButton) {
                    this.o = true;
                    ((MaterialButton) viewFindViewById2).setChecked(false);
                    this.o = false;
                }
                b(iIntValue, false);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        TreeMap treeMap = new TreeMap(this.m);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            treeMap.put((MaterialButton) getChildAt(i), Integer.valueOf(i));
        }
        this.n = (Integer[]) treeMap.values().toArray(new Integer[0]);
        super.dispatchDraw(canvas);
    }

    public final void e() {
        ef1 ef1Var;
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i = 0; i < childCount; i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            if (materialButton.getVisibility() != 8) {
                wg wgVarE = materialButton.getShapeAppearanceModel().e();
                ef1 ef1Var2 = (ef1) this.c.get(i);
                if (firstVisibleChildIndex != lastVisibleChildIndex) {
                    boolean z = getOrientation() == 0;
                    y0 y0Var = ef1.e;
                    if (i == firstVisibleChildIndex) {
                        if (z) {
                            WeakHashMap weakHashMap = zt2.a;
                            ef1Var = jt2.d(this) == 1 ? new ef1(y0Var, y0Var, ef1Var2.b, ef1Var2.c) : new ef1(ef1Var2.a, ef1Var2.d, y0Var, y0Var);
                        } else {
                            ef1Var = new ef1(ef1Var2.a, y0Var, ef1Var2.b, y0Var);
                        }
                    } else if (i != lastVisibleChildIndex) {
                        ef1Var2 = null;
                    } else if (z) {
                        WeakHashMap weakHashMap2 = zt2.a;
                        ef1Var = jt2.d(this) == 1 ? new ef1(ef1Var2.a, ef1Var2.d, y0Var, y0Var) : new ef1(y0Var, y0Var, ef1Var2.b, ef1Var2.c);
                    } else {
                        ef1Var = new ef1(y0Var, ef1Var2.d, y0Var, ef1Var2.c);
                    }
                    ef1Var2 = ef1Var;
                }
                if (ef1Var2 == null) {
                    wgVarE.e = new y0(0.0f);
                    wgVarE.f = new y0(0.0f);
                    wgVarE.g = new y0(0.0f);
                    wgVarE.h = new y0(0.0f);
                } else {
                    wgVarE.e = ef1Var2.a;
                    wgVarE.h = ef1Var2.d;
                    wgVarE.f = ef1Var2.b;
                    wgVarE.g = ef1Var2.c;
                }
                materialButton.setShapeAppearanceModel(wgVarE.a());
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    public int getCheckedButtonId() {
        if (this.f31p) {
            return this.r;
        }
        return -1;
    }

    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton materialButton = (MaterialButton) getChildAt(i);
            if (materialButton.isChecked()) {
                arrayList.add(Integer.valueOf(materialButton.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        Integer[] numArr = this.n;
        return (numArr == null || i2 >= numArr.length) ? i2 : numArr[i2].intValue();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        MaterialButton materialButton;
        super.onFinishInflate();
        int i = this.r;
        if (i == -1 || (materialButton = (MaterialButton) findViewById(i)) == null) {
            return;
        }
        materialButton.setChecked(true);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(1, getVisibleButtonCount(), false, this.f31p ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        e();
        a();
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.m.remove(this.e);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int iIndexOfChild = indexOfChild(view);
        if (iIndexOfChild >= 0) {
            this.c.remove(iIndexOfChild);
        }
        e();
        a();
    }

    public void setSelectionRequired(boolean z) {
        this.q = z;
    }

    public void setSingleSelection(boolean z) {
        if (this.f31p != z) {
            this.f31p = z;
            this.o = true;
            for (int i = 0; i < getChildCount(); i++) {
                MaterialButton materialButton = (MaterialButton) getChildAt(i);
                materialButton.setChecked(false);
                b(materialButton.getId(), false);
            }
            this.o = false;
            setCheckedId(-1);
        }
    }

    public void setSingleSelection(int i) {
        setSingleSelection(getResources().getBoolean(i));
    }
}
