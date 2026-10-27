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
import w.e;
import w.h;
import w.j;
import y.b;

/* JADX INFO: loaded from: classes.dex */
public class Flow extends VirtualLayout {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e f714l;

    public Flow(Context context) {
        super(context);
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f714l = new e();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_Layout_android_orientation) {
                    this.f714l.T1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_padding) {
                    this.f714l.Z0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.f714l.e1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.f714l.b1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.f714l.c1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.f714l.f1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.f714l.d1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.f714l.a1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.f714l.Y1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.f714l.N1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.f714l.X1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.f714l.H1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.f714l.P1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.f714l.J1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.f714l.R1(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.f714l.L1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.f714l.G1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.f714l.O1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.f714l.I1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.f714l.Q1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.f714l.V1(typedArrayObtainStyledAttributes.getFloat(index, 0.5f));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.f714l.K1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.f714l.U1(typedArrayObtainStyledAttributes.getInt(index, 2));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.f714l.M1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.f714l.W1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.f714l.S1(typedArrayObtainStyledAttributes.getInt(index, -1));
                }
            }
        }
        this.f973e = this.f714l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof e) {
            e eVar = (e) hVar;
            int i3 = bVar.S;
            if (i3 != -1) {
                eVar.T1(i3);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z3) {
        this.f714l.L0(z3);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    @SuppressLint({"WrongCall"})
    public void onMeasure(int i3, int i4) {
        v(this.f714l, i3, i4);
    }

    public void setFirstHorizontalBias(float f3) {
        this.f714l.G1(f3);
        requestLayout();
    }

    public void setFirstHorizontalStyle(int i3) {
        this.f714l.H1(i3);
        requestLayout();
    }

    public void setFirstVerticalBias(float f3) {
        this.f714l.I1(f3);
        requestLayout();
    }

    public void setFirstVerticalStyle(int i3) {
        this.f714l.J1(i3);
        requestLayout();
    }

    public void setHorizontalAlign(int i3) {
        this.f714l.K1(i3);
        requestLayout();
    }

    public void setHorizontalBias(float f3) {
        this.f714l.L1(f3);
        requestLayout();
    }

    public void setHorizontalGap(int i3) {
        this.f714l.M1(i3);
        requestLayout();
    }

    public void setHorizontalStyle(int i3) {
        this.f714l.N1(i3);
        requestLayout();
    }

    public void setMaxElementsWrap(int i3) {
        this.f714l.S1(i3);
        requestLayout();
    }

    public void setOrientation(int i3) {
        this.f714l.T1(i3);
        requestLayout();
    }

    public void setPadding(int i3) {
        this.f714l.Z0(i3);
        requestLayout();
    }

    public void setPaddingBottom(int i3) {
        this.f714l.a1(i3);
        requestLayout();
    }

    public void setPaddingLeft(int i3) {
        this.f714l.c1(i3);
        requestLayout();
    }

    public void setPaddingRight(int i3) {
        this.f714l.d1(i3);
        requestLayout();
    }

    public void setPaddingTop(int i3) {
        this.f714l.f1(i3);
        requestLayout();
    }

    public void setVerticalAlign(int i3) {
        this.f714l.U1(i3);
        requestLayout();
    }

    public void setVerticalBias(float f3) {
        this.f714l.V1(f3);
        requestLayout();
    }

    public void setVerticalGap(int i3) {
        this.f714l.W1(i3);
        requestLayout();
    }

    public void setVerticalStyle(int i3) {
        this.f714l.X1(i3);
        requestLayout();
    }

    public void setWrapMode(int i3) {
        this.f714l.Y1(i3);
        requestLayout();
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout
    public void v(j jVar, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
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

    public Flow(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
    }
}
