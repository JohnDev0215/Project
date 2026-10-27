package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import b0.b;
import z.a;
import z.d;
import z.h;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1050l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1051m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a f1052n;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public int getMargin() {
        return this.f1052n.N0();
    }

    public int getType() {
        return this.f1050l;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f1052n = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.f1052n.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.f1052n.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.f1064g = this.f1052n;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof a) {
            a aVar2 = (a) hVar;
            w(aVar2, aVar.f1945d.f1953c0, ((d) hVar.H()).c1());
            aVar2.P0(aVar.f1945d.f1969k0);
            aVar2.R0(aVar.f1945d.f1955d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z7) {
        w(constraintWidget, this.f1050l, z7);
    }

    public void setAllowsGoneWidget(boolean z7) {
        this.f1052n.P0(z7);
    }

    public void setDpMargin(int i8) {
        this.f1052n.R0((int) ((i8 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i8) {
        this.f1052n.R0(i8);
    }

    public void setType(int i8) {
        this.f1050l = i8;
    }

    public boolean v() {
        return this.f1052n.L0();
    }

    public final void w(ConstraintWidget constraintWidget, int i8, boolean z7) {
        this.f1051m = i8;
        if (Build.VERSION.SDK_INT < 17) {
            int i9 = this.f1050l;
            if (i9 == 5) {
                this.f1051m = 0;
            } else if (i9 == 6) {
                this.f1051m = 1;
            }
        } else if (z7) {
            int i10 = this.f1050l;
            if (i10 == 5) {
                this.f1051m = 1;
            } else if (i10 == 6) {
                this.f1051m = 0;
            }
        } else {
            int i11 = this.f1050l;
            if (i11 == 5) {
                this.f1051m = 0;
            } else if (i11 == 6) {
                this.f1051m = 1;
            }
        }
        if (constraintWidget instanceof a) {
            ((a) constraintWidget).Q0(this.f1051m);
        }
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        super.setVisibility(8);
    }
}
