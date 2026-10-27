package com.byd.car.property;

import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import java.util.List;
import spi.wu;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPropertyManager {
    <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr);

    <V> Response<CarPropertyValue<V>> getProperty(String str);

    List<CarPropertyConfig> getPropertyConfigs(String... strArr);

    void registerValueCallback(wu<Response> wuVar, String... strArr);

    <T> Status setProperties(String[] strArr, T[] tArr);

    <T> Status setProperty(String str, T t);

    void unregisterValueCallback(wu<Response> wuVar, String... strArr);
}
