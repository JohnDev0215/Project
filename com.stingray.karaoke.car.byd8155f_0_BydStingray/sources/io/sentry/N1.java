package io.sentry;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public final class N1 extends TimerTask {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ P1 f15907m;

    public /* synthetic */ N1(P1 p12, int i7) {
        this.l = i7;
        this.f15907m = p12;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                P1 p12 = this.f15907m;
                W1 w1S = p12.s();
                if (w1S == null) {
                    w1S = W1.OK;
                }
                p12.v(w1S, null);
                p12.f15918k.set(false);
                break;
            default:
                P1 p13 = this.f15907m;
                W1 w1S2 = p13.s();
                if (w1S2 == null) {
                    w1S2 = W1.DEADLINE_EXCEEDED;
                }
                p13.i(w1S2, p13.f15923r.f16485p != null, null);
                p13.l.set(false);
                break;
        }
    }
}
