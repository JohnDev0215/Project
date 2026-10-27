package p;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class m52 implements sh2, rh2 {
    public static final TreeMap q = new TreeMap();
    public final int c;
    public volatile String e;
    public final long[] k;
    public final double[] l;
    public final String[] m;
    public final byte[][] n;
    public final int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f154p;

    public m52(int i) {
        this.c = i;
        int i2 = i + 1;
        this.o = new int[i2];
        this.k = new long[i2];
        this.l = new double[i2];
        this.m = new String[i2];
        this.n = new byte[i2][];
    }

    public static final m52 i(int i, String str) {
        v03.j(str, "query");
        TreeMap treeMap = q;
        synchronized (treeMap) {
            Map.Entry entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (entryCeilingEntry == null) {
                m52 m52Var = new m52(i);
                m52Var.e = str;
                m52Var.f154p = i;
                return m52Var;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            m52 m52Var2 = (m52) entryCeilingEntry.getValue();
            m52Var2.getClass();
            m52Var2.e = str;
            m52Var2.f154p = i;
            return m52Var2;
        }
    }

    @Override // p.rh2
    public final void B0(byte[] bArr, int i) {
        v03.j(bArr, "value");
        this.o[i] = 5;
        this.n[i] = bArr;
    }

    @Override // p.rh2
    public final void K(int i) {
        this.o[i] = 1;
    }

    @Override // p.sh2
    public final void b(hz1 hz1Var) {
        int i = this.f154p;
        if (1 > i) {
            return;
        }
        int i2 = 1;
        while (true) {
            int i3 = this.o[i2];
            if (i3 == 1) {
                hz1Var.K(i2);
            } else if (i3 == 2) {
                hz1Var.h0(i2, this.k[i2]);
            } else if (i3 == 3) {
                hz1Var.b(i2, this.l[i2]);
            } else if (i3 == 4) {
                String str = this.m[i2];
                if (str == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                hz1Var.x(i2, str);
            } else if (i3 == 5) {
                byte[] bArr = this.n[i2];
                if (bArr == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                hz1Var.B0(bArr, i2);
            }
            if (i2 == i) {
                return;
            } else {
                i2++;
            }
        }
    }

    @Override // p.sh2
    public final String c() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.rh2
    public final void h0(int i, long j) {
        this.o[i] = 2;
        this.k[i] = j;
    }

    public final void q() {
        TreeMap treeMap = q;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.c), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator it = treeMap.descendingKeySet().iterator();
                v03.i(it, "queryPool.descendingKeySet().iterator()");
                while (true) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                    size = i;
                }
            }
        }
    }

    @Override // p.rh2
    public final void x(int i, String str) {
        v03.j(str, "value");
        this.o[i] = 4;
        this.m[i] = str;
    }
}
