package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import com.blankj.utilcode.util.f;
import java.io.File;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class CrashUtils {
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

    public static final class CrashInfo {
        private f.a mFileHeadProvider;
        private Throwable mThrowable;

        public /* synthetic */ CrashInfo(String str, Throwable th, a aVar) {
            this(str, th);
        }

        public final void addExtraHead(Map<String, String> map) {
            this.mFileHeadProvider.a(map);
        }

        public final Throwable getThrowable() {
            return this.mThrowable;
        }

        public String toString() {
            return this.mFileHeadProvider.toString() + ThrowableUtils.getFullStackTrace(this.mThrowable);
        }

        private CrashInfo(String str, Throwable th) {
            this.mThrowable = th;
            f.a aVar = new f.a("Crash");
            this.mFileHeadProvider = aVar;
            aVar.b(aVar.b, "Time Of Crash", str);
        }

        public final void addExtraHead(String str, String str2) {
            f.a aVar = this.mFileHeadProvider;
            aVar.b(aVar.c, str, str2);
        }
    }

    public interface OnCrashListener {
        void onCrash(CrashInfo crashInfo);
    }

    public static class a implements Thread.UncaughtExceptionHandler {
        public final /* synthetic */ String a;
        public final /* synthetic */ OnCrashListener b;

        public a(String str, OnCrashListener onCrashListener) {
            this.a = str;
            this.b = onCrashListener;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            String str = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss").format(new Date());
            CrashInfo crashInfo = new CrashInfo(str, th, null);
            FileIOUtils.writeFileFromString(this.a + str + ".txt", crashInfo.toString(), true);
            if (CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                CrashUtils.DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(thread, th);
            }
            OnCrashListener onCrashListener = this.b;
            if (onCrashListener != null) {
                onCrashListener.onCrash(crashInfo);
            }
        }
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    private static Thread.UncaughtExceptionHandler getUncaughtExceptionHandler(String str, OnCrashListener onCrashListener) {
        return new a(str, onCrashListener);
    }

    public static void init() {
        init("");
    }

    public static void init(@NonNull File file) {
        init(file.getAbsolutePath(), (OnCrashListener) null);
    }

    public static void init(String str) {
        init(str, (OnCrashListener) null);
    }

    public static void init(OnCrashListener onCrashListener) {
        init("", onCrashListener);
    }

    public static void init(@NonNull File file, OnCrashListener onCrashListener) {
        init(file.getAbsolutePath(), onCrashListener);
    }

    public static void init(String str, OnCrashListener onCrashListener) {
        if (StringUtils.isSpace(str)) {
            if (SDCardUtils.isSDCardEnableByEnvironment() && Utils.getApp().getExternalFilesDir(null) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(Utils.getApp().getExternalFilesDir(null));
                String str2 = FILE_SEP;
                sb.append(str2);
                sb.append("crash");
                sb.append(str2);
                str = sb.toString();
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Utils.getApp().getFilesDir());
                String str3 = FILE_SEP;
                sb2.append(str3);
                sb2.append("crash");
                sb2.append(str3);
                str = sb2.toString();
            }
        } else {
            String str4 = FILE_SEP;
            if (!str.endsWith(str4)) {
                str = spi.c.f(str, str4);
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(getUncaughtExceptionHandler(str, onCrashListener));
    }
}
