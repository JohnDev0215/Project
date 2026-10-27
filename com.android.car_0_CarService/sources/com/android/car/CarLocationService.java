package com.android.car;

import android.app.ActivityManager;
import android.car.ILocationManagerProxy;
import android.car.IPerUserCarService;
import android.car.drivingstate.CarDrivingStateEvent;
import android.car.drivingstate.ICarDrivingStateChangeListener;
import android.car.encryptionrunner.DummyEncryptionRunner;
import android.car.hardware.power.CarPowerManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserManager;
import android.util.AtomicFile;
import android.util.JsonWriter;
import android.util.Log;
import com.android.car.PerUserCarServiceHelper;
import com.android.car.systeminterface.SystemInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes3.dex */
public class CarLocationService extends BroadcastReceiver implements CarServiceBase, CarPowerManager.CarPowerStateListenerWithCompletion {
    private static final String ACCURACY = "accuracy";
    private static final String ALTITUDE = "altitude";
    private static final String BEARING = "bearing";
    private static final String BEARING_ACCURACY = "bearingAccuracy";
    private static final String CAPTURE_TIME = "captureTime";
    private static final String FILENAME = "location_cache.json";
    private static final long GRANULARITY_ONE_DAY_MS = 86400000;
    private static final String IS_FROM_MOCK_PROVIDER = "isFromMockProvider";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final int MAX_LOCATION_INJECTION_ATTEMPTS = 10;
    private static final String PROVIDER = "provider";
    private static final String SPEED = "speed";
    private static final String SPEED_ACCURACY = "speedAccuracy";
    private static final String TAG = "CarLocationService";
    private static final long TTL_THIRTY_DAYS_MS = 2592000000L;
    private static final String VERTICAL_ACCURACY = "verticalAccuracy";
    private CarDrivingStateService mCarDrivingStateService;
    private CarPowerManager mCarPowerManager;
    private final Context mContext;
    private ILocationManagerProxy mILocationManagerProxy;
    private PerUserCarServiceHelper mPerUserCarServiceHelper;
    private final Object mLock = new Object();
    private final Object mLocationManagerProxyLock = new Object();
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
    private final Handler mHandler = new Handler(this.mHandlerThread.getLooper());
    private final PerUserCarServiceHelper.ServiceCallback mUserServiceCallback = new AnonymousClass1();
    private final ICarDrivingStateChangeListener mICarDrivingStateChangeEventListener = new ICarDrivingStateChangeListener.Stub() { // from class: com.android.car.CarLocationService.2
        public void onDrivingStateChanged(CarDrivingStateEvent event) {
            CarLocationService.logd("onDrivingStateChanged: %s", event);
            if (event != null && event.eventValue == 2) {
                CarLocationService.this.deleteCacheFile();
                if (CarLocationService.this.mCarDrivingStateService != null) {
                    CarLocationService.this.mCarDrivingStateService.unregisterDrivingStateChangeListener(CarLocationService.this.mICarDrivingStateChangeEventListener);
                }
            }
        }
    };

    /* JADX INFO: renamed from: com.android.car.CarLocationService$1, reason: invalid class name */
    class AnonymousClass1 implements PerUserCarServiceHelper.ServiceCallback {
        AnonymousClass1() {
        }

        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onServiceConnected(IPerUserCarService perUserCarService) {
            CarLocationService.logd("Connected to PerUserCarService", new Object[0]);
            if (perUserCarService == null) {
                CarLocationService.logd("IPerUserCarService is null. Cannot get location manager proxy", new Object[0]);
                return;
            }
            synchronized (CarLocationService.this.mLocationManagerProxyLock) {
                try {
                    CarLocationService.this.mILocationManagerProxy = perUserCarService.getLocationManagerProxy();
                } catch (RemoteException e) {
                    Log.e(CarLocationService.TAG, "RemoteException from IPerUserCarService", e);
                    return;
                }
            }
            int currentUser = ActivityManager.getCurrentUser();
            CarLocationService.logd("Current user: %s", Integer.valueOf(currentUser));
            if (UserManager.isHeadlessSystemUserMode() && currentUser > 0) {
                CarLocationService.this.asyncOperation(new Runnable() { // from class: com.android.car.-$$Lambda$CarLocationService$1$PJ26-jNRQe6t5bQwFKIA9ddCeVY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onServiceConnected$0$CarLocationService$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onServiceConnected$0$CarLocationService$1() {
            CarLocationService.this.loadLocation();
        }

        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onPreUnbind() {
            CarLocationService.logd("Before Unbinding from PerUserCarService", new Object[0]);
            synchronized (CarLocationService.this.mLocationManagerProxyLock) {
                CarLocationService.this.mILocationManagerProxy = null;
            }
        }

        @Override // com.android.car.PerUserCarServiceHelper.ServiceCallback
        public void onServiceDisconnected() {
            CarLocationService.logd("Disconnected from PerUserCarService", new Object[0]);
            synchronized (CarLocationService.this.mLocationManagerProxyLock) {
                CarLocationService.this.mILocationManagerProxy = null;
            }
        }
    }

    public CarLocationService(Context context) {
        logd("constructed", new Object[0]);
        this.mContext = context;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        logd(DummyEncryptionRunner.INIT, new Object[0]);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.location.MODE_CHANGED");
        this.mContext.registerReceiver(this, filter);
        CarDrivingStateService carDrivingStateService = (CarDrivingStateService) CarLocalServices.getService(CarDrivingStateService.class);
        this.mCarDrivingStateService = carDrivingStateService;
        if (carDrivingStateService != null) {
            CarDrivingStateEvent event = carDrivingStateService.getCurrentDrivingState();
            if (event != null && event.eventValue == 2) {
                deleteCacheFile();
            } else {
                this.mCarDrivingStateService.registerDrivingStateChangeListener(this.mICarDrivingStateChangeEventListener);
            }
        }
        CarPowerManager carPowerManagerCreateCarPowerManager = CarLocalServices.createCarPowerManager(this.mContext);
        this.mCarPowerManager = carPowerManagerCreateCarPowerManager;
        if (carPowerManagerCreateCarPowerManager != null) {
            carPowerManagerCreateCarPowerManager.setListenerWithCompletion(this);
        }
        PerUserCarServiceHelper perUserCarServiceHelper = (PerUserCarServiceHelper) CarLocalServices.getService(PerUserCarServiceHelper.class);
        this.mPerUserCarServiceHelper = perUserCarServiceHelper;
        if (perUserCarServiceHelper != null) {
            perUserCarServiceHelper.registerServiceCallback(this.mUserServiceCallback);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        logd("release", new Object[0]);
        CarPowerManager carPowerManager = this.mCarPowerManager;
        if (carPowerManager != null) {
            carPowerManager.clearListener();
        }
        CarDrivingStateService carDrivingStateService = this.mCarDrivingStateService;
        if (carDrivingStateService != null) {
            carDrivingStateService.unregisterDrivingStateChangeListener(this.mICarDrivingStateChangeEventListener);
        }
        PerUserCarServiceHelper perUserCarServiceHelper = this.mPerUserCarServiceHelper;
        if (perUserCarServiceHelper != null) {
            perUserCarServiceHelper.unregisterServiceCallback(this.mUserServiceCallback);
        }
        this.mContext.unregisterReceiver(this);
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println(TAG);
        writer.println("Context: " + this.mContext);
        writer.println("MAX_LOCATION_INJECTION_ATTEMPTS: 10");
    }

    public void onStateChanged(int state, final CompletableFuture<Void> future) {
        logd("onStateChanged: %s", Integer.valueOf(state));
        if (state == 3) {
            CarDrivingStateService carDrivingStateService = this.mCarDrivingStateService;
            if (carDrivingStateService != null) {
                CarDrivingStateEvent event = carDrivingStateService.getCurrentDrivingState();
                if (event != null && event.eventValue == 2) {
                    deleteCacheFile();
                } else {
                    logd("Registering to receive driving state.", new Object[0]);
                    this.mCarDrivingStateService.registerDrivingStateChangeListener(this.mICarDrivingStateChangeEventListener);
                }
            }
            if (future != null) {
                future.complete(null);
            }
        } else if (state == 7) {
            asyncOperation(new Runnable() { // from class: com.android.car.-$$Lambda$CarLocationService$LTyswo2Q2YA9ZwNV60FLqVhM8VE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStateChanged$0$CarLocationService(future);
                }
            });
            return;
        }
        if (future != null) {
            future.complete(null);
        }
    }

    public /* synthetic */ void lambda$onStateChanged$0$CarLocationService(CompletableFuture future) {
        storeLocation();
        if (future != null) {
            future.complete(null);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        logd("onReceive %s", intent);
        if (isCurrentUserHeadlessSystemUser()) {
            logd("Current user is headless system user.", new Object[0]);
            return;
        }
        synchronized (this.mLocationManagerProxyLock) {
            if (this.mILocationManagerProxy == null) {
                logd("Null location manager.", new Object[0]);
                return;
            }
            String action = intent.getAction();
            try {
                if (action == "android.location.MODE_CHANGED") {
                    boolean locationEnabled = this.mILocationManagerProxy.isLocationEnabled();
                    logd("isLocationEnabled(): %s", Boolean.valueOf(locationEnabled));
                    if (!locationEnabled) {
                        deleteCacheFile();
                    }
                } else {
                    logd("Unexpected intent.", new Object[0]);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException from ILocationManagerProxy", e);
            }
        }
    }

    private boolean isCurrentUserHeadlessSystemUser() {
        int currentUserId = ActivityManager.getCurrentUser();
        return UserManager.isHeadlessSystemUserMode() && currentUserId == 0;
    }

    private void storeLocation() {
        Location location = null;
        synchronized (this.mLocationManagerProxyLock) {
            if (this.mILocationManagerProxy == null) {
                logd("Null location manager proxy.", new Object[0]);
                return;
            }
            try {
                location = this.mILocationManagerProxy.getLastKnownLocation("gps");
            } catch (RemoteException e) {
                Log.e(TAG, "RemoteException from ILocationManagerProxy", e);
            }
            if (location == null) {
                logd("Not storing null location", new Object[0]);
                return;
            }
            logd("Storing location", new Object[0]);
            AtomicFile atomicFile = new AtomicFile(getLocationCacheFile());
            FileOutputStream fos = null;
            try {
                fos = atomicFile.startWrite();
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(fos, "UTF-8"));
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name(PROVIDER).value(location.getProvider());
                    jsonWriter.name(LATITUDE).value(location.getLatitude());
                    jsonWriter.name(LONGITUDE).value(location.getLongitude());
                    if (location.hasAltitude()) {
                        jsonWriter.name(ALTITUDE).value(location.getAltitude());
                    }
                    if (location.hasSpeed()) {
                        jsonWriter.name(SPEED).value(location.getSpeed());
                    }
                    if (location.hasBearing()) {
                        jsonWriter.name(BEARING).value(location.getBearing());
                    }
                    if (location.hasAccuracy()) {
                        jsonWriter.name(ACCURACY).value(location.getAccuracy());
                    }
                    if (location.hasVerticalAccuracy()) {
                        jsonWriter.name(VERTICAL_ACCURACY).value(location.getVerticalAccuracyMeters());
                    }
                    if (location.hasSpeedAccuracy()) {
                        jsonWriter.name(SPEED_ACCURACY).value(location.getSpeedAccuracyMetersPerSecond());
                    }
                    if (location.hasBearingAccuracy()) {
                        jsonWriter.name(BEARING_ACCURACY).value(location.getBearingAccuracyDegrees());
                    }
                    if (location.isFromMockProvider()) {
                        jsonWriter.name(IS_FROM_MOCK_PROVIDER).value(true);
                    }
                    long currentTime = location.getTime();
                    jsonWriter.name(CAPTURE_TIME).value(currentTime - (currentTime % GRANULARITY_ONE_DAY_MS));
                    jsonWriter.endObject();
                    jsonWriter.close();
                    atomicFile.finishWrite(fos);
                } finally {
                }
            } catch (IOException e2) {
                Log.e(TAG, "Unable to write to disk", e2);
                atomicFile.failWrite(fos);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadLocation() {
        Location location = readLocationFromCacheFile();
        logd("Read location from timestamp %s", Long.valueOf(location.getTime()));
        long currentTime = System.currentTimeMillis();
        if (location.getTime() + TTL_THIRTY_DAYS_MS < currentTime) {
            logd("Location expired.", new Object[0]);
            deleteCacheFile();
            return;
        }
        location.setTime(currentTime);
        long elapsedTime = SystemClock.elapsedRealtimeNanos();
        location.setElapsedRealtimeNanos(elapsedTime);
        if (location.isComplete()) {
            injectLocation(location, 1);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.location.Location readLocationFromCacheFile() {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarLocationService.readLocationFromCacheFile():android.location.Location");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteCacheFile() {
        File file = getLocationCacheFile();
        boolean deleted = file.delete();
        if (deleted) {
            logd("Successfully deleted cache file at %s", file);
        } else {
            logd("Failed to delete cache file at %s", file);
        }
    }

    private void injectLocation(final Location location, final int attemptCount) {
        boolean success = false;
        synchronized (this.mLocationManagerProxyLock) {
            if (this.mILocationManagerProxy == null) {
                logd("Null location manager proxy.", new Object[0]);
            } else {
                try {
                    success = this.mILocationManagerProxy.injectLocation(location);
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException from ILocationManagerProxy", e);
                }
            }
        }
        if (success) {
            logd("Successfully injected stored location on attempt %s.", Integer.valueOf(attemptCount));
        } else if (attemptCount <= 10) {
            logd("Failed to inject stored location on attempt %s.", Integer.valueOf(attemptCount));
            asyncOperation(new Runnable() { // from class: com.android.car.-$$Lambda$CarLocationService$_hxnlSBPJEEas1P4iAESb9_xKKU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$injectLocation$1$CarLocationService(location, attemptCount);
                }
            }, attemptCount * 200);
        } else {
            logd("No location injected.", new Object[0]);
        }
    }

    public /* synthetic */ void lambda$injectLocation$1$CarLocationService(Location location, int attemptCount) {
        injectLocation(location, attemptCount + 1);
    }

    private File getLocationCacheFile() {
        SystemInterface systemInterface = (SystemInterface) CarLocalServices.getService(SystemInterface.class);
        return new File(systemInterface.getSystemCarDir(), FILENAME);
    }

    void asyncOperation(Runnable operation) {
        asyncOperation(operation, 0L);
    }

    private void asyncOperation(final Runnable operation, long delayMillis) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.-$$Lambda$CarLocationService$0Ujb8vYhS4GkP5_cOIOBuqlM-NE
            @Override // java.lang.Runnable
            public final void run() {
                operation.run();
            }
        }, delayMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String msg, Object... vals) {
        Log.d(TAG, String.format(msg, vals));
    }
}
