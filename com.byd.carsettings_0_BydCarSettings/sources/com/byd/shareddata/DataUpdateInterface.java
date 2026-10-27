package com.byd.shareddata;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public interface DataUpdateInterface {
    <T> void addDataUpdateListener(String str, DataUpdateListener<T> dataUpdateListener);

    <T> void addDataUpdateListener(String str, DataUpdateListener<T> dataUpdateListener, boolean z);

    <T> void addDataUpdateListener(Method method, DataUpdateListener<T> dataUpdateListener, boolean z);

    void removeDataUpdateListener(DataUpdateListener dataUpdateListener);

    void removeDataUpdateListener(String str);

    void removeDataUpdateListener(Method method);
}
