package io.sentry;

import h8.AbstractC1387d;
import io.sentry.protocol.C1499d;
import io.sentry.protocol.DebugImage;
import java.io.Closeable;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: io.sentry.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1491o0 implements InterfaceC1511t, Closeable {
    public final F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final M.t f16626m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e7.f f16627n;
    public volatile B o = null;

    public C1491o0(F1 f12) {
        h6.e.q(f12, "The SentryOptions is required.");
        this.l = f12;
        L1 l12 = new L1(f12, 0);
        this.f16627n = new e7.f(l12, 12);
        M.t tVar = new M.t();
        tVar.l = l12;
        h6.e.q(f12, "The SentryOptions is required");
        tVar.f4151m = f12;
        this.f16626m = tVar;
    }

    @Override // io.sentry.InterfaceC1511t
    public final H1 b(H1 h12, C1523x c1523x) {
        if (h12.f15983s == null) {
            h12.f15983s = "java";
        }
        if (m(h12, c1523x)) {
            j(h12);
        }
        return h12;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.o != null) {
            this.o.f15822f.shutdown();
        }
    }

    @Override // io.sentry.InterfaceC1511t
    public final io.sentry.protocol.A g(io.sentry.protocol.A a10, C1523x c1523x) {
        if (a10.f15983s == null) {
            a10.f15983s = "java";
        }
        k(a10);
        if (m(a10, c1523x)) {
            j(a10);
        }
        return a10;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // io.sentry.InterfaceC1511t
    public final C1480k1 i(C1480k1 c1480k1, C1523x c1523x) {
        ArrayList arrayList;
        Thread threadCurrentThread;
        io.sentry.protocol.j jVar;
        boolean z6;
        if (c1480k1.f15983s == null) {
            c1480k1.f15983s = "java";
        }
        Throwable cause = c1480k1.f15985u;
        if (cause != null) {
            e7.f fVar = this.f16627n;
            fVar.getClass();
            ArrayDeque arrayDeque = new ArrayDeque();
            HashSet hashSet = new HashSet();
            while (cause != null && hashSet.add(cause)) {
                if (cause instanceof io.sentry.exception.a) {
                    io.sentry.exception.a aVar = (io.sentry.exception.a) cause;
                    io.sentry.protocol.j jVar2 = aVar.l;
                    Throwable th = aVar.f16529m;
                    threadCurrentThread = aVar.f16530n;
                    z6 = aVar.o;
                    cause = th;
                    jVar = jVar2;
                } else {
                    threadCurrentThread = Thread.currentThread();
                    jVar = null;
                    z6 = false;
                }
                arrayDeque.addFirst(e7.f.k(cause, jVar, Long.valueOf(threadCurrentThread.getId()), ((L1) fVar.f14480m).a(cause.getStackTrace(), jVar != null && Boolean.FALSE.equals(jVar.o)), z6));
                cause = cause.getCause();
            }
            c1480k1.f16600E = new A7.D((List) new ArrayList(arrayDeque));
        }
        k(c1480k1);
        F1 f12 = this.l;
        Map mapA = f12.getModulesLoader().a();
        if (mapA != null) {
            AbstractMap abstractMap = c1480k1.f16605J;
            if (abstractMap == null) {
                c1480k1.f16605J = new HashMap(mapA);
            } else {
                abstractMap.putAll(mapA);
            }
        }
        if (m(c1480k1, c1523x)) {
            j(c1480k1);
            A7.D d = c1480k1.f16599D;
            if ((d != null ? d.l : null) == null) {
                A7.D d10 = c1480k1.f16600E;
                ArrayList<io.sentry.protocol.s> arrayList2 = d10 == null ? null : d10.l;
                if (arrayList2 == null || arrayList2.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = null;
                    for (io.sentry.protocol.s sVar : arrayList2) {
                        if (sVar.f16772q != null && sVar.o != null) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(sVar.o);
                        }
                    }
                }
                boolean zIsAttachThreads = f12.isAttachThreads();
                M.t tVar = this.f16626m;
                if (zIsAttachThreads || io.sentry.hints.a.class.isInstance(AbstractC1387d.g(c1523x))) {
                    Object objG = AbstractC1387d.g(c1523x);
                    boolean zC = objG instanceof io.sentry.hints.a ? ((io.sentry.hints.a) objG).c() : false;
                    tVar.getClass();
                    c1480k1.f16599D = new A7.D((List) tVar.E(Thread.getAllStackTraces(), arrayList, zC));
                } else if (f12.isAttachStacktrace() && ((arrayList2 == null || arrayList2.isEmpty()) && !io.sentry.hints.d.class.isInstance(AbstractC1387d.g(c1523x)))) {
                    tVar.getClass();
                    HashMap map = new HashMap();
                    Thread threadCurrentThread2 = Thread.currentThread();
                    map.put(threadCurrentThread2, threadCurrentThread2.getStackTrace());
                    c1480k1.f16599D = new A7.D((List) tVar.E(map, null, false));
                }
            }
        }
        return c1480k1;
    }

    public final void j(X0 x02) {
        if (x02.f15981q == null) {
            x02.f15981q = this.l.getRelease();
        }
        if (x02.f15982r == null) {
            x02.f15982r = this.l.getEnvironment();
        }
        if (x02.f15986v == null) {
            x02.f15986v = this.l.getServerName();
        }
        if (this.l.isAttachServerName() && x02.f15986v == null) {
            if (this.o == null) {
                synchronized (this) {
                    try {
                        if (this.o == null) {
                            if (B.f15818i == null) {
                                B.f15818i = new B();
                            }
                            this.o = B.f15818i;
                        }
                    } finally {
                    }
                }
            }
            if (this.o != null) {
                B b10 = this.o;
                if (b10.c < System.currentTimeMillis() && b10.d.compareAndSet(false, true)) {
                    b10.a();
                }
                x02.f15986v = b10.f15820b;
            }
        }
        if (x02.f15987w == null) {
            x02.f15987w = this.l.getDist();
        }
        if (x02.f15979n == null) {
            x02.f15979n = this.l.getSdkVersion();
        }
        AbstractMap abstractMap = x02.f15980p;
        F1 f12 = this.l;
        if (abstractMap == null) {
            x02.f15980p = new HashMap(new HashMap(f12.getTags()));
        } else {
            for (Map.Entry<String, String> entry : f12.getTags().entrySet()) {
                if (!x02.f15980p.containsKey(entry.getKey())) {
                    x02.a(entry.getKey(), entry.getValue());
                }
            }
        }
        io.sentry.protocol.E e10 = x02.f15984t;
        if (e10 == null) {
            e10 = new io.sentry.protocol.E();
            x02.f15984t = e10;
        }
        if (e10.f16658p == null) {
            e10.f16658p = "{{auto}}";
        }
    }

    public final void k(X0 x02) {
        ArrayList arrayList = new ArrayList();
        F1 f12 = this.l;
        if (f12.getProguardUuid() != null) {
            DebugImage debugImage = new DebugImage();
            debugImage.setType(DebugImage.PROGUARD);
            debugImage.setUuid(f12.getProguardUuid());
            arrayList.add(debugImage);
        }
        for (String str : f12.getBundleIds()) {
            DebugImage debugImage2 = new DebugImage();
            debugImage2.setType(DebugImage.JVM);
            debugImage2.setDebugId(str);
            arrayList.add(debugImage2);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        C1499d c1499d = x02.f15989y;
        if (c1499d == null) {
            c1499d = new C1499d();
        }
        List list = c1499d.f16687m;
        if (list == null) {
            c1499d.f16687m = new ArrayList(arrayList);
        } else {
            list.addAll(arrayList);
        }
        x02.f15989y = c1499d;
    }

    public final boolean m(X0 x02, C1523x c1523x) {
        if (AbstractC1387d.s(c1523x)) {
            return true;
        }
        this.l.getLogger().k(EnumC1495p1.DEBUG, "Event was cached so not applying data relevant to the current app execution/version: %s", x02.l);
        return false;
    }
}
