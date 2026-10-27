package l9;

import c9.D;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocket;
import n7.AbstractC2000o;

/* JADX INFO: loaded from: classes.dex */
public final class j extends o {
    public final Method c;
    public final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f18870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class f18871f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Class f18872g;

    public j(Method method, Method method2, Method method3, Class cls, Class cls2) {
        this.c = method;
        this.d = method2;
        this.f18870e = method3;
        this.f18871f = cls;
        this.f18872g = cls2;
    }

    @Override // l9.o
    public final void a(SSLSocket sSLSocket) {
        try {
            this.f18870e.invoke(null, sSLSocket);
        } catch (IllegalAccessException e10) {
            throw new AssertionError("failed to remove ALPN", e10);
        } catch (InvocationTargetException e11) {
            throw new AssertionError("failed to remove ALPN", e11);
        }
    }

    @Override // l9.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((D) obj) != D.HTTP_1_0) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((D) it.next()).l);
        }
        try {
            this.c.invoke(null, sSLSocket, Proxy.newProxyInstance(o.class.getClassLoader(), new Class[]{this.f18871f, this.f18872g}, new i(arrayList2)));
        } catch (IllegalAccessException e10) {
            throw new AssertionError("failed to set ALPN", e10);
        } catch (InvocationTargetException e11) {
            throw new AssertionError("failed to set ALPN", e11);
        }
    }

    @Override // l9.o
    public final String f(SSLSocket sSLSocket) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(this.d.invoke(null, sSLSocket));
            A7.m.d(invocationHandler, "null cannot be cast to non-null type okhttp3.internal.platform.Jdk8WithJettyBootPlatform.AlpnProvider");
            i iVar = (i) invocationHandler;
            boolean z6 = iVar.f18869b;
            if (!z6 && iVar.c == null) {
                o.i("ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", 4, null);
                return null;
            }
            if (z6) {
                return null;
            }
            return iVar.c;
        } catch (IllegalAccessException e10) {
            throw new AssertionError("failed to get ALPN selected protocol", e10);
        } catch (InvocationTargetException e11) {
            throw new AssertionError("failed to get ALPN selected protocol", e11);
        }
    }
}
