package a.h.h;

import android.util.Base64;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f740c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f741d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f742e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f743f;

    public e(String str, String str2, String str3, List<List<byte[]>> list) {
        this.f738a = str;
        this.f739b = str2;
        this.f740c = str3;
        if (list == null) {
            throw null;
        }
        this.f741d = list;
        this.f742e = 0;
        this.f743f = str + "-" + str2 + "-" + str3;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbD = b.a.a.a.a.d("FontRequest {mProviderAuthority: ");
        sbD.append(this.f738a);
        sbD.append(", mProviderPackage: ");
        sbD.append(this.f739b);
        sbD.append(", mQuery: ");
        sbD.append(this.f740c);
        sbD.append(", mCertificates:");
        sb.append(sbD.toString());
        for (int i = 0; i < this.f741d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f741d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f742e);
        return sb.toString();
    }
}
