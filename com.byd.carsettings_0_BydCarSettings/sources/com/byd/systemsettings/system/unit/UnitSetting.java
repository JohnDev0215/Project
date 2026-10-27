package com.byd.systemsettings.system.unit;

/* JADX INFO: loaded from: classes.dex */
public class UnitSetting {
    public static final int DISTANCE_UNIT_TYPE = 0;
    public static final int OIL_UNIT_TYPE = 1;
    public static final int POWER_UNIT_TYPE = 3;
    public static final int PRESSURE_UNIT_TYPE = 2;
    public static final int TEMPERATURE_UNIT_TYPE = 4;
    public int defDisUnit;
    public int defOilUnit;
    public int defPowerUnit;
    public int defPressUnit;
    public int defTempUnit;
    public boolean enableDisUnitSet;
    public boolean enableOilUnitSet;
    public boolean enablePowerUnitSet;
    public boolean enablePressUnitSet;
    public boolean enableTempUnitSet;

    public String toString() {
        return "UnitSetting{defDisUnit=" + this.defDisUnit + ", defOilUnit=" + this.defOilUnit + ", defPressUnit=" + this.defPressUnit + ", defPowerUnit=" + this.defPowerUnit + ", defTempUnit=" + this.defTempUnit + '}';
    }
}
