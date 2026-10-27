package com.byd.car.internal.locale;

import android.content.Context;
import com.byd.auto_emanual.Config;
import com.byd.car.DiCarLog;
import com.byd.car.ICarLocaleManager;
import com.byd.car.locale.ICarLocaleService;
import com.byd.car.locale.ccTLD;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.spi.Spi;

/* JADX INFO: loaded from: classes.dex */
@ServiceImpl(service = ICarLocaleManager.class, singleton = Config.USE_PRODUCT_URL)
public class CarLocaleManager implements ICarLocaleManager {
    private Context mContext;

    public CarLocaleManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarLocaleManager
    public String getCountryDomain() {
        try {
            return ((ICarLocaleService) Spi.getService(this.mContext, ICarLocaleService.class)).getCountryDomain();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_LOCALE, "Failed to getCountryDomain(), returns default value unknow");
            return ccTLD.UNKNOW;
        }
    }

    @Override // com.byd.car.ICarLocaleManager
    public int getCountryCode() {
        try {
            return ((ICarLocaleService) Spi.getService(this.mContext, ICarLocaleService.class)).getCountryCode();
        } catch (Throwable unused) {
            LogUtils.e(DiCarLog.TAG_CAR_LOCALE, "Failed to getCountryCode(), returns default value 0");
            return 0;
        }
    }
}
