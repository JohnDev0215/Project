package com.ts.lib.car;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ts.lib.car.CarServiceLoader;
import com.ts.lib.car.hardware.ambient.CarVendorAmbientManager;
import com.ts.lib.car.hardware.assist.CarVendorAssistManager;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyManager;
import com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager;
import com.ts.lib.car.hardware.charging.CarVendorChargingManager;
import com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager;
import com.ts.lib.car.hardware.digitalkey.CarVendorDigitalkeyManager;
import com.ts.lib.car.hardware.dipilot.CarVendorDipilotManager;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager;
import com.ts.lib.car.hardware.energy.CarVendorEnergyManager;
import com.ts.lib.car.hardware.engine.CarVendorEngineManager;
import com.ts.lib.car.hardware.fragrance.CarVendorFragranceManager;
import com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager;
import com.ts.lib.car.hardware.health.CarVendorHealthManager;
import com.ts.lib.car.hardware.hvac.CarVendorHvacManager;
import com.ts.lib.car.hardware.instrument.CarVendorInstrumentManager;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import com.ts.lib.car.hardware.ota.CarVendorOtaManager;
import com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager;
import com.ts.lib.car.hardware.power.CarVendorPowerManager;
import com.ts.lib.car.hardware.radar.CarVendorRadarManager;
import com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager;
import com.ts.lib.car.hardware.reminder.CarVendorReminderManager;
import com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager;
import com.ts.lib.car.hardware.sensor.CarVendorSensorManager;
import com.ts.lib.car.hardware.setting.CarVendorSettingManager;
import com.ts.lib.car.hardware.speed.CarVendorSpeedManager;
import com.ts.lib.car.hardware.statics.CarVendorStaticManager;
import com.ts.lib.car.hardware.tyre.CarVendorTyreManager;
import com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager;
import com.ts.lib.car.hardware.yun.CarVendorYunManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class Car {
    private static final Map<Class, String> CLASS_TO_SERVICE_NAME;
    private static final Set<Integer> CONNECTION_TYPES = new HashSet();
    public static final int CONNECTION_TYPE_ADB_EMULATOR = 4;
    public static final int CONNECTION_TYPE_EMBEDDED = 5;
    public static final int CONNECTION_TYPE_EMULATOR = 0;
    public static final int CONNECTION_TYPE_ON_DEVICE_EMULATOR = 3;
    public static final int CONNECTION_TYPE_UNKNOWN = -1;
    public static final int CONNECTION_TYPE_USB = 1;
    public static final int CONNECTION_TYPE_WIFI = 2;
    private static final String FEATURE_AUTOMOTIVE = "android.hardware.type.automotive";
    public static final String PERMISSION_FUEL = "android.car.permission.CAR_FUEL";
    public static final String PERMISSION_MILEAGE = "android.car.permission.CAR_MILEAGE";
    public static final String PERMISSION_SPEED = "android.car.permission.CAR_SPEED";
    public static final String PERMISSION_VEHICLE_DYNAMICS_STATE = "android.car.permission.VEHICLE_DYNAMICS_STATE";
    public static final String PERMISSION_VENDOR_EXTENSION = "android.car.permission.CAR_VENDOR_EXTENSION";
    private static final String PROJECTED_CAR_SERVICE_LOADER = "com.google.android.apps.auto.sdk.service.CarServiceLoaderGms";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_DISCONNECTED = 0;
    private static final String TAG = "CAR.TS.LIB.CAR";
    public static final String VENDOR_AMBIENT_SERVICE = "ambient";
    public static final String VENDOR_ASSIST_SERVICE = "assist";
    public static final String VENDOR_CARBODY_SERVICE = "carbody";
    public static final String VENDOR_CARMODE_SERVICE = "carmode";
    public static final String VENDOR_CHARGING_SERVICE = "charging";
    public static final String VENDOR_CLOUD_MESSAGE_SERVICE = "cloud_message";
    public static final String VENDOR_COLLECTDATA_SERVICE = "collectdata";
    public static final String VENDOR_DIGITALKEY_SERVICE = "digitalkey";
    public static final String VENDOR_DIPILOT_SERVICE = "dipilot";
    public static final String VENDOR_DOORLOCK_SERVICE = "doorlock";
    public static final String VENDOR_ENERGY_SERVICE = "energy";
    public static final String VENDOR_ENGINE_SERVICE = "engine";
    public static final String VENDOR_FRAGRANCE_SERVICE = "fragrance";
    public static final String VENDOR_GEARBOX_SERVICE = "gearbox";
    public static final String VENDOR_HEALTH_SERVICE = "health";
    public static final String VENDOR_HVAC_SERVICE = "hvac";
    public static final String VENDOR_INSTRUMENT_SERVICE = "instrument";
    public static final String VENDOR_LIGHT_SERVICE = "light";
    public static final String VENDOR_OTA_SERVICE = "ota";
    public static final String VENDOR_PM2P5_SERVICE = "pm2p5";
    public static final String VENDOR_POWER_SERVICE = "power";
    public static final String VENDOR_RADAR_SERVICE = "radar";
    public static final String VENDOR_REARVIEWMIRROR_SERVICE = "rearviewmirror";
    public static final String VENDOR_REMINDER_SERVICE = "reminder";
    public static final String VENDOR_SAFETYBELT_SERVICE = "safetybelt";
    public static final String VENDOR_SENSOR_SERVICE = "sensor";
    public static final String VENDOR_SETTING_SERVICE = "setting";
    public static final String VENDOR_SPEED_SERVICE = "speed";
    public static final String VENDOR_STATICS_SERVICE = "statics";
    public static final String VENDOR_TYRE_SERVICE = "tyre";
    public static final String VENDOR_UISTYLE_SERVICE = "uistyle";
    public static final String VENDOR_WHUD_SERVICE = "whud";
    public static final String VENDOR_XCALL_SERVICE = "xcall";
    public static final String VENDOR_YUN_SERVICE = "yun";
    private CarConnectionCallback mCarConnectionCallback;
    private final CarServiceLoader.CarConnectionCallbackProxy mCarConnectionCallbackProxy;
    private final Object mCarManagerLock;
    private final CarServiceLoader mCarServiceLoader;
    private int mConnectionState;
    private Context mContext;
    private final Handler mEventHandler;
    private final HashMap<String, CarVendorManagerBase> mServiceMap;

    public @interface ConnectionType {
    }

    static {
        Map<Class, String> mapping = new HashMap<>();
        mapping.put(CarVendorChargingManager.class, VENDOR_CHARGING_SERVICE);
        mapping.put(CarVendorDipilotManager.class, VENDOR_DIPILOT_SERVICE);
        mapping.put(CarVendorDoorlockManager.class, VENDOR_DOORLOCK_SERVICE);
        mapping.put(CarVendorEnergyManager.class, VENDOR_ENERGY_SERVICE);
        mapping.put(CarVendorEngineManager.class, VENDOR_ENGINE_SERVICE);
        mapping.put(CarVendorGearboxManager.class, VENDOR_GEARBOX_SERVICE);
        mapping.put(CarVendorHealthManager.class, VENDOR_HEALTH_SERVICE);
        mapping.put(CarVendorHvacManager.class, VENDOR_HVAC_SERVICE);
        mapping.put(CarVendorInstrumentManager.class, VENDOR_INSTRUMENT_SERVICE);
        mapping.put(CarVendorLightManager.class, VENDOR_LIGHT_SERVICE);
        mapping.put(CarVendorOtaManager.class, VENDOR_OTA_SERVICE);
        mapping.put(CarVendorRadarManager.class, VENDOR_RADAR_SERVICE);
        mapping.put(CarVendorSafetybeltManager.class, VENDOR_SAFETYBELT_SERVICE);
        mapping.put(CarVendorSensorManager.class, VENDOR_SENSOR_SERVICE);
        mapping.put(CarVendorSettingManager.class, VENDOR_SETTING_SERVICE);
        mapping.put(CarVendorStaticManager.class, VENDOR_STATICS_SERVICE);
        mapping.put(CarVendorTyreManager.class, VENDOR_TYRE_SERVICE);
        mapping.put(CarVendorUistyleManager.class, VENDOR_UISTYLE_SERVICE);
        mapping.put(CarVendorCarbodyManager.class, VENDOR_CARBODY_SERVICE);
        mapping.put(CarVendorFragranceManager.class, VENDOR_FRAGRANCE_SERVICE);
        mapping.put(CarVendorPm2p5Manager.class, VENDOR_PM2P5_SERVICE);
        mapping.put(CarVendorPowerManager.class, VENDOR_POWER_SERVICE);
        mapping.put(CarVendorAssistManager.class, VENDOR_ASSIST_SERVICE);
        mapping.put(CarVendorRearviewmirrorManager.class, VENDOR_REARVIEWMIRROR_SERVICE);
        mapping.put(CarVendorCollectdataManager.class, VENDOR_COLLECTDATA_SERVICE);
        mapping.put(CarVendorYunManager.class, VENDOR_YUN_SERVICE);
        mapping.put(CarVendorCarmodeManager.class, VENDOR_CARMODE_SERVICE);
        mapping.put(CarVendorDigitalkeyManager.class, VENDOR_DIGITALKEY_SERVICE);
        mapping.put(CarVendorReminderManager.class, "reminder");
        mapping.put(CarVendorSpeedManager.class, VENDOR_SPEED_SERVICE);
        mapping.put(CarVendorAmbientManager.class, VENDOR_AMBIENT_SERVICE);
        CLASS_TO_SERVICE_NAME = Collections.unmodifiableMap(mapping);
        CONNECTION_TYPES.add(0);
        CONNECTION_TYPES.add(1);
        CONNECTION_TYPES.add(2);
        CONNECTION_TYPES.add(3);
        CONNECTION_TYPES.add(4);
        CONNECTION_TYPES.add(5);
    }

    private Car(Context context, CarConnectionCallback carConnectionCallback, Handler handler) {
        this.mCarManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mConnectionState = 0;
        this.mCarConnectionCallbackProxy = new CarServiceLoader.CarConnectionCallbackProxy() { // from class: com.ts.lib.car.Car.1
            @Override // com.ts.lib.car.CarServiceLoader.CarConnectionCallbackProxy
            public void onConnected() {
                synchronized (Car.this) {
                    Car.this.mConnectionState = 2;
                }
                if (Car.this.mCarConnectionCallback != null) {
                    Car.this.mCarConnectionCallback.onConnected(Car.this);
                }
            }

            @Override // com.ts.lib.car.CarServiceLoader.CarConnectionCallbackProxy
            public void onDisconnected() {
                Car.this.mServiceMap.clear();
                synchronized (Car.this) {
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.mConnectionState = 0;
                    if (Car.this.mCarConnectionCallback != null) {
                        Car.this.mCarConnectionCallback.onDisconnected(Car.this);
                        Car.this.mCarConnectionCallback = null;
                    }
                    if (Car.this.mContext != null) {
                        Car.this.mContext = null;
                    }
                    Car.this.disconnect();
                }
            }
        };
        this.mContext = context;
        Log.checkLogLogger(context);
        this.mCarConnectionCallback = carConnectionCallback;
        if (handler == null) {
            Looper looper = Looper.getMainLooper();
            handler = new Handler(looper);
        }
        this.mEventHandler = handler;
        if (this.mContext.getPackageManager().hasSystemFeature(FEATURE_AUTOMOTIVE)) {
            this.mCarServiceLoader = new CarServiceLoaderEmbedded(context, this.mCarConnectionCallbackProxy, this.mEventHandler);
        } else {
            this.mCarServiceLoader = loadCarServiceLoader(PROJECTED_CAR_SERVICE_LOADER, context, this.mCarConnectionCallbackProxy, this.mEventHandler);
        }
    }

    public Car(CarServiceLoader serviceLoader) throws CarNotConnectedException {
        this.mCarManagerLock = new Object();
        this.mServiceMap = new HashMap<>();
        this.mConnectionState = 0;
        this.mCarConnectionCallbackProxy = new CarServiceLoader.CarConnectionCallbackProxy() { // from class: com.ts.lib.car.Car.1
            @Override // com.ts.lib.car.CarServiceLoader.CarConnectionCallbackProxy
            public void onConnected() {
                synchronized (Car.this) {
                    Car.this.mConnectionState = 2;
                }
                if (Car.this.mCarConnectionCallback != null) {
                    Car.this.mCarConnectionCallback.onConnected(Car.this);
                }
            }

            @Override // com.ts.lib.car.CarServiceLoader.CarConnectionCallbackProxy
            public void onDisconnected() {
                Car.this.mServiceMap.clear();
                synchronized (Car.this) {
                    if (Car.this.mConnectionState == 0) {
                        return;
                    }
                    Car.this.mConnectionState = 0;
                    if (Car.this.mCarConnectionCallback != null) {
                        Car.this.mCarConnectionCallback.onDisconnected(Car.this);
                        Car.this.mCarConnectionCallback = null;
                    }
                    if (Car.this.mContext != null) {
                        Car.this.mContext = null;
                    }
                    Car.this.disconnect();
                }
            }
        };
        if (!serviceLoader.isConnected()) {
            throw new CarNotConnectedException();
        }
        this.mCarServiceLoader = serviceLoader;
        this.mEventHandler = serviceLoader.getEventHandler();
        this.mContext = serviceLoader.getContext();
        this.mConnectionState = 2;
        this.mCarConnectionCallback = null;
    }

    public static Car createCar(Context context, CarConnectionCallback carConnectionCallback, Handler handler) {
        try {
            return new Car(context, carConnectionCallback, handler);
        } catch (IllegalArgumentException ex) {
            Log.error(TAG, "Car failed to be created");
            ex.printStackTrace();
            return null;
        }
    }

    public static Car createCar(Context context, CarConnectionCallback carConnectionCallback) {
        return createCar(context, carConnectionCallback, null);
    }

    private CarServiceLoader loadCarServiceLoader(String carServiceLoaderClassName, Context context, CarServiceLoader.CarConnectionCallbackProxy carConnectionCallbackProxy, Handler eventHandler) throws IllegalArgumentException {
        try {
            Class<? extends U> clsAsSubclass = Class.forName(carServiceLoaderClassName).asSubclass(CarServiceLoader.class);
            try {
                Constructor<? extends CarServiceLoader> ctor = clsAsSubclass.getDeclaredConstructor(Context.class, CarServiceLoader.CarConnectionCallbackProxy.class, Handler.class);
                try {
                    return (CarServiceLoader) ctor.newInstance(context, carConnectionCallbackProxy, eventHandler);
                } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException ex) {
                    throw new IllegalArgumentException("Cannot construct CarServiceLoader, constructor failed for " + clsAsSubclass.getName(), ex);
                }
            } catch (NoSuchMethodException ex2) {
                throw new IllegalArgumentException("Cannot construct CarServiceLoader, no constructor: " + carServiceLoaderClassName, ex2);
            }
        } catch (ClassNotFoundException ex3) {
            throw new IllegalArgumentException("Cannot find CarServiceLoader implementation:" + carServiceLoaderClassName, ex3);
        }
    }

    public void connect() throws IllegalStateException {
        synchronized (this) {
            if (this.mConnectionState != 0) {
                throw new IllegalStateException("already connected or connecting");
            }
            this.mConnectionState = 1;
            this.mCarServiceLoader.connect();
        }
    }

    public void disconnect() {
        synchronized (this) {
            tearDownCarManagers();
            this.mConnectionState = 0;
            this.mCarServiceLoader.disconnect();
        }
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = this.mConnectionState == 2;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this) {
            z = true;
            if (this.mConnectionState != 1) {
                z = false;
            }
        }
        return z;
    }

    public Object getCarManager(String serviceName) throws CarNotConnectedException {
        Object manager;
        synchronized (this.mCarManagerLock) {
            manager = this.mServiceMap.get(serviceName);
            if (manager == null) {
                manager = this.mCarServiceLoader.getCarManager(serviceName);
            }
            if (manager != null && (manager instanceof CarVendorManagerBase)) {
                this.mServiceMap.put(serviceName, (CarVendorManagerBase) manager);
            }
        }
        return manager;
    }

    public <T> T getCarManager(Class<T> cls) throws CarNotConnectedException {
        String str = CLASS_TO_SERVICE_NAME.get(cls);
        if (str == null) {
            return null;
        }
        return (T) getCarManager(str);
    }

    public int getCarConnectionType() throws CarNotConnectedException {
        int carConnectionType = this.mCarServiceLoader.getCarConnectionType();
        if (!CONNECTION_TYPES.contains(Integer.valueOf(carConnectionType))) {
            return -1;
        }
        return carConnectionType;
    }

    private void tearDownCarManagers() {
        synchronized (this.mCarManagerLock) {
            this.mServiceMap.clear();
        }
    }
}
