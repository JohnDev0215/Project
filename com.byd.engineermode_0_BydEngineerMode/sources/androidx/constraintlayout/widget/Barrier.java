package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.h;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    private int j;
    private int k;
    private androidx.constraintlayout.solver.widgets.a l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void w(ConstraintWidget constraintWidget, int i, boolean z) {
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
        if (constraintWidget instanceof androidx.constraintlayout.solver.widgets.a) {
            ((androidx.constraintlayout.solver.widgets.a) constraintWidget).Q0(this.k);
        }
    }

    public int getMargin() {
        return this.l.N0();
    }

    public int getType() {
        return this.j;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.l = new androidx.constraintlayout.solver.widgets.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.q1) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == e.p1) {
                    this.l.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == e.r1) {
                    this.l.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof androidx.constraintlayout.solver.widgets.a) {
            androidx.constraintlayout.solver.widgets.a aVar2 = (androidx.constraintlayout.solver.widgets.a) hVar;
            w(aVar2, aVar.f749d.c0, ((androidx.constraintlayout.solver.widgets.d) hVar.H()).c1());
            aVar2.P0(aVar.f749d.k0);
            aVar2.R0(aVar.f749d.d0);
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

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setVisibility(8);
    }
}
