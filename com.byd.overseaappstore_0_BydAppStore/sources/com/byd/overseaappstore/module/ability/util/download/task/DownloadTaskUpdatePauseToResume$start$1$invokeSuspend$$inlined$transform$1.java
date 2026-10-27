package com.byd.overseaappstore.module.ability.util.download.task;

import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u0002H\u008a@¨\u0006\u0004"}, d2 = {ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/flow/FlowCollector;", "", "kotlinx/coroutines/flow/FlowKt__EmittersKt$transform$1", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1", f = "DownloadTaskUpdatePauseToResume.kt", i = {}, l = {40}, m = "invokeSuspend", n = {}, s = {})
public final class DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
    public final /* synthetic */ File $fileSrc$inlined;
    public final /* synthetic */ long $fileSrcLength$inlined;
    public final /* synthetic */ Flow $this_transform;
    private /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ DownloadTaskUpdatePauseToResume this$0;

    /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector, SuspendFunction {
        public final /* synthetic */ FlowCollector<Integer> a;
        public final /* synthetic */ long b;
        public final /* synthetic */ File c;
        public final /* synthetic */ DownloadTaskUpdatePauseToResume d;

        /* JADX INFO: renamed from: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1$1", f = "DownloadTaskUpdatePauseToResume.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {239}, m = "emit", n = {"this", "$this$invokeSuspend_u24lambda_u2d3", "bufferReadArray", "currentDownloadSize", "inputStream", "raf", "readLength", "currentPercentCount"}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$7", "L$8", "I$0"})
        public static final class C00281 extends ContinuationImpl {
            public int I$0;
            public Object L$0;
            public Object L$1;
            public Object L$2;
            public Object L$3;
            public Object L$4;
            public Object L$5;
            public Object L$6;
            public Object L$7;
            public Object L$8;
            public int label;
            public /* synthetic */ Object result;

            public C00281(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(FlowCollector flowCollector, long j, File file, DownloadTaskUpdatePauseToResume downloadTaskUpdatePauseToResume) {
            this.b = j;
            this.c = file;
            this.d = downloadTaskUpdatePauseToResume;
            this.a = flowCollector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00a0 A[Catch: all -> 0x0059, TryCatch #2 {all -> 0x0059, blocks: (B:12:0x0051, B:21:0x0098, B:23:0x00a0, B:25:0x00bb, B:31:0x00e7, B:20:0x0085), top: B:49:0x0027, outer: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00e7 A[Catch: all -> 0x0059, TRY_LEAVE, TryCatch #2 {all -> 0x0059, blocks: (B:12:0x0051, B:21:0x0098, B:23:0x00a0, B:25:0x00bb, B:31:0x00e7, B:20:0x0085), top: B:49:0x0027, outer: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        /* JADX WARN: Type inference failed for: r11v0 */
        /* JADX WARN: Type inference failed for: r11v1, types: [java.io.InputStream, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v6 */
        /* JADX WARN: Type inference failed for: r11v7 */
        /* JADX WARN: Type inference failed for: r11v8 */
        /* JADX WARN: Type inference failed for: r18v0, types: [T] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00df -> B:29:0x00e0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00e4 -> B:29:0x00e0). Please report as a decompilation issue!!! */
        @Override // kotlinx.coroutines.flow.FlowCollector
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(T r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
            /*
                Method dump skipped, instruction units count: 257
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.overseaappstore.module.ability.util.download.task.DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1(Flow flow, Continuation continuation, long j, File file, DownloadTaskUpdatePauseToResume downloadTaskUpdatePauseToResume) {
        super(2, continuation);
        this.$this_transform = flow;
        this.$fileSrcLength$inlined = j;
        this.$fileSrc$inlined = file;
        this.this$0 = downloadTaskUpdatePauseToResume;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1 downloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1 = new DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1(this.$this_transform, continuation, this.$fileSrcLength$inlined, this.$fileSrc$inlined, this.this$0);
        downloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1.L$0 = obj;
        return downloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo6invoke(@NotNull FlowCollector<? super Integer> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((DownloadTaskUpdatePauseToResume$start$1$invokeSuspend$$inlined$transform$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(flowCollector, this.$fileSrcLength$inlined, this.$fileSrc$inlined, this.this$0);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
