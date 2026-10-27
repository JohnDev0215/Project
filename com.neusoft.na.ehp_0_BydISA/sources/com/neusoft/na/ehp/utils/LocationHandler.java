package com.neusoft.na.ehp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class LocationHandler {
    private static final float BEARING_DEFAULT = 0.0f;
    private static final int PERMISSION_CHECK_RETRY_TIMEOUT = 2000;
    private static final String TAG = "NeuEhpLocation";
    private static final float UPDATE_DISPLACEMENT = 0.1f;
    private static final int UPDATE_INTERVAL = 1000;
    private float LATITUDE_DEFAULT;
    private float LONGITUDE_DEFAULT;
    private Context context;
    private final boolean isOpen;
    private IDataUpdateListener mDataUpdateListener;
    private LocationListener m_AndroidLocationListener;
    private boolean m_IsMoving;
    private LocationManager m_LocationManager;
    private boolean m_NeedToSaveCurrentLocation;
    private OnNmeaMessageListener m_OnNmeaMessageListener;
    private boolean m_Started;
    private boolean m_EnableAutoSaveLocation = true;
    private int m_GpsStatus = 0;
    private boolean firstSendLocation2OcInLifeCycle = true;

    public interface IDataUpdateListener {
        void onGasStatusUpdate(int i);

        void onLocationUpdate(Location location);
    }

    public LocationHandler(Context context) {
        this.LATITUDE_DEFAULT = 0.0f;
        this.LONGITUDE_DEFAULT = 1.0f;
        this.context = context;
        this.isOpen = context.getSharedPreferences("dltLogStatus", 0).getBoolean("openDlt", false);
        SharedPreferences sharedPreferences = context.getSharedPreferences("default_location", 0);
        this.LATITUDE_DEFAULT = sharedPreferences.getFloat("lat", this.LATITUDE_DEFAULT);
        this.LONGITUDE_DEFAULT = sharedPreferences.getFloat("lon", this.LONGITUDE_DEFAULT);
        this.m_LocationManager = (LocationManager) context.getSystemService("location");
        this.m_AndroidLocationListener = new LocationListener() { // from class: com.neusoft.na.ehp.utils.LocationHandler$$ExternalSyntheticLambda0
            @Override // android.location.LocationListener
            public final void onLocationChanged(Location location) {
                this.f$0.m65lambda$new$0$comneusoftnaehputilsLocationHandler(location);
            }
        };
        if (Build.VERSION.SDK_INT >= 24) {
            this.m_OnNmeaMessageListener = new OnNmeaMessageListener() { // from class: com.neusoft.na.ehp.utils.LocationHandler$$ExternalSyntheticLambda1
                @Override // android.location.OnNmeaMessageListener
                public final void onNmeaMessage(String str, long j) {
                    this.f$0.m66lambda$new$1$comneusoftnaehputilsLocationHandler(str, j);
                }
            };
            if (context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                Log.e(TAG, "has no ACCESS_FINE_LOCATION permission");
            } else {
                Log.i(TAG, "addNmeaListener: " + this.m_LocationManager.addNmeaListener(this.m_OnNmeaMessageListener));
            }
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$com-neusoft-na-ehp-utils-LocationHandler, reason: not valid java name */
    /* synthetic */ void m65lambda$new$0$comneusoftnaehputilsLocationHandler(Location location) {
        Log.i(TAG, "m_AndroidLocationListener --> onLocationChanged location");
        commitLocation(location);
    }

    /* JADX INFO: renamed from: lambda$new$1$com-neusoft-na-ehp-utils-LocationHandler, reason: not valid java name */
    /* synthetic */ void m66lambda$new$1$comneusoftnaehputilsLocationHandler(String str, long j) {
        getSpeedFromGNSS(str);
    }

    public void start() {
        if (this.m_Started) {
            return;
        }
        this.m_Started = true;
        if (!this.m_LocationManager.isProviderEnabled("gps")) {
            Log.e(TAG, "GPS isProviderEnabled == false");
        }
        m68x15994316();
        startAutoSaveCurrentLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startLocationUpdates, reason: merged with bridge method [inline-methods] */
    public void m68x15994316() {
        Log.i(TAG, "startLocationUpdates");
        if (this.m_Started) {
            if (this.context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
                Log.i(TAG, "Manifest.permission.ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED");
                new Handler(Looper.myLooper()).postDelayed(new Runnable() { // from class: com.neusoft.na.ehp.utils.LocationHandler$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m68x15994316();
                    }
                }, 2000L);
                return;
            }
            Log.i(TAG, "using AndroidLocationService");
            Location location = null;
            if (this.m_LocationManager.isProviderEnabled("gps")) {
                Location lastKnownLocation = this.m_LocationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    Log.i(TAG, "Last known gnss location");
                    location = lastKnownLocation;
                } else {
                    Log.i(TAG, "last known gnss location invalid,using default location");
                    restoreLastLocation();
                }
            } else {
                Log.i(TAG, "Android GNSS Location provider not enabled");
            }
            if (location != null) {
                Log.i(TAG, "startLocationUpdates --> lastLocation != null");
                commitLocation(location);
            } else {
                Log.i(TAG, "No last known location available,,using default location");
                restoreLastLocation();
            }
            if (this.m_LocationManager.getAllProviders().contains("gps")) {
                Log.i(TAG, "supportedLocationProviders.contains(LocationManager.GPS_PROVIDER --> requestLocationUpdates");
                this.m_LocationManager.requestLocationUpdates("gps", 1000L, 0.1f, this.m_AndroidLocationListener, Looper.myLooper());
            } else {
                Log.i(TAG, "location provider LocationManager.GPS_PROVIDER is not supported on this device");
            }
        }
    }

    private void commitLocation(Location location) {
        Log.i(TAG, "[commitLocation]");
        if (location == null) {
            return;
        }
        boolean z = location != null && ((double) location.getSpeed()) > 0.3d;
        Log.i(TAG, "m_IsMoving == " + this.m_IsMoving + ", isMoving == " + z + ", m_NeedToSaveCurrentLocation == " + this.m_NeedToSaveCurrentLocation);
        if (this.m_IsMoving != z || this.m_NeedToSaveCurrentLocation) {
            if (this.isOpen) {
                Log.i(TAG, "save location to android sh file --> " + location.toString());
            } else {
                Log.i(TAG, "save location to android sh file");
            }
            setCurrentLocation(location.getLatitude(), location.getLongitude(), location.getBearing(), false);
            this.m_IsMoving = z;
            this.m_NeedToSaveCurrentLocation = false;
        }
        Log.i(TAG, "firstSendLocation2OcInLifeCycle == " + this.firstSendLocation2OcInLifeCycle);
        IDataUpdateListener iDataUpdateListener = this.mDataUpdateListener;
        if (iDataUpdateListener != null) {
            if (this.firstSendLocation2OcInLifeCycle) {
                this.firstSendLocation2OcInLifeCycle = false;
                Log.i(TAG, "firstSendLocation2OcInLifeCycle save location.");
                setCurrentLocation(location.getLatitude(), location.getLongitude(), location.getBearing(), false);
                return;
            }
            iDataUpdateListener.onLocationUpdate(location);
        }
    }

    private void getSpeedFromGNSS(String str) {
        Log.d(TAG, "getSpeedFromGNSS: " + str);
        String[] strArrSplit = str.split(",");
        if (!strArrSplit[0].endsWith("GSA") || TextUtils.isEmpty(strArrSplit[2])) {
            return;
        }
        int i = Integer.parseInt(strArrSplit[2]);
        int i2 = (i == 2 || i == 3) ? i : 0;
        if (this.m_GpsStatus != i2) {
            this.m_GpsStatus = i2;
            this.m_NeedToSaveCurrentLocation = true;
        }
        IDataUpdateListener iDataUpdateListener = this.mDataUpdateListener;
        if (iDataUpdateListener != null) {
            iDataUpdateListener.onGasStatusUpdate(this.m_GpsStatus);
        }
    }

    private void setCurrentLocation(double d, double d2, float f, boolean z) {
        Log.i(TAG, "[setCurrentLocation]");
        Location location = new Location(ExtensionRequestData.EMPTY_VALUE);
        SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("CurrentLocation", 0).edit();
        editorEdit.putFloat("latitude", (float) d);
        editorEdit.apply();
        editorEdit.putFloat("longitude", (float) d2);
        editorEdit.apply();
        editorEdit.putFloat("bearing", f);
        editorEdit.apply();
        if (z) {
            location.setBearing(f);
            location.setLatitude(d);
            location.setLongitude(d2);
            location.setSpeed(0.0f);
            IDataUpdateListener iDataUpdateListener = this.mDataUpdateListener;
            if (iDataUpdateListener != null) {
                iDataUpdateListener.onLocationUpdate(location);
            }
            Log.i(TAG, "setCurrentLocation m_LocationSubject.onNext()");
        }
    }

    private void restoreLastLocation() {
        Location location = new Location(ExtensionRequestData.EMPTY_VALUE);
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("CurrentLocation", 0);
        location.setLatitude(sharedPreferences.getFloat("latitude", this.LATITUDE_DEFAULT));
        location.setLongitude(sharedPreferences.getFloat("longitude", this.LONGITUDE_DEFAULT));
        location.setBearing(sharedPreferences.getFloat("bearing", 0.0f));
        IDataUpdateListener iDataUpdateListener = this.mDataUpdateListener;
        if (iDataUpdateListener != null) {
            iDataUpdateListener.onLocationUpdate(location);
        }
    }

    private void startAutoSaveCurrentLocation() {
        new Thread(new Runnable() { // from class: com.neusoft.na.ehp.utils.LocationHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m67x506f2588();
            }
        }).start();
    }

    /* JADX INFO: renamed from: lambda$startAutoSaveCurrentLocation$3$com-neusoft-na-ehp-utils-LocationHandler, reason: not valid java name */
    /* synthetic */ void m67x506f2588() {
        int i;
        InterruptedException e;
        Log.i(TAG, "startAutoSaveCurrentLocation running");
        while (true) {
            int i2 = 0;
            while (this.m_EnableAutoSaveLocation) {
                i2++;
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    i = i2;
                    e = e2;
                }
                if (i2 > 600 && this.m_IsMoving) {
                    try {
                        this.m_IsMoving = false;
                        this.m_NeedToSaveCurrentLocation = true;
                        Log.i(TAG, "startAutoSaveCurrentLocation:setCurrentLocation.");
                        break;
                    } catch (InterruptedException e3) {
                        e = e3;
                        i = 0;
                        e.printStackTrace();
                        i2 = i;
                    }
                } else if (!this.m_IsMoving) {
                    break;
                }
            }
            return;
        }
    }

    public LocationHandler setDataUpdateListener(IDataUpdateListener iDataUpdateListener) {
        this.mDataUpdateListener = iDataUpdateListener;
        return this;
    }
}
