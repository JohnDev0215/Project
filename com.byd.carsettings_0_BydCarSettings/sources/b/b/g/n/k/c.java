package b.b.g.n.k;

import b.b.g.p.d;
import com.byd.carsettings.ui.activity.ContainerActivity;
import java.util.Locale;

/* JADX INFO: compiled from: ContainerPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b.b.m.e.g<ContainerActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.p.d f1101a;

    public c() {
        b.b.g.p.d dVarA = d.g.a();
        this.f1101a = dVarA;
        dVarA.W(getV());
    }

    public int c() {
        Locale localeV = this.f1101a.v(getV().getApplicationContext());
        b.b.g.p.m.a.g(getV().getApplicationContext());
        return b.b.g.p.m.a.a(localeV);
    }

    public int d() {
        Locale localeV = this.f1101a.v(getV().getApplicationContext());
        b.b.g.p.m.a.g(getV().getApplicationContext());
        return b.b.g.p.m.a.c(localeV);
    }
}
