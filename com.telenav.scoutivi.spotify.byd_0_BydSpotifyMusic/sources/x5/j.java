package x5;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public class j extends i {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Iterator f9863a;

        public a(Iterator it) {
            this.f9863a = it;
        }

        @Override // x5.d
        public Iterator<T> iterator() {
            return this.f9863a;
        }
    }

    public static <T> d<T> c(Iterator<? extends T> it) {
        kotlin.jvm.internal.l.e(it, "<this>");
        return d(new a(it));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> d<T> d(d<? extends T> dVar) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        return dVar instanceof x5.a ? dVar : new x5.a(dVar);
    }
}
