package N3;

import Q7.InterfaceC0650c;
import S8.C0712i;
import c9.H;
import c9.InterfaceC1048i;
import h8.AbstractC1386c;
import java.io.IOException;
import m7.q;
import r8.m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e implements InterfaceC1048i, InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f4577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f4578n;

    public /* synthetic */ e(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f4577m = obj;
        this.f4578n = obj2;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                try {
                    ((g9.j) this.f4577m).d();
                    break;
                } catch (Throwable unused) {
                }
                break;
            default:
                InterfaceC0650c interfaceC0650c = (InterfaceC0650c) obj;
                m mVar = (m) this.f4577m;
                InterfaceC0650c interfaceC0650c2 = (InterfaceC0650c) this.f4578n;
                A7.m.f(interfaceC0650c, "second");
                mVar.d(interfaceC0650c2, interfaceC0650c);
                break;
        }
        return q.f19267a;
    }

    @Override // c9.InterfaceC1048i
    public void j(g9.j jVar, H h10) {
        ((C0712i) this.f4578n).resumeWith(h10);
    }

    @Override // c9.InterfaceC1048i
    public void r(g9.j jVar, IOException iOException) {
        if (jVar.f15058A) {
            return;
        }
        ((C0712i) this.f4578n).resumeWith(AbstractC1386c.f(iOException));
    }
}
