package kotlin;

import kotlin.Result;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: renamed from: kotlin.f, reason: from Kotlin metadata */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0011\u0010\u0006\u001a\u00020\u0005*\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/Throwable;", "exception", "Lkotlin/Any;", "a", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "Lkotlin/Unit;", "b", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class Throwable {
    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static final Object a(@NotNull java.lang.Throwable th) {
        kotlin.jvm.internal.q.c(th, "exception");
        return new Result.Failure(th);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    public static final void b(@NotNull Object obj) throws java.lang.Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
