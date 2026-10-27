package l6;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import c6.y;
import h5.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.l;
import m6.i;
import m6.j;
import m6.k;

/* JADX INFO: compiled from: Android10Platform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a extends h {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final boolean f7972e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0118a f7973f = new C0118a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final List<k> f7974d;

    /* JADX INFO: renamed from: l6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Android10Platform.kt */
    public static final class C0118a {
        private C0118a() {
        }

        public /* synthetic */ C0118a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final h a() {
            if (b()) {
                return new a();
            }
            return null;
        }

        public final boolean b() {
            return a.f7972e;
        }
    }

    static {
        f7972e = h.f8004c.h() && Build.VERSION.SDK_INT >= 29;
    }

    public a() {
        List listK = p.k(m6.a.f8406a.a(), new j(m6.f.f8415g.d()), new j(i.f8429b.a()), new j(m6.g.f8423b.a()));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listK) {
            if (((k) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.f7974d = arrayList;
    }

    @Override // l6.h
    public o6.c c(X509TrustManager trustManager) {
        l.e(trustManager, "trustManager");
        m6.b bVarA = m6.b.f8407d.a(trustManager);
        return bVarA != null ? bVarA : super.c(trustManager);
    }

    @Override // l6.h
    public void e(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        Object next;
        l.e(sslSocket, "sslSocket");
        l.e(protocols, "protocols");
        Iterator<T> it = this.f7974d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((k) next).a(sslSocket)) {
                    break;
                }
            }
        }
        k kVar = (k) next;
        if (kVar != null) {
            kVar.d(sslSocket, str, protocols);
        }
    }

    @Override // l6.h
    public String g(SSLSocket sslSocket) {
        Object next;
        l.e(sslSocket, "sslSocket");
        Iterator<T> it = this.f7974d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((k) next).a(sslSocket)) {
                break;
            }
        }
        k kVar = (k) next;
        if (kVar != null) {
            return kVar.b(sslSocket);
        }
        return null;
    }

    @Override // l6.h
    @SuppressLint({"NewApi"})
    public boolean i(String hostname) {
        l.e(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }
}
