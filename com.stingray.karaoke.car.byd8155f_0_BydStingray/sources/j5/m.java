package j5;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import l5.C1778f;
import l5.C1780h;
import m5.C1825b;
import m5.C1827d;
import m5.C1830g;
import m5.C1832i;
import m5.C1834k;
import m5.C1835l;
import m5.C1836m;
import m5.C1841s;
import m5.C1845w;
import m5.O;
import m5.S;
import m5.T;
import m5.c0;
import p5.AbstractC2109e;
import q5.C2185a;
import r5.C2297a;
import r5.C2298b;
import r5.C2299c;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C1551i f17021h = C1551i.d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final EnumC1550h f17022i = EnumC1550h.l;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final x f17023j = x.l;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final x f17024k = x.f17030m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal f17025a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f17026b;
    public final C1778f c;
    public final C1832i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f17027e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f17028f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1551i f17029g;

    public m() {
        C1780h c1780h = C1780h.f18644n;
        Map mapEmptyMap = Collections.emptyMap();
        Collections.emptyList();
        Collections.emptyList();
        List listEmptyList = Collections.emptyList();
        List listEmptyList2 = Collections.emptyList();
        this.f17025a = new ThreadLocal();
        this.f17026b = new ConcurrentHashMap();
        C1778f c1778f = new C1778f(mapEmptyMap, 0, listEmptyList2);
        this.c = c1778f;
        this.f17028f = true;
        this.f17029g = f17021h;
        ArrayList arrayList = new ArrayList();
        arrayList.add(c0.f19197A);
        t tVar = x.l;
        x xVar = f17023j;
        arrayList.add(xVar == tVar ? C1836m.c : new C1834k(xVar, 1));
        arrayList.add(c1780h);
        arrayList.addAll(listEmptyList);
        arrayList.add(c0.f19209p);
        arrayList.add(c0.f19202g);
        arrayList.add(c0.d);
        arrayList.add(c0.f19200e);
        arrayList.add(c0.f19201f);
        C1845w c1845w = c0.f19206k;
        arrayList.add(new T(Long.TYPE, Long.class, c1845w));
        arrayList.add(new T(Double.TYPE, Double.class, new C1552j(0)));
        arrayList.add(new T(Float.TYPE, Float.class, new C1552j(1)));
        u uVar = x.f17030m;
        x xVar2 = f17024k;
        arrayList.add(xVar2 == uVar ? C1835l.f19229b : new C1834k(new C1835l(xVar2), 0));
        arrayList.add(c0.f19203h);
        arrayList.add(c0.f19204i);
        arrayList.add(new S(AtomicLong.class, new C1553k(new C1553k(c1845w, 0), 2), 0));
        arrayList.add(new S(AtomicLongArray.class, new C1553k(new C1553k(c1845w, 1), 2), 0));
        arrayList.add(c0.f19205j);
        arrayList.add(c0.l);
        arrayList.add(c0.f19210q);
        arrayList.add(c0.f19211r);
        arrayList.add(new S(BigDecimal.class, c0.f19207m, 0));
        arrayList.add(new S(BigInteger.class, c0.f19208n, 0));
        arrayList.add(new S(l5.j.class, c0.o, 0));
        arrayList.add(c0.f19212s);
        arrayList.add(c0.f19213t);
        arrayList.add(c0.f19215v);
        arrayList.add(c0.f19216w);
        arrayList.add(c0.f19218y);
        arrayList.add(c0.f19214u);
        arrayList.add(c0.f19199b);
        arrayList.add(C1830g.c);
        arrayList.add(c0.f19217x);
        if (AbstractC2109e.f20906a) {
            arrayList.add(AbstractC2109e.c);
            arrayList.add(AbstractC2109e.f20907b);
            arrayList.add(AbstractC2109e.d);
        }
        arrayList.add(C1825b.c);
        arrayList.add(c0.f19198a);
        arrayList.add(new C1827d(c1778f, 0));
        arrayList.add(new C1827d(c1778f, 1));
        C1832i c1832i = new C1832i(c1778f);
        this.d = c1832i;
        arrayList.add(c1832i);
        arrayList.add(c0.B);
        arrayList.add(new C1841s(c1778f, f17022i, c1780h, c1832i, listEmptyList2));
        this.f17027e = Collections.unmodifiableList(arrayList);
    }

    public static void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    /* JADX WARN: Finally extract failed */
    public final Object b(String str, Type type) {
        C2185a c2185a = new C2185a(type);
        Object objA = null;
        if (str != null) {
            C2297a c2297a = new C2297a(new StringReader(str));
            c2297a.f21857z = 2;
            boolean z6 = true;
            c2297a.f21857z = 1;
            try {
                try {
                    try {
                        c2297a.Q();
                        z6 = false;
                        objA = c(c2185a).a(c2297a);
                    } catch (EOFException e10) {
                        if (!z6) {
                            throw new p(e10);
                        }
                    } catch (IllegalStateException e11) {
                        throw new p(e11);
                    }
                    c2297a.f21857z = 2;
                    if (objA != null) {
                        try {
                            if (c2297a.Q() != 10) {
                                throw new p("JSON document was not fully consumed.");
                            }
                        } catch (C2299c e12) {
                            throw new p(e12);
                        } catch (IOException e13) {
                            throw new p(e13);
                        }
                    }
                } catch (IOException e14) {
                    throw new p(e14);
                } catch (AssertionError e15) {
                    throw new AssertionError("AssertionError (GSON 2.11.0): " + e15.getMessage(), e15);
                }
            } catch (Throwable th) {
                c2297a.f21857z = 2;
                throw th;
            }
        }
        return objA;
    }

    public final y c(C2185a c2185a) {
        boolean z6;
        ConcurrentHashMap concurrentHashMap = this.f17026b;
        y yVar = (y) concurrentHashMap.get(c2185a);
        if (yVar != null) {
            return yVar;
        }
        ThreadLocal threadLocal = this.f17025a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            z6 = true;
        } else {
            y yVar2 = (y) map.get(c2185a);
            if (yVar2 != null) {
                return yVar2;
            }
            z6 = false;
        }
        try {
            l lVar = new l();
            map.put(c2185a, lVar);
            Iterator it = this.f17027e.iterator();
            y yVarA = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                yVarA = ((z) it.next()).a(this, c2185a);
                if (yVarA != null) {
                    if (lVar.f17020a != null) {
                        throw new AssertionError("Delegate is already set");
                    }
                    lVar.f17020a = yVarA;
                    map.put(c2185a, yVarA);
                }
            }
            if (z6) {
                threadLocal.remove();
            }
            if (yVarA != null) {
                if (z6) {
                    concurrentHashMap.putAll(map);
                }
                return yVarA;
            }
            throw new IllegalArgumentException("GSON (2.11.0) cannot handle " + c2185a);
        } catch (Throwable th) {
            if (z6) {
                threadLocal.remove();
            }
            throw th;
        }
    }

    public final C2298b d(Writer writer) {
        C2298b c2298b = new C2298b(writer);
        c2298b.z(this.f17029g);
        c2298b.f21867t = this.f17028f;
        c2298b.C(2);
        c2298b.f21869v = false;
        return c2298b;
    }

    public final String e(Object obj) {
        if (obj == null) {
            StringWriter stringWriter = new StringWriter();
            try {
                g(d(stringWriter));
                return stringWriter.toString();
            } catch (IOException e10) {
                throw new p(e10);
            }
        }
        Class cls = obj.getClass();
        StringWriter stringWriter2 = new StringWriter();
        try {
            f(obj, cls, d(stringWriter2));
            return stringWriter2.toString();
        } catch (IOException e11) {
            throw new p(e11);
        }
    }

    public final void f(Object obj, Class cls, C2298b c2298b) {
        y yVarC = c(new C2185a(cls));
        int i7 = c2298b.f21866s;
        if (i7 == 2) {
            c2298b.f21866s = 1;
        }
        boolean z6 = c2298b.f21867t;
        boolean z10 = c2298b.f21869v;
        c2298b.f21867t = this.f17028f;
        c2298b.f21869v = false;
        try {
            try {
                yVarC.b(c2298b, obj);
            } catch (IOException e10) {
                throw new p(e10);
            } catch (AssertionError e11) {
                throw new AssertionError("AssertionError (GSON 2.11.0): " + e11.getMessage(), e11);
            }
        } finally {
            c2298b.C(i7);
            c2298b.f21867t = z6;
            c2298b.f21869v = z10;
        }
    }

    public final void g(C2298b c2298b) {
        q qVar = q.l;
        int i7 = c2298b.f21866s;
        boolean z6 = c2298b.f21867t;
        boolean z10 = c2298b.f21869v;
        c2298b.f21867t = this.f17028f;
        c2298b.f21869v = false;
        if (i7 == 2) {
            c2298b.f21866s = 1;
        }
        try {
            try {
                c0.f19219z.getClass();
                O.d(c2298b, qVar);
                c2298b.C(i7);
                c2298b.f21867t = z6;
                c2298b.f21869v = z10;
            } catch (IOException e10) {
                throw new p(e10);
            } catch (AssertionError e11) {
                throw new AssertionError("AssertionError (GSON 2.11.0): " + e11.getMessage(), e11);
            }
        } catch (Throwable th) {
            c2298b.C(i7);
            c2298b.f21867t = z6;
            c2298b.f21869v = z10;
            throw th;
        }
    }

    public final String toString() {
        return "{serializeNulls:false,factories:" + this.f17027e + ",instanceCreators:" + this.c + "}";
    }
}
