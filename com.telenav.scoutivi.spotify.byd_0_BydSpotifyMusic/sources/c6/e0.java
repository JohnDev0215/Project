package c6;

/* JADX INFO: compiled from: TlsVersion.kt */
/* JADX INFO: loaded from: classes.dex */
public enum e0 {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");

    public static final a Companion = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3707e;

    /* JADX INFO: compiled from: TlsVersion.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final e0 a(String javaName) {
            kotlin.jvm.internal.l.e(javaName, "javaName");
            int iHashCode = javaName.hashCode();
            if (iHashCode != 79201641) {
                if (iHashCode != 79923350) {
                    switch (iHashCode) {
                        case -503070503:
                            if (javaName.equals("TLSv1.1")) {
                                return e0.TLS_1_1;
                            }
                            break;
                        case -503070502:
                            if (javaName.equals("TLSv1.2")) {
                                return e0.TLS_1_2;
                            }
                            break;
                        case -503070501:
                            if (javaName.equals("TLSv1.3")) {
                                return e0.TLS_1_3;
                            }
                            break;
                    }
                } else if (javaName.equals("TLSv1")) {
                    return e0.TLS_1_0;
                }
            } else if (javaName.equals("SSLv3")) {
                return e0.SSL_3_0;
            }
            throw new IllegalArgumentException("Unexpected TLS version: " + javaName);
        }
    }

    e0(String str) {
        this.f3707e = str;
    }

    public static final e0 forJavaName(String str) {
        return Companion.a(str);
    }

    /* JADX INFO: renamed from: -deprecated_javaName, reason: not valid java name */
    public final String m0deprecated_javaName() {
        return this.f3707e;
    }

    public final String javaName() {
        return this.f3707e;
    }
}
