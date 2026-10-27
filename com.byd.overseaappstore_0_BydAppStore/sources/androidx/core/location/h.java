package androidx.core.location;

import androidx.core.location.LocationManagerCompat;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport b;
    public final /* synthetic */ String c;

    public /* synthetic */ h(LocationManagerCompat.LocationListenerTransport locationListenerTransport, String str, int i) {
        this.a = i;
        this.b = locationListenerTransport;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$onProviderEnabled$4(this.c);
                break;
            default:
                this.b.lambda$onProviderDisabled$5(this.c);
                break;
        }
    }
}
