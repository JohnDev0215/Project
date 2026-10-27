package com.byd.car.internal.locale;

import android.content.Context;
import androidx.annotation.NonNull;
import com.byd.car.DiCarLog;
import com.byd.car.ICarLocaleManager;
import com.byd.car.locale.ICarLocaleService;
import com.byd.car.locale.ccTLD;
import com.byd.spi.Spi;
import spi.ca;
import spi.i6;

/* JADX INFO: loaded from: classes2.dex */
@ca(singleton = true)
public class CarLocaleManager implements ICarLocaleManager {
    private Context mContext;

    public CarLocaleManager(@NonNull Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.byd.car.ICarLocaleManager
    public int getCountryCode() {
        try {
            return ((ICarLocaleService) Spi.getService(this.mContext, ICarLocaleService.class)).getCountryCode();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_LOCALE, "Failed to getCountryCode(), returns default value 0");
            return 0;
        }
    }

    @Override // com.byd.car.ICarLocaleManager
    public String getCountryDomain() {
        try {
            return ((ICarLocaleService) Spi.getService(this.mContext, ICarLocaleService.class)).getCountryDomain();
        } catch (Throwable unused) {
            i6.a(DiCarLog.TAG_CAR_LOCALE, "Failed to getCountryDomain(), returns default value unknow");
            return ccTLD.UNKNOW;
        }
    }
}
