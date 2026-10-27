package y0;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: RoomSQLiteQuery.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b1.e, b1.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    static final TreeMap<Integer, i> f9947n = new TreeMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile String f9948e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final long[] f9949g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final double[] f9950h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final String[] f9951i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final byte[][] f9952j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int[] f9953k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final int f9954l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    int f9955m;

    private i(int i7) {
        this.f9954l = i7;
        int i8 = i7 + 1;
        this.f9953k = new int[i8];
        this.f9949g = new long[i8];
        this.f9950h = new double[i8];
        this.f9951i = new String[i8];
        this.f9952j = new byte[i8][];
    }

    public static i A(String str, int i7) {
        TreeMap<Integer, i> treeMap = f9947n;
        synchronized (treeMap) {
            Map.Entry<Integer, i> entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i7));
            if (entryCeilingEntry == null) {
                i iVar = new i(i7);
                iVar.G(str, i7);
                return iVar;
            }
            treeMap.remove(entryCeilingEntry.getKey());
            i value = entryCeilingEntry.getValue();
            value.G(str, i7);
            return value;
        }
    }

    private static void I() {
        TreeMap<Integer, i> treeMap = f9947n;
        if (treeMap.size() <= 15) {
            return;
        }
        int size = treeMap.size() - 10;
        Iterator<Integer> it = treeMap.descendingKeySet().iterator();
        while (true) {
            int i7 = size - 1;
            if (size <= 0) {
                return;
            }
            it.next();
            it.remove();
            size = i7;
        }
    }

    @Override // b1.d
    public void B(int i7, byte[] bArr) {
        this.f9953k[i7] = 5;
        this.f9952j[i7] = bArr;
    }

    @Override // b1.d
    public void C(int i7) {
        this.f9953k[i7] = 1;
    }

    @Override // b1.d
    public void E(int i7, double d7) {
        this.f9953k[i7] = 3;
        this.f9950h[i7] = d7;
    }

    void G(String str, int i7) {
        this.f9948e = str;
        this.f9955m = i7;
    }

    public void K() {
        TreeMap<Integer, i> treeMap = f9947n;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.f9954l), this);
            I();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // b1.e
    public void m(b1.d dVar) {
        for (int i7 = 1; i7 <= this.f9955m; i7++) {
            int i8 = this.f9953k[i7];
            if (i8 == 1) {
                dVar.C(i7);
            } else if (i8 == 2) {
                dVar.r(i7, this.f9949g[i7]);
            } else if (i8 == 3) {
                dVar.E(i7, this.f9950h[i7]);
            } else if (i8 == 4) {
                dVar.q(i7, this.f9951i[i7]);
            } else if (i8 == 5) {
                dVar.B(i7, this.f9952j[i7]);
            }
        }
    }

    @Override // b1.d
    public void q(int i7, String str) {
        this.f9953k[i7] = 4;
        this.f9951i[i7] = str;
    }

    @Override // b1.d
    public void r(int i7, long j7) {
        this.f9953k[i7] = 2;
        this.f9949g[i7] = j7;
    }

    @Override // b1.e
    public String z() {
        return this.f9948e;
    }
}
