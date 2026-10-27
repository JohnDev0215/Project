package spi;

import android.content.Context;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class s8 {
    static {
        new s8();
    }

    private s8() {
    }

    @JvmStatic
    public static final <T> T a(@NotNull Context context, @NotNull Class<T> entryPoint) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entryPoint, "entryPoint");
        return (T) t8.a(o4.a(context.getApplicationContext()), entryPoint);
    }
}
