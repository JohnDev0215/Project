package defpackage;

import android.content.ComponentCallbacks2;
import defpackage.wp2;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.download.DownloadManagerService;

/* JADX INFO: compiled from: DownloadSnackbarController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ld0 implements wp2.b {

    /* JADX INFO: compiled from: DownloadSnackbarController.java */
    public static class a {
    }

    public final wp2 a() {
        ComponentCallbacks2 componentCallbacks2 = ApplicationStatus.hasVisibleActivities() ? ApplicationStatus.c : null;
        if (componentCallbacks2 == null || !(componentCallbacks2 instanceof wp2.c)) {
            return null;
        }
        return ((wp2.c) componentCallbacks2).q0();
    }

    @Override // wp2.b
    public final void j(Object obj) {
        if (!(obj instanceof a)) {
            DownloadManagerService.openDownloadsPage(null, 6);
        } else {
            DownloadManagerService.g();
            ((a) obj).getClass();
            throw null;
        }
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }
}
