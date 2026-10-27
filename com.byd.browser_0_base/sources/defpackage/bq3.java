package defpackage;

import org.chromium.chrome.browser.webapps.WebApkUpdateManager;

/* JADX INFO: compiled from: WebApkUpdateManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bq3 implements Runnable {
    public final /* synthetic */ WebApkUpdateManager e;

    public bq3(WebApkUpdateManager webApkUpdateManager) {
        this.e = webApkUpdateManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.d(null, null, null);
    }
}
