package c9;

import g1.AbstractC1274a;
import java.util.List;
import java.util.regex.Pattern;
import r9.C2319i;
import r9.C2322l;
import r9.InterfaceC2320j;

/* JADX INFO: loaded from: classes.dex */
public final class A extends AbstractC1274a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final y f12436i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f12437j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final byte[] f12438k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2322l f12439e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f12440f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y f12441g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f12442h;

    static {
        Pattern pattern = y.d;
        P8.n.w("multipart/mixed");
        P8.n.w("multipart/alternative");
        P8.n.w("multipart/digest");
        P8.n.w("multipart/parallel");
        f12436i = P8.n.w("multipart/form-data");
        f12437j = new byte[]{13, 10};
        f12438k = new byte[]{45, 45};
    }

    public A(C2322l c2322l, y yVar, List list) {
        A7.m.f(yVar, "type");
        this.f12439e = c2322l;
        this.f12440f = list;
        Pattern pattern = y.d;
        this.f12441g = P8.n.w(yVar + "; boundary=" + c2322l.r());
        this.f12442h = -1L;
    }

    @Override // g1.AbstractC1274a
    public final long B() {
        long j3 = this.f12442h;
        if (j3 != -1) {
            return j3;
        }
        long jG0 = g0(null, true);
        this.f12442h = jG0;
        return jG0;
    }

    @Override // g1.AbstractC1274a
    public final y C() {
        return this.f12441g;
    }

    @Override // g1.AbstractC1274a
    public final void f0(InterfaceC2320j interfaceC2320j) {
        g0(interfaceC2320j, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long g0(InterfaceC2320j interfaceC2320j, boolean z6) {
        C2319i c2319i;
        if (z6) {
            interfaceC2320j = new C2319i();
            c2319i = interfaceC2320j;
        } else {
            c2319i = 0;
        }
        List list = this.f12440f;
        int size = list.size();
        C2322l c2322l = this.f12439e;
        if (size > 0) {
            throw null;
        }
        A7.m.c(interfaceC2320j);
        byte[] bArr = f12438k;
        interfaceC2320j.u(bArr);
        interfaceC2320j.A(c2322l);
        interfaceC2320j.u(bArr);
        interfaceC2320j.u(f12437j);
        if (!z6) {
            return 0L;
        }
        A7.m.c(c2319i);
        long j3 = c2319i.f21932m;
        c2319i.b();
        return j3;
    }
}
