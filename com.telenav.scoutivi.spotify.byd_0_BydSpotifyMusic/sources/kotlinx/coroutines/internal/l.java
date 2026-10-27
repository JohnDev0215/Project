package kotlinx.coroutines.internal;

import java.util.ArrayList;

/* JADX INFO: compiled from: InlineList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l<E> {
    public static Object a(Object obj) {
        return obj;
    }

    public static /* synthetic */ Object b(Object obj, int i7, kotlin.jvm.internal.g gVar) {
        if ((i7 & 1) != 0) {
            obj = null;
        }
        return a(obj);
    }

    public static final Object c(Object obj, E e7) {
        if (obj == null) {
            return a(e7);
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(e7);
            return a(obj);
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(e7);
        return a(arrayList);
    }
}
