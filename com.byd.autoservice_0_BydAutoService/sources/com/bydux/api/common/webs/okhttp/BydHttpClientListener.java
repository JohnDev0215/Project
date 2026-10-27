package com.bydux.api.common.webs.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public interface BydHttpClientListener {
    void onFailure(int i3, Call call, IOException iOException);

    void onNetworkDisconnect(int i3);

    void onResponse(int i3, Call call, Response response) throws IOException;
}
