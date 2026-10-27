package c.a.b;

import android.content.Context;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Controller;

/* JADX INFO: compiled from: MVCCreator.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f3459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3460b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f f3461c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public l f3462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i f3463e = new i();

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
        this.f3463e.d("createMVC model: " + cVar);
        this.f3463e.d("createMVC view: " + fVar);
        this.f3463e.d("createMVC controller: " + bVar);
        if (cVar == null || fVar == null || bVar == null) {
            if (cVar == null) {
                this.f3463e.a("createMVC: model is null");
            }
            if (fVar == null) {
                this.f3463e.a("createMVC: view is null");
            }
            if (bVar == null) {
                this.f3463e.a("createMVC: controller is null");
            }
            this.f3463e.a("createMVC: failed");
            return;
        }
        this.f3460b = cVar;
        this.f3461c = fVar;
        this.f3459a = bVar;
        bVar.d(this.f3463e);
        l lVar = new l();
        this.f3462d = lVar;
        lVar.c(this.f3463e);
        this.f3462d.b(this.f3459a);
        this.f3460b.g(this.f3463e);
        this.f3460b.h(this.f3462d);
        this.f3460b.f(this);
        this.f3461c.g(this.f3463e);
        this.f3461c.h(this.f3462d);
        this.f3461c.f(this);
        this.f3460b.i();
        this.f3461c.i();
        this.f3459a.a();
        this.f3459a.b(this.f3460b, this.f3461c);
    }

    public f d() {
        return this.f3461c;
    }

    public void e(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            ComplexData.r(context.getClassLoader());
        }
    }

    public void f(String str) {
        this.f3463e.f(str);
    }
}
