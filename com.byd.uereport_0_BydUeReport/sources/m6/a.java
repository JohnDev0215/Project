package m6;

import java.util.Objects;

/* JADX INFO: compiled from: $Gson$Preconditions.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static void a(boolean z7) {
        if (!z7) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T b(T t7) {
        Objects.requireNonNull(t7);
        return t7;
    }
}
