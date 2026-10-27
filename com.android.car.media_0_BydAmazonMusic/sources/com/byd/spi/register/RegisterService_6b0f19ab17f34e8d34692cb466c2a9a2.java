package com.byd.spi.register;

import com.byd.car.ICarAdasManager;
import com.byd.car.ICarCabinManager;
import com.byd.car.ICarInfoManager;
import com.byd.car.ICarLocaleManager;
import com.byd.car.internal.adas.CarAdasManager;
import com.byd.car.internal.cabin.CarCabinManager;
import com.byd.car.internal.carinfo.CarInfoManager;
import com.byd.car.internal.locale.CarLocaleManager;
import com.byd.car.property.ICarPropertyManager;
import com.byd.car.property.internal.CarPropertyManager;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes2.dex */
public class RegisterService_6b0f19ab17f34e8d34692cb466c2a9a2 {
    public static void register() {
        Spi.addService(ICarPropertyManager.class, CarPropertyManager.class);
        Spi.addService(ICarCabinManager.class, CarCabinManager.class);
        Spi.addService(ICarLocaleManager.class, CarLocaleManager.class);
        Spi.addService(ICarInfoManager.class, CarInfoManager.class);
        Spi.addService(ICarAdasManager.class, CarAdasManager.class);
    }
}
