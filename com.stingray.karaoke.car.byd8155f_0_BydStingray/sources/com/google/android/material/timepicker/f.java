package com.google.android.material.timepicker;

import F1.H;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.stingray.drivemodedetector.R;
import java.util.HashMap;
import r1.C2276h;
import r1.C2277i;
import r1.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class f extends ConstraintLayout {
    public final e B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f12971C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Y4.g f12972D;

    public f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        Y4.g gVar = new Y4.g();
        this.f12972D = gVar;
        Y4.h hVar = new Y4.h(0.5f);
        Y4.j jVarD = gVar.l.f10708a.d();
        jVarD.f10741e = hVar;
        jVarD.f10742f = hVar;
        jVarD.f10743g = hVar;
        jVarD.f10744h = hVar;
        gVar.setShapeAppearanceModel(jVarD.a());
        this.f12972D.h(ColorStateList.valueOf(-1));
        Y4.g gVar2 = this.f12972D;
        int[] iArr = H.f2303a;
        setBackground(gVar2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, K4.a.f3596p, R.attr.materialClockStyle, 0);
        this.f12971C = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.B = new e(this);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i7, layoutParams);
        if (view.getId() == -1) {
            int[] iArr = H.f2303a;
            view.setId(View.generateViewId());
        }
        Handler handler = getHandler();
        if (handler != null) {
            e eVar = this.B;
            handler.removeCallbacks(eVar);
            handler.post(eVar);
        }
    }

    public final void k() {
        int childCount = getChildCount();
        int i7 = 1;
        for (int i10 = 0; i10 < childCount; i10++) {
            if ("skip".equals(getChildAt(i10).getTag())) {
                i7++;
            }
        }
        m mVar = new m();
        mVar.b(this);
        float f10 = 0.0f;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getId() != R.id.circle_center && !"skip".equals(childAt.getTag())) {
                int id = childAt.getId();
                int i12 = this.f12971C;
                HashMap map = mVar.c;
                if (!map.containsKey(Integer.valueOf(id))) {
                    map.put(Integer.valueOf(id), new C2276h());
                }
                C2277i c2277i = ((C2276h) map.get(Integer.valueOf(id))).d;
                c2277i.f21737z = R.id.circle_center;
                c2277i.f21676A = i12;
                c2277i.B = f10;
                f10 = (360.0f / (childCount - i7)) + f10;
            }
        }
        mVar.a(this);
        setConstraintSet(null);
        requestLayout();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        k();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            e eVar = this.B;
            handler.removeCallbacks(eVar);
            handler.post(eVar);
        }
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i7) {
        this.f12972D.h(ColorStateList.valueOf(i7));
    }
}
