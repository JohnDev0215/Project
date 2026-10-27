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
import spi.c5;
import spi.ga;
import spi.h3;
import spi.i6;

/* JADX INFO: loaded from: classes2.dex */
public class DiCarInternal {
    private static final String AUTHORITY = "com.byd.car.server.provider.CarServiceProvider";
    private static final Uri CONTENT_URI = Uri.parse("content://com.byd.car.server.provider.CarServiceProvider/sync_binder");
    private static DiCarConfig sConfig;

    private static void diagnosis() {
        if (sConfig.debuggable) {
            h3.a().a.execute(new h3.c(5, new Runnable() { // from class: com.byd.car.internal.DiCarInternal.1
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
        i6.a = z;
        i6.b = z;
        i6.c = z;
        c5.b bVar = new c5.b();
        bVar.a = CONTENT_URI;
        c5 c5Var = new c5(bVar);
        ga.b bVar2 = new ga.b();
        bVar2.a = c5Var;
        Spi.init(new ga(bVar2));
        Spi.addService(ICarCabinManager.class, CarCabinManager.class);
        Spi.addService(ICarPropertyManager.class, CarPropertyManager.class);
        Spi.addService(ICarInfoManager.class, CarInfoManager.class);
        Spi.addService(ICarAdasManager.class, CarAdasManager.class);
        Spi.addService(ICarLocaleManager.class, CarLocaleManager.class);
        diagnosis();
    }
}
