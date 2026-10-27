package l6;

import c6.y;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.l;
import y5.o;

/* JADX INFO: compiled from: Jdk9Platform.kt */
/* JADX INFO: loaded from: classes.dex */
public class f extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final boolean f7997d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f7998e = new a(0 == true ? 1 : 0);

    /* JADX INFO: compiled from: Jdk9Platform.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final f a() {
            if (b()) {
                return new f();
            }
            return null;
        }

        public final boolean b() {
            return f.f7997d;
        }
    }

    static {
        String property = System.getProperty("java.specification.version");
        Integer numI = property != null ? o.i(property) : null;
        boolean z6 = true;
        if (numI == null) {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
            } catch (NoSuchMethodException unused) {
                z6 = false;
            }
        } else if (numI.intValue() < 9) {
            z6 = false;
        }
        f7997d = z6;
    }

    @Override // l6.h
    public void e(SSLSocket sslSocket, String str, List<y> protocols) {
        l.e(sslSocket, "sslSocket");
        l.e(protocols, "protocols");
        SSLParameters sslParameters = sslSocket.getSSLParameters();
        List<String> listB = h.f8004c.b(protocols);
        l.d(sslParameters, "sslParameters");
        Object[] array = listB.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
        sslParameters.setApplicationProtocols((String[]) array);
        sslSocket.setSSLParameters(sslParameters);
    }

    @Override // l6.h
    public String g(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
        try {
            String applicationProtocol = sslSocket.getApplicationProtocol();
            if (applicationProtocol == null) {
                return null;
            }
            if (applicationProtocol.hashCode() == 0) {
                if (applicationProtocol.equals("")) {
                    return null;
                }
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
