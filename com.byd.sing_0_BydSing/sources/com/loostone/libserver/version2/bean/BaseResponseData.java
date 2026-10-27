package com.loostone.libserver.version2.bean;

import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public class BaseResponseData<T> {
    private T data;
    private int code = -1;
    private String msg = "no msg";

    public final int getCode() {
        return this.code;
    }

    public final T getData() {
        return this.data;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final void setData(T t) {
        this.data = t;
    }

    public final void setMsg(String str) {
        h.d(str, "<set-?>");
        this.msg = str;
    }
}
