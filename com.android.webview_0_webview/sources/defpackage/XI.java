package defpackage;

import J.N;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.ResultAnd;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XI implements Executor, InterfaceC0600cK {
    public final VJ a;
    public final VJ b;
    public final List c;
    public final Object d;
    public final InterfaceC0656dK e;

    public XI(LJ lj) {
        CoreImpl coreImpl = (CoreImpl) lj;
        Objects.requireNonNull(coreImpl);
        this.e = new WatcherImpl();
        this.d = new Object();
        QJ qj = QJ.c;
        ByteBuffer byteBufferA = coreImpl.a(8);
        byteBufferA.putInt(0, 8);
        byteBufferA.putInt(4, qj.a);
        ResultAnd resultAnd = (ResultAnd) N.MZhgS7uU(coreImpl, byteBufferA);
        if (resultAnd.a != 0) {
            throw new WJ(resultAnd.a);
        }
        C0990jK c0990jK = new C0990jK(coreImpl, ((Integer) ((C0767fK) resultAnd.b).a).intValue());
        C0990jK c0990jK2 = new C0990jK(coreImpl, ((Integer) ((C0767fK) resultAnd.b).b).intValue());
        this.b = c0990jK;
        this.a = c0990jK2;
        this.c = new ArrayList();
        ((WatcherImpl) this.e).a(this.b, KJ.c, this);
    }

    @Override // defpackage.InterfaceC0600cK
    public void a(int i) {
        Runnable runnable;
        VJ vj;
        if (i == 0) {
            try {
                vj = this.b;
                SJ sj = SJ.c;
            } catch (WJ unused) {
            }
            boolean z = vj.y(SJ.c).a == 0;
            if (z) {
                synchronized (this.d) {
                    runnable = (Runnable) this.c.remove(0);
                }
                runnable.run();
                return;
            }
        }
        synchronized (this.d) {
            this.a.close();
            this.c.clear();
        }
        WatcherImpl watcherImpl = (WatcherImpl) this.e;
        long j = watcherImpl.a;
        if (j != 0) {
            watcherImpl.b = null;
            N.MPTT407x(watcherImpl, j);
        }
        WatcherImpl watcherImpl2 = (WatcherImpl) this.e;
        long j2 = watcherImpl2.a;
        if (j2 != 0) {
            N.Mi32vqDA(watcherImpl2, j2);
            watcherImpl2.a = 0L;
        }
        this.b.close();
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        synchronized (this.d) {
            if (!this.a.a()) {
                throw new IllegalStateException("Trying to execute an action on a closed executor.");
            }
            this.c.add(runnable);
            VJ vj = this.a;
            UJ uj = UJ.c;
            vj.N(null, null, UJ.c);
        }
    }
}
