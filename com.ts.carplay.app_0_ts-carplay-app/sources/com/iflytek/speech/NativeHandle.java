package com.iflytek.speech;

/* JADX INFO: loaded from: classes.dex */
public class NativeHandle {
    public int err_ret = 0;
    public long native_point = 0;

    public int getErr_ret() {
        return this.err_ret;
    }

    public long getNative_point() {
        return this.native_point;
    }

    public void setErr_ret(int err_ret) {
        this.err_ret = err_ret;
    }

    public void setNative_point(long native_point) {
        this.native_point = native_point;
    }

    public void reSet() {
        this.err_ret = 0;
        this.native_point = 0L;
    }
}
