package defpackage;

import android.os.SystemClock;
import android.view.ViewGroup;
import defpackage.h61;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: LayoutManagerChromePhone.java */
/* JADX INFO: loaded from: classes.dex */
public final class d61 extends c61 {
    public um2 b0;

    /* JADX INFO: compiled from: LayoutManagerChromePhone.java */
    public class a extends h61.c {
        public a() {
            super();
        }

        @Override // defpackage.t13
        public final void t(Tab tab, boolean z, boolean z2) {
            if (z) {
                int id = tab.getId();
                d61 d61Var = d61.this;
                y13 y13Var = d61Var.k;
                if ((y13Var == null ? null : y13Var.s(id)) == null) {
                    return;
                }
                if (d61Var.q.p()) {
                    y51 y51Var = d61Var.q;
                    SystemClock.uptimeMillis();
                    y51Var.w(id);
                } else if (d61Var.W) {
                    if (d61Var.r != null) {
                        return;
                    }
                    d61Var.g0(d61Var.b0, false);
                    y51 y51Var2 = d61Var.q;
                    SystemClock.uptimeMillis();
                    y51Var2.w(id);
                }
            }
        }
    }

    public d61(CompositorViewHolder compositorViewHolder, ViewGroup viewGroup, rs1 rs1Var, rs1 rs1Var2, aq1 aq1Var, bs bsVar, j31 j31Var) {
        super(compositorViewHolder, viewGroup, rs1Var, rs1Var2, aq1Var, bsVar, j31Var, null, null);
    }

    @Override // defpackage.c61, defpackage.h61
    public final y51 D(int i) {
        return i == 8 ? this.b0 : super.D(i);
    }

    @Override // defpackage.c61, defpackage.h61
    public final void M(y13 y13Var, gz2 gz2Var, e00 e00Var, df0 df0Var, xd3 xd3Var) {
        g61 g61Var = this.f;
        this.b0 = new um2(g61Var.getContext(), this, g61Var.getLayoutRenderHost());
        super.M(y13Var, gz2Var, e00Var, df0Var, xd3Var);
        this.b0.G(y13Var, this.Z.get());
    }

    @Override // defpackage.c61, defpackage.h61
    public final void P(boolean z) {
        y51 y51Var;
        if (this.q == this.i && !z) {
            if (g90.a(this.f.getContext())) {
                y51Var = this.Q;
            } else {
                y51Var = this.U;
                if (y51Var == null) {
                    y51Var = this.S;
                }
            }
            g0(y51Var, false);
        }
        super.P(z);
    }

    @Override // defpackage.h61
    public final void j0(int i, int i2, boolean z, boolean z2) {
        boolean z3 = i2 == -1;
        if (d() != 2 && d() != 16 && z3) {
            if (d() == 8) {
                V(D(2), true);
            } else {
                t(2, true);
            }
        }
        y51 y51Var = this.q;
        SystemClock.uptimeMillis();
        y51Var.v(i2);
        boolean z4 = !z2 && this.W;
        if (d() == 2 || d() == 16 || !z3 || z4) {
            return;
        }
        t(2, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x008a  */
    @Override // defpackage.h61
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l0(int r8) {
        /*
            r7 = this;
            y51 r0 = r7.q
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L60
            int r4 = r0.p
            if (r4 != r1) goto Ld
            r4 = r3
            goto Le
        Ld:
            r4 = r2
        Le:
            if (r4 != 0) goto L60
            if (r0 == 0) goto L4f
            m61[] r4 = r0.o
            if (r4 == 0) goto L4f
            int r4 = r4.length
            if (r4 == r3) goto L1a
            goto L4f
        L1a:
            r4 = r2
        L1b:
            java.util.ArrayList r5 = r7.L
            int r6 = r5.size()
            if (r4 >= r6) goto L4f
            java.lang.Object r6 = r5.get(r4)
            je2 r6 = (defpackage.je2) r6
            boolean r6 = r6.q1()
            if (r6 != 0) goto L30
            goto L4c
        L30:
            java.lang.Object r5 = r5.get(r4)
            je2 r5 = (defpackage.je2) r5
            boolean r5 = r5.o0()
            if (r5 == 0) goto L4c
            m61[] r0 = r0.o
            r0 = r0[r2]
            int r0 = r0.q()
            r7.e0(r0, r2)
            r7.k()
            r0 = r3
            goto L50
        L4c:
            int r4 = r4 + 1
            goto L1b
        L4f:
            r0 = r2
        L50:
            if (r0 == 0) goto L60
            y51 r0 = r7.q
            boolean r0 = r0.q()
            if (r0 == 0) goto L60
            y51 r7 = r7.q
            r7.z(r8)
            goto L96
        L60:
            boolean r0 = r7.W
            if (r0 == 0) goto L96
            boolean r0 = r7.q(r1)
            if (r0 != 0) goto L8f
            r0 = 16
            boolean r0 = r7.q(r0)
            if (r0 != 0) goto L8f
            y51 r0 = r7.q
            if (r0 == 0) goto L8a
            int r0 = r0.p
            if (r0 != r1) goto L7c
            r0 = r3
            goto L7d
        L7c:
            r0 = r2
        L7d:
            if (r0 == 0) goto L8a
            um2 r0 = r7.b0
            r7.V(r0, r3)
            y51 r0 = r7.q
            r0.f()
            goto L8f
        L8a:
            um2 r0 = r7.b0
            r7.g0(r0, r2)
        L8f:
            y51 r7 = r7.q
            if (r7 == 0) goto L96
            r7.z(r8)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.d61.l0(int):void");
    }

    @Override // defpackage.h61
    public final h61.c v() {
        return new a();
    }
}
