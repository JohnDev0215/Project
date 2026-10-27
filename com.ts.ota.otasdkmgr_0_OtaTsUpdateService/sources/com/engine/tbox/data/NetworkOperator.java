package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class NetworkOperator {
    int enable;
    String mccMnc;
    int sigLevel;
    public static int SIG_LEVEL_INVALID = 0;
    public static int SIG_LEVEL_2G = 1;
    public static int SIG_LEVEL_3G = 2;
    public static int SIG_LEVEL_4G = 3;

    public String getMccMnc() {
        return this.mccMnc;
    }

    public void setMccMnc(String mccMnc) {
        this.mccMnc = mccMnc;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getEnable() {
        return this.enable;
    }

    public void setSigLevel(int level) {
        this.sigLevel = level;
    }

    public int getSigLevel() {
        return this.sigLevel;
    }

    public String toString() {
        return "NetworkOperator [mccMnc=" + this.mccMnc + ", enable=" + this.enable + ", sigLevel=" + this.sigLevel + "]";
    }
}
