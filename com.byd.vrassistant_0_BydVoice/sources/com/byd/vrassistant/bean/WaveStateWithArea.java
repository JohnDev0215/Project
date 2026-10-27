package com.byd.vrassistant.bean;

import com.byd.vrassistant.eventprocessor.WaveState;

/* JADX INFO: loaded from: classes.dex */
public class WaveStateWithArea {
    String area;
    WaveState waveState;

    public WaveState getWaveState() {
        return this.waveState;
    }

    public void setWaveState(WaveState waveState) {
        this.waveState = waveState;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public static WaveStateWithArea get(WaveState waveState, String str) {
        WaveStateWithArea waveStateWithArea = new WaveStateWithArea();
        waveStateWithArea.setArea(str);
        waveStateWithArea.setWaveState(waveState);
        return waveStateWithArea;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WaveStateWithArea)) {
            return false;
        }
        WaveStateWithArea waveStateWithArea = (WaveStateWithArea) obj;
        return this.waveState == waveStateWithArea.waveState && this.area.equals(waveStateWithArea.area);
    }

    public String toString() {
        return "WaveStateWithArea{waveState=" + this.waveState + ", area='" + this.area + "'}";
    }
}
