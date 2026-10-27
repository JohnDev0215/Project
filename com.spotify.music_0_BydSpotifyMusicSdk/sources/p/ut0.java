package p;

import com.spotify.rcs.resolver.grpc.v0.Configuration;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public final class ut0 {
    public final cg2 a;

    public ut0(cg2 cg2Var) {
        this.a = cg2Var;
    }

    public final io.reactivex.rxjava3.internal.operators.completable.b a(Configuration configuration, String str) {
        Completable completableIgnoreElement = Single.just(configuration).doOnEvent(u11.q).map(new g10(str, 1)).doOnError(yn.l).onErrorReturnItem(((eg2) this.a).c.b()).doOnSuccess(new du(7, this)).ignoreElement();
        io.reactivex.rxjava3.internal.functions.c cVar = io.reactivex.rxjava3.internal.functions.k.g;
        completableIgnoreElement.getClass();
        return new io.reactivex.rxjava3.internal.operators.completable.b(completableIgnoreElement, cVar, 3);
    }
}
