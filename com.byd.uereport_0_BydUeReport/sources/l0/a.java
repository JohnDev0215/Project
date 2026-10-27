package l0;

import android.util.Base64;
import java.util.List;
import n0.h;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6018a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f6019b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f6020c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f6021d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6022e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f6023f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.e(str);
        this.f6018a = str4;
        String str5 = (String) h.e(str2);
        this.f6019b = str5;
        String str6 = (String) h.e(str3);
        this.f6020c = str6;
        this.f6021d = (List) h.e(list);
        this.f6022e = 0;
        this.f6023f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f6021d;
    }

    public int b() {
        return this.f6022e;
    }

    public String c() {
        return this.f6023f;
    }

    public String d() {
        return this.f6018a;
    }

    public String e() {
        return this.f6019b;
    }

    public String f() {
        return this.f6020c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f6018a + ", mProviderPackage: " + this.f6019b + ", mQuery: " + this.f6020c + ", mCertificates:");
        for (int i8 = 0; i8 < this.f6021d.size(); i8++) {
            sb.append(" [");
            List<byte[]> list = this.f6021d.get(i8);
            for (int i9 = 0; i9 < list.size(); i9++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i9), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f6022e);
        return sb.toString();
    }
}
