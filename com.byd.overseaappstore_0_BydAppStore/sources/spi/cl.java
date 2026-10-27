package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class cl {
    private cl() {
    }

    public static <T> void a(T t, Class<T> cls) {
        if (t != null) {
            return;
        }
        throw new IllegalStateException(cls.getCanonicalName() + " must be set");
    }
}
