package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import defpackage.a6;
import defpackage.c6;
import defpackage.q6;
import defpackage.x5;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public x5 l;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.l = new x5();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.l.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    this.l.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.l.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.l.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.l.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.l.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.l.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.l.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.l.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.l.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.l.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.l.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.l.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.l.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.l.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.l.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.l.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.l.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.l.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.l.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.l.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.l.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.l.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.l.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.l.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.l.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(q6.a aVar, a6 a6Var, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, a6Var, bVar, sparseArray);
        if (a6Var instanceof x5) {
            x5 x5Var = (x5) a6Var;
            int i = bVar.S;
            if (i != -1) {
                x5Var.T1(i);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        this.l.L0(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i, int i2) {
        v(this.l, i, i2);
    }

    public void setFirstHorizontalBias(float f) {
        this.l.G1(f);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.l.H1(i);
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.l.I1(f);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.l.J1(i);
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.l.K1(i);
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.l.L1(f);
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.l.M1(i);
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.l.N1(i);
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.l.S1(i);
        requestLayout();
    }

    public void setOrientation(int i) {
        this.l.T1(i);
        requestLayout();
    }

    public void setPadding(int i) {
        this.l.Z0(i);
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.l.a1(i);
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.l.c1(i);
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.l.d1(i);
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.l.f1(i);
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.l.U1(i);
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.l.V1(f);
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.l.W1(i);
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.l.X1(i);
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.l.Y1(i);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void v(c6 c6Var, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (c6Var == null) {
            setMeasuredDimension(0, 0);
        } else {
            c6Var.T0(mode, size, mode2, size2);
            setMeasuredDimension(c6Var.O0(), c6Var.N0());
        }
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
