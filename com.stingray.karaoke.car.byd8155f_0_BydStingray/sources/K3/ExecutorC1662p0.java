package k3;

import android.os.Handler;
import h2.AbstractC1343A;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: k3.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ExecutorC1662p0 implements Executor {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Handler f18028m;

    public /* synthetic */ ExecutorC1662p0(Handler handler, int i7) {
        this.l = i7;
        this.f18028m = handler;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.l) {
            case 0:
                AbstractC1343A.K(this.f18028m, runnable);
                break;
            default:
                this.f18028m.post(runnable);
                break;
        }
    }
}
