package defpackage;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.NdefFormatable;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KG implements MG {
    public final NdefFormatable a;

    public KG(NdefFormatable ndefFormatable) {
        this.a = ndefFormatable;
    }

    @Override // defpackage.MG
    public void a(NdefMessage ndefMessage) throws IOException, FormatException {
        this.a.format(ndefMessage);
    }

    @Override // defpackage.MG
    public boolean b() {
        return true;
    }

    @Override // defpackage.MG
    public NdefMessage c() {
        return new NdefMessage(new NdefRecord((short) 0, null, null, null), new NdefRecord[0]);
    }
}
