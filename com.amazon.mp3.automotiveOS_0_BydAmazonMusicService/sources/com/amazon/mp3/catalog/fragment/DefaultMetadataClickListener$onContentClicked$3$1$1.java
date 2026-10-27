package com.amazon.mp3.catalog.fragment;

import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.providers.RecentsPlaybackProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DefaultMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.DefaultMetadataClickListener$onContentClicked$3$1$1", f = "DefaultMetadataClickListener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DefaultMetadataClickListener$onContentClicked$3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentMetadata $contentMetadata;
    final /* synthetic */ PageType $pageType;
    final /* synthetic */ RecentlyPlayedItem $recentlyPlayedItem;
    int label;
    final /* synthetic */ DefaultMetadataClickListener this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultMetadataClickListener$onContentClicked$3$1$1(DefaultMetadataClickListener defaultMetadataClickListener, RecentlyPlayedItem recentlyPlayedItem, PageType pageType, ContentMetadata contentMetadata, Continuation<? super DefaultMetadataClickListener$onContentClicked$3$1$1> continuation) {
        super(2, continuation);
        this.this$0 = defaultMetadataClickListener;
        this.$recentlyPlayedItem = recentlyPlayedItem;
        this.$pageType = pageType;
        this.$contentMetadata = contentMetadata;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultMetadataClickListener$onContentClicked$3$1$1(this.this$0, this.$recentlyPlayedItem, this.$pageType, this.$contentMetadata, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultMetadataClickListener$onContentClicked$3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FragmentActivity activity = this.this$0.getMFragment().getActivity();
        if (activity != null) {
            RecentlyPlayedItem recentlyPlayedItem = this.$recentlyPlayedItem;
            PageType pageType = this.$pageType;
            ContentMetadata contentMetadata = this.$contentMetadata;
            RecentsPlaybackProvider recentsPlaybackProvider = new RecentsPlaybackProvider(activity);
            Intrinsics.checkNotNullExpressionValue(recentlyPlayedItem, "recentlyPlayedItem");
            recentsPlaybackProvider.startPlayback(recentlyPlayedItem, pageType, contentMetadata.getViewId());
        }
        return Unit.INSTANCE;
    }
}
