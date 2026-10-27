package androidx.core.location;

import android.location.Location;
import androidx.core.location.LocationManagerCompat;
import androidx.core.util.Consumer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ Location b;
    public final /* synthetic */ Object c;

    public /* synthetic */ b(Object obj, Location location, int i) {
        this.a = i;
        this.c = obj;
        this.b = location;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                ((Consumer) this.c).accept(this.b);
                break;
            default:
                ((LocationManagerCompat.LocationListenerTransport) this.c).lambda$onLocationChanged$0(this.b);
                break;
        }
    }
}
