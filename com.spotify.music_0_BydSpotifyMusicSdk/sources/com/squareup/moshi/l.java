package com.squareup.moshi;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class l extends m {
    Object[] r = new Object[32];
    private String s;

    public l() {
        O0(6);
    }

    private l Y0(Object obj) {
        String str;
        Object objPut;
        int iM0 = M0();
        int i = this.c;
        if (i == 1) {
            if (iM0 != 6) {
                throw new IllegalStateException("JSON must have only one top-level value.");
            }
            this.e[i - 1] = 7;
            this.r[i - 1] = obj;
        } else if (iM0 != 3 || (str = this.s) == null) {
            if (iM0 != 1) {
                if (iM0 == 9) {
                    throw new IllegalStateException("Sink from valueSink() was not closed");
                }
                throw new IllegalStateException("Nesting problem.");
            }
            ((List) this.r[i - 1]).add(obj);
        } else {
            if ((obj != null || this.o) && (objPut = ((Map) this.r[i - 1]).put(str, obj)) != null) {
                throw new IllegalArgumentException("Map key '" + this.s + "' has multiple values at path " + E0() + ": " + objPut + " and " + obj);
            }
            this.s = null;
        }
        return this;
    }

    @Override // com.squareup.moshi.m
    public m E() {
        if (M0() != 1) {
            throw new IllegalStateException("Nesting problem.");
        }
        int i = this.c;
        int i2 = this.q;
        if (i == (~i2)) {
            this.q = ~i2;
            return this;
        }
        int i3 = i - 1;
        this.c = i3;
        this.r[i3] = null;
        int[] iArr = this.l;
        int i4 = i - 2;
        iArr[i4] = iArr[i4] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m J0(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        if (M0() != 3 || this.s != null || this.f70p) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.s = str;
        this.k[this.c - 1] = str;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m K0() {
        if (this.f70p) {
            throw new IllegalStateException("null cannot be used as a map key in JSON at path " + E0());
        }
        Y0(null);
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m T0(double d) {
        if (!this.n && (Double.isNaN(d) || d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        if (this.f70p) {
            this.f70p = false;
            return J0(Double.toString(d));
        }
        Y0(Double.valueOf(d));
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m U0(long j) {
        if (this.f70p) {
            this.f70p = false;
            return J0(Long.toString(j));
        }
        Y0(Long.valueOf(j));
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m V0(Number number) {
        if ((number instanceof Byte) || (number instanceof Short) || (number instanceof Integer) || (number instanceof Long)) {
            return U0(number.longValue());
        }
        if ((number instanceof Float) || (number instanceof Double)) {
            return T0(number.doubleValue());
        }
        if (number == null) {
            return K0();
        }
        BigDecimal bigDecimal = number instanceof BigDecimal ? (BigDecimal) number : new BigDecimal(number.toString());
        if (this.f70p) {
            this.f70p = false;
            return J0(bigDecimal.toString());
        }
        Y0(bigDecimal);
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m W0(String str) {
        if (this.f70p) {
            this.f70p = false;
            return J0(str);
        }
        Y0(str);
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m X() {
        if (M0() != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.s != null) {
            throw new IllegalStateException("Dangling name: " + this.s);
        }
        int i = this.c;
        int i2 = this.q;
        if (i == (~i2)) {
            this.q = ~i2;
            return this;
        }
        this.f70p = false;
        int i3 = i - 1;
        this.c = i3;
        this.r[i3] = null;
        this.k[i3] = null;
        int[] iArr = this.l;
        int i4 = i - 2;
        iArr[i4] = iArr[i4] + 1;
        return this;
    }

    @Override // com.squareup.moshi.m
    public m X0(boolean z) {
        if (this.f70p) {
            throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + E0());
        }
        Y0(Boolean.valueOf(z));
        int[] iArr = this.l;
        int i = this.c - 1;
        iArr[i] = iArr[i] + 1;
        return this;
    }

    public Object Z0() {
        int i = this.c;
        if (i > 1 || (i == 1 && this.e[i - 1] != 7)) {
            throw new IllegalStateException("Incomplete document");
        }
        return this.r[0];
    }

    @Override // com.squareup.moshi.m
    public m b() {
        if (this.f70p) {
            throw new IllegalStateException("Array cannot be used as a map key in JSON at path " + E0());
        }
        int i = this.c;
        int i2 = this.q;
        if (i == i2 && this.e[i - 1] == 1) {
            this.q = ~i2;
            return this;
        }
        q();
        ArrayList arrayList = new ArrayList();
        Y0(arrayList);
        Object[] objArr = this.r;
        int i3 = this.c;
        objArr[i3] = arrayList;
        this.l[i3] = 0;
        O0(1);
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        int i = this.c;
        if (i > 1 || (i == 1 && this.e[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.c = 0;
    }

    @Override // java.io.Flushable
    public void flush() {
        if (this.c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    @Override // com.squareup.moshi.m
    public m i() {
        if (this.f70p) {
            throw new IllegalStateException("Object cannot be used as a map key in JSON at path " + E0());
        }
        int i = this.c;
        int i2 = this.q;
        if (i == i2 && this.e[i - 1] == 3) {
            this.q = ~i2;
            return this;
        }
        q();
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        Y0(linkedHashTreeMap);
        this.r[this.c] = linkedHashTreeMap;
        O0(3);
        return this;
    }
}
