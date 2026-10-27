package V5;

import T.C0753m;
import T.C0761q;
import com.stingray.karaoke.car.ui.composables.swipeRefresh.CircularProgressPainter;
import m7.q;
import n0.C1891t;
import x.AbstractC2631e;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2785n {
    public final /* synthetic */ h l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9501m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f9502n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ boolean f9503p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f9504q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ a f9505r;

    public f(h hVar, boolean z6, o oVar, long j3, boolean z10, float f10, a aVar) {
        this.l = hVar;
        this.f9501m = z6;
        this.f9502n = oVar;
        this.o = j3;
        this.f9503p = z10;
        this.f9504q = f10;
        this.f9505r = aVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1063349516);
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = new CircularProgressPainter();
                c0761q.a0(objG);
            }
            CircularProgressPainter circularProgressPainter = (CircularProgressPainter) objG;
            c0761q.p(false);
            h hVar = this.l;
            circularProgressPainter.f13023s.setValue(new a1.e(hVar.f9509b));
            circularProgressPainter.f13024t.setValue(new a1.e(hVar.c));
            circularProgressPainter.f13026v.setValue(new a1.e(hVar.d));
            circularProgressPainter.f13027w.setValue(new a1.e(hVar.f9510e));
            boolean z6 = this.f9501m;
            o oVar = this.f9502n;
            circularProgressPainter.f13025u.setValue(Boolean.valueOf(z6 && !oVar.b()));
            long j3 = this.o;
            circularProgressPainter.f13021q.setValue(new C1891t(j3));
            circularProgressPainter.f13022r.setValue(Float.valueOf(this.f9503p ? AbstractC2673a.u(oVar.a() / this.f9504q, 0.0f, 1.0f) : 1.0f));
            a aVar = this.f9505r;
            circularProgressPainter.f13030z.setValue(Float.valueOf(((Number) aVar.f9477b.getValue()).floatValue()));
            circularProgressPainter.f13020A.setValue(Float.valueOf(((Number) aVar.c.getValue()).floatValue()));
            circularProgressPainter.B.setValue(Float.valueOf(((Number) aVar.d.getValue()).floatValue()));
            circularProgressPainter.f13028x.setValue(Float.valueOf(((Number) aVar.f9478e.getValue()).floatValue()));
            h6.m.a(Boolean.valueOf(oVar.b()), null, AbstractC2631e.q(100, 0, null, 6), null, b0.g.b(-215787553, new e(hVar, j3, circularProgressPainter), c0761q), c0761q, 24960);
        }
        return q.f19267a;
    }
}
