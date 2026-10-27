package spi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class fg extends zk {
    public static final b i = new b(null);
    public final Method d;
    public final Method e;
    public final Method f;
    public final Class<?> g;
    public final Class<?> h;

    public static final class a implements InvocationHandler {
        public boolean a;

        @Nullable
        public String b;
        public final List<String> c;

        public a(@NotNull List<String> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            this.c = protocols;
        }

        @Override // java.lang.reflect.InvocationHandler
        @Nullable
        public final Object invoke(@NotNull Object proxy, @NotNull Method method, @Nullable Object[] objArr) throws Throwable {
            Intrinsics.checkNotNullParameter(proxy, "proxy");
            Intrinsics.checkNotNullParameter(method, "method");
            if (objArr == null) {
                objArr = new Object[0];
            }
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (Intrinsics.areEqual(name, "supports") && Intrinsics.areEqual(Boolean.TYPE, returnType)) {
                return Boolean.TRUE;
            }
            if (Intrinsics.areEqual(name, "unsupported") && Intrinsics.areEqual(Void.TYPE, returnType)) {
                this.a = true;
                return null;
            }
            if (Intrinsics.areEqual(name, "protocols")) {
                if (objArr.length == 0) {
                    return this.c;
                }
            }
            if ((!Intrinsics.areEqual(name, "selectProtocol") && !Intrinsics.areEqual(name, "select")) || !Intrinsics.areEqual(String.class, returnType) || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!Intrinsics.areEqual(name, "protocolSelected") && !Intrinsics.areEqual(name, "selected")) || objArr.length != 1) {
                    return method.invoke(this, Arrays.copyOf(objArr, objArr.length));
                }
                Object obj = objArr[0];
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                this.b = (String) obj;
                return null;
            }
            Object obj2 = objArr[0];
            Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.collections.List<*>");
            List list = (List) obj2;
            int size = list.size();
            if (size >= 0) {
                int i = 0;
                while (true) {
                    Object obj3 = list.get(i);
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.String");
                    String str = (String) obj3;
                    if (!this.c.contains(str)) {
                        if (i == size) {
                            break;
                        }
                        i++;
                    } else {
                        this.b = str;
                        return str;
                    }
                }
            }
            String str2 = this.c.get(0);
            this.b = str2;
            return str2;
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public fg(@NotNull Method putMethod, @NotNull Method getMethod, @NotNull Method removeMethod, @NotNull Class<?> clientProviderClass, @NotNull Class<?> serverProviderClass) {
        Intrinsics.checkNotNullParameter(putMethod, "putMethod");
        Intrinsics.checkNotNullParameter(getMethod, "getMethod");
        Intrinsics.checkNotNullParameter(removeMethod, "removeMethod");
        Intrinsics.checkNotNullParameter(clientProviderClass, "clientProviderClass");
        Intrinsics.checkNotNullParameter(serverProviderClass, "serverProviderClass");
        this.d = putMethod;
        this.e = getMethod;
        this.f = removeMethod;
        this.g = clientProviderClass;
        this.h = serverProviderClass;
    }

    @Override // spi.zk
    public final void a(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            this.f.invoke(null, sslSocket);
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to remove ALPN", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to remove ALPN", e2);
        }
    }

    @Override // spi.zk
    public final void d(@NotNull SSLSocket sslSocket, @Nullable String str, @NotNull List<? extends Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        try {
            this.d.invoke(null, sslSocket, Proxy.newProxyInstance(zk.class.getClassLoader(), new Class[]{this.g, this.h}, new a(zk.c.a(protocols))));
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to set ALPN", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to set ALPN", e2);
        }
    }

    @Override // spi.zk
    @Nullable
    public final String f(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.e.invoke(null, sslSocket));
            if (invocationHandler == null) {
                throw new NullPointerException("null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            }
            a aVar = (a) invocationHandler;
            boolean z = aVar.a;
            if (!z && aVar.b == null) {
                i("ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 4, null);
                return null;
            }
            if (z) {
                return null;
            }
            return aVar.b;
        } catch (IllegalAccessException e) {
            throw new AssertionError("failed to get ALPN selected protocol", e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }
}
