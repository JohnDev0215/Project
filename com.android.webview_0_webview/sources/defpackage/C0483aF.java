package defpackage;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: renamed from: aF, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0483aF implements LocationListener, XE {
    public LocationManager a;
    public boolean b;

    @Override // defpackage.XE
    public void a(boolean z) {
        boolean z2;
        ThreadUtils.b();
        b();
        if (this.a == null) {
            LocationManager locationManager = (LocationManager) Is.a.getSystemService("location");
            this.a = locationManager;
            if (locationManager == null) {
                Ts.a("LocationProvider", "Could not get location manager.", new Object[0]);
            }
        }
        List<String> providers = this.a.getProviders(true);
        if (providers != null && providers.size() == 1 && providers.get(0).equals("passive")) {
            Location lastKnownLocation = this.a.getLastKnownLocation("passive");
            if (lastKnownLocation != null) {
                ThreadUtils.b();
                LocationProviderAdapter.b(lastKnownLocation);
            }
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return;
        }
        this.b = true;
        try {
            Criteria criteria = new Criteria();
            if (z) {
                criteria.setAccuracy(1);
            }
            this.a.requestLocationUpdates(0L, 0.0f, criteria, this, ThreadUtils.d());
        } catch (IllegalArgumentException unused) {
            Ts.a("LocationProvider", "Caught IllegalArgumentException registering for location updates.", new Object[0]);
            b();
        } catch (SecurityException unused2) {
            Ts.a("LocationProvider", "Caught security exception while registering for location updates from the system. The application does not have sufficient geolocation permissions.", new Object[0]);
            b();
            LocationProviderAdapter.a("application does not have sufficient geolocation permissions.");
        }
    }

    public final void b() {
        if (this.b) {
            this.b = false;
            this.a.removeUpdates(this);
        }
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (this.b) {
            LocationProviderAdapter.b(location);
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    @Override // defpackage.XE
    public void stop() {
        ThreadUtils.b();
        b();
    }
}
