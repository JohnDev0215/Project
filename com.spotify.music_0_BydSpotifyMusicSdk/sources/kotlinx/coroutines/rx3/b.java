package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.h;
import p.dv0;
import p.fz0;
import p.ha0;
import p.qo;
import p.s70;
import p.uf2;
import p.v03;
import p.x52;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements ObservableOnSubscribe {
    public final /* synthetic */ y10 c;
    public final /* synthetic */ dv0 e;

    public /* synthetic */ b(y10 y10Var, dv0 dv0Var) {
        this.c = y10Var;
        this.e = dv0Var;
    }

    @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
    public final void subscribe(ObservableEmitter observableEmitter) {
        h hVar = ha0.b;
        hVar.getClass();
        y10 y10Var = this.c;
        v03.j(y10Var, "context");
        y10 y10VarA = kotlin.coroutines.a.a(hVar, y10Var);
        CoroutineStart coroutineStart = CoroutineStart.k;
        fz0 rxConvertKt$asObservable$1$job$1 = new RxConvertKt$asObservable$1$job$1(this.e, observableEmitter, null);
        y10 y10VarA2 = kotlinx.coroutines.a.a(EmptyCoroutineContext.c, y10VarA, true);
        s70 s70Var = ha0.a;
        if (y10VarA2 != s70Var && y10VarA2.E0(qo.l) == null) {
            y10VarA2 = y10VarA2.o0(s70Var);
        }
        uf2 uf2Var = new uf2(y10VarA2, true, true);
        uf2Var.a0(coroutineStart, uf2Var, rxConvertKt$asObservable$1$job$1);
        observableEmitter.setCancellable(new x52(uf2Var));
    }
}
