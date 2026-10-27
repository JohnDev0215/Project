package l9;

import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f18865e;
    public final ArrayList c;
    public final m9.i d;

    static {
        boolean z6 = false;
        if (n.L() && Build.VERSION.SDK_INT < 30) {
            z6 = true;
        }
        f18865e = z6;
    }

    public c() throws NoSuchMethodException {
        m9.o oVar;
        Method method;
        Method method2;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketImpl"));
            Class.forName("com.android.org.conscrypt".concat(".OpenSSLSocketFactoryImpl"));
            Class.forName("com.android.org.conscrypt".concat(".SSLParametersImpl"));
            oVar = new m9.o(cls);
        } catch (Exception e10) {
            o.f18874a.getClass();
            o.i("unable to load android socket classes", 5, e10);
            oVar = null;
        }
        ArrayList arrayListM = AbstractC1996k.M(new m9.n[]{oVar, new m9.m(m9.f.f19342f), new m9.m(m9.k.f19349a), new m9.m(m9.h.f19346a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListM) {
            if (((m9.n) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.c = arrayList;
        try {
            Class<?> cls2 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls2.getMethod("get", null);
            method2 = cls2.getMethod("open", String.class);
            method = cls2.getMethod("warnIfOpen", null);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.d = new m9.i(method3, method2, method);
    }

    @Override // l9.o
    public final h6.i b(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        m9.b bVar = x509TrustManagerExtensions != null ? new m9.b(x509TrustManager, x509TrustManagerExtensions) : null;
        return bVar != null ? bVar : new p9.a(c(x509TrustManager));
    }

    @Override // l9.o
    public final p9.d c(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.c(x509TrustManager);
        }
    }

    @Override // l9.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        Object next;
        A7.m.f(list, "protocols");
        Iterator it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((m9.n) next).a(sSLSocket)) {
                    break;
                }
            }
        }
        m9.n nVar = (m9.n) next;
        if (nVar != null) {
            nVar.d(sSLSocket, str, list);
        }
    }

    @Override // l9.o
    public final void e(Socket socket, InetSocketAddress inetSocketAddress, int i7) throws IOException {
        A7.m.f(inetSocketAddress, "address");
        socket.connect(inetSocketAddress, i7);
    }

    @Override // l9.o
    public final String f(SSLSocket sSLSocket) {
        Object next;
        Iterator it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((m9.n) next).a(sSLSocket)) {
                break;
            }
        }
        m9.n nVar = (m9.n) next;
        if (nVar != null) {
            return nVar.b(sSLSocket);
        }
        return null;
    }

    @Override // l9.o
    public final Object g() {
        m9.i iVar = this.d;
        iVar.getClass();
        Method method = iVar.f19347a;
        if (method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(null, null);
            Method method2 = iVar.f19348b;
            A7.m.c(method2);
            method2.invoke(objInvoke, "response.body().close()");
            return objInvoke;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // l9.o
    public final boolean h(String str) {
        A7.m.f(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // l9.o
    public final void j(Object obj, String str) {
        A7.m.f(str, "message");
        m9.i iVar = this.d;
        iVar.getClass();
        if (obj != null) {
            try {
                Method method = iVar.c;
                A7.m.c(method);
                method.invoke(obj, null);
                return;
            } catch (Exception unused) {
            }
        }
        o.i(str, 5, null);
    }
}
