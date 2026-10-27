package g.a.b.m0;

import g.a.b.j0.s0;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class i implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5142a = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g.a.b.g0.g f5143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[] f5144c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f5145d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BigInteger f5146e;

    public i(g.a.b.n nVar) {
        g.a.b.g0.g gVar = new g.a.b.g0.g(nVar);
        this.f5143b = gVar;
        this.f5145d = new byte[gVar.getMacSize()];
        this.f5144c = new byte[gVar.getMacSize()];
    }

    @Override // g.a.b.m0.a
    public BigInteger a() {
        int iBitLength = (this.f5146e.bitLength() + 7) / 8;
        byte[] bArr = new byte[iBitLength];
        while (true) {
            int i2 = 0;
            while (i2 < iBitLength) {
                g.a.b.g0.g gVar = this.f5143b;
                byte[] bArr2 = this.f5145d;
                gVar.update(bArr2, 0, bArr2.length);
                this.f5143b.doFinal(this.f5145d, 0);
                int iMin = Math.min(iBitLength - i2, this.f5145d.length);
                System.arraycopy(this.f5145d, 0, bArr, i2, iMin);
                i2 += iMin;
            }
            BigInteger bigIntegerE = e(bArr);
            if (bigIntegerE.compareTo(f5142a) > 0 && bigIntegerE.compareTo(this.f5146e) < 0) {
                return bigIntegerE;
            }
            g.a.b.g0.g gVar2 = this.f5143b;
            byte[] bArr3 = this.f5145d;
            gVar2.update(bArr3, 0, bArr3.length);
            this.f5143b.update((byte) 0);
            this.f5143b.doFinal(this.f5144c, 0);
            this.f5143b.init(new s0(this.f5144c));
            g.a.b.g0.g gVar3 = this.f5143b;
            byte[] bArr4 = this.f5145d;
            gVar3.update(bArr4, 0, bArr4.length);
            this.f5143b.doFinal(this.f5145d, 0);
        }
    }

    @Override // g.a.b.m0.a
    public void b(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override // g.a.b.m0.a
    public boolean c() {
        return true;
    }

    @Override // g.a.b.m0.a
    public void d(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f5146e = bigInteger;
        g.a.f.a.u(this.f5145d, (byte) 1);
        g.a.f.a.u(this.f5144c, (byte) 0);
        int iBitLength = (bigInteger.bitLength() + 7) / 8;
        byte[] bArr2 = new byte[iBitLength];
        byte[] bArrB = g.a.f.b.b(bigInteger2);
        System.arraycopy(bArrB, 0, bArr2, iBitLength - bArrB.length, bArrB.length);
        int iBitLength2 = (bigInteger.bitLength() + 7) / 8;
        byte[] bArr3 = new byte[iBitLength2];
        BigInteger bigIntegerE = e(bArr);
        if (bigIntegerE.compareTo(bigInteger) >= 0) {
            bigIntegerE = bigIntegerE.subtract(bigInteger);
        }
        byte[] bArrB2 = g.a.f.b.b(bigIntegerE);
        System.arraycopy(bArrB2, 0, bArr3, iBitLength2 - bArrB2.length, bArrB2.length);
        this.f5143b.init(new s0(this.f5144c));
        g.a.b.g0.g gVar = this.f5143b;
        byte[] bArr4 = this.f5145d;
        gVar.update(bArr4, 0, bArr4.length);
        this.f5143b.update((byte) 0);
        this.f5143b.update(bArr2, 0, iBitLength);
        this.f5143b.update(bArr3, 0, iBitLength2);
        this.f5143b.doFinal(this.f5144c, 0);
        this.f5143b.init(new s0(this.f5144c));
        g.a.b.g0.g gVar2 = this.f5143b;
        byte[] bArr5 = this.f5145d;
        gVar2.update(bArr5, 0, bArr5.length);
        this.f5143b.doFinal(this.f5145d, 0);
        g.a.b.g0.g gVar3 = this.f5143b;
        byte[] bArr6 = this.f5145d;
        gVar3.update(bArr6, 0, bArr6.length);
        this.f5143b.update((byte) 1);
        this.f5143b.update(bArr2, 0, iBitLength);
        this.f5143b.update(bArr3, 0, iBitLength2);
        this.f5143b.doFinal(this.f5144c, 0);
        this.f5143b.init(new s0(this.f5144c));
        g.a.b.g0.g gVar4 = this.f5143b;
        byte[] bArr7 = this.f5145d;
        gVar4.update(bArr7, 0, bArr7.length);
        this.f5143b.doFinal(this.f5145d, 0);
    }

    public final BigInteger e(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        return bArr.length * 8 > this.f5146e.bitLength() ? bigInteger.shiftRight((bArr.length * 8) - this.f5146e.bitLength()) : bigInteger;
    }
}
