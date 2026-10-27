package com.amazon.client.metrics.thirdparty.codec;

/* JADX INFO: loaded from: classes2.dex */
public class StringEncodedMetricEntry implements EncodedMetricEntry {
    private String mEncodedMetricEntry;

    public StringEncodedMetricEntry(String str) {
        this.mEncodedMetricEntry = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public String getEncodedMetricEntry() {
        return this.mEncodedMetricEntry;
    }

    @Override // com.amazon.client.metrics.thirdparty.codec.EncodedMetricEntry
    public int getEncodedSize() {
        return this.mEncodedMetricEntry.getBytes().length;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.mEncodedMetricEntry.equals(((StringEncodedMetricEntry) obj).getEncodedMetricEntry());
        }
        return false;
    }

    public int hashCode() {
        return this.mEncodedMetricEntry.hashCode();
    }

    public String toString() {
        return this.mEncodedMetricEntry;
    }
}
