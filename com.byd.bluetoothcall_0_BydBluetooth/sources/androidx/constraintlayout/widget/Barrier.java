package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.h;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.b;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    private int k;
    private int l;
    private androidx.constraintlayout.core.widgets.a m;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void x(ConstraintWidget constraintWidget, int i, boolean z) {
        this.l = i;
        if (Build.VERSION.SDK_INT < 17) {
            int i2 = this.k;
            if (i2 == 5) {
                this.l = 0;
            } else if (i2 == 6) {
                this.l = 1;
            }
        } else if (z) {
            int i3 = this.k;
            if (i3 == 5) {
                this.l = 1;
            } else if (i3 == 6) {
                this.l = 0;
            }
        } else {
            int i4 = this.k;
            if (i4 == 5) {
                this.l = 0;
            } else if (i4 == 6) {
                this.l = 1;
            }
        }
        if (constraintWidget instanceof androidx.constraintlayout.core.widgets.a) {
            ((androidx.constraintlayout.core.widgets.a) constraintWidget).D1(this.l);
        }
    }

    public boolean getAllowsGoneWidget() {
        return this.m.x1();
    }

    public int getMargin() {
        return this.m.z1();
    }

    public int getType() {
        return this.k;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.m = new androidx.constraintlayout.core.widgets.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.x1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.N1) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == e.M1) {
                    this.m.C1(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == e.O1) {
                    this.m.E1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1031e = this.m;
        w();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void p(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.p(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof androidx.constraintlayout.core.widgets.a) {
            androidx.constraintlayout.core.widgets.a aVar2 = (androidx.constraintlayout.core.widgets.a) hVar;
            x(aVar2, aVar.f1079e.i0, ((androidx.constraintlayout.core.widgets.d) hVar.M()).S1());
            aVar2.C1(aVar.f1079e.q0);
            aVar2.E1(aVar.f1079e.j0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void q(ConstraintWidget constraintWidget, boolean z) {
        x(constraintWidget, this.k, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.m.C1(z);
    }

    public void setDpMargin(int i) {
        this.m.E1((int) ((i * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i) {
        this.m.E1(i);
    }

    public void setType(int i) {
        this.k = i;
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
