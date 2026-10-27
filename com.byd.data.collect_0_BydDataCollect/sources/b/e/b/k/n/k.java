package b.e.b.k.n;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2504a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    m f2507d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    m f2508e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2510g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2511h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2505b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2506c = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ArrayList<m> f2509f = new ArrayList<>();

    public k(m mVar, int i2) {
        this.f2507d = null;
        this.f2508e = null;
        this.f2510g = 0;
        int i3 = f2504a;
        this.f2510g = i3;
        f2504a = i3 + 1;
        this.f2507d = mVar;
        this.f2508e = mVar;
        this.f2511h = i2;
    }

    private long c(f fVar, long j) {
        m mVar = fVar.f2489d;
        if (mVar instanceof i) {
            return j;
        }
        int size = fVar.k.size();
        long jMin = j;
        for (int i2 = 0; i2 < size; i2++) {
            d dVar = fVar.k.get(i2);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f2489d != mVar) {
                    jMin = Math.min(jMin, c(fVar2, ((long) fVar2.f2491f) + j));
                }
            }
        }
        if (fVar != mVar.f2521i) {
            return jMin;
        }
        long j2 = j - mVar.j();
        return Math.min(Math.min(jMin, c(mVar.f2520h, j2)), j2 - ((long) mVar.f2520h.f2491f));
    }

    private long d(f fVar, long j) {
        m mVar = fVar.f2489d;
        if (mVar instanceof i) {
            return j;
        }
        int size = fVar.k.size();
        long jMax = j;
        for (int i2 = 0; i2 < size; i2++) {
            d dVar = fVar.k.get(i2);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f2489d != mVar) {
                    jMax = Math.max(jMax, d(fVar2, ((long) fVar2.f2491f) + j));
                }
            }
        }
        if (fVar != mVar.f2520h) {
            return jMax;
        }
        long j2 = j + mVar.j();
        return Math.max(Math.max(jMax, d(mVar.f2521i, j2)), j2 - ((long) mVar.f2521i.f2491f));
    }

    public void a(m mVar) {
        this.f2509f.add(mVar);
        this.f2508e = mVar;
    }

    public long b(b.e.b.k.f fVar, int i2) {
        long j;
        int i3;
        m mVar = this.f2507d;
        if (mVar instanceof c) {
            if (((c) mVar).f2518f != i2) {
                return 0L;
            }
        } else if (i2 == 0) {
            if (!(mVar instanceof j)) {
                return 0L;
            }
        } else if (!(mVar instanceof l)) {
            return 0L;
        }
        f fVar2 = (i2 == 0 ? fVar.f2443f : fVar.f2444g).f2520h;
        f fVar3 = (i2 == 0 ? fVar.f2443f : fVar.f2444g).f2521i;
        boolean zContains = mVar.f2520h.l.contains(fVar2);
        boolean zContains2 = this.f2507d.f2521i.l.contains(fVar3);
        long j2 = this.f2507d.j();
        if (zContains && zContains2) {
            long jD = d(this.f2507d.f2520h, 0L);
            long jC = c(this.f2507d.f2521i, 0L);
            long j3 = jD - j2;
            m mVar2 = this.f2507d;
            int i4 = mVar2.f2521i.f2491f;
            if (j3 >= (-i4)) {
                j3 += (long) i4;
            }
            int i5 = mVar2.f2520h.f2491f;
            long j4 = ((-jC) - j2) - ((long) i5);
            if (j4 >= i5) {
                j4 -= (long) i5;
            }
            float fP = mVar2.f2514b.p(i2);
            float f2 = fP > 0.0f ? (long) ((j4 / fP) + (j3 / (1.0f - fP))) : 0L;
            long j5 = ((long) ((f2 * fP) + 0.5f)) + j2 + ((long) ((f2 * (1.0f - fP)) + 0.5f));
            m mVar3 = this.f2507d;
            j = ((long) mVar3.f2520h.f2491f) + j5;
            i3 = mVar3.f2521i.f2491f;
        } else {
            if (zContains) {
                return Math.max(d(this.f2507d.f2520h, r12.f2491f), ((long) this.f2507d.f2520h.f2491f) + j2);
            }
            if (zContains2) {
                return Math.max(-c(this.f2507d.f2521i, r12.f2491f), ((long) (-this.f2507d.f2521i.f2491f)) + j2);
            }
            m mVar4 = this.f2507d;
            j = ((long) mVar4.f2520h.f2491f) + mVar4.j();
            i3 = this.f2507d.f2521i.f2491f;
        }
        return j - ((long) i3);
    }
}
