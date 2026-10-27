package com.loostone.libtuning.data.config.vendor;

/* JADX INFO: loaded from: classes.dex */
public final class EnvironmentalSoundItem {
    private int aef2EchoFeedback;
    private int aef2EchoGain;
    private int aef2EchoMode = 2;
    private int aef2EchoTime;
    private int aef2ReverbMode;
    private int aef2ReverbTime;
    private int aef2ReverbVolume;
    private int type;

    public final int getAef2EchoFeedback() {
        return this.aef2EchoFeedback;
    }

    public final int getAef2EchoGain() {
        return this.aef2EchoGain;
    }

    public final int getAef2EchoMode() {
        return this.aef2EchoMode;
    }

    public final int getAef2EchoTime() {
        return this.aef2EchoTime;
    }

    public final int getAef2ReverbMode() {
        return this.aef2ReverbMode;
    }

    public final int getAef2ReverbTime() {
        return this.aef2ReverbTime;
    }

    public final int getAef2ReverbVolume() {
        return this.aef2ReverbVolume;
    }

    public final int getType() {
        return this.type;
    }

    public final void setAef2EchoFeedback(int i) {
        this.aef2EchoFeedback = i;
    }

    public final void setAef2EchoGain(int i) {
        this.aef2EchoGain = i;
    }

    public final void setAef2EchoMode(int i) {
        this.aef2EchoMode = i;
    }

    public final void setAef2EchoTime(int i) {
        this.aef2EchoTime = i;
    }

    public final void setAef2ReverbMode(int i) {
        this.aef2ReverbMode = i;
    }

    public final void setAef2ReverbTime(int i) {
        this.aef2ReverbTime = i;
    }

    public final void setAef2ReverbVolume(int i) {
        this.aef2ReverbVolume = i;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "EnvironmentalSoundItem(type=" + this.type + ", lst_aef2ReverbMode_tsl=" + this.aef2ReverbMode + ", lst_aef2ReverbVolume_tsl=" + this.aef2ReverbVolume + ", lst_aef2ReverbTime_tsl=" + this.aef2ReverbTime + ", lst_aef2EchoMode_tsl=" + this.aef2EchoMode + ", lst_aef2EchoGain_tsl=" + this.aef2EchoGain + ", lst_aef2EchoTime_tsl=" + this.aef2EchoTime + ", lst_aef2EchoFeedback_tsl=" + this.aef2EchoFeedback + ')';
    }
}
