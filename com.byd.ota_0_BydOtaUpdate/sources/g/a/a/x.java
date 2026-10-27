package g.a.a;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class x extends q implements e, s1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4166d = false;
    public boolean s;
    public e x;

    public x(boolean z, int i2, e eVar) {
        this.s = true;
        this.x = null;
        if (eVar instanceof d) {
            this.s = true;
        } else {
            this.s = z;
        }
        this.f4165c = i2;
        if (!this.s) {
            boolean z2 = eVar.c() instanceof t;
        }
        this.x = eVar;
    }

    public static x n(Object obj) {
        if (obj == null || (obj instanceof x)) {
            return (x) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        try {
            return n(q.j((byte[]) obj));
        } catch (IOException e2) {
            throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e2.getMessage());
        }
    }

    public static x o(x xVar, boolean z) {
        if (z) {
            return (x) xVar.p();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    @Override // g.a.a.s1
    public q a() {
        return c();
    }

    @Override // g.a.a.q
    public boolean g(q qVar) {
        if (!(qVar instanceof x)) {
            return false;
        }
        x xVar = (x) qVar;
        if (this.f4165c != xVar.f4165c || this.f4166d != xVar.f4166d || this.s != xVar.s) {
            return false;
        }
        e eVar = this.x;
        return eVar == null ? xVar.x == null : eVar.c().equals(xVar.x.c());
    }

    @Override // g.a.a.l
    public int hashCode() {
        int i2 = this.f4165c;
        e eVar = this.x;
        return eVar != null ? i2 ^ eVar.hashCode() : i2;
    }

    public boolean isEmpty() {
        return this.f4166d;
    }

    @Override // g.a.a.q
    public q l() {
        return new h1(this.s, this.f4165c, this.x);
    }

    @Override // g.a.a.q
    public q m() {
        return new q1(this.s, this.f4165c, this.x);
    }

    public q p() {
        e eVar = this.x;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public int q() {
        return this.f4165c;
    }

    public boolean r() {
        return this.s;
    }

    public String toString() {
        return "[" + this.f4165c + "]" + this.x;
    }
}
