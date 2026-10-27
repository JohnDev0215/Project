package m6;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/* JADX INFO: compiled from: AndroidLog.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d f8413a = new d();

    private d() {
    }

    @Override // java.util.logging.Handler
    public void close() {
    }

    @Override // java.util.logging.Handler
    public void flush() {
    }

    @Override // java.util.logging.Handler
    public void publish(LogRecord record) {
        kotlin.jvm.internal.l.e(record, "record");
        c cVar = c.f8412c;
        String loggerName = record.getLoggerName();
        kotlin.jvm.internal.l.d(loggerName, "record.loggerName");
        int iB = e.b(record);
        String message = record.getMessage();
        kotlin.jvm.internal.l.d(message, "record.message");
        cVar.a(loggerName, iB, message, record.getThrown());
    }
}
