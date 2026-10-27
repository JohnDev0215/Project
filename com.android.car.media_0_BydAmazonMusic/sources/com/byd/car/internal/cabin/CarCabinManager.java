package com.byd.car.internal.cabin;

import android.content.Context;
import com.byd.car.DiCarLog;
import com.byd.car.ICarCabinManager;
import com.byd.car.cabin.ICarCabinService;
import com.byd.car.carinfo.ICarInfoService;
import com.byd.spi.Spi;
import spi.ca;
import spi.i6;

/* JADX INFO: loaded from: classes2.dex */
@ca(singleton = true)
public class CarCabinManager implements ICarCabinManager {
    private Context mContext;

    public CarCabinManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarCabinManager
    public int getDeviceType() {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).getDeviceType();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getDeviceType(), returns default value -1");
            return -1;
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public int getMirrorType() {
        try {
            return ((ICarInfoService) Spi.getService(this.mContext, ICarInfoService.class)).getCarBodyConfig().mMirrorType;
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getMirrorType(), returns default value 1");
            return 1;
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public long getPlatformInfo() {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).getPlatformInfo();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getPlatformInfo(), returns default value 0L");
            return 0L;
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public int getScreenAreaWidth(int i, int i2) {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).getScreenAreaWidth(i, i2);
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getScreenAreaWidth(), returns default value 0");
            return 0;
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public String getUIName() {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).getUIName();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getUIName(), returns unknown");
            return "unknown";
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public int getUIStyle() {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).getUIStyle();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to getUIStyle(), returns STYLE_UNDEFINED");
            return -1;
        }
    }

    @Override // com.byd.car.ICarCabinManager
    public boolean isStyle(int i) {
        try {
            return ((ICarCabinService) Spi.getService(this.mContext, ICarCabinService.class)).isStyle(i);
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_CABIN, "Failed to isStyle(), returns false");
            return false;
        }
    }
}
