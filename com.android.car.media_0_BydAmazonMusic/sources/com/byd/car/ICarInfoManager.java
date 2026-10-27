package com.byd.car;

import com.byd.car.carinfo.CarBodyConfig;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarInfoManager {
    String getBrand();

    CarBodyConfig getCarBodyConfig();

    String getCarType();

    int getDriverSeat();

    String getIoTCardNumber();

    String getManufactor();

    int getPowerType();

    String getSerialNumber();

    int getVehicleId();

    String getVehicleType();

    String getVehicleVin();
}
