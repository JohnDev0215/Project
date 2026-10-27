package p;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class x1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ z1 e;

    public /* synthetic */ x1(z1 z1Var, int i) {
        this.c = i;
        this.e = z1Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        z1 z1Var = this.e;
        switch (i) {
            case 0:
                tx1 tx1Var = (tx1) obj;
                v03.j(tx1Var, "<name for destructuring parameter 0>");
                return Boolean.valueOf(((com.spotify.remoteconfig.a) z1Var.c.a.get()).h() || tx1Var.f);
            default:
                if (!((Boolean) obj).booleanValue()) {
                    return Observable.empty();
                }
                Observable observableDoOnNext = z1Var.b.a().filter(qo.e).doOnNext(w1.m);
                v03.i(observableDoOnNext, "playerSubscriptions\n    …          )\n            }");
                return observableDoOnNext;
        }
    }
}
