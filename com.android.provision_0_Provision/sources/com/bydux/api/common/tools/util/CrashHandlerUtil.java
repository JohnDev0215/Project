package com.bydux.api.common.tools.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
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

    public static CrashHandlerUtil getInstance() {
        return sInstance;
    }

    public void init(Context context) {
        init(context, true);
    }

    public void init(Context context, boolean isAppExitOnCrash) {
        if (context == null) {
            BydLogHelper.logE("context is null!");
            return;
        }
        this.mIsAppExitOnCrash = isAppExitOnCrash;
        if (!isAppExitOnCrash) {
            this.mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = context.getApplicationContext();
        this.mCacheFileName = context.getPackageName().replace(".", "_");
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable ex) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        try {
            ex.printStackTrace();
            dumpExceptionToSDCard(ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!this.mIsAppExitOnCrash && (uncaughtExceptionHandler = this.mDefaultCrashHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        } else {
            appExit();
        }
    }

    private void dumpExceptionToSDCard(Throwable ex) throws IOException {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
                printWriter.println(str2);
                dumpPhoneInfo(printWriter);
                printWriter.println();
                ex.printStackTrace(printWriter);
                printWriter.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception unused) {
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 1);
        pw.print("App Version: ");
        pw.print(packageInfo.versionName);
        pw.print('_');
        pw.println(packageInfo.versionCode);
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        pw.print("Model: ");
        pw.println(Build.MODEL);
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void appExit() {
        try {
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
