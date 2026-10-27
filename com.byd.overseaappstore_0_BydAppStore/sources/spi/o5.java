package spi;

import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class o5 {

    @NotNull
    public static final o5 a = new o5();

    private o5() {
    }

    @NotNull
    public final String a(@Nullable Long l) {
        if (l == null) {
            return "";
        }
        if (l.longValue() <= 0) {
            return "0B";
        }
        int iLog10 = (int) (Math.log10(l.longValue()) / Math.log10(1024.0d));
        double dLongValue = l.longValue() / Math.pow(1024.0d, iLog10);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.ENGLISH, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(dLongValue)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        return e.d(str, "  ", new String[]{"B", "KB", "MB", "GB", "TB"}[iLog10]);
    }
}
