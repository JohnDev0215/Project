package b.c.a.r.i;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends b.c.a.t.a {
    public static final Reader q = new a();
    public static final Object r = new Object();
    public Object[] s;
    public int t;
    public String[] u;
    public int[] v;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    public static class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    private String s() {
        return " at path " + l();
    }

    @Override // b.c.a.t.a
    public String B() throws IOException {
        JsonToken jsonTokenE = E();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenE == jsonToken || jsonTokenE == JsonToken.NUMBER) {
            String strO = ((b.c.a.l) V()).o();
            int i = this.t;
            if (i > 0) {
                int[] iArr = this.v;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strO;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenE + s());
    }

    @Override // b.c.a.t.a
    public JsonToken E() throws IOException {
        if (this.t == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objU = U();
        if (objU instanceof Iterator) {
            boolean z = this.s[this.t - 2] instanceof b.c.a.k;
            Iterator it = (Iterator) objU;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            X(it.next());
            return E();
        }
        if (objU instanceof b.c.a.k) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objU instanceof b.c.a.f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objU instanceof b.c.a.l)) {
            if (objU instanceof b.c.a.j) {
                return JsonToken.NULL;
            }
            if (objU == r) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        b.c.a.l lVar = (b.c.a.l) objU;
        if (lVar.t()) {
            return JsonToken.STRING;
        }
        if (lVar.p()) {
            return JsonToken.BOOLEAN;
        }
        if (lVar.r()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // b.c.a.t.a
    public void R() throws IOException {
        if (E() == JsonToken.NAME) {
            x();
            this.u[this.t - 2] = "null";
        } else {
            V();
            this.u[this.t - 1] = "null";
        }
        int[] iArr = this.v;
        int i = this.t - 1;
        iArr[i] = iArr[i] + 1;
    }

    public final void T(JsonToken jsonToken) throws IOException {
        if (E() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + E() + s());
    }

    public final Object U() {
        return this.s[this.t - 1];
    }

    public final Object V() {
        Object[] objArr = this.s;
        int i = this.t - 1;
        this.t = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public void W() throws IOException {
        T(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) U()).next();
        X(entry.getValue());
        X(new b.c.a.l((String) entry.getKey()));
    }

    public final void X(Object obj) {
        int i = this.t;
        Object[] objArr = this.s;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i * 2];
            int[] iArr = new int[i * 2];
            String[] strArr = new String[i * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.v, 0, iArr, 0, this.t);
            System.arraycopy(this.u, 0, strArr, 0, this.t);
            this.s = objArr2;
            this.v = iArr;
            this.u = strArr;
        }
        Object[] objArr3 = this.s;
        int i2 = this.t;
        this.t = i2 + 1;
        objArr3[i2] = obj;
    }

    @Override // b.c.a.t.a
    public void c() throws IOException {
        T(JsonToken.BEGIN_ARRAY);
        X(((b.c.a.f) U()).iterator());
        this.v[this.t - 1] = 0;
    }

    @Override // b.c.a.t.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.s = new Object[]{r};
        this.t = 1;
    }

    @Override // b.c.a.t.a
    public void e() throws IOException {
        T(JsonToken.BEGIN_OBJECT);
        X(((b.c.a.k) U()).j().iterator());
    }

    @Override // b.c.a.t.a
    public void i() throws IOException {
        T(JsonToken.END_ARRAY);
        V();
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // b.c.a.t.a
    public void j() throws IOException {
        T(JsonToken.END_OBJECT);
        V();
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // b.c.a.t.a
    public String l() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.t) {
            Object[] objArr = this.s;
            if (objArr[i] instanceof b.c.a.f) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.v[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof b.c.a.k) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.u;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // b.c.a.t.a
    public boolean n() throws IOException {
        JsonToken jsonTokenE = E();
        return (jsonTokenE == JsonToken.END_OBJECT || jsonTokenE == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // b.c.a.t.a
    public boolean t() throws IOException {
        T(JsonToken.BOOLEAN);
        boolean zI = ((b.c.a.l) V()).i();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zI;
    }

    @Override // b.c.a.t.a
    public String toString() {
        return e.class.getSimpleName();
    }

    @Override // b.c.a.t.a
    public double u() throws IOException {
        JsonToken jsonTokenE = E();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenE != jsonToken && jsonTokenE != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenE + s());
        }
        double dK = ((b.c.a.l) U()).k();
        if (!o() && (Double.isNaN(dK) || Double.isInfinite(dK))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dK);
        }
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dK;
    }

    @Override // b.c.a.t.a
    public int v() throws IOException {
        JsonToken jsonTokenE = E();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenE != jsonToken && jsonTokenE != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenE + s());
        }
        int iL = ((b.c.a.l) U()).l();
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iL;
    }

    @Override // b.c.a.t.a
    public long w() throws IOException {
        JsonToken jsonTokenE = E();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenE != jsonToken && jsonTokenE != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenE + s());
        }
        long jM = ((b.c.a.l) U()).m();
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jM;
    }

    @Override // b.c.a.t.a
    public String x() throws IOException {
        T(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) U()).next();
        String str = (String) entry.getKey();
        this.u[this.t - 1] = str;
        X(entry.getValue());
        return str;
    }

    @Override // b.c.a.t.a
    public void z() throws IOException {
        T(JsonToken.NULL);
        V();
        int i = this.t;
        if (i > 0) {
            int[] iArr = this.v;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }
}
