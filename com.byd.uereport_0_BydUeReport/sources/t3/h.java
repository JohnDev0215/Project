package t3;

import android.content.Context;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Controller;

/* JADX INFO: compiled from: MVCCreator.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f8031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f8032b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f f8033c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l f8034d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f8035e = new i();

    public h(Context context) {
        e(context);
    }

    public void a() {
        b(new k(), new q());
    }

    public void b(c cVar, f fVar) {
        c(cVar, fVar, new Controller());
    }

    public void c(c cVar, f fVar, b bVar) {
        this.f8035e.d("createMVC model: " + cVar);
        this.f8035e.d("createMVC view: " + fVar);
        this.f8035e.d("createMVC controller: " + bVar);
        if (cVar == null || fVar == null || bVar == null) {
            if (cVar == null) {
                this.f8035e.a("createMVC: model is null");
            }
            if (fVar == null) {
                this.f8035e.a("createMVC: view is null");
            }
            if (bVar == null) {
                this.f8035e.a("createMVC: controller is null");
            }
            this.f8035e.a("createMVC: failed");
            return;
        }
        this.f8032b = cVar;
        this.f8033c = fVar;
        this.f8031a = bVar;
        bVar.d(this.f8035e);
        l lVar = new l();
        this.f8034d = lVar;
        lVar.c(this.f8035e);
        this.f8034d.b(this.f8031a);
        this.f8032b.g(this.f8035e);
        this.f8032b.h(this.f8034d);
        this.f8032b.f(this);
        this.f8033c.g(this.f8035e);
        this.f8033c.h(this.f8034d);
        this.f8033c.f(this);
        this.f8032b.i();
        this.f8033c.i();
        this.f8031a.a();
        this.f8031a.b(this.f8032b, this.f8033c);
    }

    public f d() {
        return this.f8033c;
    }

    public void e(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            ComplexData.n(context.getClassLoader());
        }
    }

    public void f(String str) {
        this.f8035e.f(str);
    }
}
