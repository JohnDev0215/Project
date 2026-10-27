package com.amazon.mp3.widget;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: UpdatePlayerWidgetWorker.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\t\u001a\u00020\nH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/widget/UpdatePlayerWidgetWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "getContext", "()Landroid/content/Context;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UpdatePlayerWidgetWorker extends CoroutineWorker {
    private final Context context;

    /* JADX INFO: renamed from: com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: UpdatePlayerWidgetWorker.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.widget.UpdatePlayerWidgetWorker", f = "UpdatePlayerWidgetWorker.kt", i = {}, l = {19}, m = "doWork", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UpdatePlayerWidgetWorker.this.doWork(this);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdatePlayerWidgetWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: UpdatePlayerWidgetWorker.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroidx/work/ListenableWorker$Result;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$2", f = "UpdatePlayerWidgetWorker.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UpdatePlayerWidgetWorker.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ActionValidatedPlaybackController playbackController = PlayerWidgetUtils.getPlaybackController();
                    if (UpdatePlayerWidgetWorker.this.getInputData().getBoolean("clear_widget", false)) {
                        PlayerWidgetUtils.clearWidget(UpdatePlayerWidgetWorker.this.getContext());
                    } else {
                        Context context = UpdatePlayerWidgetWorker.this.getContext();
                        this.label = 1;
                        if (PlayerWidgetUtils.setupWidget(context, playbackController, true, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception unused) {
                ListenableWorker.Result.failure();
            }
            return ListenableWorker.Result.success();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object doWork(kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r6) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.mp3.widget.UpdatePlayerWidgetWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$1 r0 = (com.amazon.mp3.widget.UpdatePlayerWidgetWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$1 r0 = new com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)
            goto L47
        L29:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.MainCoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getMain()
            com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$2 r2 = new com.amazon.mp3.widget.UpdatePlayerWidgetWorker$doWork$2
            r4 = 0
            r2.<init>(r4)
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L47
            return r1
        L47:
            java.lang.String r0 = "override suspend fun doW…success()\n        }\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.widget.UpdatePlayerWidgetWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
