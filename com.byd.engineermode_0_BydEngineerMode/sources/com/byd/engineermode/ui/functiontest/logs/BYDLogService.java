package com.byd.engineermode.ui.functiontest.logs;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.StatFs;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BYDLogService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2462b = "/storage/63B6-453C";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f2463c = "usbotg";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f2464d = "sdcard";
    private static String e = null;
    private static boolean f = false;
    private c g;
    private final b h = new b();
    private final int i = 1;
    private final int j = 2;
    private String k = "/dr_log";

    public class a extends Thread {
        public a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            com.byd.engineermode.utils.a.a("BYDLogService save all log");
            boolean unused = BYDLogService.f = true;
            BYDLogService.this.e();
            boolean unused2 = BYDLogService.f = false;
        }
    }

    public class b extends Binder {
        public b() {
        }

        public BYDLogService a() {
            return BYDLogService.this;
        }
    }

    private boolean b(String str) {
        long jC = c(str);
        Log.i("BYDLogService", "checkSdcardLogs dataAvailSpace = " + jC + ", defaultAvailSpace = 1073741824");
        return jC > 1073741824;
    }

    private long c(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private SimpleDateFormat d() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ssZ");
    }

    private boolean f(int i) {
        boolean zEquals = Environment.getExternalStorageState().equals("mounted");
        boolean zG = g(this);
        Log.i("BYDLogService", "mediaMounted = " + zEquals + "  isUDiskMount = " + zG + ", type = " + i);
        if ((i & 2) != 0 && zG) {
            e = f2462b + "/logs/All";
            return true;
        }
        if ((i & 1) != 0 && zEquals) {
            e = null;
            return false;
        }
        f2462b = null;
        e = null;
        return false;
    }

    private void j(File file) {
        if (file.exists() && file.isDirectory()) {
            File[] fileArrListFiles = file.listFiles();
            int length = fileArrListFiles == null ? 0 : fileArrListFiles.length;
            for (int i = 0; i < length; i++) {
                String absolutePath = fileArrListFiles[i].getAbsolutePath();
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(new File(absolutePath)));
                sendBroadcast(intent);
            }
        }
    }

    public void e() {
        c cVar = this.g;
        if (cVar != null) {
            cVar.j(1);
        }
        if (i(3)) {
            c cVar2 = this.g;
            if (cVar2 != null) {
                cVar2.j(2);
            }
            Log.e("BYDLogService", "BYDLogService finish to save log");
            return;
        }
        c cVar3 = this.g;
        if (cVar3 != null) {
            cVar3.j(3);
        }
        Log.e("BYDLogService", "LogService save log error");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.util.Iterator] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public boolean g(Context context) {
        try {
            List volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            boolean z = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            com.byd.engineermode.utils.a.a("isUDiskMount isUSBExist = " + z);
            if (volumes != null && z) {
                com.byd.engineermode.utils.a.a("volumes size = " + volumes.size());
                Iterator it = volumes.iterator();
                while (it.hasNext()) {
                    VolumeInfo volumeInfo = (VolumeInfo) it.next();
                    try {
                        com.byd.engineermode.utils.a.a("addDisk volumeInfo = " + volumeInfo + ", getDeviceType = " + volumeInfo.getDeviceType() + ", volumeInfo.isMountedReadable() = " + volumeInfo.isMountedReadable());
                        if (f2463c.equals(volumeInfo.getDeviceType()) && volumeInfo.isMountedReadable()) {
                            f2462b = volumeInfo.path;
                            com.byd.engineermode.utils.a.a("USBOTG uDiskPath = " + f2462b);
                            it = 1;
                            return true;
                        }
                        if (f2464d.equals(volumeInfo.getDeviceType())) {
                            com.byd.engineermode.utils.a.a("SDCARD uDiskPath ");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            com.byd.engineermode.utils.a.c("initVolume failed = " + th2);
        }
        return false;
    }

    public void h() {
        if (!f) {
            new a().start();
            com.byd.engineermode.utils.a.a("BYDLogService SaveLogThread send message to save log");
            return;
        }
        com.byd.engineermode.utils.a.a("BYDLogService saveAllLog again");
        c cVar = this.g;
        if (cVar != null) {
            cVar.j(6);
        }
    }

    public boolean i(int i) {
        if (!f(i)) {
            c cVar = this.g;
            if (cVar != null) {
                cVar.j(10);
            }
            return false;
        }
        File file = new File(e + d().format(new Date()) + "DrLog.zip");
        Log.i("BYDLogService", "saveAllLogsAndCompress path = " + file.getPath() + ", path.getParentFile().getPath()) = " + file.getParentFile().getPath());
        if (!file.getParentFile().exists()) {
            boolean zMkdirs = file.getParentFile().mkdirs();
            Log.i("BYDLogService", "path.getParentFile().mkdirs is " + zMkdirs);
            if (!zMkdirs) {
                return false;
            }
        }
        if (!b(file.getParentFile().getPath())) {
            Log.e("BYDLogService", "saveAllLogsAndCompress this no space ");
            this.g.j(9);
            return false;
        }
        try {
            com.byd.engineermode.ui.functiontest.logs.b.a(null, file, this.k);
            c cVar2 = this.g;
            if (cVar2 != null) {
                cVar2.j(8);
            }
            j(file);
            return true;
        } catch (Exception e2) {
            Log.e("BYDLogService", "compress file error");
            e2.printStackTrace();
            return false;
        }
    }

    public void k(c cVar) {
        this.g = cVar;
    }

    public void l(c cVar) {
        this.g = null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.h;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.d("BYDLogService", "service onDestory");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }
}
