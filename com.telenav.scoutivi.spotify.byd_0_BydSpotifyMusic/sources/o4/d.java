package o4;

import java.util.Objects;

/* JADX INFO: compiled from: Preconditions.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(boolean z6, String str, Object... objArr) {
        if (!z6) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T b(T t6) {
        Objects.requireNonNull(t6);
        return t6;
    }

    public static <T> T c(T t6, String str) {
        Objects.requireNonNull(t6, str);
        return t6;
    }

    public static void d(boolean z6, String str, Object... objArr) {
        if (!z6) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }
}
