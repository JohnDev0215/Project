package defpackage;

import J.N;
import android.os.SystemClock;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class uq0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;

    public /* synthetic */ uq0(int i, long j) {
        this.e = i;
        this.f = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j = this.f;
        switch (this.e) {
            case 0:
                oj2.T("Media.VideoPersistence.Duration", SystemClock.elapsedRealtime() - j, 7000L, 36000000L, 50);
                break;
            case 1:
                N.M4qSX4lZ(j);
                break;
            default:
                N.MTN9MD0o(null, 0L, false, j);
                break;
        }
    }
}
