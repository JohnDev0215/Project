package spi;

import android.os.Build;
import android.security.NetworkSecurityPolicy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class a0 extends zk {
    public static final boolean f;
    public static final a g = new a(null);
    public final List<up> d;
    public final q3 e;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class b implements yt {
        public final X509TrustManager a;
        public final Method b;

        public b(@NotNull X509TrustManager trustManager, @NotNull Method findByIssuerAndSignatureMethod) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            Intrinsics.checkNotNullParameter(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
            this.a = trustManager;
            this.b = findByIssuerAndSignatureMethod;
        }

        @Override // spi.yt
        @Nullable
        public final X509Certificate a(@NotNull X509Certificate cert) {
            Intrinsics.checkNotNullParameter(cert, "cert");
            try {
                Object objInvoke = this.b.invoke(this.a, cert);
                if (objInvoke != null) {
                    return ((TrustAnchor) objInvoke).getTrustedCert();
                }
                throw new NullPointerException("null cannot be cast to non-null type java.security.cert.TrustAnchor");
            } catch (IllegalAccessException e) {
                throw new AssertionError("unable to get issues and signature", e);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b);
        }

        public final int hashCode() {
            X509TrustManager x509TrustManager = this.a;
            int iHashCode = (x509TrustManager != null ? x509TrustManager.hashCode() : 0) * 31;
            Method method = this.b;
            return iHashCode + (method != null ? method.hashCode() : 0);
        }

        @NotNull
        public final String toString() {
            StringBuilder sbK = e.k("CustomTrustRootIndex(trustManager=");
            sbK.append(this.a);
            sbK.append(", findByIssuerAndSignatureMethod=");
            sbK.append(this.b);
            sbK.append(")");
            return sbK.toString();
        }
    }

    static {
        boolean z = false;
        if (zk.c.c() && Build.VERSION.SDK_INT < 30) {
            z = true;
        }
        f = z;
    }

    public a0() throws NoSuchMethodException {
        as asVar;
        Method method;
        Method method2;
        up[] upVarArr = new up[4];
        Objects.requireNonNull(as.h);
        Intrinsics.checkNotNullParameter("com.android.org.conscrypt", "packageName");
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
            Class<?> cls2 = Class.forName("com.android.org.conscrypt.OpenSSLSocketFactoryImpl");
            Class<?> paramsClass = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            Intrinsics.checkNotNullExpressionValue(paramsClass, "paramsClass");
            asVar = new as(cls, cls2, paramsClass);
        } catch (Exception e) {
            Objects.requireNonNull(zk.c);
            zk.a.i("unable to load android socket classes", 5, e);
            asVar = null;
        }
        upVarArr[0] = asVar;
        Objects.requireNonNull(c0.g);
        upVarArr[1] = new c6(c0.f);
        Objects.requireNonNull(i4.b);
        upVarArr[2] = new c6(i4.a);
        Objects.requireNonNull(d2.b);
        upVarArr[3] = new c6(d2.a);
        List listListOfNotNull = CollectionsKt.listOfNotNull((Object[]) upVarArr);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOfNotNull) {
            if (((up) obj).isSupported()) {
                arrayList.add(obj);
            }
        }
        this.d = arrayList;
        Objects.requireNonNull(q3.d);
        try {
            Class<?> cls3 = Class.forName("dalvik.system.CloseGuard");
            Method method4 = cls3.getMethod("get", new Class[0]);
            method2 = cls3.getMethod("open", String.class);
            method = cls3.getMethod("warnIfOpen", new Class[0]);
            method3 = method4;
        } catch (Exception unused) {
            method = null;
            method2 = null;
        }
        this.e = new q3(method3, method2, method);
    }

    @Override // spi.zk
    @NotNull
    public final l3 b(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        x xVarA = x.d.a(trustManager);
        return xVarA != null ? xVarA : super.b(trustManager);
    }

    @Override // spi.zk
    @NotNull
    public final yt c(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        try {
            Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            Intrinsics.checkNotNullExpressionValue(method, "method");
            method.setAccessible(true);
            return new b(trustManager, method);
        } catch (NoSuchMethodException unused) {
            return super.c(trustManager);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<spi.up>] */
    @Override // spi.zk
    public final void d(@NotNull SSLSocket sslSocket, @Nullable String str, @NotNull List<Protocol> protocols) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((up) next).a(sslSocket)) {
                    break;
                }
            }
        }
        up upVar = (up) next;
        if (upVar != null) {
            upVar.c(sslSocket, str, protocols);
        }
    }

    @Override // spi.zk
    public final void e(@NotNull Socket socket, @NotNull InetSocketAddress address, int i) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(address, "address");
        try {
            socket.connect(address, i);
        } catch (ClassCastException e) {
            throw e;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.ArrayList, java.util.List<spi.up>] */
    @Override // spi.zk
    @Nullable
    public final String f(@NotNull SSLSocket sslSocket) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Iterator it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((up) next).a(sslSocket)) {
                break;
            }
        }
        up upVar = (up) next;
        if (upVar != null) {
            return upVar.b(sslSocket);
        }
        return null;
    }

    @Override // spi.zk
    @Nullable
    public final Object g() {
        Intrinsics.checkNotNullParameter("response.body().close()", "closer");
        q3 q3Var = this.e;
        Objects.requireNonNull(q3Var);
        Intrinsics.checkNotNullParameter("response.body().close()", "closer");
        Method method = q3Var.a;
        if (method == null) {
            return null;
        }
        try {
            Object objInvoke = method.invoke(null, new Object[0]);
            Method method2 = q3Var.b;
            Intrinsics.checkNotNull(method2);
            method2.invoke(objInvoke, "response.body().close()");
            return objInvoke;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // spi.zk
    public final boolean h(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }

    @Override // spi.zk
    public final void k(@NotNull String message, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(message, "message");
        q3 q3Var = this.e;
        Objects.requireNonNull(q3Var);
        boolean z = false;
        if (obj != null) {
            try {
                Method method = q3Var.c;
                Intrinsics.checkNotNull(method);
                method.invoke(obj, new Object[0]);
                z = true;
            } catch (Exception unused) {
            }
        }
        if (z) {
            return;
        }
        zk.j(this, message, 5, null, 4, null);
    }
}
