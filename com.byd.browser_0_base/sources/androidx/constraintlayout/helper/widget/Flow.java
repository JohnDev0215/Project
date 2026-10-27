package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.a;
import defpackage.ku0;
import defpackage.lo3;
import defpackage.qx;
import defpackage.wn0;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public wn0 p;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        this.p = new wn0();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.p.b1 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    wn0 wn0Var = this.p;
                    int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    wn0Var.y0 = dimensionPixelSize;
                    wn0Var.z0 = dimensionPixelSize;
                    wn0Var.A0 = dimensionPixelSize;
                    wn0Var.B0 = dimensionPixelSize;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    wn0 wn0Var2 = this.p;
                    int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                    wn0Var2.A0 = dimensionPixelSize2;
                    wn0Var2.C0 = dimensionPixelSize2;
                    wn0Var2.D0 = dimensionPixelSize2;
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.p.B0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.p.C0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.p.y0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.p.D0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.p.z0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.p.Z0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.p.J0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.p.K0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.p.L0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.p.N0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.p.M0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.p.O0 = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.p.P0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.p.R0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.p.T0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.p.S0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.p.U0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.p.Q0 = typedArrayObtainStyledAttributes.getFloat(index, 0.5f);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.p.X0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.p.Y0 = typedArrayObtainStyledAttributes.getInt(index, 2);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.p.V0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.p.W0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.p.a1 = typedArrayObtainStyledAttributes.getInt(index, -1);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.h = this.p;
        t();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void o(a.C0004a c0004a, ku0 ku0Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.o(c0004a, ku0Var, layoutParams, sparseArray);
        if (ku0Var instanceof wn0) {
            wn0 wn0Var = (wn0) ku0Var;
            int i = layoutParams.V;
            if (i != -1) {
                wn0Var.b1 = i;
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public final void onMeasure(int i, int i2) {
        u(this.p, i, i2);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void p(qx qxVar, boolean z) {
        wn0 wn0Var = this.p;
        int i = wn0Var.A0;
        if (i > 0 || wn0Var.B0 > 0) {
            if (z) {
                wn0Var.C0 = wn0Var.B0;
                wn0Var.D0 = i;
            } else {
                wn0Var.C0 = i;
                wn0Var.D0 = wn0Var.B0;
            }
        }
    }

    public void setFirstHorizontalBias(float f) {
        this.p.R0 = f;
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.p.L0 = i;
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.p.S0 = f;
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.p.M0 = i;
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.p.X0 = i;
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.p.P0 = f;
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.p.V0 = i;
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.p.J0 = i;
        requestLayout();
    }

    public void setLastHorizontalBias(float f) {
        this.p.T0 = f;
        requestLayout();
    }

    public void setLastHorizontalStyle(int i) {
        this.p.N0 = i;
        requestLayout();
    }

    public void setLastVerticalBias(float f) {
        this.p.U0 = f;
        requestLayout();
    }

    public void setLastVerticalStyle(int i) {
        this.p.O0 = i;
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.p.a1 = i;
        requestLayout();
    }

    public void setOrientation(int i) {
        this.p.b1 = i;
        requestLayout();
    }

    public void setPadding(int i) {
        wn0 wn0Var = this.p;
        wn0Var.y0 = i;
        wn0Var.z0 = i;
        wn0Var.A0 = i;
        wn0Var.B0 = i;
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.p.z0 = i;
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.p.C0 = i;
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.p.D0 = i;
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.p.y0 = i;
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.p.Y0 = i;
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.p.Q0 = f;
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.p.W0 = i;
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.p.K0 = i;
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.p.Z0 = i;
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public final void u(lo3 lo3Var, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (lo3Var == null) {
            setMeasuredDimension(0, 0);
        } else {
            lo3Var.V(mode, size, mode2, size2);
            setMeasuredDimension(lo3Var.F0, lo3Var.G0);
        }
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
