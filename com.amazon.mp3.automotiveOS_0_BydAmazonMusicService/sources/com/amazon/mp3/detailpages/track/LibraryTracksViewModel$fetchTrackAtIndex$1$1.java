package com.amazon.mp3.detailpages.track;

import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged;
import com.google.android.gms.cast.MediaError;
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

/* JADX INFO: compiled from: LibraryTracksViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.detailpages.track.LibraryTracksViewModel$fetchTrackAtIndex$1$1", f = "LibraryTracksViewModel.kt", i = {}, l = {MediaError.DetailedErrorCode.DASH_NO_INIT}, m = "invokeSuspend", n = {}, s = {})
final class LibraryTracksViewModel$fetchTrackAtIndex$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ Subscriber<? super MusicTrack> $it;
    int label;
    final /* synthetic */ LibraryTracksViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryTracksViewModel$fetchTrackAtIndex$1$1(LibraryTracksViewModel libraryTracksViewModel, Subscriber<? super MusicTrack> subscriber, int i, Continuation<? super LibraryTracksViewModel$fetchTrackAtIndex$1$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryTracksViewModel;
        this.$it = subscriber;
        this.$index = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryTracksViewModel$fetchTrackAtIndex$1$1(this.this$0, this.$it, this.$index, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryTracksViewModel$fetchTrackAtIndex$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RefinablePageDataRepository refinablePageDataRepository = (RefinablePageDataRepository) this.this$0.getPageDataRepository();
            this.label = 1;
            obj = refinablePageDataRepository.getDataSource(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged<com.amazon.mp3.library.item.MusicTrack>");
        }
        this.$it.onNext(this.this$0.getTrackAtIndex(this.$index, (DatabaseSourcePaged) obj));
        return Unit.INSTANCE;
    }
}
