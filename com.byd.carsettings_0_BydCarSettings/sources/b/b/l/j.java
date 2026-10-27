package b.b.l;

import android.content.Context;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Controller;

/* JADX INFO: compiled from: MVCCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1986a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1987b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f1988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public n f1989d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k f1990e = new k();

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
        this.f1990e.d("createMVC model: " + dVar);
        this.f1990e.d("createMVC view: " + gVar);
        this.f1990e.d("createMVC controller: " + bVar);
        if (dVar == null || gVar == null || bVar == null) {
            if (dVar == null) {
                this.f1990e.a("createMVC: model is null");
            }
            if (gVar == null) {
                this.f1990e.a("createMVC: view is null");
            }
            if (bVar == null) {
                this.f1990e.a("createMVC: controller is null");
            }
            this.f1990e.a("createMVC: failed");
            return;
        }
        this.f1987b = dVar;
        this.f1988c = gVar;
        this.f1986a = bVar;
        bVar.d(this.f1990e);
        n nVar = new n();
        this.f1989d = nVar;
        nVar.c(this.f1990e);
        this.f1989d.b(this.f1986a);
        this.f1987b.q(this.f1990e);
        this.f1987b.r(this.f1989d);
        this.f1987b.p(this);
        this.f1988c.q(this.f1990e);
        this.f1988c.r(this.f1989d);
        this.f1988c.p(this);
        this.f1987b.K();
        this.f1988c.t();
        this.f1986a.a();
        this.f1986a.b(this.f1987b, this.f1988c);
    }

    public g d() {
        return this.f1988c;
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
        this.f1990e.f(str);
    }
}
