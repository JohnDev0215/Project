package m2;

/* JADX INFO: compiled from: PreserveAspectRatio.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f8115c = new e(null, null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f8116d = new e(a.none, null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f8117e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f8118f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f8119g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f8120h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f8121i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f8122j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e f8123k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f8124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f8125b;

    /* JADX INFO: compiled from: PreserveAspectRatio.java */
    public enum a {
        none,
        xMinYMin,
        xMidYMin,
        xMaxYMin,
        xMinYMid,
        xMidYMid,
        xMaxYMid,
        xMinYMax,
        xMidYMax,
        xMaxYMax
    }

    /* JADX INFO: compiled from: PreserveAspectRatio.java */
    public enum b {
        meet,
        slice
    }

    static {
        a aVar = a.xMidYMid;
        b bVar = b.meet;
        f8117e = new e(aVar, bVar);
        a aVar2 = a.xMinYMin;
        f8118f = new e(aVar2, bVar);
        f8119g = new e(a.xMaxYMax, bVar);
        f8120h = new e(a.xMidYMin, bVar);
        f8121i = new e(a.xMidYMax, bVar);
        b bVar2 = b.slice;
        f8122j = new e(aVar, bVar2);
        f8123k = new e(aVar2, bVar2);
    }

    e(a aVar, b bVar) {
        this.f8124a = aVar;
        this.f8125b = bVar;
    }

    public a a() {
        return this.f8124a;
    }

    public b b() {
        return this.f8125b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        return this.f8124a == eVar.f8124a && this.f8125b == eVar.f8125b;
    }

    public String toString() {
        return this.f8124a + " " + this.f8125b;
    }
}
