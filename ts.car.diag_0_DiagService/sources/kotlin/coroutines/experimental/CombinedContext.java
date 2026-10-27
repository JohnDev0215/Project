package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.c;
import kotlin.jvm.b.p;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010#\u001a\u00020\u0001\u0012\u0006\u0010$\u001a\u00020\u0005¢\u0006\u0004\b%\u0010&J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001c\u0010\u0004J\u000f\u0010\u001e\u001a\u00020\u001dH\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0017\u001a\u00020\u00018\u0000@\u0000¢\u0006\u0006\n\u0004\b\u0017\u0010 R\u0013\u0010!\u001a\u00020\u00058\u0000@\u0000¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006'"}, d2 = {"Lkotlin/coroutines/experimental/CombinedContext;", "Lkotlin/coroutines/experimental/c;", "Lkotlin/Int;", "f", "()I", "Lkotlin/coroutines/experimental/c$a;", "element", "Lkotlin/Boolean;", "d", "(Lkotlin/coroutines/experimental/c$a;)Z", "context", "e", "(Lkotlin/coroutines/experimental/CombinedContext;)Z", "Lkotlin/coroutines/experimental/c$b;", "key", "a", "(Lkotlin/coroutines/experimental/c$b;)Lkotlin/coroutines/experimental/c$a;", "Lkotlin/Any;", "initial", "Lkotlin/Function2;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/b/p;)Ljava/lang/Object;", "b", "(Lkotlin/coroutines/experimental/c$b;)Lkotlin/coroutines/experimental/c;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "Lkotlin/coroutines/experimental/c;", "c", "Lkotlin/coroutines/experimental/c$a;", "p0", "p1", "<init>", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/CoroutineContext$Element;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class CombinedContext implements c {

    @NotNull
    private final c b;

    @NotNull
    private final c.a c;

    public CombinedContext(@NotNull c cVar, @NotNull c.a aVar) {
        q.c(cVar, "left");
        q.c(aVar, "element");
        this.b = cVar;
        this.c = aVar;
    }

    private final boolean d(c.a element) {
        return q.a(a(element.getKey()), element);
    }

    private final boolean e(CombinedContext context) {
        while (d(context.c)) {
            c cVar = context.b;
            if (!(cVar instanceof CombinedContext)) {
                if (cVar != null) {
                    return d((c.a) cVar);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
            context = (CombinedContext) cVar;
        }
        return false;
    }

    private final int f() {
        c cVar = this.b;
        if (cVar instanceof CombinedContext) {
            return ((CombinedContext) cVar).f() + 1;
        }
        return 2;
    }

    @Override // kotlin.coroutines.experimental.c
    @Nullable
    public <E extends c.a> E a(@NotNull c.b<E> key) {
        q.c(key, "key");
        while (true) {
            E e = (E) this.c.a(key);
            if (e != null) {
                return e;
            }
            c cVar = this.b;
            if (!(cVar instanceof CombinedContext)) {
                return (E) cVar.a(key);
            }
            this = (CombinedContext) cVar;
        }
    }

    @Override // kotlin.coroutines.experimental.c
    @NotNull
    public c b(@NotNull c.b<?> key) {
        q.c(key, "key");
        if (this.c.a(key) != null) {
            return this.b;
        }
        c cVarB = this.b.b(key);
        return cVarB == this.b ? this : cVarB == d.f1495b ? this.c : new CombinedContext(cVarB, this.c);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof CombinedContext) {
                CombinedContext combinedContext = (CombinedContext) other;
                if (combinedContext.f() != f() || !combinedContext.e(this)) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // kotlin.coroutines.experimental.c
    public <R> R fold(R initial, @NotNull p<? super R, ? super c.a, ? extends R> operation) {
        q.c(operation, "operation");
        return operation.invoke((Object) this.b.fold(initial, operation), this.c);
    }

    public int hashCode() {
        return this.b.hashCode() + this.c.hashCode();
    }

    @NotNull
    public String toString() {
        return "[" + ((String) fold("", new p<String, c.a, String>() { // from class: kotlin.coroutines.experimental.CombinedContext.toString.1
            @Override // kotlin.jvm.b.p
            @NotNull
            public final String invoke(@NotNull String str, @NotNull c.a aVar) {
                q.c(str, "acc");
                q.c(aVar, "element");
                if (str.length() == 0) {
                    return aVar.toString();
                }
                return str + ", " + aVar;
            }
        })) + "]";
    }
}
