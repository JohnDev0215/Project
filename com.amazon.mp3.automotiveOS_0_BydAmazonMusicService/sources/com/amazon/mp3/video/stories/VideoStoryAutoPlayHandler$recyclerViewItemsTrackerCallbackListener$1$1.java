package com.amazon.mp3.video.stories;

import android.view.TextureView;
import com.amazon.mp3.video.stories.ThrottleTrackingBus;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: VideoStoryAutoPlayHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.video.stories.VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1", f = "VideoStoryAutoPlayHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ThrottleTrackingBus.VisibleState $visibleState;
    int label;
    final /* synthetic */ VideoStoryAutoPlayHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1(VideoStoryAutoPlayHandler videoStoryAutoPlayHandler, ThrottleTrackingBus.VisibleState visibleState, Continuation<? super VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1> continuation) {
        super(2, continuation);
        this.this$0 = videoStoryAutoPlayHandler;
        this.$visibleState = visibleState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1(this.this$0, this.$visibleState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoStoryAutoPlayHandler$recyclerViewItemsTrackerCallbackListener$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        TextureView textureView = (TextureView) this.this$0.autoPlayViews.get(Boxing.boxInt(this.this$0.currentIndex));
        if (textureView != null) {
            textureView.setVisibility(4);
        }
        VideoStoryAutoPlayHandler videoStoryAutoPlayHandler = this.this$0;
        videoStoryAutoPlayHandler.previousIndex = videoStoryAutoPlayHandler.currentIndex;
        this.this$0.currentIndex = this.$visibleState.getFirstCompletelyVisible();
        TextureView textureView2 = (TextureView) this.this$0.autoPlayViews.get(Boxing.boxInt(this.this$0.currentIndex));
        if (textureView2 != null) {
            textureView2.setVisibility(0);
        }
        VideoStoryAutoPlayHandler.prepare$default(this.this$0, true, false, 2, null);
        return Unit.INSTANCE;
    }
}
