package s3;

import F1.C0254m;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import k3.C1648i0;

/* JADX INFO: renamed from: s3.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2404x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f22362a;

    public /* synthetic */ C2404x(RecyclerView recyclerView) {
        this.f22362a = recyclerView;
    }

    public void a(C2381a c2381a) {
        int i7 = c2381a.f22244a;
        RecyclerView recyclerView = this.f22362a;
        if (i7 == 1) {
            recyclerView.f12157x.T(c2381a.f22245b, c2381a.c);
            return;
        }
        if (i7 == 2) {
            recyclerView.f12157x.W(c2381a.f22245b, c2381a.c);
        } else if (i7 == 4) {
            recyclerView.f12157x.X(c2381a.f22245b, c2381a.c);
        } else {
            if (i7 != 8) {
                return;
            }
            recyclerView.f12157x.V(c2381a.f22245b, c2381a.c);
        }
    }

    public U b(int i7) {
        RecyclerView recyclerView = this.f22362a;
        int iA = recyclerView.f12143q.A();
        int i10 = 0;
        U u4 = null;
        while (true) {
            if (i10 >= iA) {
                break;
            }
            U uG = RecyclerView.G(recyclerView.f12143q.z(i10));
            if (uG != null && !uG.i() && uG.c == i7) {
                if (!((ArrayList) recyclerView.f12143q.o).contains(uG.f22219a)) {
                    u4 = uG;
                    break;
                }
                u4 = uG;
            }
            i10++;
        }
        if (u4 == null || ((ArrayList) recyclerView.f12143q.o).contains(u4.f22219a)) {
            return null;
        }
        return u4;
    }

    public void c(int i7, int i10) {
        int i11;
        int i12;
        RecyclerView recyclerView = this.f22362a;
        int iA = recyclerView.f12143q.A();
        int i13 = i10 + i7;
        for (int i14 = 0; i14 < iA; i14++) {
            View viewZ = recyclerView.f12143q.z(i14);
            U uG = RecyclerView.G(viewZ);
            if (uG != null && !uG.p() && (i12 = uG.c) >= i7 && i12 < i13) {
                uG.a(2);
                uG.a(1024);
                ((C2368G) viewZ.getLayoutParams()).c = true;
            }
        }
        C1648i0 c1648i0 = recyclerView.f12138n;
        ArrayList arrayList = (ArrayList) c1648i0.f17995f;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            U u4 = (U) arrayList.get(size);
            if (u4 != null && (i11 = u4.c) >= i7 && i11 < i13) {
                u4.a(2);
                c1648i0.g(size);
            }
        }
        recyclerView.f12148s0 = true;
    }

    public void d(int i7, int i10) {
        RecyclerView recyclerView = this.f22362a;
        int iA = recyclerView.f12143q.A();
        for (int i11 = 0; i11 < iA; i11++) {
            U uG = RecyclerView.G(recyclerView.f12143q.z(i11));
            if (uG != null && !uG.p() && uG.c >= i7) {
                uG.m(i10, false);
                recyclerView.f12140o0.f22205f = true;
            }
        }
        ArrayList arrayList = (ArrayList) recyclerView.f12138n.f17995f;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            U u4 = (U) arrayList.get(i12);
            if (u4 != null && u4.c >= i7) {
                u4.m(i10, false);
            }
        }
        recyclerView.requestLayout();
        recyclerView.f12146r0 = true;
    }

    public void e(int i7, int i10) {
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        RecyclerView recyclerView = this.f22362a;
        int iA = recyclerView.f12143q.A();
        int i18 = -1;
        if (i7 < i10) {
            i12 = i7;
            i11 = i10;
            i13 = -1;
        } else {
            i11 = i7;
            i12 = i10;
            i13 = 1;
        }
        for (int i19 = 0; i19 < iA; i19++) {
            U uG = RecyclerView.G(recyclerView.f12143q.z(i19));
            if (uG != null && (i17 = uG.c) >= i12 && i17 <= i11) {
                if (i17 == i7) {
                    uG.m(i10 - i7, false);
                } else {
                    uG.m(i13, false);
                }
                recyclerView.f12140o0.f22205f = true;
            }
        }
        C1648i0 c1648i0 = recyclerView.f12138n;
        c1648i0.getClass();
        if (i7 < i10) {
            i15 = i7;
            i14 = i10;
        } else {
            i14 = i7;
            i15 = i10;
            i18 = 1;
        }
        ArrayList arrayList = (ArrayList) c1648i0.f17995f;
        int size = arrayList.size();
        for (int i20 = 0; i20 < size; i20++) {
            U u4 = (U) arrayList.get(i20);
            if (u4 != null && (i16 = u4.c) >= i15 && i16 <= i14) {
                if (i16 == i7) {
                    u4.m(i10 - i7, false);
                } else {
                    u4.m(i18, false);
                }
            }
        }
        recyclerView.requestLayout();
        recyclerView.f12146r0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(s3.U r8, F1.C0254m r9, F1.C0254m r10) {
        /*
            r7 = this;
            androidx.recyclerview.widget.RecyclerView r7 = r7.f22362a
            r7.getClass()
            r0 = 0
            r8.o(r0)
            s3.C r0 = r7.f12121U
            r1 = r0
            s3.h r1 = (s3.C2388h) r1
            if (r9 == 0) goto L29
            r1.getClass()
            int r3 = r9.f2343b
            int r5 = r10.f2343b
            if (r3 != r5) goto L1f
            int r0 = r9.c
            int r2 = r10.c
            if (r0 == r2) goto L29
        L1f:
            int r4 = r9.c
            int r6 = r10.c
            r2 = r8
            boolean r8 = r1.g(r2, r3, r4, r5, r6)
            goto L38
        L29:
            r1.l(r8)
            android.view.View r9 = r8.f22219a
            r10 = 0
            r9.setAlpha(r10)
            java.util.ArrayList r9 = r1.f22283i
            r9.add(r8)
            r8 = 1
        L38:
            if (r8 == 0) goto L3d
            r7.Q()
        L3d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: s3.C2404x.f(s3.U, F1.m, F1.m):void");
    }

    public void g(U u4, C0254m c0254m, C0254m c0254m2) {
        boolean zG;
        RecyclerView recyclerView = this.f22362a;
        recyclerView.f12138n.l(u4);
        recyclerView.e(u4);
        u4.o(false);
        C2388h c2388h = (C2388h) recyclerView.f12121U;
        c2388h.getClass();
        int i7 = c0254m.f2343b;
        int i10 = c0254m.c;
        View view = u4.f22219a;
        int left = c0254m2 == null ? view.getLeft() : c0254m2.f2343b;
        int top = c0254m2 == null ? view.getTop() : c0254m2.c;
        if (u4.i() || (i7 == left && i10 == top)) {
            c2388h.l(u4);
            c2388h.f22282h.add(u4);
            zG = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            zG = c2388h.g(u4, i7, i10, left, top);
        }
        if (zG) {
            recyclerView.Q();
        }
    }

    public void h(int i7) {
        RecyclerView recyclerView = this.f22362a;
        View childAt = recyclerView.getChildAt(i7);
        if (childAt != null) {
            RecyclerView.G(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i7);
    }
}
