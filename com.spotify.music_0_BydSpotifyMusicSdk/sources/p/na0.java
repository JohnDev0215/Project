package p;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes.dex */
public final class na0 implements oa0 {
    public final Future c;

    public na0(ScheduledFuture scheduledFuture) {
        this.c = scheduledFuture;
    }

    @Override // p.oa0
    public final void dispose() {
        this.c.cancel(false);
    }

    public final String toString() {
        return "DisposableFutureHandle[" + this.c + ']';
    }
}
