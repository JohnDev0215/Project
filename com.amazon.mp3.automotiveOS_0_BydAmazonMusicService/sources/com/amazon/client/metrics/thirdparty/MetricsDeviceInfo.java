package com.amazon.client.metrics.thirdparty;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsDeviceInfo {
    private final Map<String, String> mDeviceInfo = new HashMap();

    public void addDeviceInfoData(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("device info key cannot be null");
        }
        this.mDeviceInfo.put(str, str2);
    }

    public String getDeviceInfo(String str) {
        return this.mDeviceInfo.get(str);
    }

    public Map<String, String> getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public int hashCode() {
        return 31 + this.mDeviceInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.mDeviceInfo.equals(((MetricsDeviceInfo) obj).mDeviceInfo);
    }

    public String toString() {
        return "MetricsDeviceInfo [mDeviceInfo=" + this.mDeviceInfo + "]";
    }
}
