package defpackage;

import android.os.Build;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: mA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1148mA implements InterfaceC1480sD {
    public C1148mA(C1036kA c1036kA) {
    }

    @Override // defpackage.InterfaceC1480sD
    public Object a(WebContents webContents) {
        int i = Build.VERSION.SDK_INT;
        return i >= 28 ? new C0925iA(webContents) : i >= 26 ? new C0869hA(webContents) : new C0813gA(webContents);
    }
}
