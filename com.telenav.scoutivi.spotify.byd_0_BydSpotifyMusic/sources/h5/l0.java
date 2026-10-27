package h5;

import java.util.Set;

/* JADX INFO: compiled from: Sets.kt */
/* JADX INFO: loaded from: classes.dex */
class l0 extends k0 {
    public static final <T> Set<T> d() {
        return b0.f6647e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Set<T> e(Set<? extends T> set) {
        kotlin.jvm.internal.l.e(set, "<this>");
        int size = set.size();
        return size != 0 ? size != 1 ? set : k0.c(set.iterator().next()) : d();
    }
}
