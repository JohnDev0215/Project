package com.byd.engineermode.ui.functiontest.gpstest;

/* JADX INFO: compiled from: GPSLocationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(String str, int i, float f) {
        if (i < 10) {
            return str + i + "                 " + f;
        }
        if (i < 100) {
            return str + i + "               " + f;
        }
        return str + i + "             " + f;
    }
}
