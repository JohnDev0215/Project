package com.amazon.client.metrics.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public class DataPoint {
    private final String mName;
    private final int mSamples;
    private final DataPointType mType;
    private final String mValue;

    public DataPoint(String str, String str2, int i, DataPointType dataPointType) {
        validateString(str, "name cannot be null or empty");
        validateString(str2, "value cannot be null or empty");
        if (dataPointType == null) {
            throw new IllegalArgumentException("DataPoint type cannot be null");
        }
        this.mName = str;
        this.mValue = str2;
        this.mSamples = i;
        this.mType = dataPointType;
    }

    public String getName() {
        return this.mName;
    }

    public DataPointType getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }

    public int getSamples() {
        return this.mSamples;
    }

    public int hashCode() {
        String str = this.mName;
        int iHashCode = ((((str == null ? 0 : str.hashCode()) + 31) * 31) + this.mSamples) * 31;
        DataPointType dataPointType = this.mType;
        int iHashCode2 = (iHashCode + (dataPointType == null ? 0 : dataPointType.hashCode())) * 31;
        String str2 = this.mValue;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return this.mName.equals(dataPoint.mName) && this.mSamples == dataPoint.mSamples && this.mType.equals(dataPoint.mType) && this.mValue.equals(dataPoint.mValue);
    }

    public String toString() {
        return " [" + this.mName + "," + this.mValue + "," + this.mSamples + "] ";
    }

    private void validateString(String str, String str2) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(str2);
        }
    }
}
