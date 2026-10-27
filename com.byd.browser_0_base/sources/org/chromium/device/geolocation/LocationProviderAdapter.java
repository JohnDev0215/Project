package org.chromium.device.geolocation;

import J.N;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import defpackage.aa1;
import defpackage.fa1;
import defpackage.xz;
import java.util.List;
import java.util.concurrent.FutureTask;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class LocationProviderAdapter {
    public final aa1 a;

    public class a implements Runnable {
        public final /* synthetic */ boolean e;

        public a(boolean z) {
            this.e = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            aa1 aa1Var = LocationProviderAdapter.this.a;
            aa1Var.getClass();
            Object obj = ThreadUtils.a;
            aa1Var.a();
            if (aa1Var.a == null) {
                LocationManager locationManager = (LocationManager) xz.a.getSystemService("location");
                aa1Var.a = locationManager;
                if (locationManager == null) {
                    Log.e("cr_LocationProvider", "Could not get location manager.");
                }
            }
            List<String> providers = aa1Var.a.getProviders(true);
            boolean z = false;
            if (providers != null && providers.size() == 1 && providers.get(0).equals("passive")) {
                Location lastKnownLocation = aa1Var.a.getLastKnownLocation("passive");
                if (lastKnownLocation != null) {
                    LocationProviderAdapter.a(lastKnownLocation);
                }
                z = true;
            }
            if (z) {
                return;
            }
            aa1Var.b = true;
            try {
                Criteria criteria = new Criteria();
                Context context = xz.a;
                if (this.e && context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                    criteria.setAccuracy(1);
                }
                aa1Var.a.requestLocationUpdates(0L, 0.0f, criteria, aa1Var, ThreadUtils.d());
            } catch (IllegalArgumentException unused) {
                Log.e("cr_LocationProvider", "Caught IllegalArgumentException registering for location updates.");
                aa1Var.a();
            } catch (SecurityException unused2) {
                Log.e("cr_LocationProvider", "Caught security exception while registering for location updates from the system. The application does not have sufficient geolocation permissions.");
                aa1Var.a();
                fa1.b("LocationProvider", "newErrorAvailable %s", "application does not have sufficient geolocation permissions.");
                N.M8Iz7Ptw("application does not have sufficient geolocation permissions.");
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            aa1 aa1Var = LocationProviderAdapter.this.a;
            aa1Var.getClass();
            Object obj = ThreadUtils.a;
            aa1Var.a();
        }
    }

    public LocationProviderAdapter() {
        aa1 aa1Var = LocationProviderFactory.a;
        if (aa1Var == null) {
            aa1Var = new aa1();
            LocationProviderFactory.a = aa1Var;
        }
        this.a = aa1Var;
    }

    public static void a(Location location) {
        N.MvJnRjJi(location.getLatitude(), location.getLongitude(), location.getTime() / 1000.0d, location.hasAltitude(), location.getAltitude(), location.hasAccuracy(), location.getAccuracy(), location.hasBearing(), location.getBearing(), location.hasSpeed(), location.getSpeed());
    }

    @CalledByNative
    public static LocationProviderAdapter create() {
        return new LocationProviderAdapter();
    }

    @CalledByNative
    public void start(boolean z) {
        FutureTask futureTask = new FutureTask(new a(z), null);
        if (ThreadUtils.g()) {
            futureTask.run();
        } else {
            ThreadUtils.c().post(futureTask);
        }
    }

    @CalledByNative
    public void stop() {
        FutureTask futureTask = new FutureTask(new b(), null);
        if (ThreadUtils.g()) {
            futureTask.run();
        } else {
            ThreadUtils.c().post(futureTask);
        }
    }
}
