package h3;

import B2.C0073m;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: h3.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1349A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15251a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h2.y f15252b;
    public final h2.t c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f15253e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15254f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f15255g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f15256h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f15257i;

    public C1349A(int i7) {
        this.f15251a = i7;
        switch (i7) {
            case 1:
                this.f15252b = new h2.y(0L);
                this.f15255g = -9223372036854775807L;
                this.f15256h = -9223372036854775807L;
                this.f15257i = -9223372036854775807L;
                this.c = new h2.t();
                break;
            default:
                this.f15252b = new h2.y(0L);
                this.f15255g = -9223372036854775807L;
                this.f15256h = -9223372036854775807L;
                this.f15257i = -9223372036854775807L;
                this.c = new h2.t();
                break;
        }
    }

    public static int b(int i7, byte[] bArr) {
        return (bArr[i7 + 3] & 255) | ((bArr[i7] & 255) << 24) | ((bArr[i7 + 1] & 255) << 16) | ((bArr[i7 + 2] & 255) << 8);
    }

    public static long c(h2.t tVar) {
        int i7 = tVar.f15242b;
        if (tVar.a() < 9) {
            return -9223372036854775807L;
        }
        byte[] bArr = new byte[9];
        tVar.f(0, 9, bArr);
        tVar.H(i7);
        byte b10 = bArr[0];
        if ((b10 & 196) == 68) {
            byte b11 = bArr[2];
            if ((b11 & 4) == 4) {
                byte b12 = bArr[4];
                if ((b12 & 4) == 4 && (bArr[5] & 1) == 1 && (bArr[8] & 3) == 3) {
                    long j3 = b10;
                    long j10 = b11;
                    return ((j10 & 3) << 13) | ((j3 & 3) << 28) | (((56 & j3) >> 3) << 30) | ((((long) bArr[1]) & 255) << 20) | (((j10 & 248) >> 3) << 15) | ((((long) bArr[3]) & 255) << 5) | ((((long) b12) & 248) >> 3);
                }
            }
        }
        return -9223372036854775807L;
    }

    public final void a(C0073m c0073m) {
        switch (this.f15251a) {
            case 0:
                byte[] bArr = AbstractC1343A.f15198f;
                h2.t tVar = this.c;
                tVar.getClass();
                tVar.F(bArr.length, bArr);
                this.d = true;
                c0073m.f590q = 0;
                break;
            default:
                byte[] bArr2 = AbstractC1343A.f15198f;
                h2.t tVar2 = this.c;
                tVar2.getClass();
                tVar2.F(bArr2.length, bArr2);
                this.d = true;
                c0073m.f590q = 0;
                break;
        }
    }
}
