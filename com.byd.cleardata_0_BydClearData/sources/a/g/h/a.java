package a.g.h;

import a.g.j.h;
import android.util.Base64;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f296c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f297d;
    private final int e;
    private final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f294a = str4;
        String str5 = (String) h.c(str2);
        this.f295b = str5;
        String str6 = (String) h.c(str3);
        this.f296c = str6;
        this.f297d = (List) h.c(list);
        this.e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f297d;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f294a;
    }

    public String e() {
        return this.f295b;
    }

    public String f() {
        return this.f296c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f294a + ", mProviderPackage: " + this.f295b + ", mQuery: " + this.f296c + ", mCertificates:");
        for (int i = 0; i < this.f297d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f297d.get(i);
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
