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
import com.byd.car.property.ICarPropertyManager;
import com.byd.car.property.internal.CarPropertyManager;
import com.byd.spi.Spi;
import spi.cg;
import spi.d6;
import spi.dh;
import spi.yr;

/* JADX INFO: loaded from: classes.dex */
public class DiCarInternal {
    private static final String AUTHORITY = "com.byd.car.server.provider.CarServiceProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://com.byd.car.server.provider.CarServiceProvider/sync_binder");
    private static DiCarConfig sConfig;

    private static void diagnosis() {
        if (sConfig.debuggable) {
            d6.a().a.execute(new d6.c(5, new Runnable() { // from class: com.byd.car.internal.DiCarInternal.1
                @Override // java.lang.Runnable
                public void run() {
                    DiagnosisManager.get().diagnosis(DiCarInternal.sConfig.context);
                }
            }));
        }
    }

    public static void init(DiCarConfig diCarConfig) {
        sConfig = diCarConfig;
        boolean z = diCarConfig.debuggable;
        dh.a = z;
        dh.b = z;
        dh.c = z;
        cg.b bVar = new cg.b();
        bVar.a = CONTENT_URI;
        cg cgVar = new cg(bVar);
        yr.b bVar2 = new yr.b();
        bVar2.a = cgVar;
        Spi.init(new yr(bVar2));
        Spi.addService(ICarCabinManager.class, CarCabinManager.class);
        Spi.addService(ICarPropertyManager.class, CarPropertyManager.class);
        Spi.addService(ICarInfoManager.class, CarInfoManager.class);
        Spi.addService(ICarAdasManager.class, CarAdasManager.class);
        Spi.addService(ICarLocaleManager.class, CarLocaleManager.class);
        diagnosis();
    }
}
