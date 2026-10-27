package com.byd.engineermode.utils;

import android.content.Context;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import com.byd.engineermode.DiagnoseApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: DevicesType.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f2507a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2508b = "usbotg";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f2509c = "sdcard";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Context f2510d;

    private e(Context context) {
        f2510d = context;
    }

    private String c(Context context) {
        String strE = e(context);
        String str = "";
        if (TextUtils.isEmpty(strE)) {
            a.c("getAllImagesBuildInfoTag  usbPath is null ");
            return "";
        }
        File file = new File(strE + "/all_images/buildinfo.txt");
        if (!file.exists()) {
            a.c("getAllImagesBuildInfoTag all_images/buildinfo.txt is not exists");
            return "";
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                a.i("line = " + line);
                if (line.contains("rb-byd-idc_")) {
                    str = "rb-byd-idc_";
                    break;
                }
                if (line.contains("rb-byd-dx_")) {
                    str = "rb-byd-dx_";
                    break;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static e d() {
        if (f2507a == null) {
            f2507a = new e(DiagnoseApplication.e());
        }
        return f2507a;
    }

    private String e(Context context) {
        String str = "";
        try {
            List<VolumeInfo> volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            boolean z = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            a.a("isUDiskMount isUSBExist = " + z);
            if (volumes != null && z) {
                a.a("volumes size = " + volumes.size());
                for (VolumeInfo volumeInfo : volumes) {
                    try {
                        a.a("addDisk volumeInfo = " + volumeInfo);
                        if (f2508b.equals(volumeInfo.getDeviceType())) {
                            str = volumeInfo.path;
                            a.a("USBOTG mUDiskFilePath = " + str);
                            return str;
                        }
                        if (f2509c.equals(volumeInfo.getDeviceType())) {
                            a.a("SDCARD mUDiskFilePath ");
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
        } catch (Throwable th2) {
            a.d("initVolume failed", th2);
        }
        return str;
    }

    public boolean a() {
        String bydSystemVersion = BYDAutoVersionDevice.getInstance(f2510d).getBydSystemVersion();
        i.c("TAG", "checkDeSayVersion() mBYDAutoVersionDevice = " + bydSystemVersion);
        if (TextUtils.isEmpty(bydSystemVersion)) {
            return false;
        }
        return bydSystemVersion.startsWith("56.1.1") || bydSystemVersion.startsWith("56.1.2");
    }

    public boolean b() {
        String bydSystemVersion = BYDAutoVersionDevice.getInstance(f2510d).getBydSystemVersion();
        String strC = c(f2510d);
        i.c("TAG", "checkImages() mBYDAutoVersionDevice = " + bydSystemVersion + ", allImagesBuildInfoTag = " + strC);
        if (TextUtils.isEmpty(bydSystemVersion) || TextUtils.isEmpty(strC)) {
            return false;
        }
        if ("rb-byd-dx_".equals(strC) && (bydSystemVersion.startsWith("51.1.1") || bydSystemVersion.startsWith("51.1.2"))) {
            return true;
        }
        if ("rb-byd-idc_".equals(strC)) {
            return bydSystemVersion.startsWith("51.1.3") || bydSystemVersion.startsWith("51.1.4") || bydSystemVersion.startsWith("55.1");
        }
        return false;
    }
}
