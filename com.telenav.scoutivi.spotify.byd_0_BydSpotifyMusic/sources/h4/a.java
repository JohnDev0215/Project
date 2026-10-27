package h4;

import o4.b;

/* JADX INFO: compiled from: EntryPoints.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static <T> T a(Object obj, Class<T> cls) {
        if (obj instanceof o4.a) {
            return cls.cast(obj);
        }
        if (obj instanceof b) {
            return (T) a(((b) obj).f(), cls);
        }
        throw new IllegalStateException(String.format("Given component holder %s does not implement %s or %s", obj.getClass(), o4.a.class, b.class));
    }
}
