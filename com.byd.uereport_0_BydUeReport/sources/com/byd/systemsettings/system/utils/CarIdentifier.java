package com.byd.systemsettings.system.utils;

import android.content.Context;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.system.BydSystemSettings;
import n2.b;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public final class CarIdentifier {
    private static final String COUNTRY_CODE_KOREAN = "82";
    private static final String TAG = "CarIdentifier";
    public static volatile int carModelCode;
    public static CarModel$Brand mBrand = CarModel$Brand.DYNASTY;
    public static b mConfig = null;
    public static volatile int mUIStyle;

    private static void identify() {
        a.c(TAG, "---identify---carModelCode: " + carModelCode);
    }

    private static void initUIStyle() {
        int i8 = mUIStyle;
        if (i8 == 0) {
            mBrand = CarModel$Brand.DYNASTY;
        } else if (i8 == 1) {
            mBrand = CarModel$Brand.DENZA;
        } else if (i8 != 2) {
            mBrand = CarModel$Brand.DYNASTY;
        } else {
            mBrand = CarModel$Brand.OCEAN;
        }
        a.c(TAG, "---initUIStyle---UIStyle: " + mUIStyle);
    }

    public static boolean isDenzaStyle() {
        return mUIStyle == 1;
    }

    public static void readVehicleConfig(Context context) {
        b bVarB;
        if (context == null || (bVarB = r2.a.b(context.getApplicationContext())) == null || bVarB.equals(mConfig)) {
            return;
        }
        a.c(TAG, "---readVehicleConfig---config: " + bVarB);
        mConfig = bVarB;
        carModelCode = bVarB.a();
        mUIStyle = bVarB.b();
        boolean zC = bVarB.c();
        identify();
        initUIStyle();
        AppConfig.d(mBrand);
        AppConfig.e(zC);
        a.c(TAG, "---readVehicleConfig---Brand: " + mBrand + ", RightRudder: " + zC);
    }

    public static boolean supportScreenOffFromAvm() {
        if (isDenzaStyle()) {
            return true;
        }
        String countryCode = BydSystemSettings.Singleton.get().getCountryCode();
        if (carModelCode == 0) {
            carModelCode = r2.a.a();
        }
        a.c(TAG, "supportScreenOffFromAvm", "code: " + countryCode, "model: " + carModelCode);
        if (carModelCode == 180) {
            return true;
        }
        return COUNTRY_CODE_KOREAN.equals(countryCode) && (carModelCode == 208 || carModelCode == 182);
    }
}
