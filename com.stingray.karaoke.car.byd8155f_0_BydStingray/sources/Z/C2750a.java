package z;

import A.C0004b;
import A7.o;
import T.C0735d;
import T.C0761q;
import m7.q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: z.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2750a extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f24221m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f24222n;
    public final /* synthetic */ InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0.o f24223p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C0004b f24224q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f24225r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2750a(m mVar, InterfaceC2772a interfaceC2772a, g0.o oVar, C0004b c0004b, int i7, int i10) {
        super(2);
        this.f24221m = i10;
        this.f24222n = mVar;
        this.o = interfaceC2772a;
        this.f24223p = oVar;
        this.f24224q = c0004b;
        this.f24225r = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f24221m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f24225r | 1);
                C0004b c0004b = this.f24224q;
                h6.j.c(this.f24222n, this.o, this.f24223p, c0004b, (C0761q) obj, iZ);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f24225r | 1);
                C0004b c0004b2 = this.f24224q;
                h6.j.c(this.f24222n, this.o, this.f24223p, c0004b2, (C0761q) obj, iZ2);
                break;
        }
        return q.f19267a;
    }
}
