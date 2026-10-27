package V8;

import S8.C0712i;

/* JADX INFO: loaded from: classes.dex */
public final class P implements S8.F {
    public final S l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f9578m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f9579n;
    public final C0712i o;

    public P(S s6, long j3, Object obj, C0712i c0712i) {
        this.l = s6;
        this.f9578m = j3;
        this.f9579n = obj;
        this.o = c0712i;
    }

    @Override // S8.F
    public final void a() {
        S s6 = this.l;
        synchronized (s6) {
            if (this.f9578m < s6.q()) {
                return;
            }
            Object[] objArr = s6.f9588s;
            A7.m.c(objArr);
            long j3 = this.f9578m;
            if (objArr[((int) j3) & (objArr.length - 1)] != this) {
                return;
            }
            AbstractC0828q.e(objArr, j3, AbstractC0828q.f9646b);
            s6.l();
        }
    }
}
