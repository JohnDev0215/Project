package com.byd.util;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Environment;
import com.byd.dilink.os.BootBusinessConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<SimpleDateFormat> f2956a = new a();

    class a extends ThreadLocal<SimpleDateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss", Locale.US);
        }
    }

    public static boolean a(String str) {
        return b(str, false);
    }

    public static boolean b(String str, boolean z) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        boolean zMkdirs = file.mkdirs();
        if (zMkdirs && z) {
            e(str);
        }
        return zMkdirs;
    }

    public static boolean c(boolean z) {
        boolean zC = v.d().c(z ? "sys.byd.isUSBExist" : BYDAutoSysPropertiesDevice.SD_EXIST, false);
        w.c("is has external card " + zC + " is udisk : " + z);
        return zC;
    }

    public static String d(Context context, String str, String str2) {
        return i(context, str) + File.separator + str2;
    }

    public static boolean e(String str) {
        File file = new File(str + File.separator + ".nomedia");
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(1);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static String f(Context context) {
        String str = g(context) + File.separator + "Burstshoot";
        a(str);
        return str;
    }

    public static String g(Context context) {
        String strD = d(context, Environment.DIRECTORY_DCIM, "Camera");
        a(strD);
        return strD;
    }

    public static String h(boolean z) {
        String strB = v.d().b(z ? "sys.byd.mUsbotgUuid" : "sys.byd.mSdcardUuid", null);
        if ("".equals(strB) || BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL.equals(strB)) {
            strB = null;
        }
        if (strB != null || !z) {
            return strB;
        }
        try {
            String strA = v.d().a("sys.byd.mUsbUuid");
            if ("".equals(strA)) {
                return null;
            }
            if (BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL.equals(strA)) {
                return null;
            }
            return strA;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String i(Context context, String str) {
        return (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? Environment.getExternalStoragePublicDirectory(str) : context.getFilesDir()).getAbsolutePath();
    }

    public static String j(Context context) {
        return i(context, Environment.DIRECTORY_PICTURES);
    }

    public static String k(Context context) {
        return d(context, Environment.DIRECTORY_PICTURES, "Screenshots");
    }

    public static String l(Context context) {
        String strD = d(context, Environment.DIRECTORY_PICTURES, "widget图片");
        File file = new File(strD);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return strD;
    }
}
