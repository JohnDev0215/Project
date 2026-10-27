package Q5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class N0 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f6447n;
    public final /* synthetic */ m7.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6448p;

    public /* synthetic */ N0(int i7, g0.o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        this.l = 0;
        this.f6446m = oVar;
        this.o = interfaceC2772a;
        this.f6447n = z6;
        this.f6448p = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.getClass();
                w0.c.q(C0735d.Z(this.f6448p | 1), c0761q, (g0.o) this.f6446m, (InterfaceC2772a) this.o, this.f6447n);
                break;
            case 1:
                num.getClass();
                int iZ = C0735d.Z(this.f6448p | 1);
                boolean z6 = this.f6447n;
                io.sentry.config.a.k(iZ, c0761q, (g0.o) this.f6446m, (InterfaceC2772a) this.o, z6);
                break;
            default:
                num.intValue();
                h6.q.i((String) this.f6446m, this.f6447n, (InterfaceC2782k) this.o, c0761q, C0735d.Z(this.f6448p | 1));
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ N0(Object obj, boolean z6, m7.c cVar, int i7, int i10) {
        this.l = i10;
        this.f6446m = obj;
        this.f6447n = z6;
        this.o = cVar;
        this.f6448p = i7;
    }
}
