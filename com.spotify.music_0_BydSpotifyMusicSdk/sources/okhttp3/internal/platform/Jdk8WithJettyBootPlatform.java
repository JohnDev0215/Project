package okhttp3.internal.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.internal.platform.Platform;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Jdk8WithJettyBootPlatform extends Platform {
    public static final Companion i = new Companion(0);
    public final Method d;
    public final Method e;
    public final Method f;
    public final Class g;
    public final Class h;

    public static final class AlpnProvider implements InvocationHandler {
        public final List a;
        public boolean b;
        public String c;

        public AlpnProvider(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.reflect.InvocationHandler
        public final Object invoke(Object obj, Method method, Object[] objArr) {
            v03.j(obj, "proxy");
            v03.j(method, "method");
            if (objArr == null) {
                objArr = new Object[0];
            }
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (v03.b(name, "supports") && v03.b(Boolean.TYPE, returnType)) {
                return Boolean.TRUE;
            }
            if (v03.b(name, "unsupported") && v03.b(Void.TYPE, returnType)) {
                this.b = true;
                return null;
            }
            boolean zB = v03.b(name, "protocols");
            List list = this.a;
            if (zB && objArr.length == 0) {
                return list;
            }
            if ((v03.b(name, "selectProtocol") || v03.b(name, "select")) && v03.b(String.class, returnType) && objArr.length == 1) {
                Object obj2 = objArr[0];
                if (obj2 instanceof List) {
                    v03.g(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
                    List list2 = (List) obj2;
                    int size = list2.size();
                    if (size >= 0) {
                        int i = 0;
                        while (true) {
                            Object obj3 = list2.get(i);
                            v03.g(obj3, "null cannot be cast to non-null type kotlin.String");
                            String str = (String) obj3;
                            if (!list.contains(str)) {
                                if (i == size) {
                                    break;
                                }
                                i++;
                            } else {
                                this.c = str;
                                return str;
                            }
                        }
                    }
                    String str2 = (String) list.get(0);
                    this.c = str2;
                    return str2;
                }
            }
            if ((!v03.b(name, "protocolSelected") && !v03.b(name, "selected")) || objArr.length != 1) {
                return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
            }
            Object obj4 = objArr[0];
            v03.g(obj4, "null cannot be cast to non-null type kotlin.String");
            this.c = (String) obj4;
            return null;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public Jdk8WithJettyBootPlatform(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.d = method;
        this.e = method2;
        this.f = method3;
        this.g = cls;
        this.h = cls2;
    }

    @Override // okhttp3.internal.platform.Platform
    public final void a(SSLSocket sSLSocket) {
        try {
            this.f.invoke(null, sSLSocket);
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to remove ALPN", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public final void d(SSLSocket sSLSocket, String str, List list) {
        v03.j(list, "protocols");
        Platform.a.getClass();
        try {
            this.d.invoke(null, sSLSocket, Proxy.newProxyInstance(Platform.class.getClassLoader(), new Class[]{this.g, this.h}, new AlpnProvider(Platform.Companion.a(list))));
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to set ALPN", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        }
    }

    @Override // okhttp3.internal.platform.Platform
    public final String f(SSLSocket sSLSocket) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.e.invoke(null, sSLSocket));
            v03.g(invocationHandler, "null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            AlpnProvider alpnProvider = (AlpnProvider) invocationHandler;
            boolean z = alpnProvider.b;
            if (!z && alpnProvider.c == null) {
                Platform.i(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", null);
                return null;
            }
            if (z) {
                return null;
            }
            return alpnProvider.c;
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to get ALPN selected protocol", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }
}
