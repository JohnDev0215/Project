package org.chromium.content.browser;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import java.util.List;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;

/* JADX INFO: loaded from: classes3.dex */
public class LocationProviderFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static LocationProvider sProviderImpl;

    public interface LocationProvider {
        boolean isRunning();

        void start(boolean z);

        void stop();
    }

    private LocationProviderFactory() {
    }

    public static void setLocationProviderImpl(LocationProvider locationProvider) {
        sProviderImpl = locationProvider;
    }

    public static LocationProvider get(Context context) {
        if (sProviderImpl == null) {
            sProviderImpl = new LocationProviderImpl(context);
        }
        return sProviderImpl;
    }

    private static class LocationProviderImpl implements LocationListener, LocationProvider {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final String TAG = "cr.LocationProvider";
        private Context mContext;
        private boolean mIsRunning;
        private LocationManager mLocationManager;

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        LocationProviderImpl(Context context) {
            this.mContext = context;
        }

        @Override // org.chromium.content.browser.LocationProviderFactory.LocationProvider
        public void start(boolean z) {
            unregisterFromLocationUpdates();
            registerForLocationUpdates(z);
        }

        @Override // org.chromium.content.browser.LocationProviderFactory.LocationProvider
        public void stop() {
            unregisterFromLocationUpdates();
        }

        @Override // org.chromium.content.browser.LocationProviderFactory.LocationProvider
        public boolean isRunning() {
            return this.mIsRunning;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (this.mIsRunning) {
                updateNewLocation(location);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateNewLocation(Location location) {
            LocationProviderAdapter.newLocationAvailable(location.getLatitude(), location.getLongitude(), location.getTime() / 1000.0d, location.hasAltitude(), location.getAltitude(), location.hasAccuracy(), location.getAccuracy(), location.hasBearing(), location.getBearing(), location.hasSpeed(), location.getSpeed());
        }

        private void ensureLocationManagerCreated() {
            if (this.mLocationManager != null) {
                return;
            }
            LocationManager locationManager = (LocationManager) this.mContext.getSystemService("location");
            this.mLocationManager = locationManager;
            if (locationManager == null) {
                Log.e(TAG, "Could not get location manager.", new Object[0]);
            }
        }

        private void registerForLocationUpdates(boolean z) {
            ensureLocationManagerCreated();
            if (usePassiveOneShotLocation()) {
                return;
            }
            this.mIsRunning = true;
            try {
                Criteria criteria = new Criteria();
                if (z) {
                    criteria.setAccuracy(1);
                }
                this.mLocationManager.requestLocationUpdates(0L, 0.0f, criteria, this, ThreadUtils.getUiThreadLooper());
            } catch (IllegalArgumentException unused) {
                Log.e(TAG, "Caught IllegalArgumentException registering for location updates.", new Object[0]);
                unregisterFromLocationUpdates();
            } catch (SecurityException unused2) {
                Log.e(TAG, "Caught security exception while registering for location updates from the system. The application does not have sufficient geolocation permissions.", new Object[0]);
                unregisterFromLocationUpdates();
                LocationProviderAdapter.newErrorAvailable("application does not have sufficient geolocation permissions.");
            }
        }

        private void unregisterFromLocationUpdates() {
            if (this.mIsRunning) {
                this.mIsRunning = false;
                this.mLocationManager.removeUpdates(this);
            }
        }

        private boolean usePassiveOneShotLocation() {
            if (!isOnlyPassiveLocationProviderEnabled()) {
                return false;
            }
            final Location lastKnownLocation = this.mLocationManager.getLastKnownLocation("passive");
            if (lastKnownLocation == null) {
                return true;
            }
            ThreadUtils.runOnUiThread(new Runnable() { // from class: org.chromium.content.browser.LocationProviderFactory.LocationProviderImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    LocationProviderImpl.this.updateNewLocation(lastKnownLocation);
                }
            });
            return true;
        }

        private boolean isOnlyPassiveLocationProviderEnabled() {
            List<String> providers = this.mLocationManager.getProviders(true);
            return providers != null && providers.size() == 1 && providers.get(0).equals("passive");
        }
    }
}
