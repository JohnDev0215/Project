package androidx.constraintlayout.helper.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.e;
import androidx.constraintlayout.core.widgets.h;
import androidx.constraintlayout.core.widgets.k;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.VirtualLayout;
import androidx.constraintlayout.widget.b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    private e m;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.m = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.y1) {
                    this.m.G2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.z1) {
                    this.m.L1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.J1) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        this.m.Q1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                    }
                } else if (index == androidx.constraintlayout.widget.e.K1) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        this.m.N1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                    }
                } else if (index == androidx.constraintlayout.widget.e.A1) {
                    this.m.O1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.B1) {
                    this.m.R1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.C1) {
                    this.m.P1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.D1) {
                    this.m.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.o2) {
                    this.m.L2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.e2) {
                    this.m.A2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.n2) {
                    this.m.K2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.Y1) {
                    this.m.u2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.g2) {
                    this.m.C2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.a2) {
                    this.m.w2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.i2) {
                    this.m.E2(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.c2) {
                    this.m.y2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.X1) {
                    this.m.t2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.f2) {
                    this.m.B2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.Z1) {
                    this.m.v2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.h2) {
                    this.m.D2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.l2) {
                    this.m.I2(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == androidx.constraintlayout.widget.e.b2) {
                    this.m.x2(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == androidx.constraintlayout.widget.e.k2) {
                    this.m.H2(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == androidx.constraintlayout.widget.e.d2) {
                    this.m.z2(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.m2) {
                    this.m.J2(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == androidx.constraintlayout.widget.e.j2) {
                    this.m.F2(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1031e = this.m;
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    protected void onMeasure(int i, int i2) {
        x(this.m, i, i2);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.p(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof e) {
            e eVar = (e) hVar;
            int i = bVar.Z;
            if (i != -1) {
                eVar.G2(i);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void q(ConstraintWidget constraintWidget, boolean z) {
        this.m.w1(z);
    }

    public void setFirstHorizontalBias(float f) {
        this.m.t2(f);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i) {
        this.m.u2(i);
        requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.m.v2(f);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i) {
        this.m.w2(i);
        requestLayout();
    }

    public void setHorizontalAlign(int i) {
        this.m.x2(i);
        requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.m.y2(f);
        requestLayout();
    }

    public void setHorizontalGap(int i) {
        this.m.z2(i);
        requestLayout();
    }

    public void setHorizontalStyle(int i) {
        this.m.A2(i);
        requestLayout();
    }

    public void setLastHorizontalBias(float f) {
        this.m.B2(f);
        requestLayout();
    }

    public void setLastHorizontalStyle(int i) {
        this.m.C2(i);
        requestLayout();
    }

    public void setLastVerticalBias(float f) {
        this.m.D2(f);
        requestLayout();
    }

    public void setLastVerticalStyle(int i) {
        this.m.E2(i);
        requestLayout();
    }

    public void setMaxElementsWrap(int i) {
        this.m.F2(i);
        requestLayout();
    }

    public void setOrientation(int i) {
        this.m.G2(i);
        requestLayout();
    }

    public void setPadding(int i) {
        this.m.L1(i);
        requestLayout();
    }

    public void setPaddingBottom(int i) {
        this.m.M1(i);
        requestLayout();
    }

    public void setPaddingLeft(int i) {
        this.m.O1(i);
        requestLayout();
    }

    public void setPaddingRight(int i) {
        this.m.P1(i);
        requestLayout();
    }

    public void setPaddingTop(int i) {
        this.m.R1(i);
        requestLayout();
    }

    public void setVerticalAlign(int i) {
        this.m.H2(i);
        requestLayout();
    }

    public void setVerticalBias(float f) {
        this.m.I2(f);
        requestLayout();
    }

    public void setVerticalGap(int i) {
        this.m.J2(i);
        requestLayout();
    }

    public void setVerticalStyle(int i) {
        this.m.K2(i);
        requestLayout();
    }

    public void setWrapMode(int i) {
        this.m.L2(i);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void x(k kVar, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (kVar == null) {
            setMeasuredDimension(0, 0);
        } else {
            kVar.F1(mode, size, mode2, size2);
            setMeasuredDimension(kVar.A1(), kVar.z1());
        }
    }

    public Flow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public Flow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
