package kotlin.p;

import kotlin.jvm.b.l;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public class h extends g {
    public static <T> d<T> a(kotlin.jvm.b.a<? extends T> aVar, l<? super T, ? extends T> lVar) {
        kotlin.jvm.internal.i.c(aVar, "seedFunction");
        kotlin.jvm.internal.i.c(lVar, "nextFunction");
        return new c(aVar, lVar);
    }
}
