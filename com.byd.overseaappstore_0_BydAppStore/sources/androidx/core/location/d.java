package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ d(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((LocationManagerCompat.GpsStatusTransport) this.b).lambda$onGpsStatusChanged$0((Executor) this.c);
                break;
            default:
                ((LocationManagerCompat.LocationListenerTransport) this.b).lambda$onLocationChanged$1((List) this.c);
                break;
        }
    }
}
