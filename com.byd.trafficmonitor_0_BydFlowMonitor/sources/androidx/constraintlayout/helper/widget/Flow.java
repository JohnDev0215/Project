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
import b.f.b.j.e;
import b.f.b.j.h;
import b.f.b.j.j;
import b.f.c.b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {
    public e l;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.l = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, b.f.c.e.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == b.f.c.e.ConstraintLayout_Layout_android_orientation) {
                    this.l.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_padding) {
                    this.l.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingStart) {
                    this.l.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingEnd) {
                    this.l.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingLeft) {
                    this.l.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingTop) {
                    this.l.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingRight) {
                    this.l.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_android_paddingBottom) {
                    this.l.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_wrapMode) {
                    this.l.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.l.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.l.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.l.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.l.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.l.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.l.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.l.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.l.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.l.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.l.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.l.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_verticalBias) {
                    this.l.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.l.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.l.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.l.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_verticalGap) {
                    this.l.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == b.f.c.e.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.l.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.f703e = this.l;
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
        this.l.L0(z);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i2, int i3) {
        v(this.l, i2, i3);
    }

    public void setFirstHorizontalBias(float f2) {
        this.l.G1(f2);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i2) {
        this.l.H1(i2);
        requestLayout();
    }

    public void setFirstVerticalBias(float f2) {
        this.l.I1(f2);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i2) {
        this.l.J1(i2);
        requestLayout();
    }

    public void setHorizontalAlign(int i2) {
        this.l.K1(i2);
        requestLayout();
    }

    public void setHorizontalBias(float f2) {
        this.l.L1(f2);
        requestLayout();
    }

    public void setHorizontalGap(int i2) {
        this.l.M1(i2);
        requestLayout();
    }

    public void setHorizontalStyle(int i2) {
        this.l.N1(i2);
        requestLayout();
    }

    public void setMaxElementsWrap(int i2) {
        this.l.S1(i2);
        requestLayout();
    }

    public void setOrientation(int i2) {
        this.l.T1(i2);
        requestLayout();
    }

    public void setPadding(int i2) {
        this.l.Z0(i2);
        requestLayout();
    }

    public void setPaddingBottom(int i2) {
        this.l.a1(i2);
        requestLayout();
    }

    public void setPaddingLeft(int i2) {
        this.l.c1(i2);
        requestLayout();
    }

    public void setPaddingRight(int i2) {
        this.l.d1(i2);
        requestLayout();
    }

    public void setPaddingTop(int i2) {
        this.l.f1(i2);
        requestLayout();
    }

    public void setVerticalAlign(int i2) {
        this.l.U1(i2);
        requestLayout();
    }

    public void setVerticalBias(float f2) {
        this.l.V1(f2);
        requestLayout();
    }

    public void setVerticalGap(int i2) {
        this.l.W1(i2);
        requestLayout();
    }

    public void setVerticalStyle(int i2) {
        this.l.X1(i2);
        requestLayout();
    }

    public void setWrapMode(int i2) {
        this.l.Y1(i2);
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
