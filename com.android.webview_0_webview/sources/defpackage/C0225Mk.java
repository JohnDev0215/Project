package defpackage;

import java.io.PrintStream;
import java.util.List;

/* JADX INFO: renamed from: Mk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0225Mk extends AbstractC0172Jk {
    public final C0208Lk a = new C0208Lk();

    @Override // defpackage.AbstractC0172Jk
    public void a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.a.a(th, true).add(th2);
    }

    @Override // defpackage.AbstractC0172Jk
    public void b(Throwable th) {
        th.printStackTrace();
        List<Throwable> listA = this.a.a(th, false);
        if (listA == null) {
            return;
        }
        synchronized (listA) {
            for (Throwable th2 : listA) {
                System.err.print("Suppressed: ");
                th2.printStackTrace();
            }
        }
    }

    @Override // defpackage.AbstractC0172Jk
    public void c(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> listA = this.a.a(th, false);
        if (listA == null) {
            return;
        }
        synchronized (listA) {
            for (Throwable th2 : listA) {
                printStream.print("Suppressed: ");
                th2.printStackTrace(printStream);
            }
        }
    }
}
