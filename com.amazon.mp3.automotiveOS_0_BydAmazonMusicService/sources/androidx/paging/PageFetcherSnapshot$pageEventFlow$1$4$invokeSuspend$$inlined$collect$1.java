package androidx.paging;

import androidx.mediarouter.media.MediaRouter;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1 implements FlowCollector<Unit> {
    final /* synthetic */ CoroutineScope $$this$launch$inlined;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX INFO: renamed from: androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1", f = "PageFetcherSnapshot.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 12, 12, 12, 13, 13, 13, 13, 14, 14, 15, 15, 15}, l = {142, 164, GeneratorAdapter.GT, 181, 169, 195, 213, GeneratorAdapter.GT, 224, 169, 235, 247, GeneratorAdapter.GT, MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_REMOVED, 169, voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_POSTPROCESSING}, m = "emit", n = {"this", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this", "this_$iv", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "this_$iv", "loadType", "this", "this_$iv", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "$this$withLock_u24default$iv$iv", "this", "loadType", "this_$iv", "$this$withLock_u24default$iv$iv", "this", "loadType", "this", "this_$iv", "$this$withLock_u24default$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$0", "L$1", "L$2"})
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    public PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1(PageFetcherSnapshot pageFetcherSnapshot, CoroutineScope coroutineScope) {
        this.this$0 = pageFetcherSnapshot;
        this.$$this$launch$inlined = coroutineScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x038f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x041c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0488 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0289 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x031c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x031d  */
    /* JADX WARN: Type inference failed for: r12v0, types: [kotlin.Unit] */
    /* JADX WARN: Type inference failed for: r12v1, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v100 */
    /* JADX WARN: Type inference failed for: r12v101 */
    /* JADX WARN: Type inference failed for: r12v103 */
    /* JADX WARN: Type inference failed for: r12v104 */
    /* JADX WARN: Type inference failed for: r12v16, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v2, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v3, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v43, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v74, types: [kotlinx.coroutines.sync.Mutex] */
    /* JADX WARN: Type inference failed for: r12v96 */
    /* JADX WARN: Type inference failed for: r12v97 */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object emit(kotlin.Unit r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            Method dump skipped, instruction units count: 1292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot$pageEventFlow$1$4$invokeSuspend$$inlined$collect$1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
