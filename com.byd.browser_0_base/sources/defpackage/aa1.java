package defpackage;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: compiled from: LocationProviderAndroid.java */
/* JADX INFO: loaded from: classes3.dex */
public final class aa1 implements LocationListener {
    public LocationManager a;
    public boolean b;

    public final void a() {
        if (this.b) {
            this.b = false;
            this.a.removeUpdates(this);
        }
    }

    @Override // android.location.LocationListener
    public final void onLocationChanged(Location location) {
        if (this.b) {
            LocationProviderAdapter.a(location);
        }
    }

    @Override // android.location.LocationListener
    public final void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
