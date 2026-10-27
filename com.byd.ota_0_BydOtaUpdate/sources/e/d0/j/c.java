package e.d0.j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;

/* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f3535c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f3536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f3537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class<?> f3538f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Class<?> f3539g;

    /* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
    public static class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f3540a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3541b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f3542c;

        public a(List<String> list) {
            this.f3540a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = e.d0.c.f3337b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.TRUE;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f3541b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f3540a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.f3542c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f3540a.contains(list.get(i2))) {
                    String str = (String) list.get(i2);
                    this.f3542c = str;
                    return str;
                }
            }
            String str2 = this.f3540a.get(0);
            this.f3542c = str2;
            return str2;
        }
    }

    public c(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f3535c = method;
        this.f3536d = method2;
        this.f3537e = method3;
        this.f3538f = cls;
        this.f3539g = cls2;
    }

    public static e p() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new c(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // e.d0.j.e
    public void a(SSLSocket sSLSocket) {
        try {
            this.f3537e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // e.d0.j.e
    public void e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            this.f3535c.invoke(null, sSLSocket, Proxy.newProxyInstance(e.class.getClassLoader(), new Class[]{this.f3538f, this.f3539g}, new a(e.b(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // e.d0.j.e
    public String i(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.f3536d.invoke(null, sSLSocket));
            boolean z = aVar.f3541b;
            if (!z && aVar.f3542c == null) {
                e.h().l(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            if (z) {
                return null;
            }
            return aVar.f3542c;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }
}
