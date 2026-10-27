package android.car;

import android.annotation.SuppressLint;
import android.annotation.SystemApi;
import android.app.Activity;
import android.app.Service;
import android.car.ICar;
import android.car.cluster.CarInstrumentClusterManager;
import android.car.content.pm.CarPackageManager;
import android.car.diagnostic.CarDiagnosticManager;
import android.car.drivingstate.CarDrivingStateManager;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.car.hardware.CarSensorManager;
import android.car.hardware.CarVendorExtensionManager;
import android.car.hardware.cabin.CarCabinManager;
import android.car.hardware.hvac.CarHvacManager;
import android.car.hardware.power.CarPowerManager;
import android.car.hardware.property.CarPropertyManager;
import android.car.hardware.property.ICarProperty;
import android.car.input.CarInputManager;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.car.media.CarMediaManager;
import android.car.navigation.CarNavigationStatusManager;
import android.car.occupantawareness.OccupantAwarenessManager;
import android.car.settings.CarConfigurationManager;
import android.car.storagemonitoring.CarStorageMonitoringManager;
import android.car.test.CarTestManagerBinderWrapper;
import android.car.trust.CarTrustAgentEnrollmentManager;
import android.car.user.CarUserManager;
import android.car.vms.VmsClientManager;
import android.car.vms.VmsSubscriberManager;
import android.car.watchdog.CarWatchdogManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.TransactionTooLargeException;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class Car {
    public static final String APP_FOCUS_SERVICE = "app_focus";
    public static final String AUDIO_SERVICE = "audio";
    public static final String BLUETOOTH_SERVICE = "car_bluetooth";

    @SystemApi
    @Deprecated
    public static final String CABIN_SERVICE = "cabin";
    public static final String CAR_BUGREPORT_SERVICE = "car_bugreport";
    public static final String CAR_CATEGORY_NAVIGATION = "android.car.cluster.NAVIGATION";
    public static final String CAR_CONFIGURATION_SERVICE = "configuration";

    @SystemApi
    public static final String CAR_DRIVING_STATE_SERVICE = "drivingstate";
    public static final String CAR_EXTRA_BROWSE_SERVICE_FOR_SESSION = "android.media.session.BROWSE_SERVICE";

    @SystemApi
    public static final String CAR_EXTRA_CLUSTER_ACTIVITY_STATE = "android.car.cluster.ClusterActivityState";
    public static final String CAR_EXTRA_MEDIA_COMPONENT = "android.car.intent.extra.MEDIA_COMPONENT";

    @Deprecated
    public static final String CAR_EXTRA_MEDIA_PACKAGE = "android.car.intent.extra.MEDIA_PACKAGE";
    public static final String CAR_INPUT_SERVICE = "android.car.input";

    @Deprecated
    public static final String CAR_INSTRUMENT_CLUSTER_SERVICE = "cluster_service";
    public static final String CAR_INTENT_ACTION_MEDIA_TEMPLATE = "android.car.intent.action.MEDIA_TEMPLATE";

    @SystemApi
    public static final String CAR_MEDIA_SERVICE = "car_media";
    public static final String CAR_NAVIGATION_SERVICE = "car_navigation_service";
    public static final String CAR_OCCUPANT_ZONE_SERVICE = "car_occupant_zone_service";
    private static final long CAR_SERVICE_BINDER_POLLING_INTERVAL_MS = 50;
    private static final long CAR_SERVICE_BINDER_POLLING_MAX_RETRY = 100;
    public static final String CAR_SERVICE_BINDER_SERVICE_NAME = "car_service";
    private static final long CAR_SERVICE_BIND_MAX_RETRY = 20;
    private static final long CAR_SERVICE_BIND_RETRY_INTERVAL_MS = 500;
    private static final String CAR_SERVICE_CLASS = "com.android.car.CarService";
    public static final String CAR_SERVICE_INTERFACE_NAME = "android.car.ICar";
    private static final String CAR_SERVICE_PACKAGE = "com.android.car";

    @SystemApi
    public static final String CAR_TRUST_AGENT_ENROLLMENT_SERVICE = "trust_enroll";

    @SystemApi
    public static final String CAR_USER_SERVICE = "car_user_service";
    public static final String CAR_UX_RESTRICTION_SERVICE = "uxrestriction";
    public static final long CAR_WAIT_TIMEOUT_DO_NOT_WAIT = 0;
    public static final long CAR_WAIT_TIMEOUT_WAIT_FOREVER = -1;

    @SystemApi
    public static final String CAR_WATCHDOG_SERVICE = "car_watchdog";
    public static final int CONNECTION_TYPE_EMBEDDED = 5;
    private static final boolean DBG = false;

    @SystemApi
    public static final String DIAGNOSTIC_SERVICE = "diagnostic";
    public static final String ENHANCED_AUDIO_SERVICE = "enhanced_audio";

    @SystemApi
    public static final int FEATURE_REQUEST_ALREADY_IN_THE_STATE = 1;

    @SystemApi
    public static final int FEATURE_REQUEST_MANDATORY = 2;

    @SystemApi
    public static final int FEATURE_REQUEST_NOT_EXISTING = 3;

    @SystemApi
    public static final int FEATURE_REQUEST_SUCCESS = 0;

    @SystemApi
    @Deprecated
    public static final String HVAC_SERVICE = "hvac";
    public static final String INFO_SERVICE = "info";

    @SuppressLint({"IntentName"})
    public static final String META_DATA_DISTRACTION_OPTIMIZED = "distractionOptimized";

    @SuppressLint({"IntentName"})
    public static final String META_DATA_REQUIRES_CAR_FEATURE = "requires-car-feature";

    @SystemApi
    public static final String OCCUPANT_AWARENESS_SERVICE = "occupant_awareness";
    public static final String PACKAGE_SERVICE = "package";

    @SystemApi
    public static final String PERMISSION_ADJUST_RANGE_REMAINING = "android.car.permission.ADJUST_RANGE_REMAINING";
    public static final String PERMISSION_BIND_VMS_CLIENT = "android.car.permission.BIND_VMS_CLIENT";
    public static final String PERMISSION_CAR_CONTROL_AUDIO_SETTINGS = "android.car.permission.CAR_CONTROL_AUDIO_SETTINGS";
    public static final String PERMISSION_CAR_CONTROL_AUDIO_VOLUME = "android.car.permission.CAR_CONTROL_AUDIO_VOLUME";
    public static final String PERMISSION_CAR_CONTROL_ENHANCED_AUDIO = "android.car.permission.CAR_CONTROL_ENHANCED_AUDIO";

    @SystemApi
    public static final String PERMISSION_CAR_DIAGNOSTIC_CLEAR = "android.car.permission.CLEAR_CAR_DIAGNOSTICS";

    @SystemApi
    public static final String PERMISSION_CAR_DIAGNOSTIC_READ_ALL = "android.car.permission.CAR_DIAGNOSTICS";
    public static final String PERMISSION_CAR_DISPLAY_IN_CLUSTER = "android.car.permission.CAR_DISPLAY_IN_CLUSTER";

    @SystemApi
    public static final String PERMISSION_CAR_DRIVING_STATE = "android.car.permission.CAR_DRIVING_STATE";

    @SystemApi
    public static final String PERMISSION_CAR_DYNAMICS_STATE = "android.car.permission.CAR_DYNAMICS_STATE";

    @SystemApi
    public static final String PERMISSION_CAR_ENGINE_DETAILED = "android.car.permission.CAR_ENGINE_DETAILED";

    @SystemApi
    public static final String PERMISSION_CAR_ENROLL_TRUST = "android.car.permission.CAR_ENROLL_TRUST";
    public static final String PERMISSION_CAR_INFO = "android.car.permission.CAR_INFO";

    @SystemApi
    public static final String PERMISSION_CAR_INSTRUMENT_CLUSTER_CONTROL = "android.car.permission.CAR_INSTRUMENT_CLUSTER_CONTROL";
    public static final String PERMISSION_CAR_NAVIGATION_MANAGER = "android.car.permission.CAR_NAVIGATION_MANAGER";

    @SystemApi
    public static final String PERMISSION_CAR_POWER = "android.car.permission.CAR_POWER";

    @SystemApi
    public static final String PERMISSION_CAR_PROJECTION = "android.car.permission.CAR_PROJECTION";

    @SystemApi
    public static final String PERMISSION_CAR_PROJECTION_STATUS = "android.car.permission.ACCESS_CAR_PROJECTION_STATUS";

    @SystemApi
    public static final String PERMISSION_CAR_TEST_SERVICE = "android.car.permission.CAR_TEST_SERVICE";
    public static final String PERMISSION_CAR_UX_RESTRICTIONS_CONFIGURATION = "android.car.permission.CAR_UX_RESTRICTIONS_CONFIGURATION";

    @SystemApi
    public static final String PERMISSION_CONTROL_APP_BLOCKING = "android.car.permission.CONTROL_APP_BLOCKING";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_CLIMATE = "android.car.permission.CONTROL_CAR_CLIMATE";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_DOORS = "android.car.permission.CONTROL_CAR_DOORS";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_FEATURES = "android.car.permission.CONTROL_CAR_FEATURES";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_MIRRORS = "android.car.permission.CONTROL_CAR_MIRRORS";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_OCCUPANT_AWARENESS_SYSTEM = "android.car.permission.CONTROL_CAR_OCCUPANT_AWARENESS_SYSTEM";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_SEATS = "android.car.permission.CONTROL_CAR_SEATS";

    @SystemApi
    public static final String PERMISSION_CONTROL_CAR_WINDOWS = "android.car.permission.CONTROL_CAR_WINDOWS";
    public static final String PERMISSION_CONTROL_DISPLAY_UNITS = "android.car.permission.CONTROL_CAR_DISPLAY_UNITS";

    @SystemApi
    public static final String PERMISSION_CONTROL_ENERGY_PORTS = "android.car.permission.CONTROL_CAR_ENERGY_PORTS";

    @SystemApi
    public static final String PERMISSION_CONTROL_EXTERIOR_LIGHTS = "android.car.permission.CONTROL_CAR_EXTERIOR_LIGHTS";
    public static final String PERMISSION_CONTROL_INTERIOR_LIGHTS = "android.car.permission.CONTROL_CAR_INTERIOR_LIGHTS";
    public static final String PERMISSION_ENERGY = "android.car.permission.CAR_ENERGY";
    public static final String PERMISSION_ENERGY_PORTS = "android.car.permission.CAR_ENERGY_PORTS";
    public static final String PERMISSION_EXTERIOR_ENVIRONMENT = "android.car.permission.CAR_EXTERIOR_ENVIRONMENT";

    @SystemApi
    public static final String PERMISSION_EXTERIOR_LIGHTS = "android.car.permission.CAR_EXTERIOR_LIGHTS";
    public static final String PERMISSION_IDENTIFICATION = "android.car.permission.CAR_IDENTIFICATION";

    @SystemApi
    public static final String PERMISSION_MILEAGE = "android.car.permission.CAR_MILEAGE";

    @SystemApi
    @Deprecated
    public static final String PERMISSION_MOCK_VEHICLE_HAL = "android.car.permission.CAR_MOCK_VEHICLE_HAL";
    public static final String PERMISSION_POWERTRAIN = "android.car.permission.CAR_POWERTRAIN";

    @SystemApi
    public static final String PERMISSION_READ_CAR_OCCUPANT_AWARENESS_STATE = "android.car.permission.READ_CAR_OCCUPANT_AWARENESS_STATE";

    @SystemApi
    public static final String PERMISSION_READ_CAR_VENDOR_PERMISSION_INFO = "android.car.permission.READ_CAR_VENDOR_PERMISSION_INFO";
    public static final String PERMISSION_READ_DISPLAY_UNITS = "android.car.permission.READ_CAR_DISPLAY_UNITS";
    public static final String PERMISSION_READ_INTERIOR_LIGHTS = "android.car.permission.READ_CAR_INTERIOR_LIGHTS";
    public static final String PERMISSION_READ_STEERING_STATE = "android.car.permission.READ_CAR_STEERING";

    @SystemApi
    public static final String PERMISSION_RECEIVE_CAR_AUDIO_DUCKING_EVENTS = "android.car.permission.RECEIVE_CAR_AUDIO_DUCKING_EVENTS";
    public static final String PERMISSION_SPEED = "android.car.permission.CAR_SPEED";

    @SystemApi
    public static final String PERMISSION_STORAGE_MONITORING = "android.car.permission.STORAGE_MONITORING";

    @SystemApi
    public static final String PERMISSION_TIRES = "android.car.permission.CAR_TIRES";

    @SystemApi
    public static final String PERMISSION_USE_CAR_WATCHDOG = "android.car.permission.USE_CAR_WATCHDOG";

    @SystemApi
    public static final String PERMISSION_VENDOR_EXTENSION = "android.car.permission.CAR_VENDOR_EXTENSION";

    @SystemApi
    public static final String PERMISSION_VMS_PUBLISHER = "android.car.permission.VMS_PUBLISHER";

    @SystemApi
    public static final String PERMISSION_VMS_SUBSCRIBER = "android.car.permission.VMS_SUBSCRIBER";

    @SystemApi
    public static final String POWER_SERVICE = "power";

    @SystemApi
    public static final String PROJECTION_SERVICE = "projection";
    public static final String PROPERTY_SERVICE = "property";

    @Deprecated
    public static final String SENSOR_SERVICE = "sensor";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;

    @SystemApi
    public static final String STORAGE_MONITORING_SERVICE = "storage_monitoring";

    @SystemApi
    public static final String TEST_SERVICE = "car-service-test";

    @SystemApi
    public static final String VEHICLE_MAP_SERVICE = "vehicle_map_service";

    @SystemApi
    @Deprecated
    public static final String VENDOR_EXTENSION_SERVICE = "vendor_extension";

    @SystemApi
    @Deprecated
    public static final String VMS_SUBSCRIBER_SERVICE = "vehicle_map_subscriber_service";

    @GuardedBy({"mLock"})
    private int mConnectionRetryCount;
    private final Runnable mConnectionRetryFailedRunnable;
    private final Runnable mConnectionRetryRunnable;

    @GuardedBy({"mLock"})
    private int mConnectionState;
    private final Exception mConstructionStack;
    private final Context mContext;
    private final Handler mEventHandler;
    private final CarFeatures mFeatures;
    private final Object mLock;
    private final Handler mMainThreadEventHandler;

    @GuardedBy({"mLock"})
    private ICar mService;

    @GuardedBy({"mLock"})
    private boolean mServiceBound;
    private final ServiceConnection mServiceConnectionListener;
    private final ServiceConnection mServiceConnectionListenerClient;

    @GuardedBy({"mLock"})
    private final HashMap<String, CarManagerBase> mServiceMap;
    private final CarServiceLifecycleListener mStatusChangeCallback;

    public interface CarServiceLifecycleListener {
        void onLifecycleChanged(Car car, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionType {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeaturerRequestEnum {
    }

    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StateTypeEnum {
    }

    private Car(Context context, ICar iCar, ServiceConnection serviceConnection, CarServiceLifecycleListener carServiceLifecycleListener, Handler handler) {
        this.mLock = new Object();
        this.mConnectionRetryRunnable = new Runnable() { // from class: android.car.Car.1
            @Override // java.lang.Runnable
            public void run() {
                Car.this.startCarService();
            }
        };
        this.mConnectionRetryFailedRunnable = new Runnable() { // from class: android.car.Car.2
            @Override // java.lang.Runnable
            public void run() {
                Car.this.mServiceConnectionListener.onServiceDisconnected(new ComponentName(Car.CAR_SERVICE_PACKAGE, Car.CAR_SERVICE_CLASS));
            }
        };
        this.mServiceConnectionListener = new ServiceConnection() { // from class: android.car.Car.3
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (Car.this.mLock) {
                    ICar iCarAsInterface = ICar.Stub.asInterface(iBinder);
                    if (iCarAsInterface == null) {
                        Log.wtf(CarLibLog.TAG_CAR, "null binder service", new RuntimeException());
                        return;
                    }
                    if (Car.this.mService == null || !Car.this.mService.asBinder().equals(iCarAsInterface.asBinder())) {
                        Car.this.mConnectionState = 2;
                        Car.this.mService = iCarAsInterface;
                        if (Car.this.mStatusChangeCallback != null) {
                            Car.this.mStatusChangeCallback.onLifecycleChanged(Car.this, true);
                        } else if (Car.this.mServiceConnectionListenerClient != null) {
                            Car.this.mServiceConnectionListenerClient.onServiceConnected(componentName, iBinder);
                        }
                    }
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Car.this.mFeatures.resetCache();
                synchronized (Car.this.mLock) {
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.handleCarDisconnectLocked();
                    if (Car.this.mStatusChangeCallback != null) {
                        Car.this.mStatusChangeCallback.onLifecycleChanged(Car.this, false);
                    } else if (Car.this.mServiceConnectionListenerClient != null) {
                        Car.this.mServiceConnectionListenerClient.onServiceDisconnected(componentName);
                    } else {
                        Car.this.finishClient();
                    }
                }
            }
        };
        this.mServiceMap = new HashMap<>();
        this.mFeatures = new CarFeatures();
        this.mContext = context;
        Handler handlerDetermineEventHandler = determineEventHandler(handler);
        this.mEventHandler = handlerDetermineEventHandler;
        this.mMainThreadEventHandler = determineMainThreadEventHandler(handlerDetermineEventHandler);
        this.mService = iCar;
        this.mConnectionState = iCar != null ? 2 : 0;
        this.mServiceConnectionListenerClient = serviceConnection;
        this.mStatusChangeCallback = carServiceLifecycleListener;
        this.mConstructionStack = (serviceConnection == null && carServiceLifecycleListener == null) ? new RuntimeException() : null;
    }

    public Car(Context context, ICar iCar, Handler handler) {
        this(context, iCar, null, null, handler);
    }

    private static void assertNonNullContext(Context context) {
        Objects.requireNonNull(context);
        if (context instanceof ContextWrapper) {
            Objects.requireNonNull(((ContextWrapper) context).getBaseContext(), "ContextWrapper with null base passed as Context, forgot to set base Context?");
        }
    }

    private CarManagerBase constructCarManager(String str, IBinder iBinder) {
        try {
            return (CarManagerBase) this.mContext.getClassLoader().loadClass(str).getConstructor(Car.class, IBinder.class).newInstance(this, iBinder);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            Log.e(CarLibLog.TAG_CAR, "Cannot construct CarManager, class:" + str, e);
            return null;
        }
    }

    public static Car createCar(Context context) {
        return createCar(context, (Handler) null);
    }

    @Deprecated
    public static Car createCar(Context context, ServiceConnection serviceConnection) {
        return createCar(context, serviceConnection, null);
    }

    @Deprecated
    public static Car createCar(Context context, ServiceConnection serviceConnection, Handler handler) {
        assertNonNullContext(context);
        if (context.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            try {
                return new Car(context, null, serviceConnection, null, handler);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        Log.e(CarLibLog.TAG_CAR, "FEATURE_AUTOMOTIVE not declared while android.car is used");
        return null;
    }

    public static Car createCar(Context context, Handler handler) {
        String str;
        String str2;
        RuntimeException runtimeException;
        assertNonNullContext(context);
        boolean z = false;
        int i = 0;
        Car car = null;
        while (true) {
            IBinder service = ServiceManager.getService(CAR_SERVICE_BINDER_SERVICE_NAME);
            if (car == null) {
                car = new Car(context, ICar.Stub.asInterface(service), null, null, handler);
            }
            if (service != null) {
                if (!z) {
                    car.startCarService();
                    return car;
                }
                synchronized (car) {
                    if (car.mService == null) {
                        car.mService = ICar.Stub.asInterface(service);
                        Log.w(CarLibLog.TAG_CAR, "waited for car_service (ms):" + (((long) i) * CAR_SERVICE_BINDER_POLLING_INTERVAL_MS), new RuntimeException());
                    }
                    car.mConnectionState = 2;
                }
                return car;
            }
            if (!z) {
                car.startCarService();
                z = true;
            }
            i++;
            if (i > CAR_SERVICE_BINDER_POLLING_MAX_RETRY) {
                str = CarLibLog.TAG_CAR;
                str2 = "cannot get car_service, waited for car service (ms):5000";
                runtimeException = new RuntimeException();
                break;
            }
            try {
                Thread.sleep(CAR_SERVICE_BINDER_POLLING_INTERVAL_MS);
            } catch (InterruptedException unused) {
                str = CarLibLog.TAG_CAR;
                str2 = "interrupted while waiting for car_service";
                runtimeException = new RuntimeException();
            }
        }
        Log.e(str, str2, runtimeException);
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.car.Car createCar(android.content.Context r21, android.os.Handler r22, long r23, android.car.Car.CarServiceLifecycleListener r25) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.Car.createCar(android.content.Context, android.os.Handler, long, android.car.Car$CarServiceLifecycleListener):android.car.Car");
    }

    private CarManagerBase createCarManagerLocked(String str, IBinder iBinder) {
        str.hashCode();
        switch (str) {
            case "projection":
                return new CarProjectionManager(this, iBinder);
            case "car_bluetooth":
                return new CarBluetoothManager(this, iBinder);
            case "car_navigation_service":
                return new CarNavigationStatusManager(this, iBinder);
            case "occupant_awareness":
                return new OccupantAwarenessManager(this, iBinder);
            case "diagnostic":
                return new CarDiagnosticManager(this, iBinder);
            case "enhanced_audio":
                return new CarEnhancedAudioManager(this, iBinder);
            case "property":
                return new CarPropertyManager(this, ICarProperty.Stub.asInterface(iBinder));
            case "sensor":
                return new CarSensorManager(this, iBinder);
            case "vendor_extension":
                return new CarVendorExtensionManager(this, iBinder);
            case "package":
                return new CarPackageManager(this, iBinder);
            case "car-service-test":
                return new CarTestManagerBinderWrapper(this, iBinder);
            case "drivingstate":
                return new CarDrivingStateManager(this, iBinder);
            case "car_occupant_zone_service":
                return new CarOccupantZoneManager(this, iBinder);
            case "car_media":
                return new CarMediaManager(this, iBinder);
            case "storage_monitoring":
                return new CarStorageMonitoringManager(this, iBinder);
            case "hvac":
                return new CarHvacManager(this, iBinder);
            case "info":
                return new CarInfoManager(this, iBinder);
            case "audio":
                return new CarAudioManager(this, iBinder);
            case "cabin":
                return new CarCabinManager(this, iBinder);
            case "power":
                return new CarPowerManager(this, iBinder);
            case "vehicle_map_subscriber_service":
                return VmsSubscriberManager.wrap(this, (VmsClientManager) getCarManager(VEHICLE_MAP_SERVICE));
            case "car_user_service":
                return new CarUserManager(this, iBinder);
            case "uxrestriction":
                return new CarUxRestrictionsManager(this, iBinder);
            case "car_watchdog":
                return new CarWatchdogManager(this, iBinder);
            case "cluster_service":
                return new CarInstrumentClusterManager(this, iBinder);
            case "android.car.input":
                return new CarInputManager(this, iBinder);
            case "car_bugreport":
                return new CarBugreportManager(this, iBinder);
            case "app_focus":
                return new CarAppFocusManager(this, iBinder);
            case "trust_enroll":
                return new CarTrustAgentEnrollmentManager(this, iBinder);
            case "configuration":
                return new CarConfigurationManager(this, iBinder);
            case "vehicle_map_service":
                return new VmsClientManager(this, iBinder);
            default:
                try {
                    String carManagerClassForFeature = this.mService.getCarManagerClassForFeature(str);
                    if (carManagerClassForFeature != null) {
                        return constructCarManager(carManagerClassForFeature, iBinder);
                    }
                    Log.e(CarLibLog.TAG_CAR, "Cannot construct CarManager for service:" + str + " : no class defined");
                    return null;
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                    return null;
                }
        }
    }

    private static Handler determineEventHandler(Handler handler) {
        return handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }

    private static Handler determineMainThreadEventHandler(Handler handler) {
        Looper mainLooper = Looper.getMainLooper();
        return handler.getLooper() == mainLooper ? handler : new Handler(mainLooper);
    }

    private void dispatchCarReadyToMainThread(boolean z) {
        if (z) {
            this.mStatusChangeCallback.onLifecycleChanged(this, true);
        } else {
            this.mMainThreadEventHandler.post(new Runnable() { // from class: android.car.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f206a.a();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishClient() {
        String str;
        Context context = this.mContext;
        if (context == null) {
            throw new IllegalStateException("Car service has crashed, null Context");
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return;
            }
            Log.w(CarLibLog.TAG_CAR, "Car service crashed, client not handling it, finish Activity, created from " + this.mConstructionStack);
            activity.finish();
            return;
        }
        if (context instanceof Service) {
            Service service = (Service) context;
            str = service.getPackageName() + "," + service.getClass().getSimpleName();
        } else {
            str = null;
        }
        killClient(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCarDisconnectLocked() {
        if (this.mConnectionState == 0) {
            return;
        }
        this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
        this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
        this.mConnectionRetryCount = 0;
        tearDownCarManagersLocked();
        this.mService = null;
        this.mConnectionState = 0;
    }

    public static <T> T handleRemoteExceptionFromCarService(Service service, RemoteException remoteException, T t) {
        handleRemoteExceptionFromCarService(service, remoteException);
        return t;
    }

    public static void handleRemoteExceptionFromCarService(Service service, RemoteException remoteException) {
        if (remoteException instanceof TransactionTooLargeException) {
            Log.w(CarLibLog.TAG_CAR, "Car service threw TransactionTooLargeException, client:" + service.getPackageName() + "," + service.getClass().getSimpleName(), remoteException);
            throw new CarTransactionException(remoteException, "Car service threw TransactionTooLargeException, client: %s, %s", service.getPackageName(), service.getClass().getSimpleName());
        }
        Log.w(CarLibLog.TAG_CAR, "Car service has crashed, client:" + service.getPackageName() + "," + service.getClass().getSimpleName(), remoteException);
        service.stopSelf();
    }

    private void killClient(String str) {
        Log.w(CarLibLog.TAG_CAR, "**Car service has crashed. Client(" + str + ") is not handling it. Client should use Car.createCar(..., CarServiceLifecycleListener, ...) to handle it properly. Check pritned callstack to check where other version of Car.createCar() was called. Killing the client process**", this.mConstructionStack);
        Process.killProcess(Process.myPid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$dispatchCarReadyToMainThread$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a() {
        this.mStatusChangeCallback.onLifecycleChanged(this, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCarService() {
        Intent intent = new Intent();
        intent.setPackage(CAR_SERVICE_PACKAGE);
        intent.setAction(CAR_SERVICE_INTERFACE_NAME);
        boolean zBindServiceAsUser = this.mContext.bindServiceAsUser(intent, this.mServiceConnectionListener, 1, UserHandle.CURRENT_OR_SELF);
        synchronized (this.mLock) {
            if (zBindServiceAsUser) {
                this.mEventHandler.removeCallbacks(this.mConnectionRetryRunnable);
                this.mMainThreadEventHandler.removeCallbacks(this.mConnectionRetryFailedRunnable);
                this.mConnectionRetryCount = 0;
                this.mServiceBound = true;
            } else {
                int i = this.mConnectionRetryCount + 1;
                this.mConnectionRetryCount = i;
                if (i > CAR_SERVICE_BIND_MAX_RETRY) {
                    Log.w(CarLibLog.TAG_CAR, "cannot bind to car service after max retry");
                    this.mMainThreadEventHandler.post(this.mConnectionRetryFailedRunnable);
                } else {
                    this.mEventHandler.postDelayed(this.mConnectionRetryRunnable, CAR_SERVICE_BIND_RETRY_INTERVAL_MS);
                }
            }
        }
    }

    private void tearDownCarManagersLocked() {
        Iterator<CarManagerBase> it = this.mServiceMap.values().iterator();
        while (it.hasNext()) {
            it.next().onCarDisconnected();
        }
        this.mServiceMap.clear();
    }

    @Deprecated
    public void connect() {
        synchronized (this.mLock) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            startCarService();
        }
    }

    @SystemApi
    public int disableFeature(String str) {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return 3;
            }
            try {
                return iCar.disableFeature(str);
            } catch (RemoteException e) {
                return ((Integer) handleRemoteExceptionFromCarService(e, 3)).intValue();
            }
        }
    }

    public void disconnect() {
        synchronized (this.mLock) {
            handleCarDisconnectLocked();
            if (this.mServiceBound) {
                this.mContext.unbindService(this.mServiceConnectionListener);
                this.mServiceBound = false;
            }
        }
    }

    @SystemApi
    public int enableFeature(String str) {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return 3;
            }
            try {
                return iCar.enableFeature(str);
            } catch (RemoteException e) {
                return ((Integer) handleRemoteExceptionFromCarService(e, 3)).intValue();
            }
        }
    }

    @SystemApi
    public List<String> getAllEnabledFeatures() {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return Collections.EMPTY_LIST;
            }
            try {
                return iCar.getAllEnabledFeatures();
            } catch (RemoteException e) {
                return (List) handleRemoteExceptionFromCarService(e, Collections.EMPTY_LIST);
            }
        }
    }

    @SystemApi
    public List<String> getAllPendingDisabledFeatures() {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return Collections.EMPTY_LIST;
            }
            try {
                return iCar.getAllPendingDisabledFeatures();
            } catch (RemoteException e) {
                return (List) handleRemoteExceptionFromCarService(e, Collections.EMPTY_LIST);
            }
        }
    }

    @SystemApi
    public List<String> getAllPendingEnabledFeatures() {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return Collections.EMPTY_LIST;
            }
            try {
                return iCar.getAllPendingEnabledFeatures();
            } catch (RemoteException e) {
                return (List) handleRemoteExceptionFromCarService(e, Collections.EMPTY_LIST);
            }
        }
    }

    public int getCarConnectionType() {
        return 5;
    }

    public Object getCarManager(String str) {
        synchronized (this.mLock) {
            if (this.mService == null) {
                Log.w(CarLibLog.TAG_CAR, "getCarManager not working while car service not ready");
                return null;
            }
            CarManagerBase carManagerBaseCreateCarManagerLocked = this.mServiceMap.get(str);
            if (carManagerBaseCreateCarManagerLocked == null) {
                try {
                    IBinder carService = this.mService.getCarService(str);
                    if (carService == null) {
                        Log.w(CarLibLog.TAG_CAR, "getCarManager could not get binder for service:" + str);
                        return null;
                    }
                    carManagerBaseCreateCarManagerLocked = createCarManagerLocked(str, carService);
                    if (carManagerBaseCreateCarManagerLocked == null) {
                        Log.w(CarLibLog.TAG_CAR, "getCarManager could not create manager for service:" + str);
                        return null;
                    }
                    this.mServiceMap.put(str, carManagerBaseCreateCarManagerLocked);
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
            }
            return carManagerBaseCreateCarManagerLocked;
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    public Handler getEventHandler() {
        return this.mEventHandler;
    }

    @VisibleForTesting
    public ServiceConnection getServiceConnectionListener() {
        return this.mServiceConnectionListener;
    }

    @VisibleForTesting
    public <T> T handleRemoteExceptionFromCarService(RemoteException remoteException, T t) {
        handleRemoteExceptionFromCarService(remoteException);
        return t;
    }

    void handleRemoteExceptionFromCarService(RemoteException remoteException) {
        if (remoteException instanceof TransactionTooLargeException) {
            Log.w(CarLibLog.TAG_CAR, "Car service threw TransactionTooLargeException", remoteException);
            throw new CarTransactionException(remoteException, "Car service threw TransactionTooLargException", new Object[0]);
        }
        Log.w(CarLibLog.TAG_CAR, "Car service has crashed", remoteException);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mService != null;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isFeatureEnabled(String str) {
        synchronized (this.mLock) {
            ICar iCar = this.mService;
            if (iCar == null) {
                return false;
            }
            return this.mFeatures.isFeatureEnabled(iCar, str);
        }
    }
}
