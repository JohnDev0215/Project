package p;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class ul2 extends o72 implements Runnable {
    public final long m;

    public ul2(long j, c00 c00Var) {
        super(c00Var, ((ContinuationImpl) c00Var).getContext());
        this.m = j;
    }

    @Override // p.a1, kotlinx.coroutines.g
    public final String P() {
        return super.P() + "(timeMillis=" + this.m + ')';
    }

    @Override // java.lang.Runnable
    public final void run() {
        bk0.t(this.k);
        t(new TimeoutCancellationException("Timed out waiting for " + this.m + " ms", this));
    }
}
