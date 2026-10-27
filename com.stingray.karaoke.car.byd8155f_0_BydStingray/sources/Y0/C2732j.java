package y0;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: y0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2732j extends CancellationException {
    public C2732j(long j3) {
        super("Timed out waiting for " + j3 + " ms");
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(AbstractC2737o.c);
        return this;
    }
}
