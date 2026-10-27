package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.qx;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    public View[] A;
    public float B;
    public float C;
    public boolean D;
    public boolean E;
    public float n;
    public float o;
    public float p;
    public ConstraintLayout q;
    public float r;
    public float s;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x;
    public float y;
    public final boolean z;

    public Layer(Context context) {
        super(context);
        this.n = Float.NaN;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.r = 1.0f;
        this.s = 1.0f;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = true;
        this.A = null;
        this.B = 0.0f;
        this.C = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void j(ConstraintLayout constraintLayout) {
        i(constraintLayout);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        this.i = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_visibility) {
                    this.D = true;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_elevation) {
                    this.E = true;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = (ConstraintLayout) getParent();
        if (this.D || this.E) {
            int visibility = getVisibility();
            float elevation = getElevation();
            for (int i = 0; i < this.f; i++) {
                View viewC = this.q.c(this.e[i]);
                if (viewC != null) {
                    if (this.D) {
                        viewC.setVisibility(visibility);
                    }
                    if (this.E && elevation > 0.0f) {
                        viewC.setTranslationZ(viewC.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void q() {
        v();
        this.t = Float.NaN;
        this.u = Float.NaN;
        qx qxVar = ((ConstraintLayout.LayoutParams) getLayoutParams()).q0;
        qxVar.R(0);
        qxVar.O(0);
        u();
        layout(((int) this.x) - getPaddingLeft(), ((int) this.y) - getPaddingTop(), getPaddingRight() + ((int) this.v), getPaddingBottom() + ((int) this.w));
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void r(ConstraintLayout constraintLayout) {
        this.q = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.p = rotation;
        } else {
            if (Float.isNaN(this.p)) {
                return;
            }
            this.p = rotation;
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        h();
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        this.n = f;
        w();
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        this.o = f;
        w();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.p = f;
        w();
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        this.r = f;
        w();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        this.s = f;
        w();
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        this.B = f;
        w();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        this.C = f;
        w();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        h();
    }

    public final void u() {
        if (this.q == null) {
            return;
        }
        if (this.z || Float.isNaN(this.t) || Float.isNaN(this.u)) {
            if (!Float.isNaN(this.n) && !Float.isNaN(this.o)) {
                this.u = this.o;
                this.t = this.n;
                return;
            }
            View[] viewArrM = m(this.q);
            int left = viewArrM[0].getLeft();
            int top = viewArrM[0].getTop();
            int right = viewArrM[0].getRight();
            int bottom = viewArrM[0].getBottom();
            for (int i = 0; i < this.f; i++) {
                View view = viewArrM[i];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.v = right;
            this.w = bottom;
            this.x = left;
            this.y = top;
            if (Float.isNaN(this.n)) {
                this.t = (left + right) / 2;
            } else {
                this.t = this.n;
            }
            if (Float.isNaN(this.o)) {
                this.u = (top + bottom) / 2;
            } else {
                this.u = this.o;
            }
        }
    }

    public final void v() {
        int i;
        if (this.q == null || (i = this.f) == 0) {
            return;
        }
        View[] viewArr = this.A;
        if (viewArr == null || viewArr.length != i) {
            this.A = new View[i];
        }
        for (int i2 = 0; i2 < this.f; i2++) {
            this.A[i2] = this.q.c(this.e[i2]);
        }
    }

    public final void w() {
        if (this.q == null) {
            return;
        }
        if (this.A == null) {
            v();
        }
        u();
        double radians = Float.isNaN(this.p) ? 0.0d : Math.toRadians(this.p);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f = this.r;
        float f2 = f * fCos;
        float f3 = this.s;
        float f4 = (-f3) * fSin;
        float f5 = f * fSin;
        float f6 = f3 * fCos;
        for (int i = 0; i < this.f; i++) {
            View view = this.A[i];
            int right = (view.getRight() + view.getLeft()) / 2;
            int bottom = (view.getBottom() + view.getTop()) / 2;
            float f7 = right - this.t;
            float f8 = bottom - this.u;
            float f9 = (((f4 * f8) + (f2 * f7)) - f7) + this.B;
            float f10 = (((f6 * f8) + (f7 * f5)) - f8) + this.C;
            view.setTranslationX(f9);
            view.setTranslationY(f10);
            view.setScaleY(this.s);
            view.setScaleX(this.r);
            if (!Float.isNaN(this.p)) {
                view.setRotation(this.p);
            }
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = Float.NaN;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.r = 1.0f;
        this.s = 1.0f;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = true;
        this.A = null;
        this.B = 0.0f;
        this.C = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = Float.NaN;
        this.o = Float.NaN;
        this.p = Float.NaN;
        this.r = 1.0f;
        this.s = 1.0f;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = Float.NaN;
        this.x = Float.NaN;
        this.y = Float.NaN;
        this.z = true;
        this.A = null;
        this.B = 0.0f;
        this.C = 0.0f;
    }
}
