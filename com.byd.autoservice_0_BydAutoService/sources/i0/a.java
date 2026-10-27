package i0;

import android.util.Base64;
import java.util.List;
import k0.h;

/* JADX INFO: compiled from: FontRequest.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3224a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3225b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3226c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<List<byte[]>> f3227d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3228e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f3229f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        String str4 = (String) h.c(str);
        this.f3224a = str4;
        String str5 = (String) h.c(str2);
        this.f3225b = str5;
        String str6 = (String) h.c(str3);
        this.f3226c = str6;
        this.f3227d = (List) h.c(list);
        this.f3228e = 0;
        this.f3229f = str4 + "-" + str5 + "-" + str6;
    }

    public List<List<byte[]>> a() {
        return this.f3227d;
    }

    public int b() {
        return this.f3228e;
    }

    public String c() {
        return this.f3229f;
    }

    public String d() {
        return this.f3224a;
    }

    public String e() {
        return this.f3225b;
    }

    public String f() {
        return this.f3226c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f3224a + ", mProviderPackage: " + this.f3225b + ", mQuery: " + this.f3226c + ", mCertificates:");
        for (int i3 = 0; i3 < this.f3227d.size(); i3++) {
            sb.append(" [");
            List<byte[]> list = this.f3227d.get(i3);
            for (int i4 = 0; i4 < list.size(); i4++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i4), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f3228e);
        return sb.toString();
    }
}
