package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.d;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends b {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f1647o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f1648p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private r.a f1649q;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    private void x(r.e eVar, int i7, boolean z6) {
        this.f1648p = i7;
        if (z6) {
            int i8 = this.f1647o;
            if (i8 == 5) {
                this.f1648p = 1;
            } else if (i8 == 6) {
                this.f1648p = 0;
            }
        } else {
            int i9 = this.f1647o;
            if (i9 == 5) {
                this.f1648p = 0;
            } else if (i9 == 6) {
                this.f1648p = 1;
            }
        }
        if (eVar instanceof r.a) {
            ((r.a) eVar).D1(this.f1648p);
        }
    }

    public boolean getAllowsGoneWidget() {
        return this.f1649q.x1();
    }

    public int getMargin() {
        return this.f1649q.z1();
    }

    public int getType() {
        return this.f1647o;
    }

    @Override // androidx.constraintlayout.widget.b
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        this.f1649q = new r.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == i.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == i.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.f1649q.C1(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == i.ConstraintLayout_Layout_barrierMargin) {
                    this.f1649q.E1(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1743i = this.f1649q;
        w();
    }

    @Override // androidx.constraintlayout.widget.b
    public void p(d.a aVar, r.j jVar, ConstraintLayout.b bVar, SparseArray<r.e> sparseArray) {
        super.p(aVar, jVar, bVar, sparseArray);
        if (jVar instanceof r.a) {
            r.a aVar2 = (r.a) jVar;
            x(aVar2, aVar.f1779e.f1811h0, ((r.f) jVar.M()).S1());
            aVar2.C1(aVar.f1779e.f1827p0);
            aVar2.E1(aVar.f1779e.f1813i0);
        }
    }

    @Override // androidx.constraintlayout.widget.b
    public void q(r.e eVar, boolean z6) {
        x(eVar, this.f1647o, z6);
    }

    public void setAllowsGoneWidget(boolean z6) {
        this.f1649q.C1(z6);
    }

    public void setDpMargin(int i7) {
        this.f1649q.E1((int) ((i7 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i7) {
        this.f1649q.E1(i7);
    }

    public void setType(int i7) {
        this.f1647o = i7;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }
}
