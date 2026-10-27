package defpackage;

import org.chromium.base.ApplicationStatus;

/* JADX INFO: renamed from: ws, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1733ws implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        if (ApplicationStatus.c != null) {
            return;
        }
        C1678vs c1678vs = new C1678vs(this);
        ApplicationStatus.c = c1678vs;
        ApplicationStatus.e.f(c1678vs);
    }
}
