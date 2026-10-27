package c.g.h;

import android.util.Base64;
import c.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f2199d;
    private final int e;
    private final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f2196a = str4;
        String str5 = (String) h.c(str2);
        this.f2197b = str5;
        String str6 = (String) h.c(str3);
        this.f2198c = str6;
        this.f2199d = (List) h.c(list);
        this.e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f2199d;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f2196a;
    }

    public String e() {
        return this.f2197b;
    }

    public String f() {
        return this.f2198c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2196a + ", mProviderPackage: " + this.f2197b + ", mQuery: " + this.f2198c + ", mCertificates:");
        for (int i = 0; i < this.f2199d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f2199d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.e);
        return sb.toString();
    }
}
