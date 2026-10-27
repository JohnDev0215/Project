package com.bydux.api.common.webs.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public interface BydHttpClientListener {
    void onFailure(int i8, Call call, IOException iOException);

    void onNetworkDisconnect(int i8);

    void onResponse(int i8, Call call, Response response) throws IOException;
}
