package h6;

import c6.l;
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

/* JADX INFO: compiled from: ConnectionSpecSelector.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f6659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f6660b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f6661c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<l> f6662d;

    public b(List<l> connectionSpecs) {
        kotlin.jvm.internal.l.e(connectionSpecs, "connectionSpecs");
        this.f6662d = connectionSpecs;
    }

    private final boolean c(SSLSocket sSLSocket) {
        int size = this.f6662d.size();
        for (int i7 = this.f6659a; i7 < size; i7++) {
            if (this.f6662d.get(i7).e(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public final l a(SSLSocket sslSocket) throws UnknownServiceException, CloneNotSupportedException {
        l lVar;
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        int i7 = this.f6659a;
        int size = this.f6662d.size();
        while (true) {
            if (i7 >= size) {
                lVar = null;
                break;
            }
            lVar = this.f6662d.get(i7);
            if (lVar.e(sslSocket)) {
                this.f6659a = i7 + 1;
                break;
            }
            i7++;
        }
        if (lVar != null) {
            this.f6660b = c(sslSocket);
            lVar.c(sslSocket, this.f6661c);
            return lVar;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find acceptable protocols. isFallback=");
        sb.append(this.f6661c);
        sb.append(',');
        sb.append(" modes=");
        sb.append(this.f6662d);
        sb.append(',');
        sb.append(" supported protocols=");
        String[] enabledProtocols = sslSocket.getEnabledProtocols();
        kotlin.jvm.internal.l.c(enabledProtocols);
        String string = Arrays.toString(enabledProtocols);
        kotlin.jvm.internal.l.d(string, "java.util.Arrays.toString(this)");
        sb.append(string);
        throw new UnknownServiceException(sb.toString());
    }

    public final boolean b(IOException e7) {
        kotlin.jvm.internal.l.e(e7, "e");
        this.f6661c = true;
        return (!this.f6660b || (e7 instanceof ProtocolException) || (e7 instanceof InterruptedIOException) || ((e7 instanceof SSLHandshakeException) && (e7.getCause() instanceof CertificateException)) || (e7 instanceof SSLPeerUnverifiedException) || !(e7 instanceof SSLException)) ? false : true;
    }
}
