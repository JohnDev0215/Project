package f;

import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
public final class v implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f5412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f.b0.g.j f5413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w f5414d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f5415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5416f;

    /* JADX INFO: compiled from: RealCall.java */
    public final class a extends f.b0.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f f5417c;

        public a(f fVar) {
            super("OkHttp %s", v.this.e());
            this.f5417c = fVar;
        }

        @Override // f.b0.b
        public void k() {
            IOException e2;
            y yVarC;
            boolean z = true;
            try {
                try {
                    yVarC = v.this.c();
                } catch (IOException e3) {
                    e2 = e3;
                    z = false;
                }
                try {
                    if (v.this.f5413c.e()) {
                        this.f5417c.onFailure(v.this, new IOException("Canceled"));
                    } else {
                        this.f5417c.onResponse(v.this, yVarC);
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    if (z) {
                        f.b0.j.e.h().l(4, "Callback failure for " + v.this.f(), e2);
                    } else {
                        this.f5417c.onFailure(v.this, e2);
                    }
                }
            } finally {
                v.this.f5412b.g().e(this);
            }
        }

        public String l() {
            return v.this.f5414d.h().l();
        }
    }

    public v(u uVar, w wVar, boolean z) {
        this.f5412b = uVar;
        this.f5414d = wVar;
        this.f5415e = z;
        this.f5413c = new f.b0.g.j(uVar, z);
    }

    public final void a() {
        this.f5413c.i(f.b0.j.e.h().j("response.body().close()"));
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public v clone() {
        return new v(this.f5412b, this.f5414d, this.f5415e);
    }

    public y c() throws IOException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f5412b.l());
        arrayList.add(this.f5413c);
        arrayList.add(new f.b0.g.a(this.f5412b.f()));
        arrayList.add(new f.b0.e.a(this.f5412b.m()));
        arrayList.add(new f.b0.f.a(this.f5412b));
        if (!this.f5415e) {
            arrayList.addAll(this.f5412b.n());
        }
        arrayList.add(new f.b0.g.b(this.f5415e));
        return new f.b0.g.g(arrayList, null, null, null, 0, this.f5414d).a(this.f5414d);
    }

    @Override // f.e
    public void cancel() {
        this.f5413c.b();
    }

    public boolean d() {
        return this.f5413c.e();
    }

    public String e() {
        return this.f5414d.h().A();
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        sb.append(d() ? "canceled " : "");
        sb.append(this.f5415e ? "web socket" : "call");
        sb.append(" to ");
        sb.append(e());
        return sb.toString();
    }

    @Override // f.e
    public void w(f fVar) {
        synchronized (this) {
            if (this.f5416f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f5416f = true;
        }
        a();
        this.f5412b.g().a(new a(fVar));
    }

    @Override // f.e
    public y z() throws IOException {
        synchronized (this) {
            if (this.f5416f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f5416f = true;
        }
        a();
        try {
            this.f5412b.g().b(this);
            y yVarC = c();
            if (yVarC != null) {
                return yVarC;
            }
            throw new IOException("Canceled");
        } finally {
            this.f5412b.g().f(this);
        }
    }
}
