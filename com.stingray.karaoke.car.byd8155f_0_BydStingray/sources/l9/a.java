package l9;

import android.net.http.X509TrustManagerExtensions;
import android.security.NetworkSecurityPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class a extends o {
    public static final boolean d = n.L();
    public final ArrayList c;

    public a() {
        ArrayList arrayListM = AbstractC1996k.M(new m9.n[]{n.L() ? new m9.a() : null, new m9.m(m9.f.f19342f), new m9.m(m9.k.f19349a), new m9.m(m9.h.f19346a)});
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListM) {
            if (((m9.n) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.c = arrayList;
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
    public final boolean h(String str) {
        A7.m.f(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }
}
