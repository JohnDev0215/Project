package com.byd.feature.carsettings;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class CarStatusSettingDiCare$DiCareRecord implements Serializable {
    public static final String DICARE_HEV_MILEAGE = "hev_mileage";
    public static final String DICARE_MILEAGE_UNIT = "unit";
    public static final String DICARE_TIME = "time";
    public static final String DICARE_TOTAL_MILEAGE = "total_mileage";
    public static final String DICARE_TYPE = "type";
    private int hevMileage;
    private long time;
    private int totalMileage;
    private int type;
    private String unit;

    private CarStatusSettingDiCare$DiCareRecord() {
    }

    public int getHevMileage() {
        return this.hevMileage;
    }

    public long getTime() {
        return this.time;
    }

    public int getTotalMileage() {
        return this.totalMileage;
    }

    public int getType() {
        return this.type;
    }

    public String getUnit() {
        return this.unit;
    }
}
