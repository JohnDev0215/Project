package f.k0.l;

import f.c0;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: Jdk8WithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes.dex */
class d extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f5436c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Method f5437d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Method f5438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Class<?> f5439f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Class<?> f5440g;

    /* JADX INFO: compiled from: Jdk8WithJettyBootPlatform.java */
    private static class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<String> f5441a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f5442b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f5443c;

        a(List<String> list) {
            this.f5441a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = f.k0.e.f5134b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.TRUE;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f5442b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f5441a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.f5443c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str = (String) list.get(i2);
                if (this.f5441a.contains(str)) {
                    this.f5443c = str;
                    return str;
                }
            }
            String str2 = this.f5441a.get(0);
            this.f5443c = str2;
            return str2;
        }
    }

    d(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f5436c = method;
        this.f5437d = method2;
        this.f5438e = method3;
        this.f5439f = cls;
        this.f5440g = cls2;
    }

    public static f r() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN", true, null);
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider", true, null);
            return new d(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider", true, null), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider", true, null));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // f.k0.l.f
    public void a(SSLSocket sSLSocket) {
        try {
            this.f5438e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        }
    }

    @Override // f.k0.l.f
    public void g(SSLSocket sSLSocket, String str, List<c0> list) {
        try {
            this.f5436c.invoke(null, sSLSocket, Proxy.newProxyInstance(f.class.getClassLoader(), new Class[]{this.f5439f, this.f5440g}, new a(f.b(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        }
    }

    @Override // f.k0.l.f
    @Nullable
    public String l(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.f5437d.invoke(null, sSLSocket));
            boolean z = aVar.f5442b;
            if (!z && aVar.f5443c == null) {
                f.j().p(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            if (z) {
                return null;
            }
            return aVar.f5443c;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }
}
