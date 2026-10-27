package l6;

import c6.y;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: Jdk8WithJettyBootPlatform.kt */
/* JADX INFO: loaded from: classes.dex */
public final class e extends h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f7988i = new b(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Method f7989d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Method f7990e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Method f7991f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Class<?> f7992g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Class<?> f7993h;

    /* JADX INFO: compiled from: Jdk8WithJettyBootPlatform.kt */
    private static final class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f7994a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f7995b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final List<String> f7996c;

        public a(List<String> protocols) {
            l.e(protocols, "protocols");
            this.f7996c = protocols;
        }

        public final String a() {
            return this.f7995b;
        }

        public final boolean b() {
            return this.f7994a;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] objArr) {
            l.e(proxy, "proxy");
            l.e(method, "method");
            if (objArr == null) {
                objArr = new Object[0];
            }
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (l.a(name, "supports") && l.a(Boolean.TYPE, returnType)) {
                return Boolean.TRUE;
            }
            if (l.a(name, "unsupported") && l.a(Void.TYPE, returnType)) {
                this.f7994a = true;
                return null;
            }
            if (l.a(name, "protocols")) {
                if (objArr.length == 0) {
                    return this.f7996c;
                }
            }
            if ((!l.a(name, "selectProtocol") && !l.a(name, "select")) || !l.a(String.class, returnType) || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!l.a(name, "protocolSelected") && !l.a(name, "selected")) || objArr.length != 1) {
                    return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
                }
                Object obj = objArr[0];
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                this.f7995b = (String) obj;
                return null;
            }
            Object obj2 = objArr[0];
            Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List list = (List) obj2;
            int size = list.size();
            if (size >= 0) {
                int i7 = 0;
                while (true) {
                    Object obj3 = list.get(i7);
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.String");
                    String str = (String) obj3;
                    if (!this.f7996c.contains(str)) {
                        if (i7 == size) {
                            break;
                        }
                        i7++;
                    } else {
                        this.f7995b = str;
                        return str;
                    }
                }
            }
            String str2 = this.f7996c.get(0);
            this.f7995b = str2;
            return str2;
        }
    }

    /* JADX INFO: compiled from: Jdk8WithJettyBootPlatform.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final h a() {
            String jvmVersion = System.getProperty("java.specification.version", "unknown");
            try {
                l.d(jvmVersion, "jvmVersion");
                if (Integer.parseInt(jvmVersion) >= 9) {
                    return null;
                }
            } catch (NumberFormatException unused) {
            }
            try {
                Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
                Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider", true, null);
                Class<?> clientProviderClass = Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider", true, null);
                Class<?> serverProviderClass = Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider", true, null);
                Method putMethod = cls.getMethod("put", SSLSocket.class, cls2);
                Method getMethod = cls.getMethod("get", SSLSocket.class);
                Method removeMethod = cls.getMethod("remove", SSLSocket.class);
                l.d(putMethod, "putMethod");
                l.d(getMethod, "getMethod");
                l.d(removeMethod, "removeMethod");
                l.d(clientProviderClass, "clientProviderClass");
                l.d(serverProviderClass, "serverProviderClass");
                return new e(putMethod, getMethod, removeMethod, clientProviderClass, serverProviderClass);
            } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                return null;
            }
        }
    }

    public e(Method putMethod, Method getMethod, Method removeMethod, Class<?> clientProviderClass, Class<?> serverProviderClass) {
        l.e(putMethod, "putMethod");
        l.e(getMethod, "getMethod");
        l.e(removeMethod, "removeMethod");
        l.e(clientProviderClass, "clientProviderClass");
        l.e(serverProviderClass, "serverProviderClass");
        this.f7989d = putMethod;
        this.f7990e = getMethod;
        this.f7991f = removeMethod;
        this.f7992g = clientProviderClass;
        this.f7993h = serverProviderClass;
    }

    @Override // l6.h
    public void b(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
        try {
            this.f7991f.invoke(null, sslSocket);
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to remove ALPN", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to remove ALPN", e8);
        }
    }

    @Override // l6.h
    public void e(SSLSocket sslSocket, String str, List<? extends y> protocols) {
        l.e(sslSocket, "sslSocket");
        l.e(protocols, "protocols");
        try {
            this.f7989d.invoke(null, sslSocket, Proxy.newProxyInstance(h.class.getClassLoader(), new Class[]{this.f7992g, this.f7993h}, new a(h.f8004c.b(protocols))));
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to set ALPN", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to set ALPN", e8);
        }
    }

    @Override // l6.h
    public String g(SSLSocket sslSocket) {
        l.e(sslSocket, "sslSocket");
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.f7990e.invoke(null, sslSocket));
            if (invocationHandler == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            }
            a aVar = (a) invocationHandler;
            if (!aVar.b() && aVar.a() == null) {
                h.k(this, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 0, null, 6, null);
                return null;
            }
            if (aVar.b()) {
                return null;
            }
            return aVar.a();
        } catch (IllegalAccessException e7) {
            throw new AssertionError("failed to get ALPN selected protocol", e7);
        } catch (InvocationTargetException e8) {
            throw new AssertionError("failed to get ALPN selected protocol", e8);
        }
    }
}
