package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.c;
import b.e.b.k.j;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    private int j;
    private int k;
    private b.e.b.k.a l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void w(b.e.b.k.e eVar, int i2, boolean z) {
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
        if (eVar instanceof b.e.b.k.a) {
            ((b.e.b.k.a) eVar).Q0(this.k);
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
        this.l = new b.e.b.k.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, f.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == f.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.P0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == f.ConstraintLayout_Layout_barrierMargin) {
                    this.l.R0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.f995e = this.l;
        u();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void n(c.a aVar, j jVar, ConstraintLayout.b bVar, SparseArray<b.e.b.k.e> sparseArray) {
        super.n(aVar, jVar, bVar, sparseArray);
        if (jVar instanceof b.e.b.k.a) {
            b.e.b.k.a aVar2 = (b.e.b.k.a) jVar;
            w(aVar2, aVar.f1073d.c0, ((b.e.b.k.f) jVar.H()).c1());
            aVar2.P0(aVar.f1073d.k0);
            aVar2.R0(aVar.f1073d.d0);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void o(b.e.b.k.e eVar, boolean z) {
        w(eVar, this.j, z);
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

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        super.setVisibility(8);
    }
}
