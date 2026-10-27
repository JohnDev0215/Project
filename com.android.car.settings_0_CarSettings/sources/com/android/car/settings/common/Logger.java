package com.android.car.settings.common;

import android.util.Log;

/* JADX INFO: loaded from: classes5.dex */
public final class Logger {
    private static final String TAG = "CarSettings";
    private final String mPrefix;

    public Logger(Class<?> cls) {
        this(cls.getSimpleName());
    }

    public Logger(String prefix) {
        this.mPrefix = "[" + prefix + "] ";
    }

    protected boolean forceAllLogging() {
        return false;
    }

    public void v(String message) {
        if (isV()) {
            Log.v(TAG, this.mPrefix.concat(message));
        }
    }

    public void v(String message, Throwable throwable) {
        if (isV()) {
            Log.v(TAG, this.mPrefix.concat(message), throwable);
        }
    }

    public void d(String message) {
        if (isD()) {
            Log.d(TAG, this.mPrefix.concat(message));
        }
    }

    public void d(String message, Throwable throwable) {
        if (isD()) {
            Log.d(TAG, this.mPrefix.concat(message), throwable);
        }
    }

    public void i(String message) {
        if (isI()) {
            Log.i(TAG, this.mPrefix.concat(message));
        }
    }

    public void i(String message, Throwable throwable) {
        if (isI()) {
            Log.i(TAG, this.mPrefix.concat(message), throwable);
        }
    }

    public void w(String message) {
        Log.w(TAG, this.mPrefix.concat(message));
    }

    public void w(String message, Throwable throwable) {
        Log.w(TAG, this.mPrefix.concat(message), throwable);
    }

    public void e(String message) {
        Log.e(TAG, this.mPrefix.concat(message));
    }

    public void e(String message, Throwable throwable) {
        Log.e(TAG, this.mPrefix.concat(message), throwable);
    }

    public void wtf(String message) {
        Log.wtf(TAG, this.mPrefix.concat(message));
    }

    public void wtf(String message, Throwable throwable) {
        Log.wtf(TAG, this.mPrefix.concat(message), throwable);
    }

    private boolean isV() {
        return Log.isLoggable(TAG, 2) || forceAllLogging();
    }

    private boolean isD() {
        return Log.isLoggable(TAG, 3) || forceAllLogging();
    }

    private boolean isI() {
        return Log.isLoggable(TAG, 4) || forceAllLogging();
    }
}
