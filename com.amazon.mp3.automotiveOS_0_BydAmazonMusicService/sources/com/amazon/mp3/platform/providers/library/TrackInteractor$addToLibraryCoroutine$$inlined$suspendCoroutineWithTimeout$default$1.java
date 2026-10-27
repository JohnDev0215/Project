package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import android.os.Bundle;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1", f = "TrackInteractor.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
public final class TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ EntityItemIdentifier $identifier$inlined;
    int label;
    final /* synthetic */ TrackInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1(Continuation continuation, TrackInteractor trackInteractor, EntityItemIdentifier entityItemIdentifier) {
        super(2, continuation);
        this.this$0 = trackInteractor;
        this.$identifier$inlined = entityItemIdentifier;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1(continuation, this.this$0, this.$identifier$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            JobQueue jobQueue = this.this$0.getJobQueue();
            final Context context = this.this$0.getContext();
            final String identifier = this.$identifier$inlined.getId().getIdentifier();
            final TrackInteractor trackInteractor = this.this$0;
            jobQueue.addJob(new AddPrimeTrackJob(context, identifier) { // from class: com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$2$1
                @Override // com.amazon.mp3.service.job.Job
                protected void onFinished(int resultCode, Bundle resultBundle) {
                    super.onFinished(resultCode, resultBundle);
                    cancellableContinuationImpl.resumeWith(trackInteractor.m1379getJobResultIoAF18A(resultCode));
                }
            });
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
