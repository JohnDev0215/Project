package com.loostone.libtuning.data.config;

/* JADX INFO: loaded from: classes.dex */
public final class HardEffectVolume {
    private double[] micVolcurveX;
    private double[] micVolcurveY;
    private double[] sysVolcurveX;
    private double[] sysVolcurveY;

    public final double[] getMicVolcurveX() {
        return this.micVolcurveX;
    }

    public final double[] getMicVolcurveY() {
        return this.micVolcurveY;
    }

    public final double[] getSysVolcurveX() {
        return this.sysVolcurveX;
    }

    public final double[] getSysVolcurveY() {
        return this.sysVolcurveY;
    }

    public final void setMicVolcurveX(double[] dArr) {
        this.micVolcurveX = dArr;
    }

    public final void setMicVolcurveY(double[] dArr) {
        this.micVolcurveY = dArr;
    }

    public final void setSysVolcurveX(double[] dArr) {
        this.sysVolcurveX = dArr;
    }

    public final void setSysVolcurveY(double[] dArr) {
        this.sysVolcurveY = dArr;
    }
}
