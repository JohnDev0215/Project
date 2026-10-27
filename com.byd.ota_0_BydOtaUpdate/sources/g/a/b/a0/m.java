package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public class m extends h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4323d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4324e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4325f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4326g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f4327h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4328i;

    public m() {
        this.f4327h = new int[16];
        reset();
    }

    public m(m mVar) {
        super(mVar);
        this.f4327h = new int[16];
        m(mVar);
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        m((m) eVar);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new m(this);
    }

    @Override // g.a.b.n
    public int doFinal(byte[] bArr, int i2) {
        e();
        o(this.f4323d, bArr, i2);
        o(this.f4324e, bArr, i2 + 4);
        o(this.f4325f, bArr, i2 + 8);
        o(this.f4326g, bArr, i2 + 12);
        reset();
        return 16;
    }

    @Override // g.a.b.a0.h
    public void f() {
        int i2 = this.f4323d;
        int i3 = this.f4324e;
        int i4 = this.f4325f;
        int i5 = this.f4326g;
        int iN = n(((i2 + i(i3, i4, i5)) + this.f4327h[0]) - 680876936, 7) + i3;
        int iN2 = n(((i5 + i(iN, i3, i4)) + this.f4327h[1]) - 389564586, 12) + iN;
        int iN3 = n(i4 + i(iN2, iN, i3) + this.f4327h[2] + 606105819, 17) + iN2;
        int iN4 = n(((i3 + i(iN3, iN2, iN)) + this.f4327h[3]) - 1044525330, 22) + iN3;
        int iN5 = n(((iN + i(iN4, iN3, iN2)) + this.f4327h[4]) - 176418897, 7) + iN4;
        int iN6 = n(iN2 + i(iN5, iN4, iN3) + this.f4327h[5] + 1200080426, 12) + iN5;
        int iN7 = n(((iN3 + i(iN6, iN5, iN4)) + this.f4327h[6]) - 1473231341, 17) + iN6;
        int iN8 = n(((iN4 + i(iN7, iN6, iN5)) + this.f4327h[7]) - 45705983, 22) + iN7;
        int iN9 = n(iN5 + i(iN8, iN7, iN6) + this.f4327h[8] + 1770035416, 7) + iN8;
        int iN10 = n(((iN6 + i(iN9, iN8, iN7)) + this.f4327h[9]) - 1958414417, 12) + iN9;
        int iN11 = n(((iN7 + i(iN10, iN9, iN8)) + this.f4327h[10]) - 42063, 17) + iN10;
        int iN12 = n(((iN8 + i(iN11, iN10, iN9)) + this.f4327h[11]) - 1990404162, 22) + iN11;
        int iN13 = n(iN9 + i(iN12, iN11, iN10) + this.f4327h[12] + 1804603682, 7) + iN12;
        int iN14 = n(((iN10 + i(iN13, iN12, iN11)) + this.f4327h[13]) - 40341101, 12) + iN13;
        int iN15 = n(((iN11 + i(iN14, iN13, iN12)) + this.f4327h[14]) - 1502002290, 17) + iN14;
        int iN16 = n(iN12 + i(iN15, iN14, iN13) + this.f4327h[15] + 1236535329, 22) + iN15;
        int iN17 = n(((iN13 + j(iN16, iN15, iN14)) + this.f4327h[1]) - 165796510, 5) + iN16;
        int iN18 = n(((iN14 + j(iN17, iN16, iN15)) + this.f4327h[6]) - 1069501632, 9) + iN17;
        int iN19 = n(iN15 + j(iN18, iN17, iN16) + this.f4327h[11] + 643717713, 14) + iN18;
        int iN20 = n(((iN16 + j(iN19, iN18, iN17)) + this.f4327h[0]) - 373897302, 20) + iN19;
        int iN21 = n(((iN17 + j(iN20, iN19, iN18)) + this.f4327h[5]) - 701558691, 5) + iN20;
        int iN22 = n(iN18 + j(iN21, iN20, iN19) + this.f4327h[10] + 38016083, 9) + iN21;
        int iN23 = n(((iN19 + j(iN22, iN21, iN20)) + this.f4327h[15]) - 660478335, 14) + iN22;
        int iN24 = n(((iN20 + j(iN23, iN22, iN21)) + this.f4327h[4]) - 405537848, 20) + iN23;
        int iN25 = n(iN21 + j(iN24, iN23, iN22) + this.f4327h[9] + 568446438, 5) + iN24;
        int iN26 = n(((iN22 + j(iN25, iN24, iN23)) + this.f4327h[14]) - 1019803690, 9) + iN25;
        int iN27 = n(((iN23 + j(iN26, iN25, iN24)) + this.f4327h[3]) - 187363961, 14) + iN26;
        int iN28 = n(iN24 + j(iN27, iN26, iN25) + this.f4327h[8] + 1163531501, 20) + iN27;
        int iN29 = n(((iN25 + j(iN28, iN27, iN26)) + this.f4327h[13]) - 1444681467, 5) + iN28;
        int iN30 = n(((iN26 + j(iN29, iN28, iN27)) + this.f4327h[2]) - 51403784, 9) + iN29;
        int iN31 = n(iN27 + j(iN30, iN29, iN28) + this.f4327h[7] + 1735328473, 14) + iN30;
        int iN32 = n(((iN28 + j(iN31, iN30, iN29)) + this.f4327h[12]) - 1926607734, 20) + iN31;
        int iN33 = n(((iN29 + k(iN32, iN31, iN30)) + this.f4327h[5]) - 378558, 4) + iN32;
        int iN34 = n(((iN30 + k(iN33, iN32, iN31)) + this.f4327h[8]) - 2022574463, 11) + iN33;
        int iN35 = n(iN31 + k(iN34, iN33, iN32) + this.f4327h[11] + 1839030562, 16) + iN34;
        int iN36 = n(((iN32 + k(iN35, iN34, iN33)) + this.f4327h[14]) - 35309556, 23) + iN35;
        int iN37 = n(((iN33 + k(iN36, iN35, iN34)) + this.f4327h[1]) - 1530992060, 4) + iN36;
        int iN38 = n(iN34 + k(iN37, iN36, iN35) + this.f4327h[4] + 1272893353, 11) + iN37;
        int iN39 = n(((iN35 + k(iN38, iN37, iN36)) + this.f4327h[7]) - 155497632, 16) + iN38;
        int iN40 = n(((iN36 + k(iN39, iN38, iN37)) + this.f4327h[10]) - 1094730640, 23) + iN39;
        int iN41 = n(iN37 + k(iN40, iN39, iN38) + this.f4327h[13] + 681279174, 4) + iN40;
        int iN42 = n(((iN38 + k(iN41, iN40, iN39)) + this.f4327h[0]) - 358537222, 11) + iN41;
        int iN43 = n(((iN39 + k(iN42, iN41, iN40)) + this.f4327h[3]) - 722521979, 16) + iN42;
        int iN44 = n(iN40 + k(iN43, iN42, iN41) + this.f4327h[6] + 76029189, 23) + iN43;
        int iN45 = n(((iN41 + k(iN44, iN43, iN42)) + this.f4327h[9]) - 640364487, 4) + iN44;
        int iN46 = n(((iN42 + k(iN45, iN44, iN43)) + this.f4327h[12]) - 421815835, 11) + iN45;
        int iN47 = n(iN43 + k(iN46, iN45, iN44) + this.f4327h[15] + 530742520, 16) + iN46;
        int iN48 = n(((iN44 + k(iN47, iN46, iN45)) + this.f4327h[2]) - 995338651, 23) + iN47;
        int iN49 = n(((iN45 + l(iN48, iN47, iN46)) + this.f4327h[0]) - 198630844, 6) + iN48;
        int iN50 = n(iN46 + l(iN49, iN48, iN47) + this.f4327h[7] + 1126891415, 10) + iN49;
        int iN51 = n(((iN47 + l(iN50, iN49, iN48)) + this.f4327h[14]) - 1416354905, 15) + iN50;
        int iN52 = n(((iN48 + l(iN51, iN50, iN49)) + this.f4327h[5]) - 57434055, 21) + iN51;
        int iN53 = n(iN49 + l(iN52, iN51, iN50) + this.f4327h[12] + 1700485571, 6) + iN52;
        int iN54 = n(((iN50 + l(iN53, iN52, iN51)) + this.f4327h[3]) - 1894986606, 10) + iN53;
        int iN55 = n(((iN51 + l(iN54, iN53, iN52)) + this.f4327h[10]) - 1051523, 15) + iN54;
        int iN56 = n(((iN52 + l(iN55, iN54, iN53)) + this.f4327h[1]) - 2054922799, 21) + iN55;
        int iN57 = n(iN53 + l(iN56, iN55, iN54) + this.f4327h[8] + 1873313359, 6) + iN56;
        int iN58 = n(((iN54 + l(iN57, iN56, iN55)) + this.f4327h[15]) - 30611744, 10) + iN57;
        int iN59 = n(((iN55 + l(iN58, iN57, iN56)) + this.f4327h[6]) - 1560198380, 15) + iN58;
        int iN60 = n(iN56 + l(iN59, iN58, iN57) + this.f4327h[13] + 1309151649, 21) + iN59;
        int iN61 = n(((iN57 + l(iN60, iN59, iN58)) + this.f4327h[4]) - 145523070, 6) + iN60;
        int iN62 = n(((iN58 + l(iN61, iN60, iN59)) + this.f4327h[11]) - 1120210379, 10) + iN61;
        int iN63 = n(iN59 + l(iN62, iN61, iN60) + this.f4327h[2] + 718787259, 15) + iN62;
        int iN64 = n(((iN60 + l(iN63, iN62, iN61)) + this.f4327h[9]) - 343485551, 21) + iN63;
        this.f4323d += iN61;
        this.f4324e += iN64;
        this.f4325f += iN63;
        this.f4326g += iN62;
        this.f4328i = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f4327h;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // g.a.b.a0.h
    public void g(long j) {
        if (this.f4328i > 14) {
            f();
        }
        int[] iArr = this.f4327h;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // g.a.b.n
    public String getAlgorithmName() {
        return "MD5";
    }

    @Override // g.a.b.n
    public int getDigestSize() {
        return 16;
    }

    @Override // g.a.b.a0.h
    public void h(byte[] bArr, int i2) {
        int[] iArr = this.f4327h;
        int i3 = this.f4328i;
        int i4 = i3 + 1;
        this.f4328i = i4;
        iArr[i3] = ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
        if (i4 == 16) {
            f();
        }
    }

    public final int i(int i2, int i3, int i4) {
        return ((~i2) & i4) | (i3 & i2);
    }

    public final int j(int i2, int i3, int i4) {
        return (i2 & i4) | (i3 & (~i4));
    }

    public final int k(int i2, int i3, int i4) {
        return (i2 ^ i3) ^ i4;
    }

    public final int l(int i2, int i3, int i4) {
        return (i2 | (~i4)) ^ i3;
    }

    public final void m(m mVar) {
        super.d(mVar);
        this.f4323d = mVar.f4323d;
        this.f4324e = mVar.f4324e;
        this.f4325f = mVar.f4325f;
        this.f4326g = mVar.f4326g;
        int[] iArr = mVar.f4327h;
        System.arraycopy(iArr, 0, this.f4327h, 0, iArr.length);
        this.f4328i = mVar.f4328i;
    }

    public final int n(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public final void o(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) i2;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) (i2 >>> 16);
        bArr[i3 + 3] = (byte) (i2 >>> 24);
    }

    @Override // g.a.b.a0.h, g.a.b.n
    public void reset() {
        super.reset();
        this.f4323d = 1732584193;
        this.f4324e = -271733879;
        this.f4325f = -1732584194;
        this.f4326g = 271733878;
        this.f4328i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.f4327h;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }
}
