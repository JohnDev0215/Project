package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.reflect.k;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public interface l<R> extends k<R>, kotlin.jvm.b.a<R> {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/l$a;", "R", "Lkotlin/reflect/k$a;", "Lkotlin/Function0;", "Lkotlin/Any;", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface a<R> extends k.a<R>, kotlin.jvm.b.a<R> {
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    Object getDelegate();

    @NotNull
    a<R> getGetter();
}
