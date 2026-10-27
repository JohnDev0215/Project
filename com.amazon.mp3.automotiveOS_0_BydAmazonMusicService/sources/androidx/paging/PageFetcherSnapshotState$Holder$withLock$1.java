package androidx.paging;

import androidx.paging.PageFetcherSnapshotState;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: PageFetcherSnapshotState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "androidx.paging.PageFetcherSnapshotState$Holder", f = "PageFetcherSnapshotState.kt", i = {0, 0, 0}, l = {403}, m = "withLock", n = {"this", "block", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
final class PageFetcherSnapshotState$Holder$withLock$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshotState.Holder<Key, Value> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshotState$Holder$withLock$1(PageFetcherSnapshotState.Holder<Key, Value> holder, Continuation<? super PageFetcherSnapshotState$Holder$withLock$1> continuation) {
        super(continuation);
        this.this$0 = holder;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to androidx.paging.PageFetcherSnapshotState$Holder$withLock$1<T> for r1v1 'this'  kotlin.coroutines.Continuation
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
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r1.this$0
            r0 = 0
            java.lang.Object r2 = r2.withLock(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshotState$Holder$withLock$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
