package u1;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: GlideUrl.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements n1.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f9585b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final URL f9586c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9587d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f9588e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private URL f9589f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile byte[] f9590g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f9591h;

    public g(URL url) {
        this(url, h.f9593b);
    }

    private byte[] d() {
        if (this.f9590g == null) {
            this.f9590g = c().getBytes(n1.f.f8504a);
        }
        return this.f9590g;
    }

    private String f() {
        if (TextUtils.isEmpty(this.f9588e)) {
            String string = this.f9587d;
            if (TextUtils.isEmpty(string)) {
                string = ((URL) k2.j.d(this.f9586c)).toString();
            }
            this.f9588e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.f9588e;
    }

    private URL g() {
        if (this.f9589f == null) {
            this.f9589f = new URL(f());
        }
        return this.f9589f;
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        messageDigest.update(d());
    }

    public String c() {
        String str = this.f9587d;
        return str != null ? str : ((URL) k2.j.d(this.f9586c)).toString();
    }

    public Map<String, String> e() {
        return this.f9585b.a();
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return c().equals(gVar.c()) && this.f9585b.equals(gVar.f9585b);
    }

    public String h() {
        return f();
    }

    @Override // n1.f
    public int hashCode() {
        if (this.f9591h == 0) {
            int iHashCode = c().hashCode();
            this.f9591h = iHashCode;
            this.f9591h = (iHashCode * 31) + this.f9585b.hashCode();
        }
        return this.f9591h;
    }

    public URL i() {
        return g();
    }

    public String toString() {
        return c();
    }

    public g(String str) {
        this(str, h.f9593b);
    }

    public g(URL url, h hVar) {
        this.f9586c = (URL) k2.j.d(url);
        this.f9587d = null;
        this.f9585b = (h) k2.j.d(hVar);
    }

    public g(String str, h hVar) {
        this.f9586c = null;
        this.f9587d = k2.j.b(str);
        this.f9585b = (h) k2.j.d(hVar);
    }
}
