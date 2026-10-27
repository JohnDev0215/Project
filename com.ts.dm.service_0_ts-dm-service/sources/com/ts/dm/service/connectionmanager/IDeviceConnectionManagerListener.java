package com.ts.dm.service.connectionmanager;

import com.ts.dm.service.data.ConnectionInfoBean;

/* JADX INFO: loaded from: classes.dex */
public interface IDeviceConnectionManagerListener {
    void onDeviceChanged(ConnectionInfoBean connectionInfoBean);

    void onDeviceDetached(ConnectionInfoBean connectionInfoBean);

    void onDeviceProjectionRequested(boolean z, ConnectionInfoBean connectionInfoBean);
}
