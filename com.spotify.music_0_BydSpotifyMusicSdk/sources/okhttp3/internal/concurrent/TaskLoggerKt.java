package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Logger;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class TaskLoggerKt {
    public static final void a(Task task, TaskQueue taskQueue, String str) {
        TaskRunner.h.getClass();
        Logger logger = TaskRunner.j;
        StringBuilder sb = new StringBuilder();
        sb.append(taskQueue.b);
        sb.append(' ');
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        v03.i(str2, "format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(task.a);
        logger.fine(sb.toString());
    }

    public static final String b(long j) {
        String str;
        if (j <= -999500000) {
            str = ((j - ((long) 500000000)) / ((long) 1000000000)) + " s ";
        } else if (j <= -999500) {
            str = ((j - ((long) 500000)) / ((long) 1000000)) + " ms";
        } else if (j <= 0) {
            str = ((j - ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j < 999500) {
            str = ((j + ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j < 999500000) {
            str = ((j + ((long) 500000)) / ((long) 1000000)) + " ms";
        } else {
            str = ((j + ((long) 500000000)) / ((long) 1000000000)) + " s ";
        }
        String str2 = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        v03.i(str2, "format(format, *args)");
        return str2;
    }
}
