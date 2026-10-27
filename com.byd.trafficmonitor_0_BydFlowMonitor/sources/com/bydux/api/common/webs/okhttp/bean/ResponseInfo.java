package com.bydux.api.common.webs.okhttp.bean;

import f.y;

/* JADX INFO: loaded from: classes.dex */
public class ResponseInfo<T> {
    private T mBody;
    private String mData;
    private y mResponse;

    public ResponseInfo(y yVar, T t, String str) {
        this.mResponse = yVar;
        this.mBody = t;
        this.mData = str;
    }

    public T getBody() {
        return this.mBody;
    }

    public String getData() {
        return this.mData;
    }

    public y getResponse() {
        return this.mResponse;
    }
}
