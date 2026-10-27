package W2;

import B2.L;
import M.t;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public L f9741b;
    public T c;
    public g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f9742e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f9743f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f9744g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9745h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9746i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f9748k;
    public boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9749m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f9740a = new e();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public t f9747j = new t();

    public void a(long j3) {
        this.f9744g = j3;
    }

    public abstract long b(h2.t tVar);

    public abstract boolean c(h2.t tVar, long j3, t tVar2);

    public void d(boolean z6) {
        if (z6) {
            this.f9747j = new t();
            this.f9743f = 0L;
            this.f9745h = 0;
        } else {
            this.f9745h = 1;
        }
        this.f9742e = -1L;
        this.f9744g = 0L;
    }
}
