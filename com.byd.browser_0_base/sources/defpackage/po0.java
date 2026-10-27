package defpackage;

import android.util.Base64;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class po0 {
    public final String a;
    public final String b;
    public final String c;
    public final List<List<byte[]>> d;
    public final int e;
    public final String f;

    public po0(String str, String str2, String str3, List<List<byte[]>> list) {
        str.getClass();
        this.a = str;
        str2.getClass();
        this.b = str2;
        this.c = str3;
        list.getClass();
        this.d = list;
        this.e = 0;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.a + ", mProviderPackage: " + this.b + ", mQuery: " + this.c + ", mCertificates:");
        int i = 0;
        while (true) {
            List<List<byte[]>> list = this.d;
            if (i >= list.size()) {
                sb.append("}");
                sb.append("mCertificatesArray: " + this.e);
                return sb.toString();
            }
            sb.append(" [");
            List<byte[]> list2 = list.get(i);
            for (int i2 = 0; i2 < list2.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list2.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
            i++;
        }
    }

    public po0(int i, String str) {
        this.a = "com.google.android.gms.fonts";
        this.b = "com.google.android.gms";
        str.getClass();
        this.c = str;
        this.d = null;
        if (i != 0) {
            this.e = i;
            this.f = "com.google.android.gms.fonts-com.google.android.gms-".concat(str);
            return;
        }
        throw new IllegalArgumentException();
    }
}
