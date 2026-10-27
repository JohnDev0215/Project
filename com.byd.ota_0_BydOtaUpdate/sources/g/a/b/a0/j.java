package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public abstract class j implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long[] f4301a = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f4302b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4303c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f4304d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4305e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4306f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f4307g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f4308h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4309i;
    public long j;
    public long k;
    public long l;
    public long m;
    public long[] n;
    public int o;

    public j() {
        this.f4302b = new byte[8];
        this.n = new long[80];
        this.f4303c = 0;
        reset();
    }

    public j(j jVar) {
        this.f4302b = new byte[8];
        this.n = new long[80];
        k(jVar);
    }

    public final long d(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    public final long e(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    public final long f(long j) {
        return (j >>> 7) ^ (((j << 63) | (j >>> 1)) ^ ((j << 56) | (j >>> 8)));
    }

    public final long g(long j) {
        return (j >>> 6) ^ (((j << 45) | (j >>> 19)) ^ ((j << 3) | (j >>> 61)));
    }

    @Override // g.a.b.p
    public int getByteLength() {
        return 128;
    }

    public final long h(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    public final long i(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    public final void j() {
        long j = this.f4304d;
        if (j > 2305843009213693951L) {
            this.f4305e += j >>> 61;
            this.f4304d = j & 2305843009213693951L;
        }
    }

    public void k(j jVar) {
        byte[] bArr = jVar.f4302b;
        System.arraycopy(bArr, 0, this.f4302b, 0, bArr.length);
        this.f4303c = jVar.f4303c;
        this.f4304d = jVar.f4304d;
        this.f4305e = jVar.f4305e;
        this.f4306f = jVar.f4306f;
        this.f4307g = jVar.f4307g;
        this.f4308h = jVar.f4308h;
        this.f4309i = jVar.f4309i;
        this.j = jVar.j;
        this.k = jVar.k;
        this.l = jVar.l;
        this.m = jVar.m;
        long[] jArr = jVar.n;
        System.arraycopy(jArr, 0, this.n, 0, jArr.length);
        this.o = jVar.o;
    }

    public void l() {
        j();
        long j = this.f4304d << 3;
        long j2 = this.f4305e;
        byte b2 = -128;
        while (true) {
            update(b2);
            if (this.f4303c == 0) {
                n(j, j2);
                m();
                return;
            }
            b2 = 0;
        }
    }

    public void m() {
        j();
        for (int i2 = 16; i2 <= 79; i2++) {
            long[] jArr = this.n;
            long jG = g(jArr[i2 - 2]);
            long[] jArr2 = this.n;
            jArr[i2] = jG + jArr2[i2 - 7] + f(jArr2[i2 - 15]) + this.n[i2 - 16];
        }
        long j = this.f4306f;
        long j2 = this.f4307g;
        long j3 = this.f4308h;
        long j4 = this.f4309i;
        long j5 = this.j;
        long j6 = this.k;
        long j7 = this.l;
        long j8 = j6;
        long j9 = j4;
        int i3 = 0;
        long jH = j2;
        long j10 = j3;
        long j11 = j5;
        int i4 = 0;
        long j12 = this.m;
        long j13 = j;
        long j14 = j7;
        while (i4 < 10) {
            long j15 = j11;
            long jI = i(j11) + d(j11, j8, j14);
            long[] jArr3 = f4301a;
            int i5 = i3 + 1;
            long j16 = j12 + jI + jArr3[i3] + this.n[i3];
            long j17 = j9 + j16;
            long jH2 = j16 + h(j13) + e(j13, jH, j10);
            int i6 = i5 + 1;
            long jI2 = j14 + i(j17) + d(j17, j15, j8) + jArr3[i5] + this.n[i5];
            long j18 = j10 + jI2;
            long jH3 = jI2 + h(jH2) + e(jH2, j13, jH);
            int i7 = i6 + 1;
            long jI3 = j8 + i(j18) + d(j18, j17, j15) + jArr3[i6] + this.n[i6];
            long j19 = jH + jI3;
            long jH4 = jI3 + h(jH3) + e(jH3, jH2, j13);
            int i8 = i7 + 1;
            long jI4 = j15 + i(j19) + d(j19, j18, j17) + jArr3[i7] + this.n[i7];
            long j20 = j13 + jI4;
            long jH5 = jI4 + h(jH4) + e(jH4, jH3, jH2);
            int i9 = i8 + 1;
            long jI5 = j17 + i(j20) + d(j20, j19, j18) + jArr3[i8] + this.n[i8];
            long j21 = jH2 + jI5;
            long jH6 = jI5 + h(jH5) + e(jH5, jH4, jH3);
            int i10 = i9 + 1;
            long jI6 = j18 + i(j21) + d(j21, j20, j19) + jArr3[i9] + this.n[i9];
            long j22 = jH3 + jI6;
            long jH7 = jI6 + h(jH6) + e(jH6, jH5, jH4);
            j14 = j22;
            int i11 = i10 + 1;
            long jI7 = j19 + i(j22) + d(j22, j21, j20) + jArr3[i10] + this.n[i10];
            long j23 = jH4 + jI7;
            j8 = j23;
            jH = jI7 + h(jH7) + e(jH7, jH6, jH5);
            long jI8 = j20 + i(j23) + d(j23, j14, j21) + jArr3[i11] + this.n[i11];
            long jH8 = jI8 + h(jH) + e(jH, jH7, jH6);
            i4++;
            j11 = jH5 + jI8;
            j10 = jH7;
            j12 = j21;
            j9 = jH6;
            i3 = i11 + 1;
            j13 = jH8;
        }
        this.f4306f += j13;
        this.f4307g += jH;
        this.f4308h += j10;
        this.f4309i += j9;
        this.j += j11;
        this.k += j8;
        this.l += j14;
        this.m += j12;
        this.o = 0;
        for (int i12 = 0; i12 < 16; i12++) {
            this.n[i12] = 0;
        }
    }

    public void n(long j, long j2) {
        if (this.o > 14) {
            m();
        }
        long[] jArr = this.n;
        jArr[14] = j2;
        jArr[15] = j;
    }

    public void o(byte[] bArr, int i2) {
        this.n[this.o] = g.a.f.f.b(bArr, i2);
        int i3 = this.o + 1;
        this.o = i3;
        if (i3 == 16) {
            m();
        }
    }

    @Override // g.a.b.n
    public void reset() {
        this.f4304d = 0L;
        this.f4305e = 0L;
        int i2 = 0;
        this.f4303c = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f4302b;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = 0;
            i3++;
        }
        this.o = 0;
        while (true) {
            long[] jArr = this.n;
            if (i2 == jArr.length) {
                return;
            }
            jArr[i2] = 0;
            i2++;
        }
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        byte[] bArr = this.f4302b;
        int i2 = this.f4303c;
        int i3 = i2 + 1;
        this.f4303c = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            o(bArr, 0);
            this.f4303c = 0;
        }
        this.f4304d++;
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        while (this.f4303c != 0 && i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
        while (i3 > this.f4302b.length) {
            o(bArr, i2);
            byte[] bArr2 = this.f4302b;
            i2 += bArr2.length;
            i3 -= bArr2.length;
            this.f4304d += (long) bArr2.length;
        }
        while (i3 > 0) {
            update(bArr[i2]);
            i2++;
            i3--;
        }
    }
}
