package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DG {
    public static final byte[][] b = {new byte[]{-128, 115, -64, 33, -64, 87, 89, 117, 98, 105, 75, 101, 121}, new byte[]{89, 117, 98, 105, 107, 101, 121, 78, 69, 79, 114, 51}};
    public static DG c;
    public final List a = new ArrayList();

    public DG() {
        String strMOVY9QtZ = N.MOVY9QtZ("WebNFCBlockList", "historical_bytes_additions");
        if (strMOVY9QtZ == null || strMOVY9QtZ.isEmpty()) {
            return;
        }
        for (String str : strMOVY9QtZ.split(",")) {
            int length = str.length();
            byte[] bArr = null;
            if (length % 2 == 1) {
                Ts.f("NfcBlocklist", "Length of %s is odd", str);
            } else {
                byte[] bArr2 = new byte[length / 2];
                for (int i = 0; i < length; i += 2) {
                    char cCharAt = str.charAt(i);
                    char cCharAt2 = str.charAt(i + 1);
                    if (Character.digit(cCharAt, 16) < 0 || Character.digit(cCharAt2, 16) < 0) {
                        Ts.f("NfcBlocklist", "Invalid hex character found in %s", str);
                        break;
                    }
                    bArr2[i / 2] = (byte) (Character.digit(cCharAt2, 16) + (Character.digit(cCharAt, 16) << 4));
                }
                bArr = bArr2;
            }
            if (bArr != null) {
                this.a.add(bArr);
            }
        }
    }
}
