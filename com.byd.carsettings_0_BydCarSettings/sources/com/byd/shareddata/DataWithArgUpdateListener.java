package com.byd.shareddata;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DataWithArgUpdateListener<T, K> extends DataUpdateListener {
    private K[] monitorArgs = (K[]) new Object[0];

    @Override // com.byd.shareddata.DataUpdateListener
    public void dataValueChanged(String str, Object obj, Object obj2) {
    }

    public abstract void dataValueChanged(String str, K k, T t, T t2);

    public K[] getMonitorArgs() {
        return this.monitorArgs;
    }

    public DataWithArgUpdateListener<T, K> setMonitorArgs(K... kArr) {
        this.monitorArgs = kArr;
        return this;
    }
}
