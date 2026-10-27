package e2;

import d5.C1077F;
import d5.C1078G;
import h2.AbstractC1343A;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class k0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public HashMap f14137A;
    public HashSet B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f14140e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14141f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f14142g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f14143h;
    public d5.d0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f14147m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public d5.d0 f14148n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14149p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f14150q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public d5.d0 f14151r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public j0 f14152s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public d5.d0 f14153t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f14154u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f14155v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f14156w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f14157x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f14158y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f14159z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f14138a = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14139b = Integer.MAX_VALUE;
    public int c = Integer.MAX_VALUE;
    public int d = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f14144i = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f14145j = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f14146k = true;

    public k0() {
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        this.l = d0Var;
        this.f14147m = 0;
        this.f14148n = d0Var;
        this.o = 0;
        this.f14149p = Integer.MAX_VALUE;
        this.f14150q = Integer.MAX_VALUE;
        this.f14151r = d0Var;
        this.f14152s = j0.d;
        this.f14153t = d0Var;
        this.f14154u = 0;
        this.f14155v = 0;
        this.f14156w = false;
        this.f14157x = false;
        this.f14158y = false;
        this.f14159z = false;
        this.f14137A = new HashMap();
        this.B = new HashSet();
    }

    public static d5.d0 f(String[] strArr) {
        C1077F c1077fU = d5.I.u();
        for (String str : strArr) {
            str.getClass();
            c1077fU.a(AbstractC1343A.I(str));
        }
        return c1077fU.h();
    }

    public void a(h0 h0Var) {
        this.f14137A.put(h0Var.f14118a, h0Var);
    }

    public l0 b() {
        return new l0(this);
    }

    public k0 c() {
        this.f14137A.clear();
        return this;
    }

    public k0 d(int i7) {
        Iterator it = this.f14137A.values().iterator();
        while (it.hasNext()) {
            if (((h0) it.next()).f14118a.c == i7) {
                it.remove();
            }
        }
        return this;
    }

    public final void e(l0 l0Var) {
        this.f14138a = l0Var.f14195a;
        this.f14139b = l0Var.f14196b;
        this.c = l0Var.c;
        this.d = l0Var.d;
        this.f14140e = l0Var.f14197e;
        this.f14141f = l0Var.f14198f;
        this.f14142g = l0Var.f14199g;
        this.f14143h = l0Var.f14200h;
        this.f14144i = l0Var.f14201i;
        this.f14145j = l0Var.f14202j;
        this.f14146k = l0Var.f14203k;
        this.l = l0Var.l;
        this.f14147m = l0Var.f14204m;
        this.f14148n = l0Var.f14205n;
        this.o = l0Var.o;
        this.f14149p = l0Var.f14206p;
        this.f14150q = l0Var.f14207q;
        this.f14151r = l0Var.f14208r;
        this.f14152s = l0Var.f14209s;
        this.f14153t = l0Var.f14210t;
        this.f14154u = l0Var.f14211u;
        this.f14155v = l0Var.f14212v;
        this.f14156w = l0Var.f14213w;
        this.f14157x = l0Var.f14214x;
        this.f14158y = l0Var.f14215y;
        this.f14159z = l0Var.f14216z;
        this.B = new HashSet(l0Var.B);
        this.f14137A = new HashMap(l0Var.f14194A);
    }

    public k0 g() {
        this.f14155v = -3;
        return this;
    }

    public k0 h(h0 h0Var) {
        g0 g0Var = h0Var.f14118a;
        d(g0Var.c);
        this.f14137A.put(g0Var, h0Var);
        return this;
    }

    public k0 i() {
        return j(new String[0]);
    }

    public k0 j(String... strArr) {
        this.f14153t = f(strArr);
        return this;
    }

    public k0 k() {
        this.f14154u = 0;
        return this;
    }

    public k0 l(int i7) {
        this.B.remove(Integer.valueOf(i7));
        return this;
    }

    public k0 m(int i7, int i10) {
        this.f14144i = i7;
        this.f14145j = i10;
        this.f14146k = true;
        return this;
    }
}
