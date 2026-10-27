package f.b0.f;

import f.k;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpecSelector.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<k> f5041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5042b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5044d;

    public b(List<k> list) {
        this.f5041a = list;
    }

    public k a(SSLSocket sSLSocket) throws IOException {
        k kVar;
        int i2 = this.f5042b;
        int size = this.f5041a.size();
        while (true) {
            if (i2 >= size) {
                kVar = null;
                break;
            }
            kVar = this.f5041a.get(i2);
            if (kVar.c(sSLSocket)) {
                this.f5042b = i2 + 1;
                break;
            }
            i2++;
        }
        if (kVar != null) {
            this.f5043c = c(sSLSocket);
            f.b0.a.f5010a.c(kVar, sSLSocket, this.f5044d);
            return kVar;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f5044d + ", modes=" + this.f5041a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public boolean b(IOException iOException) {
        this.f5044d = true;
        if (!this.f5043c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }

    public final boolean c(SSLSocket sSLSocket) {
        for (int i2 = this.f5042b; i2 < this.f5041a.size(); i2++) {
            if (this.f5041a.get(i2).c(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
