package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.i;
import kotlin.text.s;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.platform.android.SocketAdapter;

/* JADX INFO: compiled from: DeferredSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DeferredSocketAdapter implements SocketAdapter {
    private SocketAdapter delegate;
    private boolean initialized;
    private final String socketPackage;

    public DeferredSocketAdapter(String str) {
        i.c(str, "socketPackage");
        this.socketPackage = str;
    }

    private final synchronized SocketAdapter getDelegate(SSLSocket sSLSocket) {
        Class<?> superclass;
        if (!this.initialized) {
            try {
                superclass = sSLSocket.getClass();
            } catch (Exception e2) {
                Platform.Companion.get().log("Failed to initialize DeferredSocketAdapter " + this.socketPackage, 5, e2);
            }
            do {
                String name = superclass.getName();
                if (!i.a(name, this.socketPackage + ".OpenSSLSocketImpl")) {
                    superclass = superclass.getSuperclass();
                    i.b(superclass, "possibleClass.superclass");
                } else {
                    this.delegate = new AndroidSocketAdapter(superclass);
                    this.initialized = true;
                }
            } while (superclass != null);
            throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + sSLSocket);
        }
        return this.delegate;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        i.c(sSLSocket, "sslSocket");
        i.c(list, "protocols");
        SocketAdapter delegate = getDelegate(sSLSocket);
        if (delegate != null) {
            delegate.configureTlsExtensions(sSLSocket, str, list);
        }
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket sSLSocket) {
        i.c(sSLSocket, "sslSocket");
        SocketAdapter delegate = getDelegate(sSLSocket);
        if (delegate != null) {
            return delegate.getSelectedProtocol(sSLSocket);
        }
        return null;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return true;
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sSLSocket) {
        i.c(sSLSocket, "sslSocket");
        String name = sSLSocket.getClass().getName();
        i.b(name, "sslSocket.javaClass.name");
        return s.w(name, this.socketPackage, false, 2, null);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocketFactory(SSLSocketFactory sSLSocketFactory) {
        i.c(sSLSocketFactory, "sslSocketFactory");
        return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sSLSocketFactory);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        i.c(sSLSocketFactory, "sslSocketFactory");
        return null;
    }
}
