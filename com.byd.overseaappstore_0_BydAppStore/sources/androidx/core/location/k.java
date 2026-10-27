package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ LocationManagerCompat.PreRGnssStatusTransport b;
    public final /* synthetic */ Executor c;

    public /* synthetic */ k(LocationManagerCompat.PreRGnssStatusTransport preRGnssStatusTransport, Executor executor, int i) {
        this.a = i;
        this.b = preRGnssStatusTransport;
        this.c = executor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$onStarted$0(this.c);
                break;
            default:
                this.b.lambda$onStopped$1(this.c);
                break;
        }
    }
}
