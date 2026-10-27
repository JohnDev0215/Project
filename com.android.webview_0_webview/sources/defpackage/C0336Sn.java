package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Sn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0336Sn implements InterfaceC0761fE {
    public final WeakReference a;

    public C0336Sn(AwContents awContents, C1838yn c1838yn) {
        this.a = new WeakReference(awContents);
    }

    @Override // defpackage.InterfaceC0761fE
    public void a(InterfaceC0873hE interfaceC0873hE) {
        AwContents awContents = (AwContents) this.a.get();
        if (awContents == null) {
            throw new IllegalStateException("AwContents should be available at this time");
        }
        awContents.B0 = interfaceC0873hE;
    }

    @Override // defpackage.InterfaceC0761fE
    public InterfaceC0873hE get() {
        AwContents awContents = (AwContents) this.a.get();
        if (awContents == null) {
            return null;
        }
        return awContents.B0;
    }
}
