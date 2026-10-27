package e;

import e.p;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
public final class x implements e {
    public boolean E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w f3667c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e.d0.g.j f3668d;
    public final p s;
    public final y x;
    public final boolean y;

    /* JADX INFO: compiled from: RealCall.java */
    public final class a extends e.d0.b {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final f f3669d;

        public a(f fVar) {
            super("OkHttp %s", x.this.f());
            this.f3669d = fVar;
        }

        @Override // e.d0.b
        public void k() {
            IOException e2;
            boolean z = true;
            try {
                try {
                    a0 a0VarE = x.this.e();
                    try {
                        if (x.this.f3668d.e()) {
                            this.f3669d.b(x.this, new IOException("Canceled"));
                        } else {
                            this.f3669d.a(x.this, a0VarE);
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        if (z) {
                            e.d0.j.e.h().l(4, "Callback failure for " + x.this.g(), e2);
                        } else {
                            this.f3669d.b(x.this, e2);
                        }
                    }
                } finally {
                    x.this.f3667c.j().e(this);
                }
            } catch (IOException e4) {
                e2 = e4;
                z = false;
            }
        }

        public String l() {
            return x.this.x.h().l();
        }
    }

    public x(w wVar, y yVar, boolean z) {
        p.c cVarL = wVar.l();
        this.f3667c = wVar;
        this.x = yVar;
        this.y = z;
        this.f3668d = new e.d0.g.j(wVar, z);
        this.s = cVarL.a(this);
    }

    public final void a() {
        this.f3668d.i(e.d0.j.e.h().j("response.body().close()"));
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public x clone() {
        return new x(this.f3667c, this.x, this.y);
    }

    @Override // e.e
    public a0 c() throws IOException {
        synchronized (this) {
            if (this.E3) {
                throw new IllegalStateException("Already Executed");
            }
            this.E3 = true;
        }
        a();
        try {
            this.f3667c.j().b(this);
            a0 a0VarE = e();
            if (a0VarE != null) {
                return a0VarE;
            }
            throw new IOException("Canceled");
        } finally {
            this.f3667c.j().f(this);
        }
    }

    @Override // e.e
    public void cancel() {
        this.f3668d.b();
    }

    @Override // e.e
    public boolean d() {
        return this.f3668d.e();
    }

    public a0 e() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f3667c.p());
        arrayList.add(this.f3668d);
        arrayList.add(new e.d0.g.a(this.f3667c.i()));
        arrayList.add(new e.d0.e.a(this.f3667c.q()));
        arrayList.add(new e.d0.f.a(this.f3667c));
        if (!this.y) {
            arrayList.addAll(this.f3667c.r());
        }
        arrayList.add(new e.d0.g.b(this.y));
        return new e.d0.g.g(arrayList, null, null, null, 0, this.x).a(this.x);
    }

    public String f() {
        return this.x.h().B();
    }

    public String g() {
        StringBuilder sb = new StringBuilder();
        sb.append(d() ? "canceled " : "");
        sb.append(this.y ? "web socket" : "call");
        sb.append(" to ");
        sb.append(f());
        return sb.toString();
    }

    @Override // e.e
    public void s(f fVar) {
        synchronized (this) {
            if (this.E3) {
                throw new IllegalStateException("Already Executed");
            }
            this.E3 = true;
        }
        a();
        this.f3667c.j().a(new a(fVar));
    }
}
