package defpackage;

import J.N;
import android.os.IBinder;
import org.chromium.chrome.browser.base.SplitCompatService;

/* JADX INFO: compiled from: WebApkInstallCoordinatorServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class gp3 extends SplitCompatService.a {

    /* JADX INFO: compiled from: WebApkInstallCoordinatorServiceImpl.java */
    public class a extends qw0 {
    }

    public gp3() {
        new a();
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        sk skVar = bq.a;
        N.M09VlOh_("WebApkInstallService");
        return null;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void b() {
        sp.a().d(false);
    }
}
