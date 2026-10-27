package kotlin.r;

import java.util.Iterator;
import kotlin.jvm.internal.h;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public class g extends f {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Iterables.kt */
    public static final class a<T> implements Iterable<T> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ kotlin.r.a f4109b;

        public a(kotlin.r.a aVar) {
            this.f4109b = aVar;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.f4109b.iterator();
        }
    }

    public static <T> Iterable<T> a(kotlin.r.a<? extends T> aVar) {
        h.d(aVar, "$this$asIterable");
        return new a(aVar);
    }
}
