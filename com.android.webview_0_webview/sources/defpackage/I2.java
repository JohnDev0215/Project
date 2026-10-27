package defpackage;

import android.util.Base64;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class I2 {
    public final String a;
    public final String b;
    public final String c;
    public final List d;
    public final int e;
    public final String f;

    public I2(String str, String str2, String str3, List list) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        if (list == null) {
            throw null;
        }
        this.d = list;
        this.e = 0;
        this.f = str + "-" + this.b + "-" + this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbF = Z7.f("FontRequest {mProviderAuthority: ");
        sbF.append(this.a);
        sbF.append(", mProviderPackage: ");
        sbF.append(this.b);
        sbF.append(", mQuery: ");
        sbF.append(this.c);
        sbF.append(", mCertificates:");
        sb.append(sbF.toString());
        for (int i = 0; i < this.d.size(); i++) {
            sb.append(" [");
            List list = (List) this.d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: 0");
        return sb.toString();
    }
}
