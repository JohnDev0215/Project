package com.qtxa.clean.sysmonitor;

import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CpuInfo implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final CpuInfo f44a = new CpuInfo();
    public double appCpuRatio;
    public double ioWaitRatio;
    public double sysCpuRatio;
    public double totalUseRatio;
    public double userCpuRatio;

    public CpuInfo(double d, double d2, double d3, double d4, double d5) {
        this.totalUseRatio = d;
        this.appCpuRatio = d2;
        this.userCpuRatio = d3;
        this.sysCpuRatio = d4;
        this.ioWaitRatio = d5;
    }

    public String toString() {
        return "app:" + String.format(Locale.US, "%.1f", Double.valueOf(this.appCpuRatio * 100.0d)) + "% , total:" + String.format(Locale.US, "%.1f", Double.valueOf(this.totalUseRatio * 100.0d)) + "% , user:" + String.format(Locale.US, "%.1f", Double.valueOf(this.userCpuRatio * 100.0d)) + "% , system:" + String.format(Locale.US, "%.1f", Double.valueOf(this.sysCpuRatio * 100.0d)) + "% , iowait:" + String.format(Locale.US, "%.1f", Double.valueOf(this.ioWaitRatio * 100.0d)) + "%";
    }

    public CpuInfo() {
    }
}
