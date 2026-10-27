package h.a.a.a;

/* JADX INFO: compiled from: LogLevel.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final String a(int i) {
        if (i == 0) {
            return "LOG_OFF";
        }
        if (i == 1) {
            return "LOG_FATAL";
        }
        if (i == 2) {
            return "LOG_ERROR";
        }
        if (i == 3) {
            return "LOG_WARN";
        }
        if (i == 4) {
            return "LOG_NOTICE";
        }
        if (i == 5) {
            return "LOG_INFO";
        }
        if (i == 6) {
            return "LOG_DEBUG";
        }
        if (i == 7) {
            return "LOG_VERBOSE";
        }
        if (i == 8) {
            return "LOG_DEFAULT";
        }
        if (i == 10) {
            return "LOG_MAX";
        }
        return "0x" + Integer.toHexString(i);
    }
}
