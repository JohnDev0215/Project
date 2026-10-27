package f.b0.j;

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
    public final Method f5273c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f5274d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f5275e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class<?> f5276f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Class<?> f5277g;

    /* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
    public static class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<String> f5278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f5280c;

        public a(List<String> list) {
            this.f5278a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = f.b0.c.f5013b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return Boolean.TRUE;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f5279b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f5278a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.f5280c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f5278a.contains(list.get(i2))) {
                    String str = (String) list.get(i2);
                    this.f5280c = str;
                    return str;
                }
            }
            String str2 = this.f5278a.get(0);
            this.f5280c = str2;
            return str2;
        }
    }

    public c(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f5273c = method;
        this.f5274d = method2;
        this.f5275e = method3;
        this.f5276f = cls;
        this.f5277g = cls2;
    }

    public static e n() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new c(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // f.b0.j.e
    public void a(SSLSocket sSLSocket) {
        try {
            this.f5275e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }

    @Override // f.b0.j.e
    public void e(SSLSocket sSLSocket, String str, List<Protocol> list) {
        try {
            this.f5273c.invoke(null, sSLSocket, Proxy.newProxyInstance(e.class.getClassLoader(), new Class[]{this.f5276f, this.f5277g}, new a(e.b(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // f.b0.j.e
    public String i(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.f5274d.invoke(null, sSLSocket));
            boolean z = aVar.f5279b;
            if (!z && aVar.f5280c == null) {
                e.h().l(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            if (z) {
                return null;
            }
            return aVar.f5280c;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            throw new AssertionError();
        }
    }
}
