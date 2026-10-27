package e.a.l;

/* JADX INFO: compiled from: OnErrorNotImplementedException.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public c(Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
