package y0;

import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: y0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2724b extends CancellationException {
    public static final C2724b l = new C2724b();

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(AbstractC2737o.c);
        return this;
    }
}
