package f.k0.h;

import f.o;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpecSelector.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<o> f5161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f5162b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5163c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5164d;

    c(List<o> list) {
        this.f5161a = list;
    }

    private boolean c(SSLSocket sSLSocket) {
        for (int i2 = this.f5162b; i2 < this.f5161a.size(); i2++) {
            if (this.f5161a.get(i2).c(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    o a(SSLSocket sSLSocket) throws UnknownServiceException {
        o oVar;
        int i2 = this.f5162b;
        int size = this.f5161a.size();
        while (true) {
            if (i2 >= size) {
                oVar = null;
                break;
            }
            oVar = this.f5161a.get(i2);
            if (oVar.c(sSLSocket)) {
                this.f5162b = i2 + 1;
                break;
            }
            i2++;
        }
        if (oVar != null) {
            this.f5163c = c(sSLSocket);
            f.k0.c.f5131a.c(oVar, sSLSocket, this.f5164d);
            return oVar;
        }
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f5164d + ", modes=" + this.f5161a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
    }

    boolean b(IOException iOException) {
        this.f5164d = true;
        if (!this.f5163c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        return iOException instanceof SSLException;
    }
}
