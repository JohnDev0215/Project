package com.ts.ota.otasdkmgr;

import com.engine.tbox.TboxManager;
import com.ts.lib.car.hardware.carbody.CarVendorCarbodyMgr;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockMgr;
import com.ts.lib.car.hardware.ota.CarVendorOtaMgr;
import com.ts.lib.power.manager.CarPowerManager;
import com.ts.ota.otasdkmgr.adapter.TSOtaMasterAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface IOtaTsUpdateServiceProxy {
    CarVendorCarbodyMgr getCarBodyMgr();

    CarVendorDoorlockMgr getCarDoorMgr();

    CarPowerManager getCarPowerManager();

    CarVendorOtaMgr getCarVendorOtaMgr();

    TSOtaMasterAdapter getTSOtaMasterAdapter();

    TboxManager getTboxManager();
}
