package X5;

import S8.AbstractC0727y;
import V8.AbstractC0828q;
import g6.C1310h;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class V0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final N5.Z f10206a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final J5.k f10207b;
    public final C1310h c;
    public final X8.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final X8.c f10208e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f10209f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final V8.f0 f10210g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final V8.N f10211h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f10212i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f10213j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final V8.f0 f10214k;
    public String l;

    public V0(N5.Z z6, J5.k kVar, C1310h c1310h) {
        A7.m.f(z6, "playerManager");
        A7.m.f(kVar, "resourceFileService");
        A7.m.f(c1310h, "scoringTestRepoManager");
        this.f10206a = z6;
        this.f10207b = kVar;
        this.c = c1310h;
        Z8.e eVar = S8.E.f8084a;
        X8.c cVarA = AbstractC0727y.a(Z8.d.f11010n);
        this.d = cVarA;
        this.f10208e = AbstractC0727y.a(X8.m.f10573a);
        this.f10209f = new Object();
        V8.f0 f0VarC = AbstractC0828q.c(null);
        this.f10210g = f0VarC;
        this.f10211h = new V8.N(f0VarC);
        this.f10213j = new Object();
        V8.f0 f0VarC2 = AbstractC0828q.c(null);
        this.f10214k = f0VarC2;
        new V8.N(f0VarC2);
        AbstractC0727y.w(cVarA, null, null, new J0(this, null), 3);
        AbstractC0727y.w(cVarA, null, null, new L0(this, null), 3);
        AbstractC0727y.w(cVarA, null, null, new O0(this, null), 3);
    }

    public static final void a(V0 v02, String str) {
        if (h6.n.f15589g) {
            AbstractC0727y.w(v02.d, null, null, new T0(v02, str, null), 3);
        } else {
            v02.c(str);
        }
    }

    public final void b(String str) {
        synchronized (this.f10209f) {
            if (str != null) {
                try {
                    if (str.length() != 0) {
                        if (!A7.m.a(this.f10212i, str)) {
                            this.f10210g.l(null);
                            this.f10206a.f4623A = 0;
                            this.f10212i = str;
                        } else if (this.f10210g.getValue() != null) {
                            return;
                        }
                        AbstractC0727y.w(this.d, null, null, new P0(this, str, null), 3);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f10210g.l(null);
        }
    }

    public final void c(String str) {
        synchronized (this.f10213j) {
            if (str != null) {
                try {
                    if (str.length() != 0) {
                        if (!A7.m.a(this.l, str)) {
                            this.f10214k.l(null);
                            this.l = str;
                        } else if (this.f10214k.getValue() != null) {
                            return;
                        }
                        AbstractC0727y.w(this.d, null, null, new Q0(this, str, null), 3);
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f10214k.l(null);
        }
    }

    public final void d(IOException iOException) {
        AbstractC0727y.w(this.f10208e, null, null, new R0(this, iOException, null), 3);
    }
}
