package Q5;

import java.util.List;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0558d0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6742m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f6743n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0558d0(int i7, List list) {
        super(1);
        this.f6742m = i7;
        this.f6743n = list;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f6742m) {
            case 0:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 1:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 2:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 3:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 4:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 5:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            case 6:
                this.f6743n.get(((Number) obj).intValue());
                return null;
            default:
                io.sentry.U u4 = (io.sentry.U) obj;
                A7.m.f(u4, "it");
                List list = this.f6743n;
                if (!list.isEmpty()) {
                    u4.A(AbstractC1998m.f0(list, null, null, null, io.sentry.okhttp.c.o, 31), "proxies");
                }
                return m7.q.f19267a;
        }
    }
}
