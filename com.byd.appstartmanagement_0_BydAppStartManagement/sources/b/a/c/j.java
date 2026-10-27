package b.a.c;

import android.content.Context;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Controller;

/* JADX INFO: compiled from: MVCCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f234b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f235c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public n f236d;
    public k e = new k();

    public j(Context context) {
        e(context);
    }

    public void a() {
        b(new m(), new t());
    }

    public void b(d dVar, g gVar) {
        c(dVar, gVar, new Controller());
    }

    public void c(d dVar, g gVar, b bVar) {
        this.e.d("createMVC model: " + dVar);
        this.e.d("createMVC view: " + gVar);
        this.e.d("createMVC controller: " + bVar);
        if (dVar == null || gVar == null || bVar == null) {
            if (dVar == null) {
                this.e.a("createMVC: model is null");
            }
            if (gVar == null) {
                this.e.a("createMVC: view is null");
            }
            if (bVar == null) {
                this.e.a("createMVC: controller is null");
            }
            this.e.a("createMVC: failed");
            return;
        }
        this.f234b = dVar;
        this.f235c = gVar;
        this.f233a = bVar;
        bVar.d(this.e);
        n nVar = new n();
        this.f236d = nVar;
        nVar.c(this.e);
        this.f236d.b(this.f233a);
        this.f234b.q(this.e);
        this.f234b.r(this.f236d);
        this.f234b.p(this);
        this.f235c.q(this.e);
        this.f235c.r(this.f236d);
        this.f235c.p(this);
        this.f234b.K();
        this.f235c.t();
        this.f233a.a();
        this.f233a.b(this.f234b, this.f235c);
    }

    public g d() {
        return this.f235c;
    }

    public void e(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            ComplexData.i(context.getClassLoader());
        }
    }

    public void f(String str) {
        this.e.f(str);
    }
}
