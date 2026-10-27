package kotlin.reflect;

import kotlin.Metadata;
import kotlin.jvm.b.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public interface j<D, E, R> extends n<D, E, R>, Object<R> {

    /* JADX INFO: compiled from: caradapter-bug */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0003\u0010\u0001*\u0004\b\u0004\u0010\u0002*\u0004\b\u0005\u0010\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001\u0007¨\u0006\b"}, d2 = {"Lkotlin/reflect/j$a;", "D", "E", "R", "Lkotlin/reflect/g;", "Lkotlin/Function3;", "Lkotlin/Any;", "a", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
    public interface a<D, E, R> extends g<R>, q<D, E, R, kotlin.q> {
    }

    @NotNull
    a<D, E, R> getSetter();
}
