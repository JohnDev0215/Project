package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.b.j.a;
import b.e.b.j.d;
import b.e.b.j.h;
import b.e.c.b;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int H3;
    public int I3;
    public a J3;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public int getMargin() {
        return this.J3.N0();
    }

    public int getType() {
        return this.H3;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.J3 = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.q1) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == e.p1) {
                    this.J3.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == e.r1) {
                    this.J3.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.x = this.J3;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof a) {
            a aVar2 = (a) hVar;
            w(aVar2, aVar.f1479d.c0, ((d) hVar.H()).c1());
            aVar2.P0(aVar.f1479d.k0);
            aVar2.R0(aVar.f1479d.d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        w(constraintWidget, this.H3, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.J3.P0(z);
    }

    public void setDpMargin(int i2) {
        this.J3.R0((int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i2) {
        this.J3.R0(i2);
    }

    public void setType(int i2) {
        this.H3 = i2;
    }

    public boolean v() {
        return this.J3.L0();
    }

    public final void w(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.I3 = i2;
        if (Build.VERSION.SDK_INT < 17) {
            int i3 = this.H3;
            if (i3 == 5) {
                this.I3 = 0;
            } else if (i3 == 6) {
                this.I3 = 1;
            }
        } else if (z) {
            int i4 = this.H3;
            if (i4 == 5) {
                this.I3 = 1;
            } else if (i4 == 6) {
                this.I3 = 0;
            }
        } else {
            int i5 = this.H3;
            if (i5 == 5) {
                this.I3 = 0;
            } else if (i5 == 6) {
                this.I3 = 1;
            }
        }
        if (constraintWidget instanceof a) {
            ((a) constraintWidget).Q0(this.I3);
        }
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        super.setVisibility(8);
    }
}
