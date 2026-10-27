package com.amazon.CoralAndroidClient.Model;

/* JADX INFO: loaded from: classes2.dex */
public class StringValue implements Value {
    private String mValue;

    public StringValue(String str) {
        this.mValue = str == null ? "" : str;
    }

    public StringValue() {
        this(null);
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return getValue();
    }
}
