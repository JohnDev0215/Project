package com.ts.carplay.adapter.wifi;

import com.ts.carplay.adapter.common.IAdapter;

/* JADX INFO: loaded from: classes.dex */
public interface IWifiAdapter extends IAdapter {
    int getSupportWifiChannel();

    void registerListener(IWifiAdapterListener iWifiAdapterListener);

    void unregisterListener(IWifiAdapterListener iWifiAdapterListener);
}
