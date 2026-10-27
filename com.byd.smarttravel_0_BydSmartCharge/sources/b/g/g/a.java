package b.g.g;

import android.util.Base64;
import b.g.i.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f1015d;
    public final int e;
    public final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f1012a = str4;
        String str5 = (String) h.c(str2);
        this.f1013b = str5;
        String str6 = (String) h.c(str3);
        this.f1014c = str6;
        this.f1015d = (List) h.c(list);
        this.e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f1015d;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f1012a;
    }

    public String e() {
        return this.f1013b;
    }

    public String f() {
        return this.f1014c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1012a + ", mProviderPackage: " + this.f1013b + ", mQuery: " + this.f1014c + ", mCertificates:");
        for (int i = 0; i < this.f1015d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f1015d.get(i);
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
