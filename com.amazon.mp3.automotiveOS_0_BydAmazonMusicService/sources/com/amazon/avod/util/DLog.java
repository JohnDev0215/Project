package com.amazon.avod.util;

import android.util.Log;
import com.google.common.base.Objects;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class DLog {
    public static final OutputChannel ANDROID_LOG;
    private static final Logger LOGGER = LoggerFactory.getLogger("AmazonVideo");
    public static final OutputChannel NO_OP;
    private static final OutputChannel OUTPUT_CHANNEL_DEFAULT;
    public static final OutputChannel SYSTEM_OUT;
    private static boolean sDevMessagesEnabled;
    private static OutputChannel sOutputChannel;
    private static boolean sReflectiveTagGenerationEnabled;
    private static boolean sShowEntryExitEnabled;
    private static boolean sShowFullExceptionsEnabled;
    private static boolean sShowThreadIdsEnabled;
    private static boolean sShowTimestampsEnabled;
    private static boolean sStripSensitiveData;
    private static boolean sTracingEnabled;

    public interface OutputChannel {
        void print(int i, String str, String str2);

        void print(int i, String str, String str2, Throwable th);
    }

    static {
        OutputChannel outputChannel = new OutputChannel() { // from class: com.amazon.avod.util.DLog.1
            public String toString() {
                return "Android logger";
            }

            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2) {
                if (i == 3) {
                    DLog.LOGGER.debug(str2);
                    return;
                }
                if (i == 4) {
                    DLog.LOGGER.info(str2);
                    return;
                }
                if (i == 5) {
                    DLog.LOGGER.warn(str2);
                } else if (i != 6) {
                    DLog.LOGGER.trace(str2);
                } else {
                    DLog.LOGGER.error(str2);
                }
            }

            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2, Throwable th) {
                if (i == 3) {
                    DLog.LOGGER.debug(str2, th);
                    return;
                }
                if (i == 4) {
                    DLog.LOGGER.info(str2, th);
                    return;
                }
                if (i == 5) {
                    DLog.LOGGER.warn(str2, th);
                } else if (i != 6) {
                    DLog.LOGGER.trace(str2, th);
                } else {
                    DLog.LOGGER.error(str2, th);
                }
            }
        };
        ANDROID_LOG = outputChannel;
        SYSTEM_OUT = new OutputChannel() { // from class: com.amazon.avod.util.DLog.2
            public String toString() {
                return "standard output console";
            }

            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2) {
                System.out.println(str + ": " + str2);
            }

            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2, Throwable th) {
                System.out.println(str + ": " + str2);
                if (th != null) {
                    th.printStackTrace();
                }
            }
        };
        NO_OP = new OutputChannel() { // from class: com.amazon.avod.util.DLog.3
            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2) {
            }

            @Override // com.amazon.avod.util.DLog.OutputChannel
            public void print(int i, String str, String str2, Throwable th) {
            }

            public String toString() {
                return "no-op";
            }
        };
        OUTPUT_CHANNEL_DEFAULT = outputChannel;
        sOutputChannel = outputChannel;
        sTracingEnabled = true;
        sDevMessagesEnabled = false;
        sShowEntryExitEnabled = false;
        sReflectiveTagGenerationEnabled = false;
        sShowThreadIdsEnabled = false;
        sShowTimestampsEnabled = false;
        sShowFullExceptionsEnabled = false;
        sStripSensitiveData = true;
    }

    private DLog() {
    }

    public static boolean isStripSensitiveDataEnabled() {
        return sStripSensitiveData;
    }

    public static void logf(String str) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(str));
        }
    }

    public static void logf(String str, Object obj) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj)));
        }
    }

    public static void logf(String str, Object obj, Object obj2) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2)));
        }
    }

    public static void logf(String str, Object obj, Object obj2, Object obj3) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3)));
        }
    }

    public static void logf(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4)));
        }
    }

    public static void logf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5)));
        }
    }

    public static void logf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5, obj6)));
        }
    }

    public static void logf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (sTracingEnabled) {
            sOutputChannel.print(4, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5, obj6, obj7)));
        }
    }

    public static void devf(String str) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(str));
        }
    }

    public static void devf(String str, Object obj) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj)));
        }
    }

    public static void devf(String str, Object obj, Object obj2) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2)));
        }
    }

    public static void devf(String str, Object obj, Object obj2, Object obj3) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3)));
        }
    }

    public static void devf(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4)));
        }
    }

    public static void devf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5)));
        }
    }

    public static void devf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5, obj6)));
        }
    }

    public static void devf(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (sDevMessagesEnabled && sTracingEnabled) {
            sOutputChannel.print(3, makeTag(), makeText(String.format(Locale.US, str, obj, obj2, obj3, obj4, obj5, obj6, obj7)));
        }
    }

    public static void wtf(String str, String str2, Throwable th) {
        if (sTracingEnabled) {
            sOutputChannel.print(6, str, makeText("WTF EXCEPTION : ", str2), th);
            Log.wtf(str, str2, th);
        }
    }

    public static void warnf(String str) {
        if (sTracingEnabled) {
            sOutputChannel.print(5, makeTag(), makeText("Warning: ", str));
        }
    }

    public static void warnf(String str, Object... objArr) {
        if (sTracingEnabled) {
            sOutputChannel.print(5, makeTag(), makeText("Warning: ", String.format(Locale.US, str, objArr)));
        }
    }

    public static void errorf(String str) {
        if (sTracingEnabled) {
            sOutputChannel.print(6, makeTag(), makeText("ERROR: ", str));
        }
    }

    public static void errorf(String str, Object... objArr) {
        if (sTracingEnabled) {
            sOutputChannel.print(6, makeTag(), makeText("ERROR: ", String.format(Locale.US, str, objArr)));
        }
    }

    @Deprecated
    public static void exceptionf(Throwable th) {
        if (sTracingEnabled) {
            String strMakeTag = makeTag();
            if (sShowFullExceptionsEnabled) {
                sOutputChannel.print(6, strMakeTag, makeText("EXCEPTION: ", th.getClass().getName(), " ", th.getLocalizedMessage()));
                logFullExceptionStackTrace(strMakeTag, th);
            } else {
                sOutputChannel.print(6, strMakeTag, makeText("EXCEPTION ", th.getClass().getSimpleName(), " in ", stackFrame(1), ": ", th.getMessage()));
            }
        }
    }

    public static void exceptionf(Throwable th, String str, Object... objArr) {
        if (sTracingEnabled) {
            String strMakeTag = makeTag();
            if (sShowFullExceptionsEnabled) {
                sOutputChannel.print(6, strMakeTag, makeText("EXCEPTION: ", String.format(Locale.US, str, objArr), ": ", th.getClass().getName(), " ", th.getLocalizedMessage()));
                logFullExceptionStackTrace(strMakeTag, th);
            } else {
                sOutputChannel.print(6, strMakeTag, makeText("EXCEPTION ", th.getClass().getSimpleName(), " in ", stackFrame(1), ": ", String.format(Locale.US, str, objArr), ": ", th.getMessage()));
            }
        }
    }

    private static void logFullExceptionStackTrace(@Nonnull String str, @Nonnull Throwable th) {
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sOutputChannel.print(6, str, makeText(stackTraceElement));
        }
    }

    public static void enterf(String str) {
        if (sTracingEnabled && sShowEntryExitEnabled) {
            sOutputChannel.print(2, makeTag(), makeText("ENTER: ", stackFrame(1), " from ", stackFrame(2), ": ", str));
        }
    }

    public static void enterf(String str, Object obj) {
        if (sTracingEnabled && sShowEntryExitEnabled) {
            sOutputChannel.print(2, makeTag(), makeText("ENTER: ", stackFrame(1), " from ", stackFrame(2), ": ", String.format(Locale.US, str, obj)));
        }
    }

    public static void enterf(String str, Object obj, Object obj2, Object obj3) {
        if (sTracingEnabled && sShowEntryExitEnabled) {
            sOutputChannel.print(2, makeTag(), makeText("ENTER: ", stackFrame(1), " from ", stackFrame(2), ": ", String.format(Locale.US, str, obj, obj2, obj3)));
        }
    }

    public static final String maskString(Object obj) {
        return obj == null ? "null" : ((obj instanceof String) && Objects.equal(obj, "")) ? "" : isStripSensitiveDataEnabled() ? "xxaivxx" : obj.toString();
    }

    public static String sanitize(Object obj, int i) {
        String string = obj != null ? obj.toString() : null;
        if (!sStripSensitiveData || obj == null) {
            return string;
        }
        String string2 = obj.toString();
        return string2.substring(0, Math.min(i, string2.length() / 2)) + "...";
    }

    public static String sanitize(Object obj, String... strArr) {
        String string = obj != null ? obj.toString() : null;
        if (!sStripSensitiveData || obj == null) {
            return string;
        }
        String string2 = obj.toString();
        for (String str : strArr) {
            string2 = string2.replaceAll(str, "<REMOVED>");
        }
        return string2;
    }

    public static String sanitize_avod_message(Object obj) {
        return sanitize(obj, "deviceID=[^&]*", "deviceId=[^&]*", "sprefix=[^&]*", "query=[^&]*", "bulkActions=[^&]*", "\"url\":\"http[^\"]*", "customer A[A-Z0-9]*", "pin=[^&]*", "customerId=[^&]+", "networkId=[^&]+", "\\((\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\)", "phrase=[^&]+");
    }

    private static String stackFrame(int i) {
        return prettify(new Throwable().getStackTrace()[Math.min(i, r0.length - 2) + 1]);
    }

    public static void logHugeString(String str, String str2) {
        if (!sTracingEnabled) {
            return;
        }
        int length = str2.length();
        String strConcat = str.concat("[%d-%d): %s");
        String strMakeTag = makeTag();
        int i = 0;
        while (true) {
            int iMin = Math.min(length, i + 100);
            sOutputChannel.print(3, strMakeTag, makeText(String.format(Locale.US, strConcat, Integer.valueOf(i), Integer.valueOf(iMin), str2.substring(i, iMin))));
            if (iMin == length) {
                return;
            } else {
                i = iMin;
            }
        }
    }

    private static String makeTag() {
        if (!sReflectiveTagGenerationEnabled) {
            return "AmazonVideo";
        }
        return "AmazonVideo." + new Throwable().getStackTrace()[2].getClassName().split("\\.", 0)[3];
    }

    private static String makeText(Object... objArr) {
        if (!sReflectiveTagGenerationEnabled && !sShowTimestampsEnabled && !sShowThreadIdsEnabled && objArr.length == 1) {
            Object obj = objArr[0];
            if (!(obj instanceof byte[]) && !(obj instanceof Object[])) {
                return String.valueOf(obj);
            }
        }
        StringBuilder sb = new StringBuilder(512);
        if (sReflectiveTagGenerationEnabled) {
            sb.append(stackFrame(2));
            sb.append(": ");
        }
        if (sShowTimestampsEnabled) {
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss: ", Locale.US).format(new Date()));
        }
        if (sShowThreadIdsEnabled) {
            sb.append("T");
            sb.append(Thread.currentThread().getId());
            sb.append(": ");
        }
        addFlattenedArray(sb, objArr);
        return sb.toString();
    }

    private static void addFlattenedArray(StringBuilder sb, Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                sb.append("null");
            } else if (obj instanceof byte[]) {
                addHexString(sb, (byte[]) obj);
            } else if (obj instanceof Object[]) {
                addFlattenedArray(sb, (Object[]) obj);
            } else {
                sb.append(obj.toString());
            }
        }
    }

    private static void addHexString(StringBuilder sb, byte[] bArr) {
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
    }

    private static String prettify(StackTraceElement stackTraceElement) {
        return stackTraceElement.getClassName().split("\\.")[r0.length - 1] + "." + stackTraceElement.getMethodName();
    }
}
