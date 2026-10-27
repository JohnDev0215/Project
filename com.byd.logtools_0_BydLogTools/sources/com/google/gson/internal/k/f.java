package com.google.gson.internal.k;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends com.google.gson.stream.a {
    private static final Reader q = new a();
    private static final Object r = new Object();
    private Object[] s;
    private int t;
    private String[] u;
    private int[] v;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    class a extends Reader {
        a() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    private void J(JsonToken jsonToken) throws IOException {
        if (x() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + x() + m());
    }

    private Object L() {
        return this.s[this.t - 1];
    }

    private Object M() {
        Object[] objArr = this.s;
        int i = this.t - 1;
        this.t = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    private void O(Object obj) {
        int i = this.t;
        Object[] objArr = this.s;
        if (i == objArr.length) {
            int i2 = i * 2;
            this.s = Arrays.copyOf(objArr, i2);
            this.v = Arrays.copyOf(this.v, i2);
            this.u = (String[]) Arrays.copyOf(this.u, i2);
        }
        Object[] objArr2 = this.s;
        int i3 = this.t;
        this.t = i3 + 1;
        objArr2[i3] = obj;
    }

    private String m() {
        return " at path " + i();
    }

    @Override // com.google.gson.stream.a
    public void H() throws IOException {
        if (x() == JsonToken.NAME) {
            r();
            this.u[this.t - 2] = "null";
        } else {
            M();
            int i = this.t;
            if (i > 0) {
                this.u[i - 1] = "null";
            }
        }
        int i2 = this.t;
        if (i2 > 0) {
            int[] iArr = this.v;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    com.google.gson.i K() throws IOException {
        JsonToken jsonTokenX = x();
        if (jsonTokenX != JsonToken.NAME && jsonTokenX != JsonToken.END_ARRAY && jsonTokenX != JsonToken.END_OBJECT && jsonTokenX != JsonToken.END_DOCUMENT) {
            com.google.gson.i iVar = (com.google.gson.i) L();
            H();
            return iVar;
        }
        throw new IllegalStateException("Unexpected " + jsonTokenX + " when reading a JsonElement.");
    }

    public void N() throws IOException {
        J(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) L()).next();
        O(entry.getValue());
        O(new com.google.gson.l((String) entry.getKey()));
    }

    @Override // com.google.gson.stream.a
    public void a() throws IOException {
        J(JsonToken.BEGIN_ARRAY);
        O(((com.google.gson.f) L()).iterator());
        this.v[this.t - 1] = 0;
    }

    @Override // com.google.gson.stream.a
    public void b() throws IOException {
        J(JsonToken.BEGIN_OBJECT);
        O(((com.google.gson.k) L()).i().iterator());
    }

    @Override // com.google.gson.stream.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.s = new Object[]{r};
        this.t = 1;
    }

    @Override // com.google.gson.stream.a
    public void f() throws IOException {
        J(JsonToken.END_ARRAY);
        M();
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.a
    public void g() throws IOException {
        J(JsonToken.END_OBJECT);
        M();
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.a
    public String i() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (true) {
            int i2 = this.t;
            if (i >= i2) {
                return sb.toString();
            }
            Object[] objArr = this.s;
            if (objArr[i] instanceof com.google.gson.f) {
                i++;
                if (i < i2 && (objArr[i] instanceof Iterator)) {
                    sb.append('[');
                    sb.append(this.v[i]);
                    sb.append(']');
                }
            } else if ((objArr[i] instanceof com.google.gson.k) && (i = i + 1) < i2 && (objArr[i] instanceof Iterator)) {
                sb.append('.');
                String[] strArr = this.u;
                if (strArr[i] != null) {
                    sb.append(strArr[i]);
                }
            }
            i++;
        }
    }

    @Override // com.google.gson.stream.a
    public boolean j() throws IOException {
        JsonToken jsonTokenX = x();
        return (jsonTokenX == JsonToken.END_OBJECT || jsonTokenX == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // com.google.gson.stream.a
    public boolean n() throws IOException {
        J(JsonToken.BOOLEAN);
        boolean zH = ((com.google.gson.l) M()).h();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zH;
    }

    @Override // com.google.gson.stream.a
    public double o() throws IOException {
        JsonToken jsonTokenX = x();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenX != jsonToken && jsonTokenX != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenX + m());
        }
        double dI = ((com.google.gson.l) L()).i();
        if (!k() && (Double.isNaN(dI) || Double.isInfinite(dI))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dI);
        }
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dI;
    }

    @Override // com.google.gson.stream.a
    public int p() throws IOException {
        JsonToken jsonTokenX = x();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenX != jsonToken && jsonTokenX != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenX + m());
        }
        int iJ = ((com.google.gson.l) L()).j();
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iJ;
    }

    @Override // com.google.gson.stream.a
    public long q() throws IOException {
        JsonToken jsonTokenX = x();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenX != jsonToken && jsonTokenX != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenX + m());
        }
        long jK = ((com.google.gson.l) L()).k();
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jK;
    }

    @Override // com.google.gson.stream.a
    public String r() throws IOException {
        J(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) L()).next();
        String str = (String) entry.getKey();
        this.u[this.t - 1] = str;
        O(entry.getValue());
        return str;
    }

    @Override // com.google.gson.stream.a
    public void t() throws IOException {
        J(JsonToken.NULL);
        M();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.google.gson.stream.a
    public String toString() {
        return f.class.getSimpleName() + m();
    }

    @Override // com.google.gson.stream.a
    public String v() throws IOException {
        JsonToken jsonTokenX = x();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenX == jsonToken || jsonTokenX == JsonToken.NUMBER) {
            String strM = ((com.google.gson.l) M()).m();
            int i = this.t;
            if (i > 0) {
                int[] iArr = this.v;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strM;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenX + m());
    }

    @Override // com.google.gson.stream.a
    public JsonToken x() throws IOException {
        if (this.t == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objL = L();
        if (objL instanceof Iterator) {
            boolean z = this.s[this.t - 2] instanceof com.google.gson.k;
            Iterator it = (Iterator) objL;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            O(it.next());
            return x();
        }
        if (objL instanceof com.google.gson.k) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objL instanceof com.google.gson.f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objL instanceof com.google.gson.l)) {
            if (objL instanceof com.google.gson.j) {
                return JsonToken.NULL;
            }
            if (objL == r) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        com.google.gson.l lVar = (com.google.gson.l) objL;
        if (lVar.q()) {
            return JsonToken.STRING;
        }
        if (lVar.n()) {
            return JsonToken.BOOLEAN;
        }
        if (lVar.p()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }
}
