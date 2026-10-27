package com.qtxa.qtxadefend;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final v f146b;
    private final j c;
    private final l d;
    private final Context e;
    private final t f;
    private final x g;
    private final f h;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.qtxa.qtxadefend.a.j().k(u.this);
            com.qtxa.qtxadefend.a.j().m("qtxa.appsec.rule");
            y.d().g("qtxa.systemerr.rule");
        }
    }

    u(Context context) {
        this.e = context;
        w.d("SecurityContext");
        this.f146b = new v(this);
        j jVarA = j.a();
        this.c = jVarA;
        jVarA.b(this);
        this.d = new l(this);
        this.f145a = new c(this);
        this.f = new t(context);
        z.i().l(this.e);
        y.d().e(this.e);
        this.g = new x(this);
        this.h = new f("QtEventHandler");
    }

    c a() {
        return this.f145a;
    }

    t b() {
        return this.f;
    }

    Context c() {
        return this.e;
    }

    f d() {
        return this.h;
    }

    j e() {
        return this.c;
    }

    l f() {
        return this.d;
    }

    v g() {
        return this.f146b;
    }

    x h() {
        return this.g;
    }

    void i() {
        e.c().d(this.f146b, this.e);
        this.h.start();
        this.h.a(new a());
    }

    void j() {
        this.h.quit();
        a0.a().c();
        e.c().e();
        this.f146b.k();
    }
}
