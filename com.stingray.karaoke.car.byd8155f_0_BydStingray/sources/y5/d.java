package Y5;

import A7.m;
import E0.X;
import K.L0;
import V8.AbstractC0828q;
import V8.N;
import V8.f0;
import android.content.Context;
import android.util.SparseBooleanArray;
import h2.AbstractC1346c;
import j2.C1538a;
import java.io.File;
import k2.r;
import l2.q;
import l2.t;
import n2.C1914n;
import n2.C1915o;
import n2.C1923x;
import w2.C2594h;
import w2.C2595i;
import w2.o;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f10785a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X f10786b;
    public final c c;
    public final o d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f0 f10787e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final N f10788f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f0 f10789g;

    public d(Context context) {
        m.f(context, "appContext");
        this.f10785a = context;
        this.c = new c(this);
        o oVar = new o(context);
        C2595i c2595iE = oVar.e();
        c2595iE.getClass();
        C2594h c2594h = new C2594h(c2595iE);
        SparseBooleanArray sparseBooleanArray = c2594h.f23208K;
        if (!sparseBooleanArray.get(1)) {
            sparseBooleanArray.put(1, true);
        }
        oVar.j(new C2595i(c2594h));
        this.d = oVar;
        C1915o c1915o = new C1915o(context);
        AbstractC1346c.j(!c1915o.f19715t);
        c1915o.f19702e = new C1914n(oVar, 1);
        C1923x c1923xA = c1915o.a();
        c1923xA.h(1);
        c1923xA.q(true);
        f0 f0VarC = AbstractC0828q.c(c1923xA);
        this.f10787e = f0VarC;
        this.f10788f = new N(f0VarC);
        this.f10789g = AbstractC0828q.c(null);
        File file = new File(context.getFilesDir(), "downloads");
        e eVar = e.f10790b;
        if (eVar.f10791a == null) {
            eVar.f10791a = new t(file, new q(), new C1538a(context.getApplicationContext(), "exoplayer_internal.db", null, 1));
        }
        t tVar = eVar.f10791a;
        if (tVar != null) {
            e7.f fVar = new e7.f((char) 0, 28);
            fVar.f14480m = tVar;
            M.t tVar2 = new M.t(context, new L0(5));
            r rVar = new r();
            X x8 = new X(2);
            x8.o = tVar;
            x8.f1598q = fVar;
            x8.f1596n = false;
            x8.f1597p = rVar;
            x8.f1599r = tVar2;
            x8.f1595m = 2;
            this.f10786b = x8;
        }
    }
}
