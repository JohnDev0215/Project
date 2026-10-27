package g.a.b.d0;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
public class t implements g.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4593a = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f4594b = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f4595c = BigInteger.valueOf(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.j0.e0 f4596d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SecureRandom f4597e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4598f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4599g;

    @Override // g.a.b.a
    public int a() {
        return this.f4598f ? ((this.f4599g + 7) / 8) * 2 : (this.f4599g - 1) / 8;
    }

    @Override // g.a.b.a
    public byte[] b(byte[] bArr, int i2, int i3) {
        if (this.f4596d == null) {
            throw new IllegalStateException("ElGamal engine not initialised");
        }
        if (i3 > (this.f4598f ? ((this.f4599g - 1) + 7) / 8 : c())) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        BigInteger bigIntegerC = this.f4596d.b().c();
        if (this.f4596d instanceof g.a.b.j0.g0) {
            int i4 = i3 / 2;
            byte[] bArr2 = new byte[i4];
            byte[] bArr3 = new byte[i4];
            System.arraycopy(bArr, i2, bArr2, 0, i4);
            System.arraycopy(bArr, i2 + i4, bArr3, 0, i4);
            return g.a.f.b.b(new BigInteger(1, bArr2).modPow(bigIntegerC.subtract(f4594b).subtract(((g.a.b.j0.g0) this.f4596d).c()), bigIntegerC).multiply(new BigInteger(1, bArr3)).mod(bigIntegerC));
        }
        if (i2 != 0 || i3 != bArr.length) {
            byte[] bArr4 = new byte[i3];
            System.arraycopy(bArr, i2, bArr4, 0, i3);
            bArr = bArr4;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(bigIntegerC) >= 0) {
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        g.a.b.j0.h0 h0Var = (g.a.b.j0.h0) this.f4596d;
        int iBitLength = bigIntegerC.bitLength();
        BigInteger bigInteger2 = new BigInteger(iBitLength, this.f4597e);
        while (true) {
            if (!bigInteger2.equals(f4593a) && bigInteger2.compareTo(bigIntegerC.subtract(f4595c)) <= 0) {
                break;
            }
            bigInteger2 = new BigInteger(iBitLength, this.f4597e);
        }
        BigInteger bigIntegerModPow = this.f4596d.b().a().modPow(bigInteger2, bigIntegerC);
        BigInteger bigIntegerMod = bigInteger.multiply(h0Var.c().modPow(bigInteger2, bigIntegerC)).mod(bigIntegerC);
        byte[] byteArray = bigIntegerModPow.toByteArray();
        byte[] byteArray2 = bigIntegerMod.toByteArray();
        int iA = a();
        byte[] bArr5 = new byte[iA];
        int i5 = iA / 2;
        if (byteArray.length > i5) {
            System.arraycopy(byteArray, 1, bArr5, i5 - (byteArray.length - 1), byteArray.length - 1);
        } else {
            System.arraycopy(byteArray, 0, bArr5, i5 - byteArray.length, byteArray.length);
        }
        if (byteArray2.length > i5) {
            System.arraycopy(byteArray2, 1, bArr5, iA - (byteArray2.length - 1), byteArray2.length - 1);
        } else {
            System.arraycopy(byteArray2, 0, bArr5, iA - byteArray2.length, byteArray2.length);
        }
        return bArr5;
    }

    @Override // g.a.b.a
    public int c() {
        return this.f4598f ? (this.f4599g - 1) / 8 : ((this.f4599g + 7) / 8) * 2;
    }

    @Override // g.a.b.a
    public void init(boolean z, g.a.b.i iVar) {
        SecureRandom secureRandomB;
        if (iVar instanceof g.a.b.j0.x0) {
            g.a.b.j0.x0 x0Var = (g.a.b.j0.x0) iVar;
            this.f4596d = (g.a.b.j0.e0) x0Var.a();
            secureRandomB = x0Var.b();
        } else {
            this.f4596d = (g.a.b.j0.e0) iVar;
            secureRandomB = g.a.b.j.b();
        }
        this.f4597e = secureRandomB;
        this.f4598f = z;
        this.f4599g = this.f4596d.b().c().bitLength();
        if (z) {
            if (!(this.f4596d instanceof g.a.b.j0.h0)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.f4596d instanceof g.a.b.j0.g0)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }
}
