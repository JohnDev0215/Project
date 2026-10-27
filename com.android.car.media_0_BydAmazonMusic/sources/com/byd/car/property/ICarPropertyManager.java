package com.byd.car.property;

import com.byd.datasource.feature.Response;
import com.byd.datasource.feature.Status;
import java.util.List;
import spi.nc;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarPropertyManager {
    <V> Response<List<CarPropertyValue<V>>> getProperties(String... strArr);

    <V> Response<CarPropertyValue<V>> getProperty(String str);

    List<CarPropertyConfig> getPropertyConfigs(String... strArr);

    void registerValueCallback(nc<Response> ncVar, String... strArr);

    <T> Status setProperties(String[] strArr, T[] tArr);

    <T> Status setProperty(String str, T t);

    void unregisterValueCallback(nc<Response> ncVar, String... strArr);
}
