package ts.car.usb.log;

import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class Logger {
    private static final int DEFAULT_MIN_LOG_LEVEL = 3;
    private static final String DEFAULT_TAG = "ExtUsbManager";
    private static final Set<String> IGNORED_CLASS_NAMES;
    private static final int NAME_SIZE = 3;
    private final String messagePrefix;
    private int minLogLevel;
    private final String tag;

    static {
        HashSet hashSet = new HashSet(3);
        IGNORED_CLASS_NAMES = hashSet;
        hashSet.add("dalvik.system.VMStack");
        IGNORED_CLASS_NAMES.add("java.lang.Thread");
        IGNORED_CLASS_NAMES.add(Logger.class.getCanonicalName());
    }

    public Logger(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public Logger(String messagePrefix) {
        this(DEFAULT_TAG, messagePrefix);
    }

    public Logger(String tag, String messagePrefix) {
        String str;
        this.minLogLevel = 3;
        this.tag = tag;
        String prefix = messagePrefix == null ? getCallerSimpleName() : messagePrefix;
        if (prefix.length() > 0) {
            str = prefix + ": ";
        } else {
            str = prefix;
        }
        this.messagePrefix = str;
    }

    public Logger() {
        this(DEFAULT_TAG, null);
    }

    public Logger(int minLogLevel) {
        this(DEFAULT_TAG, null);
        this.minLogLevel = minLogLevel;
    }

    public void setMinLogLevel(int minLogLevel) {
        this.minLogLevel = minLogLevel;
    }

    public boolean isLoggable(int logLevel) {
        return logLevel >= this.minLogLevel || Log.isLoggable(this.tag, logLevel);
    }

    private static String getCallerSimpleName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement elem : stackTrace) {
            String className = elem.getClassName();
            if (!IGNORED_CLASS_NAMES.contains(className)) {
                String[] classParts = className.split("\\.");
                return classParts[classParts.length - 1];
            }
        }
        return Logger.class.getSimpleName();
    }

    private String toMessage(String format, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.messagePrefix);
        sb.append(args.length > 0 ? String.format(format, args) : format);
        return sb.toString();
    }

    public void v(String format, Object... args) {
        if (isLoggable(2)) {
            Log.v(this.tag, toMessage(format, args));
        }
    }

    public void v(Throwable t, String format, Object... args) {
        if (isLoggable(2)) {
            Log.v(this.tag, toMessage(format, args), t);
        }
    }

    public void d(String format, Object... args) {
        if (isLoggable(3)) {
            Log.d(this.tag, toMessage(format, args));
        }
    }

    public void d(Throwable t, String format, Object... args) {
        if (isLoggable(3)) {
            Log.d(this.tag, toMessage(format, args), t);
        }
    }

    public void i(String format, Object... args) {
        if (isLoggable(4)) {
            Log.i(this.tag, toMessage(format, args));
        }
    }

    public void i(Throwable t, String format, Object... args) {
        if (isLoggable(4)) {
            Log.i(this.tag, toMessage(format, args), t);
        }
    }

    public void w(String format, Object... args) {
        if (isLoggable(5)) {
            Log.w(this.tag, toMessage(format, args));
        }
    }

    public void w(Throwable t, String format, Object... args) {
        if (isLoggable(5)) {
            Log.w(this.tag, toMessage(format, args), t);
        }
    }

    public void e(String format, Object... args) {
        if (isLoggable(6)) {
            Log.e(this.tag, toMessage(format, args));
        }
    }

    public void e(Throwable t, String format, Object... args) {
        if (isLoggable(6)) {
            Log.e(this.tag, toMessage(format, args), t);
        }
    }
}
