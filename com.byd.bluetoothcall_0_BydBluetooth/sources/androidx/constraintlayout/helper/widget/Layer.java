package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.e;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private boolean A;
    private boolean B;
    private float k;
    private float l;
    private float m;
    ConstraintLayout n;
    private float o;
    private float p;
    protected float q;
    protected float r;
    protected float s;
    protected float t;
    protected float u;
    protected float v;
    boolean w;
    View[] x;
    private float y;
    private float z;

    public Layer(Context context) {
        super(context);
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = Float.NaN;
        this.o = 1.0f;
        this.p = 1.0f;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = true;
        this.x = null;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    private void y() {
        int i;
        if (this.n == null || (i = this.f1029c) == 0) {
            return;
        }
        View[] viewArr = this.x;
        if (viewArr == null || viewArr.length != i) {
            this.x = new View[i];
        }
        for (int i2 = 0; i2 < this.f1029c; i2++) {
            this.x[i2] = this.n.k(this.f1028b[i2]);
        }
    }

    private void z() {
        if (this.n == null) {
            return;
        }
        if (this.x == null) {
            y();
        }
        x();
        double radians = Float.isNaN(this.m) ? 0.0d : Math.toRadians(this.m);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f = this.o;
        float f2 = f * fCos;
        float f3 = this.p;
        float f4 = (-f3) * fSin;
        float f5 = f * fSin;
        float f6 = f3 * fCos;
        for (int i = 0; i < this.f1029c; i++) {
            View view = this.x[i];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f7 = left - this.q;
            float f8 = top - this.r;
            float f9 = (((f2 * f7) + (f4 * f8)) - f7) + this.y;
            float f10 = (((f7 * f5) + (f6 * f8)) - f8) + this.z;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.p);
            view.setScaleX(this.o);
            if (!Float.isNaN(this.m)) {
                view.setRotation(this.m);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void j(ConstraintLayout constraintLayout) {
        i(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.f = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.E1) {
                    this.A = true;
                } else if (index == e.L1) {
                    this.B = true;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.n = (ConstraintLayout) getParent();
        if (this.A || this.B) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i = 0; i < this.f1029c; i++) {
                View viewK = this.n.k(this.f1028b[i]);
                if (viewK != null) {
                    if (this.A) {
                        viewK.setVisibility(visibility);
                    }
                    if (this.B && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewK.setTranslationZ(viewK.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        y();
        this.q = Float.NaN;
        this.r = Float.NaN;
        ConstraintWidget constraintWidgetB = ((ConstraintLayout.b) getLayoutParams()).b();
        constraintWidgetB.n1(0);
        constraintWidgetB.O0(0);
        x();
        layout(((int) this.u) - getPaddingLeft(), ((int) this.v) - getPaddingTop(), ((int) this.s) + getPaddingRight(), ((int) this.t) + getPaddingBottom());
        z();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        h();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.k = f;
        z();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.l = f;
        z();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.m = f;
        z();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.o = f;
        z();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.p = f;
        z();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.y = f;
        z();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.z = f;
        z();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        h();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void t(ConstraintLayout constraintLayout) {
        this.n = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.m = rotation;
        } else {
            if (Float.isNaN(this.m)) {
                return;
            }
            this.m = rotation;
        }
    }

    protected void x() {
        if (this.n == null) {
            return;
        }
        if (this.w || Float.isNaN(this.q) || Float.isNaN(this.r)) {
            if (!Float.isNaN(this.k) && !Float.isNaN(this.l)) {
                this.r = this.l;
                this.q = this.k;
                return;
            }
            View[] viewArrN = n(this.n);
            int left = viewArrN[0].getLeft();
            int top = viewArrN[0].getTop();
            int right = viewArrN[0].getRight();
            int bottom = viewArrN[0].getBottom();
            for (int i = 0; i < this.f1029c; i++) {
                View view = viewArrN[i];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.s = right;
            this.t = bottom;
            this.u = left;
            this.v = top;
            if (Float.isNaN(this.k)) {
                this.q = (left + right) / 2;
            } else {
                this.q = this.k;
            }
            if (Float.isNaN(this.l)) {
                this.r = (top + bottom) / 2;
            } else {
                this.r = this.l;
            }
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = Float.NaN;
        this.o = 1.0f;
        this.p = 1.0f;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = true;
        this.x = null;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.m = Float.NaN;
        this.o = 1.0f;
        this.p = 1.0f;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = true;
        this.x = null;
        this.y = 0.0f;
        this.z = 0.0f;
    }
}
