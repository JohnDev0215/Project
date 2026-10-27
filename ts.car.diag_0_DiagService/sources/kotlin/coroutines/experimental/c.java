package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.b.p;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
public interface c {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0007R\u0016\u0010\u0006\u001a\u00020\u000386@\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lkotlin/coroutines/experimental/c$a;", "Lkotlin/coroutines/experimental/c;", "Lkotlin/Any;", "Lkotlin/coroutines/experimental/c$b;", "getKey", "()Lkotlin/coroutines/experimental/c$b;", "key", "a", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface a extends c {
        @Override // kotlin.coroutines.experimental.c
        @Nullable
        <E extends a> E a(@NotNull b<E> bVar);

        @NotNull
        b<?> getKey();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/experimental/c$b;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "E", "Lkotlin/Any;", "b", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface b<E extends a> {
    }

    @Nullable
    <E extends a> E a(@NotNull b<E> bVar);

    @NotNull
    c b(@NotNull b<?> bVar);

    <R> R fold(R r, @NotNull p<? super R, ? super a, ? extends R> pVar);
}
