package d1;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: RoomSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements h1.e, h1.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final TreeMap<Integer, l> f4405d = new TreeMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile String f4406e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long[] f4407f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final double[] f4408g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String[] f4409h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final byte[][] f4410i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f4411j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f4412k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4413l;

    public l(int i8) {
        this.f4412k = i8;
        int i9 = i8 + 1;
        this.f4411j = new int[i9];
        this.f4407f = new long[i9];
        this.f4408g = new double[i9];
        this.f4409h = new String[i9];
        this.f4410i = new byte[i9][];
    }

    public static l i(String str, int i8) {
        TreeMap<Integer, l> treeMap = f4405d;
        synchronized (treeMap) {
            Map.Entry<Integer, l> entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i8));
            if (entryCeilingEntry == null) {
                l lVar = new l(i8);
                lVar.j(str, i8);
                return lVar;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            l value = entryCeilingEntry.getValue();
            value.j(str, i8);
            return value;
        }
    }

    public static void m() {
        TreeMap<Integer, l> treeMap = f4405d;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator<Integer> it = treeMap.descendingKeySet().iterator();
        while (true) {
            int i8 = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = i8;
        }
    }

    @Override // h1.d
    public void A(int i8, double d8) {
        this.f4411j[i8] = 3;
        this.f4408g[i8] = d8;
    }

    @Override // h1.d
    public void U(int i8, long j8) {
        this.f4411j[i8] = 2;
        this.f4407f[i8] = j8;
    }

    @Override // h1.d
    public void b0(int i8, byte[] bArr) {
        this.f4411j[i8] = 5;
        this.f4410i[i8] = bArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // h1.e
    public String e() {
        return this.f4406e;
    }

    @Override // h1.e
    public void g(h1.d dVar) {
        for (int i8 = 1; i8 <= this.f4413l; i8++) {
            int i9 = this.f4411j[i8];
            if (i9 == 1) {
                dVar.z(i8);
            } else if (i9 == 2) {
                dVar.U(i8, this.f4407f[i8]);
            } else if (i9 == 3) {
                dVar.A(i8, this.f4408g[i8]);
            } else if (i9 == 4) {
                dVar.l(i8, this.f4409h[i8]);
            } else if (i9 == 5) {
                dVar.b0(i8, this.f4410i[i8]);
            }
        }
    }

    public void j(String str, int i8) {
        this.f4406e = str;
        this.f4413l = i8;
    }

    @Override // h1.d
    public void l(int i8, String str) {
        this.f4411j[i8] = 4;
        this.f4409h[i8] = str;
    }

    public void n() {
        TreeMap<Integer, l> treeMap = f4405d;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.f4412k), this);
            m();
        }
    }

    @Override // h1.d
    public void z(int i8) {
        this.f4411j[i8] = 1;
    }
}
