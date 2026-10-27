package defpackage;

import java.io.PrintStream;

/* JADX INFO: renamed from: Nk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0243Nk extends AbstractC0172Jk {
    @Override // defpackage.AbstractC0172Jk
    public void a(Throwable th, Throwable th2) {
    }

    @Override // defpackage.AbstractC0172Jk
    public void b(Throwable th) {
        th.printStackTrace();
    }

    @Override // defpackage.AbstractC0172Jk
    public void c(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
    }
}
