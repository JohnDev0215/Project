package com.google.gson.internal.bind;

import com.google.gson.g;
import com.google.gson.j;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.o;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: JsonTreeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends com.google.gson.w.a {
    private static final Reader r = new C0084a();
    private static final Object s = new Object();
    private Object[] t;
    private int u;
    private String[] v;
    private int[] w;

    /* JADX INFO: renamed from: com.google.gson.internal.bind.a$a */
    /* JADX INFO: compiled from: JsonTreeReader.java */
    static class C0084a extends Reader {
        C0084a() {
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i2, int i3) {
            throw new AssertionError();
        }
    }

    public a(j jVar) {
        super(r);
        this.t = new Object[32];
        this.u = 0;
        this.v = new String[32];
        this.w = new int[32];
        z0(jVar);
    }

    private String Y() {
        return " at path " + U();
    }

    private void v0(com.google.gson.w.b bVar) {
        if (j0() == bVar) {
            return;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + j0() + Y());
    }

    private Object w0() {
        return this.t[this.u - 1];
    }

    private Object x0() {
        Object[] objArr = this.t;
        int i2 = this.u - 1;
        this.u = i2;
        Object obj = objArr[i2];
        objArr[i2] = null;
        return obj;
    }

    private void z0(Object obj) {
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

    @Override // com.google.gson.w.a
    public void R() {
        v0(com.google.gson.w.b.END_ARRAY);
        x0();
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.w.a
    public void S() {
        v0(com.google.gson.w.b.END_OBJECT);
        x0();
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.w.a
    public String U() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = 0;
        while (i2 < this.u) {
            Object[] objArr = this.t;
            if (objArr[i2] instanceof g) {
                i2++;
                if (objArr[i2] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.w[i2]);
                    sb.append(']');
                }
            } else if (objArr[i2] instanceof m) {
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

    @Override // com.google.gson.w.a
    public boolean V() {
        com.google.gson.w.b bVarJ0 = j0();
        return (bVarJ0 == com.google.gson.w.b.END_OBJECT || bVarJ0 == com.google.gson.w.b.END_ARRAY) ? false : true;
    }

    @Override // com.google.gson.w.a
    public boolean Z() {
        v0(com.google.gson.w.b.BOOLEAN);
        boolean zA = ((o) x0()).a();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return zA;
    }

    @Override // com.google.gson.w.a
    public void a() {
        v0(com.google.gson.w.b.BEGIN_ARRAY);
        z0(((g) w0()).iterator());
        this.w[this.u - 1] = 0;
    }

    @Override // com.google.gson.w.a
    public double a0() {
        com.google.gson.w.b bVarJ0 = j0();
        com.google.gson.w.b bVar = com.google.gson.w.b.NUMBER;
        if (bVarJ0 != bVar && bVarJ0 != com.google.gson.w.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarJ0 + Y());
        }
        double dB = ((o) w0()).b();
        if (!W() && (Double.isNaN(dB) || Double.isInfinite(dB))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + dB);
        }
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return dB;
    }

    @Override // com.google.gson.w.a
    public int b0() {
        com.google.gson.w.b bVarJ0 = j0();
        com.google.gson.w.b bVar = com.google.gson.w.b.NUMBER;
        if (bVarJ0 != bVar && bVarJ0 != com.google.gson.w.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarJ0 + Y());
        }
        int iC = ((o) w0()).c();
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return iC;
    }

    @Override // com.google.gson.w.a
    public long c0() {
        com.google.gson.w.b bVarJ0 = j0();
        com.google.gson.w.b bVar = com.google.gson.w.b.NUMBER;
        if (bVarJ0 != bVar && bVarJ0 != com.google.gson.w.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarJ0 + Y());
        }
        long jG = ((o) w0()).g();
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return jG;
    }

    @Override // com.google.gson.w.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.t = new Object[]{s};
        this.u = 1;
    }

    @Override // com.google.gson.w.a
    public void d() {
        v0(com.google.gson.w.b.BEGIN_OBJECT);
        z0(((m) w0()).n().iterator());
    }

    @Override // com.google.gson.w.a
    public String d0() {
        v0(com.google.gson.w.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) w0()).next();
        String str = (String) entry.getKey();
        this.v[this.u - 1] = str;
        z0(entry.getValue());
        return str;
    }

    @Override // com.google.gson.w.a
    public void f0() {
        v0(com.google.gson.w.b.NULL);
        x0();
        int i2 = this.u;
        if (i2 > 0) {
            int[] iArr = this.w;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // com.google.gson.w.a
    public String h0() {
        com.google.gson.w.b bVarJ0 = j0();
        com.google.gson.w.b bVar = com.google.gson.w.b.STRING;
        if (bVarJ0 == bVar || bVarJ0 == com.google.gson.w.b.NUMBER) {
            String strH = ((o) x0()).h();
            int i2 = this.u;
            if (i2 > 0) {
                int[] iArr = this.w;
                int i3 = i2 - 1;
                iArr[i3] = iArr[i3] + 1;
            }
            return strH;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + bVarJ0 + Y());
    }

    @Override // com.google.gson.w.a
    public com.google.gson.w.b j0() {
        if (this.u == 0) {
            return com.google.gson.w.b.END_DOCUMENT;
        }
        Object objW0 = w0();
        if (objW0 instanceof Iterator) {
            boolean z = this.t[this.u - 2] instanceof m;
            Iterator it = (Iterator) objW0;
            if (!it.hasNext()) {
                return z ? com.google.gson.w.b.END_OBJECT : com.google.gson.w.b.END_ARRAY;
            }
            if (z) {
                return com.google.gson.w.b.NAME;
            }
            z0(it.next());
            return j0();
        }
        if (objW0 instanceof m) {
            return com.google.gson.w.b.BEGIN_OBJECT;
        }
        if (objW0 instanceof g) {
            return com.google.gson.w.b.BEGIN_ARRAY;
        }
        if (!(objW0 instanceof o)) {
            if (objW0 instanceof l) {
                return com.google.gson.w.b.NULL;
            }
            if (objW0 == s) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        o oVar = (o) objW0;
        if (oVar.s()) {
            return com.google.gson.w.b.STRING;
        }
        if (oVar.o()) {
            return com.google.gson.w.b.BOOLEAN;
        }
        if (oVar.q()) {
            return com.google.gson.w.b.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // com.google.gson.w.a
    public void t0() {
        if (j0() == com.google.gson.w.b.NAME) {
            d0();
            this.v[this.u - 2] = "null";
        } else {
            x0();
            int i2 = this.u;
            if (i2 > 0) {
                this.v[i2 - 1] = "null";
            }
        }
        int i3 = this.u;
        if (i3 > 0) {
            int[] iArr = this.w;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
        }
    }

    @Override // com.google.gson.w.a
    public String toString() {
        return a.class.getSimpleName();
    }

    public void y0() {
        v0(com.google.gson.w.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) w0()).next();
        z0(entry.getValue());
        z0(new o((String) entry.getKey()));
    }
}
