package com.bydux.api.common.webs.okhttp;

import f.e;
import f.y;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public interface BydHttpClientListener {
    void onFailure(int i2, e eVar, IOException iOException);

    void onNetworkDisconnect(int i2);

    void onResponse(int i2, e eVar, y yVar) throws IOException;
}
