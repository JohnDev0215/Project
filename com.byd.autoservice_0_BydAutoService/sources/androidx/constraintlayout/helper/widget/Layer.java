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
    public boolean A;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f715j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f716k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f717l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConstraintLayout f718m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f719n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f720o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f721p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f722q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f723r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f724s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f725t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f726u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f727v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View[] f728w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f729x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f730y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f731z;

    public Layer(Context context) {
        super(context);
        this.f715j = Float.NaN;
        this.f716k = Float.NaN;
        this.f717l = Float.NaN;
        this.f719n = 1.0f;
        this.f720o = 1.0f;
        this.f721p = Float.NaN;
        this.f722q = Float.NaN;
        this.f723r = Float.NaN;
        this.f724s = Float.NaN;
        this.f725t = Float.NaN;
        this.f726u = Float.NaN;
        this.f727v = true;
        this.f728w = null;
        this.f729x = 0.0f;
        this.f730y = 0.0f;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f974f = false;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_Layout_android_visibility) {
                    this.f731z = true;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_elevation) {
                    this.A = true;
                }
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f718m = (ConstraintLayout) getParent();
        if (this.f731z || this.A) {
            int visibility = getVisibility();
            float elevation = Build.VERSION.SDK_INT >= 21 ? getElevation() : 0.0f;
            for (int i3 = 0; i3 < this.f971c; i3++) {
                View viewG = this.f718m.g(this.f970b[i3]);
                if (viewG != null) {
                    if (this.f731z) {
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
        this.f721p = Float.NaN;
        this.f722q = Float.NaN;
        ConstraintWidget constraintWidgetB = ((ConstraintLayout.b) getLayoutParams()).b();
        constraintWidgetB.F0(0);
        constraintWidgetB.i0(0);
        v();
        layout(((int) this.f725t) - getPaddingLeft(), ((int) this.f726u) - getPaddingTop(), ((int) this.f723r) + getPaddingRight(), ((int) this.f724s) + getPaddingBottom());
        if (Float.isNaN(this.f717l)) {
            return;
        }
        x();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void r(ConstraintLayout constraintLayout) {
        this.f718m = constraintLayout;
        float rotation = getRotation();
        if (rotation != 0.0f) {
            this.f717l = rotation;
        } else {
            if (Float.isNaN(this.f717l)) {
                return;
            }
            this.f717l = rotation;
        }
    }

    @Override // android.view.View
    public void setElevation(float f3) {
        super.setElevation(f3);
        g();
    }

    @Override // android.view.View
    public void setPivotX(float f3) {
        this.f715j = f3;
        x();
    }

    @Override // android.view.View
    public void setPivotY(float f3) {
        this.f716k = f3;
        x();
    }

    @Override // android.view.View
    public void setRotation(float f3) {
        this.f717l = f3;
        x();
    }

    @Override // android.view.View
    public void setScaleX(float f3) {
        this.f719n = f3;
        x();
    }

    @Override // android.view.View
    public void setScaleY(float f3) {
        this.f720o = f3;
        x();
    }

    @Override // android.view.View
    public void setTranslationX(float f3) {
        this.f729x = f3;
        x();
    }

    @Override // android.view.View
    public void setTranslationY(float f3) {
        this.f730y = f3;
        x();
    }

    @Override // android.view.View
    public void setVisibility(int i3) {
        super.setVisibility(i3);
        g();
    }

    public void v() {
        if (this.f718m == null) {
            return;
        }
        if (this.f727v || Float.isNaN(this.f721p) || Float.isNaN(this.f722q)) {
            if (!Float.isNaN(this.f715j) && !Float.isNaN(this.f716k)) {
                this.f722q = this.f716k;
                this.f721p = this.f715j;
                return;
            }
            View[] viewArrL = l(this.f718m);
            int left = viewArrL[0].getLeft();
            int top = viewArrL[0].getTop();
            int right = viewArrL[0].getRight();
            int bottom = viewArrL[0].getBottom();
            for (int i3 = 0; i3 < this.f971c; i3++) {
                View view = viewArrL[i3];
                left = Math.min(left, view.getLeft());
                top = Math.min(top, view.getTop());
                right = Math.max(right, view.getRight());
                bottom = Math.max(bottom, view.getBottom());
            }
            this.f723r = right;
            this.f724s = bottom;
            this.f725t = left;
            this.f726u = top;
            if (Float.isNaN(this.f715j)) {
                this.f721p = (left + right) / 2;
            } else {
                this.f721p = this.f715j;
            }
            if (Float.isNaN(this.f716k)) {
                this.f722q = (top + bottom) / 2;
            } else {
                this.f722q = this.f716k;
            }
        }
    }

    public final void w() {
        int i3;
        if (this.f718m == null || (i3 = this.f971c) == 0) {
            return;
        }
        View[] viewArr = this.f728w;
        if (viewArr == null || viewArr.length != i3) {
            this.f728w = new View[i3];
        }
        for (int i4 = 0; i4 < this.f971c; i4++) {
            this.f728w[i4] = this.f718m.g(this.f970b[i4]);
        }
    }

    public final void x() {
        if (this.f718m == null) {
            return;
        }
        if (this.f728w == null) {
            w();
        }
        v();
        double radians = Math.toRadians(this.f717l);
        float fSin = (float) Math.sin(radians);
        float fCos = (float) Math.cos(radians);
        float f3 = this.f719n;
        float f4 = f3 * fCos;
        float f5 = this.f720o;
        float f6 = (-f5) * fSin;
        float f7 = f3 * fSin;
        float f8 = f5 * fCos;
        for (int i3 = 0; i3 < this.f971c; i3++) {
            View view = this.f728w[i3];
            int left = (view.getLeft() + view.getRight()) / 2;
            int top = (view.getTop() + view.getBottom()) / 2;
            float f9 = left - this.f721p;
            float f10 = top - this.f722q;
            float f11 = (((f4 * f9) + (f6 * f10)) - f9) + this.f729x;
            float f12 = (((f9 * f7) + (f8 * f10)) - f10) + this.f730y;
            view.setTranslationX(f11);
            view.setTranslationY(f12);
            view.setScaleY(this.f720o);
            view.setScaleX(this.f719n);
            view.setRotation(this.f717l);
        }
    }

    public Layer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f715j = Float.NaN;
        this.f716k = Float.NaN;
        this.f717l = Float.NaN;
        this.f719n = 1.0f;
        this.f720o = 1.0f;
        this.f721p = Float.NaN;
        this.f722q = Float.NaN;
        this.f723r = Float.NaN;
        this.f724s = Float.NaN;
        this.f725t = Float.NaN;
        this.f726u = Float.NaN;
        this.f727v = true;
        this.f728w = null;
        this.f729x = 0.0f;
        this.f730y = 0.0f;
    }

    public Layer(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f715j = Float.NaN;
        this.f716k = Float.NaN;
        this.f717l = Float.NaN;
        this.f719n = 1.0f;
        this.f720o = 1.0f;
        this.f721p = Float.NaN;
        this.f722q = Float.NaN;
        this.f723r = Float.NaN;
        this.f724s = Float.NaN;
        this.f725t = Float.NaN;
        this.f726u = Float.NaN;
        this.f727v = true;
        this.f728w = null;
        this.f729x = 0.0f;
        this.f730y = 0.0f;
    }
}
