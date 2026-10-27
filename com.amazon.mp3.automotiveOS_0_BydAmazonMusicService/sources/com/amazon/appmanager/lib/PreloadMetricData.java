package com.amazon.appmanager.lib;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class PreloadMetricData {
    private Map<String, String> mExceptionMap = new ConcurrentHashMap();
    private String mPTPreviousValue;
    private String mPTSource;

    protected enum SourceType {
        SP,
        CP,
        File
    }

    public void setPTSource(String str) {
        this.mPTSource = str;
    }

    public void setPTPreviousValue(String str) {
        this.mPTPreviousValue = str;
    }

    public void logException(String str, String str2) {
        this.mExceptionMap.put(str, str2);
    }
}
