package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public float H3;
    public float I3;
    public float J3;
    public ConstraintLayout K3;
    public float L3;
    public float M3;
    public float N3;
    public float O3;
    public float P3;
    public float Q3;
    public float R3;
    public float S3;
    public boolean T3;
    public View[] U3;
    public float V3;
    public float W3;
    public boolean X3;
    public boolean Y3;

    public Layer(Context context) {
        super(context);
        this.H3 = Float.NaN;
        this.I3 = Float.NaN;
        this.J3 = Float.NaN;
        this.L3 = 1.0f;
        this.M3 = 1.0f;
        this.N3 = Float.NaN;
        this.O3 = Float.NaN;
        this.P3 = Float.NaN;
        this.Q3 = Float.NaN;
        this.R3 = Float.NaN;
        this.S3 = Float.NaN;
        this.T3 = true;
        this.U3 = null;
        this.V3 = 0.0f;
        this.W3 = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.y = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.h1) {
                    this.X3 = true;
                } else if (index == e.o1) {
                    this.Y3 = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.K3 = (ConstraintLayout) getParent();
        if (this.X3 || this.Y3) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i2 = 0; i2 < this.f394d; i2++) {
                View viewG = this.K3.g(this.f393c[i2]);
                if (viewG != null) {
                    if (this.X3) {
                        viewG.setVisibility(visibility);
                    }
                    if (this.Y3 && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(ConstraintLayout constraintLayout) {
        w();
        this.N3 = Float.NaN;
        this.O3 = Float.NaN;
        ConstraintWidget constraintWidgetB = ((ConstraintLayout.b) getLayoutParams()).b();
        constraintWidgetB.F0(0);
        constraintWidgetB.i0(0);
        v();
        layout(((int) this.R3) - getPaddingLeft(), ((int) this.S3) - getPaddingTop(), ((int) this.P3) + getPaddingRight(), ((int) this.Q3) + getPaddingBottom());
        if (Float.isNaN(this.J3)) {
            return;
        }
        x();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        this.K3 = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.J3 = rotation;
        } else {
            if (Float.isNaN(this.J3)) {
                return;
            }
            this.J3 = rotation;
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        g();
    }

    @Override // android.view.View
    public void setPivotX(float f2) {
        this.H3 = f2;
        x();
    }

    @Override // android.view.View
    public void setPivotY(float f2) {
        this.I3 = f2;
        x();
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.J3 = f2;
        x();
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        this.L3 = f2;
        x();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        this.M3 = f2;
        x();
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        this.V3 = f2;
        x();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        this.W3 = f2;
        x();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        g();
    }

    public void v() {
        if (this.K3 == null) {
            return;
        }
        if (this.T3 || Float.isNaN(this.N3) || Float.isNaN(this.O3)) {
            if (!Float.isNaN(this.H3) && !Float.isNaN(this.I3)) {
                this.O3 = this.I3;
                this.N3 = this.H3;
                return;
            }
            View[] viewArrL = l(this.K3);
            int left = viewArrL[0].getLeft();
            int top = viewArrL[0].getTop();
            int right = viewArrL[0].getRight();
            int bottom = viewArrL[0].getBottom();
            for (int i2 = 0; i2 < this.f394d; i2++) {
                View view = viewArrL[i2];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.P3 = right;
            this.Q3 = bottom;
            this.R3 = left;
            this.S3 = top;
            if (Float.isNaN(this.H3)) {
                this.N3 = (left + right) / 2;
            } else {
                this.N3 = this.H3;
            }
            if (Float.isNaN(this.I3)) {
                this.O3 = (top + bottom) / 2;
            } else {
                this.O3 = this.I3;
            }
        }
    }

    public final void w() {
        int i2;
        if (this.K3 == null || (i2 = this.f394d) == 0) {
            return;
        }
        View[] viewArr = this.U3;
        if (viewArr == null || viewArr.length != i2) {
            this.U3 = new View[i2];
        }
        for (int i3 = 0; i3 < this.f394d; i3++) {
            this.U3[i3] = this.K3.g(this.f393c[i3]);
        }
    }

    public final void x() {
        if (this.K3 == null) {
            return;
        }
        if (this.U3 == null) {
            w();
        }
        v();
        double radians = Math.toRadians(this.J3);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f2 = this.L3;
        float f3 = f2 * fCos;
        float f4 = this.M3;
        float f5 = (-f4) * fSin;
        float f6 = f2 * fSin;
        float f7 = f4 * fCos;
        for (int i2 = 0; i2 < this.f394d; i2++) {
            View view = this.U3[i2];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f8 = left - this.N3;
            float f9 = top - this.O3;
            float f10 = (((f3 * f8) + (f5 * f9)) - f8) + this.V3;
            float f11 = (((f8 * f6) + (f7 * f9)) - f9) + this.W3;
            view.setTranslationX(f10);
            view.setTranslationY(f11);
            view.setScaleY(this.M3);
            view.setScaleX(this.L3);
            view.setRotation(this.J3);
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H3 = Float.NaN;
        this.I3 = Float.NaN;
        this.J3 = Float.NaN;
        this.L3 = 1.0f;
        this.M3 = 1.0f;
        this.N3 = Float.NaN;
        this.O3 = Float.NaN;
        this.P3 = Float.NaN;
        this.Q3 = Float.NaN;
        this.R3 = Float.NaN;
        this.S3 = Float.NaN;
        this.T3 = true;
        this.U3 = null;
        this.V3 = 0.0f;
        this.W3 = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.H3 = Float.NaN;
        this.I3 = Float.NaN;
        this.J3 = Float.NaN;
        this.L3 = 1.0f;
        this.M3 = 1.0f;
        this.N3 = Float.NaN;
        this.O3 = Float.NaN;
        this.P3 = Float.NaN;
        this.Q3 = Float.NaN;
        this.R3 = Float.NaN;
        this.S3 = Float.NaN;
        this.T3 = true;
        this.U3 = null;
        this.V3 = 0.0f;
        this.W3 = 0.0f;
    }
}
