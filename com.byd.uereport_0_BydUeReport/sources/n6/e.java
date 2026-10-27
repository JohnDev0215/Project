package n6;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends q6.a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Reader f6562t = new a();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Object f6563u = new Object();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Object[] f6564v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f6565w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String[] f6566x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f6567y;

    /* JADX INFO: compiled from: JsonTreeReader.java */
    public static class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i8, int i9) throws IOException {
            throw new AssertionError();
        }
    }

    private String x() {
        return " at path " + a();
    }

    @Override // q6.a
    public boolean B() throws IOException {
        k0(JsonToken.BOOLEAN);
        boolean zI = ((k6.l) m0()).i();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
        return zI;
    }

    @Override // q6.a
    public double C() throws IOException {
        JsonToken jsonTokenR = R();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenR != jsonToken && jsonTokenR != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenR + x());
        }
        double dK = ((k6.l) l0()).k();
        if (!v() && (Double.isNaN(dK) || Double.isInfinite(dK))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dK);
        }
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
        return dK;
    }

    @Override // q6.a
    public int E() throws IOException {
        JsonToken jsonTokenR = R();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenR != jsonToken && jsonTokenR != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenR + x());
        }
        int iL = ((k6.l) l0()).l();
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
        return iL;
    }

    @Override // q6.a
    public long F() throws IOException {
        JsonToken jsonTokenR = R();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenR != jsonToken && jsonTokenR != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenR + x());
        }
        long jM = ((k6.l) l0()).m();
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
        return jM;
    }

    @Override // q6.a
    public String I() throws IOException {
        k0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) l0()).next();
        String str = (String) entry.getKey();
        this.f6566x[this.f6565w - 1] = str;
        o0(entry.getValue());
        return str;
    }

    @Override // q6.a
    public void M() throws IOException {
        k0(JsonToken.NULL);
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
    }

    @Override // q6.a
    public String O() throws IOException {
        JsonToken jsonTokenR = R();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenR == jsonToken || jsonTokenR == JsonToken.NUMBER) {
            String strO = ((k6.l) m0()).o();
            int i8 = this.f6565w;
            if (i8 > 0) {
                int[] iArr = this.f6567y;
                int i9 = i8 - 1;
                iArr[i9] = iArr[i9] + 1;
            }
            return strO;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenR + x());
    }

    @Override // q6.a
    public JsonToken R() throws IOException {
        if (this.f6565w == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objL0 = l0();
        if (objL0 instanceof Iterator) {
            boolean z7 = this.f6564v[this.f6565w - 2] instanceof k6.k;
            Iterator it = (Iterator) objL0;
            if (!it.hasNext()) {
                return z7 ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z7) {
                return JsonToken.NAME;
            }
            o0(it.next());
            return R();
        }
        if (objL0 instanceof k6.k) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objL0 instanceof k6.f) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objL0 instanceof k6.l)) {
            if (objL0 instanceof k6.j) {
                return JsonToken.NULL;
            }
            if (objL0 == f6563u) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        k6.l lVar = (k6.l) objL0;
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

    @Override // q6.a
    public String a() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i8 = 0;
        while (i8 < this.f6565w) {
            Object[] objArr = this.f6564v;
            if (objArr[i8] instanceof k6.f) {
                i8++;
                if (objArr[i8] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.f6567y[i8]);
                    sb.append(']');
                }
            } else if (objArr[i8] instanceof k6.k) {
                i8++;
                if (objArr[i8] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.f6566x;
                    if (strArr[i8] != null) {
                        sb.append(strArr[i8]);
                    }
                }
            }
            i8++;
        }
        return sb.toString();
    }

    @Override // q6.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f6564v = new Object[]{f6563u};
        this.f6565w = 1;
    }

    @Override // q6.a
    public void g() throws IOException {
        k0(JsonToken.BEGIN_ARRAY);
        o0(((k6.f) l0()).iterator());
        this.f6567y[this.f6565w - 1] = 0;
    }

    @Override // q6.a
    public void g0() throws IOException {
        if (R() == JsonToken.NAME) {
            I();
            this.f6566x[this.f6565w - 2] = "null";
        } else {
            m0();
            this.f6566x[this.f6565w - 1] = "null";
        }
        int[] iArr = this.f6567y;
        int i8 = this.f6565w - 1;
        iArr[i8] = iArr[i8] + 1;
    }

    @Override // q6.a
    public void i() throws IOException {
        k0(JsonToken.BEGIN_OBJECT);
        o0(((k6.k) l0()).j().iterator());
    }

    public final void k0(JsonToken jsonToken) throws IOException {
        if (R() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + R() + x());
    }

    public final Object l0() {
        return this.f6564v[this.f6565w - 1];
    }

    public final Object m0() {
        Object[] objArr = this.f6564v;
        int i8 = this.f6565w - 1;
        this.f6565w = i8;
        Object obj = objArr[i8];
        objArr[i8] = null;
        return obj;
    }

    public void n0() throws IOException {
        k0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) l0()).next();
        o0(entry.getValue());
        o0(new k6.l((String) entry.getKey()));
    }

    @Override // q6.a
    public void o() throws IOException {
        k0(JsonToken.END_ARRAY);
        m0();
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
    }

    public final void o0(Object obj) {
        int i8 = this.f6565w;
        Object[] objArr = this.f6564v;
        if (i8 == objArr.length) {
            Object[] objArr2 = new Object[i8 * 2];
            int[] iArr = new int[i8 * 2];
            String[] strArr = new String[i8 * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i8);
            System.arraycopy(this.f6567y, 0, iArr, 0, this.f6565w);
            System.arraycopy(this.f6566x, 0, strArr, 0, this.f6565w);
            this.f6564v = objArr2;
            this.f6567y = iArr;
            this.f6566x = strArr;
        }
        Object[] objArr3 = this.f6564v;
        int i9 = this.f6565w;
        this.f6565w = i9 + 1;
        objArr3[i9] = obj;
    }

    @Override // q6.a
    public void q() throws IOException {
        k0(JsonToken.END_OBJECT);
        m0();
        m0();
        int i8 = this.f6565w;
        if (i8 > 0) {
            int[] iArr = this.f6567y;
            int i9 = i8 - 1;
            iArr[i9] = iArr[i9] + 1;
        }
    }

    @Override // q6.a
    public boolean t() throws IOException {
        JsonToken jsonTokenR = R();
        return (jsonTokenR == JsonToken.END_OBJECT || jsonTokenR == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // q6.a
    public String toString() {
        return e.class.getSimpleName();
    }
}
