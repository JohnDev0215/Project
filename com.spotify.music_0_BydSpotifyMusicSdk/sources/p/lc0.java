package p;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class lc0 extends vr2 {
    public final /* synthetic */ vr2 n;
    public final /* synthetic */ ThreadPoolExecutor o;

    public lc0(vr2 vr2Var, ThreadPoolExecutor threadPoolExecutor) {
        this.n = vr2Var;
        this.o = threadPoolExecutor;
    }

    @Override // p.vr2
    public final void n(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.o;
        try {
            this.n.n(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // p.vr2
    public final void o(gh2 gh2Var) {
        ThreadPoolExecutor threadPoolExecutor = this.o;
        try {
            this.n.o(gh2Var);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
