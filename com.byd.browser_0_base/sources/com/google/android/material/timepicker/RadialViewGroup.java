package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.a;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$styleable;
import defpackage.in3;
import defpackage.ma2;
import defpackage.om3;
import defpackage.sc1;
import defpackage.uj2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class RadialViewGroup extends ConstraintLayout {
    public final a u;
    public int v;
    public sc1 w;

    public RadialViewGroup(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            view.setId(om3.e.a());
        }
        Handler handler = getHandler();
        if (handler != null) {
            a aVar = this.u;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        s();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            a aVar = this.u;
            handler.removeCallbacks(aVar);
            handler.post(aVar);
        }
    }

    public final void s() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            if ("skip".equals(getChildAt(i2).getTag())) {
                i++;
            }
        }
        androidx.constraintlayout.widget.a aVar = new androidx.constraintlayout.widget.a();
        aVar.e(this);
        float f = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            int i4 = R$id.circle_center;
            if (id != i4 && !"skip".equals(childAt.getTag())) {
                int id2 = childAt.getId();
                int i5 = this.v;
                a.b bVar = aVar.h(id2).e;
                bVar.A = i4;
                bVar.B = i5;
                bVar.C = f;
                f = (360.0f / (childCount - i)) + f;
            }
        }
        aVar.b(this);
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        this.w.n(ColorStateList.valueOf(i));
    }

    public RadialViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [com.google.android.material.timepicker.a] */
    public RadialViewGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R$layout.material_radial_view_group, this);
        sc1 sc1Var = new sc1();
        this.w = sc1Var;
        ma2 ma2Var = new ma2(0.5f);
        uj2 uj2Var = sc1Var.e.a;
        uj2Var.getClass();
        uj2.a aVar = new uj2.a(uj2Var);
        aVar.e = ma2Var;
        aVar.f = ma2Var;
        aVar.g = ma2Var;
        aVar.h = ma2Var;
        sc1Var.setShapeAppearanceModel(new uj2(aVar));
        this.w.n(ColorStateList.valueOf(-1));
        sc1 sc1Var2 = this.w;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(this, sc1Var2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadialViewGroup, i, 0);
        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RadialViewGroup_materialCircleRadius, 0);
        this.u = new Runnable() { // from class: com.google.android.material.timepicker.a
            @Override // java.lang.Runnable
            public final void run() {
                this.e.s();
            }
        };
        typedArrayObtainStyledAttributes.recycle();
    }
}
