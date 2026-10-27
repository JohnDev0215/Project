package spi;

import android.util.Log;
import java.util.Objects;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class z extends Handler {
    public static final z a = new z();

    private z() {
    }

    @Override // java.util.logging.Handler
    public final void close() {
    }

    @Override // java.util.logging.Handler
    public final void flush() {
    }

    @Override // java.util.logging.Handler
    public final void publish(@NotNull LogRecord record) {
        int iMin;
        Intrinsics.checkNotNullParameter(record, "record");
        y yVar = y.c;
        String loggerName = record.getLoggerName();
        Intrinsics.checkNotNullExpressionValue(loggerName, "record.loggerName");
        int i = record.getLevel().intValue() > Level.INFO.intValue() ? 5 : record.getLevel().intValue() == Level.INFO.intValue() ? 4 : 3;
        String message = record.getMessage();
        Intrinsics.checkNotNullExpressionValue(message, "record.message");
        Throwable thrown = record.getThrown();
        Objects.requireNonNull(yVar);
        Intrinsics.checkNotNullParameter(loggerName, "loggerName");
        Intrinsics.checkNotNullParameter(message, "message");
        String strTake = y.b.get(loggerName);
        if (strTake == null) {
            strTake = StringsKt___StringsKt.take(loggerName, 23);
        }
        if (Log.isLoggable(strTake, i)) {
            if (thrown != null) {
                StringBuilder sbM = e.m(message, "\n");
                sbM.append(Log.getStackTraceString(thrown));
                message = sbM.toString();
            }
            int i2 = 0;
            int length = message.length();
            while (i2 < length) {
                int iJ = StringsKt.j(message, '\n', i2, 4);
                if (iJ == -1) {
                    iJ = length;
                }
                while (true) {
                    iMin = Math.min(iJ, i2 + 4000);
                    String strSubstring = message.substring(i2, iMin);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Log.println(i, strTake, strSubstring);
                    if (iMin >= iJ) {
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
