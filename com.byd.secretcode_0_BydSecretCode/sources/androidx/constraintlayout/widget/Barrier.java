package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.a6;
import defpackage.q6;
import defpackage.t5;
import defpackage.w5;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int j;
    public int k;
    public t5 l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public int getMargin() {
        return this.l.N0();
    }

    public int getType() {
        return this.j;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.l = new t5();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.l.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(q6.a aVar, a6 a6Var, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, a6Var, bVar, sparseArray);
        if (a6Var instanceof t5) {
            t5 t5Var = (t5) a6Var;
            w(t5Var, aVar.d.c0, ((w5) a6Var.H()).c1());
            t5Var.P0(aVar.d.k0);
            t5Var.R0(aVar.d.d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        w(constraintWidget, this.j, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.P0(z);
    }

    public void setDpMargin(int i) {
        this.l.R0((int) ((i * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i) {
        this.l.R0(i);
    }

    public void setType(int i) {
        this.j = i;
    }

    public boolean v() {
        return this.l.L0();
    }

    public final void w(ConstraintWidget constraintWidget, int i, boolean z) {
        this.k = i;
        if (Build.VERSION.SDK_INT < 17) {
            int i2 = this.j;
            if (i2 == 5) {
                this.k = 0;
            } else if (i2 == 6) {
                this.k = 1;
            }
        } else if (z) {
            int i3 = this.j;
            if (i3 == 5) {
                this.k = 1;
            } else if (i3 == 6) {
                this.k = 0;
            }
        } else {
            int i4 = this.j;
            if (i4 == 5) {
                this.k = 0;
            } else if (i4 == 6) {
                this.k = 1;
            }
        }
        if (constraintWidget instanceof t5) {
            ((t5) constraintWidget).Q0(this.k);
        }
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
