package defpackage;

import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;

/* JADX INFO: renamed from: wA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1697wA implements InterfaceC1488sL {
    public static AndroidOverlayProviderImpl a;

    @Override // defpackage.InterfaceC1488sL
    public InterfaceC0822gJ a() {
        if (a == null) {
            a = new AndroidOverlayProviderImpl();
        }
        return a;
    }
}
