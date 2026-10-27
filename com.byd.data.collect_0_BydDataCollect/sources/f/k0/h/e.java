package f.k0.h;

import f.b0;
import f.i0;
import f.k0.h.j;
import f.t;
import f.y;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/* JADX INFO: compiled from: ExchangeFinder.java */
/* JADX INFO: loaded from: classes.dex */
final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f5181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f.e f5182b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f5183c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f.i f5184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final t f5185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private j.a f5186f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final j f5187g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private f f5188h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f5189i;
    private i0 j;

    e(k kVar, g gVar, f.e eVar, f.i iVar, t tVar) {
        this.f5181a = kVar;
        this.f5183c = gVar;
        this.f5182b = eVar;
        this.f5184d = iVar;
        this.f5185e = tVar;
        this.f5187g = new j(eVar, gVar.f5203f, iVar, tVar);
    }

    private f c(int i2, int i3, int i4, int i5, boolean z) throws Throwable {
        f fVar;
        Socket socketT;
        Socket socketN;
        f fVar2;
        boolean z2;
        i0 i0VarC;
        boolean z3;
        List<i0> listA;
        j.a aVar;
        synchronized (this.f5183c) {
            if (this.f5181a.i()) {
                throw new IOException("Canceled");
            }
            this.f5189i = false;
            k kVar = this.f5181a;
            fVar = kVar.f5224i;
            socketT = null;
            socketN = (fVar == null || !fVar.k) ? null : kVar.n();
            k kVar2 = this.f5181a;
            fVar2 = kVar2.f5224i;
            if (fVar2 != null) {
                fVar = null;
            } else {
                fVar2 = null;
            }
            if (fVar2 != null) {
                z2 = false;
                i0VarC = null;
            } else if (this.f5183c.h(this.f5182b, kVar2, null, false)) {
                fVar2 = this.f5181a.f5224i;
                i0VarC = null;
                z2 = true;
            } else {
                i0VarC = this.j;
                if (i0VarC != null) {
                    this.j = null;
                } else {
                    if (g()) {
                        i0VarC = this.f5181a.f5224i.r();
                    }
                    z2 = false;
                    i0VarC = null;
                }
                z2 = false;
            }
        }
        f.k0.e.g(socketN);
        if (fVar != null) {
            this.f5185e.h(this.f5184d, fVar);
        }
        if (z2) {
            this.f5185e.g(this.f5184d, fVar2);
        }
        if (fVar2 != null) {
            return fVar2;
        }
        if (i0VarC != null || ((aVar = this.f5186f) != null && aVar.b())) {
            z3 = false;
        } else {
            this.f5186f = this.f5187g.d();
            z3 = true;
        }
        synchronized (this.f5183c) {
            if (this.f5181a.i()) {
                throw new IOException("Canceled");
            }
            if (z3) {
                listA = this.f5186f.a();
                if (this.f5183c.h(this.f5182b, this.f5181a, listA, false)) {
                    fVar2 = this.f5181a.f5224i;
                    z2 = true;
                }
            } else {
                listA = null;
            }
            if (!z2) {
                if (i0VarC == null) {
                    i0VarC = this.f5186f.c();
                }
                fVar2 = new f(this.f5183c, i0VarC);
                this.f5188h = fVar2;
            }
        }
        if (z2) {
            this.f5185e.g(this.f5184d, fVar2);
            return fVar2;
        }
        fVar2.e(i2, i3, i4, i5, z, this.f5184d, this.f5185e);
        this.f5183c.f5203f.a(fVar2.r());
        synchronized (this.f5183c) {
            this.f5188h = null;
            if (this.f5183c.h(this.f5182b, this.f5181a, listA, true)) {
                fVar2.k = true;
                socketT = fVar2.t();
                fVar2 = this.f5181a.f5224i;
                this.j = i0VarC;
            } else {
                this.f5183c.g(fVar2);
                this.f5181a.a(fVar2);
            }
        }
        f.k0.e.g(socketT);
        this.f5185e.g(this.f5184d, fVar2);
        return fVar2;
    }

    private f d(int i2, int i3, int i4, int i5, boolean z, boolean z2) throws Throwable {
        while (true) {
            f fVarC = c(i2, i3, i4, i5, z);
            synchronized (this.f5183c) {
                if (fVarC.m == 0 && !fVarC.o()) {
                    return fVarC;
                }
                if (fVarC.n(z2)) {
                    return fVarC;
                }
                fVarC.q();
            }
        }
    }

    private boolean g() {
        f fVar = this.f5181a.f5224i;
        return fVar != null && fVar.l == 0 && f.k0.e.D(fVar.r().a().l(), this.f5182b.l());
    }

    f a() {
        return this.f5188h;
    }

    public f.k0.i.c b(b0 b0Var, y.a aVar, boolean z) {
        try {
            return d(aVar.e(), aVar.f(), aVar.c(), b0Var.v(), b0Var.D(), z).p(b0Var, aVar);
        } catch (i e2) {
            h();
            throw e2;
        } catch (IOException e3) {
            h();
            throw new i(e3);
        }
    }

    boolean e() {
        synchronized (this.f5183c) {
            boolean z = true;
            if (this.j != null) {
                return true;
            }
            if (g()) {
                this.j = this.f5181a.f5224i.r();
                return true;
            }
            j.a aVar = this.f5186f;
            if ((aVar == null || !aVar.b()) && !this.f5187g.b()) {
                z = false;
            }
            return z;
        }
    }

    boolean f() {
        boolean z;
        synchronized (this.f5183c) {
            z = this.f5189i;
        }
        return z;
    }

    void h() {
        synchronized (this.f5183c) {
            this.f5189i = true;
        }
    }
}
