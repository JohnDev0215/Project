package com.byd.car.iot.converter;

import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public interface TaskWrapper<R, W> {
    void addReadTask(String str, String str2, IotTask<R> iotTask);

    void addWriteTask(String str, String str2, IotTask<W> iotTask);

    Optional<IotTask<R>> findReadTask(String str, String str2);

    Optional<IotTask<W>> findWriteTask(String str, String str2);

    void removeReadTask(String str, String str2);

    void removeWriteTask(String str, String str2);
}
