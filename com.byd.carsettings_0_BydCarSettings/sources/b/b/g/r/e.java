package b.b.g.r;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.core.view.InputDeviceCompat;
import b.b.g.o.c;
import b.b.i.a;
import b.b.i.b;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoServiceUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b.b.g.k.h f1796a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static b.b.g.k.j f1797b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static b.b.g.k.l f1798c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static b.b.g.k.p f1799d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static b.b.g.k.k f1800e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static b.b.g.k.s f1801f = null;
    public static b.b.g.k.n g = null;
    public static b.b.g.k.r h = null;
    public static b.b.g.k.o i = null;
    public static b.b.g.k.g j = null;
    public static b.b.g.k.d k = null;
    public static b.b.g.k.e l = null;
    public static b.b.g.k.c m = null;
    public static b.b.g.k.a n = null;
    public static b.b.g.k.m o = null;
    public static y p = null;
    public static b.b.g.k.f q = null;
    public static volatile boolean r = false;

    /* JADX INFO: compiled from: BYDAutoServiceUtils.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1802a;

        public a(Context context) {
            this.f1802a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.p = new y();
            b.b.j.e.a(this.f1802a);
        }
    }

    public static void a(Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static boolean b(int i2) {
        return b.b.g.n.q.b.c().e(i2) != 0;
    }

    public static boolean c(int i2) {
        y yVar = p;
        if (yVar != null) {
            return yVar.hasMessages(i2);
        }
        return false;
    }

    public static void d(Context context) {
        if (r) {
            return;
        }
        r = true;
        BydLogHelper.logD("init device start");
        c.d.a().r(context);
        a.e.a().b(context);
        b.C0080b.a().a(context);
        b.b.a.f.h.a().d(new a(context));
        b.b.g.n.q.c.K().f0(context);
        b.b.g.n.q.e.j().C(context);
        b.b.g.n.q.h.m().D(context);
        b.b.g.n.q.s.g().q(context);
        b.b.g.n.q.o.m().v(context);
        b.b.g.n.q.f.g().i(context);
        b.b.g.n.q.k.c().h(context);
        b.b.g.n.q.r.b().p(context);
        b.b.g.n.q.l.b().g(context);
        b.b.g.n.q.d.x().b0(context);
        b.b.g.n.q.a.a().c(context);
        b.b.g.n.q.p.c().e(context);
        b.b.g.n.q.n.b().d();
        b.b.g.n.q.j.a().c(context);
        b.b.g.n.q.q.b().c();
        BydLogHelper.logD("init devices end");
    }

    public static boolean e(b.b.a.c.a aVar) {
        return (aVar == null || aVar.i() == null || ((Integer) aVar.i()).intValue() != -256) ? false : true;
    }

    public static void f(a.b.d... dVarArr) {
        if (dVarArr == null) {
            return;
        }
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            if (dVarArr[i2] instanceof b.b.g.k.s) {
                b.b.g.n.q.s.g().r((b.b.g.k.s) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.l) {
                b.b.g.n.q.h.m().G((b.b.g.k.l) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.d) {
                b.b.g.n.q.g.g().i((b.b.g.k.d) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.r) {
                b.b.g.n.q.r.b().q((b.b.g.k.r) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.p) {
                b.b.g.n.q.o.m().w((b.b.g.k.p) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.j) {
                b.b.g.n.q.e.j().D((b.b.g.k.j) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.h) {
                b.b.g.n.q.c.K().g0((b.b.g.k.h) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.n) {
                b.b.g.n.q.k.c().i((b.b.g.k.n) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.o) {
                b.b.g.n.q.l.b().h((b.b.g.k.o) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.i) {
                b.b.g.n.q.d.x().c0((b.b.g.k.i) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.k) {
                b.b.g.n.q.f.g().j((b.b.g.k.k) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.m) {
                b.b.g.n.q.j.a().d((b.b.g.k.m) dVarArr[i2]);
            }
        }
    }

    public static void g() {
        BydLogHelper.logD("BYDAutoServiceUtils", "register listener start");
        b.b.g.n.q.c cVarK = b.b.g.n.q.c.K();
        b.b.g.k.h hVar = new b.b.g.k.h();
        f1796a = hVar;
        cVarK.g0(hVar);
        b.b.g.n.q.e eVarJ = b.b.g.n.q.e.j();
        b.b.g.k.j jVar = new b.b.g.k.j();
        f1797b = jVar;
        eVarJ.D(jVar);
        b.b.g.n.q.h hVarM = b.b.g.n.q.h.m();
        b.b.g.k.l lVar = new b.b.g.k.l();
        f1798c = lVar;
        hVarM.G(lVar);
        b.b.g.n.q.o oVarM = b.b.g.n.q.o.m();
        b.b.g.k.p pVar = new b.b.g.k.p();
        f1799d = pVar;
        oVarM.w(pVar);
        b.b.g.n.q.f fVarG = b.b.g.n.q.f.g();
        b.b.g.k.k kVar = new b.b.g.k.k();
        f1800e = kVar;
        fVarG.j(kVar);
        b.b.g.n.q.s sVarG = b.b.g.n.q.s.g();
        b.b.g.k.s sVar = new b.b.g.k.s();
        f1801f = sVar;
        sVarG.r(sVar);
        b.b.g.n.q.k kVarC = b.b.g.n.q.k.c();
        b.b.g.k.n nVar = new b.b.g.k.n();
        g = nVar;
        kVarC.i(nVar);
        b.b.g.n.q.r rVarB = b.b.g.n.q.r.b();
        b.b.g.k.r rVar = new b.b.g.k.r();
        h = rVar;
        rVarB.q(rVar);
        b.b.g.n.q.l lVarB = b.b.g.n.q.l.b();
        b.b.g.k.o oVar = new b.b.g.k.o();
        i = oVar;
        lVarB.h(oVar);
        b.b.g.n.q.m mVarP0 = b.b.g.n.q.m.p0();
        b.b.g.k.g gVar = new b.b.g.k.g();
        j = gVar;
        mVarP0.K1(gVar);
        b.b.g.n.q.g gVarG = b.b.g.n.q.g.g();
        b.b.g.k.d dVar = new b.b.g.k.d();
        k = dVar;
        gVarG.i(dVar);
        b.b.g.n.q.i iVarK = b.b.g.n.q.i.k();
        b.b.g.k.e eVar = new b.b.g.k.e();
        l = eVar;
        iVarK.n(eVar);
        b.b.g.n.q.a aVarA = b.b.g.n.q.a.a();
        b.b.g.k.c cVar = new b.b.g.k.c();
        m = cVar;
        aVarA.d(cVar);
        b.b.g.n.q.b bVarC = b.b.g.n.q.b.c();
        b.b.g.k.a aVar = new b.b.g.k.a();
        n = aVar;
        bVarC.h(aVar);
        b.b.g.n.q.j jVarA = b.b.g.n.q.j.a();
        b.b.g.k.m mVar = new b.b.g.k.m();
        o = mVar;
        jVarA.d(mVar);
        b.b.g.n.q.n nVarB = b.b.g.n.q.n.b();
        b.b.g.k.f fVar = new b.b.g.k.f();
        q = fVar;
        nVarB.e(fVar);
        c.d.a().v();
        BydLogHelper.logD("BYDAutoServiceUtils", "register listener end");
    }

    public static void h(int i2) {
        y yVar = p;
        if (yVar != null) {
            yVar.removeMessages(i2);
        }
    }

    public static void i(int i2) {
        j(i2, 0);
    }

    public static void j(int i2, int i3) {
        k(i2, i3, 2000);
    }

    public static void k(int i2, int i3, int i4) {
        y yVar = p;
        if (yVar != null) {
            yVar.removeMessages(i2);
            Message messageObtainMessage = p.obtainMessage();
            messageObtainMessage.what = i2;
            messageObtainMessage.arg1 = i3;
            messageObtainMessage.obj = Integer.valueOf(InputDeviceCompat.SOURCE_ANY);
            p.sendMessageDelayed(messageObtainMessage, i4);
        }
    }

    public static void l(a.b.d... dVarArr) {
        if (dVarArr == null) {
            return;
        }
        for (int i2 = 0; i2 < dVarArr.length; i2++) {
            if (dVarArr[i2] instanceof b.b.g.k.s) {
                b.b.g.n.q.s.g().z((b.b.g.k.s) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.l) {
                b.b.g.n.q.h.m().X((b.b.g.k.l) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.d) {
                b.b.g.n.q.g.g().k((b.b.g.k.d) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.r) {
                b.b.g.n.q.r.b().s((b.b.g.k.r) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.p) {
                b.b.g.n.q.o.m().E((b.b.g.k.p) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.j) {
                b.b.g.n.q.e.j().Q((b.b.g.k.j) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.h) {
                b.b.g.n.q.c.K().F0((b.b.g.k.h) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.n) {
                b.b.g.n.q.k.c().k((b.b.g.k.n) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.o) {
                b.b.g.n.q.l.b().j((b.b.g.k.o) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.i) {
                b.b.g.n.q.d.x().X0((b.b.g.k.i) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.k) {
                b.b.g.n.q.f.g().k((b.b.g.k.k) dVarArr[i2]);
            } else if (dVarArr[i2] instanceof b.b.g.k.m) {
                b.b.g.n.q.j.a().e((b.b.g.k.m) dVarArr[i2]);
            }
        }
    }

    public static void m() {
        BydLogHelper.logD("BYDAutoServiceUtils", "unregister listener start");
        y yVar = p;
        if (yVar != null) {
            yVar.removeCallbacksAndMessages(null);
        }
        b.b.g.n.q.c.K().F0(f1796a);
        b.b.g.n.q.e.j().Q(f1797b);
        b.b.g.n.q.h.m().X(f1798c);
        b.b.g.n.q.o.m().E(f1799d);
        b.b.g.n.q.f.g().k(f1800e);
        b.b.g.n.q.s.g().z(f1801f);
        b.b.g.n.q.k.c().k(g);
        b.b.g.n.q.r.b().s(h);
        b.b.g.n.q.l.b().j(i);
        b.b.g.n.q.m.p0().H3(j);
        b.b.g.n.q.g.g().k(k);
        b.b.g.n.q.i.k().w(l);
        b.b.g.n.q.a.a().e(m);
        b.b.g.n.q.b.c().j(n);
        b.b.g.n.q.j.a().e(o);
        b.b.g.n.q.n.b().f(q);
        c.d.a().Y();
        BydLogHelper.logD("BYDAutoServiceUtils", "unregister listener end");
    }

    public static void n(int i2, Handler handler) {
        o(i2, handler, 300);
    }

    public static void o(int i2, Handler handler, int i3) {
        if (handler != null) {
            if (handler.hasMessages(i2)) {
                handler.removeMessages(i2);
            }
            handler.sendEmptyMessageDelayed(i2, i3);
        }
    }
}
