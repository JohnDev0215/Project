package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.time.DurationKt;

/* JADX INFO: compiled from: TaskLogger.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002\u001a\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/concurrent/Task;", "task", "Lokhttp3/internal/concurrent/TaskQueue;", "queue", "", "message", "", "log", "", "ns", "formatDuration", "okhttp"}, k = 2, mv = {1, 6, 0})
public final class TaskLoggerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void log(Task task, TaskQueue taskQueue, String str) {
        Logger logger = TaskRunner.INSTANCE.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append(taskQueue.getName());
        sb.append(' ');
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(task.getName());
        logger.fine(sb.toString());
    }

    public static final String formatDuration(long j) {
        String str;
        if (j <= -999500000) {
            str = ((j - ((long) 500000000)) / ((long) 1000000000)) + " s ";
        } else if (j <= -999500) {
            str = ((j - ((long) 500000)) / ((long) DurationKt.NANOS_IN_MILLIS)) + " ms";
        } else if (j <= 0) {
            str = ((j - ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j < 999500) {
            str = ((j + ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j < 999500000) {
            str = ((j + ((long) 500000)) / ((long) DurationKt.NANOS_IN_MILLIS)) + " ms";
        } else {
            str = ((j + ((long) 500000000)) / ((long) 1000000000)) + " s ";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str2 = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        return str2;
    }
}
