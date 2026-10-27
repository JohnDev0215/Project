package M;

import A.C0050y0;
import A.InterfaceC0007c0;
import A.U;
import A.Y;
import B2.AbstractC0062b;
import B2.C0064d;
import B2.C0074n;
import B2.L;
import B5.RunnableC0079c;
import B8.AbstractC0090b;
import B8.C0092d;
import B8.InterfaceC0093e;
import C0.h0;
import C0.i0;
import E0.F;
import E0.t0;
import F1.InterfaceC0255n;
import F1.V;
import F8.AbstractC0287w;
import G.G;
import K.L0;
import N0.C0411f;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0671y;
import Q7.N;
import T.C0741g;
import T7.C0790i;
import T7.Q;
import V8.f0;
import W7.AbstractC0839c;
import X5.V0;
import a.AbstractC0904a;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import c9.H;
import c9.InterfaceC1048i;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import e2.C1207o;
import e2.C1208p;
import e7.AbstractC1232b;
import e7.C1233c;
import e7.C1234d;
import e7.C1235e;
import g1.C1280g;
import g4.C1296B;
import h2.AbstractC1346c;
import h2.C1344a;
import h8.C1389f;
import io.sentry.C1459d1;
import io.sentry.C1474i1;
import io.sentry.C1489n1;
import io.sentry.EnumC1469h;
import io.sentry.EnumC1492o1;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import io.sentry.L1;
import j8.C1559d;
import j8.C1560e;
import j8.C1562g;
import j8.EnumC1558c;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import k1.C1596a;
import k2.InterfaceC1606g;
import l8.AbstractC1787e;
import l8.C1789g;
import l8.InterfaceC1788f;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2061f;
import r7.EnumC2303a;
import t8.C2448a;
import t8.C2449b;
import t8.C2450c;
import t8.C2451d;
import t8.C2452e;
import y.j0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class t implements i0, InterfaceC0007c0, InterfaceC0255n, InterfaceC1048i, Z7.A, f7.c, com.bumptech.glide.load.data.d, InterfaceC0093e, K, io.sentry.clientreport.f, InterfaceC1606g {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f4151m;

    public /* synthetic */ t(Object obj, Object obj2) {
        this.l = obj;
        this.f4151m = obj2;
    }

    public static void B(F f10) {
        f10.j();
        int i7 = 0;
        f10.f1477P = false;
        V.d dVarL = f10.L();
        int i10 = dVarL.f9279n;
        if (i10 > 0) {
            Object[] objArr = dVarL.l;
            do {
                B((F) objArr[i7]);
                i7++;
            } while (i7 < i10);
        }
    }

    public static void J(t tVar, C1280g c1280g, float f10, int i7) {
        if ((i7 & 2) != 0) {
            f10 = 0;
        }
        tVar.I(c1280g, f10, 0);
    }

    public static EnumC1469h t(EnumC1492o1 enumC1492o1) {
        return EnumC1492o1.Event.equals(enumC1492o1) ? EnumC1469h.Error : EnumC1492o1.Session.equals(enumC1492o1) ? EnumC1469h.Session : EnumC1492o1.Transaction.equals(enumC1492o1) ? EnumC1469h.Transaction : EnumC1492o1.UserFeedback.equals(enumC1492o1) ? EnumC1469h.UserReport : EnumC1492o1.Profile.equals(enumC1492o1) ? EnumC1469h.Profile : EnumC1492o1.Statsd.equals(enumC1492o1) ? EnumC1469h.MetricBucket : EnumC1492o1.Attachment.equals(enumC1492o1) ? EnumC1469h.Attachment : EnumC1492o1.CheckIn.equals(enumC1492o1) ? EnumC1469h.Monitor : EnumC1492o1.ReplayVideo.equals(enumC1492o1) ? EnumC1469h.Replay : EnumC1469h.Default;
    }

    @Override // io.sentry.clientreport.f
    public void A(io.sentry.clientreport.d dVar, C1489n1 c1489n1) {
        if (c1489n1 == null) {
            return;
        }
        try {
            Iterator it = ((Collection) c1489n1.f16625n).iterator();
            while (it.hasNext()) {
                o(dVar, (C1474i1) it.next());
            }
        } catch (Throwable th) {
            ((F1) this.f4151m).getLogger().p(EnumC1495p1.ERROR, th, "Unable to record lost envelope.", new Object[0]);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean C(t8.g gVar, AbstractC0287w abstractC0287w, C1559d c1559d) {
        EnumC1558c enumC1558c = c1559d.f17302n;
        int i7 = enumC1558c == null ? -1 : AbstractC0090b.f754a[enumC1558c.ordinal()];
        if (i7 != 10) {
            InterfaceC0671y interfaceC0671y = (InterfaceC0671y) this.l;
            if (i7 != 13) {
                return A7.m.a(gVar.a(interfaceC0671y), abstractC0287w);
            }
            if (gVar instanceof C2449b) {
                C2449b c2449b = (C2449b) gVar;
                if (((List) c2449b.f22499a).size() == c1559d.f17309v.size()) {
                    AbstractC0287w abstractC0287wF = interfaceC0671y.l().f(abstractC0287w);
                    G7.d dVarB = AbstractC1999n.B((Collection) c2449b.f22499a);
                    if (!(dVarB instanceof Collection) || !((Collection) dVarB).isEmpty()) {
                        G7.c cVarD = dVarB.iterator();
                        while (cVarD.f2715n) {
                            int iB = cVarD.b();
                            t8.g gVar2 = (t8.g) ((List) c2449b.f22499a).get(iB);
                            C1559d c1559d2 = (C1559d) c1559d.f17309v.get(iB);
                            A7.m.e(c1559d2, "value.getArrayElement(i)");
                            if (!C(gVar2, abstractC0287wF, c1559d2)) {
                                return false;
                            }
                        }
                    }
                }
            }
            throw new IllegalStateException(("Deserialized ArrayValue should have the same number of elements as the original array value: " + gVar).toString());
        }
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
        if (interfaceC0652e != null) {
            C2061f c2061f = N7.i.f4796e;
            if (!N7.i.b(interfaceC0652e, N7.n.f4832P)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [A7.o, z7.k] */
    public Object D(Class cls) {
        A7.m.f(cls, "key");
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f4151m;
        Object obj = concurrentHashMap.get(cls);
        if (obj != null) {
            return obj;
        }
        Object objB = ((A7.o) this.l).b(cls);
        Object objPutIfAbsent = concurrentHashMap.putIfAbsent(cls, objB);
        return objPutIfAbsent == null ? objB : objPutIfAbsent;
    }

    public ArrayList E(Map map, ArrayList arrayList, boolean z6) {
        Thread threadCurrentThread = Thread.currentThread();
        if (map.isEmpty()) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        if (!map.containsKey(threadCurrentThread)) {
            map.put(threadCurrentThread, threadCurrentThread.getStackTrace());
        }
        for (Map.Entry entry : map.entrySet()) {
            Thread thread = (Thread) entry.getKey();
            boolean z10 = (thread == threadCurrentThread && !z6) || (arrayList != null && arrayList.contains(Long.valueOf(thread.getId())));
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
            Thread thread2 = (Thread) entry.getKey();
            io.sentry.protocol.z zVar = new io.sentry.protocol.z();
            zVar.f16809n = thread2.getName();
            zVar.f16808m = Integer.valueOf(thread2.getPriority());
            zVar.l = Long.valueOf(thread2.getId());
            zVar.f16812r = Boolean.valueOf(thread2.isDaemon());
            zVar.o = thread2.getState().name();
            zVar.f16810p = Boolean.valueOf(z10);
            ArrayList arrayListA = ((L1) this.l).a(stackTraceElementArr, false);
            if (((F1) this.f4151m).isAttachStacktrace() && arrayListA != null && !arrayListA.isEmpty()) {
                io.sentry.protocol.y yVar = new io.sentry.protocol.y(arrayListA);
                yVar.f16807n = Boolean.TRUE;
                zVar.f16814t = yVar;
            }
            arrayList2.add(zVar);
        }
        return arrayList2;
    }

    public B2.r F(Object... objArr) {
        Constructor constructorA;
        synchronized (((AtomicBoolean) this.f4151m)) {
            if (!((AtomicBoolean) this.f4151m).get()) {
                try {
                    constructorA = ((C0074n) this.l).a();
                } catch (ClassNotFoundException unused) {
                    ((AtomicBoolean) this.f4151m).set(true);
                    constructorA = null;
                } catch (Exception e10) {
                    throw new RuntimeException("Error instantiating extension", e10);
                }
            }
            constructorA = null;
        }
        if (constructorA == null) {
            return null;
        }
        try {
            return (B2.r) constructorA.newInstance(objArr);
        } catch (Exception e11) {
            throw new IllegalStateException("Unexpected error creating extractor", e11);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    public InputMethodManager G() {
        return (InputMethodManager) this.f4151m.getValue();
    }

    public boolean H() {
        return !(((t0) ((io.sentry.internal.debugmeta.c) this.f4151m).f16570n).isEmpty() && ((t0) ((io.sentry.internal.debugmeta.c) this.l).f16570n).isEmpty());
    }

    public void I(C1280g c1280g, float f10, float f11) {
        int i7 = c1280g.f14722b;
        String str = "start";
        if (i7 != -2) {
            if (i7 == -1) {
                str = "end";
            } else if (i7 != 1) {
                h6.n.j("CCL", "verticalAnchorIndexToAnchorName: Unknown vertical index");
            } else {
                str = "right";
            }
        }
        C1596a c1596a = new C1596a(new char[0]);
        c1596a.s(k1.i.s(c1280g.f14721a.toString()));
        c1596a.s(k1.i.s(str));
        c1596a.s(new k1.e(f10));
        c1596a.s(new k1.e(f11));
        ((k1.g) this.l).G((String) this.f4151m, c1596a);
    }

    public io.sentry.instrumentation.file.e K() {
        File file = (File) this.f4151m;
        boolean zExists = file.exists();
        File file2 = (File) this.l;
        if (zExists) {
            file2.delete();
            file.renameTo(file2);
        }
        return h6.j.h(new FileInputStream(file2), file2);
    }

    public void L(String str, String str2, Long l) {
        AtomicLong atomicLong = (AtomicLong) ((Map) ((io.sentry.util.d) ((e7.f) this.l).f14480m).a()).get(new io.sentry.clientreport.c(str, str2));
        if (atomicLong != null) {
            atomicLong.addAndGet(l.longValue());
        }
    }

    public t8.g M(AbstractC0287w abstractC0287w, C1559d c1559d, InterfaceC1788f interfaceC1788f) {
        t8.g xVar;
        A7.m.f(c1559d, "value");
        A7.m.f(interfaceC1788f, "nameResolver");
        boolean zBooleanValue = AbstractC1787e.f18838M.c(c1559d.f17311x).booleanValue();
        EnumC1558c enumC1558c = c1559d.f17302n;
        switch (enumC1558c == null ? -1 : AbstractC0090b.f754a[enumC1558c.ordinal()]) {
            case 1:
                byte b10 = (byte) c1559d.o;
                xVar = zBooleanValue ? new t8.x(b10) : new C2451d(b10);
                break;
            case 2:
                return new C2452e(Character.valueOf((char) c1559d.o));
            case 3:
                short s6 = (short) c1559d.o;
                xVar = zBooleanValue ? new t8.x(s6) : new t8.u(s6);
                break;
            case 4:
                int i7 = (int) c1559d.o;
                xVar = zBooleanValue ? new t8.x(i7) : new t8.k(i7);
                break;
            case 5:
                long j3 = c1559d.o;
                return zBooleanValue ? new t8.x(j3) : new t8.s(j3);
            case 6:
                return new C2450c(c1559d.f17303p);
            case 7:
                return new C2450c(c1559d.f17304q);
            case 8:
                return new C2450c(Boolean.valueOf(c1559d.o != 0));
            case 9:
                return new t8.v(interfaceC1788f.n(c1559d.f17305r));
            case 10:
                return new t8.r(AbstractC0904a.D(interfaceC1788f, c1559d.f17306s), c1559d.f17310w);
            case 11:
                return new t8.i(AbstractC0904a.D(interfaceC1788f, c1559d.f17306s), AbstractC0904a.F(interfaceC1788f, c1559d.f17307t));
            case 12:
                C1562g c1562g = c1559d.f17308u;
                A7.m.e(c1562g, "value.annotation");
                xVar = new C2448a((Object) z(c1562g, interfaceC1788f));
                break;
            case 13:
                List<C1559d> list = c1559d.f17309v;
                A7.m.e(list, "value.arrayElementList");
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
                for (C1559d c1559d2 : list) {
                    F8.A aE = ((InterfaceC0671y) this.l).l().e();
                    A7.m.e(c1559d2, "it");
                    arrayList.add(M(aE, c1559d2, interfaceC1788f));
                }
                return new t8.w(arrayList, abstractC0287w);
            default:
                throw new IllegalStateException(("Unsupported annotation argument type: " + c1559d.f17302n + " (expected " + abstractC0287w + ')').toString());
        }
        return xVar;
    }

    public void N(io.sentry.clientreport.b bVar) {
        if (bVar == null) {
            return;
        }
        for (io.sentry.clientreport.e eVar : bVar.f16497m) {
            L(eVar.l, eVar.f16501m, eVar.f16502n);
        }
    }

    public C1344a O() throws IOException {
        File file = (File) this.l;
        if (file.exists()) {
            File file2 = (File) this.f4151m;
            if (file2.exists()) {
                file.delete();
            } else if (!file.renameTo(file2)) {
                AbstractC1346c.z("AtomicFile", "Couldn't rename file " + file + " to backup file " + file2);
            }
        }
        try {
            return new C1344a(file);
        } catch (FileNotFoundException e10) {
            File parentFile = file.getParentFile();
            if (parentFile == null || !parentFile.mkdirs()) {
                throw new IOException("Couldn't create " + file, e10);
            }
            try {
                return new C1344a(file);
            } catch (FileNotFoundException e11) {
                throw new IOException("Couldn't create " + file, e11);
            }
        }
    }

    @Override // A.InterfaceC0007c0
    public Object a(Y y10, U u4) {
        Object objB = ((R.w) this.f4151m).b(j0.f23942m, new R.v(this, y10, null), u4);
        return objB == EnumC2303a.l ? objB : m7.q.f19267a;
    }

    @Override // f7.c
    public void b(Object obj) {
        C1235e c1235e = (C1235e) ((AbstractC1232b) this.l);
        c1235e.getClass();
        B8.t tVar = new B8.t(12);
        tVar.o = "POST";
        tVar.f809p = (String) obj;
        C1234d c1234dE1 = c1235e.e1(tVar);
        c1234dE1.T0("success", new d7.h((RunnableC0079c) this.f4151m, 2));
        c1234dE1.T0("error", new C1233c(c1235e, 2));
        c1234dE1.a1();
    }

    @Override // com.bumptech.glide.load.data.d
    public void c(Exception exc) {
        C1296B c1296b = (C1296B) this.f4151m;
        k4.p pVar = (k4.p) this.l;
        k4.p pVar2 = c1296b.f14849q;
        if (pVar2 == null || pVar2 != pVar) {
            return;
        }
        C1296B c1296b2 = (C1296B) this.f4151m;
        k4.p pVar3 = (k4.p) this.l;
        g4.i iVar = c1296b2.f14846m;
        g4.e eVar = c1296b2.f14850r;
        com.bumptech.glide.load.data.e eVar2 = pVar3.c;
        iVar.a(eVar, exc, eVar2, eVar2.e());
    }

    @Override // com.bumptech.glide.load.data.d
    public void d(Object obj) {
        C1296B c1296b = (C1296B) this.f4151m;
        k4.p pVar = (k4.p) this.l;
        k4.p pVar2 = c1296b.f14849q;
        if (pVar2 == null || pVar2 != pVar) {
            return;
        }
        C1296B c1296b2 = (C1296B) this.f4151m;
        k4.p pVar3 = (k4.p) this.l;
        g4.k kVar = c1296b2.l.f14873p;
        if (obj != null && kVar.a(pVar3.c.e())) {
            c1296b2.f14848p = obj;
            c1296b2.f14846m.l(2);
        } else {
            g4.i iVar = c1296b2.f14846m;
            e4.e eVar = pVar3.f18205a;
            com.bumptech.glide.load.data.e eVar2 = pVar3.c;
            iVar.b(eVar, obj, eVar2, eVar2.e(), c1296b2.f14850r);
        }
    }

    @Override // C0.i0
    public boolean e(Object obj, Object obj2) {
        G g7 = (G) this.l;
        return A7.m.a(g7.b(obj), g7.b(obj2));
    }

    @Override // io.sentry.clientreport.f
    public C1489n1 f(C1489n1 c1489n1) {
        F1 f12 = (F1) this.f4151m;
        Date dateF = AbstractC1985B.f();
        e7.f fVar = (e7.f) this.l;
        fVar.getClass();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : ((Map) ((io.sentry.util.d) fVar.f14480m).a()).entrySet()) {
            long andSet = ((AtomicLong) entry.getValue()).getAndSet(0L);
            Long lValueOf = Long.valueOf(andSet);
            if (andSet > 0) {
                arrayList.add(new io.sentry.clientreport.e(((io.sentry.clientreport.c) entry.getKey()).f16499a, ((io.sentry.clientreport.c) entry.getKey()).f16500b, lValueOf));
            }
        }
        io.sentry.clientreport.b bVar = arrayList.isEmpty() ? null : new io.sentry.clientreport.b(dateF, arrayList);
        if (bVar == null) {
            return c1489n1;
        }
        try {
            f12.getLogger().k(EnumC1495p1.DEBUG, "Attaching client report to envelope.", new Object[0]);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = ((Collection) c1489n1.f16625n).iterator();
            while (it.hasNext()) {
                arrayList2.add((C1474i1) it.next());
            }
            arrayList2.add(C1474i1.a(f12.getSerializer(), bVar));
            return new C1489n1((C1459d1) c1489n1.f16624m, arrayList2);
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Unable to attach client report to envelope.", new Object[0]);
            return c1489n1;
        }
    }

    @Override // io.sentry.clientreport.f
    public void g(io.sentry.clientreport.d dVar, EnumC1469h enumC1469h) {
        h(dVar, enumC1469h, 1L);
    }

    @Override // io.sentry.clientreport.f
    public void h(io.sentry.clientreport.d dVar, EnumC1469h enumC1469h, long j3) {
        try {
            L(dVar.getReason(), enumC1469h.getCategory(), Long.valueOf(j3));
        } catch (Throwable th) {
            ((F1) this.f4151m).getLogger().p(EnumC1495p1.ERROR, th, "Unable to record lost event.", new Object[0]);
        }
    }

    @Override // C0.i0
    public void i(h0 h0Var) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) this.f4151m;
        linkedHashMap.clear();
        Iterator it = h0Var.l.iterator();
        while (it.hasNext()) {
            Object objB = ((G) this.l).b(it.next());
            Integer num = (Integer) linkedHashMap.get(objB);
            int iIntValue = num != null ? num.intValue() : 0;
            if (iIntValue == 7) {
                it.remove();
            } else {
                linkedHashMap.put(objB, Integer.valueOf(iIntValue + 1));
            }
        }
    }

    @Override // c9.InterfaceC1048i
    public void j(g9.j jVar, H h10) {
        A7.m.f(jVar, "call");
        if (h10.f12518r == null || !h10.j()) {
            ((V0) this.l).d(new E5.s(android.hardware.bydauto.ac.tsimpl.a.e(h10.o, "Failed to load KML. Status code: ")));
            return;
        }
        B3.m mVar = h10.f12518r;
        A7.m.c(mVar);
        t6.h hVarV = new C1789g(8).v(new InputStreamReader(mVar.g()));
        V0 v02 = (V0) this.l;
        Object obj = v02.f10209f;
        String str = (String) this.f4151m;
        synchronized (obj) {
            if (A7.m.a(str, v02.f10212i)) {
                f0 f0Var = v02.f10210g;
                f0Var.getClass();
                f0Var.m(null, hVarV);
                ((V0) this.l).f10206a.f4623A = Math.min(hVarV.f22492a, hVarV.f22493b);
            }
        }
    }

    @Override // io.sentry.K
    public void k(EnumC1495p1 enumC1495p1, String str, Object... objArr) {
        K k10 = (K) this.f4151m;
        if (k10 == null || !m(enumC1495p1)) {
            return;
        }
        k10.k(enumC1495p1, str, objArr);
    }

    public void l(F f10, boolean z6) {
        io.sentry.internal.debugmeta.c cVar = (io.sentry.internal.debugmeta.c) this.f4151m;
        io.sentry.internal.debugmeta.c cVar2 = (io.sentry.internal.debugmeta.c) this.l;
        if (z6) {
            cVar2.B(f10);
            cVar.B(f10);
        } else {
            if (((t0) cVar2.f16570n).contains(f10)) {
                return;
            }
            cVar.B(f10);
        }
    }

    @Override // io.sentry.K
    public boolean m(EnumC1495p1 enumC1495p1) {
        F1 f12 = (F1) this.l;
        return enumC1495p1 != null && f12.isDebug() && enumC1495p1.ordinal() >= f12.getDiagnosticLevel().ordinal();
    }

    public T0.y n(List list) {
        T0.i iVar;
        Exception e10;
        T0.i iVar2;
        try {
            int size = list.size();
            int i7 = 0;
            iVar = null;
            while (i7 < size) {
                try {
                    iVar2 = (T0.i) list.get(i7);
                } catch (Exception e11) {
                    e10 = e11;
                }
                try {
                    iVar2.a((T0.j) this.f4151m);
                    i7++;
                    iVar = iVar2;
                } catch (Exception e12) {
                    e10 = e12;
                    iVar = iVar2;
                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder("Error while applying EditCommand batch to buffer (length=");
                    sb2.append(((A2.g) ((T0.j) this.f4151m).f8457q).o());
                    sb2.append(", composition=");
                    sb2.append(((T0.j) this.f4151m).c());
                    sb2.append(", selection=");
                    T0.j jVar = (T0.j) this.f4151m;
                    sb2.append((Object) N0.K.g(AbstractC0904a.q(jVar.f8454m, jVar.f8455n)));
                    sb2.append("):");
                    sb.append(sb2.toString());
                    sb.append('\n');
                    AbstractC1998m.d0(list, sb, "\n", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : new C0741g(iVar, 4, this));
                    String string = sb.toString();
                    A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
                    throw new RuntimeException(string, e10);
                }
            }
            T0.j jVar2 = (T0.j) this.f4151m;
            jVar2.getClass();
            C0411f c0411f = new C0411f(((A2.g) jVar2.f8457q).toString(), null, 6);
            T0.j jVar3 = (T0.j) this.f4151m;
            long jQ = AbstractC0904a.q(jVar3.f8454m, jVar3.f8455n);
            N0.K k10 = N0.K.f(((T0.y) this.l).f8482b) ? null : new N0.K(jQ);
            T0.y yVar = new T0.y(c0411f, k10 != null ? k10.f4357a : AbstractC0904a.q(N0.K.d(jQ), N0.K.e(jQ)), ((T0.j) this.f4151m).c());
            this.l = yVar;
            return yVar;
        } catch (Exception e13) {
            iVar = null;
            e10 = e13;
        }
    }

    @Override // io.sentry.clientreport.f
    public void o(io.sentry.clientreport.d dVar, C1474i1 c1474i1) {
        io.sentry.protocol.A aE;
        F1 f12 = (F1) this.f4151m;
        if (c1474i1 == null) {
            return;
        }
        try {
            EnumC1492o1 enumC1492o1 = c1474i1.f16555a.f16585n;
            if (EnumC1492o1.ClientReport.equals(enumC1492o1)) {
                try {
                    N(c1474i1.c(f12.getSerializer()));
                } catch (Exception unused) {
                    f12.getLogger().k(EnumC1495p1.ERROR, "Unable to restore counts from previous client report.", new Object[0]);
                }
            } else {
                EnumC1469h enumC1469hT = t(enumC1492o1);
                if (enumC1469hT.equals(EnumC1469h.Transaction) && (aE = c1474i1.e(f12.getSerializer())) != null) {
                    L(dVar.getReason(), EnumC1469h.Span.getCategory(), Long.valueOf(((long) aE.f16649D.size()) + 1));
                }
                L(dVar.getReason(), enumC1469hT.getCategory(), 1L);
            }
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Unable to record lost envelope item.", new Object[0]);
        }
    }

    @Override // io.sentry.K
    public void p(EnumC1495p1 enumC1495p1, Throwable th, String str, Object... objArr) {
        K k10 = (K) this.f4151m;
        if (k10 == null || !m(enumC1495p1)) {
            return;
        }
        k10.p(enumC1495p1, th, str, objArr);
    }

    @Override // k2.InterfaceC1606g
    public k2.h q() {
        return new k2.n((Context) this.l, ((L0) this.f4151m).q());
    }

    @Override // c9.InterfaceC1048i
    public void r(g9.j jVar, IOException iOException) {
        A7.m.f(jVar, "call");
        ((V0) this.l).d(iOException);
    }

    @Override // io.sentry.K
    public void s(EnumC1495p1 enumC1495p1, String str, Throwable th) {
        K k10 = (K) this.f4151m;
        if (k10 == null || !m(enumC1495p1)) {
            return;
        }
        k10.s(enumC1495p1, str, th);
    }

    public void u(long j3, h2.t tVar) {
        if (tVar.a() < 9) {
            return;
        }
        int iH = tVar.h();
        int iH2 = tVar.h();
        int iV = tVar.v();
        if (iH == 434 && iH2 == 1195456820 && iV == 3) {
            AbstractC0062b.e(j3, tVar, (L[]) this.f4151m);
        }
    }

    public boolean v(F f10, boolean z6) {
        boolean zContains = ((t0) ((io.sentry.internal.debugmeta.c) this.l).f16570n).contains(f10);
        return z6 ? zContains : zContains || ((t0) ((io.sentry.internal.debugmeta.c) this.f4151m).f16570n).contains(f10);
    }

    @Override // B8.InterfaceC0093e
    public C0092d w(C2057b c2057b) {
        A7.m.f(c2057b, "classId");
        C1389f c1389f = (C1389f) this.f4151m;
        A7.m.f(c1389f.c().c, "<this>");
        V7.c cVarF = n7.z.f((V7.b) this.l, c2057b, n8.f.f20166g);
        if (cVarF == null) {
            return null;
        }
        AbstractC0839c.a(cVarF.f9548a).equals(c2057b);
        return c1389f.f(cVarF);
    }

    @Override // F1.InterfaceC0255n
    public V x(View view, V v10) {
        C0064d c0064d = (C0064d) this.f4151m;
        int i7 = c0064d.f556b;
        C0050y0 c0050y0 = (C0050y0) this.l;
        int iA = v10.a();
        BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) c0050y0.f301n;
        bottomSheetBehavior.f12786r = iA;
        int[] iArr = F1.H.f2303a;
        boolean z6 = view.getLayoutDirection() == 1;
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        boolean z10 = bottomSheetBehavior.f12782m;
        F1.U u4 = v10.f2321a;
        if (z10) {
            int i10 = u4.h().d;
            bottomSheetBehavior.f12785q = i10;
            paddingBottom = i10 + c0064d.d;
        }
        int i11 = c0064d.c;
        if (bottomSheetBehavior.f12783n) {
            paddingLeft = (z6 ? i11 : i7) + u4.h().f24113a;
        }
        if (bottomSheetBehavior.o) {
            if (!z6) {
                i7 = i11;
            }
            paddingRight = u4.h().c + i7;
        }
        view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
        boolean z11 = c0050y0.f300m;
        if (z11) {
            bottomSheetBehavior.f12781k = u4.f().d;
        }
        if (z10 || z11) {
            bottomSheetBehavior.G();
        }
        return v10;
    }

    public void y(B2.t tVar, h3.H h10) {
        int i7 = 0;
        while (true) {
            L[] lArr = (L[]) this.f4151m;
            if (i7 >= lArr.length) {
                return;
            }
            h10.a();
            h10.b();
            L lU = tVar.u(h10.d, 3);
            C1208p c1208p = (C1208p) ((List) this.l).get(i7);
            String str = c1208p.f14316n;
            AbstractC1346c.b("Invalid closed caption MIME type provided: " + str, "application/cea-608".equals(str) || "application/cea-708".equals(str));
            C1207o c1207o = new C1207o();
            h10.b();
            c1207o.f14237a = h10.f15293e;
            c1207o.f14246m = e2.L.l(str);
            c1207o.f14239e = c1208p.f14308e;
            c1207o.d = c1208p.d;
            c1207o.f14232G = c1208p.f14300H;
            c1207o.f14248p = c1208p.f14318q;
            lU.d(new C1208p(c1207o));
            lArr[i7] = lU;
            i7++;
        }
    }

    public R7.c z(C1562g c1562g, InterfaceC1788f interfaceC1788f) {
        A7.m.f(c1562g, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        InterfaceC0652e interfaceC0652eF = AbstractC0669w.f((InterfaceC0671y) this.l, AbstractC0904a.D(interfaceC1788f, c1562g.f17341n), (B8.t) this.f4151m);
        Map mapX = n7.v.l;
        if (c1562g.o.size() != 0 && !H8.i.f(interfaceC0652eF) && r8.d.n(interfaceC0652eF, 5)) {
            Collection collectionH = interfaceC0652eF.H();
            A7.m.e(collectionH, "annotationClass.constructors");
            C0790i c0790i = (C0790i) AbstractC1998m.s0(collectionH);
            if (c0790i != null) {
                List listD0 = c0790i.D0();
                A7.m.e(listD0, "constructor.valueParameters");
                int iQ = n7.z.q(AbstractC2000o.J(listD0, 10));
                if (iQ < 16) {
                    iQ = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
                for (Object obj : listD0) {
                    linkedHashMap.put(((Q) obj).getName(), obj);
                }
                List<C1560e> list = c1562g.o;
                A7.m.e(list, "proto.argumentList");
                ArrayList arrayList = new ArrayList();
                for (C1560e c1560e : list) {
                    A7.m.e(c1560e, "it");
                    Q q4 = (Q) linkedHashMap.get(AbstractC0904a.F(interfaceC1788f, c1560e.f17327n));
                    if (q4 != null) {
                        C2061f c2061fF = AbstractC0904a.F(interfaceC1788f, c1560e.f17327n);
                        AbstractC0287w abstractC0287wB = q4.b();
                        A7.m.e(abstractC0287wB, "parameter.type");
                        C1559d c1559d = c1560e.o;
                        A7.m.e(c1559d, "proto.value");
                        t8.g gVarM = M(abstractC0287wB, c1559d, interfaceC1788f);
                        iVar = C(gVarM, abstractC0287wB, c1559d) ? gVarM : null;
                        if (iVar == null) {
                            String str = "Unexpected argument value: actual type " + c1559d.f17302n + " != expected type " + abstractC0287wB;
                            A7.m.f(str, "message");
                            iVar = new t8.j(str);
                        }
                        iVar = new m7.i(c2061fF, iVar);
                    }
                    if (iVar != null) {
                        arrayList.add(iVar);
                    }
                }
                mapX = n7.z.x(arrayList);
            }
        }
        return new R7.c(interfaceC0652eF.j(), mapX, N.c);
    }

    public /* synthetic */ t(Object obj, Object obj2, boolean z6) {
        this.f4151m = obj;
        this.l = obj2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public t(InterfaceC2782k interfaceC2782k) {
        this.l = (A7.o) interfaceC2782k;
        this.f4151m = new ConcurrentHashMap();
    }

    public t(F1 f12) {
        this.f4151m = f12;
        this.l = new e7.f(17, (byte) 0);
    }

    public t(InterfaceC0671y interfaceC0671y, B8.t tVar) {
        A7.m.f(interfaceC0671y, "module");
        A7.m.f(tVar, "notFoundClasses");
        this.l = interfaceC0671y;
        this.f4151m = tVar;
    }

    public t(List list) {
        this.l = list;
        this.f4151m = new L[list.size()];
    }

    public t(int i7) {
        switch (i7) {
            case 4:
                this.l = new io.sentry.internal.debugmeta.c(3);
                this.f4151m = new io.sentry.internal.debugmeta.c(3);
                break;
            case 5:
                this.l = new V.d(new F[16]);
                break;
            case 6:
                this.l = new V.d(new Reference[16]);
                this.f4151m = new ReferenceQueue();
                break;
            case 7:
            case 8:
            default:
                this.l = new ConcurrentHashMap();
                this.f4151m = new ConcurrentHashMap();
                break;
            case 9:
                this.l = new LinkedHashMap();
                this.f4151m = new LinkedHashMap();
                break;
        }
    }

    public t(View view) {
        this.l = view;
        this.f4151m = h6.r.n(m7.g.f19257m, new K7.K(this, 4));
    }

    public t(Context context) {
        this(context, new L0(5));
    }

    public t(int i7, k1.g gVar) {
        this.l = gVar;
        String str = "start";
        if (i7 != -2) {
            if (i7 == -1) {
                str = "end";
            } else if (i7 == 0) {
                str = "left";
            } else if (i7 != 1) {
                h6.n.j("CCL", "verticalAnchorIndexToAnchorName: Unknown vertical index");
            } else {
                str = "right";
            }
        }
        this.f4151m = str;
    }

    public t(Context context, L0 l02) {
        this.l = context.getApplicationContext();
        this.f4151m = l02;
    }

    public t(Map map) {
        this.f4151m = map;
        this.l = new E8.l("Java nullability annotation states").c(new S0.p(this, 8));
    }

    public t(C0074n c0074n) {
        this.l = c0074n;
        this.f4151m = new AtomicBoolean(false);
    }
}
