package com.bydux.api.common.tools.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bydux.api.common.config.ConfigManager;

/* JADX INFO: loaded from: classes5.dex */
public class BydLogHelper {
    private static boolean mLogDEnable = true;
    private static boolean mLogEEnable = true;
    private static boolean mLogVEnable = true;

    private BydLogHelper() {
    }

    public static void init(Context context) {
        mLogVEnable = ConfigManager.getInstance().getLogvEnable(context);
        mLogDEnable = true;
        mLogEEnable = ConfigManager.getInstance().getLogeEnable(context);
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

    public static void logV(String content) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printV("", "", content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printV(fileName, stackTrace[3].getMethodName(), content, stackTrace[3].getLineNumber());
        }
    }

    public static void logV(String func, String content) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printV("", func, content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printV(fileName, func, content, stackTrace[3].getLineNumber());
        }
    }

    public static void logV(String tag, String func, String content) {
        if (mLogVEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printV(tag, func, content, 0);
            } else {
                printV(tag, func, content, stackTrace[3].getLineNumber());
            }
        }
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

    public static void logD(String content) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printD("", "", content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printD(fileName, stackTrace[3].getMethodName(), content, stackTrace[3].getLineNumber());
        }
    }

    public static void logD(String func, String content) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printD("", func, content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printD(fileName, func, content, stackTrace[3].getLineNumber());
        }
    }

    public static void logD(String tag, String func, String content) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printD(tag, func, content, 0);
            } else {
                printD(tag, func, content, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void logI(String tag, String func, String content) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printI(tag, func, content, 0);
            } else {
                printI(tag, func, content, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void logI(String tag, String content) {
        logI(tag, "", content);
    }

    public static void logW(String tag, String func, String content) {
        if (mLogDEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printW(tag, func, content, 0);
            } else {
                printW(tag, func, content, stackTrace[3].getLineNumber());
            }
        }
    }

    public static void logW(String tag, String content) {
        logW(tag, "", content);
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

    public static void logE(String content) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printE("", "", content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printE(fileName, stackTrace[3].getMethodName(), content, stackTrace[3].getLineNumber());
        }
    }

    public static void logE(String func, String content) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printE("", func, content, 0);
                return;
            }
            String fileName = stackTrace[3].getFileName();
            int iLastIndexOf = fileName.lastIndexOf(46);
            if (iLastIndexOf > -1 && iLastIndexOf < fileName.length()) {
                fileName = fileName.substring(0, iLastIndexOf);
            }
            printE(fileName, func, content, stackTrace[3].getLineNumber());
        }
    }

    public static void logE(String tag, String func, String content) {
        if (mLogEEnable) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace == null || stackTrace.length < 4) {
                printE(tag, func, content, 0);
            } else {
                printE(tag, func, content, stackTrace[3].getLineNumber());
            }
        }
    }

    private static void printV(String tag, String func, String content, int lineNumber) {
        Log.v(tag, getMsg(func, content, lineNumber));
    }

    private static void printD(String tag, String func, String content, int lineNumber) {
        Log.d(tag, getMsg(func, content, lineNumber));
    }

    private static void printI(String tag, String func, String content, int lineNumber) {
        Log.i(tag, getMsg(func, content, lineNumber));
    }

    private static void printW(String tag, String func, String content, int lineNumber) {
        Log.w(tag, getMsg(func, content, lineNumber));
    }

    private static void printE(String tag, String func, String content, int lineNumber) {
        Log.e(tag, getMsg(func, content, lineNumber));
    }

    private static String getMsg(String func, String content, int lineNumber) {
        String str = ("[" + func + "]") + "(" + lineNumber + ")";
        if (TextUtils.isEmpty(content)) {
            return str;
        }
        return str + content;
    }
}
