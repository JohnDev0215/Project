package defpackage;

import android.os.Binder;
import android.os.Process;
import java.util.Map;
import org.chromium.android_webview.services.DeveloperUiService;

/* JADX INFO: renamed from: ks, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1073ks extends AbstractBinderC0357Tq {
    public final /* synthetic */ DeveloperUiService a;

    public BinderC1073ks(DeveloperUiService developerUiService) {
        this.a = developerUiService;
    }

    @Override // defpackage.InterfaceC0375Uq
    public void q(Map map) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("setFlagOverrides() may only be called by the Developer UI app");
        }
        synchronized (DeveloperUiService.u) {
            DeveloperUiService.a(this.a, DeveloperUiService.v, map);
            DeveloperUiService.v = map;
            if (map.isEmpty()) {
                DeveloperUiService.b(this.a);
            } else {
                DeveloperUiService.c(this.a);
            }
        }
    }
}
