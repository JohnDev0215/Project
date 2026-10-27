package android.location;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.PropertyInvalidatedCache;
import android.compat.Compatibility;
import android.content.Context;
import android.location.GnssAntennaInfo;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.GpsMeasurementsEvent;
import android.location.GpsNavigationMessageEvent;
import android.location.GpsStatus;
import android.location.IBatchedLocationCallback;
import android.location.IGnssAntennaInfoListener;
import android.location.IGnssMeasurementsListener;
import android.location.IGnssNavigationMessageListener;
import android.location.IGnssStatusListener;
import android.location.ILocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.ICancellationSignal;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.ArrayMap;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.location.ProviderProperties;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.internal.util.function.pooled.PooledRunnable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public class LocationManager {
    public static final String CACHE_KEY_LOCATION_ENABLED_PROPERTY = "cache_key.location_enabled";
    public static final String EXTRA_LOCATION_ENABLED = "android.location.extra.LOCATION_ENABLED";
    public static final String EXTRA_PROVIDER_ENABLED = "android.location.extra.PROVIDER_ENABLED";
    public static final String EXTRA_PROVIDER_NAME = "android.location.extra.PROVIDER_NAME";
    public static final String FUSED_PROVIDER = "fused";
    private static final long GET_CURRENT_LOCATION_MAX_TIMEOUT_MS = 30000;
    private static final long GET_PROVIDER_SECURITY_EXCEPTIONS = 150935354;
    public static final String GPS_PROVIDER = "gps";
    private static final long GPS_STATUS_USAGE = 144027538;
    public static final String HIGH_POWER_REQUEST_CHANGE_ACTION = "android.location.HIGH_POWER_REQUEST_CHANGE";
    private static final long INCOMPLETE_LOCATION = 148964793;
    public static final String KEY_LOCATION_CHANGED = "location";
    public static final String KEY_PROVIDER_ENABLED = "providerEnabled";
    public static final String KEY_PROXIMITY_ENTERING = "entering";

    @Deprecated
    public static final String KEY_STATUS_CHANGED = "status";
    public static final String METADATA_SETTINGS_FOOTER_STRING = "com.android.settings.location.FOOTER_STRING";
    public static final String MODE_CHANGED_ACTION = "android.location.MODE_CHANGED";
    public static final String NETWORK_PROVIDER = "network";
    public static final String PASSIVE_PROVIDER = "passive";
    public static final String PROVIDERS_CHANGED_ACTION = "android.location.PROVIDERS_CHANGED";
    public static final String SETTINGS_FOOTER_DISPLAYED_ACTION = "com.android.settings.location.DISPLAYED_FOOTER";
    private static final long TARGETED_PENDING_INTENT = 148963590;
    private final BatchedLocationCallbackManager mBatchedLocationCallbackManager;
    private final Context mContext;
    private final GnssAntennaInfoListenerManager mGnssAntennaInfoListenerManager;
    private final GnssMeasurementsListenerManager mGnssMeasurementsListenerManager;
    private final GnssNavigationMessageListenerManager mGnssNavigationMessageListenerTransport;
    private final GnssStatusListenerManager mGnssStatusListenerManager;
    private final ILocationManager mService;
    private PropertyInvalidatedCache<Integer, Boolean> mLocationEnabledCache = new PropertyInvalidatedCache<Integer, Boolean>(4, CACHE_KEY_LOCATION_ENABLED_PROPERTY) { // from class: android.location.LocationManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.app.PropertyInvalidatedCache
        public Boolean recompute(Integer num) {
            try {
                return Boolean.valueOf(LocationManager.this.mService.isLocationEnabledForUser(num.intValue()));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private final Object mLock = new Object();
    private final ArrayMap<LocationListener, LocationListenerTransport> mListeners = new ArrayMap<>();

    @SystemApi
    @Deprecated
    public boolean addGpsMeasurementListener(GpsMeasurementsEvent.Listener listener) {
        return false;
    }

    @SystemApi
    @Deprecated
    public boolean addGpsNavigationMessageListener(GpsNavigationMessageEvent.Listener listener) {
        return false;
    }

    @Deprecated
    public boolean addNmeaListener(GpsStatus.NmeaListener nmeaListener) {
        return false;
    }

    @Deprecated
    public void clearTestProviderLocation(String str) {
    }

    @Deprecated
    public void clearTestProviderStatus(String str) {
    }

    @SystemApi
    @Deprecated
    public void removeGpsMeasurementListener(GpsMeasurementsEvent.Listener listener) {
    }

    @SystemApi
    @Deprecated
    public void removeGpsNavigationMessageListener(GpsNavigationMessageEvent.Listener listener) {
    }

    @Deprecated
    public void removeNmeaListener(GpsStatus.NmeaListener nmeaListener) {
    }

    @Deprecated
    public void setTestProviderStatus(String str, int i, Bundle bundle, long j) {
    }

    public LocationManager(Context context, ILocationManager iLocationManager) {
        this.mBatchedLocationCallbackManager = new BatchedLocationCallbackManager();
        this.mGnssStatusListenerManager = new GnssStatusListenerManager();
        this.mGnssMeasurementsListenerManager = new GnssMeasurementsListenerManager();
        this.mGnssNavigationMessageListenerTransport = new GnssNavigationMessageListenerManager();
        this.mGnssAntennaInfoListenerManager = new GnssAntennaInfoListenerManager();
        this.mService = iLocationManager;
        this.mContext = context;
    }

    public String[] getBackgroundThrottlingWhitelist() {
        try {
            return this.mService.getBackgroundThrottlingWhitelist();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String[] getIgnoreSettingsWhitelist() {
        try {
            return this.mService.getIgnoreSettingsWhitelist();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public String getExtraLocationControllerPackage() {
        try {
            return this.mService.getExtraLocationControllerPackage();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    @SystemApi
    public void setExtraLocationControllerPackage(String str) {
        try {
            this.mService.setExtraLocationControllerPackage(str);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setExtraLocationControllerPackageEnabled(boolean z) {
        try {
            this.mService.setExtraLocationControllerPackageEnabled(z);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isExtraLocationControllerPackageEnabled() {
        try {
            return this.mService.isExtraLocationControllerPackageEnabled();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    @SystemApi
    @Deprecated
    public void setLocationControllerExtraPackage(String str) {
        try {
            this.mService.setExtraLocationControllerPackage(str);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void setLocationControllerExtraPackageEnabled(boolean z) {
        try {
            this.mService.setExtraLocationControllerPackageEnabled(z);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public boolean isLocationEnabled() {
        return isLocationEnabledForUser(Process.myUserHandle());
    }

    @SystemApi
    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        synchronized (this.mLock) {
            PropertyInvalidatedCache<Integer, Boolean> propertyInvalidatedCache = this.mLocationEnabledCache;
            if (propertyInvalidatedCache != null) {
                return propertyInvalidatedCache.query(Integer.valueOf(userHandle.getIdentifier())).booleanValue();
            }
            try {
                return this.mService.isLocationEnabledForUser(userHandle.getIdentifier());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public void setLocationEnabledForUser(boolean z, UserHandle userHandle) {
        try {
            this.mService.setLocationEnabledForUser(z, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isProviderEnabled(String str) {
        return isProviderEnabledForUser(str, Process.myUserHandle());
    }

    @SystemApi
    public boolean isProviderEnabledForUser(String str, UserHandle userHandle) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            return this.mService.isProviderEnabledForUser(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public boolean setProviderEnabledForUser(String str, boolean z, UserHandle userHandle) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        return Settings.Secure.putStringForUser(this.mContext.getContentResolver(), "location_providers_allowed", (z ? "+" : NativeLibraryHelper.CLEAR_ABI_OVERRIDE) + str, userHandle.getIdentifier());
    }

    public Location getLastLocation() {
        try {
            return this.mService.getLastLocation(null, this.mContext.getPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public Location getLastKnownLocation(String str) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            return this.mService.getLastLocation(LocationRequest.createFromDeprecatedProvider(str, 0L, 0.0f, true), this.mContext.getPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void getCurrentLocation(String str, CancellationSignal cancellationSignal, Executor executor, Consumer<Location> consumer) {
        getCurrentLocation(LocationRequest.createFromDeprecatedProvider(str, 0L, 0.0f, true), cancellationSignal, executor, consumer);
    }

    @SystemApi
    public void getCurrentLocation(LocationRequest locationRequest, CancellationSignal cancellationSignal, Executor executor, Consumer<Location> consumer) {
        LocationRequest numUpdates = new LocationRequest(locationRequest).setNumUpdates(1);
        if (numUpdates.getExpireIn() > 30000) {
            numUpdates.setExpireIn(30000L);
        }
        GetCurrentLocationTransport getCurrentLocationTransport = new GetCurrentLocationTransport(executor, consumer);
        if (cancellationSignal != null) {
            cancellationSignal.throwIfCanceled();
        }
        try {
            ICancellationSignal currentLocation = this.mService.getCurrentLocation(numUpdates, getCurrentLocationTransport, this.mContext.getPackageName(), this.mContext.getAttributionTag(), getCurrentLocationTransport.getListenerId());
            if (currentLocation != null) {
                getCurrentLocationTransport.register((AlarmManager) this.mContext.getSystemService(AlarmManager.class), cancellationSignal, currentLocation);
                if (cancellationSignal != null) {
                    cancellationSignal.setRemote(currentLocation);
                    return;
                }
                return;
            }
            getCurrentLocationTransport.fail();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void requestSingleUpdate(String str, LocationListener locationListener, Looper looper) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        LocationRequest locationRequestCreateFromDeprecatedProvider = LocationRequest.createFromDeprecatedProvider(str, 0L, 0.0f, true);
        locationRequestCreateFromDeprecatedProvider.setExpireIn(30000L);
        requestLocationUpdates(locationRequestCreateFromDeprecatedProvider, locationListener, looper);
    }

    @Deprecated
    public void requestSingleUpdate(Criteria criteria, LocationListener locationListener, Looper looper) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        LocationRequest locationRequestCreateFromDeprecatedCriteria = LocationRequest.createFromDeprecatedCriteria(criteria, 0L, 0.0f, true);
        locationRequestCreateFromDeprecatedCriteria.setExpireIn(30000L);
        requestLocationUpdates(locationRequestCreateFromDeprecatedCriteria, locationListener, looper);
    }

    @Deprecated
    public void requestSingleUpdate(String str, PendingIntent pendingIntent) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        LocationRequest locationRequestCreateFromDeprecatedProvider = LocationRequest.createFromDeprecatedProvider(str, 0L, 0.0f, true);
        locationRequestCreateFromDeprecatedProvider.setExpireIn(30000L);
        requestLocationUpdates(locationRequestCreateFromDeprecatedProvider, pendingIntent);
    }

    @Deprecated
    public void requestSingleUpdate(Criteria criteria, PendingIntent pendingIntent) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        LocationRequest locationRequestCreateFromDeprecatedCriteria = LocationRequest.createFromDeprecatedCriteria(criteria, 0L, 0.0f, true);
        locationRequestCreateFromDeprecatedCriteria.setExpireIn(30000L);
        requestLocationUpdates(locationRequestCreateFromDeprecatedCriteria, pendingIntent);
    }

    public void requestLocationUpdates(String str, long j, float f, LocationListener locationListener) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(str, j, f, false), locationListener, (Looper) null);
    }

    public void requestLocationUpdates(String str, long j, float f, LocationListener locationListener, Looper looper) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(str, j, f, false), locationListener, looper);
    }

    public void requestLocationUpdates(String str, long j, float f, Executor executor, LocationListener locationListener) {
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(str, j, f, false), executor, locationListener);
    }

    public void requestLocationUpdates(long j, float f, Criteria criteria, LocationListener locationListener, Looper looper) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, j, f, false), locationListener, looper);
    }

    public void requestLocationUpdates(long j, float f, Criteria criteria, Executor executor, LocationListener locationListener) {
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, j, f, false), executor, locationListener);
    }

    public void requestLocationUpdates(String str, long j, float f, PendingIntent pendingIntent) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        requestLocationUpdates(LocationRequest.createFromDeprecatedProvider(str, j, f, false), pendingIntent);
    }

    public void requestLocationUpdates(long j, float f, Criteria criteria, PendingIntent pendingIntent) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        requestLocationUpdates(LocationRequest.createFromDeprecatedCriteria(criteria, j, f, false), pendingIntent);
    }

    @SystemApi
    public void requestLocationUpdates(LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        requestLocationUpdates(locationRequest, new HandlerExecutor(looper == null ? new Handler() : new Handler(looper)), locationListener);
    }

    @SystemApi
    public void requestLocationUpdates(LocationRequest locationRequest, Executor executor, LocationListener locationListener) {
        synchronized (this.mListeners) {
            LocationListenerTransport locationListenerTransport = this.mListeners.get(locationListener);
            if (locationListenerTransport != null) {
                locationListenerTransport.unregister();
            } else {
                locationListenerTransport = new LocationListenerTransport(locationListener);
                this.mListeners.put(locationListener, locationListenerTransport);
            }
            locationListenerTransport.register(executor);
            try {
                try {
                    this.mService.requestLocationUpdates(locationRequest, locationListenerTransport, null, this.mContext.getPackageName(), this.mContext.getAttributionTag(), locationListenerTransport.getListenerId());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                locationListenerTransport.unregister();
                this.mListeners.remove(locationListener);
                throw th;
            }
        }
    }

    @SystemApi
    public void requestLocationUpdates(LocationRequest locationRequest, PendingIntent pendingIntent) {
        Preconditions.checkArgument(locationRequest != null, "invalid null location request");
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        try {
            this.mService.requestLocationUpdates(locationRequest, null, pendingIntent, this.mContext.getPackageName(), this.mContext.getAttributionTag(), null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean injectLocation(Location location) {
        Preconditions.checkArgument(location != null, "invalid null location");
        Preconditions.checkArgument(location.isComplete(), "incomplete location object, missing timestamp or accuracy?");
        try {
            this.mService.injectLocation(location);
            return true;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeUpdates(LocationListener locationListener) {
        Preconditions.checkArgument(locationListener != null, "invalid null listener");
        synchronized (this.mListeners) {
            LocationListenerTransport locationListenerTransportRemove = this.mListeners.remove(locationListener);
            if (locationListenerTransportRemove == null) {
                return;
            }
            locationListenerTransportRemove.unregister();
            try {
                this.mService.removeUpdates(locationListenerTransportRemove, null);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void removeUpdates(PendingIntent pendingIntent) {
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        try {
            this.mService.removeUpdates(null, pendingIntent);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<String> getAllProviders() {
        try {
            return this.mService.getAllProviders();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<String> getProviders(boolean z) {
        try {
            return this.mService.getProviders(null, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<String> getProviders(Criteria criteria, boolean z) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        try {
            return this.mService.getProviders(criteria, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getBestProvider(Criteria criteria, boolean z) {
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        try {
            return this.mService.getBestProvider(criteria, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LocationProvider getProvider(String str) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        if (!Compatibility.isChangeEnabled(GET_PROVIDER_SECURITY_EXCEPTIONS)) {
            if (NETWORK_PROVIDER.equals(str) || FUSED_PROVIDER.equals(str)) {
                try {
                    this.mContext.enforcePermission(Manifest.permission.ACCESS_FINE_LOCATION, Process.myPid(), Process.myUid(), null);
                } catch (SecurityException unused) {
                    this.mContext.enforcePermission(Manifest.permission.ACCESS_COARSE_LOCATION, Process.myPid(), Process.myUid(), null);
                }
            } else {
                this.mContext.enforcePermission(Manifest.permission.ACCESS_FINE_LOCATION, Process.myPid(), Process.myUid(), null);
            }
        }
        try {
            ProviderProperties providerProperties = this.mService.getProviderProperties(str);
            if (providerProperties == null) {
                return null;
            }
            return new LocationProvider(str, providerProperties);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isProviderPackage(String str) {
        try {
            return this.mService.isProviderPackage(str);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    public List<String> getProviderPackages(String str) {
        try {
            return this.mService.getProviderPackages(str);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return Collections.emptyList();
        }
    }

    public boolean sendExtraCommand(String str, String str2, Bundle bundle) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        Preconditions.checkArgument(str2 != null, "invalid null command");
        try {
            return this.mService.sendExtraCommand(str, str2, bundle);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addTestProvider(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, int i2) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            this.mService.addTestProvider(str, new ProviderProperties(z, z2, z3, z4, z5, z6, z7, i, i2), this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeTestProvider(String str) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            this.mService.removeTestProvider(str, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setTestProviderLocation(String str, Location location) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        Preconditions.checkArgument(location != null, "invalid null location");
        if (Compatibility.isChangeEnabled(INCOMPLETE_LOCATION)) {
            Preconditions.checkArgument(location.isComplete(), "incomplete location object, missing timestamp or accuracy?");
        } else {
            location.makeComplete();
        }
        try {
            this.mService.setTestProviderLocation(str, location, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setTestProviderEnabled(String str, boolean z) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            this.mService.setTestProviderEnabled(str, z, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void clearTestProviderEnabled(String str) {
        setTestProviderEnabled(str, false);
    }

    public List<LocationRequest> getTestProviderCurrentRequests(String str) {
        Preconditions.checkArgument(str != null, "invalid null provider");
        try {
            return this.mService.getTestProviderCurrentRequests(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addProximityAlert(double d, double d2, float f, long j, PendingIntent pendingIntent) {
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        if (j < 0) {
            j = Long.MAX_VALUE;
        }
        try {
            this.mService.requestGeofence(new LocationRequest().setExpireIn(j), Geofence.createCircle(d, d2, f), pendingIntent, this.mContext.getPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeProximityAlert(PendingIntent pendingIntent) {
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        try {
            this.mService.removeGeofence(null, pendingIntent, this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addGeofence(LocationRequest locationRequest, Geofence geofence, PendingIntent pendingIntent) {
        Preconditions.checkArgument(locationRequest != null, "invalid null location request");
        Preconditions.checkArgument(geofence != null, "invalid null geofence");
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        try {
            this.mService.requestGeofence(locationRequest, geofence, pendingIntent, this.mContext.getPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeGeofence(Geofence geofence, PendingIntent pendingIntent) {
        Preconditions.checkArgument(geofence != null, "invalid null geofence");
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        try {
            this.mService.removeGeofence(geofence, pendingIntent, this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeAllGeofences(PendingIntent pendingIntent) {
        Preconditions.checkArgument(pendingIntent != null, "invalid null pending intent");
        if (Compatibility.isChangeEnabled(TARGETED_PENDING_INTENT)) {
            Preconditions.checkArgument(pendingIntent.isTargetedToPackage(), "pending intent must be targeted to a package");
        }
        try {
            this.mService.removeGeofence(null, pendingIntent, this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public GnssCapabilities getGnssCapabilities() {
        try {
            long gnssCapabilities = this.mService.getGnssCapabilities();
            if (gnssCapabilities == -1) {
                gnssCapabilities = 0;
            }
            return GnssCapabilities.of(gnssCapabilities);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getGnssYearOfHardware() {
        try {
            return this.mService.getGnssYearOfHardware();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getGnssHardwareModelName() {
        try {
            return this.mService.getGnssHardwareModelName();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public GpsStatus getGpsStatus(GpsStatus gpsStatus) {
        if (Compatibility.isChangeEnabled(GPS_STATUS_USAGE)) {
            throw new UnsupportedOperationException("GpsStatus APIs not supported, please use GnssStatus APIs instead");
        }
        GnssStatus gnssStatus = this.mGnssStatusListenerManager.getGnssStatus();
        int ttff = this.mGnssStatusListenerManager.getTtff();
        if (gnssStatus == null) {
            return gpsStatus == null ? GpsStatus.createEmpty() : gpsStatus;
        }
        if (gpsStatus == null) {
            return GpsStatus.create(gnssStatus, ttff);
        }
        gpsStatus.setStatus(gnssStatus, ttff);
        return gpsStatus;
    }

    @Deprecated
    public boolean addGpsStatusListener(GpsStatus.Listener listener) {
        if (Compatibility.isChangeEnabled(GPS_STATUS_USAGE)) {
            throw new UnsupportedOperationException("GpsStatus APIs not supported, please use GnssStatus APIs instead");
        }
        try {
            return this.mGnssStatusListenerManager.addListener(listener, (Executor) new HandlerExecutor(new Handler()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void removeGpsStatusListener(GpsStatus.Listener listener) {
        if (Compatibility.isChangeEnabled(GPS_STATUS_USAGE)) {
            throw new UnsupportedOperationException("GpsStatus APIs not supported, please use GnssStatus APIs instead");
        }
        try {
            this.mGnssStatusListenerManager.removeListener(listener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean registerGnssStatusCallback(GnssStatus.Callback callback) {
        return registerGnssStatusCallback(callback, (Handler) null);
    }

    public boolean registerGnssStatusCallback(GnssStatus.Callback callback, Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        try {
            return this.mGnssStatusListenerManager.addListener(callback, handler);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean registerGnssStatusCallback(Executor executor, GnssStatus.Callback callback) {
        try {
            return this.mGnssStatusListenerManager.addListener(callback, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterGnssStatusCallback(GnssStatus.Callback callback) {
        try {
            this.mGnssStatusListenerManager.removeListener(callback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean addNmeaListener(OnNmeaMessageListener onNmeaMessageListener) {
        return addNmeaListener(onNmeaMessageListener, (Handler) null);
    }

    public boolean addNmeaListener(OnNmeaMessageListener onNmeaMessageListener, Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        try {
            return this.mGnssStatusListenerManager.addListener(onNmeaMessageListener, handler);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean addNmeaListener(Executor executor, OnNmeaMessageListener onNmeaMessageListener) {
        try {
            return this.mGnssStatusListenerManager.addListener(onNmeaMessageListener, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeNmeaListener(OnNmeaMessageListener onNmeaMessageListener) {
        try {
            this.mGnssStatusListenerManager.removeListener(onNmeaMessageListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback) {
        return registerGnssMeasurementsCallback($$Lambda$PNiE7SuEFxRjAZH7pJpZIFOFjWg.INSTANCE, callback);
    }

    public boolean registerGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback, Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        try {
            return this.mGnssMeasurementsListenerManager.addListener(callback, handler);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean registerGnssMeasurementsCallback(Executor executor, GnssMeasurementsEvent.Callback callback) {
        try {
            return this.mGnssMeasurementsListenerManager.addListener(callback, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean registerGnssMeasurementsCallback(GnssRequest gnssRequest, Executor executor, GnssMeasurementsEvent.Callback callback) {
        Preconditions.checkArgument(gnssRequest != null, "invalid null request");
        try {
            return this.mGnssMeasurementsListenerManager.addListener(gnssRequest, callback, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void injectGnssMeasurementCorrections(GnssMeasurementCorrections gnssMeasurementCorrections) {
        Preconditions.checkArgument(gnssMeasurementCorrections != null);
        try {
            this.mService.injectGnssMeasurementCorrections(gnssMeasurementCorrections, this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterGnssMeasurementsCallback(GnssMeasurementsEvent.Callback callback) {
        try {
            this.mGnssMeasurementsListenerManager.removeListener(callback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean registerAntennaInfoListener(Executor executor, GnssAntennaInfo.Listener listener) {
        try {
            return this.mGnssAntennaInfoListenerManager.addListener(listener, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterAntennaInfoListener(GnssAntennaInfo.Listener listener) {
        try {
            this.mGnssAntennaInfoListenerManager.removeListener(listener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback) {
        return registerGnssNavigationMessageCallback($$Lambda$PNiE7SuEFxRjAZH7pJpZIFOFjWg.INSTANCE, callback);
    }

    public boolean registerGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback, Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        try {
            return this.mGnssNavigationMessageListenerTransport.addListener(callback, handler);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean registerGnssNavigationMessageCallback(Executor executor, GnssNavigationMessage.Callback callback) {
        try {
            return this.mGnssNavigationMessageListenerTransport.addListener(callback, executor);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unregisterGnssNavigationMessageCallback(GnssNavigationMessage.Callback callback) {
        try {
            this.mGnssNavigationMessageListenerTransport.removeListener(callback);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getGnssBatchSize() {
        try {
            return this.mService.getGnssBatchSize(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean registerGnssBatchedLocationCallback(long j, boolean z, BatchedLocationCallback batchedLocationCallback, Handler handler) {
        if (handler == null) {
            handler = new Handler();
        }
        synchronized (this.mBatchedLocationCallbackManager) {
            try {
                try {
                    if (!this.mBatchedLocationCallbackManager.addListener(batchedLocationCallback, handler)) {
                        return false;
                    }
                    return this.mService.startGnssBatch(j, z, this.mContext.getPackageName(), this.mContext.getAttributionTag());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @SystemApi
    public void flushGnssBatch() {
        try {
            this.mService.flushGnssBatch(this.mContext.getPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean unregisterGnssBatchedLocationCallback(BatchedLocationCallback batchedLocationCallback) {
        synchronized (this.mBatchedLocationCallbackManager) {
            try {
                try {
                    this.mBatchedLocationCallbackManager.removeListener(batchedLocationCallback);
                    this.mService.stopGnssBatch();
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class GetCurrentLocationTransport extends ILocationListener.Stub implements AlarmManager.OnAlarmListener, CancellationSignal.OnCancelListener {
        private AlarmManager mAlarmManager;
        private Consumer<Location> mConsumer;
        private Executor mExecutor;
        private ICancellationSignal mRemoteCancellationSignal;

        @Override // android.location.ILocationListener
        public void onProviderEnabled(String str) {
        }

        private GetCurrentLocationTransport(Executor executor, Consumer<Location> consumer) {
            Preconditions.checkArgument(executor != null, "illegal null executor");
            Preconditions.checkArgument(consumer != null, "illegal null consumer");
            this.mExecutor = executor;
            this.mConsumer = consumer;
            this.mAlarmManager = null;
            this.mRemoteCancellationSignal = null;
        }

        public String getListenerId() {
            return AppOpsManager.toReceiverId(this.mConsumer);
        }

        public synchronized void register(AlarmManager alarmManager, CancellationSignal cancellationSignal, ICancellationSignal iCancellationSignal) {
            if (this.mConsumer == null) {
                return;
            }
            this.mAlarmManager = alarmManager;
            alarmManager.set(3, 30000 + SystemClock.elapsedRealtime(), "GetCurrentLocation", this, null);
            if (cancellationSignal != null) {
                cancellationSignal.setOnCancelListener(this);
            }
            this.mRemoteCancellationSignal = iCancellationSignal;
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            synchronized (this) {
                this.mRemoteCancellationSignal = null;
            }
            remove();
        }

        private Consumer<Location> remove() {
            Consumer<Location> consumer;
            ICancellationSignal iCancellationSignal;
            synchronized (this) {
                this.mExecutor = null;
                consumer = this.mConsumer;
                this.mConsumer = null;
                AlarmManager alarmManager = this.mAlarmManager;
                if (alarmManager != null) {
                    alarmManager.cancel(this);
                    this.mAlarmManager = null;
                }
                iCancellationSignal = this.mRemoteCancellationSignal;
                this.mRemoteCancellationSignal = null;
            }
            if (iCancellationSignal != null) {
                try {
                    iCancellationSignal.cancel();
                } catch (RemoteException unused) {
                }
            }
            return consumer;
        }

        public void fail() {
            deliverResult(null);
        }

        @Override // android.app.AlarmManager.OnAlarmListener
        public void onAlarm() {
            synchronized (this) {
                this.mAlarmManager = null;
            }
            deliverResult(null);
        }

        @Override // android.location.ILocationListener
        public void onLocationChanged(Location location) {
            deliverResult(location);
        }

        @Override // android.location.ILocationListener
        public void onProviderDisabled(String str) {
            deliverResult(null);
        }

        @Override // android.location.ILocationListener
        public void onRemoved() {
            deliverResult(null);
        }

        private synchronized void deliverResult(Location location) {
            if (this.mExecutor == null) {
                return;
            }
            PooledRunnable pooledRunnableRecycleOnUse = PooledLambda.obtainRunnable(new BiConsumer() { // from class: android.location.-$$Lambda$LocationManager$GetCurrentLocationTransport$XerMt2JSHPNcTGKULR6Qr2tjbmQ
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((LocationManager.GetCurrentLocationTransport) obj).acceptResult((Location) obj2);
                }
            }, this, location).recycleOnUse();
            try {
                this.mExecutor.execute(pooledRunnableRecycleOnUse);
            } catch (RejectedExecutionException e) {
                pooledRunnableRecycleOnUse.recycle();
                throw e;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void acceptResult(Location location) {
            Consumer<Location> consumerRemove = remove();
            if (consumerRemove != null) {
                consumerRemove.accept(location);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class LocationListenerTransport extends ILocationListener.Stub {
        private volatile Executor mExecutor;
        private final LocationListener mListener;

        private LocationListenerTransport(LocationListener locationListener) {
            this.mExecutor = null;
            Preconditions.checkArgument(locationListener != null, "invalid null listener");
            this.mListener = locationListener;
        }

        public LocationListener getKey() {
            return this.mListener;
        }

        public String getListenerId() {
            return AppOpsManager.toReceiverId(this.mListener);
        }

        public void register(Executor executor) {
            Preconditions.checkArgument(executor != null, "invalid null executor");
            this.mExecutor = executor;
        }

        public void unregister() {
            this.mExecutor = null;
        }

        @Override // android.location.ILocationListener
        public void onLocationChanged(Location location) {
            Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            PooledRunnable pooledRunnableRecycleOnUse = PooledLambda.obtainRunnable(new TriConsumer() { // from class: android.location.-$$Lambda$LocationManager$LocationListenerTransport$YrwBF_AnCcvKFK7WCTKqbcEQX2I
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((LocationManager.LocationListenerTransport) obj).acceptLocation((Executor) obj2, (Location) obj3);
                }
            }, this, executor, location).recycleOnUse();
            try {
                executor.execute(pooledRunnableRecycleOnUse);
            } catch (RejectedExecutionException e) {
                pooledRunnableRecycleOnUse.recycle();
                locationCallbackFinished();
                throw e;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void acceptLocation(Executor executor, Location location) {
            try {
                if (executor != this.mExecutor) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    this.mListener.onLocationChanged(location);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } finally {
                locationCallbackFinished();
            }
        }

        @Override // android.location.ILocationListener
        public void onProviderEnabled(String str) {
            Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            PooledRunnable pooledRunnableRecycleOnUse = PooledLambda.obtainRunnable($$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU.INSTANCE, this, executor, str, true).recycleOnUse();
            try {
                executor.execute(pooledRunnableRecycleOnUse);
            } catch (RejectedExecutionException e) {
                pooledRunnableRecycleOnUse.recycle();
                locationCallbackFinished();
                throw e;
            }
        }

        @Override // android.location.ILocationListener
        public void onProviderDisabled(String str) {
            Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            PooledRunnable pooledRunnableRecycleOnUse = PooledLambda.obtainRunnable($$Lambda$LocationManager$LocationListenerTransport$OfJ8qeu44rva3AgarDg5NIlE0KU.INSTANCE, this, executor, str, false).recycleOnUse();
            try {
                executor.execute(pooledRunnableRecycleOnUse);
            } catch (RejectedExecutionException e) {
                pooledRunnableRecycleOnUse.recycle();
                locationCallbackFinished();
                throw e;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void acceptProviderChange(Executor executor, String str, boolean z) {
            try {
                if (executor != this.mExecutor) {
                    return;
                }
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    if (z) {
                        this.mListener.onProviderEnabled(str);
                    } else {
                        this.mListener.onProviderDisabled(str);
                    }
                } finally {
                }
            } finally {
                locationCallbackFinished();
            }
        }

        @Override // android.location.ILocationListener
        public void onRemoved() {
            final Executor executor = this.mExecutor;
            if (executor == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: android.location.-$$Lambda$LocationManager$LocationListenerTransport$UReZvpcRVyxIQC_n1_o6ggh7f4k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRemoved$0$LocationManager$LocationListenerTransport(executor);
                }
            });
        }

        public /* synthetic */ void lambda$onRemoved$0$LocationManager$LocationListenerTransport(Executor executor) {
            if (executor != this.mExecutor) {
                return;
            }
            unregister();
            synchronized (LocationManager.this.mListeners) {
                LocationManager.this.mListeners.remove(this.mListener, this);
            }
        }

        private void locationCallbackFinished() {
            try {
                LocationManager.this.mService.locationCallbackFinished(this);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class NmeaAdapter extends GnssStatus.Callback implements OnNmeaMessageListener {
        private final OnNmeaMessageListener mListener;

        private NmeaAdapter(OnNmeaMessageListener onNmeaMessageListener) {
            this.mListener = onNmeaMessageListener;
        }

        @Override // android.location.OnNmeaMessageListener
        public void onNmeaMessage(String str, long j) {
            this.mListener.onNmeaMessage(str, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GnssStatusListenerManager extends AbstractListenerManager<Void, GnssStatus.Callback> {
        private volatile GnssStatus mGnssStatus;
        private IGnssStatusListener mListenerTransport;
        private volatile int mTtff;

        private GnssStatusListenerManager() {
        }

        public GnssStatus getGnssStatus() {
            return this.mGnssStatus;
        }

        public int getTtff() {
            return this.mTtff;
        }

        public boolean addListener(GpsStatus.Listener listener, Executor executor) throws RemoteException {
            return addInternal((Object) null, listener, executor);
        }

        public boolean addListener(OnNmeaMessageListener onNmeaMessageListener, Handler handler) throws RemoteException {
            return addInternal((Object) null, onNmeaMessageListener, handler);
        }

        public boolean addListener(OnNmeaMessageListener onNmeaMessageListener, Executor executor) throws RemoteException {
            return addInternal((Object) null, onNmeaMessageListener, executor);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.location.AbstractListenerManager
        public GnssStatus.Callback convertKey(Object obj) {
            if (obj instanceof GnssStatus.Callback) {
                return (GnssStatus.Callback) obj;
            }
            if (obj instanceof GpsStatus.Listener) {
                return new GnssStatus.Callback(obj) { // from class: android.location.LocationManager.GnssStatusListenerManager.1
                    private final GpsStatus.Listener mGpsListener;
                    final /* synthetic */ Object val$listener;

                    {
                        this.val$listener = obj;
                        this.mGpsListener = (GpsStatus.Listener) obj;
                    }

                    @Override // android.location.GnssStatus.Callback
                    public void onStarted() {
                        this.mGpsListener.onGpsStatusChanged(1);
                    }

                    @Override // android.location.GnssStatus.Callback
                    public void onStopped() {
                        this.mGpsListener.onGpsStatusChanged(2);
                    }

                    @Override // android.location.GnssStatus.Callback
                    public void onFirstFix(int i) {
                        this.mGpsListener.onGpsStatusChanged(3);
                    }

                    @Override // android.location.GnssStatus.Callback
                    public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
                        this.mGpsListener.onGpsStatusChanged(4);
                    }
                };
            }
            if (obj instanceof OnNmeaMessageListener) {
                return new NmeaAdapter((OnNmeaMessageListener) obj);
            }
            throw new IllegalStateException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.location.AbstractListenerManager
        public boolean registerService(Void r6) throws RemoteException {
            Preconditions.checkState(this.mListenerTransport == null);
            GnssStatusListener gnssStatusListener = new GnssStatusListener();
            if (!LocationManager.this.mService.registerGnssStatusCallback(gnssStatusListener, LocationManager.this.mContext.getPackageName(), LocationManager.this.mContext.getAttributionTag())) {
                return false;
            }
            this.mListenerTransport = gnssStatusListener;
            return true;
        }

        @Override // android.location.AbstractListenerManager
        protected void unregisterService() throws RemoteException {
            if (this.mListenerTransport != null) {
                LocationManager.this.mService.unregisterGnssStatusCallback(this.mListenerTransport);
                this.mListenerTransport = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class GnssStatusListener extends IGnssStatusListener.Stub {
            private GnssStatusListener() {
            }

            @Override // android.location.IGnssStatusListener
            public void onGnssStarted() {
                GnssStatusListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$oRuFAYa4fkzgauXEWH0FRWNIgxI
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssStatus.Callback) obj).onStarted();
                    }
                });
            }

            @Override // android.location.IGnssStatusListener
            public void onGnssStopped() {
                GnssStatusListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$a8ETEwLHBoqoaB6emdmNGmkxlT0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssStatus.Callback) obj).onStopped();
                    }
                });
            }

            @Override // android.location.IGnssStatusListener
            public void onFirstFix(final int i) {
                GnssStatusListenerManager.this.mTtff = i;
                GnssStatusListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssStatusListenerManager$GnssStatusListener$Umi1XIblgQ2WICNEBWtM6rY6XiI
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssStatus.Callback) obj).onFirstFix(i);
                    }
                });
            }

            @Override // android.location.IGnssStatusListener
            public void onSvStatusChanged(int i, int[] iArr, float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, float[] fArr5) {
                final GnssStatus gnssStatusWrap = GnssStatus.wrap(i, iArr, fArr, fArr2, fArr3, fArr4, fArr5);
                GnssStatusListenerManager.this.mGnssStatus = gnssStatusWrap;
                GnssStatusListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssStatusListenerManager$GnssStatusListener$2s2WQP0dZaOWdqNJGYo1PIfeWfg
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssStatus.Callback) obj).onSatelliteStatusChanged(gnssStatusWrap);
                    }
                });
            }

            @Override // android.location.IGnssStatusListener
            public void onNmeaReceived(final long j, final String str) {
                GnssStatusListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssStatusListenerManager$GnssStatusListener$5ns--hvtb5SGygAgzGxWHFX0Ifs
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        LocationManager.GnssStatusListenerManager.GnssStatusListener.lambda$onNmeaReceived$2(str, j, (GnssStatus.Callback) obj);
                    }
                });
            }

            static /* synthetic */ void lambda$onNmeaReceived$2(String str, long j, GnssStatus.Callback callback) {
                if (callback instanceof NmeaAdapter) {
                    ((NmeaAdapter) callback).onNmeaMessage(str, j);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GnssMeasurementsListenerManager extends AbstractListenerManager<GnssRequest, GnssMeasurementsEvent.Callback> {
        private IGnssMeasurementsListener mListenerTransport;

        private GnssMeasurementsListenerManager() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.location.AbstractListenerManager
        public boolean registerService(GnssRequest gnssRequest) throws RemoteException {
            Preconditions.checkState(this.mListenerTransport == null);
            GnssMeasurementsListener gnssMeasurementsListener = new GnssMeasurementsListener();
            if (!LocationManager.this.mService.addGnssMeasurementsListener(gnssRequest, gnssMeasurementsListener, LocationManager.this.mContext.getPackageName(), LocationManager.this.mContext.getAttributionTag())) {
                return false;
            }
            this.mListenerTransport = gnssMeasurementsListener;
            return true;
        }

        @Override // android.location.AbstractListenerManager
        protected void unregisterService() throws RemoteException {
            if (this.mListenerTransport != null) {
                LocationManager.this.mService.removeGnssMeasurementsListener(this.mListenerTransport);
                this.mListenerTransport = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.location.AbstractListenerManager
        public GnssRequest merge(List<GnssRequest> list) {
            Preconditions.checkArgument(!list.isEmpty());
            for (GnssRequest gnssRequest : list) {
                if (gnssRequest != null && gnssRequest.isFullTracking()) {
                    return gnssRequest;
                }
            }
            return list.get(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        class GnssMeasurementsListener extends IGnssMeasurementsListener.Stub {
            private GnssMeasurementsListener() {
            }

            @Override // android.location.IGnssMeasurementsListener
            public void onGnssMeasurementsReceived(final GnssMeasurementsEvent gnssMeasurementsEvent) {
                GnssMeasurementsListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssMeasurementsListenerManager$GnssMeasurementsListener$RQ2eiwTgUeDvwCCo5gQM6PdggnE
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssMeasurementsEvent.Callback) obj).onGnssMeasurementsReceived(gnssMeasurementsEvent);
                    }
                });
            }

            @Override // android.location.IGnssMeasurementsListener
            public void onStatusChanged(final int i) {
                GnssMeasurementsListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssMeasurementsListenerManager$GnssMeasurementsListener$box1r7MAHXmX15HpJhTOo85Ddgg
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssMeasurementsEvent.Callback) obj).onStatusChanged(i);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GnssNavigationMessageListenerManager extends AbstractListenerManager<Void, GnssNavigationMessage.Callback> {
        private IGnssNavigationMessageListener mListenerTransport;

        private GnssNavigationMessageListenerManager() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.location.AbstractListenerManager
        public boolean registerService(Void r6) throws RemoteException {
            Preconditions.checkState(this.mListenerTransport == null);
            GnssNavigationMessageListener gnssNavigationMessageListener = new GnssNavigationMessageListener();
            if (!LocationManager.this.mService.addGnssNavigationMessageListener(gnssNavigationMessageListener, LocationManager.this.mContext.getPackageName(), LocationManager.this.mContext.getAttributionTag())) {
                return false;
            }
            this.mListenerTransport = gnssNavigationMessageListener;
            return true;
        }

        @Override // android.location.AbstractListenerManager
        protected void unregisterService() throws RemoteException {
            if (this.mListenerTransport != null) {
                LocationManager.this.mService.removeGnssNavigationMessageListener(this.mListenerTransport);
                this.mListenerTransport = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class GnssNavigationMessageListener extends IGnssNavigationMessageListener.Stub {
            private GnssNavigationMessageListener() {
            }

            @Override // android.location.IGnssNavigationMessageListener
            public void onGnssNavigationMessageReceived(final GnssNavigationMessage gnssNavigationMessage) {
                GnssNavigationMessageListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssNavigationMessageListenerManager$GnssNavigationMessageListener$RJ8_L6f_yY1B3knQ4-nhDgiwh90
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssNavigationMessage.Callback) obj).onGnssNavigationMessageReceived(gnssNavigationMessage);
                    }
                });
            }

            @Override // android.location.IGnssNavigationMessageListener
            public void onStatusChanged(final int i) {
                GnssNavigationMessageListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssNavigationMessageListenerManager$GnssNavigationMessageListener$b_Kr4NIyiS_Irhg5fyKV3QfLsLE
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssNavigationMessage.Callback) obj).onStatusChanged(i);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class GnssAntennaInfoListenerManager extends AbstractListenerManager<Void, GnssAntennaInfo.Listener> {
        private IGnssAntennaInfoListener mListenerTransport;

        private GnssAntennaInfoListenerManager() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.location.AbstractListenerManager
        public boolean registerService(Void r6) throws RemoteException {
            Preconditions.checkState(this.mListenerTransport == null);
            GnssAntennaInfoListener gnssAntennaInfoListener = new GnssAntennaInfoListener();
            if (!LocationManager.this.mService.addGnssAntennaInfoListener(gnssAntennaInfoListener, LocationManager.this.mContext.getPackageName(), LocationManager.this.mContext.getAttributionTag())) {
                return false;
            }
            this.mListenerTransport = gnssAntennaInfoListener;
            return true;
        }

        @Override // android.location.AbstractListenerManager
        protected void unregisterService() throws RemoteException {
            if (this.mListenerTransport != null) {
                LocationManager.this.mService.removeGnssAntennaInfoListener(this.mListenerTransport);
                this.mListenerTransport = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class GnssAntennaInfoListener extends IGnssAntennaInfoListener.Stub {
            private GnssAntennaInfoListener() {
            }

            @Override // android.location.IGnssAntennaInfoListener
            public void onGnssAntennaInfoReceived(final List<GnssAntennaInfo> list) {
                GnssAntennaInfoListenerManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$GnssAntennaInfoListenerManager$GnssAntennaInfoListener$xNKwPP6JItQTPmsN2mi2yidU5Cw
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((GnssAntennaInfo.Listener) obj).onGnssAntennaInfoReceived(list);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class BatchedLocationCallbackManager extends AbstractListenerManager<Void, android.location.BatchedLocationCallback> {
        private IBatchedLocationCallback mListenerTransport;

        private BatchedLocationCallbackManager() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.location.AbstractListenerManager
        public boolean registerService(Void r6) throws RemoteException {
            Preconditions.checkState(this.mListenerTransport == null);
            BatchedLocationCallback batchedLocationCallback = new BatchedLocationCallback();
            if (!LocationManager.this.mService.addGnssBatchingCallback(batchedLocationCallback, LocationManager.this.mContext.getPackageName(), LocationManager.this.mContext.getAttributionTag())) {
                return false;
            }
            this.mListenerTransport = batchedLocationCallback;
            return true;
        }

        @Override // android.location.AbstractListenerManager
        protected void unregisterService() throws RemoteException {
            if (this.mListenerTransport != null) {
                LocationManager.this.mService.removeGnssBatchingCallback();
                this.mListenerTransport = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        class BatchedLocationCallback extends IBatchedLocationCallback.Stub {
            private BatchedLocationCallback() {
            }

            @Override // android.location.IBatchedLocationCallback
            public void onLocationBatch(final List<Location> list) {
                BatchedLocationCallbackManager.this.execute(new Consumer() { // from class: android.location.-$$Lambda$LocationManager$BatchedLocationCallbackManager$BatchedLocationCallback$T0wYlrCWVl5uKNUgPNHr-0Xec7A
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((BatchedLocationCallback) obj).onLocationBatch(list);
                    }
                });
            }
        }
    }

    public static void invalidateLocalLocationEnabledCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_LOCATION_ENABLED_PROPERTY);
    }

    public void disableLocalLocationEnabledCaches() {
        synchronized (this.mLock) {
            this.mLocationEnabledCache = null;
        }
    }
}
