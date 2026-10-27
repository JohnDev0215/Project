package com.byd.displaymode.bean;

/* JADX INFO: loaded from: classes.dex */
public class SeatState {
    private int heatLevel;
    private int seatId;
    private int ventilation;

    public SeatState(int i8, int i9, int i10) {
        this.seatId = i8;
        setSeatProperty(i9, i10);
    }

    public int getHeatLevel() {
        return this.heatLevel;
    }

    public int getSeatId() {
        return this.seatId;
    }

    public int getVentilation() {
        return this.ventilation;
    }

    public boolean isInvalid() {
        return this.heatLevel <= 0 || this.ventilation <= 0;
    }

    public boolean isLowLeveHeat() {
        return this.heatLevel == 2;
    }

    public boolean isLowLeveVentilation() {
        return this.ventilation == 2;
    }

    public void setHeatLevel(int i8) {
        this.heatLevel = i8;
    }

    public void setSeatId(int i8) {
        this.seatId = i8;
    }

    public void setSeatProperty(int i8, int i9) {
        this.heatLevel = i8;
        this.ventilation = i9;
    }

    public void setVentilation(int i8) {
        this.ventilation = i8;
    }
}
