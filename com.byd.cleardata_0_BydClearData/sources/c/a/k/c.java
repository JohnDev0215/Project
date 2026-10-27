package c.a.k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends RuntimeException {
    public c(Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
