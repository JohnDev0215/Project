package m6;

import c6.y;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.net.ssl.SSLSocket;
import m6.j;
import y5.p;

/* JADX INFO: compiled from: AndroidSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public class f implements k {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final j.a f8414f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f8415g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f8416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Method f8417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f8418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Method f8419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Class<? super SSLSocket> f8420e;

    /* JADX INFO: compiled from: AndroidSocketAdapter.kt */
    public static final class a {

        /* JADX INFO: renamed from: m6.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AndroidSocketAdapter.kt */
        public static final class C0130a implements j.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f8421a;

            C0130a(String str) {
                this.f8421a = str;
            }

            @Override // m6.j.a
            public boolean a(SSLSocket sslSocket) {
                kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
                String name = sslSocket.getClass().getName();
                kotlin.jvm.internal.l.d(name, "sslSocket.javaClass.name");
                return p.A(name, this.f8421a + '.', false, 2, null);
            }

            @Override // m6.j.a
            public k b(SSLSocket sslSocket) {
                kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
                return f.f8415g.b(sslSocket.getClass());
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final f b(Class<? super SSLSocket> cls) {
            Class<? super SSLSocket> superclass = cls;
            while (superclass != null && (!kotlin.jvm.internal.l.a(superclass.getSimpleName(), "OpenSSLSocketImpl"))) {
                superclass = superclass.getSuperclass();
                if (superclass == null) {
                    throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
                }
            }
            kotlin.jvm.internal.l.c(superclass);
            return new f(superclass);
        }

        public final j.a c(String packageName) {
            kotlin.jvm.internal.l.e(packageName, "packageName");
            return new C0130a(packageName);
        }

        public final j.a d() {
            return f.f8414f;
        }
    }

    static {
        a aVar = new a(null);
        f8415g = aVar;
        f8414f = aVar.c("com.google.android.gms.org.conscrypt");
    }

    public f(Class<? super SSLSocket> sslSocketClass) throws NoSuchMethodException {
        kotlin.jvm.internal.l.e(sslSocketClass, "sslSocketClass");
        this.f8420e = sslSocketClass;
        Method declaredMethod = sslSocketClass.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        kotlin.jvm.internal.l.d(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.f8416a = declaredMethod;
        this.f8417b = sslSocketClass.getMethod("setHostname", String.class);
        this.f8418c = sslSocketClass.getMethod("getAlpnSelectedProtocol", new Class[0]);
        this.f8419d = sslSocketClass.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // m6.k
    public boolean a(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        return this.f8420e.isInstance(sslSocket);
    }

    @Override // m6.k
    public String b(SSLSocket sslSocket) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        if (!a(sslSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.f8418c.invoke(sslSocket, new Object[0]);
            if (bArr == null) {
                return null;
            }
            Charset charset = StandardCharsets.UTF_8;
            kotlin.jvm.internal.l.d(charset, "StandardCharsets.UTF_8");
            return new String(bArr, charset);
        } catch (IllegalAccessException e7) {
            throw new AssertionError(e7);
        } catch (NullPointerException e8) {
            if (kotlin.jvm.internal.l.a(e8.getMessage(), "ssl == null")) {
                return null;
            }
            throw e8;
        } catch (InvocationTargetException e9) {
            throw new AssertionError(e9);
        }
    }

    @Override // m6.k
    public boolean c() {
        return l6.b.f7976g.b();
    }

    @Override // m6.k
    public void d(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        kotlin.jvm.internal.l.e(sslSocket, "sslSocket");
        kotlin.jvm.internal.l.e(protocols, "protocols");
        if (a(sslSocket)) {
            try {
                this.f8416a.invoke(sslSocket, Boolean.TRUE);
                if (str != null) {
                    this.f8417b.invoke(sslSocket, str);
                }
                this.f8419d.invoke(sslSocket, l6.h.f8004c.c(protocols));
            } catch (IllegalAccessException e7) {
                throw new AssertionError(e7);
            } catch (InvocationTargetException e8) {
                throw new AssertionError(e8);
            }
        }
    }
}
