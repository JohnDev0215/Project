package com.ts.lib.car;

import android.car.hardware.CarPropertyValue;

/* JADX INFO: loaded from: classes2.dex */
public class CarBaseProperty {
    private int mAreaId;
    private int mPropertyId;
    private int mStatus;
    private long mTimestamp;
    private Object mValue;

    public void setCarPropertyValue(CarPropertyValue propertyValue) {
        this.mPropertyId = propertyValue.getPropertyId();
        this.mAreaId = propertyValue.getAreaId();
        this.mStatus = propertyValue.getStatus();
        this.mTimestamp = propertyValue.getTimestamp();
        this.mValue = propertyValue.getValue();
    }

    public int getPropertyId() {
        return this.mPropertyId;
    }

    public int getAreaId() {
        return this.mAreaId;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public Object getValue() {
        return this.mValue;
    }

    public String propertyIdTenToHex() {
        String strHex = Integer.toHexString(this.mPropertyId);
        return strHex;
    }

    public String areaIdTenToHex() {
        String strHex = Integer.toHexString(this.mAreaId);
        return strHex;
    }
}
