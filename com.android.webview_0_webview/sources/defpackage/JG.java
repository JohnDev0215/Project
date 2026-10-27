package defpackage;

import org.chromium.device.nfc.NfcDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JG implements FF {
    public NfcDelegate s;
    public HG t;

    public JG(NfcDelegate nfcDelegate) {
        this.s = nfcDelegate;
    }

    @Override // defpackage.FF
    public void C(int i, C0934iJ c0934iJ) {
        InterfaceC1212nJ interfaceC1212nJ;
        HG hg = this.t;
        if (hg != null && (interfaceC1212nJ = hg.u) != null) {
            interfaceC1212nJ.close();
            hg.u = null;
        }
        this.t = new HG(i, this.s, c0934iJ);
    }

    @Override // defpackage.LI
    public void I(WJ wj) {
    }

    @Override // defpackage.FF
    public void L() {
        HG hg = this.t;
        if (hg != null) {
            hg.R();
        }
    }

    @Override // defpackage.FF
    public void O() {
        HG hg = this.t;
        if (hg != null) {
            hg.P();
        }
    }

    @Override // defpackage.InterfaceC0822gJ, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        HG hg = this.t;
        if (hg != null) {
            InterfaceC1212nJ interfaceC1212nJ = hg.u;
            if (interfaceC1212nJ != null) {
                interfaceC1212nJ.close();
                hg.u = null;
            }
            this.t = null;
        }
    }
}
