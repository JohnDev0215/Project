package b.g.h;

import android.util.Base64;
import b.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2410b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2411c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f2412d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f2413e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f2414f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f2409a = str4;
        String str5 = (String) h.c(str2);
        this.f2410b = str5;
        String str6 = (String) h.c(str3);
        this.f2411c = str6;
        this.f2412d = (List) h.c(list);
        this.f2413e = 0;
        this.f2414f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f2412d;
    }

    public int b() {
        return this.f2413e;
    }

    public String c() {
        return this.f2414f;
    }

    public String d() {
        return this.f2409a;
    }

    public String e() {
        return this.f2410b;
    }

    public String f() {
        return this.f2411c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2409a + ", mProviderPackage: " + this.f2410b + ", mQuery: " + this.f2411c + ", mCertificates:");
        for (int i = 0; i < this.f2412d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f2412d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f2413e);
        return sb.toString();
    }
}
