package com.amazon.mp3.detailpages.actions;

import com.amazon.mp3.library.item.MusicTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import rx.Subscriber;

/* JADX INFO: compiled from: DefaultTrackInformationFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher$getAllTracks$1$1", f = "DefaultTrackInformationFetcher.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
final class DefaultTrackInformationFetcher$getAllTracks$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Subscriber<? super List<? extends MusicTrack>> $subscriber;
    int label;
    final /* synthetic */ DefaultTrackInformationFetcher this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultTrackInformationFetcher$getAllTracks$1$1(DefaultTrackInformationFetcher defaultTrackInformationFetcher, Subscriber<? super List<? extends MusicTrack>> subscriber, Continuation<? super DefaultTrackInformationFetcher$getAllTracks$1$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultTrackInformationFetcher;
        this.$subscriber = subscriber;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultTrackInformationFetcher$getAllTracks$1$1(this.this$0, this.$subscriber, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultTrackInformationFetcher$getAllTracks$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DefaultTrackInformationFetcher defaultTrackInformationFetcher = this.this$0;
            this.label = 1;
            obj = defaultTrackInformationFetcher.getTracks(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$subscriber.onNext((List) obj);
        this.$subscriber.onCompleted();
        return Unit.INSTANCE;
    }
}
