package X8;

import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public final class e extends RuntimeException {
    public final transient InterfaceC2194h l;

    public e(InterfaceC2194h interfaceC2194h) {
        this.l = interfaceC2194h;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return this.l.toString();
    }
}
