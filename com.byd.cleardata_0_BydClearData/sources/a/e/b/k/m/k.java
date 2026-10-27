package a.e.b.k.m;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f242a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    m f245d;
    m e;
    int g;
    int h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f243b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f244c = false;
    ArrayList<m> f = new ArrayList<>();

    public k(m mVar, int i) {
        this.f245d = null;
        this.e = null;
        this.g = 0;
        int i2 = f242a;
        this.g = i2;
        f242a = i2 + 1;
        this.f245d = mVar;
        this.e = mVar;
        this.h = i;
    }

    private long c(f fVar, long j) {
        m mVar = fVar.f237d;
        if (mVar instanceof i) {
            return j;
        }
        int size = fVar.k.size();
        long jMin = j;
        for (int i = 0; i < size; i++) {
            d dVar = fVar.k.get(i);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f237d != mVar) {
                    jMin = Math.min(jMin, c(fVar2, ((long) fVar2.f) + j));
                }
            }
        }
        if (fVar != mVar.i) {
            return jMin;
        }
        long j2 = j - mVar.j();
        return Math.min(Math.min(jMin, c(mVar.h, j2)), j2 - ((long) mVar.h.f));
    }

    private long d(f fVar, long j) {
        m mVar = fVar.f237d;
        if (mVar instanceof i) {
            return j;
        }
        int size = fVar.k.size();
        long jMax = j;
        for (int i = 0; i < size; i++) {
            d dVar = fVar.k.get(i);
            if (dVar instanceof f) {
                f fVar2 = (f) dVar;
                if (fVar2.f237d != mVar) {
                    jMax = Math.max(jMax, d(fVar2, ((long) fVar2.f) + j));
                }
            }
        }
        if (fVar != mVar.h) {
            return jMax;
        }
        long j2 = j + mVar.j();
        return Math.max(Math.max(jMax, d(mVar.i, j2)), j2 - ((long) mVar.i.f));
    }

    public void a(m mVar) {
        this.f.add(mVar);
        this.e = mVar;
    }

    public long b(a.e.b.k.f fVar, int i) {
        long j;
        m mVar;
        long j2;
        long jD;
        m mVar2 = this.f245d;
        if (mVar2 instanceof c) {
            if (((c) mVar2).f != i) {
                return 0L;
            }
        } else if (i == 0) {
            if (!(mVar2 instanceof j)) {
                return 0L;
            }
        } else if (!(mVar2 instanceof l)) {
            return 0L;
        }
        f fVar2 = (i == 0 ? fVar.f : fVar.g).h;
        f fVar3 = (i == 0 ? fVar.f : fVar.g).i;
        boolean zContains = mVar2.h.l.contains(fVar2);
        boolean zContains2 = this.f245d.i.l.contains(fVar3);
        long j3 = this.f245d.j();
        if (!zContains || !zContains2) {
            if (zContains) {
                jD = d(this.f245d.h, r12.f);
                j2 = ((long) this.f245d.h.f) + j3;
            } else if (zContains2) {
                long jC = c(this.f245d.i, r12.f);
                j2 = ((long) (-this.f245d.i.f)) + j3;
                jD = -jC;
            } else {
                m mVar3 = this.f245d;
                j = ((long) mVar3.h.f) + mVar3.j();
                mVar = this.f245d;
            }
            return Math.max(jD, j2);
        }
        long jD2 = d(this.f245d.h, 0L);
        long jC2 = c(this.f245d.i, 0L);
        long j4 = jD2 - j3;
        m mVar4 = this.f245d;
        int i2 = mVar4.i.f;
        if (j4 >= (-i2)) {
            j4 += (long) i2;
        }
        int i3 = mVar4.h.f;
        long j5 = ((-jC2) - j3) - ((long) i3);
        if (j5 >= i3) {
            j5 -= (long) i3;
        }
        float fM = mVar4.f248b.m(i);
        float f = fM > 0.0f ? (long) ((j5 / fM) + (j4 / (1.0f - fM))) : 0L;
        long j6 = ((long) ((f * fM) + 0.5f)) + j3 + ((long) ((f * (1.0f - fM)) + 0.5f));
        mVar = this.f245d;
        j = ((long) mVar.h.f) + j6;
        return j - ((long) mVar.i.f);
    }
}
