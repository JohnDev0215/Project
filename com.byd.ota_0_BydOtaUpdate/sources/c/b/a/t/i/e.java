package c.b.a.t.i;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends c.b.a.v.a {
    public static final Reader P3 = new a();
    public static final Object Q3 = new Object();
    public Object[] R3;
    public int S3;
    public String[] T3;
    public int[] U3;

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

    public e(c.b.a.k kVar) {
        super(P3);
        this.R3 = new Object[32];
        this.S3 = 0;
        this.T3 = new String[32];
        this.U3 = new int[32];
        r0(kVar);
    }

    private String Q() {
        return " at path " + M();
    }

    @Override // c.b.a.v.a
    public void J() throws IOException {
        n0(JsonToken.END_ARRAY);
        p0();
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.a.v.a
    public void K() throws IOException {
        n0(JsonToken.END_OBJECT);
        p0();
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.a.v.a
    public String M() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = 0;
        while (i2 < this.S3) {
            Object[] objArr = this.R3;
            if (objArr[i2] instanceof c.b.a.h) {
                i2++;
                if (objArr[i2] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.U3[i2]);
                    sb.append(']');
                }
            } else if (objArr[i2] instanceof c.b.a.m) {
                i2++;
                if (objArr[i2] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.T3;
                    if (strArr[i2] != null) {
                        sb.append(strArr[i2]);
                    }
                }
            }
            i2++;
        }
        return sb.toString();
    }

    @Override // c.b.a.v.a
    public boolean N() throws IOException {
        JsonToken jsonTokenB0 = b0();
        return (jsonTokenB0 == JsonToken.END_OBJECT || jsonTokenB0 == JsonToken.END_ARRAY) ? false : true;
    }

    @Override // c.b.a.v.a
    public boolean R() throws IOException {
        n0(JsonToken.BOOLEAN);
        boolean zI = ((c.b.a.n) p0()).i();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return zI;
    }

    @Override // c.b.a.v.a
    public double S() throws IOException {
        JsonToken jsonTokenB0 = b0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenB0 != jsonToken && jsonTokenB0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenB0 + Q());
        }
        double dK = ((c.b.a.n) o0()).k();
        if (!O() && (Double.isNaN(dK) || Double.isInfinite(dK))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dK);
        }
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return dK;
    }

    @Override // c.b.a.v.a
    public int T() throws IOException {
        JsonToken jsonTokenB0 = b0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenB0 != jsonToken && jsonTokenB0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenB0 + Q());
        }
        int iL = ((c.b.a.n) o0()).l();
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return iL;
    }

    @Override // c.b.a.v.a
    public long U() throws IOException {
        JsonToken jsonTokenB0 = b0();
        JsonToken jsonToken = JsonToken.NUMBER;
        if (jsonTokenB0 != jsonToken && jsonTokenB0 != JsonToken.STRING) {
            throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenB0 + Q());
        }
        long jM = ((c.b.a.n) o0()).m();
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return jM;
    }

    @Override // c.b.a.v.a
    public String V() throws IOException {
        n0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) o0()).next();
        String str = (String) entry.getKey();
        this.T3[this.S3 - 1] = str;
        r0(entry.getValue());
        return str;
    }

    @Override // c.b.a.v.a
    public void X() throws IOException {
        n0(JsonToken.NULL);
        p0();
        int i2 = this.S3;
        if (i2 > 0) {
            int[] iArr = this.U3;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // c.b.a.v.a
    public String Z() throws IOException {
        JsonToken jsonTokenB0 = b0();
        JsonToken jsonToken = JsonToken.STRING;
        if (jsonTokenB0 == jsonToken || jsonTokenB0 == JsonToken.NUMBER) {
            String strD = ((c.b.a.n) p0()).d();
            int i2 = this.S3;
            if (i2 > 0) {
                int[] iArr = this.U3;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
            }
            return strD;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + jsonTokenB0 + Q());
    }

    @Override // c.b.a.v.a
    public JsonToken b0() throws IOException {
        if (this.S3 == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object objO0 = o0();
        if (objO0 instanceof Iterator) {
            boolean z = this.R3[this.S3 - 2] instanceof c.b.a.m;
            Iterator it = (Iterator) objO0;
            if (!it.hasNext()) {
                return z ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
            if (z) {
                return JsonToken.NAME;
            }
            r0(it.next());
            return b0();
        }
        if (objO0 instanceof c.b.a.m) {
            return JsonToken.BEGIN_OBJECT;
        }
        if (objO0 instanceof c.b.a.h) {
            return JsonToken.BEGIN_ARRAY;
        }
        if (!(objO0 instanceof c.b.a.n)) {
            if (objO0 instanceof c.b.a.l) {
                return JsonToken.NULL;
            }
            if (objO0 == Q3) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        c.b.a.n nVar = (c.b.a.n) objO0;
        if (nVar.s()) {
            return JsonToken.STRING;
        }
        if (nVar.o()) {
            return JsonToken.BOOLEAN;
        }
        if (nVar.q()) {
            return JsonToken.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // c.b.a.v.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.R3 = new Object[]{Q3};
        this.S3 = 1;
    }

    @Override // c.b.a.v.a
    public void d() throws IOException {
        n0(JsonToken.BEGIN_ARRAY);
        r0(((c.b.a.h) o0()).iterator());
        this.U3[this.S3 - 1] = 0;
    }

    @Override // c.b.a.v.a
    public void l0() throws IOException {
        if (b0() == JsonToken.NAME) {
            V();
            this.T3[this.S3 - 2] = "null";
        } else {
            p0();
            this.T3[this.S3 - 1] = "null";
        }
        int[] iArr = this.U3;
        int i2 = this.S3 - 1;
        iArr[i2] = iArr[i2] + 1;
    }

    public final void n0(JsonToken jsonToken) throws IOException {
        if (b0() == jsonToken) {
            return;
        }
        throw new IllegalStateException("Expected " + jsonToken + " but was " + b0() + Q());
    }

    public final Object o0() {
        return this.R3[this.S3 - 1];
    }

    public final Object p0() {
        Object[] objArr = this.R3;
        int i2 = this.S3 - 1;
        this.S3 = i2;
        Object obj = objArr[i2];
        objArr[i2] = null;
        return obj;
    }

    public void q0() throws IOException {
        n0(JsonToken.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) o0()).next();
        r0(entry.getValue());
        r0(new c.b.a.n((String) entry.getKey()));
    }

    public final void r0(Object obj) {
        int i2 = this.S3;
        Object[] objArr = this.R3;
        if (i2 == objArr.length) {
            Object[] objArr2 = new Object[i2 * 2];
            int[] iArr = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(objArr, 0, objArr2, 0, i2);
            System.arraycopy(this.U3, 0, iArr, 0, this.S3);
            System.arraycopy(this.T3, 0, strArr, 0, this.S3);
            this.R3 = objArr2;
            this.U3 = iArr;
            this.T3 = strArr;
        }
        Object[] objArr3 = this.R3;
        int i3 = this.S3;
        this.S3 = i3 + 1;
        objArr3[i3] = obj;
    }

    @Override // c.b.a.v.a
    public void s() throws IOException {
        n0(JsonToken.BEGIN_OBJECT);
        r0(((c.b.a.m) o0()).j().iterator());
    }

    @Override // c.b.a.v.a
    public String toString() {
        return e.class.getSimpleName();
    }
}
