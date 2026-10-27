package b0;

import A.C0004b;
import A7.o;
import T.C0735d;
import T.C0761q;
import h6.m;
import m7.q;
import x.InterfaceC2615D;
import x.x0;
import z.C2751b;
import z.n;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f12184m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g0.o f12185n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f12186p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f12187q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f12188r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f12189s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Boolean bool, g0.o oVar, InterfaceC2615D interfaceC2615D, String str, b bVar, int i7) {
        super(2);
        this.f12184m = 2;
        this.f12188r = bool;
        this.f12185n = oVar;
        this.f12187q = interfaceC2615D;
        this.f12189s = str;
        this.f12186p = bVar;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f12184m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.o) | 1;
                Boolean bool = (Boolean) this.f12188r;
                Integer num = (Integer) this.f12187q;
                ((b) this.f12186p).f(this.f12185n, num, bool, (InterfaceC2772a) this.f12189s, (C0761q) obj, iZ);
                break;
            case 1:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.o | 1);
                g0.o oVar = this.f12185n;
                InterfaceC2782k interfaceC2782k = (InterfaceC2782k) this.f12187q;
                androidx.compose.ui.viewinterop.a.b((InterfaceC2782k) this.f12186p, oVar, interfaceC2782k, (InterfaceC2782k) this.f12188r, (InterfaceC2782k) this.f12189s, (C0761q) obj, iZ2);
                break;
            case 2:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.o | 1);
                b bVar = (b) this.f12186p;
                m.a((Boolean) this.f12188r, this.f12185n, (InterfaceC2615D) this.f12187q, (String) this.f12189s, bVar, (C0761q) obj, iZ3);
                break;
            case 3:
                ((Number) obj2).intValue();
                int iZ4 = C0735d.Z(this.o | 1);
                b bVar2 = (b) this.f12186p;
                m.b((x0) this.f12187q, this.f12185n, (InterfaceC2615D) this.f12188r, (InterfaceC2782k) this.f12189s, bVar2, (C0761q) obj, iZ4);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ5 = C0735d.Z(this.o | 1);
                C0004b c0004b = (C0004b) this.f12188r;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f12189s;
                g0.o oVar2 = this.f12185n;
                n.d((z.g) this.f12186p, interfaceC2772a, oVar2, (C2751b) this.f12187q, c0004b, (C0761q) obj, iZ5);
                break;
        }
        return q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(m7.c cVar, g0.o oVar, Object obj, Object obj2, m7.c cVar2, int i7, int i10) {
        super(2);
        this.f12184m = i10;
        this.f12186p = cVar;
        this.f12185n = oVar;
        this.f12187q = obj;
        this.f12188r = obj2;
        this.f12189s = cVar2;
        this.o = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(x0 x0Var, g0.o oVar, InterfaceC2615D interfaceC2615D, InterfaceC2782k interfaceC2782k, b bVar, int i7) {
        super(2);
        this.f12184m = 3;
        this.f12187q = x0Var;
        this.f12185n = oVar;
        this.f12188r = interfaceC2615D;
        this.f12189s = interfaceC2782k;
        this.f12186p = bVar;
        this.o = i7;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(z.g gVar, InterfaceC2772a interfaceC2772a, g0.o oVar, C2751b c2751b, C0004b c0004b, int i7) {
        super(2);
        this.f12184m = 4;
        this.f12186p = gVar;
        this.f12189s = interfaceC2772a;
        this.f12185n = oVar;
        this.f12187q = c2751b;
        this.f12188r = c0004b;
        this.o = i7;
    }
}
