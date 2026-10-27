package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.ObservableEmitter;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.dv0;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1", f = "RxConvert.kt", l = {114}, m = "invokeSuspend")
final class RxConvertKt$asObservable$1$job$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ ObservableEmitter<Object> $emitter;
    final /* synthetic */ dv0 $this_asObservable;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RxConvertKt$asObservable$1$job$1(dv0 dv0Var, ObservableEmitter observableEmitter, c00 c00Var) {
        super(2, c00Var);
        this.$this_asObservable = dv0Var;
        this.$emitter = observableEmitter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        RxConvertKt$asObservable$1$job$1 rxConvertKt$asObservable$1$job$1 = new RxConvertKt$asObservable$1$job$1(this.$this_asObservable, this.$emitter, c00Var);
        rxConvertKt$asObservable$1$job$1.L$0 = obj;
        return rxConvertKt$asObservable$1$job$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((RxConvertKt$asObservable$1$job$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            java.lang.Object r0 = r7.L$0
            p.e20 r0 = (p.e20) r0
            kotlin.a.d(r8)     // Catch: java.lang.Throwable -> L11
            goto L38
        L11:
            r8 = move-exception
            goto L42
        L13:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L1b:
            kotlin.a.d(r8)
            java.lang.Object r8 = r7.L$0
            p.e20 r8 = (p.e20) r8
            p.dv0 r1 = r7.$this_asObservable     // Catch: java.lang.Throwable -> L3e
            p.jv0 r3 = new p.jv0     // Catch: java.lang.Throwable -> L3e
            io.reactivex.rxjava3.core.ObservableEmitter<java.lang.Object> r4 = r7.$emitter     // Catch: java.lang.Throwable -> L3e
            r5 = 3
            r3.<init>(r5, r4)     // Catch: java.lang.Throwable -> L3e
            r7.L$0 = r8     // Catch: java.lang.Throwable -> L3e
            r7.label = r2     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r1 = r1.c(r3, r7)     // Catch: java.lang.Throwable -> L3e
            if (r1 != r0) goto L37
            return r0
        L37:
            r0 = r8
        L38:
            io.reactivex.rxjava3.core.ObservableEmitter<java.lang.Object> r8 = r7.$emitter     // Catch: java.lang.Throwable -> L11
            r8.onComplete()     // Catch: java.lang.Throwable -> L11
            goto L66
        L3e:
            r0 = move-exception
            r6 = r0
            r0 = r8
            r8 = r6
        L42:
            boolean r1 = r8 instanceof java.util.concurrent.CancellationException
            if (r1 != 0) goto L61
            io.reactivex.rxjava3.core.ObservableEmitter<java.lang.Object> r2 = r7.$emitter
            boolean r2 = r2.tryOnError(r8)
            if (r2 != 0) goto L66
            p.y10 r0 = r0.j()
            if (r1 == 0) goto L55
            goto L66
        L55:
            io.reactivex.rxjava3.plugins.RxJavaPlugins.b(r8)     // Catch: java.lang.Throwable -> L59
            goto L66
        L59:
            r1 = move-exception
            kotlin.a.a(r8, r1)
            p.ux2.m(r0, r8)
            goto L66
        L61:
            io.reactivex.rxjava3.core.ObservableEmitter<java.lang.Object> r8 = r7.$emitter
            r8.onComplete()
        L66:
            p.lq2 r8 = p.lq2.a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.RxConvertKt$asObservable$1$job$1.i(java.lang.Object):java.lang.Object");
    }
}
