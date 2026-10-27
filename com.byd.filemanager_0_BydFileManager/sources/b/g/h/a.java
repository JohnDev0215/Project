package b.g.h;

import android.util.Base64;
import b.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1854c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f1855d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f1856e;
    private final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f1852a = str4;
        String str5 = (String) h.c(str2);
        this.f1853b = str5;
        String str6 = (String) h.c(str3);
        this.f1854c = str6;
        this.f1855d = (List) h.c(list);
        this.f1856e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f1855d;
    }

    public int b() {
        return this.f1856e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f1852a;
    }

    public String e() {
        return this.f1853b;
    }

    public String f() {
        return this.f1854c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1852a + ", mProviderPackage: " + this.f1853b + ", mQuery: " + this.f1854c + ", mCertificates:");
        for (int i = 0; i < this.f1855d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f1855d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f1856e);
        return sb.toString();
    }
}
