package g6;

import java.util.Arrays;
import java.util.logging.Logger;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.u;

/* JADX INFO: compiled from: TaskLogger.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final String b(long j7) {
        String str;
        if (j7 <= -999500000) {
            str = ((j7 - ((long) 500000000)) / ((long) 1000000000)) + " s ";
        } else if (j7 <= -999500) {
            str = ((j7 - ((long) 500000)) / ((long) 1000000)) + " ms";
        } else if (j7 <= 0) {
            str = ((j7 - ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j7 < 999500) {
            str = ((j7 + ((long) 500)) / ((long) 1000)) + " µs";
        } else if (j7 < 999500000) {
            str = ((j7 + ((long) 500000)) / ((long) 1000000)) + " ms";
        } else {
            str = ((j7 + ((long) 500000000)) / ((long) 1000000000)) + " s ";
        }
        u uVar = u.f7511a;
        String str2 = String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
        l.d(str2, "java.lang.String.format(format, *args)");
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(a aVar, d dVar, String str) {
        Logger loggerA = e.f6579j.a();
        StringBuilder sb = new StringBuilder();
        sb.append(dVar.f());
        sb.append(' ');
        u uVar = u.f7511a;
        String str2 = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        l.d(str2, "java.lang.String.format(format, *args)");
        sb.append(str2);
        sb.append(": ");
        sb.append(aVar.b());
        loggerA.fine(sb.toString());
    }
}
