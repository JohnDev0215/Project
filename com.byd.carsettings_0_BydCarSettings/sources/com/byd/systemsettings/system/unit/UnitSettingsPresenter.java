package com.byd.systemsettings.system.unit;

import b.b.g.p.d;
import b.b.g.r.e;
import b.b.m.e.g;

/* JADX INFO: loaded from: classes.dex */
public class UnitSettingsPresenter extends g<UnitSettingsPager> {
    private final d settings = d.g.a();

    public UnitSetting getDefUnit() {
        UnitSetting unitSetting = new UnitSetting();
        unitSetting.defDisUnit = getDistanceUnitIndex();
        unitSetting.defOilUnit = getOliUnitIndex();
        unitSetting.defPressUnit = getPressureUnitIndex();
        unitSetting.defPowerUnit = getPowerUnitIndex();
        unitSetting.defTempUnit = getTemperatureUnitIndex();
        unitSetting.enableOilUnitSet = supportOil();
        unitSetting.enableDisUnitSet = supportDis();
        unitSetting.enablePressUnitSet = supportPress();
        unitSetting.enablePowerUnitSet = supportPower();
        unitSetting.enableTempUnitSet = supportTemp();
        return unitSetting;
    }

    public int getDistanceUnitIndex() {
        int iA = this.settings.A() - 1;
        if (iA < 0) {
            return 0;
        }
        return iA;
    }

    public int getOliUnitIndex() {
        int iM = this.settings.M() - 2;
        if (iM < 0) {
            return 0;
        }
        return iM;
    }

    public int getPowerUnitIndex() {
        int iO = this.settings.O() - 1;
        if (iO < 0) {
            return 0;
        }
        return iO;
    }

    public int getPressureUnitIndex() {
        int iP = this.settings.P();
        if (iP == 3 || iP < 0) {
            return 0;
        }
        return iP;
    }

    public int getTemperatureUnitIndex() {
        return this.settings.R() == 1 ? 0 : 1;
    }

    public void setUnit(int i, int i2) {
        int i3 = 0;
        if (i == 0) {
            i3 = i2 == 0 ? 5 : 6;
            e.j(70013, i2 == 0 ? 2 : 1);
        } else {
            if (i != 1) {
                if (i == 2) {
                    i3 = i2 == 0 ? 3 : i2 == 1 ? 1 : 2;
                    e.i(70015);
                    i = 1;
                } else if (i == 3) {
                    i3 = i2 == 0 ? 1 : 2;
                    e.i(70017);
                    i = 3;
                } else if (i == 4) {
                    int i4 = i2 == 0 ? 1 : 2;
                    e.j(30013, i2);
                    i = 0;
                    i3 = i4;
                }
                this.settings.M0(i, i3);
            }
            i3 = i2 == 0 ? 1 : i2 == 1 ? 3 : 4;
            e.i(70016);
        }
        i = 2;
        this.settings.M0(i, i3);
    }

    public boolean supportDis() {
        int iM = this.settings.M();
        return (iM == 1 || iM == 2 || iM == 3 || iM == 4) ? false : true;
    }

    public boolean supportOil() {
        int iM = this.settings.M();
        return iM == 1 || iM == 2 || iM == 3 || iM == 4;
    }

    public boolean supportPower() {
        int iO = this.settings.O();
        return iO == 1 || iO == 2;
    }

    public boolean supportPress() {
        int iP = this.settings.P();
        return iP == 1 || iP == 2 || iP == 3;
    }

    public boolean supportTemp() {
        int iR = this.settings.R();
        return iR == 0 || iR == 1;
    }
}
