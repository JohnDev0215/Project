package kotlin.coroutines;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.b.p;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/CoroutineContext;", "Lkotlin/Any;", "b", "a", "DefaultImpls", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public interface CoroutineContext {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public static final class DefaultImpls {
        @NotNull
        public static CoroutineContext a(CoroutineContext coroutineContext, @NotNull CoroutineContext coroutineContext2) {
            q.c(coroutineContext2, "context");
            return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, new p<CoroutineContext, a, CoroutineContext>() { // from class: kotlin.coroutines.CoroutineContext.plus.1
                @Override // kotlin.jvm.b.p
                @NotNull
                public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext3, @NotNull a aVar) {
                    q.c(coroutineContext3, "acc");
                    q.c(aVar, "element");
                    CoroutineContext coroutineContextMinusKey = coroutineContext3.minusKey(aVar.getKey());
                    if (coroutineContextMinusKey == EmptyCoroutineContext.INSTANCE) {
                        return aVar;
                    }
                    kotlin.coroutines.b bVar = (kotlin.coroutines.b) coroutineContextMinusKey.get(kotlin.coroutines.b.f1489b);
                    if (bVar == null) {
                        return new CombinedContext(coroutineContextMinusKey, aVar);
                    }
                    CoroutineContext coroutineContextMinusKey2 = coroutineContextMinusKey.minusKey(kotlin.coroutines.b.f1489b);
                    return coroutineContextMinusKey2 == EmptyCoroutineContext.INSTANCE ? new CombinedContext(aVar, bVar) : new CombinedContext(new CombinedContext(coroutineContextMinusKey2, aVar), bVar);
                }
            });
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0007R\u0016\u0010\u0006\u001a\u00020\u000386@\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"Lkotlin/coroutines/CoroutineContext$a;", "Lkotlin/coroutines/CoroutineContext;", "Lkotlin/Any;", "Lkotlin/coroutines/CoroutineContext$b;", "getKey", "()Lkotlin/coroutines/CoroutineContext$b;", "key", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface a extends CoroutineContext {
        @Override // kotlin.coroutines.CoroutineContext
        @Nullable
        <E extends a> E get(@NotNull b<E> bVar);

        @NotNull
        b<?> getKey();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/coroutines/CoroutineContext$b;", "Lkotlin/coroutines/CoroutineContext$Element;", "E", "Lkotlin/Any;", "b", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface b<E extends a> {
    }

    <R> R fold(R r, @NotNull p<? super R, ? super a, ? extends R> pVar);

    @Nullable
    <E extends a> E get(@NotNull b<E> bVar);

    @NotNull
    CoroutineContext minusKey(@NotNull b<?> bVar);

    @NotNull
    CoroutineContext plus(@NotNull CoroutineContext coroutineContext);
}
