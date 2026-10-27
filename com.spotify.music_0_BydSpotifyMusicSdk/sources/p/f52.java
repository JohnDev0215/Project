package p;

import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class f52 implements io.reactivex.rxjava3.functions.p {
    public final /* synthetic */ AtomicInteger c;
    public final /* synthetic */ u11 e;

    public f52(AtomicInteger atomicInteger, u11 u11Var) {
        this.c = atomicInteger;
        this.e = u11Var;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        int andIncrement = this.c.getAndIncrement();
        this.e.getClass();
        return andIncrement <= 3;
    }
}
