package p;

import com.google.common.base.Optional;
import com.spotify.cosmos.rxrouter.RxRouter;

/* JADX INFO: loaded from: classes.dex */
public final class yy2 implements io.reactivex.rxjava3.functions.o {
    public static final yy2 c = new yy2();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        Optional optional = (Optional) obj;
        v03.j(optional, "obj");
        return (RxRouter) optional.c();
    }
}
