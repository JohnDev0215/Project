package org.greenrobot.eventbus;

import android.os.Looper;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidLogger;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface Logger {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class Default {
        public static Logger get() {
            return (!AndroidLogger.isAndroidLogAvailable() || getAndroidMainLooperOrNull() == null) ? new SystemOutLogger() : new AndroidLogger("EventBus");
        }

        public static Object getAndroidMainLooperOrNull() {
            try {
                return Looper.getMainLooper();
            } catch (RuntimeException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class JavaLogger implements Logger {
        public final java.util.logging.Logger logger;

        public JavaLogger(String str) {
            this.logger = java.util.logging.Logger.getLogger(str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            this.logger.log(level, str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            this.logger.log(level, str, th);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class SystemOutLogger implements Logger {
        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str) {
            System.out.println("[" + level + "] " + str);
        }

        @Override // org.greenrobot.eventbus.Logger
        public void log(Level level, String str, Throwable th) {
            System.out.println("[" + level + "] " + str);
            th.printStackTrace(System.out);
        }
    }

    void log(Level level, String str);

    void log(Level level, String str, Throwable th);
}
