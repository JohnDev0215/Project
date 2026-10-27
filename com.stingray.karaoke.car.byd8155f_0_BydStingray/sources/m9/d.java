package m9;

import android.util.Log;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f19341a = new d();

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }

    @Override // java.util.logging.Handler
    public final void publish(LogRecord logRecord) {
        int iMin;
        A7.m.f(logRecord, "record");
        CopyOnWriteArraySet copyOnWriteArraySet = c.f19339a;
        String loggerName = logRecord.getLoggerName();
        A7.m.e(loggerName, "record.loggerName");
        int iIntValue = logRecord.getLevel().intValue();
        Level level = Level.INFO;
        int i7 = iIntValue > level.intValue() ? 5 : logRecord.getLevel().intValue() == level.intValue() ? 4 : 3;
        String message = logRecord.getMessage();
        A7.m.e(message, "record.message");
        Throwable thrown = logRecord.getThrown();
        String strF1 = (String) c.f19340b.get(loggerName);
        if (strF1 == null) {
            strF1 = Q8.h.f1(23, loggerName);
        }
        if (Log.isLoggable(strF1, i7)) {
            if (thrown != null) {
                message = message + '\n' + Log.getStackTraceString(thrown);
            }
            int length = message.length();
            int i10 = 0;
            while (i10 < length) {
                int iJ0 = Q8.h.J0(message, '\n', i10, false, 4);
                if (iJ0 == -1) {
                    iJ0 = length;
                }
                while (true) {
                    iMin = Math.min(iJ0, i10 + 4000);
                    String strSubstring = message.substring(i10, iMin);
                    A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(i7, strF1, strSubstring);
                    if (iMin >= iJ0) {
                        break;
                    } else {
                        i10 = iMin;
                    }
                }
                i10 = iMin + 1;
            }
        }
    }
}
