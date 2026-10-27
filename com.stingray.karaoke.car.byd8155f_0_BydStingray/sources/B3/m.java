package B3;

import c9.y;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import n7.AbstractC1985B;
import r9.InterfaceC2321k;

/* JADX INFO: loaded from: classes.dex */
public abstract class m implements Closeable {
    public abstract void b(byte[] bArr);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        d9.b.c(v());
    }

    public InputStream g() {
        return v().J();
    }

    public abstract long i();

    public abstract y j();

    public abstract void k(A2.g gVar);

    public abstract C7.a m();

    public abstract void o(int i7);

    public abstract int q();

    public abstract InterfaceC2321k v();

    public String y() throws IllegalAccessException, IOException, InvocationTargetException {
        Charset charsetA;
        InterfaceC2321k interfaceC2321kV = v();
        try {
            y yVarJ = j();
            if (yVarJ == null || (charsetA = yVarJ.a(Q8.a.f7211a)) == null) {
                charsetA = Q8.a.f7211a;
            }
            String strH = interfaceC2321kV.H(d9.b.s(interfaceC2321kV, charsetA));
            AbstractC1985B.c(interfaceC2321kV, null);
            return strH;
        } finally {
        }
    }
}
