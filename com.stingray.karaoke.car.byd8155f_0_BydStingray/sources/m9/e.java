package m9;

import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
public final class e implements l {
    @Override // m9.l
    public final boolean a(SSLSocket sSLSocket) {
        return Q8.o.z0(sSLSocket.getClass().getName(), "com.google.android.gms.org.conscrypt.", false);
    }

    @Override // m9.l
    public final n b(SSLSocket sSLSocket) {
        Class<?> cls = sSLSocket.getClass();
        Class<?> superclass = cls;
        while (!superclass.getSimpleName().equals("OpenSSLSocketImpl")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        return new f(superclass);
    }
}
