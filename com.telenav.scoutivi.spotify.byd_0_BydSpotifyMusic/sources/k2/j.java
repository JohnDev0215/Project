package k2;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Objects;

/* JADX INFO: compiled from: Preconditions.java */
/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static void a(boolean z6, String str) {
        if (!z6) {
            throw new IllegalArgumentException(str);
        }
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        return str;
    }

    public static <T extends Collection<Y>, Y> T c(T t6) {
        if (t6.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        return t6;
    }

    public static <T> T d(T t6) {
        return (T) e(t6, "Argument must not be null");
    }

    public static <T> T e(T t6, String str) {
        Objects.requireNonNull(t6, str);
        return t6;
    }
}
