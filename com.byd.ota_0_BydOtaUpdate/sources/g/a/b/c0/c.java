package g.a.b.c0;

import g.a.b.i;
import g.a.b.j;
import g.a.b.j0.x0;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class c implements g.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SecureRandom f4397a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.a f4398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4399c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4400d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public byte[] f4404h;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4402f = -1;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public byte[] f4403g = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4401e = i();

    public class a implements PrivilegedAction {
        public a() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty("org.bouncycastle.pkcs1.strict");
        }
    }

    public class b implements PrivilegedAction {
        public b() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty("org.bouncycastle.pkcs1.not_strict");
        }
    }

    public c(g.a.b.a aVar) {
        this.f4398b = aVar;
    }

    public static int d(byte[] bArr, int i2) {
        int i3 = 0 | (bArr[0] ^ 2);
        int i4 = i2 + 1;
        int length = bArr.length - i4;
        for (int i5 = 1; i5 < length; i5++) {
            byte b2 = bArr[i5];
            int i6 = b2 | (b2 >> 1);
            int i7 = i6 | (i6 >> 2);
            i3 |= ((i7 | (i7 >> 4)) & 1) - 1;
        }
        int i8 = bArr[bArr.length - i4] | i3;
        int i9 = i8 | (i8 >> 1);
        int i10 = i9 | (i9 >> 2);
        return ~(((i10 | (i10 >> 4)) & 1) - 1);
    }

    @Override // g.a.b.a
    public int a() {
        int iA = this.f4398b.a();
        return this.f4399c ? iA : iA - 10;
    }

    @Override // g.a.b.a
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        return this.f4399c ? g(bArr, i2, i3) : e(bArr, i2, i3);
    }

    @Override // g.a.b.a
    public int c() {
        int iC = this.f4398b.c();
        return this.f4399c ? iC - 10 : iC;
    }

    public final byte[] e(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (this.f4402f != -1) {
            return f(bArr, i2, i3);
        }
        byte[] bArrB = this.f4398b.b(bArr, i2, i3);
        boolean z = this.f4401e & (bArrB.length != this.f4398b.a());
        if (bArrB.length < a()) {
            bArrB = this.f4404h;
        }
        byte b2 = bArrB[0];
        boolean z2 = !this.f4400d ? b2 == 1 : b2 == 2;
        int iH = h(b2, bArrB) + 1;
        if (z2 || (iH < 10)) {
            g.a.f.a.u(bArrB, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        }
        if (z) {
            g.a.f.a.u(bArrB, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        }
        int length = bArrB.length - iH;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArrB, iH, bArr2, 0, length);
        return bArr2;
    }

    public final byte[] f(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (!this.f4400d) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] bArrB = this.f4398b.b(bArr, i2, i3);
        byte[] bArr2 = this.f4403g;
        if (bArr2 == null) {
            bArr2 = new byte[this.f4402f];
            this.f4397a.nextBytes(bArr2);
        }
        if (this.f4401e & (bArrB.length != this.f4398b.a())) {
            bArrB = this.f4404h;
        }
        int iD = d(bArrB, this.f4402f);
        byte[] bArr3 = new byte[this.f4402f];
        int i4 = 0;
        while (true) {
            int i5 = this.f4402f;
            if (i4 >= i5) {
                g.a.f.a.u(bArrB, (byte) 0);
                return bArr3;
            }
            bArr3[i4] = (byte) ((bArrB[(bArrB.length - i5) + i4] & (~iD)) | (bArr2[i4] & iD));
            i4++;
        }
    }

    public final byte[] g(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        if (i3 > c()) {
            throw new IllegalArgumentException("input data too large");
        }
        int iC = this.f4398b.c();
        byte[] bArr2 = new byte[iC];
        if (this.f4400d) {
            bArr2[0] = 1;
            for (int i4 = 1; i4 != (iC - i3) - 1; i4++) {
                bArr2[i4] = -1;
            }
        } else {
            this.f4397a.nextBytes(bArr2);
            bArr2[0] = 2;
            for (int i5 = 1; i5 != (iC - i3) - 1; i5++) {
                while (bArr2[i5] == 0) {
                    bArr2[i5] = (byte) this.f4397a.nextInt();
                }
            }
        }
        int i6 = iC - i3;
        bArr2[i6 - 1] = 0;
        System.arraycopy(bArr, i2, bArr2, i6, i3);
        return this.f4398b.b(bArr2, 0, iC);
    }

    public final int h(byte b2, byte[] bArr) throws InvalidCipherTextException {
        int i2 = -1;
        boolean z = false;
        for (int i3 = 1; i3 != bArr.length; i3++) {
            byte b3 = bArr[i3];
            if ((b3 == 0) & (i2 < 0)) {
                i2 = i3;
            }
            z |= (b3 != -1) & (b2 == 1) & (i2 < 0);
        }
        if (z) {
            return -1;
        }
        return i2;
    }

    public final boolean i() {
        String str = (String) AccessController.doPrivileged(new a());
        return ((String) AccessController.doPrivileged(new b())) != null ? !r1.equals("true") : str == null || str.equals("true");
    }

    @Override // g.a.b.a
    public void init(boolean z, i iVar) {
        g.a.b.j0.b bVar;
        if (iVar instanceof x0) {
            x0 x0Var = (x0) iVar;
            this.f4397a = x0Var.b();
            bVar = (g.a.b.j0.b) x0Var.a();
        } else {
            bVar = (g.a.b.j0.b) iVar;
            if (!bVar.a() && z) {
                this.f4397a = j.b();
            }
        }
        this.f4398b.init(z, iVar);
        this.f4400d = bVar.a();
        this.f4399c = z;
        this.f4404h = new byte[this.f4398b.a()];
        if (this.f4402f > 0 && this.f4403g == null && this.f4397a == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }
}
