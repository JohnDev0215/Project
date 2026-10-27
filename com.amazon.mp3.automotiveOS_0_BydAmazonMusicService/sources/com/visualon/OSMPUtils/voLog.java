package com.visualon.OSMPUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes7.dex */
public class voLog {
    private static final String JAVA_MODULE_ID = "090B0000";
    private static final String LIB_NAME = "voLogSys";
    private static final String SEPARATOR = ",";
    private static boolean isInitialized = false;
    private static boolean mJNILoaded = false;
    private static int mLogSysVer = 0;
    private static long mNativeContext = 0;
    private static boolean m_bPrintLog = false;

    private static native int nativeAddConfigure(long j, String str);

    private static native int nativeInit(long j, String str);

    private static native int nativeInitCrashLogDir(long j, String str);

    private static native boolean nativeIsDebug(long j);

    private static native int nativeLog(long j, int i, int i2, String str);

    private static native int nativeUninit(long j);

    private enum FileLevelStatus {
        LOGFORV2(0),
        LOGFORV1(255),
        LOGFORUNKNOWN(Integer.MAX_VALUE);

        private int value;

        FileLevelStatus(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static FileLevelStatus valueOf(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (i == values()[i2].getValue()) {
                    return values()[i2];
                }
            }
            return LOGFORUNKNOWN;
        }
    }

    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        return stackTraceElement.getFileName().split("\\.")[0];
    }

    private static void printLog(String str, String str2, String str3, int i, FileLevelStatus fileLevelStatus, Object... objArr) {
        if (enablePrintLog()) {
            String str4 = str2 == null ? "No Message." : str2;
            try {
                str4 = "" + String.format(str2, objArr);
            } catch (Exception unused) {
            }
            nativeLog(mNativeContext, i, fileLevelStatus.getValue(), getLogInfo(Thread.currentThread().getStackTrace()[Math.min(4, Thread.currentThread().getStackTrace().length - 1)], str3, fileLevelStatus, str + " " + str4));
        }
    }

    public static void v2(String str, Object... objArr) {
        printLog("", str, "Verbose", 2, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void v(String str, String str2, Object... objArr) {
        printLog(str, str2, "Verbose", 2, FileLevelStatus.LOGFORV1, objArr);
    }

    public static void i2(String str, Object... objArr) {
        printLog("", str, "Info", 4, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void i(String str, String str2, Object... objArr) {
        printLog(str, str2, "Info", 4, FileLevelStatus.LOGFORV1, objArr);
    }

    public static void e2(String str, Object... objArr) {
        printLog("", str, "Error", 6, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        printLog(str, str2, "Error", 6, FileLevelStatus.LOGFORV1, objArr);
    }

    public static void d2(String str, Object... objArr) {
        printLog("", str, "Debug", 3, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void d(String str, String str2, Object... objArr) {
        printLog(str, str2, "Debug", 3, FileLevelStatus.LOGFORV1, objArr);
    }

    public static void w2(String str, Object... objArr) {
        printLog("", str, "Warning", 5, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void w(String str, String str2, Object... objArr) {
        printLog(str, str2, "Warning", 5, FileLevelStatus.LOGFORV1, objArr);
    }

    public static void crash2(String str, Object... objArr) {
        printLog("", str, "Crash", 7, FileLevelStatus.LOGFORV2, objArr);
    }

    public static void crash(String str, String str2, Object... objArr) {
        printLog(str, str2, "Crash", 7, FileLevelStatus.LOGFORV1, objArr);
    }

    public static boolean enablePrintLog() {
        return m_bPrintLog && mJNILoaded && isInitialized;
    }

    private static String getLogInfo(StackTraceElement stackTraceElement, String str, FileLevelStatus fileLevelStatus, String str2) {
        StringBuilder sb = new StringBuilder();
        long id = Thread.currentThread().getId();
        String fileName = stackTraceElement.getFileName();
        String methodName = stackTraceElement.getMethodName();
        int lineNumber = stackTraceElement.getLineNumber();
        sb.append(new SimpleDateFormat("HH:mm:ss|SSS ").format(new Date()));
        sb.append("@@@VOLOG");
        sb.append(SEPARATOR);
        sb.append(String.format("%7s", str));
        sb.append(SEPARATOR);
        sb.append(" ModuleID[090B0000]");
        sb.append(SEPARATOR);
        sb.append(" ThreadID[" + id + "]");
        sb.append(SEPARATOR);
        sb.append(" " + fileName);
        sb.append(SEPARATOR);
        sb.append(" " + methodName);
        sb.append(SEPARATOR);
        sb.append(" Line#" + lineNumber);
        if (str2 != null && str2.length() != 0) {
            sb.append(SEPARATOR);
            sb.append(" " + str2);
        }
        return sb.toString();
    }

    public static int initLogLib(String str) {
        int i = 0;
        if (isInitialized) {
            return 0;
        }
        if (!loadJNI(str)) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        int iNativeInit = nativeInit(mNativeContext, str);
        mLogSysVer = iNativeInit;
        if (iNativeInit == 0 || iNativeInit >= 320) {
            isInitialized = true;
        } else {
            i = -1;
        }
        if (iNativeInit >= 320) {
            m_bPrintLog = nativeIsDebug(mNativeContext);
        }
        return i;
    }

    public static int uninitLogLib() {
        if (!isInitialized) {
            return 0;
        }
        int iNativeUninit = nativeUninit(mNativeContext);
        if (iNativeUninit == 0) {
            isInitialized = false;
        }
        return iNativeUninit;
    }

    public static int AddConfigure(String str) {
        if (!mJNILoaded) {
            return voOSType.VOOSMP_ERR_JNI;
        }
        if (!isInitialized) {
            return voOSType.VOOSMP_ERR_Uninitialize;
        }
        if (mLogSysVer >= 320) {
            return nativeAddConfigure(mNativeContext, str);
        }
        return -1;
    }

    private static boolean loadJNI(String str) {
        boolean z = mJNILoaded;
        if (z) {
            return z;
        }
        boolean zLoadOneLibInPath = voLoadJNI.loadOneLibInPath(str);
        mJNILoaded = zLoadOneLibInPath;
        if (zLoadOneLibInPath) {
            return zLoadOneLibInPath;
        }
        boolean zLoadReachableLib = voLoadJNI.loadReachableLib(str, LIB_NAME);
        mJNILoaded = zLoadReachableLib;
        return zLoadReachableLib;
    }
}
