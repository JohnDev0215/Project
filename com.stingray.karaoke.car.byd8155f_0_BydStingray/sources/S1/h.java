package S1;

import A7.m;
import A9.k;
import N1.C0422c;
import androidx.datastore.preferences.protobuf.AbstractC0957u;
import androidx.datastore.preferences.protobuf.AbstractC0959w;
import androidx.datastore.preferences.protobuf.C0944g;
import androidx.datastore.preferences.protobuf.C0948k;
import androidx.datastore.preferences.protobuf.C0961y;
import androidx.datastore.preferences.protobuf.InterfaceC0958v;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import n7.AbstractC1998m;
import n7.z;
import r9.B;
import r9.C;
import r9.C2318h;
import r9.D;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f7872a = new h();

    public final b a(D d) throws C0422c {
        byte[] bArr;
        try {
            R1.d dVarQ = R1.d.q(new C2318h(d, 1));
            b bVar = new b(false);
            e[] eVarArr = (e[]) Arrays.copyOf(new e[0], 0);
            m.f(eVarArr, "pairs");
            bVar.b();
            if (eVarArr.length > 0) {
                e eVar = eVarArr[0];
                throw null;
            }
            Map mapO = dVarQ.o();
            m.e(mapO, "preferencesProto.preferencesMap");
            for (Map.Entry entry : mapO.entrySet()) {
                String str = (String) entry.getKey();
                R1.h hVar = (R1.h) entry.getValue();
                m.e(str, "name");
                m.e(hVar, "value");
                int iE = hVar.E();
                switch (iE == 0 ? -1 : g.f7871a[AbstractC2641j.c(iE)]) {
                    case -1:
                        throw new C0422c("Value case is null.", null);
                    case 0:
                    default:
                        throw new k();
                    case 1:
                        bVar.e(C7.a.y(str), Boolean.valueOf(hVar.v()));
                        break;
                    case 2:
                        bVar.e(new d(str), Float.valueOf(hVar.z()));
                        break;
                    case 3:
                        bVar.e(new d(str), Double.valueOf(hVar.y()));
                        break;
                    case 4:
                        bVar.e(new d(str), Integer.valueOf(hVar.A()));
                        break;
                    case 5:
                        bVar.e(new d(str), Long.valueOf(hVar.B()));
                        break;
                    case 6:
                        d dVarA0 = C7.a.a0(str);
                        String strC = hVar.C();
                        m.e(strC, "value.string");
                        bVar.e(dVarA0, strC);
                        break;
                    case 7:
                        d dVar = new d(str);
                        InterfaceC0958v interfaceC0958vP = hVar.D().p();
                        m.e(interfaceC0958vP, "value.stringSet.stringsList");
                        bVar.e(dVar, AbstractC1998m.D0(interfaceC0958vP));
                        break;
                    case 8:
                        d dVar2 = new d(str);
                        C0944g c0944gW = hVar.w();
                        int size = c0944gW.size();
                        if (size == 0) {
                            bArr = AbstractC0959w.f11891b;
                        } else {
                            byte[] bArr2 = new byte[size];
                            c0944gW.m(size, bArr2);
                            bArr = bArr2;
                        }
                        m.e(bArr, "value.bytes.toByteArray()");
                        bVar.e(dVar2, bArr);
                        break;
                    case 9:
                        throw new C0422c("Value not set.", null);
                }
            }
            return new b(z.z(bVar.a()), true);
        } catch (C0961y e10) {
            throw new C0422c("Unable to parse preferences proto.", e10);
        }
    }

    public final void b(Object obj, C c) throws IOException {
        AbstractC0957u abstractC0957uA;
        Map mapA = ((b) obj).a();
        R1.b bVarP = R1.d.p();
        for (Map.Entry entry : mapA.entrySet()) {
            d dVar = (d) entry.getKey();
            Object value = entry.getValue();
            String str = dVar.f7868a;
            if (value instanceof Boolean) {
                R1.g gVarF = R1.h.F();
                boolean zBooleanValue = ((Boolean) value).booleanValue();
                gVarF.c();
                R1.h.s((R1.h) gVarF.f11888m, zBooleanValue);
                abstractC0957uA = gVarF.a();
            } else if (value instanceof Float) {
                R1.g gVarF2 = R1.h.F();
                float fFloatValue = ((Number) value).floatValue();
                gVarF2.c();
                R1.h.t((R1.h) gVarF2.f11888m, fFloatValue);
                abstractC0957uA = gVarF2.a();
            } else if (value instanceof Double) {
                R1.g gVarF3 = R1.h.F();
                double dDoubleValue = ((Number) value).doubleValue();
                gVarF3.c();
                R1.h.q((R1.h) gVarF3.f11888m, dDoubleValue);
                abstractC0957uA = gVarF3.a();
            } else if (value instanceof Integer) {
                R1.g gVarF4 = R1.h.F();
                int iIntValue = ((Number) value).intValue();
                gVarF4.c();
                R1.h.u((R1.h) gVarF4.f11888m, iIntValue);
                abstractC0957uA = gVarF4.a();
            } else if (value instanceof Long) {
                R1.g gVarF5 = R1.h.F();
                long jLongValue = ((Number) value).longValue();
                gVarF5.c();
                R1.h.n((R1.h) gVarF5.f11888m, jLongValue);
                abstractC0957uA = gVarF5.a();
            } else if (value instanceof String) {
                R1.g gVarF6 = R1.h.F();
                gVarF6.c();
                R1.h.o((R1.h) gVarF6.f11888m, (String) value);
                abstractC0957uA = gVarF6.a();
            } else if (value instanceof Set) {
                R1.g gVarF7 = R1.h.F();
                R1.e eVarQ = R1.f.q();
                m.d(value, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                eVarQ.c();
                R1.f.n((R1.f) eVarQ.f11888m, (Set) value);
                gVarF7.c();
                R1.h.p((R1.h) gVarF7.f11888m, (R1.f) eVarQ.a());
                abstractC0957uA = gVarF7.a();
            } else {
                if (!(value instanceof byte[])) {
                    throw new IllegalStateException("PreferencesSerializer does not support type: ".concat(value.getClass().getName()));
                }
                R1.g gVarF8 = R1.h.F();
                byte[] bArr = (byte[]) value;
                C0944g c0944g = C0944g.f11844n;
                C0944g c0944gL = C0944g.l(0, bArr.length, bArr);
                gVarF8.c();
                R1.h.r((R1.h) gVarF8.f11888m, c0944gL);
                abstractC0957uA = gVarF8.a();
            }
            bVarP.getClass();
            str.getClass();
            bVarP.c();
            R1.d.n((R1.d) bVarP.f11888m).put(str, (R1.h) abstractC0957uA);
        }
        R1.d dVar2 = (R1.d) bVarP.a();
        B b10 = new B(c);
        int iA = dVar2.a(null);
        Logger logger = C0948k.f11864h;
        if (iA > 4096) {
            iA = 4096;
        }
        C0948k c0948k = new C0948k(b10, iA);
        dVar2.b(c0948k);
        if (c0948k.f11867f > 0) {
            c0948k.I();
        }
    }
}
