package androidx.test.internal.platform.util;

import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class InstrumentationParameterUtil {
    public static long getTimeoutMillis(String key, long defaultValue) {
        Checks.checkArgument(defaultValue != 0, "default timeout value cannot be zero");
        long value = Long.parseLong(InstrumentationRegistry.getArguments().getString(key, "0"));
        if (value == 0) {
            value = defaultValue;
        }
        if (value < 0) {
            return TimeUnit.DAYS.toMillis(1L);
        }
        return value;
    }
}
