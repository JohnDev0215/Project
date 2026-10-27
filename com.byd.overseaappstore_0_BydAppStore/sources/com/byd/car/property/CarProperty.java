package com.byd.car.property;

import android.content.Context;
import com.byd.car.DiCar;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import java.util.List;
import spi.wu;

/* JADX INFO: loaded from: classes.dex */
public class CarProperty {
    private static Context mContext;

    public static <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getProperties(strArr);
    }

    public static <V> Response<CarPropertyValue<V>> getProperty(String str) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getProperty(str);
    }

    public static List<CarPropertyConfig> getPropertyConfigs(String... strArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getPropertyConfigs(strArr);
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static void registerValueCallback(wu<Response> wuVar, String... strArr) {
        ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).registerValueCallback(wuVar, strArr);
    }

    public static <T> Status setProperties(String[] strArr, T[] tArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).setProperties(strArr, tArr);
    }

    public static <T> Status setProperty(String str, T t) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).setProperty(str, t);
    }

    public static void unregisterValueCallback(wu<Response> wuVar, String... strArr) {
        ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).unregisterValueCallback(wuVar, strArr);
    }
}
