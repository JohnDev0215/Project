package kotlin.jvm.internal;

import java.util.Iterator;

/* JADX INFO: compiled from: ArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static final <T> Iterator<T> a(T[] tArr) {
        h.d(tArr, "array");
        return new a(tArr);
    }
}
