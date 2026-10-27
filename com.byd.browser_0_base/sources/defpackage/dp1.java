package defpackage;

import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappBridge;
import org.chromium.chrome.browser.browserservices.permissiondelegation.a;

/* JADX INFO: compiled from: NotificationPermissionUpdater.java */
/* JADX INFO: loaded from: classes.dex */
public final class dp1 {
    public final a a;
    public final sf3 b;

    public dp1(a aVar, sf3 sf3Var) {
        this.a = aVar;
        this.b = sf3Var;
    }

    public final void a(ft1 ft1Var, long j, String str, int i) {
        this.a.e(ft1Var, str, 5, i);
        InstalledWebappBridge.b(i, j);
    }
}
