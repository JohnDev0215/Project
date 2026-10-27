package androidx.work;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "androidx.work.CoroutineWorker$getForegroundInfoAsync$1", f = "CoroutineWorker.kt", l = {134}, m = "invokeSuspend")
final class CoroutineWorker$getForegroundInfoAsync$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ a $jobFuture;
    Object L$0;
    int label;
    final /* synthetic */ CoroutineWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(a aVar, CoroutineWorker coroutineWorker, c00 c00Var) {
        super(2, c00Var);
        this.$jobFuture = aVar;
        this.this$0 = coroutineWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new CoroutineWorker$getForegroundInfoAsync$1(this.$jobFuture, this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        CoroutineWorker$getForegroundInfoAsync$1 coroutineWorker$getForegroundInfoAsync$1 = (CoroutineWorker$getForegroundInfoAsync$1) a((e20) obj, (c00) obj2);
        lq2 lq2Var = lq2.a;
        coroutineWorker$getForegroundInfoAsync$1.i(lq2Var);
        return lq2Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a aVar = (a) this.L$0;
            kotlin.a.d(obj);
            aVar.c.j(obj);
            return lq2.a;
        }
        kotlin.a.d(obj);
        a aVar2 = this.$jobFuture;
        CoroutineWorker coroutineWorker = this.this$0;
        this.L$0 = aVar2;
        this.label = 1;
        coroutineWorker.getClass();
        throw new IllegalStateException("Not implemented");
    }
}
