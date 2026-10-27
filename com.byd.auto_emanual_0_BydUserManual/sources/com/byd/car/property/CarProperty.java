package com.byd.car.property;

import android.content.Context;
import com.byd.car.DiCar;
import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import com.byd.foundation.callback.ValueCallback;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CarProperty {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    public static <T> Status setProperty(String str, T t) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).setProperty(str, t);
    }

    public static <T> Status setProperties(String[] strArr, T[] tArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).setProperties(strArr, tArr);
    }

    public static <V> Response<CarPropertyValue<V>> getProperty(String str) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getProperty(str);
    }

    public static <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getProperties(strArr);
    }

    public static List<CarPropertyConfig> getPropertyConfigs(String... strArr) {
        return ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).getPropertyConfigs(strArr);
    }

    public static void registerValueCallback(ValueCallback<Response> valueCallback, String... strArr) {
        ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).registerValueCallback(valueCallback, strArr);
    }

    public static void unregisterValueCallback(ValueCallback<Response> valueCallback, String... strArr) {
        ((ICarPropertyManager) DiCar.getCarManager(mContext, ICarPropertyManager.class)).unregisterValueCallback(valueCallback, strArr);
    }
}
