package B8;

import A.C0004b;
import A.C0050y0;
import B5.RunnableC0079c;
import E0.X;
import F8.AbstractC0287w;
import N1.C0420a;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;
import Q7.N;
import S8.C0711h;
import S8.C0718o;
import S8.C0722t;
import S8.InterfaceC0725w;
import S8.W;
import T7.Q;
import a.AbstractC0904a;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import b3.C0994a;
import d5.C1078G;
import d5.I;
import e2.S;
import g4.C1298b;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import io.sentry.B0;
import io.sentry.C1422a;
import io.sentry.C1458d0;
import io.sentry.C1459d1;
import io.sentry.C1474i1;
import io.sentry.C1477j1;
import io.sentry.C1480k1;
import io.sentry.C1489n1;
import io.sentry.C1503q;
import io.sentry.C1523x;
import io.sentry.C1524x0;
import io.sentry.CallableC1462e1;
import io.sentry.CallableC1465f1;
import io.sentry.CallableC1471h1;
import io.sentry.EnumC1469h;
import io.sentry.EnumC1492o1;
import io.sentry.EnumC1495p1;
import io.sentry.F;
import io.sentry.F1;
import io.sentry.G;
import io.sentry.G0;
import io.sentry.H;
import io.sentry.H1;
import io.sentry.InterfaceC1511t;
import io.sentry.InterfaceC1522w1;
import io.sentry.K;
import io.sentry.L;
import io.sentry.L0;
import io.sentry.N0;
import io.sentry.R1;
import io.sentry.RunnableC1509s0;
import io.sentry.S1;
import io.sentry.SpotlightIntegration;
import io.sentry.T;
import io.sentry.V;
import io.sentry.X0;
import io.sentry.Y1;
import io.sentry.Z0;
import io.sentry.a2;
import io.sentry.android.core.C1443t;
import io.sentry.android.core.d0;
import io.sentry.b2;
import io.sentry.protocol.C1498c;
import j8.C1565j;
import j8.C1574t;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import k3.C0;
import k3.C1639f;
import k3.C1679y0;
import k3.I0;
import k3.InterfaceC1636e;
import k3.RunnableC1630c;
import k3.y1;
import k3.z1;
import k8.C1708a;
import l3.C1764u;
import l8.C1789g;
import l8.InterfaceC1788f;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2057b;
import o8.C2061f;
import t8.C2448a;
import u.C2458F;
import u.C2465e;
import u2.n0;
import w.AbstractC2571b;
import w.J;
import w8.C2607b;
import x.AbstractC2652s;
import x.H0;
import x.InterfaceC2616E;
import x.InterfaceC2653t;
import x3.AbstractC2673a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class t implements InterfaceC0093e, Y2.l, h8.m, h8.n, H, H0 {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f807m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f808n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f809p;

    public /* synthetic */ t(Object obj, int i7) {
        this.l = i7;
        this.f808n = obj;
    }

    public static ArrayList R(C1523x c1523x) {
        ArrayList arrayList = new ArrayList(c1523x.f16983b);
        C1422a c1422a = c1523x.c;
        if (c1422a != null) {
            arrayList.add(c1422a);
        }
        C1422a c1422a2 = c1523x.d;
        if (c1422a2 != null) {
            arrayList.add(c1422a2);
        }
        C1422a c1422a3 = c1523x.f16984e;
        if (c1422a3 != null) {
            arrayList.add(c1422a3);
        }
        return arrayList;
    }

    public static ConnectivityManager V(Context context, K k10) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            k10.k(EnumC1495p1.INFO, "ConnectivityManager is null and cannot check network status", new Object[0]);
        }
        return connectivityManager;
    }

    public static boolean k0(Context context, K k10, io.sentry.android.core.B b10, ConnectivityManager.NetworkCallback networkCallback) {
        b10.getClass();
        ConnectivityManager connectivityManagerV = V(context, k10);
        if (connectivityManagerV == null) {
            return false;
        }
        if (!AbstractC1386c.l(context, "android.permission.ACCESS_NETWORK_STATE")) {
            k10.k(EnumC1495p1.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return false;
        }
        try {
            connectivityManagerV.registerDefaultNetworkCallback(networkCallback);
            return true;
        } catch (Throwable th) {
            k10.s(EnumC1495p1.WARNING, "registerDefaultNetworkCallback failed", th);
            return false;
        }
    }

    public void A(n3.z zVar) {
        if (((n3.z) zVar.f19923f) != null) {
            M();
            ((S6.k) this.f809p).a('\n');
        }
    }

    public C1489n1 B(X0 x02, ArrayList arrayList, R1 r12, a2 a2Var, G0 g02) {
        io.sentry.protocol.t tVar;
        int i7 = 1;
        int i10 = 24;
        ArrayList arrayList2 = new ArrayList();
        F1 f12 = (F1) this.f808n;
        if (x02 != null) {
            T serializer = f12.getSerializer();
            Charset charset = C1474i1.d;
            h6.e.q(serializer, "ISerializer is required.");
            io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(new CallableC1462e1(serializer, i7, x02), i10);
            arrayList2.add(new C1474i1(new C1477j1(EnumC1492o1.resolve(x02), new CallableC1465f1(cVar, 2), "application/json", (String) null, (String) null), new CallableC1465f1(cVar, 3)));
            tVar = x02.l;
        } else {
            tVar = null;
        }
        if (r12 != null) {
            arrayList2.add(C1474i1.b(f12.getSerializer(), r12));
        }
        if (g02 != null) {
            long maxTraceFileSize = f12.getMaxTraceFileSize();
            T serializer2 = f12.getSerializer();
            Charset charset2 = C1474i1.d;
            File file = g02.l;
            io.sentry.internal.debugmeta.c cVar2 = new io.sentry.internal.debugmeta.c(new CallableC1471h1(file, maxTraceFileSize, g02, serializer2), i10);
            arrayList2.add(new C1474i1(new C1477j1(EnumC1492o1.Profile, new CallableC1465f1(cVar2, 0), "application-json", file.getName(), (String) null), new CallableC1465f1(cVar2, i7)));
            if (tVar == null) {
                tVar = new io.sentry.protocol.t(g02.f15844H);
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C1422a c1422a = (C1422a) it.next();
                T serializer3 = f12.getSerializer();
                K logger = f12.getLogger();
                long maxAttachmentSize = f12.getMaxAttachmentSize();
                Charset charset3 = C1474i1.d;
                io.sentry.internal.debugmeta.c cVar3 = new io.sentry.internal.debugmeta.c(new CallableC1471h1(c1422a, maxAttachmentSize, serializer3, logger), i10);
                arrayList2.add(new C1474i1(new C1477j1(EnumC1492o1.Attachment, new CallableC1465f1(cVar3, 12), c1422a.d, c1422a.c, c1422a.f16000e), new CallableC1465f1(cVar3, 13)));
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return new C1489n1(new C1459d1(tVar, f12.getSdkVersion(), a2Var), arrayList2);
    }

    @Override // h8.n
    public void B0(Object obj) {
        ((ArrayList) this.f808n).add(H0.b.d((H0.b) this.o, (C2061f) this.f809p, obj));
    }

    public C1489n1 C(final H1 h12, final L0 l02, a2 a2Var, final boolean z6) {
        ArrayList arrayList = new ArrayList();
        F1 f12 = (F1) this.f808n;
        final T serializer = f12.getSerializer();
        final K logger = f12.getLogger();
        Charset charset = C1474i1.d;
        final File file = h12.f15870A;
        io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(new Callable() { // from class: io.sentry.g1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                T t10 = serializer;
                H1 h13 = h12;
                File file2 = file;
                K k10 = logger;
                boolean z10 = z6;
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, C1474i1.d));
                        try {
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            t10.f(h13, bufferedWriter);
                            linkedHashMap.put(EnumC1492o1.ReplayEvent.getItemType(), byteArrayOutputStream.toByteArray());
                            byteArrayOutputStream.reset();
                            L0 l03 = l02;
                            if (l03 != null) {
                                t10.f(l03, bufferedWriter);
                                linkedHashMap.put(EnumC1492o1.ReplayRecording.getItemType(), byteArrayOutputStream.toByteArray());
                                byteArrayOutputStream.reset();
                            }
                            if (file2 != null && file2.exists()) {
                                byte[] bArrM = AbstractC1386c.m(file2.getPath(), 10485760L);
                                if (bArrM.length > 0) {
                                    linkedHashMap.put(EnumC1492o1.ReplayVideo.getItemType(), bArrM);
                                }
                            }
                            byte[] bArrF = C1474i1.f(linkedHashMap);
                            bufferedWriter.close();
                            byteArrayOutputStream.close();
                        } finally {
                        }
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        k10.s(EnumC1495p1.ERROR, "Could not serialize replay recording", th);
                        if (file2 != null) {
                            if (z10) {
                                AbstractC1386c.g(file2.getParentFile());
                            } else {
                                file2.delete();
                            }
                        }
                        return null;
                    } finally {
                        if (file2 != null) {
                            if (z10) {
                                AbstractC1386c.g(file2.getParentFile());
                            } else {
                                file2.delete();
                            }
                        }
                    }
                }
            }
        }, 24);
        arrayList.add(new C1474i1(new C1477j1(EnumC1492o1.ReplayVideo, new CallableC1465f1(cVar, 6), (String) null, (String) null, (String) null), new CallableC1465f1(cVar, 8)));
        return new C1489n1(new C1459d1(h12.l, f12.getSdkVersion(), a2Var), arrayList);
    }

    public io.sentry.protocol.t D(C1489n1 c1489n1, C1523x c1523x) {
        if (c1523x == null) {
            c1523x = new C1523x();
        }
        try {
            c1523x.a();
            return n0(c1489n1, c1523x);
        } catch (IOException e10) {
            ((F1) this.f808n).getLogger().s(EnumC1495p1.ERROR, "Failed to capture envelope.", e10);
            return io.sentry.protocol.t.f16774m;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public io.sentry.protocol.t E(io.sentry.C1480k1 r12, io.sentry.N0 r13, io.sentry.C1523x r14) {
        /*
            Method dump skipped, instruction units count: 683
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.t.E(io.sentry.k1, io.sentry.N0, io.sentry.x):io.sentry.protocol.t");
    }

    public io.sentry.protocol.t F(H1 h12, N0 n02, C1523x c1523x) {
        C1498c c1498c;
        S1 s1C;
        if (c1523x == null) {
            c1523x = new C1523x();
        }
        if (q0(h12, c1523x) && n02 != null) {
            if (h12.o == null) {
                h12.o = n02.d;
            }
            if (h12.f15984t == null) {
                h12.f15984t = n02.f15894b;
            }
            AbstractMap abstractMap = h12.f15980p;
            ConcurrentHashMap concurrentHashMap = n02.f15897g;
            if (abstractMap == null) {
                h12.f15980p = new HashMap(new HashMap(h6.r.p(concurrentHashMap)));
            } else {
                for (Map.Entry entry : h6.r.p(concurrentHashMap).entrySet()) {
                    if (!h12.f15980p.containsKey(entry.getKey())) {
                        h12.f15980p.put((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
            Iterator it = new C1498c(n02.o).entrySet().iterator();
            while (true) {
                boolean zHasNext = it.hasNext();
                c1498c = h12.f15978m;
                if (!zHasNext) {
                    break;
                }
                Map.Entry entry2 = (Map.Entry) it.next();
                if (!c1498c.containsKey(entry2.getKey())) {
                    c1498c.put((String) entry2.getKey(), entry2.getValue());
                }
            }
            V v10 = n02.f15893a;
            if (v10 != null && (s1C = v10.c()) != null) {
                v10 = s1C;
            }
            if (c1498c.b() == null) {
                if (v10 == null) {
                    c1498c.e(b2.a(n02.f15905q));
                } else {
                    c1498c.e(v10.p());
                }
            }
        }
        F1 f12 = (F1) this.f808n;
        f12.getLogger().k(EnumC1495p1.DEBUG, "Capturing session replay: %s", h12.l);
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        io.sentry.protocol.t tVar2 = h12.l;
        if (tVar2 != null) {
            tVar = tVar2;
        }
        Iterator<InterfaceC1511t> it2 = f12.getEventProcessors().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            InterfaceC1511t next = it2.next();
            try {
                h12 = next.b(h12, c1523x);
            } catch (Throwable th) {
                f12.getLogger().p(EnumC1495p1.ERROR, th, "An exception occurred while processing replay event by processor: %s", next.getClass().getName());
            }
            if (h12 == null) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "Replay event was dropped by a processor: %s", next.getClass().getName());
                f12.getClientReportRecorder().g(io.sentry.clientreport.d.EVENT_PROCESSOR, EnumC1469h.Replay);
                break;
            }
        }
        if (h12 != null) {
            f12.getBeforeSendReplay();
        }
        if (h12 == null) {
            return io.sentry.protocol.t.f16774m;
        }
        a2 a2VarQ = null;
        if (n02 != null) {
            try {
                V v11 = n02.f15893a;
                if (v11 != null) {
                    a2VarQ = v11.d();
                } else {
                    A2.c cVar = (A2.c) n02.f(new C1458d0(f12, 8, n02)).o;
                    if (cVar != null) {
                        a2VarQ = cVar.q();
                    }
                }
            } catch (IOException e10) {
                f12.getLogger().p(EnumC1495p1.WARNING, e10, "Capturing event %s failed.", tVar);
                return io.sentry.protocol.t.f16774m;
            }
        }
        C1489n1 c1489n1C = C(h12, c1523x.f16985f, a2VarQ, io.sentry.hints.b.class.isInstance(AbstractC1387d.g(c1523x)));
        c1523x.a();
        ((io.sentry.transport.f) this.o).n(c1489n1C, c1523x);
        return tVar;
    }

    public void G(R1 r12, C1523x c1523x) {
        h6.e.q(r12, "Session is required.");
        F1 f12 = (F1) this.f808n;
        String str = r12.f15939x;
        if (str == null || str.isEmpty()) {
            f12.getLogger().k(EnumC1495p1.WARNING, "Sessions can't be captured without setting a release.", new Object[0]);
            return;
        }
        try {
            T serializer = f12.getSerializer();
            io.sentry.protocol.r sdkVersion = f12.getSdkVersion();
            h6.e.q(serializer, "Serializer is required.");
            D(new C1489n1((io.sentry.protocol.t) null, sdkVersion, C1474i1.b(serializer, r12)), c1523x);
        } catch (IOException e10) {
            f12.getLogger().s(EnumC1495p1.ERROR, "Failed to capture session.", e10);
        }
    }

    public io.sentry.protocol.t H(io.sentry.protocol.A a10, a2 a2Var, N0 n02, C1523x c1523x, G0 g02) {
        io.sentry.protocol.A aH0 = a10;
        C1523x c1523x2 = c1523x == null ? new C1523x() : c1523x;
        if (q0(a10, c1523x2) && n02 != null) {
            c1523x2.f16983b.addAll(new CopyOnWriteArrayList(n02.f15904p));
        }
        F1 f12 = (F1) this.f808n;
        K logger = f12.getLogger();
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        logger.k(enumC1495p1, "Capturing transaction: %s", aH0.l);
        io.sentry.protocol.t tVar = io.sentry.protocol.t.f16774m;
        io.sentry.protocol.t tVar2 = aH0.l;
        io.sentry.protocol.t tVar3 = tVar2 != null ? tVar2 : tVar;
        if (q0(a10, c1523x2)) {
            z(a10, n02);
            if (n02 != null) {
                aH0 = h0(a10, c1523x2, n02.f15899i);
            }
            if (aH0 == null) {
                f12.getLogger().k(enumC1495p1, "Transaction was dropped by applyScope", new Object[0]);
            }
        }
        if (aH0 != null) {
            aH0 = h0(aH0, c1523x2, f12.getEventProcessors());
        }
        io.sentry.protocol.A a11 = aH0;
        if (a11 == null) {
            f12.getLogger().k(enumC1495p1, "Transaction was dropped by Event processors.", new Object[0]);
            return tVar;
        }
        ArrayList arrayList = a11.f16649D;
        int size = arrayList.size();
        f12.getBeforeSendTransaction();
        int size2 = arrayList.size();
        if (size2 < size) {
            int i7 = size - size2;
            f12.getLogger().k(enumC1495p1, "%d spans were dropped by beforeSendTransaction.", Integer.valueOf(i7));
            f12.getClientReportRecorder().h(io.sentry.clientreport.d.BEFORE_SEND, EnumC1469h.Span, i7);
        }
        try {
            ArrayList arrayListR = R(c1523x2);
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayListR.iterator();
            while (it.hasNext()) {
                ((C1422a) it.next()).getClass();
            }
            C1489n1 c1489n1B = B(a11, arrayList2, null, a2Var, g02);
            c1523x2.a();
            return c1489n1B != null ? n0(c1489n1B, c1523x2) : tVar3;
        } catch (io.sentry.exception.b | IOException e10) {
            f12.getLogger().p(EnumC1495p1.WARNING, e10, "Capturing transaction %s failed.", tVar3);
            return io.sentry.protocol.t.f16774m;
        }
    }

    public void I(C1298b c1298b) {
        g4.x xVar;
        synchronized (this) {
            ((HashMap) this.o).remove(c1298b.f14852a);
            if (c1298b.f14853b && (xVar = c1298b.c) != null) {
                ((g4.l) this.f807m).e(c1298b.f14852a, new g4.r(xVar, true, false, c1298b.f14852a, (g4.l) this.f807m));
            }
        }
    }

    public void J(boolean z6) {
        long shutdownTimeoutMillis;
        io.sentry.transport.f fVar = (io.sentry.transport.f) this.o;
        F1 f12 = (F1) this.f808n;
        f12.getLogger().k(EnumC1495p1.INFO, "Closing SentryClient.", new Object[0]);
        try {
            ((L) this.f807m).close();
        } catch (IOException e10) {
            f12.getLogger().s(EnumC1495p1.WARNING, "Failed to close the metrics aggregator.", e10);
        }
        if (z6) {
            shutdownTimeoutMillis = 0;
        } else {
            try {
                shutdownTimeoutMillis = f12.getShutdownTimeoutMillis();
            } catch (IOException e11) {
                f12.getLogger().s(EnumC1495p1.WARNING, "Failed to close the connection to the Sentry Server.", e11);
            }
        }
        fVar.h(shutdownTimeoutMillis);
        fVar.a(z6);
        for (InterfaceC1511t interfaceC1511t : f12.getEventProcessors()) {
            if (interfaceC1511t instanceof Closeable) {
                try {
                    ((Closeable) interfaceC1511t).close();
                } catch (IOException e12) {
                    f12.getLogger().k(EnumC1495p1.WARNING, "Failed to close the event processor {}.", interfaceC1511t, e12);
                }
            }
        }
    }

    public void K(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((C2458F) this.o).get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i7 = 0; i7 < size; i7++) {
                K(arrayList2.get(i7), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object L(s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r7 instanceof N1.C0429j
            if (r0 == 0) goto L13
            r0 = r7
            N1.j r0 = (N1.C0429j) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            N1.j r0 = new N1.j
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.f4531m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3a
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            B8.t r6 = r0.l
            h8.AbstractC1386c.u(r7)
            goto L63
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            B8.t r6 = r0.l
            h8.AbstractC1386c.u(r7)
            goto L72
        L3a:
            h8.AbstractC1386c.u(r7)
            java.lang.Object r7 = r6.f809p
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r2 = r6.f807m
            N1.Q r2 = (N1.Q) r2
            if (r7 == 0) goto L66
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L4e
            goto L66
        L4e:
            N1.b0 r7 = r2.f()
            N1.m r4 = new N1.m
            r5 = 0
            r4.<init>(r2, r6, r5)
            r0.l = r6
            r0.o = r3
            java.lang.Object r7 = r7.b(r4, r0)
            if (r7 != r1) goto L63
            return r1
        L63:
            N1.d r7 = (N1.C0423d) r7
            goto L74
        L66:
            r0.l = r6
            r0.o = r4
            r7 = 0
            java.lang.Object r7 = N1.Q.e(r2, r7, r0)
            if (r7 != r1) goto L72
            return r1
        L72:
            N1.d r7 = (N1.C0423d) r7
        L74:
            java.lang.Object r6 = r6.f807m
            N1.Q r6 = (N1.Q) r6
            N1.S r6 = r6.f4492r
            r6.b(r7)
            m7.q r6 = m7.q.f19267a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.t.L(s7.c):java.lang.Object");
    }

    public void M() {
        S6.k kVar = (S6.k) this.f809p;
        StringBuilder sb = kVar.l;
        if (sb.length() <= 0 || '\n' == sb.charAt(sb.length() - 1)) {
            return;
        }
        kVar.a('\n');
    }

    public void N(ArrayDeque arrayDeque, Object obj) {
        synchronized (this) {
            if (!arrayDeque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        j0();
    }

    public void O(g9.g gVar) {
        A7.m.f(gVar, "call");
        gVar.f15054m.decrementAndGet();
        N((ArrayDeque) this.f809p, gVar);
    }

    public void P(C1639f c1639f) {
        I0 i02 = (I0) ((WeakReference) this.f807m).get();
        if (i02 == null) {
            return;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        while (atomicBoolean.get()) {
            atomicBoolean.set(false);
            InterfaceC1636e interfaceC1636e = (InterfaceC1636e) c1639f.c.poll();
            if (interfaceC1636e == null) {
                c1639f.f17945f = false;
                return;
            }
            AtomicBoolean atomicBoolean2 = new AtomicBoolean(true);
            AbstractC1343A.K(i02.l, new C0(i02, X(c1639f.f17942a), new d0(this, interfaceC1636e, atomicBoolean2, c1639f, atomicBoolean, 1)));
            atomicBoolean2.set(false);
        }
    }

    public void Q(final C1679y0 c1679y0) {
        synchronized (this.f808n) {
            try {
                C1639f c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
                if (c1639f == null) {
                    return;
                }
                final S s6 = c1639f.f17946g;
                c1639f.f17946g = S.f13999b;
                c1639f.c.add(new InterfaceC1636e(c1679y0, s6) { // from class: k3.d

                    /* JADX INFO: renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ C1679y0 f17935b;

                    @Override // k3.InterfaceC1636e
                    public final h5.y run() {
                        I0 i02 = (I0) ((WeakReference) this.f17934a.f807m).get();
                        if (i02 != null) {
                            i02.o(this.f17935b);
                        }
                        return h5.v.f15564m;
                    }
                });
                if (c1639f.f17945f) {
                    return;
                }
                c1639f.f17945f = true;
                P(c1639f);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public S S(C1679y0 c1679y0) {
        synchronized (this.f808n) {
            try {
                C1639f c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
                if (c1639f == null) {
                    return null;
                }
                return c1639f.f17944e;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public InterfaceC0652e T(C2057b c2057b, List list) {
        A7.m.f(c2057b, "classId");
        return (InterfaceC0652e) ((E8.e) this.f807m).b(new Q7.A(c2057b, list));
    }

    public I U() {
        I iW;
        synchronized (this.f808n) {
            iW = I.w(((C2465e) this.o).values());
        }
        return iW;
    }

    @Override // h8.n
    public h8.m W(C2057b c2057b) {
        ArrayList arrayList = new ArrayList();
        return new t(((H0.b) this.o).G(c2057b, N.c, arrayList), this, arrayList);
    }

    public C1679y0 X(Object obj) {
        C1679y0 c1679y0;
        synchronized (this.f808n) {
            c1679y0 = (C1679y0) ((C2465e) this.o).get(obj);
        }
        return c1679y0;
    }

    public X Y(C1679y0 c1679y0) {
        C1639f c1639f;
        synchronized (this.f808n) {
            c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
        }
        if (c1639f != null) {
            return c1639f.f17943b;
        }
        return null;
    }

    public AbstractC2652s Z(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2) {
        if (((AbstractC2652s) this.f809p) == null) {
            this.f809p = abstractC2652s.c();
        }
        AbstractC2652s abstractC2652s3 = (AbstractC2652s) this.f809p;
        if (abstractC2652s3 == null) {
            A7.m.j("velocityVector");
            throw null;
        }
        int iB = abstractC2652s3.b();
        for (int i7 = 0; i7 < iB; i7++) {
            AbstractC2652s abstractC2652s4 = (AbstractC2652s) this.f809p;
            if (abstractC2652s4 == null) {
                A7.m.j("velocityVector");
                throw null;
            }
            abstractC2652s.getClass();
            long j10 = j3 / 1000000;
            w.I iA = ((J) ((C1764u) this.f808n).f18608m).a(abstractC2652s2.a(i7));
            long j11 = iA.c;
            abstractC2652s4.e((((Math.signum(iA.f23072a) * AbstractC2571b.a(j11 > 0 ? j10 / j11 : 1.0f).f23087b) * iA.f23073b) / j11) * 1000.0f, i7);
        }
        AbstractC2652s abstractC2652s5 = (AbstractC2652s) this.f809p;
        if (abstractC2652s5 != null) {
            return abstractC2652s5;
        }
        A7.m.j("velocityVector");
        throw null;
    }

    public boolean a0(C1679y0 c1679y0) {
        boolean z6;
        synchronized (this.f808n) {
            z6 = ((C2465e) this.f809p).get(c1679y0) != null;
        }
        return z6;
    }

    @Override // x.F0
    public long b(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        G7.c it = AbstractC2673a.b0(0, abstractC2652s.b()).iterator();
        long jMax = 0;
        while (it.f2715n) {
            int iB = it.b();
            jMax = Math.max(jMax, ((InterfaceC2653t) this.f808n).get(iB).d(abstractC2652s.a(iB), abstractC2652s2.a(iB), abstractC2652s3.a(iB)));
        }
        return jMax;
    }

    public boolean b0(C1679y0 c1679y0, int i7) {
        C1639f c1639f;
        synchronized (this.f808n) {
            c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
        }
        I0 i02 = (I0) ((WeakReference) this.f807m).get();
        return c1639f != null && c1639f.f17944e.a(i7) && i02 != null && i02.f17811t.A().a(i7);
    }

    @Override // x.F0
    public AbstractC2652s c(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        if (((AbstractC2652s) this.f809p) == null) {
            this.f809p = abstractC2652s3.c();
        }
        AbstractC2652s abstractC2652s4 = (AbstractC2652s) this.f809p;
        if (abstractC2652s4 == null) {
            A7.m.j("velocityVector");
            throw null;
        }
        int iB = abstractC2652s4.b();
        for (int i7 = 0; i7 < iB; i7++) {
            AbstractC2652s abstractC2652s5 = (AbstractC2652s) this.f809p;
            if (abstractC2652s5 == null) {
                A7.m.j("velocityVector");
                throw null;
            }
            abstractC2652s5.e(((InterfaceC2653t) this.f808n).get(i7).c(j3, abstractC2652s.a(i7), abstractC2652s2.a(i7), abstractC2652s3.a(i7)), i7);
        }
        AbstractC2652s abstractC2652s6 = (AbstractC2652s) this.f809p;
        if (abstractC2652s6 != null) {
            return abstractC2652s6;
        }
        A7.m.j("velocityVector");
        throw null;
    }

    public boolean c0(D8.v vVar) {
        A7.m.f(vVar, "descriptor");
        if (!A7.m.a((D8.v) this.o, vVar)) {
            t tVar = (t) this.f808n;
            if (!(tVar != null ? tVar.c0(vVar) : false)) {
                return false;
            }
        }
        return true;
    }

    public boolean d0(C1679y0 c1679y0, int i7) {
        C1639f c1639f;
        synchronized (this.f808n) {
            c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
        }
        if (c1639f != null) {
            z1 z1Var = c1639f.d;
            z1Var.getClass();
            boolean z6 = false;
            AbstractC1346c.b("Use contains(Command) for custom command", i7 != 0);
            Iterator<E> it = z1Var.f18173a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (((y1) it.next()).f18167a == i7) {
                    z6 = true;
                    break;
                }
            }
            if (z6) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [io.sentry.K] */
    /* JADX WARN: Type inference failed for: r3v3, types: [io.sentry.K] */
    /* JADX WARN: Type inference failed for: r3v7, types: [io.sentry.F] */
    @Override // io.sentry.H
    public F e() {
        Context context = (Context) this.f808n;
        ?? r32 = (K) this.o;
        ConnectivityManager connectivityManagerV = V(context, r32);
        if (connectivityManagerV == null) {
            return F.UNKNOWN;
        }
        if (!AbstractC1386c.l(context, "android.permission.ACCESS_NETWORK_STATE")) {
            r32.k(EnumC1495p1.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            return F.NO_PERMISSION;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManagerV.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                r32.k(EnumC1495p1.INFO, "NetworkInfo is null, there's no active network.", new Object[0]);
                r32 = F.DISCONNECTED;
            } else {
                r32 = activeNetworkInfo.isConnected() ? F.CONNECTED : F.DISCONNECTED;
            }
            return r32;
        } catch (Throwable th) {
            r32.s(EnumC1495p1.WARNING, "Could not retrieve Connection Status", th);
            return F.UNKNOWN;
        }
    }

    public boolean e0(C1679y0 c1679y0, y1 y1Var) {
        C1639f c1639f;
        synchronized (this.f808n) {
            c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
        }
        if (c1639f != null) {
            z1 z1Var = c1639f.d;
            z1Var.getClass();
            y1Var.getClass();
            if (z1Var.f18173a.contains(y1Var)) {
                return true;
            }
        }
        return false;
    }

    @Override // io.sentry.H
    public boolean f(G g7) {
        io.sentry.android.core.B b10 = (io.sentry.android.core.B) this.f809p;
        b10.getClass();
        io.sentry.android.core.internal.util.a aVar = new io.sentry.android.core.internal.util.a(this, g7);
        ((HashMap) this.f807m).put(g7, aVar);
        return k0((Context) this.f808n, (K) this.o, b10, aVar);
    }

    public int f0() {
        return ((S6.k) this.f809p).l.length();
    }

    @Override // io.sentry.H
    public String g() {
        io.sentry.android.core.B b10 = (io.sentry.android.core.B) this.f809p;
        Context context = (Context) this.f808n;
        K k10 = (K) this.o;
        ConnectivityManager connectivityManagerV = V(context, k10);
        String str = null;
        if (connectivityManagerV != null) {
            if (AbstractC1386c.l(context, "android.permission.ACCESS_NETWORK_STATE")) {
                try {
                    b10.getClass();
                    Network activeNetwork = connectivityManagerV.getActiveNetwork();
                    if (activeNetwork == null) {
                        k10.k(EnumC1495p1.INFO, "Network is null and cannot check network status", new Object[0]);
                    } else {
                        NetworkCapabilities networkCapabilities = connectivityManagerV.getNetworkCapabilities(activeNetwork);
                        if (networkCapabilities == null) {
                            k10.k(EnumC1495p1.INFO, "NetworkCapabilities is null and cannot check network type", new Object[0]);
                        } else {
                            boolean zHasTransport = networkCapabilities.hasTransport(3);
                            boolean zHasTransport2 = networkCapabilities.hasTransport(1);
                            boolean zHasTransport3 = networkCapabilities.hasTransport(0);
                            if (zHasTransport) {
                                str = "ethernet";
                            } else if (zHasTransport2) {
                                str = "wifi";
                            } else if (zHasTransport3) {
                                str = "cellular";
                            }
                        }
                    }
                } catch (Throwable th) {
                    k10.s(EnumC1495p1.ERROR, "Failed to retrieve network info", th);
                }
            } else {
                k10.k(EnumC1495p1.INFO, "No permission (ACCESS_NETWORK_STATE) to check network status.", new Object[0]);
            }
        }
        return str;
    }

    public C1480k1 g0(C1480k1 c1480k1, C1523x c1523x, List list) {
        F1 f12 = (F1) this.f808n;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            InterfaceC1511t interfaceC1511t = (InterfaceC1511t) it.next();
            try {
                boolean z6 = interfaceC1511t instanceof C1443t;
                boolean zIsInstance = io.sentry.hints.b.class.isInstance(AbstractC1387d.g(c1523x));
                if (zIsInstance && z6) {
                    c1480k1 = ((C1443t) interfaceC1511t).i(c1480k1, c1523x);
                } else if (!zIsInstance && !z6) {
                    c1480k1 = interfaceC1511t.i(c1480k1, c1523x);
                }
            } catch (Throwable th) {
                f12.getLogger().p(EnumC1495p1.ERROR, th, "An exception occurred while processing event by processor: %s", interfaceC1511t.getClass().getName());
            }
            if (c1480k1 == null) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "Event was dropped by a processor: %s", interfaceC1511t.getClass().getName());
                f12.getClientReportRecorder().g(io.sentry.clientreport.d.EVENT_PROCESSOR, EnumC1469h.Error);
                break;
            }
        }
        return c1480k1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    @Override // Y2.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(byte[] r35, int r36, int r37, Y2.k r38, h2.g r39) {
        /*
            Method dump skipped, instruction units count: 618
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.t.h(byte[], int, int, Y2.k, h2.g):void");
    }

    public io.sentry.protocol.A h0(io.sentry.protocol.A a10, C1523x c1523x, List list) {
        F1 f12 = (F1) this.f808n;
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            InterfaceC1511t interfaceC1511t = (InterfaceC1511t) it.next();
            int size = a10.f16649D.size();
            try {
                a10 = interfaceC1511t.g(a10, c1523x);
            } catch (Throwable th) {
                f12.getLogger().p(EnumC1495p1.ERROR, th, "An exception occurred while processing transaction by processor: %s", interfaceC1511t.getClass().getName());
            }
            int size2 = a10 == null ? 0 : a10.f16649D.size();
            if (a10 == null) {
                f12.getLogger().k(EnumC1495p1.DEBUG, "Transaction was dropped by a processor: %s", interfaceC1511t.getClass().getName());
                io.sentry.clientreport.f clientReportRecorder = f12.getClientReportRecorder();
                io.sentry.clientreport.d dVar = io.sentry.clientreport.d.EVENT_PROCESSOR;
                clientReportRecorder.g(dVar, EnumC1469h.Transaction);
                f12.getClientReportRecorder().h(dVar, EnumC1469h.Span, size + 1);
                break;
            }
            if (size2 < size) {
                int i7 = size - size2;
                f12.getLogger().k(EnumC1495p1.DEBUG, "%d spans were dropped by a processor: %s", Integer.valueOf(i7), interfaceC1511t.getClass().getName());
                f12.getClientReportRecorder().h(io.sentry.clientreport.d.EVENT_PROCESSOR, EnumC1469h.Span, i7);
            }
        }
        return a10;
    }

    @Override // h8.m
    public void i() {
        switch (this.l) {
            case 14:
                ArrayList arrayList = (ArrayList) this.o;
                if (!arrayList.isEmpty()) {
                    ((HashMap) ((io.sentry.internal.debugmeta.c) this.f809p).f16570n).put((h8.p) this.f808n, arrayList);
                }
                break;
            case 15:
                ((D) this.o).i();
                ((ArrayList) ((t) this.f809p).f808n).add(new C2448a((R7.b) AbstractC1998m.r0((ArrayList) this.f807m)));
                break;
            default:
                ArrayList arrayList2 = (ArrayList) this.f808n;
                D d = (D) this.f807m;
                d.getClass();
                A7.m.f(arrayList2, "elements");
                C2061f c2061f = (C2061f) this.f809p;
                if (c2061f != null) {
                    Q qV = l9.d.V(c2061f, (InterfaceC0652e) d.f749p);
                    if (qV != null) {
                        HashMap map = (HashMap) d.f748n;
                        List listD = O8.l.d(arrayList2);
                        AbstractC0287w abstractC0287wB = qV.b();
                        A7.m.e(abstractC0287wB, "parameter.type");
                        map.put(c2061f, new t8.w(listD, abstractC0287wB));
                        break;
                    } else if (((H0.b) d.o).E((C2057b) d.f750q) && A7.m.a(c2061f.b(), "value")) {
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj : arrayList2) {
                            if (obj instanceof C2448a) {
                                arrayList3.add(obj);
                            }
                        }
                        Iterator it = arrayList3.iterator();
                        while (it.hasNext()) {
                            ((List) d.f751r).add((R7.b) ((C2448a) it.next()).f22499a);
                        }
                        break;
                    }
                }
                break;
        }
    }

    @Override // h8.n
    public void i0(C2057b c2057b, C2061f c2061f) {
        ((ArrayList) this.f808n).add(new t8.i(c2057b, c2061f));
    }

    @Override // h8.m
    public void j(C2061f c2061f, t8.f fVar) {
        ((D) this.f808n).j(c2061f, fVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j0() {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.t.j0():void");
    }

    @Override // h8.m
    public h8.n k(C2061f c2061f) {
        return ((D) this.f808n).k(c2061f);
    }

    @Override // h8.m
    public h8.m l(C2057b c2057b, C2061f c2061f) {
        return ((D) this.f808n).l(c2057b, c2061f);
    }

    public void l0(C1679y0 c1679y0) {
        synchronized (this.f808n) {
            try {
                C1639f c1639f = (C1639f) ((C2465e) this.f809p).remove(c1679y0);
                if (c1639f == null) {
                    return;
                }
                ((C2465e) this.o).remove(c1639f.f17942a);
                c1639f.f17943b.a();
                I0 i02 = (I0) ((WeakReference) this.f807m).get();
                if (i02 == null || i02.h()) {
                    return;
                }
                AbstractC1343A.K(i02.l, new RunnableC1630c(i02, c1679y0, 0));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // h8.m
    public void m(C2061f c2061f, C2057b c2057b, C2061f c2061f2) {
        ((D) this.f808n).m(c2061f, c2057b, c2061f2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object m0(s7.AbstractC2410c r9) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r9 instanceof N1.W
            if (r0 == 0) goto L13
            r0 = r9
            N1.W r0 = (N1.W) r0
            int r1 = r0.f4504p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f4504p = r1
            goto L18
        L13:
            N1.W r0 = new N1.W
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.f4503n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f4504p
            m7.q r3 = m7.q.f19267a
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L46
            if (r2 == r5) goto L3c
            if (r2 != r4) goto L34
            b9.a r8 = r0.f4502m
            B8.t r0 = r0.l
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Throwable -> L31
            goto L92
        L31:
            r9 = move-exception
            goto La5
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3c:
            b9.a r8 = r0.f4502m
            B8.t r2 = r0.l
            h8.AbstractC1386c.u(r9)
            r9 = r8
            r8 = r2
            goto L6c
        L46:
            h8.AbstractC1386c.u(r9)
            java.lang.Object r9 = r8.o
            S8.o r9 = (S8.C0718o) r9
            r9.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = S8.d0.l
            java.lang.Object r9 = r2.get(r9)
            boolean r9 = r9 instanceof S8.S
            if (r9 != 0) goto L5b
            return r3
        L5b:
            r0.l = r8
            java.lang.Object r9 = r8.f808n
            b9.c r9 = (b9.c) r9
            r0.f4502m = r9
            r0.f4504p = r5
            java.lang.Object r2 = r9.c(r6, r0)
            if (r2 != r1) goto L6c
            return r1
        L6c:
            java.lang.Object r2 = r8.o     // Catch: java.lang.Throwable -> La3
            S8.o r2 = (S8.C0718o) r2     // Catch: java.lang.Throwable -> La3
            r2.getClass()     // Catch: java.lang.Throwable -> La3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = S8.d0.l     // Catch: java.lang.Throwable -> La3
            java.lang.Object r2 = r5.get(r2)     // Catch: java.lang.Throwable -> La3
            boolean r2 = r2 instanceof S8.S     // Catch: java.lang.Throwable -> La3
            if (r2 != 0) goto L83
            b9.c r9 = (b9.c) r9
            r9.e(r6)
            return r3
        L83:
            r0.l = r8     // Catch: java.lang.Throwable -> La3
            r0.f4502m = r9     // Catch: java.lang.Throwable -> La3
            r0.f4504p = r4     // Catch: java.lang.Throwable -> La3
            java.lang.Object r0 = r8.L(r0)     // Catch: java.lang.Throwable -> La3
            if (r0 != r1) goto L90
            return r1
        L90:
            r0 = r8
            r8 = r9
        L92:
            java.lang.Object r9 = r0.o     // Catch: java.lang.Throwable -> L31
            S8.o r9 = (S8.C0718o) r9     // Catch: java.lang.Throwable -> L31
            r9.T(r3)     // Catch: java.lang.Throwable -> L31
            b9.c r8 = (b9.c) r8
            r8.e(r6)
            return r3
        L9f:
            r7 = r9
            r9 = r8
            r8 = r7
            goto La5
        La3:
            r8 = move-exception
            goto L9f
        La5:
            b9.c r8 = (b9.c) r8
            r8.e(r6)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.t.m0(s7.c):java.lang.Object");
    }

    public io.sentry.protocol.t n0(C1489n1 c1489n1, C1523x c1523x) {
        F1 f12 = (F1) this.f808n;
        InterfaceC1522w1 beforeEnvelopeCallback = f12.getBeforeEnvelopeCallback();
        if (beforeEnvelopeCallback != null) {
            try {
                SpotlightIntegration spotlightIntegration = (SpotlightIntegration) beforeEnvelopeCallback;
                try {
                    spotlightIntegration.f15955n.submit(new A2.m(spotlightIntegration, 9, c1489n1));
                } catch (RejectedExecutionException e10) {
                    spotlightIntegration.f15954m.s(EnumC1495p1.WARNING, "Spotlight envelope submission rejected.", e10);
                }
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.ERROR, "The BeforeEnvelope callback threw an exception.", th);
            }
        }
        ((io.sentry.transport.f) this.o).n(c1489n1, c1523x);
        io.sentry.protocol.t tVar = ((C1459d1) c1489n1.f16624m).l;
        return tVar != null ? tVar : io.sentry.protocol.t.f16774m;
    }

    @Override // x.F0
    public AbstractC2652s o(long j3, AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        if (((AbstractC2652s) this.o) == null) {
            this.o = abstractC2652s.c();
        }
        AbstractC2652s abstractC2652s4 = (AbstractC2652s) this.o;
        if (abstractC2652s4 == null) {
            A7.m.j("valueVector");
            throw null;
        }
        int iB = abstractC2652s4.b();
        for (int i7 = 0; i7 < iB; i7++) {
            AbstractC2652s abstractC2652s5 = (AbstractC2652s) this.o;
            if (abstractC2652s5 == null) {
                A7.m.j("valueVector");
                throw null;
            }
            abstractC2652s5.e(((InterfaceC2653t) this.f808n).get(i7).b(j3, abstractC2652s.a(i7), abstractC2652s2.a(i7), abstractC2652s3.a(i7)), i7);
        }
        AbstractC2652s abstractC2652s6 = (AbstractC2652s) this.o;
        if (abstractC2652s6 != null) {
            return abstractC2652s6;
        }
        A7.m.j("valueVector");
        throw null;
    }

    @Override // h8.n
    public void o0(t8.f fVar) {
        ((ArrayList) this.f808n).add(new t8.r(new t8.p(fVar)));
    }

    @Override // io.sentry.H
    public void p(G g7) {
        ConnectivityManager.NetworkCallback networkCallback = (ConnectivityManager.NetworkCallback) ((HashMap) this.f807m).remove(g7);
        if (networkCallback != null) {
            ((io.sentry.android.core.B) this.f809p).getClass();
            Context context = (Context) this.f808n;
            K k10 = (K) this.o;
            ConnectivityManager connectivityManagerV = V(context, k10);
            if (connectivityManagerV == null) {
                return;
            }
            try {
                connectivityManagerV.unregisterNetworkCallback(networkCallback);
            } catch (Throwable th) {
                k10.s(EnumC1495p1.WARNING, "unregisterNetworkCallback failed", th);
            }
        }
    }

    public void p0(n3.z zVar, int i7) {
        Class<?> cls = zVar.getClass();
        N0.q qVar = (N0.q) this.f808n;
        S6.h hVar = (S6.h) ((S6.e) qVar.l).f8072a.get(cls);
        if (hVar != null) {
            Object objA = hVar.a(qVar, (S6.c) this.o);
            S6.k kVar = (S6.k) this.f809p;
            StringBuilder sb = kVar.l;
            int length = sb.length();
            int length2 = sb.length();
            if (length <= i7 || i7 < 0 || length > length2) {
                return;
            }
            S6.k.c(kVar, objA, i7, length);
        }
    }

    @Override // h8.m
    public void q(C2061f c2061f, Object obj) {
        ((D) this.f808n).q(c2061f, obj);
    }

    public boolean q0(X0 x02, C1523x c1523x) {
        if (AbstractC1387d.s(c1523x)) {
            return true;
        }
        ((F1) this.f808n).getLogger().k(EnumC1495p1.DEBUG, "Event was cached so not applying scope: %s", x02.l);
        return false;
    }

    public void r0(n3.z zVar) {
        S6.f fVar = (S6.f) ((Map) this.f807m).get(zVar.getClass());
        if (fVar != null) {
            fVar.a(this, zVar);
        } else {
            s0(zVar);
        }
    }

    @Override // Y2.l
    public int s() {
        return 2;
    }

    public void s0(n3.z zVar) {
        n3.z zVar2 = (n3.z) zVar.c;
        while (zVar2 != null) {
            n3.z zVar3 = (n3.z) zVar2.f19923f;
            zVar2.a(this);
            zVar2 = zVar3;
        }
    }

    @Override // x.F0
    public AbstractC2652s t(AbstractC2652s abstractC2652s, AbstractC2652s abstractC2652s2, AbstractC2652s abstractC2652s3) {
        if (((AbstractC2652s) this.f807m) == null) {
            this.f807m = abstractC2652s3.c();
        }
        AbstractC2652s abstractC2652s4 = (AbstractC2652s) this.f807m;
        if (abstractC2652s4 == null) {
            A7.m.j("endVelocityVector");
            throw null;
        }
        int iB = abstractC2652s4.b();
        for (int i7 = 0; i7 < iB; i7++) {
            AbstractC2652s abstractC2652s5 = (AbstractC2652s) this.f807m;
            if (abstractC2652s5 == null) {
                A7.m.j("endVelocityVector");
                throw null;
            }
            abstractC2652s5.e(((InterfaceC2653t) this.f808n).get(i7).f(abstractC2652s.a(i7), abstractC2652s2.a(i7), abstractC2652s3.a(i7)), i7);
        }
        AbstractC2652s abstractC2652s6 = (AbstractC2652s) this.f807m;
        if (abstractC2652s6 != null) {
            return abstractC2652s6;
        }
        A7.m.j("endVelocityVector");
        throw null;
    }

    public D t0(int i7, C2057b c2057b, V7.a aVar) {
        h8.p pVar = new h8.p(((h8.p) this.f808n).f15622a + '@' + i7);
        io.sentry.internal.debugmeta.c cVar = (io.sentry.internal.debugmeta.c) this.f807m;
        List arrayList = (List) ((HashMap) cVar.f16570n).get(pVar);
        if (arrayList == null) {
            arrayList = new ArrayList();
            ((HashMap) cVar.f16570n).put(pVar, arrayList);
        }
        return ((H0.b) cVar.f16569m).H(c2057b, aVar, arrayList);
    }

    public synchronized void u(g4.q qVar, g4.r rVar) {
        C1298b c1298b = (C1298b) ((HashMap) this.o).put(qVar, new C1298b(qVar, rVar, (ReferenceQueue) this.f809p));
        if (c1298b != null) {
            c1298b.c = null;
            c1298b.clear();
        }
    }

    public void v(Object obj, C1679y0 c1679y0, z1 z1Var, S s6) {
        synchronized (this.f808n) {
            try {
                C1679y0 c1679y0X = X(obj);
                if (c1679y0X == null) {
                    ((C2465e) this.o).put(obj, c1679y0);
                    ((C2465e) this.f809p).put(c1679y0, new C1639f(obj, new X(1), z1Var, s6));
                } else {
                    C1639f c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0X);
                    AbstractC1346c.k(c1639f);
                    c1639f.d = z1Var;
                    c1639f.f17944e = s6;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // B8.InterfaceC0093e
    public C0092d w(C2057b c2057b) {
        A7.m.f(c2057b, "classId");
        C1565j c1565j = (C1565j) ((LinkedHashMap) this.f807m).get(c2057b);
        if (c1565j == null) {
            return null;
        }
        ((A.F) this.f809p).b(c2057b);
        return new C0092d((C1789g) this.f808n, c1565j, (C1708a) this.o, N.c);
    }

    public void y(C1679y0 c1679y0, int i7, InterfaceC1636e interfaceC1636e) {
        synchronized (this.f808n) {
            try {
                C1639f c1639f = (C1639f) ((C2465e) this.f809p).get(c1679y0);
                if (c1639f != null) {
                    S s6 = c1639f.f17946g;
                    s6.getClass();
                    C0050y0 c0050y0 = new C0050y0(5);
                    c0050y0.j(s6.f14000a);
                    c0050y0.f(i7);
                    c1639f.f17946g = new S(c0050y0.l());
                    c1639f.c.add(interfaceC1636e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void z(X0 x02, N0 n02) {
        if (n02 != null) {
            if (x02.o == null) {
                x02.o = n02.d;
            }
            if (x02.f15984t == null) {
                x02.f15984t = n02.f15894b;
            }
            AbstractMap abstractMap = x02.f15980p;
            ConcurrentHashMap concurrentHashMap = n02.f15897g;
            if (abstractMap == null) {
                x02.f15980p = new HashMap(new HashMap(h6.r.p(concurrentHashMap)));
            } else {
                for (Map.Entry entry : h6.r.p(concurrentHashMap).entrySet()) {
                    if (!x02.f15980p.containsKey(entry.getKey())) {
                        x02.f15980p.put((String) entry.getKey(), (String) entry.getValue());
                    }
                }
            }
            List list = x02.f15988x;
            Y1 y12 = n02.f15896f;
            if (list == null) {
                x02.f15988x = new ArrayList(new ArrayList(y12));
            } else if (!y12.isEmpty()) {
                list.addAll(y12);
                Collections.sort(list, (Z0) this.f809p);
            }
            AbstractMap abstractMap2 = x02.f15990z;
            ConcurrentHashMap concurrentHashMap2 = n02.f15898h;
            if (abstractMap2 == null) {
                x02.f15990z = new HashMap(new HashMap(concurrentHashMap2));
            } else {
                for (Map.Entry entry2 : concurrentHashMap2.entrySet()) {
                    if (!x02.f15990z.containsKey(entry2.getKey())) {
                        x02.f15990z.put((String) entry2.getKey(), entry2.getValue());
                    }
                }
            }
            Iterator it = new C1498c(n02.o).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry3 = (Map.Entry) it.next();
                Object key = entry3.getKey();
                C1498c c1498c = x02.f15978m;
                if (!c1498c.containsKey(key)) {
                    c1498c.put((String) entry3.getKey(), entry3.getValue());
                }
            }
        }
    }

    public /* synthetic */ t(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        this.l = i7;
        this.f808n = obj;
        this.o = obj2;
        this.f809p = obj3;
        this.f807m = obj4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public t(Boolean bool, Double d) {
        this(bool, d, Boolean.FALSE, (Double) null);
        this.l = 19;
    }

    public t(E8.o oVar, InterfaceC0671y interfaceC0671y) {
        this.l = 5;
        A7.m.f(oVar, "storageManager");
        A7.m.f(interfaceC0671y, "module");
        this.f808n = oVar;
        this.o = interfaceC0671y;
        E8.l lVar = (E8.l) oVar;
        this.f809p = lVar.b(new Q7.C(this, 1));
        this.f807m = lVar.b(new Q7.C(this, 0));
    }

    public t(j8.E e10, C1789g c1789g, C1708a c1708a, A.F f10) {
        this.l = 0;
        this.f808n = c1789g;
        this.o = c1708a;
        this.f809p = f10;
        List list = e10.f17095r;
        A7.m.e(list, "proto.class_List");
        int iQ = n7.z.q(AbstractC2000o.J(list, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ < 16 ? 16 : iQ);
        for (Object obj : list) {
            linkedHashMap.put(AbstractC0904a.D((C1789g) this.f808n, ((C1565j) obj).f17395p), obj);
        }
        this.f807m = linkedHashMap;
    }

    public t(Boolean bool, Double d, Boolean bool2, Double d10) {
        this.l = 19;
        this.f808n = bool;
        this.o = d;
        this.f809p = Boolean.valueOf(bool.booleanValue() && bool2.booleanValue());
        this.f807m = d10;
    }

    public t(InterfaceC0725w interfaceC0725w, A.F f10, N1.N n10) {
        this.l = 4;
        this.f808n = interfaceC0725w;
        this.o = n10;
        this.f809p = AbstractC2673a.a(Integer.MAX_VALUE, 6, null);
        this.f807m = new C0420a(0);
        W w10 = (W) interfaceC0725w.getCoroutineContext().F(C0722t.f8137m);
        if (w10 != null) {
            w10.z(new C0004b(f10, 22, this));
        }
    }

    public t(t tVar) {
        this.l = 29;
        ArrayList arrayList = (ArrayList) tVar.f808n;
        LinkedHashSet linkedHashSet = (LinkedHashSet) tVar.f807m;
        LinkedHashSet linkedHashSet2 = t9.f.f22524p;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(arrayList);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            arrayList2.add(t9.f.f22525q.get((Class) it.next()));
        }
        this.f808n = arrayList2;
        this.f809p = new l9.n(16);
        this.f807m = (ArrayList) tVar.f809p;
        ArrayList arrayList3 = (ArrayList) tVar.o;
        this.o = arrayList3;
        new t9.j(new m2.b(arrayList3, Collections.emptyMap()));
    }

    public t(Context context, K k10, io.sentry.android.core.B b10) {
        this.l = 20;
        Context applicationContext = context.getApplicationContext();
        this.f808n = applicationContext != null ? applicationContext : context;
        this.o = k10;
        this.f809p = b10;
        this.f807m = new HashMap();
    }

    public t(F1 f12) {
        Object runnableC1509s0;
        this.l = 18;
        this.f809p = new Z0();
        this.f808n = f12;
        io.sentry.X transportFactory = f12.getTransportFactory();
        if (transportFactory instanceof B0) {
            transportFactory = new C1524x0(5);
            f12.setTransportFactory(transportFactory);
        }
        C1503q c1503qRetrieveParsedDsn = f12.retrieveParsedDsn();
        URI uri = c1503qRetrieveParsedDsn.c;
        String string = uri.resolve(uri.getPath() + "/envelope/").toString();
        StringBuilder sb = new StringBuilder("Sentry sentry_version=7,sentry_client=");
        sb.append(f12.getSentryClientName());
        sb.append(",sentry_key=");
        sb.append(c1503qRetrieveParsedDsn.f16818b);
        String str = c1503qRetrieveParsedDsn.f16817a;
        sb.append((str == null || str.length() <= 0) ? "" : ",sentry_secret=".concat(str));
        String string2 = sb.toString();
        String sentryClientName = f12.getSentryClientName();
        HashMap map = new HashMap();
        map.put("User-Agent", sentryClientName);
        map.put("X-Sentry-Auth", string2);
        this.o = transportFactory.o(f12, new io.sentry.internal.debugmeta.c(string, map));
        if (f12.isEnableMetrics()) {
            runnableC1509s0 = new RunnableC1509s0(f12, this);
        } else {
            runnableC1509s0 = io.sentry.metrics.d.l;
        }
        this.f807m = runnableC1509s0;
    }

    public t(N0.q qVar, S6.c cVar, S6.k kVar, Map map, Y4.e eVar) {
        this.l = 6;
        this.f808n = qVar;
        this.o = cVar;
        this.f809p = kVar;
        this.f807m = map;
    }

    public t(int i7) {
        this.l = i7;
        switch (i7) {
            case 11:
                this.o = new ArrayDeque();
                this.f809p = new ArrayDeque();
                this.f807m = new ArrayDeque();
                break;
            case 12:
                break;
            case 13:
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(new C1.m(3));
                this.o = new HashMap();
                this.f809p = new ReferenceQueue();
                this.f808n = executorServiceNewSingleThreadExecutor;
                executorServiceNewSingleThreadExecutor.execute(new RunnableC0079c(this, 19));
                break;
            case 24:
                this.f808n = new E1.c(10);
                this.o = new C2458F(0);
                this.f809p = new ArrayList();
                this.f807m = new HashSet();
                break;
            case 28:
                this.f808n = new ArrayList();
                this.o = new ArrayList();
                this.f809p = new ArrayList();
                this.f807m = t9.f.f22524p;
                break;
            default:
                this.f808n = new h2.t();
                this.o = new h2.t();
                this.f809p = new C0994a();
                break;
        }
    }

    public t(I0 i02) {
        this.l = 23;
        this.o = new C2465e(0);
        this.f809p = new C2465e(0);
        this.f808n = new Object();
        this.f807m = new WeakReference(i02);
    }

    public t(Typeface typeface, U1.b bVar) {
        int i7;
        int i10;
        this.l = 7;
        this.f807m = typeface;
        this.f808n = bVar;
        this.f809p = new T1.t(1024);
        int iB = bVar.b(6);
        if (iB != 0) {
            int i11 = iB + bVar.l;
            i7 = ((ByteBuffer) bVar.o).getInt(((ByteBuffer) bVar.o).getInt(i11) + i11);
        } else {
            i7 = 0;
        }
        this.o = new char[i7 * 2];
        int iB2 = bVar.b(6);
        if (iB2 != 0) {
            int i12 = iB2 + bVar.l;
            i10 = ((ByteBuffer) bVar.o).getInt(((ByteBuffer) bVar.o).getInt(i12) + i12);
        } else {
            i10 = 0;
        }
        for (int i13 = 0; i13 < i10; i13++) {
            T1.w wVar = new T1.w(this, i13);
            U1.a aVarC = wVar.c();
            int iB3 = aVarC.b(4);
            Character.toChars(iB3 != 0 ? ((ByteBuffer) aVarC.o).getInt(iB3 + aVarC.l) : 0, (char[]) this.o, i13 * 2);
            l9.l.C("invalid metadata codepoint length", wVar.b() > 0);
            ((T1.t) this.f809p).a(wVar, 0, wVar.b() - 1);
        }
    }

    public t(io.sentry.internal.debugmeta.c cVar, h8.p pVar) {
        this.l = 14;
        this.f807m = cVar;
        this.l = 14;
        this.f809p = cVar;
        this.f808n = pVar;
        this.o = new ArrayList();
    }

    public t(H0.b bVar, C2061f c2061f, D d) {
        this.l = 16;
        this.o = bVar;
        this.f809p = c2061f;
        this.f807m = d;
        this.f808n = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public t(U8.l lVar, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, C0711h c0711h) {
        this.l = 9;
        this.f808n = lVar;
        this.o = (A7.j) interfaceC2786o;
        this.f809p = (A7.j) interfaceC2786o2;
        this.f807m = c0711h;
    }

    public t(D d, t tVar, ArrayList arrayList) {
        this.l = 15;
        this.o = d;
        this.f809p = tVar;
        this.f807m = arrayList;
        this.f808n = d;
    }

    public t(d5.d0 d0Var, C1489n1 c1489n1, io.sentry.internal.debugmeta.c cVar, M.t tVar) {
        Object objW;
        this.l = 17;
        if (d0Var != null) {
            objW = I.w(d0Var);
        } else {
            C1078G c1078g = I.f13384m;
            objW = d5.d0.f13415p;
        }
        this.f808n = objW;
        this.o = c1489n1;
        this.f809p = cVar;
        this.f807m = tVar;
    }

    public t(D8.k kVar) {
        this.l = 1;
        this.f809p = kVar;
        List list = kVar.f1241p.f17377E;
        A7.m.e(list, "classProto.enumEntryList");
        int iQ = n7.z.q(AbstractC2000o.J(list, 10));
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ < 16 ? 16 : iQ);
        for (Object obj : list) {
            linkedHashMap.put(AbstractC0904a.F((InterfaceC1788f) kVar.f1248w.f780b, ((C1574t) obj).o), obj);
        }
        this.f807m = linkedHashMap;
        D8.k kVar2 = (D8.k) this.f809p;
        this.f808n = ((E8.l) ((i) kVar2.f1248w.f779a).f762a).c(new C0004b(this, 5, kVar2));
        E8.o oVar = ((i) ((D8.k) this.f809p).f1248w.f779a).f762a;
        A.I0 i02 = new A.I0(this, 5);
        E8.l lVar = (E8.l) oVar;
        lVar.getClass();
        this.o = new E8.i(lVar, i02);
    }

    public t(N1.Q q4, List list) {
        this.l = 3;
        this.f807m = q4;
        this.f808n = b9.d.a();
        C0718o c0718o = new C0718o(true);
        c0718o.Q(null);
        this.o = c0718o;
        this.f809p = AbstractC1998m.z0(list);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public t(InterfaceC2616E interfaceC2616E) {
        this(new C2607b(interfaceC2616E, 3), 26);
        this.l = 26;
    }

    public t(n0 n0Var, boolean[] zArr) {
        this.l = 25;
        this.f808n = n0Var;
        this.o = zArr;
        int i7 = n0Var.f22881a;
        this.f809p = new boolean[i7];
        this.f807m = new boolean[i7];
    }
}
