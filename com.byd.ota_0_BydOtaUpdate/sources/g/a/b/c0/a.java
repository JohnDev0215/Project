package g.a.b.c0;

import g.a.b.i;
import g.a.b.j0.e1;
import g.a.b.j0.x0;
import java.math.BigInteger;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* JADX INFO: loaded from: classes.dex */
public class a implements g.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4383a = BigInteger.valueOf(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f4384b = BigInteger.valueOf(6);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static byte[] f4385c = {14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 11, 6, 7, 10, 12, 1};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static byte[] f4386d = {8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 13, 10, 14, 9, 0, 7};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g.a.b.a f4387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4388f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4389g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4390h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BigInteger f4391i;

    public a(g.a.b.a aVar) {
        this.f4387e = aVar;
    }

    public static byte[] d(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 0) {
            return byteArray;
        }
        int length = byteArray.length - 1;
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 1, bArr, 0, length);
        return bArr;
    }

    @Override // g.a.b.a
    public int a() {
        int iA = this.f4387e.a();
        return this.f4388f ? iA : (iA + 1) / 2;
    }

    @Override // g.a.b.a
    public byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        return this.f4388f ? f(bArr, i2, i3) : e(bArr, i2, i3);
    }

    @Override // g.a.b.a
    public int c() {
        int iC = this.f4387e.c();
        return this.f4388f ? (iC + 1) / 2 : iC;
    }

    public final byte[] e(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] bArrB = this.f4387e.b(bArr, i2, i3);
        int i4 = (this.f4389g + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, bArrB);
        BigInteger bigInteger2 = f4383a;
        BigInteger bigIntegerMod = bigInteger.mod(bigInteger2);
        BigInteger bigInteger3 = f4384b;
        if (!bigIntegerMod.equals(bigInteger3)) {
            if (!this.f4391i.subtract(bigInteger).mod(bigInteger2).equals(bigInteger3)) {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
            bigInteger = this.f4391i.subtract(bigInteger);
        }
        byte[] bArrD = d(bigInteger);
        if ((bArrD[bArrD.length - 1] & 15) != 6) {
            throw new InvalidCipherTextException("invalid forcing byte in block");
        }
        bArrD[bArrD.length - 1] = (byte) (((bArrD[bArrD.length - 1] & 255) >>> 4) | (f4386d[(bArrD[bArrD.length - 2] & 255) >> 4] << 4));
        byte[] bArr2 = f4385c;
        bArrD[0] = (byte) (bArr2[bArrD[1] & 15] | (bArr2[(bArrD[1] & 255) >>> 4] << 4));
        int i5 = 1;
        int i6 = 0;
        boolean z = false;
        for (int length = bArrD.length - 1; length >= bArrD.length - (i4 * 2); length -= 2) {
            byte[] bArr3 = f4385c;
            int i7 = bArr3[bArrD[length] & 15] | (bArr3[(bArrD[length] & 255) >>> 4] << 4);
            int i8 = length - 1;
            if (((bArrD[i8] ^ i7) & 255) != 0) {
                if (z) {
                    throw new InvalidCipherTextException("invalid tsums in block");
                }
                i5 = (bArrD[i8] ^ i7) & 255;
                z = true;
                i6 = i8;
            }
        }
        bArrD[i6] = 0;
        int length2 = (bArrD.length - i6) / 2;
        byte[] bArr4 = new byte[length2];
        for (int i9 = 0; i9 < length2; i9++) {
            bArr4[i9] = bArrD[(i9 * 2) + i6 + 1];
        }
        this.f4390h = i5 - 1;
        return bArr4;
    }

    public final byte[] f(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        int i4 = this.f4389g;
        int i5 = (i4 + 7) / 8;
        byte[] bArr2 = new byte[i5];
        int i6 = 1;
        int i7 = this.f4390h + 1;
        int i8 = (i4 + 13) / 16;
        int i9 = 0;
        while (i9 < i8) {
            if (i9 > i8 - i3) {
                int i10 = i8 - i9;
                System.arraycopy(bArr, (i2 + i3) - i10, bArr2, i5 - i8, i10);
            } else {
                System.arraycopy(bArr, i2, bArr2, i5 - (i9 + i3), i3);
            }
            i9 += i3;
        }
        for (int i11 = i5 - (i8 * 2); i11 != i5; i11 += 2) {
            byte b2 = bArr2[(i5 - i8) + (i11 / 2)];
            byte[] bArr3 = f4385c;
            bArr2[i11] = (byte) (bArr3[b2 & 15] | (bArr3[(b2 & 255) >>> 4] << 4));
            bArr2[i11 + 1] = b2;
        }
        int i12 = i5 - (i3 * 2);
        bArr2[i12] = (byte) (bArr2[i12] ^ i7);
        int i13 = i5 - 1;
        bArr2[i13] = (byte) ((bArr2[i13] << 4) | 6);
        int i14 = 8 - ((this.f4389g - 1) % 8);
        if (i14 != 8) {
            bArr2[0] = (byte) (bArr2[0] & (255 >>> i14));
            bArr2[0] = (byte) ((128 >>> i14) | bArr2[0]);
            i6 = 0;
        } else {
            bArr2[0] = 0;
            bArr2[1] = (byte) (bArr2[1] | 128);
        }
        return this.f4387e.b(bArr2, i6, i5 - i6);
    }

    @Override // g.a.b.a
    public void init(boolean z, i iVar) {
        e1 e1Var = iVar instanceof x0 ? (e1) ((x0) iVar).a() : (e1) iVar;
        this.f4387e.init(z, iVar);
        BigInteger bigIntegerC = e1Var.c();
        this.f4391i = bigIntegerC;
        this.f4389g = bigIntegerC.bitLength();
        this.f4388f = z;
    }
}
