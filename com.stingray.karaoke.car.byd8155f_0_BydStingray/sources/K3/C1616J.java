package k3;

import android.os.Bundle;
import android.os.Looper;
import h2.AbstractC1346c;
import o2.C2024a;

/* JADX INFO: renamed from: k3.J, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1616J implements h2.l, InterfaceC1656m0, h2.g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f17818m;

    public /* synthetic */ C1616J(int i7, int i10) {
        this.l = i10;
        this.f17818m = i7;
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 2:
                u1Var.I(this.f17818m);
                break;
            case 3:
                u1Var.j0(this.f17818m);
                break;
            case 4:
                u1Var.h(this.f17818m);
                break;
            default:
                u1Var.B0(this.f17818m);
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        switch (this.l) {
            case 0:
                ((e2.U) obj).d(this.f17818m);
                break;
            case 6:
                ((e2.U) obj).d(this.f17818m);
                break;
            default:
                o2.j jVar = (o2.j) obj;
                jVar.getClass();
                int i7 = this.f17818m;
                if (i7 == 1) {
                    jVar.f20441u = true;
                }
                jVar.f20433k = i7;
                break;
        }
    }

    @Override // k3.InterfaceC1656m0
    public void e(C1628b0 c1628b0) {
        if (c1628b0.v()) {
            C1672v c1672v = c1628b0.f17904a;
            c1672v.getClass();
            AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
            c1672v.d.getClass();
            h5.v vVarC = InterfaceC1668t.c();
            vVarC.b(new h.k(c1628b0, vVarC, this.f17818m, 3), h5.r.l);
        }
    }

    public /* synthetic */ C1616J(int i7, y1 y1Var, Bundle bundle) {
        this.l = 1;
        this.f17818m = i7;
    }

    public /* synthetic */ C1616J(C2024a c2024a, int i7, e2.V v10, e2.V v11) {
        this.l = 7;
        this.f17818m = i7;
    }
}
