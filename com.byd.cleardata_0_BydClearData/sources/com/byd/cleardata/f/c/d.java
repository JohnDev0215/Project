package com.byd.cleardata.f.c;

import com.byd.cleardata.f.a.f;
import com.byd.cleardata.f.a.g;
import com.byd.cleardata.f.a.h;
import com.byd.cleardata.h.e;
import com.byd.cleardata.h.m;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class d extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ExecutorService f2638d;
    private c.a.j.b e;
    private com.byd.cleardata.h.d f;

    class a implements c.a.l.d<m> {
        a() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(m mVar) {
            if (mVar.d() == 2) {
                V v = d.this.f2616a;
                if (v != 0) {
                    ((h) v).n(mVar.a());
                    return;
                }
                return;
            }
            V v2 = d.this.f2616a;
            if (v2 != 0) {
                ((h) v2).e();
            }
        }
    }

    class b implements c.a.l.d<c.a.j.b> {
        b() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(c.a.j.b bVar) {
        }
    }

    public d(h hVar) {
        super(hVar);
        this.f2638d = Executors.newSingleThreadExecutor();
    }

    @Override // com.byd.cleardata.f.c.b
    public void b() {
        super.b();
        c.a.j.b bVar = this.e;
        if (bVar != null) {
            bVar.e();
        }
        com.byd.cleardata.h.d dVar = this.f;
        if (dVar != null) {
            dVar.l();
        }
    }

    public void f() {
        com.byd.cleardata.h.d dVar = this.f;
        if (dVar != null) {
            dVar.l();
        }
    }

    @Override // com.byd.cleardata.f.c.b
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public f c() {
        return new com.byd.cleardata.f.b.c(this);
    }

    public void h(String str) {
        com.byd.cleardata.i.c.b("searchFiles key = " + str);
        f();
        com.byd.cleardata.h.d dVarE = e.e(str);
        this.f = dVarE;
        this.e = dVarE.k(c.a.o.a.a()).e(new b()).f(c.a.i.b.a.a()).h(new a());
    }
}
