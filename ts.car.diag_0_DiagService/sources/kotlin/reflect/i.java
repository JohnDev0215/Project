package kotlin.reflect;

import kotlin.Metadata;
import kotlin.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public interface i<T, R> extends m<T, R>, Object<R> {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/i$a;", "T", "R", "Lkotlin/reflect/g;", "Lkotlin/Function2;", "Lkotlin/Any;", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface a<T, R> extends g<R>, kotlin.jvm.b.p<T, R, q> {
    }

    @NotNull
    a<T, R> getSetter();
}
