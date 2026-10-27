package androidx.room;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: RoomSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements b.m.a.e, b.m.a.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final TreeMap<Integer, l> f1357b = new TreeMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile String f1358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final long[] f1359d;
    final double[] e;
    final String[] f;
    final byte[][] g;
    private final int[] h;
    final int i;
    int j;

    private l(int i) {
        this.i = i;
        int i2 = i + 1;
        this.h = new int[i2];
        this.f1359d = new long[i2];
        this.e = new double[i2];
        this.f = new String[i2];
        this.g = new byte[i2][];
    }

    public static l v(String str, int i) {
        TreeMap<Integer, l> treeMap = f1357b;
        synchronized (treeMap) {
            Map.Entry<Integer, l> entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (entryCeilingEntry == null) {
                l lVar = new l(i);
                lVar.w(str, i);
                return lVar;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            l value = entryCeilingEntry.getValue();
            value.w(str, i);
            return value;
        }
    }

    private static void x() {
        TreeMap<Integer, l> treeMap = f1357b;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator<Integer> it = treeMap.descendingKeySet().iterator();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = i;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // b.m.a.d
    public void f(int i, String str) {
        this.h[i] = 4;
        this.f[i] = str;
    }

    @Override // b.m.a.d
    public void g(int i, long j) {
        this.h[i] = 2;
        this.f1359d[i] = j;
    }

    @Override // b.m.a.d
    public void l(int i, byte[] bArr) {
        this.h[i] = 5;
        this.g[i] = bArr;
    }

    @Override // b.m.a.d
    public void m(int i) {
        this.h[i] = 1;
    }

    @Override // b.m.a.d
    public void n(int i, double d2) {
        this.h[i] = 3;
        this.e[i] = d2;
    }

    @Override // b.m.a.e
    public String o() {
        return this.f1358c;
    }

    @Override // b.m.a.e
    public void u(b.m.a.d dVar) {
        for (int i = 1; i <= this.j; i++) {
            int i2 = this.h[i];
            if (i2 == 1) {
                dVar.m(i);
            } else if (i2 == 2) {
                dVar.g(i, this.f1359d[i]);
            } else if (i2 == 3) {
                dVar.n(i, this.e[i]);
            } else if (i2 == 4) {
                dVar.f(i, this.f[i]);
            } else if (i2 == 5) {
                dVar.l(i, this.g[i]);
            }
        }
    }

    void w(String str, int i) {
        this.f1358c = str;
        this.j = i;
    }

    public void y() {
        TreeMap<Integer, l> treeMap = f1357b;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.i), this);
            x();
        }
    }
}
