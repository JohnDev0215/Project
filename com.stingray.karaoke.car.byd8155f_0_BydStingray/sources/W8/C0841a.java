package W8;

import V8.InterfaceC0818g;
import java.util.concurrent.CancellationException;

/* JADX INFO: renamed from: W8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0841a extends CancellationException {
    public final transient InterfaceC0818g l;

    public C0841a(InterfaceC0818g interfaceC0818g) {
        super("Flow was aborted, no more elements needed");
        this.l = interfaceC0818g;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
