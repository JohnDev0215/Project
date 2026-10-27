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
import b0.b;
import z.e;
import z.h;
import z.j;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public e f809n;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f809n = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.f809n.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    this.f809n.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.f809n.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.f809n.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.f809n.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.f809n.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.f809n.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.f809n.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.f809n.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.f809n.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.f809n.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.f809n.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.f809n.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.f809n.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.f809n.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.f809n.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.f809n.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.f809n.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.f809n.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.f809n.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.f809n.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.f809n.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.f809n.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.f809n.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.f809n.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.f809n.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.f1064g = this.f809n;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof e) {
            e eVar = (e) hVar;
            int i8 = bVar.S;
            if (i8 != -1) {
                eVar.T1(i8);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z7) {
        this.f809n.L0(z7);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i8, int i9) {
        v(this.f809n, i8, i9);
    }

    public void setFirstHorizontalBias(float f8) {
        this.f809n.G1(f8);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i8) {
        this.f809n.H1(i8);
        requestLayout();
    }

    public void setFirstVerticalBias(float f8) {
        this.f809n.I1(f8);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i8) {
        this.f809n.J1(i8);
        requestLayout();
    }

    public void setHorizontalAlign(int i8) {
        this.f809n.K1(i8);
        requestLayout();
    }

    public void setHorizontalBias(float f8) {
        this.f809n.L1(f8);
        requestLayout();
    }

    public void setHorizontalGap(int i8) {
        this.f809n.M1(i8);
        requestLayout();
    }

    public void setHorizontalStyle(int i8) {
        this.f809n.N1(i8);
        requestLayout();
    }

    public void setMaxElementsWrap(int i8) {
        this.f809n.S1(i8);
        requestLayout();
    }

    public void setOrientation(int i8) {
        this.f809n.T1(i8);
        requestLayout();
    }

    public void setPadding(int i8) {
        this.f809n.Z0(i8);
        requestLayout();
    }

    public void setPaddingBottom(int i8) {
        this.f809n.a1(i8);
        requestLayout();
    }

    public void setPaddingLeft(int i8) {
        this.f809n.c1(i8);
        requestLayout();
    }

    public void setPaddingRight(int i8) {
        this.f809n.d1(i8);
        requestLayout();
    }

    public void setPaddingTop(int i8) {
        this.f809n.f1(i8);
        requestLayout();
    }

    public void setVerticalAlign(int i8) {
        this.f809n.U1(i8);
        requestLayout();
    }

    public void setVerticalBias(float f8) {
        this.f809n.V1(f8);
        requestLayout();
    }

    public void setVerticalGap(int i8) {
        this.f809n.W1(i8);
        requestLayout();
    }

    public void setVerticalStyle(int i8) {
        this.f809n.X1(i8);
        requestLayout();
    }

    public void setWrapMode(int i8) {
        this.f809n.Y1(i8);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void v(j jVar, int i8, int i9) {
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i9);
        int size2 = View.MeasureSpec.getSize(i9);
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

    public Flow(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
    }
}
