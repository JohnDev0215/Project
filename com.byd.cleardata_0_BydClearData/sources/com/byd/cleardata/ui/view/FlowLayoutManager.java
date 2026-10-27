package com.byd.cleardata.ui.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class FlowLayoutManager extends RecyclerView.o {
    private int s = 2;
    private a t;

    public interface a {
        void a(int i);
    }

    public FlowLayoutManager(a aVar) {
        this.t = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean K0() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p Z() {
        return new RecyclerView.p(-2, -2);
    }

    public void i2(int i) {
        this.s = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void r1(RecyclerView.v vVar, RecyclerView.a0 a0Var) {
        int i;
        super.r1(vVar, a0Var);
        S(vVar);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int iMax = 0;
        for (int i6 = 0; i6 < u0(); i6++) {
            if (i3 < this.s) {
                View viewO = vVar.o(i6);
                RecyclerView.p pVar = (RecyclerView.p) viewO.getLayoutParams();
                z(viewO);
                U0(viewO, 0, 0);
                int iO0 = o0(viewO) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
                int iN0 = n0(viewO) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
                int i7 = i2 + iO0;
                if (i7 <= G0()) {
                    i = i7;
                    S0(viewO, ((ViewGroup.MarginLayoutParams) pVar).leftMargin + (i7 - iO0), i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, i7 - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 + iN0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                    iMax = Math.max(iMax, iN0);
                } else {
                    i = i7;
                    i3++;
                    if (i3 < this.s) {
                        if (iMax == 0) {
                            iMax = iN0;
                        }
                        i5 += iMax;
                        S0(viewO, ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, iO0 - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 + iN0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                        i2 = iO0;
                        iMax = iN0;
                    } else {
                        i4 = i6;
                    }
                }
                i2 = i;
            }
        }
        a aVar = this.t;
        if (aVar != null) {
            aVar.a(i4);
        }
    }
}
