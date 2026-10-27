package com.byd.cleardata.f.c;

import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.byd.cleardata.f.a.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final c.a.j.a f2612d;

    /* JADX INFO: renamed from: com.byd.cleardata.f.c.a$a, reason: collision with other inner class name */
    class C0091a implements c.a.l.d<List<com.byd.cleardata.d.b>> {
        C0091a() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(List<com.byd.cleardata.d.b> list) {
            ((com.byd.cleardata.f.a.b) a.this.f2616a).s(list);
        }
    }

    class b implements c.a.l.d<Boolean> {
        b() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Boolean bool) {
            ((com.byd.cleardata.f.a.b) a.this.f2616a).g(0, bool.booleanValue());
        }
    }

    class c implements c.a.l.d<Boolean> {
        c() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Boolean bool) {
            ((com.byd.cleardata.f.a.b) a.this.f2616a).g(1, bool.booleanValue());
        }
    }

    public a(com.byd.cleardata.f.a.b bVar) {
        super(bVar);
        this.f2612d = new c.a.j.a();
    }

    @Override // com.byd.cleardata.f.c.b
    public com.byd.cleardata.f.b.b c() {
        return null;
    }

    public void f() {
        this.f2612d.c(com.byd.cleardata.g.d.b(null).k(c.a.o.a.b()).f(c.a.i.b.a.a()).h(new c()));
    }

    public void g(String str) {
        if (TextUtils.isEmpty(str)) {
            ((com.byd.cleardata.f.a.b) this.f2616a).g(0, false);
        } else {
            this.f2612d.c(com.byd.cleardata.g.d.c(str).k(c.a.o.a.b()).f(c.a.i.b.a.a()).h(new b()));
        }
    }

    public void h() {
        this.f2612d.c(com.byd.cleardata.g.d.d().k(c.a.o.a.b()).f(c.a.i.b.a.a()).h(new C0091a()));
    }
}
