package m6;

import android.annotation.SuppressLint;
import android.net.ssl.SSLSockets;
import android.os.Build;
import c6.y;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: Android10SocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public final class a implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0129a f8406a = new C0129a(null);

    /* JADX INFO: renamed from: m6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Android10SocketAdapter.kt */
    public static final class C0129a {
        private C0129a() {
        }

        public /* synthetic */ C0129a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final k a() {
            if (b()) {
                return new a();
            }
            return null;
        }

        public final boolean b() {
            return l6.h.f8004c.h() && Build.VERSION.SDK_INT >= 29;
        }
    }

    @Override // m6.k
    public boolean a(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        return SSLSockets.isSupportedSocket(sslSocket);
    }

    @Override // m6.k
    @SuppressLint({"NewApi"})
    public String b(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        String applicationProtocol = sslSocket.getApplicationProtocol();
        if (applicationProtocol == null || (applicationProtocol.hashCode() == 0 && applicationProtocol.equals(""))) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // m6.k
    public boolean c() {
        return f8406a.b();
    }

    @Override // m6.k
    @SuppressLint({"NewApi"})
    public void d(SSLSocket sslSocket, String str, List<? extends y> protocols) throws IOException {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        try {
            SSLSockets.setUseSessionTickets(sslSocket, true);
            SSLParameters sslParameters = sslSocket.getSSLParameters();
            kotlin.jvm.internal.l.d(sslParameters, "sslParameters");
            Object[] array = l6.h.f8004c.b(protocols).toArray(new String[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            sslParameters.setApplicationProtocols((String[]) array);
            sslSocket.setSSLParameters(sslParameters);
        } catch (IllegalArgumentException e7) {
            throw new IOException("Android internal error", e7);
        }
    }
}
