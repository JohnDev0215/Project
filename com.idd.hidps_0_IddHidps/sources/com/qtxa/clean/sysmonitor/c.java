package com.qtxa.clean.sysmonitor;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static double a(String str) {
        try {
            int i = Integer.parseInt(com.qtxa.clean.c.a().c("get cpu core"));
            CpuInfo cpuInfoB = b(str);
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", "sample cpu info for process: " + str);
            double d = cpuInfoB.appCpuRatio * 100.0d;
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", "get from native cpu core number: " + i);
            StringBuilder sb = new StringBuilder();
            sb.append("processName: ");
            sb.append(str);
            sb.append("'s cpu data is: ");
            double d2 = d * ((double) i);
            sb.append(d2);
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", sb.toString());
            return d2;
        } catch (NumberFormatException unused) {
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", "get invalid Cpu data: ");
            return 0.0d;
        }
    }

    private static CpuInfo b(String str) {
        a aVarD = a.d(str);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "before snapshot total time  = " + aVarD.e);
        try {
            Thread.sleep(1000L);
        } catch (Throwable th) {
            th.printStackTrace();
            Thread.currentThread().interrupt();
        }
        a aVarD2 = a.d(str);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "after snapshot total time  = " + aVarD2.e);
        float f = ((float) (aVarD2.e - aVarD.e)) * 1.0f;
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "totalTime must = " + f);
        if (f <= 0.0f) {
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", "totalTime must greater than 0");
            return CpuInfo.f44a;
        }
        double d = (f - (aVarD2.c - aVarD.c)) / f;
        double d2 = (aVarD2.f - aVarD.f) / f;
        double d3 = (aVarD2.f52a - aVarD.f52a) / f;
        double d4 = (aVarD2.f53b - aVarD.f53b) / f;
        double d5 = (aVarD2.d - aVarD.d) / f;
        if (!d(Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4), Double.valueOf(d5))) {
            d.a("QTXA_SYSTEM_MONITOR_EXPORT", "not valid ratio..");
            return CpuInfo.f44a;
        }
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "begin app cpu data  = " + aVarD.f);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "end app cpu data  = " + aVarD2.f);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "total time  = " + f);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "app ratio  = " + d2);
        d.a("QTXA_SYSTEM_MONITOR_EXPORT", "total ratio  = " + d);
        return new CpuInfo(d, d2, d3, d4, d5);
    }

    public static int c(String str) {
        return com.qtxa.clean.c.a().d(str);
    }

    private static boolean d(Double... dArr) {
        for (Double d : dArr) {
            double dDoubleValue = d.doubleValue();
            if (dDoubleValue < 0.0d || dDoubleValue > 1.0d) {
                return false;
            }
        }
        return true;
    }
}
