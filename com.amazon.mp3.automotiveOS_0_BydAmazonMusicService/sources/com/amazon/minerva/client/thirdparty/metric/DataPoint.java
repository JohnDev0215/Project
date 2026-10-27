package com.amazon.minerva.client.thirdparty.metric;

/* JADX INFO: loaded from: classes3.dex */
public class DataPoint {
    private final String mName;
    private final ValueType mType;
    private final String mValue;

    public DataPoint(String str, ValueType valueType, String str2) {
        this.mName = str;
        this.mType = valueType;
        this.mValue = str2;
    }

    public String getName() {
        return this.mName;
    }

    public ValueType getType() {
        return this.mType;
    }

    public String getValue() {
        return this.mValue;
    }
}
