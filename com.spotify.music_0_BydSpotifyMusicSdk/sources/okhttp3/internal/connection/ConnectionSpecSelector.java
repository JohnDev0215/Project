package okhttp3.internal.connection;

import java.net.UnknownServiceException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.CipherSuite;
import okhttp3.CipherSuite$Companion$ORDER_BY_NAME$1;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Util;
import p.jm1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionSpecSelector {
    public final List a;
    public int b;
    public boolean c;
    public boolean d;

    public ConnectionSpecSelector(List list) {
        v03.j(list, "connectionSpecs");
        this.a = list;
    }

    public final ConnectionSpec a(SSLSocket sSLSocket) throws UnknownServiceException {
        ConnectionSpec connectionSpec;
        int i;
        boolean z;
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        int i2 = this.b;
        List list = this.a;
        int size = list.size();
        while (true) {
            if (i2 >= size) {
                connectionSpec = null;
                break;
            }
            connectionSpec = (ConnectionSpec) list.get(i2);
            if (connectionSpec.b(sSLSocket)) {
                this.b = i2 + 1;
                break;
            }
            i2++;
        }
        if (connectionSpec == null) {
            StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
            sb.append(this.d);
            sb.append(", modes=");
            sb.append(list);
            sb.append(", supported protocols=");
            String[] enabledProtocols2 = sSLSocket.getEnabledProtocols();
            v03.h(enabledProtocols2);
            String string = Arrays.toString(enabledProtocols2);
            v03.i(string, "toString(this)");
            sb.append(string);
            throw new UnknownServiceException(sb.toString());
        }
        int i3 = this.b;
        int size2 = list.size();
        while (true) {
            i = 0;
            if (i3 >= size2) {
                z = false;
                break;
            }
            if (((ConnectionSpec) list.get(i3)).b(sSLSocket)) {
                z = true;
                break;
            }
            i3++;
        }
        this.c = z;
        boolean z2 = this.d;
        String[] strArr = connectionSpec.c;
        if (strArr != null) {
            String[] enabledCipherSuites2 = sSLSocket.getEnabledCipherSuites();
            v03.i(enabledCipherSuites2, "sslSocket.enabledCipherSuites");
            CipherSuite.b.getClass();
            enabledCipherSuites = Util.p(enabledCipherSuites2, strArr, CipherSuite.c);
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = connectionSpec.d;
        if (strArr2 != null) {
            String[] enabledProtocols3 = sSLSocket.getEnabledProtocols();
            v03.i(enabledProtocols3, "sslSocket.enabledProtocols");
            enabledProtocols = Util.p(enabledProtocols3, strArr2, jm1.c);
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        v03.i(supportedCipherSuites, "supportedCipherSuites");
        CipherSuite.b.getClass();
        CipherSuite$Companion$ORDER_BY_NAME$1 cipherSuite$Companion$ORDER_BY_NAME$1 = CipherSuite.c;
        byte[] bArr = Util.a;
        v03.j(cipherSuite$Companion$ORDER_BY_NAME$1, "comparator");
        int length = supportedCipherSuites.length;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            }
            if (cipherSuite$Companion$ORDER_BY_NAME$1.compare(supportedCipherSuites[i], "TLS_FALLBACK_SCSV") == 0) {
                break;
            }
            i++;
        }
        if (z2 && i != -1) {
            v03.i(enabledCipherSuites, "cipherSuitesIntersection");
            String str = supportedCipherSuites[i];
            v03.i(str, "supportedCipherSuites[indexOfFallbackScsv]");
            Object[] objArrCopyOf = Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length + 1);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            enabledCipherSuites = (String[]) objArrCopyOf;
            enabledCipherSuites[enabledCipherSuites.length - 1] = str;
        }
        ConnectionSpec.Builder builder = new ConnectionSpec.Builder();
        builder.a = connectionSpec.a;
        builder.b = strArr;
        builder.c = strArr2;
        builder.d = connectionSpec.b;
        v03.i(enabledCipherSuites, "cipherSuitesIntersection");
        builder.b((String[]) Arrays.copyOf(enabledCipherSuites, enabledCipherSuites.length));
        v03.i(enabledProtocols, "tlsVersionsIntersection");
        builder.e((String[]) Arrays.copyOf(enabledProtocols, enabledProtocols.length));
        ConnectionSpec connectionSpecA = builder.a();
        if (connectionSpecA.c() != null) {
            sSLSocket.setEnabledProtocols(connectionSpecA.d);
        }
        if (connectionSpecA.a() != null) {
            sSLSocket.setEnabledCipherSuites(connectionSpecA.c);
        }
        return connectionSpec;
    }
}
