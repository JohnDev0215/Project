package f.k0.l;

import f.c0;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: Jdk9Platform.java */
/* JADX INFO: loaded from: classes.dex */
final class e extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Method f5444c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Method f5445d;

    e(Method method, Method method2) {
        this.f5444c = method;
        this.f5445d = method2;
    }

    public static e r() {
        try {
            return new e(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // f.k0.l.f
    public void g(SSLSocket sSLSocket, String str, List<c0> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> listB = f.b(list);
            this.f5444c.invoke(sSLParameters, listB.toArray(new String[listB.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to set SSL parameters", e2);
        }
    }

    @Override // f.k0.l.f
    @Nullable
    public String l(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f5445d.invoke(sSLSocket, new Object[0]);
            if (str == null) {
                return null;
            }
            if (str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError("failed to get ALPN selected protocol", e2);
        }
    }
}
