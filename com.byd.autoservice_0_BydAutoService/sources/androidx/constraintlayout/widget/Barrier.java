package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import w.a;
import w.d;
import w.h;
import y.b;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f959j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f960k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a f961l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public int getMargin() {
        return this.f961l.N0();
    }

    public int getType() {
        return this.f959j;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        this.f961l = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.f961l.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == R$styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.f961l.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.f973e = this.f961l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(b.a aVar, h hVar, ConstraintLayout.b bVar, SparseArray<ConstraintWidget> sparseArray) {
        super.n(aVar, hVar, bVar, sparseArray);
        if (hVar instanceof a) {
            a aVar2 = (a) hVar;
            w(aVar2, aVar.f5769d.f5777c0, ((d) hVar.H()).c1());
            aVar2.P0(aVar.f5769d.f5793k0);
            aVar2.R0(aVar.f5769d.f5779d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(ConstraintWidget constraintWidget, boolean z3) {
        w(constraintWidget, this.f959j, z3);
    }

    public void setAllowsGoneWidget(boolean z3) {
        this.f961l.P0(z3);
    }

    public void setDpMargin(int i3) {
        this.f961l.R0((int) ((i3 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i3) {
        this.f961l.R0(i3);
    }

    public void setType(int i3) {
        this.f959j = i3;
    }

    public boolean v() {
        return this.f961l.L0();
    }

    public final void w(ConstraintWidget constraintWidget, int i3, boolean z3) {
        this.f960k = i3;
        if (Build.VERSION.SDK_INT < 17) {
            int i4 = this.f959j;
            if (i4 == 5) {
                this.f960k = 0;
            } else if (i4 == 6) {
                this.f960k = 1;
            }
        } else if (z3) {
            int i5 = this.f959j;
            if (i5 == 5) {
                this.f960k = 1;
            } else if (i5 == 6) {
                this.f960k = 0;
            }
        } else {
            int i6 = this.f959j;
            if (i6 == 5) {
                this.f960k = 0;
            } else if (i6 == 6) {
                this.f960k = 1;
            }
        }
        if (constraintWidget instanceof a) {
            ((a) constraintWidget).Q0(this.f960k);
        }
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        super.setVisibility(8);
    }
}
