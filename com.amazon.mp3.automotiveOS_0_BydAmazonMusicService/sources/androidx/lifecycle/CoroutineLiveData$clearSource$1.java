package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: CoroutineLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.lifecycle.CoroutineLiveData", f = "CoroutineLiveData.kt", i = {0}, l = {234}, m = "clearSource$lifecycle_livedata_ktx_release", n = {"this"}, s = {"L$0"})
final class CoroutineLiveData$clearSource$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CoroutineLiveData<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CoroutineLiveData$clearSource$1(CoroutineLiveData<T> coroutineLiveData, Continuation<? super CoroutineLiveData$clearSource$1> continuation) {
        super(continuation);
        this.this$0 = coroutineLiveData;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to androidx.lifecycle.CoroutineLiveData$clearSource$1 for r1v1 'this'  kotlin.coroutines.Continuation
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r2) {
        /*
            r1 = this;
            r1.result = r2
            int r2 = r1.label
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 | r0
            r1.label = r2
            androidx.lifecycle.CoroutineLiveData<T> r2 = r1.this$0
            java.lang.Object r2 = r2.clearSource$lifecycle_livedata_ktx_release(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.CoroutineLiveData$clearSource$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
