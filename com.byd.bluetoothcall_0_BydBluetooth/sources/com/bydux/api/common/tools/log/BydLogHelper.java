package com.bydux.api.common.tools.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bydux.api.common.config.ConfigManager;

/* JADX INFO: loaded from: classes.dex */
public class BydLogHelper {
    private static boolean mLogDEnable = true;
    private static boolean mLogEEnable = true;
    private static boolean mLogVEnable = true;

    private BydLogHelper() {
    }

    private static String getMsg(String str, String str2, int i) {
        String str3 = ("[" + str + "]") + "(" + i + ")";
        if (TextUtils.isEmpty(str2)) {
            return str3;
        }
        return str3 + str2;
    }

    public static void init(Context context) {
        mLogVEnable = ConfigManager.getInstance().getLogvEnable(context);
        mLogDEnable = true;
        mLogEEnable = ConfigManager.getInstance().getLogeEnable(context);
    }

    public static void logD() {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printD("", "", "", 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printD(fileName, stackTrace[3].getMethodName(), "", stackTrace[3].getLineNumber());
        }
    }

    public static void logE() {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printE("", "", "", 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printE(fileName, stackTrace[3].getMethodName(), "", stackTrace[3].getLineNumber());
        }
    }

    public static void logI(String str, String str2, String str3) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printI(str, str2, str3, 0);
            } else {
                printI(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void logV() {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printV("", "", "", 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printV(fileName, stackTrace[3].getMethodName(), "", stackTrace[3].getLineNumber());
        }
    }

    public static void logW(String str, String str2, String str3) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printW(str, str2, str3, 0);
            } else {
                printW(str, str2, str3, stackTrace[3].getLineNumber());
            }
        }
    }

    private static void printD(String str, String str2, String str3, int i) {
        Log.d(str, getMsg(str2, str3, i));
    }

    private static void printE(String str, String str2, String str3, int i) {
        Log.e(str, getMsg(str2, str3, i));
    }

    private static void printI(String str, String str2, String str3, int i) {
        Log.i(str, getMsg(str2, str3, i));
    }

    private static void printV(String str, String str2, String str3, int i) {
        Log.v(str, getMsg(str2, str3, i));
    }

    private static void printW(String str, String str2, String str3, int i) {
        Log.w(str, getMsg(str2, str3, i));
    }

    public static void logI(String str, String str2) {
        logI(str, "", str2);
    }

    public static void logW(String str, String str2) {
        logW(str, "", str2);
    }

    public static void logD(String str) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printD(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
                return;
            }
            printD("", "", str, 0);
        }
    }

    public static void logE(String str) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printE(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
                return;
            }
            printE("", "", str, 0);
        }
    }

    public static void logV(String str) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printV(fileName, stackTrace[3].getMethodName(), str, stackTrace[3].getLineNumber());
                return;
            }
            printV("", "", str, 0);
        }
    }

    public static void logD(String str, String str2) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printD(fileName, str, str2, stackTrace[3].getLineNumber());
                return;
            }
            printD("", str, str2, 0);
        }
    }

    public static void logE(String str, String str2) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printE(fileName, str, str2, stackTrace[3].getLineNumber());
                return;
            }
            printE("", str, str2, 0);
        }
    }

    public static void logV(String str, String str2) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                String fileName = stackTrace[3].getFileName();
                int iLastIndexOf = fileName.lastIndexOf(46);
                if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                    fileName = fileName.substring(0, iLastIndexOf);
                }
                printV(fileName, str, str2, stackTrace[3].getLineNumber());
                return;
            }
            printV("", str, str2, 0);
        }
    }

    public static void logD(String str, String str2, String str3) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                printD(str, str2, str3, stackTrace[3].getLineNumber());
            } else {
                printD(str, str2, str3, 0);
            }
        }
    }

    public static void logE(String str, String str2, String str3) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                printE(str, str2, str3, stackTrace[3].getLineNumber());
            } else {
                printE(str, str2, str3, 0);
            }
        }
    }

    public static void logV(String str, String str2, String str3) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace != null && stackTrace.length >= 4) {
                printV(str, str2, str3, stackTrace[3].getLineNumber());
            } else {
                printV(str, str2, str3, 0);
            }
        }
    }
}
