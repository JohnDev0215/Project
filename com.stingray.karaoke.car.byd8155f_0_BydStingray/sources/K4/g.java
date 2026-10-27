package k4;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class g implements e4.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f18190b;
    public final URL c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f18191e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public URL f18192f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile byte[] f18193g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f18194h;

    public g(URL url) {
        k kVar = h.f18195a;
        A4.h.c(url, "Argument must not be null");
        this.c = url;
        this.d = null;
        A4.h.c(kVar, "Argument must not be null");
        this.f18190b = kVar;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        if (this.f18193g == null) {
            this.f18193g = c().getBytes(e4.e.f14430a);
        }
        messageDigest.update(this.f18193g);
    }

    public final String c() {
        String str = this.d;
        if (str != null) {
            return str;
        }
        URL url = this.c;
        A4.h.c(url, "Argument must not be null");
        return url.toString();
    }

    public final URL d() {
        if (this.f18192f == null) {
            if (TextUtils.isEmpty(this.f18191e)) {
                String string = this.d;
                if (TextUtils.isEmpty(string)) {
                    URL url = this.c;
                    A4.h.c(url, "Argument must not be null");
                    string = url.toString();
                }
                this.f18191e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
            }
            this.f18192f = new URL(this.f18191e);
        }
        return this.f18192f;
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return c().equals(gVar.c()) && this.f18190b.equals(gVar.f18190b);
    }

    @Override // e4.e
    public final int hashCode() {
        if (this.f18194h == 0) {
            int iHashCode = c().hashCode();
            this.f18194h = iHashCode;
            this.f18194h = this.f18190b.f18198b.hashCode() + (iHashCode * 31);
        }
        return this.f18194h;
    }

    public final String toString() {
        return c();
    }

    public g(String str) {
        k kVar = h.f18195a;
        this.c = null;
        if (!TextUtils.isEmpty(str)) {
            this.d = str;
            A4.h.c(kVar, "Argument must not be null");
            this.f18190b = kVar;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }
}
