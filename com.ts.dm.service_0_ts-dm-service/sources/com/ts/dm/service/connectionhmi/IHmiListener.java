package com.ts.dm.service.connectionhmi;

import com.ts.dm.service.data.ConnectionInfoBean;

/* JADX INFO: loaded from: classes.dex */
public interface IHmiListener {
    void onCheckConnectionResult(ConnectionInfoBean connectionInfoBean, ConnectionInfoBean connectionInfoBean2);

    void onConnect(ConnectionInfoBean connectionInfoBean);
}
