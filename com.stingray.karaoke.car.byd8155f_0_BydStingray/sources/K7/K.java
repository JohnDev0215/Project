package K7;

import E0.AbstractC0156f;
import F8.AbstractC0287w;
import P.C0489a;
import P.C0490b;
import Q.AbstractC0536v0;
import Q.C0523o0;
import Q.C0540x0;
import S8.C0712i;
import S8.InterfaceC0710g;
import T.C0738e0;
import T.C0742g0;
import T.C0767t0;
import T.EnumC0756n0;
import T.Q0;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.C0971i;
import c8.C1036a;
import d0.C1053b;
import d8.C1117d;
import d8.C1129p;
import f8.InterfaceC1265a;
import h8.AbstractC1387d;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import n0.C1886o;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o7.C2050b;
import o8.C2057b;
import o8.C2061f;
import t8.C2449b;
import u.C2484x;
import x.C2650p;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class K extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3735m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3736n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ K(Object obj, int i7) {
        super(0);
        this.f3735m = i7;
        this.f3736n = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v94, types: [java.lang.Object, java.util.Map] */
    /* JADX WARN: Type inference failed for: r12v58, types: [java.lang.Object, z7.k] */
    /* JADX WARN: Type inference failed for: r6v1, types: [T.N, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object[]] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        InterfaceC2772a interfaceC2772a;
        InterfaceC0710g interfaceC0710gT;
        Map mapR;
        R7.m mVar;
        C2449b c2449bA;
        int iHashCode = 0;
        switch (this.f3735m) {
            case 0:
                return new J((L) this.f3736n);
            case 1:
                return new M((N) this.f3736n);
            case 2:
                List upperBounds = ((p0) this.f3736n).l.getUpperBounds();
                A7.m.e(upperBounds, "descriptor.upperBounds");
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(upperBounds, 10));
                Iterator it = upperBounds.iterator();
                while (it.hasNext()) {
                    arrayList.add(new o0((AbstractC0287w) it.next(), null));
                }
                return arrayList;
            case 3:
                for (Map.Entry entry : ((Map) this.f3736n).entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    iHashCode += (value instanceof boolean[] ? Arrays.hashCode((boolean[]) value) : value instanceof char[] ? Arrays.hashCode((char[]) value) : value instanceof byte[] ? Arrays.hashCode((byte[]) value) : value instanceof short[] ? Arrays.hashCode((short[]) value) : value instanceof int[] ? Arrays.hashCode((int[]) value) : value instanceof float[] ? Arrays.hashCode((float[]) value) : value instanceof long[] ? Arrays.hashCode((long[]) value) : value instanceof double[] ? Arrays.hashCode((double[]) value) : value instanceof Object[] ? Arrays.hashCode((Object[]) value) : value.hashCode()) ^ (str.hashCode() * 127);
                }
                return Integer.valueOf(iHashCode);
            case 4:
                Object systemService = ((View) ((M.t) this.f3736n).l).getContext().getSystemService("input_method");
                A7.m.d(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                return (InputMethodManager) systemService;
            case 5:
                return new BaseInputConnection(((M.z) this.f3736n).f4172a, false);
            case 6:
                N.h hVar = (N.h) this.f3736n;
                hVar.f4268N = null;
                AbstractC0156f.o(hVar);
                AbstractC0156f.n(hVar);
                AbstractC0156f.m(hVar);
                return Boolean.TRUE;
            case 7:
                N.k kVar = (N.k) this.f3736n;
                kVar.f4283J = null;
                AbstractC0156f.o(kVar);
                AbstractC0156f.n(kVar);
                AbstractC0156f.m(kVar);
                return Boolean.TRUE;
            case 8:
                C2650p c2650p = O.C.f4892a;
                return new m0.c(((m0.c) ((Q0) this.f3736n).getValue()).f18880a);
            case 9:
                ((C0489a) this.f3736n).f5215u.setValue(Boolean.valueOf(!((Boolean) r12.f5215u.getValue()).booleanValue()));
                return m7.q.f19267a;
            case 10:
                AbstractC0156f.m((C0490b) this.f3736n);
                return m7.q.f19267a;
            case 11:
                P7.i iVar = (P7.i) this.f3736n;
                N7.l lVar = iVar.f5437f;
                if (lVar == null) {
                    throw new AssertionError("JvmBuiltins instance has not been initialized properly");
                }
                P7.h hVar2 = (P7.h) lVar.a();
                iVar.f5437f = null;
                return hVar2;
            case 12:
                C0523o0 c0523o0 = (C0523o0) this.f3736n;
                if (!((Boolean) c0523o0.f6011v.getValue()).booleanValue() && (interfaceC2772a = c0523o0.f6003m) != null) {
                    interfaceC2772a.a();
                }
                return m7.q.f19267a;
            case 13:
                return Float.valueOf(((C0540x0) this.f3736n).f6068a.y(AbstractC0536v0.f6061a));
            case 14:
                return (y8.n) ((Q7.M) this.f3736n).f7179b.b(G8.f.f2724a);
            case 15:
                R7.j jVar = (R7.j) this.f3736n;
                return jVar.f7537a.i(jVar.f7538b).j();
            case 16:
                File file = (File) ((B8.A) this.f3736n).a();
                String name = file.getName();
                A7.m.e(name, "getName(...)");
                if (Q8.h.a1('.', name, "").equals("preferences_pb")) {
                    String str2 = r9.z.f21951m;
                    File absoluteFile = file.getAbsoluteFile();
                    A7.m.e(absoluteFile, "file.absoluteFile");
                    return C1886o.j(absoluteFile);
                }
                throw new IllegalStateException(("File extension for file: " + file + " does not match required extension for Preferences file: preferences_pb").toString());
            case 17:
                C0742g0 c0742g0 = (C0742g0) this.f3736n;
                C2484x c2484x = new C2484x(c0742g0.f8252a.size());
                ArrayList arrayList2 = c0742g0.f8252a;
                int size = arrayList2.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ?? F9 = (T.N) arrayList2.get(i7);
                    Object obj = F9.f8207b;
                    int i10 = F9.f8206a;
                    Object m10 = obj != null ? new T.M(Integer.valueOf(i10), F9.f8207b) : Integer.valueOf(i10);
                    int iD = c2484x.d(m10);
                    boolean z6 = iD < 0;
                    Object obj2 = z6 ? null : c2484x.c[iD];
                    if (obj2 != null) {
                        if (!(obj2 instanceof List) || ((obj2 instanceof B7.a) && !(obj2 instanceof B7.c))) {
                            F9 = AbstractC1999n.F(obj2, F9);
                        } else {
                            List listB = A7.E.b(obj2);
                            listB.add(F9);
                            F9 = listB;
                        }
                    }
                    if (z6) {
                        int i11 = ~iD;
                        c2484x.f22644b[i11] = m10;
                        c2484x.c[i11] = F9;
                    } else {
                        c2484x.c[iD] = F9;
                    }
                }
                return new T.W(c2484x);
            case 18:
                C0767t0 c0767t0 = (C0767t0) this.f3736n;
                synchronized (c0767t0.f8352b) {
                    interfaceC0710gT = c0767t0.t();
                    if (((EnumC0756n0) c0767t0.f8364r.getValue()).compareTo(EnumC0756n0.f8276m) <= 0) {
                        Throwable th = c0767t0.d;
                        CancellationException cancellationException = new CancellationException("Recomposer shutdown; frame clock awaiter will never resume");
                        cancellationException.initCause(th);
                        throw cancellationException;
                    }
                }
                if (interfaceC0710gT != null) {
                    ((C0712i) interfaceC0710gT).resumeWith(m7.q.f19267a);
                }
                return m7.q.f19267a;
            case 19:
                Object systemService2 = ((View) ((l3.P) this.f3736n).f18537m).getContext().getSystemService("input_method");
                A7.m.d(systemService2, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                return (InputMethodManager) systemService2;
            case 20:
                return new BaseInputConnection(((T0.B) this.f3736n).f8411a, false);
            case 21:
                return (List) ((T7.P) this.f3736n).f8821x.getValue();
            case 22:
                X0.b bVar = (X0.b) this.f3736n;
                if (((m0.f) bVar.f9969n.getValue()).f18890a == 9205357640488583168L) {
                    return null;
                }
                C0738e0 c0738e0 = bVar.f9969n;
                if (m0.f.e(((m0.f) c0738e0.getValue()).f18890a)) {
                    return null;
                }
                return bVar.l.b(((m0.f) c0738e0.getValue()).f18890a);
            case 23:
                C2050b c2050b = new C2050b(10);
                Z7.v vVar = (Z7.v) this.f3736n;
                c2050b.add(vVar.f10959a.l);
                Z7.B b10 = vVar.f10960b;
                if (b10 != null) {
                    c2050b.add("under-migration:".concat(b10.l));
                }
                for (Map.Entry entry2 : vVar.c.entrySet()) {
                    c2050b.add("@" + entry2.getKey() + ':' + ((Z7.B) entry2.getValue()).l);
                }
                return (String[]) AbstractC1387d.a(c2050b).toArray(new String[0]);
            case 24:
                Object obj3 = a8.e.f11208a;
                InterfaceC1265a interfaceC1265a = ((a8.i) this.f3736n).d;
                W7.s sVar = interfaceC1265a instanceof W7.s ? (W7.s) interfaceC1265a : null;
                t8.i iVar2 = (sVar == null || (mVar = (R7.m) a8.e.f11209b.get(C2061f.e(sVar.f9896b.name()).b())) == null) ? null : new t8.i(C2057b.j(N7.n.f4863v), C2061f.e(mVar.name()));
                mapR = iVar2 != null ? n7.z.r(new m7.i(a8.c.c, iVar2)) : null;
                return mapR == null ? n7.v.l : mapR;
            case 25:
                InterfaceC1265a interfaceC1265a2 = ((a8.j) this.f3736n).d;
                if (interfaceC1265a2 instanceof W7.g) {
                    Object obj4 = a8.e.f11208a;
                    c2449bA = a8.e.a(((W7.g) interfaceC1265a2).a());
                } else if (interfaceC1265a2 instanceof W7.s) {
                    Object obj5 = a8.e.f11208a;
                    c2449bA = a8.e.a(AbstractC1387d.j(interfaceC1265a2));
                } else {
                    c2449bA = null;
                }
                mapR = c2449bA != null ? n7.z.r(new m7.i(a8.c.f11206b, c2449bA)) : null;
                return mapR == null ? n7.v.l : mapR;
            case 26:
                ((C0971i) this.f3736n).f11992m = null;
                return m7.q.f19267a;
            case 27:
                return androidx.lifecycle.a0.h((androidx.lifecycle.l0) this.f3736n);
            case 28:
                C1053b c1053b = (C1053b) this.f3736n;
                d0.n nVar = c1053b.l;
                Object obj6 = c1053b.o;
                if (obj6 != null) {
                    return nVar.v(c1053b, obj6);
                }
                throw new IllegalArgumentException("Value should be initialized");
            default:
                C1117d c1117d = (C1117d) this.f3736n;
                Collection collectionValues = ((Map) C7.a.N(c1117d.c.f13617u, C1129p.f13614y[0])).values();
                ArrayList arrayList3 = new ArrayList();
                Iterator it2 = collectionValues.iterator();
                while (it2.hasNext()) {
                    D8.s sVarA = ((C1036a) c1117d.f13567b.f4399m).d.a(c1117d.c, (V7.c) it2.next());
                    if (sVarA != null) {
                        arrayList3.add(sVarA);
                    }
                }
                return (y8.n[]) H7.H.K(arrayList3).toArray(new y8.n[0]);
        }
    }
}
