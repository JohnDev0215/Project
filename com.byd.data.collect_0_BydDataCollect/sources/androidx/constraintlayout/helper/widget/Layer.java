package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.f;
import b.e.b.k.e;

/* JADX INFO: loaded from: classes.dex */
public class Layer extends ConstraintHelper {
    private boolean A;
    private float j;
    private float k;
    private float l;
    ConstraintLayout m;
    private float n;
    private float o;
    protected float p;
    protected float q;
    protected float r;
    protected float s;
    protected float t;
    protected float u;
    boolean v;
    View[] w;
    private float x;
    private float y;
    private boolean z;

    public Layer(Context context) {
        super(context);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    private void w() {
        int i2;
        if (this.m == null || (i2 = this.f993c) == 0) {
            return;
        }
        View[] viewArr = this.w;
        if (viewArr == null || viewArr.length != i2) {
            this.w = new View[i2];
        }
        for (int i3 = 0; i3 < this.f993c; i3++) {
            this.w[i3] = this.m.g(this.f992b[i3]);
        }
    }

    private void x() {
        if (this.m == null) {
            return;
        }
        if (this.w == null) {
            w();
        }
        v();
        double radians = Math.toRadians(this.l);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f2 = this.n;
        float f3 = f2 * fCos;
        float f4 = this.o;
        float f5 = (-f4) * fSin;
        float f6 = f2 * fSin;
        float f7 = f4 * fCos;
        for (int i2 = 0; i2 < this.f993c; i2++) {
            View view = this.w[i2];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f8 = left - this.p;
            float f9 = top - this.q;
            float f10 = (((f3 * f8) + (f5 * f9)) - f8) + this.x;
            float f11 = (((f8 * f6) + (f7 * f9)) - f9) + this.y;
            view.setTranslationX(f10);
            view.setTranslationY(f11);
            view.setScaleY(this.o);
            view.setScaleX(this.n);
            view.setRotation(this.l);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f996f = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.ConstraintLayout_Layout_android_visibility) {
                    this.z = true;
                } else if (index == f.ConstraintLayout_Layout_android_elevation) {
                    this.A = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.m = (ConstraintLayout) getParent();
        if (this.z || this.A) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i2 = 0; i2 < this.f993c; i2++) {
                View viewG = this.m.g(this.f992b[i2]);
                if (viewG != null) {
                    if (this.z) {
                        viewG.setVisibility(visibility);
                    }
                    if (this.A && elevation > 0.0f && Build.VERSION.SDK_INT >= 21) {
                        viewG.setTranslationZ(viewG.getTranslationZ() + elevation);
                    }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(ConstraintLayout constraintLayout) {
        w();
        this.p = Float.NaN;
        this.q = Float.NaN;
        e eVarB = ((ConstraintLayout.b) getLayoutParams()).b();
        eVarB.F0(0);
        eVarB.i0(0);
        v();
        layout(((int) this.t) - getPaddingLeft(), ((int) this.u) - getPaddingTop(), ((int) this.r) + getPaddingRight(), ((int) this.s) + getPaddingBottom());
        if (Float.isNaN(this.l)) {
            return;
        }
        x();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        this.m = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.l = rotation;
        } else {
            if (Float.isNaN(this.l)) {
                return;
            }
            this.l = rotation;
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        g();
    }

    @Override // android.view.View
    public void setPivotX(float f2) {
        this.j = f2;
        x();
    }

    @Override // android.view.View
    public void setPivotY(float f2) {
        this.k = f2;
        x();
    }

    @Override // android.view.View
    public void setRotation(float f2) {
        this.l = f2;
        x();
    }

    @Override // android.view.View
    public void setScaleX(float f2) {
        this.n = f2;
        x();
    }

    @Override // android.view.View
    public void setScaleY(float f2) {
        this.o = f2;
        x();
    }

    @Override // android.view.View
    public void setTranslationX(float f2) {
        this.x = f2;
        x();
    }

    @Override // android.view.View
    public void setTranslationY(float f2) {
        this.y = f2;
        x();
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        g();
    }

    protected void v() {
        if (this.m == null) {
            return;
        }
        if (this.v || Float.isNaN(this.p) || Float.isNaN(this.q)) {
            if (!Float.isNaN(this.j) && !Float.isNaN(this.k)) {
                this.q = this.k;
                this.p = this.j;
                return;
            }
            View[] viewArrL = l(this.m);
            int left = viewArrL[0].getLeft();
            int top = viewArrL[0].getTop();
            int right = viewArrL[0].getRight();
            int bottom = viewArrL[0].getBottom();
            for (int i2 = 0; i2 < this.f993c; i2++) {
                View view = viewArrL[i2];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.r = right;
            this.s = bottom;
            this.t = left;
            this.u = top;
            if (Float.isNaN(this.j)) {
                this.p = (left + right) / 2;
            } else {
                this.p = this.j;
            }
            if (Float.isNaN(this.k)) {
                this.q = (top + bottom) / 2;
            } else {
                this.q = this.k;
            }
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.j = Float.NaN;
        this.k = Float.NaN;
        this.l = Float.NaN;
        this.n = 1.0f;
        this.o = 1.0f;
        this.p = Float.NaN;
        this.q = Float.NaN;
        this.r = Float.NaN;
        this.s = Float.NaN;
        this.t = Float.NaN;
        this.u = Float.NaN;
        this.v = true;
        this.w = null;
        this.x = 0.0f;
        this.y = 0.0f;
    }
}
