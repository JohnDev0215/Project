package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.VirtualLayout;
import b.e.b.j.e;
import b.e.b.j.h;
import b.e.b.j.j;
import b.e.c.b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public e J3;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.J3 = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.e.c.e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == b.e.c.e.b1) {
                    this.J3.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.c1) {
                    this.J3.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.m1) {
                    this.J3.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.n1) {
                    this.J3.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.d1) {
                    this.J3.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.e1) {
                    this.J3.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.f1) {
                    this.J3.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.g1) {
                    this.J3.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.L1) {
                    this.J3.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.B1) {
                    this.J3.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.K1) {
                    this.J3.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.v1) {
                    this.J3.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.D1) {
                    this.J3.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.x1) {
                    this.J3.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.F1) {
                    this.J3.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.e.c.e.z1) {
                    this.J3.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.u1) {
                    this.J3.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.C1) {
                    this.J3.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.w1) {
                    this.J3.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.E1) {
                    this.J3.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.I1) {
                    this.J3.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.e.c.e.y1) {
                    this.J3.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == b.e.c.e.H1) {
                    this.J3.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == b.e.c.e.A1) {
                    this.J3.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.J1) {
                    this.J3.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.e.c.e.G1) {
                    this.J3.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.x = this.J3;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof e) {
            e eVar = (e) hVar;
            int i2 = bVar.S;
            if (i2 != -1) {
                eVar.T1(i2);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        this.J3.L0(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i2, int i3) {
        v(this.J3, i2, i3);
    }

    public void setFirstHorizontalBias(float f2) {
        this.J3.G1(f2);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i2) {
        this.J3.H1(i2);
        requestLayout();
    }

    public void setFirstVerticalBias(float f2) {
        this.J3.I1(f2);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i2) {
        this.J3.J1(i2);
        requestLayout();
    }

    public void setHorizontalAlign(int i2) {
        this.J3.K1(i2);
        requestLayout();
    }

    public void setHorizontalBias(float f2) {
        this.J3.L1(f2);
        requestLayout();
    }

    public void setHorizontalGap(int i2) {
        this.J3.M1(i2);
        requestLayout();
    }

    public void setHorizontalStyle(int i2) {
        this.J3.N1(i2);
        requestLayout();
    }

    public void setMaxElementsWrap(int i2) {
        this.J3.S1(i2);
        requestLayout();
    }

    public void setOrientation(int i2) {
        this.J3.T1(i2);
        requestLayout();
    }

    public void setPadding(int i2) {
        this.J3.Z0(i2);
        requestLayout();
    }

    public void setPaddingBottom(int i2) {
        this.J3.a1(i2);
        requestLayout();
    }

    public void setPaddingLeft(int i2) {
        this.J3.c1(i2);
        requestLayout();
    }

    public void setPaddingRight(int i2) {
        this.J3.d1(i2);
        requestLayout();
    }

    public void setPaddingTop(int i2) {
        this.J3.f1(i2);
        requestLayout();
    }

    public void setVerticalAlign(int i2) {
        this.J3.U1(i2);
        requestLayout();
    }

    public void setVerticalBias(float f2) {
        this.J3.V1(f2);
        requestLayout();
    }

    public void setVerticalGap(int i2) {
        this.J3.W1(i2);
        requestLayout();
    }

    public void setVerticalStyle(int i2) {
        this.J3.X1(i2);
        requestLayout();
    }

    public void setWrapMode(int i2) {
        this.J3.Y1(i2);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void v(j jVar, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
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

    public Flow(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
