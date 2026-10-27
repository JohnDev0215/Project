package io.sentry;

import B2.InterfaceC0070j;
import H7.InterfaceC0318c;
import T.C0735d;
import T.C0738e0;
import android.graphics.Bitmap;
import android.os.HandlerThread;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.widget.EditText;
import c9.InterfaceC1048i;
import g8.C1322l;
import g8.C1324n;
import g8.C1326p;
import h2.AbstractC1343A;
import h4.C1361c;
import h4.InterfaceC1365g;
import i4.C1409b;
import i4.C1410c;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import k1.AbstractC1598c;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import r9.AbstractC2312b;
import r9.C2319i;
import r9.C2322l;
import w8.C2607b;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: io.sentry.n1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1489n1 implements I3.g, P0.c, T1.o, InterfaceC1048i, InterfaceC0070j, C0 {
    public static volatile C1489n1 o;
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f16624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f16625n;

    public /* synthetic */ C1489n1(int i7, boolean z6) {
        this.l = i7;
    }

    public static F8.H k(List list) {
        return list.isEmpty() ? F8.H.f2438n : new F8.H(list);
    }

    public static C1489n1 s() {
        if (o == null) {
            synchronized (C1489n1.class) {
                try {
                    if (o == null) {
                        o = new C1489n1(0);
                    }
                } finally {
                }
            }
        }
        return o;
    }

    public static C1489n1 z(String... strArr) {
        try {
            C2322l[] c2322lArr = new C2322l[strArr.length];
            C2319i c2319i = new C2319i();
            for (int i7 = 0; i7 < strArr.length; i7++) {
                E5.w.F(c2319i, strArr[i7]);
                c2319i.z();
                c2322lArr[i7] = c2319i.F(c2319i.f21932m);
            }
            return new C1489n1((String[]) strArr.clone(), 5, AbstractC2312b.g(c2322lArr));
        } catch (IOException e10) {
            throw new AssertionError(e10);
        }
    }

    @Override // P0.c
    public int A(int i7) {
        CharSequence charSequence = (CharSequence) this.f16624m;
        int textRunCursor = ((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, i7, 2);
        if (textRunCursor == -1) {
            return -1;
        }
        if (((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, textRunCursor, 2) == -1) {
            return -1;
        }
        return textRunCursor;
    }

    public void B(C1.h hVar) {
        int i7 = hVar.f941b;
        C1.n nVar = (C1.n) this.f16625n;
        C2607b c2607b = (C2607b) this.f16624m;
        if (i7 != 0) {
            nVar.execute(new C1.a(c2607b, i7));
        } else {
            nVar.execute(new h5.t(c2607b, 4, hVar.f940a));
        }
    }

    public X1 C() {
        return (X1) ((LinkedBlockingDeque) this.f16624m).peek();
    }

    public void D(InterfaceC1365g interfaceC1365g, Object obj) {
        HashMap map = (HashMap) this.f16625n;
        C1361c c1361c = (C1361c) map.get(interfaceC1365g);
        if (c1361c == null) {
            c1361c = new C1361c(interfaceC1365g);
            c1361c.d = c1361c;
            C1361c c1361c2 = (C1361c) this.f16624m;
            c1361c.d = c1361c2.d;
            c1361c.c = c1361c2;
            c1361c2.d = c1361c;
            c1361c.d.c = c1361c;
            map.put(interfaceC1365g, c1361c);
        } else {
            interfaceC1365g.a();
        }
        if (c1361c.f15512b == null) {
            c1361c.f15512b = new ArrayList();
        }
        c1361c.f15512b.add(obj);
    }

    public void E(String str) {
        C1409b c1409b;
        synchronized (this) {
            try {
                Object obj = ((HashMap) this.f16624m).get(str);
                A4.h.c(obj, "Argument must not be null");
                c1409b = (C1409b) obj;
                int i7 = c1409b.f15748b;
                if (i7 < 1) {
                    throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + c1409b.f15748b);
                }
                int i10 = i7 - 1;
                c1409b.f15748b = i10;
                if (i10 == 0) {
                    C1409b c1409b2 = (C1409b) ((HashMap) this.f16624m).remove(str);
                    if (!c1409b2.equals(c1409b)) {
                        throw new IllegalStateException("Removed the wrong lock, expected to remove: " + c1409b + ", but actually removed: " + c1409b2 + ", safeKey: " + str);
                    }
                    C1410c c1410c = (C1410c) this.f16625n;
                    synchronized (c1410c.f15749a) {
                        try {
                            if (c1410c.f15749a.size() < 10) {
                                c1410c.f15749a.offer(c1409b2);
                            }
                        } finally {
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        c1409b.f15747a.unlock();
    }

    public Object F() {
        C1361c c1361c = (C1361c) this.f16624m;
        C1361c c1361c2 = c1361c.d;
        while (true) {
            if (c1361c2.equals(c1361c)) {
                return null;
            }
            ArrayList arrayList = c1361c2.f15512b;
            int size = arrayList != null ? arrayList.size() : 0;
            Object objRemove = size > 0 ? c1361c2.f15512b.remove(size - 1) : null;
            if (objRemove != null) {
                return objRemove;
            }
            C1361c c1361c3 = c1361c2.d;
            c1361c3.c = c1361c2.c;
            c1361c2.c.d = c1361c3;
            HashMap map = (HashMap) this.f16625n;
            InterfaceC1365g interfaceC1365g = c1361c2.f15511a;
            map.remove(interfaceC1365g);
            interfaceC1365g.a();
            c1361c2 = c1361c2.d;
        }
    }

    public C1489n1 G(double d) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        cVar.o();
        if (cVar.f16976q || !(Double.isNaN(d) || Double.isInfinite(d))) {
            cVar.b();
            cVar.l.append((CharSequence) Double.toString(d));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
    }

    public C1489n1 H(long j3) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        cVar.o();
        cVar.b();
        cVar.l.write(Long.toString(j3));
        return this;
    }

    public C1489n1 I(K k10, Object obj) {
        ((e7.f) this.f16625n).o(this, k10, obj);
        return this;
    }

    public C1489n1 J(Boolean bool) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        if (bool == null) {
            cVar.j();
        } else {
            cVar.o();
            cVar.b();
            cVar.l.write(bool.booleanValue() ? "true" : "false");
        }
        return this;
    }

    public C1489n1 K(Number number) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        if (number == null) {
            cVar.j();
        } else {
            cVar.o();
            String string = number.toString();
            if (!cVar.f16976q && (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
                throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
            }
            cVar.b();
            cVar.l.append((CharSequence) string);
        }
        return this;
    }

    public C1489n1 L(String str) {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        if (str == null) {
            cVar.j();
        } else {
            cVar.o();
            cVar.b();
            cVar.m(str);
        }
        return this;
    }

    public C1489n1 M(boolean z6) throws IOException {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        cVar.o();
        cVar.b();
        cVar.l.write(z6 ? "true" : "false");
        return this;
    }

    @Override // I3.g
    public void N(I3.a aVar, Bitmap bitmap, Map map) {
        int i7;
        Object objRemove;
        int iB = com.bumptech.glide.d.B(bitmap);
        I3.e eVar = (I3.e) this.f16625n;
        synchronized (((m6.b) eVar.f22260g)) {
            i7 = eVar.f22257b;
        }
        if (iB <= i7) {
            ((I3.e) this.f16625n).k(aVar, new I3.d(bitmap, map, iB));
            return;
        }
        I3.e eVar2 = (I3.e) this.f16625n;
        eVar2.getClass();
        synchronized (((m6.b) eVar2.f22260g)) {
            K3.n nVar = (K3.n) eVar2.f22259f;
            nVar.getClass();
            objRemove = nVar.f3576a.remove(aVar);
            if (objRemove != null) {
                eVar2.c -= eVar2.l(aVar, objRemove);
            }
        }
        if (objRemove != null) {
            eVar2.c(aVar, objRemove, null);
        }
        ((C1.j) this.f16624m).r(aVar, bitmap, map, iB);
    }

    @Override // T1.o
    public boolean a(CharSequence charSequence, int i7, int i10, T1.w wVar) {
        if ((wVar.c & 4) > 0) {
            return true;
        }
        if (((T1.y) this.f16624m) == null) {
            this.f16624m = new T1.y(charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableString(charSequence));
        }
        ((C8.d) this.f16625n).getClass();
        ((T1.y) this.f16624m).setSpan(new T1.x(wVar), i7, i10, 33);
        return true;
    }

    @Override // I3.g
    public void b(int i7) {
        int i10;
        if (i7 >= 40) {
            ((I3.e) this.f16625n).n(-1);
            return;
        }
        if (10 > i7 || i7 >= 20) {
            return;
        }
        I3.e eVar = (I3.e) this.f16625n;
        synchronized (((m6.b) eVar.f22260g)) {
            i10 = eVar.c;
        }
        eVar.n(i10 / 2);
    }

    @Override // T1.o
    public Object c() {
        return (T1.y) this.f16624m;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x011c A[EDGE_INSN: B:64:0x011c->B:50:0x011c BREAK  A[LOOP:1: B:36:0x00e3->B:49:0x010b], SYNTHETIC] */
    @Override // B2.InterfaceC0070j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public B2.C0069i d(B2.C0073m r17, long r18) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.C1489n1.d(B2.m, long):B2.i");
    }

    @Override // B2.InterfaceC0070j
    public void e() {
        byte[] bArr = AbstractC1343A.f15198f;
        h2.t tVar = (h2.t) this.f16625n;
        tVar.getClass();
        tVar.F(bArr.length, bArr);
    }

    public void f(String str) {
        h6.e.q(str, "integration is required.");
        ((CopyOnWriteArraySet) this.f16624m).add(str);
    }

    public void g(String str) {
        ((CopyOnWriteArraySet) this.f16625n).add(new io.sentry.protocol.u(str, "7.18.0"));
    }

    public AbstractC1598c h() {
        String str = (String) this.f16624m;
        if (str != null) {
            return k1.i.s(str);
        }
        h6.n.j("CCL", "DimensionDescription: Null value & symbol for " + ((String) this.f16625n) + ". Using WrapContent.");
        return k1.i.s("wrap");
    }

    public C1489n1 i() {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        cVar.o();
        cVar.b();
        int i7 = cVar.f16974n;
        int[] iArr = cVar.f16973m;
        if (i7 == iArr.length) {
            cVar.f16973m = Arrays.copyOf(iArr, i7 * 2);
        }
        int[] iArr2 = cVar.f16973m;
        int i10 = cVar.f16974n;
        cVar.f16974n = i10 + 1;
        iArr2[i10] = 3;
        cVar.l.write(123);
        return this;
    }

    @Override // c9.InterfaceC1048i
    public void j(g9.j jVar, c9.H h10) {
        A7.m.f(jVar, "call");
        if (h10.f12518r == null || !h10.j()) {
            ((X5.V0) this.f16624m).d(new E5.s(android.hardware.bydauto.ac.tsimpl.a.e(h10.o, "Failed to load MIDI. Status code: ")));
            return;
        }
        B3.m mVar = h10.f12518r;
        A7.m.c(mVar);
        n6.w wVar = new n6.w(new ByteArrayInputStream(n7.z.w(mVar.g())));
        X5.V0 v02 = (X5.V0) this.f16624m;
        Object obj = v02.f10213j;
        String str = (String) this.f16625n;
        synchronized (obj) {
            if (A7.m.a(str, v02.l)) {
                V8.f0 f0Var = v02.f10214k;
                f0Var.getClass();
                f0Var.m(null, wVar);
            }
        }
    }

    @Override // P0.c
    public int l(int i7) {
        CharSequence charSequence = (CharSequence) this.f16624m;
        return ((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, i7, 2);
    }

    @Override // P0.c
    public int m(int i7) {
        CharSequence charSequence = (CharSequence) this.f16624m;
        return ((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, i7, 0);
    }

    public C1489n1 n() {
        ((io.sentry.vendor.gson.stream.c) this.f16624m).g('}', 3, 5);
        return this;
    }

    public void o(String str, InterfaceC2782k interfaceC2782k) {
        LinkedHashMap linkedHashMap = ((K3.n) this.f16625n).f3576a;
        C1324n c1324n = new C1324n(this, str);
        interfaceC2782k.b(c1324n);
        ArrayList arrayList = c1324n.f15022b;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) ((m7.i) it.next()).l);
        }
        String str2 = (String) c1324n.c.l;
        String str3 = c1324n.f15021a;
        A7.m.f(str2, "ret");
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append('(');
        sb.append(AbstractC1998m.f0(arrayList2, "", null, null, h8.q.f15623m, 30));
        sb.append(')');
        if (str2.length() > 1) {
            str2 = "L" + str2 + ';';
        }
        sb.append(str2);
        String string = sb.toString();
        String str4 = (String) this.f16624m;
        A7.m.f(str4, "internalName");
        A7.m.f(string, "jvmDescriptor");
        String str5 = str4 + '.' + string;
        C1326p c1326p = (C1326p) c1324n.c.f19259m;
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add((C1326p) ((m7.i) it2.next()).f19259m);
        }
        linkedHashMap.put(str5, new C1322l(c1326p, arrayList3));
    }

    public Object p(InterfaceC1365g interfaceC1365g) {
        HashMap map = (HashMap) this.f16625n;
        C1361c c1361c = (C1361c) map.get(interfaceC1365g);
        if (c1361c == null) {
            c1361c = new C1361c(interfaceC1365g);
            map.put(interfaceC1365g, c1361c);
        } else {
            interfaceC1365g.a();
        }
        C1361c c1361c2 = c1361c.d;
        c1361c2.c = c1361c.c;
        c1361c.c.d = c1361c2;
        C1361c c1361c3 = (C1361c) this.f16624m;
        c1361c.d = c1361c3;
        C1361c c1361c4 = c1361c3.c;
        c1361c.c = c1361c4;
        c1361c4.d = c1361c;
        c1361c.d.c = c1361c;
        ArrayList arrayList = c1361c.f15512b;
        int size = arrayList != null ? arrayList.size() : 0;
        if (size > 0) {
            return c1361c.f15512b.remove(size - 1);
        }
        return null;
    }

    public int q(InterfaceC0318c interfaceC0318c) {
        int iIntValue;
        A7.m.f(interfaceC0318c, "kClass");
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f16624m;
        String strA = interfaceC0318c.a();
        A7.m.c(strA);
        A7.m.f(concurrentHashMap, "<this>");
        Integer num = (Integer) concurrentHashMap.get(strA);
        if (num != null) {
            return num.intValue();
        }
        synchronized (concurrentHashMap) {
            try {
                Integer numValueOf = (Integer) concurrentHashMap.get(strA);
                if (numValueOf == null) {
                    int andIncrement = ((AtomicInteger) this.f16625n).getAndIncrement();
                    numValueOf = Integer.valueOf(andIncrement);
                    concurrentHashMap.putIfAbsent(strA, Integer.valueOf(andIncrement));
                }
                iIntValue = numValueOf.intValue();
            } catch (Throwable th) {
                throw th;
            }
        }
        return iIntValue;
    }

    @Override // c9.InterfaceC1048i
    public void r(g9.j jVar, IOException iOException) {
        A7.m.f(jVar, "call");
        ((X5.V0) this.f16624m).d(iOException);
    }

    public m0.d t(E0.F f10) {
        Field field = (Field) this.f16625n;
        if (field == null) {
            return null;
        }
        try {
            E0.d0 d0VarA = ((E0.N) field.get(f10)).a();
            d0VarA.getClass();
            return C0.c0.f(d0VarA);
        } catch (Exception e10) {
            ((K) this.f16624m).s(EnumC1495p1.WARNING, "Could not fetch position for LayoutNode", e10);
            return null;
        }
    }

    public String toString() {
        switch (this.l) {
            case 20:
                StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
                C1361c c1361c = (C1361c) this.f16624m;
                C1361c c1361c2 = c1361c.c;
                boolean z6 = false;
                while (!c1361c2.equals(c1361c)) {
                    sb.append('{');
                    sb.append(c1361c2.f15511a);
                    sb.append(':');
                    ArrayList arrayList = c1361c2.f15512b;
                    sb.append(arrayList != null ? arrayList.size() : 0);
                    sb.append("}, ");
                    c1361c2 = c1361c2.c;
                    z6 = true;
                }
                if (z6) {
                    sb.delete(sb.length() - 2, sb.length());
                }
                sb.append(" )");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public C0.K u() {
        return (C0.K) ((C0738e0) this.f16625n).getValue();
    }

    public synchronized Map v() {
        try {
            if (((Map) this.f16625n) == null) {
                this.f16625n = Collections.unmodifiableMap(new HashMap((HashMap) this.f16624m));
            }
        } catch (Throwable th) {
            throw th;
        }
        return (Map) this.f16625n;
    }

    public C1489n1 w(String str) {
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) this.f16624m;
        if (str == null) {
            cVar.getClass();
            throw new NullPointerException("name == null");
        }
        if (cVar.f16977r != null) {
            throw new IllegalStateException();
        }
        if (cVar.f16974n == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        cVar.f16977r = str;
        return this;
    }

    @Override // I3.g
    public I3.b x(I3.a aVar) {
        I3.d dVar = (I3.d) ((I3.e) this.f16625n).g(aVar);
        if (dVar != null) {
            return new I3.b(dVar.f2936a, dVar.f2937b);
        }
        return null;
    }

    @Override // P0.c
    public int y(int i7) {
        CharSequence charSequence = (CharSequence) this.f16624m;
        int textRunCursor = ((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, i7, 0);
        if (textRunCursor == -1) {
            return -1;
        }
        if (((TextPaint) this.f16625n).getTextRunCursor(charSequence, 0, charSequence.length(), false, textRunCursor, 0) == -1) {
            return -1;
        }
        return textRunCursor;
    }

    public /* synthetic */ C1489n1(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f16624m = obj;
        this.f16625n = obj2;
    }

    public C1489n1(Writer writer, int i7) {
        this.l = 23;
        this.f16624m = new io.sentry.vendor.gson.stream.c(writer);
        this.f16625n = new e7.f(i7);
    }

    public C1489n1(K k10) {
        this.l = 26;
        this.f16625n = null;
        this.f16624m = k10;
        try {
            Field declaredField = E0.F.class.getDeclaredField("layoutDelegate");
            this.f16625n = declaredField;
            declaredField.setAccessible(true);
        } catch (Exception unused) {
            k10.k(EnumC1495p1.WARNING, "Could not find LayoutNode.layoutDelegate field", new Object[0]);
        }
    }

    public C1489n1(E0.F f10, C0.K k10) {
        this.l = 4;
        this.f16624m = f10;
        this.f16625n = C0735d.O(k10, T.S.f8224q);
    }

    public C1489n1(C1459d1 c1459d1, Collection collection) {
        this.l = 24;
        h6.e.q(c1459d1, "SentryEnvelopeHeader is required.");
        this.f16624m = c1459d1;
        h6.e.q(collection, "SentryEnvelope items are required.");
        this.f16625n = collection;
    }

    public C1489n1(int i7) {
        this.l = i7;
        switch (i7) {
            case 2:
                this.f16625n = new HashMap();
                HandlerThread handlerThread = new HandlerThread("Segment-Stats", 10);
                handlerThread.start();
                this.f16624m = new B5.H(handlerThread.getLooper(), this, 1);
                break;
            case 6:
                this.f16624m = new ConcurrentHashMap();
                this.f16625n = new AtomicInteger(0);
                break;
            case 8:
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                this.f16624m = byteArrayOutputStream;
                this.f16625n = new DataOutputStream(byteArrayOutputStream);
                break;
            case 10:
                this.f16624m = new C8.d(11);
                this.f16625n = new R0.b(16);
                break;
            case 20:
                this.f16624m = new C1361c(null);
                this.f16625n = new HashMap();
                break;
            case 22:
                this.f16624m = new HashMap();
                this.f16625n = new C1410c(0);
                break;
            case 28:
                this.f16624m = new HashMap();
                break;
            default:
                this.f16624m = new CopyOnWriteArraySet();
                this.f16625n = new CopyOnWriteArraySet();
                break;
        }
    }

    public C1489n1(io.sentry.protocol.t tVar, io.sentry.protocol.r rVar, C1474i1 c1474i1) {
        this.l = 24;
        this.f16624m = new C1459d1(tVar, rVar, null);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(c1474i1);
        this.f16625n = arrayList;
    }

    public C1489n1(K k10, X1 x12) {
        this.l = 25;
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        this.f16624m = linkedBlockingDeque;
        h6.e.q(k10, "logger is required");
        this.f16625n = k10;
        linkedBlockingDeque.push(x12);
    }

    public C1489n1(int i7, C1.j jVar) {
        this.l = 7;
        this.f16624m = jVar;
        this.f16625n = new I3.e(i7, this);
    }

    public C1489n1(h2.y yVar) {
        this.l = 19;
        this.f16624m = yVar;
        this.f16625n = new h2.t();
    }

    public C1489n1(K3.n nVar, String str) {
        this.l = 18;
        A7.m.f(str, "className");
        this.f16625n = nVar;
        this.f16624m = str;
    }

    public C1489n1(EditText editText) {
        this.l = 12;
        this.f16624m = editText;
        V1.h hVar = new V1.h(editText);
        this.f16625n = hVar;
        editText.addTextChangedListener(hVar);
        if (V1.a.f9312b == null) {
            synchronized (V1.a.f9311a) {
                try {
                    if (V1.a.f9312b == null) {
                        V1.a aVar = new V1.a();
                        try {
                            V1.a.c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, V1.a.class.getClassLoader());
                        } catch (Throwable unused) {
                        }
                        V1.a.f9312b = aVar;
                    }
                } finally {
                }
            }
        }
        editText.setEditableFactory(V1.a.f9312b);
    }

    public C1489n1(d5.d0 d0Var, int[] iArr) {
        this.l = 21;
        this.f16624m = d5.I.w(d0Var);
        this.f16625n = iArr;
    }
}
