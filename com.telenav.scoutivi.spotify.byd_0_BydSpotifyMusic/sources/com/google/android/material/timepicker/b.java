package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.d;
import e3.g;
import e3.i;
import i0.u;
import o2.f;
import o2.h;
import o2.l;

/* JADX INFO: compiled from: RadialViewGroup.java */
/* JADX INFO: loaded from: classes.dex */
class b extends ConstraintLayout {
    private final Runnable D;
    private int E;
    private g F;

    /* JADX INFO: compiled from: RadialViewGroup.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.F();
        }
    }

    public b(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private Drawable B() {
        g gVar = new g();
        this.F = gVar;
        gVar.V(new i(0.5f));
        this.F.X(ColorStateList.valueOf(-1));
        return this.F;
    }

    private static boolean E(View view) {
        return "skip".equals(view.getTag());
    }

    private void G() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.D);
            handler.post(this.D);
        }
    }

    public int C() {
        return this.E;
    }

    public void D(int i7) {
        this.E = i7;
        F();
    }

    protected void F() {
        int childCount = getChildCount();
        int i7 = 1;
        for (int i8 = 0; i8 < childCount; i8++) {
            if (E(getChildAt(i8))) {
                i7++;
            }
        }
        d dVar = new d();
        dVar.p(this);
        float f7 = 0.0f;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            int id = childAt.getId();
            int i10 = f.circle_center;
            if (id != i10 && !E(childAt)) {
                dVar.s(childAt.getId(), i10, this.E, f7);
                f7 += 360.0f / (childCount - i7);
            }
        }
        dVar.i(this);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i7, layoutParams);
        if (view.getId() == -1) {
            view.setId(u.h());
        }
        G();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        F();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        G();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i7) {
        this.F.X(ColorStateList.valueOf(i7));
    }

    public b(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        LayoutInflater.from(context).inflate(h.material_radial_view_group, this);
        u.n0(this, B());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.RadialViewGroup, i7, 0);
        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(l.RadialViewGroup_materialCircleRadius, 0);
        this.D = new a();
        typedArrayObtainStyledAttributes.recycle();
    }
}
