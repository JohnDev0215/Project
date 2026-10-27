package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class dl {
    private dl() {
    }

    public static void a(boolean z, @NonNull String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    @NonNull
    public static <T> T b(@Nullable T t) {
        Objects.requireNonNull(t, "Argument must not be null");
        return t;
    }
}
