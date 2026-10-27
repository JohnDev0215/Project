package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class t8 {
    private t8() {
    }

    public static <T> T a(Object obj, Class<T> cls) {
        if (obj instanceof wc) {
            return cls.cast(obj);
        }
        if (obj instanceof xc) {
            return (T) a(((xc) obj).a(), cls);
        }
        throw new IllegalStateException(String.format("Given component holder %s does not implement %s or %s", obj.getClass(), wc.class, xc.class));
    }
}
