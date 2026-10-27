package com.ts.permission.bean;

/* JADX INFO: loaded from: classes8.dex */
public abstract class BaseConfig {
    protected static final String TAG = "TSPS_CONFIG";
    protected int checkMark;

    protected boolean isValid() {
        return this.checkMark <= 0;
    }
}
