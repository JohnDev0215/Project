package com.byd.car.internal.adas;

import android.content.Context;
import com.byd.auto_emanual.Config;
import com.byd.car.DiCarLog;
import com.byd.car.ICarAdasManager;
import com.byd.car.adas.ICarAdasService;
import com.byd.car.adas.VehicleAdasVendor;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = ICarAdasManager.class, singleton = Config.USE_PRODUCT_URL)
public class CarAdasManager implements ICarAdasManager {
    private Context mContext;

    public CarAdasManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarAdasManager
    public VehicleAdasVendor getAdasVendor() {
        try {
            return VehicleAdasVendor.fromName(((ICarAdasService) Spi.getService(this.mContext, ICarAdasService.class)).getAdasVendor());
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_CABIN, "Failed to getAdasVendor(), returns default value " + VehicleAdasVendor.OTHER);
            return VehicleAdasVendor.OTHER;
        }
    }
}
