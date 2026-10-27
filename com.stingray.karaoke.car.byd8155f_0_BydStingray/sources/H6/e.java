package h6;

import A7.x;
import D.AbstractC0120b;
import E0.F;
import N0.L;
import Q7.InterfaceC0652e;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.Q0;
import T.S;
import T.X;
import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.node.Owner;
import com.stingray.karaoke.car.utils.ToastType;
import h8.AbstractC1387d;
import h8.C1390g;
import io.sentry.C1454c;
import io.sentry.C1482l0;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import io.sentry.X0;
import io.sentry.protocol.C1499d;
import io.sentry.protocol.D;
import io.sentry.protocol.E;
import io.sentry.protocol.t;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import k6.v;
import k6.w;
import m7.q;
import n3.C1941f;
import n7.AbstractC2004s;
import o8.C2057b;
import o8.C2060e;
import q7.C2195i;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.C2304b;
import r7.C2305c;
import r7.C2306d;
import r7.C2307e;
import s7.AbstractC2408a;
import s7.AbstractC2410c;
import v8.AbstractC2564d;
import w8.C2608c;
import x.AbstractC2613B;
import x.AbstractC2631e;
import x.AbstractC2637h;
import x.C2620I;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f15573b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15574a = 8;

    public static final long a(float f10, float f11) {
        return (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
    }

    public static final void b(final g0.o oVar, final int i7, final int i10, final int i11, final int i12, final m7.i iVar, final long j3, final float f10, final long j10, final float f11, final L l, final boolean z6, final InterfaceC2785n interfaceC2785n, C0761q c0761q, final int i13, final int i14) {
        int i15;
        int i16;
        x xVar;
        Integer num;
        A7.m.f(iVar, "accentColor");
        A7.m.f(interfaceC2785n, "playSoundFx");
        c0761q.S(738703902);
        if ((i13 & 6) == 0) {
            i15 = (c0761q.f(oVar) ? 4 : 2) | i13;
        } else {
            i15 = i13;
        }
        if ((i13 & 48) == 0) {
            i15 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i13 & 384) == 0) {
            i15 |= c0761q.d(i10) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i15 |= c0761q.d(i11) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i15 |= c0761q.d(i12) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i15 |= c0761q.f(iVar) ? 131072 : 65536;
        }
        if ((1572864 & i13) == 0) {
            i15 |= c0761q.e(j3) ? 1048576 : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i15 |= c0761q.c(f10) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i15 |= c0761q.e(j10) ? 67108864 : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i15 |= c0761q.c(f11) ? 536870912 : 268435456;
        }
        if ((i14 & 6) == 0) {
            i16 = i14 | (c0761q.f(l) ? 4 : 2);
        } else {
            i16 = i14;
        }
        if ((i14 & 48) == 0) {
            i16 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i14 & 384) == 0) {
            i16 |= c0761q.h(interfaceC2785n) ? 256 : 128;
        }
        int i17 = i16;
        if ((i15 & 306783379) == 306783378 && (i17 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int iV = AbstractC2673a.v(i10, 0, i12);
            x xVar2 = new x();
            xVar2.l = i7;
            if (i7 < 1) {
                xVar2.l = 1;
            }
            int i18 = i11 - 1;
            float f12 = i18;
            float f13 = (xVar2.l - 1) / f12;
            float f14 = (iV / i12) / f12;
            int i19 = i12 * i18;
            float f15 = f13 + f14;
            C2620I c2620iF = AbstractC2631e.f(AbstractC2631e.n("infiniteMultiplierScale", c0761q, 0), 1.0f, 1.1f, AbstractC2631e.m(AbstractC2631e.q(500, 0, AbstractC2613B.c, 2), 2, 4), "multiplierScale", c0761q, 29112, 0);
            c0761q.Q(-998503128);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            Object objE = M5.b.e(-998501040, c0761q, false);
            if (objE == s6) {
                objE = C0735d.M(i7);
                c0761q.a0(objE);
            }
            C0732b0 c0732b0 = (C0732b0) objE;
            c0761q.p(false);
            Q0 q0B = AbstractC2637h.b(!((Boolean) x8.getValue()).booleanValue() ? 1.0f : 2.5f, null, "multiplierChangeScale", null, c0761q, 3072, 22);
            Integer numValueOf = Integer.valueOf(i7);
            c0761q.Q(-998491866);
            boolean z10 = ((i15 & 112) == 32) | ((i17 & 896) == 256);
            Object objG2 = c0761q.G();
            if (z10 || objG2 == s6) {
                xVar = xVar2;
                num = numValueOf;
                p6.c cVar = new p6.c(i7, interfaceC2785n, c0732b0, x8, null);
                c0761q.a0(cVar);
                objG2 = cVar;
            } else {
                num = numValueOf;
                xVar = xVar2;
            }
            c0761q.p(false);
            C0735d.f(c0761q, num, (InterfaceC2785n) objG2);
            AbstractC0120b.a(androidx.compose.foundation.layout.a.k(oVar, f10), g0.b.f14664p, false, b0.g.b(1912177012, new p6.f(f15, i19, f10, f11, j3, iVar, q0B, z6, c2620iF, xVar, l, j10, x8), c0761q), c0761q, 3120, 4);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: p6.b
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i13 | 1);
                    int iZ2 = C0735d.Z(i14);
                    boolean z11 = z6;
                    InterfaceC2785n interfaceC2785n2 = interfaceC2785n;
                    h6.e.b(oVar, i7, i10, i11, i12, iVar, j3, f10, j10, f11, l, z11, interfaceC2785n2, (C0761q) obj, iZ, iZ2);
                    return q.f19267a;
                }
            };
        }
    }

    public static final void c(long j3, b0.b bVar) {
        io.sentry.hints.i iVar = k6.o.f18284a;
        k6.o.d.add(new v(j3, bVar));
        io.sentry.hints.i.o();
    }

    public static final long d(ToastType toastType) {
        return w.f18302a[toastType.ordinal()] == 5 ? 5000L : 3000L;
    }

    public static void f(String str) {
        if (str.length() > 127) {
            str = str.substring(0, 127);
        }
        Trace.beginSection(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static InterfaceC2189c g(InterfaceC2189c interfaceC2189c, InterfaceC2189c interfaceC2189c2, InterfaceC2785n interfaceC2785n) {
        A7.m.f(interfaceC2785n, "<this>");
        if (interfaceC2785n instanceof AbstractC2408a) {
            return ((AbstractC2408a) interfaceC2785n).create(interfaceC2189c, interfaceC2189c2);
        }
        InterfaceC2194h context = interfaceC2189c2.getContext();
        return context == C2195i.l ? new C2304b(interfaceC2189c2, interfaceC2189c, interfaceC2785n) : new C2305c(interfaceC2189c2, context, interfaceC2785n, interfaceC2189c);
    }

    public static boolean h(X0 x02, String str, C1482l0 c1482l0, K k10) {
        int i7;
        int i10;
        i7 = 8;
        i10 = 0;
        switch (str) {
            case "debug_meta":
                x02.f15989y = (C1499d) c1482l0.O(k10, new io.sentry.clientreport.a(i7));
                return true;
            case "server_name":
                x02.f15986v = c1482l0.Q();
                return true;
            case "contexts":
                x02.f15978m.putAll(io.sentry.clientreport.a.c(c1482l0, k10));
                return true;
            case "environment":
                x02.f15982r = c1482l0.Q();
                return true;
            case "breadcrumbs":
                x02.f15988x = c1482l0.C(k10, new C1454c(i10));
                return true;
            case "sdk":
                x02.f15979n = (io.sentry.protocol.r) c1482l0.O(k10, new io.sentry.clientreport.a(20));
                return true;
            case "dist":
                x02.f15987w = c1482l0.Q();
                return true;
            case "tags":
                x02.f15980p = r.p((Map) c1482l0.N());
                return true;
            case "user":
                x02.f15984t = (E) c1482l0.O(k10, new D(i10));
                return true;
            case "extra":
                x02.f15990z = r.p((Map) c1482l0.N());
                return true;
            case "event_id":
                x02.l = (t) c1482l0.O(k10, new io.sentry.clientreport.a(22));
                return true;
            case "release":
                x02.f15981q = c1482l0.Q();
                return true;
            case "request":
                x02.o = (io.sentry.protocol.n) c1482l0.O(k10, new io.sentry.clientreport.a(18));
                return true;
            case "platform":
                x02.f15983s = c1482l0.Q();
                return true;
            default:
                return false;
        }
    }

    public static boolean i(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static final HashSet j(Iterable iterable) {
        HashSet hashSet = new HashSet();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            Set setG = ((y8.n) it.next()).g();
            if (setG == null) {
                return null;
            }
            AbstractC2004s.L(hashSet, setG);
        }
        return hashSet;
    }

    public static InterfaceC2189c k(InterfaceC2189c interfaceC2189c) {
        InterfaceC2189c interfaceC2189cIntercepted;
        A7.m.f(interfaceC2189c, "<this>");
        AbstractC2410c abstractC2410c = interfaceC2189c instanceof AbstractC2410c ? (AbstractC2410c) interfaceC2189c : null;
        return (abstractC2410c == null || (interfaceC2189cIntercepted = abstractC2410c.intercepted()) == null) ? interfaceC2189c : interfaceC2189cIntercepted;
    }

    public static final boolean l(long j3) {
        long j10 = (j3 & 9187343241974906880L) ^ 9187343241974906880L;
        return (((~j10) & (j10 - 4294967297L)) & (-9223372034707292160L)) == 0;
    }

    public static final boolean m(long j3) {
        return (j3 & 9223372034707292159L) != 9205357640488583168L;
    }

    public static final boolean n(long j3) {
        return (j3 & 9223372034707292159L) == 9205357640488583168L;
    }

    public static final long o(long j3, long j10, float f10) {
        float fO = AbstractC2673a.O(Float.intBitsToFloat((int) (j3 >> 32)), Float.intBitsToFloat((int) (j10 >> 32)), f10);
        float fO2 = AbstractC2673a.O(Float.intBitsToFloat((int) (j3 & 4294967295L)), Float.intBitsToFloat((int) (j10 & 4294967295L)), f10);
        return (((long) Float.floatToRawIntBits(fO)) << 32) | (((long) Float.floatToRawIntBits(fO2)) & 4294967295L);
    }

    public static final ArrayList p(Map map, InterfaceC2782k interfaceC2782k) {
        A7.m.f(map, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            C1941f c1941f = (C1941f) entry.getValue();
            Boolean boolValueOf = c1941f != null ? Boolean.valueOf(c1941f.f19859b) : null;
            A7.m.c(boolValueOf);
            if (!boolValueOf.booleanValue() && !c1941f.c) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Set setKeySet = linkedHashMap.keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (((Boolean) interfaceC2782k.b((String) obj)).booleanValue()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static void q(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    public static final String r(InterfaceC0652e interfaceC0652e, String str) {
        String strB;
        A7.m.f(interfaceC0652e, "classDescriptor");
        String str2 = P7.d.f5417a;
        C2060e c2060eI = AbstractC2564d.g(interfaceC0652e).i();
        A7.m.e(c2060eI, "fqNameSafe.toUnsafe()");
        C2057b c2057bF = P7.d.f(c2060eI);
        if (c2057bF != null) {
            strB = C2608c.b(c2057bF).e();
            A7.m.e(strB, "byClassId(it).internalName");
        } else {
            strB = AbstractC1387d.b(interfaceC0652e, C1390g.d);
        }
        A7.m.f(strB, "internalName");
        return strB + '.' + str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void s(View view, io.sentry.android.replay.viewhierarchy.d dVar, F1 f12) {
        F root;
        if (view instanceof ViewGroup) {
            if (Q8.h.C0(view.getClass().getName(), "AndroidComposeView", false)) {
                try {
                    Owner owner = view instanceof Owner ? (Owner) view : null;
                    if (owner != null && (root = owner.getRoot()) != null) {
                        f.v(root, dVar, true, f12);
                        return;
                    }
                } catch (Throwable th) {
                    f12.getLogger().p(EnumC1495p1.ERROR, th, "Error traversing Compose tree. Most likely you're using an unsupported version of\nandroidx.compose.ui:ui. The minimum supported version is 1.5.0. If it's a newer\nversion, please open a github issue with the version you're using, so we can add\nsupport for it.", new Object[0]);
                }
            }
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(viewGroup.getChildCount());
            int childCount = viewGroup.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = viewGroup.getChildAt(i7);
                if (childAt != null) {
                    io.sentry.android.replay.viewhierarchy.d dVarI = i.i(childAt, dVar, viewGroup.indexOfChild(childAt), f12);
                    arrayList.add(dVarI);
                    s(childAt, dVarI, f12);
                }
            }
            dVar.f16458f = arrayList;
        }
    }

    public static Object t(InterfaceC2785n interfaceC2785n, Object obj, InterfaceC2189c interfaceC2189c) {
        A7.m.f(interfaceC2785n, "<this>");
        InterfaceC2194h context = interfaceC2189c.getContext();
        Object c2306d = context == C2195i.l ? new C2306d(interfaceC2189c) : new C2307e(interfaceC2189c, context);
        A7.E.e(2, interfaceC2785n);
        return interfaceC2785n.invoke(obj, c2306d);
    }

    public abstract String e();

    public String toString() {
        switch (this.f15574a) {
            case 8:
                return e();
            default:
                return super.toString();
        }
    }
}
