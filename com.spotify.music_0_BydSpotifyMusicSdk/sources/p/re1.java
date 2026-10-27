package p;

import com.apollographql.apollo3.api.json.JsonReader$Token;
import com.apollographql.apollo3.exception.JsonDataException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class re1 implements u71 {
    public final List c;
    public JsonReader$Token e;
    public Object k;
    public final Object[] l;
    public final Map[] m;
    public final Iterator[] n;
    public final int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f179p;

    public re1(List list, Map map) {
        v03.j(map, "root");
        v03.j(list, "pathRoot");
        this.c = list;
        this.l = new Object[256];
        this.m = new Map[256];
        this.n = new Iterator[256];
        this.o = new int[256];
        this.e = JsonReader$Token.k;
        this.k = map;
    }

    public static JsonReader$Token c(Object obj) {
        if (obj == null) {
            return JsonReader$Token.r;
        }
        if (obj instanceof List) {
            return JsonReader$Token.c;
        }
        if (obj instanceof Map) {
            return JsonReader$Token.k;
        }
        boolean z = obj instanceof Integer;
        JsonReader$Token jsonReader$Token = JsonReader$Token.o;
        if (!z) {
            if (obj instanceof Long) {
                return JsonReader$Token.f26p;
            }
            if (!(obj instanceof Double) && !(obj instanceof o71)) {
                if (obj instanceof String) {
                    return JsonReader$Token.n;
                }
                if (obj instanceof Boolean) {
                    return JsonReader$Token.q;
                }
                throw new IllegalStateException(("Unsupported value " + obj).toString());
            }
        }
        return jsonReader$Token;
    }

    @Override // p.u71
    public final void D() {
        b();
    }

    @Override // p.u71
    public final int D0() {
        int iIntValue;
        int i;
        int iOrdinal = this.e.ordinal();
        if (iOrdinal != 5 && iOrdinal != 6 && iOrdinal != 7) {
            throw new JsonDataException("Expected an Int but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj instanceof Integer) {
            iIntValue = ((Number) obj).intValue();
        } else {
            if (obj instanceof Long) {
                long jLongValue = ((Number) obj).longValue();
                i = (int) jLongValue;
                if (i != jLongValue) {
                    throw new IllegalStateException((jLongValue + " cannot be converted to Int").toString());
                }
            } else if (obj instanceof Double) {
                double dDoubleValue = ((Number) obj).doubleValue();
                i = (int) dDoubleValue;
                if (i != dDoubleValue) {
                    throw new IllegalStateException((dDoubleValue + " cannot be converted to Int").toString());
                }
            } else if (obj instanceof String) {
                iIntValue = Integer.parseInt((String) obj);
            } else {
                if (!(obj instanceof o71)) {
                    throw new IllegalStateException(("Expected an Int but got " + obj + " instead").toString());
                }
                iIntValue = Integer.parseInt(((o71) obj).a);
            }
            iIntValue = i;
        }
        b();
        return iIntValue;
    }

    @Override // p.u71
    public final double T() {
        double dDoubleValue;
        int iOrdinal = this.e.ordinal();
        if (iOrdinal != 5 && iOrdinal != 6 && iOrdinal != 7) {
            throw new JsonDataException("Expected a Double but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj instanceof Integer) {
            dDoubleValue = ((Number) obj).intValue();
        } else if (obj instanceof Long) {
            long jLongValue = ((Number) obj).longValue();
            double d = jLongValue;
            if (((long) d) != jLongValue) {
                throw new IllegalStateException((jLongValue + " cannot be converted to Double").toString());
            }
            dDoubleValue = d;
        } else if (obj instanceof Double) {
            dDoubleValue = ((Number) obj).doubleValue();
        } else if (obj instanceof String) {
            dDoubleValue = Double.parseDouble((String) obj);
        } else {
            if (!(obj instanceof o71)) {
                throw new IllegalStateException(("Expected a Double but got " + obj + " instead").toString());
            }
            dDoubleValue = Double.parseDouble(((o71) obj).a);
        }
        b();
        return dDoubleValue;
    }

    @Override // p.u71
    public final o71 U() {
        o71 o71Var;
        int iOrdinal = this.e.ordinal();
        if (iOrdinal != 5 && iOrdinal != 6 && iOrdinal != 7) {
            throw new JsonDataException("Expected a Number but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double)) {
            o71Var = new o71(obj.toString());
        } else if (obj instanceof String) {
            o71Var = new o71((String) obj);
        } else {
            if (!(obj instanceof o71)) {
                throw new IllegalStateException(("Expected JsonNumber but got " + obj + " instead").toString());
            }
            o71Var = (o71) obj;
        }
        b();
        return o71Var;
    }

    @Override // p.u71
    public final int W(List list) {
        v03.j(list, "names");
        while (hasNext()) {
            String strR0 = r0();
            int i = this.f179p - 1;
            int[] iArr = this.o;
            int iIndexOf = iArr[i];
            if (iIndexOf >= list.size() || !v03.b(list.get(iIndexOf), strR0)) {
                iIndexOf = list.indexOf(strR0);
                if (iIndexOf != -1) {
                    iArr[this.f179p - 1] = iIndexOf + 1;
                }
            } else {
                int i2 = this.f179p - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            if (iIndexOf != -1) {
                return iIndexOf;
            }
            b();
        }
        return -1;
    }

    @Override // p.u71
    public final long Z() {
        long jLongValue;
        int iOrdinal = this.e.ordinal();
        if (iOrdinal != 5 && iOrdinal != 6 && iOrdinal != 7) {
            throw new JsonDataException("Expected a Long but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj instanceof Integer) {
            jLongValue = ((Number) obj).intValue();
        } else if (obj instanceof Long) {
            jLongValue = ((Number) obj).longValue();
        } else if (obj instanceof Double) {
            double dDoubleValue = ((Number) obj).doubleValue();
            long j = (long) dDoubleValue;
            if (j != dDoubleValue) {
                throw new IllegalStateException((dDoubleValue + " cannot be converted to Long").toString());
            }
            jLongValue = j;
        } else if (obj instanceof String) {
            jLongValue = Long.parseLong((String) obj);
        } else {
            if (!(obj instanceof o71)) {
                throw new IllegalStateException(("Expected Int but got " + obj + " instead").toString());
            }
            jLongValue = Long.parseLong(((o71) obj).a);
        }
        b();
        return jLongValue;
    }

    public final void b() {
        int i = this.f179p;
        if (i == 0) {
            this.e = JsonReader$Token.s;
            return;
        }
        Iterator it = this.n[i - 1];
        v03.h(it);
        int i2 = this.f179p - 1;
        Object[] objArr = this.l;
        Object obj = objArr[i2];
        if (obj instanceof Integer) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            objArr[i2] = Integer.valueOf(((Integer) obj).intValue() + 1);
        }
        if (!it.hasNext()) {
            this.e = objArr[this.f179p + (-1)] instanceof Integer ? JsonReader$Token.e : JsonReader$Token.l;
            return;
        }
        Object next = it.next();
        this.k = next;
        this.e = next instanceof Map.Entry ? JsonReader$Token.m : c(next);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.u71
    public final u71 f() {
        if (this.e != JsonReader$Token.k) {
            throw new JsonDataException("Expected BEGIN_OBJECT but was " + this.e + " at path " + i());
        }
        int i = this.f179p;
        if (i >= 256) {
            throw new IllegalStateException("Nesting too deep".toString());
        }
        this.f179p = i + 1;
        Object obj = this.k;
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        }
        this.m[i] = (Map) obj;
        g();
        return this;
    }

    @Override // p.u71
    public final void g() {
        int i = this.f179p - 1;
        Map map = this.m[i];
        this.l[i] = null;
        v03.h(map);
        this.n[i] = map.entrySet().iterator();
        this.o[this.f179p - 1] = 0;
        b();
    }

    @Override // p.u71
    public final ArrayList getPath() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.c);
        int i = this.f179p;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.l[i2];
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // p.u71
    public final u71 h() {
        if (this.e != JsonReader$Token.e) {
            throw new JsonDataException("Expected END_ARRAY but was " + this.e + " at path " + i());
        }
        int i = this.f179p - 1;
        this.f179p = i;
        this.n[i] = null;
        this.l[i] = null;
        b();
        return this;
    }

    @Override // p.u71
    public final boolean hasNext() {
        int iOrdinal = this.e.ordinal();
        return (iOrdinal == 1 || iOrdinal == 3) ? false : true;
    }

    public final String i() {
        return ou.h0(getPath(), ".", null, null, null, 62);
    }

    @Override // p.u71
    public final u71 j() {
        if (this.e != JsonReader$Token.c) {
            throw new JsonDataException("Expected BEGIN_ARRAY but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any?>");
        }
        List list = (List) obj;
        int i = this.f179p;
        if (i >= 256) {
            throw new IllegalStateException("Nesting too deep".toString());
        }
        this.f179p = i + 1;
        this.l[i] = -1;
        this.n[this.f179p - 1] = list.iterator();
        b();
        return this;
    }

    @Override // p.u71
    public final u71 k() {
        if (this.e != JsonReader$Token.l) {
            throw new JsonDataException("Expected END_OBJECT but was " + this.e + " at path " + i());
        }
        int i = this.f179p - 1;
        this.f179p = i;
        this.n[i] = null;
        this.l[i] = null;
        this.m[i] = null;
        b();
        return this;
    }

    @Override // p.u71
    public final JsonReader$Token peek() {
        return this.e;
    }

    @Override // p.u71
    public final String r0() {
        if (this.e != JsonReader$Token.m) {
            throw new JsonDataException("Expected NAME but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map.Entry<kotlin.String, kotlin.Any?>");
        }
        Map.Entry entry = (Map.Entry) obj;
        this.l[this.f179p - 1] = entry.getKey();
        this.k = entry.getValue();
        this.e = c(entry.getValue());
        return (String) entry.getKey();
    }

    @Override // p.u71
    public final String u() {
        int iOrdinal = this.e.ordinal();
        if (iOrdinal == 5 || iOrdinal == 6 || iOrdinal == 7) {
            Object obj = this.k;
            v03.h(obj);
            String string = obj.toString();
            b();
            return string;
        }
        throw new JsonDataException("Expected a String but was " + this.e + " at path " + i());
    }

    @Override // p.u71
    public final boolean w0() {
        if (this.e != JsonReader$Token.q) {
            throw new JsonDataException("Expected BOOLEAN but was " + this.e + " at path " + i());
        }
        Object obj = this.k;
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        b();
        return zBooleanValue;
    }

    @Override // p.u71
    public final void z0() {
        if (this.e == JsonReader$Token.r) {
            b();
            return;
        }
        throw new JsonDataException("Expected NULL but was " + this.e + " at path " + i());
    }
}
