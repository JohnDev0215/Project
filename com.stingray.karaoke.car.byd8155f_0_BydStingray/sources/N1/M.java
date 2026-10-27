package N1;

import S8.C0720q;
import java.util.concurrent.CancellationException;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class M extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final M f4478m = new M(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        U u4 = (U) obj;
        Throwable cancellationException = (Throwable) obj2;
        A7.m.f(u4, "msg");
        if (cancellationException == null) {
            cancellationException = new CancellationException("DataStore scope was cancelled before updateData could complete");
        }
        u4.f4500b.T(new C0720q(cancellationException, false));
        return m7.q.f19267a;
    }
}
