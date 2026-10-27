package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public float A;
    public boolean B;
    public boolean C;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f810l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f812n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ConstraintLayout f813o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f814p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f815q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f816r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f817s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f818t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f819u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f820v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f821w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f822x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View[] f823y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f824z;

    public Layer(Context context) {
        super(context);
        this.f810l = Float.NaN;
        this.f811m = Float.NaN;
        this.f812n = Float.NaN;
        this.f814p = 1.0f;
        this.f815q = 1.0f;
        this.f816r = Float.NaN;
        this.f817s = Float.NaN;
        this.f818t = Float.NaN;
        this.f819u = Float.NaN;
        this.f820v = Float.NaN;
        this.f821w = Float.NaN;
        this.f822x = true;
        this.f823y = null;
        this.f824z = 0.0f;
        this.A = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f1065h = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_Layout_android_visibility) {
                    this.B = true;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_elevation) {
                    this.C = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f813o = (ConstraintLayout) getParent();
        if (this.B || this.C) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i8 = 0; i8 < this.f1062e; i8++) {
                View viewG = this.f813o.g(this.f1061d[i8]);
                if (viewG != null) {
                    if (this.B) {
                        viewG.setVisibility(visibility);
                    }
                    if (this.C && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(ConstraintLayout constraintLayout) {
        w();
        this.f816r = Float.NaN;
        this.f817s = Float.NaN;
        ConstraintWidget constraintWidgetB = ((ConstraintLayout.b) getLayoutParams()).b();
        constraintWidgetB.F0(0);
        constraintWidgetB.i0(0);
        v();
        layout(((int) this.f820v) - getPaddingLeft(), ((int) this.f821w) - getPaddingTop(), ((int) this.f818t) + getPaddingRight(), ((int) this.f819u) + getPaddingBottom());
        if (Float.isNaN(this.f812n)) {
            return;
        }
        x();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        this.f813o = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.f812n = rotation;
        } else {
            if (Float.isNaN(this.f812n)) {
                return;
            }
            this.f812n = rotation;
        }
    }

    @Override // android.view.View
    public void setElevation(float f8) {
        super.setElevation(f8);
        g();
    }

    @Override // android.view.View
    public void setPivotX(float f8) {
        this.f810l = f8;
        x();
    }

    @Override // android.view.View
    public void setPivotY(float f8) {
        this.f811m = f8;
        x();
    }

    @Override // android.view.View
    public void setRotation(float f8) {
        this.f812n = f8;
        x();
    }

    @Override // android.view.View
    public void setScaleX(float f8) {
        this.f814p = f8;
        x();
    }

    @Override // android.view.View
    public void setScaleY(float f8) {
        this.f815q = f8;
        x();
    }

    @Override // android.view.View
    public void setTranslationX(float f8) {
        this.f824z = f8;
        x();
    }

    @Override // android.view.View
    public void setTranslationY(float f8) {
        this.A = f8;
        x();
    }

    @Override // android.view.View
    public void setVisibility(int i8) {
        super.setVisibility(i8);
        g();
    }

    public void v() {
        if (this.f813o == null) {
            return;
        }
        if (this.f822x || Float.isNaN(this.f816r) || Float.isNaN(this.f817s)) {
            if (!Float.isNaN(this.f810l) && !Float.isNaN(this.f811m)) {
                this.f817s = this.f811m;
                this.f816r = this.f810l;
                return;
            }
            View[] viewArrL = l(this.f813o);
            int left = viewArrL[0].getLeft();
            int top = viewArrL[0].getTop();
            int right = viewArrL[0].getRight();
            int bottom = viewArrL[0].getBottom();
            for (int i8 = 0; i8 < this.f1062e; i8++) {
                View view = viewArrL[i8];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.f818t = right;
            this.f819u = bottom;
            this.f820v = left;
            this.f821w = top;
            if (Float.isNaN(this.f810l)) {
                this.f816r = (left + right) / 2;
            } else {
                this.f816r = this.f810l;
            }
            if (Float.isNaN(this.f811m)) {
                this.f817s = (top + bottom) / 2;
            } else {
                this.f817s = this.f811m;
            }
        }
    }

    public final void w() {
        int i8;
        if (this.f813o == null || (i8 = this.f1062e) == 0) {
            return;
        }
        View[] viewArr = this.f823y;
        if (viewArr == null || viewArr.length != i8) {
            this.f823y = new View[i8];
        }
        for (int i9 = 0; i9 < this.f1062e; i9++) {
            this.f823y[i9] = this.f813o.g(this.f1061d[i9]);
        }
    }

    public final void x() {
        if (this.f813o == null) {
            return;
        }
        if (this.f823y == null) {
            w();
        }
        v();
        double radians = Math.toRadians(this.f812n);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f8 = this.f814p;
        float f9 = f8 * fCos;
        float f10 = this.f815q;
        float f11 = (-f10) * fSin;
        float f12 = f8 * fSin;
        float f13 = f10 * fCos;
        for (int i8 = 0; i8 < this.f1062e; i8++) {
            View view = this.f823y[i8];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f14 = left - this.f816r;
            float f15 = top - this.f817s;
            float f16 = (((f9 * f14) + (f11 * f15)) - f14) + this.f824z;
            float f17 = (((f14 * f12) + (f13 * f15)) - f15) + this.A;
            view.setTranslationX(f16);
            view.setTranslationY(f17);
            view.setScaleY(this.f815q);
            view.setScaleX(this.f814p);
            view.setRotation(this.f812n);
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f810l = Float.NaN;
        this.f811m = Float.NaN;
        this.f812n = Float.NaN;
        this.f814p = 1.0f;
        this.f815q = 1.0f;
        this.f816r = Float.NaN;
        this.f817s = Float.NaN;
        this.f818t = Float.NaN;
        this.f819u = Float.NaN;
        this.f820v = Float.NaN;
        this.f821w = Float.NaN;
        this.f822x = true;
        this.f823y = null;
        this.f824z = 0.0f;
        this.A = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f810l = Float.NaN;
        this.f811m = Float.NaN;
        this.f812n = Float.NaN;
        this.f814p = 1.0f;
        this.f815q = 1.0f;
        this.f816r = Float.NaN;
        this.f817s = Float.NaN;
        this.f818t = Float.NaN;
        this.f819u = Float.NaN;
        this.f820v = Float.NaN;
        this.f821w = Float.NaN;
        this.f822x = true;
        this.f823y = null;
        this.f824z = 0.0f;
        this.A = 0.0f;
    }
}
