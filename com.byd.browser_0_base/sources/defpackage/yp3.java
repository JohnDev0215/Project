package defpackage;

import org.chromium.chrome.browser.metrics.UmaUtils;

/* JADX INFO: compiled from: WebApkSplashscreenMetrics.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yp3 implements vq2 {
    public final long a;
    public final long b;

    public yp3(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    @Override // defpackage.vq2
    public final void a(long j) {
        if (!UmaUtils.c() || UmaUtils.b()) {
            return;
        }
        long j2 = this.a;
        if (j2 == -1) {
            return;
        }
        oj2.Y(j - j2, "WebApk.Startup.Cold.ShellLaunchToSplashscreenVisible");
        long j3 = this.b;
        if (j3 != -1) {
            oj2.Y(j3 - j2, "WebApk.Startup.Cold.NewStyle.ShellLaunchToSplashscreenVisible");
        }
    }

    @Override // defpackage.vq2
    public final void b() {
    }
}
