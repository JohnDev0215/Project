package defpackage;

import org.chromium.device.nfc.NfcDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IG implements InterfaceC1488sL {
    public NfcDelegate a;

    public IG(NfcDelegate nfcDelegate) {
        this.a = nfcDelegate;
    }

    @Override // defpackage.InterfaceC1488sL
    public InterfaceC0822gJ a() {
        return new JG(this.a);
    }
}
