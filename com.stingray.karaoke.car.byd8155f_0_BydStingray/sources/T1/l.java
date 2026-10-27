package T1;

import g1.AbstractC1274a;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractC1274a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AbstractC1274a f8506e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ThreadPoolExecutor f8507f;

    public l(AbstractC1274a abstractC1274a, ThreadPoolExecutor threadPoolExecutor) {
        this.f8506e = abstractC1274a;
        this.f8507f = threadPoolExecutor;
    }

    @Override // g1.AbstractC1274a
    public final void V(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.f8507f;
        try {
            this.f8506e.V(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // g1.AbstractC1274a
    public final void W(B8.t tVar) {
        ThreadPoolExecutor threadPoolExecutor = this.f8507f;
        try {
            this.f8506e.W(tVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
