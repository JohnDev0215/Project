package com.bydux.api.common.tools.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class CrashHandlerUtil implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME_SUFFIX = ".trace";
    private static final String PATH = "/CrashInfo/log/";
    private static CrashHandlerUtil sInstance = new CrashHandlerUtil();
    private String mCacheFileName;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private boolean mIsAppExitOnCrash = false;

    private CrashHandlerUtil() {
    }

    private void appExit() {
        try {
            System.exit(0);
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Exception e9) {
            e9.printStackTrace();
        }
    }

    private void dumpExceptionToSDCard(Throwable th) throws IOException {
        try {
            if (!TextUtils.equals(Environment.getExternalStorageState(), "mounted")) {
                BydLogHelper.logE("sdcard unmounted,skip dump exception");
                return;
            }
            String str = this.mContext.getExternalFilesDir(null) + PATH;
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            String str2 = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date(System.currentTimeMillis()));
            File file2 = new File(str + this.mCacheFileName + str2 + FILE_NAME_SUFFIX);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
            }
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
                printWriter.println(str2);
                dumpPhoneInfo(printWriter);
                printWriter.println();
                th.printStackTrace(printWriter);
                printWriter.close();
            } catch (Exception e9) {
                e9.printStackTrace();
            }
        } catch (Exception unused) {
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 1);
        printWriter.print("App Version: ");
        printWriter.print(packageInfo.versionName);
        printWriter.print('_');
        printWriter.println(packageInfo.versionCode);
        printWriter.print("OS Version: ");
        printWriter.print(Build.VERSION.RELEASE);
        printWriter.print(BydPowerManagerDevice.SEPARATOR_K_V);
        printWriter.println(Build.VERSION.SDK_INT);
        printWriter.print("Vendor: ");
        printWriter.println(Build.MANUFACTURER);
        printWriter.print("Model: ");
        printWriter.println(Build.MODEL);
        printWriter.print("CPU ABI: ");
        printWriter.println(Build.CPU_ABI);
    }

    public static CrashHandlerUtil getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        init(context, true);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        try {
            th.printStackTrace();
            dumpExceptionToSDCard(th);
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        if (this.mIsAppExitOnCrash || (uncaughtExceptionHandler = this.mDefaultCrashHandler) == null) {
            appExit();
        } else {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public void init(Context context, boolean z7) {
        if (context == null) {
            BydLogHelper.logE("context is null!");
            return;
        }
        this.mIsAppExitOnCrash = z7;
        if (!z7) {
            this.mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();
        this.mCacheFileName = context.getPackageName().replace(".", BydPowerManagerDevice.SEPARATOR_K_V);
    }
}
