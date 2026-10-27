package e.d0.f;

import e.k;
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
    public final List<k> f3362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3363b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3364c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3365d;

    public b(List<k> list) {
        this.f3362a = list;
    }

    public k a(SSLSocket sSLSocket) throws IOException {
        k kVar;
        int i2 = this.f3363b;
        int size = this.f3362a.size();
        while (true) {
            if (i2 >= size) {
                kVar = null;
                break;
            }
            kVar = this.f3362a.get(i2);
            if (kVar.c(sSLSocket)) {
                this.f3363b = i2 + 1;
                break;
            }
            i2++;
        }
        if (kVar != null) {
            this.f3364c = c(sSLSocket);
            e.d0.a.f3334a.c(kVar, sSLSocket, this.f3365d);
            return kVar;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f3365d + ", modes=" + this.f3362a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    public boolean b(IOException iOException) {
        this.f3365d = true;
        if (!this.f3364c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((z && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }

    public final boolean c(SSLSocket sSLSocket) {
        for (int i2 = this.f3363b; i2 < this.f3362a.size(); i2++) {
            if (this.f3362a.get(i2).c(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
