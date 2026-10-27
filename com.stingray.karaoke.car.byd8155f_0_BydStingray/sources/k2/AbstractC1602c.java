package k2;

import android.os.SystemClock;
import d5.d0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;

/* JADX INFO: renamed from: k2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1602c implements h {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f17649m = new ArrayList(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17650n;
    public m o;

    public AbstractC1602c(boolean z6) {
        this.l = z6;
    }

    @Override // k2.h
    public final void b(E e10) {
        e10.getClass();
        ArrayList arrayList = this.f17649m;
        if (arrayList.contains(e10)) {
            return;
        }
        arrayList.add(e10);
        this.f17650n++;
    }

    public final void e(int i7) {
        boolean z6;
        m mVar = this.o;
        int i10 = AbstractC1343A.f15195a;
        for (int i11 = 0; i11 < this.f17650n; i11++) {
            E e10 = (E) this.f17649m.get(i11);
            boolean z10 = this.l;
            x2.h hVar = (x2.h) e10;
            synchronized (hVar) {
                d0 d0Var = x2.h.f23733n;
                if (z10) {
                    int i12 = mVar.f17681i;
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (z6) {
                    hVar.f23744h += (long) i7;
                }
            }
        }
    }

    public final void p() {
        boolean z6;
        m mVar = this.o;
        int i7 = AbstractC1343A.f15195a;
        for (int i10 = 0; i10 < this.f17650n; i10++) {
            E e10 = (E) this.f17649m.get(i10);
            boolean z10 = this.l;
            x2.h hVar = (x2.h) e10;
            synchronized (hVar) {
                try {
                    d0 d0Var = x2.h.f23733n;
                    if (z10) {
                        int i11 = mVar.f17681i;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (z6) {
                        AbstractC1346c.j(hVar.f23742f > 0);
                        hVar.c.getClass();
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        int i12 = (int) (jElapsedRealtime - hVar.f23743g);
                        hVar.f23745i += (long) i12;
                        long j3 = hVar.f23746j;
                        long j10 = hVar.f23744h;
                        hVar.f23746j = j3 + j10;
                        if (i12 > 0) {
                            hVar.f23741e.a((j10 * 8000.0f) / i12, (int) Math.sqrt(j10));
                            if (hVar.f23745i >= 2000 || hVar.f23746j >= 524288) {
                                hVar.f23747k = (long) hVar.f23741e.b();
                            }
                            hVar.c(i12, hVar.f23744h, hVar.f23747k);
                            hVar.f23743g = jElapsedRealtime;
                            hVar.f23744h = 0L;
                        }
                        hVar.f23742f--;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.o = null;
    }

    public final void q() {
        for (int i7 = 0; i7 < this.f17650n; i7++) {
            ((E) this.f17649m.get(i7)).getClass();
        }
    }

    public final void r(m mVar) {
        boolean z6;
        this.o = mVar;
        for (int i7 = 0; i7 < this.f17650n; i7++) {
            E e10 = (E) this.f17649m.get(i7);
            boolean z10 = this.l;
            x2.h hVar = (x2.h) e10;
            synchronized (hVar) {
                try {
                    d0 d0Var = x2.h.f23733n;
                    if (z10) {
                        int i10 = mVar.f17681i;
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (z6) {
                        if (hVar.f23742f == 0) {
                            hVar.c.getClass();
                            hVar.f23743g = SystemClock.elapsedRealtime();
                        }
                        hVar.f23742f++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
