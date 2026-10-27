package org.chromium.content.browser;

import android.app.Activity;
import defpackage.C0590cA;
import org.chromium.base.Callback;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentNfcDelegate implements NfcDelegate {
    public static ContentNfcDelegate create() {
        return new ContentNfcDelegate();
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public void a(int i) {
        NfcHost nfcHost = (NfcHost) NfcHost.v.get(i);
        nfcHost.u = null;
        C0590cA.P(nfcHost.s).s.g(nfcHost);
        NfcHost.v.remove(nfcHost.t);
    }

    @Override // org.chromium.device.nfc.NfcDelegate
    public void b(int i, Callback callback) {
        NfcHost nfcHost = (NfcHost) NfcHost.v.get(i);
        nfcHost.u = callback;
        C0590cA.P(nfcHost.s).s.f(nfcHost);
        WindowAndroid windowAndroidH = nfcHost.s.H();
        nfcHost.u.a(windowAndroidH != null ? (Activity) windowAndroidH.Q().get() : null);
    }
}
