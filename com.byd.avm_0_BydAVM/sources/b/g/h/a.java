package b.g.h;

import android.util.Base64;
import b.g.j.h;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f1629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f1632d;
    private final int e;
    private final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f1629a = str4;
        String str5 = (String) h.c(str2);
        this.f1630b = str5;
        String str6 = (String) h.c(str3);
        this.f1631c = str6;
        this.f1632d = (List) h.c(list);
        this.e = 0;
        this.f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f1632d;
    }

    public int b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.f1629a;
    }

    public String e() {
        return this.f1630b;
    }

    public String f() {
        return this.f1631c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1629a + ", mProviderPackage: " + this.f1630b + ", mQuery: " + this.f1631c + ", mCertificates:");
        for (int i = 0; i < this.f1632d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f1632d.get(i);
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
