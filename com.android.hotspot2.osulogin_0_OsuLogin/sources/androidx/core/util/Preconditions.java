package androidx.core.util;

/* JADX INFO: loaded from: classes.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }
}
