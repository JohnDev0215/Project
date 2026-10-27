package defpackage;

import android.net.Uri;

/* JADX INFO: compiled from: Origin.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ft1 {
    public final Uri a;

    public ft1(Uri uri) {
        this.a = uri;
    }

    public static ft1 a(Uri uri) {
        if (uri != null && uri.getScheme() != null && uri.getAuthority() != null) {
            String scheme = uri.getScheme();
            if (!scheme.equals("http") && !scheme.equals("https")) {
                return null;
            }
            int port = uri.getPort();
            if (scheme.equals("http") && port == 80) {
                port = -1;
            }
            if (scheme.equals("https") && port == 443) {
                port = -1;
            }
            String host = uri.getHost();
            if (port != -1) {
                host = host + ":" + port;
            }
            try {
                return new ft1(uri.normalizeScheme().buildUpon().opaquePart("").fragment("").path("").encodedAuthority(host).clearQuery().build());
            } catch (UnsupportedOperationException unused) {
            }
        }
        return null;
    }

    public static ft1 b(String str) {
        return a(Uri.parse(str));
    }

    public static ft1 c(String str) {
        Uri uri = Uri.parse(str);
        ft1 ft1VarA = a(uri);
        if (ft1VarA != null) {
            return ft1VarA;
        }
        throw new IllegalArgumentException("Could not parse: " + uri);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ft1.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ft1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return this.a.toString();
    }
}
