package com.bydux.api.common.webs.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public interface BydHttpClientListener {
    void onFailure(int i, Call call, IOException iOException);

    void onNetworkDisconnect(int i);

    void onResponse(int i, Call call, Response response) throws IOException;
}
