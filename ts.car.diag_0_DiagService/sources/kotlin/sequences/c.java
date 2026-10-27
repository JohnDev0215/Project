package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.RestrictsSuspension;
import kotlin.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.3")
@RestrictsSuspension
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0000B\t\b\u0016¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lkotlin/sequences/c;", "T", "Lkotlin/sequences/b;", "sequence", "Lkotlin/coroutines/a;", "p1", "Lkotlin/Any;", "c", "(Lkotlin/sequences/b;Lkotlin/coroutines/a;)Ljava/lang/Object;", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class c<T> {
    @Nullable
    public abstract Object a(T t, @NotNull kotlin.coroutines.a<? super q> aVar);

    @Nullable
    public abstract Object b(@NotNull Iterator<? extends T> it, @NotNull kotlin.coroutines.a<? super q> aVar);

    @Nullable
    public final Object c(@NotNull b<? extends T> sequence, @NotNull kotlin.coroutines.a<? super q> p1) {
        return b(sequence.iterator(), p1);
    }
}
