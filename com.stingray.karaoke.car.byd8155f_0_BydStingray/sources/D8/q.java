package D8;

import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import n7.AbstractC1998m;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class q extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1264m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ A7.o f1265n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public q(InterfaceC2772a interfaceC2772a, int i7) {
        super(0);
        this.f1264m = i7;
        switch (i7) {
            case 1:
                this.f1265n = (A7.o) interfaceC2772a;
                super(0);
                break;
            default:
                this.f1265n = (A7.o) interfaceC2772a;
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r1v5, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f1264m) {
            case 0:
                return AbstractC1998m.D0((Iterable) this.f1265n.a());
            default:
                try {
                    return (List) this.f1265n.a();
                } catch (SSLPeerUnverifiedException unused) {
                    return n7.u.l;
                }
        }
    }
}
