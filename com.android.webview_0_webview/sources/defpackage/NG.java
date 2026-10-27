package defpackage;

import android.nfc.tech.TagTechnology;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NG {
    public final TagTechnology a;
    public final MG b;
    public boolean c;
    public final String d;

    public NG(TagTechnology tagTechnology, MG mg, byte[] bArr) {
        String string;
        this.a = tagTechnology;
        this.b = mg;
        if (bArr.length < 0) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder(bArr.length * 3);
            for (byte b : bArr) {
                if (sb.length() > 0) {
                    sb.append(":");
                }
                sb.append(String.format("%02x", Integer.valueOf(b & 255)));
            }
            string = sb.toString();
        }
        this.d = string;
    }

    public void a() throws IOException {
        if (this.a.isConnected()) {
            return;
        }
        this.a.connect();
        this.c = true;
    }
}
