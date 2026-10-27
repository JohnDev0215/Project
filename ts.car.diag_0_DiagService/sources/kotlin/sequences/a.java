package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.b.l;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
final class a<T> implements b<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final kotlin.jvm.b.a<T> f1529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final l<T, T> f1530b;

    /* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
    /* JADX INFO: renamed from: kotlin.sequences.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\n\u0018\u00002\u00020\u0001B\u0011\b\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u00058\u0000@\u0000X\u0086\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0000@\u0000X\u0086\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lkotlin/sequences/a$a;", "Ljava/util/Iterator;", "Lkotlin/Unit;", "a", "()V", "Lkotlin/Any;", "next", "()Ljava/lang/Object;", "Lkotlin/Boolean;", "hasNext", "()Z", "c", "Ljava/lang/Object;", "Lkotlin/Int;", "d", "I", "Lkotlin/sequences/a;", "p0", "<init>", "(Lkotlin/sequences/GeneratorSequence;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class C0047a implements Iterator<T> {

        @Nullable
        private T c;
        private int d = -2;

        C0047a() {
        }

        private final void a() {
            T t;
            if (this.d == -2) {
                t = (T) a.this.f1529a.invoke();
            } else {
                l lVar = a.this.f1530b;
                T t2 = this.c;
                if (t2 == null) {
                    q.h();
                    throw null;
                }
                t = (T) lVar.invoke(t2);
            }
            this.c = t;
            this.d = t == null ? 0 : 1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.d < 0) {
                a();
            }
            return this.d == 1;
        }

        @Override // java.util.Iterator
        @NotNull
        public T next() {
            if (this.d < 0) {
                a();
            }
            if (this.d == 0) {
                throw new NoSuchElementException();
            }
            T t = this.c;
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            this.d = -1;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(@NotNull kotlin.jvm.b.a<? extends T> aVar, @NotNull l<? super T, ? extends T> lVar) {
        q.c(aVar, "getInitialValue");
        q.c(lVar, "getNextValue");
        this.f1529a = aVar;
        this.f1530b = lVar;
    }

    @Override // kotlin.sequences.b
    @NotNull
    public Iterator<T> iterator() {
        return new C0047a();
    }
}
