package i3;

import B2.L;
import T0.j;
import e2.C1207o;
import e2.C1208p;
import e2.M;
import h2.AbstractC1343A;
import h2.t;
import java.math.RoundingMode;
import u2.T;

/* JADX INFO: renamed from: i3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1402a implements InterfaceC1403b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f15720m = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f15721n = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143, 157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f15722a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f15723b;
    public final j c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final byte[] f15724e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t f15725f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f15726g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1208p f15727h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f15728i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f15729j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f15730k;
    public long l;

    public C1402a(T t10, L l, j jVar) throws M {
        this.f15722a = t10;
        this.f15723b = l;
        this.c = jVar;
        int i7 = jVar.f8455n;
        int iMax = Math.max(1, i7 / 10);
        this.f15726g = iMax;
        t tVar = new t((byte[]) jVar.f8457q);
        tVar.o();
        int iO = tVar.o();
        this.d = iO;
        int i10 = jVar.f8454m;
        int i11 = jVar.o;
        int i12 = (((i11 - (i10 * 4)) * 8) / (jVar.f8456p * i10)) + 1;
        if (iO != i12) {
            throw M.a(null, "Expected frames per block: " + i12 + "; got: " + iO);
        }
        int iF = AbstractC1343A.f(iMax, iO);
        this.f15724e = new byte[iF * i11];
        this.f15725f = new t(iO * 2 * i10 * iF);
        int i13 = ((i11 * i7) * 8) / iO;
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l("audio/raw");
        c1207o.f14242h = i13;
        c1207o.f14243i = i13;
        c1207o.f14247n = iMax * 2 * i10;
        c1207o.B = i10;
        c1207o.f14228C = i7;
        c1207o.f14229D = 2;
        this.f15727h = new C1208p(c1207o);
    }

    @Override // i3.InterfaceC1403b
    public final void a(long j3, int i7) {
        this.f15722a.w(new C1407f(this.c, this.d, i7, j3));
        this.f15723b.d(this.f15727h);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0045 A[ADDED_TO_REGION, EDGE_INSN: B:44:0x0045->B:14:0x0045 BREAK  A[LOOP:0: B:6:0x0023->B:13:0x003f], REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x003c -> B:4:0x0020). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // i3.InterfaceC1403b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(B2.C0073m r26, long r27) {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i3.C1402a.b(B2.m, long):boolean");
    }

    @Override // i3.InterfaceC1403b
    public final void c(long j3) {
        this.f15728i = 0;
        this.f15729j = j3;
        this.f15730k = 0;
        this.l = 0L;
    }

    public final void d(int i7) {
        long j3 = this.f15729j;
        long j10 = this.l;
        j jVar = this.c;
        long j11 = jVar.f8455n;
        int i10 = AbstractC1343A.f15195a;
        long jN = j3 + AbstractC1343A.N(j10, 1000000L, j11, RoundingMode.DOWN);
        int i11 = i7 * 2 * jVar.f8454m;
        this.f15723b.b(jN, 1, i11, this.f15730k - i11, null);
        this.l += (long) i7;
        this.f15730k -= i11;
    }
}
