package b.b.g.n.i;

import b.b.g.n.q.m;
import b.b.g.p.d;

/* JADX INFO: compiled from: RadioButton3Presenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.m.e.g<b.b.g.q.f.a.e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1033a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.p.d f1034b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f1035c;

    public void c() {
        b.b.g.p.d dVarA = d.g.a();
        this.f1034b = dVarA;
        dVarA.W(getV().getContext());
        this.f1035c = m.p0();
    }

    public void d(int i) {
        b.b.m.c.a.a(f1033a, "----setDayNightModeByIndex------index = " + i);
        this.f1034b.B0(getV().getContext(), i);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        this.f1035c = null;
    }
}
