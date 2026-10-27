package com.neusoft.na.ocservice;

import android.car.Car;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.bydux.api.common.tools.util.ListUtils;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.neusoft.na.common.BasicLifeCycleController;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.LocationUtils;
import com.neusoft.na.ocservice.util.SystemUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogging;
import org.threeten.bp.Duration;
import rx.Observable;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: LocationHandler.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u0000 h2\u00020\u0001:\u0003hijB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u00106\u001a\u0002072\u0006\u00108\u001a\u0002052\u0006\u00109\u001a\u000205H\u0002J\b\u0010:\u001a\u000207H\u0002J\u000e\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0<J\u000e\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0<J\u001a\u0010>\u001a\u0002072\b\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020BH\u0002J\u0018\u0010C\u001a\u0002072\u0006\u0010D\u001a\u00020\u00132\u0006\u0010E\u001a\u00020\u0013H\u0002J\u001a\u0010F\u001a\u0002072\b\u0010G\u001a\u0004\u0018\u00010H2\u0006\u0010I\u001a\u00020\u001fH\u0016J\u0006\u0010J\u001a\u000207J\u0006\u0010K\u001a\u000207J\u0010\u0010L\u001a\u0002072\u0006\u0010M\u001a\u00020NH\u0016J\u0006\u0010O\u001a\u000207J\b\u0010P\u001a\u000207H\u0002J(\u0010Q\u001a\u0002072\u0006\u0010R\u001a\u00020\u00062\u0006\u0010S\u001a\u00020\u00062\u0006\u0010T\u001a\u00020\u00062\b\b\u0002\u0010U\u001a\u00020\rJ\u000e\u0010V\u001a\u0002072\u0006\u0010W\u001a\u00020\rJ\u0010\u0010X\u001a\u0002072\u0006\u0010Y\u001a\u00020\rH\u0002J\u000e\u0010Z\u001a\u0002072\u0006\u0010[\u001a\u000205J\b\u0010\\\u001a\u000207H\u0007J\b\u0010]\u001a\u000207H\u0002J\b\u0010^\u001a\u000207H\u0002J\b\u0010_\u001a\u000207H\u0002J\b\u0010`\u001a\u000207H\u0002J\u0006\u0010a\u001a\u000207J\b\u0010b\u001a\u000207H\u0002J\b\u0010c\u001a\u000207H\u0002J\b\u0010d\u001a\u000207H\u0002J\b\u0010e\u001a\u000207H\u0002J\u0012\u0010f\u001a\u00020\r2\b\u0010g\u001a\u0004\u0018\u00010\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u00102\u001a\u000603R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006k"}, d2 = {"Lcom/neusoft/na/ocservice/LocationHandler;", "Landroid/hardware/SensorEventListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "LATITUDE_DEFAULT", "", "LONGITUDE_DEFAULT", "activateLocation", "Landroid/location/Location;", "androidSensorManager", "Landroid/hardware/SensorManager;", "bydDrEnabled", "", "getContext", "()Landroid/content/Context;", OneCoreService.PAYLOAD_GPS_LOCATION_CAN_UPDATE, "hasSpeedLastLocation", "mAccelerometerReading", "", "mLastViewerOrientation", "Lcom/neusoft/na/ocservice/LocationHandler$ViewerOrientation;", "mMagnetometerReading", "m_AndroidLocationListener", "Landroid/location/LocationListener;", "m_EnableAutoSaveLocation", "m_GMSLocationCallback", "Lcom/google/android/gms/location/LocationCallback;", "m_GMSLocationProvider", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "m_GpsStatus", "", "m_GpsStatusSubject", "Lrx/subjects/PublishSubject;", "m_IsMoving", "m_LocationManager", "Landroid/location/LocationManager;", "m_LocationRequest", "Lcom/google/android/gms/location/LocationRequest;", "m_LocationSettingRequest", "Lcom/google/android/gms/location/LocationSettingsRequest;", "m_LocationSubject", "m_NeedToSaveCurrentLocation", "m_NmeaListener", "Landroid/location/GpsStatus$NmeaListener;", "m_OnNmeaMessageListener", "Landroid/location/OnNmeaMessageListener;", "m_Started", "m_UseMovementHeading", "persistGpsMode", "staticHold", "Lcom/neusoft/na/ocservice/LocationHandler$StaticHold;", "systemAdapterCarSpeed", "", "calcViewerOrientation", "", "pitch", "roll", "commitLocation", "getGpsStatusObj", "Lrx/Observable;", "getLocationObs", "getSpeedFromGNSS", "message", "", "timestamp", "", "lowPassFilter", "input", "output", "onAccuracyChanged", "p0", "Landroid/hardware/Sensor;", "p1", "onPause", "onResume", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "registerNmeaMessageListener", "restoreLastLocation", "setCurrentLocation", "latitude", "longitude", "bearing", "needToPublish", "setGpsLocationCanUpdate", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "setHeadingSource", "useMovementHeading", "setSystemAdapterCarSpeed", "carGearSpeed", "start", "startActivateLocationSocketService", "startAutoSaveCurrentLocation", "startLocationUpdates", "startOrientationUpdates", "stop", "stopAutoSaveCurrentLocation", "stopLocationUpdates", "stopOrientationUpdates", "updateStandstillHeading", "useAsNewLocation", "location", "Companion", "StaticHold", "ViewerOrientation", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LocationHandler implements SensorEventListener {
    public static final float BEARING_DEFAULT = 0.0f;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Duration FASTEST_UPDATE_INTERVAL = Duration.ofSeconds(1).dividedBy(2);
    private static final Duration PERMISSION_CHECK_RETRY_TIMEOUT = Duration.ofSeconds(2);
    private static final String TAG = "LocationHandler";
    private static final float UPDATE_DISPLACEMENT = 0.0f;
    private static final long UPDATE_INTERVAL = 0;
    private float LATITUDE_DEFAULT;
    private float LONGITUDE_DEFAULT;
    private Location activateLocation;
    private SensorManager androidSensorManager;
    private boolean bydDrEnabled;
    private final Context context;
    private boolean gpsLocationCanUpdate;
    private Location hasSpeedLastLocation;
    private final float[] mAccelerometerReading;
    private ViewerOrientation mLastViewerOrientation;
    private final float[] mMagnetometerReading;
    private final LocationListener m_AndroidLocationListener;
    private boolean m_EnableAutoSaveLocation;
    private final LocationCallback m_GMSLocationCallback;
    private final FusedLocationProviderClient m_GMSLocationProvider;
    private int m_GpsStatus;
    private final PublishSubject<Integer> m_GpsStatusSubject;
    private boolean m_IsMoving;
    private final LocationManager m_LocationManager;
    private final LocationRequest m_LocationRequest;
    private final LocationSettingsRequest m_LocationSettingRequest;
    private final PublishSubject<Location> m_LocationSubject;
    private boolean m_NeedToSaveCurrentLocation;
    private GpsStatus.NmeaListener m_NmeaListener;
    private OnNmeaMessageListener m_OnNmeaMessageListener;
    private boolean m_Started;
    private boolean m_UseMovementHeading;
    private boolean persistGpsMode;
    private final StaticHold staticHold;
    private double systemAdapterCarSpeed;

    /* JADX INFO: compiled from: LocationHandler.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/neusoft/na/ocservice/LocationHandler$ViewerOrientation;", "", "(Ljava/lang/String;I)V", "UNKNOWN", "PORTRAIT", "PORTRAIT_REVERSE", "LANDSCAPE_LEFT", "LANDSCAPE_RIGHT", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private enum ViewerOrientation {
        UNKNOWN,
        PORTRAIT,
        PORTRAIT_REVERSE,
        LANDSCAPE_LEFT,
        LANDSCAPE_RIGHT
    }

    /* JADX INFO: compiled from: LocationHandler.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ViewerOrientation.values().length];
            try {
                iArr[ViewerOrientation.PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ViewerOrientation.PORTRAIT_REVERSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ViewerOrientation.LANDSCAPE_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ViewerOrientation.LANDSCAPE_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor p0, int p1) {
    }

    public LocationHandler(Context context) throws ClassNotFoundException {
        FusedLocationProviderClient fusedLocationProviderClient;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.LATITUDE_DEFAULT = 3.14787f;
        this.LONGITUDE_DEFAULT = 101.694f;
        Object systemService = context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.m_LocationManager = (LocationManager) systemService;
        PublishSubject<Location> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.m_LocationSubject = publishSubjectCreate;
        PublishSubject<Integer> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.m_GpsStatusSubject = publishSubjectCreate2;
        this.m_EnableAutoSaveLocation = true;
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
            INSTANCE.getLogger().debug("Using googlePlayServices location provider");
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        } else {
            INSTANCE.getLogger().debug("No GooglePlayServices location provider available, falling back to internal location provider");
            fusedLocationProviderClient = null;
        }
        this.m_GMSLocationProvider = fusedLocationProviderClient;
        this.staticHold = new StaticHold();
        this.gpsLocationCanUpdate = true;
        if (CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            Float[] pitch = LocationUtils.INSTANCE.getPitch(context);
            Float f = pitch[0];
            Intrinsics.checkNotNull(f);
            this.LATITUDE_DEFAULT = f.floatValue();
            Float f2 = pitch[1];
            Intrinsics.checkNotNull(f2);
            this.LONGITUDE_DEFAULT = f2.floatValue();
        }
        if (CheckBoardKt.isByd()) {
            this.bydDrEnabled = Intrinsics.areEqual(new SystemUtils().getSystemPropertiesValue("persist.sys.byd.dr.enabled"), "true");
            Log.i(TAG, "bydDrEnabled == " + this.bydDrEnabled);
        } else if (CheckBoardKt.isByd8155f()) {
            String systemPropertiesValue = new SystemUtils().getSystemPropertiesValue("persist.vendor.byd.gnss.mode");
            Log.i(TAG, "persistVendorBydGnssMode == " + systemPropertiesValue);
            this.persistGpsMode = Intrinsics.areEqual(systemPropertiesValue, FileUtils.DEFAULT_VERSION);
            Log.i(TAG, "persistGpsMode == " + this.persistGpsMode);
        }
        this.m_GMSLocationCallback = new LocationCallback() { // from class: com.neusoft.na.ocservice.LocationHandler.1
            @Override // com.google.android.gms.location.LocationCallback
            public void onLocationResult(LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                super.onLocationResult(locationResult);
                Location lastLocation = locationResult.getLastLocation();
                if (lastLocation != null) {
                    LocationHandler locationHandler = LocationHandler.this;
                    if (lastLocation.hasSpeed()) {
                        LocationHandler.INSTANCE.getLogger().debug("location hasSpeed is true");
                    } else {
                        LocationHandler.INSTANCE.getLogger().debug("location hasSpeed is false");
                    }
                    LocationHandler.INSTANCE.getLogger().debug("m_GMSLocationCallback [google]: new Location");
                    locationHandler.setHeadingSource((((double) lastLocation.getSpeed()) > 3.0d) & (lastLocation.getAccuracy() < 30.0f) & lastLocation.hasBearing());
                    if (locationHandler.staticHold.push(lastLocation, locationHandler.m_UseMovementHeading)) {
                        Log.i(LocationHandler.TAG, "Trigger update location");
                        locationHandler.commitLocation();
                    }
                }
            }
        };
        this.m_AndroidLocationListener = new LocationListener() { // from class: com.neusoft.na.ocservice.LocationHandler.2
            @Override // android.location.LocationListener
            public void onProviderDisabled(String provider) {
                Intrinsics.checkNotNullParameter(provider, "provider");
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String provider) {
                Intrinsics.checkNotNullParameter(provider, "provider");
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override // android.location.LocationListener
            public void onLocationChanged(final Location location) {
                Intrinsics.checkNotNullParameter(location, "location");
                if (CheckBoardKt.isByd8155f() && !LocationHandler.this.gpsLocationCanUpdate) {
                    Log.i(LocationHandler.TAG, "shield onLocationChanged");
                    return;
                }
                LocationHandler locationHandler = LocationHandler.this;
                if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
                    Log.i(LocationHandler.TAG, "LocationService [android]: new Location --> " + location.getLatitude() + ", " + location.getLongitude() + ", " + location.getSpeed() + ", " + location.getBearing());
                    LocationHandler.INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.ocservice.LocationHandler$2$onLocationChanged$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return "LocationService [android]: new Location --> " + location.getLatitude() + ", " + location.getLongitude() + ", " + location.getSpeed() + ", " + location.getBearing();
                        }
                    });
                }
                if (locationHandler.useAsNewLocation(location)) {
                    Log.i(LocationHandler.TAG, "m_AndroidLocationListener --> onLocationChanged location");
                    locationHandler.commitLocation();
                }
                locationHandler.activateLocation = location;
            }
        };
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(Duration.ofSeconds(1L).toMillis());
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL.toMillis());
        locationRequest.setPriority(100);
        this.m_LocationRequest = locationRequest;
        LocationSettingsRequest locationSettingsRequestBuild = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        Intrinsics.checkNotNullExpressionValue(locationSettingsRequestBuild, "Builder().addLocationReq…_LocationRequest).build()");
        this.m_LocationSettingRequest = locationSettingsRequestBuild;
        startActivateLocationSocketService();
        this.mAccelerometerReading = new float[3];
        this.mMagnetometerReading = new float[3];
        this.mLastViewerOrientation = ViewerOrientation.UNKNOWN;
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX INFO: compiled from: LocationHandler.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/neusoft/na/ocservice/LocationHandler$Companion;", "Lmu/KLogging;", "()V", "BEARING_DEFAULT", "", "FASTEST_UPDATE_INTERVAL", "Lorg/threeten/bp/Duration;", "kotlin.jvm.PlatformType", "PERMISSION_CHECK_RETRY_TIMEOUT", "TAG", "", "UPDATE_DISPLACEMENT", "UPDATE_INTERVAL", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void registerNmeaMessageListener() {
        Log.i(TAG, "[registerNmeaMessageListener]");
        if (this.m_OnNmeaMessageListener == null) {
            this.m_OnNmeaMessageListener = new OnNmeaMessageListener() { // from class: com.neusoft.na.ocservice.LocationHandler$$ExternalSyntheticLambda1
                @Override // android.location.OnNmeaMessageListener
                public final void onNmeaMessage(String str, long j) {
                    LocationHandler.registerNmeaMessageListener$lambda$1(this.f$0, str, j);
                }
            };
            if (ActivityCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                Log.e(TAG, "has no ACCESS_FINE_LOCATION permission");
                return;
            }
            LocationManager locationManager = this.m_LocationManager;
            OnNmeaMessageListener onNmeaMessageListener = this.m_OnNmeaMessageListener;
            Intrinsics.checkNotNull(onNmeaMessageListener);
            Log.i(TAG, "addNmeaSuccess == " + locationManager.addNmeaListener(onNmeaMessageListener));
            return;
        }
        Log.i(TAG, "ignore duplicate bindings");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerNmeaMessageListener$lambda$1(LocationHandler this$0, String str, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getSpeedFromGNSS(str, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean useAsNewLocation(Location location) {
        boolean z = false;
        if (location == null) {
            return false;
        }
        Location mLocation = this.staticHold.getMLocation();
        if (location.hasSpeed()) {
            INSTANCE.getLogger().debug("location hasSpeed is true");
        } else {
            INSTANCE.getLogger().debug("location hasSpeed is false");
        }
        if (CheckBoardKt.isByd8155f()) {
            this.staticHold.push(location, true);
            return true;
        }
        if (Intrinsics.areEqual(location.getProvider(), "gps")) {
            boolean zHasBearing = location.hasBearing() & (((double) Math.abs(location.getBearing())) >= 1.0E-4d) & (((double) location.getSpeed()) > 3.0d) & (location.getAccuracy() < 30.0f);
            Log.i(TAG, "useLocationHeading == " + zHasBearing);
            if (mLocation == null) {
                INSTANCE.getLogger().debug("use new GNSS location - previous undefined");
            } else if (!location.hasAccuracy()) {
                INSTANCE.getLogger().debug("skip GNSS location - no accuracy");
                return false;
            }
            z = zHasBearing;
        } else if (Intrinsics.areEqual(location.getProvider(), "network") && !CheckBoardKt.isByd() && !CheckBoardKt.isByd8155f()) {
            if (mLocation == null) {
                INSTANCE.getLogger().debug("use the new network location - previous undefined");
            } else if (Intrinsics.areEqual(mLocation.getProvider(), "gps")) {
                if (location.getTime() - mLocation.getTime() < 10000) {
                    INSTANCE.getLogger().debug("skip network location - last GNSS ms old");
                    return false;
                }
            } else if (Intrinsics.areEqual(mLocation.getProvider(), "network") && mLocation.hasAccuracy() && location.getAccuracy() > 2 * mLocation.getAccuracy()) {
                INSTANCE.getLogger().debug("skip network location due to accuracy");
                return false;
            }
        }
        setHeadingSource(z);
        return this.staticHold.push(location, this.m_UseMovementHeading);
    }

    public final void start() {
        if (this.m_Started) {
            return;
        }
        this.m_Started = true;
        startLocationUpdates();
        startOrientationUpdates();
        if (CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            startAutoSaveCurrentLocation();
        }
    }

    public final void stop() {
        this.m_Started = false;
        stopLocationUpdates();
        stopOrientationUpdates();
        if (CheckBoardKt.isProtonMC2() || CheckBoardKt.isByd() || CheckBoardKt.isByd8155f()) {
            stopAutoSaveCurrentLocation();
        }
    }

    public final void onPause() {
        INSTANCE.getLogger().debug("onPause()");
        stopOrientationUpdates();
    }

    public final void onResume() {
        INSTANCE.getLogger().debug("onResume");
        startOrientationUpdates();
    }

    public final Observable<Location> getLocationObs() {
        Observable<Location> observableAsObservable = this.m_LocationSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "m_LocationSubject.asObservable()");
        return observableAsObservable;
    }

    public final Observable<Integer> getGpsStatusObj() {
        Observable<Integer> observableAsObservable = this.m_GpsStatusSubject.asObservable();
        Intrinsics.checkNotNullExpressionValue(observableAsObservable, "m_GpsStatusSubject.asObservable()");
        return observableAsObservable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x019f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void startLocationUpdates() {
        /*
            Method dump skipped, instruction units count: 488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.LocationHandler.startLocationUpdates():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startLocationUpdates$lambda$2(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startLocationUpdates$lambda$3(LocationHandler this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Log.i(TAG, "addOnFailureListener");
        INSTANCE.getLogger().error("onFailureListener");
        if (!CheckBoardKt.isProtonMC2() && !CheckBoardKt.isByd() && !CheckBoardKt.isByd8155f()) {
            this$0.m_LocationSubject.onNext(null);
        } else {
            this$0.restoreLastLocation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startLocationUpdates$lambda$4(LocationHandler this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.i(TAG, "addOnCanceledListener");
        INSTANCE.getLogger().debug("onCancelListener");
        if (!CheckBoardKt.isProtonMC2() && !CheckBoardKt.isByd() && !CheckBoardKt.isByd8155f()) {
            this$0.m_LocationSubject.onNext(null);
        } else {
            this$0.restoreLastLocation();
        }
    }

    private final void stopLocationUpdates() {
        FusedLocationProviderClient fusedLocationProviderClient = this.m_GMSLocationProvider;
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(this.m_GMSLocationCallback);
        } else {
            LocationManager locationManager = this.m_LocationManager;
            if (locationManager != null) {
                locationManager.removeUpdates(this.m_AndroidLocationListener);
            }
        }
        OnNmeaMessageListener onNmeaMessageListener = this.m_OnNmeaMessageListener;
        if (onNmeaMessageListener != null) {
            LocationManager locationManager2 = this.m_LocationManager;
            Intrinsics.checkNotNull(onNmeaMessageListener);
            locationManager2.removeNmeaListener(onNmeaMessageListener);
            this.m_OnNmeaMessageListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void commitLocation() {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.LocationHandler.commitLocation():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setHeadingSource(boolean useMovementHeading) {
        if (useMovementHeading != this.m_UseMovementHeading) {
            this.m_UseMovementHeading = useMovementHeading;
            Log.i(TAG, "[setHeadingSource] m_UseMovementHeading == " + this.m_UseMovementHeading);
        }
    }

    private final void startOrientationUpdates() {
        if (this.androidSensorManager == null) {
            SensorManager sensorManager = (SensorManager) this.context.getSystemService(Car.SENSOR_SERVICE);
            this.androidSensorManager = sensorManager;
            if (sensorManager != null) {
                INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.ocservice.LocationHandler.startOrientationUpdates.1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "androidSensorManager available";
                    }
                });
            } else {
                INSTANCE.getLogger().debug(new Function0<Object>() { // from class: com.neusoft.na.ocservice.LocationHandler.startOrientationUpdates.2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return "androidSensorManager not available";
                    }
                });
            }
        }
        SensorManager sensorManager2 = this.androidSensorManager;
        if (sensorManager2 != null) {
            INSTANCE.getLogger().debug("startOrientationHandler ...");
            Sensor defaultSensor = sensorManager2.getDefaultSensor(1);
            if (defaultSensor != null) {
                Intrinsics.checkNotNullExpressionValue(defaultSensor, "getDefaultSensor(Sensor.TYPE_ACCELEROMETER)");
                sensorManager2.registerListener(this, defaultSensor, 3, 2);
            }
            if (!CheckBoardKt.isByd8155f()) {
                Sensor defaultSensor2 = sensorManager2.getDefaultSensor(2);
                if (defaultSensor2 != null) {
                    Intrinsics.checkNotNullExpressionValue(defaultSensor2, "getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)");
                    sensorManager2.registerListener(this, defaultSensor2, 3, 2);
                    return;
                }
                return;
            }
            Log.i(TAG, "register Sensor.TYPE_MAGNETIC_FIELD");
        }
    }

    private final void stopOrientationUpdates() {
        SensorManager sensorManager = this.androidSensorManager;
        if (sensorManager != null) {
            INSTANCE.getLogger().debug("stopOrientationUpdates ...");
            sensorManager.unregisterListener(this);
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.sensor.getType() == 1) {
            float[] fArr = event.values;
            Intrinsics.checkNotNullExpressionValue(fArr, "event.values");
            lowPassFilter(fArr, this.mAccelerometerReading);
        } else if (event.sensor.getType() == 2) {
            float[] fArr2 = event.values;
            Intrinsics.checkNotNullExpressionValue(fArr2, "event.values");
            lowPassFilter(fArr2, this.mMagnetometerReading);
            updateStandstillHeading();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0083 A[PHI: r0
  0x0083: PHI (r0v12 float) = (r0v11 float), (r0v21 float) binds: [B:21:0x0081, B:18:0x0077] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateStandstillHeading() {
        /*
            r14 = this;
            java.lang.String r0 = "[updateStandstillHeading]"
            java.lang.String r1 = "LocationHandler"
            android.util.Log.i(r1, r0)
            boolean r0 = r14.m_UseMovementHeading
            if (r0 != 0) goto L9f
            java.lang.String r0 = "[updateStandstillHeading] --> do fix"
            android.util.Log.i(r1, r0)
            r0 = 9
            float[] r0 = new float[r0]
            r1 = 3
            float[] r2 = new float[r1]
            float[] r3 = r14.mAccelerometerReading
            float[] r4 = r14.mMagnetometerReading
            r5 = 0
            boolean r3 = android.hardware.SensorManager.getRotationMatrix(r0, r5, r3, r4)
            if (r3 == 0) goto L93
            android.hardware.SensorManager.getOrientation(r0, r2)
            r0 = 0
            r0 = r2[r0]
            double r3 = (double) r0
            r5 = 4633260481411531256(0x404ca5dc1a63c1f8, double:57.29577951308232)
            double r3 = r3 * r5
            r0 = 1
            r7 = r2[r0]
            double r7 = (double) r7
            double r7 = r7 * r5
            r9 = 2
            r2 = r2[r9]
            double r10 = (double) r2
            double r10 = r10 * r5
            r14.calcViewerOrientation(r7, r10)
            com.neusoft.na.ocservice.LocationHandler$ViewerOrientation r2 = r14.mLastViewerOrientation
            int[] r5 = com.neusoft.na.ocservice.LocationHandler.WhenMappings.$EnumSwitchMapping$0
            int r2 = r2.ordinal()
            r2 = r5[r2]
            r5 = 4640537203540230144(0x4066800000000000, double:180.0)
            r7 = 4636033603912859648(0x4056800000000000, double:90.0)
            r12 = 4645040803167600640(0x4076800000000000, double:360.0)
            if (r2 == r0) goto L7a
            if (r2 == r9) goto L6a
            r0 = 90
            if (r2 == r1) goto L64
            r1 = 4
            if (r2 == r1) goto L61
            return
        L61:
            double r0 = (double) r0
            double r3 = r3 + r0
            goto L66
        L64:
            double r0 = (double) r0
            double r3 = r3 - r0
        L66:
            double r3 = r3 + r12
            double r3 = r3 % r12
            float r0 = (float) r3
            goto L87
        L6a:
            r0 = 180(0xb4, float:2.52E-43)
            double r0 = (double) r0
            double r3 = r3 - r0
            double r3 = r3 + r12
            double r3 = r3 % r12
            float r0 = (float) r3
            double r1 = java.lang.Math.abs(r10)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 < 0) goto L87
            goto L83
        L7a:
            float r0 = (float) r3
            double r1 = java.lang.Math.abs(r10)
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 < 0) goto L87
        L83:
            double r0 = (double) r0
            double r0 = r0 + r5
            double r0 = r0 % r12
            float r0 = (float) r0
        L87:
            com.neusoft.na.ocservice.LocationHandler$StaticHold r1 = r14.staticHold
            boolean r0 = r1.push(r0)
            if (r0 == 0) goto La4
            r14.commitLocation()
            goto La4
        L93:
            com.neusoft.na.ocservice.LocationHandler$Companion r0 = com.neusoft.na.ocservice.LocationHandler.INSTANCE
            mu.KLogger r0 = r0.getLogger()
            java.lang.String r1 = "SensorManager.getRotationMatrix failed"
            r0.warn(r1)
            goto La4
        L9f:
            java.lang.String r0 = "[updateStandstillHeading] --> do not fix"
            android.util.Log.i(r1, r0)
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.LocationHandler.updateStandstillHeading():void");
    }

    private final void calcViewerOrientation(double pitch, double roll) {
        ViewerOrientation viewerOrientation;
        double d = 180.0d - roll;
        if (((pitch < -10.0d) & (roll > 90.0d) & (Math.abs(pitch) > Math.abs(d))) || (((pitch > (-10.0d) ? 1 : (pitch == (-10.0d) ? 0 : -1)) < 0) & ((Math.abs(pitch) > Math.abs(roll) ? 1 : (Math.abs(pitch) == Math.abs(roll) ? 0 : -1)) > 0))) {
            viewerOrientation = ViewerOrientation.PORTRAIT;
        } else {
            double d2 = roll + 180.0d;
            if (((pitch > 10.0d) & (roll < -90.0d) & (Math.abs(pitch) > Math.abs(d2))) || (((pitch > 10.0d ? 1 : (pitch == 10.0d ? 0 : -1)) > 0) & ((Math.abs(pitch) > Math.abs(roll) ? 1 : (Math.abs(pitch) == Math.abs(roll) ? 0 : -1)) > 0))) {
                viewerOrientation = ViewerOrientation.PORTRAIT_REVERSE;
            } else {
                if (((roll < -45.0d) & (roll > -90.0d) & (Math.abs(roll) > Math.abs(pitch))) || ((roll < -90.0d) & (d2 > 45.0d))) {
                    viewerOrientation = ViewerOrientation.LANDSCAPE_LEFT;
                } else {
                    if (((roll > 45.0d) & (roll < 90.0d) & (Math.abs(roll) > Math.abs(pitch))) | ((d > 45.0d) & (roll > 90.0d))) {
                        viewerOrientation = ViewerOrientation.LANDSCAPE_RIGHT;
                    } else {
                        viewerOrientation = this.mLastViewerOrientation;
                    }
                }
            }
        }
        if (viewerOrientation != this.mLastViewerOrientation) {
            this.mLastViewerOrientation = viewerOrientation;
        }
    }

    private final void lowPassFilter(float[] input, float[] output) {
        int length = input.length;
        for (int i = 0; i < length; i++) {
            float f = output[i];
            output[i] = f + (0.5f * (input[i] - f));
        }
    }

    /* JADX INFO: compiled from: LocationHandler.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\nJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/neusoft/na/ocservice/LocationHandler$StaticHold;", "", "(Lcom/neusoft/na/ocservice/LocationHandler;)V", "mHeading", "", "mLocation", "Landroid/location/Location;", "getHeading", "getLocation", "push", "", "location", "useHeading", "heading", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private final class StaticHold {
        private float mHeading;
        private Location mLocation;

        public StaticHold() {
        }

        public final boolean push(Location location, boolean useHeading) {
            Log.i(LocationHandler.TAG, "[StaticHold] push(location: Location?, useHeading: Boolean) --> " + useHeading);
            boolean z = true;
            boolean z2 = false;
            if (location != null) {
                if (useHeading) {
                    Log.i(LocationHandler.TAG, "[StaticHold] push(location: Location?, useHeading: Boolean) --> update bearing");
                    this.mHeading = location.getBearing();
                    z2 = true;
                } else {
                    Log.i(LocationHandler.TAG, "[StaticHold] push(location: Location?, useHeading: Boolean) --> use last time bearing");
                    location.setBearing(this.mHeading);
                }
            }
            Location location2 = this.mLocation;
            if (location2 != null && (location2 == null || location == null || location2.distanceTo(location) < 0.0f)) {
                z = z2;
            }
            this.mLocation = location;
            LocationHandler.INSTANCE.getLogger().debug("update location");
            return z;
        }

        public final boolean push(float heading) {
            Log.i(LocationHandler.TAG, "[StaticHold] push(heading: Float) --> " + heading);
            float fAbs = Math.abs(heading - this.mHeading);
            if (fAbs > 180.0f) {
                fAbs = 360.0f - fAbs;
            }
            if (fAbs <= 10.0f) {
                return false;
            }
            if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
                Log.i(LocationHandler.TAG, "USE NEW HEADING - lastHeading=" + this.mHeading + ", diff=" + fAbs);
            }
            this.mHeading = heading;
            Location location = this.mLocation;
            if (location == null) {
                return true;
            }
            location.setBearing(heading);
            return true;
        }

        /* JADX INFO: renamed from: getHeading, reason: from getter */
        public final float getMHeading() {
            return this.mHeading;
        }

        /* JADX INFO: renamed from: getLocation, reason: from getter */
        public final Location getMLocation() {
            return this.mLocation;
        }
    }

    private final void getSpeedFromGNSS(String message, long timestamp) {
        if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
            Log.i(TAG, "[getSpeedFromGNSS] --> " + message);
        }
        try {
            Intrinsics.checkNotNull(message);
            List listSplit$default = StringsKt.split$default((CharSequence) message, new String[]{ListUtils.DEFAULT_JOIN_SEPARATOR}, false, 0, 6, (Object) null);
            if (StringsKt.endsWith$default((String) listSplit$default.get(0), "GSA", false, 2, (Object) null)) {
                if (((CharSequence) listSplit$default.get(2)).length() > 0) {
                    int i = Integer.parseInt((String) listSplit$default.get(2));
                    if (i != 2 && i != 3) {
                        i = 0;
                    }
                    if (this.m_GpsStatus != i) {
                        this.m_GpsStatus = i;
                        this.m_NeedToSaveCurrentLocation = true;
                        if (this.staticHold.getMLocation() != null) {
                            Log.i(TAG, "getSpeedFromGNSS");
                            Location mLocation = this.staticHold.getMLocation();
                            Intrinsics.checkNotNull(mLocation);
                            float latitude = (float) mLocation.getLatitude();
                            Location mLocation2 = this.staticHold.getMLocation();
                            Intrinsics.checkNotNull(mLocation2);
                            float longitude = (float) mLocation2.getLongitude();
                            Location mLocation3 = this.staticHold.getMLocation();
                            Intrinsics.checkNotNull(mLocation3);
                            setCurrentLocation(latitude, longitude, mLocation3.getBearing(), false);
                        }
                    }
                    this.m_GpsStatusSubject.onNext(Integer.valueOf(this.m_GpsStatus));
                }
            }
        } catch (RemoteException unused) {
            BasicLifeCycleController.INSTANCE.getLogger().error("failed to getSpeedFromGNSS!");
            Log.e(TAG, "failed to getSpeedFromGNSS!");
        }
    }

    public static /* synthetic */ void setCurrentLocation$default(LocationHandler locationHandler, float f, float f2, float f3, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        locationHandler.setCurrentLocation(f, f2, f3, z);
    }

    public final void setCurrentLocation(float latitude, float longitude, float bearing, boolean needToPublish) {
        Log.i(TAG, "[setCurrentLocation]");
        Location location = new Location("");
        SharedPreferences.Editor editorEdit = this.context.getSharedPreferences("CurrentLocation", 0).edit();
        Intrinsics.checkNotNullExpressionValue(editorEdit, "this.context.getSharedPr…text.MODE_PRIVATE).edit()");
        editorEdit.putFloat("latitude", latitude);
        editorEdit.apply();
        editorEdit.putFloat("longitude", longitude);
        editorEdit.apply();
        editorEdit.putFloat("bearing", bearing);
        editorEdit.apply();
        if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
            Log.i(TAG, "lat == " + latitude + ", lon == " + longitude);
        }
        if (needToPublish) {
            Log.i(TAG, "set current location to OC");
            location.setBearing(bearing);
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            location.setSpeed(0.0f);
            this.m_LocationSubject.onNext(location);
        }
    }

    public final void setGpsLocationCanUpdate(boolean value) {
        Log.i(TAG, "[setGpsLocationCanUpdate] --> value == " + value);
        this.gpsLocationCanUpdate = value;
        if (value) {
            return;
        }
        Log.i(TAG, "set default location to OC");
        this.m_LocationSubject.onNext(null);
    }

    public final void setSystemAdapterCarSpeed(double carGearSpeed) {
        Log.i(TAG, "[setSystemAdapterCarSpeed] --> carGearSpeed == " + carGearSpeed);
        this.systemAdapterCarSpeed = carGearSpeed;
        if (((int) carGearSpeed) == 0) {
            commitLocation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restoreLastLocation() {
        Log.i(TAG, "[restoreLastLocation]");
        Location location = new Location("");
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("CurrentLocation", 0);
        if (OneCoreService.INSTANCE.checkPrintLocationLog()) {
            Log.i(TAG, "saved lat == " + sharedPreferences.getFloat("latitude", this.LATITUDE_DEFAULT) + ", lon = " + sharedPreferences.getFloat("longitude", this.LONGITUDE_DEFAULT));
        }
        location.setLatitude(sharedPreferences.getFloat("latitude", this.LATITUDE_DEFAULT));
        location.setLongitude(sharedPreferences.getFloat("longitude", this.LONGITUDE_DEFAULT));
        location.setBearing(sharedPreferences.getFloat("bearing", 0.0f));
        this.m_LocationSubject.onNext(location);
        Log.i(TAG, "send to oc");
        this.staticHold.push(location, true);
    }

    private final void startAutoSaveCurrentLocation() {
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.LocationHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LocationHandler.startAutoSaveCurrentLocation$lambda$10(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startAutoSaveCurrentLocation$lambda$10(LocationHandler this$0) {
        int i;
        InterruptedException e;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        INSTANCE.getLogger().debug("startAutoSaveCurrentLocation running");
        while (true) {
            int i2 = 0;
            while (this$0.m_EnableAutoSaveLocation) {
                i2++;
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    i = i2;
                    e = e2;
                }
                if (i2 > 600 && this$0.m_IsMoving) {
                    try {
                        this$0.m_IsMoving = false;
                        this$0.m_NeedToSaveCurrentLocation = true;
                        INSTANCE.getLogger().debug("startAutoSaveCurrentLocation:setCurrentLocation.");
                        break;
                    } catch (InterruptedException e3) {
                        e = e3;
                        i = 0;
                        e.printStackTrace();
                        i2 = i;
                    }
                } else if (!this$0.m_IsMoving) {
                    break;
                }
            }
            return;
        }
    }

    private final void stopAutoSaveCurrentLocation() {
        this.m_EnableAutoSaveLocation = false;
        INSTANCE.getLogger().debug("stopAutoSaveCurrentLocation.");
    }

    private final void startActivateLocationSocketService() {
        new Thread(new Runnable() { // from class: com.neusoft.na.ocservice.LocationHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                LocationHandler.startActivateLocationSocketService$lambda$11(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startActivateLocationSocketService$lambda$11(LocationHandler this$0) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ServerSocket serverSocket = new ServerSocket(20981);
        while (true) {
            Log.i(TAG, "activateLocationSocketService accept");
            Socket socketAccept = serverSocket.accept();
            Log.i(TAG, "socket accept");
            PrintWriter printWriter = new PrintWriter(socketAccept.getOutputStream());
            if (this$0.activateLocation == null) {
                printWriter.write("-1\n");
            } else {
                StringBuilder sb = new StringBuilder();
                Location location = this$0.activateLocation;
                Intrinsics.checkNotNull(location);
                StringBuilder sbAppend = sb.append(location.getLongitude()).append(',');
                Location location2 = this$0.activateLocation;
                Intrinsics.checkNotNull(location2);
                printWriter.write(sbAppend.append(location2.getLatitude()).append('\n').toString());
            }
            printWriter.flush();
            Log.i(TAG, "printWriter end");
        }
    }
}
