package y;

import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: y.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2681B extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23826m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2682C f23827n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2681B(C2682C c2682c, int i7) {
        super(1);
        this.f23826m = i7;
        this.f23827n = c2682c;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f23826m) {
            case 0:
                long j3 = ((m0.c) obj).f18880a;
                InterfaceC2772a interfaceC2772a = this.f23827n.f23830U;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
                break;
            case 1:
                long j10 = ((m0.c) obj).f18880a;
                InterfaceC2772a interfaceC2772a2 = this.f23827n.f23829T;
                if (interfaceC2772a2 != null) {
                    interfaceC2772a2.a();
                }
                break;
            default:
                long j11 = ((m0.c) obj).f18880a;
                C2682C c2682c = this.f23827n;
                if (c2682c.f23929E) {
                    c2682c.f23930F.a();
                }
                break;
        }
        return m7.q.f19267a;
    }
}
