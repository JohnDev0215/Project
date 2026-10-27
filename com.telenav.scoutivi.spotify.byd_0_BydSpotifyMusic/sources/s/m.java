package s;

import java.util.ArrayList;

/* JADX INFO: compiled from: RunGroup.java */
/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static int f9426h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    p f9429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    p f9430d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f9432f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f9433g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9427a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9428b = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<p> f9431e = new ArrayList<>();

    public m(p pVar, int i7) {
        this.f9429c = null;
        this.f9430d = null;
        int i8 = f9426h;
        this.f9432f = i8;
        f9426h = i8 + 1;
        this.f9429c = pVar;
        this.f9430d = pVar;
        this.f9433g = i7;
    }

    private long c(f fVar, long j7) {
        p pVar = fVar.f9410d;
        if (pVar instanceof k) {
            return j7;
        }
        int size = fVar.f9417k.size();
        long jMin = j7;
        for (int i7 = 0; i7 < size; i7++) {
            d dVar = fVar.f9417k.get(i7);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f9410d != pVar) {
                    jMin = Math.min(jMin, c(fVar2, ((long) fVar2.f9412f) + j7));
                }
            }
        }
        if (fVar != pVar.f9460i) {
            return jMin;
        }
        long j8 = j7 - pVar.j();
        return Math.min(Math.min(jMin, c(pVar.f9459h, j8)), j8 - ((long) pVar.f9459h.f9412f));
    }

    private long d(f fVar, long j7) {
        p pVar = fVar.f9410d;
        if (pVar instanceof k) {
            return j7;
        }
        int size = fVar.f9417k.size();
        long jMax = j7;
        for (int i7 = 0; i7 < size; i7++) {
            d dVar = fVar.f9417k.get(i7);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f9410d != pVar) {
                    jMax = Math.max(jMax, d(fVar2, ((long) fVar2.f9412f) + j7));
                }
            }
        }
        if (fVar != pVar.f9459h) {
            return jMax;
        }
        long j8 = j7 + pVar.j();
        return Math.max(Math.max(jMax, d(pVar.f9460i, j8)), j8 - ((long) pVar.f9460i.f9412f));
    }

    public void a(p pVar) {
        this.f9431e.add(pVar);
        this.f9430d = pVar;
    }

    public long b(r.f fVar, int i7) {
        long j7;
        int i8;
        p pVar = this.f9429c;
        if (pVar instanceof c) {
            if (((c) pVar).f9457f != i7) {
                return 0L;
            }
        } else if (i7 == 0) {
            if (!(pVar instanceof l)) {
                return 0L;
            }
        } else if (!(pVar instanceof n)) {
            return 0L;
        }
        f fVar2 = (i7 == 0 ? fVar.f9212e : fVar.f9214f).f9459h;
        f fVar3 = (i7 == 0 ? fVar.f9212e : fVar.f9214f).f9460i;
        boolean zContains = pVar.f9459h.f9418l.contains(fVar2);
        boolean zContains2 = this.f9429c.f9460i.f9418l.contains(fVar3);
        long j8 = this.f9429c.j();
        if (zContains && zContains2) {
            long jD = d(this.f9429c.f9459h, 0L);
            long jC = c(this.f9429c.f9460i, 0L);
            long j9 = jD - j8;
            p pVar2 = this.f9429c;
            int i9 = pVar2.f9460i.f9412f;
            if (j9 >= (-i9)) {
                j9 += (long) i9;
            }
            int i10 = pVar2.f9459h.f9412f;
            long j10 = ((-jC) - j8) - ((long) i10);
            if (j10 >= i10) {
                j10 -= (long) i10;
            }
            float fS = pVar2.f9453b.s(i7);
            float f7 = fS > 0.0f ? (long) ((j10 / fS) + (j9 / (1.0f - fS))) : 0L;
            long j11 = ((long) ((f7 * fS) + 0.5f)) + j8 + ((long) ((f7 * (1.0f - fS)) + 0.5f));
            p pVar3 = this.f9429c;
            j7 = ((long) pVar3.f9459h.f9412f) + j11;
            i8 = pVar3.f9460i.f9412f;
        } else {
            if (zContains) {
                return Math.max(d(this.f9429c.f9459h, r12.f9412f), ((long) this.f9429c.f9459h.f9412f) + j8);
            }
            if (zContains2) {
                return Math.max(-c(this.f9429c.f9460i, r12.f9412f), ((long) (-this.f9429c.f9460i.f9412f)) + j8);
            }
            p pVar4 = this.f9429c;
            j7 = ((long) pVar4.f9459h.f9412f) + pVar4.j();
            i8 = this.f9429c.f9460i.f9412f;
        }
        return j7 - ((long) i8);
    }
}
