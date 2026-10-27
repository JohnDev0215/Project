package C1;

import android.util.Base64;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f933b;
    public final String c;
    public final List d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f934e;

    public e(String str, List list, String str2, String str3) {
        str.getClass();
        this.f932a = str;
        str2.getClass();
        this.f933b = str2;
        this.c = str3;
        list.getClass();
        this.d = list;
        this.f934e = str + "-" + str2 + "-" + str3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f932a + ", mProviderPackage: " + this.f933b + ", mQuery: " + this.c + ", mCertificates:");
        int i7 = 0;
        while (true) {
            List list = this.d;
            if (i7 >= list.size()) {
                sb.append("}mCertificatesArray: 0");
                return sb.toString();
            }
            sb.append(" [");
            List list2 = (List) list.get(i7);
            for (int i10 = 0; i10 < list2.size(); i10++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list2.get(i10), 0));
                sb.append("\"");
            }
            sb.append(" ]");
            i7++;
        }
    }
}
