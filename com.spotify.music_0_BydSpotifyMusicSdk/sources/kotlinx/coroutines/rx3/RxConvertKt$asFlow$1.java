package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.fz0;
import p.lq2;
import p.py0;
import p.sx1;
import p.y52;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.rx3.RxConvertKt$asFlow$1", f = "RxConvert.kt", l = {95}, m = "invokeSuspend")
final class RxConvertKt$asFlow$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ ObservableSource<Object> $this_asFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RxConvertKt$asFlow$1(ObservableSource observableSource, c00 c00Var) {
        super(2, c00Var);
        this.$this_asFlow = observableSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        RxConvertKt$asFlow$1 rxConvertKt$asFlow$1 = new RxConvertKt$asFlow$1(this.$this_asFlow, c00Var);
        rxConvertKt$asFlow$1.L$0 = obj;
        return rxConvertKt$asFlow$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((RxConvertKt$asFlow$1) a((sx1) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            sx1 sx1Var = (sx1) this.L$0;
            final AtomicReference atomicReference = new AtomicReference();
            this.$this_asFlow.subscribe(new y52(sx1Var, atomicReference));
            py0 py0Var = new py0() { // from class: kotlinx.coroutines.rx3.RxConvertKt$asFlow$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    Disposable andSet = atomicReference.getAndSet(EmptyDisposable.c);
                    if (andSet != null) {
                        andSet.dispose();
                    }
                    return lq2.a;
                }
            };
            this.label = 1;
            if (kotlinx.coroutines.channels.b.a(sx1Var, py0Var, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
