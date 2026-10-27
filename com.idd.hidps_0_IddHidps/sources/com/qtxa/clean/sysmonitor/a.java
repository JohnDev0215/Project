package com.qtxa.clean.sysmonitor;

import com.qtxa.qtxadefend.w;
import java.io.BufferedReader;
import java.io.StringReader;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f52a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f53b;
    public long c;
    public long d;
    public long e;
    public long f;

    public a(long j, long j2, long j3, long j4, long j5, long j6) {
        this.f52a = 0L;
        this.f53b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
        this.f52a = j;
        this.f53b = j2;
        this.c = j3;
        this.d = j4;
        this.e = j5;
        this.f = j6;
    }

    public static String a() {
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new StringReader(com.qtxa.clean.c.a().g(-1)));
            try {
                String line = bufferedReader2.readLine();
                if (line == null) {
                    b.a(bufferedReader2);
                    return "";
                }
                b.a(bufferedReader2);
                return line;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                b.a(bufferedReader);
                return "";
            }
        } catch (Throwable unused2) {
        }
    }

    private static String b(String str) {
        BufferedReader bufferedReader = null;
        try {
            d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "before get cpu rate of Process " + str);
            int iA = e.a(str);
            if (iA <= 0) {
                d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", str + "'s pid = " + iA);
                b.a(null);
                return "";
            }
            BufferedReader bufferedReader2 = new BufferedReader(new StringReader(com.qtxa.clean.c.a().g(iA)));
            try {
                String line = bufferedReader2.readLine();
                if (line == null) {
                    b.a(bufferedReader2);
                    return "";
                }
                b.a(bufferedReader2);
                return line;
            } catch (Throwable unused) {
                bufferedReader = bufferedReader2;
                b.a(bufferedReader);
                return "";
            }
        } catch (Throwable unused2) {
        }
    }

    private static a c(String str, String str2) {
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "parse begin.");
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "cpuRate : " + str);
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "pidCpuRate : " + str2);
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length < 9) {
            throw new IllegalStateException("cpu info array size must great than 9");
        }
        long j = Long.parseLong(strArrSplit[2]);
        long j2 = Long.parseLong(strArrSplit[3]);
        long j3 = Long.parseLong(strArrSplit[4]);
        long j4 = Long.parseLong(strArrSplit[5]);
        long j5 = Long.parseLong(strArrSplit[6]);
        long j6 = Long.parseLong(strArrSplit[8]) + j2 + j + j3 + j4 + j5 + Long.parseLong(strArrSplit[7]);
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "total cpu time : " + j6);
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "total cpu idle : " + j4);
        if (str2.equals("")) {
            d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "pidCpuRate is empty string. ");
            return new a(j, j3, j4, j5, j6, 0L);
        }
        String[] strArrSplit2 = str2.split(" ");
        if (strArrSplit2.length < 17) {
            throw new IllegalStateException("pid cpu info array size must great than 17");
        }
        long j7 = Long.parseLong(strArrSplit2[16]) + Long.parseLong(strArrSplit2[13]) + Long.parseLong(strArrSplit2[14]) + Long.parseLong(strArrSplit2[15]);
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "app cpu time : " + j7);
        d.a("QTXA_SYSTEM_MONITOR_CpuSnapShot", "parse end.");
        return new a(j, j3, j4, j5, j6, j7);
    }

    public static a d(String str) {
        try {
            return c(a(), b(str));
        } catch (Throwable unused) {
            w.e("QTXA_SYSTEM_MONITOR_CpuSnapShot", "snapshotByProcessName exception occured. " + str);
            return new a();
        }
    }

    public a() {
        this.f52a = 0L;
        this.f53b = 0L;
        this.c = 0L;
        this.d = 0L;
        this.e = 0L;
        this.f = 0L;
    }
}
