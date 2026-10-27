package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.a;
import defpackage.ku0;
import defpackage.qx;
import defpackage.rx;
import defpackage.yc;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public int n;
    public int o;
    public yc p;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public boolean getAllowsGoneWidget() {
        return this.p.z0;
    }

    public int getMargin() {
        return this.p.A0;
    }

    public int getType() {
        return this.n;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        this.p = new yc();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.p.z0 = typedArrayObtainStyledAttributes.getBoolean(index, true);
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.p.A0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.h = this.p;
        t();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void o(a.C0004a c0004a, ku0 ku0Var, Constraints.LayoutParams layoutParams, SparseArray sparseArray) {
        super.o(c0004a, ku0Var, layoutParams, sparseArray);
        if (ku0Var instanceof yc) {
            yc ycVar = (yc) ku0Var;
            boolean z = ((rx) ku0Var.W).B0;
            a.b bVar = c0004a.e;
            u(ycVar, bVar.g0, z);
            ycVar.z0 = bVar.o0;
            ycVar.A0 = bVar.h0;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void p(qx qxVar, boolean z) {
        u(qxVar, this.n, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.p.z0 = z;
    }

    public void setDpMargin(int i) {
        this.p.A0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.p.A0 = i;
    }

    public void setType(int i) {
        this.n = i;
    }

    public final void u(qx qxVar, int i, boolean z) {
        this.o = i;
        if (z) {
            int i2 = this.n;
            if (i2 == 5) {
                this.o = 1;
            } else if (i2 == 6) {
                this.o = 0;
            }
        } else {
            int i3 = this.n;
            if (i3 == 5) {
                this.o = 0;
            } else if (i3 == 6) {
                this.o = 1;
            }
        }
        if (qxVar instanceof yc) {
            ((yc) qxVar).y0 = this.o;
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
