package x;

/* JADX INFO: loaded from: classes.dex */
public interface s0 {
    Object a();

    default boolean b(Object obj, Object obj2) {
        return obj.equals(a()) && obj2.equals(c());
    }

    Object c();
}
