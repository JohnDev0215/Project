package com.bydux.api.common.webs.okhttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public interface BydHttpClientListener {
    void onFailure(final int fn, Call call, IOException e);

    void onNetworkDisconnect(final int fn);

    void onResponse(final int fn, Call call, Response response) throws IOException;
}
