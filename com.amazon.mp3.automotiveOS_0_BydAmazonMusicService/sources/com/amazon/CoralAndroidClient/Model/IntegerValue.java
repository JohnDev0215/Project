package com.amazon.CoralAndroidClient.Model;

/* JADX INFO: loaded from: classes2.dex */
public class IntegerValue implements Value {
    private int mValue;

    public IntegerValue(int i) {
        this.mValue = i;
    }

    public IntegerValue() {
        this(0);
    }

    public void setValue(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() {
        return Integer.valueOf(getValue());
    }
}
