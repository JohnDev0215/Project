package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.ClientException;

/* JADX INFO: loaded from: classes2.dex */
public interface ResultHandler {
    void onException(ClientException clientException);

    void onSuccess(ClientOutput clientOutput);
}
