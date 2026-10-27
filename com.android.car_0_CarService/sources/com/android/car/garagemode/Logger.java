package com.android.car.garagemode;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
class Logger {
    private final String mPrefix;
    private final String mTag = "M_STR_GarageMode";

    Logger(String prefix) {
        this.mPrefix = prefix;
    }

    public void v(String msg) {
        Log.v(this.mTag, buildMessage(msg));
    }

    public void v(String msg, Exception ex) {
        Log.v(this.mTag, buildMessage(msg), ex);
    }

    public void i(String msg) {
        Log.i(this.mTag, buildMessage(msg));
    }

    public void i(String msg, Exception ex) {
        Log.i(this.mTag, buildMessage(msg), ex);
    }

    public void d(String msg) {
        Log.d(this.mTag, buildMessage(msg));
    }

    public void d(String msg, Exception ex) {
        Log.d(this.mTag, buildMessage(msg), ex);
    }

    public void w(String msg, Exception ex) {
        Log.w(this.mTag, buildMessage(msg), ex);
    }

    public void w(String msg) {
        Log.w(this.mTag, buildMessage(msg));
    }

    public void e(String msg) {
        Log.e(this.mTag, buildMessage(msg));
    }

    public void e(String msg, Exception ex) {
        Log.e(this.mTag, buildMessage(msg), ex);
    }

    public void e(String msg, Throwable ex) {
        Log.e(this.mTag, buildMessage(msg), ex);
    }

    private String buildMessage(String msg) {
        return String.format("[%s]: %s", this.mPrefix, msg);
    }
}
