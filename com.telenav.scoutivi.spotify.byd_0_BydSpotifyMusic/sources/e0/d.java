package e0;

import android.util.Base64;
import h0.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f6006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f6007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f6009d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6010e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f6011f;

    public d(String str, String str2, String str3, List<List<byte[]>> list) {
        this.f6006a = (String) h.f(str);
        this.f6007b = (String) h.f(str2);
        this.f6008c = (String) h.f(str3);
        this.f6009d = (List) h.f(list);
        this.f6011f = a(str, str2, str3);
    }

    private String a(String str, String str2, String str3) {
        return str + "-" + str2 + "-" + str3;
    }

    public List<List<byte[]>> b() {
        return this.f6009d;
    }

    public int c() {
        return this.f6010e;
    }

    String d() {
        return this.f6011f;
    }

    public String e() {
        return this.f6006a;
    }

    public String f() {
        return this.f6007b;
    }

    public String g() {
        return this.f6008c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f6006a + ", mProviderPackage: " + this.f6007b + ", mQuery: " + this.f6008c + ", mCertificates:");
        for (int i7 = 0; i7 < this.f6009d.size(); i7++) {
            sb.append(" [");
            List<byte[]> list = this.f6009d.get(i7);
            for (int i8 = 0; i8 < list.size(); i8++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i8), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f6010e);
        return sb.toString();
    }
}
