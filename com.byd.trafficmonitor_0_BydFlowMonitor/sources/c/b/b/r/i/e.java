package c.b.b.r.i;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends c.b.b.t.a {
    public static final Reader r = new a();
    public static final Object s = new Object();
    public Object[] t;
    public int u;
    public String[] v;
    public int[] w;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    public static class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i2, int i3) throws IOException {
            throw new AssertionError();
        }
    }

    private String Z() {
        return " at path " + V();
    }

    public final void A0(Object obj) {
        int i2 = this.u;
        Object[] objArr = this.t;
        if (i2 == objArr.length) {
            Object[] objArr2 = new Object[i2 * 2];
            int[] iArr = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.w, 0, iArr, 0, this.u);
            System.arraycopy(this.v, 0, strArr, 0, this.u);
            this.t = objArr2;
            this.w = iArr;
            this.v = strArr;
        }
        Object[] objArr3 = this.t;
        int i3 = this.u;
        this.u = i3 + 1;
        objArr3[i3] = obj;
    }

    @Override // c.b.b.t.a
    public void O() throws IOException {
        w0(JsonToken.BEGIN_OBJECT);
        A0(((c.b.b.k) x0()).j().iterator());
    }

    @Override // c.b.b.t.a
    public void S() throws IOException {
        w0(JsonToken.END_ARRAY);
        y0();
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.b.t.a
    public void T() throws IOException {
        w0(JsonToken.END_OBJECT);
        y0();
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.b.t.a
    public String V() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = 0;
        while (i2 < this.u) {
            Object[] objArr = this.t;
            if (objArr[i2] instanceof c.b.b.f) {
                i2++;
                if (objArr[i2] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.w[i2]);
                    sb.append(']');
                }
            } else if (objArr[i2] instanceof c.b.b.k) {
                i2++;
                if (objArr[i2] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.v;
                    if (strArr[i2] != null) {
                        sb.append(strArr[i2]);
                    }
                }
            }
            i2++;
        }
        return sb.toString();
    }

    @Override // c.b.b.t.a
    public boolean W() throws IOException {
        JsonToken jsonTokenK0 = k0();
        return (jsonTokenK0 == JsonToken.END_OBJECT || jsonTokenK0 == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // c.b.b.t.a
    public boolean a0() throws IOException {
        w0(JsonToken.BOOLEAN);
        boolean zI = ((c.b.b.l) y0()).i();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return zI;
    }

    @Override // c.b.b.t.a
    public double b0() throws IOException {
        JsonToken jsonTokenK0 = k0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenK0 != jsonToken && jsonTokenK0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenK0 + Z());
        }
        double dK = ((c.b.b.l) x0()).k();
        if (!X() && (Double.isNaN(dK) || Double.isInfinite(dK))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dK);
        }
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return dK;
    }

    @Override // c.b.b.t.a
    public int c0() throws IOException {
        JsonToken jsonTokenK0 = k0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenK0 != jsonToken && jsonTokenK0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenK0 + Z());
        }
        int iL = ((c.b.b.l) x0()).l();
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return iL;
    }

    @Override // c.b.b.t.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.t = new Object[]{s};
        this.u = 1;
    }

    @Override // c.b.b.t.a
    public long d0() throws IOException {
        JsonToken jsonTokenK0 = k0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenK0 != jsonToken && jsonTokenK0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenK0 + Z());
        }
        long jM = ((c.b.b.l) x0()).m();
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return jM;
    }

    @Override // c.b.b.t.a
    public String e0() throws IOException {
        w0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) x0()).next();
        String str = (String) entry.getKey();
        this.v[this.u - 1] = str;
        A0(entry.getValue());
        return str;
    }

    @Override // c.b.b.t.a
    public void g0() throws IOException {
        w0(JsonToken.NULL);
        y0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.b.t.a
    public String i0() throws IOException {
        JsonToken jsonTokenK0 = k0();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenK0 == jsonToken || jsonTokenK0 == JsonToken.NUMBER) {
            String strO = ((c.b.b.l) y0()).o();
            int i2 = this.u;
            if (i2 > 0) {
                int[] iArr = this.w;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
            }
            return strO;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenK0 + Z());
    }

    @Override // c.b.b.t.a
    public JsonToken k0() throws IOException {
        if (this.u == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objX0 = x0();
        if (objX0 instanceof Iterator) {
            boolean z = this.t[this.u - 2] instanceof c.b.b.k;
            Iterator it = (Iterator) objX0;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            A0(it.next());
            return k0();
        }
        if (objX0 instanceof c.b.b.k) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objX0 instanceof c.b.b.f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objX0 instanceof c.b.b.l)) {
            if (objX0 instanceof c.b.b.j) {
                return JsonToken.NULL;
            }
            if (objX0 == s) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        c.b.b.l lVar = (c.b.b.l) objX0;
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

    @Override // c.b.b.t.a
    public String toString() {
        return e.class.getSimpleName();
    }

    @Override // c.b.b.t.a
    public void u0() throws IOException {
        if (k0() == JsonToken.NAME) {
            e0();
            this.v[this.u - 2] = "null";
        } else {
            y0();
            this.v[this.u - 1] = "null";
        }
        int[] iArr = this.w;
        int i2 = this.u - 1;
        iArr[i2] = iArr[i2] + 1;
    }

    public final void w0(JsonToken jsonToken) throws IOException {
        if (k0() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + k0() + Z());
    }

    public final Object x0() {
        return this.t[this.u - 1];
    }

    public final Object y0() {
        Object[] objArr = this.t;
        int i2 = this.u - 1;
        this.u = i2;
        Object obj = objArr[i2];
        objArr[i2] = null;
        return obj;
    }

    @Override // c.b.b.t.a
    public void z() throws IOException {
        w0(JsonToken.BEGIN_ARRAY);
        A0(((c.b.b.f) x0()).iterator());
        this.w[this.u - 1] = 0;
    }

    public void z0() throws IOException {
        w0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) x0()).next();
        A0(entry.getValue());
        A0(new c.b.b.l((String) entry.getKey()));
    }
}
