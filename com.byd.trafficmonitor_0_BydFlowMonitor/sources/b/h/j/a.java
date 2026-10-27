package b.h.j;

import android.util.Base64;
import b.h.l.g;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2482a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2483b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f2485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f2487f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) g.c(str);
        this.f2482a = str4;
        String str5 = (String) g.c(str2);
        this.f2483b = str5;
        String str6 = (String) g.c(str3);
        this.f2484c = str6;
        this.f2485d = (List) g.c(list);
        this.f2486e = 0;
        this.f2487f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f2485d;
    }

    public int b() {
        return this.f2486e;
    }

    public String c() {
        return this.f2487f;
    }

    public String d() {
        return this.f2482a;
    }

    public String e() {
        return this.f2483b;
    }

    public String f() {
        return this.f2484c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f2482a + ", mProviderPackage: " + this.f2483b + ", mQuery: " + this.f2484c + ", mCertificates:");
        for (int i2 = 0; i2 < this.f2485d.size(); i2++) {
            sb.append(" [");
            List<byte[]> list = this.f2485d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f2486e);
        return sb.toString();
    }
}
