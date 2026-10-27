package defpackage;

import org.chromium.ui.base.DeviceFormFactor;

/* JADX INFO: renamed from: ez, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0745ez {
    public static void a() {
        C0683dt c0683dtX = C0683dt.x();
        try {
            if (!DeviceFormFactor.isTablet()) {
                Es.e().a("use-mobile-user-agent");
            }
            c0683dtX.close();
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
