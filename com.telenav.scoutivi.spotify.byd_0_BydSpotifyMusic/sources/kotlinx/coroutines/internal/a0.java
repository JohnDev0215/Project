package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlinx.coroutines.internal.z;

/* JADX INFO: compiled from: ConcurrentLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a0<S extends z<S>> {
    public static Object a(Object obj) {
        return obj;
    }

    public static final S b(Object obj) {
        if (obj == f.f7628a) {
            throw new IllegalStateException("Does not contain segment".toString());
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type S");
        return (S) obj;
    }

    public static final boolean c(Object obj) {
        return obj == f.f7628a;
    }
}
