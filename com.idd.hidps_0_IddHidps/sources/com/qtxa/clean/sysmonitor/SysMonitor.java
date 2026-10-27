package com.qtxa.clean.sysmonitor;

import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.view.Choreographer;
import com.qtxa.qtxadefend.Utils;
import com.qtxa.qtxadefend.w;
import com.qtxa.report.JniQtxaManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SysMonitor {
    private static double C;
    private static double D;
    private static Timer E;
    private long d;
    private Context e;
    private List<Integer> g;
    private List<Long> h;
    private BufferedReader i;
    private String j;
    private double k;
    private long n;
    private long o;
    private double p;
    private double q;
    private long r;
    private long s;
    private HashMap<String, Integer> z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f45a = 60000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<com.qtxa.clean.a> f46b = new ArrayList();
    private com.qtxa.clean.d.a c = null;
    private boolean f = true;
    private String l = "";
    private String m = "";
    long t = 0;
    long u = 0;
    private long v = 1;
    private int w = 1;
    private boolean x = false;
    private List<String> y = new ArrayList();
    private JniQtxaManager.RuleCallback A = new a();
    private SysResourceStatus B = SysResourceStatus.NO_LOW;

    public enum SysResourceStatus {
        LOW_DISK,
        LOW_MEMORY,
        LOW_CPU,
        NO_LOW
    }

    class a implements JniQtxaManager.RuleCallback {
        a() {
        }

        @Override // com.qtxa.report.JniQtxaManager.RuleCallback
        public void callback(int i, byte[] bArr) throws Throwable {
            if (i == 1) {
                try {
                    SysMonitor.this.z(bArr);
                    return;
                } catch (Exception e) {
                    w.d("get cpu rules error: " + e.toString());
                    return;
                }
            }
            w.d("get cpu rules error: " + i);
            d.b("QtSysMonitor", "get cpu rules:" + i);
            if (com.qtxa.qtxadefend.b.c) {
                SysMonitor.this.O();
            }
        }
    }

    class b implements Choreographer.FrameCallback {
        b() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            SysMonitor.this.u = System.currentTimeMillis();
            SysMonitor sysMonitor = SysMonitor.this;
            sysMonitor.D(sysMonitor.u);
            SysMonitor sysMonitor2 = SysMonitor.this;
            long j2 = sysMonitor2.u;
            long j3 = sysMonitor2.t;
            if (j2 - j3 > 40 && j3 != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("frame over time");
                SysMonitor sysMonitor3 = SysMonitor.this;
                sb.append(sysMonitor3.u - sysMonitor3.t);
                sb.append("ms: ");
                sb.append(SysMonitor.this.t);
                sb.append("-");
                sb.append(SysMonitor.this.u);
                w.e("HidpsSysMonitor", sb.toString());
                List list = SysMonitor.this.h;
                SysMonitor sysMonitor4 = SysMonitor.this;
                list.add(0, Long.valueOf(sysMonitor4.u - sysMonitor4.t));
            }
            Choreographer.getInstance().postFrameCallback(this);
            SysMonitor sysMonitor5 = SysMonitor.this;
            sysMonitor5.t = sysMonitor5.u;
        }
    }

    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() throws Throwable {
            SysMonitor.this.M();
        }
    }

    public static String B(String str, int i, String str2) {
        StringReader stringReader = new StringReader(str);
        JSONArray jSONArray = new JSONArray();
        try {
            BufferedReader bufferedReader = new BufferedReader(stringReader);
            do {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                C(line, jSONArray, str2);
            } while (jSONArray.length() <= i);
        } catch (Exception e) {
            w.d("parser_top:" + e.toString());
        }
        return jSONArray.toString();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:10|(1:12)(11:14|(1:16)(2:46|17)|18|(1:20)(2:21|(1:23)(2:42|24))|25|(4:27|(2:30|28)|48|(1:32))|33|44|34|38|39)|13|18|(0)(0)|25|(0)|33|44|34|38|39) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
    
        r13 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ae, code lost:
    
        r13.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void C(java.lang.String r13, org.json.JSONArray r14, java.lang.String r15) {
        /*
            java.lang.String r13 = r13.trim()
            r0 = 32
            java.lang.String[] r13 = com.qtxa.qtxadefend.Utils.mySplit(r13, r0)
            if (r13 == 0) goto Lb4
            int r0 = r13.length
            if (r0 != 0) goto L11
            goto Lb4
        L11:
            r0 = 0
            r1 = r13[r0]
            boolean r1 = x(r1)
            if (r1 != 0) goto L1b
            return
        L1b:
            r1 = 5
            r1 = r13[r1]
            java.lang.String r2 = "M"
            java.lang.String r3 = ""
            java.lang.String r1 = r1.replace(r2, r3)
            r4 = 6
            r4 = r13[r4]
            java.lang.String r2 = r4.replace(r2, r3)
            java.lang.String r4 = "K"
            boolean r5 = r1.contains(r4)
            r6 = 4652218415073722368(0x4090000000000000, double:1024.0)
            java.lang.String r8 = "G"
            r9 = 0
            if (r5 == 0) goto L40
            r1.replace(r4, r3)
        L3e:
            r11 = r9
            goto L54
        L40:
            boolean r5 = r1.contains(r8)
            if (r5 == 0) goto L50
            java.lang.String r1 = r1.replace(r8, r3)
            double r11 = java.lang.Double.parseDouble(r1)
            double r11 = r11 * r6
            goto L54
        L50:
            double r11 = java.lang.Double.parseDouble(r1)     // Catch: java.lang.Throwable -> L3e
        L54:
            boolean r1 = r2.contains(r4)
            if (r1 == 0) goto L5e
            r2.replace(r4, r3)
            goto L73
        L5e:
            boolean r1 = r2.contains(r8)
            if (r1 == 0) goto L6f
            java.lang.String r1 = r2.replace(r8, r3)
            double r1 = java.lang.Double.parseDouble(r1)
            double r9 = r1 * r6
            goto L73
        L6f:
            double r9 = java.lang.Double.parseDouble(r2)     // Catch: java.lang.Throwable -> L73
        L73:
            double r11 = r11 - r9
            r1 = 8
            r1 = r13[r1]
            r2 = 11
            r13 = r13[r2]
            boolean r2 = android.text.TextUtils.isEmpty(r15)
            if (r2 != 0) goto L98
            java.lang.String r2 = "&&&"
            java.lang.String[] r15 = r15.split(r2)
            r2 = r0
        L89:
            int r3 = r15.length
            if (r0 >= r3) goto L95
            r2 = r15[r0]
            boolean r2 = r13.contains(r2)
            int r0 = r0 + 1
            goto L89
        L95:
            if (r2 != 0) goto L98
            return
        L98:
            org.json.JSONObject r15 = new org.json.JSONObject
            r15.<init>()
            java.lang.String r0 = "cpu"
            r15.put(r0, r1)     // Catch: org.json.JSONException -> Lad
            java.lang.String r0 = "mem"
            r15.put(r0, r11)     // Catch: org.json.JSONException -> Lad
            java.lang.String r0 = "name"
            r15.put(r0, r13)     // Catch: org.json.JSONException -> Lad
            goto Lb1
        Lad:
            r13 = move-exception
            r13.printStackTrace()
        Lb1:
            r14.put(r15)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.clean.sysmonitor.SysMonitor.C(java.lang.String, org.json.JSONArray, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(long j) {
        if (this.v == 1) {
            this.v = j;
        }
        long j2 = this.v;
        long j3 = j / 1000;
        if (j2 / 1000 == j3) {
            this.w++;
            return;
        }
        if (j3 - (j2 / 1000) >= 1) {
            d.b("HidpsSysMonitor", "sm:" + this.w);
            this.g.add(0, Integer.valueOf(this.w));
            this.w = 1;
            this.v = j;
        }
    }

    private void F(double d) {
        this.k = d;
    }

    private void G(long j, long j2) {
        this.n = j;
        this.o = j2;
    }

    private void H(long j, long j2) {
        this.r = j;
        this.s = j2;
    }

    private void J(String str) {
        this.l = str;
    }

    private void L() {
        Choreographer.getInstance().postFrameCallback(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() throws Throwable {
        if (this.y.size() == 0) {
            return;
        }
        d.a("HidpsSysMonitor", "get system data every 60 seconds.");
        if (this.y.contains("ram")) {
            s();
        }
        if (this.y.contains("cpu")) {
            l();
        }
        if (this.y.contains("disk")) {
            p();
        }
        this.y.contains("iops");
        q();
        float f = (float) this.k;
        long j = this.n;
        long j2 = j - this.o;
        long j3 = (long) this.p;
        long j4 = (long) this.q;
        long j5 = this.r;
        d.c(f, j, j2, j3, j4, j5, j5 - this.s, this.l, this.m);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006c A[Catch: all -> 0x0068, TRY_LEAVE, TryCatch #5 {all -> 0x0068, blocks: (B:38:0x0064, B:42:0x006c), top: B:49:0x0064 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void O() throws java.lang.Throwable {
        /*
            r5 = this;
            java.lang.String r0 = "testLoaderRules....1-6-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            java.lang.String r2 = "/data/qtxa/rule/1-6-0.rule_bak"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L45
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
            r3.<init>()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3e
        L16:
            int r0 = r1.read(r2)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r4 = -1
            if (r0 == r4) goto L22
            r4 = 0
            r3.write(r2, r4, r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            goto L16
        L22:
            byte[] r0 = r3.toByteArray()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r5.z(r0)     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r3.close()     // Catch: java.lang.Throwable -> L36 java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Throwable -> L55
            r3.close()     // Catch: java.lang.Throwable -> L55
            goto L60
        L36:
            r5 = move-exception
            goto L3c
        L38:
            r5 = move-exception
            goto L40
        L3a:
            r5 = move-exception
            r3 = r0
        L3c:
            r0 = r1
            goto L62
        L3e:
            r5 = move-exception
            r3 = r0
        L40:
            r0 = r1
            goto L47
        L42:
            r5 = move-exception
            r3 = r0
            goto L62
        L45:
            r5 = move-exception
            r3 = r0
        L47:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L61
            java.lang.String r5 = "testLoaderRules error....1-6-0.rule_bak"
            com.qtxa.qtxadefend.w.a(r5)     // Catch: java.lang.Throwable -> L61
            if (r0 == 0) goto L57
            r0.close()     // Catch: java.lang.Throwable -> L55
            goto L57
        L55:
            r5 = move-exception
            goto L5d
        L57:
            if (r3 == 0) goto L60
            r3.close()     // Catch: java.lang.Throwable -> L55
            goto L60
        L5d:
            r5.printStackTrace()
        L60:
            return
        L61:
            r5 = move-exception
        L62:
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.lang.Throwable -> L68
            goto L6a
        L68:
            r0 = move-exception
            goto L70
        L6a:
            if (r3 == 0) goto L73
            r3.close()     // Catch: java.lang.Throwable -> L68
            goto L73
        L70:
            r0.printStackTrace()
        L73:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.clean.sysmonitor.SysMonitor.O():void");
    }

    public static SysMonitor h(Context context) {
        return null;
    }

    private double j() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        double availableBlocks = (((double) statFs.getAvailableBlocks()) * ((double) statFs.getBlockSize())) / 1.073741824E9d;
        d.a("HidpsSysMonitor", "get available disk size: " + availableBlocks);
        return availableBlocks;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    private void l() throws Throwable {
        BufferedReader bufferedReader;
        int i;
        String strG = com.qtxa.clean.c.a().g(-1);
        String strI = com.qtxa.clean.c.a().i(2);
        String strB = B(strI, 5, "");
        this.m = B(strI, 5, "qtxa&&&idd");
        J(strB);
        StringReader stringReader = new StringReader(strG);
        ?? r1 = 0;
        r1 = 0;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(stringReader);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            String[] strArrMySplit = Utils.mySplit(bufferedReader.readLine(), ' ');
            double d = Double.parseDouble(strArrMySplit[4]);
            double d2 = Double.parseDouble(strArrMySplit[1]) + Double.parseDouble(strArrMySplit[2]) + Double.parseDouble(strArrMySplit[3]) + Double.parseDouble(strArrMySplit[5]) + Double.parseDouble(strArrMySplit[7]) + Double.parseDouble(strArrMySplit[6]);
            double d3 = d2 + d;
            double d4 = d3 - (C + D);
            double d5 = 0.0d;
            double d6 = d3;
            if (0.0d != d4) {
                double d7 = ((d2 - C) * 100.0d) / (d3 - (C + D));
                int i2 = (d7 > 0.0d ? 1 : (d7 == 0.0d ? 0 : -1));
                if (i2 < 0) {
                    i = i2;
                } else {
                    int i3 = (d7 > 100.0d ? 1 : (d7 == 100.0d ? 0 : -1));
                    if (i3 > 0) {
                        d5 = 100.0d;
                        i = i3;
                    } else {
                        d5 = d7;
                        i = i3;
                    }
                }
                F(d5);
                d6 = i;
            }
            C = d2;
            D = d;
            bufferedReader.close();
            r1 = d6;
        } catch (IOException e3) {
            e = e3;
            bufferedReader2 = bufferedReader;
            e.printStackTrace();
            r1 = bufferedReader2;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
                r1 = bufferedReader2;
            }
        } catch (Throwable th2) {
            th = th2;
            r1 = bufferedReader;
            if (r1 != 0) {
                r1.close();
            }
            throw th;
        }
    }

    private CpuInfo m(String str, boolean z) {
        com.qtxa.clean.sysmonitor.a aVarD = com.qtxa.clean.sysmonitor.a.d(str);
        d.a("HidpsSysMonitor", "before snapshot total time  = " + aVarD.e);
        try {
            Thread.sleep(400L);
        } catch (Throwable th) {
            th.printStackTrace();
            Thread.currentThread().interrupt();
        }
        com.qtxa.clean.sysmonitor.a aVarD2 = com.qtxa.clean.sysmonitor.a.d(str);
        d.a("HidpsSysMonitor", "after snapshot total time  = " + aVarD2.e);
        float f = ((float) (aVarD2.e - aVarD.e)) * 1.0f;
        d.a("HidpsSysMonitor", "totalTime must = " + f);
        if (f <= 0.0f) {
            w.e("HidpsSysMonitor", "totalTime must greater than 0");
            return CpuInfo.f44a;
        }
        double d = (f - (aVarD2.c - aVarD.c)) / f;
        double d2 = (aVarD2.f - aVarD.f) / f;
        double d3 = (aVarD2.f52a - aVarD.f52a) / f;
        double d4 = (aVarD2.f53b - aVarD.f53b) / f;
        double d5 = (aVarD2.d - aVarD.d) / f;
        if (y(Double.valueOf(d), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4), Double.valueOf(d5))) {
            return new CpuInfo(d, d2, d3, d4, d5);
        }
        w.e("HidpsSysMonitor", "not valid ratio..");
        return CpuInfo.f44a;
    }

    private double[] n() {
        double[] dArr = {0.0d, 0.0d};
        StorageManager storageManager = (StorageManager) this.e.getSystemService(StorageManager.class);
        StorageStatsManager storageStatsManager = (StorageStatsManager) this.e.getSystemService("storagestats");
        long totalBytes = 0;
        long freeBytes = 0;
        for (VolumeInfo volumeInfo : storageManager.getVolumes()) {
            if (volumeInfo.getType() == 1 && volumeInfo.isMountedReadable()) {
                try {
                    totalBytes += storageStatsManager.getTotalBytes(volumeInfo.getFsUuid());
                    freeBytes += storageStatsManager.getFreeBytes(volumeInfo.getFsUuid());
                } catch (IOException e) {
                    w.e("HidpsSysMonitor", e.toString());
                }
            }
        }
        dArr[0] = totalBytes;
        dArr[1] = freeBytes;
        G(totalBytes, freeBytes);
        return dArr;
    }

    private void p() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        statFs.getAvailableBytes();
        statFs.getTotalBytes();
        statFs.getFreeBytes();
        G((((long) statFs.getBlockCount()) * blockSize) / 1024, (((long) statFs.getAvailableBlocks()) * blockSize) / 1024);
    }

    private void q() {
        A(com.qtxa.clean.c.a().b());
    }

    private void s() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(com.qtxa.clean.c.a().f()));
            this.i = bufferedReader;
            this.j = bufferedReader.readLine();
            while (this.j != null) {
                if (this.f && this.j.startsWith("MemTotal:")) {
                    this.d = Long.parseLong(this.j.split("[ ]+", 3)[1]);
                    this.f = false;
                    d.a("HidpsSysMonitor", "MemToal:" + String.valueOf(this.d));
                } else if (this.j.startsWith("MemFree:")) {
                    d.a("HidpsSysMonitor", "MemFree:" + this.j.split("[ ]+", 3)[1]);
                } else if (this.j.startsWith("MemAvailable:")) {
                    String str = this.j.split("[ ]+", 3)[1];
                    d.a("HidpsSysMonitor", "MemAvailable:" + str);
                    H(this.d, Long.parseLong(str));
                } else if (this.j.startsWith("Buffers:")) {
                    d.a("HidpsSysMonitor", "MemBuffered:" + this.j.split("[ ]+", 3)[1]);
                } else if (this.j.startsWith("Cached:")) {
                    d.a("HidpsSysMonitor", "Cached:" + this.j.split("[ ]+", 3)[1]);
                }
                this.j = this.i.readLine();
            }
            if (this.i != null) {
                this.i.close();
            }
        } catch (Exception e) {
            w.d("getRamUsageInternal: " + e.toString());
            e.printStackTrace();
        }
    }

    private double v() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        double blockCount = (((double) statFs.getBlockCount()) * ((double) statFs.getBlockSize())) / 1.073741824E9d;
        d.a("HidpsSysMonitor", "get total disk size: " + blockCount);
        return blockCount;
    }

    public static boolean x(String str) {
        char cCharAt;
        if (str.trim().length() == 0) {
            return false;
        }
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
            cCharAt = str.charAt(length);
            if (cCharAt < '0') {
                break;
            }
        } while (cCharAt <= '9');
        return false;
    }

    private boolean y(Double... dArr) {
        for (Double d : dArr) {
            double dDoubleValue = d.doubleValue();
            if (dDoubleValue < 0.0d || dDoubleValue > 1.0d) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(byte[] bArr) {
        try {
            String str = new String(bArr, "UTF-8");
            d.b("QtSysMonitor", "get cpu rules:" + str);
            w.d("get cpu rules: " + str);
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("item");
            if (jSONArray != null) {
                this.y.clear();
                for (int i = 0; i < jSONArray.length(); i++) {
                    g(jSONArray.getString(i));
                }
            }
            int i2 = jSONObject.getInt("cycle");
            if (i2 == 0) {
                N();
                return;
            }
            int i3 = i2 * 1000;
            if (i3 != this.f45a) {
                I(i3);
                N();
                K();
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.d("cpu config  error:" + e.toString());
            JniQtxaManager.java_rollBack("systemerr", 1, 9, 0, 1, this.A);
        }
    }

    public void A(String str) {
        BufferedReader bufferedReader;
        double d;
        double d2;
        try {
            bufferedReader = new BufferedReader(new StringReader(str));
            d = 0.0d;
            d2 = 0.0d;
        } catch (Exception e) {
            w.d("iops:total_read：" + e.toString());
            return;
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                this.p = d;
                this.q = d2;
                return;
            } else if (!TextUtils.isEmpty(line) && line.trim().length() != 0) {
                try {
                    String[] strArrMySplit = Utils.mySplit(line.trim(), ' ');
                    d += Double.parseDouble(strArrMySplit[2]);
                    d2 += Double.parseDouble(strArrMySplit[3]);
                } catch (Exception e2) {
                    w.d("iops:total_read：" + e2.toString());
                }
            }
            w.d("iops:total_read：" + e.toString());
            return;
        }
    }

    public void E(String str) {
        if (JniQtxaManager.library) {
            JniQtxaManager.java_loadRule(str, 1, 9, 0, 1, this.A);
        }
    }

    public void I(int i) {
        d.a("HidpsSysMonitor", "setSamplePeriod: " + i);
        this.f45a = i;
    }

    public void K() {
        if (E == null) {
            synchronized (SysMonitor.class) {
                E = new Timer(true);
                w.d("SysMonitor begin for start: 0 cycle: " + this.f45a);
                E.schedule(new c(), 0L, (long) this.f45a);
            }
        }
    }

    public void N() {
        if (E != null) {
            w.d("SysMonitor cancel");
            E.cancel();
            E = null;
        }
    }

    public double P(double d) {
        String[] strArr = {"com.qtxa.bodyguard"};
        for (int i = 0; i < 1; i++) {
            d -= ((double) com.qtxa.clean.c.a().d(strArr[i])) / 1048576.0d;
        }
        return d;
    }

    public void f(com.qtxa.clean.a aVar) {
        if (aVar == null || this.f46b.contains(aVar)) {
            return;
        }
        this.f46b.add(aVar);
    }

    public void g(String str) {
        this.y.add(str);
    }

    public HashMap<String, Integer> i() {
        return this.z;
    }

    public double k(boolean z) {
        CpuInfo cpuInfoM = m("com.idd.hidps", z);
        d.a("HidpsSysMonitor", "privacy cpu usage info: " + cpuInfoM);
        double d = cpuInfoM.totalUseRatio * 100.0d;
        d.a("POPUWAL", "get cpu data is: " + d);
        if (!z) {
            this.c.i(d);
        }
        return d;
    }

    public double[] o() {
        double[] dArr = new double[2];
        if (Build.VERSION.SDK_INT >= 24) {
            return n();
        }
        dArr[0] = v();
        dArr[1] = j();
        return dArr;
    }

    public double[] r() {
        s();
        d.a("HidpsSysMonitor", "before trim, mAvilable ram space:" + this.s);
        double dP = P((double) this.s);
        d.a("HidpsSysMonitor", "mAvilable actual avail ram space:" + dP);
        return new double[]{this.r, dP};
    }

    public boolean t() {
        return this.x;
    }

    public SysResourceStatus u() {
        return this.B;
    }

    public void w() {
        if (true == com.qtxa.clean.e.a.b().x()) {
            d.a("HidpsSysMonitor", "support auto clean and enable frame monitor.");
            L();
        }
    }
}
