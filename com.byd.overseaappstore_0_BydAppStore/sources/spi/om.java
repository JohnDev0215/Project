package spi;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class om {
    public static final om a = new om();

    private om() {
    }

    @NotNull
    public final String a(@NotNull hf url) {
        Intrinsics.checkNotNullParameter(url, "url");
        String strB = url.b();
        String strD = url.d();
        if (strD == null) {
            return strB;
        }
        return strB + '?' + strD;
    }
}
