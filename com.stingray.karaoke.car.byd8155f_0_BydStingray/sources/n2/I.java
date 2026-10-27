package n2;

import android.util.Pair;
import e2.f0;
import h2.AbstractC1346c;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import u2.C2492C;
import u2.C2500c;
import u2.InterfaceC2490A;
import u2.n0;

/* JADX INFO: loaded from: classes.dex */
public final class I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f19538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19539b;
    public final u2.a0[] c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f19540e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f19541f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public J f19542g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f19543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean[] f19544i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbstractC1905e[] f19545j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final w2.r f19546k;
    public final Y4.m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public I f19547m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n0 f19548n;
    public w2.s o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f19549p;

    public I(AbstractC1905e[] abstractC1905eArr, long j3, w2.r rVar, x2.e eVar, Y4.m mVar, J j10, w2.s sVar) {
        this.f19545j = abstractC1905eArr;
        this.f19549p = j3;
        this.f19546k = rVar;
        this.l = mVar;
        C2492C c2492c = j10.f19550a;
        this.f19539b = c2492c.f22663a;
        this.f19542g = j10;
        this.f19548n = n0.d;
        this.o = sVar;
        this.c = new u2.a0[abstractC1905eArr.length];
        this.f19544i = new boolean[abstractC1905eArr.length];
        mVar.getClass();
        int i7 = Z.f19611n;
        Pair pair = (Pair) c2492c.f22663a;
        Object obj = pair.first;
        C2492C c2492cA = c2492c.a(pair.second);
        T t10 = (T) ((HashMap) mVar.f10760e).get(obj);
        t10.getClass();
        ((HashSet) mVar.f10763h).add(t10);
        S s6 = (S) ((HashMap) mVar.f10762g).get(t10);
        if (s6 != null) {
            s6.f19580a.e(s6.f19581b);
        }
        t10.c.add(c2492cA);
        InterfaceC2490A interfaceC2490AB = t10.f19582a.b(c2492cA, eVar, j10.f19551b);
        ((IdentityHashMap) mVar.d).put(interfaceC2490AB, t10);
        mVar.d();
        long j11 = j10.d;
        this.f19538a = j11 != -9223372036854775807L ? new C2500c(interfaceC2490AB, true, 0L, j11) : interfaceC2490AB;
    }

    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, u2.A] */
    public final long a(w2.s sVar, long j3, boolean z6, boolean[] zArr) {
        AbstractC1905e[] abstractC1905eArr;
        u2.a0[] a0VarArr;
        int i7 = 0;
        while (true) {
            boolean z10 = true;
            if (i7 >= sVar.f23271a) {
                break;
            }
            if (z6 || !sVar.a(this.o, i7)) {
                z10 = false;
            }
            this.f19544i[i7] = z10;
            i7++;
        }
        int i10 = 0;
        while (true) {
            abstractC1905eArr = this.f19545j;
            int length = abstractC1905eArr.length;
            a0VarArr = this.c;
            if (i10 >= length) {
                break;
            }
            if (abstractC1905eArr[i10].f19636m == -2) {
                a0VarArr[i10] = null;
            }
            i10++;
        }
        b();
        this.o = sVar;
        c();
        long jK = this.f19538a.k(sVar.c, this.f19544i, this.c, zArr, j3);
        for (int i11 = 0; i11 < abstractC1905eArr.length; i11++) {
            if (abstractC1905eArr[i11].f19636m == -2 && this.o.b(i11)) {
                a0VarArr[i11] = new l9.n(13);
            }
        }
        this.f19541f = false;
        for (int i12 = 0; i12 < a0VarArr.length; i12++) {
            if (a0VarArr[i12] != null) {
                AbstractC1346c.j(sVar.b(i12));
                if (abstractC1905eArr[i12].f19636m != -2) {
                    this.f19541f = true;
                }
            } else {
                AbstractC1346c.j(sVar.c[i12] == null);
            }
        }
        return jK;
    }

    public final void b() {
        if (this.f19547m != null) {
            return;
        }
        int i7 = 0;
        while (true) {
            w2.s sVar = this.o;
            if (i7 >= sVar.f23271a) {
                return;
            }
            boolean zB = sVar.b(i7);
            w2.q qVar = this.o.c[i7];
            if (zB && qVar != null) {
                qVar.e();
            }
            i7++;
        }
    }

    public final void c() {
        if (this.f19547m != null) {
            return;
        }
        int i7 = 0;
        while (true) {
            w2.s sVar = this.o;
            if (i7 >= sVar.f23271a) {
                return;
            }
            boolean zB = sVar.b(i7);
            w2.q qVar = this.o.c[i7];
            if (zB && qVar != null) {
                qVar.c();
            }
            i7++;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, u2.b0] */
    public final long d() {
        if (!this.f19540e) {
            return this.f19542g.f19551b;
        }
        long jL = this.f19541f ? this.f19538a.l() : Long.MIN_VALUE;
        return jL == Long.MIN_VALUE ? this.f19542g.f19552e : jL;
    }

    public final long e() {
        return this.f19542g.f19551b + this.f19549p;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, u2.A] */
    public final void f(float f10, f0 f0Var, boolean z6) {
        this.f19540e = true;
        this.f19548n = this.f19538a.j();
        w2.s sVarJ = j(f10, f0Var, z6);
        J j3 = this.f19542g;
        long j10 = j3.f19552e;
        long jMax = j3.f19551b;
        if (j10 != -9223372036854775807L && jMax >= j10) {
            jMax = Math.max(0L, j10 - 1);
        }
        long jA = a(sVarJ, jMax, false, new boolean[this.f19545j.length]);
        long j11 = this.f19549p;
        J j12 = this.f19542g;
        this.f19549p = (j12.f19551b - jA) + j11;
        this.f19542g = j12.b(jA);
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, u2.b0] */
    public final boolean g() {
        return this.f19540e && (!this.f19541f || this.f19538a.l() == Long.MIN_VALUE);
    }

    public final boolean h() {
        return this.f19540e && (g() || d() - this.f19542g.f19551b >= -9223372036854775807L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, u2.A] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void i() {
        b();
        ?? r02 = this.f19538a;
        try {
            boolean z6 = r02 instanceof C2500c;
            Y4.m mVar = this.l;
            if (z6) {
                mVar.h(((C2500c) r02).l);
            } else {
                mVar.h(r02);
            }
        } catch (RuntimeException e10) {
            AbstractC1346c.q("MediaPeriodHolder", "Period release failed.", e10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:141:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0370  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final w2.s j(float r33, e2.f0 r34, boolean r35) {
        /*
            Method dump skipped, instruction units count: 2125
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: n2.I.j(float, e2.f0, boolean):w2.s");
    }

    public final void k() {
        Object obj = this.f19538a;
        if (obj instanceof C2500c) {
            long j3 = this.f19542g.d;
            if (j3 == -9223372036854775807L) {
                j3 = Long.MIN_VALUE;
            }
            C2500c c2500c = (C2500c) obj;
            c2500c.f22800p = 0L;
            c2500c.f22801q = j3;
        }
    }
}
