package com.amazon.minerva.client.thirdparty.metric;

import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class IonMetricEvent {
    private IonTimestamp mClientTimestamp;
    private IonStruct mKeyValuePairs;
    private IonString mMetricEventId;
    private IonSymbol mMetricGroupId;
    private IonSymbol mMetricSchemaId;
    private String mRegion;
    private long mSizeInByte;

    public IonMetricEvent(IonSymbol ionSymbol, IonSymbol ionSymbol2, IonTimestamp ionTimestamp, IonString ionString, IonStruct ionStruct) {
        this.mMetricGroupId = ionSymbol;
        this.mMetricSchemaId = ionSymbol2;
        this.mClientTimestamp = ionTimestamp;
        this.mMetricEventId = ionString;
        this.mKeyValuePairs = ionStruct;
    }

    public IonSymbol getMetricGroupId() {
        return this.mMetricGroupId;
    }

    public IonSymbol getMetricSchemaId() {
        return this.mMetricSchemaId;
    }

    public IonTimestamp getClientTimestamp() {
        return this.mClientTimestamp;
    }

    public IonString getMetricEventId() {
        return this.mMetricEventId;
    }

    public IonStruct getKeyValuePairs() {
        return this.mKeyValuePairs;
    }

    public long getSizeInByte() {
        return this.mSizeInByte;
    }

    public void setSizeInByte(long j) {
        this.mSizeInByte = j;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public void setRegion(String str) {
        this.mRegion = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IonMetricEvent ionMetricEvent = (IonMetricEvent) obj;
        return Objects.equals(this.mMetricGroupId, ionMetricEvent.mMetricGroupId) && Objects.equals(this.mMetricSchemaId, ionMetricEvent.mMetricSchemaId) && Objects.equals(this.mClientTimestamp, ionMetricEvent.mClientTimestamp) && Objects.equals(this.mMetricEventId, ionMetricEvent.mMetricEventId) && Objects.equals(this.mKeyValuePairs, ionMetricEvent.mKeyValuePairs);
    }
}
