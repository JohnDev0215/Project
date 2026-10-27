package g.a.b.d0;

import java.math.BigInteger;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.j0.e1 f4545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4546b;

    public BigInteger a(byte[] bArr, int i2, int i3) {
        if (i3 > c() + 1) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (i3 == c() + 1 && !this.f4546b) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (i2 != 0 || i3 != bArr.length) {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            bArr = bArr2;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(this.f4545a.c()) < 0) {
            return bigInteger;
        }
        throw new DataLengthException("input too large for RSA cipher.");
    }

    public byte[] b(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (this.f4546b) {
            if (byteArray[0] == 0 && byteArray.length > d()) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                return bArr;
            }
            if (byteArray.length < d()) {
                int iD = d();
                byte[] bArr2 = new byte[iD];
                System.arraycopy(byteArray, 0, bArr2, iD - byteArray.length, byteArray.length);
                return bArr2;
            }
        } else if (byteArray[0] == 0) {
            int length2 = byteArray.length - 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(byteArray, 1, bArr3, 0, length2);
            return bArr3;
        }
        return byteArray;
    }

    public int c() {
        int iBitLength = (this.f4545a.c().bitLength() + 7) / 8;
        return this.f4546b ? iBitLength - 1 : iBitLength;
    }

    public int d() {
        int iBitLength = (this.f4545a.c().bitLength() + 7) / 8;
        return this.f4546b ? iBitLength : iBitLength - 1;
    }

    public void e(boolean z, g.a.b.i iVar) {
        if (iVar instanceof g.a.b.j0.x0) {
            iVar = ((g.a.b.j0.x0) iVar).a();
        }
        this.f4545a = (g.a.b.j0.e1) iVar;
        this.f4546b = z;
    }

    public BigInteger f(BigInteger bigInteger) {
        g.a.b.j0.e1 e1Var = this.f4545a;
        if (!(e1Var instanceof g.a.b.j0.f1)) {
            return bigInteger.modPow(e1Var.b(), this.f4545a.c());
        }
        g.a.b.j0.f1 f1Var = (g.a.b.j0.f1) e1Var;
        BigInteger bigIntegerG = f1Var.g();
        BigInteger bigIntegerI = f1Var.i();
        BigInteger bigIntegerE = f1Var.e();
        BigInteger bigIntegerF = f1Var.f();
        BigInteger bigIntegerJ = f1Var.j();
        BigInteger bigIntegerModPow = bigInteger.remainder(bigIntegerG).modPow(bigIntegerE, bigIntegerG);
        BigInteger bigIntegerModPow2 = bigInteger.remainder(bigIntegerI).modPow(bigIntegerF, bigIntegerI);
        return bigIntegerModPow.subtract(bigIntegerModPow2).multiply(bigIntegerJ).mod(bigIntegerG).multiply(bigIntegerI).add(bigIntegerModPow2);
    }
}
