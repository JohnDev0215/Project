package okhttp3.internal.platform;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.b;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.platform.android.Android10SocketAdapter;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.CertificateChainCleaner;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@SuppressSignatureCheck
public final class Android10Platform extends Platform {
    public static final Companion e;
    public static final boolean f;
    public final ArrayList d;

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
        e = new Companion(z ? 1 : 0);
        Platform.a.getClass();
        if (v03.b("Dalvik", System.getProperty("java.vm.name")) && Build.VERSION.SDK_INT >= 29) {
            z = true;
        }
        f = z;
    }

    public Android10Platform() {
        SocketAdapter[] socketAdapterArr = new SocketAdapter[4];
        Android10SocketAdapter.a.getClass();
        Platform.a.getClass();
        socketAdapterArr[0] = (!v03.b("Dalvik", System.getProperty("java.vm.name")) || Build.VERSION.SDK_INT < 29) ? null : new Android10SocketAdapter();
        AndroidSocketAdapter.f.getClass();
        socketAdapterArr[1] = new DeferredSocketAdapter(AndroidSocketAdapter.g);
        ConscryptSocketAdapter.a.getClass();
        socketAdapterArr[2] = new DeferredSocketAdapter(ConscryptSocketAdapter.b);
        BouncyCastleSocketAdapter.a.getClass();
        socketAdapterArr[3] = new DeferredSocketAdapter(BouncyCastleSocketAdapter.b);
        ArrayList arrayListQ0 = b.q0(socketAdapterArr);
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListQ0) {
            if (((SocketAdapter) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.d = arrayList;
    }

    @Override // okhttp3.internal.platform.Platform
    public final CertificateChainCleaner b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        AndroidCertificateChainCleaner.d.getClass();
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        AndroidCertificateChainCleaner androidCertificateChainCleaner = x509TrustManagerExtensions != null ? new AndroidCertificateChainCleaner(x509TrustManager, x509TrustManagerExtensions) : null;
        return androidCertificateChainCleaner != null ? androidCertificateChainCleaner : new BasicCertificateChainCleaner(c(x509TrustManager));
    }

    @Override // okhttp3.internal.platform.Platform
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object next;
        v03.j(list, "protocols");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((SocketAdapter) next).a(sSLSocket)) {
                    break;
                }
            }
        }
        SocketAdapter socketAdapter = (SocketAdapter) next;
        if (socketAdapter != null) {
            socketAdapter.d(sSLSocket, str, list);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public final String f(SSLSocket sSLSocket) {
        Object next;
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((SocketAdapter) next).a(sSLSocket)) {
                break;
            }
        }
        SocketAdapter socketAdapter = (SocketAdapter) next;
        if (socketAdapter != null) {
            return socketAdapter.b(sSLSocket);
        }
        return null;
    }

    @Override // okhttp3.internal.platform.Platform
    public final boolean h(String str) {
        v03.j(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
