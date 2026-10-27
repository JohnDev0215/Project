package com.amazon.music.media.auto.tab.search;

import com.amazon.music.search.EverythingSearchRequest;
import com.amazon.music.search.Search;
import com.amazon.music.search.SearchResult;
import com.amazon.music.search.SearchService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextSearchProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/search/SearchResult;", "kotlin.jvm.PlatformType", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.media.auto.tab.search.TextSearchProvider$executeTenzingRequest$2$1", f = "TextSearchProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class TextSearchProvider$executeTenzingRequest$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SearchResult>, Object> {
    final /* synthetic */ SearchService $it;
    final /* synthetic */ EverythingSearchRequest $request;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextSearchProvider$executeTenzingRequest$2$1(SearchService searchService, EverythingSearchRequest everythingSearchRequest, Continuation<? super TextSearchProvider$executeTenzingRequest$2$1> continuation) {
        super(2, continuation);
        this.$it = searchService;
        this.$request = everythingSearchRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextSearchProvider$executeTenzingRequest$2$1(this.$it, this.$request, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super SearchResult> continuation) {
        return ((TextSearchProvider$executeTenzingRequest$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new Search(this.$it).search(this.$request);
    }
}
