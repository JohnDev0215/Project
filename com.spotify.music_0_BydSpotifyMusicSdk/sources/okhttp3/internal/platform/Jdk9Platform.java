package okhttp3.internal.platform;

import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.platform.Platform;
import p.mg2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class Jdk9Platform extends Platform {
    public static final Companion d;
    public static final boolean e;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        boolean z = false;
        z = false;
        d = new Companion(z ? 1 : 0);
        String property = System.getProperty("java.specification.version");
        Integer numY = property != null ? mg2.Y(property) : null;
        if (numY == null) {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]);
                z = true;
            } catch (NoSuchMethodException unused) {
            }
        } else if (numY.intValue() >= 9) {
            z = true;
        }
        e = z;
    }

    @Override // okhttp3.internal.platform.Platform
    public final void d(SSLSocket sSLSocket, String str, List list) {
        v03.j(list, "protocols");
        SSLParameters sSLParameters = sSLSocket.getSSLParameters();
        Platform.a.getClass();
        sSLParameters.setApplicationProtocols((String[]) Platform.Companion.a(list).toArray(new String[0]));
        sSLSocket.setSSLParameters(sSLParameters);
    }

    @Override // okhttp3.internal.platform.Platform
    public final String f(SSLSocket sSLSocket) {
        try {
            String applicationProtocol = sSLSocket.getApplicationProtocol();
            if (applicationProtocol == null) {
                return null;
            }
            if (v03.b(applicationProtocol, "")) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
}
