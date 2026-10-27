package A4;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Executor {
    public final /* synthetic */ int l;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.l) {
            case 0:
                q.f().post(runnable);
                break;
            default:
                runnable.run();
                break;
        }
    }
}
