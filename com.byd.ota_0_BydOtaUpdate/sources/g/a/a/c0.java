package g.a.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3783d;
    public final n[] s;

    public class a implements Enumeration {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3784a = 0;

        public a() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f3784a < c0.this.s.length;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            n[] nVarArr = c0.this.s;
            int i2 = this.f3784a;
            this.f3784a = i2 + 1;
            return nVarArr[i2];
        }
    }

    public c0(byte[] bArr) {
        this(bArr, 1000);
    }

    public c0(byte[] bArr, int i2) {
        this(bArr, null, i2);
    }

    public c0(byte[] bArr, n[] nVarArr, int i2) {
        super(bArr);
        this.s = nVarArr;
        this.f3783d = i2;
    }

    public c0(n[] nVarArr) {
        this(nVarArr, 1000);
    }

    public c0(n[] nVarArr, int i2) {
        this(u(nVarArr), nVarArr, i2);
    }

    public static c0 r(r rVar) {
        n[] nVarArr = new n[rVar.size()];
        Enumeration enumerationR = rVar.r();
        int i2 = 0;
        while (enumerationR.hasMoreElements()) {
            nVarArr[i2] = (n) enumerationR.nextElement();
            i2++;
        }
        return new c0(nVarArr);
    }

    public static byte[] u(n[] nVarArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i2 = 0; i2 != nVarArr.length; i2++) {
            try {
                byteArrayOutputStream.write(((y0) nVarArr[i2]).p());
            } catch (IOException e2) {
                throw new IllegalArgumentException("exception converting octets " + e2.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(nVarArr[i2].getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // g.a.a.q
    public void h(p pVar) throws IOException {
        pVar.c(36);
        pVar.c(128);
        Enumeration enumerationT = t();
        while (enumerationT.hasMoreElements()) {
            pVar.j((e) enumerationT.nextElement());
        }
        pVar.c(0);
        pVar.c(0);
    }

    @Override // g.a.a.q
    public int i() throws IOException {
        Enumeration enumerationT = t();
        int i2 = 0;
        while (enumerationT.hasMoreElements()) {
            i2 += ((e) enumerationT.nextElement()).c().i();
        }
        return i2 + 2 + 2;
    }

    @Override // g.a.a.q
    public boolean k() {
        return true;
    }

    @Override // g.a.a.n
    public byte[] p() {
        return this.f3929c;
    }

    public final Vector s() {
        Vector vector = new Vector();
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f3929c;
            if (i2 >= bArr.length) {
                return vector;
            }
            int i3 = this.f3783d;
            int length = (i2 + i3 > bArr.length ? bArr.length : i3 + i2) - i2;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, i2, bArr2, 0, length);
            vector.addElement(new y0(bArr2));
            i2 += this.f3783d;
        }
    }

    public Enumeration t() {
        return this.s == null ? s().elements() : new a();
    }
}
