package com.byd.car.internal;

import android.net.Uri;
import com.byd.car.DiCarConfig;
import com.byd.car.ICarAdasManager;
import com.byd.car.ICarCabinManager;
import com.byd.car.ICarInfoManager;
import com.byd.car.ICarLocaleManager;
import com.byd.car.internal.adas.CarAdasManager;
import com.byd.car.internal.cabin.CarCabinManager;
import com.byd.car.internal.carinfo.CarInfoManager;
import com.byd.car.internal.diagnosis.DiagnosisManager;
import com.byd.car.internal.locale.CarLocaleManager;
import com.byd.car.iot.ICarIotManager;
import com.byd.car.iot.internal.CarIoTManager;
import com.byd.car.property.ICarPropertyManager;
import com.byd.car.property.internal.CarPropertyManager;
import com.byd.foundation.executor.DiExecutors;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.Spi;
import com.byd.spi.config.IpcConfig;
import com.byd.spi.config.SpiConfig;

/* JADX INFO: loaded from: classes.dex */
public class DiCarInternal {
    private static final String AUTHORITY = "com.byd.car.server.provider.CarServiceProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://com.byd.car.server.provider.CarServiceProvider/sync_binder");
    private static DiCarConfig sConfig;

    public static void init(DiCarConfig diCarConfig) {
        sConfig = diCarConfig;
        LogUtils.setDebugEnable(diCarConfig.debuggable);
        Spi.init(new SpiConfig.Builder().setIpcConfig(new IpcConfig.Builder().setContentUri(CONTENT_URI).build()).build());
        Spi.addService(ICarCabinManager.class, CarCabinManager.class);
        Spi.addService(ICarPropertyManager.class, CarPropertyManager.class);
        Spi.addService(ICarInfoManager.class, CarInfoManager.class);
        Spi.addService(ICarAdasManager.class, CarAdasManager.class);
        Spi.addService(ICarLocaleManager.class, CarLocaleManager.class);
        Spi.addService(ICarIotManager.class, CarIoTManager.class);
        diagnosis();
    }

    private static void diagnosis() {
        if (sConfig.debuggable) {
            DiExecutors.get().execute(new Runnable() { // from class: com.byd.car.internal.DiCarInternal.1
                @Override // java.lang.Runnable
                public void run() {
                    DiagnosisManager.get().diagnosis(DiCarInternal.sConfig.context);
                }
            });
        }
    }
}
