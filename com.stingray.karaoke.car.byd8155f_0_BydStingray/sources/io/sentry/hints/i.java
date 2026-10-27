package io.sentry.hints;

import A7.m;
import B2.B;
import B2.C0076p;
import B2.F;
import B2.L;
import B2.t;
import E0.C0167q;
import Q5.C0545a;
import Q5.C0618s1;
import T.C0754m0;
import T.C0761q;
import Y2.l;
import a3.C0919h;
import b6.p;
import c3.C1019b;
import c9.G;
import c9.H;
import com.stingray.karaoke.car.utils.MainLayerLevel;
import d3.C1067a;
import e0.C1178r;
import e2.C1208p;
import e3.C1222e;
import f3.C1255a;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import k4.q;
import k4.r;
import k4.w;
import k4.y;
import k6.C1700h;
import k6.v;
import l5.n;
import l5.o;
import n7.AbstractC1998m;
import o8.C2058c;
import x0.InterfaceC2660a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class i implements t, B4.c, Y2.j, InterfaceC2660a, h, r, o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static i f16551m;
    public final /* synthetic */ int l;

    public /* synthetic */ i(int i7) {
        this.l = i7;
    }

    public static final H f(H h10) {
        if ((h10 != null ? h10.f12518r : null) == null) {
            return h10;
        }
        G gK = h10.k();
        gK.f12508g = null;
        return gK.a();
    }

    public static void g() {
        C1178r c1178r = k6.o.c;
        ArrayList<C1700h> arrayList = new ArrayList();
        ListIterator listIterator = c1178r.listIterator();
        while (true) {
            C0167q c0167q = (C0167q) listIterator;
            if (!c0167q.hasNext()) {
                break;
            }
            Object next = c0167q.next();
            if (((C1700h) next).d) {
                arrayList.add(next);
            }
        }
        for (C1700h c1700h : arrayList) {
            InterfaceC2772a interfaceC2772a = c1700h.c;
            if (interfaceC2772a != null) {
                interfaceC2772a.a();
            }
            k6.o.c.remove(c1700h);
        }
    }

    public static boolean h() {
        C1178r c1178r = k6.o.c;
        C1700h c1700h = (C1700h) AbstractC1998m.i0(c1178r);
        if (c1700h == null) {
            return false;
        }
        if (!c1700h.d) {
            return true;
        }
        InterfaceC2772a interfaceC2772a = c1700h.c;
        if (interfaceC2772a != null) {
            interfaceC2772a.a();
        }
        c1178r.remove(c1700h);
        return true;
    }

    public static void i(String str, boolean z6) {
        Object next;
        m.f(str, "id");
        ListIterator listIterator = k6.o.c.listIterator();
        while (true) {
            C0167q c0167q = (C0167q) listIterator;
            if (!c0167q.hasNext()) {
                next = null;
                break;
            } else {
                next = c0167q.next();
                if (((C1700h) next).f18266a.equals(str)) {
                    break;
                }
            }
        }
        C1700h c1700h = (C1700h) next;
        if (c1700h != null) {
            if (z6 || c1700h.d) {
                InterfaceC2772a interfaceC2772a = c1700h.c;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
                k6.o.c.remove(c1700h);
            }
        }
    }

    public static boolean k(String str) {
        return ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) ? false : true;
    }

    public static O7.d l(String str, C2058c c2058c) {
        O7.e eVar;
        Integer numValueOf;
        m.f(c2058c, "packageFqName");
        O7.e[] eVarArrValues = O7.e.values();
        int length = eVarArrValues.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                eVar = null;
                break;
            }
            eVar = eVarArrValues[i7];
            if (m.a(eVar.l, c2058c) && Q8.o.z0(str, eVar.f5186m, false)) {
                break;
            }
            i7++;
        }
        if (eVar == null) {
            return null;
        }
        String strSubstring = str.substring(eVar.f5186m.length());
        m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        if (strSubstring.length() == 0) {
            numValueOf = null;
            break;
        }
        int length2 = strSubstring.length();
        int i10 = 0;
        for (int i11 = 0; i11 < length2; i11++) {
            int iCharAt = strSubstring.charAt(i11) - '0';
            if (iCharAt < 0 || iCharAt >= 10) {
                numValueOf = null;
                break;
            }
            i10 = (i10 * 10) + iCharAt;
        }
        numValueOf = Integer.valueOf(i10);
        if (numValueOf != null) {
            return new O7.d(eVar, numValueOf.intValue());
        }
        return null;
    }

    public static void m(MainLayerLevel mainLayerLevel, b0.b bVar) {
        m.f(mainLayerLevel, "level");
        k6.o.f18285b.put(mainLayerLevel, bVar);
    }

    public static void o() {
        int i7 = 1;
        C1178r c1178r = k6.o.d;
        if (c1178r.isEmpty()) {
            m(MainLayerLevel.TOAST, null);
            return;
        }
        if (k6.o.f18286e == null) {
            v vVar = (v) AbstractC1998m.Y(c1178r);
            k6.o.f18286e = vVar;
            if (vVar != null) {
                b0.b bVar = new b0.b(1275434627, new C0618s1(vVar, i7), true);
                i iVar = k6.o.f18284a;
                m(MainLayerLevel.TOAST, bVar);
            }
        }
    }

    @Override // k4.r
    public q A(w wVar) {
        return new y(wVar.b(k4.g.class, InputStream.class), 1);
    }

    @Override // l5.o
    public Object H() {
        switch (this.l) {
            case 27:
                return new ArrayDeque();
            case 28:
                return new ConcurrentHashMap();
            default:
                return new n(true);
        }
    }

    @Override // Y2.j
    public int a(C1208p c1208p) {
        String str = c1208p.f14316n;
        if (str != null) {
            switch (str) {
                case "application/dvbsubs":
                case "application/pgs":
                case "application/x-mp4-vtt":
                    return 2;
                case "text/vtt":
                    return 1;
                case "application/x-quicktime-tx3g":
                    return 2;
                case "text/x-ssa":
                case "application/x-subrip":
                case "application/ttml+xml":
                    return 1;
            }
        }
        throw new IllegalArgumentException(M5.b.g("Unsupported MIME type: ", str));
    }

    @Override // B4.c
    public void b(Object obj) {
        ((List) obj).clear();
    }

    @Override // Y2.j
    public l c(C1208p c1208p) {
        List list;
        String str = c1208p.f14316n;
        if (str != null) {
            list = c1208p.f14318q;
            switch (str) {
                case "application/dvbsubs":
                    return new C0919h(list);
                case "application/pgs":
                    return new B8.t(10);
                case "application/x-mp4-vtt":
                    return new B(1);
                case "text/vtt":
                    return new io.sentry.internal.debugmeta.c(17);
                case "application/x-quicktime-tx3g":
                    return new C1255a(list);
                case "text/x-ssa":
                    return new C1019b(list);
                case "application/x-subrip":
                    return new C1067a();
                case "application/ttml+xml":
                    return new C1222e();
            }
        }
        throw new IllegalArgumentException(M5.b.g("Unsupported MIME type: ", str));
    }

    public void d(int i7, C0761q c0761q) {
        int i10;
        c0761q.S(1226026910);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            MainLayerLevel mainLayerLevel = MainLayerLevel.DIALOG;
            m(mainLayerLevel, null);
            C1700h c1700h = (C1700h) AbstractC1998m.i0(k6.o.c);
            if (c1700h != null) {
                i iVar = k6.o.f18284a;
                m(mainLayerLevel, c1700h.f18267b);
            }
            List listU0 = AbstractC1998m.u0(k6.o.f18285b.f13733m, new E5.y(7));
            ArrayList arrayList = new ArrayList();
            Iterator it = listU0.iterator();
            while (it.hasNext()) {
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) ((Map.Entry) it.next()).getValue();
                if (interfaceC2785n != null) {
                    arrayList.add(interfaceC2785n);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                ((InterfaceC2785n) it2.next()).invoke(c0761q, 0);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 11, this);
        }
    }

    @Override // B2.t
    public void e() {
    }

    @Override // Y2.j
    public boolean j(C1208p c1208p) {
        String str = c1208p.f14316n;
        return Objects.equals(str, "text/x-ssa") || Objects.equals(str, "text/vtt") || Objects.equals(str, "application/x-mp4-vtt") || Objects.equals(str, "application/x-subrip") || Objects.equals(str, "application/x-quicktime-tx3g") || Objects.equals(str, "application/pgs") || Objects.equals(str, "application/dvbsubs") || Objects.equals(str, "application/ttml+xml");
    }

    @Override // B2.t
    public L u(int i7, int i10) {
        return new C0076p();
    }

    @Override // B2.t
    public void w(F f10) {
    }

    public i(p pVar) {
        this.l = 17;
    }
}
