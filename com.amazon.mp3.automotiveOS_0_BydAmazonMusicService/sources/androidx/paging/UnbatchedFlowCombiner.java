package androidx.paging;

import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: compiled from: FlowExt.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003Be\u0012[\u0010\u0004\u001aW\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ#\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000Rh\u0010\u0004\u001aW\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013R\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00100\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0018\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Landroidx/paging/UnbatchedFlowCombiner;", PlatformWeblabs.T1, PlatformWeblabs.T2, "", "send", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "t1", "t2", "Landroidx/paging/CombineSource;", "updateFrom", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function4;)V", "initialDispatched", "Lkotlinx/coroutines/CompletableDeferred;", "lock", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/jvm/functions/Function4;", "valueReceived", "", "[Lkotlinx/coroutines/CompletableDeferred;", "values", "[Ljava/lang/Object;", "onNext", "index", "", "value", "(ILjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class UnbatchedFlowCombiner<T1, T2> {
    private final CompletableDeferred<Unit> initialDispatched;
    private final Mutex lock;
    private final Function4<T1, T2, CombineSource, Continuation<? super Unit>, Object> send;
    private final CompletableDeferred<Unit>[] valueReceived;
    private final Object[] values;

    /* JADX INFO: renamed from: androidx.paging.UnbatchedFlowCombiner$onNext$1, reason: invalid class name */
    /* JADX INFO: compiled from: FlowExt.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.UnbatchedFlowCombiner", f = "FlowExt.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2}, l = {188, 227, 205}, m = "onNext", n = {"this", "value", "index", "this", "value", "$this$withLock_u24default$iv", "index", "this", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UnbatchedFlowCombiner<T1, T2> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UnbatchedFlowCombiner<T1, T2> unbatchedFlowCombiner, Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
            this.this$0 = unbatchedFlowCombiner;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.onNext(0, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UnbatchedFlowCombiner(Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super Unit>, ? extends Object> send) {
        Intrinsics.checkNotNullParameter(send, "send");
        this.send = send;
        this.initialDispatched = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.lock = MutexKt.Mutex$default(false, 1, null);
        CompletableDeferred<Unit>[] completableDeferredArr = new CompletableDeferred[2];
        for (int i = 0; i < 2; i++) {
            completableDeferredArr[i] = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        }
        this.valueReceived = completableDeferredArr;
        Object[] objArr = new Object[2];
        for (int i2 = 0; i2 < 2; i2++) {
            objArr[i2] = FlowExtKt.NULL;
        }
        this.values = objArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b2 A[Catch: all -> 0x0117, TryCatch #1 {all -> 0x0117, blocks: (B:34:0x00ab, B:36:0x00b2, B:44:0x00c5, B:46:0x00cd, B:56:0x00e4, B:60:0x00ee, B:58:0x00e9, B:59:0x00ec, B:52:0x00dc, B:42:0x00c1), top: B:75:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00cd A[Catch: all -> 0x0117, TryCatch #1 {all -> 0x0117, blocks: (B:34:0x00ab, B:36:0x00b2, B:44:0x00c5, B:46:0x00cd, B:56:0x00e4, B:60:0x00ee, B:58:0x00e9, B:59:0x00ec, B:52:0x00dc, B:42:0x00c1), top: B:75:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00df A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object onNext(int r17, java.lang.Object r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.UnbatchedFlowCombiner.onNext(int, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
