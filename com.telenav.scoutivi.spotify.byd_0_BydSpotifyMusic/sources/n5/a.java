package n5;

import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: JDK7PlatformImplementations.kt */
/* JADX INFO: loaded from: classes.dex */
public class a extends m5.a {
    @Override // m5.a
    public void a(Throwable cause, Throwable exception) {
        l.e(cause, "cause");
        l.e(exception, "exception");
        cause.addSuppressed(exception);
    }
}
