package b.f.j;

import android.util.Base64;
import b.f.l.f;
import java.util.List;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1677b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1678c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f1679d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1680e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1681f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) f.b(str);
        this.f1676a = str4;
        String str5 = (String) f.b(str2);
        this.f1677b = str5;
        String str6 = (String) f.b(str3);
        this.f1678c = str6;
        this.f1679d = (List) f.b(list);
        this.f1680e = 0;
        this.f1681f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f1679d;
    }

    public int b() {
        return this.f1680e;
    }

    public String c() {
        return this.f1681f;
    }

    public String d() {
        return this.f1676a;
    }

    public String e() {
        return this.f1677b;
    }

    public String f() {
        return this.f1678c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f1676a + ", mProviderPackage: " + this.f1677b + ", mQuery: " + this.f1678c + ", mCertificates:");
        for (int i2 = 0; i2 < this.f1679d.size(); i2++) {
            sb.append(" [");
            List<byte[]> list = this.f1679d.get(i2);
            for (int i3 = 0; i3 < list.size(); i3++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i3), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f1680e);
        return sb.toString();
    }
}
