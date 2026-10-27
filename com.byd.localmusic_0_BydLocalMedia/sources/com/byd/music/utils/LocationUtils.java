package com.byd.music.utils;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import com.blankj.utilcode.util.Utils;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class LocationUtils {
    private static volatile LocationUtils INSTANCE = null;
    private static final String TAG = "zzl";
    private Address address;
    public String adminArea;
    public String locality;
    private Location location;
    private String provider;
    public double lat = 0.0d;
    public double lng = 0.0d;
    private LocationManager locationManager = (LocationManager) Utils.getApp().getSystemService("location");

    public static LocationUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (LocationUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LocationUtils();
                }
            }
        }
        return INSTANCE;
    }

    private LocationUtils() {
        getProvider();
        updateWithNewLocation();
    }

    private void getProvider() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(1);
        this.provider = this.locationManager.getBestProvider(criteria, true);
        LogUtil.e(TAG, "getProvider: leave with provider =  " + this.provider);
    }

    private List<Address> getAddressbyGeoPoint() {
        LogUtil.e(TAG, "getAddressbyGeoPoint: enter");
        List<Address> fromLocation = null;
        try {
            if (this.location == null) {
                return null;
            }
            fromLocation = new Geocoder(Utils.getApp(), Locale.getDefault()).getFromLocation(this.location.getLatitude(), this.location.getLongitude(), 1);
            LogUtil.e(TAG, "getAddressbyGeoPoint: location != null");
            return fromLocation;
        } catch (Exception e) {
            e.printStackTrace();
            return fromLocation;
        }
    }

    public void updateWithNewLocation() {
        String str = this.provider;
        if (str == null || str.isEmpty()) {
            LogUtil.e(TAG, "updateWithNewLocation: provider is null...");
            return;
        }
        this.location = this.locationManager.getLastKnownLocation(this.provider);
        StringBuilder sb = new StringBuilder();
        sb.append("updateWithNewLocation: location == null ? >> ");
        sb.append(this.location == null);
        LogUtil.e(TAG, sb.toString());
        Location location = this.location;
        if (location != null) {
            this.lat = location.getLatitude();
            this.lng = this.location.getLongitude();
            LogUtil.e(TAG, "updateWithNewLocation: 纬度 = " + this.lat);
            LogUtil.e(TAG, "updateWithNewLocation: 经度 = " + this.lng);
        }
        List<Address> addressbyGeoPoint = getAddressbyGeoPoint();
        if (addressbyGeoPoint == null || addressbyGeoPoint.size() != 1) {
            return;
        }
        this.adminArea = addressbyGeoPoint.get(0).getAdminArea();
        this.locality = addressbyGeoPoint.get(0).getLocality();
        LogUtil.e(TAG, "updateWithNewLocation: 省：" + addressbyGeoPoint.get(0).getAdminArea());
        LogUtil.e(TAG, "updateWithNewLocation: 市：" + addressbyGeoPoint.get(0).getLocality());
    }
}
