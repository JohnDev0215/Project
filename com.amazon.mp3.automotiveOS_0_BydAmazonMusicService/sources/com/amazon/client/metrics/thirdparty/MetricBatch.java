package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MetricBatch {
    private long mBatchSizeInBytes;
    private boolean mContainsUserMetrics;
    private String mDeviceSerialNumber;
    private String mDeviceType;
    private final List<EncodedMetricEntry> mSerializedMetricEntryList = new ArrayList();
    private final Map<String, String> mDeviceInfoMap = new HashMap();

    public void putMetricsDeviceInfo(Map<String, String> map) {
        if (map == null) {
            throw new IllegalArgumentException("Device Info Map is null");
        }
        this.mDeviceInfoMap.putAll(map);
        this.mDeviceSerialNumber = this.mDeviceInfoMap.remove(Splash.PARAMS_DEVICE_ID);
        this.mDeviceType = this.mDeviceInfoMap.remove(Splash.PARAMS_DEVICE_TYPE);
        String str = this.mDeviceSerialNumber;
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Device Info Map missing device ID");
        }
    }

    public synchronized void addEntry(EncodedMetricEntry encodedMetricEntry) {
        this.mSerializedMetricEntryList.add(encodedMetricEntry);
        this.mBatchSizeInBytes += (long) encodedMetricEntry.getEncodedSize();
    }

    public synchronized long getBatchSizeInBytes() {
        return this.mBatchSizeInBytes;
    }

    public synchronized int getMetricEntryCount() {
        return this.mSerializedMetricEntryList.size();
    }

    public String getDeviceSerialNumber() {
        return this.mDeviceSerialNumber;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public Map<String, String> getDeviceInfoMap() {
        return this.mDeviceInfoMap;
    }

    public synchronized EncodedMetricEntry getMetricEntry(int i) {
        return this.mSerializedMetricEntryList.get(i);
    }

    public synchronized boolean containsUserMetrics() {
        return this.mContainsUserMetrics;
    }

    public synchronized void setContainsUserMetrics(boolean z) {
        this.mContainsUserMetrics = z;
    }

    public int hashCode() {
        int iHashCode = 1;
        for (int i = 0; i < getMetricEntryCount(); i++) {
            iHashCode = (iHashCode * 31) + getMetricEntry(i).hashCode();
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MetricBatch metricBatch = (MetricBatch) obj;
        if (getMetricEntryCount() != metricBatch.getMetricEntryCount()) {
            return false;
        }
        for (int i = 0; i < getMetricEntryCount(); i++) {
            if (!getMetricEntry(i).equals(metricBatch.getMetricEntry(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mDeviceSerialNumber);
        sb.append("\n");
        sb.append(this.mDeviceType);
        sb.append("\n");
        sb.append(this.mDeviceInfoMap.toString());
        sb.append("\n");
        sb.append(this.mBatchSizeInBytes);
        sb.append("\n");
        for (int i = 0; i < this.mSerializedMetricEntryList.size(); i++) {
            sb.append(this.mSerializedMetricEntryList.get(i).toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
