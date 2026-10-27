package x2;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f23729a;

    public final void a(int i7) {
        h hVar = this.f23729a;
        synchronized (hVar) {
            int i10 = hVar.f23748m;
            if (i10 == 0 || hVar.d) {
                if (i10 == i7) {
                    return;
                }
                hVar.f23748m = i7;
                if (i7 != 1 && i7 != 0 && i7 != 8) {
                    hVar.f23747k = hVar.b(i7);
                    hVar.c.getClass();
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    hVar.c(hVar.f23742f > 0 ? (int) (jElapsedRealtime - hVar.f23743g) : 0, hVar.f23744h, hVar.f23747k);
                    hVar.f23743g = jElapsedRealtime;
                    hVar.f23744h = 0L;
                    hVar.f23746j = 0L;
                    hVar.f23745i = 0L;
                    p pVar = hVar.f23741e;
                    pVar.f23764b.clear();
                    pVar.d = -1;
                    pVar.f23765e = 0;
                    pVar.f23766f = 0;
                }
            }
        }
    }
}
