package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.operators.completable.g;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import p.c00;
import p.lq2;
import p.ry0;
import p.vr2;
import p.w52;
import p.yp;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final kotlinx.coroutines.flow.b a(Observable observable) {
        return new kotlinx.coroutines.flow.b(new RxConvertKt$asFlow$1(observable, null), EmptyCoroutineContext.c, -2, BufferOverflow.c);
    }

    public static final Object b(Completable completable, c00 c00Var) {
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        completable.subscribe(new w52(zpVar));
        Object objQ = zpVar.q();
        return objQ == CoroutineSingletons.c ? objQ : lq2.a;
    }

    public static final Object c(Single single, c00 c00Var) {
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        single.subscribe(new g(1, zpVar));
        return zpVar.q();
    }

    public static final void d(yp ypVar, final Disposable disposable) {
        ((zp) ypVar).t(new ry0() { // from class: kotlinx.coroutines.rx3.RxAwaitKt$disposeOnCancellation$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                disposable.dispose();
                return lq2.a;
            }
        });
    }
}
