package com.android.car;

import android.app.ActivityManager;
import android.car.CarFeatures;
import android.car.ICar;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.car.user.CarUserManager;
import android.car.userlib.CarUserManagerHelper;
import android.content.Context;
import android.content.res.Resources;
import android.hardware.automotive.vehicle.V2_0.IVehicle;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ShellCallback;
import android.os.UserManager;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.EventLog;
import android.util.Log;
import android.util.Slog;
import android.util.TimingsTraceLog;
import com.android.car.Manifest;
import com.android.car.am.FixedActivityService;
import com.android.car.audio.CarAudioService;
import com.android.car.cluster.InstrumentClusterService;
import com.android.car.enhancedaudio.CarAudioServiceWrapper;
import com.android.car.enhancedaudio.CarEnhancedAudioService;
import com.android.car.garagemode.GarageModeService;
import com.android.car.hal.VehicleHal;
import com.android.car.pm.CarPackageManagerService;
import com.android.car.stats.CarStatsService;
import com.android.car.systeminterface.SystemInterface;
import com.android.car.trust.CarTrustedDeviceService;
import com.android.car.user.CarUserNoticeService;
import com.android.car.user.CarUserService;
import com.android.car.vms.VmsBrokerService;
import com.android.car.watchdog.CarWatchdogService;
import com.android.internal.car.ICarServiceHelper;
import com.android.internal.os.IResultReceiver;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
public class ICarImpl extends ICar.Stub {
    private static final boolean DBG = true;
    private static final int INITIAL_VHAL_GET_RETRY = 2;
    public static final String INTERNAL_INPUT_SERVICE = "internal_input";
    public static final String INTERNAL_SYSTEM_ACTIVITY_MONITORING_SERVICE = "system_activity_monitoring";
    private static final String PROPERTY_USE_ENHANCED_AUDIO_FEATURE = "persist.sys.use_enhancedaudio";
    private static final String TAG = "ICarImpl";
    private static final String VHAL_TIMING_TAG = "VehicleHalTiming";
    private final CarServiceBase[] mAllServices;
    private final AppFocusService mAppFocusService;
    private TimingsTraceLog mBootTiming;
    private final CarAudioService mCarAudioService;
    private final CarAudioServiceWrapper mCarAudioServiceWrapper;
    private final CarBluetoothService mCarBluetoothService;
    private final CarBugreportManagerService mCarBugreportManagerService;
    private final CarConfigurationService mCarConfigurationService;
    private final CarDiagnosticService mCarDiagnosticService;
    private final CarDrivingStateService mCarDrivingStateService;
    private final CarEnhancedAudioService mCarEnhancedAudioService;
    private final CarExperimentalFeatureServiceController mCarExperimentalFeatureServiceController;
    private final CarInputService mCarInputService;
    private final CarLocationService mCarLocationService;
    private final CarMediaService mCarMediaService;
    private final CarOccupantZoneService mCarOccupantZoneService;
    private final CarPackageManagerService mCarPackageManagerService;
    private final CarPowerManagementService mCarPowerManagementService;
    private final CarProjectionService mCarProjectionService;
    private final CarPropertyService mCarPropertyService;
    private final CarStatsService mCarStatsService;
    private final CarStorageMonitoringService mCarStorageMonitoringService;
    private CarTestService mCarTestService;
    private final CarTrustedDeviceService mCarTrustedDeviceService;
    private final CarUxRestrictionsManagerService mCarUXRestrictionsService;
    private final CarUserNoticeService mCarUserNoticeService;
    private final CarUserService mCarUserService;
    private final CarWatchdogService mCarWatchdogService;
    private final Context mContext;
    private final CarFeatureController mFeatureController;
    private final FixedActivityService mFixedActivityService;
    private final GarageModeService mGarageModeService;
    private final VehicleHal mHal;
    private ICarServiceHelper mICarServiceHelper;
    private final InstrumentClusterService mInstrumentClusterService;
    private final Object mLock;
    private final OccupantAwarenessService mOccupantAwarenessService;
    private final PerUserCarServiceHelper mPerUserCarServiceHelper;
    private final SystemActivityMonitoringService mSystemActivityMonitoringService;
    private final SystemInterface mSystemInterface;
    private final SystemStateControllerService mSystemStateControllerService;
    private final CarUserManagerHelper mUserManagerHelper;
    private final String mVehicleInterfaceName;
    private final VmsBrokerService mVmsBrokerService;

    public ICarImpl(Context serviceContext, IVehicle vehicle, SystemInterface systemInterface, CanBusErrorNotifier errorNotifier, String vehicleInterfaceName) {
        this(serviceContext, vehicle, systemInterface, errorNotifier, vehicleInterfaceName, null, null);
    }

    ICarImpl(Context serviceContext, IVehicle vehicle, SystemInterface systemInterface, CanBusErrorNotifier errorNotifier, String vehicleInterfaceName, CarUserService carUserService, CarWatchdogService carWatchdogService) {
        String[] disabledFeaturesFromVhal;
        String disabledFeatures;
        this.mLock = new Object();
        this.mContext = serviceContext;
        this.mSystemInterface = systemInterface;
        VehicleHal vehicleHal = new VehicleHal(serviceContext, vehicle);
        this.mHal = vehicleHal;
        VehiclePropValue disabledOptionalFeatureValue = vehicleHal.getIfAvailableOrFailForEarlyStage(VehicleProperty.DISABLED_OPTIONAL_FEATURES, 2);
        String[] disabledFeaturesFromVhal2 = null;
        if (disabledOptionalFeatureValue != null && (disabledFeatures = disabledOptionalFeatureValue.value.stringValue) != null && !disabledFeatures.isEmpty()) {
            disabledFeaturesFromVhal2 = disabledFeatures.split(",");
        }
        if (disabledFeaturesFromVhal2 != null) {
            disabledFeaturesFromVhal = disabledFeaturesFromVhal2;
        } else {
            disabledFeaturesFromVhal = new String[0];
        }
        Resources res = this.mContext.getResources();
        String[] defaultEnabledFeatures = res.getStringArray(R.array.config_allowed_optional_car_features);
        CarFeatureController carFeatureController = new CarFeatureController(serviceContext, defaultEnabledFeatures, disabledFeaturesFromVhal, this.mSystemInterface.getSystemCarDir());
        this.mFeatureController = carFeatureController;
        CarLocalServices.addService(CarFeatureController.class, carFeatureController);
        this.mVehicleInterfaceName = vehicleInterfaceName;
        this.mUserManagerHelper = new CarUserManagerHelper(serviceContext);
        if (carUserService != null) {
            this.mCarUserService = carUserService;
        } else {
            UserManager userManager = (UserManager) serviceContext.getSystemService("user");
            int maxRunningUsers = res.getInteger(android.R.integer.config_displayWhiteBalanceTransitionTimeDecrease);
            this.mCarUserService = new CarUserService(serviceContext, this.mHal.getUserHal(), this.mUserManagerHelper, userManager, ActivityManager.getService(), maxRunningUsers);
        }
        this.mCarOccupantZoneService = new CarOccupantZoneService(serviceContext);
        this.mSystemActivityMonitoringService = new SystemActivityMonitoringService(serviceContext);
        this.mCarPowerManagementService = new CarPowerManagementService(this.mContext, this.mHal.getPowerHal(), systemInterface, this.mCarUserService);
        if (this.mFeatureController.isFeatureEnabled(CarFeatures.FEATURE_CAR_USER_NOTICE_SERVICE)) {
            this.mCarUserNoticeService = new CarUserNoticeService(serviceContext);
        } else {
            this.mCarUserNoticeService = null;
        }
        this.mCarPropertyService = new CarPropertyService(serviceContext, this.mHal.getPropertyHal());
        this.mCarDrivingStateService = new CarDrivingStateService(serviceContext, this.mCarPropertyService);
        this.mCarUXRestrictionsService = new CarUxRestrictionsManagerService(serviceContext, this.mCarDrivingStateService, this.mCarPropertyService);
        if (this.mFeatureController.isFeatureEnabled("occupant_awareness")) {
            this.mOccupantAwarenessService = new OccupantAwarenessService(serviceContext);
        } else {
            this.mOccupantAwarenessService = null;
        }
        this.mCarPackageManagerService = new CarPackageManagerService(serviceContext, this.mCarUXRestrictionsService, this.mSystemActivityMonitoringService);
        this.mPerUserCarServiceHelper = new PerUserCarServiceHelper(serviceContext, this.mCarUserService);
        this.mCarBluetoothService = new CarBluetoothService(serviceContext, this.mPerUserCarServiceHelper);
        this.mCarInputService = new CarInputService(serviceContext, this.mHal.getInputHal(), this.mCarUserService);
        this.mCarProjectionService = new CarProjectionService(serviceContext, null, this.mCarInputService, this.mCarBluetoothService);
        this.mGarageModeService = new GarageModeService(this.mContext);
        this.mAppFocusService = new AppFocusService(serviceContext, this.mSystemActivityMonitoringService);
        this.mCarEnhancedAudioService = new CarEnhancedAudioService(serviceContext);
        this.mCarAudioServiceWrapper = new CarAudioServiceWrapper(serviceContext, this.mCarEnhancedAudioService);
        this.mCarAudioService = null;
        this.mFixedActivityService = new FixedActivityService(serviceContext);
        this.mInstrumentClusterService = new InstrumentClusterService(serviceContext, this.mAppFocusService, this.mCarInputService);
        this.mSystemStateControllerService = new SystemStateControllerService(serviceContext, null, this);
        CarStatsService carStatsService = new CarStatsService(serviceContext);
        this.mCarStatsService = carStatsService;
        carStatsService.init();
        if (this.mFeatureController.isFeatureEnabled("vehicle_map_service")) {
            this.mVmsBrokerService = new VmsBrokerService(this.mContext, this.mCarStatsService);
        } else {
            this.mVmsBrokerService = null;
        }
        if (this.mFeatureController.isFeatureEnabled("diagnostic")) {
            this.mCarDiagnosticService = new CarDiagnosticService(serviceContext, this.mHal.getDiagnosticHal());
        } else {
            this.mCarDiagnosticService = null;
        }
        if (this.mFeatureController.isFeatureEnabled("storage_monitoring")) {
            this.mCarStorageMonitoringService = new CarStorageMonitoringService(serviceContext, systemInterface);
        } else {
            this.mCarStorageMonitoringService = null;
        }
        this.mCarConfigurationService = new CarConfigurationService(serviceContext, new JsonReaderImpl());
        this.mCarLocationService = new CarLocationService(serviceContext);
        this.mCarTrustedDeviceService = new CarTrustedDeviceService(serviceContext);
        this.mCarMediaService = new CarMediaService(serviceContext, this.mCarUserService);
        this.mCarBugreportManagerService = new CarBugreportManagerService(serviceContext);
        if (!Build.IS_USER) {
            this.mCarExperimentalFeatureServiceController = new CarExperimentalFeatureServiceController(serviceContext);
        } else {
            this.mCarExperimentalFeatureServiceController = null;
        }
        if (carWatchdogService == null) {
            this.mCarWatchdogService = new CarWatchdogService(serviceContext);
        } else {
            this.mCarWatchdogService = carWatchdogService;
        }
        CarLocalServices.addService(CarPowerManagementService.class, this.mCarPowerManagementService);
        CarLocalServices.addService(CarPropertyService.class, this.mCarPropertyService);
        CarLocalServices.addService(CarUserService.class, this.mCarUserService);
        CarLocalServices.addService(CarTrustedDeviceService.class, this.mCarTrustedDeviceService);
        CarLocalServices.addService(CarUserNoticeService.class, this.mCarUserNoticeService);
        CarLocalServices.addService(SystemInterface.class, this.mSystemInterface);
        CarLocalServices.addService(CarDrivingStateService.class, this.mCarDrivingStateService);
        CarLocalServices.addService(PerUserCarServiceHelper.class, this.mPerUserCarServiceHelper);
        CarLocalServices.addService(FixedActivityService.class, this.mFixedActivityService);
        CarLocalServices.addService(VmsBrokerService.class, this.mVmsBrokerService);
        CarLocalServices.addService(CarOccupantZoneService.class, this.mCarOccupantZoneService);
        CarLocalServices.addService(AppFocusService.class, this.mAppFocusService);
        List<CarServiceBase> allServices = new ArrayList<>();
        allServices.add(this.mFeatureController);
        allServices.add(this.mCarUserService);
        allServices.add(this.mSystemActivityMonitoringService);
        allServices.add(this.mCarPowerManagementService);
        allServices.add(this.mCarEnhancedAudioService);
        allServices.add(this.mCarAudioServiceWrapper);
        allServices.add(this.mCarPropertyService);
        allServices.add(this.mCarDrivingStateService);
        allServices.add(this.mCarOccupantZoneService);
        allServices.add(this.mCarUXRestrictionsService);
        addServiceIfNonNull(allServices, this.mOccupantAwarenessService);
        allServices.add(this.mCarPackageManagerService);
        allServices.add(this.mCarInputService);
        allServices.add(this.mGarageModeService);
        addServiceIfNonNull(allServices, this.mCarUserNoticeService);
        allServices.add(this.mAppFocusService);
        allServices.add(this.mFixedActivityService);
        allServices.add(this.mInstrumentClusterService);
        allServices.add(this.mSystemStateControllerService);
        allServices.add(this.mPerUserCarServiceHelper);
        allServices.add(this.mCarBluetoothService);
        allServices.add(this.mCarProjectionService);
        addServiceIfNonNull(allServices, this.mCarDiagnosticService);
        addServiceIfNonNull(allServices, this.mCarStorageMonitoringService);
        allServices.add(this.mCarConfigurationService);
        addServiceIfNonNull(allServices, this.mVmsBrokerService);
        allServices.add(this.mCarTrustedDeviceService);
        allServices.add(this.mCarMediaService);
        allServices.add(this.mCarLocationService);
        allServices.add(this.mCarBugreportManagerService);
        allServices.add(this.mCarWatchdogService);
        addServiceIfNonNull(allServices, this.mCarExperimentalFeatureServiceController);
        this.mAllServices = (CarServiceBase[]) allServices.toArray(new CarServiceBase[allServices.size()]);
    }

    private void addServiceIfNonNull(List<CarServiceBase> services, CarServiceBase service) {
        if (service != null) {
            services.add(service);
        }
    }

    void init() {
        this.mBootTiming = new TimingsTraceLog(VHAL_TIMING_TAG, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
        traceBegin("VehicleHal.init");
        this.mHal.init();
        traceEnd();
        traceBegin("CarService.initAllServices");
        for (final CarServiceBase service : this.mAllServices) {
            Log.i(TAG, service.toString() + "init start");
            if (service == this.mCarEnhancedAudioService) {
                Thread thread = new Thread(new Runnable() { // from class: com.android.car.ICarImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        service.init();
                    }
                });
                thread.start();
            } else {
                service.init();
                Log.i(TAG, service.toString() + "init end");
            }
        }
        traceEnd();
    }

    void release() {
        for (int i = this.mAllServices.length - 1; i >= 0; i--) {
            this.mAllServices[i].release();
        }
        this.mHal.release();
    }

    void vehicleHalReconnected(IVehicle vehicle) {
        EventLog.writeEvent(150051, this.mAllServices.length);
        this.mHal.vehicleHalReconnected(vehicle);
        for (CarServiceBase service : this.mAllServices) {
            service.vehicleHalReconnected();
        }
    }

    public void setCarServiceHelper(IBinder helper) {
        EventLog.writeEvent(150052, Binder.getCallingPid());
        assertCallingFromSystemProcess();
        ICarServiceHelper carServiceHelper = ICarServiceHelper.Stub.asInterface(helper);
        synchronized (this.mLock) {
            this.mICarServiceHelper = carServiceHelper;
        }
        this.mSystemInterface.setCarServiceHelper(carServiceHelper);
        this.mCarOccupantZoneService.setCarServiceHelper(carServiceHelper);
    }

    public void onUserLifecycleEvent(int eventType, long timestampMs, int fromUserId, int toUserId) {
        assertCallingFromSystemProcess();
        EventLog.writeEvent(150053, Integer.valueOf(eventType), Integer.valueOf(fromUserId), Integer.valueOf(toUserId));
        Log.d(TAG, "onUserLifecycleEvent(" + CarUserManager.lifecycleEventTypeToString(eventType) + ", " + toUserId + ")");
        this.mCarUserService.onUserLifecycleEvent(eventType, timestampMs, fromUserId, toUserId);
    }

    public void onFirstUserUnlocked(int userId, long timestampMs, long duration, int halResponseTime) {
        this.mCarUserService.onFirstUserUnlocked(userId, timestampMs, duration, halResponseTime);
    }

    public void getInitialUserInfo(int requestType, int timeoutMs, IBinder binder) {
        IResultReceiver receiver = IResultReceiver.Stub.asInterface(binder);
        this.mCarUserService.getInitialUserInfo(requestType, timeoutMs, receiver);
    }

    public void setInitialUser(int userId) {
        EventLog.writeEvent(150054, userId);
        Log.d(TAG, "setInitialUser(): " + userId);
        this.mCarUserService.setInitialUser(userId);
    }

    public boolean isFeatureEnabled(String featureName) {
        return this.mFeatureController.isFeatureEnabled(featureName);
    }

    public int enableFeature(String featureName) {
        return this.mFeatureController.enableFeature(featureName);
    }

    public int disableFeature(String featureName) {
        return this.mFeatureController.disableFeature(featureName);
    }

    public List<String> getAllEnabledFeatures() {
        return this.mFeatureController.getAllEnabledFeatures();
    }

    public List<String> getAllPendingDisabledFeatures() {
        return this.mFeatureController.getAllPendingDisabledFeatures();
    }

    public List<String> getAllPendingEnabledFeatures() {
        return this.mFeatureController.getAllPendingEnabledFeatures();
    }

    public String getCarManagerClassForFeature(String featureName) {
        CarExperimentalFeatureServiceController carExperimentalFeatureServiceController = this.mCarExperimentalFeatureServiceController;
        if (carExperimentalFeatureServiceController == null) {
            return null;
        }
        return carExperimentalFeatureServiceController.getCarManagerClassForFeature(featureName);
    }

    static void assertCallingFromSystemProcess() {
        int uid = Binder.getCallingUid();
        if (uid != 1000) {
            throw new SecurityException("Only allowed from system");
        }
    }

    static void assertCallingFromSystemProcessOrSelf() {
        int uid = Binder.getCallingUid();
        int pid = Binder.getCallingPid();
        if (uid != 1000 && pid != Process.myPid()) {
            throw new SecurityException("Only allowed from system or self");
        }
    }

    public IBinder getCarService(String serviceName) {
        CarTestService carTestService;
        if (!this.mFeatureController.isFeatureEnabled(serviceName)) {
            Log.w(CarLog.TAG_SERVICE, "getCarService for disabled service:" + serviceName);
            return null;
        }
        switch (serviceName) {
            case "audio":
                return this.mCarAudioServiceWrapper;
            case "enhanced_audio":
                return this.mCarEnhancedAudioService;
            case "app_focus":
                return this.mAppFocusService;
            case "package":
                return this.mCarPackageManagerService;
            case "diagnostic":
                assertAnyDiagnosticPermission(this.mContext);
                return this.mCarDiagnosticService;
            case "power":
                assertPowerPermission(this.mContext);
                return this.mCarPowerManagementService;
            case "cabin":
            case "hvac":
            case "info":
            case "property":
            case "sensor":
            case "vendor_extension":
                return this.mCarPropertyService;
            case "car_navigation_service":
                assertNavigationManagerPermission(this.mContext);
                IInstrumentClusterNavigation navService = this.mInstrumentClusterService.getNavigationService();
                if (navService == null) {
                    return null;
                }
                return navService.asBinder();
            case "cluster_service":
                assertClusterManagerPermission(this.mContext);
                return this.mInstrumentClusterService.getManagerService();
            case "projection":
                return this.mCarProjectionService;
            case "vehicle_map_service":
                assertAnyVmsPermission(this.mContext);
                return this.mVmsBrokerService;
            case "vehicle_map_subscriber_service":
                assertVmsSubscriberPermission(this.mContext);
                return this.mVmsBrokerService;
            case "car-service-test":
                assertPermission(this.mContext, Manifest.permission.CAR_TEST_SERVICE);
                synchronized (this.mLock) {
                    if (this.mCarTestService == null) {
                        this.mCarTestService = new CarTestService(this.mContext, this);
                    }
                    carTestService = this.mCarTestService;
                    break;
                }
                return carTestService;
            case "car_bluetooth":
                return this.mCarBluetoothService;
            case "storage_monitoring":
                assertPermission(this.mContext, Manifest.permission.STORAGE_MONITORING);
                return this.mCarStorageMonitoringService;
            case "drivingstate":
                assertDrivingStatePermission(this.mContext);
                return this.mCarDrivingStateService;
            case "uxrestriction":
                return this.mCarUXRestrictionsService;
            case "occupant_awareness":
                return this.mOccupantAwarenessService;
            case "configuration":
                return this.mCarConfigurationService;
            case "trust_enroll":
                assertTrustAgentEnrollmentPermission(this.mContext);
                return this.mCarTrustedDeviceService.getCarTrustAgentEnrollmentService();
            case "car_media":
                return this.mCarMediaService;
            case "car_occupant_zone_service":
                return this.mCarOccupantZoneService;
            case "car_bugreport":
                return this.mCarBugreportManagerService;
            case "car_user_service":
                return this.mCarUserService;
            case "car_watchdog":
                return this.mCarWatchdogService;
            case "android.car.input":
                return this.mCarInputService;
            default:
                IBinder service = null;
                CarExperimentalFeatureServiceController carExperimentalFeatureServiceController = this.mCarExperimentalFeatureServiceController;
                if (carExperimentalFeatureServiceController != null) {
                    service = carExperimentalFeatureServiceController.getCarService(serviceName);
                }
                if (service == null) {
                    Log.w(CarLog.TAG_SERVICE, "getCarService for unknown service:" + serviceName);
                }
                return service;
        }
    }

    public int getCarConnectionType() {
        return 5;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.car.CarServiceBase getCarInternalService(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 846184(0xce968, float:1.185756E-39)
            r2 = 1
            if (r0 == r1) goto L1a
            r1 = 782548936(0x2ea4bfc8, float:7.491924E-11)
            if (r0 == r1) goto L10
        Lf:
            goto L24
        L10:
            java.lang.String r0 = "internal_input"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto Lf
            r0 = 0
            goto L25
        L1a:
            java.lang.String r0 = "system_activity_monitoring"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto Lf
            r0 = r2
            goto L25
        L24:
            r0 = -1
        L25:
            if (r0 == 0) goto L44
            if (r0 == r2) goto L41
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getCarInternalService for unknown service:"
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "CAR.SERVICE"
            android.util.Log.w(r1, r0)
            r0 = 0
            return r0
        L41:
            com.android.car.SystemActivityMonitoringService r0 = r3.mSystemActivityMonitoringService
            return r0
        L44:
            com.android.car.CarInputService r0 = r3.mCarInputService
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.ICarImpl.getCarInternalService(java.lang.String):com.android.car.CarServiceBase");
    }

    public static void assertVehicleHalMockPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_MOCK_VEHICLE_HAL);
    }

    public static void assertNavigationManagerPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_NAVIGATION_MANAGER);
    }

    public static void assertClusterManagerPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_INSTRUMENT_CLUSTER_CONTROL);
    }

    public static void assertPowerPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_POWER);
    }

    public static void assertProjectionPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_PROJECTION);
    }

    public static void assertProjectionStatusPermission(Context context) {
        assertPermission(context, Manifest.permission.ACCESS_CAR_PROJECTION_STATUS);
    }

    public static void assertAnyDiagnosticPermission(Context context) {
        assertAnyPermission(context, Manifest.permission.CAR_DIAGNOSTICS, Manifest.permission.CLEAR_CAR_DIAGNOSTICS);
    }

    public static void assertDrivingStatePermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_DRIVING_STATE);
    }

    public static void assertAnyVmsPermission(Context context) {
        assertAnyPermission(context, Manifest.permission.VMS_SUBSCRIBER, Manifest.permission.VMS_PUBLISHER);
    }

    public static void assertVmsPublisherPermission(Context context) {
        assertPermission(context, Manifest.permission.VMS_PUBLISHER);
    }

    public static void assertVmsSubscriberPermission(Context context) {
        assertPermission(context, Manifest.permission.VMS_SUBSCRIBER);
    }

    public static void assertTrustAgentEnrollmentPermission(Context context) {
        assertPermission(context, Manifest.permission.CAR_ENROLL_TRUST);
    }

    public static void assertPermission(Context context, String permission) {
        if (context.checkCallingOrSelfPermission(permission) != 0) {
            throw new SecurityException("requires " + permission);
        }
    }

    public static boolean hasPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static void assertAnyPermission(Context context, String... permissions) {
        for (String permission : permissions) {
            if (context.checkCallingOrSelfPermission(permission) == 0) {
                return;
            }
        }
        throw new SecurityException("requires any of " + Arrays.toString(permissions));
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
            writer.println("Permission Denial: can't dump CarService from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " without permission android.permission.DUMP");
            return;
        }
        if (args == null || args.length == 0 || (args.length > 0 && "-a".equals(args[0]))) {
            writer.println("*Dump car service*");
            dumpAllServices(writer);
            dumpAllHals(writer);
            return;
        }
        if ("--list".equals(args[0])) {
            dumpListOfServices(writer);
            return;
        }
        if ("--services".equals(args[0])) {
            if (args.length < 2) {
                writer.println("Must pass services to dump when using --services");
                return;
            }
            int length = args.length - 1;
            String[] services = new String[length];
            System.arraycopy(args, 1, services, 0, length);
            dumpIndividualServices(writer, services);
            return;
        }
        if ("--metrics".equals(args[0])) {
            this.mCarStatsService.dump(fd, writer, (String[]) Arrays.copyOfRange(args, 1, args.length));
            return;
        }
        if ("--vms-hal".equals(args[0])) {
            this.mHal.getVmsHal().dumpMetrics(fd);
            return;
        }
        if ("--hal".equals(args[0])) {
            if (args.length == 1) {
                dumpAllHals(writer);
                return;
            }
            int length2 = args.length - 1;
            String[] halNames = new String[length2];
            System.arraycopy(args, 1, halNames, 0, length2);
            this.mHal.dumpSpecificHals(writer, halNames);
            return;
        }
        if ("--list-hals".equals(args[0])) {
            this.mHal.dumpListHals(writer);
            return;
        }
        if ("--user-metrics".equals(args[0])) {
            this.mCarUserService.dumpUserMetrics(writer);
            return;
        }
        if ("--first-user-metrics".equals(args[0])) {
            this.mCarUserService.dumpFirstUserUnlockDuration(writer);
        } else if ("--help".equals(args[0])) {
            showDumpHelp(writer);
        } else {
            execShellCmd(args, writer);
        }
    }

    private void dumpAllHals(PrintWriter writer) {
        writer.println("*Dump Vehicle HAL*");
        writer.println("Vehicle HAL Interface: " + this.mVehicleInterfaceName);
        try {
            this.mHal.dump(writer);
        } catch (Exception e) {
            writer.println("Failed dumping: " + this.mHal.getClass().getName());
            e.printStackTrace(writer);
        }
    }

    private void showDumpHelp(PrintWriter writer) {
        writer.println("Car service dump usage:");
        writer.println("[NO ARG]");
        writer.println("\t  dumps everything (all services and HALs)");
        writer.println("--help");
        writer.println("\t  shows this help");
        writer.println("--list");
        writer.println("\t  lists the name of all services");
        writer.println("--list-hals");
        writer.println("\t  lists the name of all HALs");
        writer.println("--services <SVC1> [SVC2] [SVCN]");
        writer.println("\t  dumps just the specific services, where SVC is just the service class");
        writer.println("\t  name (like CarUserService)");
        writer.println("--vms-hal");
        writer.println("\t  dumps the VMS HAL metrics");
        writer.println("--hal [HAL1] [HAL2] [HALN]");
        writer.println("\t  dumps just the specified HALs (or all of them if none specified),");
        writer.println("\t  where HAL is just the class name (like UserHalService)");
        writer.println("--user-metrics");
        writer.println("\t  dumps user switching and stopping metrics ");
        writer.println("--first-user-metrics");
        writer.println("\t  dumps how long it took to unlock first user since Android started\n");
        writer.println("\t  (or -1 if not unlocked)");
        writer.println("-h");
        writer.println("\t  shows commands usage (NOTE: commands are not available on USER builds");
        writer.println("[ANYTHING ELSE]");
        writer.println("\t  runs the given command (use --h to see the available commands)");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onShellCommand(FileDescriptor in, FileDescriptor out, FileDescriptor err, String[] args, ShellCallback callback, ResultReceiver resultReceiver) throws RemoteException {
        newCarShellCommand().exec(this, in, out, err, args, callback, resultReceiver);
    }

    private CarShellCommand newCarShellCommand() {
        return new CarShellCommand(this.mContext, this.mHal, this.mCarAudioService, this.mCarAudioServiceWrapper, this.mCarPackageManagerService, this.mCarProjectionService, this.mCarPowerManagementService, this.mCarTrustedDeviceService, this.mFixedActivityService, this.mFeatureController, this.mCarInputService, this.mSystemInterface, this.mGarageModeService, this.mCarUserService, this.mCarOccupantZoneService);
    }

    private void dumpListOfServices(PrintWriter writer) {
        for (CarServiceBase service : this.mAllServices) {
            writer.println(service.getClass().getName());
        }
    }

    private void dumpAllServices(PrintWriter writer) {
        writer.println("*Dump all services*");
        for (CarServiceBase service : this.mAllServices) {
            dumpService(service, writer);
        }
        CarTestService carTestService = this.mCarTestService;
        if (carTestService != null) {
            dumpService(carTestService, writer);
        }
    }

    private void dumpIndividualServices(PrintWriter writer, String... serviceNames) {
        for (String serviceName : serviceNames) {
            writer.println("** Dumping " + serviceName + "\n");
            CarServiceBase service = getCarServiceBySubstring(serviceName);
            if (service == null) {
                writer.println("No such service!");
            } else {
                dumpService(service, writer);
            }
            writer.println();
        }
    }

    private CarServiceBase getCarServiceBySubstring(final String className) {
        return (CarServiceBase) Arrays.asList(this.mAllServices).stream().filter(new Predicate() { // from class: com.android.car.-$$Lambda$ICarImpl$Qg3l9n90fFDGeYgIpwrUtQuBZcc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CarServiceBase) obj).getClass().getSimpleName().equals(className);
            }
        }).findFirst().orElse(null);
    }

    private void dumpService(CarServiceBase service, PrintWriter writer) {
        try {
            service.dump(writer);
        } catch (Exception e) {
            writer.println("Failed dumping: " + service.getClass().getName());
            e.printStackTrace(writer);
        }
    }

    void execShellCmd(String[] args, PrintWriter writer) {
        newCarShellCommand().exec(args, writer);
    }

    private void traceBegin(String name) {
        Slog.i(TAG, name);
        this.mBootTiming.traceBegin(name);
    }

    private void traceEnd() {
        this.mBootTiming.traceEnd();
    }
}
