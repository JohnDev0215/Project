package defpackage;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LG implements MG {
    public final Ndef a;

    public LG(Ndef ndef) {
        this.a = ndef;
    }

    @Override // defpackage.MG
    public void a(NdefMessage ndefMessage) throws IOException, FormatException {
        this.a.writeNdefMessage(ndefMessage);
    }

    @Override // defpackage.MG
    public boolean b() {
        return this.a.getNdefMessage() == null;
    }

    @Override // defpackage.MG
    public NdefMessage c() {
        return this.a.getNdefMessage();
    }
}
