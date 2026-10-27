package com.byd.car.internal.adas;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.car.DiCarLog;
import com.byd.car.ICarAdasManager;
import com.byd.car.adas.ICarAdasService;
import com.byd.car.adas.VehicleAdasVendor;
import com.byd.spi.Spi;
import spi.dh;
import spi.e;
import spi.zo;

/* JADX INFO: loaded from: classes.dex */
@zo(singleton = true)
public class CarAdasManager implements ICarAdasManager {
    private Context mContext;

    public CarAdasManager(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarAdasManager
    public VehicleAdasVendor getAdasVendor() {
        try {
            return VehicleAdasVendor.fromName(((ICarAdasService) Spi.getService(this.mContext, ICarAdasService.class)).getAdasVendor());
        } catch (Throwable unused) {
            StringBuilder sbK = e.k("Failed to getAdasVendor(), returns default value ");
            VehicleAdasVendor vehicleAdasVendor = VehicleAdasVendor.OTHER;
            sbK.append(vehicleAdasVendor);
            dh.a(DiCarLog.TAG_CAR_CABIN, sbK.toString());
            return vehicleAdasVendor;
        }
    }
}
