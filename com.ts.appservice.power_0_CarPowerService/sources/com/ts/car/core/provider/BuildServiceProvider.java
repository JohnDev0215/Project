package com.ts.car.core.provider;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import com.ts.androidauto.sdk.PlatformAndroidAutoAppManager;
import com.ts.androidauto.sdk.PlatformAndroidAutoDeviceListManager;
import com.ts.androidauto.sdk.PlatformAndroidAutoServiceManager;
import com.ts.car.sdk.core.PlatformCoreSystemService;
import com.ts.car.sdk.dcm.PlatformDeviceConnectionManager;
import com.ts.car.sdk.dm.PlatformDeviceManagerService;
import com.ts.car.sdk.firewall.PlatformFirewallManager;
import com.ts.car.sdk.input.PlatformHardKeyAdminManager;
import com.ts.car.sdk.input.PlatformHardKeyManager;
import com.ts.car.sdk.network.PlatformExtConnectivityManager;
import com.ts.car.sdk.time.PlatformTimeSyncManager;
import com.ts.carplay.manager.PlatformCarPlayAppManager;
import com.ts.carplay.manager.PlatformCarPlayDeviceListManager;
import com.ts.carplay.manager.PlatformCarPlayServiceManager;

/* JADX INFO: loaded from: classes.dex */
public class BuildServiceProvider implements IServiceProvider {

    private enum ServiceType {
        None,
        ts_car_service_dm_IDeviceManager,
        ts_car_service_carplay_ICarPlayAppManager,
        ts_car_service_androidauto_IAndroidAutoDeviceListManager,
        ts_car_service_core_ICoreSystemService,
        ts_car_service_ncm_IExtConnectivityManager,
        ts_car_service_input_IHardKeyManager,
        ts_car_service_androidauto_IAndroidAutoServiceManager,
        ts_car_service_time_ITimeSyncManager,
        ts_car_service_androidauto_IAndroidAutoAppManager,
        ts_car_service_carplay_ICarPlayDeviceListManager,
        ts_car_service_firewall_IFirewallManager,
        ts_car_service_carplay_ICarPlayServiceManager,
        ts_car_service_dcm_IDeviceConnectionManager,
        ts_car_service_input_IHardKeyAdminManager;

        static ServiceType getType(String clazz) {
            try {
                return valueOf(clazz.replace(".", "_"));
            } catch (IllegalArgumentException e) {
                Log.w("BuildServiceProvider", "Illegal clazz argument: " + clazz);
                return None;
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                return None;
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.car.core.provider.BuildServiceProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType;

        static {
            int[] iArr = new int[ServiceType.values().length];
            $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType = iArr;
            try {
                iArr[ServiceType.ts_car_service_dm_IDeviceManager.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_carplay_ICarPlayAppManager.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_androidauto_IAndroidAutoDeviceListManager.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_core_ICoreSystemService.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_ncm_IExtConnectivityManager.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_input_IHardKeyManager.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_androidauto_IAndroidAutoServiceManager.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_time_ITimeSyncManager.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_androidauto_IAndroidAutoAppManager.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_carplay_ICarPlayDeviceListManager.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_firewall_IFirewallManager.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_carplay_ICarPlayServiceManager.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_dcm_IDeviceConnectionManager.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.ts_car_service_input_IHardKeyAdminManager.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
        }
    }

    @Override // com.ts.car.core.provider.IServiceProvider
    public <T> T getService(Class<T> clazz, Context context, IBinder binder) {
        switch (AnonymousClass1.$SwitchMap$com$ts$car$core$provider$BuildServiceProvider$ServiceType[ServiceType.getType(clazz.getName()).ordinal()]) {
            case 1:
                return clazz.cast(new PlatformDeviceManagerService(context, binder));
            case 2:
                return clazz.cast(new PlatformCarPlayAppManager(context, binder));
            case 3:
                return clazz.cast(new PlatformAndroidAutoDeviceListManager(context, binder));
            case 4:
                return clazz.cast(new PlatformCoreSystemService(context, binder));
            case 5:
                return clazz.cast(new PlatformExtConnectivityManager(context, binder));
            case 6:
                return clazz.cast(new PlatformHardKeyManager(context, binder));
            case 7:
                return clazz.cast(new PlatformAndroidAutoServiceManager(context, binder));
            case 8:
                return clazz.cast(new PlatformTimeSyncManager(context, binder));
            case 9:
                return clazz.cast(new PlatformAndroidAutoAppManager(context, binder));
            case 10:
                return clazz.cast(new PlatformCarPlayDeviceListManager(context, binder));
            case 11:
                return clazz.cast(new PlatformFirewallManager(context, binder));
            case 12:
                return clazz.cast(new PlatformCarPlayServiceManager(context, binder));
            case 13:
                return clazz.cast(new PlatformDeviceConnectionManager(context, binder));
            case 14:
                return clazz.cast(new PlatformHardKeyAdminManager(context, binder));
            default:
                return null;
        }
    }
}
