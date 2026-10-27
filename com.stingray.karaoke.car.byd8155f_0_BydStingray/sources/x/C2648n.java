package x;

import T.C0735d;
import T.C0738e0;
import T.Q0;

/* JADX INFO: renamed from: x.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2648n implements Q0 {
    public final D0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0738e0 f23569m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC2652s f23570n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f23571p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f23572q;

    public /* synthetic */ C2648n(D0 d02, Object obj, AbstractC2652s abstractC2652s, int i7) {
        this(d02, obj, (i7 & 4) != 0 ? null : abstractC2652s, Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    @Override // T.Q0
    public final Object getValue() {
        return this.f23569m.getValue();
    }

    public final String toString() {
        return "AnimationState(value=" + this.f23569m.getValue() + ", velocity=" + this.l.f23395b.b(this.f23570n) + ", isRunning=" + this.f23572q + ", lastFrameTimeNanos=" + this.o + ", finishedTimeNanos=" + this.f23571p + ')';
    }

    public C2648n(D0 d02, Object obj, AbstractC2652s abstractC2652s, long j3, long j10, boolean z6) {
        AbstractC2652s abstractC2652sI;
        this.l = d02;
        this.f23569m = C0735d.O(obj, T.S.f8224q);
        if (abstractC2652s != null) {
            abstractC2652sI = AbstractC2631e.i(abstractC2652s);
        } else {
            abstractC2652sI = (AbstractC2652s) d02.f23394a.b(obj);
            abstractC2652sI.d();
        }
        this.f23570n = abstractC2652sI;
        this.o = j3;
        this.f23571p = j10;
        this.f23572q = z6;
    }
}
