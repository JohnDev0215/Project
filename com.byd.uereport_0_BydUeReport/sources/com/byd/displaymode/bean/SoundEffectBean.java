package com.byd.displaymode.bean;

/* JADX INFO: loaded from: classes.dex */
public class SoundEffectBean {
    private int balanceTone;
    private int configLevel;
    private int dynPosition;
    private int dynaSensual;
    private int effectType;
    private int factoryType;
    private int loudness;

    public SoundEffectBean() {
    }

    public int getBalanceTone() {
        return this.balanceTone;
    }

    public int getConfigLevel() {
        return this.configLevel;
    }

    public int getDynPosition() {
        return this.dynPosition;
    }

    public int getDynaSensual() {
        return this.dynaSensual;
    }

    public int getEffectType() {
        return this.effectType;
    }

    public int getFactoryType() {
        return this.factoryType;
    }

    public int getLoudness() {
        return this.loudness;
    }

    public void setBalanceTone(int i8) {
        this.balanceTone = i8;
    }

    public void setConfigLevel(int i8) {
        this.configLevel = i8;
    }

    public void setData(int i8, int i9, int i10, int i11, int i12, int i13) {
        if (i8 > 0 && i8 != this.factoryType) {
            this.factoryType = i8;
            if (1 == i8 && i9 > 0) {
                this.configLevel = i9;
            }
        }
        if (1 == this.factoryType && i10 > 0 && i10 != this.effectType) {
            this.effectType = i10;
        }
        if (i11 > 0 && i11 != this.loudness) {
            this.loudness = i11;
        }
        if (2 == i8 || 3 == i8) {
            if (i12 > 0 && i12 != this.dynPosition) {
                this.dynPosition = i12;
            }
            if (i13 <= 0 || 2 != i8) {
                return;
            }
            this.dynaSensual = i13;
        }
    }

    public void setDynPosition(int i8) {
        this.dynPosition = i8;
    }

    public void setDynaSensual(int i8) {
        this.dynaSensual = i8;
    }

    public void setEffectType(int i8) {
        this.effectType = i8;
    }

    public void setFactoryType(int i8) {
        this.factoryType = i8;
    }

    public void setLoudness(int i8) {
        this.loudness = i8;
    }

    public SoundEffectBean(int i8, int i9) {
        this.factoryType = i8;
        this.configLevel = i9;
    }
}
