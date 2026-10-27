package com.ts.dm.service.connectionhmi;

import com.ts.dm.service.data.ConnectionInfoBean;

/* JADX INFO: loaded from: classes.dex */
public interface IHmiFactory {
    void changeHmi(int i, ConnectionInfoBean connectionInfoBean);

    void onCheckConnectionResult(ConnectionInfoBean connectionInfoBean, ConnectionInfoBean connectionInfoBean2);

    void onClickHmiForConnect(ConnectionInfoBean connectionInfoBean);
}
