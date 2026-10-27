package com.engine.tbox.core.conn;

import com.engine.tbox.core.data.CountSyncMessage;

/* JADX INFO: loaded from: classes.dex */
public interface IResponse {
    void onResponse(CountSyncMessage countSyncMessage);

    void onTimeout(CountSyncMessage countSyncMessage);
}
