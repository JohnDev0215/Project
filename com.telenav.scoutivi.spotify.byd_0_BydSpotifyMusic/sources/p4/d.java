package p4;

import java.util.Objects;

/* JADX INFO: compiled from: Preconditions.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static <T> void a(T t6, Class<T> cls) {
        if (t6 != null) {
            return;
        }
        throw new IllegalStateException(cls.getCanonicalName() + " must be set");
    }

    public static <T> T b(T t6) {
        Objects.requireNonNull(t6);
        return t6;
    }

    public static <T> T c(T t6, String str) {
        Objects.requireNonNull(t6, str);
        return t6;
    }

    public static <T> T d(T t6) {
        Objects.requireNonNull(t6, "Cannot return null from a non-@Nullable @Provides method");
        return t6;
    }
}
