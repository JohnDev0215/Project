package b.b.a.r.i;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends b.b.a.t.a {
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
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    private String n() {
        return " at path " + j();
    }

    @Override // b.b.a.t.a
    public void I() throws IOException {
        if (y() == JsonToken.NAME) {
            s();
            this.v[this.u - 2] = "null";
        } else {
            M();
            this.v[this.u - 1] = "null";
        }
        int[] iArr = this.w;
        int i = this.u - 1;
        iArr[i] = iArr[i] + 1;
    }

    public final void K(JsonToken jsonToken) throws IOException {
        if (y() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + y() + n());
    }

    public final Object L() {
        return this.t[this.u - 1];
    }

    public final Object M() {
        Object[] objArr = this.t;
        int i = this.u - 1;
        this.u = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public void N() throws IOException {
        K(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) L()).next();
        O(entry.getValue());
        O(new b.b.a.l((String) entry.getKey()));
    }

    public final void O(Object obj) {
        int i = this.u;
        Object[] objArr = this.t;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[i * 2];
            int[] iArr = new int[i * 2];
            String[] strArr = new String[i * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.w, 0, iArr, 0, this.u);
            System.arraycopy(this.v, 0, strArr, 0, this.u);
            this.t = objArr2;
            this.w = iArr;
            this.v = strArr;
        }
        Object[] objArr3 = this.t;
        int i2 = this.u;
        this.u = i2 + 1;
        objArr3[i2] = obj;
    }

    @Override // b.b.a.t.a
    public void b() throws IOException {
        K(JsonToken.BEGIN_ARRAY);
        O(((b.b.a.f) L()).iterator());
        this.w[this.u - 1] = 0;
    }

    @Override // b.b.a.t.a
    public void c() throws IOException {
        K(JsonToken.BEGIN_OBJECT);
        O(((b.b.a.k) L()).j().iterator());
    }

    @Override // b.b.a.t.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.t = new Object[]{s};
        this.u = 1;
    }

    @Override // b.b.a.t.a
    public void g() throws IOException {
        K(JsonToken.END_ARRAY);
        M();
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // b.b.a.t.a
    public void h() throws IOException {
        K(JsonToken.END_OBJECT);
        M();
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // b.b.a.t.a
    public String j() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = 0;
        while (i < this.u) {
            Object[] objArr = this.t;
            if (objArr[i] instanceof b.b.a.f) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.w[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof b.b.a.k) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.v;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // b.b.a.t.a
    public boolean k() throws IOException {
        JsonToken jsonTokenY = y();
        return (jsonTokenY == JsonToken.END_OBJECT || jsonTokenY == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // b.b.a.t.a
    public boolean o() throws IOException {
        K(JsonToken.BOOLEAN);
        boolean zI = ((b.b.a.l) M()).i();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zI;
    }

    @Override // b.b.a.t.a
    public double p() throws IOException {
        JsonToken jsonTokenY = y();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenY != jsonToken && jsonTokenY != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenY + n());
        }
        double dK = ((b.b.a.l) L()).k();
        if (!l() && (Double.isNaN(dK) || Double.isInfinite(dK))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dK);
        }
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return dK;
    }

    @Override // b.b.a.t.a
    public int q() throws IOException {
        JsonToken jsonTokenY = y();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenY != jsonToken && jsonTokenY != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenY + n());
        }
        int iL = ((b.b.a.l) L()).l();
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return iL;
    }

    @Override // b.b.a.t.a
    public long r() throws IOException {
        JsonToken jsonTokenY = y();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenY != jsonToken && jsonTokenY != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenY + n());
        }
        long jM = ((b.b.a.l) L()).m();
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return jM;
    }

    @Override // b.b.a.t.a
    public String s() throws IOException {
        K(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) L()).next();
        String str = (String) entry.getKey();
        this.v[this.u - 1] = str;
        O(entry.getValue());
        return str;
    }

    @Override // b.b.a.t.a
    public String toString() {
        return e.class.getSimpleName();
    }

    @Override // b.b.a.t.a
    public void u() throws IOException {
        K(JsonToken.NULL);
        M();
        int i = this.u;
        if (i > 0) {
            int[] iArr = this.w;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // b.b.a.t.a
    public String w() throws IOException {
        JsonToken jsonTokenY = y();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenY == jsonToken || jsonTokenY == JsonToken.NUMBER) {
            String strO = ((b.b.a.l) M()).o();
            int i = this.u;
            if (i > 0) {
                int[] iArr = this.w;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return strO;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenY + n());
    }

    @Override // b.b.a.t.a
    public JsonToken y() throws IOException {
        if (this.u == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objL = L();
        if (objL instanceof Iterator) {
            boolean z = this.t[this.u - 2] instanceof b.b.a.k;
            Iterator it = (Iterator) objL;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            O(it.next());
            return y();
        }
        if (objL instanceof b.b.a.k) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objL instanceof b.b.a.f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objL instanceof b.b.a.l)) {
            if (objL instanceof b.b.a.j) {
                return JsonToken.NULL;
            }
            if (objL == s) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        b.b.a.l lVar = (b.b.a.l) objL;
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
}
