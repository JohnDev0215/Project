package defpackage;

import android.view.KeyEvent;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: yn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1838yn extends KD implements LD {
    public final /* synthetic */ AwContents s;

    public C1838yn(AwContents awContents) {
        this.s = awContents;
    }

    @Override // defpackage.KD, defpackage.LD
    public void d(KeyEvent keyEvent) {
        if (AwContents.u(keyEvent)) {
            this.s.S.M(true);
        }
    }
}
