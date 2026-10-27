package com.byd.car.internal.carinfo;

import android.content.Context;
import android.util.Log;
import com.byd.auto_emanual.Config;
import com.byd.car.BuildConfig;
import com.byd.car.DiCarLog;
import com.byd.car.ICarInfoManager;
import com.byd.car.carinfo.CarBodyConfig;
import com.byd.car.carinfo.ICarInfoService;
import com.byd.car.property.CarPropertyValue;
import com.byd.car.property.ICarPropertyService;
import com.byd.datasource.feature.Response;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = ICarInfoManager.class, singleton = Config.USE_PRODUCT_URL)
public class CarInfoManager implements ICarInfoManager {
    private Context mContext;

    public CarInfoManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarInfoManager
    public String getVehicleVin() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getVehicleVin();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to get vehicleVin, returns default value \"\"");
            return "";
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getSerialNumber() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getSerialNumber();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to get serialnumber, returns default value \"\"");
            return "";
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getIoTCardNumber() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getIoTCardNumber();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getIoTCardNumber(), returns default value \"\"");
            return "";
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getBrand() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getBrand();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getBrand(), returns default value unknown");
            return "unknown";
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getManufactor() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getManufactor();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getManufactor(), returns default value \"\"");
            return "unknown";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.car.ICarInfoManager
    public int getVehicleId() {
        try {
            return ((Integer) ((CarPropertyValue) ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getProperty("model_value").result).getValue()).intValue();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getVehicleId(), returns default value 0");
            return 0;
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public int getDriverSeat() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getDriverSeat();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getVehicleId(), returns default value 1");
            return 1;
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getCarType() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getCarType(getVehicleId());
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getCarType(), returns default value unknown");
            return "unknown";
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public String getVehicleType() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getVehicleType();
        } catch (Throwable th) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getVehicleType(), returns default value unknown");
            Log.d(DiCarLog.TAG_CAR_INFO, "e: " + th.getMessage());
            return "unknown";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.car.ICarInfoManager
    public int getPowerType() {
        try {
            Response property = ((ICarPropertyService) Spi.getService(this.mContext, ICarPropertyService.class)).getProperty("power_type");
            if (property == null || property.result == 0) {
                return -1;
            }
            int iIntValue = ((Integer) ((CarPropertyValue) property.result).getValue()).intValue();
            if (iIntValue == 0 || iIntValue == 1 || iIntValue == 2) {
                return iIntValue;
            }
            return -1;
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_INFO, "Failed to getPowerType(), returns default value -1");
            return -1;
        }
    }

    @Override // com.byd.car.ICarInfoManager
    public CarBodyConfig getCarBodyConfig() {
        CarBodyConfig carBodyConfig;
        ICarInfoService iCarInfoService = (ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class);
        if (iCarInfoService != null) {
            try {
                carBodyConfig = iCarInfoService.getCarBodyConfig();
            } catch (Throwable th) {
                LogUtils.w(BuildConfig.TAG, "getCarBodyConfig error", th);
                carBodyConfig = null;
            }
        } else {
            carBodyConfig = null;
        }
        return carBodyConfig == null ? new CarBodyConfig() : carBodyConfig;
    }
}
