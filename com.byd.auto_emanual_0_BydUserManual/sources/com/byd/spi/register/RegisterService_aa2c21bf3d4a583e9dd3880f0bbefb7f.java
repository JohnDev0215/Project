package com.byd.spi.register;

import com.byd.car.iot.ICarIotManager;
import com.byd.car.iot.internal.CarIoTManager;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes.dex */
public class RegisterService_aa2c21bf3d4a583e9dd3880f0bbefb7f {
    public static void register() {
        Spi.addService(ICarIotManager.class, CarIoTManager.class);
    }
}
