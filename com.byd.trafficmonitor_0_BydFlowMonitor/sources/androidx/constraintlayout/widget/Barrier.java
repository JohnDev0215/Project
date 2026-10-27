package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.f.b.j.a;
import b.f.b.j.d;
import b.f.b.j.h;
import b.f.c.b;
import b.f.c.e;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int j;
    public int k;
    public a l;

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
        this.l = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == e.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == e.ConstraintLayout_Layout_barrierMargin) {
                    this.l.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.f703e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof a) {
            a aVar2 = (a) hVar;
            w(aVar2, aVar.f2291d.c0, ((d) hVar.H()).c1());
            aVar2.P0(aVar.f2291d.k0);
            aVar2.R0(aVar.f2291d.d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z) {
        w(constraintWidget, this.j, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.P0(z);
    }

    public void setDpMargin(int i2) {
        this.l.R0((int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i2) {
        this.l.R0(i2);
    }

    public void setType(int i2) {
        this.j = i2;
    }

    public boolean v() {
        return this.l.L0();
    }

    public final void w(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.k = i2;
        if (Build.VERSION.SDK_INT < 17) {
            int i3 = this.j;
            if (i3 == 5) {
                this.k = 0;
            } else if (i3 == 6) {
                this.k = 1;
            }
        } else if (z) {
            int i4 = this.j;
            if (i4 == 5) {
                this.k = 1;
            } else if (i4 == 6) {
                this.k = 0;
            }
        } else {
            int i5 = this.j;
            if (i5 == 5) {
                this.k = 0;
            } else if (i5 == 6) {
                this.k = 1;
            }
        }
        if (constraintWidget instanceof a) {
            ((a) constraintWidget).Q0(this.k);
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
