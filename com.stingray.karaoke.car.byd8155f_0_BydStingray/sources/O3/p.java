package O3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public final class p extends FutureTask {
    public q l;

    @Override // java.util.concurrent.FutureTask
    public final void done() {
        try {
            if (isCancelled()) {
                return;
            }
            try {
                this.l.d((o) get());
            } catch (InterruptedException | ExecutionException e10) {
                this.l.d(new o(e10));
            }
        } finally {
            this.l = null;
        }
    }
}
