package com.bydux.api.common.webs.okhttp.bean;

import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class ResponseInfo<T> {
    private T mBody;
    private String mData;
    private Response mResponse;

    public ResponseInfo(Response response, T t, String str) {
        this.mResponse = response;
        this.mBody = t;
        this.mData = str;
    }

    public T getBody() {
        return this.mBody;
    }

    public String getData() {
        return this.mData;
    }

    public Response getResponse() {
        return this.mResponse;
    }
}
