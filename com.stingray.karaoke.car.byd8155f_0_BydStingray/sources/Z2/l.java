package z2;

import E5.z;
import android.os.SystemClock;
import android.util.Pair;
import d5.I;
import e2.C1207o;
import e2.C1208p;
import e2.L;
import e2.q0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.x;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArraySet;
import l8.C1789g;
import q3.ExecutorC2176c;
import w8.C2607b;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static final ExecutorC2176c o = new ExecutorC2176c(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f24330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f24331b;
    public final s c;
    public final k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final I f24332e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1789g f24333f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h2.v f24334g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CopyOnWriteArraySet f24335h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C1208p f24336i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public n f24337j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public x f24338k;
    public Pair l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f24339m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f24340n;

    public l(p2.q qVar) {
        i iVar = new i(this, qVar.f20758a);
        this.f24330a = iVar;
        h2.v vVar = (h2.v) qVar.f20762g;
        this.f24334g = vVar;
        o oVar = (o) qVar.c;
        this.f24331b = oVar;
        oVar.f24349k = vVar;
        s sVar = new s(new C2607b(this, 9), oVar);
        this.c = sVar;
        k kVar = (k) qVar.f20760e;
        AbstractC1346c.k(kVar);
        this.d = kVar;
        this.f24332e = (I) qVar.f20761f;
        this.f24333f = new C1789g(oVar, sVar);
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        this.f24335h = copyOnWriteArraySet;
        this.f24340n = 0;
        copyOnWriteArraySet.add(iVar);
    }

    public static void a(l lVar, long j3, long j10) {
        s sVar = lVar.c;
        z zVar = sVar.f24371f;
        int i7 = zVar.c;
        if (i7 == 0) {
            return;
        }
        if (i7 == 0) {
            throw new NoSuchElementException();
        }
        long j11 = ((long[]) zVar.f1842e)[zVar.f1841b];
        Long l = (Long) sVar.f24370e.w(j11);
        o oVar = sVar.f24369b;
        if (l != null && l.longValue() != sVar.f24374i) {
            sVar.f24374i = l.longValue();
            oVar.d(2);
        }
        int iA = sVar.f24369b.a(j11, j3, j10, sVar.f24374i, false, sVar.c);
        l lVar2 = (l) sVar.f24368a.f23345m;
        if (iA != 0 && iA != 1) {
            if (iA != 2 && iA != 3 && iA != 4) {
                if (iA != 5) {
                    throw new IllegalStateException(String.valueOf(iA));
                }
                return;
            }
            sVar.f24375j = j11;
            zVar.B();
            for (i iVar : lVar2.f24335h) {
                iVar.f24327m.execute(new RunnableC2762h(iVar, iVar.l, 2));
            }
            AbstractC1346c.k(null);
            throw null;
        }
        sVar.f24375j = j11;
        long jB = zVar.B();
        q0 q0Var = (q0) sVar.d.w(jB);
        if (q0Var != null && !q0Var.equals(q0.d) && !q0Var.equals(sVar.f24373h)) {
            sVar.f24373h = q0Var;
            C1207o c1207o = new C1207o();
            c1207o.f14252t = q0Var.f14332a;
            c1207o.f14253u = q0Var.f14333b;
            c1207o.f14246m = L.l("video/raw");
            lVar2.f24336i = new C1208p(c1207o);
            for (i iVar2 : lVar2.f24335h) {
                iVar2.f24327m.execute(new RunnableC2762h(iVar2, iVar2.l, q0Var));
            }
        }
        boolean z6 = oVar.d != 3;
        oVar.d = 3;
        oVar.f24349k.getClass();
        oVar.f24344f = AbstractC1343A.H(SystemClock.elapsedRealtime());
        if (z6 && lVar2.l != null) {
            for (i iVar3 : lVar2.f24335h) {
                iVar3.f24327m.execute(new RunnableC2762h(iVar3, iVar3.l, 1));
            }
        }
        if (lVar2.f24337j != null) {
            C1208p c1208p = lVar2.f24336i;
            C1208p c1208p2 = c1208p == null ? new C1208p(new C1207o()) : c1208p;
            n nVar = lVar2.f24337j;
            lVar2.f24334g.getClass();
            nVar.d(jB, System.nanoTime(), c1208p2, null);
        }
        AbstractC1346c.k(null);
        throw null;
    }
}
