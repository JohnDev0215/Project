package androidx.test.internal.util;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.test.internal.util.ProcSummary;

/* JADX INFO: loaded from: classes5.dex */
public final class LogUtil {
    private static volatile String myProcName = null;

    interface Supplier<T> {
        T get();
    }

    static final /* synthetic */ String lambda$logDebug$0$LogUtil(String message) {
        return message;
    }

    public static void logDebug(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier(message) { // from class: androidx.test.internal.util.LogUtil$$Lambda$0
            private final String arg$1;

            {
                this.arg$1 = message;
            }

            @Override // androidx.test.internal.util.LogUtil.Supplier
            public Object get() {
                return LogUtil.lambda$logDebug$0$LogUtil(this.arg$1);
            }
        }, args);
    }

    private static void logDebug(String tag, Supplier<String> msgSupplier, Object... args) {
        if (isLoggable(tag, 3)) {
            Log.d(tag, String.format(msgSupplier.get(), args));
        }
    }

    static final /* synthetic */ String lambda$logDebugWithProcess$1$LogUtil(String message) {
        String strProcName = procName();
        StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 4 + String.valueOf(strProcName).length());
        sb.append(message);
        sb.append(" in ");
        sb.append(strProcName);
        return sb.toString();
    }

    public static void logDebugWithProcess(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier(message) { // from class: androidx.test.internal.util.LogUtil$$Lambda$1
            private final String arg$1;

            {
                this.arg$1 = message;
            }

            @Override // androidx.test.internal.util.LogUtil.Supplier
            public Object get() {
                return LogUtil.lambda$logDebugWithProcess$1$LogUtil(this.arg$1);
            }
        }, args);
    }

    private static final String procName() {
        String procDesc;
        String procDesc2 = myProcName;
        if (procDesc2 == null) {
            try {
                procDesc = ProcSummary.summarize("self").cmdline;
            } catch (ProcSummary.SummaryException e) {
                procDesc = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            if (procDesc.length() > 64 && procDesc.contains("-classpath")) {
                return "robolectric";
            }
            return procDesc;
        }
        return procDesc2;
    }

    private static boolean isLoggable(String tag, final int level) {
        if (tag.length() > 23) {
            tag = tag.substring(0, 22);
        }
        return Log.isLoggable(tag, level);
    }
}
