package h6;

import B2.C0073m;
import T.C0753m;
import T.C0761q;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.KeyEvent;
import e2.M;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.t;
import i3.C1406e;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import n0.C1891t;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import q8.C2203g;
import q8.C2206j;
import t7.C2447b;
import w0.C2586a;
import y.t0;
import y.y0;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15598a = 0;

    public static final long a(int i7) {
        long j3 = (((long) i7) << 32) | (((long) 0) & 4294967295L);
        int i10 = C2586a.f23163n;
        return j3;
    }

    public static boolean b(C0073m c0073m) {
        t tVar = new t(8);
        int i7 = C1406e.a(c0073m, tVar).f15742a;
        if (i7 != 1380533830 && i7 != 1380333108) {
            return false;
        }
        c0073m.k(tVar.f15241a, 0, 4, false);
        tVar.H(0);
        int iH = tVar.h();
        if (iH == 1463899717) {
            return true;
        }
        AbstractC1346c.p("WavHeaderReader", "Unsupported form type: " + iH);
        return false;
    }

    public static void c(int i7, int i10, int i11) {
        if (i7 < 0 || i10 > i11) {
            StringBuilder sbL = M5.b.l("fromIndex: ", ", toIndex: ", i7, i10, ", size: ");
            sbL.append(i11);
            throw new IndexOutOfBoundsException(sbL.toString());
        }
        if (i7 > i10) {
            throw new IllegalArgumentException(A7.k.i("fromIndex: ", " > toIndex: ", i7, i10));
        }
    }

    public static boolean d(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
        Display display = displayManager != null ? displayManager.getDisplay(0) : null;
        if (display == null || !display.isHdr()) {
            return false;
        }
        for (int i7 : display.getHdrCapabilities().getSupportedHdrTypes()) {
            if (i7 == 1) {
                return true;
            }
        }
        return false;
    }

    public static final C2447b e(Enum[] enumArr) {
        A7.m.f(enumArr, "entries");
        return new C2447b(enumArr);
    }

    public static String h(KeyEvent.Callback callback) {
        if (callback == null) {
            return null;
        }
        String canonicalName = callback.getClass().getCanonicalName();
        return canonicalName != null ? canonicalName : callback.getClass().getSimpleName();
    }

    public static k6.p i(List list, C0761q c0761q, int i7, int i10) {
        c0761q.Q(-266960334);
        if ((i10 & 1) != 0) {
            c0761q.Q(-2010783876);
            list = AbstractC1999n.D(new C1891t(l6.d.c(c0761q).f18715q), new C1891t(l6.d.c(c0761q).f18716r));
            c0761q.p(false);
        }
        k6.p pVar = new k6.p(list, AbstractC1999n.D(Float.valueOf(0.2f), Float.valueOf(0.95f)), 280.0f, false);
        c0761q.p(false);
        return pVar;
    }

    public static List k(C0761q c0761q) {
        c0761q.Q(33667292);
        List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.e.f18791l0)), new m7.i(Float.valueOf(1.0f), new C1891t(l6.e.f18793m0)));
        c0761q.p(false);
        return listD;
    }

    public static k6.p l(int i7, C0761q c0761q) {
        List listD;
        c0761q.Q(1965349602);
        c0761q.Q(-1953991442);
        switch (i7) {
            case 0:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18734D)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18736E)));
                c0761q.p(false);
                break;
            case 1:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18738F)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18740G)));
                c0761q.p(false);
                break;
            case 2:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18741H)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18743I)));
                c0761q.p(false);
                break;
            case 3:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18745J)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18747K)));
                c0761q.p(false);
                break;
            case 4:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18749L)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18751M)));
                c0761q.p(false);
                break;
            case 5:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18753N)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18755O)));
                c0761q.p(false);
                break;
            case 6:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18757P)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18759Q)));
                c0761q.p(false);
                break;
            case 7:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18761R)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18763S)));
                c0761q.p(false);
                break;
            case 8:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18764T)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18765U)));
                c0761q.p(false);
                break;
            default:
                listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.1373f), new C1891t(l6.e.f18766V)), new m7.i(Float.valueOf(0.8503f), new C1891t(l6.e.f18767W)));
                c0761q.p(false);
                break;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listD, 10));
        Iterator it = listD.iterator();
        while (it.hasNext()) {
            arrayList.add(new C1891t(((C1891t) ((m7.i) it.next()).f19259m).f19453a));
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(listD, 10));
        Iterator it2 = listD.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Float.valueOf(((Number) ((m7.i) it2.next()).l).floatValue()));
        }
        k6.p pVar = new k6.p(arrayList, arrayList2, 140.0f, true);
        c0761q.p(false);
        return pVar;
    }

    public static m7.f n(m7.g gVar, InterfaceC2772a interfaceC2772a) {
        A7.m.f(interfaceC2772a, "initializer");
        int iOrdinal = gVar.ordinal();
        if (iOrdinal == 0) {
            return new m7.n(interfaceC2772a);
        }
        m7.p pVar = m7.p.f19266a;
        if (iOrdinal == 1) {
            m7.m mVar = new m7.m();
            mVar.l = interfaceC2772a;
            mVar.f19261m = pVar;
            return mVar;
        }
        if (iOrdinal != 2) {
            throw new A9.k();
        }
        m7.r rVar = new m7.r();
        rVar.l = interfaceC2772a;
        rVar.f19268m = pVar;
        return rVar;
    }

    public static m7.n o(InterfaceC2772a interfaceC2772a) {
        A7.m.f(interfaceC2772a, "initializer");
        return new m7.n(interfaceC2772a);
    }

    public static ConcurrentHashMap p(Map map) {
        if (map == null) {
            return null;
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                concurrentHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return concurrentHashMap;
    }

    public static final y0 q(C0761q c0761q) {
        Object[] objArr = new Object[0];
        io.sentry.internal.debugmeta.c cVar = y0.f24027i;
        boolean zD = c0761q.d(0);
        Object objG = c0761q.G();
        if (zD || objG == C0753m.f8270a) {
            objG = new t0(0);
            c0761q.a0(objG);
        }
        return (y0) w0.c.Z(objArr, cVar, (InterfaceC2772a) objG, c0761q, 0, 4);
    }

    public static final g0.o r(g0.o oVar, float f10, float f11) {
        return (f10 == 1.0f && f11 == 1.0f) ? oVar : androidx.compose.ui.graphics.a.b(oVar, f10, f11, 0.0f, 0.0f, 0.0f, 0L, null, false, 131068);
    }

    public static C1406e s(int i7, C0073m c0073m, t tVar) throws M {
        C1406e c1406eA = C1406e.a(c0073m, tVar);
        while (true) {
            int i10 = c1406eA.f15742a;
            if (i10 == i7) {
                return c1406eA;
            }
            AbstractC1347d.p("Ignoring unknown WAV chunk: ", i10, "WavHeaderReader");
            long j3 = c1406eA.f15743b;
            long j10 = 8 + j3;
            if (j3 % 2 != 0) {
                j10 = 9 + j3;
            }
            if (j10 > 2147483647L) {
                throw M.b("Chunk is too large (~2GB+) to skip; id: " + i10);
            }
            c0073m.i((int) j10);
            c1406eA = C1406e.a(c0073m, tVar);
        }
    }

    public static g0.o t(g0.o oVar, y0 y0Var) {
        return g0.a.b(oVar, new androidx.compose.foundation.e(y0Var, false, null, true, true));
    }

    public static C2203g u(InterfaceC2782k interfaceC2782k) {
        C2206j c2206j = new C2206j();
        interfaceC2782k.b(c2206j);
        c2206j.f21320a = true;
        return new C2203g(c2206j);
    }

    public abstract Method f(Class cls, Field field);

    public abstract Constructor g(Class cls);

    public abstract String[] j(Class cls);

    public abstract boolean m(Class cls);
}
