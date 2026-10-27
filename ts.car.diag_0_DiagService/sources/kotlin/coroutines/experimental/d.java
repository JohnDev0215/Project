package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.experimental.c;
import kotlin.jvm.b.p;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lkotlin/coroutines/experimental/d;", "Lkotlin/coroutines/experimental/c;", "Lkotlin/coroutines/experimental/c$b;", "key", "Lkotlin/coroutines/experimental/c$a;", "a", "(Lkotlin/coroutines/experimental/c$b;)Lkotlin/coroutines/experimental/c$a;", "Lkotlin/Any;", "initial", "Lkotlin/Function2;", "operation", "fold", "(Ljava/lang/Object;Lkotlin/jvm/b/p;)Ljava/lang/Object;", "b", "(Lkotlin/coroutines/experimental/c$b;)Lkotlin/coroutines/experimental/c;", "Lkotlin/Int;", "hashCode", "()I", "Lkotlin/String;", "toString", "()Ljava/lang/String;", "<init>", "()V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class d implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f1495b = new d();

    private d() {
    }

    @Override // kotlin.coroutines.experimental.c
    @Nullable
    public <E extends c.a> E a(@NotNull c.b<E> key) {
        q.c(key, "key");
        return null;
    }

    @Override // kotlin.coroutines.experimental.c
    @NotNull
    public c b(@NotNull c.b<?> key) {
        q.c(key, "key");
        return this;
    }

    @Override // kotlin.coroutines.experimental.c
    public <R> R fold(R initial, @NotNull p<? super R, ? super c.a, ? extends R> operation) {
        q.c(operation, "operation");
        return initial;
    }

    public int hashCode() {
        return 0;
    }

    @NotNull
    public String toString() {
        return "EmptyCoroutineContext";
    }
}
