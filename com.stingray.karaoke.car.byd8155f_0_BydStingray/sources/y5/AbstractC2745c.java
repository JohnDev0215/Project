package y5;

/* JADX INFO: renamed from: y5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2745c implements Comparable {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static int f24125n = -1;
    public static int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static int f24126p = -1;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final A4.b f24127m;

    public AbstractC2745c(long j3, long j10) {
        this.l = j3;
        this.f24127m = new A4.b((int) j10);
    }

    public String toString() {
        return "" + this.l + " (" + this.f24127m.f413b + "): " + getClass().getSimpleName();
    }
}
