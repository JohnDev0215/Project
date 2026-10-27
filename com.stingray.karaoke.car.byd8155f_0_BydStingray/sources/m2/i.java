package m2;

import r2.C2280b;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Thread {
    public final /* synthetic */ C2280b l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(C2280b c2280b) {
        super("ExoPlayer:SimpleDecoder");
        this.l = c2280b;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        do {
            try {
            } catch (InterruptedException e10) {
                throw new IllegalStateException(e10);
            }
        } while (this.l.i());
    }
}
