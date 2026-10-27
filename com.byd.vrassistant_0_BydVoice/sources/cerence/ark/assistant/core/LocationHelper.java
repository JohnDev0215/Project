package cerence.ark.assistant.core;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class LocationHelper {
    private static final float DEFAULT_UPDATE_MIN_DISTANCE = 5.0f;
    private static final long DEFAULT_UPDATE_MIN_TIME = 10000;
    private static final String TAG = "LocationHelper";
    private static Location sCurrentLocation;
    private static LocationListener sLocationListener;
    private static LocationManager sLocationManager;
    private static OnLocationChangeListener sOnLocationChangeListener;

    public interface OnLocationChangeListener {
        void onLastKnownLocation(Location location);

        void onLocationChanged(Location location);
    }

    private LocationHelper() {
    }

    private static Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(2);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    public static boolean isGpsEnabled(Context context) {
        return ((LocationManager) context.getSystemService("location")).isProviderEnabled("gps");
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled("network") || locationManager.isProviderEnabled("gps");
    }

    public static boolean openGpsSettings(Context context) {
        try {
            context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS").addFlags(268435456));
            return true;
        } catch (Exception e) {
            Logger.error(TAG, "openGpsSettings: exception", e);
            return false;
        }
    }

    public static Address getAddress(Context context, double d, double d2) {
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.getDefault()).getFromLocation(d, d2, 1);
            if (fromLocation.size() > 0) {
                return fromLocation.get(0);
            }
            return null;
        } catch (IOException e) {
            Logger.error(TAG, "getAddress: exception", e);
            return null;
        }
    }

    public static String getCountryName(Context context, double d, double d2) {
        Address address = getAddress(context, d, d2);
        if (address == null) {
            return null;
        }
        return address.getCountryName();
    }

    public static String getLocality(Context context, double d, double d2) {
        Address address = getAddress(context, d, d2);
        if (address == null) {
            return null;
        }
        return address.getLocality();
    }

    public static String getStreet(Context context, double d, double d2) {
        Address address = getAddress(context, d, d2);
        if (address == null) {
            return null;
        }
        return address.getAddressLine(0);
    }

    public static Location getCurrentLocation() {
        return sCurrentLocation;
    }

    public static boolean requestLocationUpdates(Context context, long j, float f, OnLocationChangeListener onLocationChangeListener) {
        if (context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            Logger.error(TAG, "requestLocationUpdates: the android.permission.ACCESS_FINE_LOCATION was denied.");
            return false;
        }
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        sLocationManager = locationManager;
        if (!locationManager.isProviderEnabled("network") && !sLocationManager.isProviderEnabled("gps")) {
            Logger.error(TAG, "requestLocationUpdates: No location provider available");
            return false;
        }
        String bestProvider = sLocationManager.getBestProvider(getCriteria(), true);
        if (TextUtils.isEmpty(bestProvider)) {
            Logger.error(TAG, "requestLocationUpdates: provider is Empty");
            return false;
        }
        Logger.debug(TAG, "provider: " + bestProvider);
        sOnLocationChangeListener = onLocationChangeListener;
        Location lastKnownLocation = sLocationManager.getLastKnownLocation(bestProvider);
        if (lastKnownLocation != null) {
            sCurrentLocation = lastKnownLocation;
            if (onLocationChangeListener != null) {
                onLocationChangeListener.onLastKnownLocation(lastKnownLocation);
            }
        }
        if (sLocationListener == null) {
            sLocationListener = new LocationListener() { // from class: cerence.ark.assistant.core.LocationHelper.1
                @Override // android.location.LocationListener
                public void onLocationChanged(Location location) {
                    Location unused = LocationHelper.sCurrentLocation = location;
                    OnLocationChangeListener onLocationChangeListener2 = LocationHelper.sOnLocationChangeListener;
                    if (onLocationChangeListener2 != null) {
                        onLocationChangeListener2.onLocationChanged(location);
                    }
                }

                @Override // android.location.LocationListener
                public void onStatusChanged(String str, int i, Bundle bundle) {
                    Logger.debug(LocationHelper.TAG, String.format("onStatusChanged: provider = %s, status = %s", str, Integer.valueOf(i)));
                }

                @Override // android.location.LocationListener
                public void onProviderEnabled(String str) {
                    Logger.debug(LocationHelper.TAG, "onProviderEnabled: provider = " + str);
                }

                @Override // android.location.LocationListener
                public void onProviderDisabled(String str) {
                    Logger.error(LocationHelper.TAG, "onProviderDisabled: provider = " + str);
                }
            };
        }
        sLocationManager.requestLocationUpdates(bestProvider, j, f, sLocationListener);
        return true;
    }

    public static boolean requestLocationUpdates(Context context, OnLocationChangeListener onLocationChangeListener) {
        return requestLocationUpdates(context, DEFAULT_UPDATE_MIN_TIME, DEFAULT_UPDATE_MIN_DISTANCE, onLocationChangeListener);
    }

    public static void stopLocationUpdates() {
        LocationListener locationListener;
        LocationManager locationManager = sLocationManager;
        if (locationManager != null && (locationListener = sLocationListener) != null) {
            locationManager.removeUpdates(locationListener);
        }
        sLocationManager = null;
        sCurrentLocation = null;
        sLocationListener = null;
        sOnLocationChangeListener = null;
    }
}
