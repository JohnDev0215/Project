package Q5;

import T.C0735d;
import T.C0761q;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class M implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6435m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6436n;
    public final /* synthetic */ Object o;

    public /* synthetic */ M(Object obj, Object obj2, int i7, int i10) {
        this.l = i10;
        this.f6435m = obj;
        this.o = obj2;
        this.f6436n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                l9.l.g((g0.o) this.f6435m, (D.L) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
            case 1:
                num.getClass();
                w0.c.u((g0.o) this.f6435m, (C0595m1) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
            case 2:
                num.getClass();
                AbstractC2673a.e(C0735d.Z(this.f6436n | 1), c0761q, (g0.o) this.f6435m, (InterfaceC2782k) this.o);
                break;
            case 3:
                num.getClass();
                AbstractC2673a.n((g0.o) this.f6435m, (Z5.c) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
            case 4:
                num.intValue();
                AbstractC2673a.k((String) this.f6435m, (String) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
            case 5:
                num.intValue();
                io.sentry.config.a.v((String) this.f6435m, (X5.D0) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
            default:
                num.getClass();
                C7.a.j((g0.o) this.f6435m, (X5.D0) this.o, c0761q, C0735d.Z(this.f6436n | 1));
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ M(InterfaceC2782k interfaceC2782k, g0.o oVar, int i7) {
        this.l = 2;
        this.o = interfaceC2782k;
        this.f6435m = oVar;
        this.f6436n = i7;
    }
}
