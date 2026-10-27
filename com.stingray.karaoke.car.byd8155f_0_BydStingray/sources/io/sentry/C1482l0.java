package io.sentry;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1482l0 implements Closeable {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f16608m;

    public C1482l0(Reader reader) {
        this.f16608m = new io.sentry.vendor.gson.stream.a(reader);
    }

    private final void V(boolean z6) {
    }

    private final void W() {
    }

    public final ArrayList C(K k10, InterfaceC1452b0 interfaceC1452b0) throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() == io.sentry.vendor.gson.stream.b.NULL) {
                    aVar.L();
                    return null;
                }
                aVar.b();
                ArrayList arrayList = new ArrayList();
                if (aVar.q()) {
                    do {
                        try {
                            arrayList.add(interfaceC1452b0.a(this, k10));
                        } catch (Exception e10) {
                            k10.s(EnumC1495p1.WARNING, "Failed to deserialize object in list.", e10);
                        }
                        break;
                    } while (aVar.P() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT);
                }
                aVar.k();
                return arrayList;
            default:
                if (T() == io.sentry.vendor.gson.stream.b.NULL) {
                    M();
                    return null;
                }
                try {
                    b();
                    ArrayList arrayList2 = new ArrayList();
                    ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
                    if (!arrayDeque.isEmpty()) {
                        do {
                            try {
                                arrayList2.add(interfaceC1452b0.a(this, k10));
                            } catch (Exception e11) {
                                k10.s(EnumC1495p1.WARNING, "Failed to deserialize object in list.", e11);
                            }
                            break;
                        } while (T() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT);
                    }
                    if (arrayDeque.size() > 1) {
                        arrayDeque.removeLast();
                        break;
                    }
                    return arrayList2;
                } catch (Exception e12) {
                    throw new IOException(e12);
                }
        }
    }

    public final long F() throws IOException {
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).G();
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return ((Number) objS).longValue();
                }
                throw new IOException("Expected long");
        }
    }

    public final Long G() throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return Long.valueOf(aVar.G());
                }
                aVar.L();
                return null;
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return Long.valueOf(((Number) objS).longValue());
                }
                return null;
        }
    }

    public final HashMap I(K k10, io.sentry.clientreport.a aVar) throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar2 = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar2.P() == io.sentry.vendor.gson.stream.b.NULL) {
                    aVar2.L();
                    return null;
                }
                HashMap map = new HashMap();
                g();
                if (aVar2.q()) {
                    while (true) {
                        String strI = aVar2.I();
                        ArrayList arrayListC = C(k10, aVar);
                        if (arrayListC != null) {
                            map.put(strI, arrayListC);
                        }
                        if (aVar2.P() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT || aVar2.P() == io.sentry.vendor.gson.stream.b.NAME) {
                        }
                    }
                }
                i();
                return map;
            default:
                if (T() == io.sentry.vendor.gson.stream.b.NULL) {
                    M();
                    return null;
                }
                HashMap map2 = new HashMap();
                try {
                    g();
                    if (!((ArrayDeque) this.f16608m).isEmpty()) {
                        while (true) {
                            String strL = L();
                            ArrayList arrayListC2 = C(k10, aVar);
                            if (arrayListC2 != null) {
                                map2.put(strL, arrayListC2);
                            }
                            if (T() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT || T() == io.sentry.vendor.gson.stream.b.NAME) {
                            }
                        }
                    }
                    i();
                    return map2;
                } catch (Exception e10) {
                    throw new IOException(e10);
                }
        }
    }

    public final HashMap K(K k10, InterfaceC1452b0 interfaceC1452b0) throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() == io.sentry.vendor.gson.stream.b.NULL) {
                    aVar.L();
                    return null;
                }
                aVar.g();
                HashMap map = new HashMap();
                if (aVar.q()) {
                    while (true) {
                        try {
                            map.put(aVar.I(), interfaceC1452b0.a(this, k10));
                        } catch (Exception e10) {
                            k10.s(EnumC1495p1.WARNING, "Failed to deserialize object in map.", e10);
                        }
                        if (aVar.P() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT || aVar.P() == io.sentry.vendor.gson.stream.b.NAME) {
                        }
                        break;
                    }
                }
                aVar.m();
                return map;
            default:
                if (T() == io.sentry.vendor.gson.stream.b.NULL) {
                    M();
                    return null;
                }
                try {
                    g();
                    HashMap map2 = new HashMap();
                    if (!((ArrayDeque) this.f16608m).isEmpty()) {
                        while (true) {
                            try {
                                map2.put(L(), interfaceC1452b0.a(this, k10));
                            } catch (Exception e11) {
                                k10.s(EnumC1495p1.WARNING, "Failed to deserialize object in map.", e11);
                            }
                            if (T() == io.sentry.vendor.gson.stream.b.BEGIN_OBJECT || T() == io.sentry.vendor.gson.stream.b.NAME) {
                            }
                            break;
                        }
                    }
                    i();
                    return map2;
                } catch (Exception e12) {
                    throw new IOException(e12);
                }
        }
    }

    public final String L() throws IOException {
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).I();
            default:
                Map.Entry entry = (Map.Entry) ((ArrayDeque) this.f16608m).peekLast();
                if (entry != null && entry.getKey() != null) {
                    return (String) entry.getKey();
                }
                throw new IOException("Expected a name but was " + T());
        }
    }

    public void M() throws IOException {
        if (S() == null) {
            return;
        }
        throw new IOException("Expected null but was " + T());
    }

    public final Object N() {
        switch (this.l) {
            case 0:
                A7.D d = new A7.D(6, false);
                d.v(this);
                InterfaceC1467g0 interfaceC1467g0P = d.p();
                if (interfaceC1467g0P != null) {
                    return interfaceC1467g0P.getValue();
                }
                return null;
            default:
                return S();
        }
    }

    public final Object O(K k10, InterfaceC1452b0 interfaceC1452b0) {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return interfaceC1452b0.a(this, k10);
                }
                aVar.L();
                return null;
            default:
                ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
                Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
                if (entry == null) {
                    return null;
                }
                Object value = entry.getValue();
                if (k10 != null) {
                    return interfaceC1452b0.a(this, k10);
                }
                arrayDeque.removeLast();
                return value;
        }
    }

    public final String P() throws IOException {
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).N();
            default:
                String str = (String) S();
                if (str != null) {
                    return str;
                }
                throw new IOException("Expected string");
        }
    }

    public final String Q() {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return aVar.N();
                }
                aVar.L();
                return null;
            default:
                return (String) S();
        }
    }

    public final void R(K k10, AbstractMap abstractMap, String str) {
        switch (this.l) {
            case 0:
                try {
                    abstractMap.put(str, N());
                } catch (Exception e10) {
                    k10.p(EnumC1495p1.ERROR, e10, "Error deserializing unknown key: %s", str);
                    return;
                }
                break;
            default:
                try {
                    abstractMap.put(str, S());
                } catch (Exception e11) {
                    k10.p(EnumC1495p1.ERROR, e11, "Error deserializing unknown key: %s", str);
                }
                break;
        }
    }

    public Object S() throws IOException {
        try {
            ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
            Map.Entry entry = (Map.Entry) arrayDeque.peekLast();
            if (entry == null) {
                return null;
            }
            Object value = entry.getValue();
            arrayDeque.removeLast();
            return value;
        } catch (Exception e10) {
            throw new IOException(e10);
        }
    }

    public final io.sentry.vendor.gson.stream.b T() {
        Map.Entry entry;
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).P();
            default:
                ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
                if (!arrayDeque.isEmpty() && (entry = (Map.Entry) arrayDeque.peekLast()) != null) {
                    if (entry.getKey() != null) {
                        return io.sentry.vendor.gson.stream.b.NAME;
                    }
                    Object value = entry.getValue();
                    return value instanceof Map ? io.sentry.vendor.gson.stream.b.BEGIN_OBJECT : value instanceof List ? io.sentry.vendor.gson.stream.b.BEGIN_ARRAY : value instanceof String ? io.sentry.vendor.gson.stream.b.STRING : value instanceof Number ? io.sentry.vendor.gson.stream.b.NUMBER : value instanceof Boolean ? io.sentry.vendor.gson.stream.b.BOOLEAN : value instanceof io.sentry.vendor.gson.stream.b ? (io.sentry.vendor.gson.stream.b) value : io.sentry.vendor.gson.stream.b.END_DOCUMENT;
                }
                return io.sentry.vendor.gson.stream.b.END_DOCUMENT;
        }
    }

    public final void U(boolean z6) {
        switch (this.l) {
            case 0:
                ((io.sentry.vendor.gson.stream.a) this.f16608m).f16959m = z6;
                break;
        }
    }

    public void b() throws IOException {
        ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
        Map.Entry entry = (Map.Entry) arrayDeque.removeLast();
        if (entry == null) {
            throw new IOException("No more entries");
        }
        Object value = entry.getValue();
        if (!(value instanceof List)) {
            throw new IOException("Current token is not an object");
        }
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, io.sentry.vendor.gson.stream.b.END_ARRAY));
        List list = (List) value;
        for (int size = list.size() - 1; size >= 0; size--) {
            arrayDeque.addLast(new AbstractMap.SimpleEntry(null, list.get(size)));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        switch (this.l) {
            case 0:
                ((io.sentry.vendor.gson.stream.a) this.f16608m).close();
                break;
            default:
                ((ArrayDeque) this.f16608m).clear();
                break;
        }
    }

    public final void g() throws IOException {
        switch (this.l) {
            case 0:
                ((io.sentry.vendor.gson.stream.a) this.f16608m).g();
                return;
            default:
                ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
                Map.Entry entry = (Map.Entry) arrayDeque.removeLast();
                if (entry == null) {
                    throw new IOException("No more entries");
                }
                Object value = entry.getValue();
                if (!(value instanceof Map)) {
                    throw new IOException("Current token is not an object");
                }
                arrayDeque.addLast(new AbstractMap.SimpleEntry(null, io.sentry.vendor.gson.stream.b.END_OBJECT));
                Iterator it = ((Map) value).entrySet().iterator();
                while (it.hasNext()) {
                    arrayDeque.addLast((Map.Entry) it.next());
                }
                return;
        }
    }

    public final void i() {
        switch (this.l) {
            case 0:
                ((io.sentry.vendor.gson.stream.a) this.f16608m).m();
                break;
            default:
                ArrayDeque arrayDeque = (ArrayDeque) this.f16608m;
                if (arrayDeque.size() > 1) {
                    arrayDeque.removeLast();
                }
                break;
        }
    }

    public final Boolean j() {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return Boolean.valueOf(aVar.z());
                }
                aVar.L();
                return null;
            default:
                return (Boolean) S();
        }
    }

    public final Date k(K k10) {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() == io.sentry.vendor.gson.stream.b.NULL) {
                    aVar.L();
                    break;
                } else {
                    String strN = aVar.N();
                    if (strN != null) {
                        try {
                            try {
                            } catch (Exception unused) {
                                return AbstractC1985B.i(strN);
                            }
                        } catch (Exception e10) {
                            k10.s(EnumC1495p1.ERROR, "Error when deserializing millis timestamp format.", e10);
                            return null;
                        }
                        break;
                    }
                }
                break;
            default:
                String str = (String) S();
                if (str != null) {
                    try {
                        try {
                        } catch (Exception e11) {
                            k10.s(EnumC1495p1.ERROR, "Error when deserializing millis timestamp format.", e11);
                            return null;
                        }
                    } catch (Exception unused2) {
                        return AbstractC1985B.i(str);
                    }
                    break;
                }
                break;
        }
        return null;
    }

    public final double m() throws IOException {
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).C();
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return ((Number) objS).doubleValue();
                }
                throw new IOException("Expected double");
        }
    }

    public final Double o() throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return Double.valueOf(aVar.C());
                }
                aVar.L();
                return null;
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return Double.valueOf(((Number) objS).doubleValue());
                }
                return null;
        }
    }

    public final float q() throws IOException {
        switch (this.l) {
            case 0:
                return (float) ((io.sentry.vendor.gson.stream.a) this.f16608m).C();
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return ((Number) objS).floatValue();
                }
                throw new IOException("Expected float");
        }
    }

    public final Float v() throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return Float.valueOf(q());
                }
                aVar.L();
                return null;
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return Float.valueOf(((Number) objS).floatValue());
                }
                return null;
        }
    }

    public final int y() throws IOException {
        switch (this.l) {
            case 0:
                return ((io.sentry.vendor.gson.stream.a) this.f16608m).F();
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return ((Number) objS).intValue();
                }
                throw new IOException("Expected int");
        }
    }

    public final Integer z() throws IOException {
        switch (this.l) {
            case 0:
                io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) this.f16608m;
                if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                    return Integer.valueOf(aVar.F());
                }
                aVar.L();
                return null;
            default:
                Object objS = S();
                if (objS instanceof Number) {
                    return Integer.valueOf(((Number) objS).intValue());
                }
                return null;
        }
    }

    public C1482l0(Map map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f16608m = arrayDeque;
        arrayDeque.addLast(new AbstractMap.SimpleEntry(null, map));
    }
}
