package com.byd.autoiot.utils;

import android.util.Log;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class BydAutoIotSdkLog {
    private static final String TAG = "AutoIoTsdK-";
    public static boolean enable = true;
    public static boolean enableTrace = true;

    public static void enable(boolean z) {
        enable = z;
    }

    public static void enableTrace(boolean z) {
        enableTrace = z;
    }

    public static void d(String str, String str2) {
        if (enable) {
            if (!enableTrace) {
                Log.d(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.d(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2);
        }
    }

    public static void dDefaultTag(String str) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.d(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.d(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.d(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2, th);
        }
    }

    public static void dDefaultTag(String str, Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.d(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str, th);
        }
    }

    public static void i(String str, String str2) {
        if (enable) {
            if (!enableTrace) {
                Log.i(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.i(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2);
        }
    }

    public static void iDefaultTag(String str) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.i(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.i(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.i(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2, th);
        }
    }

    public static void iDefaultTag(String str, Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.i(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str, th);
        }
    }

    public static void w(String str, String str2) {
        if (enable) {
            if (!enableTrace) {
                Log.w(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2);
        }
    }

    public static void wDefaultTag(String str) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.w(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2, th);
        }
    }

    public static void wDefaultTag(String str, Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str, th);
        }
    }

    public static void w(String str, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.w(TAG + str, "" + th);
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + th);
        }
    }

    public static void wDefaultTag(Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.w(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + th);
        }
    }

    public static void e(String str, String str2) {
        if (enable) {
            if (!enableTrace) {
                Log.e(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.e(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2);
        }
    }

    public static void eDefaultTag(String str) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.e(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.e(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.e(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2, th);
        }
    }

    public static void eDefaultTag(String str, Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.e(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str, th);
        }
    }

    public static void v(String str, String str2) {
        if (enable) {
            if (!enableTrace) {
                Log.e(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.v(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2);
        }
    }

    public static void vDefaultTag(String str) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.v(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (enable) {
            if (!enableTrace) {
                Log.v(TAG + str, str2);
                return;
            }
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.v(TAG + str, Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str2, th);
        }
    }

    public static void vDefaultTag(String str, Throwable th) {
        if (enable) {
            StackTraceElement stackTraceElement = getStackTraceElement();
            Log.v(TAG + stackTraceElement.getClassName(), Ini.SECTION_PREFIX + Thread.currentThread().getName() + "][" + (stackTraceElement.getMethodName() + "(" + stackTraceElement.getLineNumber() + ")") + Ini.SECTION_SUFFIX + str, th);
        }
    }

    private static StackTraceElement getStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 2;
        while (i < 6 && stackTrace[i].getClassName().equals(BydAutoIotSdkLog.class.getName())) {
            i++;
        }
        return stackTrace[i];
    }

    public static void printStackTrace() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(stackTraceElement.getClassName());
            sb.append(".").append(stackTraceElement.getMethodName());
            sb.append("(").append(stackTraceElement.getClassName()).append(":");
            sb.append(stackTraceElement.getLineNumber()).append(")").append("\n");
        }
        w(TAG, "printStackTrace" + ((Object) sb));
    }
}
