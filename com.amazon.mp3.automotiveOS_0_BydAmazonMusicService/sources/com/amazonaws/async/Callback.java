package com.amazonaws.async;

/* JADX INFO: loaded from: classes5.dex */
public interface Callback<R> {
    void onError(Exception exc);

    void onResult(R r);
}
