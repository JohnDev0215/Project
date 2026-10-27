package k3;

import android.util.SparseBooleanArray;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: k3.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1665r0 implements Runnable {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f18102m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f18103n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f18104p;

    public /* synthetic */ RunnableC1665r0(C1671u0 c1671u0, A0 a02, e7.f fVar, boolean z6) {
        this.f18103n = c1671u0;
        this.o = a02;
        this.f18104p = fVar;
        this.f18102m = z6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                ((C1671u0) this.f18103n).d((A0) this.o, (e7.f) this.f18104p, this.f18102m);
                break;
            default:
                Q0 q02 = (Q0) ((t4.e) this.f18103n).o;
                u1 u1Var = q02.f17843f.f17811t;
                n1.i(u1Var, (C1681z0) this.o);
                int iE = u1Var.e();
                if (iE == 1) {
                    if (u1Var.R0(2)) {
                        u1Var.c();
                    }
                } else if (iE == 4 && u1Var.R0(4)) {
                    u1Var.Y();
                }
                boolean z6 = this.f18102m;
                if (z6 && u1Var.R0(1)) {
                    u1Var.g();
                }
                SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
                for (int i7 : new int[]{31, 2}) {
                    AbstractC1346c.j(!false);
                    sparseBooleanArray.append(i7, true);
                }
                if (z6) {
                    AbstractC1346c.j(!false);
                    sparseBooleanArray.append(1, true);
                }
                AbstractC1346c.j(!false);
                q02.f17843f.o((C1679y0) this.f18104p);
                break;
        }
    }

    public /* synthetic */ RunnableC1665r0(t4.e eVar, C1681z0 c1681z0, boolean z6, C1679y0 c1679y0) {
        this.f18103n = eVar;
        this.o = c1681z0;
        this.f18102m = z6;
        this.f18104p = c1679y0;
    }
}
