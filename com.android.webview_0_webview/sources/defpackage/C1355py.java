package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: py, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1355py implements InterfaceC1656vO {
    public C1546tO a;
    public WindowAndroid b;
    public Runnable c;

    @Override // defpackage.InterfaceC1656vO
    public void b(JO jo, int i) {
        Runnable runnable = this.c;
        if (runnable != null) {
            runnable.run();
        }
        this.c = null;
    }

    @Override // defpackage.InterfaceC1656vO
    public void d(JO jo, int i) {
        if (i == 1) {
            this.a.b(jo, 2);
            return;
        }
        try {
            this.b.X(new Intent(Build.VERSION.SDK_INT >= 29 ? "android.settings.panel.action.NFC" : "android.settings.NFC_SETTINGS"), new C1300oy(this, jo), null);
        } catch (ActivityNotFoundException unused) {
            this.a.b(jo, 1);
        }
    }
}
