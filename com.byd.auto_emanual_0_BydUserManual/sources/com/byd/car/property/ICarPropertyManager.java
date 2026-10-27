package com.byd.car.property;

import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import com.byd.foundation.callback.ValueCallback;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPropertyManager {
    <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr);

    <V> Response<CarPropertyValue<V>> getProperty(String str);

    List<CarPropertyConfig> getPropertyConfigs(String... strArr);

    void registerValueCallback(ValueCallback<Response> valueCallback, String... strArr);

    <T> Status setProperties(String[] strArr, T[] tArr);

    <T> Status setProperty(String str, T t);

    void unregisterValueCallback(ValueCallback<Response> valueCallback, String... strArr);
}
