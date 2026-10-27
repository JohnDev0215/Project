package b.g.h;

import android.util.Base64;
import b.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f2567d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f2568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f2569f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f2564a = str4;
        String str5 = (String) h.c(str2);
        this.f2565b = str5;
        String str6 = (String) h.c(str3);
        this.f2566c = str6;
        this.f2567d = (List) h.c(list);
        this.f2568e = 0;
        this.f2569f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f2567d;
    }

    public int b() {
        return this.f2568e;
    }

    public String c() {
        return this.f2569f;
    }

    public String d() {
        return this.f2564a;
    }

    public String e() {
        return this.f2565b;
    }

    public String f() {
        return this.f2566c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2564a + ", mProviderPackage: " + this.f2565b + ", mQuery: " + this.f2566c + ", mCertificates:");
        for (int i2 = 0; i2 < this.f2567d.size(); i2++) {
            sb.append(" [");
            List<byte[]> list = this.f2567d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f2568e);
        return sb.toString();
    }
}
