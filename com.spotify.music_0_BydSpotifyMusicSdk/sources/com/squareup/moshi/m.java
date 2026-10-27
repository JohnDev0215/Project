package com.squareup.moshi;

import java.io.Closeable;
import java.io.Flushable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p.io;

/* JADX INFO: loaded from: classes.dex */
public abstract class m implements Closeable, Flushable {
    String m;
    boolean n;
    boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    boolean f70p;
    int c = 0;
    int[] e = new int[32];
    String[] k = new String[32];
    int[] l = new int[32];
    int q = -1;

    public static m L0(io ioVar) {
        return new i(ioVar);
    }

    public abstract m E();

    public final String E0() {
        return g.a(this.c, this.e, this.k, this.l);
    }

    public final boolean G0() {
        return this.o;
    }

    public final boolean H0() {
        return this.n;
    }

    public final m I0(Object obj) {
        if (obj instanceof Map) {
            i();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (!(key instanceof String)) {
                    throw new IllegalArgumentException(key == null ? "Map keys must be non-null" : "Map keys must be of type String: ".concat(key.getClass().getName()));
                }
                J0((String) key);
                I0(entry.getValue());
            }
            X();
        } else if (obj instanceof List) {
            b();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                I0(it.next());
            }
            E();
        } else if (obj instanceof String) {
            W0((String) obj);
        } else if (obj instanceof Boolean) {
            X0(((Boolean) obj).booleanValue());
        } else if (obj instanceof Double) {
            T0(((Double) obj).doubleValue());
        } else if (obj instanceof Long) {
            U0(((Long) obj).longValue());
        } else if (obj instanceof Number) {
            V0((Number) obj);
        } else {
            if (obj != null) {
                throw new IllegalArgumentException("Unsupported type: ".concat(obj.getClass().getName()));
            }
            K0();
        }
        return this;
    }

    public final void J(int i) {
        this.q = i;
    }

    public abstract m J0(String str);

    public abstract m K0();

    public final int M0() {
        int i = this.c;
        if (i != 0) {
            return this.e[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void N0() {
        int iM0 = M0();
        if (iM0 != 5 && iM0 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        this.f70p = true;
    }

    public final void O0(int i) {
        int[] iArr = this.e;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr[i2] = i;
    }

    public final void P0(int i) {
        this.e[this.c - 1] = i;
    }

    public void Q0(String str) {
        if (str.isEmpty()) {
            str = null;
        }
        this.m = str;
    }

    public final void R0(boolean z) {
        this.n = z;
    }

    public final void S0(boolean z) {
        this.o = z;
    }

    public abstract m T0(double d);

    public abstract m U0(long j);

    public abstract m V0(Number number);

    public abstract m W0(String str);

    public abstract m X();

    public abstract m X0(boolean z);

    public abstract m b();

    public final int c() {
        int iM0 = M0();
        if (iM0 != 5 && iM0 != 3 && iM0 != 2 && iM0 != 1) {
            throw new IllegalStateException("Nesting problem.");
        }
        int i = this.q;
        this.q = this.c;
        return i;
    }

    public abstract m i();

    public final String o0() {
        String str = this.m;
        return str != null ? str : "";
    }

    public final boolean q() {
        int i = this.c;
        int[] iArr = this.e;
        if (i != iArr.length) {
            return false;
        }
        if (i == 256) {
            throw new JsonDataException("Nesting too deep at " + E0() + ": circular reference?");
        }
        this.e = Arrays.copyOf(iArr, iArr.length * 2);
        String[] strArr = this.k;
        this.k = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
        int[] iArr2 = this.l;
        this.l = Arrays.copyOf(iArr2, iArr2.length * 2);
        if (!(this instanceof l)) {
            return true;
        }
        l lVar = (l) this;
        Object[] objArr = lVar.r;
        lVar.r = Arrays.copyOf(objArr, objArr.length * 2);
        return true;
    }
}
