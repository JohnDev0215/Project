package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p.fo;
import p.ko;

/* JADX INFO: loaded from: classes.dex */
final class j extends JsonReader {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final Object f68p = new Object();
    private Object[] o;

    public static final class a implements Iterator<Object>, Cloneable {
        final JsonReader.Token c;
        final Object[] e;
        int k;

        public a(JsonReader.Token token, Object[] objArr, int i) {
            this.c = token;
            this.e = objArr;
            this.k = i;
        }

        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a clone() {
            return new a(this.c, this.e, this.k);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.k < this.e.length;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object[] objArr = this.e;
            int i = this.k;
            this.k = i + 1;
            return objArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public j(Object obj) {
        int[] iArr = this.e;
        int i = this.c;
        iArr[i] = 7;
        Object[] objArr = new Object[32];
        this.o = objArr;
        this.c = i + 1;
        objArr[i] = obj;
    }

    private void T0(Object obj) {
        int i = this.c;
        if (i == this.o.length) {
            if (i == 256) {
                throw new JsonDataException("Nesting too deep at " + J());
            }
            int[] iArr = this.e;
            this.e = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.k;
            this.k = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.l;
            this.l = Arrays.copyOf(iArr2, iArr2.length * 2);
            Object[] objArr = this.o;
            this.o = Arrays.copyOf(objArr, objArr.length * 2);
        }
        Object[] objArr2 = this.o;
        int i2 = this.c;
        this.c = i2 + 1;
        objArr2[i2] = obj;
    }

    private void U0() {
        int i = this.c;
        int i2 = i - 1;
        this.c = i2;
        Object[] objArr = this.o;
        objArr[i2] = null;
        this.e[i2] = 0;
        if (i2 > 0) {
            int[] iArr = this.l;
            int i3 = i - 2;
            iArr[i3] = iArr[i3] + 1;
            Object obj = objArr[i - 2];
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                if (it.hasNext()) {
                    T0(it.next());
                }
            }
        }
    }

    private <T> T V0(Class<T> cls, JsonReader.Token token) {
        int i = this.c;
        Object obj = i != 0 ? this.o[i - 1] : null;
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        if (obj == null && token == JsonReader.Token.NULL) {
            return null;
        }
        if (obj == f68p) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw S0(obj, token);
    }

    private String W0(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        if (key instanceof String) {
            return (String) key;
        }
        throw S0(key, JsonReader.Token.NAME);
    }

    @Override // com.squareup.moshi.JsonReader
    public void D() {
        if (this.n) {
            throw new JsonDataException("Cannot skip unexpected " + H0() + " at " + J());
        }
        int i = this.c;
        if (i > 1) {
            this.k[i - 2] = "null";
        }
        Object obj = i != 0 ? this.o[i - 1] : null;
        if (obj instanceof a) {
            throw new JsonDataException("Expected a value but was " + H0() + " at path " + J());
        }
        if (obj instanceof Map.Entry) {
            Object[] objArr = this.o;
            objArr[i - 1] = ((Map.Entry) objArr[i - 1]).getValue();
        } else {
            if (i > 0) {
                U0();
                return;
            }
            throw new JsonDataException("Expected a value but was " + H0() + " at path " + J());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int D0() {
        int iIntValueExact;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object objV0 = V0(Object.class, token);
        if (objV0 instanceof Number) {
            iIntValueExact = ((Number) objV0).intValue();
        } else {
            if (!(objV0 instanceof String)) {
                throw S0(objV0, token);
            }
            try {
                try {
                    iIntValueExact = Integer.parseInt((String) objV0);
                } catch (NumberFormatException unused) {
                    iIntValueExact = new BigDecimal((String) objV0).intValueExact();
                }
            } catch (NumberFormatException unused2) {
                throw S0(objV0, JsonReader.Token.NUMBER);
            }
        }
        U0();
        return iIntValueExact;
    }

    @Override // com.squareup.moshi.JsonReader
    public ko E0() throws IOException {
        Object objL0 = L0();
        fo foVar = new fo();
        m mVarL0 = m.L0(foVar);
        try {
            mVarL0.I0(objL0);
            mVarL0.close();
            return foVar;
        } catch (Throwable th) {
            if (mVarL0 != null) {
                try {
                    mVarL0.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader.Token H0() {
        int i = this.c;
        if (i == 0) {
            return JsonReader.Token.END_DOCUMENT;
        }
        Object obj = this.o[i - 1];
        if (obj instanceof a) {
            return ((a) obj).c;
        }
        if (obj instanceof List) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (obj instanceof Map) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (obj instanceof Map.Entry) {
            return JsonReader.Token.NAME;
        }
        if (obj instanceof String) {
            return JsonReader.Token.STRING;
        }
        if (obj instanceof Boolean) {
            return JsonReader.Token.BOOLEAN;
        }
        if (obj instanceof Number) {
            return JsonReader.Token.NUMBER;
        }
        if (obj == null) {
            return JsonReader.Token.NULL;
        }
        if (obj == f68p) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw S0(obj, "a JSON value");
    }

    @Override // com.squareup.moshi.JsonReader
    public JsonReader I0() {
        return new j(this);
    }

    @Override // com.squareup.moshi.JsonReader
    public void J0() {
        if (hasNext()) {
            T0(r0());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public int M0(JsonReader.b bVar) {
        Map.Entry<?, ?> entry = (Map.Entry) V0(Map.Entry.class, JsonReader.Token.NAME);
        String strW0 = W0(entry);
        int length = bVar.a.length;
        for (int i = 0; i < length; i++) {
            if (bVar.a[i].equals(strW0)) {
                this.o[this.c - 1] = entry.getValue();
                this.k[this.c - 2] = strW0;
                return i;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public int N0(JsonReader.b bVar) {
        int i = this.c;
        Object obj = i != 0 ? this.o[i - 1] : null;
        if (!(obj instanceof String)) {
            if (obj != f68p) {
                return -1;
            }
            throw new IllegalStateException("JsonReader is closed");
        }
        String str = (String) obj;
        int length = bVar.a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (bVar.a[i2].equals(str)) {
                U0();
                return i2;
            }
        }
        return -1;
    }

    @Override // com.squareup.moshi.JsonReader
    public void Q0() {
        if (!this.n) {
            this.o[this.c - 1] = ((Map.Entry) V0(Map.Entry.class, JsonReader.Token.NAME)).getValue();
            this.k[this.c - 2] = "null";
        } else {
            JsonReader.Token tokenH0 = H0();
            r0();
            throw new JsonDataException("Cannot skip unexpected " + tokenH0 + " at " + J());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public double T() throws JsonEncodingException {
        double dDoubleValue;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object objV0 = V0(Object.class, token);
        if (objV0 instanceof Number) {
            dDoubleValue = ((Number) objV0).doubleValue();
        } else {
            if (!(objV0 instanceof String)) {
                throw S0(objV0, token);
            }
            try {
                dDoubleValue = Double.parseDouble((String) objV0);
            } catch (NumberFormatException unused) {
                throw S0(objV0, JsonReader.Token.NUMBER);
            }
        }
        if (this.m || !(Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue))) {
            U0();
            return dDoubleValue;
        }
        throw new JsonEncodingException("JSON forbids NaN and infinities: " + dDoubleValue + " at path " + J());
    }

    @Override // com.squareup.moshi.JsonReader
    public long Z() {
        long jLongValueExact;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object objV0 = V0(Object.class, token);
        if (objV0 instanceof Number) {
            jLongValueExact = ((Number) objV0).longValue();
        } else {
            if (!(objV0 instanceof String)) {
                throw S0(objV0, token);
            }
            try {
                try {
                    jLongValueExact = Long.parseLong((String) objV0);
                } catch (NumberFormatException unused) {
                    jLongValueExact = new BigDecimal((String) objV0).longValueExact();
                }
            } catch (NumberFormatException unused2) {
                throw S0(objV0, JsonReader.Token.NUMBER);
            }
        }
        U0();
        return jLongValueExact;
    }

    @Override // com.squareup.moshi.JsonReader
    public void b() {
        List list = (List) V0(List.class, JsonReader.Token.BEGIN_ARRAY);
        a aVar = new a(JsonReader.Token.END_ARRAY, list.toArray(new Object[list.size()]), 0);
        Object[] objArr = this.o;
        int i = this.c;
        objArr[i - 1] = aVar;
        this.e[i - 1] = 1;
        this.l[i - 1] = 0;
        if (aVar.hasNext()) {
            T0(aVar.next());
        }
    }

    @Override // com.squareup.moshi.JsonReader
    public void c() {
        Map map = (Map) V0(Map.class, JsonReader.Token.BEGIN_OBJECT);
        a aVar = new a(JsonReader.Token.END_OBJECT, map.entrySet().toArray(new Object[map.size()]), 0);
        Object[] objArr = this.o;
        int i = this.c;
        objArr[i - 1] = aVar;
        this.e[i - 1] = 3;
        if (aVar.hasNext()) {
            T0(aVar.next());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Arrays.fill(this.o, 0, this.c, (Object) null);
        this.o[0] = f68p;
        this.e[0] = 8;
        this.c = 1;
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean hasNext() {
        int i = this.c;
        if (i == 0) {
            return false;
        }
        Object obj = this.o[i - 1];
        return !(obj instanceof Iterator) || ((Iterator) obj).hasNext();
    }

    @Override // com.squareup.moshi.JsonReader
    public void i() {
        JsonReader.Token token = JsonReader.Token.END_ARRAY;
        a aVar = (a) V0(a.class, token);
        if (aVar.c != token || aVar.hasNext()) {
            throw S0(aVar, token);
        }
        U0();
    }

    @Override // com.squareup.moshi.JsonReader
    public <T> T o0() {
        V0(Void.class, JsonReader.Token.NULL);
        U0();
        return null;
    }

    @Override // com.squareup.moshi.JsonReader
    public void q() {
        JsonReader.Token token = JsonReader.Token.END_OBJECT;
        a aVar = (a) V0(a.class, token);
        if (aVar.c != token || aVar.hasNext()) {
            throw S0(aVar, token);
        }
        this.k[this.c - 1] = null;
        U0();
    }

    @Override // com.squareup.moshi.JsonReader
    public String r0() {
        Map.Entry<?, ?> entry = (Map.Entry) V0(Map.Entry.class, JsonReader.Token.NAME);
        String strW0 = W0(entry);
        this.o[this.c - 1] = entry.getValue();
        this.k[this.c - 2] = strW0;
        return strW0;
    }

    @Override // com.squareup.moshi.JsonReader
    public String u() {
        int i = this.c;
        Object obj = i != 0 ? this.o[i - 1] : null;
        if (obj instanceof String) {
            U0();
            return (String) obj;
        }
        if (obj instanceof Number) {
            U0();
            return obj.toString();
        }
        if (obj == f68p) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw S0(obj, JsonReader.Token.STRING);
    }

    @Override // com.squareup.moshi.JsonReader
    public boolean w0() {
        Boolean bool = (Boolean) V0(Boolean.class, JsonReader.Token.BOOLEAN);
        U0();
        return bool.booleanValue();
    }

    public j(j jVar) {
        super(jVar);
        this.o = (Object[]) jVar.o.clone();
        for (int i = 0; i < this.c; i++) {
            Object[] objArr = this.o;
            Object obj = objArr[i];
            if (obj instanceof a) {
                objArr[i] = ((a) obj).clone();
            }
        }
    }
}
