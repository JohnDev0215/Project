package com.android.location.fused;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.os.WorkSource;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.location.ProviderRequest;
import com.android.location.provider.LocationProviderBase;
import com.android.location.provider.LocationRequestUnbundled;
import com.android.location.provider.ProviderPropertiesUnbundled;
import com.android.location.provider.ProviderRequestUnbundled;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FusedLocationProvider extends LocationProviderBase {
    private static final ProviderPropertiesUnbundled PROPERTIES = ProviderPropertiesUnbundled.create(false, false, false, false, true, true, true, 1, 1);
    private final Context mContext;

    @GuardedBy({"mLock"})
    private Location mFusedLocation;

    @GuardedBy({"mLock"})
    private long mGpsInterval;
    private final LocationListener mGpsListener;

    @GuardedBy({"mLock"})
    private Location mGpsLocation;
    private final LocationManager mLocationManager;
    private final Object mLock;

    @GuardedBy({"mLock"})
    private long mNetworkInterval;
    private final LocationListener mNetworkListener;

    @GuardedBy({"mLock"})
    private Location mNetworkLocation;

    @GuardedBy({"mLock"})
    private ProviderRequestUnbundled mRequest;
    private final BroadcastReceiver mUserChangeReceiver;

    @GuardedBy({"mLock"})
    private WorkSource mWorkSource;

    public FusedLocationProvider(Context context) {
        super("FusedLocationProvider", PROPERTIES);
        this.mLock = new Object();
        this.mContext = context;
        this.mLocationManager = (LocationManager) context.getSystemService(LocationManager.class);
        this.mGpsListener = new LocationListener() { // from class: com.android.location.fused.FusedLocationProvider.1
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                synchronized (FusedLocationProvider.this.mLock) {
                    FusedLocationProvider.this.mGpsLocation = location;
                    FusedLocationProvider.this.reportBestLocationLocked();
                }
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String str) {
                synchronized (FusedLocationProvider.this.mLock) {
                    if (FusedLocationProvider.this.mRequest.getReportLocation() && FusedLocationProvider.this.mRequest.isLocationSettingsIgnored()) {
                        return;
                    }
                    FusedLocationProvider.this.mGpsLocation = null;
                }
            }
        };
        this.mNetworkListener = new LocationListener() { // from class: com.android.location.fused.FusedLocationProvider.2
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                synchronized (FusedLocationProvider.this.mLock) {
                    FusedLocationProvider.this.mNetworkLocation = location;
                    FusedLocationProvider.this.reportBestLocationLocked();
                }
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String str) {
                synchronized (FusedLocationProvider.this.mLock) {
                    if (FusedLocationProvider.this.mRequest.getReportLocation() && FusedLocationProvider.this.mRequest.isLocationSettingsIgnored()) {
                        return;
                    }
                    FusedLocationProvider.this.mNetworkLocation = null;
                }
            }
        };
        this.mUserChangeReceiver = new BroadcastReceiver() { // from class: com.android.location.fused.FusedLocationProvider.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                    FusedLocationProvider.this.onUserChanged();
                }
            }
        };
        this.mRequest = new ProviderRequestUnbundled(ProviderRequest.EMPTY_REQUEST);
        this.mWorkSource = new WorkSource();
        this.mGpsInterval = Long.MAX_VALUE;
        this.mNetworkInterval = Long.MAX_VALUE;
    }

    void start() {
        this.mContext.registerReceiver(this.mUserChangeReceiver, new IntentFilter("android.intent.action.USER_SWITCHED"));
    }

    void stop() {
        this.mContext.unregisterReceiver(this.mUserChangeReceiver);
        synchronized (this.mLock) {
            this.mRequest = new ProviderRequestUnbundled(ProviderRequest.EMPTY_REQUEST);
            updateRequirementsLocked();
        }
    }

    public void onSetRequest(ProviderRequestUnbundled providerRequestUnbundled, WorkSource workSource) {
        synchronized (this.mLock) {
            this.mRequest = providerRequestUnbundled;
            this.mWorkSource = workSource;
            updateRequirementsLocked();
        }
    }

    @GuardedBy({"mLock"})
    private void updateRequirementsLocked() {
        long j;
        long interval = Long.MAX_VALUE;
        if (this.mRequest.getReportLocation()) {
            long interval2 = Long.MAX_VALUE;
            for (LocationRequestUnbundled locationRequestUnbundled : this.mRequest.getLocationRequests()) {
                int quality = locationRequestUnbundled.getQuality();
                if (quality != 100) {
                    if (quality == 102 || quality == 104 || quality == 201) {
                        if (locationRequestUnbundled.getInterval() < interval2) {
                            interval2 = locationRequestUnbundled.getInterval();
                        }
                    } else if (quality != 203) {
                    }
                }
                if (locationRequestUnbundled.getInterval() < interval) {
                    interval = locationRequestUnbundled.getInterval();
                }
                if (locationRequestUnbundled.getInterval() < interval2) {
                    interval2 = locationRequestUnbundled.getInterval();
                }
            }
            j = interval2;
        } else {
            j = Long.MAX_VALUE;
        }
        long j2 = this.mGpsInterval;
        if (interval != j2) {
            resetProviderRequestLocked("gps", j2, interval, this.mGpsListener);
            this.mGpsInterval = interval;
        }
        long j3 = this.mNetworkInterval;
        if (j != j3) {
            resetProviderRequestLocked("network", j3, j, this.mNetworkListener);
            this.mNetworkInterval = j;
        }
    }

    @GuardedBy({"mLock"})
    private void resetProviderRequestLocked(String str, long j, long j2, LocationListener locationListener) {
        if (j != Long.MAX_VALUE) {
            this.mLocationManager.removeUpdates(locationListener);
        }
        if (j2 != Long.MAX_VALUE) {
            LocationRequest locationRequestCreateFromDeprecatedProvider = LocationRequest.createFromDeprecatedProvider(str, j2, 0.0f, false);
            if (this.mRequest.isLocationSettingsIgnored()) {
                locationRequestCreateFromDeprecatedProvider.setLocationSettingsIgnored(true);
            }
            locationRequestCreateFromDeprecatedProvider.setWorkSource(this.mWorkSource);
            this.mLocationManager.requestLocationUpdates(locationRequestCreateFromDeprecatedProvider, locationListener, Looper.getMainLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @GuardedBy({"mLock"})
    public void reportBestLocationLocked() {
        Bundle extras;
        Location locationChooseBestLocation = chooseBestLocation(this.mGpsLocation, this.mNetworkLocation);
        if (locationChooseBestLocation == this.mFusedLocation) {
            return;
        }
        this.mFusedLocation = locationChooseBestLocation;
        if (locationChooseBestLocation == null) {
            return;
        }
        Location location = this.mNetworkLocation;
        if (location != null && (extras = location.getExtras()) != null) {
            Parcelable parcelable = extras.getParcelable("noGPSLocation");
            if (parcelable instanceof Location) {
                Bundle extras2 = this.mFusedLocation.getExtras();
                if (extras2 == null) {
                    extras2 = new Bundle();
                    this.mFusedLocation.setExtras(extras2);
                }
                extras2.putParcelable("noGPSLocation", parcelable);
            }
        }
        reportLocation(this.mFusedLocation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserChanged() {
        synchronized (this.mLock) {
            this.mFusedLocation = null;
            this.mGpsLocation = null;
            this.mNetworkLocation = null;
        }
    }

    void dump(PrintWriter printWriter) {
        synchronized (this.mLock) {
            printWriter.println("request: " + this.mRequest);
            if (this.mGpsInterval != Long.MAX_VALUE) {
                printWriter.println("  gps interval: " + this.mGpsInterval);
            }
            if (this.mNetworkInterval != Long.MAX_VALUE) {
                printWriter.println("  network interval: " + this.mNetworkInterval);
            }
            if (this.mGpsLocation != null) {
                printWriter.println("  last gps location: " + this.mGpsLocation);
            }
            if (this.mNetworkLocation != null) {
                printWriter.println("  last network location: " + this.mNetworkLocation);
            }
        }
    }

    private static Location chooseBestLocation(Location location, Location location2) {
        return location == null ? location2 : (location2 != null && location.getElapsedRealtimeNanos() <= location2.getElapsedRealtimeNanos() + 11000000000L) ? (location2.getElapsedRealtimeNanos() <= location.getElapsedRealtimeNanos() + 11000000000L && location.hasAccuracy()) ? (location2.hasAccuracy() && location.getAccuracy() >= location2.getAccuracy()) ? location2 : location : location2 : location;
    }
}
