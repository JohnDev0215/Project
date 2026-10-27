package com.byd.util;

/* JADX INFO: loaded from: classes2.dex */
public class o {
    public static String a(int i) {
        Object objValueOf;
        Object objValueOf2;
        Object objValueOf3;
        if (i < 0) {
            return "00:00:00";
        }
        int i2 = i / 1000;
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        int i5 = i2 % 60;
        StringBuilder sb = new StringBuilder();
        if (i3 < 10) {
            objValueOf = "0" + i3;
        } else {
            objValueOf = Integer.valueOf(i3);
        }
        sb.append(objValueOf);
        sb.append(":");
        if (i4 < 10) {
            objValueOf2 = "0" + i4;
        } else {
            objValueOf2 = Integer.valueOf(i4);
        }
        sb.append(objValueOf2);
        sb.append(":");
        if (i5 < 10) {
            objValueOf3 = "0" + i5;
        } else {
            objValueOf3 = Integer.valueOf(i5);
        }
        sb.append(objValueOf3);
        return sb.toString();
    }

    public static String b(long j) {
        Object objValueOf;
        Object objValueOf2;
        Object objValueOf3;
        if (j < 0) {
            return "00:00";
        }
        long j2 = j / 1000;
        int i = (int) (j2 / 3600);
        int i2 = (int) ((j2 % 3600) / 60);
        int i3 = (int) (j2 % 60);
        StringBuilder sb = new StringBuilder();
        if (i > 0) {
            if (i < 10) {
                objValueOf3 = "0" + i;
            } else {
                objValueOf3 = Integer.valueOf(i);
            }
            sb.append(objValueOf3);
            sb.append(":");
        }
        if (i2 < 10) {
            objValueOf = "0" + i2;
        } else {
            objValueOf = Integer.valueOf(i2);
        }
        sb.append(objValueOf);
        sb.append(":");
        if (i3 < 10) {
            objValueOf2 = "0" + i3;
        } else {
            objValueOf2 = Integer.valueOf(i3);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }

    public static String c(long j) {
        Object objValueOf;
        Object objValueOf2;
        if (j < 0) {
            return "00:00";
        }
        long j2 = j / 1000;
        int i = (int) (j2 / 60);
        int i2 = (int) (j2 % 60);
        StringBuilder sb = new StringBuilder();
        if (i < 10) {
            objValueOf = "0" + i;
        } else {
            objValueOf = Integer.valueOf(i);
        }
        sb.append(objValueOf);
        sb.append(":");
        if (i2 < 10) {
            objValueOf2 = "0" + i2;
        } else {
            objValueOf2 = Integer.valueOf(i2);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }
}
