package m9;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public class f implements n {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f19342f = new e();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f19343a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f19344b;
    public final Method c;
    public final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f19345e;

    public f(Class cls) throws NoSuchMethodException {
        this.f19343a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        A7.m.e(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.f19344b = declaredMethod;
        this.c = cls.getMethod("setHostname", String.class);
        this.d = cls.getMethod("getAlpnSelectedProtocol", null);
        this.f19345e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // m9.n
    public final boolean a(SSLSocket sSLSocket) {
        return this.f19343a.isInstance(sSLSocket);
    }

    @Override // m9.n
    public final String b(SSLSocket sSLSocket) {
        if (!this.f19343a.isInstance(sSLSocket)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.d.invoke(sSLSocket, null);
            if (bArr != null) {
                return new String(bArr, Q8.a.f7211a);
            }
            return null;
        } catch (IllegalAccessException e10) {
            throw new AssertionError(e10);
        } catch (InvocationTargetException e11) {
            Throwable cause = e11.getCause();
            if ((cause instanceof NullPointerException) && A7.m.a(((NullPointerException) cause).getMessage(), "ssl == null")) {
                return null;
            }
            throw new AssertionError(e11);
        }
    }

    @Override // m9.n
    public final boolean c() {
        boolean z6 = l9.c.f18865e;
        return l9.c.f18865e;
    }

    @Override // m9.n
    public final void d(SSLSocket sSLSocket, String str, List list) {
        A7.m.f(list, "protocols");
        if (this.f19343a.isInstance(sSLSocket)) {
            try {
                this.f19344b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null) {
                    this.c.invoke(sSLSocket, str);
                }
                Method method = this.f19345e;
                l9.o oVar = l9.o.f18874a;
                method.invoke(sSLSocket, l9.n.I(list));
            } catch (IllegalAccessException e10) {
                throw new AssertionError(e10);
            } catch (InvocationTargetException e11) {
                throw new AssertionError(e11);
            }
        }
    }
}
