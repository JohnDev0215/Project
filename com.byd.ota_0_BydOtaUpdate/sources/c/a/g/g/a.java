package c.a.g.g;

import c.a.g.b;
import c.a.g.e.h;
import c.a.g.f.c;
import c.a.g.h.e;
import c.a.g.h.f;
import c.a.g.h.g;
import e.u;
import e.v;
import e.z;
import java.io.File;
import java.util.Map;

/* JADX INFO: compiled from: GcyRemoteModel.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f2688a;

    public static synchronized a l() {
        if (f2688a == null) {
            f2688a = new a();
        }
        return f2688a;
    }

    @Override // c.a.g.b
    public void a(e eVar, String str) {
        h.d().a("loginIn", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void b(e eVar, String str) {
        h.d().a("submitReport", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void c(e eVar, String str) {
        h.d().a("getEcuList", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void d(f fVar, String str, String str2) {
        h.c().a(str, str2).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(fVar);
    }

    @Override // c.a.g.b
    public void e(e eVar, Map<String, Object> map, Map<String, File> map2, String str) {
        v.a aVarF = new v.a().f(v.f3646e);
        for (String str2 : map.keySet()) {
            aVarF.a(str2, map.get(str2).toString());
        }
        for (String str3 : map2.keySet()) {
            aVarF.b("file", str3, new g(str3, map2.get(str3)));
        }
        h.d().a(str, aVarF.e()).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void f(e eVar, String str) {
        h.d().a("checkVersion", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void g(e eVar, String str) {
        h.d().a("downloadReport", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void h(e eVar, String str) {
        h.d().a("monitorDataReport", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void i(e eVar, String str) {
        h.d().a("register", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void j(e eVar, String str) {
        h.d().a("versionSync", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(eVar);
    }

    @Override // c.a.g.b
    public void k(c.a.g.h.c cVar, String str, String str2) {
        h.c().a(str, str2).u(d.a.s.a.b()).o(d.a.s.a.b()).q(c.i()).a(cVar);
    }
}
