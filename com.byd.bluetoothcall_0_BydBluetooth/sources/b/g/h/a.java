package b.g.h;

import android.util.Base64;
import b.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f2211d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f2212e;
    private final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f2208a = str4;
        String str5 = (String) h.c(str2);
        this.f2209b = str5;
        String str6 = (String) h.c(str3);
        this.f2210c = str6;
        this.f2211d = (List) h.c(list);
        this.f2212e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f2211d;
    }

    public int b() {
        return this.f2212e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f2208a;
    }

    public String e() {
        return this.f2209b;
    }

    public String f() {
        return this.f2210c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2208a + ", mProviderPackage: " + this.f2209b + ", mQuery: " + this.f2210c + ", mCertificates:");
        for (int i = 0; i < this.f2211d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f2211d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f2212e);
        return sb.toString();
    }
}
