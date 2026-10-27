package u2;

import F0.E0;
import android.net.Uri;
import e2.C1192A;
import e2.C1207o;
import e2.C1208p;
import e2.C1212u;
import e2.C1214w;
import e2.C1215x;
import e2.C1216y;
import e2.C1217z;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import y2.ExecutorC2739a;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends AbstractC2498a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final k2.m f22858h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final M.t f22859i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C1208p f22860j;
    public final x2.i l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final g0 f22863n;
    public final e2.F o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public k2.E f22864p;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f22861k = -9223372036854775807L;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f22862m = true;

    public k0(e2.E e10, M.t tVar, x2.i iVar) {
        C1216y c1216y;
        C1192A c1192a;
        this.f22859i = tVar;
        this.l = iVar;
        boolean z6 = true;
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y2 = new C1216y();
        e2.B b10 = e2.B.d;
        Uri uri = Uri.EMPTY;
        String string = e10.f13867a.toString();
        string.getClass();
        d5.I iW = d5.I.w(d5.I.B(e10));
        if (((Uri) e02.f2016e) != null && ((UUID) e02.d) == null) {
            z6 = false;
        }
        AbstractC1346c.j(z6);
        if (uri != null) {
            C1215x c1215x = ((UUID) e02.d) != null ? new C1215x(e02) : null;
            c1216y = c1216y2;
            c1192a = new C1192A(uri, null, c1215x, null, listEmptyList, null, iW, -9223372036854775807L);
        } else {
            c1216y = c1216y2;
            c1192a = null;
        }
        e2.F f10 = new e2.F(string, new C1214w(c1212u), c1192a, new C1217z(c1216y), e2.I.f13914K, b10);
        this.o = f10;
        C1207o c1207o = new C1207o();
        String str = e10.f13868b;
        c1207o.f14246m = e2.L.l(str == null ? "text/x-unknown" : str);
        c1207o.d = e10.c;
        c1207o.f14239e = e10.d;
        c1207o.f14240f = e10.f13869e;
        c1207o.f14238b = e10.f13870f;
        String str2 = e10.f13871g;
        c1207o.f14237a = str2 != null ? str2 : null;
        this.f22860j = new C1208p(c1207o);
        Map mapEmptyMap = Collections.emptyMap();
        Uri uri2 = e10.f13867a;
        AbstractC1346c.l(uri2, "The uri must be set.");
        this.f22858h = new k2.m(uri2, 0L, 1, null, mapEmptyMap, 0L, -1L, null, 1);
        this.f22863n = new g0(-9223372036854775807L, true, false, f10);
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        k2.E e10 = this.f22864p;
        B5.u uVar = new B5.u((CopyOnWriteArrayList) this.c.o, 0, c2492c);
        return new j0(this.f22858h, this.f22859i, e10, this.f22860j, this.f22861k, this.l, uVar, this.f22862m);
    }

    @Override // u2.AbstractC2498a
    public final e2.F h() {
        return this.o;
    }

    @Override // u2.AbstractC2498a
    public final void j() {
    }

    @Override // u2.AbstractC2498a
    public final void l(k2.E e10) {
        this.f22864p = e10;
        m(this.f22863n);
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
        x2.n nVar = ((j0) interfaceC2490A).f22851t;
        x2.k kVar = nVar.f23758b;
        if (kVar != null) {
            kVar.a(true);
        }
        ExecutorC2739a executorC2739a = nVar.f23757a;
        I i7 = executorC2739a.f24118m;
        executorC2739a.l.shutdown();
    }

    @Override // u2.AbstractC2498a
    public final void p() {
    }
}
