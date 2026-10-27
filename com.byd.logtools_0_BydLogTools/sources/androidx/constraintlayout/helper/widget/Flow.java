package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.e;
import androidx.constraintlayout.solver.widgets.h;
import androidx.constraintlayout.solver.widgets.j;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    private e l;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.l = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.b1) {
                    this.l.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.c1) {
                    this.l.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.m1) {
                    this.l.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.n1) {
                    this.l.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.d1) {
                    this.l.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.e1) {
                    this.l.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.f1) {
                    this.l.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.g1) {
                    this.l.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.L1) {
                    this.l.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.B1) {
                    this.l.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.K1) {
                    this.l.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.v1) {
                    this.l.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.D1) {
                    this.l.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.x1) {
                    this.l.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.F1) {
                    this.l.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.z1) {
                    this.l.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.u1) {
                    this.l.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.C1) {
                    this.l.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.w1) {
                    this.l.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.E1) {
                    this.l.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.I1) {
                    this.l.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.y1) {
                    this.l.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == androidx.constraintlayout.widget.e.H1) {
                    this.l.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == androidx.constraintlayout.widget.e.A1) {
                    this.l.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.J1) {
                    this.l.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.G1) {
                    this.l.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.f1083e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof e) {
            e eVar = (e) hVar;
            int i = bVar.S;
            if (i != -1) {
                eVar.T1(i);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        this.l.L0(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    protected void onMeasure(int i, int i2) {
        v(this.l, i, i2);
    }

    public void setFirstHorizontalBias(float f2) {
        this.l.G1(f2);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.l.H1(i);
        requestLayout();
    }

    public void setFirstVerticalBias(float f2) {
        this.l.I1(f2);
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

    public void setHorizontalBias(float f2) {
        this.l.L1(f2);
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

    public void setVerticalBias(float f2) {
        this.l.V1(f2);
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
    public void v(j jVar, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (jVar == null) {
            setMeasuredDimension(0, 0);
        } else {
            jVar.T0(mode, size, mode2, size2);
            setMeasuredDimension(jVar.O0(), jVar.N0());
        }
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
