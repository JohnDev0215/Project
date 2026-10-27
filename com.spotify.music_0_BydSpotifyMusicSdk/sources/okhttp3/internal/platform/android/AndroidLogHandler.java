package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.text.b;
import p.og2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidLogHandler extends Handler {
    public static final AndroidLogHandler a = new AndroidLogHandler();

    private AndroidLogHandler() {
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int iMin;
        v03.j(logRecord, "record");
        AndroidLog androidLog = AndroidLog.a;
        String loggerName = logRecord.getLoggerName();
        v03.i(loggerName, "record.loggerName");
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        int i = iIntValue > level.intValue() ? 5 : logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        String message = logRecord.getMessage();
        v03.i(message, "record.message");
        Throwable thrown = logRecord.getThrown();
        androidLog.getClass();
        String strH0 = (String) AndroidLog.c.get(loggerName);
        if (strH0 == null) {
            strH0 = og2.H0(23, loggerName);
        }
        if (Log.isLoggable(strH0, i)) {
            if (thrown != null) {
                message = message + '\n' + Log.getStackTraceString(thrown);
            }
            int length = message.length();
            int i2 = 0;
            while (i2 < length) {
                int iN0 = b.n0(message, '\n', i2, false, 4);
                if (iN0 == -1) {
                    iN0 = length;
                }
                while (true) {
                    iMin = Math.min(iN0, i2 + 4000);
                    String strSubstring = message.substring(i2, iMin);
                    v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i, strH0, strSubstring);
                    if (iMin >= iN0) {
                        break;
                    } else {
                        i2 = iMin;
                    }
                }
                i2 = iMin + 1;
            }
        }
    }
}
