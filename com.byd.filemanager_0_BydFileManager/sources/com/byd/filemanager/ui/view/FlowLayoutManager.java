package com.byd.filemanager.ui.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.f;

/* JADX INFO: loaded from: classes.dex */
public class FlowLayoutManager extends RecyclerView.o {
    private a u;
    private int w;
    private int t = 2;
    private boolean v = f.h().y();

    public interface a {
        void a(int i);
    }

    public FlowLayoutManager(int i, a aVar) {
        this.w = i;
        this.u = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.p N() {
        return new RecyclerView.p(-2, -2);
    }

    public void W1(int i) {
        this.t = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void f1(RecyclerView.v vVar, RecyclerView.z zVar) {
        int i;
        super.f1(vVar, zVar);
        G(vVar);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int iMax = 0;
        for (int i6 = 0; i6 < i0(); i6++) {
            try {
                if (i3 < this.t && zVar.b() > i6) {
                    View viewO = vVar.o(i6);
                    RecyclerView.p pVar = (RecyclerView.p) viewO.getLayoutParams();
                    n(viewO);
                    I0(viewO, 0, 0);
                    int iC0 = c0(viewO) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
                    int iB0 = b0(viewO) + ((ViewGroup.MarginLayoutParams) pVar).topMargin + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
                    int iU0 = u0();
                    int i7 = i2 + iC0;
                    if (i7 <= u0()) {
                        if (this.v) {
                            int i8 = iU0 - i7;
                            int i9 = ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i8;
                            int i10 = this.w;
                            i = i7;
                            G0(viewO, i9 + i10, i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, i10 + (i8 - ((ViewGroup.MarginLayoutParams) pVar).leftMargin) + iC0, (i5 + iB0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                        } else {
                            i = i7;
                            G0(viewO, (i - iC0) + ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, i - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 + iB0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                        }
                        iMax = Math.max(iMax, iB0);
                    } else {
                        i = i7;
                        i3++;
                        if (i3 < this.t) {
                            if (this.v) {
                                int i11 = iU0 - iC0;
                                int i12 = ((ViewGroup.MarginLayoutParams) pVar).rightMargin + i11;
                                int i13 = this.w;
                                int i14 = i12 + i13;
                                int i15 = i13 + (i11 - ((ViewGroup.MarginLayoutParams) pVar).leftMargin) + iC0;
                                if (iMax == 0) {
                                    iMax = iB0;
                                }
                                i5 += iMax;
                                G0(viewO, i14, i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, i15, (i5 + iB0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                            } else {
                                if (iMax == 0) {
                                    iMax = iB0;
                                }
                                i5 += iMax;
                                G0(viewO, ((ViewGroup.MarginLayoutParams) pVar).leftMargin, i5 + ((ViewGroup.MarginLayoutParams) pVar).topMargin, iC0 - ((ViewGroup.MarginLayoutParams) pVar).rightMargin, (i5 + iB0) - ((ViewGroup.MarginLayoutParams) pVar).bottomMargin);
                            }
                            i2 = iC0;
                            iMax = iB0;
                        } else {
                            i4 = i6;
                        }
                    }
                    i2 = i;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        a aVar = this.u;
        if (aVar != null) {
            aVar.a(i4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean y0() {
        return true;
    }
}
